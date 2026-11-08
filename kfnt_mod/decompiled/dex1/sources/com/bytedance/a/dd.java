package com.bytedance.a;

import android.content.ContentValues;
import android.database.Cursor;
import androidx.annotation.NonNull;
import com.bytedance.a.ce;
import com.bytedance.applog.AppLog;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class dd extends bu {
    public byte[] m;
    public int n;
    public int o;
    public JSONArray p;
    public long q;
    public JSONArray r;
    public long s;
    public cy t;
    public JSONArray u;
    public dx v;
    public JSONObject w;
    public JSONArray x;
    public long y;
    public JSONArray z;

    @Override // com.bytedance.a.bu
    public int a(@NonNull Cursor cursor) {
        this.f3433b = cursor.getLong(0);
        this.f3434c = cursor.getLong(1);
        this.m = cursor.getBlob(2);
        this.n = cursor.getInt(3);
        this.k = cursor.getInt(4);
        this.e = "";
        this.w = null;
        this.t = null;
        this.v = null;
        this.u = null;
        this.p = null;
        this.r = null;
        this.x = null;
        this.z = null;
        return 5;
    }

    @Override // com.bytedance.a.bu
    public List<String> a() {
        return Arrays.asList("_id", "integer primary key autoincrement", "local_time_ms", "integer", "_data", "blob", "_fail", "integer", "event_type", "integer");
    }

    @Override // com.bytedance.a.bu
    public void a(@NonNull ContentValues contentValues) {
        contentValues.put("local_time_ms", Long.valueOf(this.f3434c));
        contentValues.put("_data", j());
        contentValues.put("event_type", Integer.valueOf(this.k));
    }

    @Override // com.bytedance.a.bu
    public void a(@NonNull JSONObject jSONObject) {
        de.a("U SHALL NOT PASS!", null);
    }

    public void a(JSONObject jSONObject, cy cyVar, dx dxVar, JSONArray jSONArray, JSONArray[] jSONArrayArr, long[] jArr, JSONArray jSONArray2, int i) {
        a(0L);
        this.w = jSONObject;
        this.t = cyVar;
        this.v = dxVar;
        this.u = jSONArray;
        this.p = jSONArrayArr[0];
        this.q = jArr[0];
        this.r = jSONArrayArr[1];
        this.s = jArr[1];
        this.x = jSONArrayArr[2];
        this.y = jArr[2];
        this.z = jSONArray2;
        this.k = i;
    }

    @Override // com.bytedance.a.bu
    public bu b(@NonNull JSONObject jSONObject) {
        de.a("U SHALL NOT PASS!", null);
        return null;
    }

    @Override // com.bytedance.a.bu
    public JSONObject b() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("magic_tag", "ss_app_log");
        jSONObject.put("header", this.w);
        jSONObject.put("time_sync", bf.f3399b);
        jSONObject.put("local_time", System.currentTimeMillis() / 1000);
        if (this.t != null) {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(this.t.f());
            jSONObject.put("launch", jSONArray);
        }
        dx dxVar = this.v;
        if (dxVar != null) {
            JSONObject jSONObjectF = dxVar.f();
            JSONArray jSONArray2 = this.u;
            int length = jSONArray2 != null ? jSONArray2.length() : 0;
            JSONArray jSONArray3 = new JSONArray();
            for (int i = 0; i < length; i++) {
                JSONArray jSONArray4 = new JSONArray();
                JSONObject jSONObject2 = new JSONObject(new JSONObject(this.u.optString(i)).optString("params"));
                jSONArray4.put(0, jSONObject2.optString("page_key", ""));
                jSONArray4.put(1, (jSONObject2.optInt("duration", 0) + com.bytedance.bdturing.a.i) / 1000);
                jSONArray3.put(jSONArray4);
            }
            if (length > 0) {
                jSONObjectF.put("activites", jSONArray3);
            }
            int i2 = AppLog.sLaunchFrom;
            if (i2 > 0) {
                jSONObjectF.put("launch_from", i2);
                AppLog.sLaunchFrom = 0;
            }
            JSONArray jSONArray5 = new JSONArray();
            jSONArray5.put(jSONObjectF);
            jSONObject.put("terminate", jSONArray5);
        }
        JSONArray jSONArray6 = this.p;
        int length2 = jSONArray6 != null ? jSONArray6.length() : 0;
        if (length2 > 0) {
            jSONObject.put("event", this.p);
        }
        JSONArray jSONArray7 = this.u;
        int length3 = jSONArray7 != null ? jSONArray7.length() : 0;
        if (dg.a()) {
            if (this.r == null) {
                this.r = this.u;
            } else if (length3 > 0) {
                for (int i3 = 0; i3 < length3; i3++) {
                    this.r.put(this.u.get(i3));
                }
            }
        }
        JSONArray jSONArray8 = this.r;
        int length4 = jSONArray8 != null ? jSONArray8.length() : 0;
        if (length4 > 0) {
            jSONObject.put("event_v3", this.r);
        }
        JSONArray jSONArray9 = this.x;
        int length5 = jSONArray9 != null ? jSONArray9.length() : 0;
        if (length5 > 0) {
            jSONObject.put("log_data", this.x);
        }
        JSONArray jSONArray10 = this.z;
        int length6 = jSONArray10 != null ? jSONArray10.length() : 0;
        if (length6 > 0) {
            jSONObject.put("item_impression", this.z);
        }
        StringBuilder sb = new StringBuilder("pack {");
        sb.append("ts:");
        sb.append(this.f3434c);
        sb.append(", la:");
        Object obj = this.t;
        if (obj == null) {
            obj = OnekeyLoginConstants.CU_RESULT_SUCCESS;
        }
        sb.append(obj);
        sb.append(", te:");
        Object obj2 = this.v;
        if (obj2 == null) {
            obj2 = OnekeyLoginConstants.CU_RESULT_SUCCESS;
        }
        sb.append(obj2);
        sb.append(", p:");
        sb.append(length3);
        sb.append(", v1:");
        sb.append(length2);
        sb.append(", v3:");
        sb.append(length4);
        sb.append(", m:");
        sb.append(length5);
        sb.append(", imp:");
        sb.append(length6);
        sb.append(com.alipay.sdk.util.f.d);
        de.a(sb.toString(), null);
        return jSONObject;
    }

    @Override // com.bytedance.a.bu
    @NonNull
    public String d() {
        return "pack";
    }

    @Override // com.bytedance.a.bu
    public String h() {
        return String.valueOf(this.f3433b);
    }

    public byte[] j() {
        this.m = null;
        try {
            this.m = m.a(f().toString());
            return this.m;
        } catch (OutOfMemoryError e) {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (true) {
                ce.b[] bVarArr = ce.d;
                if (i >= bVarArr.length) {
                    throw new RuntimeException(sb.toString(), e);
                }
                if (bVarArr[i] != null) {
                    sb.append(bVarArr[i].toString());
                    sb.append(com.alipay.sdk.util.f.f2657b);
                }
                i++;
            }
        }
    }

    public static byte[] a(ArrayList<bu> arrayList, JSONObject jSONObject) {
        JSONArray jSONArray;
        try {
            dd ddVar = new dd();
            JSONArray[] jSONArrayArr = {new JSONArray(), new JSONArray(), null};
            long[] jArr = new long[3];
            Iterator<bu> it = arrayList.iterator();
            while (it.hasNext()) {
                bu next = it.next();
                if ("event".equals(next.d())) {
                    jSONArray = jSONArrayArr[0];
                } else if ("eventv3".equals(next.d())) {
                    jSONArray = jSONArrayArr[1];
                }
                jSONArray.put(next.b());
            }
            ddVar.a(jSONObject, null, null, null, jSONArrayArr, jArr, null, 0);
            return ddVar.f().toString().getBytes();
        } catch (JSONException e) {
            de.a("U SHALL NOT PASS!", e);
            return null;
        }
    }
}
