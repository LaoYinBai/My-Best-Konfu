package com.bytedance.sdk.adtnc.a;

import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    public boolean f5075a = false;

    /* renamed from: b, reason: collision with root package name */
    public boolean f5076b = true;

    /* renamed from: c, reason: collision with root package name */
    public Map<String, Integer> f5077c = null;
    public Map<String, String> d = null;
    public int e = 10;
    public int f = 1;
    public int g = 1;
    public int h = 10;
    public int i = 1;
    public int j = 1;
    public int k = 900;
    public int l = 120;
    public String m = null;
    public int n = 0;
    public long o = 0;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" localEnable: ");
        sb.append(this.f5075a);
        sb.append(" probeEnable: ");
        sb.append(this.f5076b);
        sb.append(" hostFilter: ");
        sb.append(this.f5077c != null ? this.f5077c.size() : 0);
        sb.append(" hostMap: ");
        sb.append(this.d != null ? this.d.size() : 0);
        sb.append(" reqTo: ");
        sb.append(this.e);
        sb.append("#");
        sb.append(this.f);
        sb.append("#");
        sb.append(this.g);
        sb.append(" reqErr: ");
        sb.append(this.h);
        sb.append("#");
        sb.append(this.i);
        sb.append("#");
        sb.append(this.j);
        sb.append(" updateInterval: ");
        sb.append(this.k);
        sb.append(" updateRandom: ");
        sb.append(this.l);
        sb.append(" httpBlack: ");
        sb.append(this.m);
        return sb.toString();
    }
}
