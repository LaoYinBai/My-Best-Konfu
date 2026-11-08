package com.taobao.agoo.a;

import android.content.Context;
import android.text.TextUtils;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.UtilityImpl;
import com.umeng.commonsdk.proguard.o;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    public static final String SP_AGOO_BIND_FILE_NAME = "AGOO_BIND";
    private ConcurrentMap<String, Integer> a = new ConcurrentHashMap();
    private String b;
    private long c;
    private Context d;

    public a(Context context) {
        if (context == null) {
            throw new RuntimeException("Context is null!!");
        }
        this.d = context.getApplicationContext();
    }

    public void a(String str) throws JSONException {
        Integer num = this.a.get(str);
        if (num == null || num.intValue() != 2) {
            this.a.put(str, 2);
            com.taobao.accs.client.b.a(this.d, "AGOO_BIND", this.c, this.a);
        }
    }

    public boolean b(String str) throws JSONException {
        if (this.a.isEmpty()) {
            b();
        }
        Integer num = this.a.get(str);
        ALog.i("AgooBindCache", "isAgooRegistered", "packageName", str, "appStatus", num, "agooBindStatus", this.a);
        return (UtilityImpl.a("Agoo_AppStore", this.d) || num == null || num.intValue() != 2) ? false : true;
    }

    public void c(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.b = str;
    }

    public void a() {
        this.b = null;
    }

    public boolean d(String str) {
        return this.b != null && this.b.equals(str);
    }

    private void b() throws JSONException {
        try {
            String string = this.d.getSharedPreferences("AGOO_BIND", 0).getString("bind_status", null);
            if (TextUtils.isEmpty(string)) {
                ALog.w("AgooBindCache", "restoreAgooClients packs null return", new Object[0]);
                return;
            }
            JSONArray jSONArray = new JSONArray(string);
            this.c = jSONArray.getLong(0);
            if (System.currentTimeMillis() < this.c + 86400000) {
                for (int i = 1; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    this.a.put(jSONObject.getString("p"), Integer.valueOf(jSONObject.getInt(o.at)));
                }
                ALog.i("AgooBindCache", "restoreAgooClients", "mAgooBindStatus", this.a);
                return;
            }
            ALog.i("AgooBindCache", "restoreAgooClients expired", "agooLastFlushTime", Long.valueOf(this.c));
            this.c = 0L;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
