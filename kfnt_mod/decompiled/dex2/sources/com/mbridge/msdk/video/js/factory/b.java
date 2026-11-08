package com.mbridge.msdk.video.js.factory;

import android.app.Activity;
import android.webkit.WebView;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.video.bt.module.MBridgeBTContainer;
import com.mbridge.msdk.video.js.a.h;
import com.mbridge.msdk.video.js.a.j;
import com.mbridge.msdk.video.js.a.k;
import com.mbridge.msdk.video.js.a.l;
import com.mbridge.msdk.video.js.a.m;
import com.mbridge.msdk.video.js.a.n;
import com.mbridge.msdk.video.js.c;
import com.mbridge.msdk.video.js.e;
import com.mbridge.msdk.video.js.f;
import com.mbridge.msdk.video.js.i;
import com.mbridge.msdk.video.module.MBridgeContainerView;
import com.mbridge.msdk.video.module.MBridgeVideoView;

/* compiled from: JSFactory.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class b extends a {
    private Activity h;
    private WebView i;
    private MBridgeVideoView j;
    private MBridgeContainerView k;
    private CampaignEx l;
    private MBridgeBTContainer m;
    private c.a n;
    private String o;

    public b(Activity activity) {
        this.h = activity;
    }

    public b(Activity activity, MBridgeBTContainer mBridgeBTContainer, WebView webView) {
        this.h = activity;
        this.m = mBridgeBTContainer;
        this.i = webView;
    }

    public b(Activity activity, WebView webView, MBridgeVideoView mBridgeVideoView, MBridgeContainerView mBridgeContainerView, CampaignEx campaignEx, c.a aVar) {
        this.h = activity;
        this.i = webView;
        this.j = mBridgeVideoView;
        this.k = mBridgeContainerView;
        this.l = campaignEx;
        this.n = aVar;
        this.o = mBridgeVideoView.getUnitId();
    }

    @Override // com.mbridge.msdk.video.js.factory.a, com.mbridge.msdk.video.js.factory.IJSFactory
    public final com.mbridge.msdk.video.js.a getActivityProxy() {
        if (this.i == null) {
            return super.getActivityProxy();
        }
        if (this.a == null) {
            this.a = new h(this.i);
        }
        return this.a;
    }

    @Override // com.mbridge.msdk.video.js.factory.a, com.mbridge.msdk.video.js.factory.IJSFactory
    public final f getJSNotifyProxy() {
        if (this.i == null) {
            return super.getJSNotifyProxy();
        }
        if (this.d == null) {
            this.d = new l(this.i);
        }
        return this.d;
    }

    @Override // com.mbridge.msdk.video.js.factory.a, com.mbridge.msdk.video.js.factory.IJSFactory
    public final c getJSCommon() {
        if (this.h == null || this.l == null) {
            return super.getJSCommon();
        }
        if (this.b == null) {
            this.b = new j(this.h, this.l);
        }
        this.b.a(this.h);
        this.b.a(this.o);
        this.b.a(this.n);
        return this.b;
    }

    @Override // com.mbridge.msdk.video.js.factory.a, com.mbridge.msdk.video.js.factory.IJSFactory
    public final i getJSVideoModule() {
        if (this.j == null) {
            return super.getJSVideoModule();
        }
        if (this.c == null) {
            this.c = new n(this.j);
        }
        return this.c;
    }

    @Override // com.mbridge.msdk.video.js.factory.a, com.mbridge.msdk.video.js.factory.IJSFactory
    public final e getJSContainerModule() {
        if (this.k == null) {
            return super.getJSContainerModule();
        }
        if (this.e == null) {
            this.e = new k(this.k);
        }
        return this.e;
    }

    @Override // com.mbridge.msdk.video.js.factory.a, com.mbridge.msdk.video.js.factory.IJSFactory
    public final com.mbridge.msdk.video.js.h getIJSRewardVideoV1() {
        if (this.k == null || this.h == null) {
            return super.getIJSRewardVideoV1();
        }
        if (this.f == null) {
            this.f = new m(this.h, this.k);
        }
        return this.f;
    }

    @Override // com.mbridge.msdk.video.js.factory.a, com.mbridge.msdk.video.js.factory.IJSFactory
    public final com.mbridge.msdk.video.js.b getJSBTModule() {
        if (this.h == null || this.m == null) {
            return super.getJSBTModule();
        }
        if (this.g == null) {
            this.g = new com.mbridge.msdk.video.js.a.i(this.h, this.m);
        }
        return this.g;
    }

    public final void a(j jVar) {
        this.b = jVar;
    }
}
