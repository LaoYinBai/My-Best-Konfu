package com.huawei.hms.common.util;

import android.util.Base64;
import com.huawei.hms.support.log.HMSLog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a {
    public static byte[] a(String str) {
        byte[] bArr = new byte[0];
        if (str == null) {
            return bArr;
        }
        try {
            return Base64.decode(str, 10);
        } catch (IllegalArgumentException e) {
            HMSLog.e("Base64Utils", "decodeUrlSafe failed : " + e.getMessage());
            return bArr;
        }
    }

    public static String a(byte[] bArr) {
        if (bArr != null) {
            return Base64.encodeToString(bArr, 10);
        }
        return null;
    }
}
