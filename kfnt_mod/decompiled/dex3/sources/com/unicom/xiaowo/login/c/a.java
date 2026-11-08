package com.unicom.xiaowo.login.c;

import android.net.Network;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class a {
    private ExecutorService a = Executors.newFixedThreadPool(2);
    private k b = new k();

    public final void a(String str, String str2, HashMap map, d dVar) {
        this.a.submit(new b(this, str, str2, map, dVar));
    }

    public final void a(String str, HashMap map, Network network, d dVar) {
        this.a.submit(new c(this, str, null, network, dVar));
    }
}
