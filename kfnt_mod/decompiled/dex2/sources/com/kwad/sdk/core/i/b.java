package com.kwad.sdk.core.i;

import androidx.annotation.NonNull;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    private static String a = "GlobalThreadPools";
    private static Map<String, WeakReference<ExecutorService>> b = new ConcurrentHashMap();

    private static class a implements InterfaceC0040b {
        private a() {
        }

        @Override // com.kwad.sdk.core.i.b.InterfaceC0040b
        @NonNull
        public ExecutorService a() {
            return Executors.newSingleThreadExecutor();
        }
    }

    /* renamed from: com.kwad.sdk.core.i.b$b, reason: collision with other inner class name */
    private interface InterfaceC0040b {
        @NonNull
        ExecutorService a();
    }

    private static class c implements InterfaceC0040b {
        private c() {
        }

        @Override // com.kwad.sdk.core.i.b.InterfaceC0040b
        @NonNull
        public ExecutorService a() {
            return new ThreadPoolExecutor(2, 5, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new d(5, "diskAndHttp"));
        }
    }

    public static class d implements ThreadFactory {
        private static final AtomicInteger a = new AtomicInteger(1);
        private final String d;
        private final int e;
        private final AtomicInteger c = new AtomicInteger(1);
        private final ThreadGroup b = Thread.currentThread().getThreadGroup();

        public d(int i, String str) {
            this.e = i;
            this.d = "ksad-" + str + a.getAndIncrement() + "-thread-";
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(this.b, runnable, this.d + this.c.getAndIncrement(), 0L);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            thread.setPriority(this.e);
            return thread;
        }
    }

    private static class e implements InterfaceC0040b {
        private e() {
        }

        @Override // com.kwad.sdk.core.i.b.InterfaceC0040b
        @NonNull
        public ExecutorService a() {
            return new ThreadPoolExecutor(1, 3, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new d(3, "uil-pool-"));
        }
    }

    private static class f implements InterfaceC0040b {
        private f() {
        }

        @Override // com.kwad.sdk.core.i.b.InterfaceC0040b
        @NonNull
        public ExecutorService a() {
            return new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new d(5, "lruDiskCache"));
        }
    }

    private static class g {
        static String a = "lruDiskCache";
        static String b = "ksImageLoaderTask";
        static String c = "report";
        static String d = "appInstallManager";
        static String e = "diskAndHttpIO";
        static String f = "async";
        static String g = "async-schedule";
        static String h = "videoCache";
    }

    private static class h implements InterfaceC0040b {
        private h() {
        }

        @Override // com.kwad.sdk.core.i.b.InterfaceC0040b
        @NonNull
        public ExecutorService a() {
            return Executors.newSingleThreadExecutor(new d(3, "report-"));
        }
    }

    private static class i implements InterfaceC0040b {
        private i() {
        }

        @Override // com.kwad.sdk.core.i.b.InterfaceC0040b
        @NonNull
        public ExecutorService a() {
            return new ThreadPoolExecutor(3, 5, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new d(5, g.h));
        }
    }

    public static ExecutorService a() {
        com.kwad.sdk.core.d.a.a(a, "forDiskLruCache");
        return a(g.a, new f());
    }

    @NonNull
    private static ExecutorService a(String str, @NonNull InterfaceC0040b interfaceC0040b) {
        if (str == null) {
            return interfaceC0040b.a();
        }
        WeakReference<ExecutorService> weakReference = b.get(str);
        if (weakReference != null && weakReference.get() != null) {
            return weakReference.get();
        }
        ExecutorService executorServiceA = interfaceC0040b.a();
        b.put(str, new WeakReference<>(executorServiceA));
        return executorServiceA;
    }

    public static ExecutorService b() {
        return a(g.a, new f());
    }

    public static synchronized ExecutorService c() {
        com.kwad.sdk.core.d.a.a(a, "forKsImageLoaderTask");
        return a(g.b, new e());
    }

    public static synchronized ExecutorService d() {
        com.kwad.sdk.core.d.a.a(a, "forKsImageLoaderCachedImages");
        return a(g.b, new e());
    }

    public static ExecutorService e() {
        com.kwad.sdk.core.d.a.a(a, "forKsImageLoaderTaskDistributor");
        return a(g.b, new e());
    }

    public static synchronized ExecutorService f() {
        com.kwad.sdk.core.d.a.a(a, "forBaseBatchReporter");
        return a(g.c, new h());
    }

    public static synchronized ExecutorService g() {
        com.kwad.sdk.core.d.a.a(a, "forAdReportManager");
        return a(g.c, new h());
    }

    public static ExecutorService h() {
        com.kwad.sdk.core.d.a.a(a, "forBaseNetwork");
        return a(g.e, new c());
    }

    public static ExecutorService i() {
        com.kwad.sdk.core.d.a.a(a, "forFileHelper");
        return a(g.e, new c());
    }

    public static ExecutorService j() {
        com.kwad.sdk.core.d.a.a(a, "forAppCacheManager");
        return a(g.d, new a());
    }

    public static ExecutorService k() {
        com.kwad.sdk.core.d.a.a(a, "forAppInstallCheckManager");
        return a(g.d, new a());
    }

    public static ExecutorService l() {
        com.kwad.sdk.core.d.a.a(a, "forHttpCacheServer");
        return a(g.h, new i());
    }

    public static ExecutorService m() {
        com.kwad.sdk.core.d.a.a(a, "forAppStatusHelper");
        return Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.kwad.sdk.core.i.b.1
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, "ksad-ashelper");
                thread.setPriority(3);
                return thread;
            }
        });
    }

    public static ExecutorService n() {
        com.kwad.sdk.core.d.a.a(a, "forAsync");
        return a(g.f, new InterfaceC0040b() { // from class: com.kwad.sdk.core.i.b.2
            @Override // com.kwad.sdk.core.i.b.InterfaceC0040b
            @NonNull
            public ExecutorService a() {
                return new ThreadPoolExecutor(0, 3, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new d(5, g.f));
            }
        });
    }

    public static ScheduledExecutorService o() {
        com.kwad.sdk.core.d.a.a(a, "forAsyncSchedule");
        ExecutorService executorServiceA = a(g.g, new InterfaceC0040b() { // from class: com.kwad.sdk.core.i.b.3
            @Override // com.kwad.sdk.core.i.b.InterfaceC0040b
            @NonNull
            public ExecutorService a() {
                return new ScheduledThreadPoolExecutor(1, new d(5, g.g));
            }
        });
        return executorServiceA instanceof ScheduledExecutorService ? (ScheduledExecutorService) executorServiceA : new ScheduledThreadPoolExecutor(1, new d(5, g.g));
    }
}
