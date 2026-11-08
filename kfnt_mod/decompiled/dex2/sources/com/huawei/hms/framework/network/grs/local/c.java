package com.huawei.hms.framework.network.grs.local;

import android.content.Context;
import com.huawei.hms.framework.common.Logger;
import com.mbridge.msdk.playercommon.exoplayer2.util.MimeTypes;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c extends a {
    public c(Context context) {
        if (a("grs_sdk_global_route_config.json", context) == 0) {
            this.d = true;
        }
    }

    @Override // com.huawei.hms.framework.network.grs.local.a
    public int a(String str) throws JSONException {
        this.a = new com.huawei.hms.framework.network.grs.local.model.a();
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONObject(MimeTypes.BASE_TYPE_APPLICATION);
            String string = jSONObject.getString("name");
            long j = jSONObject.getLong("cacheControl");
            JSONArray jSONArray = jSONObject.getJSONArray("services");
            this.a.a(string);
            this.a.a(j);
            ArrayList arrayList = new ArrayList(16);
            if (jSONArray != null && jSONArray.length() != 0) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add((String) jSONArray.get(i));
                }
                return 0;
            }
            return -1;
        } catch (JSONException e) {
            Logger.w("LocalManagerV1", "parse appbean failed maybe json style is wrong.", e);
            return -1;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0076 A[Catch: JSONException -> 0x008f, LOOP:1: B:23:0x0070->B:25:0x0076, LOOP_END, TryCatch #0 {JSONException -> 0x008f, blocks: (B:6:0x000a, B:7:0x0015, B:9:0x001b, B:12:0x004a, B:17:0x0061, B:19:0x0068, B:23:0x0070, B:25:0x0076, B:26:0x0082, B:27:0x0089, B:13:0x004f, B:16:0x005a), top: B:34:0x000a }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.List<com.huawei.hms.framework.network.grs.local.model.b> a(org.json.JSONArray r8, org.json.JSONObject r9) throws org.json.JSONException {
        /*
            r7 = this;
            if (r9 == 0) goto L9d
            int r8 = r9.length()
            if (r8 != 0) goto La
            goto L9d
        La:
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch: org.json.JSONException -> L8f
            r0 = 16
            r8.<init>(r0)     // Catch: org.json.JSONException -> L8f
            java.util.Iterator r1 = r9.keys()     // Catch: org.json.JSONException -> L8f
        L15:
            boolean r2 = r1.hasNext()     // Catch: org.json.JSONException -> L8f
            if (r2 == 0) goto L8e
            java.lang.Object r2 = r1.next()     // Catch: org.json.JSONException -> L8f
            java.lang.String r2 = (java.lang.String) r2     // Catch: org.json.JSONException -> L8f
            com.huawei.hms.framework.network.grs.local.model.b r3 = new com.huawei.hms.framework.network.grs.local.model.b     // Catch: org.json.JSONException -> L8f
            r3.<init>()     // Catch: org.json.JSONException -> L8f
            r3.a(r2)     // Catch: org.json.JSONException -> L8f
            org.json.JSONObject r2 = r9.getJSONObject(r2)     // Catch: org.json.JSONException -> L8f
            java.lang.String r4 = "name"
            java.lang.String r4 = r2.getString(r4)     // Catch: org.json.JSONException -> L8f
            r3.b(r4)     // Catch: org.json.JSONException -> L8f
            java.lang.String r4 = "description"
            java.lang.String r4 = r2.getString(r4)     // Catch: org.json.JSONException -> L8f
            r3.c(r4)     // Catch: org.json.JSONException -> L8f
            r4 = 0
            java.lang.String r5 = "countriesOrAreas"
            boolean r5 = r2.has(r5)     // Catch: org.json.JSONException -> L8f
            if (r5 == 0) goto L4f
            java.lang.String r4 = "countriesOrAreas"
        L4a:
            org.json.JSONArray r4 = r2.getJSONArray(r4)     // Catch: org.json.JSONException -> L8f
            goto L61
        L4f:
            java.lang.String r5 = "countries"
            boolean r5 = r2.has(r5)     // Catch: org.json.JSONException -> L8f
            if (r5 == 0) goto L5a
            java.lang.String r4 = "countries"
            goto L4a
        L5a:
            java.lang.String r2 = "LocalManagerV1"
            java.lang.String r5 = "current country or area group has not config countries or areas."
            com.huawei.hms.framework.common.Logger.w(r2, r5)     // Catch: org.json.JSONException -> L8f
        L61:
            java.util.HashSet r2 = new java.util.HashSet     // Catch: org.json.JSONException -> L8f
            r2.<init>(r0)     // Catch: org.json.JSONException -> L8f
            if (r4 == 0) goto L89
            int r5 = r4.length()     // Catch: org.json.JSONException -> L8f
            if (r5 != 0) goto L6f
            goto L89
        L6f:
            r5 = 0
        L70:
            int r6 = r4.length()     // Catch: org.json.JSONException -> L8f
            if (r5 >= r6) goto L82
            java.lang.Object r6 = r4.get(r5)     // Catch: org.json.JSONException -> L8f
            java.lang.String r6 = (java.lang.String) r6     // Catch: org.json.JSONException -> L8f
            r2.add(r6)     // Catch: org.json.JSONException -> L8f
            int r5 = r5 + 1
            goto L70
        L82:
            r3.a(r2)     // Catch: org.json.JSONException -> L8f
            r8.add(r3)     // Catch: org.json.JSONException -> L8f
            goto L15
        L89:
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch: org.json.JSONException -> L8f
            r8.<init>()     // Catch: org.json.JSONException -> L8f
        L8e:
            return r8
        L8f:
            r8 = move-exception
            java.lang.String r9 = "LocalManagerV1"
            java.lang.String r0 = "parse countryGroups failed maybe json style is wrong."
            com.huawei.hms.framework.common.Logger.w(r9, r0, r8)
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            return r8
        L9d:
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.framework.network.grs.local.c.a(org.json.JSONArray, org.json.JSONObject):java.util.List");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0092 A[Catch: JSONException -> 0x00ed, LOOP:2: B:19:0x008c->B:21:0x0092, LOOP_END, TryCatch #0 {JSONException -> 0x00ed, blocks: (B:2:0x0000, B:3:0x000f, B:5:0x0016, B:7:0x002c, B:8:0x0044, B:10:0x004a, B:13:0x0061, B:18:0x0078, B:19:0x008c, B:21:0x0092, B:22:0x00a0, B:14:0x0066, B:17:0x0071, B:23:0x00ad, B:25:0x00b6, B:26:0x00bc, B:31:0x00d7, B:33:0x00de, B:34:0x00e5, B:27:0x00c1, B:29:0x00c9, B:30:0x00d0), top: B:40:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00de A[Catch: JSONException -> 0x00ed, TryCatch #0 {JSONException -> 0x00ed, blocks: (B:2:0x0000, B:3:0x000f, B:5:0x0016, B:7:0x002c, B:8:0x0044, B:10:0x004a, B:13:0x0061, B:18:0x0078, B:19:0x008c, B:21:0x0092, B:22:0x00a0, B:14:0x0066, B:17:0x0071, B:23:0x00ad, B:25:0x00b6, B:26:0x00bc, B:31:0x00d7, B:33:0x00de, B:34:0x00e5, B:27:0x00c1, B:29:0x00c9, B:30:0x00d0), top: B:40:0x0000 }] */
    @Override // com.huawei.hms.framework.network.grs.local.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int b(java.lang.String r13) throws org.json.JSONException {
        /*
            r12 = this;
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch: org.json.JSONException -> Led
            r0.<init>(r13)     // Catch: org.json.JSONException -> Led
            java.lang.String r13 = "services"
            org.json.JSONObject r13 = r0.getJSONObject(r13)     // Catch: org.json.JSONException -> Led
            java.util.Iterator r0 = r13.keys()     // Catch: org.json.JSONException -> Led
        Lf:
            boolean r1 = r0.hasNext()     // Catch: org.json.JSONException -> Led
            r2 = 0
            if (r1 == 0) goto Lec
            java.lang.Object r1 = r0.next()     // Catch: org.json.JSONException -> Led
            java.lang.String r1 = (java.lang.String) r1     // Catch: org.json.JSONException -> Led
            com.huawei.hms.framework.network.grs.local.model.c r3 = new com.huawei.hms.framework.network.grs.local.model.c     // Catch: org.json.JSONException -> Led
            r3.<init>()     // Catch: org.json.JSONException -> Led
            r3.a(r1)     // Catch: org.json.JSONException -> Led
            java.util.Set<java.lang.String> r4 = r12.e     // Catch: org.json.JSONException -> Led
            boolean r4 = r4.contains(r1)     // Catch: org.json.JSONException -> Led
            if (r4 != 0) goto Lf
            java.util.Set<java.lang.String> r4 = r12.e     // Catch: org.json.JSONException -> Led
            r4.add(r1)     // Catch: org.json.JSONException -> Led
            org.json.JSONObject r4 = r13.getJSONObject(r1)     // Catch: org.json.JSONException -> Led
            java.lang.String r5 = "routeBy"
            java.lang.String r5 = r4.getString(r5)     // Catch: org.json.JSONException -> Led
            r3.c(r5)     // Catch: org.json.JSONException -> Led
            java.lang.String r5 = "servings"
            org.json.JSONArray r5 = r4.getJSONArray(r5)     // Catch: org.json.JSONException -> Led
        L44:
            int r6 = r5.length()     // Catch: org.json.JSONException -> Led
            if (r2 >= r6) goto Lad
            java.lang.Object r6 = r5.get(r2)     // Catch: org.json.JSONException -> Led
            org.json.JSONObject r6 = (org.json.JSONObject) r6     // Catch: org.json.JSONException -> Led
            com.huawei.hms.framework.network.grs.local.model.d r7 = new com.huawei.hms.framework.network.grs.local.model.d     // Catch: org.json.JSONException -> Led
            r7.<init>()     // Catch: org.json.JSONException -> Led
            java.lang.String r8 = "no-country"
            java.lang.String r9 = "countryOrAreaGroup"
            boolean r9 = r6.has(r9)     // Catch: org.json.JSONException -> Led
            if (r9 == 0) goto L66
            java.lang.String r8 = "countryOrAreaGroup"
        L61:
            java.lang.String r8 = r6.getString(r8)     // Catch: org.json.JSONException -> Led
            goto L78
        L66:
            java.lang.String r9 = "countryGroup"
            boolean r9 = r6.has(r9)     // Catch: org.json.JSONException -> Led
            if (r9 == 0) goto L71
            java.lang.String r8 = "countryGroup"
            goto L61
        L71:
            java.lang.String r9 = "LocalManagerV1"
            java.lang.String r10 = "maybe this service routeBy is unconditional."
            com.huawei.hms.framework.common.Logger.v(r9, r10)     // Catch: org.json.JSONException -> Led
        L78:
            r7.a(r8)     // Catch: org.json.JSONException -> Led
            java.lang.String r8 = "addresses"
            org.json.JSONObject r6 = r6.getJSONObject(r8)     // Catch: org.json.JSONException -> Led
            java.util.HashMap r8 = new java.util.HashMap     // Catch: org.json.JSONException -> Led
            r9 = 16
            r8.<init>(r9)     // Catch: org.json.JSONException -> Led
            java.util.Iterator r9 = r6.keys()     // Catch: org.json.JSONException -> Led
        L8c:
            boolean r10 = r9.hasNext()     // Catch: org.json.JSONException -> Led
            if (r10 == 0) goto La0
            java.lang.Object r10 = r9.next()     // Catch: org.json.JSONException -> Led
            java.lang.String r10 = (java.lang.String) r10     // Catch: org.json.JSONException -> Led
            java.lang.String r11 = r6.getString(r10)     // Catch: org.json.JSONException -> Led
            r8.put(r10, r11)     // Catch: org.json.JSONException -> Led
            goto L8c
        La0:
            r7.a(r8)     // Catch: org.json.JSONException -> Led
            java.lang.String r6 = r7.a()     // Catch: org.json.JSONException -> Led
            r3.a(r6, r7)     // Catch: org.json.JSONException -> Led
            int r2 = r2 + 1
            goto L44
        Lad:
            java.lang.String r2 = "countryOrAreaGroups"
            boolean r2 = r4.has(r2)     // Catch: org.json.JSONException -> Led
            r5 = 0
            if (r2 == 0) goto Lc1
            java.lang.String r2 = "countryOrAreaGroups"
            org.json.JSONObject r2 = r4.getJSONObject(r2)     // Catch: org.json.JSONException -> Led
        Lbc:
            java.util.List r5 = r12.a(r5, r2)     // Catch: org.json.JSONException -> Led
            goto Ld7
        Lc1:
            java.lang.String r2 = "countryGroups"
            boolean r2 = r4.has(r2)     // Catch: org.json.JSONException -> Led
            if (r2 == 0) goto Ld0
            java.lang.String r2 = "countryGroups"
            org.json.JSONObject r2 = r4.getJSONObject(r2)     // Catch: org.json.JSONException -> Led
            goto Lbc
        Ld0:
            java.lang.String r2 = "LocalManagerV1"
            java.lang.String r4 = "service use default countryOrAreaGroup"
            com.huawei.hms.framework.common.Logger.v(r2, r4)     // Catch: org.json.JSONException -> Led
        Ld7:
            r3.a(r5)     // Catch: org.json.JSONException -> Led
            com.huawei.hms.framework.network.grs.local.model.a r2 = r12.a     // Catch: org.json.JSONException -> Led
            if (r2 != 0) goto Le5
            com.huawei.hms.framework.network.grs.local.model.a r2 = new com.huawei.hms.framework.network.grs.local.model.a     // Catch: org.json.JSONException -> Led
            r2.<init>()     // Catch: org.json.JSONException -> Led
            r12.a = r2     // Catch: org.json.JSONException -> Led
        Le5:
            com.huawei.hms.framework.network.grs.local.model.a r2 = r12.a     // Catch: org.json.JSONException -> Led
            r2.a(r1, r3)     // Catch: org.json.JSONException -> Led
            goto Lf
        Lec:
            return r2
        Led:
            r13 = move-exception
            java.lang.String r0 = "LocalManagerV1"
            java.lang.String r1 = "parse 1.0 services failed maybe because of json style.please check!"
            com.huawei.hms.framework.common.Logger.w(r0, r1, r13)
            r13 = -1
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.framework.network.grs.local.c.b(java.lang.String):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0034 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0035 A[Catch: JSONException -> 0x00b8, TryCatch #0 {JSONException -> 0x00b8, blocks: (B:3:0x000a, B:6:0x001a, B:13:0x0035, B:15:0x003c, B:16:0x0040, B:18:0x0046, B:21:0x0074, B:26:0x008c, B:28:0x0093, B:32:0x009b, B:34:0x00a1, B:35:0x00ad, B:22:0x0079, B:25:0x0084, B:7:0x001f, B:10:0x002a), top: B:42:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00a1 A[Catch: JSONException -> 0x00b8, LOOP:1: B:32:0x009b->B:34:0x00a1, LOOP_END, TryCatch #0 {JSONException -> 0x00b8, blocks: (B:3:0x000a, B:6:0x001a, B:13:0x0035, B:15:0x003c, B:16:0x0040, B:18:0x0046, B:21:0x0074, B:26:0x008c, B:28:0x0093, B:32:0x009b, B:34:0x00a1, B:35:0x00ad, B:22:0x0079, B:25:0x0084, B:7:0x001f, B:10:0x002a), top: B:42:0x000a }] */
    @Override // com.huawei.hms.framework.network.grs.local.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int c(java.lang.String r11) throws org.json.JSONException {
        /*
            r10 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 16
            r0.<init>(r1)
            r10.b = r0
            r0 = -1
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: org.json.JSONException -> Lb8
            r2.<init>(r11)     // Catch: org.json.JSONException -> Lb8
            java.lang.String r11 = "countryOrAreaGroups"
            boolean r11 = r2.has(r11)     // Catch: org.json.JSONException -> Lb8
            r3 = 0
            if (r11 == 0) goto L1f
            java.lang.String r11 = "countryOrAreaGroups"
        L1a:
            org.json.JSONObject r11 = r2.getJSONObject(r11)     // Catch: org.json.JSONException -> Lb8
            goto L32
        L1f:
            java.lang.String r11 = "countryGroups"
            boolean r11 = r2.has(r11)     // Catch: org.json.JSONException -> Lb8
            if (r11 == 0) goto L2a
            java.lang.String r11 = "countryGroups"
            goto L1a
        L2a:
            java.lang.String r11 = "LocalManagerV1"
            java.lang.String r2 = "maybe local config json is wrong because the default countryOrAreaGroups isn't config."
            com.huawei.hms.framework.common.Logger.e(r11, r2)     // Catch: org.json.JSONException -> Lb8
            r11 = r3
        L32:
            if (r11 != 0) goto L35
            return r0
        L35:
            int r2 = r11.length()     // Catch: org.json.JSONException -> Lb8
            r4 = 0
            if (r2 == 0) goto Lb7
            java.util.Iterator r2 = r11.keys()     // Catch: org.json.JSONException -> Lb8
        L40:
            boolean r5 = r2.hasNext()     // Catch: org.json.JSONException -> Lb8
            if (r5 == 0) goto Lb7
            java.lang.Object r5 = r2.next()     // Catch: org.json.JSONException -> Lb8
            java.lang.String r5 = (java.lang.String) r5     // Catch: org.json.JSONException -> Lb8
            com.huawei.hms.framework.network.grs.local.model.b r6 = new com.huawei.hms.framework.network.grs.local.model.b     // Catch: org.json.JSONException -> Lb8
            r6.<init>()     // Catch: org.json.JSONException -> Lb8
            r6.a(r5)     // Catch: org.json.JSONException -> Lb8
            org.json.JSONObject r5 = r11.getJSONObject(r5)     // Catch: org.json.JSONException -> Lb8
            java.lang.String r7 = "name"
            java.lang.String r7 = r5.getString(r7)     // Catch: org.json.JSONException -> Lb8
            r6.b(r7)     // Catch: org.json.JSONException -> Lb8
            java.lang.String r7 = "description"
            java.lang.String r7 = r5.getString(r7)     // Catch: org.json.JSONException -> Lb8
            r6.c(r7)     // Catch: org.json.JSONException -> Lb8
            java.lang.String r7 = "countriesOrAreas"
            boolean r7 = r5.has(r7)     // Catch: org.json.JSONException -> Lb8
            if (r7 == 0) goto L79
            java.lang.String r7 = "countriesOrAreas"
        L74:
            org.json.JSONArray r5 = r5.getJSONArray(r7)     // Catch: org.json.JSONException -> Lb8
            goto L8c
        L79:
            java.lang.String r7 = "countries"
            boolean r7 = r5.has(r7)     // Catch: org.json.JSONException -> Lb8
            if (r7 == 0) goto L84
            java.lang.String r7 = "countries"
            goto L74
        L84:
            java.lang.String r5 = "LocalManagerV1"
            java.lang.String r7 = "current country or area group has not config countries or areas."
            com.huawei.hms.framework.common.Logger.w(r5, r7)     // Catch: org.json.JSONException -> Lb8
            r5 = r3
        L8c:
            java.util.HashSet r7 = new java.util.HashSet     // Catch: org.json.JSONException -> Lb8
            r7.<init>(r1)     // Catch: org.json.JSONException -> Lb8
            if (r5 == 0) goto Lb6
            int r8 = r5.length()     // Catch: org.json.JSONException -> Lb8
            if (r8 != 0) goto L9a
            goto Lb6
        L9a:
            r8 = 0
        L9b:
            int r9 = r5.length()     // Catch: org.json.JSONException -> Lb8
            if (r8 >= r9) goto Lad
            java.lang.Object r9 = r5.get(r8)     // Catch: org.json.JSONException -> Lb8
            java.lang.String r9 = (java.lang.String) r9     // Catch: org.json.JSONException -> Lb8
            r7.add(r9)     // Catch: org.json.JSONException -> Lb8
            int r8 = r8 + 1
            goto L9b
        Lad:
            r6.a(r7)     // Catch: org.json.JSONException -> Lb8
            java.util.List<com.huawei.hms.framework.network.grs.local.model.b> r5 = r10.b     // Catch: org.json.JSONException -> Lb8
            r5.add(r6)     // Catch: org.json.JSONException -> Lb8
            goto L40
        Lb6:
            return r0
        Lb7:
            return r4
        Lb8:
            r11 = move-exception
            java.lang.String r1 = "LocalManagerV1"
            java.lang.String r2 = "parse countrygroup failed maybe json style is wrong."
            com.huawei.hms.framework.common.Logger.w(r1, r2, r11)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.framework.network.grs.local.c.c(java.lang.String):int");
    }
}
