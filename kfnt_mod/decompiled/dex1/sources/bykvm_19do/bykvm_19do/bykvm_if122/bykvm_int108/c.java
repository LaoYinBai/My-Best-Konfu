package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_int108;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.f;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.i;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.m;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.n;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.w;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.z;
import com.bytedance.msdk.adapter.ThirdSdkInit;
import com.bytedance.msdk.adapter.config.DefaultAdapterClasses;
import com.bytedance.msdk.adapter.config.ITTAdapterConfiguration;
import com.bytedance.msdk.adapter.config.TTBaseAdapterConfiguration;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.ThreadHelper;
import com.bytedance.msdk.api.v2.ad.custom.init.GMCustomAdapterConfiguration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private static volatile bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_int108.a f1973a = null;

    /* renamed from: b, reason: collision with root package name */
    private static long f1974b = 0;

    /* renamed from: c, reason: collision with root package name */
    private static boolean f1975c = false;
    private static long d;
    private static i e;

    static class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f1976a;

        a(Context context) {
            this.f1976a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            m.a(this.f1976a);
        }
    }

    static class b implements i.b {
        b() {
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.i.b
        public void a() {
            Logger.d("TTMediationSDK", "--==-- AppStateListener on foreground");
            long unused = c.d = SystemClock.elapsedRealtime();
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.i.b
        public void b() {
            Logger.d("TTMediationSDK", "--==-- AppStateListener on background");
            g.a(SystemClock.elapsedRealtime() - c.d);
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.a().a();
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.c().a();
        }
    }

    /* renamed from: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_int108.c$c, reason: collision with other inner class name */
    static class RunnableC0068c implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f1977a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_int108.c f1978b;

        /* renamed from: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_int108.c$c$a */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.a aVarA = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().a("baidu");
                if (aVarA != null) {
                    ThirdSdkInit.initTTBaiduSDK(RunnableC0068c.this.f1977a, aVarA.a());
                }
            }
        }

        /* renamed from: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_int108.c$c$b */
        class b implements bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_int108.c {
            b() {
            }

            @Override // bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_int108.c
            public void a() {
                bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_int108.c cVar = RunnableC0068c.this.f1978b;
                if (cVar != null) {
                    cVar.a();
                }
            }
        }

        RunnableC0068c(Context context, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_int108.c cVar) {
            this.f1977a = context;
            this.f1978b = cVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            ThirdSdkInit.initTTPangleSDK(this.f1977a);
            c.c();
            Set<String> setA = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b.D().a();
            Set<String> classNamesSet = DefaultAdapterClasses.getClassNamesSet();
            classNamesSet.addAll(setA);
            Map<String, Map<String, String>> mapI = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b.D().i();
            c.b(mapI);
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_int108.b.a(this.f1977a);
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.a aVarA = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().a("klevin");
            if (aVarA != null) {
                ThirdSdkInit.initTTKlevinSDK(this.f1977a, aVarA.a());
            }
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.a aVarA2 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().a("mintegral");
            if (aVarA2 != null) {
                ThirdSdkInit.initTTMintergralSDK(this.f1977a, aVarA2.a(), aVarA2.b());
            }
            ThreadHelper.runOnThreadPool(new a());
            Map<String, Map<String, String>> mapS = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b.D().s();
            if (this.f1978b != null) {
                c.f1973a.a(this.f1977a, classNamesSet, mapI, mapS, new b());
            } else {
                c.f1973a.a(this.f1977a, classNamesSet, mapI, mapS, null);
            }
        }
    }

    public static void c() {
        if (f1973a == null) {
            synchronized (c.class) {
                if (f1973a == null) {
                    f1973a = new bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_int108.a();
                }
            }
        }
    }

    public static bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_int108.a d() {
        c();
        return f1973a;
    }

    public static Map<String, ITTAdapterConfiguration> e() {
        c();
        return f1973a.a();
    }

    public static long f() {
        return f1974b;
    }

    private static void g() {
        try {
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().a();
        } catch (Throwable th) {
            Logger.e("TTMediationSDK", "InitHelper-->initSetting->loadData Exception=" + th.toString());
        }
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.b.a(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f()).a();
    }

    private static void h() {
        Context contextD = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d();
        if (contextD == null) {
            return;
        }
        try {
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.b.b().a().a(contextD, false, true, new bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.a(contextD));
        } catch (Exception unused) {
        }
    }

    public static boolean i() {
        return f1975c;
    }

    private static void j() {
        Application applicationA = a.b.a();
        if (applicationA == null) {
            Logger.d("TTMediationSDK", "AppStateListener注册失败, app = null");
            return;
        }
        Logger.d("TTMediationSDK", "AppStateListener注册成功");
        i iVar = e;
        if (iVar != null) {
            iVar.a(applicationA);
        }
        e = new i();
        e.a(applicationA, new b());
    }

    public static void b(Context context) {
        if (context != null) {
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.b(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Map<String, Map<String, String>> map) {
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.a aVarA;
        String strA;
        Class<?> cls;
        Class<?> cls2;
        Class<?> cls3;
        String strA2;
        Class<?> cls4;
        String strA3;
        Class<?> cls5;
        String strA4;
        Class<?> cls6;
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.a aVarA2 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().a("pangle");
        String strA5 = aVarA2 != null ? aVarA2.a() : null;
        if (TextUtils.isEmpty(strA5)) {
            strA5 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b.D().c();
        }
        Class<?> cls7 = DefaultAdapterClasses.getClass("pangle");
        if (cls7 != null && !TextUtils.isEmpty(strA5)) {
            HashMap map2 = new HashMap();
            map2.put("app_id", strA5);
            map.put(cls7.getName(), map2);
        }
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.a aVarA3 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().a("baidu");
        if (aVarA3 != null && (strA4 = aVarA3.a()) != null && (cls6 = DefaultAdapterClasses.getClass("baidu")) != null) {
            HashMap map3 = new HashMap();
            map3.put("app_id", strA4);
            map.put(cls6.getName(), map3);
        }
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.a aVarA4 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().a("admob");
        if (aVarA4 != null && (strA3 = aVarA4.a()) != null && (cls5 = DefaultAdapterClasses.getClass("admob")) != null) {
            HashMap map4 = new HashMap();
            map4.put("app_id", strA3);
            map.put(cls5.getName(), map4);
        }
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.a aVarA5 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().a("gdt");
        if (aVarA5 != null && (strA2 = aVarA5.a()) != null && (cls4 = DefaultAdapterClasses.getClass("gdt")) != null) {
            HashMap map5 = new HashMap();
            map5.put("app_id", strA2);
            map.put(cls4.getName(), map5);
        }
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.a aVarA6 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().a("sigmob");
        if (aVarA6 != null) {
            String strA6 = aVarA6.a();
            String strB = aVarA6.b();
            if (strA6 != null && strB != null && (cls3 = DefaultAdapterClasses.getClass("sigmob")) != null) {
                HashMap map6 = new HashMap();
                map6.put("app_id", strA6);
                map6.put("app_key", strB);
                map.put(cls3.getName(), map6);
            }
        }
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.a aVarA7 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().a("klevin");
        if (aVarA7 != null) {
            String strA7 = aVarA7.a();
            if (!TextUtils.isEmpty(strA7) && (cls2 = DefaultAdapterClasses.getClass("klevin")) != null) {
                HashMap map7 = new HashMap();
                map7.put("app_id", strA7);
                map.put(cls2.getName(), map7);
            }
        }
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.a aVarA8 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().a("ks");
        if (aVarA8 != null && (strA = aVarA8.a()) != null && (cls = DefaultAdapterClasses.getClass("ks")) != null) {
            HashMap map8 = new HashMap();
            map8.put("app_id", strA);
            map8.put(TTBaseAdapterConfiguration.APP_NAME_EXTRA_KEY, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b.D().h());
            map.put(cls.getName(), map8);
        }
        Class<?> cls8 = DefaultAdapterClasses.getClass("mintegral");
        if (cls8 == null || (aVarA = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().a("mintegral")) == null) {
            return;
        }
        String strA8 = aVarA.a();
        String strB2 = aVarA.b();
        HashMap map9 = new HashMap();
        map9.put("app_id", strA8);
        map9.put("app_key", strB2);
        map.put(cls8.getName(), map9);
    }

    public static void a(Activity activity) {
        ThirdSdkInit.initUnitySDK(activity);
    }

    public static String a(Map<String, Object> map, String str) {
        c();
        return f1973a.a(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d(), map, str);
    }

    public static GMCustomAdapterConfiguration a(String str) {
        c();
        return f1973a.b(str);
    }

    public static void a(Context context) {
        f1975c = true;
        f1974b = SystemClock.elapsedRealtime();
        ThreadHelper.runOnThreadPool(new a(context));
        w.a(context);
        b(context);
        n.m(context);
        h();
        g();
        z.c();
        f.c().b();
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.a().b();
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.c().b();
        j();
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.i.b().a();
    }

    public static void a(Context context, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_int108.c cVar) {
        ThreadHelper.runOnUiThread(new RunnableC0068c(context, cVar));
    }
}
