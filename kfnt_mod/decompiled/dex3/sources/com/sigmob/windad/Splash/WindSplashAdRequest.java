package com.sigmob.windad.Splash;

import com.sigmob.windad.WindAdRequest;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class WindSplashAdRequest extends WindAdRequest {
    private int c;
    private String d;
    private String e;
    private boolean f;

    public WindSplashAdRequest(String str, String str2, Map<String, Object> map) {
        super(str, str2, map, 2);
        this.c = 5;
        this.f = false;
    }

    public WindSplashAdRequest(String str, String str2, Map<String, Object> map, int i, String str3, String str4, boolean z) {
        super(str, str2, map, 2);
        this.c = 5;
        this.f = false;
        this.c = i;
        this.d = str3;
        this.e = str4;
        this.f = z;
    }

    public String getAppDesc() {
        return this.e;
    }

    public String getAppTitle() {
        return this.d;
    }

    public int getFetchDelay() {
        return this.c;
    }

    public boolean isDisableAutoHideAd() {
        return this.f;
    }

    public void setAppDesc(String str) {
        this.e = str;
    }

    public void setAppTitle(String str) {
        this.d = str;
    }

    public void setDisableAutoHideAd(boolean z) {
        this.f = z;
    }

    public void setFetchDelay(int i) {
        this.c = i;
    }
}
