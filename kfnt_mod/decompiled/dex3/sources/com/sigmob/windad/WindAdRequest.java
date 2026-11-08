package com.sigmob.windad;

import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class WindAdRequest {
    protected int a;
    protected String b;
    private String c;
    private String d;
    private int e;
    private String f;
    private Map<String, Object> g;

    public WindAdRequest() {
        this.c = "";
        this.d = "";
        this.g = new HashMap();
        this.a = 1;
    }

    public WindAdRequest(String str, String str2, int i, Map<String, Object> map) {
        this.c = str;
        this.d = str2;
        this.g = map;
        this.e = i;
    }

    public WindAdRequest(String str, String str2, Map<String, Object> map) {
        this.c = str;
        this.d = str2;
        this.g = map;
        this.a = 1;
    }

    public WindAdRequest(String str, String str2, Map<String, Object> map, int i) {
        this.c = str;
        this.d = str2;
        this.g = map;
        this.a = i;
    }

    public int getAdCount() {
        return this.e;
    }

    public int getAdType() {
        return this.a;
    }

    public String getBidToken() {
        return this.b;
    }

    public String getLoadId() {
        return this.f;
    }

    public Map<String, Object> getOptions() {
        if (this.g == null) {
            this.g = new HashMap();
        }
        return this.g;
    }

    public String getPlacementId() {
        return this.c;
    }

    public String getUserId() {
        return this.d;
    }

    public void setAdCount(int i) {
        this.e = i;
    }

    public void setBidToken(String str) {
        this.b = str;
    }

    public void setLoadId(String str) {
        this.f = str;
    }

    public void setOptions(Map<String, Object> map) {
        this.g = map;
    }

    public void setPlacementId(String str) {
        this.c = str;
    }

    public void setUserId(String str) {
        this.d = str;
    }
}
