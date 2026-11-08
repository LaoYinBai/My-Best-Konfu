package com.mbridge.msdk.interstitialvideo.out;

import android.content.Context;
import android.text.TextUtils;
import com.mbridge.msdk.b.b;
import com.mbridge.msdk.foundation.tools.q;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.reward.a.a;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBInterstitialVideoHandler {
    private a a;

    public MBInterstitialVideoHandler(Context context, String str, String str2) {
        if (com.mbridge.msdk.foundation.controller.a.f().j() == null && context != null) {
            com.mbridge.msdk.foundation.controller.a.f().b(context);
        }
        String strD = w.d(str2);
        if (!TextUtils.isEmpty(strD)) {
            w.a(str2, strD);
        }
        a(str, str2);
    }

    public MBInterstitialVideoHandler(String str, String str2) {
        a(str, str2);
    }

    private void a(String str, String str2) {
        try {
            if (this.a == null) {
                this.a = new a();
                this.a.a(true);
            }
            this.a.b(str, str2);
            b.a().e(str2);
        } catch (Throwable th) {
            s.b("MBRewardVideoHandler", th.getMessage(), th);
        }
    }

    public void load() {
        if (this.a != null) {
            this.a.d(true);
        }
    }

    public void loadFormSelfFilling() {
        if (this.a != null) {
            this.a.d(false);
        }
    }

    public boolean isReady() {
        if (this.a != null) {
            return this.a.e(true);
        }
        return false;
    }

    public String getRequestId() {
        return this.a != null ? this.a.b() : "";
    }

    public void show() throws Throwable {
        if (this.a != null) {
            this.a.a((String) null, (String) null, (String) null);
        }
    }

    public void setRewardVideoListener(InterstitialVideoListener interstitialVideoListener) {
        if (this.a != null) {
            this.a.a(new com.mbridge.msdk.interstitialvideo.a.a(interstitialVideoListener));
        }
    }

    public void setInterstitialVideoListener(InterstitialVideoListener interstitialVideoListener) {
        if (this.a != null) {
            this.a.a(new com.mbridge.msdk.interstitialvideo.a.a(interstitialVideoListener));
        }
    }

    public void clearVideoCache() {
        try {
            if (this.a != null) {
                a aVar = this.a;
                q.a();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playVideoMute(int i) {
        if (this.a != null) {
            this.a.a(i);
        }
    }

    public void setIVRewardEnable(int i, int i2) throws JSONException {
        if (this.a != null) {
            this.a.a(i, com.mbridge.msdk.foundation.same.a.y, i2);
        }
    }

    public void setIVRewardEnable(int i, double d) throws JSONException {
        if (this.a != null) {
            this.a.a(i, com.mbridge.msdk.foundation.same.a.x, (int) (d * 100.0d));
        }
    }

    public void setAlertDialogText(String str, String str2, String str3, String str4) {
        if (this.a != null) {
            this.a.a(str, str2, str3, str4);
        }
    }
}
