package com.mbridge.msdk.out;

import android.content.Context;
import android.text.TextUtils;
import com.mbridge.msdk.foundation.tools.q;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.reward.a.a;
import com.mbridge.msdk.video.bt.module.b.g;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBRewardVideoHandler {
    private static final String TAG = "MBRewardVideoHandler";
    private a controller;

    public MBRewardVideoHandler(Context context, String str, String str2) {
        if (com.mbridge.msdk.foundation.controller.a.f().j() == null && context != null) {
            com.mbridge.msdk.foundation.controller.a.f().b(context);
        }
        String strD = w.d(str2);
        if (!TextUtils.isEmpty(strD)) {
            w.a(str2, strD);
        }
        initMBRewardVideoHandler(str, str2);
    }

    public MBRewardVideoHandler(String str, String str2) {
        String strD = w.d(str2);
        if (!TextUtils.isEmpty(strD)) {
            w.a(str2, strD);
        }
        initMBRewardVideoHandler(str, str2);
    }

    private void initMBRewardVideoHandler(String str, String str2) {
        try {
            if (this.controller == null) {
                this.controller = new a();
                this.controller.a(false);
            }
            this.controller.b(str, str2);
        } catch (Throwable th) {
            s.b(TAG, th.getMessage(), th);
        }
    }

    public void load() {
        if (this.controller != null) {
            this.controller.d(true);
        }
    }

    public void loadFormSelfFilling() {
        if (this.controller != null) {
            this.controller.d(false);
        }
    }

    public boolean isReady() {
        if (this.controller != null) {
            return this.controller.e(false);
        }
        return false;
    }

    public String getRequestId() {
        return this.controller != null ? this.controller.b() : "";
    }

    public void show() throws Throwable {
        if (this.controller != null) {
            this.controller.a((String) null, (String) null, (String) null);
        }
    }

    public void show(String str) throws Throwable {
        if (this.controller != null) {
            this.controller.a((String) null, str, (String) null);
        }
    }

    public void show(String str, String str2) throws Throwable {
        if (this.controller != null) {
            this.controller.a((String) null, str, str2);
        }
    }

    public void setRewardVideoListener(g gVar) {
        if (this.controller != null) {
            this.controller.a(new com.mbridge.msdk.video.bt.module.b.a(gVar));
        }
    }

    public void clearVideoCache() {
        try {
            if (this.controller != null) {
                a aVar = this.controller;
                q.a();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playVideoMute(int i) {
        if (this.controller != null) {
            this.controller.a(i);
        }
    }

    public void setAlertDialogText(String str, String str2, String str3, String str4) {
        if (this.controller != null) {
            this.controller.a(str, str2, str3, str4);
        }
    }

    public void setRewardPlus(boolean z) {
        if (this.controller != null) {
            this.controller.c(z);
        }
    }
}
