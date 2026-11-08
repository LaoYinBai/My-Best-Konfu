package com.alipay.sdk.protocol;

import android.text.TextUtils;
import com.alipay.sdk.util.f;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private a f2627a;

    /* renamed from: b, reason: collision with root package name */
    private String f2628b;

    /* renamed from: c, reason: collision with root package name */
    private String[] f2629c;

    public b(String str) {
        this.f2628b = str;
    }

    public b(String str, a aVar) {
        this.f2628b = str;
        this.f2627a = aVar;
    }

    public static void a(b bVar) throws JSONException {
        String[] strArrC = bVar.c();
        if (strArrC.length == 3 && TextUtils.equals("tid", strArrC[0])) {
            com.alipay.sdk.tid.a aVarA = com.alipay.sdk.tid.a.a(com.alipay.sdk.sys.b.a().b());
            if (TextUtils.isEmpty(strArrC[1]) || TextUtils.isEmpty(strArrC[2])) {
                return;
            }
            aVarA.a(strArrC[1], strArrC[2]);
        }
    }

    public static List<b> a(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        if (jSONObject == null) {
            return arrayList;
        }
        String[] strArrB = b(jSONObject.optString("name", ""));
        for (int i = 0; i < strArrB.length; i++) {
            a aVarA = a.a(strArrB[i]);
            if (aVarA != a.None) {
                b bVar = new b(strArrB[i], aVarA);
                bVar.f2629c = a(strArrB[i]);
                arrayList.add(bVar);
            }
        }
        return arrayList;
    }

    private static String[] a(String str) {
        ArrayList arrayList = new ArrayList();
        int iIndexOf = str.indexOf(40);
        int iLastIndexOf = str.lastIndexOf(41);
        if (iIndexOf == -1 || iLastIndexOf == -1 || iLastIndexOf <= iIndexOf) {
            return null;
        }
        for (String str2 : str.substring(iIndexOf + 1, iLastIndexOf).split("' *, *'", -1)) {
            arrayList.add(str2.trim().replaceAll("'", "").replaceAll("\"", ""));
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    private static String[] b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str.split(f.f2657b);
    }

    public String a() {
        return this.f2628b;
    }

    public a b() {
        return this.f2627a;
    }

    public String[] c() {
        return this.f2629c;
    }
}
