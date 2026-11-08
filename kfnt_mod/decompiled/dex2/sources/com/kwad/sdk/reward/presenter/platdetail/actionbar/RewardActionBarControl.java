package com.kwad.sdk.reward.presenter.platdetail.actionbar;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class RewardActionBarControl {
    private b a;

    @Nullable
    private d b;

    @Nullable
    private a c;

    @Nullable
    private c d;
    private AdTemplate e;
    private AdInfo f;
    private final long h;
    private Context j;
    private Handler g = new Handler(Looper.getMainLooper());
    private boolean i = false;

    enum ShowActionBarResult {
        SHOW_NEW_STYLE,
        SHOW_NATIVE,
        SHOW_H5_SUCCESS,
        SHOW_H5_FAILURE,
        SHOW_FOLLOW,
        SHOW_ORDER
    }

    public interface a {
        void d();
    }

    public interface b {
        void a(boolean z);
    }

    public interface c {
        void d();
    }

    public interface d {
        boolean a();
    }

    public RewardActionBarControl(Context context, AdTemplate adTemplate) {
        this.j = context;
        this.e = adTemplate;
        this.f = com.kwad.sdk.core.response.a.d.j(adTemplate);
        long jC = com.kwad.sdk.core.response.a.b.c(adTemplate);
        this.h = jC <= 0 ? 1000L : jC;
    }

    private ShowActionBarResult a(boolean z) {
        if (com.kwad.sdk.core.response.a.a.aH(this.f) && this.c != null) {
            this.c.d();
            return ShowActionBarResult.SHOW_FOLLOW;
        }
        if (com.kwad.sdk.core.response.a.a.aI(this.f) && this.d != null) {
            this.d.d();
            return ShowActionBarResult.SHOW_ORDER;
        }
        if (com.kwad.sdk.core.response.a.a.T(this.f)) {
            return ShowActionBarResult.SHOW_NEW_STYLE;
        }
        if (!com.kwad.sdk.core.response.a.b.d(this.e) || this.b == null) {
            b(z);
            return ShowActionBarResult.SHOW_NATIVE;
        }
        com.kwad.sdk.core.d.a.a("ActionBarControl", "showWebActionBar success in " + this.h);
        return this.b.a() ? ShowActionBarResult.SHOW_H5_SUCCESS : ShowActionBarResult.SHOW_H5_FAILURE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        if (this.a != null) {
            com.kwad.sdk.core.d.a.a("ActionBarControl", "showNativeActionBar");
            this.a.a(z);
        }
    }

    public void a(int i, int i2) {
        ShowActionBarResult showActionBarResultA = a(false);
        com.kwad.sdk.core.d.a.a("ActionBarControl", "showActionBarOnVideoStart result: " + showActionBarResultA);
        if (showActionBarResultA != ShowActionBarResult.SHOW_H5_FAILURE) {
            return;
        }
        this.g.postDelayed(new Runnable() { // from class: com.kwad.sdk.reward.presenter.platdetail.actionbar.RewardActionBarControl.1
            @Override // java.lang.Runnable
            public void run() {
                RewardActionBarControl.this.i = true;
                com.kwad.sdk.core.d.a.a("ActionBarControl", "mHasOutTime");
                if (RewardActionBarControl.this.b != null && RewardActionBarControl.this.b.a()) {
                    com.kwad.sdk.core.d.a.a("ActionBarControl", "showWebActionBar success on " + RewardActionBarControl.this.h);
                    return;
                }
                com.kwad.sdk.core.d.a.a("ActionBarControl", "showWebActionBar out " + RewardActionBarControl.this.h);
                com.kwad.sdk.core.report.d.c(RewardActionBarControl.this.e, RewardActionBarControl.this.h);
                RewardActionBarControl.this.b(true);
            }
        }, this.h);
    }

    @MainThread
    public void a(@Nullable a aVar) {
        this.c = aVar;
    }

    @MainThread
    public void a(b bVar) {
        this.a = bVar;
    }

    @MainThread
    public void a(@Nullable c cVar) {
        this.d = cVar;
    }

    @MainThread
    public void a(d dVar) {
        this.b = dVar;
    }

    public void b(int i, int i2) {
        if (this.i) {
            com.kwad.sdk.core.d.a.c("ActionBarControl", "showWebActionBar time out on pageStatus");
        } else {
            this.g.removeCallbacksAndMessages(null);
            a(true);
        }
    }
}
