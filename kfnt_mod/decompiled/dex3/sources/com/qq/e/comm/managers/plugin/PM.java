package com.qq.e.comm.managers.plugin;

import android.content.Context;
import com.qq.e.comm.constants.Constants;
import com.qq.e.comm.constants.Sig;
import com.qq.e.comm.pi.POFactory;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.StringUtil;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class PM {
    private static final Map<Class<?>, String> p = new c();
    private final Context b;
    private String c;
    private File d;
    private int e;
    private DexClassLoader f;
    private RandomAccessFile g;
    private FileLock h;
    private boolean i;
    private POFactory k;
    private int l;
    private Future<Boolean> m;
    private final f n;
    private f o;
    final ExecutorService a = Executors.newSingleThreadExecutor();
    private final e j = null;

    class a implements Callable<Boolean> {
        a() {
        }

        @Override // java.util.concurrent.Callable
        public Boolean call() throws Exception {
            long jCurrentTimeMillis = System.currentTimeMillis();
            PM pm = PM.this;
            pm.i = PM.a(pm);
            if (PM.b(PM.this)) {
                PM.this.c();
            }
            PM.this.l = (int) (System.currentTimeMillis() - jCurrentTimeMillis);
            return Boolean.TRUE;
        }
    }

    class b implements f {
        b() {
        }
    }

    static class c extends HashMap<Class<?>, String> {
        c() {
            put(POFactory.class, "com.qq.e.comm.plugin.POFactoryImpl");
        }
    }

    public PM(Context context, e eVar) {
        b bVar = new b();
        this.n = bVar;
        this.o = bVar;
        this.b = context.getApplicationContext();
        e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        e eVar = this.j;
        if (eVar != null) {
            eVar.onLoadFail();
        }
    }

    static boolean a(PM pm) {
        if (pm == null) {
            throw null;
        }
        try {
            File fileB = h.b(pm.b);
            if (!fileB.exists()) {
                fileB.createNewFile();
                StringUtil.writeTo("lock", fileB);
            }
            if (fileB.exists()) {
                RandomAccessFile randomAccessFile = new RandomAccessFile(fileB, "rw");
                pm.g = randomAccessFile;
                FileLock fileLockTryLock = randomAccessFile.getChannel().tryLock();
                pm.h = fileLockTryLock;
                if (fileLockTryLock != null) {
                    pm.g.writeByte(37);
                    return true;
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    private boolean b() {
        if (!this.i) {
            return false;
        }
        Context context = this.b;
        if (!com.qq.e.comm.managers.plugin.b.a(context, h.e(context), h.f(this.b))) {
            GDTLogger.e("插件初始化失败 ");
            com.qq.e.comm.managers.plugin.a.a(new Exception(), "initAssetPlugin failed");
            return false;
        }
        this.c = Sig.ASSET_PLUGIN_SIG;
        this.d = h.e(this.b);
        this.e = Constants.PLUGIN.ASSET_PLUGIN_VERSION;
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static boolean b(com.qq.e.comm.managers.plugin.PM r4) {
        /*
            if (r4 == 0) goto L6f
            r0 = 0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L2f
            r1.<init>()     // Catch: java.lang.Throwable -> L2f
            java.lang.String r2 = "TimeStap_BEFORE_PLUGIN_INIT:"
            r1.append(r2)     // Catch: java.lang.Throwable -> L2f
            long r2 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L2f
            r1.append(r2)     // Catch: java.lang.Throwable -> L2f
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L2f
            com.qq.e.comm.util.GDTLogger.d(r1)     // Catch: java.lang.Throwable -> L2f
            boolean r1 = r4.d()     // Catch: java.lang.Throwable -> L2f
            if (r1 != 0) goto L27
            boolean r4 = r4.b()     // Catch: java.lang.Throwable -> L2f
            if (r4 == 0) goto L29
        L27:
            r4 = 1
            r0 = 1
        L29:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            goto L41
        L2f:
            r4 = move-exception
            java.lang.String r1 = "插件加载出现异常"
            com.qq.e.comm.util.GDTLogger.e(r1, r4)     // Catch: java.lang.Throwable -> L55
            java.lang.String r1 = r4.getMessage()     // Catch: java.lang.Throwable -> L55
            com.qq.e.comm.managers.plugin.a.a(r4, r1)     // Catch: java.lang.Throwable -> L55
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
        L41:
            java.lang.String r1 = "TimeStap_AFTER_PLUGIN_INIT:"
            r4.append(r1)
            long r1 = java.lang.System.currentTimeMillis()
            r4.append(r1)
            java.lang.String r4 = r4.toString()
            com.qq.e.comm.util.GDTLogger.d(r4)
            return r0
        L55:
            r4 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "TimeStap_AFTER_PLUGIN_INIT:"
            r0.append(r1)
            long r1 = java.lang.System.currentTimeMillis()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.qq.e.comm.util.GDTLogger.d(r0)
            throw r4
        L6f:
            r4 = 0
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.e.comm.managers.plugin.PM.b(com.qq.e.comm.managers.plugin.PM):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        StringBuilder sb = new StringBuilder();
        sb.append("PluginFile:\t");
        File file = this.d;
        sb.append(file == null ? "null" : file.getAbsolutePath());
        GDTLogger.d(sb.toString());
        if (this.c == null || this.d == null) {
            this.f = null;
            return;
        }
        try {
            this.f = new DexClassLoader(this.d.getAbsolutePath(), h.a(this.b).getAbsolutePath(), null, getClass().getClassLoader());
            e eVar = this.j;
            if (eVar != null) {
                eVar.onLoadSuccess();
            }
        } catch (Throwable th) {
            GDTLogger.e("插件ClassLoader构造发生异常", th);
            a();
            com.qq.e.comm.managers.plugin.a.a(th, th.getMessage());
        }
    }

    static void d(PM pm) {
        if (pm == null) {
            throw null;
        }
        try {
            if (pm.f == null && pm.d()) {
                pm.c();
            }
        } catch (Exception e) {
            GDTLogger.e("插件更新失败: ", e);
            pm.a();
        }
    }

    private boolean d() {
        if (this.i) {
            g gVar = new g(h.c(this.b), h.d(this.b));
            if (gVar.a()) {
                GDTLogger.d("NextExist,Updated=" + gVar.a(h.e(this.b), h.f(this.b)));
            }
        }
        g gVar2 = new g(h.e(this.b), h.f(this.b));
        if (gVar2.a()) {
            if (gVar2.c() >= 1300 && gVar2.c() / 10 == 130) {
                this.c = gVar2.b();
                this.e = gVar2.c();
                this.d = h.e(this.b);
                return true;
            }
            GDTLogger.d("last updated plugin version =" + this.e + ";asset plugin version=" + Constants.PLUGIN.ASSET_PLUGIN_VERSION);
        }
        return false;
    }

    private void e() {
        this.m = this.a.submit(new a());
    }

    private void f() throws ExecutionException, InterruptedException {
        Future<Boolean> future = this.m;
        if (future != null) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException unused) {
            }
        }
    }

    public <T> T getFactory(Class<T> cls) throws ExecutionException, InterruptedException, d {
        f();
        GDTLogger.d("GetFactoryInstaceforInterface:" + cls);
        ClassLoader classLoader = Sig.ASSET_PLUGIN_SIG == null ? PM.class.getClassLoader() : this.f;
        StringBuilder sb = new StringBuilder();
        sb.append("PluginClassLoader is parent");
        sb.append(PM.class.getClassLoader() == classLoader);
        GDTLogger.d(sb.toString());
        if (classLoader == null) {
            throw new d("Fail to init GDTADPLugin,PluginClassLoader == null;while loading factory impl for:" + cls);
        }
        try {
            String str = p.get(cls);
            if (StringUtil.isEmpty(str)) {
                throw new d("factory  implemention name is not specified for interface:" + cls.getName());
            }
            Class<?> clsLoadClass = classLoader.loadClass(str);
            T tCast = cls.cast(clsLoadClass.getDeclaredMethod("getInstance", new Class[0]).invoke(clsLoadClass, new Object[0]));
            GDTLogger.d("ServiceDelegateFactory =" + tCast);
            return tCast;
        } catch (Throwable th) {
            throw new d("Fail to getfactory implement instance for interface:" + cls.getName(), th);
        }
    }

    public int getInitCostTime() {
        return this.l;
    }

    public String getLocalSig() throws ExecutionException, InterruptedException {
        f();
        return this.c;
    }

    public POFactory getPOFactory() throws d {
        if (this.k == null) {
            this.k = (POFactory) getFactory(POFactory.class);
        }
        return this.k;
    }

    public int getPluginVersion() throws ExecutionException, InterruptedException {
        f();
        return this.e;
    }

    public void update(String str, String str2) {
        if (this.i) {
            com.qq.e.comm.managers.plugin.c cVar = new com.qq.e.comm.managers.plugin.c(this.b, this.a);
            cVar.a(this.o);
            cVar.a(str, str2);
        }
    }
}
