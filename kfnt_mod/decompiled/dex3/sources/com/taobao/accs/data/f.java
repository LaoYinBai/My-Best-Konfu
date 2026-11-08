package com.taobao.accs.data;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class f implements Runnable {
    final /* synthetic */ d a;

    f(d dVar) {
        this.a = dVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.a.c != null) {
            this.a.c.a();
        }
    }
}
