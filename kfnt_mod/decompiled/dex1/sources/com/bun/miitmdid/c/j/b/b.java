package com.bun.miitmdid.c.j.b;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.Keep;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    @Keep
    private static Context f3309a;

    /* renamed from: b, reason: collision with root package name */
    @Keep
    private static boolean f3310b;

    /* renamed from: c, reason: collision with root package name */
    @Keep
    private static b f3311c;

    @Keep
    private static com.bun.miitmdid.c.j.b.a d;

    @Keep
    private static c e;

    @Keep
    private static c f;

    @Keep
    private static c g;

    @Keep
    private static Object h = new Object();

    @Keep
    private static HandlerThread i;

    @Keep
    private static Handler j;

    @Keep
    private static String k;

    @Keep
    private static String l;

    @Keep
    private static String m;

    @Keep
    private static String n;

    @Keep
    static final class a extends Handler {
        @Keep
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        @Keep
        public native void handleMessage(Message message);
    }

    @Keep
    private b() {
    }

    @Keep
    public static native b a(Context context);

    @Keep
    public static native String a(String str, String str2);

    @Keep
    private static native void a(Context context, int i2, String str);

    @Keep
    private native void b(int i2, String str);

    @Keep
    static native /* synthetic */ com.bun.miitmdid.c.j.b.a c();

    @Keep
    static native /* synthetic */ String c(String str);

    @Keep
    static native /* synthetic */ Object d();

    @Keep
    public static native void e();

    @Keep
    private static native void f();

    @Keep
    public native String a();

    @Keep
    public native String a(String str);

    @Keep
    public native void a(int i2, String str);

    @Keep
    public native String b(String str);

    @Keep
    public native boolean b();
}
