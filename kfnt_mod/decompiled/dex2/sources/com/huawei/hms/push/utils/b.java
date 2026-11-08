package com.huawei.hms.push.utils;

import android.content.Context;
import com.huawei.hms.common.internal.ResponseErrorCode;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.hianalytics.HiAnalyticsClient;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class b {
    public static String a(Context context, String str) {
        return HiAnalyticsClient.reportEntry(context, str, 40004301);
    }

    public static void a(Context context, String str, ResponseErrorCode responseErrorCode) {
        HiAnalyticsClient.reportExit(context, str, responseErrorCode.getTransactionId(), responseErrorCode.getStatusCode(), responseErrorCode.getErrorCode(), 40004301);
    }

    public static void a(Context context, String str, String str2, com.huawei.hms.push.constant.a aVar) {
        HiAnalyticsClient.reportExit(context, str, str2, Status.SUCCESS.getStatusCode(), aVar.b(), 40004301);
    }

    public static void a(Context context, String str, String str2, int i) {
        HiAnalyticsClient.reportExit(context, str, str2, Status.SUCCESS.getStatusCode(), i, 40004301);
    }
}
