package com.ss.union.game.sdk.core.antiAddiction.abs;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import com.ss.union.game.sdk.common.util.ActivityUtils;
import com.ss.union.game.sdk.common.util.ProcessUtils;
import com.ss.union.game.sdk.core.antiAddiction.a.a;
import com.ss.union.game.sdk.core.util.LogDevKitUtils;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class AbsAntiAddictionStrategy {
    private static final String a = "AntiAddiction";
    private int e;
    private final int b = 59000;
    private Runnable f = new Runnable() { // from class: com.ss.union.game.sdk.core.antiAddiction.abs.AbsAntiAddictionStrategy.2
        @Override // java.lang.Runnable
        public void run() throws JSONException {
            if (AbsAntiAddictionStrategy.this.c && AbsAntiAddictionStrategy.this.e <= 0) {
                AbsAntiAddictionStrategy.this.antiAddictionFinished();
                a.c();
                AbsAntiAddictionStrategy.this.stopAllAntiAddictionCountdown();
            } else {
                AbsAntiAddictionStrategy.this.e -= 59;
                AbsAntiAddictionStrategy.this.d.postDelayed(this, 59000L);
            }
        }
    };
    private Handler d = new Handler(Looper.getMainLooper());
    private boolean c = ProcessUtils.appIsForeground();

    protected abstract void antiAddictionFinished();

    public abstract void antiAddictionStart();

    public AbsAntiAddictionStrategy() {
        ActivityUtils.registerForegroundListener(new ActivityUtils.ForegroundListener() { // from class: com.ss.union.game.sdk.core.antiAddiction.abs.AbsAntiAddictionStrategy.1
            @Override // com.ss.union.game.sdk.common.util.ActivityUtils.ForegroundListener
            public void onForeground(Activity activity) {
                AbsAntiAddictionStrategy.this.c = true;
            }

            @Override // com.ss.union.game.sdk.common.util.ActivityUtils.ForegroundListener
            public void onBackground() {
                AbsAntiAddictionStrategy.this.c = false;
            }
        });
    }

    public void stopAllAntiAddictionCountdown() {
        this.d.removeCallbacksAndMessages(null);
        LogDevKitUtils.logAntiAddiction("stopAllAntiAddictionCountdown " + getClass().getSimpleName());
    }

    protected void startAntiAddiction(int i) {
        LogDevKitUtils.logAntiAddiction("startAntiAddiction time = " + i);
        this.e = i;
        a();
        this.d.post(this.f);
    }

    private void a() {
        this.d.removeCallbacks(this.f);
    }

    private void a(Runnable runnable, long j) {
        if (runnable == null) {
            return;
        }
        if (j <= 0) {
            this.d.post(runnable);
        } else {
            this.d.postDelayed(runnable, j);
        }
    }
}
