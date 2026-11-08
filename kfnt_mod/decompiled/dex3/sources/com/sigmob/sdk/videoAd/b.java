package com.sigmob.sdk.videoAd;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import com.sigmob.sdk.common.Constants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class b extends com.sigmob.sdk.base.common.g {
    protected b(Activity activity, String str, com.sigmob.sdk.base.common.h hVar) {
        super(activity, str, hVar);
    }

    @Override // com.sigmob.sdk.base.common.g
    public void b() {
        this.f.onSetContentView(this.e);
    }

    protected void b(Bundle bundle) {
        try {
            boolean z = bundle.getBoolean(Constants.ENABLEKEEPON, false);
            boolean z2 = bundle.getBoolean(Constants.ENABLESCREENLOCKDISPLAYAD, false);
            if (Build.VERSION.SDK_INT < 27) {
                if (z2) {
                    k().getWindow().addFlags(2621440);
                }
                if (z) {
                    k().getWindow().addFlags(128);
                    return;
                }
                return;
            }
            if (z2) {
                k().setShowWhenLocked(true);
                k().setTurnScreenOn(true);
            }
            if (z) {
                k().setShowWhenLocked(true);
            }
        } catch (Throwable unused) {
        }
    }
}
