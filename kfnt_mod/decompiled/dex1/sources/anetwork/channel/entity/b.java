package anetwork.channel.entity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static final ExecutorService[] f685a = new ExecutorService[2];

    /* renamed from: b, reason: collision with root package name */
    private static AtomicInteger f686b = new AtomicInteger(0);

    static {
        for (int i = 0; i < 2; i++) {
            f685a[i] = Executors.newSingleThreadExecutor(new c());
        }
    }

    public static void a(int i, Runnable runnable) {
        f685a[Math.abs(i % 2)].submit(runnable);
    }
}
