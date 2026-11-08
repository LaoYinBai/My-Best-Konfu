package com.mbridge.msdk.foundation.same.net.g;

import android.text.TextUtils;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.same.net.m;
import com.mbridge.msdk.foundation.tools.s;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Pattern;

/* compiled from: RequestUrlUtil.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class d {
    private String A;
    private String B;
    private String C;
    private String D;
    private String E;
    private String F;
    private String G;
    private String H;
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public String k;
    public String l;
    public String m;
    public String n;
    public String o;
    public String p;
    public String q;
    public int r;
    public boolean s;
    public ArrayList<String> t;
    private final String u;
    private boolean v;
    private String w;
    private String x;
    private String y;
    private String z;

    public final boolean a() {
        try {
            if (this.t != null && this.r <= this.t.size() - 1) {
                this.c = this.t.get(this.r);
                e();
                return true;
            }
            if (this.s) {
                this.r = 0;
            }
            return false;
        } catch (Throwable th) {
            s.a("RequestUrlUtil", th.getMessage());
            return false;
        }
    }

    /* compiled from: RequestUrlUtil.java */
    private static class a {
        private static final d a = new d();
    }

    public final boolean b() {
        return this.v;
    }

    private d() {
        this.u = "RequestUrlUtil";
        this.v = true;
        this.w = "https://{}hb.rayjump.com";
        this.a = "https://analytics.rayjump.com";
        this.b = "https://net.rayjump.com";
        this.c = "https://configure.rayjump.com";
        this.x = "/bid";
        this.y = "/load";
        this.z = "/openapi/ad/v3";
        this.A = "/openapi/ad/v4";
        this.B = "/openapi/ad/v5";
        this.C = "/image";
        this.D = "/mapping";
        this.E = "/setting";
        this.F = "/sdk/customid";
        this.G = "/rewardsetting";
        this.H = "/appwall/setting";
        this.d = this.w + this.x;
        this.e = this.w + this.y;
        this.f = this.b + this.z;
        this.g = this.b + this.A;
        this.h = this.b + this.B;
        this.i = this.b + this.C;
        this.j = this.c + this.E;
        this.k = this.c + this.F;
        this.l = this.c + this.G;
        this.m = this.c + this.D;
        this.n = this.c + this.H;
        this.o = "https://detect.rayjump.com/mapi/find";
        this.p = "https://detect.rayjump.com/mapi/result";
        this.q = "https://d1tru86qrby720.cloudfront.net/hostsetting";
        this.r = 0;
        this.s = false;
        this.t = new ArrayList<>(Arrays.asList("https://configure.rayjump.com", "https://policy.rayjump.com", "https://lazy.rayjump.com", "https://check.rayjump.com"));
    }

    public static d c() {
        return a.a;
    }

    public final String a(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                String[] strArrSplit = str.split("_");
                if (strArrSplit.length > 1) {
                    return a(true, strArrSplit[1]);
                }
            }
        } catch (Exception e) {
            s.d("RequestUrlUtil", e.getMessage());
        }
        return TextUtils.isEmpty(str) ? a.a.v ? this.h : this.f : a(true, "");
    }

    public final String a(boolean z, String str) {
        if (z) {
            if (this.e.contains("{}") && !TextUtils.isEmpty(str)) {
                return this.e.replace("{}", str + "-");
            }
            return this.e.replace("{}", "");
        }
        return this.d.replace("{}", "");
    }

    public final void d() {
        HashMap<String, String> mapAx;
        com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
        if (aVarB != null) {
            this.v = !aVarB.d(2);
            if (aVarB.ax() == null || aVarB.ax().size() <= 0 || (mapAx = aVarB.ax()) == null || mapAx.size() <= 0) {
                return;
            }
            if (mapAx.containsKey(NotifyType.VIBRATE) && !TextUtils.isEmpty(mapAx.get(NotifyType.VIBRATE)) && b(mapAx.get(NotifyType.VIBRATE))) {
                this.b = mapAx.get(NotifyType.VIBRATE);
                this.f = this.b + this.z;
                this.g = this.b + this.A;
                this.h = this.b + this.B;
                this.i = this.b + this.C;
            }
            if (mapAx.containsKey(CampaignEx.JSON_KEY_HB) && !TextUtils.isEmpty(mapAx.get(CampaignEx.JSON_KEY_HB)) && b(mapAx.get(CampaignEx.JSON_KEY_HB))) {
                this.w = mapAx.get(CampaignEx.JSON_KEY_HB);
                this.d = this.w + this.x;
                this.e = this.w + this.y;
            }
            if (mapAx.containsKey("lg") && !TextUtils.isEmpty(mapAx.get("lg"))) {
                String str = mapAx.get("lg");
                if (b(str)) {
                    this.a = str;
                } else if (!TextUtils.isEmpty(str)) {
                    m.a().a(str);
                }
            }
            if (mapAx.containsKey("dr") && !TextUtils.isEmpty(mapAx.get("dr")) && b(mapAx.get("dr"))) {
                this.p = mapAx.get("dr");
            }
            if (mapAx.containsKey("df") && !TextUtils.isEmpty(mapAx.get("df")) && b(mapAx.get("df"))) {
                this.o = mapAx.get("df");
            }
        }
    }

    private boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return Pattern.compile("(https|http)://[-A-Za-z0-9{}+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]".trim()).matcher(str.trim()).matches();
    }

    public final void e() {
        this.j = this.c + this.E;
        this.k = this.c + this.F;
        this.l = this.c + this.G;
        this.m = this.c + this.D;
        this.n = this.c + this.H;
    }
}
