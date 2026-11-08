package com.bytedance.a;

import org.json.JSONArray;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class bb {

    /* renamed from: a, reason: collision with root package name */
    public static bv f3394a;

    static {
        bv bvVar;
        try {
            Object objInvoke = Class.forName("com.bytedance.applog.et_verify.EventVerify").getMethod("inst", new Class[0]).invoke(null, new Object[0]);
            bvVar = objInvoke instanceof bv ? (bv) objInvoke : null;
        } catch (Exception unused) {
            bvVar = null;
        } catch (Throwable th) {
            de.a("can't find event verify, should compile with ET", null);
            throw th;
        }
        if (bvVar == null) {
            de.a("can't find event verify, should compile with ET", null);
        } else {
            de.a(bvVar.toString(), null);
        }
        f3394a = bvVar;
    }

    public static void a(String str, JSONArray jSONArray) {
        bv bvVar = f3394a;
        if (bvVar != null) {
            bvVar.a(str, jSONArray);
        }
    }

    public static boolean a() {
        bv bvVar = f3394a;
        if (bvVar != null) {
            return bvVar.a();
        }
        return false;
    }
}
