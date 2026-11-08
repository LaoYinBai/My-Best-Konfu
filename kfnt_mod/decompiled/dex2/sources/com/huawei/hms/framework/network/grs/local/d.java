package com.huawei.hms.framework.network.grs.local;

import android.content.Context;
import com.huawei.hms.framework.common.Logger;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d extends a {
    public d(Context context) {
        if (a("grs_app_global_route_config.json", context) == 0) {
            this.d = true;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x008f A[LOOP:2: B:23:0x0089->B:25:0x008f, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00d7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(org.json.JSONArray r14) throws org.json.JSONException {
        /*
            r13 = this;
            if (r14 == 0) goto Le7
            int r0 = r14.length()
            if (r0 != 0) goto La
            goto Le7
        La:
            r0 = 0
            r1 = 0
        Lc:
            int r2 = r14.length()
            if (r1 >= r2) goto Le7
            org.json.JSONObject r2 = r14.getJSONObject(r1)
            com.huawei.hms.framework.network.grs.local.model.c r3 = new com.huawei.hms.framework.network.grs.local.model.c
            r3.<init>()
            java.lang.String r4 = "name"
            java.lang.String r4 = r2.getString(r4)
            r3.a(r4)
            java.util.Set<java.lang.String> r5 = r13.e
            boolean r5 = r5.contains(r4)
            if (r5 != 0) goto Le3
            java.util.Set<java.lang.String> r5 = r13.e
            r5.add(r4)
            java.lang.String r5 = "routeBy"
            java.lang.String r5 = r2.getString(r5)
            r3.c(r5)
            java.lang.String r5 = "servings"
            org.json.JSONArray r5 = r2.getJSONArray(r5)
            r6 = 0
        L41:
            int r7 = r5.length()
            if (r6 >= r7) goto Laa
            java.lang.Object r7 = r5.get(r6)
            org.json.JSONObject r7 = (org.json.JSONObject) r7
            com.huawei.hms.framework.network.grs.local.model.d r8 = new com.huawei.hms.framework.network.grs.local.model.d
            r8.<init>()
            java.lang.String r9 = "no-country"
            java.lang.String r10 = "countryOrAreaGroup"
            boolean r10 = r7.has(r10)
            if (r10 == 0) goto L63
            java.lang.String r9 = "countryOrAreaGroup"
        L5e:
            java.lang.String r9 = r7.getString(r9)
            goto L75
        L63:
            java.lang.String r10 = "countryGroup"
            boolean r10 = r7.has(r10)
            if (r10 == 0) goto L6e
            java.lang.String r9 = "countryGroup"
            goto L5e
        L6e:
            java.lang.String r10 = "LocalManagerV2"
            java.lang.String r11 = "maybe this service routeBy is unconditional."
            com.huawei.hms.framework.common.Logger.v(r10, r11)
        L75:
            r8.a(r9)
            java.lang.String r9 = "addresses"
            org.json.JSONObject r7 = r7.getJSONObject(r9)
            java.util.HashMap r9 = new java.util.HashMap
            r10 = 16
            r9.<init>(r10)
            java.util.Iterator r10 = r7.keys()
        L89:
            boolean r11 = r10.hasNext()
            if (r11 == 0) goto L9d
            java.lang.Object r11 = r10.next()
            java.lang.String r11 = (java.lang.String) r11
            java.lang.String r12 = r7.getString(r11)
            r9.put(r11, r12)
            goto L89
        L9d:
            r8.a(r9)
            java.lang.String r7 = r8.a()
            r3.a(r7, r8)
            int r6 = r6 + 1
            goto L41
        Laa:
            java.lang.String r5 = "countryOrAreaGroups"
            boolean r5 = r2.has(r5)
            r6 = 0
            if (r5 == 0) goto Lbe
            java.lang.String r5 = "countryOrAreaGroups"
        Lb5:
            org.json.JSONArray r2 = r2.getJSONArray(r5)
            java.util.List r6 = r13.a(r2, r6)
            goto Ld0
        Lbe:
            java.lang.String r5 = "countryGroups"
            boolean r5 = r2.has(r5)
            if (r5 == 0) goto Lc9
            java.lang.String r5 = "countryGroups"
            goto Lb5
        Lc9:
            java.lang.String r2 = "LocalManagerV2"
            java.lang.String r5 = "service use default countryOrAreaGroup"
            com.huawei.hms.framework.common.Logger.i(r2, r5)
        Ld0:
            r3.a(r6)
            com.huawei.hms.framework.network.grs.local.model.a r2 = r13.a
            if (r2 != 0) goto Lde
            com.huawei.hms.framework.network.grs.local.model.a r2 = new com.huawei.hms.framework.network.grs.local.model.a
            r2.<init>()
            r13.a = r2
        Lde:
            com.huawei.hms.framework.network.grs.local.model.a r2 = r13.a
            r2.a(r4, r3)
        Le3:
            int r1 = r1 + 1
            goto Lc
        Le7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.framework.network.grs.local.d.a(org.json.JSONArray):void");
    }

    @Override // com.huawei.hms.framework.network.grs.local.a
    public int a(String str) throws JSONException {
        this.a = new com.huawei.hms.framework.network.grs.local.model.a();
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONArray("applications").getJSONObject(0);
            this.a.a(jSONObject.getString("name"));
            JSONArray jSONArray = jSONObject.getJSONArray("services");
            ArrayList arrayList = new ArrayList(16);
            if (jSONArray != null && jSONArray.length() != 0) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add((String) jSONArray.get(i));
                }
                if (jSONObject.has("customservices")) {
                    a(jSONObject.getJSONArray("customservices"));
                }
                return 0;
            }
            return -1;
        } catch (JSONException e) {
            Logger.w("LocalManagerV2", "parse appbean failed maybe json style is wrong.", e);
            return -1;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0074 A[Catch: JSONException -> 0x0090, LOOP:1: B:23:0x006e->B:25:0x0074, LOOP_END, TryCatch #0 {JSONException -> 0x0090, blocks: (B:6:0x000a, B:7:0x0013, B:9:0x0019, B:12:0x0048, B:17:0x005f, B:19:0x0066, B:23:0x006e, B:25:0x0074, B:26:0x0080, B:27:0x0089, B:13:0x004d, B:16:0x0058), top: B:35:0x000a }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.List<com.huawei.hms.framework.network.grs.local.model.b> a(org.json.JSONArray r9, org.json.JSONObject r10) throws org.json.JSONException {
        /*
            r8 = this;
            if (r9 == 0) goto L9e
            int r10 = r9.length()
            if (r10 != 0) goto La
            goto L9e
        La:
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch: org.json.JSONException -> L90
            r0 = 16
            r10.<init>(r0)     // Catch: org.json.JSONException -> L90
            r1 = 0
            r2 = 0
        L13:
            int r3 = r9.length()     // Catch: org.json.JSONException -> L90
            if (r2 >= r3) goto L8f
            org.json.JSONObject r3 = r9.getJSONObject(r2)     // Catch: org.json.JSONException -> L90
            com.huawei.hms.framework.network.grs.local.model.b r4 = new com.huawei.hms.framework.network.grs.local.model.b     // Catch: org.json.JSONException -> L90
            r4.<init>()     // Catch: org.json.JSONException -> L90
            java.lang.String r5 = "id"
            java.lang.String r5 = r3.getString(r5)     // Catch: org.json.JSONException -> L90
            r4.a(r5)     // Catch: org.json.JSONException -> L90
            java.lang.String r5 = "name"
            java.lang.String r5 = r3.getString(r5)     // Catch: org.json.JSONException -> L90
            r4.b(r5)     // Catch: org.json.JSONException -> L90
            java.lang.String r5 = "description"
            java.lang.String r5 = r3.getString(r5)     // Catch: org.json.JSONException -> L90
            r4.c(r5)     // Catch: org.json.JSONException -> L90
            r5 = 0
            java.lang.String r6 = "countriesOrAreas"
            boolean r6 = r3.has(r6)     // Catch: org.json.JSONException -> L90
            if (r6 == 0) goto L4d
            java.lang.String r5 = "countriesOrAreas"
        L48:
            org.json.JSONArray r5 = r3.getJSONArray(r5)     // Catch: org.json.JSONException -> L90
            goto L5f
        L4d:
            java.lang.String r6 = "countries"
            boolean r6 = r3.has(r6)     // Catch: org.json.JSONException -> L90
            if (r6 == 0) goto L58
            java.lang.String r5 = "countries"
            goto L48
        L58:
            java.lang.String r3 = "LocalManagerV2"
            java.lang.String r6 = "current country or area group has not config countries or areas."
            com.huawei.hms.framework.common.Logger.w(r3, r6)     // Catch: org.json.JSONException -> L90
        L5f:
            java.util.HashSet r3 = new java.util.HashSet     // Catch: org.json.JSONException -> L90
            r3.<init>(r0)     // Catch: org.json.JSONException -> L90
            if (r5 == 0) goto L89
            int r6 = r5.length()     // Catch: org.json.JSONException -> L90
            if (r6 != 0) goto L6d
            goto L89
        L6d:
            r6 = 0
        L6e:
            int r7 = r5.length()     // Catch: org.json.JSONException -> L90
            if (r6 >= r7) goto L80
            java.lang.Object r7 = r5.get(r6)     // Catch: org.json.JSONException -> L90
            java.lang.String r7 = (java.lang.String) r7     // Catch: org.json.JSONException -> L90
            r3.add(r7)     // Catch: org.json.JSONException -> L90
            int r6 = r6 + 1
            goto L6e
        L80:
            r4.a(r3)     // Catch: org.json.JSONException -> L90
            r10.add(r4)     // Catch: org.json.JSONException -> L90
            int r2 = r2 + 1
            goto L13
        L89:
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch: org.json.JSONException -> L90
            r9.<init>()     // Catch: org.json.JSONException -> L90
            return r9
        L8f:
            return r10
        L90:
            r9 = move-exception
            java.lang.String r10 = "LocalManagerV2"
            java.lang.String r0 = "parse countrygroup failed maybe json style is wrong."
            com.huawei.hms.framework.common.Logger.w(r10, r0, r9)
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            return r9
        L9e:
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.framework.network.grs.local.d.a(org.json.JSONArray, org.json.JSONObject):java.util.List");
    }

    @Override // com.huawei.hms.framework.network.grs.local.a
    public int b(String str) {
        try {
            a(new JSONObject(str).getJSONArray("services"));
            return 0;
        } catch (JSONException e) {
            Logger.w("LocalManagerV2", "parse 2.0 services failed maybe because of json style.please check!", e);
            return -1;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0034 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0035 A[Catch: JSONException -> 0x00b7, TryCatch #0 {JSONException -> 0x00b7, blocks: (B:3:0x000a, B:6:0x001a, B:13:0x0035, B:16:0x003d, B:18:0x0043, B:21:0x0071, B:26:0x0089, B:28:0x0090, B:32:0x0098, B:34:0x009e, B:35:0x00aa, B:22:0x0076, B:25:0x0081, B:7:0x001f, B:10:0x002a), top: B:42:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x009e A[Catch: JSONException -> 0x00b7, LOOP:1: B:32:0x0098->B:34:0x009e, LOOP_END, TryCatch #0 {JSONException -> 0x00b7, blocks: (B:3:0x000a, B:6:0x001a, B:13:0x0035, B:16:0x003d, B:18:0x0043, B:21:0x0071, B:26:0x0089, B:28:0x0090, B:32:0x0098, B:34:0x009e, B:35:0x00aa, B:22:0x0076, B:25:0x0081, B:7:0x001f, B:10:0x002a), top: B:42:0x000a }] */
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
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: org.json.JSONException -> Lb7
            r2.<init>(r11)     // Catch: org.json.JSONException -> Lb7
            java.lang.String r11 = "countryOrAreaGroups"
            boolean r11 = r2.has(r11)     // Catch: org.json.JSONException -> Lb7
            r3 = 0
            if (r11 == 0) goto L1f
            java.lang.String r11 = "countryOrAreaGroups"
        L1a:
            org.json.JSONArray r11 = r2.getJSONArray(r11)     // Catch: org.json.JSONException -> Lb7
            goto L32
        L1f:
            java.lang.String r11 = "countryGroups"
            boolean r11 = r2.has(r11)     // Catch: org.json.JSONException -> Lb7
            if (r11 == 0) goto L2a
            java.lang.String r11 = "countryGroups"
            goto L1a
        L2a:
            java.lang.String r11 = "LocalManagerV2"
            java.lang.String r2 = "maybe local config json is wrong because the default countryOrAreaGroups isn't config."
            com.huawei.hms.framework.common.Logger.e(r11, r2)     // Catch: org.json.JSONException -> Lb7
            r11 = r3
        L32:
            if (r11 != 0) goto L35
            return r0
        L35:
            int r2 = r11.length()     // Catch: org.json.JSONException -> Lb7
            r4 = 0
            if (r2 == 0) goto Lb6
            r2 = 0
        L3d:
            int r5 = r11.length()     // Catch: org.json.JSONException -> Lb7
            if (r2 >= r5) goto Lb6
            org.json.JSONObject r5 = r11.getJSONObject(r2)     // Catch: org.json.JSONException -> Lb7
            com.huawei.hms.framework.network.grs.local.model.b r6 = new com.huawei.hms.framework.network.grs.local.model.b     // Catch: org.json.JSONException -> Lb7
            r6.<init>()     // Catch: org.json.JSONException -> Lb7
            java.lang.String r7 = "id"
            java.lang.String r7 = r5.getString(r7)     // Catch: org.json.JSONException -> Lb7
            r6.a(r7)     // Catch: org.json.JSONException -> Lb7
            java.lang.String r7 = "name"
            java.lang.String r7 = r5.getString(r7)     // Catch: org.json.JSONException -> Lb7
            r6.b(r7)     // Catch: org.json.JSONException -> Lb7
            java.lang.String r7 = "description"
            java.lang.String r7 = r5.getString(r7)     // Catch: org.json.JSONException -> Lb7
            r6.c(r7)     // Catch: org.json.JSONException -> Lb7
            java.lang.String r7 = "countriesOrAreas"
            boolean r7 = r5.has(r7)     // Catch: org.json.JSONException -> Lb7
            if (r7 == 0) goto L76
            java.lang.String r7 = "countriesOrAreas"
        L71:
            org.json.JSONArray r5 = r5.getJSONArray(r7)     // Catch: org.json.JSONException -> Lb7
            goto L89
        L76:
            java.lang.String r7 = "countries"
            boolean r7 = r5.has(r7)     // Catch: org.json.JSONException -> Lb7
            if (r7 == 0) goto L81
            java.lang.String r7 = "countries"
            goto L71
        L81:
            java.lang.String r5 = "LocalManagerV2"
            java.lang.String r7 = "current country or area group has not config countries or areas."
            com.huawei.hms.framework.common.Logger.w(r5, r7)     // Catch: org.json.JSONException -> Lb7
            r5 = r3
        L89:
            java.util.HashSet r7 = new java.util.HashSet     // Catch: org.json.JSONException -> Lb7
            r7.<init>(r1)     // Catch: org.json.JSONException -> Lb7
            if (r5 == 0) goto Lb5
            int r8 = r5.length()     // Catch: org.json.JSONException -> Lb7
            if (r8 != 0) goto L97
            goto Lb5
        L97:
            r8 = 0
        L98:
            int r9 = r5.length()     // Catch: org.json.JSONException -> Lb7
            if (r8 >= r9) goto Laa
            java.lang.Object r9 = r5.get(r8)     // Catch: org.json.JSONException -> Lb7
            java.lang.String r9 = (java.lang.String) r9     // Catch: org.json.JSONException -> Lb7
            r7.add(r9)     // Catch: org.json.JSONException -> Lb7
            int r8 = r8 + 1
            goto L98
        Laa:
            r6.a(r7)     // Catch: org.json.JSONException -> Lb7
            java.util.List<com.huawei.hms.framework.network.grs.local.model.b> r5 = r10.b     // Catch: org.json.JSONException -> Lb7
            r5.add(r6)     // Catch: org.json.JSONException -> Lb7
            int r2 = r2 + 1
            goto L3d
        Lb5:
            return r0
        Lb6:
            return r4
        Lb7:
            r11 = move-exception
            java.lang.String r1 = "LocalManagerV2"
            java.lang.String r2 = "parse countrygroup failed maybe json style is wrong."
            com.huawei.hms.framework.common.Logger.w(r1, r2, r11)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.framework.network.grs.local.d.c(java.lang.String):int");
    }
}
