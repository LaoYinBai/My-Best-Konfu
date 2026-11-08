package com.bytedance.a;

import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import java.io.Closeable;
import java.io.IOException;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class dj {

    /* renamed from: a, reason: collision with root package name */
    public static String f3509a;

    public static String a(boolean z) {
        return z ? "yes" : "no";
    }

    public static void a(Closeable closeable) throws IOException {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static boolean a(String str) {
        int length = str != null ? str.length() : 0;
        if (length < 13 || length > 128) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if ((cCharAt < '0' || cCharAt > '9') && ((cCharAt < 'a' || cCharAt > 'f') && ((cCharAt < 'A' || cCharAt > 'F') && cCharAt != '-'))) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(String str, String str2) {
        return (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) || (str != null && str.equals(str2));
    }

    public static boolean a(JSONObject jSONObject, JSONObject jSONObject2) {
        return (jSONObject == null || jSONObject2 == null) ? jSONObject == jSONObject2 || (jSONObject != null && jSONObject.equals(jSONObject2)) : jSONObject.toString().equals(jSONObject2.toString());
    }

    public static JSONObject b(JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        try {
            Iterator<String> itKeys = jSONObject2.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                jSONObject.put(next, jSONObject2.opt(next));
            }
        } catch (JSONException e) {
            de.a("U SHALL NOT PASS!", e);
        }
        return jSONObject;
    }

    public static boolean b(String str) {
        boolean z = false;
        if (TextUtils.isEmpty(str) || "unknown".equalsIgnoreCase(str) || "Null".equalsIgnoreCase(str)) {
            return false;
        }
        int i = 0;
        while (true) {
            if (i >= str.length()) {
                z = true;
                break;
            }
            if (str.charAt(i) != '0') {
                break;
            }
            i++;
        }
        return !z;
    }

    public static void a(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase != null) {
            try {
                sQLiteDatabase.endTransaction();
            } catch (Throwable th) {
                de.a("U SHALL NOT PASS!", th);
            }
        }
    }

    public static JSONObject a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        JSONObject jSONObject2 = new JSONObject();
        b(jSONObject2, jSONObject);
        try {
            String strA = o.a(jSONObject2.optJSONObject("oaid"));
            if (TextUtils.isEmpty(strA)) {
                return jSONObject2;
            }
            jSONObject2.put("oaid", strA);
            return jSONObject2;
        } catch (Exception e) {
            de.a("U SHALL NOT PASS!", e);
            return jSONObject2;
        }
    }
}
