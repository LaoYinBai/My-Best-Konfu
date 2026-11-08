package anetwork.channel.g;

import anet.channel.monitor.b;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    static AtomicBoolean f703a = new AtomicBoolean(false);

    public static synchronized void a() {
        if (f703a.compareAndSet(false, true)) {
            b.a().d();
        }
    }
}
