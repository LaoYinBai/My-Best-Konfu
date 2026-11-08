package com.huawei.hms.aaid.encrypt;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.aaid.constant.AaidIdConstant;
import com.huawei.hms.opendevice.a.e;
import com.huawei.hms.opendevice.a.f;
import com.ss.union.game.sdk.push.R;
import java.io.UnsupportedEncodingException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class c {
    private static e a;

    private static String a() {
        return "2A57086C86EF54970C1E6EB37BFC72B1";
    }

    private static e a(Context context, com.huawei.hms.aaid.b.c cVar) {
        if (a == null) {
            if (!TextUtils.isEmpty(cVar.b(AaidIdConstant.PushLocalSecret.WORK_KEY))) {
                a = e.a(cVar.b(AaidIdConstant.PushLocalSecret.ROOT_KEY_FIRST), cVar.b(AaidIdConstant.PushLocalSecret.ROOT_KEY_SECOND), cVar.b(AaidIdConstant.PushLocalSecret.ROOT_KEY_THIRD), cVar.b(AaidIdConstant.PushLocalSecret.ROOT_KEY_SALT));
            } else {
                b(context);
            }
        }
        return a;
    }

    public static String a(Context context) {
        com.huawei.hms.aaid.b.c cVar = new com.huawei.hms.aaid.b.c(context, AaidIdConstant.PushLocalSecret.FILE_NAME);
        return f.b(cVar.b(AaidIdConstant.PushLocalSecret.WORK_KEY), a(context, cVar));
    }

    public static void b(Context context) {
        com.huawei.hms.aaid.b.c cVar = new com.huawei.hms.aaid.b.c(context, AaidIdConstant.PushLocalSecret.FILE_NAME);
        if (TextUtils.isEmpty(cVar.b(AaidIdConstant.PushLocalSecret.WORK_KEY))) {
            byte[] bArrA = com.huawei.hms.opendevice.a.b.a(32);
            byte[] bArrA2 = com.huawei.hms.opendevice.a.b.a(32);
            byte[] bArrA3 = com.huawei.hms.opendevice.a.b.a(32);
            byte[] bArrA4 = com.huawei.hms.opendevice.a.b.a(32);
            String strA = a.a(bArrA);
            String strA2 = a.a(bArrA2);
            String strA3 = a.a(bArrA3);
            a = e.a(strA, strA2, strA3, bArrA4);
            cVar.a(AaidIdConstant.PushLocalSecret.ROOT_KEY_FIRST, strA);
            cVar.a(AaidIdConstant.PushLocalSecret.ROOT_KEY_SECOND, strA2);
            cVar.a(AaidIdConstant.PushLocalSecret.ROOT_KEY_THIRD, strA3);
            cVar.a(AaidIdConstant.PushLocalSecret.ROOT_KEY_SALT, a.a(bArrA4));
            cVar.a(AaidIdConstant.PushLocalSecret.WORK_KEY, f.a(a.a(com.huawei.hms.opendevice.a.b.a(32)), a));
        }
    }

    public static byte[] c(Context context) throws UnsupportedEncodingException {
        byte[] bArrA = a.a(context.getString(R.string.push_cat_head));
        byte[] bArrA2 = a.a(context.getString(R.string.push_cat_body));
        return a(a(a(bArrA, bArrA2), a.a(a())));
    }

    private static byte[] a(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null || bArr.length == 0 || bArr2.length == 0) {
            return new byte[0];
        }
        int length = bArr.length;
        if (length != bArr2.length) {
            return new byte[0];
        }
        byte[] bArr3 = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr3[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
        return bArr3;
    }

    private static byte[] a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return new byte[0];
        }
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) (bArr[i] >> 2);
        }
        return bArr;
    }
}
