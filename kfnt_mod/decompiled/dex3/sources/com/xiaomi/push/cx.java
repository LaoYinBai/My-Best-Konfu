package com.xiaomi.push;

import android.content.Context;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class cx {
    private int a;

    public cx(int i) {
        this.a = i;
    }

    public int a() {
        return this.a;
    }

    public abstract String a(Context context, String str, List<bf> list);

    /* renamed from: a, reason: collision with other method in class */
    public boolean m192a(Context context, String str, List<bf> list) {
        return true;
    }
}
