package com.huawei.hms.aaid;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import com.huawei.hmf.tasks.f;
import com.huawei.hmf.tasks.g;
import com.huawei.hmf.tasks.i;
import com.huawei.hms.aaid.a.e;
import com.huawei.hms.aaid.b.a;
import com.huawei.hms.aaid.b.b;
import com.huawei.hms.aaid.b.c;
import com.huawei.hms.aaid.constant.AaidIdConstant;
import com.huawei.hms.aaid.entity.AAIDResult;
import com.huawei.hms.aaid.entity.DeleteTokenReq;
import com.huawei.hms.aaid.entity.TokenReq;
import com.huawei.hms.aaid.entity.TokenResult;
import com.huawei.hms.aaid.plugin.ProxyCenter;
import com.huawei.hms.api.Api;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.HuaweiApi;
import com.huawei.hms.common.internal.AbstractClientBuilder;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.JsonUtil;
import com.huawei.hms.utils.Util;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class HmsInstanceId {
    public static final String TAG = "HmsInstanceId";
    private static final byte[] a = new byte[0];
    private static final byte[] b = new byte[0];
    private Context c;
    private c d;
    private HuaweiApi<Api.ApiOptions.NoOptions> e;

    private HmsInstanceId(Context context) {
        this.c = null;
        this.d = null;
        this.c = context.getApplicationContext();
        this.d = new c(context, "aaid");
        Api api = new Api(HuaweiApiAvailability.HMS_API_NAME_PUSH);
        if (context instanceof Activity) {
            this.e = new HuaweiApi<>((Activity) context, (Api<Api.ApiOptions>) api, (Api.ApiOptions) null, (AbstractClientBuilder) new e());
        } else {
            this.e = new HuaweiApi<>(context, (Api<Api.ApiOptions>) api, (Api.ApiOptions) null, new e());
        }
        this.e.setKitSdkVersion(40004301);
    }

    public static HmsInstanceId getInstance(Context context) {
        Preconditions.checkNotNull(context);
        com.huawei.hms.aaid.encrypt.c.b(context);
        return new HmsInstanceId(context);
    }

    public String getId() {
        return a.b(this.c);
    }

    public f<AAIDResult> getAAID() {
        try {
            return i.a(new com.huawei.hms.aaid.a.a(this.c.getApplicationContext()));
        } catch (Exception unused) {
            g gVar = new g();
            gVar.a((Exception) com.huawei.hms.aaid.constant.a.a(com.huawei.hms.aaid.constant.a.ERROR_INTERNAL_ERROR));
            return gVar.a();
        }
    }

    public long getCreationTime() {
        try {
            if (!this.d.d(AaidIdConstant.CREATE_TIME)) {
                getAAID();
            }
            return this.d.c(AaidIdConstant.CREATE_TIME);
        } catch (Exception unused) {
            return 0L;
        }
    }

    public void deleteAAID() throws ApiException {
        try {
            if (this.d.d("aaid")) {
                this.d.e("aaid");
                this.d.e(AaidIdConstant.CREATE_TIME);
            }
        } catch (RuntimeException unused) {
            throw com.huawei.hms.aaid.constant.a.a(com.huawei.hms.aaid.constant.a.ERROR_INTERNAL_ERROR);
        } catch (Exception unused2) {
            throw com.huawei.hms.aaid.constant.a.a(com.huawei.hms.aaid.constant.a.ERROR_INTERNAL_ERROR);
        }
    }

    @Deprecated
    public String getToken() {
        try {
            return getToken(null, null);
        } catch (Exception unused) {
            return null;
        }
    }

    public String getToken(String str, String str2) throws ApiException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw com.huawei.hms.aaid.constant.a.a(com.huawei.hms.aaid.constant.a.ERROR_MAIN_THREAD);
        }
        if (ProxyCenter.getInstance().getProxy() != null) {
            ProxyCenter.getInstance().getProxy().getToken(this.c);
            HMSLog.i(TAG, "use proxy get token, please check HmsMessageService.onNewToken receive result.");
            return null;
        }
        String strA = b.a(this.c, AaidIdConstant.getToken);
        try {
            TokenReq tokenReqA = a.a(str, str2, this.c);
            tokenReqA.setAaid(getId());
            HMSLog.d(TAG, "getToken req :" + tokenReqA.toString());
            return ((TokenResult) i.a(this.e.doWrite(new com.huawei.hms.aaid.a.c(AaidIdConstant.getToken, JsonUtil.createJsonString(tokenReqA), this.c, strA)))).getToken();
        } catch (Exception e) {
            if (e.getCause() instanceof ApiException) {
                ApiException apiException = (ApiException) e.getCause();
                b.a(this.c, AaidIdConstant.getToken, strA, apiException.getStatusCode());
                throw apiException;
            }
            b.a(this.c, AaidIdConstant.getToken, strA, com.huawei.hms.aaid.constant.a.ERROR_INTERNAL_ERROR);
            throw com.huawei.hms.aaid.constant.a.a(com.huawei.hms.aaid.constant.a.ERROR_INTERNAL_ERROR);
        }
    }

    public void deleteToken(String str, String str2) throws ApiException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw com.huawei.hms.aaid.constant.a.a(com.huawei.hms.aaid.constant.a.ERROR_MAIN_THREAD);
        }
        if (ProxyCenter.getInstance().getProxy() != null) {
            HMSLog.i(TAG, "use proxy delete token");
            ProxyCenter.getInstance().getProxy().deleteToken(this.c);
            return;
        }
        String strA = b.a(this.c, AaidIdConstant.deleteToken);
        try {
            DeleteTokenReq deleteTokenReq = new DeleteTokenReq();
            deleteTokenReq.setAppId(str);
            deleteTokenReq.setScope(str2);
            deleteTokenReq.setPkgName(this.c.getPackageName());
            if (TextUtils.isEmpty(str)) {
                deleteTokenReq.setAppId(Util.getAppId(this.c));
            }
            if (TextUtils.isEmpty(str2)) {
                deleteTokenReq.setScope("HCM");
            }
            String strA2 = com.huawei.hms.aaid.encrypt.b.a(this.c, AaidIdConstant.PushClientSelfInfo.FILE_NAME);
            if (!TextUtils.isEmpty(strA2)) {
                deleteTokenReq.setToken(strA2);
            }
            i.a(this.e.doWrite(new com.huawei.hms.aaid.a.b(AaidIdConstant.deleteToken, JsonUtil.createJsonString(deleteTokenReq), strA)));
            com.huawei.hms.aaid.encrypt.b.b(this.c, AaidIdConstant.PushClientSelfInfo.FILE_NAME);
        } catch (Exception e) {
            if (e.getCause() instanceof ApiException) {
                ApiException apiException = (ApiException) e.getCause();
                b.a(this.c, AaidIdConstant.deleteToken, strA, apiException.getStatusCode());
                throw apiException;
            }
            b.a(this.c, AaidIdConstant.deleteToken, strA, com.huawei.hms.aaid.constant.a.ERROR_INTERNAL_ERROR);
            throw com.huawei.hms.aaid.constant.a.a(com.huawei.hms.aaid.constant.a.ERROR_INTERNAL_ERROR);
        }
    }
}
