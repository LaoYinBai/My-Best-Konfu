package com.mbridge.msdk.foundation.same.net.h;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.same.net.Aa;
import com.mbridge.msdk.foundation.same.net.i;
import com.mbridge.msdk.foundation.same.net.k;
import com.mbridge.msdk.foundation.same.net.l;
import com.mbridge.msdk.foundation.same.net.m;
import com.mbridge.msdk.foundation.same.net.n;
import com.mbridge.msdk.foundation.tools.SameMD5;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.out.CustomInfoManager;
import java.io.File;
import org.json.JSONArray;

/* compiled from: CommonAsyncHttpRequest.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    private static final String b = "b";
    protected Context a;

    public b(Context context) {
        if (context == null) {
            this.a = com.mbridge.msdk.foundation.controller.a.f().j();
        } else {
            this.a = context.getApplicationContext();
        }
    }

    public void a(String str, d dVar) {
        if (dVar != null) {
            String strA = Aa.a();
            if (strA == null) {
                strA = "";
            }
            dVar.a("channel", strA);
            dVar.a("band_width", com.mbridge.msdk.foundation.same.net.a.a().b() + "");
            dVar.a("open", com.mbridge.msdk.foundation.same.a.C);
            s.a(b, "excute addExtraParams , url:" + str);
            if (!TextUtils.isEmpty(str) && str.contains("setting")) {
                String strB = Aa.b();
                if (!TextUtils.isEmpty(strB)) {
                    dVar.a("keyword", strB);
                }
            }
            String str2 = dVar.b().get(MBridgeConstans.PROPERTIES_UNIT_ID);
            if (str2 != null) {
                String customInfoByUnitId = CustomInfoManager.getInstance().getCustomInfoByUnitId(str2, str);
                if (!TextUtils.isEmpty(customInfoByUnitId)) {
                    dVar.a("ch_info", customInfoByUnitId);
                }
                com.mbridge.msdk.b.d dVarF = com.mbridge.msdk.b.b.a().f(com.mbridge.msdk.foundation.controller.a.f().k(), str2);
                if (dVarF == null || TextUtils.isEmpty(dVarF.a())) {
                    return;
                }
                dVar.a("u_stid", dVarF.a());
                return;
            }
            return;
        }
        s.d(b, "addExtraParams error, params is null,frame work error");
    }

    public final void a(int i, String str, d dVar, com.mbridge.msdk.foundation.same.net.e eVar) {
        a(i, str, dVar, new com.mbridge.msdk.foundation.same.net.b(), eVar);
    }

    public final void a(int i, String str, d dVar, l lVar, com.mbridge.msdk.foundation.same.net.e eVar) {
        i fVar;
        if (dVar == null) {
            try {
                dVar = new d();
            } catch (Exception e) {
                s.a(b, e.getMessage());
            }
        }
        a(str, dVar);
        e.d(dVar);
        String str2 = dVar.b().get("sign");
        if (TextUtils.isEmpty(str2)) {
            str2 = "";
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        dVar.a("ts", jCurrentTimeMillis + "");
        dVar.a("st", SameMD5.getMD5(jCurrentTimeMillis + str2));
        k kVarA = com.mbridge.msdk.foundation.same.net.g.c.a().a(dVar);
        if (kVarA != null && eVar != null) {
            eVar.a(kVarA);
            return;
        }
        String str3 = str + "?" + dVar.toString();
        s.b(b, "get url = " + str3);
        switch (i) {
            case 0:
                fVar = new com.mbridge.msdk.foundation.same.net.d.f(0, str3, null, eVar);
                break;
            case 1:
                fVar = new com.mbridge.msdk.foundation.same.net.d.d(0, str3, null, eVar);
                break;
            case 2:
                fVar = new com.mbridge.msdk.foundation.same.net.d.c(0, str3, null, eVar);
                break;
            default:
                fVar = null;
                break;
        }
        if (fVar != null) {
            fVar.a(lVar);
            n.a(fVar);
        }
    }

    public static void a(File file, String str, com.mbridge.msdk.foundation.same.net.e eVar) {
        s.b(b, "downloadFile url = " + str);
        n.a().a(file, str, eVar);
    }

    public final void b(int i, String str, d dVar, com.mbridge.msdk.foundation.same.net.e eVar) {
        String str2 = dVar.b().get("sign");
        if (TextUtils.isEmpty(str2)) {
            str2 = "";
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        dVar.a("ts", jCurrentTimeMillis + "");
        dVar.a("st", SameMD5.getMD5(jCurrentTimeMillis + str2));
        b(i, str, dVar, new com.mbridge.msdk.foundation.same.net.b(), eVar);
    }

    public final void a(int i, String str, d dVar, com.mbridge.msdk.foundation.same.net.e eVar, l lVar) {
        String str2 = dVar.b().get("sign");
        if (str2 == null) {
            str2 = "";
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        dVar.a("ts", jCurrentTimeMillis + "");
        dVar.a("st", SameMD5.getMD5(jCurrentTimeMillis + str2));
        if (lVar != null) {
            b(i, str, dVar, lVar, eVar);
        } else {
            b(i, str, dVar, new com.mbridge.msdk.foundation.same.net.b(), eVar);
        }
    }

    public final void c(int i, String str, d dVar, com.mbridge.msdk.foundation.same.net.e eVar) {
        b(i, str, dVar, new com.mbridge.msdk.foundation.same.net.b(), eVar);
    }

    private void b(int i, String str, d dVar, l lVar, com.mbridge.msdk.foundation.same.net.e eVar) {
        if (dVar == null) {
            try {
                dVar = new d();
            } catch (Exception e) {
                s.a(b, e.getMessage());
            }
        }
        a(str, dVar);
        e.d(dVar);
        s.b(b, "post url = " + str);
        if (str.contains(com.mbridge.msdk.foundation.same.net.g.d.c().a)) {
            com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
            int iAj = 0;
            String strN = GrsBaseInfo.CountryCodeSource.UNKNOWN;
            if (aVarB != null) {
                iAj = aVarB.aj();
                strN = aVarB.N();
            }
            dVar.a("country_code", strN);
            if (iAj == 2) {
                return;
            }
            if (iAj == 1) {
                m.a().a(dVar.toString(), eVar);
                return;
            }
        } else if (str.contains(com.mbridge.msdk.foundation.same.net.g.d.c().h)) {
            if (dVar != null) {
                str = str + "?" + dVar.toString();
            }
            JSONArray jSONArrayB = com.mbridge.msdk.foundation.db.a.a.a().b();
            if (jSONArrayB != null) {
                dVar = new d();
                dVar.a(d.e, jSONArrayB.toString());
            }
        }
        i fVar = null;
        switch (i) {
            case 0:
                fVar = new com.mbridge.msdk.foundation.same.net.d.f(1, str, dVar.toString(), eVar);
                fVar.a("Content-Type", "application/x-www-form-urlencoded");
                break;
            case 1:
                fVar = new com.mbridge.msdk.foundation.same.net.d.d(1, str, dVar.toString(), eVar);
                fVar.a("Content-Type", "application/x-www-form-urlencoded");
                break;
            case 2:
                fVar = new com.mbridge.msdk.foundation.same.net.d.c(1, str, dVar.toString(), eVar);
                fVar.a("Content-Type", "application/x-www-form-urlencoded");
                break;
        }
        if (fVar != null) {
            fVar.a(lVar);
            n.a(fVar);
        }
    }
}
