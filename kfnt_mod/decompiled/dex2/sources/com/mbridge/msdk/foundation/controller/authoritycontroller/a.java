package com.mbridge.msdk.foundation.controller.authoritycontroller;

import android.text.TextUtils;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.b.b;
import com.mbridge.msdk.foundation.tools.s;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: SDKAuthorityController.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static volatile a c;
    public CallBackForDeveloper b;
    public ArrayList<String> a = new ArrayList<>();
    private int e = 3;
    private AuthorityInfoBean d = new AuthorityInfoBean();

    private a() {
        try {
            if (com.mbridge.msdk.foundation.a.a.a.a().b(MBridgeConstans.AUTHORITY_GENERAL_DATA).equals("")) {
                this.d.b(1);
            }
            if (com.mbridge.msdk.foundation.a.a.a.a().b(MBridgeConstans.AUTHORITY_DEVICE_ID).equals("")) {
                this.d.c(1);
            }
            if (com.mbridge.msdk.foundation.a.a.a.a().b(MBridgeConstans.AUTHORITY_SERIAL_ID).equals("")) {
                this.d.d(1);
            }
            if (com.mbridge.msdk.foundation.a.a.a.a().b("authority_applist").equals("")) {
                this.d.f(1);
            }
            if (com.mbridge.msdk.foundation.a.a.a.a().b("authority_app_download").equals("")) {
                this.d.g(1);
            }
            if (com.mbridge.msdk.foundation.a.a.a.a().b(com.mbridge.msdk.a.AUTHORITY_OA_ID).equals("")) {
                this.d.e(1);
            }
            if (com.mbridge.msdk.foundation.a.a.a.a().b(MBridgeConstans.AUTHORITY_OTHER).equals("")) {
                this.d.h(1);
            }
            this.a.add(MBridgeConstans.AUTHORITY_GENERAL_DATA);
            this.a.add(MBridgeConstans.AUTHORITY_DEVICE_ID);
            this.a.add("authority_applist");
            this.a.add("authority_app_download");
            this.a.add(MBridgeConstans.AUTHORITY_SERIAL_ID);
            this.a.add(com.mbridge.msdk.a.AUTHORITY_OA_ID);
            this.a.add(MBridgeConstans.AUTHORITY_OTHER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static a a() {
        if (c == null) {
            synchronized (a.class) {
                if (c == null) {
                    c = new a();
                }
            }
        }
        return c;
    }

    public final void a(String str, int i) {
        if (this.d != null) {
            if (str.equals(MBridgeConstans.AUTHORITY_GENERAL_DATA)) {
                this.d.b(i);
                return;
            }
            if (str.equals(MBridgeConstans.AUTHORITY_DEVICE_ID)) {
                this.d.c(i);
                return;
            }
            if (str.equals("authority_applist")) {
                this.d.f(i);
                return;
            }
            if (str.equals("authority_app_download")) {
                this.d.g(i);
                return;
            }
            if (str.equals(MBridgeConstans.AUTHORITY_ALL_INFO)) {
                this.d.a(i);
                return;
            }
            if (str.equals(MBridgeConstans.AUTHORITY_SERIAL_ID)) {
                this.d.d(i);
            } else if (str.equals(com.mbridge.msdk.a.AUTHORITY_OA_ID)) {
                this.d.e(i);
            } else if (str.equals(MBridgeConstans.AUTHORITY_OTHER)) {
                this.d.h(i);
            }
        }
    }

    public final AuthorityInfoBean b() {
        if (this.d != null) {
            return this.d;
        }
        return new AuthorityInfoBean().a(1);
    }

    private int c(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                String strB = com.mbridge.msdk.foundation.a.a.a.a().b(str);
                if (TextUtils.isEmpty(strB)) {
                    return 0;
                }
                return Integer.parseInt(strB);
            }
        } catch (Exception e) {
            s.d("SDKAuthorityController", e.getMessage());
        }
        return 0;
    }

    private int d(String str) {
        com.mbridge.msdk.b.a aVarB = b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
        if (aVarB == null) {
            aVarB = b.a().b();
        }
        if (str.equals(MBridgeConstans.AUTHORITY_GENERAL_DATA)) {
            return aVarB.i();
        }
        if (str.equals(MBridgeConstans.AUTHORITY_DEVICE_ID)) {
            return aVarB.j();
        }
        if (str.equals("authority_applist")) {
            return aVarB.p();
        }
        if (str.equals("authority_app_download")) {
            return aVarB.u();
        }
        if (str.equals(MBridgeConstans.AUTHORITY_SERIAL_ID)) {
            return aVarB.h();
        }
        if (str.equals(com.mbridge.msdk.a.AUTHORITY_OA_ID)) {
            return aVarB.j();
        }
        return -1;
    }

    public final boolean a(String str) {
        boolean z;
        com.mbridge.msdk.b.a aVarB = b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
        if (aVarB == null) {
            aVarB = b.a().b();
            z = true;
        } else {
            z = false;
        }
        int iK = aVarB.k();
        boolean z2 = iK != 0 ? iK == 1 && d(str) == 1 : c(str) == 1 && d(str) == 1;
        if (str.equals("authority_applist") || str.equals("authority_app_download")) {
            z2 = d(str) != 0;
        }
        if (str.equals(MBridgeConstans.AUTHORITY_OTHER)) {
            z2 = c(str) == 1;
        }
        if (str.equals(MBridgeConstans.AUTHORITY_DEVICE_ID) && a().e == 2) {
            z2 = (aVarB.au() || z || c(str) != 1) ? false : true;
        }
        return (str.equals("authority_imei_mac") || str.equals("authority_imsi_id") || str.equals("authority_android_id")) ? !aVarB.au() : z2;
    }

    public final String c() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            int i = 1;
            if (this.e != 1 && this.e != 3) {
                i = 0;
            }
            jSONObject.put(MBridgeConstans.AUTHORITY_CONSENTSTATUS, String.valueOf(i));
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    public final void b(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            String strOptString = new JSONObject(str).optString(MBridgeConstans.AUTHORITY_CONSENTSTATUS);
            boolean z = true;
            if (!TextUtils.isEmpty(strOptString)) {
                this.e = Integer.parseInt(strOptString) == 1 ? 1 : 2;
            }
            if (this.b != null) {
                CallBackForDeveloper callBackForDeveloper = this.b;
                if (this.e != 1) {
                    z = false;
                }
                callBackForDeveloper.onAuthorityCallback(z);
            }
        } catch (Exception e) {
            s.d("SDKAuthorityController", e.getMessage());
        }
    }

    public final String d() throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < this.a.size(); i++) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("keyname", String.valueOf(this.a.get(i)));
                jSONObject.put("client_status", c(this.a.get(i)));
                jSONObject.put("server_status", d(this.a.get(i)));
                jSONArray.put(jSONObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONArray.toString();
    }

    public final void a(int i) {
        if (this.d != null) {
            this.e = i != 1 ? 2 : 1;
        }
    }

    public final boolean e() {
        return this.e == 1 || this.e == 3;
    }

    public final void b(int i) {
        com.mbridge.msdk.foundation.a.a.a.a().a(MBridgeConstans.AUTHORITY_DNT, i);
    }

    public final int f() {
        return this.e;
    }
}
