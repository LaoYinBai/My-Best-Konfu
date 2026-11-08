package com.huawei.hms.opendevice.a;

import com.huawei.hms.support.log.HMSLog;
import java.security.SecureRandom;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class b {
    public static byte[] a(int i) {
        try {
            byte[] bArr = new byte[i];
            new SecureRandom().nextBytes(bArr);
            return bArr;
        } catch (Exception e) {
            HMSLog.e("EncryptUtil", "generate secure random error" + e.getMessage());
            return new byte[0];
        }
    }
}
