package com.ss.android.token;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.bytedance.sdk.account.api.IBDAccount;
import com.bytedance.sdk.account.api.IBDAccountAPI;
import com.bytedance.sdk.account.api.call.AbsApiCall;
import com.bytedance.sdk.account.api.call.LogoutApiResponse;
import com.bytedance.sdk.account.api.callback.UpdateTokenCallback;
import com.bytedance.sdk.account.api.response.UpdateTokenResponse;
import com.bytedance.sdk.account.impl.BDAccountDelegate;
import com.bytedance.sdk.account.job.UpdateTokenJob;
import com.bytedance.sdk.account.utility.NetworkUtils;
import com.bytedance.sdk.account.utility.WeakHandler;
import com.bytedance.sdk.account.utils.UrlBuilder;
import com.ss.android.downloadlib.constants.DownloadConstants;
import com.ss.android.token.TTTokenConfig;
import com.umeng.message.common.inter.ITagManager;
import java.security.PrivateKey;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TokenFactory implements WeakHandler.IHandler {
    private static TokenFactory sInstance;
    protected IBDAccountAPI mAccountAPI;
    protected IBDAccount mAccountManager;
    private UpdateTokenCallback mCallback;
    private TTTokenConfig mConfig;
    private Context mContext;
    private Handler mHandler;
    private volatile boolean mIsFirstRequestToken;
    private volatile boolean mIsInited;
    private SharedPreferences mPreferences;
    private PrivateKey mPrivateKey;
    private volatile String mXTTToken;
    private volatile boolean mIsUpdateLoading = false;
    private volatile boolean mIsRecordLost = false;
    private final int MSG_RETRY = 1000;
    private volatile int mNoNetworkRetryCount = 0;
    private final long NO_NET_RETRY_INTERVAL = DownloadConstants.DEFAULT_NEXT_INSTALL_MIN_INTERVAL;
    private volatile boolean isEnable = true;

    public String getSdkVersion() {
        return "2";
    }

    protected static void initialize(Context context, TTTokenConfig tTTokenConfig) {
        sInstance = new TokenFactory(context, tTTokenConfig);
    }

    private TokenFactory(Context context, TTTokenConfig tTTokenConfig) {
        this.mIsInited = false;
        this.mConfig = tTTokenConfig;
        this.mContext = context.getApplicationContext();
        String tokenSaveName = tTTokenConfig.getTokenSaveName();
        this.mPreferences = context.getSharedPreferences(TextUtils.isEmpty(tokenSaveName) ? "token_shared_preference" : tokenSaveName, 0);
        this.mXTTToken = this.mPreferences.getString("X-Tt-Token", "");
        this.mPreferences.edit().putBoolean("first-launch", false).apply();
        this.mHandler = new WeakHandler(Looper.getMainLooper(), this);
        this.mIsInited = !TextUtils.isEmpty(this.mXTTToken);
        this.mAccountAPI = BDAccountDelegate.createBDAccountApi(this.mContext);
        this.mAccountManager = BDAccountDelegate.instance(this.mContext);
        this.mIsFirstRequestToken = this.mPreferences.getBoolean("first_beat", true);
        tryUpdateToken(true, false);
    }

    protected static TokenFactory getInstance() {
        return sInstance;
    }

    public String getXTTToken() {
        return this.mXTTToken;
    }

    public boolean isTokenLost() {
        if (this.mIsRecordLost || !this.mIsInited) {
            return false;
        }
        if (!"change.token".equals(this.mXTTToken) && !TextUtils.isEmpty(this.mXTTToken)) {
            return false;
        }
        this.mIsRecordLost = true;
        return true;
    }

    protected void clearToken() {
        this.mXTTToken = "";
        this.mIsInited = false;
        if (this.mPreferences != null) {
            this.mPreferences.edit().putString("X-Tt-Token", "").apply();
        }
    }

    private boolean isHost(String str) {
        return this.isEnable && TokenUtils.isInDomainList(str, this.mConfig.getHostList());
    }

    private String getUrlPath(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return Uri.parse(str).getPath();
    }

    private boolean inBlackList(String str) {
        TTTokenConfig.IBlackList blackList;
        if (str == null || (blackList = this.mConfig.getBlackList()) == null) {
            return false;
        }
        return blackList.inBlackList(str);
    }

    public Map<String, String> addRequestHeader(String str) throws JSONException {
        if (sInstance == null || !sInstance.isHost(str) || sInstance.inBlackList(str)) {
            return null;
        }
        HashMap map = new HashMap();
        if (!TextUtils.isEmpty(sInstance.mXTTToken)) {
            map.put("X-Tt-Token", sInstance.getXTTToken());
        }
        map.put("sdk-version", sInstance.getSdkVersion());
        map.put(TokenConstants.ACCOUNT_SDK_VERSION, String.valueOf(9));
        if (sInstance.isTokenLost()) {
            TTTokenMonitor.monitorTokenLost(str);
        }
        return map;
    }

    public void processResponseHeader(String str, List<TTTokenHeader> list) {
        if (sInstance == null || !sInstance.isHost(str) || sInstance.inBlackList(str) || list == null || list.isEmpty()) {
            return;
        }
        String value = null;
        String value2 = null;
        for (TTTokenHeader tTTokenHeader : list) {
            if ("X-Tt-Token-Sign".equalsIgnoreCase(tTTokenHeader.getName())) {
                value2 = tTTokenHeader.getValue();
            } else if ("X-Tt-Token".equalsIgnoreCase(tTTokenHeader.getName())) {
                value = tTTokenHeader.getValue();
            }
            if (!TextUtils.isEmpty(value) && !TextUtils.isEmpty(value2)) {
                sInstance.decryptSign(value2, value);
                return;
            }
        }
    }

    private boolean isValidUpdateToken(String str, String str2) {
        if (str2 == null || !str2.contains("/passport/token/beat/v2/") || TextUtils.isEmpty(this.mXTTToken) || this.mXTTToken.length() < 34) {
            return true;
        }
        if (str.length() < 34) {
            return false;
        }
        return str.substring(2, 34).equals(this.mXTTToken.substring(2, 34));
    }

    private synchronized void decryptSign(String str, String str2) {
        boolean z = true;
        if (this.mConfig.isNeedTokenSign()) {
            String message = "";
            if (this.mPrivateKey == null) {
                try {
                    this.mPrivateKey = RSAUtils.loadPrivateKey();
                } catch (Exception e) {
                    e.printStackTrace();
                    message = e.getMessage();
                }
            }
            if (this.mPrivateKey != null) {
                byte[] bArrDecryptData = RSAUtils.decryptData(RSAUtils.hexStringToByteArray(str), this.mPrivateKey);
                String str3 = bArrDecryptData != null ? new String(bArrDecryptData) : null;
                if (str3 != null && str3.length() > 100) {
                    str3 = str3.substring(0, 100);
                }
                String strSubstring = (str2 == null || str2.length() <= 100) ? str2 : str2.substring(0, 100);
                if (str3 != null && str2 != null && str3.equals(strSubstring)) {
                    this.mXTTToken = str2;
                    this.mPreferences.edit().putString("X-Tt-Token", str2).apply();
                    if ("change.token".equals(this.mXTTToken) || TextUtils.isEmpty(this.mXTTToken)) {
                        z = false;
                    }
                    this.mIsInited = z;
                } else {
                    TTTokenMonitor.monitorDecryptError("compare", str, str2, message);
                    clearToken();
                }
            } else {
                TTTokenMonitor.monitorDecryptError("privateKey", str, str2, message);
                clearToken();
            }
        } else if (!TextUtils.isEmpty(str2)) {
            this.mXTTToken = str2;
            this.mPreferences.edit().putString("X-Tt-Token", str2).apply();
            if ("change.token".equals(this.mXTTToken) || TextUtils.isEmpty(this.mXTTToken)) {
                z = false;
            }
            this.mIsInited = z;
        }
    }

    protected void tryUpdateToken(boolean z, boolean z2) {
        if (this.mIsUpdateLoading) {
            return;
        }
        this.mIsUpdateLoading = true;
        if (!NetworkUtils.isNetworkAvailable(this.mContext)) {
            this.mNoNetworkRetryCount++;
            this.mHandler.sendEmptyMessageDelayed(1000, Math.min(this.mNoNetworkRetryCount * DownloadConstants.DEFAULT_NEXT_INSTALL_MIN_INTERVAL, this.mConfig.getUpdateInterval()));
            this.mIsUpdateLoading = false;
            return;
        }
        if (this.mAccountManager == null || !this.mAccountManager.isLogin()) {
            this.mHandler.sendEmptyMessageDelayed(1000, this.mConfig.getUpdateInterval());
            this.mIsUpdateLoading = false;
            return;
        }
        this.mNoNetworkRetryCount = 0;
        final String tokenBeatUrl = getTokenBeatUrl(z, z2);
        if (TextUtils.isEmpty(tokenBeatUrl)) {
            return;
        }
        this.mCallback = new UpdateTokenCallback() { // from class: com.ss.android.token.TokenFactory.1
            public void onSuccess(UpdateTokenResponse updateTokenResponse) {
                try {
                    TokenFactory.this.mIsUpdateLoading = false;
                    TokenFactory.this.mHandler.sendEmptyMessageDelayed(1000, TokenFactory.this.mConfig.getUpdateInterval());
                    if (TokenFactory.this.mIsFirstRequestToken) {
                        TokenFactory.this.mIsFirstRequestToken = false;
                        TokenFactory.this.mPreferences.edit().putBoolean("first_beat", false).apply();
                    }
                } catch (Exception unused) {
                }
            }

            public void onError(UpdateTokenResponse updateTokenResponse, int i) {
                try {
                    TokenFactory.this.mIsUpdateLoading = false;
                    if (updateTokenResponse != null && "session_expired".equalsIgnoreCase(updateTokenResponse.errorName)) {
                        TokenFactory.this.onSessionExpired(tokenBeatUrl, null, true, null);
                    } else {
                        TTTokenMonitor.monitorToken(TTTokenMonitor.TT_TOKEN_BEAT, null, i, updateTokenResponse != null ? updateTokenResponse.errorMsg : "");
                    }
                    if (TokenFactory.this.mIsFirstRequestToken && updateTokenResponse != null && updateTokenResponse.result != null) {
                        TokenFactory.this.mIsFirstRequestToken = false;
                        TokenFactory.this.mPreferences.edit().putBoolean("first_beat", false).apply();
                    }
                    TokenFactory.this.mHandler.sendEmptyMessageDelayed(1000, TokenFactory.this.mConfig.getUpdateInterval());
                } catch (Exception unused) {
                }
            }
        };
        requestTokenBeat(tokenBeatUrl, this.mCallback);
    }

    private void requestTokenBeat(String str, UpdateTokenCallback updateTokenCallback) {
        UpdateTokenJob.updateToken(this.mContext, str, updateTokenCallback).start();
    }

    public void handleMsg(Message message) {
        if (message.what == 1000) {
            this.mHandler.removeMessages(1000);
            tryUpdateToken(false, false);
        }
    }

    public void onSessionExpired(String str, List<TTTokenHeader> list, boolean z, AbsApiCall<LogoutApiResponse> absApiCall) throws JSONException {
        TTTokenMonitor.monitorSessionExpire(str, list);
        if (this.mAccountManager == null || !this.mAccountManager.isLogin()) {
            return;
        }
        clearToken();
        if (this.mAccountManager != null) {
            this.mAccountManager.invalidateSession(z);
        }
        sessionExpiredInternal(absApiCall);
    }

    private void sessionExpiredInternal(AbsApiCall<LogoutApiResponse> absApiCall) {
        this.mAccountAPI.logout("sdk_expired_logout", (Map) null, absApiCall);
    }

    public void stopUpdateToken() {
        this.mHandler.removeMessages(1000);
    }

    public void setTokenEnable(boolean z) {
        this.isEnable = z;
        if (this.isEnable) {
            return;
        }
        clearToken();
    }

    public String getTokenBeatUrl(boolean z, boolean z2) {
        UrlBuilder urlBuilder = new UrlBuilder(this.mConfig.getBeatHost() + "/passport/token/beat/v2/");
        String str = z ? "boot" : "polling";
        if (z2) {
            str = "wap_login";
        }
        urlBuilder.addParam(DownloadConstants.EVENT_SCENE, str);
        urlBuilder.addParam("first_beat", this.mIsFirstRequestToken ? ITagManager.STATUS_TRUE : "false");
        return urlBuilder.toString();
    }

    public void addConfigHost(Collection<String> collection) {
        if (this.mConfig != null) {
            this.mConfig.dynamicAddHostList(collection);
        }
    }
}
