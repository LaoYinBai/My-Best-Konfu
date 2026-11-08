package com.kwad.sdk.crash.utils;

import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.text.SimpleDateFormat;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class j {
    private static SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String a(long j) {
        String strValueOf;
        String strValueOf2;
        long j2 = j / 60000;
        long j3 = (j - (60000 * j2)) / 1000;
        if (j2 < 10) {
            strValueOf = PushConstants.PUSH_TYPE_NOTIFY + j2;
        } else {
            strValueOf = String.valueOf(j2);
        }
        if (j3 < 10) {
            strValueOf2 = PushConstants.PUSH_TYPE_NOTIFY + j3;
        } else {
            strValueOf2 = String.valueOf(j3);
        }
        return strValueOf + ":" + strValueOf2;
    }
}
