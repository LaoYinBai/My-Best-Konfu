package com.sigmob.sdk.nativead;

import com.sigmob.sdk.videoplayer.VideoAdView;
import java.lang.ref.WeakReference;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class j implements i {
    private WeakReference<VideoAdView> a;
    private h b;

    public j(VideoAdView videoAdView) {
        this.a = new WeakReference<>(videoAdView);
    }

    private VideoAdView i() {
        return this.a.get();
    }

    @Override // com.sigmob.sdk.nativead.i
    public void a() {
        VideoAdView videoAdViewI = i();
        if (videoAdViewI != null) {
            videoAdViewI.C();
        }
    }

    @Override // com.sigmob.sdk.nativead.i
    public void a(h hVar) {
        this.b = hVar;
    }

    @Override // com.sigmob.sdk.nativead.i
    public void b() {
        VideoAdView videoAdViewI = i();
        if (videoAdViewI != null) {
            videoAdViewI.B();
        }
    }

    @Override // com.sigmob.sdk.nativead.i
    public void c() {
        VideoAdView videoAdViewI = i();
        if (videoAdViewI != null) {
            videoAdViewI.B();
        }
    }

    @Override // com.sigmob.sdk.nativead.i
    public void d() {
        VideoAdView videoAdViewI = i();
        if (videoAdViewI != null) {
            videoAdViewI.D();
        }
    }

    @Override // com.sigmob.sdk.nativead.i
    public int e() {
        VideoAdView videoAdViewI = i();
        if (videoAdViewI == null || videoAdViewI.getDuration() == 0) {
            return 0;
        }
        return (int) (videoAdViewI.getDuration() / 1000);
    }

    @Override // com.sigmob.sdk.nativead.i
    public h f() {
        return this.b;
    }

    @Override // com.sigmob.sdk.nativead.i
    public void g() {
        this.b = null;
    }

    @Override // com.sigmob.sdk.nativead.i
    public int h() {
        VideoAdView videoAdViewI = i();
        if (videoAdViewI != null) {
            return (int) (videoAdViewI.getCurrentPositionWhenPlaying() / 1000);
        }
        return 0;
    }
}
