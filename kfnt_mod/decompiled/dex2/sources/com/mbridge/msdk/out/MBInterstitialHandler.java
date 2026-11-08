package com.mbridge.msdk.out;

import android.content.Context;
import android.text.TextUtils;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.interstitial.c.a;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBInterstitialHandler {
    private a mController;

    public MBInterstitialHandler(Context context, Map<String, Object> map) {
        if (this.mController == null) {
            this.mController = new a();
        }
        if (map != null && map.containsKey(MBridgeConstans.PROPERTIES_UNIT_ID)) {
            String str = (String) map.get(MBridgeConstans.PROPERTIES_UNIT_ID);
            if (!TextUtils.isEmpty(str)) {
                String strD = w.d(str);
                if (!TextUtils.isEmpty(strD)) {
                    w.a(str, strD);
                }
            }
        }
        this.mController.a(context, map);
        if (com.mbridge.msdk.foundation.controller.a.f().j() != null || context == null) {
            return;
        }
        com.mbridge.msdk.foundation.controller.a.f().b(context);
    }

    public void preload() {
        try {
            if (this.mController != null) {
                this.mController.a();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getRequestId() {
        return this.mController != null ? this.mController.b() : "";
    }

    public void show() {
        try {
            this.mController.c();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setInterstitialListener(InterstitialListener interstitialListener) {
        try {
            if (this.mController != null) {
                this.mController.a(interstitialListener);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
