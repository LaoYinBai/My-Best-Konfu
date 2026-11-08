package com.sigmob.sdk.base.models;

import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LoadAdRequest {
    public static final String SPLASH_APP_DESC = "APP_DESC";
    public static final String SPLASH_APP_TITLE = "APP_TITLE";
    public static final String SPLASH_DISABLE_AD_HIDE = "SPLASH_DISABLE_AD_HIDE";
    public static final String SPLASH_FETCH_TIMEOUT = "FETCH_TIMEOUT";
    private final String a;
    private final String b;
    private final int c;
    private String d;
    private Map<String, Object> e;
    private String f;
    private String g;
    private int h;
    private boolean i;
    private boolean j;
    private String k;
    private boolean l;
    private int m = 1;
    private String n;
    private boolean o;
    private String p;

    public LoadAdRequest(String str, String str2, int i, String str3, Map<String, Object> map) {
        this.a = str;
        this.d = str3;
        this.e = map;
        this.b = str2;
        this.c = i;
    }

    public LoadAdRequest(boolean z, String str, String str2, String str3, int i, String str4, Map<String, Object> map) {
        this.o = z;
        this.p = str;
        this.a = str2;
        this.d = str4;
        this.e = map;
        this.b = str3;
        this.c = i;
    }

    public int getAdCount() {
        return this.m;
    }

    public String getAdScene() {
        return this.k;
    }

    public int getAdType() {
        return this.c;
    }

    public String getBidToken() {
        return this.p;
    }

    public String getLastCampid() {
        return this.g;
    }

    public String getLastCrid() {
        return this.f;
    }

    public String getLoadId() {
        return this.d;
    }

    public Map<String, Object> getOptions() {
        if (this.e == null) {
            this.e = new HashMap();
        }
        return this.e;
    }

    public String getPlacementId() {
        return this.b;
    }

    public String getRequestId() {
        return this.n;
    }

    public int getRequest_scene_type() {
        return this.h;
    }

    public String getUserId() {
        return this.a;
    }

    public boolean isEnable_keep_on() {
        return this.l;
    }

    public boolean isEnable_screen_lock_displayad() {
        return this.j;
    }

    public boolean isExpired() {
        return this.i;
    }

    public boolean isUseMediation() {
        return this.o;
    }

    public void setAdCount(int i) {
        this.m = i;
    }

    public void setAdScene(String str) {
        this.k = str;
    }

    public void setBidToken(String str) {
        this.p = str;
    }

    public void setEnable_keep_on(boolean z) {
        this.l = z;
    }

    public void setEnable_screen_lock_displayad(boolean z) {
        this.j = z;
    }

    public void setExpired(boolean z) {
        this.i = z;
    }

    public void setLastCampid(String str) {
        this.g = str;
    }

    public void setLastCrid(String str) {
        this.f = str;
    }

    public void setLoadId(String str) {
        this.d = str;
    }

    public void setRequestId(String str) {
        this.n = str;
    }

    public void setRequest_scene_type(int i) {
        this.h = i;
    }
}
