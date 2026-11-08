package com.unicom.xiaowo.login.c;

import android.net.Network;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class c implements Runnable {
    private /* synthetic */ String a;
    private /* synthetic */ HashMap b;
    private /* synthetic */ Network c;
    private /* synthetic */ d d;
    private /* synthetic */ a e;

    c(a aVar, String str, HashMap map, Network network, d dVar) {
        this.e = aVar;
        this.a = str;
        this.b = map;
        this.c = network;
        this.d = dVar;
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        try {
            this.d.a(this.e.b.a(this.a, this.b, this.c));
        } catch (Exception e) {
            com.unicom.xiaowo.login.d.f.b(e.getMessage());
        }
    }
}
