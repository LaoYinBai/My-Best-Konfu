package com.huawei.agconnect.a.a;

import android.content.Context;
import android.util.Log;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class g extends f {
    private final Map<String, String> a;
    private final Object b;
    private SecretKey c;

    g(Context context) {
        super(context);
        this.a = new HashMap();
        this.b = new Object();
        try {
            this.c = e.a(c.a(a("/AD91D45E3E72DB6989DDCB13287E75061FABCB933D886E6C6ABEF0939B577138")), c.a(a("/B314B3BF013DF5AC4134E880AF3D2B7C9FFBE8F0305EAC1C898145E2BCF1F21C")), c.a(a("/C767BD8FDF53E53D059BE95B09E2A71056F5F180AECC62836B287ACA5793421B")), c.a(a("/DCB3E6D4C2CF80F30D89CDBC412C964DA8381BB84668769391FBCC3E329AD0FD")));
        } catch (Exception unused) {
            Log.e("SecurityResourcesReader", "Exception when reading the 'K&I' for 'Config'.");
            this.c = null;
        }
    }

    @Override // com.huawei.agconnect.a.a.f, com.huawei.agconnect.a.a.b
    public String a(String str, String str2) {
        if (this.c == null) {
            return str2;
        }
        synchronized (this.b) {
            String str3 = this.a.get(str);
            if (str3 != null) {
                return str3;
            }
            String strA = a(str);
            if (strA == null) {
                return str2;
            }
            try {
                String str4 = new String(a(this.c, c.a(strA)), C.UTF8_NAME);
                this.a.put(str, str4);
                return str4;
            } catch (Exception unused) {
                Log.e("SecurityResourcesReader", "Exception when reading the 'V' for 'Config'.");
                return str2;
            }
        }
    }

    private String a(String str) {
        return super.a(str, (String) null);
    }

    private static byte[] a(SecretKey secretKey, byte[] bArr) throws GeneralSecurityException {
        if (secretKey == null || bArr == null) {
            throw new NullPointerException("key or cipherText must not be null.");
        }
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, 1, 17);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(2, secretKey, new IvParameterSpec(bArrCopyOfRange));
        return cipher.doFinal(bArr, bArrCopyOfRange.length + 1, (bArr.length - bArrCopyOfRange.length) - 1);
    }
}
