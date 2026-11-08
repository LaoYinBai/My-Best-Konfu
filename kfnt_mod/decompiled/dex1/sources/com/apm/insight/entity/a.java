package com.apm.insight.entity;

import android.content.Context;
import android.support.v4.media.session.PlaybackStateCompat;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.apm.insight.l.l;
import com.apm.insight.l.o;
import com.apm.insight.l.q;
import com.apm.insight.l.v;
import com.apm.insight.nativecrash.NativeImpl;
import com.bytedance.b.a.c.h;
import com.bytedance.pangle.provider.ContentProviderManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    protected JSONObject f2833a;

    /* renamed from: b, reason: collision with root package name */
    protected Header f2834b;

    public a() {
        this.f2833a = new JSONObject();
    }

    public a(JSONObject jSONObject) {
        this.f2833a = jSONObject;
    }

    public static a a(long j, Context context, String str) {
        a aVar = new a();
        aVar.a("is_dart", (Object) 1);
        aVar.a("crash_time", Long.valueOf(j));
        aVar.a(ContentProviderManager.PLUGIN_PROCESS_NAME, (Object) com.apm.insight.l.a.c(context));
        aVar.a("data", (Object) str);
        com.apm.insight.l.a.a(context, aVar.h());
        return aVar;
    }

    public static a a(long j, Context context, @Nullable Thread thread, @NonNull Throwable th) {
        if (j == 0) {
            j = System.currentTimeMillis();
        }
        a aVar = new a();
        aVar.a("isJava", (Object) 1);
        aVar.a("data", (Object) v.a(th));
        aVar.a("crash_time", Long.valueOf(j));
        aVar.a(ContentProviderManager.PLUGIN_PROCESS_NAME, (Object) com.apm.insight.l.a.c(context));
        if (!com.apm.insight.l.a.b(context)) {
            aVar.a("remote_process", (Object) 1);
        }
        String name = thread == null ? null : thread.getName();
        if (name != null) {
            aVar.a("crash_thread_name", (Object) name);
        }
        return aVar;
    }

    public static void a(JSONObject jSONObject, String str, String str2, String str3) {
        if (jSONObject == null) {
            return;
        }
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(str);
        if (jSONObjectOptJSONObject == null) {
            try {
                jSONObjectOptJSONObject = new JSONObject();
                jSONObject.put(str, jSONObjectOptJSONObject);
            } catch (Throwable unused) {
                return;
            }
        }
        jSONObjectOptJSONObject.put(str2, str3);
    }

    public static void a(JSONObject jSONObject, Throwable th) {
        String str = "npth_err_info";
        if (jSONObject.opt("npth_err_info") != null) {
            for (int i = 0; i < 5; i++) {
                if (jSONObject.opt("npth_err_info" + i) == null) {
                    try {
                        str = "npth_err_info" + i;
                    } catch (Throwable unused) {
                        return;
                    }
                }
            }
            return;
        }
        jSONObject.put(str, v.a(th));
    }

    public static void a(JSONObject jSONObject, Map<? extends String, ? extends String> map) {
        if (map != null) {
            try {
                for (Map.Entry<? extends String, ? extends String> entry : map.entrySet()) {
                    jSONObject.put(entry.getKey(), entry.getValue());
                }
            } catch (Throwable unused) {
            }
        }
    }

    public static void a(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            jSONObject.put("storage", jSONObject2);
        } catch (Throwable unused) {
        }
        long jOptLong = jSONObject2.optLong("inner_free");
        long jOptLong2 = jSONObject2.optLong("sdcard_free");
        long jOptLong3 = jSONObject2.optLong("inner_free_real");
        String str = jOptLong <= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID ? "0 - 1K" : jOptLong <= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH ? "1K - 64K" : jOptLong <= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED ? "64K - 512K" : jOptLong <= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED ? "512K - 1M" : jOptLong <= 67108864 ? "1M - 64M" : "64M - ";
        String str2 = jOptLong3 <= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID ? "0 - 1K" : jOptLong3 <= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH ? "1K - 64K" : jOptLong3 <= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED ? "64K - 512K" : jOptLong3 <= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED ? "512K - 1M" : jOptLong3 <= 67108864 ? "1M - 64M" : "64M - ";
        String str3 = jOptLong2 <= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID ? "0 - 1K" : jOptLong2 <= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH ? "1K - 64K" : jOptLong2 <= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED ? "64K - 512K" : jOptLong2 <= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED ? "512K - 1M" : jOptLong2 <= 67108864 ? "1M - 64M" : "64M - ";
        a(jSONObject, "filters", "inner_free", str);
        a(jSONObject, "filters", "inner_free_real", str2);
        a(jSONObject, "filters", "sdcard_free", str3);
    }

    public static boolean a(String str) {
        return o.d(str).exists();
    }

    public static void b(JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        Object objOpt;
        JSONObject jSONObject3;
        JSONObject jSONObject4;
        if (jSONObject == null || jSONObject2 == null || jSONObject2.length() <= 0) {
            return;
        }
        try {
            Iterator<String> itKeys = jSONObject2.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                Object objOpt2 = jSONObject.opt(next);
                if (objOpt2 == null) {
                    objOpt = jSONObject2.opt(next);
                } else {
                    if (objOpt2 instanceof JSONObject) {
                        jSONObject3 = jSONObject.getJSONObject(next);
                        jSONObject4 = jSONObject2.getJSONObject(next);
                    } else if (objOpt2 instanceof JSONArray) {
                        JSONArray jSONArrayOptJSONArray = jSONObject2.optJSONArray(next);
                        if (jSONArrayOptJSONArray != null) {
                            JSONArray jSONArray = (JSONArray) objOpt2;
                            if (jSONArray.length() == 1 && (jSONArray.opt(0) instanceof JSONObject) && (jSONArrayOptJSONArray.opt(0) instanceof JSONObject)) {
                                jSONObject3 = jSONArray.getJSONObject(0);
                                jSONObject4 = jSONArrayOptJSONArray.getJSONObject(0);
                            } else {
                                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                                    jSONArray.put(jSONArrayOptJSONArray.get(i));
                                }
                            }
                        }
                    } else {
                        objOpt = jSONObject2.opt(next);
                    }
                    b(jSONObject3, jSONObject4);
                }
                jSONObject.put(next, objOpt);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static boolean b(String str) {
        return ((long) com.apm.insight.nativecrash.c.c(str)) > com.apm.insight.nativecrash.b.i();
    }

    public static boolean c(String str) {
        return com.apm.insight.nativecrash.c.a(str) > 960;
    }

    public static boolean d(String str) {
        return com.apm.insight.nativecrash.c.b(str) > 350;
    }

    public a a(int i, String str) throws JSONException {
        try {
            this.f2833a.put("miniapp_id", i);
            this.f2833a.put("miniapp_version", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public a a(long j) {
        try {
            a("app_start_time", Long.valueOf(j));
            a("app_start_time_readable", (Object) new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault()).format(new Date(j)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public a a(Header header) {
        a("header", header.f());
        this.f2834b = header;
        return this;
    }

    public a a(com.apm.insight.runtime.a.b bVar) throws JSONException {
        a("activity_trace", bVar.g());
        a("activity_track", bVar.i());
        return this;
    }

    public a a(String str, String str2) {
        Object objOpt = h().opt("data");
        a(objOpt instanceof JSONArray ? ((JSONArray) objOpt).optJSONObject(0) : h(), "filters", str, str2);
        return this;
    }

    public a a(String str, JSONArray jSONArray) throws JSONException {
        JSONObject jSONObjectOptJSONObject = h().optJSONObject("custom_long");
        if (jSONObjectOptJSONObject == null) {
            jSONObjectOptJSONObject = new JSONObject();
            a("custom_long", jSONObjectOptJSONObject);
        }
        try {
            jSONObjectOptJSONObject.put(str, jSONArray);
        } catch (JSONException unused) {
        }
        return this;
    }

    public a a(List<String> list) {
        JSONArray jSONArray = new JSONArray();
        if (list != null && !list.isEmpty()) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next());
            }
        }
        a("patch_info", (Object) jSONArray);
        return this;
    }

    public a a(Map<String, Integer> map) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        if (map == null) {
            this.f2833a.put("plugin_info", jSONArray);
            return this;
        }
        for (String str : map.keySet()) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(h.t, str);
            jSONObject.put("version_code", map.get(str));
            jSONArray.put(jSONObject);
        }
        this.f2833a.put("plugin_info", jSONArray);
        return this;
    }

    public a a(JSONObject jSONObject) {
        a("header", jSONObject);
        return this;
    }

    public void a(@NonNull String str, @Nullable Object obj) {
        try {
            this.f2833a.put(str, obj);
        } catch (Exception e) {
            q.b((Throwable) e);
        }
    }

    public boolean a() {
        return h().opt("data") instanceof JSONArray ? !l.a(((JSONArray) r0).optJSONObject(0), "logcat") : !l.a(this.f2833a, "logcat");
    }

    public a b(String str, String str2) {
        Object objOpt = h().opt("data");
        a(objOpt instanceof JSONArray ? ((JSONArray) objOpt).optJSONObject(0) : h(), "custom", str, str2);
        return this;
    }

    public a b(Map<Integer, String> map) throws JSONException {
        if (map != null && map.size() > 0) {
            JSONObject jSONObject = new JSONObject();
            for (Integer num : map.keySet()) {
                try {
                    jSONObject.put(String.valueOf(num), map.get(num));
                } catch (JSONException e) {
                    q.b((Throwable) e);
                }
            }
            try {
                this.f2833a.put("sdk_info", jSONObject);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return this;
    }

    public a b(JSONObject jSONObject) {
        a(this.f2833a, jSONObject);
        return this;
    }

    public void b() {
        a("has_logcat", String.valueOf(a()));
    }

    public a c(Map<? extends String, ? extends String> map) throws JSONException {
        if (map != null) {
            JSONObject jSONObjectE = e("filters");
            for (Map.Entry<? extends String, ? extends String> entry : map.entrySet()) {
                try {
                    jSONObjectE.put(entry.getKey(), entry.getValue());
                } catch (JSONException unused) {
                }
            }
            a("filters", jSONObjectE);
        }
        return this;
    }

    public void c() {
        a("is_64_devices", String.valueOf(Header.a()));
        a("is_64_runtime", String.valueOf(NativeImpl.e()));
        a("is_x86_devices", String.valueOf(Header.b()));
    }

    public void c(@NonNull JSONObject jSONObject) throws JSONException {
        b(this.f2833a, jSONObject);
    }

    public boolean d() {
        return c(com.apm.insight.h.f());
    }

    public JSONObject e(String str) {
        Object objOpt = h().opt("data");
        JSONObject jSONObjectOptJSONObject = objOpt instanceof JSONArray ? ((JSONArray) objOpt).optJSONObject(0) : h();
        if (jSONObjectOptJSONObject == null) {
            return new JSONObject();
        }
        JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject(str);
        if (jSONObjectOptJSONObject2 != null) {
            return jSONObjectOptJSONObject2;
        }
        JSONObject jSONObject = new JSONObject();
        a(str, jSONObject);
        return jSONObject;
    }

    public boolean e() {
        return d(com.apm.insight.h.f());
    }

    public boolean f() {
        return b(com.apm.insight.h.f());
    }

    public boolean g() {
        return a(com.apm.insight.h.f());
    }

    public JSONObject h() {
        return this.f2833a;
    }

    public Header i() {
        if (this.f2834b == null) {
            this.f2834b = new Header(com.apm.insight.h.g());
            a(this.f2834b);
        }
        return this.f2834b;
    }
}
