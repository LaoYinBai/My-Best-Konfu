package com.huawei.hms.aaid;

import android.app.Activity;
import android.content.Context;
import com.huawei.hmf.tasks.f;
import com.huawei.hmf.tasks.g;
import com.huawei.hms.aaid.a.e;
import com.huawei.hms.aaid.b.a;
import com.huawei.hms.aaid.b.b;
import com.huawei.hms.aaid.b.c;
import com.huawei.hms.aaid.constant.AaidIdConstant;
import com.huawei.hms.aaid.entity.TokenReq;
import com.huawei.hms.aaid.entity.TokenResult;
import com.huawei.hms.api.Api;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.HuaweiApi;
import com.huawei.hms.common.internal.AbstractClientBuilder;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.utils.JsonUtil;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class HmsInstanceIdEx {
    public static final String TAG = "HmsInstanceIdEx";
    private static final byte[] a = new byte[0];
    private Context b;
    private c c;
    private HuaweiApi<Api.ApiOptions.NoOptions> d;

    private HmsInstanceIdEx(Context context) {
        this.b = null;
        this.c = null;
        this.b = context;
        this.c = new c(context, "aaid");
        Api api = new Api(HuaweiApiAvailability.HMS_API_NAME_PUSH);
        if (context instanceof Activity) {
            this.d = new HuaweiApi<>((Activity) context, (Api<Api.ApiOptions>) api, (Api.ApiOptions) null, (AbstractClientBuilder) new e());
        } else {
            this.d = new HuaweiApi<>(context, (Api<Api.ApiOptions>) api, (Api.ApiOptions) null, new e());
        }
        this.d.setKitSdkVersion(40004301);
    }

    public static HmsInstanceIdEx getInstance(Context context) {
        Preconditions.checkNotNull(context);
        return new HmsInstanceIdEx(context);
    }

    public f<TokenResult> getToken() {
        String strA = b.a(this.b, AaidIdConstant.getToken);
        try {
            TokenReq tokenReqA = a.a(null, null, this.b);
            tokenReqA.setAaid(HmsInstanceId.getInstance(this.b).getId());
            return this.d.doWrite(new com.huawei.hms.aaid.a.c(AaidIdConstant.getToken, JsonUtil.createJsonString(tokenReqA), this.b, strA));
        } catch (RuntimeException unused) {
            g gVar = new g();
            gVar.a((Exception) com.huawei.hms.aaid.constant.a.a(com.huawei.hms.aaid.constant.a.ERROR_INTERNAL_ERROR));
            b.a(this.b, AaidIdConstant.getToken, strA, com.huawei.hms.aaid.constant.a.ERROR_INTERNAL_ERROR);
            return gVar.a();
        } catch (Exception unused2) {
            g gVar2 = new g();
            gVar2.a((Exception) com.huawei.hms.aaid.constant.a.a(com.huawei.hms.aaid.constant.a.ERROR_INTERNAL_ERROR));
            b.a(this.b, AaidIdConstant.getToken, strA, com.huawei.hms.aaid.constant.a.ERROR_INTERNAL_ERROR);
            return gVar2.a();
        }
    }

    public String getAAId(String str) throws ApiException {
        if (str == null) {
            throw com.huawei.hms.aaid.constant.a.a(com.huawei.hms.aaid.constant.a.ERROR_ARGUMENTS_INVALID);
        }
        try {
            if (this.c.d(str)) {
                return this.c.b(str);
            }
            String strA = a.a(str + a.a(this.b));
            this.c.a(str, strA);
            this.c.a(a(str), Long.valueOf(System.currentTimeMillis()));
            return strA;
        } catch (RuntimeException unused) {
            throw com.huawei.hms.aaid.constant.a.a(com.huawei.hms.aaid.constant.a.ERROR_INTERNAL_ERROR);
        } catch (Exception unused2) {
            throw com.huawei.hms.aaid.constant.a.a(com.huawei.hms.aaid.constant.a.ERROR_INTERNAL_ERROR);
        }
    }

    public long getCreationTime(String str) throws ApiException {
        if (str == null) {
            throw com.huawei.hms.aaid.constant.a.a(com.huawei.hms.aaid.constant.a.ERROR_ARGUMENTS_INVALID);
        }
        try {
            if (!this.c.d(a(str))) {
                getAAId(str);
            }
            return this.c.c(a(str));
        } catch (RuntimeException unused) {
            throw com.huawei.hms.aaid.constant.a.a(com.huawei.hms.aaid.constant.a.ERROR_INTERNAL_ERROR);
        } catch (Exception unused2) {
            throw com.huawei.hms.aaid.constant.a.a(com.huawei.hms.aaid.constant.a.ERROR_INTERNAL_ERROR);
        }
    }

    public void deleteAAID(String str) throws ApiException {
        if (str == null) {
            throw com.huawei.hms.aaid.constant.a.a(com.huawei.hms.aaid.constant.a.ERROR_ARGUMENTS_INVALID);
        }
        try {
            if (this.c.d(str)) {
                this.c.e(str);
                this.c.e(a(str));
            }
        } catch (RuntimeException unused) {
            throw com.huawei.hms.aaid.constant.a.a(com.huawei.hms.aaid.constant.a.ERROR_INTERNAL_ERROR);
        } catch (Exception unused2) {
            throw com.huawei.hms.aaid.constant.a.a(com.huawei.hms.aaid.constant.a.ERROR_INTERNAL_ERROR);
        }
    }

    private String a(String str) {
        return AaidIdConstant.CREATE_TIME + str;
    }
}
