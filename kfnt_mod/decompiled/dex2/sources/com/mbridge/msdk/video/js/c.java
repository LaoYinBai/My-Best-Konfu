package com.mbridge.msdk.video.js;

import android.app.Activity;
import android.content.Context;
import com.mbridge.msdk.out.NativeListener;

/* compiled from: IJSCommon.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public interface c extends d {

    /* compiled from: IJSCommon.java */
    public interface a extends NativeListener.NativeTrackingListener {
        void a();

        void a(int i, String str);

        void a(boolean z);

        void b();
    }

    int a();

    void a(int i);

    void a(int i, String str);

    void a(Activity activity);

    void a(Context context);

    void a(com.mbridge.msdk.video.bt.module.a.b bVar);

    void a(a aVar);

    void a(com.mbridge.msdk.videocommon.d.c cVar);

    void a(String str);

    void a(boolean z);

    int b();

    void b(int i);

    void b(String str);

    void b(boolean z);

    void c(int i);

    boolean c();

    String d();

    void d(int i);

    void e();

    void e(int i);

    void f();

    void f(int i);

    void g();

    void g(int i);

    int h();

    String h(int i);

    int i();

    void i(int i);

    String j();
}
