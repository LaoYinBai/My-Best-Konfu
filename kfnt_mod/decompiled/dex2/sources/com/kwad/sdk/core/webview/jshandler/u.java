package com.kwad.sdk.core.webview.jshandler;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.utils.ax;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class u implements com.kwad.sdk.core.webview.kwai.a {
    public static int a = 1;
    public static int b = 2;
    private com.kwad.sdk.core.webview.kwai.c c;
    private int d;
    private int e;
    private b g;
    private c f = new c();

    @Nullable
    private Runnable h = null;

    public static class a extends com.kwad.sdk.core.response.kwai.a {
        public int a;
        public int b;
    }

    public interface b {
        void a(int i);
    }

    private class c implements Runnable {
        private boolean b;
        private int c;

        private c() {
            this.b = false;
            this.c = -1;
        }

        public void a(int i) {
            this.c = i;
        }

        public void a(boolean z) {
            this.b = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.kwad.sdk.core.d.a.a("RegisterTimer", "TimerRunnable run timerPaused:  " + this.b + ", currentTime: " + this.c);
            if (this.b) {
                ax.a(this, null, 1000L);
            } else {
                if (this.c < 0) {
                    return;
                }
                u.this.a(this.c);
                this.c--;
                ax.a(this, null, 1000L);
            }
        }
    }

    private u(int i, int i2) {
        this.d = -1;
        this.e = -1;
        this.d = i;
        this.e = i2;
    }

    public static int a(AdInfo adInfo) {
        int i = adInfo.adInsertScreenInfo.autoCloseTime;
        int iA = com.kwad.sdk.core.config.b.a(adInfo);
        if (i > 0) {
            iA = Math.min(iA, i);
        }
        if (iA > 0) {
            return iA;
        }
        return 60;
    }

    @Nullable
    public static u a(Context context, AdTemplate adTemplate) {
        AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(adTemplate);
        boolean zAn = com.kwad.sdk.core.response.a.a.an(adInfoJ);
        boolean z = !com.kwad.sdk.core.response.a.a.P(adInfoJ);
        if (zAn && z) {
            return new u(b, a(adInfoJ));
        }
        if (adInfoJ.adInsertScreenInfo.autoCloseTime > 0) {
            return new u(a, a(adInfoJ));
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        com.kwad.sdk.core.d.a.a("RegisterTimer", "updateTimer: " + i + ", mCallBackFunction: " + this.c);
        if (i >= 0 && this.c != null) {
            if (this.g != null && i == 0) {
                this.g.a(this.d);
            }
            a aVar = new a();
            aVar.b = i;
            aVar.a = this.d;
            this.c.a(aVar);
        }
    }

    @Override // com.kwad.sdk.core.webview.kwai.a
    @NonNull
    public String a() {
        return "registerTimerListener";
    }

    public void a(b bVar) {
        this.g = bVar;
    }

    @Override // com.kwad.sdk.core.webview.kwai.a
    public void a(String str, @NonNull com.kwad.sdk.core.webview.kwai.c cVar) {
        this.c = cVar;
        if (this.h != null) {
            this.h.run();
            this.h = null;
        }
    }

    @Override // com.kwad.sdk.core.webview.kwai.a
    public void b() {
        this.c = null;
    }

    public void c() {
        com.kwad.sdk.core.d.a.a("RegisterTimer", "startTimer: mCallBackFunction: " + this.c);
        if (this.c == null) {
            this.h = new Runnable() { // from class: com.kwad.sdk.core.webview.jshandler.u.1
                @Override // java.lang.Runnable
                public void run() {
                    u.this.c();
                }
            };
        } else {
            this.f.a(this.e);
            ax.a(this.f);
        }
    }

    public void d() {
        this.f.a(true);
    }

    public void e() {
        this.f.a(false);
    }
}
