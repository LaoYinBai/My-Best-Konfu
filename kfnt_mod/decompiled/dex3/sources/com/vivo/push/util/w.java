package com.vivo.push.util;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class w implements c {
    public static String a = "SpCache";
    private static String c = "com.vivo.push.cache";
    public SharedPreferences b;

    @Override // com.vivo.push.util.c
    public final boolean a(Context context) {
        if (this.b != null) {
            return true;
        }
        this.b = context.getSharedPreferences(c, 0);
        return true;
    }

    @Override // com.vivo.push.util.c
    public final String a(String str, String str2) {
        String string = this.b.getString(str, str2);
        n.d(a, "getString " + str + " is " + string);
        return string;
    }

    @Override // com.vivo.push.util.c
    public final void b(String str, String str2) {
        SharedPreferences.Editor editorEdit = this.b.edit();
        if (editorEdit != null) {
            editorEdit.putString(str, str2);
            a.a(editorEdit);
            n.d(a, "putString by " + str);
            return;
        }
        n.b(a, "putString error by " + str);
    }
}
