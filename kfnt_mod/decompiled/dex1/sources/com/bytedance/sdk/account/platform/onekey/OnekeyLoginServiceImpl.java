package com.bytedance.sdk.account.platform.onekey;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;
import anet.channel.strategy.dispatch.DispatchConstants;
import cn.com.chinatelecom.account.api.CtAuth;
import cn.com.chinatelecom.account.api.CtSetting;
import com.bytedance.b.a.a.d;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.bytedance.sdk.account.platform.api.IOnekeyLoginService;
import com.bytedance.sdk.account.platform.base.AuthorizeCallback;
import com.bytedance.sdk.account.platform.base.AuthorizeMonitorUtil;
import com.bytedance.sdk.account.platform.base.OneLoginCallback;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import com.bytedance.sdk.account.platform.base.OnekeyLoginErrorResponse;
import com.bytedance.sdk.account.platform.onekey.OnekeyLoginConfig;
import com.cmic.sso.sdk.auth.AuthnHelper;
import com.cmic.sso.sdk.auth.TokenListener;
import com.unicom.xiaowo.login.ResultListener;
import com.unicom.xiaowo.login.UniAuthHelper;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class OnekeyLoginServiceImpl implements IOnekeyLoginService {
    private static final String CM_EVENT_CARRIER = "china_mobile";
    private static final String CT_EVENT_CARRIER = "china_telecom";
    private static final String CU_EVENT_CARRIER = "china_unicom";
    private static final String GET_CARRIER_EVENT = "one_click_carrier_response";
    private static final String GET_NETWORK_STATUS_EVENT = "one_click_network_response";
    private static final String GET_PHONE_EVENT = "one_click_number_request_response";
    private static final String GET_PHONE_SEND_EVENT = "one_click_number_request_send";
    private static final String GET_TOKEN_EVENT = "one_click_login_token_response";
    private static final String GET_TOKEN_SEND_EVENT = "one_click_login_token_send";
    private static final int MSG_CU_REQUEST_TIME_OUT = 1003;
    private static final String OTHERS_EVENT_CARRIER = "others";
    private static ArrayList<String> neededPermission = new ArrayList<String>() { // from class: com.bytedance.sdk.account.platform.onekey.OnekeyLoginServiceImpl.2
        {
            add("android.permission.INTERNET");
            add("android.permission.ACCESS_WIFI_STATE");
            add("android.permission.ACCESS_NETWORK_STATE");
            add("android.permission.CHANGE_NETWORK_STATE");
        }
    };
    private volatile boolean isCancel;
    private Context mApplicationContext;
    private AuthnHelper mAuthHelper;
    private OnekeyLoginConfig.CMSettingConfig mCMSettingConfig;
    private String mCTAccessCode;
    private OnekeyLoginConfig.CTSettingConfig mCTSettingConfig;
    private String mCUAccessCode;
    private OnekeyLoginConfig.CUSettingConfig mCUSettingConfig;
    private IOnekeyMonitor mMonitor;
    private UniAuthHelper mUniAuthHelper;
    protected Handler mainHandler = new Handler(Looper.getMainLooper()) { // from class: com.bytedance.sdk.account.platform.onekey.OnekeyLoginServiceImpl.1
        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (OnekeyLoginServiceImpl.this.isCancel) {
            }
            OnekeyResponseCallable onekeyResponseCallable = null;
            if (message.obj != null && (message.obj instanceof OnekeyResponseCallable)) {
                onekeyResponseCallable = (OnekeyResponseCallable) message.obj;
            }
            int i = message.what;
            if (i == 1003) {
                OnekeyLoginServiceImpl.this.handleCuRequestTimeout(onekeyResponseCallable);
                return;
            }
            switch (i) {
                case 1011:
                    if (onekeyResponseCallable != null && onekeyResponseCallable.mCallback != null && (onekeyResponseCallable.response instanceof Bundle)) {
                        onekeyResponseCallable.mCallback.onSuccess((Bundle) onekeyResponseCallable.response);
                        break;
                    }
                    break;
                case 1012:
                    if (onekeyResponseCallable != null && onekeyResponseCallable.mCallback != null && (onekeyResponseCallable.response instanceof OnekeyLoginErrorResponse)) {
                        onekeyResponseCallable.mCallback.onError((OnekeyLoginErrorResponse) onekeyResponseCallable.response);
                        break;
                    }
                    break;
            }
        }
    };
    private boolean mIsCuReqTimeout = false;

    private String getMonitorEvent(int i) {
        switch (i) {
            case 1:
                return GET_PHONE_EVENT;
            case 2:
            case 3:
                return GET_TOKEN_EVENT;
            default:
                return "";
        }
    }

    private String getNetStatusStr(int i) {
        switch (i) {
            case -1:
                return "error";
            case 0:
                return OnekeyLoginConstants.NETWORK_TYPE_NONE_STR;
            case 1:
                return OnekeyLoginConstants.NETWORK_TYPE_MOBILE_STR;
            case 2:
                return OnekeyLoginConstants.NETWORK_TYPE_WIFI_STR;
            case 3:
                return OnekeyLoginConstants.NETWORK_TYPE_WIFI_AND_MOBILE_STR;
            default:
                return "";
        }
    }

    OnekeyLoginServiceImpl(Context context, OnekeyLoginConfig onekeyLoginConfig) {
        this.mApplicationContext = context.getApplicationContext();
        this.mCMSettingConfig = onekeyLoginConfig.getCMSettingConfig();
        this.mCTSettingConfig = onekeyLoginConfig.getCTSettingConfig();
        this.mCUSettingConfig = onekeyLoginConfig.getCUSettingConfig();
        this.mMonitor = onekeyLoginConfig.getMonitor();
        this.mAuthHelper = AuthnHelper.getInstance(this.mApplicationContext);
        this.mUniAuthHelper = UniAuthHelper.getInstance(this.mApplicationContext);
        this.mAuthHelper.setOverTime(OnekeySettingManager.instance() != null ? OnekeySettingManager.instance().getCMTimeout() : OnekeyLoginConfig.OVER_TIME);
        if (this.mCTSettingConfig != null) {
            if (this.mCTSettingConfig.isShowLog()) {
                CtAuth.getInstance().init(this.mApplicationContext, this.mCTSettingConfig.mCTAppKey, this.mCTSettingConfig.mCTAppSecret, new CTLogger(this.mCTSettingConfig.getLogTag()));
            } else {
                CtAuth.getInstance().init(this.mApplicationContext, this.mCTSettingConfig.mCTAppKey, this.mCTSettingConfig.mCTAppSecret, null);
            }
        }
        checkPermission();
    }

    private void checkPermission() {
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = neededPermission.iterator();
        boolean z = false;
        while (it.hasNext()) {
            String next = it.next();
            if (ContextCompat.checkSelfPermission(this.mApplicationContext, next) != 0) {
                arrayList.add(next);
                z = true;
            }
        }
        if (z) {
            throw new IllegalStateException("please request permission as listed: " + arrayList.toString());
        }
    }

    @Override // com.bytedance.sdk.account.platform.api.IOnekeyLoginService
    public String getCarrier() throws JSONException {
        this.isCancel = false;
        String carrierType = NetworkTypeHelper.getCarrierType(this.mApplicationContext);
        onGetCarrierEvent(carrierType);
        return carrierType;
    }

    @Override // com.bytedance.sdk.account.platform.api.IOnekeyLoginService
    public int getNetworkStatus() throws JSONException {
        this.isCancel = false;
        int networkStatus = NetworkTypeHelper.getNetworkStatus(this.mApplicationContext);
        onGetNetworkStatusEvent(networkStatus);
        return networkStatus;
    }

    @Override // com.bytedance.sdk.account.platform.api.IOnekeyLoginService
    public void getPhoneInfo(AuthorizeCallback authorizeCallback) throws JSONException {
        this.isCancel = false;
        this.mIsCuReqTimeout = false;
        String carrier = getCarrier();
        int networkStatus = getNetworkStatus();
        if ("mobile".equals(carrier)) {
            getCMPhoneInfo(networkStatus, authorizeCallback);
            return;
        }
        if ("telecom".equals(carrier)) {
            getCTPhoneInfo(networkStatus, authorizeCallback);
            return;
        }
        if (OnekeyLoginConstants.UNICOM.equals(carrier)) {
            getCuPhoneInfo(networkStatus, authorizeCallback);
            return;
        }
        if (authorizeCallback != null && !this.isCancel) {
            OnekeyLoginErrorResponse onekeyLoginErrorResponse = new OnekeyLoginErrorResponse();
            onekeyLoginErrorResponse.netType = carrier;
            onekeyLoginErrorResponse.netStatus = networkStatus;
            onekeyLoginErrorResponse.errorType = 1;
            onekeyLoginErrorResponse.platformErrorCode = "-2";
            onekeyLoginErrorResponse.platformErrorMsg = OnekeyLoginConstants.ErrorMsg.ERROR_NOT_SUPPORT;
            authorizeCallback.onError(onekeyLoginErrorResponse);
        }
        if (this.mMonitor != null) {
            this.mMonitor.onEvent(GET_PHONE_EVENT, getMonitorJson(false, "-2", OnekeyLoginConstants.ErrorMsg.ERROR_NOT_SUPPORT, 0L, carrier, networkStatus, authorizeCallback));
        }
    }

    private void getCMPhoneInfo(final int i, final AuthorizeCallback authorizeCallback) {
        if (this.mCMSettingConfig == null) {
            if (authorizeCallback != null && !this.isCancel) {
                authorizeCallback.onError(getErrorResponse("-3", OnekeyLoginConstants.ErrorMsg.ERROR_SDK_INIT_MSG, "mobile", i, 1, null));
            }
            if (this.mMonitor != null) {
                this.mMonitor.onEvent(GET_PHONE_EVENT, getMonitorJson(false, "-3", OnekeyLoginConstants.ErrorMsg.ERROR_SDK_INIT_MSG, 0L, CM_EVENT_CARRIER, i, authorizeCallback));
                return;
            }
            return;
        }
        if (!OnekeySettingManager.instance().isCMEnable()) {
            postCarrierDisableError("mobile", i, GET_PHONE_EVENT, 1, authorizeCallback);
            return;
        }
        boolean zIsMobileEnabled = NetworkTypeHelper.isMobileEnabled(i);
        if (OnekeySettingManager.instance().needCMDataMobile() && !zIsMobileEnabled) {
            postDataMobileDisableError("mobile", i, GET_PHONE_EVENT, 1, authorizeCallback);
            return;
        }
        if (OnekeySettingManager.instance().needCMPhonePermission() && !CommonUtils.hasPermission(this.mApplicationContext, "android.permission.READ_PHONE_STATE")) {
            postNoPermissionError("mobile", i, GET_PHONE_EVENT, 1, authorizeCallback);
            return;
        }
        if (this.mMonitor != null) {
            this.mMonitor.onEvent(GET_PHONE_SEND_EVENT, getMonitorJson(CM_EVENT_CARRIER, i, authorizeCallback));
        }
        final long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            this.mAuthHelper.getPhoneInfo(this.mCMSettingConfig.mCMAppId, this.mCMSettingConfig.mCMAppKey, new TokenListener() { // from class: com.bytedance.sdk.account.platform.onekey.OnekeyLoginServiceImpl.3
                @Override // com.cmic.sso.sdk.auth.TokenListener
                public void onGetTokenComplete(JSONObject jSONObject) {
                    String strOptString;
                    String strOptString2 = "-1";
                    long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
                    if (jSONObject == null) {
                        strOptString = OnekeyLoginConstants.ErrorMsg.ERROR_INVALID_RESPONSE;
                    } else {
                        if (jSONObject.has(OnekeyLoginConstants.CM_KEY_PRE_PHONE_SCRIP)) {
                            String strOptString3 = jSONObject.optString(OnekeyLoginConstants.CM_KEY_PRE_PHONE_SCRIP);
                            Bundle bundle = new Bundle();
                            bundle.putString(IOnekeyLoginService.ResponseConstants.SECURITY_PHONE, strOptString3);
                            bundle.putString(IOnekeyLoginService.ResponseConstants.NET_TYPE, "mobile");
                            bundle.putString(IOnekeyLoginService.ResponseConstants.RAW_RESULT, jSONObject.toString());
                            OnekeyLoginServiceImpl.this.sendOneKeyMessage(1011, new OnekeyResponseCallable(authorizeCallback, bundle));
                            if (OnekeyLoginServiceImpl.this.mMonitor != null) {
                                OnekeyLoginServiceImpl.this.mMonitor.onEvent(OnekeyLoginServiceImpl.GET_PHONE_EVENT, OnekeyLoginServiceImpl.this.getMonitorJson(true, null, null, jCurrentTimeMillis2, OnekeyLoginServiceImpl.CM_EVENT_CARRIER, i, authorizeCallback));
                                return;
                            }
                            return;
                        }
                        strOptString2 = jSONObject.optString("resultCode");
                        strOptString = jSONObject.optString(OnekeyLoginConstants.CM_RESULT_DESC);
                    }
                    OnekeyLoginServiceImpl.this.postLoginErrorResponse(strOptString2, strOptString, "mobile", i, 1, System.currentTimeMillis() - jCurrentTimeMillis, jSONObject, authorizeCallback);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            postLoginErrorResponse("-1", e.getMessage(), "mobile", i, 1, System.currentTimeMillis() - jCurrentTimeMillis, null, authorizeCallback);
        }
    }

    private void getCTPhoneInfo(int i, AuthorizeCallback authorizeCallback) {
        getCtInfo(GET_PHONE_EVENT, i, authorizeCallback);
    }

    public void getCuPhoneInfo(final int i, final AuthorizeCallback authorizeCallback) {
        this.mCUAccessCode = "";
        if (this.mCUSettingConfig == null) {
            if (authorizeCallback != null && !this.isCancel) {
                authorizeCallback.onError(getErrorResponse("-3", OnekeyLoginConstants.ErrorMsg.ERROR_SDK_INIT_MSG, OnekeyLoginConstants.UNICOM, i, 1, null));
            }
            if (this.mMonitor != null) {
                this.mMonitor.onEvent(GET_PHONE_EVENT, getMonitorJson(false, "-3", OnekeyLoginConstants.ErrorMsg.ERROR_SDK_INIT_MSG, 0L, CU_EVENT_CARRIER, i, authorizeCallback));
                return;
            }
            return;
        }
        if (!OnekeySettingManager.instance().isCUEnable()) {
            postCarrierDisableError(OnekeyLoginConstants.UNICOM, i, GET_PHONE_EVENT, 1, authorizeCallback);
            return;
        }
        boolean zIsMobileEnabled = NetworkTypeHelper.isMobileEnabled(i);
        if (OnekeySettingManager.instance().needCUDataMobile() && !zIsMobileEnabled) {
            postDataMobileDisableError(OnekeyLoginConstants.UNICOM, i, GET_PHONE_EVENT, 1, authorizeCallback);
            return;
        }
        sendOneKeyMessageDelay(1003, new OnekeyResponseCallable(authorizeCallback, getErrorResponse(OnekeyLoginConstants.ErrorCode.ERROR_CU_REQUEST_TIMEOUT, OnekeyLoginConstants.ErrorMsg.ERROR_CU_REQUEST_TIMEOUT_MSG, OnekeyLoginConstants.UNICOM, i, 1, null)), OnekeySettingManager.instance().getCUTimeout());
        if (this.mMonitor != null) {
            this.mMonitor.onEvent(GET_PHONE_SEND_EVENT, getMonitorJson(CU_EVENT_CARRIER, i, authorizeCallback));
        }
        final long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            this.mUniAuthHelper.getAccesscode(this.mCUSettingConfig.mCUAppId, this.mCUSettingConfig.mCUAppSecret, new ResultListener() { // from class: com.bytedance.sdk.account.platform.onekey.OnekeyLoginServiceImpl.4
                public void onResult(String str) {
                    JSONObject jSONObject;
                    String strOptString;
                    String str2;
                    JSONObject jSONObject2;
                    JSONObject jSONObject3;
                    if (OnekeyLoginServiceImpl.this.mIsCuReqTimeout) {
                        return;
                    }
                    if (OnekeyLoginServiceImpl.this.mainHandler != null && OnekeyLoginServiceImpl.this.mainHandler.hasMessages(1003)) {
                        OnekeyLoginServiceImpl.this.mainHandler.removeMessages(1003);
                    }
                    String str3 = "-1";
                    String str4 = "unknown";
                    long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
                    JSONObject jSONObject4 = null;
                    if (!TextUtils.isEmpty(str)) {
                        try {
                            jSONObject = new JSONObject(str);
                            try {
                            } catch (Exception e) {
                                e = e;
                            }
                        } catch (Exception e2) {
                            e = e2;
                        }
                        if (OnekeyLoginConstants.CU_RESULT_SUCCESS.equals(jSONObject.optString("resultCode")) && (jSONObject3 = (JSONObject) jSONObject.opt(OnekeyLoginConstants.CU_KEY_RESULT_DATA)) != null && jSONObject3.has("mobile")) {
                            String strOptString2 = jSONObject3.optString("mobile");
                            OnekeyLoginServiceImpl.this.mCUAccessCode = jSONObject3.optString("accessCode");
                            Bundle bundle = new Bundle();
                            bundle.putString(IOnekeyLoginService.ResponseConstants.SECURITY_PHONE, strOptString2);
                            bundle.putString(IOnekeyLoginService.ResponseConstants.NET_TYPE, OnekeyLoginConstants.UNICOM);
                            bundle.putString(IOnekeyLoginService.ResponseConstants.RAW_RESULT, str);
                            OnekeyLoginServiceImpl.this.sendOneKeyMessage(1011, new OnekeyResponseCallable(authorizeCallback, bundle));
                            if (OnekeyLoginServiceImpl.this.mMonitor != null) {
                                OnekeyLoginServiceImpl.this.mMonitor.onEvent(OnekeyLoginServiceImpl.GET_PHONE_EVENT, OnekeyLoginServiceImpl.this.getMonitorJson(true, null, null, jCurrentTimeMillis2, OnekeyLoginServiceImpl.CU_EVENT_CARRIER, i, authorizeCallback));
                                return;
                            }
                            return;
                        }
                        String strOptString3 = jSONObject.optString("resultCode");
                        try {
                            strOptString = jSONObject.optString(OnekeyLoginConstants.CU_KEY_RESULT_MSG);
                            str2 = strOptString3;
                            jSONObject2 = jSONObject;
                        } catch (Exception e3) {
                            e = e3;
                            str3 = strOptString3;
                            jSONObject4 = jSONObject;
                            e.printStackTrace();
                            str2 = str3;
                            strOptString = str4;
                            jSONObject2 = jSONObject4;
                            OnekeyLoginServiceImpl.this.mCUAccessCode = "";
                            OnekeyLoginServiceImpl.this.postLoginErrorResponse(str2, strOptString, OnekeyLoginConstants.UNICOM, i, 1, System.currentTimeMillis() - jCurrentTimeMillis, jSONObject2, authorizeCallback);
                        }
                        OnekeyLoginServiceImpl.this.mCUAccessCode = "";
                        OnekeyLoginServiceImpl.this.postLoginErrorResponse(str2, strOptString, OnekeyLoginConstants.UNICOM, i, 1, System.currentTimeMillis() - jCurrentTimeMillis, jSONObject2, authorizeCallback);
                    }
                    str4 = OnekeyLoginConstants.ErrorMsg.ERROR_INVALID_RESPONSE;
                    str2 = str3;
                    strOptString = str4;
                    jSONObject2 = jSONObject4;
                    OnekeyLoginServiceImpl.this.mCUAccessCode = "";
                    OnekeyLoginServiceImpl.this.postLoginErrorResponse(str2, strOptString, OnekeyLoginConstants.UNICOM, i, 1, System.currentTimeMillis() - jCurrentTimeMillis, jSONObject2, authorizeCallback);
                }
            });
        } catch (Exception e) {
            postLoginErrorResponse("-1", e.getMessage(), OnekeyLoginConstants.UNICOM, i, 1, System.currentTimeMillis() - jCurrentTimeMillis, null, authorizeCallback);
        }
    }

    @Override // com.bytedance.sdk.account.platform.api.IOnekeyLoginService
    public void getAuthToken(AuthorizeCallback authorizeCallback) throws JSONException {
        this.isCancel = false;
        this.mIsCuReqTimeout = false;
        String carrier = getCarrier();
        int networkStatus = getNetworkStatus();
        if ("mobile".equals(carrier)) {
            if (!OnekeySettingManager.instance().isCMEnable()) {
                postCarrierDisableError(carrier, networkStatus, GET_TOKEN_EVENT, 2, authorizeCallback);
                return;
            } else if (OnekeySettingManager.instance().needCMPhonePermission() && !CommonUtils.hasPermission(this.mApplicationContext, "android.permission.READ_PHONE_STATE")) {
                postNoPermissionError("mobile", networkStatus, GET_TOKEN_EVENT, 2, authorizeCallback);
                return;
            } else {
                getCmAuthToken(networkStatus, authorizeCallback);
                return;
            }
        }
        if ("telecom".equals(carrier)) {
            if (!OnekeySettingManager.instance().isCTEnable()) {
                postCarrierDisableError(carrier, networkStatus, GET_TOKEN_EVENT, 2, authorizeCallback);
                return;
            } else {
                getCtInfo(GET_TOKEN_EVENT, networkStatus, authorizeCallback);
                return;
            }
        }
        if (OnekeyLoginConstants.UNICOM.equals(carrier)) {
            if (!OnekeySettingManager.instance().isCUEnable()) {
                postCarrierDisableError(carrier, networkStatus, GET_TOKEN_EVENT, 2, authorizeCallback);
                return;
            } else if (TextUtils.isEmpty(this.mCUAccessCode)) {
                getCuAuthAccessCode(networkStatus, authorizeCallback);
                return;
            } else {
                getCuAuthToken(this.mCUAccessCode, networkStatus, System.currentTimeMillis(), true, authorizeCallback);
                return;
            }
        }
        if (authorizeCallback != null) {
            OnekeyLoginErrorResponse onekeyLoginErrorResponse = new OnekeyLoginErrorResponse();
            onekeyLoginErrorResponse.netType = carrier;
            onekeyLoginErrorResponse.errorType = 2;
            onekeyLoginErrorResponse.platformErrorCode = "-2";
            onekeyLoginErrorResponse.platformErrorMsg = OnekeyLoginConstants.ErrorMsg.ERROR_NOT_SUPPORT;
            authorizeCallback.onError(onekeyLoginErrorResponse);
        }
        if (this.mMonitor != null) {
            this.mMonitor.onEvent(GET_TOKEN_EVENT, getMonitorJson(false, "-2", OnekeyLoginConstants.ErrorMsg.ERROR_NOT_SUPPORT, 0L, "others", networkStatus, authorizeCallback));
        }
    }

    @Override // com.bytedance.sdk.account.platform.api.IOnekeyLoginService
    public void cancel() {
        this.isCancel = true;
        if (this.mainHandler != null) {
            this.mainHandler.removeCallbacksAndMessages(null);
        }
        this.mCTAccessCode = "";
        this.mCUAccessCode = "";
        this.mIsCuReqTimeout = false;
    }

    public void getCmAuthToken(final int i, final AuthorizeCallback authorizeCallback) {
        if (this.mCMSettingConfig == null) {
            if (authorizeCallback != null && !this.isCancel) {
                authorizeCallback.onError(getErrorResponse("-3", OnekeyLoginConstants.ErrorMsg.ERROR_SDK_INIT_MSG, "mobile", i, 2, null));
            }
            if (this.mMonitor != null) {
                this.mMonitor.onEvent(GET_TOKEN_EVENT, getMonitorJson(false, "-3", OnekeyLoginConstants.ErrorMsg.ERROR_SDK_INIT_MSG, 0L, CM_EVENT_CARRIER, i, authorizeCallback));
                return;
            }
            return;
        }
        if (this.mMonitor != null) {
            this.mMonitor.onEvent(GET_TOKEN_SEND_EVENT, getMonitorJson(CM_EVENT_CARRIER, i, authorizeCallback));
        }
        final long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            this.mAuthHelper.loginAuth(this.mCMSettingConfig.mCMAppId, this.mCMSettingConfig.mCMAppKey, new TokenListener() { // from class: com.bytedance.sdk.account.platform.onekey.OnekeyLoginServiceImpl.5
                @Override // com.cmic.sso.sdk.auth.TokenListener
                public void onGetTokenComplete(JSONObject jSONObject) {
                    String strOptString = "-1";
                    String str = "unknown";
                    long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
                    if (jSONObject == null) {
                        str = OnekeyLoginConstants.ErrorMsg.ERROR_INVALID_RESPONSE;
                    } else if (OnekeyLoginConstants.CM_RESULT_SUCCESS.equals(jSONObject.optString("resultCode"))) {
                        if (jSONObject.has("token")) {
                            String strOptString2 = jSONObject.optString("token");
                            String strOptString3 = jSONObject.optString("openId");
                            String strOptString4 = jSONObject.optString("authType");
                            String strOptString5 = jSONObject.optString("authTypeDes");
                            Bundle bundle = new Bundle();
                            bundle.putString("access_token", strOptString2);
                            bundle.putString(IOnekeyLoginService.ResponseConstants.NET_TYPE, "mobile");
                            bundle.putString("openId", strOptString3);
                            bundle.putString("authType", strOptString4);
                            bundle.putString("authTypeDes", strOptString5);
                            bundle.putString(IOnekeyLoginService.ResponseConstants.RAW_RESULT, jSONObject.toString());
                            OnekeyLoginServiceImpl.this.sendOneKeyMessage(1011, new OnekeyResponseCallable(authorizeCallback, bundle));
                            if (OnekeyLoginServiceImpl.this.mMonitor != null) {
                                OnekeyLoginServiceImpl.this.mMonitor.onEvent(OnekeyLoginServiceImpl.GET_TOKEN_EVENT, OnekeyLoginServiceImpl.this.getMonitorJson(true, null, null, jCurrentTimeMillis2, OnekeyLoginServiceImpl.CM_EVENT_CARRIER, i, authorizeCallback));
                                return;
                            }
                            return;
                        }
                        strOptString = jSONObject.optString("resultCode");
                        str = OnekeyLoginConstants.ErrorMsg.ERROR_INVALID_RESPONSE;
                    } else {
                        strOptString = jSONObject.optString("resultCode");
                    }
                    String str2 = str;
                    OnekeyLoginServiceImpl.this.postLoginErrorResponse(strOptString, str2, "mobile", i, 2, System.currentTimeMillis() - jCurrentTimeMillis, jSONObject, authorizeCallback);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            postLoginErrorResponse("-1", e.getMessage(), "mobile", i, 2, System.currentTimeMillis() - jCurrentTimeMillis, null, authorizeCallback);
        }
    }

    private void getCtInfo(final String str, final int i, final AuthorizeCallback authorizeCallback) {
        OnekeyLoginErrorResponse errorResponse;
        if (this.isCancel) {
            return;
        }
        if (this.mCTSettingConfig == null) {
            if (authorizeCallback != null && !this.isCancel) {
                if (str.equals(GET_PHONE_EVENT)) {
                    errorResponse = getErrorResponse("-3", OnekeyLoginConstants.ErrorMsg.ERROR_SDK_INIT_MSG, "telecom", i, 1, null);
                } else {
                    errorResponse = getErrorResponse("-3", OnekeyLoginConstants.ErrorMsg.ERROR_SDK_INIT_MSG, "telecom", i, 2, null);
                }
                authorizeCallback.onError(errorResponse);
            }
            if (this.mMonitor != null) {
                this.mMonitor.onEvent(str, getMonitorJson(false, "-3", OnekeyLoginConstants.ErrorMsg.ERROR_SDK_INIT_MSG, 0L, CT_EVENT_CARRIER, i, authorizeCallback));
                return;
            }
            return;
        }
        if (this.mMonitor != null) {
            if (str.equals(GET_PHONE_EVENT)) {
                this.mMonitor.onEvent(GET_PHONE_SEND_EVENT, getMonitorJson(CT_EVENT_CARRIER, i, authorizeCallback));
            } else {
                this.mMonitor.onEvent(GET_TOKEN_SEND_EVENT, getMonitorJson(CT_EVENT_CARRIER, i, authorizeCallback));
            }
        }
        if (str.equals(GET_TOKEN_EVENT) && !TextUtils.isEmpty(this.mCTAccessCode)) {
            Bundle bundle = new Bundle();
            bundle.putString(IOnekeyLoginService.ResponseConstants.NET_TYPE, "telecom_v2");
            bundle.putString("access_token", this.mCTAccessCode);
            sendOneKeyMessage(1011, new OnekeyResponseCallable(authorizeCallback, bundle));
            if (this.mMonitor != null) {
                this.mMonitor.onEvent(str, getMonitorJson(true, null, null, 0L, CT_EVENT_CARRIER, i, authorizeCallback));
            }
            this.mCTAccessCode = "";
            return;
        }
        int cTTimeout = (int) OnekeySettingManager.instance().getCTTimeout();
        final long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            CtAuth.getInstance().requestPreLogin(new CtSetting(cTTimeout, cTTimeout, cTTimeout), new cn.com.chinatelecom.account.api.ResultListener() { // from class: com.bytedance.sdk.account.platform.onekey.OnekeyLoginServiceImpl.6
                /* JADX WARN: Removed duplicated region for block: B:28:0x00cb  */
                /* JADX WARN: Removed duplicated region for block: B:29:0x00e0  */
                @Override // cn.com.chinatelecom.account.api.ResultListener
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void onResult(java.lang.String r18) {
                    /*
                        Method dump skipped, instructions count: 245
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.bytedance.sdk.account.platform.onekey.OnekeyLoginServiceImpl.AnonymousClass6.onResult(java.lang.String):void");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            if (str.equals(GET_PHONE_EVENT)) {
                postLoginErrorResponse("-1", e.getMessage(), "telecom_v2", i, 1, System.currentTimeMillis() - jCurrentTimeMillis, null, authorizeCallback);
            } else {
                postLoginErrorResponse("-1", e.getMessage(), "telecom_v2", i, 2, System.currentTimeMillis() - jCurrentTimeMillis, null, authorizeCallback);
            }
        }
    }

    private void getCuAuthAccessCode(final int i, final AuthorizeCallback authorizeCallback) {
        this.mCUAccessCode = "";
        if (this.mCUSettingConfig == null) {
            if (authorizeCallback != null && !this.isCancel) {
                authorizeCallback.onError(getErrorResponse("-3", OnekeyLoginConstants.ErrorMsg.ERROR_SDK_INIT_MSG, OnekeyLoginConstants.UNICOM, i, 3, null));
            }
            if (this.mMonitor != null) {
                this.mMonitor.onEvent(GET_TOKEN_EVENT, getMonitorJson(false, "-3", OnekeyLoginConstants.ErrorMsg.ERROR_SDK_INIT_MSG, 0L, CU_EVENT_CARRIER, i, authorizeCallback));
                return;
            }
            return;
        }
        sendOneKeyMessageDelay(1003, new OnekeyResponseCallable(authorizeCallback, getErrorResponse(OnekeyLoginConstants.ErrorCode.ERROR_CU_REQUEST_TIMEOUT, OnekeyLoginConstants.ErrorMsg.ERROR_CU_REQUEST_TIMEOUT_MSG, OnekeyLoginConstants.UNICOM, i, 3, null)), OnekeySettingManager.instance().getCUTimeout());
        final long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            this.mUniAuthHelper.getAccesscode(this.mCUSettingConfig.mCUAppId, this.mCUSettingConfig.mCUAppSecret, new ResultListener() { // from class: com.bytedance.sdk.account.platform.onekey.OnekeyLoginServiceImpl.7
                /* JADX WARN: Removed duplicated region for block: B:26:0x0080 A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:27:0x0081  */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void onResult(java.lang.String r16) {
                    /*
                        r15 = this;
                        r1 = r15
                        java.lang.String r2 = "-1"
                        java.lang.String r3 = "unknown"
                        boolean r0 = android.text.TextUtils.isEmpty(r16)
                        r4 = 0
                        if (r0 != 0) goto L6c
                        org.json.JSONObject r5 = new org.json.JSONObject     // Catch: java.lang.Exception -> L67
                        r0 = r16
                        r5.<init>(r0)     // Catch: java.lang.Exception -> L67
                        java.lang.String r0 = "0"
                        java.lang.String r4 = "resultCode"
                        java.lang.String r4 = r5.optString(r4)     // Catch: java.lang.Exception -> L64
                        boolean r0 = r0.equals(r4)     // Catch: java.lang.Exception -> L64
                        if (r0 == 0) goto L51
                        java.lang.String r0 = "resultData"
                        java.lang.Object r0 = r5.opt(r0)     // Catch: java.lang.Exception -> L64
                        org.json.JSONObject r0 = (org.json.JSONObject) r0     // Catch: java.lang.Exception -> L64
                        if (r0 == 0) goto L51
                        java.lang.String r4 = "accessCode"
                        boolean r4 = r0.has(r4)     // Catch: java.lang.Exception -> L64
                        if (r4 == 0) goto L51
                        com.bytedance.sdk.account.platform.onekey.OnekeyLoginServiceImpl r4 = com.bytedance.sdk.account.platform.onekey.OnekeyLoginServiceImpl.this     // Catch: java.lang.Exception -> L64
                        java.lang.String r6 = "accessCode"
                        java.lang.String r0 = r0.optString(r6)     // Catch: java.lang.Exception -> L64
                        com.bytedance.sdk.account.platform.onekey.OnekeyLoginServiceImpl.access$502(r4, r0)     // Catch: java.lang.Exception -> L64
                        com.bytedance.sdk.account.platform.onekey.OnekeyLoginServiceImpl r6 = com.bytedance.sdk.account.platform.onekey.OnekeyLoginServiceImpl.this     // Catch: java.lang.Exception -> L64
                        com.bytedance.sdk.account.platform.onekey.OnekeyLoginServiceImpl r0 = com.bytedance.sdk.account.platform.onekey.OnekeyLoginServiceImpl.this     // Catch: java.lang.Exception -> L64
                        java.lang.String r7 = com.bytedance.sdk.account.platform.onekey.OnekeyLoginServiceImpl.access$500(r0)     // Catch: java.lang.Exception -> L64
                        int r8 = r2     // Catch: java.lang.Exception -> L64
                        long r9 = r3     // Catch: java.lang.Exception -> L64
                        r11 = 0
                        com.bytedance.sdk.account.platform.base.AuthorizeCallback r12 = r5     // Catch: java.lang.Exception -> L64
                        r6.getCuAuthToken(r7, r8, r9, r11, r12)     // Catch: java.lang.Exception -> L64
                        return
                    L51:
                        java.lang.String r0 = "resultCode"
                        java.lang.String r4 = r5.optString(r0)     // Catch: java.lang.Exception -> L64
                        java.lang.String r0 = "resultMsg"
                        java.lang.String r0 = r5.optString(r0)     // Catch: java.lang.Exception -> L61
                        r7 = r0
                        r6 = r4
                        r13 = r5
                        goto L71
                    L61:
                        r0 = move-exception
                        r2 = r4
                        goto L65
                    L64:
                        r0 = move-exception
                    L65:
                        r4 = r5
                        goto L68
                    L67:
                        r0 = move-exception
                    L68:
                        r0.printStackTrace()
                        goto L6e
                    L6c:
                        java.lang.String r3 = "invalid_response"
                    L6e:
                        r6 = r2
                        r7 = r3
                        r13 = r4
                    L71:
                        com.bytedance.sdk.account.platform.onekey.OnekeyLoginServiceImpl r0 = com.bytedance.sdk.account.platform.onekey.OnekeyLoginServiceImpl.this
                        java.lang.String r2 = ""
                        com.bytedance.sdk.account.platform.onekey.OnekeyLoginServiceImpl.access$502(r0, r2)
                        com.bytedance.sdk.account.platform.onekey.OnekeyLoginServiceImpl r0 = com.bytedance.sdk.account.platform.onekey.OnekeyLoginServiceImpl.this
                        boolean r0 = com.bytedance.sdk.account.platform.onekey.OnekeyLoginServiceImpl.access$400(r0)
                        if (r0 == 0) goto L81
                        return
                    L81:
                        com.bytedance.sdk.account.platform.onekey.OnekeyLoginServiceImpl r0 = com.bytedance.sdk.account.platform.onekey.OnekeyLoginServiceImpl.this
                        android.os.Handler r0 = r0.mainHandler
                        if (r0 == 0) goto L9a
                        com.bytedance.sdk.account.platform.onekey.OnekeyLoginServiceImpl r0 = com.bytedance.sdk.account.platform.onekey.OnekeyLoginServiceImpl.this
                        android.os.Handler r0 = r0.mainHandler
                        r2 = 1003(0x3eb, float:1.406E-42)
                        boolean r0 = r0.hasMessages(r2)
                        if (r0 == 0) goto L9a
                        com.bytedance.sdk.account.platform.onekey.OnekeyLoginServiceImpl r0 = com.bytedance.sdk.account.platform.onekey.OnekeyLoginServiceImpl.this
                        android.os.Handler r0 = r0.mainHandler
                        r0.removeMessages(r2)
                    L9a:
                        com.bytedance.sdk.account.platform.onekey.OnekeyLoginServiceImpl r5 = com.bytedance.sdk.account.platform.onekey.OnekeyLoginServiceImpl.this
                        java.lang.String r8 = "unicom"
                        int r9 = r2
                        r10 = 3
                        long r2 = java.lang.System.currentTimeMillis()
                        long r11 = r3
                        long r11 = r2 - r11
                        com.bytedance.sdk.account.platform.base.AuthorizeCallback r14 = r5
                        com.bytedance.sdk.account.platform.onekey.OnekeyLoginServiceImpl.access$300(r5, r6, r7, r8, r9, r10, r11, r13, r14)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.bytedance.sdk.account.platform.onekey.OnekeyLoginServiceImpl.AnonymousClass7.onResult(java.lang.String):void");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            postLoginErrorResponse("-1", e.getMessage(), OnekeyLoginConstants.UNICOM, i, 3, System.currentTimeMillis() - jCurrentTimeMillis, null, authorizeCallback);
        }
    }

    public void getCuAuthToken(String str, final int i, final long j, boolean z, final AuthorizeCallback authorizeCallback) {
        if (this.isCancel) {
            this.mCUAccessCode = "";
            return;
        }
        if (this.mCUSettingConfig == null) {
            if (authorizeCallback != null && !this.isCancel) {
                authorizeCallback.onError(getErrorResponse("-3", OnekeyLoginConstants.ErrorMsg.ERROR_SDK_INIT_MSG, OnekeyLoginConstants.UNICOM, i, 2, null));
            }
            if (this.mMonitor != null) {
                this.mMonitor.onEvent(GET_TOKEN_EVENT, getMonitorJson(false, "-3", OnekeyLoginConstants.ErrorMsg.ERROR_SDK_INIT_MSG, j, CU_EVENT_CARRIER, i, authorizeCallback));
            }
            this.mCUAccessCode = "";
            return;
        }
        if (this.mIsCuReqTimeout) {
            this.mCUAccessCode = "";
            return;
        }
        if (z) {
            sendOneKeyMessageDelay(1003, new OnekeyResponseCallable(authorizeCallback, getErrorResponse(OnekeyLoginConstants.ErrorCode.ERROR_CU_REQUEST_TIMEOUT, OnekeyLoginConstants.ErrorMsg.ERROR_CU_REQUEST_TIMEOUT_MSG, OnekeyLoginConstants.UNICOM, i, 3, null)), OnekeySettingManager.instance().getCUTimeout());
        }
        if (this.mMonitor != null) {
            this.mMonitor.onEvent(GET_TOKEN_SEND_EVENT, getMonitorJson(CU_EVENT_CARRIER, i, authorizeCallback));
        }
        try {
            this.mUniAuthHelper.getToken(this.mCUSettingConfig.mCUAppId, this.mCUSettingConfig.mCUAppSecret, str, new ResultListener() { // from class: com.bytedance.sdk.account.platform.onekey.OnekeyLoginServiceImpl.8
                public void onResult(String str2) {
                    String str3;
                    String strOptString;
                    JSONObject jSONObject;
                    String strOptString2;
                    JSONObject jSONObject2;
                    OnekeyLoginServiceImpl.this.mCUAccessCode = "";
                    if (OnekeyLoginServiceImpl.this.mIsCuReqTimeout) {
                        return;
                    }
                    if (OnekeyLoginServiceImpl.this.mainHandler != null && OnekeyLoginServiceImpl.this.mainHandler.hasMessages(1003)) {
                        OnekeyLoginServiceImpl.this.mainHandler.removeMessages(1003);
                    }
                    String str4 = "-1";
                    String str5 = "unknown";
                    JSONObject jSONObject3 = null;
                    long jCurrentTimeMillis = System.currentTimeMillis() - j;
                    if (!TextUtils.isEmpty(str2)) {
                        try {
                            jSONObject = new JSONObject(str2);
                            try {
                                if (OnekeyLoginConstants.CU_RESULT_SUCCESS.equals(jSONObject.optString("resultCode"))) {
                                    JSONObject jSONObject4 = (JSONObject) jSONObject.opt(OnekeyLoginConstants.CU_KEY_RESULT_DATA);
                                    if (jSONObject4 != null && jSONObject4.has("access_token")) {
                                        String strOptString3 = jSONObject4.optString("access_token");
                                        Bundle bundle = new Bundle();
                                        bundle.putString("access_token", strOptString3);
                                        bundle.putString(IOnekeyLoginService.ResponseConstants.NET_TYPE, OnekeyLoginConstants.UNICOM);
                                        bundle.putString("expires_in", String.valueOf(jSONObject4.optLong("expires_in")));
                                        bundle.putString("openId", jSONObject4.optString(OnekeyLoginConstants.CU_KEY_OPEN_ID));
                                        bundle.putString(IOnekeyLoginService.ResponseConstants.RAW_RESULT, str2);
                                        OnekeyLoginServiceImpl.this.sendOneKeyMessage(1011, new OnekeyResponseCallable(authorizeCallback, bundle));
                                        if (OnekeyLoginServiceImpl.this.mMonitor != null) {
                                            OnekeyLoginServiceImpl.this.mMonitor.onEvent(OnekeyLoginServiceImpl.GET_TOKEN_EVENT, OnekeyLoginServiceImpl.this.getMonitorJson(true, null, null, jCurrentTimeMillis, OnekeyLoginServiceImpl.CU_EVENT_CARRIER, i, authorizeCallback));
                                            return;
                                        }
                                        return;
                                    }
                                    str5 = OnekeyLoginConstants.ErrorMsg.ERROR_INVALID_RESPONSE;
                                }
                                strOptString2 = jSONObject.optString("resultCode");
                            } catch (Exception e) {
                                e = e;
                            }
                        } catch (Exception e2) {
                            e = e2;
                        }
                        try {
                            strOptString = jSONObject.optString(OnekeyLoginConstants.CU_KEY_RESULT_MSG);
                            str3 = strOptString2;
                            jSONObject2 = jSONObject;
                        } catch (Exception e3) {
                            e = e3;
                            str4 = strOptString2;
                            jSONObject3 = jSONObject;
                            e.printStackTrace();
                            str3 = str4;
                            strOptString = str5;
                            jSONObject2 = jSONObject3;
                            OnekeyLoginServiceImpl.this.postLoginErrorResponse(str3, strOptString, OnekeyLoginConstants.UNICOM, i, 2, jCurrentTimeMillis, jSONObject2, authorizeCallback);
                        }
                        OnekeyLoginServiceImpl.this.postLoginErrorResponse(str3, strOptString, OnekeyLoginConstants.UNICOM, i, 2, jCurrentTimeMillis, jSONObject2, authorizeCallback);
                    }
                    strOptString = OnekeyLoginConstants.ErrorMsg.ERROR_INVALID_RESPONSE;
                    str3 = "-1";
                    jSONObject2 = jSONObject3;
                    OnekeyLoginServiceImpl.this.postLoginErrorResponse(str3, strOptString, OnekeyLoginConstants.UNICOM, i, 2, jCurrentTimeMillis, jSONObject2, authorizeCallback);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            postLoginErrorResponse("-1", e.getMessage(), OnekeyLoginConstants.UNICOM, i, 2, System.currentTimeMillis() - j, null, authorizeCallback);
        }
    }

    private OnekeyLoginErrorResponse getErrorResponse(String str, String str2, String str3, int i, int i2, JSONObject jSONObject) {
        OnekeyLoginErrorResponse onekeyLoginErrorResponse = new OnekeyLoginErrorResponse();
        onekeyLoginErrorResponse.netType = str3;
        onekeyLoginErrorResponse.netStatus = i;
        onekeyLoginErrorResponse.platformErrorCode = str;
        onekeyLoginErrorResponse.platformErrorMsg = str2;
        onekeyLoginErrorResponse.errorType = i2;
        onekeyLoginErrorResponse.rawResult = jSONObject;
        return onekeyLoginErrorResponse;
    }

    private void postCarrierDisableError(String str, int i, String str2, int i2, AuthorizeCallback authorizeCallback) {
        if (authorizeCallback != null && !this.isCancel) {
            authorizeCallback.onError(getErrorResponse(OnekeyLoginConstants.ErrorCode.ERROR_CARRIER_DISABLED, OnekeyLoginConstants.ErrorMsg.ERROR_CARRIER_DISABLED_MSG, str, i, i2, null));
        }
        if (this.mMonitor != null) {
            this.mMonitor.onEvent(str2, getMonitorJson(false, OnekeyLoginConstants.ErrorCode.ERROR_CARRIER_DISABLED, OnekeyLoginConstants.ErrorMsg.ERROR_CARRIER_DISABLED_MSG, 0L, str, i, authorizeCallback));
        }
    }

    private void postDataMobileDisableError(String str, int i, String str2, int i2, AuthorizeCallback authorizeCallback) {
        if (authorizeCallback != null && !this.isCancel) {
            authorizeCallback.onError(getErrorResponse(OnekeyLoginConstants.ErrorCode.ERROR_CARRIER_DATA_MOBILE_DISABLED, OnekeyLoginConstants.ErrorMsg.ERROR_CARRIER_DATA_MOBILE_DISABLED_MSG, str, i, i2, null));
        }
        if (this.mMonitor != null) {
            this.mMonitor.onEvent(str2, getMonitorJson(false, OnekeyLoginConstants.ErrorCode.ERROR_CARRIER_DATA_MOBILE_DISABLED, OnekeyLoginConstants.ErrorMsg.ERROR_CARRIER_DATA_MOBILE_DISABLED_MSG, 0L, str, i, authorizeCallback));
        }
    }

    private void postNoPermissionError(String str, int i, String str2, int i2, AuthorizeCallback authorizeCallback) {
        if (authorizeCallback != null && !this.isCancel) {
            authorizeCallback.onError(getErrorResponse(OnekeyLoginConstants.ErrorCode.ERROR_NO_PHONE_STATE_PERMISSON, OnekeyLoginConstants.ErrorMsg.ERROR_NO_PHONE_STATE_PERMISSON_MSG, str, i, i2, null));
        }
        if (this.mMonitor != null) {
            this.mMonitor.onEvent(str2, getMonitorJson(false, OnekeyLoginConstants.ErrorCode.ERROR_NO_PHONE_STATE_PERMISSON, OnekeyLoginConstants.ErrorMsg.ERROR_NO_PHONE_STATE_PERMISSON_MSG, 0L, str, i, authorizeCallback));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postLoginErrorResponse(String str, String str2, String str3, int i, int i2, long j, JSONObject jSONObject, AuthorizeCallback authorizeCallback) {
        sendOneKeyMessage(1012, new OnekeyResponseCallable(authorizeCallback, getErrorResponse(str, str2, str3, i, i2, jSONObject)));
        if (this.mMonitor != null) {
            this.mMonitor.onEvent(getMonitorEvent(i2), getMonitorJson(false, str, str2, j, getCarrierEvent(str3), i, authorizeCallback));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String getCarrierEvent(java.lang.String r4) {
        /*
            r3 = this;
            java.lang.String r0 = ""
            int r1 = r4.hashCode()
            r2 = -1429363305(0xffffffffaacda597, float:-3.6530216E-13)
            if (r1 == r2) goto L2a
            r2 = -1068855134(0xffffffffc04a90a2, float:-3.1650777)
            if (r1 == r2) goto L20
            r2 = -840542575(0xffffffffcde65691, float:-4.830541E8)
            if (r1 == r2) goto L16
            goto L34
        L16:
            java.lang.String r1 = "unicom"
            boolean r4 = r4.equals(r1)
            if (r4 == 0) goto L34
            r4 = 2
            goto L35
        L20:
            java.lang.String r1 = "mobile"
            boolean r4 = r4.equals(r1)
            if (r4 == 0) goto L34
            r4 = 0
            goto L35
        L2a:
            java.lang.String r1 = "telecom"
            boolean r4 = r4.equals(r1)
            if (r4 == 0) goto L34
            r4 = 1
            goto L35
        L34:
            r4 = -1
        L35:
            switch(r4) {
                case 0: goto L3f;
                case 1: goto L3c;
                case 2: goto L39;
                default: goto L38;
            }
        L38:
            goto L41
        L39:
            java.lang.String r0 = "china_unicom"
            goto L41
        L3c:
            java.lang.String r0 = "china_telecom"
            goto L41
        L3f:
            java.lang.String r0 = "china_mobile"
        L41:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.sdk.account.platform.onekey.OnekeyLoginServiceImpl.getCarrierEvent(java.lang.String):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void handleCuRequestTimeout(OnekeyResponseCallable onekeyResponseCallable) {
        this.mIsCuReqTimeout = true;
        this.mCUAccessCode = "";
        if (onekeyResponseCallable == null || onekeyResponseCallable.mCallback == null || !(onekeyResponseCallable.response instanceof OnekeyLoginErrorResponse)) {
            return;
        }
        OnekeyLoginErrorResponse onekeyLoginErrorResponse = (OnekeyLoginErrorResponse) onekeyResponseCallable.response;
        onekeyResponseCallable.mCallback.onError(onekeyLoginErrorResponse);
        if (this.mMonitor != null) {
            this.mMonitor.onEvent(onekeyLoginErrorResponse.errorType == 1 ? GET_PHONE_EVENT : GET_TOKEN_EVENT, getMonitorJson(false, onekeyLoginErrorResponse.platformErrorCode, onekeyLoginErrorResponse.platformErrorMsg, OnekeySettingManager.instance().getCUTimeout(), CU_EVENT_CARRIER, onekeyLoginErrorResponse.netStatus, onekeyResponseCallable.mCallback));
        }
    }

    public void sendOneKeyMessage(int i, Object obj) {
        if (this.isCancel) {
            return;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = i;
        if (obj != null) {
            messageObtain.obj = obj;
        }
        if (this.mainHandler != null) {
            this.mainHandler.sendMessage(messageObtain);
        }
    }

    public void sendOneKeyMessageDelay(int i, Object obj, long j) {
        if (this.isCancel) {
            return;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = i;
        if (obj != null) {
            messageObtain.obj = obj;
        }
        if (this.mainHandler != null) {
            this.mainHandler.sendMessageDelayed(messageObtain, j);
        }
    }

    public JSONObject getMonitorJson(boolean z, String str, String str2, long j, String str3, int i, AuthorizeCallback authorizeCallback) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            setExtraJsonData(authorizeCallback, jSONObject);
            jSONObject.put(AccountMonitorConstants.CommonParameter.SENDCODE_RESULT, z ? 1 : 0);
            if (!z) {
                jSONObject.put("error_code", str);
                jSONObject.put("error_msg", str2);
            }
            jSONObject.put("duration", j);
            jSONObject.put(DispatchConstants.CARRIER, str3);
            jSONObject.put(d.s, getNetStatusStr(i));
            jSONObject.put("permission", hasReadPhoneStatePermission());
            jSONObject.put(AuthorizeMonitorUtil.Param.PARAMS_FOR_SPECIAL, AuthorizeMonitorUtil.SPECIAL);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public JSONObject getMonitorJson(String str, int i, AuthorizeCallback authorizeCallback) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            setExtraJsonData(authorizeCallback, jSONObject);
            jSONObject.put(DispatchConstants.CARRIER, str);
            jSONObject.put(d.s, getNetStatusStr(i));
            jSONObject.put("permission", hasReadPhoneStatePermission());
            jSONObject.put(AuthorizeMonitorUtil.Param.PARAMS_FOR_SPECIAL, AuthorizeMonitorUtil.SPECIAL);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private void setExtraJsonData(AuthorizeCallback authorizeCallback, JSONObject jSONObject) throws JSONException {
        JSONObject jSONObjectOnSendEvent;
        if (jSONObject == null || authorizeCallback == null) {
            return;
        }
        try {
            if (!(authorizeCallback instanceof OneLoginCallback) || (jSONObjectOnSendEvent = ((OneLoginCallback) authorizeCallback).onSendEvent()) == null || jSONObjectOnSendEvent.length() <= 0) {
                return;
            }
            Iterator<String> itKeys = jSONObjectOnSendEvent.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                jSONObject.put(next, jSONObjectOnSendEvent.getString(next));
            }
        } catch (Exception unused) {
        }
    }

    public void onGetCarrierEvent(String str) throws JSONException {
        if (this.mMonitor != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(DispatchConstants.CARRIER, str);
                jSONObject.put("carrier_log", NetworkTypeHelper.getCarrierLog(this.mApplicationContext));
                jSONObject.put("permission", hasReadPhoneStatePermission());
                jSONObject.put(AuthorizeMonitorUtil.Param.PARAMS_FOR_SPECIAL, AuthorizeMonitorUtil.SPECIAL);
                this.mMonitor.onEvent(GET_CARRIER_EVENT, jSONObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void onGetNetworkStatusEvent(int i) throws JSONException {
        if (this.mMonitor != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("network", getNetStatusStr(i));
                jSONObject.put("network_log", NetworkTypeHelper.getNetTypeLog(this.mApplicationContext));
                jSONObject.put("permission", hasReadPhoneStatePermission());
                jSONObject.put(AuthorizeMonitorUtil.Param.PARAMS_FOR_SPECIAL, AuthorizeMonitorUtil.SPECIAL);
                this.mMonitor.onEvent(GET_NETWORK_STATUS_EVENT, jSONObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private int hasReadPhoneStatePermission() {
        return CommonUtils.hasPermission(this.mApplicationContext, "android.permission.READ_PHONE_STATE") ? 1 : 0;
    }
}
