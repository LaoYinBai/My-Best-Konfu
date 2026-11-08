package com.kwad.sdk.reward.c;

import android.content.Context;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import com.kwad.sdk.api.KsVideoPlayConfig;
import com.kwad.sdk.contentalliance.detail.video.DetailVideoView;
import com.kwad.sdk.contentalliance.detail.video.c;
import com.kwad.sdk.contentalliance.detail.video.e;
import com.kwad.sdk.core.config.b;
import com.kwad.sdk.core.response.a.d;
import com.kwad.sdk.core.response.a.f;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.core.response.model.VideoPlayerStatus;
import com.kwad.sdk.core.video.kwai.c;
import com.kwad.sdk.reward.KSRewardVideoActivityProxy;
import com.kwad.sdk.utils.j;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a implements com.kwad.sdk.reward.a.a {
    private String a;
    private AdTemplate b;
    private long c;
    private DetailVideoView d;
    private com.kwad.sdk.contentalliance.detail.video.a e;
    private KsVideoPlayConfig f;
    private VideoPlayerStatus g;
    private e h;
    private Context i;
    private boolean j;
    private boolean k;
    private boolean l;
    private final List<j.a> m;
    private j.a n;

    public a(@NonNull AdTemplate adTemplate, @NonNull DetailVideoView detailVideoView, KsVideoPlayConfig ksVideoPlayConfig) {
        this(adTemplate, detailVideoView, ksVideoPlayConfig, false);
        this.i = detailVideoView.getContext();
    }

    public a(@NonNull final AdTemplate adTemplate, @NonNull DetailVideoView detailVideoView, KsVideoPlayConfig ksVideoPlayConfig, boolean z) {
        this.k = false;
        this.l = false;
        this.m = new ArrayList();
        this.n = new j.a() { // from class: com.kwad.sdk.reward.c.a.1
            @Override // com.kwad.sdk.utils.j.a
            public void a() {
                a.this.l = true;
                if (a.this.e != null) {
                    a.this.e.a(false);
                }
                synchronized (a.this.m) {
                    Iterator it = a.this.m.iterator();
                    while (it.hasNext()) {
                        ((j.a) it.next()).a();
                    }
                }
            }

            @Override // com.kwad.sdk.utils.j.a
            public void b() {
                synchronized (a.this.m) {
                    Iterator it = a.this.m.iterator();
                    while (it.hasNext()) {
                        ((j.a) it.next()).b();
                    }
                }
            }
        };
        this.f = ksVideoPlayConfig;
        this.b = adTemplate;
        this.i = detailVideoView.getContext();
        this.g = adTemplate.mVideoPlayerStatus;
        this.k = z;
        String strA = com.kwad.sdk.core.response.a.a.a(d.j(adTemplate));
        this.c = com.kwad.sdk.core.response.a.a.l(d.j(adTemplate));
        int iC = b.C();
        if (iC < 0) {
            File fileB = com.kwad.sdk.core.diskcache.a.a.a().b(strA);
            if (fileB != null && fileB.exists()) {
                strA = fileB.getAbsolutePath();
            }
            this.d = detailVideoView;
            this.e = new com.kwad.sdk.contentalliance.detail.video.a(detailVideoView);
            i();
            this.h = new e() { // from class: com.kwad.sdk.reward.c.a.2
                @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
                public void a(int i, int i2) throws JSONException {
                    super.a(i, i2);
                    com.kwad.sdk.core.report.d.b(adTemplate, i, i2);
                }
            };
            this.e.a(this.h);
            this.e.a(new c.e() { // from class: com.kwad.sdk.reward.c.a.3
                @Override // com.kwad.sdk.core.video.kwai.c.e
                public void a(c cVar) {
                    a.this.e.e();
                }
            });
            com.kwad.sdk.utils.b.a(this.i).a(this.n);
        }
        if (iC != 0) {
            strA = com.kwad.sdk.core.videocache.b.a.a(detailVideoView.getContext()).a(strA);
        }
        this.a = strA;
        this.d = detailVideoView;
        this.e = new com.kwad.sdk.contentalliance.detail.video.a(detailVideoView);
        i();
        this.h = new e() { // from class: com.kwad.sdk.reward.c.a.2
            @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
            public void a(int i, int i2) throws JSONException {
                super.a(i, i2);
                com.kwad.sdk.core.report.d.b(adTemplate, i, i2);
            }
        };
        this.e.a(this.h);
        this.e.a(new c.e() { // from class: com.kwad.sdk.reward.c.a.3
            @Override // com.kwad.sdk.core.video.kwai.c.e
            public void a(c cVar) {
                a.this.e.e();
            }
        });
        com.kwad.sdk.utils.b.a(this.i).a(this.n);
    }

    private void i() {
        this.e.a(new c.a(this.b).a(this.a).b(f.b(d.k(this.b))).a(this.g).a(com.kwad.sdk.contentalliance.detail.video.b.a(this.b)).a(), this.d);
        if (this.f != null) {
            a(this.f.isVideoSoundEnable(), false);
        }
        this.e.d();
    }

    @MainThread
    public void a(com.kwad.sdk.contentalliance.detail.video.d dVar) {
        if (dVar == null) {
            return;
        }
        this.e.a(dVar);
    }

    @Override // com.kwad.sdk.reward.a.a
    public void a(com.kwad.sdk.core.e.a aVar) {
        this.l = false;
        if (this.e.a() == null) {
            i();
        }
    }

    public void a(j.a aVar) {
        this.m.add(aVar);
    }

    public void a(boolean z) {
        long jK = this.e.k();
        if (z) {
            this.e.a(9);
        } else {
            this.e.a(jK);
        }
        if (z) {
            this.e.h();
        }
    }

    public void a(boolean z, boolean z2) {
        this.j = z;
        if (!z) {
            this.e.a(0.0f, 0.0f);
            return;
        }
        this.e.a(1.0f, 1.0f);
        if (z2) {
            com.kwad.sdk.utils.b.a(this.i).a(true);
        }
    }

    public boolean a() {
        return this.k;
    }

    public void b() {
        if (com.kwad.sdk.reward.j.a()) {
            return;
        }
        this.e.f();
    }

    @MainThread
    public void b(com.kwad.sdk.contentalliance.detail.video.d dVar) {
        if (dVar == null) {
            return;
        }
        this.e.b(dVar);
    }

    @Override // com.kwad.sdk.reward.a.a
    public void b(com.kwad.sdk.core.e.a aVar) {
        if (((aVar instanceof KSRewardVideoActivityProxy) && ((KSRewardVideoActivityProxy) aVar).isRefluxVisible()) ? false : true) {
            b();
        }
        if (this.j || (b.F() && this.l)) {
            com.kwad.sdk.utils.b.a(this.i).a(b.F());
            if (b.F() && this.l) {
                this.l = false;
                this.j = true;
                a(true, false);
            } else {
                if (this.k || !com.kwad.sdk.utils.b.a(this.i).a()) {
                    return;
                }
                this.j = false;
                a(this.j, false);
            }
        }
    }

    public void b(j.a aVar) {
        this.m.remove(aVar);
    }

    public void c() {
        if (com.kwad.sdk.reward.j.a()) {
            return;
        }
        if (this.b.mXiaomiAppStoreDetailViewOpen && this.b.mAdScene != null && this.b.mAdScene.getAdStyle() == 2) {
            return;
        }
        this.e.g();
    }

    @Override // com.kwad.sdk.reward.a.a
    public void c(com.kwad.sdk.core.e.a aVar) {
        c();
    }

    public void d() {
        if (com.kwad.sdk.reward.j.a()) {
            return;
        }
        this.e.g();
    }

    @Override // com.kwad.sdk.reward.a.a
    public void d(com.kwad.sdk.core.e.a aVar) {
        this.l = false;
        if (this.e != null) {
            this.e.b(this.h);
            this.e.h();
        }
    }

    @MainThread
    public void e() {
        if (this.e != null) {
            this.e.n();
            this.e.h();
        }
        com.kwad.sdk.utils.b.a(this.i).b(this.n);
    }

    public int f() {
        return this.e.i();
    }

    public int g() {
        return this.e.j();
    }

    public long h() {
        if (this.e != null) {
            return this.e.l();
        }
        return 0L;
    }
}
