package com.huawei.hms.common.api;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.huawei.hms.api.Api;
import com.huawei.hms.api.ConnectionResult;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.common.HuaweiApi;
import com.huawei.hms.support.log.HMSLog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class AvailabilityException extends Exception {
    private String a = "AvailabilityException";
    private String b = null;

    private void a(int i) {
        if (i != 21) {
            switch (i) {
                case 0:
                    this.b = FirebaseAnalytics.Param.SUCCESS;
                    break;
                case 1:
                    this.b = "SERVICE_MISSING";
                    break;
                case 2:
                    this.b = "SERVICE_VERSION_UPDATE_REQUIRED";
                    break;
                case 3:
                    this.b = "SERVICE_DISABLED";
                    break;
                default:
                    this.b = "INTERNAL_ERROR";
                    break;
            }
        }
        this.b = "ANDROID_VERSION_UNSUPPORT";
    }

    private ConnectionResult b(int i) {
        HMSLog.i(this.a, "The availability check result is: " + i);
        a(i);
        return new ConnectionResult(i);
    }

    public ConnectionResult getConnectionResult(HuaweiApiCallable huaweiApiCallable) {
        if (huaweiApiCallable == null || huaweiApiCallable.getHuaweiApi() == null) {
            HMSLog.e(this.a, "The huaweiApi is null.");
            return b(8);
        }
        return b(HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(huaweiApiCallable.getHuaweiApi().getContext(), 30000000));
    }

    public ConnectionResult getConnectionResult(HuaweiApi<? extends Api.ApiOptions> huaweiApi) {
        if (huaweiApi == null) {
            HMSLog.e(this.a, "The huaweiApi is null.");
            return b(8);
        }
        return b(HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(huaweiApi.getContext(), 30000000));
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.b;
    }
}
