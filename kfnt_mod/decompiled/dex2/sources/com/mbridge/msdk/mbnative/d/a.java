package com.mbridge.msdk.mbnative.d;

import android.content.Context;
import android.text.TextUtils;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.out.Campaign;
import com.mbridge.msdk.out.Frame;
import com.mbridge.msdk.out.NativeListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: AdListenerProxy.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a implements NativeListener.NativeAdListener {
    private static final String a = "a";
    private NativeListener.NativeAdListener b;
    private boolean c = false;
    private String d;
    private Context e;
    private boolean f;

    public final void a(boolean z) {
        this.f = z;
    }

    public final void a(String str) {
        this.d = str;
    }

    public a() {
    }

    public a(NativeListener.NativeAdListener nativeAdListener) {
        this.b = nativeAdListener;
    }

    public final boolean a() {
        return this.c;
    }

    public final void b() {
        this.c = true;
    }

    @Override // com.mbridge.msdk.out.NativeListener.NativeAdListener
    public void onAdLoaded(List<Campaign> list, int i) {
        CopyOnWriteArrayList copyOnWriteArrayList;
        try {
            this.c = false;
            synchronized (list) {
                copyOnWriteArrayList = new CopyOnWriteArrayList(list);
            }
            if (this.b != null) {
                if (copyOnWriteArrayList.size() > 0) {
                    this.b.onAdLoaded(copyOnWriteArrayList, i);
                } else {
                    this.b.onAdLoaded(list, i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.mbridge.msdk.out.NativeListener.NativeAdListener
    public void onAdLoadError(String str) {
        this.c = false;
        s.a(a, "onAdLoadError,message:" + str);
        if (this.b != null) {
            this.b.onAdLoadError(str);
            if (this.e == null) {
                this.e = com.mbridge.msdk.foundation.controller.a.f().j();
            }
            if (TextUtils.isEmpty(this.d)) {
                return;
            }
            com.mbridge.msdk.mbnative.e.a.a(this.e, str, this.d, this.f);
        }
    }

    @Override // com.mbridge.msdk.out.NativeListener.NativeAdListener
    public void onAdClick(Campaign campaign) {
        s.a(a, "onAdClick,campaign:" + campaign);
        if (this.b != null) {
            this.b.onAdClick(campaign);
        }
    }

    @Override // com.mbridge.msdk.out.NativeListener.NativeAdListener
    public void onAdFramesLoaded(List<Frame> list) {
        if (this.b != null) {
            this.b.onAdFramesLoaded(list);
        }
    }

    @Override // com.mbridge.msdk.out.NativeListener.NativeAdListener
    public void onLoggingImpression(int i) {
        s.a(a, "onLoggingImpression,adsourceType:" + i);
        if (this.b != null) {
            this.b.onLoggingImpression(i);
        }
    }
}
