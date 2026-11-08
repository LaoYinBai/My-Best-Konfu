package com.apm.insight.entity;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.apm.insight.CrashType;
import com.apm.insight.g;
import com.apm.insight.l.i;
import com.apm.insight.l.l;
import com.apm.insight.l.q;
import com.apm.insight.l.v;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    private static ConcurrentLinkedQueue<g> f2835a = new ConcurrentLinkedQueue<>();

    /* renamed from: b, reason: collision with root package name */
    private static ConcurrentHashMap<Integer, g> f2836b = new ConcurrentHashMap<>();

    public interface a {
        void a(JSONObject jSONObject);
    }

    public static File a(File file) {
        return new File(file, "all_data.json");
    }

    @NonNull
    public static JSONArray a() {
        g next;
        JSONArray jSONArray = new JSONArray();
        Iterator<g> it = f2835a.iterator();
        while (it.hasNext() && (next = it.next()) != null) {
            jSONArray.put(next.c());
        }
        return jSONArray;
    }

    @NonNull
    public static JSONArray a(Object obj) {
        JSONArray jSONArray = new JSONArray();
        Iterator<g> it = f2835a.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            g next = it.next();
            if (next != null && next.a(obj)) {
                jSONArray.put(next.a(CrashType.JAVA, (JSONArray) null));
                break;
            }
        }
        return jSONArray;
    }

    public static JSONArray a(Object obj, Throwable th, StackTraceElement[] stackTraceElementArr) {
        Iterator<g> it = f2835a.iterator();
        while (it.hasNext()) {
            g next = it.next();
            if (next != null && next.a(obj)) {
                JSONArray jSONArrayA = next.a(stackTraceElementArr, th);
                JSONArray jSONArray = new JSONArray();
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("aid", next.b());
                    jSONObject.put("lines", jSONArrayA);
                    jSONArray.put(jSONObject);
                } catch (Throwable unused) {
                }
                return jSONArray;
            }
        }
        return null;
    }

    @NonNull
    public static JSONArray a(String str) {
        g next;
        JSONArray jSONArray = new JSONArray();
        String[] strArrSplit = str.split("\n");
        Iterator<g> it = f2835a.iterator();
        while (it.hasNext() && (next = it.next()) != null) {
            if (com.apm.insight.runtime.a.b(next.b())) {
                JSONArray jSONArrayA = next.a(strArrSplit);
                if (!l.a(jSONArrayA)) {
                    jSONArray.put(next.a(CrashType.ANR, jSONArrayA));
                }
            }
        }
        return jSONArray;
    }

    @NonNull
    public static JSONArray a(String str, String str2, JSONArray jSONArray) {
        JSONObject jSONObjectOptJSONObject;
        JSONArray jSONArray2 = new JSONArray();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObjectOptJSONObject2 = jSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject2 != null && (jSONObjectOptJSONObject = jSONObjectOptJSONObject2.optJSONObject("header")) != null && com.apm.insight.runtime.a.c(String.valueOf(jSONObjectOptJSONObject.opt("aid")))) {
                if (TextUtils.isEmpty(jSONObjectOptJSONObject.optString("package"))) {
                    jSONArray2.put(jSONObjectOptJSONObject2);
                    break;
                }
                JSONArray jSONArrayOptJSONArray = jSONObjectOptJSONObject.optJSONArray("so_list");
                if (!l.a(jSONArrayOptJSONArray)) {
                    for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                        if (str.contains(jSONArrayOptJSONArray.optString(i2))) {
                            jSONArray2.put(jSONObjectOptJSONObject2);
                            break;
                            break;
                        }
                    }
                }
            }
        }
        return jSONArray2;
    }

    @Nullable
    public static JSONArray a(Throwable th, Thread thread, @Nullable File file) throws Throwable {
        g next;
        JSONArray jSONArray = new JSONArray();
        StackTraceElement[] stackTraceElementArrB = v.b(th);
        Iterator<g> it = f2835a.iterator();
        while (it.hasNext() && (next = it.next()) != null) {
            if (com.apm.insight.runtime.a.a(next.b())) {
                JSONArray jSONArrayA = next.a(stackTraceElementArrB, th);
                if (!l.a(jSONArrayA)) {
                    jSONArray.put(next.a(CrashType.JAVA, jSONArrayA));
                }
            } else {
                q.a((Object) ("not enable javaCrash aid: " + next.b()));
            }
        }
        if (l.a(jSONArray)) {
            return null;
        }
        if (file != null) {
            try {
                i.a(new File(file, "all_data.json"), jSONArray, false);
            } catch (IOException unused) {
            }
        }
        return jSONArray;
    }

    public static void a(@NonNull g gVar) {
        f2835a.add(gVar);
        if (gVar.d()) {
            f2836b.put(4444, gVar);
        }
    }

    public static void a(JSONObject jSONObject, JSONArray jSONArray, a aVar) throws JSONException {
        JSONObject jSONObjectOptJSONObject;
        q.a((Object) ("uploadFromFile with allData " + jSONArray));
        JSONArray jSONArray2 = new JSONArray();
        for (int i = 0; i < jSONArray.length() && (jSONObjectOptJSONObject = jSONArray.optJSONObject(i)) != null; i++) {
            if (l.a(jSONObjectOptJSONObject, 0, "header", "single_upload") == 1) {
                JSONObject jSONObject2 = new JSONObject();
                com.apm.insight.entity.a.b(jSONObject2, jSONObject);
                com.apm.insight.entity.a.b(jSONObject2, jSONObjectOptJSONObject);
                aVar.a(jSONObject2);
            } else {
                jSONArray2.put(jSONObjectOptJSONObject);
            }
        }
        if (jSONArray2.length() == 0) {
            return;
        }
        JSONObject jSONObject3 = new JSONObject();
        com.apm.insight.entity.a.b(jSONObject3, jSONObject);
        try {
            jSONObject3.put("all_data", jSONArray2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        aVar.a(jSONObject3);
    }

    @Nullable
    public static String b(Object obj) {
        Iterator<g> it = f2835a.iterator();
        while (it.hasNext()) {
            g next = it.next();
            if (next != null && next.a(obj)) {
                return next.b();
            }
        }
        return null;
    }

    @NonNull
    public static JSONArray b() {
        g next;
        JSONArray jSONArray = new JSONArray();
        Iterator<g> it = f2835a.iterator();
        while (it.hasNext() && (next = it.next()) != null) {
            jSONArray.put(next.a((CrashType) null));
        }
        return jSONArray;
    }

    public static int c() {
        return f2835a.size();
    }
}
