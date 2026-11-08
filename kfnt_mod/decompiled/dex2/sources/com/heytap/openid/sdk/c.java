package com.heytap.openid.sdk;

import android.content.Context;
import android.content.ServiceConnection;
import androidx.annotation.Keep;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {

    @Keep
    public com.heytap.openid.a a = null;

    @Keep
    public String b = null;

    @Keep
    public String c = null;

    @Keep
    public final Object d = new Object();

    @Keep
    public ServiceConnection e = new b(this);

    @Keep
    private static class a {

        @Keep
        public static final c a = new c(null);
    }

    @Keep
    public /* synthetic */ c(b bVar) {
    }

    @Keep
    public native synchronized String a(Context context, String str);

    @Keep
    public native boolean a(Context context);

    @Keep
    public final native String b(Context context, String str);
}
