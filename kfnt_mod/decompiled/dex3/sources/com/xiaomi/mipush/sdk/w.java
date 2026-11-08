package com.xiaomi.mipush.sdk;

import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class w {
    int a = 0;

    /* renamed from: a, reason: collision with other field name */
    String f98a = "";

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof w)) {
            return false;
        }
        w wVar = (w) obj;
        return !TextUtils.isEmpty(wVar.f98a) && wVar.f98a.equals(this.f98a);
    }
}
