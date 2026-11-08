package com.bytedance.a;

import android.accounts.AccountManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.CookieManager;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class h {

    /* renamed from: a, reason: collision with root package name */
    public static volatile h f3526a;

    /* renamed from: b, reason: collision with root package name */
    public volatile boolean f3527b = false;

    /* renamed from: c, reason: collision with root package name */
    public volatile boolean f3528c = false;
    public ConcurrentHashMap<String, String> d = new ConcurrentHashMap<>();
    public WeakReference<Context> e;
    public volatile String f;

    public h(Context context) {
        String userData;
        this.e = new WeakReference<>(context);
        if (b()) {
            String string = this.e.get().getSharedPreferences("debug_new_user_mode_sp", 0).getString("new_user_mode_json_str", "");
            if (TextUtils.isEmpty(string)) {
                Context context2 = this.e.get();
                try {
                    userData = AccountManager.get(context2).getUserData(m.a(context2), "new_user_mode_account");
                } catch (Throwable th) {
                    de.a("", th);
                    userData = "";
                }
                string = (userData == null || !userData.startsWith("newUserModeUtil:")) ? "" : userData.substring(16);
            }
            try {
                a(string);
            } catch (JSONException e) {
                de.a("", e);
            }
            SharedPreferences.Editor editorEdit = this.e.get().getSharedPreferences("debug_new_user_mode_sp", 0).edit();
            editorEdit.putString("new_user_mode_json_str", string);
            editorEdit.apply();
        }
    }

    public static h a(Context context) {
        if (f3526a == null) {
            synchronized (h.class) {
                if (f3526a == null) {
                    f3526a = new h(context);
                }
            }
        }
        return f3526a;
    }

    public void a() {
        if (b()) {
            SharedPreferences.Editor editorEdit = this.e.get().getSharedPreferences("device_param_fake", 0).edit();
            editorEdit.clear();
            editorEdit.apply();
            if (Build.VERSION.SDK_INT >= 21) {
                CookieManager.getInstance().removeAllCookies(null);
            }
            SharedPreferences.Editor editorEdit2 = this.e.get().getSharedPreferences("cookieStore", 0).edit();
            editorEdit2.clear();
            editorEdit2.apply();
            SharedPreferences.Editor editorEdit3 = this.e.get().getSharedPreferences("tt_new_token_save_sp", 0).edit();
            editorEdit3.putString("X-Tt-Token", "");
            editorEdit3.apply();
            SharedPreferences.Editor editorEdit4 = this.e.get().getSharedPreferences("com.ss.android.deviceregister.utils.Cdid", 0).edit();
            editorEdit4.putString("cdid", "");
            editorEdit4.apply();
        }
    }

    public final void a(String str) {
        JSONObject jSONObject = new JSONObject(str);
        this.f3527b = jSONObject.optBoolean("is_new_user_mode", false);
        this.f3528c = jSONObject.optBoolean("auto_mode", false);
        JSONObject jSONObject2 = new JSONObject(jSONObject.optString("debug_custom_param", ""));
        Iterator<String> itKeys = jSONObject2.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            this.d.put(next, jSONObject2.optString(next));
        }
    }

    public final boolean b() {
        az azVar;
        if (TextUtils.isEmpty(this.f)) {
            if (TextUtils.isEmpty(bo.f3418b) && (azVar = bo.e) != null) {
                bo.f3418b = azVar.a();
            }
            this.f = bo.f3418b;
        }
        return "local_test".equals(this.f);
    }

    public final String c() {
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<String, String> entry : this.d.entrySet()) {
            jSONObject.put(entry.getKey(), entry.getValue());
        }
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("is_new_user_mode", this.f3527b);
        jSONObject2.put("auto_mode", this.f3528c);
        jSONObject2.put("debug_custom_param", jSONObject.toString());
        return jSONObject2.toString();
    }
}
