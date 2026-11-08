package com.mbridge.msdk.video.js.factory;

import com.mbridge.msdk.video.js.a.d;
import com.mbridge.msdk.video.js.a.g;
import com.mbridge.msdk.video.js.c;
import com.mbridge.msdk.video.js.e;
import com.mbridge.msdk.video.js.f;
import com.mbridge.msdk.video.js.h;
import com.mbridge.msdk.video.js.i;

/* compiled from: DefaultJSFactory.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a implements IJSFactory {
    protected com.mbridge.msdk.video.js.a a;
    protected c b;
    protected i c;
    protected f d;
    protected e e;
    protected h f;
    protected com.mbridge.msdk.video.js.b g;

    @Override // com.mbridge.msdk.video.js.factory.IJSFactory
    public com.mbridge.msdk.video.js.a getActivityProxy() {
        if (this.a == null) {
            this.a = new com.mbridge.msdk.video.js.a.a();
        }
        return this.a;
    }

    @Override // com.mbridge.msdk.video.js.factory.IJSFactory
    public c getJSCommon() {
        if (this.b == null) {
            this.b = new com.mbridge.msdk.video.js.a.c();
        }
        return this.b;
    }

    @Override // com.mbridge.msdk.video.js.factory.IJSFactory
    public i getJSVideoModule() {
        if (this.c == null) {
            this.c = new g();
        }
        return this.c;
    }

    @Override // com.mbridge.msdk.video.js.factory.IJSFactory
    public f getJSNotifyProxy() {
        if (this.d == null) {
            this.d = new com.mbridge.msdk.video.js.a.e();
        }
        return this.d;
    }

    @Override // com.mbridge.msdk.video.js.factory.IJSFactory
    public e getJSContainerModule() {
        if (this.e == null) {
            this.e = new d();
        }
        return this.e;
    }

    @Override // com.mbridge.msdk.video.js.factory.IJSFactory
    public h getIJSRewardVideoV1() {
        if (this.f == null) {
            this.f = new com.mbridge.msdk.video.js.a.f();
        }
        return this.f;
    }

    @Override // com.mbridge.msdk.video.js.factory.IJSFactory
    public com.mbridge.msdk.video.js.b getJSBTModule() {
        if (this.g == null) {
            this.g = new com.mbridge.msdk.video.js.a.b();
        }
        return this.g;
    }
}
