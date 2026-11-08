package org.android.agoo.control;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
class e implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ AgooFactory c;

    e(AgooFactory agooFactory, String str, String str2) {
        this.c = agooFactory;
        this.a = str;
        this.b = str2;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        this.c.updateMsgStatus(this.a, this.b);
    }
}
