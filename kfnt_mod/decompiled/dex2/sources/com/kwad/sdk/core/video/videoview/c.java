package com.kwad.sdk.core.video.videoview;

import android.content.Context;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class c extends FrameLayout {
    private static final AtomicInteger a = new AtomicInteger(0);
    private Timer b;
    private TimerTask c;

    @NonNull
    protected final d d;

    public c(Context context, @NonNull d dVar) {
        super(context);
        this.d = dVar;
    }

    protected abstract void a(int i);

    public void a(int i, int i2) {
    }

    protected abstract void e();

    protected abstract void i();

    protected void k() {
        l();
        if (this.b == null) {
            this.b = new Timer("ksad-IVideoPlayer-timer" + a.getAndIncrement());
        }
        if (this.c == null) {
            this.c = new TimerTask() { // from class: com.kwad.sdk.core.video.videoview.c.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    c.this.post(new Runnable() { // from class: com.kwad.sdk.core.video.videoview.c.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            c.this.i();
                        }
                    });
                }
            };
        }
        this.b.schedule(this.c, 0L, 1000L);
    }

    protected void l() {
        if (this.b != null) {
            this.b.cancel();
            this.b = null;
        }
        if (this.c != null) {
            this.c.cancel();
            this.c = null;
        }
    }
}
