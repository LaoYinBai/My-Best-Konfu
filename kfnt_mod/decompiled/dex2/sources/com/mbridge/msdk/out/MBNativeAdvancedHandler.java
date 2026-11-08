package com.mbridge.msdk.out;

import android.app.Activity;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.mbridge.msdk.advanced.c.c;
import com.mbridge.msdk.foundation.tools.w;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBNativeAdvancedHandler {
    private c nativeAdvancedProvider;

    public MBNativeAdvancedHandler(Activity activity, String str, String str2) {
        String strD = w.d(str2);
        if (!TextUtils.isEmpty(strD)) {
            w.a(str2, strD);
        }
        this.nativeAdvancedProvider = new c(str, str2, activity);
    }

    public void setCloseButtonState(MBMultiStateEnum mBMultiStateEnum) throws JSONException {
        int i = -1;
        switch (mBMultiStateEnum) {
            case negative:
                i = 0;
                break;
            case positive:
                i = 1;
                break;
        }
        this.nativeAdvancedProvider.a(i);
    }

    public void setNativeViewSize(int i, int i2) {
        this.nativeAdvancedProvider.a(i2, i);
    }

    public void setViewElementStyle(JSONObject jSONObject) throws JSONException {
        if (this.nativeAdvancedProvider != null) {
            this.nativeAdvancedProvider.a(jSONObject);
        }
    }

    public void setAdListener(NativeAdvancedAdListener nativeAdvancedAdListener) {
        if (this.nativeAdvancedProvider != null) {
            this.nativeAdvancedProvider.a(nativeAdvancedAdListener);
        }
    }

    public void load() throws Throwable {
        if (this.nativeAdvancedProvider != null) {
            this.nativeAdvancedProvider.b("");
        }
    }

    public void loadByToken(String str) throws Throwable {
        if (this.nativeAdvancedProvider != null) {
            this.nativeAdvancedProvider.a(str);
        }
    }

    public boolean isReady() {
        return isReady("");
    }

    public boolean isReady(String str) {
        if (this.nativeAdvancedProvider != null) {
            return this.nativeAdvancedProvider.c(str);
        }
        return false;
    }

    public String getRequestId() {
        return this.nativeAdvancedProvider != null ? this.nativeAdvancedProvider.d() : "";
    }

    public void autoLoopPlay(int i) throws JSONException {
        if (this.nativeAdvancedProvider != null) {
            this.nativeAdvancedProvider.c(i);
        }
    }

    public void setPlayMuteState(int i) throws JSONException {
        if (this.nativeAdvancedProvider != null) {
            this.nativeAdvancedProvider.b(i);
        }
    }

    public void onResume() {
        if (this.nativeAdvancedProvider != null) {
            this.nativeAdvancedProvider.d(3);
        }
    }

    public void onPause() {
        if (this.nativeAdvancedProvider != null) {
            this.nativeAdvancedProvider.e(3);
        }
    }

    public void release() throws Throwable {
        if (this.nativeAdvancedProvider != null) {
            this.nativeAdvancedProvider.e();
        }
    }

    public ViewGroup getAdViewGroup() {
        if (this.nativeAdvancedProvider != null) {
            return this.nativeAdvancedProvider.b();
        }
        return null;
    }
}
