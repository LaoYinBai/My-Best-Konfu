package com.ss.android.message.a;

import android.content.Context;
import com.ss.android.pushmanager.setting.PushMultiProcessSharedProvider;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    public static String a(Context context, String str) {
        return context.getString(context.getResources().getIdentifier(str, PushMultiProcessSharedProvider.STRING_TYPE, context.getPackageName()));
    }

    public static int b(Context context, String str) {
        return context.getResources().getIdentifier(str, "drawable", context.getPackageName());
    }
}
