package com.mbridge.msdk.foundation.same.net.h;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.kwad.v8.Platform;
import com.liulishuo.filedownloader.services.FileDownloadBroadcastHandler;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.tools.n;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.u;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.mbbid.common.BidResponsedEx;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CommonRequestParamsForAdd.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class e {
    private static String a = "";
    private static String b = "";

    public static void a(d dVar, Context context) throws JSONException {
        if (com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA)) {
            dVar.a("has_wx", w.c(context, "com.tencent.mm") + "");
            com.mbridge.msdk.foundation.controller.authoritycontroller.a.a();
            if (com.mbridge.msdk.foundation.a.a.a.a().b(MBridgeConstans.AUTHORITY_DNT, 0) == 1) {
                dVar.a("dnt", "1");
            }
        }
        if (com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_OTHER)) {
            dVar.a(d.f, u.b() + "");
            dVar.a(d.g, u.a() + "");
            dVar.a("power_rate", com.mbridge.msdk.foundation.tools.b.a() + "");
            dVar.a("charging", com.mbridge.msdk.foundation.tools.b.b() + "");
            dVar.a(d.d, o.F());
        }
        dVar.a("pkg_source", o.a(o.z(context), context));
        if (com.mbridge.msdk.foundation.controller.a.f().n() != null) {
            dVar.a("web_env", com.mbridge.msdk.foundation.controller.a.f().n().toString());
        }
        if (Build.VERSION.SDK_INT > 18) {
            dVar.a("http_req", "2");
        }
        a(dVar, true);
        e(dVar);
        c(dVar);
        com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
        if (aVarB == null) {
            aVarB = com.mbridge.msdk.b.b.a().b();
        }
        if (aVarB.au()) {
            dVar.a("gdpr_consent", com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().f() + "");
        }
        f(dVar);
    }

    public static void a(d dVar) {
        dVar.a("api_version", com.mbridge.msdk.foundation.same.a.f);
    }

    public static void b(d dVar) throws JSONException {
        a(dVar, false);
        e(dVar);
        c(dVar);
        f(dVar);
    }

    public static void c(d dVar) {
        try {
            if (TextUtils.isEmpty(com.mbridge.msdk.foundation.same.a.s)) {
                com.mbridge.msdk.foundation.same.a.s = com.mbridge.msdk.foundation.a.a.a.a().b(com.huawei.updatesdk.service.d.a.b.a);
            }
            if (!TextUtils.isEmpty(com.mbridge.msdk.foundation.same.a.s)) {
                dVar.a(com.huawei.updatesdk.service.d.a.b.a, com.mbridge.msdk.foundation.same.a.s);
            }
            if (TextUtils.isEmpty(com.mbridge.msdk.foundation.same.a.t)) {
                com.mbridge.msdk.foundation.same.a.t = com.mbridge.msdk.foundation.a.a.a.a().b("c");
            }
            if (TextUtils.isEmpty(com.mbridge.msdk.foundation.same.a.t)) {
                return;
            }
            dVar.a("c", com.mbridge.msdk.foundation.same.a.t);
        } catch (Exception e) {
            s.d("CommonRequestParamsForAdd", e.getMessage());
        }
    }

    private static void e(d dVar) {
        int I = o.I();
        if (I != -1) {
            dVar.a("unknown_source", I + "");
        }
    }

    private static void f(d dVar) {
        String strJ = o.J();
        if (TextUtils.isEmpty(strJ) || strJ.equals(Platform.ANDROID)) {
            return;
        }
        dVar.a("hm_info", strJ);
    }

    private static void a(d dVar, boolean z) throws JSONException {
        Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
        com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
        if (aVarB != null) {
            if (!TextUtils.isEmpty(aVarB.b()) && z) {
                dVar.a("a_stid", aVarB.b());
            }
            JSONObject jSONObject = new JSONObject();
            try {
                if (aVarB.E() == 1) {
                    if (o.b(contextJ) != null && com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a("authority_imei_mac")) {
                        jSONObject.put("imei", o.b(contextJ));
                    }
                    if (o.i(contextJ) != null && com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a("authority_imei_mac")) {
                        jSONObject.put("mac", o.i(contextJ));
                    }
                    if (o.c(contextJ) != null && z && com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a("authority_imei_mac")) {
                        jSONObject.put("imsi", o.c(contextJ));
                    }
                }
                if (aVarB.j() == 1 && o.a(contextJ) != null && com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_DEVICE_ID)) {
                    jSONObject.put("oaid", o.a(contextJ));
                }
                if (aVarB.F() == 1 && o.g(contextJ) != null && com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a("authority_android_id")) {
                    jSONObject.put("android_id", o.g(contextJ));
                }
                if (com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA)) {
                    jSONObject.put(BidResponsedEx.KEY_CID, o.a() + "");
                    jSONObject.put("dmt", o.H() + "");
                    jSONObject.put("dmf", o.G());
                    jSONObject.put("ct", o.s());
                }
                if (TextUtils.isEmpty(jSONObject.toString())) {
                    return;
                }
                if (!jSONObject.equals(a)) {
                    b = n.a(jSONObject.toString());
                }
                if (TextUtils.isEmpty(b)) {
                    return;
                }
                dVar.a("dvi", b);
            } catch (Exception e) {
                s.d("CommonRequestParamsForAdd", e.getMessage());
            }
        }
    }

    public static void d(d dVar) {
        if (dVar != null) {
            if (!com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA)) {
                dVar.a(FileDownloadBroadcastHandler.KEY_MODEL);
                dVar.a("brand");
                dVar.a("screen_size");
                dVar.a("sub_ip");
                dVar.a("network_type");
                dVar.a("useragent");
                dVar.a("ua");
                dVar.a("language");
                dVar.a("network_str");
                dVar.a("mnc");
                dVar.a("mcc");
                dVar.a("os_version");
                dVar.a("gp_version");
                dVar.a("country_code");
            }
            if (!com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_OTHER)) {
                dVar.a(d.f);
                dVar.a(d.g);
                dVar.a("power_rate");
                dVar.a("charging");
                dVar.a("timezone");
            }
            if (com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_DEVICE_ID)) {
                return;
            }
            dVar.a("gaid");
            dVar.a("gaid2");
            dVar.a("oaid");
        }
    }
}
