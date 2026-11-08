package com.huawei.a.a.b.d;

import android.text.TextUtils;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {
    private String a;
    private String b;
    private String c;
    private List<com.huawei.a.a.b.c.b.d> d;
    private String e;

    public c(String str, String str2, String str3, List<com.huawei.a.a.b.c.b.d> list, String str4) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = list;
        this.e = str4;
    }

    private String a(String str, String str2) {
        String str3;
        String strC = com.huawei.a.a.a.c.c(str, str2);
        if (TextUtils.isEmpty(strC)) {
            com.huawei.a.a.b.e.a.a("DataReportHandler", "No report address,TAG : %s,TYPE: %s ", str, str2);
            return "";
        }
        if ("oper".equals(str2)) {
            str3 = "{url}/common/hmshioperqrt";
        } else if ("maint".equals(str2)) {
            str3 = "{url}/common/hmshimaintqrt";
        } else {
            if (!"diffprivacy".equals(str2)) {
                return "";
            }
            str3 = "{url}/common/common2";
        }
        return str3.replace("{url}", strC);
    }

    private byte[] a(com.huawei.a.a.b.c.b.e eVar) throws IOException {
        String str;
        String str2;
        try {
            JSONObject jSONObjectC = eVar.c();
            if (jSONObjectC != null) {
                return com.huawei.a.a.b.k.d.a(jSONObjectC.toString().getBytes(C.UTF8_NAME));
            }
            com.huawei.a.a.b.e.a.c("DataReportHandler", "uploadEvents is null");
            return new byte[0];
        } catch (UnsupportedEncodingException unused) {
            str = "DataReportHandler";
            str2 = "sendData(): getBytes - Unsupported coding format!!";
            com.huawei.a.a.b.e.a.c(str, str2);
            return new byte[0];
        } catch (JSONException unused2) {
            str = "DataReportHandler";
            str2 = "uploadEvents to json error";
            com.huawei.a.a.b.e.a.c(str, str2);
            return new byte[0];
        }
    }

    private JSONArray b() {
        JSONArray jSONArray = new JSONArray();
        Iterator<com.huawei.a.a.b.c.b.d> it = this.d.iterator();
        while (it.hasNext()) {
            try {
                jSONArray.put(it.next().d());
            } catch (JSONException unused) {
                com.huawei.a.a.b.e.a.b("DataReportHandler", "handleEvents: json error,Abandon this data");
            }
        }
        return jSONArray;
    }

    private void c() {
        if (com.huawei.a.a.b.k.b.a(com.huawei.a.a.a.b.j(), "backup_event", 5242880)) {
            com.huawei.a.a.b.e.a.b("DataReportHandler", "backup file reach max limited size, discard new event ");
            return;
        }
        JSONArray jSONArrayB = b();
        String strA = com.huawei.a.a.b.k.e.a(this.a, this.b, this.e);
        com.huawei.a.a.b.e.a.b("DataReportHandler", "Update data cached into backup,spKey: " + strA);
        com.huawei.a.a.b.i.a.a(com.huawei.a.a.a.b.j(), "backup_event", strA, jSONArrayB.toString());
    }

    private com.huawei.a.a.b.c.b.e d() {
        return com.huawei.a.a.d.a.a(this.d, this.a, this.b, this.e, this.c);
    }

    public void a() throws IOException {
        com.huawei.a.a.b.j.c.a dVar;
        com.huawei.a.a.b.j.c.b bVarA;
        String str;
        String str2;
        String strA = a(this.a, this.b);
        if (!TextUtils.isEmpty(strA) || "preins".equals(this.b)) {
            if (!"_hms_config_tag".equals(this.a) && !"_openness_config_tag".equals(this.a)) {
                c();
            }
            com.huawei.a.a.b.c.b.e eVarD = d();
            if (eVarD != null) {
                byte[] bArrA = a(eVarD);
                if (bArrA.length == 0) {
                    str = "DataReportHandler";
                    str2 = "request body is empty";
                } else {
                    dVar = new com.huawei.a.a.b.j.b.a(bArrA, strA, this.a, this.b, this.e, this.d);
                    bVarA = com.huawei.a.a.b.j.c.b.b();
                }
            } else {
                dVar = new com.huawei.a.a.b.j.b.d(this.d, this.a, this.e, this.b);
                bVarA = com.huawei.a.a.b.j.c.b.a();
            }
            bVarA.a(dVar);
            return;
        }
        str = "DataReportHandler";
        str2 = "collectUrl is empty";
        com.huawei.a.a.b.e.a.c(str, str2);
    }
}
