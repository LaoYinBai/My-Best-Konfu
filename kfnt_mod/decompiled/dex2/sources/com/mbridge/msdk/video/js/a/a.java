package com.mbridge.msdk.video.js.a;

import android.content.res.Configuration;
import com.mbridge.msdk.foundation.tools.s;

/* compiled from: DefaultJSActivity.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a implements com.mbridge.msdk.video.js.a {
    @Override // com.mbridge.msdk.video.js.a
    public void a() {
        s.a("DefaultJSActivity", "DefaultJSActivity-onPause");
    }

    @Override // com.mbridge.msdk.video.js.a
    public void b() {
        s.a("DefaultJSActivity", "DefaultJSActivity-onResume");
    }

    @Override // com.mbridge.msdk.video.js.a
    public void c() {
        s.a("DefaultJSActivity", "DefaultJSActivity-onDestory");
    }

    @Override // com.mbridge.msdk.video.js.a
    public final void d() {
        s.a("DefaultJSActivity", "DefaultJSActivity-onStop");
    }

    @Override // com.mbridge.msdk.video.js.a
    public final void e() {
        s.a("DefaultJSActivity", "DefaultJSActivity-onStart");
    }

    @Override // com.mbridge.msdk.video.js.a
    public final void f() {
        s.a("DefaultJSActivity", "DefaultJSActivity-onRestart");
    }

    @Override // com.mbridge.msdk.video.js.a
    public void a(Configuration configuration) {
        s.a("DefaultJSActivity", "DefaultJSActivity-onConfigurationChanged:" + configuration.orientation);
    }

    @Override // com.mbridge.msdk.video.js.a
    public void g() {
        s.a("DefaultJSActivity", "DefaultJSActivity-onBackPressed");
    }

    @Override // com.mbridge.msdk.video.js.a
    public int h() {
        s.a("DefaultJSActivity", "isSystemResume");
        return 0;
    }

    @Override // com.mbridge.msdk.video.js.a
    public void a(int i) {
        s.a("DefaultJSActivity", "setSystemResume,isResume:" + i);
    }
}
