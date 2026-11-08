package com.baidu.mobads.sdk.internal;

import com.baidu.mobads.sdk.internal.u;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class k {

    /* renamed from: a, reason: collision with root package name */
    private static volatile k f3239a;

    /* renamed from: b, reason: collision with root package name */
    private List<u.a> f3240b = new CopyOnWriteArrayList();

    private k() {
    }

    public static k a() {
        if (f3239a == null) {
            synchronized (k.class) {
                if (f3239a == null) {
                    f3239a = new k();
                }
            }
        }
        return f3239a;
    }

    public void a(u.a aVar) {
        if (aVar == null || this.f3240b.contains(aVar)) {
            return;
        }
        this.f3240b.add(aVar);
    }

    public void a(int i) {
        for (u.a aVar : this.f3240b) {
            if (i == 1) {
                aVar.onSuccess();
            } else if (i == 2) {
                aVar.onFailure();
            }
            b(aVar);
        }
    }

    private void b(u.a aVar) {
        if (this.f3240b.contains(aVar)) {
            this.f3240b.remove(aVar);
        }
    }
}
