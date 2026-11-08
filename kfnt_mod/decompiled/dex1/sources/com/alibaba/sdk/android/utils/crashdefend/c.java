package com.alibaba.sdk.android.utils.crashdefend;

import android.util.Log;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c implements Cloneable {

    /* renamed from: a, reason: collision with root package name */
    public int f2418a;

    /* renamed from: a, reason: collision with other field name */
    public long f56a;

    /* renamed from: a, reason: collision with other field name */
    public String f58a;

    /* renamed from: b, reason: collision with root package name */
    public int f2419b;

    /* renamed from: b, reason: collision with other field name */
    public long f59b;

    /* renamed from: b, reason: collision with other field name */
    public String f60b;
    public int crashCount;

    /* renamed from: c, reason: collision with root package name */
    public int f2420c = 0;
    public volatile boolean d = false;

    /* renamed from: a, reason: collision with other field name */
    public SDKMessageCallback f57a = null;

    public Object clone() {
        try {
            return (c) super.clone();
        } catch (CloneNotSupportedException e) {
            Log.e("CrashSDK", "clone fail:", e);
            return null;
        }
    }
}
