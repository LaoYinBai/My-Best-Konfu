package com.huawei.hms.push.ups;

import android.content.Context;
import com.huawei.hmf.tasks.c;
import com.huawei.hmf.tasks.f;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.push.HmsMessaging;
import com.huawei.hms.push.ups.entity.CodeResult;
import com.huawei.hms.push.ups.entity.TokenResult;
import com.huawei.hms.push.ups.entity.UPSRegisterCallBack;
import com.huawei.hms.push.ups.entity.UPSTurnCallBack;
import com.huawei.hms.push.ups.entity.UPSUnRegisterCallBack;
import com.huawei.hms.support.api.push.c.a;
import com.huawei.hms.support.log.HMSLog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class UPSService {
    private UPSService() {
    }

    public static void registerToken(Context context, String str, String str2, String str3, UPSRegisterCallBack uPSRegisterCallBack) {
        HMSLog.i("UPSService", "invoke registerToken");
        Preconditions.checkNotNull(uPSRegisterCallBack);
        if (!a.a()) {
            uPSRegisterCallBack.onResult(new TokenResult(com.huawei.hms.push.constant.a.ERROR_OPERATION_NOT_SUPPORTED.b()));
            return;
        }
        try {
            uPSRegisterCallBack.onResult(new TokenResult(HmsInstanceId.getInstance(context).getToken(str, null)));
        } catch (ApiException e) {
            uPSRegisterCallBack.onResult(new TokenResult(e.getStatusCode(), e.getMessage()));
        }
    }

    public static void unRegisterToken(Context context, UPSUnRegisterCallBack uPSUnRegisterCallBack) {
        HMSLog.i("UPSService", "invoke unRegisterToken");
        Preconditions.checkNotNull(uPSUnRegisterCallBack);
        if (!a.a()) {
            uPSUnRegisterCallBack.onResult(new TokenResult(com.huawei.hms.push.constant.a.ERROR_OPERATION_NOT_SUPPORTED.b()));
            return;
        }
        try {
            HmsInstanceId.getInstance(context).deleteToken(null, null);
            uPSUnRegisterCallBack.onResult(new TokenResult());
        } catch (ApiException e) {
            uPSUnRegisterCallBack.onResult(new TokenResult(e.getStatusCode(), e.getMessage()));
        }
    }

    public static void turnOnPush(Context context, final UPSTurnCallBack uPSTurnCallBack) {
        HMSLog.i("UPSService", "invoke turnOnPush");
        Preconditions.checkNotNull(uPSTurnCallBack);
        if (!a.a()) {
            uPSTurnCallBack.onResult(new TokenResult(com.huawei.hms.push.constant.a.ERROR_OPERATION_NOT_SUPPORTED.b()));
        } else {
            HmsMessaging.getInstance(context).turnOnPush().a(new c<Void>() { // from class: com.huawei.hms.push.ups.UPSService.1
                @Override // com.huawei.hmf.tasks.c
                public void onComplete(f<Void> fVar) {
                    if (fVar.c()) {
                        uPSTurnCallBack.onResult(new CodeResult());
                    } else {
                        ApiException apiException = (ApiException) fVar.f();
                        uPSTurnCallBack.onResult(new CodeResult(apiException.getStatusCode(), apiException.getMessage()));
                    }
                }
            });
        }
    }

    public static void turnOffPush(Context context, final UPSTurnCallBack uPSTurnCallBack) {
        HMSLog.i("UPSService", "invoke turnOffPush");
        Preconditions.checkNotNull(uPSTurnCallBack);
        if (!a.a()) {
            uPSTurnCallBack.onResult(new TokenResult(com.huawei.hms.push.constant.a.ERROR_OPERATION_NOT_SUPPORTED.b()));
        } else {
            HmsMessaging.getInstance(context).turnOffPush().a(new c<Void>() { // from class: com.huawei.hms.push.ups.UPSService.2
                @Override // com.huawei.hmf.tasks.c
                public void onComplete(f<Void> fVar) {
                    if (fVar.c()) {
                        uPSTurnCallBack.onResult(new CodeResult());
                    } else {
                        ApiException apiException = (ApiException) fVar.f();
                        uPSTurnCallBack.onResult(new CodeResult(apiException.getStatusCode(), apiException.getMessage()));
                    }
                }
            });
        }
    }
}
