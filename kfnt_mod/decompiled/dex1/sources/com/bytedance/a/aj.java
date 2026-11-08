package com.bytedance.a;

import android.text.TextUtils;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class aj extends bd {

    /* renamed from: a, reason: collision with root package name */
    @org.b.a.e
    public String f3352a;

    /* renamed from: b, reason: collision with root package name */
    @org.b.a.e
    public String f3353b;

    /* renamed from: c, reason: collision with root package name */
    @org.b.a.e
    public String f3354c;

    @org.b.a.e
    public String d;

    @org.b.a.e
    public String e;

    @org.b.a.e
    public Integer f;

    @org.b.a.e
    public String g;

    @org.b.a.e
    public String h;

    @org.b.a.e
    public String i;

    @org.b.a.e
    public String j;

    @org.b.a.e
    public String k;

    @org.b.a.e
    public String l;

    @Override // com.bytedance.a.bd
    @org.b.a.d
    public JSONObject a() {
        return new JSONObject();
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0064  */
    @Override // com.bytedance.a.bd
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(@org.b.a.d org.json.JSONObject r2) {
        /*
            r1 = this;
            java.lang.String r0 = "json"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0)
            java.lang.String r0 = r1.e
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L15
            java.lang.String r0 = "tr_token"
            java.lang.String r0 = r2.optString(r0)
            r1.e = r0
        L15:
            java.lang.String r0 = r1.f3352a
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L25
            java.lang.String r0 = "aid"
            java.lang.String r0 = r2.optString(r0)
            r1.f3352a = r0
        L25:
            java.lang.String r0 = r1.f3353b
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L35
            java.lang.String r0 = "bd_did"
            java.lang.String r0 = r2.optString(r0)
            r1.f3353b = r0
        L35:
            java.lang.String r0 = r1.f3354c
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L45
            java.lang.String r0 = "ssid"
            java.lang.String r0 = r2.optString(r0)
            r1.f3354c = r0
        L45:
            java.lang.String r0 = r1.d
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L55
            java.lang.String r0 = "user_unique_id"
            java.lang.String r0 = r2.optString(r0)
            r1.d = r0
        L55:
            java.lang.Integer r0 = r1.f
            if (r0 == 0) goto L64
            if (r0 != 0) goto L5e
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L5e:
            int r0 = r0.intValue()
            if (r0 > 0) goto L70
        L64:
            java.lang.String r0 = "click_time"
            int r0 = r2.optInt(r0)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1.f = r0
        L70:
            java.lang.String r0 = r1.g
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L80
            java.lang.String r0 = "tr_shareuser"
            java.lang.String r0 = r2.optString(r0)
            r1.g = r0
        L80:
            java.lang.String r0 = r1.h
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L90
            java.lang.String r0 = "tr_admaster"
            java.lang.String r0 = r2.optString(r0)
            r1.h = r0
        L90:
            java.lang.String r0 = r1.i
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto La0
            java.lang.String r0 = "tr_param1"
            java.lang.String r0 = r2.optString(r0)
            r1.i = r0
        La0:
            java.lang.String r0 = r1.j
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto Lb0
            java.lang.String r0 = "tr_param2"
            java.lang.String r0 = r2.optString(r0)
            r1.j = r0
        Lb0:
            java.lang.String r0 = r1.k
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto Lc0
            java.lang.String r0 = "tr_param3"
            java.lang.String r0 = r2.optString(r0)
            r1.k = r0
        Lc0:
            java.lang.String r0 = r1.l
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto Ld0
            java.lang.String r0 = "tr_param4"
            java.lang.String r2 = r2.optString(r0)
            r1.l = r2
        Ld0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.a.aj.a(org.json.JSONObject):void");
    }

    @org.b.a.d
    public String toString() {
        StringBuilder sbA = a.a("aid=");
        sbA.append(this.f3352a);
        sbA.append("&bd_did=");
        sbA.append(this.f3353b);
        sbA.append("&ssid=");
        sbA.append(this.f3354c);
        sbA.append("&user_unique_id=");
        sbA.append(this.d);
        String string = sbA.toString();
        if (!TextUtils.isEmpty(this.e)) {
            string = string + "&token=" + this.e;
        }
        Integer num = this.f;
        if (num != null && num.intValue() > 0) {
            string = string + "&click_time=" + this.f;
        }
        if (!TextUtils.isEmpty(this.g)) {
            string = string + "&tr_shareuser=" + this.g;
        }
        if (!TextUtils.isEmpty(this.h)) {
            string = string + "&tr_admaster=" + this.h;
        }
        if (!TextUtils.isEmpty(this.i)) {
            string = string + "&tr_param1=" + this.i;
        }
        if (!TextUtils.isEmpty(this.j)) {
            string = string + "&tr_param2=" + this.j;
        }
        if (!TextUtils.isEmpty(this.k)) {
            string = string + "&tr_param3=" + this.k;
        }
        if (TextUtils.isEmpty(this.l)) {
            return string;
        }
        return string + "&tr_param4=" + this.l;
    }
}
