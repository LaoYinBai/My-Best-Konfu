package com.ss.union.game.sdk.vcenter.c.a;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import com.ss.union.game.sdk.vcenter.b.a.e;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class d extends com.ss.union.game.sdk.vcenter.b.a.a {
    private static d a = new d();
    private List<a> b = new ArrayList();

    public interface a {
        void b();
    }

    @Override // com.ss.union.game.sdk.vcenter.b.a.a
    public int a() {
        return 1003;
    }

    @Override // com.ss.union.game.sdk.vcenter.b.a.a
    public void a(e.a aVar, Intent intent, com.ss.union.game.sdk.vcenter.b.a.c cVar) {
    }

    public static d b() {
        return a;
    }

    @Override // com.ss.union.game.sdk.vcenter.b.a.a
    public e.b a(e.a aVar, Intent intent) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.ss.union.game.sdk.vcenter.c.a.d.1
            @Override // java.lang.Runnable
            public void run() {
                for (a aVar2 : (a[]) d.this.b.toArray(new a[0])) {
                    try {
                        aVar2.b();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return e.b.a(aVar);
    }

    public void a(a aVar) {
        this.b.add(aVar);
    }

    public void b(a aVar) {
        this.b.remove(aVar);
    }
}
