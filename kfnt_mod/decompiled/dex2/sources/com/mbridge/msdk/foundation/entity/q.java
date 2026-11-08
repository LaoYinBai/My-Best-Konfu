package com.mbridge.msdk.foundation.entity;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.w;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;

/* compiled from: VideoReportData.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class q {
    public static int a = 1;
    public static int b;
    private String A;
    private String B;
    private String C;
    private String D;
    private String E;
    private String F;
    private String G;
    private String c;
    private String d;
    private String e;
    private String f;
    private int g;
    private String h;
    private int i;
    private int j;
    private int k;
    private String l;
    private String m;
    private int n;
    private int o;
    private String p;
    private long q;
    private String s;
    private String t;
    private String u;
    private String v;
    private String w;
    private String x;
    private String y;
    private String z;
    private int r = 0;
    private int H = 0;

    public final String a() {
        return this.c;
    }

    public final void a(String str) {
        this.c = str;
    }

    public final String b() {
        return this.d;
    }

    public final void b(String str) {
        this.d = str;
    }

    public final String c() {
        return this.e;
    }

    public final void c(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.e = URLEncoder.encode(str);
    }

    public final String d() {
        return this.F;
    }

    public final void d(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            this.F = URLEncoder.encode(str, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final String e() {
        return this.E;
    }

    public final void e(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            this.E = URLEncoder.encode(str, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final String f() {
        return this.D;
    }

    public final void f(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            this.D = URLEncoder.encode(str, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final String g() {
        return this.C;
    }

    public final void g(String str) {
        this.C = str;
    }

    public final String h() {
        return this.G;
    }

    public final void h(String str) {
        this.G = str;
    }

    public final String i() {
        return this.w;
    }

    public final void i(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            this.w = URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public final void j(String str) {
        this.y = str;
    }

    public final void a(int i) {
        this.H = i;
    }

    public q(String str, int i, int i2, int i3, int i4, String str2, String str3, int i5, String str4, int i6, String str5) {
        this.f = str;
        this.g = i;
        this.h = str5;
        this.i = i2;
        this.j = i3;
        this.k = i4;
        try {
            if (!TextUtils.isEmpty(str2)) {
                this.l = URLEncoder.encode(str2, "utf-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.m = str3;
        this.o = i5;
        this.p = str4;
        this.q = i6;
    }

    public q() {
    }

    public q(String str, int i, String str2, String str3, String str4) {
        this.f = str;
        this.h = str4;
        this.g = i;
        if (!TextUtils.isEmpty(str2)) {
            try {
                this.l = URLEncoder.encode(str2, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        this.m = str3;
    }

    public q(String str, int i, int i2, String str2, int i3, String str3, int i4, String str4) {
        this.f = str;
        this.g = i;
        this.h = str4;
        this.k = i2;
        if (!TextUtils.isEmpty(str2)) {
            try {
                this.l = URLEncoder.encode(str2, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        this.o = i3;
        this.p = str3;
        this.q = i4;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public q(android.content.Context r2, com.mbridge.msdk.foundation.entity.CampaignEx r3, int r4, java.lang.String r5, long r6, int r8) {
        /*
            r1 = this;
            r1.<init>()
            r0 = 0
            r1.r = r0
            r1.H = r0
            r0 = 1
            if (r8 == r0) goto L1d
            r0 = 287(0x11f, float:4.02E-43)
            if (r8 == r0) goto L18
            switch(r8) {
                case 94: goto L18;
                case 95: goto L13;
                default: goto L12;
            }
        L12:
            goto L21
        L13:
            java.lang.String r8 = "2000025"
            r1.f = r8
            goto L21
        L18:
            java.lang.String r8 = "2000022"
            r1.f = r8
            goto L21
        L1d:
            java.lang.String r8 = "2000022"
            r1.f = r8
        L21:
            int r8 = com.mbridge.msdk.foundation.tools.o.C(r2)
            r1.g = r8
            int r8 = r1.g
            java.lang.String r2 = com.mbridge.msdk.foundation.tools.o.a(r2, r8)
            r1.h = r2
            int r2 = r3.getVideoLength()
            r1.k = r2
            java.lang.String r2 = r3.getRequestId()
            r1.s = r2
            java.lang.String r2 = r3.getRequestIdNotice()
            r1.t = r2
            java.lang.String r2 = r1.l
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto L65
            java.lang.String r2 = r3.getNoticeUrl()     // Catch: java.io.UnsupportedEncodingException -> L61
            if (r2 != 0) goto L54
            java.lang.String r2 = r3.getClickURL()     // Catch: java.io.UnsupportedEncodingException -> L61
            goto L58
        L54:
            java.lang.String r2 = r3.getNoticeUrl()     // Catch: java.io.UnsupportedEncodingException -> L61
        L58:
            java.lang.String r8 = "utf-8"
            java.lang.String r2 = java.net.URLEncoder.encode(r2, r8)     // Catch: java.io.UnsupportedEncodingException -> L61
            r1.l = r2     // Catch: java.io.UnsupportedEncodingException -> L61
            goto L65
        L61:
            r2 = move-exception
            r2.printStackTrace()
        L65:
            r1.o = r4
            r1.p = r5
            r4 = 0
            int r2 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r2 != 0) goto L74
            int r2 = r3.getVideoSize()
            long r6 = (long) r2
        L74:
            r1.q = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.entity.q.<init>(android.content.Context, com.mbridge.msdk.foundation.entity.CampaignEx, int, java.lang.String, long, int):void");
    }

    public q(String str, String str2, String str3, String str4, String str5, String str6, int i, String str7) {
        this.f = str;
        this.z = str2;
        this.x = str3;
        this.A = str4;
        this.u = str5;
        this.v = str6;
        this.g = i;
        this.h = str7;
    }

    public q(String str) {
        this.B = str;
    }

    public q(String str, int i, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.f = str;
        this.o = i;
        this.p = str2;
        try {
            if (!TextUtils.isEmpty(str3)) {
                this.D = URLEncoder.encode(str3, "utf-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.v = str4;
        this.u = str5;
        this.m = str6;
        this.C = str7;
        if (Integer.valueOf(str2).intValue() > com.mbridge.msdk.foundation.same.a.u) {
            this.o = 2;
        }
    }

    public q(String str, String str2, String str3, String str4, String str5, int i) {
        this.f = str;
        this.v = str2;
        this.s = str3;
        this.t = str4;
        this.u = str5;
        this.g = i;
    }

    public q(String str, String str2, String str3, String str4, String str5, int i, int i2, String str6) {
        this.f = str;
        this.v = str2;
        this.s = str3;
        this.t = str4;
        this.u = str5;
        this.g = i;
        this.m = str6;
        this.n = i2;
    }

    public final String j() {
        return this.s;
    }

    public final void k(String str) {
        this.s = str;
    }

    public final String k() {
        return this.t;
    }

    public final void l(String str) {
        this.t = str;
    }

    public final String l() {
        return this.u;
    }

    public final void m(String str) {
        this.u = str;
    }

    public final String m() {
        return this.v;
    }

    public final void n(String str) {
        this.v = str;
    }

    public final String n() {
        return this.f;
    }

    public final void o(String str) {
        this.f = str;
    }

    public final int o() {
        return this.j;
    }

    public final int p() {
        return this.k;
    }

    public final String q() {
        return this.l;
    }

    public final String r() {
        return this.m;
    }

    public final void p(String str) {
        this.m = str;
    }

    public final String s() {
        return this.p;
    }

    public final void q(String str) {
        this.p = str;
    }

    public final long t() {
        return this.q;
    }

    public final int u() {
        return this.g;
    }

    public final void b(int i) {
        this.g = i;
    }

    public final String v() {
        return this.h;
    }

    public final void r(String str) {
        this.h = str;
    }

    public final int w() {
        return this.i;
    }

    public final int x() {
        return this.o;
    }

    public final void c(int i) {
        this.o = i;
    }

    public static String a(List<q> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (q qVar : list) {
            stringBuffer.append("key=" + qVar.f + ContainerUtils.FIELD_DELIMITER);
            stringBuffer.append("resource_type=" + qVar.c + ContainerUtils.FIELD_DELIMITER);
            stringBuffer.append("unit_id=" + qVar.u + ContainerUtils.FIELD_DELIMITER);
            StringBuilder sb = new StringBuilder();
            sb.append("ad_type=");
            sb.append(qVar.G);
            stringBuffer.append(sb.toString());
            stringBuffer.append("cid=" + qVar.v + ContainerUtils.FIELD_DELIMITER);
            stringBuffer.append("rid=" + qVar.s + ContainerUtils.FIELD_DELIMITER);
            stringBuffer.append("rid_n=" + qVar.t + ContainerUtils.FIELD_DELIMITER);
            stringBuffer.append("reason=" + qVar.m + ContainerUtils.FIELD_DELIMITER);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("creative=");
            sb2.append(qVar.e);
            stringBuffer.append(sb2.toString());
            if (com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_DEVICE_ID)) {
                stringBuffer.append("devid=" + qVar.d + ContainerUtils.FIELD_DELIMITER);
            }
            if (com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA)) {
                stringBuffer.append("network_type=" + qVar.g + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("\n");
            } else {
                stringBuffer.append("\n");
            }
        }
        return stringBuffer.toString();
    }

    public static String b(List<q> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (q qVar : list) {
            if (com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA)) {
                stringBuffer.append("key=" + qVar.f + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("network_type=" + qVar.g + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("network_str=" + qVar.h + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("reason=" + qVar.m + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("cid=" + qVar.v + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("video_url=" + qVar.E + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("rid=" + qVar.s + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("rid_n=" + qVar.t + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("unit_id=" + qVar.u + ContainerUtils.FIELD_DELIMITER);
                StringBuilder sb = new StringBuilder();
                sb.append("offer_url=");
                sb.append(qVar.l);
                stringBuffer.append(sb.toString());
                stringBuffer.append("\n");
            } else {
                stringBuffer.append("key=" + qVar.f + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("reason=" + qVar.m + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("cid=" + qVar.v + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("video_url=" + qVar.E + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("rid=" + qVar.s + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("rid_n=" + qVar.t + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("unit_id=" + qVar.u + ContainerUtils.FIELD_DELIMITER);
                StringBuilder sb2 = new StringBuilder();
                sb2.append("offer_url=");
                sb2.append(qVar.l);
                stringBuffer.append(sb2.toString());
                stringBuffer.append("\n");
            }
        }
        return stringBuffer.toString();
    }

    public static String c(List<q> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (q qVar : list) {
            if (com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA)) {
                stringBuffer.append("key=" + qVar.f + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("network_type=" + qVar.g + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("network_str=" + qVar.h + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("result=" + qVar.o + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("duration=" + qVar.p + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("video_size=" + qVar.q + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("video_length=" + qVar.k + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("reason=" + qVar.m + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("cid=" + qVar.v + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("video_url=" + qVar.E + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("rid=" + qVar.s + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("rid_n=" + qVar.t + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("unit_id=" + qVar.u + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("offer_url=" + qVar.l + ContainerUtils.FIELD_DELIMITER);
            } else {
                stringBuffer.append("key=" + qVar.f + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("result=" + qVar.o + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("duration=" + qVar.p + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("video_size=" + qVar.q + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("video_length=" + qVar.k + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("reason=" + qVar.m + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("cid=" + qVar.v + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("video_url=" + qVar.E + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("rid=" + qVar.s + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("rid_n=" + qVar.t + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("unit_id=" + qVar.u + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("offer_url=" + qVar.l + ContainerUtils.FIELD_DELIMITER);
            }
            if (qVar.c != null) {
                stringBuffer.append("resource_type=" + qVar.c + ContainerUtils.FIELD_DELIMITER);
            }
            if (qVar.e != null) {
                stringBuffer.append("creative=" + qVar.e + ContainerUtils.FIELD_DELIMITER);
            }
            stringBuffer.append("\n");
        }
        return stringBuffer.toString();
    }

    public static String a(q qVar) {
        if (qVar == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("key=" + qVar.f + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("cid=" + qVar.v + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("unit_id=" + qVar.u + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("network_type=" + qVar.g + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("rid=" + qVar.s + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("rid_n=" + qVar.t + ContainerUtils.FIELD_DELIMITER);
        StringBuilder sb = new StringBuilder();
        sb.append("reason=");
        sb.append(qVar.m);
        stringBuffer.append(sb.toString());
        stringBuffer.append("\n");
        return stringBuffer.toString();
    }

    public static String b(q qVar) {
        if (qVar == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("key=" + qVar.f + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("cid=" + qVar.v + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("unit_id=" + qVar.u + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("network_type=" + qVar.g + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("mraid_type=" + qVar.H + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("rid_n=" + qVar.t + ContainerUtils.FIELD_DELIMITER);
        StringBuilder sb = new StringBuilder();
        sb.append("rid=");
        sb.append(qVar.s);
        stringBuffer.append(sb.toString());
        stringBuffer.append("\n");
        return stringBuffer.toString();
    }

    public static String c(q qVar) {
        if (qVar == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("key=" + qVar.f + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("result=" + qVar.o + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("duration=" + qVar.p + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("cid=" + qVar.v + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("reason=" + qVar.m + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("ad_type=" + qVar.G + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("unit_id=" + qVar.u + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("devid=" + qVar.d + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("mraid_type=" + qVar.H + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("network_type=" + qVar.g + ContainerUtils.FIELD_DELIMITER);
        if (!TextUtils.isEmpty(qVar.D)) {
            stringBuffer.append("endcard_url=" + qVar.D + ContainerUtils.FIELD_DELIMITER);
        }
        if (!TextUtils.isEmpty(qVar.C)) {
            stringBuffer.append("type=" + qVar.C + ContainerUtils.FIELD_DELIMITER);
        }
        if (qVar.c != null) {
            stringBuffer.append("resource_type=" + qVar.c + ContainerUtils.FIELD_DELIMITER);
        }
        stringBuffer.append("rid_n=" + qVar.t + ContainerUtils.FIELD_DELIMITER);
        StringBuilder sb = new StringBuilder();
        sb.append("rid=");
        sb.append(qVar.s);
        stringBuffer.append(sb.toString());
        stringBuffer.append("\n");
        return stringBuffer.toString();
    }

    public static String d(q qVar) {
        if (qVar == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("key=" + qVar.f + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("result=" + qVar.o + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("duration=" + qVar.p + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("cid=" + qVar.v + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("unit_id=" + qVar.u + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("reason=" + qVar.m + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("ad_type=" + qVar.G + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("rid=" + qVar.s + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("rid_n=" + qVar.t + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("network_type=" + qVar.g + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("mraid_type=" + qVar.H + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("devid=" + qVar.d + ContainerUtils.FIELD_DELIMITER);
        if (qVar.c != null) {
            stringBuffer.append("resource_type=" + qVar.c + ContainerUtils.FIELD_DELIMITER);
        }
        if (!TextUtils.isEmpty(qVar.D)) {
            stringBuffer.append("endcard_url=" + qVar.D + ContainerUtils.FIELD_DELIMITER);
        }
        stringBuffer.append("type=" + qVar.C);
        return stringBuffer.toString();
    }

    public static String d(List<q> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (q qVar : list) {
            stringBuffer.append("key=" + qVar.f + ContainerUtils.FIELD_DELIMITER);
            stringBuffer.append("result=" + qVar.o + ContainerUtils.FIELD_DELIMITER);
            stringBuffer.append("duration=" + qVar.p + ContainerUtils.FIELD_DELIMITER);
            stringBuffer.append("endcard_url=" + qVar.D + ContainerUtils.FIELD_DELIMITER);
            stringBuffer.append("cid=" + qVar.v + ContainerUtils.FIELD_DELIMITER);
            stringBuffer.append("unit_id=" + qVar.u + ContainerUtils.FIELD_DELIMITER);
            stringBuffer.append("reason=" + qVar.m + ContainerUtils.FIELD_DELIMITER);
            stringBuffer.append("ad_type=" + qVar.G + ContainerUtils.FIELD_DELIMITER);
            stringBuffer.append("rid=" + qVar.s + ContainerUtils.FIELD_DELIMITER);
            stringBuffer.append("rid_n=" + qVar.t + ContainerUtils.FIELD_DELIMITER);
            StringBuilder sb = new StringBuilder();
            sb.append("type=");
            sb.append(qVar.C);
            stringBuffer.append(sb.toString());
            stringBuffer.append("\n");
        }
        return stringBuffer.toString();
    }

    public static String e(List<q> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (q qVar : list) {
            if (com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA)) {
                stringBuffer.append("key=" + qVar.f + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("network_type=" + qVar.g + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("result=" + qVar.o + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("cid=" + qVar.v + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("template_url=" + qVar.w + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("reason=" + qVar.m + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("rid=" + qVar.s + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("rid_n=" + qVar.t + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("unit_id=" + qVar.u + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("\n");
            } else {
                stringBuffer.append("key=" + qVar.f + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("result=" + qVar.o + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("cid=" + qVar.v + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("template_url=" + qVar.w + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("reason=" + qVar.m + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("rid=" + qVar.s + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("rid_n=" + qVar.t + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("unit_id=" + qVar.u + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("\n");
            }
        }
        return stringBuffer.toString();
    }

    public static String f(List<q> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (q qVar : list) {
            if (com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA)) {
                stringBuffer.append("key=" + qVar.f + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("network_type=" + qVar.g + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("cid=" + qVar.v + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("image_url=" + qVar.F + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("reason=" + qVar.m + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("rid=" + qVar.s + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("rid_n=" + qVar.t + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("unit_id=" + qVar.u + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("\n");
            } else {
                stringBuffer.append("key=" + qVar.f + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("cid=" + qVar.v + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("image_url=" + qVar.F + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("reason=" + qVar.m + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("rid=" + qVar.s + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("rid_n=" + qVar.t + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("unit_id=" + qVar.u + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("\n");
            }
        }
        return stringBuffer.toString();
    }

    public static String e(q qVar) {
        if (qVar == null) {
            return null;
        }
        try {
            StringBuffer stringBuffer = new StringBuffer();
            if (com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA)) {
                stringBuffer.append("key=" + qVar.f + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("error=" + w.c(qVar.y) + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("template_url=" + w.c(qVar.w) + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("unit_id=" + w.c(qVar.u) + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("cid=" + w.c(qVar.v) + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("network_str=" + qVar.h + ContainerUtils.FIELD_DELIMITER);
                StringBuilder sb = new StringBuilder();
                sb.append("network_type=");
                sb.append(qVar.g);
                stringBuffer.append(sb.toString());
            } else {
                stringBuffer.append("key=" + qVar.f + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("error=" + w.c(qVar.y) + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("template_url=" + w.c(qVar.w) + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("unit_id=" + w.c(qVar.u) + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("cid=" + w.c(qVar.v) + ContainerUtils.FIELD_DELIMITER);
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String f(q qVar) {
        if (qVar == null) {
            return null;
        }
        try {
            StringBuffer stringBuffer = new StringBuffer();
            if (com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA)) {
                stringBuffer.append("key=" + qVar.f + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("event=" + w.c(qVar.z) + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("template=" + w.c(qVar.x) + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("layout=" + w.c(qVar.A) + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("unit_id=" + w.c(qVar.u) + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("cid=" + w.c(qVar.v) + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("network_str=" + qVar.h + ContainerUtils.FIELD_DELIMITER);
                StringBuilder sb = new StringBuilder();
                sb.append("network_type=");
                sb.append(qVar.g);
                stringBuffer.append(sb.toString());
            } else {
                stringBuffer.append("key=" + qVar.f + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("event=" + w.c(qVar.z) + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("template=" + w.c(qVar.x) + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("layout=" + w.c(qVar.A) + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("unit_id=" + w.c(qVar.u) + ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append("cid=" + w.c(qVar.v) + ContainerUtils.FIELD_DELIMITER);
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String g(List<q> list) {
        if (list == null) {
            return null;
        }
        try {
            if (list.size() <= 0) {
                return null;
            }
            StringBuffer stringBuffer = new StringBuffer();
            Iterator<q> it = list.iterator();
            while (it.hasNext()) {
                stringBuffer.append(it.next().B);
                stringBuffer.append("\n");
            }
            return stringBuffer.toString();
        } catch (Throwable th) {
            s.b("VideoReportData", th.getMessage(), th);
            return null;
        }
    }

    public static String g(q qVar) {
        if (qVar == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("key=" + qVar.f + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("cid=" + qVar.v + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("rid=" + qVar.s + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("rid_n=" + qVar.t + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("unit_id=" + qVar.u + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("network_type=" + qVar.g + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("mraid_type=" + qVar.H + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("platform=1");
        return stringBuffer.toString();
    }

    public static String h(q qVar) {
        if (qVar == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("key=" + qVar.f + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("cid=" + qVar.v + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("rid=" + qVar.s + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("rid_n=" + qVar.t + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("unit_id=" + qVar.u + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("reason=" + qVar.m + ContainerUtils.FIELD_DELIMITER);
        stringBuffer.append("case=" + qVar.n + ContainerUtils.FIELD_DELIMITER);
        StringBuilder sb = new StringBuilder();
        sb.append("network_type=");
        sb.append(qVar.g);
        stringBuffer.append(sb.toString());
        return stringBuffer.toString();
    }

    public final String toString() {
        return "RewardReportData [key=" + this.f + ", networkType=" + this.g + ", isCompleteView=" + this.i + ", watchedMillis=" + this.j + ", videoLength=" + this.k + ", offerUrl=" + this.l + ", reason=" + this.m + ", result=" + this.o + ", duration=" + this.p + ", videoSize=" + this.q + "]";
    }
}
