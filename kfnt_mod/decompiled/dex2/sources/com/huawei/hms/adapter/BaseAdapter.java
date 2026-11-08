package com.huawei.hms.adapter;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import com.huawei.hms.activity.BridgeActivity;
import com.huawei.hms.adapter.internal.CommonCode;
import com.huawei.hms.adapter.sysobs.SystemManager;
import com.huawei.hms.adapter.sysobs.SystemObserver;
import com.huawei.hms.adapter.ui.BaseResolutionAdapter;
import com.huawei.hms.common.internal.RequestHeader;
import com.huawei.hms.common.internal.ResponseHeader;
import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.support.api.PendingResultImpl;
import com.huawei.hms.support.api.ResolveResult;
import com.huawei.hms.support.api.client.ApiClient;
import com.huawei.hms.support.api.client.PendingResult;
import com.huawei.hms.support.api.client.ResultCallback;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hms.support.hianalytics.HiAnalyticsUtil;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.update.kpms.KpmsConstant;
import com.huawei.hms.utils.JsonUtil;
import com.huawei.hms.utils.Util;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class BaseAdapter {
    private WeakReference<ApiClient> a;
    private WeakReference<Activity> b;
    private BaseCallBack c;
    private String d;
    private String e;
    private Parcelable f;
    private BaseCallBack g;
    private String h;
    private Context i;
    private RequestHeader j = new RequestHeader();
    private ResponseHeader k = new ResponseHeader();
    private SystemObserver l;

    public interface BaseCallBack {
        void onComplete(String str, String str2, Parcelable parcelable);

        void onError(String str);
    }

    public BaseAdapter(ApiClient apiClient) {
        this.a = new WeakReference<>(apiClient);
    }

    public BaseAdapter(ApiClient apiClient, Activity activity) {
        this.a = new WeakReference<>(apiClient);
        this.b = new WeakReference<>(activity);
        this.i = activity.getApplicationContext();
    }

    public void baseRequest(String str, String str2, Parcelable parcelable, BaseCallBack baseCallBack) throws IllegalArgumentException {
        a(str, str2, parcelable, baseCallBack);
        if (this.a == null) {
            HMSLog.e("BaseAdapter", "client is null");
            h();
            baseCallBack.onError(a(-2));
            return;
        }
        ApiClient apiClient = this.a.get();
        this.c = baseCallBack;
        JsonUtil.jsonToEntity(str, this.j);
        CoreBaseRequest coreBaseRequest = new CoreBaseRequest();
        coreBaseRequest.setJsonObject(str2);
        coreBaseRequest.setJsonHeader(str);
        coreBaseRequest.setParcelable(parcelable);
        String apiName = this.j.getApiName();
        if (TextUtils.isEmpty(apiName)) {
            HMSLog.e("BaseAdapter", "get uri null");
            h();
            baseCallBack.onError(a(-5));
            return;
        }
        this.h = this.j.getTransactionId();
        if (TextUtils.isEmpty(this.h)) {
            HMSLog.e("BaseAdapter", "get transactionId null");
            h();
            baseCallBack.onError(a(-6));
            return;
        }
        HMSLog.i("BaseAdapter", "in baseRequest + uri is :" + apiName + ", transactionId is : " + this.h);
        a(this.i, this.j);
        a(apiClient, apiName, coreBaseRequest).setResultCallback(new BaseRequestResultCallback());
    }

    private PendingResult<ResolveResult<CoreBaseResponse>> a(ApiClient apiClient, String str, CoreBaseRequest coreBaseRequest) {
        return new a(apiClient, str, coreBaseRequest);
    }

    private static class a extends PendingResultImpl<ResolveResult<CoreBaseResponse>, CoreBaseResponse> {
        public a(ApiClient apiClient, String str, IMessageEntity iMessageEntity) {
            super(apiClient, str, iMessageEntity);
        }

        @Override // com.huawei.hms.support.api.PendingResultImpl
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ResolveResult<CoreBaseResponse> onComplete(CoreBaseResponse coreBaseResponse) {
            ResolveResult<CoreBaseResponse> resolveResult = new ResolveResult<>(coreBaseResponse);
            resolveResult.setStatus(Status.SUCCESS);
            return resolveResult;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Activity a() {
        ApiClient apiClient;
        if (this.b == null || (apiClient = this.a.get()) == null) {
            return null;
        }
        return Util.getActiveActivity(this.b.get(), apiClient.getContext());
    }

    public class BaseRequestResultCallback implements ResultCallback<ResolveResult<CoreBaseResponse>> {
        public BaseRequestResultCallback() {
        }

        @Override // com.huawei.hms.support.api.client.ResultCallback
        public void onResult(ResolveResult<CoreBaseResponse> resolveResult) throws IllegalArgumentException {
            BaseCallBack baseCallBackB = BaseAdapter.this.b();
            if (baseCallBackB == null) {
                HMSLog.e("BaseAdapter", "onResult baseCallBack null");
                BaseAdapter.this.h();
                return;
            }
            if (resolveResult == null) {
                HMSLog.e("BaseAdapter", "result null");
                baseCallBackB.onError(BaseAdapter.this.a(-1));
                BaseAdapter.this.h();
                return;
            }
            CoreBaseResponse value = resolveResult.getValue();
            if (value == null) {
                HMSLog.e("BaseAdapter", "response null");
                BaseAdapter.this.h();
                baseCallBackB.onError(BaseAdapter.this.a(-1));
                return;
            }
            if (!TextUtils.isEmpty(value.getJsonHeader())) {
                JsonUtil.jsonToEntity(value.getJsonHeader(), BaseAdapter.this.k);
                BaseAdapter.this.a(BaseAdapter.this.i, BaseAdapter.this.k);
                if ("intent".equals(BaseAdapter.this.k.getResolution())) {
                    Activity activityA = BaseAdapter.this.a();
                    if (activityA == null || activityA.isFinishing()) {
                        HMSLog.e("BaseAdapter", "activity null");
                        a(baseCallBackB, value);
                        return;
                    }
                    PendingIntent pendingIntent = value.getPendingIntent();
                    if (pendingIntent != null) {
                        if (Util.isAvailableLibExist(BaseAdapter.this.i)) {
                            BaseAdapter.this.a(activityA, pendingIntent);
                            return;
                        } else {
                            baseCallBackB.onError(BaseAdapter.this.a(-9));
                            return;
                        }
                    }
                    Intent intent = value.getIntent();
                    if (intent != null) {
                        if (Util.isAvailableLibExist(BaseAdapter.this.i)) {
                            BaseAdapter.this.a(activityA, intent);
                            return;
                        } else {
                            baseCallBackB.onError(BaseAdapter.this.a(-9));
                            return;
                        }
                    }
                    HMSLog.e("BaseAdapter", "hasResolution is true but NO_SOLUTION");
                    BaseAdapter.this.h();
                    baseCallBackB.onError(BaseAdapter.this.a(-4));
                    return;
                }
                a(baseCallBackB, value);
                return;
            }
            HMSLog.e("BaseAdapter", "jsonHeader null");
            BaseAdapter.this.h();
            baseCallBackB.onError(BaseAdapter.this.a(-1));
        }

        private void a(BaseCallBack baseCallBack, CoreBaseResponse coreBaseResponse) {
            HMSLog.i("BaseAdapter", "baseCallBack.onComplete");
            PendingIntent pendingIntent = coreBaseResponse.getPendingIntent();
            if (pendingIntent != null) {
                BaseAdapter.this.h();
                baseCallBack.onComplete(coreBaseResponse.getJsonHeader(), coreBaseResponse.getJsonBody(), pendingIntent);
                return;
            }
            Intent intent = coreBaseResponse.getIntent();
            if (intent != null) {
                BaseAdapter.this.h();
                baseCallBack.onComplete(coreBaseResponse.getJsonHeader(), coreBaseResponse.getJsonBody(), intent);
            } else {
                BaseAdapter.this.h();
                baseCallBack.onComplete(coreBaseResponse.getJsonHeader(), coreBaseResponse.getJsonBody(), null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Activity activity, Parcelable parcelable) {
        HMSLog.i("BaseAdapter", "startResolution");
        if (this.j != null) {
            b(this.i, this.j);
        }
        if (this.l == null) {
            i();
        }
        SystemManager.getSystemNotifier().registerObserver(this.l);
        Intent intentStartBridgeActivity = BridgeActivity.getIntentStartBridgeActivity(activity, BaseResolutionAdapter.class.getName());
        Bundle bundle = new Bundle();
        bundle.putParcelable(CommonCode.MapKey.HAS_RESOLUTION, parcelable);
        intentStartBridgeActivity.putExtras(bundle);
        intentStartBridgeActivity.putExtra("transaction_id", this.h);
        activity.startActivity(intentStartBridgeActivity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BaseCallBack b() {
        if (this.c == null) {
            HMSLog.e("BaseAdapter", "callback null");
            return null;
        }
        return this.c;
    }

    private void a(Context context, RequestHeader requestHeader) {
        Map<String, String> mapFromRequestHeader = HiAnalyticsUtil.getInstance().getMapFromRequestHeader(requestHeader);
        mapFromRequestHeader.put(HiAnalyticsConstant.HaKey.BI_KEY_DIRECTION, HiAnalyticsConstant.Direction.REQUEST);
        mapFromRequestHeader.put(HiAnalyticsConstant.HaKey.BI_KEY_VERSION, HiAnalyticsUtil.versionCodeToName(String.valueOf(requestHeader.getKitSdkVersion())));
        HiAnalyticsUtil.getInstance().onNewEvent(context, HiAnalyticsConstant.HMS_SDK_BASE_CALL_AIDL, mapFromRequestHeader);
    }

    private void b(Context context, RequestHeader requestHeader) {
        Map<String, String> mapFromRequestHeader = HiAnalyticsUtil.getInstance().getMapFromRequestHeader(requestHeader);
        mapFromRequestHeader.put(HiAnalyticsConstant.HaKey.BI_KEY_DIRECTION, HiAnalyticsConstant.Direction.REQUEST);
        mapFromRequestHeader.put(HiAnalyticsConstant.HaKey.BI_KEY_VERSION, HiAnalyticsUtil.versionCodeToName(String.valueOf(requestHeader.getKitSdkVersion())));
        HiAnalyticsUtil.getInstance().onNewEvent(context, HiAnalyticsConstant.HMS_SDK_BASE_START_RESOLUTION, mapFromRequestHeader);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, ResponseHeader responseHeader) {
        HiAnalyticsUtil.getInstance();
        Map<String, String> mapFromRequestHeader = HiAnalyticsUtil.getMapFromRequestHeader(responseHeader);
        mapFromRequestHeader.put(HiAnalyticsConstant.HaKey.BI_KEY_DIRECTION, HiAnalyticsConstant.Direction.RESPONSE);
        mapFromRequestHeader.put(HiAnalyticsConstant.HaKey.BI_KEY_VERSION, HiAnalyticsUtil.versionCodeToName(String.valueOf(this.j.getKitSdkVersion())));
        HiAnalyticsUtil.getInstance().onNewEvent(context, HiAnalyticsConstant.HMS_SDK_BASE_CALL_AIDL, mapFromRequestHeader);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, ResponseHeader responseHeader, long j) {
        HiAnalyticsUtil.getInstance();
        Map<String, String> mapFromRequestHeader = HiAnalyticsUtil.getMapFromRequestHeader(responseHeader);
        mapFromRequestHeader.put(HiAnalyticsConstant.HaKey.BI_KEY_DIRECTION, HiAnalyticsConstant.Direction.RESPONSE);
        mapFromRequestHeader.put(HiAnalyticsConstant.HaKey.BI_KEY_WAITTIME, String.valueOf(j));
        mapFromRequestHeader.put(HiAnalyticsConstant.HaKey.BI_KEY_VERSION, HiAnalyticsUtil.versionCodeToName(String.valueOf(this.j.getKitSdkVersion())));
        HiAnalyticsUtil.getInstance().onNewEvent(context, HiAnalyticsConstant.HMS_SDK_BASE_START_RESOLUTION, mapFromRequestHeader);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(int i) {
        this.k.setTransactionId(this.j.getTransactionId());
        this.k.setAppID(this.j.getAppID());
        this.k.setApiName(this.j.getApiName());
        this.k.setSrvName(this.j.getSrvName());
        this.k.setPkgName(this.j.getPkgName());
        this.k.setStatusCode(1);
        this.k.setErrorCode(i);
        this.k.setErrorReason("Core error");
        return this.k.toJson();
    }

    private String c() {
        return this.d;
    }

    private void a(String str) {
        this.d = str;
    }

    private String d() {
        return this.e;
    }

    private void b(String str) {
        this.e = str;
    }

    private Parcelable e() {
        return this.f;
    }

    private void a(Parcelable parcelable) {
        this.f = parcelable;
    }

    private BaseCallBack f() {
        return this.g;
    }

    private void a(BaseCallBack baseCallBack) {
        this.g = baseCallBack;
    }

    private void a(String str, String str2, Parcelable parcelable, BaseCallBack baseCallBack) {
        a(str);
        b(str2);
        a(parcelable);
        a(baseCallBack);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() throws IllegalArgumentException {
        this.k = null;
        this.k = new ResponseHeader();
        baseRequest(c(), d(), e(), f());
        h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        a((String) null);
        b((String) null);
        a((Parcelable) null);
        a((BaseCallBack) null);
    }

    private void i() {
        this.l = new SystemObserver() { // from class: com.huawei.hms.adapter.BaseAdapter.1
            @Override // com.huawei.hms.adapter.sysobs.SystemObserver
            public boolean onUpdateResult(int i) {
                return false;
            }

            @Override // com.huawei.hms.adapter.sysobs.SystemObserver
            public boolean onSolutionResult(Intent intent, String str) throws JSONException, IllegalArgumentException {
                if (!TextUtils.isEmpty(str)) {
                    if (!str.equals(BaseAdapter.this.h)) {
                        return false;
                    }
                    HMSLog.i("BaseAdapter", "onSolutionResult + id is :" + str);
                    BaseCallBack baseCallBackB = BaseAdapter.this.b();
                    if (baseCallBackB == null) {
                        HMSLog.e("BaseAdapter", "onResult baseCallBack null");
                        BaseAdapter.this.h();
                        return true;
                    }
                    long jLongValue = 0;
                    if (intent == null) {
                        HMSLog.e("BaseAdapter", "onSolutionResult but data is null");
                        String strA = BaseAdapter.this.a(-7);
                        BaseAdapter.this.a(BaseAdapter.this.i, BaseAdapter.this.k, 0L);
                        baseCallBackB.onError(strA);
                        BaseAdapter.this.h();
                        return true;
                    }
                    if (intent.getIntExtra(KpmsConstant.KIT_UPDATE_RESULT, 0) == 1) {
                        HMSLog.e("BaseAdapter", "kit update success,replay request");
                        BaseAdapter.this.g();
                        return true;
                    }
                    HMSLog.e("BaseAdapter", "onComplete for on activity result");
                    String stringExtra = intent.getStringExtra(CommonCode.MapKey.JSON_HEADER);
                    String stringExtra2 = intent.getStringExtra(CommonCode.MapKey.JSON_BODY);
                    Object infoFromJsonobject = JsonUtil.getInfoFromJsonobject(stringExtra, "status_code");
                    Object infoFromJsonobject2 = JsonUtil.getInfoFromJsonobject(stringExtra, "error_code");
                    if (intent.hasExtra(CommonCode.MapKey.HMS_FOREGROUND_RES_UI)) {
                        Object infoFromJsonobject3 = JsonUtil.getInfoFromJsonobject(intent.getStringExtra(CommonCode.MapKey.HMS_FOREGROUND_RES_UI), "uiDuration");
                        if (infoFromJsonobject3 instanceof Long) {
                            jLongValue = ((Long) infoFromJsonobject3).longValue();
                        }
                    }
                    if (!(infoFromJsonobject instanceof Integer) || !(infoFromJsonobject2 instanceof Integer)) {
                        BaseAdapter.this.a(-8);
                        BaseAdapter.this.a(BaseAdapter.this.i, BaseAdapter.this.k, jLongValue);
                    } else {
                        int iIntValue = ((Integer) infoFromJsonobject).intValue();
                        BaseAdapter.this.a(((Integer) infoFromJsonobject2).intValue());
                        BaseAdapter.this.k.setStatusCode(iIntValue);
                        BaseAdapter.this.a(BaseAdapter.this.i, BaseAdapter.this.k, jLongValue);
                    }
                    baseCallBackB.onComplete(stringExtra, stringExtra2, null);
                    return true;
                }
                HMSLog.e("BaseAdapter", "onSolutionResult but id is null");
                BaseCallBack baseCallBackB2 = BaseAdapter.this.b();
                if (baseCallBackB2 != null) {
                    baseCallBackB2.onError(BaseAdapter.this.a(-6));
                    BaseAdapter.this.h();
                    return true;
                }
                HMSLog.e("BaseAdapter", "onSolutionResult baseCallBack null");
                BaseAdapter.this.h();
                return true;
            }
        };
    }
}
