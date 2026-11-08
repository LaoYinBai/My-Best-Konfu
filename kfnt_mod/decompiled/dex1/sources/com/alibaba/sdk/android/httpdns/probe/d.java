package com.alibaba.sdk.android.httpdns.probe;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class d {

    /* renamed from: a, reason: collision with root package name */
    private static IPProbeService f2393a;

    public static synchronized IPProbeService a(b bVar) {
        if (f2393a == null) {
            f2393a = new e();
            f2393a.setIPListUpdateCallback(bVar);
        }
        return f2393a;
    }
}
