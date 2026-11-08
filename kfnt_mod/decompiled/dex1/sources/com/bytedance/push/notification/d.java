package com.bytedance.push.notification;

import android.os.SystemClock;
import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class d {

    /* renamed from: a, reason: collision with root package name */
    private long f4886a;

    /* renamed from: b, reason: collision with root package name */
    private String f4887b;

    /* renamed from: c, reason: collision with root package name */
    private int f4888c;

    d() {
    }

    boolean a(String str, int i) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (this.f4888c == i && jElapsedRealtime - this.f4886a <= 1000 && TextUtils.equals(this.f4887b, str)) {
            return true;
        }
        this.f4887b = str;
        this.f4888c = i;
        this.f4886a = jElapsedRealtime;
        return false;
    }
}
