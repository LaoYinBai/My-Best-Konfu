package com.bytedance.push.alive;

import android.content.Context;
import android.os.Looper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static volatile a f4729a;

    /* renamed from: b, reason: collision with root package name */
    private final Context f4730b;

    /* renamed from: c, reason: collision with root package name */
    private AtomicBoolean f4731c = new AtomicBoolean(false);
    private List<d> d = new ArrayList();

    public static a a(Context context) {
        if (f4729a == null) {
            synchronized (b.class) {
                if (f4729a == null) {
                    f4729a = new a(context);
                }
            }
        }
        return f4729a;
    }

    private a(Context context) {
        this.f4730b = context.getApplicationContext();
        this.d.add(new e(com.ss.android.message.d.a().c()));
        this.d.add(new g());
        this.d.add(new i());
        this.d.add(new h());
    }

    public void a() {
        Runnable runnable = new Runnable() { // from class: com.bytedance.push.alive.a.1
            @Override // java.lang.Runnable
            public void run() {
                if (a.this.f4731c.compareAndSet(false, true)) {
                    com.bytedance.push.utils.e.a("PushAlive", "start keep alive");
                    ArrayList arrayList = new ArrayList();
                    for (d dVar : a.this.d) {
                        if (dVar.b(a.this.f4730b)) {
                            arrayList.add(dVar);
                        }
                    }
                    com.bytedance.push.utils.e.a("PushAlive", "alive ways: " + arrayList);
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        ((d) it.next()).a(a.this.f4730b);
                    }
                }
            }
        };
        if (Looper.getMainLooper() == Looper.myLooper()) {
            com.bytedance.common.push.utility.b.b.a(runnable);
        } else {
            runnable.run();
        }
    }
}
