package com.bytedance.mobsec.matrix;

import com.bytedance.JProtect;
import cp.bd.c.d;

@JProtect
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class MSProxy {
    protected MSProxy() {
    }

    public static native Object a(int i, int i2, long j, String str, Object obj);

    static Object b(int i, int i2, long j, String str, Object obj) {
        return d.b(i, i2, j, str, obj);
    }
}
