package com.apm.insight.g;

import android.os.Looper;
import android.os.SystemClock;
import com.apm.insight.CrashType;
import com.apm.insight.ICrashCallback;
import com.apm.insight.ICrashFilter;
import com.apm.insight.IOOMCallback;
import com.apm.insight.h;
import com.apm.insight.l.e;
import com.apm.insight.l.i;
import com.apm.insight.l.k;
import com.apm.insight.l.q;
import com.apm.insight.l.r;
import com.apm.insight.l.v;
import com.apm.insight.nativecrash.NativeImpl;
import com.apm.insight.runtime.a.f;
import com.apm.insight.runtime.o;
import com.apm.insight.runtime.p;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a implements Thread.UncaughtExceptionHandler {

    /* renamed from: a, reason: collision with root package name */
    private static a f2858a = null;
    private static volatile boolean i = false;
    private static volatile ThreadLocal<Boolean> j = new ThreadLocal<>();
    private static ArrayList<b> k = new ArrayList<>();

    /* renamed from: b, reason: collision with root package name */
    private Thread.UncaughtExceptionHandler f2859b;

    /* renamed from: c, reason: collision with root package name */
    private c f2860c;
    private c d;
    private volatile int e = 0;
    private volatile int f = 0;
    private ConcurrentHashMap<String, Object> g = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Object> h = new ConcurrentHashMap<>();

    private a() {
        d();
    }

    private static int a(Throwable th, Thread thread) {
        int iA = 0;
        for (int i2 = 0; i2 < k.size(); i2++) {
            try {
                try {
                    iA |= k.get(i2).a(th, thread);
                } catch (Throwable th2) {
                    com.apm.insight.b.a().a("NPTH_CATCH", th2);
                }
            } catch (Throwable unused) {
            }
        }
        return iA;
    }

    public static a a() {
        if (f2858a == null) {
            f2858a = new a();
        }
        return f2858a;
    }

    private String a(File file, boolean z, Throwable th, String str, Thread thread, boolean z2) {
        String absolutePath = file.getAbsolutePath();
        this.h.put(file.getName(), file);
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
            NativeImpl.c(absolutePath);
        } catch (Throwable unused) {
        }
        String str2 = null;
        if (z2) {
            int iH = NativeImpl.h(absolutePath);
            if (iH <= 0) {
                return null;
            }
            try {
                NativeImpl.a(iH, com.apm.insight.l.a.c(h.g()));
                NativeImpl.a(iH, "\n");
                NativeImpl.a(iH, th.getMessage());
                NativeImpl.a(iH, "\n");
                NativeImpl.a(iH, th.getClass().getName());
                if (th.getMessage() != null) {
                    NativeImpl.a(iH, ": ");
                    NativeImpl.a(iH, th.getMessage());
                }
                NativeImpl.a(iH, "\n");
                NativeImpl.a(iH, thread.getName());
                NativeImpl.a(iH, "\n");
            } catch (Throwable unused2) {
            }
            try {
                NativeImpl.a(iH, "stack:");
                NativeImpl.a(iH, "\n");
            } catch (Throwable unused3) {
            }
            v.a(th, iH);
            NativeImpl.b(iH);
            return null;
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            if (r.a(32768)) {
                throw new RuntimeException("test exception before write stack");
            }
            fileOutputStream.write((com.apm.insight.l.a.c(h.g()) + "\n").getBytes());
            fileOutputStream.write((th.getMessage() + "\n").getBytes());
            fileOutputStream.write((th + "\n").getBytes());
            fileOutputStream.write((thread.getName() + "\n").getBytes());
            try {
                fileOutputStream.write("stack:\n".getBytes());
            } catch (Throwable unused4) {
            }
            try {
                try {
                    if (r.a(8192)) {
                        throw new RuntimeException("test exception npth write stack");
                    }
                    String strA = v.a(th, thread, new PrintStream(fileOutputStream), Looper.getMainLooper() == Looper.myLooper() ? new e.a() { // from class: com.apm.insight.g.a.1

                        /* renamed from: a, reason: collision with root package name */
                        boolean f2861a = false;

                        @Override // com.apm.insight.l.e.a
                        public boolean a(String str3) {
                            if (!this.f2861a && str3.contains("android.os.Looper.loop")) {
                                this.f2861a = true;
                            }
                            return !this.f2861a;
                        }
                    } : new e.a());
                    try {
                        k.a(fileOutputStream);
                        return strA;
                    } catch (Throwable th2) {
                        str2 = strA;
                        th = th2;
                        try {
                        } catch (Throwable th3) {
                            try {
                                fileOutputStream.write("err:\n".getBytes());
                                fileOutputStream.write((th + "\n").getBytes());
                                fileOutputStream.write((th3 + "\n").getBytes());
                            } catch (Throwable unused5) {
                            }
                        }
                        if (r.a(16384)) {
                            throw new RuntimeException("test exception system write stack");
                        }
                        th.printStackTrace(new PrintStream(fileOutputStream));
                        return str2;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            } finally {
                k.a(fileOutputStream);
            }
        } catch (Throwable unused6) {
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:107:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0237 A[Catch: all -> 0x0262, TRY_LEAVE, TryCatch #6 {all -> 0x0262, blocks: (B:132:0x0231, B:134:0x0237), top: B:174:0x0231 }] */
    /* JADX WARN: Removed duplicated region for block: B:136:0x023c  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0265  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0140 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x005e A[Catch: all -> 0x0061, Throwable -> 0x0068, TRY_ENTER, TryCatch #20 {all -> 0x0061, Throwable -> 0x0068, blocks: (B:25:0x005e, B:32:0x0076, B:35:0x00ab, B:31:0x0074), top: B:181:0x005c }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0074 A[Catch: all -> 0x0061, Throwable -> 0x0068, TryCatch #20 {all -> 0x0061, Throwable -> 0x0068, blocks: (B:25:0x005e, B:32:0x0076, B:35:0x00ab, B:31:0x0074), top: B:181:0x005c }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00c6 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00ce A[Catch: all -> 0x0221, Throwable -> 0x0229, TryCatch #21 {all -> 0x0221, Throwable -> 0x0229, blocks: (B:40:0x00bb, B:52:0x00f0, B:49:0x00ce, B:51:0x00d3, B:50:0x00d1), top: B:190:0x00bb }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00d1 A[Catch: all -> 0x0221, Throwable -> 0x0229, TryCatch #21 {all -> 0x0221, Throwable -> 0x0229, blocks: (B:40:0x00bb, B:52:0x00f0, B:49:0x00ce, B:51:0x00d3, B:50:0x00d1), top: B:190:0x00bb }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0113 A[Catch: all -> 0x0213, Throwable -> 0x021a, TryCatch #22 {Throwable -> 0x021a, all -> 0x0213, blocks: (B:56:0x010a, B:57:0x010d, B:59:0x0113, B:61:0x011a, B:63:0x011f, B:62:0x011d), top: B:188:0x010a }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x011a A[Catch: all -> 0x0213, Throwable -> 0x021a, TryCatch #22 {Throwable -> 0x021a, all -> 0x0213, blocks: (B:56:0x010a, B:57:0x010d, B:59:0x0113, B:61:0x011a, B:63:0x011f, B:62:0x011d), top: B:188:0x010a }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x011d A[Catch: all -> 0x0213, Throwable -> 0x021a, TryCatch #22 {Throwable -> 0x021a, all -> 0x0213, blocks: (B:56:0x010a, B:57:0x010d, B:59:0x0113, B:61:0x011a, B:63:0x011f, B:62:0x011d), top: B:188:0x010a }] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0196  */
    /* JADX WARN: Type inference failed for: r1v23, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v24 */
    /* JADX WARN: Type inference failed for: r1v25 */
    /* JADX WARN: Type inference failed for: r1v26 */
    /* JADX WARN: Type inference failed for: r1v27 */
    /* JADX WARN: Type inference failed for: r1v29 */
    /* JADX WARN: Type inference failed for: r2v18, types: [com.apm.insight.a.a] */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v28 */
    /* JADX WARN: Type inference failed for: r2v31 */
    /* JADX WARN: Type inference failed for: r2v40 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.Throwable a(java.lang.Thread r23, java.lang.Throwable r24) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 656
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apm.insight.g.a.a(java.lang.Thread, java.lang.Throwable):java.lang.Throwable");
    }

    private void a(Thread thread, Throwable th, boolean z, long j2) {
        List<IOOMCallback> listA = o.a().a();
        CrashType crashType = z ? CrashType.LAUNCH : CrashType.JAVA;
        Iterator<IOOMCallback> it = listA.iterator();
        while (it.hasNext()) {
            try {
                it.next().onCrash(crashType, th, thread, j2);
            } catch (Throwable th2) {
                q.b(th2);
            }
        }
    }

    public static void a(final Throwable th) {
        if (th == null) {
            return;
        }
        try {
            p.b().a(new Runnable() { // from class: com.apm.insight.g.a.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        com.apm.insight.entity.a aVarA = com.apm.insight.entity.a.a(System.currentTimeMillis(), h.g(), (Thread) null, th);
                        aVarA.a("userdefine", (Object) 1);
                        com.apm.insight.entity.a aVarA2 = f.a().a(CrashType.CUSTOM_JAVA, aVarA);
                        if (aVarA2 != null) {
                            com.apm.insight.k.d.a().b(aVarA2.h());
                        }
                    } catch (Throwable unused) {
                    }
                }
            });
        } catch (Throwable unused) {
        }
    }

    public static boolean a(long j2) {
        return e.a(j2);
    }

    private static Throwable b(Throwable th, Thread thread) {
        for (int i2 = 0; i2 < k.size(); i2++) {
            try {
                try {
                    k.get(i2).b(th, thread);
                } catch (Throwable th2) {
                    return th2;
                }
            } catch (Throwable unused) {
            }
        }
        if (Looper.getMainLooper() != Looper.myLooper()) {
            return null;
        }
        try {
            Looper.loop();
            return null;
        } catch (Throwable th3) {
            return th3;
        }
    }

    public static boolean b() {
        return i;
    }

    private boolean b(Thread thread, Throwable th) {
        ICrashFilter iCrashFilterB = h.b().b();
        if (iCrashFilterB == null) {
            return true;
        }
        try {
            return iCrashFilterB.onJavaCrashFilter(th, thread);
        } catch (Throwable unused) {
            return true;
        }
    }

    public static void c(final String str) {
        if (str == null) {
            return;
        }
        try {
            p.b().a(new Runnable() { // from class: com.apm.insight.g.a.3
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        com.apm.insight.entity.a aVar = new com.apm.insight.entity.a();
                        aVar.a("data", (Object) str);
                        aVar.a("userdefine", (Object) 1);
                        com.apm.insight.entity.a aVarA = f.a().a(CrashType.CUSTOM_JAVA, aVar);
                        if (aVarA != null) {
                            com.apm.insight.k.d.a().b(aVarA.h());
                        }
                    } catch (Throwable unused) {
                    }
                }
            });
        } catch (Throwable unused) {
        }
    }

    private void c(Thread thread, Throwable th) {
        if (this.f2859b == null || this.f2859b == this || r.a(512)) {
            return;
        }
        this.f2859b.uncaughtException(thread, th);
    }

    public static boolean c() {
        Boolean bool = j.get();
        return bool != null && bool.booleanValue();
    }

    private void d() {
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (defaultUncaughtExceptionHandler != this) {
            this.f2859b = defaultUncaughtExceptionHandler;
            Thread.setDefaultUncaughtExceptionHandler(this);
        }
    }

    private void e() {
        synchronized (this) {
            this.f--;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        while (this.f != 0 && SystemClock.uptimeMillis() - jUptimeMillis < 10000) {
            SystemClock.sleep(50L);
        }
    }

    private void f() {
        File fileA = com.apm.insight.l.o.a(h.g());
        File fileA2 = com.apm.insight.l.o.a();
        if (i.b(fileA) && i.b(fileA2)) {
            return;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        while (!com.apm.insight.k.i.a() && SystemClock.uptimeMillis() - jUptimeMillis < 10000) {
            try {
                SystemClock.sleep(500L);
            } catch (Throwable unused) {
            }
        }
    }

    private boolean g() {
        return com.apm.insight.runtime.a.a("exception_modules", "oom_callback") == 1;
    }

    public void a(c cVar) {
        this.f2860c = cVar;
    }

    public void a(String str) {
        this.g.put(str, new Object());
    }

    public void a(Thread thread, Throwable th, boolean z, com.apm.insight.entity.a aVar) {
        List<ICrashCallback> listC;
        CrashType crashType;
        if (z) {
            listC = o.a().b();
            crashType = CrashType.LAUNCH;
        } else {
            listC = o.a().c();
            crashType = CrashType.JAVA;
        }
        for (ICrashCallback iCrashCallback : listC) {
            long jUptimeMillis = SystemClock.uptimeMillis();
            try {
                iCrashCallback.onCrash(crashType, v.a(th), thread);
                aVar.b("callback_cost_" + iCrashCallback.getClass().getName(), String.valueOf(SystemClock.uptimeMillis() - jUptimeMillis));
            } catch (Throwable th2) {
                q.b(th2);
                aVar.b("callback_err_" + iCrashCallback.getClass().getName(), String.valueOf(SystemClock.uptimeMillis() - jUptimeMillis));
            }
        }
    }

    public void b(c cVar) {
        this.d = cVar;
    }

    public boolean b(String str) {
        return this.g.containsKey(str);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) throws Throwable {
        do {
            th = a(thread, th);
        } while (th != null);
    }
}
