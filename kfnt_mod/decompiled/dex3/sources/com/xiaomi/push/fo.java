package com.xiaomi.push;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class fo extends Thread {
    final /* synthetic */ fn a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    fo(fn fnVar, String str) {
        super(str);
        this.a = fnVar;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        try {
            this.a.a.m321a();
        } catch (Exception e) {
            this.a.c(9, e);
        }
    }
}
