package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1;

import android.content.Context;
import android.text.TextUtils;
import bykvm_19do.bykvm_19do.bykvm_19do.e;
import com.bytedance.mapplog.util.TTEncryptUtils;
import com.bytedance.msdk.adapter.util.Logger;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class m {

    /* renamed from: a, reason: collision with root package name */
    private static volatile String f2016a = "";

    /* renamed from: b, reason: collision with root package name */
    private static volatile String f2017b = "";

    /* renamed from: c, reason: collision with root package name */
    private static volatile boolean f2018c = false;

    static class a implements bykvm_19do.bykvm_19do.bykvm_19do.c {
        a() {
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_19do.c
        public void a(String str, String str2, String str3) {
            if (TextUtils.isEmpty(m.f2016a)) {
                Logger.i("TTMediationSDK_SDK_Init", "applod ------------: did = " + str);
                String unused = m.f2016a = str;
                n.a(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d(), m.f2016a);
                if (TextUtils.isEmpty(m.f2016a)) {
                    return;
                }
                bykvm_19do.bykvm_19do.bykvm_if122.bykvm_int108.d.f();
                bykvm_19do.bykvm_19do.bykvm_if122.bykvm_int108.d.g();
            }
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_19do.c
        public void a(boolean z, String str, String str2, String str3, String str4, String str5, String str6) {
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_19do.c
        public void a(boolean z, JSONObject jSONObject) {
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_19do.c
        public void b(boolean z, JSONObject jSONObject) {
        }
    }

    static class b implements bykvm_19do.bykvm_19do.bykvm_19do.e {
        b() {
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_19do.e
        public void a(e.a aVar) {
            if (!TextUtils.isEmpty(m.f2017b) || aVar == null || TextUtils.isEmpty(aVar.f1326a)) {
                return;
            }
            Logger.i("TTMediationSDK_SDK_Init", "applod ------------: oaid = " + aVar.f1326a);
            String unused = m.f2017b = aVar.f1326a;
        }
    }

    static class c implements bykvm_19do.bykvm_19do.bykvm_19do.g {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f2019a;

        c(Context context) {
            this.f2019a = context;
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_19do.g
        public String a() {
            return n.i(this.f2019a);
        }
    }

    public static String c() {
        if (!TextUtils.isEmpty(f2016a)) {
            return f2016a;
        }
        f2016a = bykvm_19do.bykvm_19do.bykvm_19do.a.g();
        Logger.i("TTMediationSDK_SDK_Init", "applod ------------: did from get = " + f2016a);
        if (TextUtils.isEmpty(f2016a)) {
            e();
        }
        return f2016a;
    }

    public static String d() {
        if (!TextUtils.isEmpty(f2017b)) {
            return f2017b;
        }
        if (TextUtils.isEmpty(f2017b)) {
            f();
        }
        return f2017b;
    }

    private static void e() {
        bykvm_19do.bykvm_19do.bykvm_19do.a.a(new a());
    }

    private static void f() {
        bykvm_19do.bykvm_19do.bykvm_19do.a.a(new b());
    }

    public static byte[] a(byte[] bArr, int i) {
        return TTEncryptUtils.a(bArr, i);
    }

    private static void b(Context context) {
        if (f2018c) {
            return;
        }
        f2018c = true;
        bykvm_19do.bykvm_19do.bykvm_19do.h hVar = new bykvm_19do.bykvm_19do.bykvm_19do.h("262612", "gromore");
        hVar.a(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b.D().q().isCanUsePhoneState());
        hVar.b(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b.D().q().isCanUseLocation());
        hVar.a(new c(context));
        bykvm_19do.bykvm_19do.bykvm_19do.a.a(context, hVar);
        c();
        d();
        Logger.i("TTMediationSDK_SDK_Init", "applod ------------: init end ");
    }

    public static void a(Context context) {
        b(context);
    }
}
