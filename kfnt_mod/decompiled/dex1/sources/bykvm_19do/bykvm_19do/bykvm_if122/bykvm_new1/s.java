package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1;

import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class s {
    public static void a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            a(str2);
            throw null;
        }
    }

    private static void a(String str) {
        throw new IllegalArgumentException(str);
    }
}
