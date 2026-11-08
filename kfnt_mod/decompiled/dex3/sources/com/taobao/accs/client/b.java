package com.taobao.accs.client;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.taobao.accs.utl.ALog;
import com.umeng.commonsdk.proguard.o;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b {
    private Context a;
    private long d;
    private String f;
    private ConcurrentMap<String, Integer> b = new ConcurrentHashMap();
    private ConcurrentMap<String, Set<String>> c = new ConcurrentHashMap();
    private String e = "ClientManager_";

    public b(Context context, String str) throws JSONException {
        this.f = "ACCS_BIND";
        if (context == null) {
            throw new RuntimeException("Context is null!!");
        }
        this.e += str;
        this.a = context.getApplicationContext();
        this.f = "ACCS_BIND" + str;
        a();
    }

    public void a(String str) throws JSONException {
        Integer num = this.b.get(str);
        if (num == null || num.intValue() != 2) {
            this.b.put(str, 2);
            a(this.a, this.f, this.d, this.b);
        }
    }

    public void b(String str) throws JSONException {
        Integer num = this.b.get(str);
        if (num == null || num.intValue() != 4) {
            this.b.put(str, 4);
            a(this.a, this.f, this.d, this.b);
        }
    }

    public boolean c(String str) throws JSONException {
        if (this.b.isEmpty()) {
            a();
        }
        Integer num = this.b.get(str);
        ALog.i(this.e, "isAppBinded", "appStatus", num, "mBindStatus", this.b);
        return num != null && num.intValue() == 2;
    }

    public boolean d(String str) {
        Integer num = this.b.get(str);
        return num != null && num.intValue() == 4;
    }

    public void a(String str, String str2) {
        try {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                Set<String> hashSet = this.c.get(str);
                if (hashSet == null) {
                    hashSet = new HashSet<>();
                }
                hashSet.add(str2);
                this.c.put(str, hashSet);
            }
        } catch (Exception e) {
            ALog.e(this.e, this.e + e.toString(), new Object[0]);
            e.printStackTrace();
        }
    }

    public void e(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            this.c.remove(str);
        } catch (Exception e) {
            ALog.e(this.e, this.e + e.toString(), new Object[0]);
            e.printStackTrace();
        }
    }

    public boolean b(String str, String str2) {
        Set<String> set;
        try {
            if (!TextUtils.isEmpty(str) && (set = this.c.get(str)) != null) {
                if (set.contains(str2)) {
                    return true;
                }
            }
        } catch (Exception e) {
            ALog.e(this.e, this.e + e.toString(), new Object[0]);
            e.printStackTrace();
        }
        return false;
    }

    private void a() throws JSONException {
        try {
            String string = this.a.getSharedPreferences(this.f, 0).getString("bind_status", null);
            if (TextUtils.isEmpty(string)) {
                ALog.w(this.e, "restoreClients break as packages null", new Object[0]);
                return;
            }
            JSONArray jSONArray = new JSONArray(string);
            this.d = jSONArray.getLong(0);
            if (System.currentTimeMillis() < this.d + 86400000) {
                for (int i = 1; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    this.b.put(jSONObject.getString("p"), Integer.valueOf(jSONObject.getInt(o.at)));
                }
                ALog.i(this.e, "restoreClients success", "mBindStatus", this.b);
                return;
            }
            ALog.i(this.e, "restoreClients expired", "lastFlushTime", Long.valueOf(this.d));
            this.d = 0L;
        } catch (Exception e) {
            ALog.w(this.e, "restoreClients", e, new Object[0]);
        }
    }

    public static void a(Context context, String str, long j, Map<String, Integer> map) throws JSONException {
        try {
            String[] strArr = (String[]) map.keySet().toArray(new String[0]);
            JSONArray jSONArray = new JSONArray();
            if (j > 0 && j < System.currentTimeMillis()) {
                jSONArray.put(j);
            } else {
                jSONArray.put(System.currentTimeMillis() - (Math.random() * 8.64E7d));
            }
            for (String str2 : strArr) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("p", str2);
                jSONObject.put(o.at, map.get(str2).intValue());
                jSONArray.put(jSONObject);
            }
            SharedPreferences.Editor editorEdit = context.getSharedPreferences(str, 0).edit();
            editorEdit.putString("bind_status", jSONArray.toString());
            editorEdit.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
