package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b {
    private static volatile b a;

    /* renamed from: a, reason: collision with other field name */
    private Context f79a;

    /* renamed from: a, reason: collision with other field name */
    private a f80a;

    /* renamed from: a, reason: collision with other field name */
    String f81a;

    /* renamed from: a, reason: collision with other field name */
    private Map<String, a> f82a;

    public static class a {

        /* renamed from: a, reason: collision with other field name */
        private Context f83a;

        /* renamed from: a, reason: collision with other field name */
        public String f84a;
        public String b;
        public String c;
        public String d;
        public String e;
        public String f;
        public String g;
        public String h;

        /* renamed from: a, reason: collision with other field name */
        public boolean f85a = true;

        /* renamed from: b, reason: collision with other field name */
        public boolean f86b = false;
        public int a = 1;

        public a(Context context) {
            this.f83a = context;
        }

        public static a a(Context context, String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                a aVar = new a(context);
                aVar.f84a = jSONObject.getString(com.sigmob.sdk.common.Constants.APPID);
                aVar.b = jSONObject.getString("appToken");
                aVar.c = jSONObject.getString("regId");
                aVar.d = jSONObject.getString("regSec");
                aVar.f = jSONObject.getString("devId");
                aVar.e = jSONObject.getString("vName");
                aVar.f85a = jSONObject.getBoolean("valid");
                aVar.f86b = jSONObject.getBoolean("paused");
                aVar.a = jSONObject.getInt("envType");
                aVar.g = jSONObject.getString("regResource");
                return aVar;
            } catch (Throwable th) {
                com.xiaomi.channel.commonutils.logger.b.a(th);
                return null;
            }
        }

        private String a() {
            return com.xiaomi.push.g.m345a(this.f83a, this.f83a.getPackageName());
        }

        public static String a(a aVar) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(com.sigmob.sdk.common.Constants.APPID, aVar.f84a);
                jSONObject.put("appToken", aVar.b);
                jSONObject.put("regId", aVar.c);
                jSONObject.put("regSec", aVar.d);
                jSONObject.put("devId", aVar.f);
                jSONObject.put("vName", aVar.e);
                jSONObject.put("valid", aVar.f85a);
                jSONObject.put("paused", aVar.f86b);
                jSONObject.put("envType", aVar.a);
                jSONObject.put("regResource", aVar.g);
                return jSONObject.toString();
            } catch (Throwable th) {
                com.xiaomi.channel.commonutils.logger.b.a(th);
                return null;
            }
        }

        /* renamed from: a, reason: collision with other method in class */
        public void m75a() {
            b.a(this.f83a).edit().clear().commit();
            this.f84a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.f = null;
            this.e = null;
            this.f85a = false;
            this.f86b = false;
            this.h = null;
            this.a = 1;
        }

        public void a(int i) {
            this.a = i;
        }

        public void a(String str, String str2) {
            this.c = str;
            this.d = str2;
            this.f = com.xiaomi.push.i.l(this.f83a);
            this.e = a();
            this.f85a = true;
        }

        public void a(String str, String str2, String str3) {
            this.f84a = str;
            this.b = str2;
            this.g = str3;
            SharedPreferences.Editor editorEdit = b.a(this.f83a).edit();
            editorEdit.putString(com.sigmob.sdk.common.Constants.APPID, this.f84a);
            editorEdit.putString("appToken", str2);
            editorEdit.putString("regResource", str3);
            editorEdit.commit();
        }

        public void a(boolean z) {
            this.f86b = z;
        }

        /* renamed from: a, reason: collision with other method in class */
        public boolean m76a() {
            return m77a(this.f84a, this.b);
        }

        /* renamed from: a, reason: collision with other method in class */
        public boolean m77a(String str, String str2) {
            return TextUtils.equals(this.f84a, str) && TextUtils.equals(this.b, str2) && !TextUtils.isEmpty(this.c) && !TextUtils.isEmpty(this.d) && (TextUtils.equals(this.f, com.xiaomi.push.i.l(this.f83a)) || TextUtils.equals(this.f, com.xiaomi.push.i.k(this.f83a)));
        }

        public void b() {
            this.f85a = false;
            b.a(this.f83a).edit().putBoolean("valid", this.f85a).commit();
        }

        public void b(String str, String str2, String str3) {
            this.c = str;
            this.d = str2;
            this.f = com.xiaomi.push.i.l(this.f83a);
            this.e = a();
            this.f85a = true;
            this.h = str3;
            SharedPreferences.Editor editorEdit = b.a(this.f83a).edit();
            editorEdit.putString("regId", str);
            editorEdit.putString("regSec", str2);
            editorEdit.putString("devId", this.f);
            editorEdit.putString("vName", a());
            editorEdit.putBoolean("valid", true);
            editorEdit.putString("appRegion", str3);
            editorEdit.commit();
        }

        public void c(String str, String str2, String str3) {
            this.f84a = str;
            this.b = str2;
            this.g = str3;
        }
    }

    private b(Context context) {
        this.f79a = context;
        c();
    }

    public static SharedPreferences a(Context context) {
        return context.getSharedPreferences("mipush", 0);
    }

    /* renamed from: a, reason: collision with other method in class */
    public static b m63a(Context context) {
        if (a == null) {
            synchronized (b.class) {
                if (a == null) {
                    a = new b(context);
                }
            }
        }
        return a;
    }

    private void c() {
        this.f80a = new a(this.f79a);
        this.f82a = new HashMap();
        SharedPreferences sharedPreferencesA = a(this.f79a);
        this.f80a.f84a = sharedPreferencesA.getString(com.sigmob.sdk.common.Constants.APPID, null);
        this.f80a.b = sharedPreferencesA.getString("appToken", null);
        this.f80a.c = sharedPreferencesA.getString("regId", null);
        this.f80a.d = sharedPreferencesA.getString("regSec", null);
        this.f80a.f = sharedPreferencesA.getString("devId", null);
        if (!TextUtils.isEmpty(this.f80a.f) && com.xiaomi.push.i.m436a(this.f80a.f)) {
            this.f80a.f = com.xiaomi.push.i.l(this.f79a);
            sharedPreferencesA.edit().putString("devId", this.f80a.f).commit();
        }
        this.f80a.e = sharedPreferencesA.getString("vName", null);
        this.f80a.f85a = sharedPreferencesA.getBoolean("valid", true);
        this.f80a.f86b = sharedPreferencesA.getBoolean("paused", false);
        this.f80a.a = sharedPreferencesA.getInt("envType", 1);
        this.f80a.g = sharedPreferencesA.getString("regResource", null);
        this.f80a.h = sharedPreferencesA.getString("appRegion", null);
    }

    public int a() {
        return this.f80a.a;
    }

    public a a(String str) {
        if (this.f82a.containsKey(str)) {
            return this.f82a.get(str);
        }
        String str2 = "hybrid_app_info_" + str;
        SharedPreferences sharedPreferencesA = a(this.f79a);
        if (!sharedPreferencesA.contains(str2)) {
            return null;
        }
        a aVarA = a.a(this.f79a, sharedPreferencesA.getString(str2, ""));
        this.f82a.put(str2, aVarA);
        return aVarA;
    }

    /* renamed from: a, reason: collision with other method in class */
    public String m64a() {
        return this.f80a.f84a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m65a() {
        this.f80a.m75a();
    }

    public void a(int i) {
        this.f80a.a(i);
        a(this.f79a).edit().putInt("envType", i).commit();
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m66a(String str) {
        SharedPreferences.Editor editorEdit = a(this.f79a).edit();
        editorEdit.putString("vName", str);
        editorEdit.commit();
        this.f80a.e = str;
    }

    public void a(String str, a aVar) {
        this.f82a.put(str, aVar);
        a(this.f79a).edit().putString("hybrid_app_info_" + str, a.a(aVar)).commit();
    }

    public void a(String str, String str2, String str3) {
        this.f80a.a(str, str2, str3);
    }

    public void a(boolean z) {
        this.f80a.a(z);
        a(this.f79a).edit().putBoolean("paused", z).commit();
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m67a() {
        return !TextUtils.equals(com.xiaomi.push.g.m345a(this.f79a, this.f79a.getPackageName()), this.f80a.e);
    }

    public boolean a(String str, String str2) {
        return this.f80a.m77a(str, str2);
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m68a(String str, String str2, String str3) {
        a aVarA = a(str3);
        return aVarA != null && TextUtils.equals(str, aVarA.f84a) && TextUtils.equals(str2, aVarA.b);
    }

    public String b() {
        return this.f80a.b;
    }

    /* renamed from: b, reason: collision with other method in class */
    public void m69b() {
        this.f80a.b();
    }

    public void b(String str) {
        this.f82a.remove(str);
        a(this.f79a).edit().remove("hybrid_app_info_" + str).commit();
    }

    public void b(String str, String str2, String str3) {
        this.f80a.b(str, str2, str3);
    }

    /* renamed from: b, reason: collision with other method in class */
    public boolean m70b() {
        if (this.f80a.m76a()) {
            return true;
        }
        com.xiaomi.channel.commonutils.logger.b.m22a("Don't send message before initialization succeeded!");
        return false;
    }

    /* renamed from: c, reason: collision with other method in class */
    public String m71c() {
        return this.f80a.c;
    }

    /* renamed from: c, reason: collision with other method in class */
    public boolean m72c() {
        return this.f80a.m76a();
    }

    public String d() {
        return this.f80a.d;
    }

    /* renamed from: d, reason: collision with other method in class */
    public boolean m73d() {
        return this.f80a.f86b;
    }

    public String e() {
        return this.f80a.g;
    }

    /* renamed from: e, reason: collision with other method in class */
    public boolean m74e() {
        return !this.f80a.f85a;
    }

    public String f() {
        return this.f80a.h;
    }
}
