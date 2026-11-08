package com.taobao.accs.d;

import com.taobao.accs.utl.ALog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private static a a;
    private ClassLoader b = null;
    private boolean c = false;

    public static synchronized a a() {
        if (a == null) {
            a = new a();
        }
        return a;
    }

    public synchronized ClassLoader b() {
        if (this.b == null) {
            ALog.d("ACCSClassLoader", "getClassLoader", new Object[0]);
            this.b = a.class.getClassLoader();
        }
        return this.b;
    }
}
