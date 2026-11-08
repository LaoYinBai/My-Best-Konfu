package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import bykvm_19do.bykvm_19do.bykvm_19do.a1;
import bykvm_19do.bykvm_19do.bykvm_19do.g1;
import bykvm_19do.bykvm_19do.bykvm_19do.i1;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class v0 implements a1 {

    /* renamed from: a, reason: collision with root package name */
    private static final o0<Boolean> f1397a = new a();

    static class a extends o0<Boolean> {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // bykvm_19do.bykvm_19do.bykvm_19do.o0
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public Boolean a(Object... objArr) {
            return Boolean.valueOf(u0.a((Context) objArr[0], "com.huawei.hwid"));
        }
    }

    static class c extends a1.a {

        /* renamed from: c, reason: collision with root package name */
        long f1398c = 0;

        c() {
        }
    }

    v0() {
    }

    static boolean d(Context context) {
        if (context == null) {
            return false;
        }
        return f1397a.b(context).booleanValue();
    }

    private static int e(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.huawei.hwid", 0);
            if (packageInfo != null) {
                return packageInfo.versionCode;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return 0;
    }

    @Nullable
    private static Pair<String, Boolean> f(Context context) {
        return (Pair) new i1(context, new Intent("com.uodis.opendevice.OPENIDS_SERVICE").setPackage("com.huawei.hwid"), new b()).a();
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.a1
    public boolean a(Context context) {
        return d(context);
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.a1
    @Nullable
    @WorkerThread
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public c b(Context context) {
        c cVar = new c();
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                String string = Settings.Global.getString(context.getContentResolver(), "pps_oaid");
                String string2 = Settings.Global.getString(context.getContentResolver(), "pps_track_limit");
                if (!TextUtils.isEmpty(string)) {
                    cVar.f1312a = string;
                    cVar.f1313b = Boolean.parseBoolean(string2);
                    cVar.f1398c = 202003021704L;
                    return cVar;
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        Pair<String, Boolean> pairF = f(context);
        if (pairF != null) {
            cVar.f1312a = (String) pairF.first;
            cVar.f1313b = ((Boolean) pairF.second).booleanValue();
            cVar.f1398c = e(context);
        }
        return cVar;
    }

    static class b implements i1.b<g1, Pair<String, Boolean>> {
        b() {
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_19do.i1.b
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public g1 a(IBinder iBinder) {
            return g1.a.a(iBinder);
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_19do.i1.b
        public Pair<String, Boolean> a(g1 g1Var) {
            if (g1Var == null) {
                return null;
            }
            return new Pair<>(g1Var.a(), Boolean.valueOf(g1Var.c()));
        }
    }
}
