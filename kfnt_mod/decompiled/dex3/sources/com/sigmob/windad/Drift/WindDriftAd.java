package com.sigmob.windad.Drift;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.sigmob.sdk.a.a;
import com.sigmob.sdk.a.b;
import com.sigmob.windad.WindAdAdapterError;
import com.sigmob.windad.WindAdError;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class WindDriftAd {
    private final WindDriftAdListener a;
    private final b b;
    private final String c;
    private final Handler d = new Handler(Looper.getMainLooper());

    private final class DriftAdListener implements a {
        private DriftAdListener() {
        }

        @Override // com.sigmob.sdk.a.a
        public void onDriftAdClose(final String str) {
            WindDriftAd.this.d.post(new Runnable() { // from class: com.sigmob.windad.Drift.WindDriftAd.DriftAdListener.4
                @Override // java.lang.Runnable
                public void run() {
                    if (WindDriftAd.this.a != null) {
                        WindDriftAd.this.a.onDriftAdClosed(str);
                    }
                }
            });
        }

        @Override // com.sigmob.sdk.a.a
        public void onDriftAdError(final int i, final String str, final String str2) {
            WindDriftAd.this.b.d();
            WindDriftAd.this.d.post(new Runnable() { // from class: com.sigmob.windad.Drift.WindDriftAd.DriftAdListener.2
                @Override // java.lang.Runnable
                public void run() {
                    if (WindDriftAd.this.a != null) {
                        WindDriftAd.this.a.onDriftAdError(WindDriftAd.this.a(i, str), str2);
                    }
                }
            });
        }

        @Override // com.sigmob.sdk.a.a
        public void onDriftAdPresent(final String str) {
            WindDriftAd.this.d.post(new Runnable() { // from class: com.sigmob.windad.Drift.WindDriftAd.DriftAdListener.3
                @Override // java.lang.Runnable
                public void run() {
                    if (WindDriftAd.this.a != null) {
                        WindDriftAd.this.a.onDriftAdExposure(str);
                    }
                }
            });
        }

        @Override // com.sigmob.sdk.a.a
        public void onDriftAdReceive(final String str) {
            WindDriftAd.this.d.post(new Runnable() { // from class: com.sigmob.windad.Drift.WindDriftAd.DriftAdListener.1
                @Override // java.lang.Runnable
                public void run() {
                    if (WindDriftAd.this.a != null) {
                        WindDriftAd.this.a.onDriftAdLoadSuccess(str);
                    }
                }
            });
        }
    }

    public WindDriftAd(Context context, String str, WindDriftAdListener windDriftAdListener) {
        this.c = str;
        this.b = new b(context, str, new DriftAdListener());
        this.a = windDriftAdListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WindAdAdapterError a(int i, String str) {
        return new WindAdAdapterError(i, str);
    }

    public void action() {
        if (this.b != null) {
            this.b.a();
        } else if (this.a != null) {
            this.a.onDriftAdError(new WindAdAdapterError(WindAdError.ERROR_SIGMOB_REQUEST.getErrorCode(), "ad is null"), this.c);
        }
    }

    public File getIconFile() {
        if (this.b != null) {
            return this.b.b();
        }
        return null;
    }

    public void loadAd() {
        if (this.b != null) {
            this.b.c();
        } else if (this.a != null) {
            this.a.onDriftAdError(new WindAdAdapterError(WindAdError.ERROR_SIGMOB_REQUEST.getErrorCode(), "ad is null"), this.c);
        }
    }
}
