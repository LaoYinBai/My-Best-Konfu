package com.huawei.agconnect.a.a;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.huawei.hms.aaid.constant.AaidIdConstant;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class f implements b {
    private final Context a;

    f(Context context) {
        this.a = context;
    }

    @Override // com.huawei.agconnect.a.a.b
    public String a(String str, String str2) {
        String strA = a(str);
        if (TextUtils.isEmpty(strA)) {
            return str2;
        }
        int identifier = this.a.getResources().getIdentifier(strA, "string", this.a.getPackageName());
        if (identifier == 0) {
            return str2;
        }
        try {
            return this.a.getResources().getString(identifier);
        } catch (Resources.NotFoundException unused) {
            return str2;
        }
    }

    private static String a(String str) {
        try {
            return "agc_" + c.a(a(str.getBytes(C.UTF8_NAME)));
        } catch (UnsupportedEncodingException unused) {
            return "";
        } catch (NoSuchAlgorithmException unused2) {
            return "";
        }
    }

    private static byte[] a(byte[] bArr) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(AaidIdConstant.SIGNATURE_SHA256).digest(bArr);
    }
}
