package com.baidu.mobads.sdk.internal;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.appsflyer.share.Constants;
import com.baidu.mobads.sdk.api.IXAdContainerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.lang.Thread;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.jar.JarFile;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class bh {

    /* renamed from: a, reason: collision with root package name */
    public static final String f3160a = "ApkLoader";

    /* renamed from: b, reason: collision with root package name */
    protected static Thread.UncaughtExceptionHandler f3161b = null;

    /* renamed from: c, reason: collision with root package name */
    public static final String f3162c = "__badApkVersion__9.171";
    public static final String d = "previousProxyVersion";
    protected static final String e = "__xadsdk__remote__final__";
    protected static final String f = "bdxadsdk.jar";
    protected static final String g = "__xadsdk__remote__final__builtin__.jar";
    protected static final String h = "__xadsdk__remote__final__builtinversion__.jar";
    protected static final String i = "__xadsdk__remote__final__downloaded__.jar";
    protected static final String j = "__xadsdk__remote__final__running__.jar";
    public static final String k = "OK";
    public static final String l = "ERROR";
    public static final String m = "APK_INFO";
    public static final String n = "CODE";
    public static final String o = "success";
    protected static volatile ax p = null;
    protected static volatile ax q = null;
    protected static volatile Class r = null;
    protected static String s = null;
    protected static final Handler t = new bi(Looper.getMainLooper());
    private static final String x = "baidu_sdk_remote";
    private boolean A;
    private CopyOnWriteArrayList<c> B;
    private c C;
    protected Handler u;

    @SuppressLint({"HandlerLeak"})
    protected final Handler v;
    private bf w;
    private final Context y;
    private bb z;

    public interface c {
        void a(boolean z);
    }

    public final String a() {
        return "9.171";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        try {
            File[] fileArrListFiles = this.y.getFilesDir().listFiles();
            int i2 = 0;
            while (fileArrListFiles != null) {
                if (i2 >= fileArrListFiles.length) {
                    return;
                }
                if (fileArrListFiles[i2].getAbsolutePath().contains(e) && fileArrListFiles[i2].getAbsolutePath().endsWith("dex")) {
                    fileArrListFiles[i2].delete();
                }
                i2++;
            }
        } catch (Exception e2) {
            bb.a().c(e2);
        }
    }

    public bh(Activity activity) {
        this(activity.getApplicationContext());
    }

    public bh(Context context) {
        this.z = bb.a();
        this.A = false;
        this.u = t;
        this.B = new CopyOnWriteArrayList<>();
        this.v = new bj(this, Looper.getMainLooper());
        this.y = context;
        c(context);
        if (f3161b == null) {
            f3161b = bv.a(context);
            bv.a(context).a(new bk(this));
        }
        if (Thread.getDefaultUncaughtExceptionHandler() instanceof bv) {
            return;
        }
        Thread.setDefaultUncaughtExceptionHandler(f3161b);
    }

    private static void c(Context context) {
        if (TextUtils.isEmpty(s)) {
            s = context.getDir(x, 0).getAbsolutePath() + Constants.URL_PATH_DELIMITER;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SharedPreferences m() {
        return this.y.getSharedPreferences(r.as, 0);
    }

    protected void b() {
        new File(f()).delete();
    }

    @TargetApi(9)
    protected void a(String str) {
        if (p != null) {
            SharedPreferences.Editor editorEdit = m().edit();
            editorEdit.putFloat(f3162c, (float) p.f3137b);
            if (av.a(this.y).a() >= 9) {
                editorEdit.apply();
            } else {
                editorEdit.commit();
            }
        }
    }

    private boolean n() {
        String string = m().getString(d, null);
        return string == null || !string.equals(a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        Message messageObtainMessage = this.u.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putBoolean("success", z);
        messageObtainMessage.setData(bundle);
        messageObtainMessage.what = 0;
        this.u.sendMessage(messageObtainMessage);
    }

    protected static String c() {
        if (TextUtils.isEmpty(s)) {
            return "";
        }
        return s + g;
    }

    protected static String d() {
        if (TextUtils.isEmpty(s)) {
            return "";
        }
        return s + h;
    }

    protected void e() throws b {
        this.z.a(f3160a, "start load assets file");
        d(this.y);
        String strC = c();
        ba baVar = new ba(strC, this.y);
        if (az.a(baVar)) {
            this.z.a(f3160a, "assets file can read ,will use it ");
            if (c(baVar)) {
                b(true);
                return;
            }
            return;
        }
        throw new b("loadBuiltInApk failed: " + strC);
    }

    private static synchronized void d(Context context) {
        try {
            String strC = c();
            double dB = b(strC);
            bb.a().a(f3160a, "copy assets,compare version=" + Double.valueOf("9.171") + "remote=" + dB);
            if (Double.valueOf("9.171").doubleValue() != dB) {
                ba baVar = new ba(strC, context);
                if (baVar.exists()) {
                    baVar.delete();
                }
                az.a(context, f, strC);
            }
        } catch (Exception e2) {
            throw new b("loadBuiltInApk failed: " + e2.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ba baVar) {
        Class<?> clsB = baVar.b();
        synchronized (this) {
            q = new ax(clsB, this.y);
        }
    }

    private void b(ba baVar) throws Throwable {
        this.z.a(f3160a, "len=" + baVar.length() + ", path=" + baVar.getAbsolutePath());
        if (p == null) {
            String strA = a(this.y);
            ba baVar2 = new ba(strA, this.y);
            if (baVar2.exists()) {
                baVar2.delete();
            }
            try {
                az.a(new FileInputStream(baVar), strA);
            } catch (Exception e2) {
                this.z.c(e2);
            }
            p = new ax(baVar2.b(), this.y);
            try {
                IXAdContainerFactory iXAdContainerFactoryA = p.a();
                this.z.a(f3160a, "preloaded apk.version=" + iXAdContainerFactoryA.getRemoteVersion());
                return;
            } catch (a e3) {
                this.z.a(f3160a, "preload local apk " + baVar.getAbsolutePath() + " failed, msg:" + e3.getMessage() + ", v=" + p.f3137b);
                a(e3.getMessage());
                throw e3;
            }
        }
        this.z.a(f3160a, "mApkBuilder already initialized, version: " + p.f3137b);
    }

    private boolean c(ba baVar) {
        synchronized (this) {
            b(baVar);
            this.z.a(f3160a, "loaded: " + baVar.getPath());
        }
        return true;
    }

    private boolean o() {
        try {
            if (!az.a(c())) {
                if (!az.a(f())) {
                    return false;
                }
            }
            return true;
        } catch (Exception e2) {
            this.z.a(e2);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        if (!z && !o()) {
            this.A = true;
        } else {
            a(z, z ? "apk Successfully Loaded" : "apk Load Failed");
        }
        if (this.A) {
            am.a().a((h) new bl(this, z));
        } else {
            am.a().a(new bm(this, z), 5L, TimeUnit.SECONDS);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z) {
        double d2;
        if (z) {
            try {
                d2 = p.f3137b;
            } catch (Exception unused) {
                return;
            }
        } else {
            d2 = 0.0d;
        }
        af.a(d2, new bn(this, d2), new bo(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(boolean z, String str) {
        bv.a(this.y).c();
        if (this.B != null && this.B.size() > 0) {
            Iterator<c> it = this.B.iterator();
            while (it.hasNext()) {
                c next = it.next();
                next.a(z);
                this.B.remove(next);
            }
        }
    }

    public static String a(Context context) {
        if (TextUtils.isEmpty(s)) {
            s = context.getDir(x, 0).getAbsolutePath() + Constants.URL_PATH_DELIMITER;
        }
        if (TextUtils.isEmpty(s)) {
            return "";
        }
        return s + j;
    }

    protected static String f() {
        if (TextUtils.isEmpty(s)) {
            return "";
        }
        return s + i;
    }

    protected void g() throws a {
        if (h() != 2 ? p() : false) {
            this.z.a(f3160a, "load downloaded file success,use it");
            b(true);
            return;
        }
        this.z.a(f3160a, "no downloaded file yet, use built-in apk file");
        try {
            e();
        } catch (b e2) {
            this.z.a(f3160a, "loadBuiltInApk failed: " + e2.toString());
            throw new a("load built-in apk failed" + e2.toString());
        }
    }

    public int h() {
        return this.y.getApplicationContext().getSharedPreferences("baidu_cloudControlConfig", 0).getInt("baidu_cloudConfig_pktype", 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean p() throws a {
        ba baVar = new ba(f(), this.y);
        if (!az.a(baVar)) {
            return false;
        }
        try {
            if (n()) {
                throw new a("XAdApkLoader upgraded, drop stale downloaded file, use built-in instead");
            }
            synchronized (this) {
                this.z.a(f3160a, "loadDownloadedOrBuiltInApk len=" + baVar.length() + ", path=" + baVar.getAbsolutePath());
                b(baVar);
                double d2 = (double) m().getFloat(f3162c, -1.0f);
                this.z.a(f3160a, "downloadedApkFile.getApkVersion(): " + baVar.c() + ", badApkVersion: " + d2);
                if (baVar.c() == d2) {
                    throw new a("downloaded file marked bad, drop it and use built-in");
                }
                this.z.a(f3160a, "loaded: " + baVar.getPath());
            }
            return true;
        } catch (a e2) {
            this.z.a(f3160a, "load downloaded apk failed: " + e2.toString() + ", fallback to built-in");
            if (baVar.exists()) {
                baVar.delete();
            }
            k();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(bf bfVar) {
        if (bfVar.a().booleanValue()) {
            bd bdVarA = bd.a(this.y, bfVar, s, this.v);
            if (!bdVarA.isAlive()) {
                this.z.a(f3160a, "XApkDownloadThread starting ...");
                bdVarA.start();
            } else {
                this.z.a(f3160a, "XApkDownloadThread already started");
                bdVarA.a(bfVar.c());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(c cVar, Handler handler) throws a {
        if (this.B != null && !this.B.contains(cVar)) {
            this.B.add(cVar);
        }
        this.u = handler;
        if (p == null) {
            g();
        } else {
            b(true);
        }
    }

    @TargetApi(9)
    public void a(c cVar, Handler handler) {
        am.a().a((h) new bp(this, cVar, handler));
    }

    public void a(c cVar) {
        a(cVar, t);
    }

    public IXAdContainerFactory i() {
        return a(p);
    }

    public IXAdContainerFactory j() {
        return a(q);
    }

    private IXAdContainerFactory a(ax axVar) {
        if (axVar == null) {
            return null;
        }
        try {
            return axVar.a();
        } catch (Exception unused) {
            return null;
        }
    }

    protected void k() {
        if (p != null) {
            p.b();
            p = null;
        }
    }

    public static final class a extends Exception {

        /* renamed from: a, reason: collision with root package name */
        private static final long f3163a = 2978543166232984104L;

        public a(String str) {
            bb.a().c(str);
        }
    }

    protected static final class b extends Exception {

        /* renamed from: a, reason: collision with root package name */
        private static final long f3164a = -7838296421993681751L;

        public b(String str) {
            bb.a().c(str);
        }
    }

    public static double b(Context context) throws Throwable {
        try {
            c(context);
            double dB = b(f());
            String strD = d();
            if (Double.valueOf("9.171").doubleValue() > b(strD)) {
                ba baVar = new ba(strD, context);
                if (baVar.exists()) {
                    baVar.delete();
                }
                az.a(context, f, strD);
            }
            return Math.max(dB, b(d()));
        } catch (Exception unused) {
            return 0.0d;
        }
    }

    public static double b(String str) throws NumberFormatException {
        if (bu.d.booleanValue()) {
            File file = new File(str);
            if (az.a(file)) {
                JarFile jarFile = new JarFile(file);
                double d2 = Double.parseDouble(jarFile.getManifest().getMainAttributes().getValue("Implementation-Version"));
                jarFile.close();
                if (d2 > 0.0d) {
                    return d2;
                }
            }
            return 0.0d;
        }
        return Double.valueOf("9.171").doubleValue();
    }
}
