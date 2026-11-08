package b.b.a.j;

import android.content.ContentValues;
import android.database.Cursor;
import androidx.annotation.NonNull;
import b.b.a.j.d;
import b.b.a.k.v;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class i extends b {
    public byte[] l;
    public int m;
    public int n;
    public JSONArray o;
    public long p;
    public JSONArray q;
    public long r;
    public h s;
    public JSONArray t;
    public m u;
    public JSONObject v;
    public JSONArray w;
    public long x;
    public JSONArray y;

    public static byte[] a(ArrayList<b> arrayList, JSONObject jSONObject) {
        try {
            i iVar = new i();
            JSONArray[] jSONArrayArr = {new JSONArray(), new JSONArray(), null};
            long[] jArr = new long[3];
            Iterator<b> it = arrayList.iterator();
            while (it.hasNext()) {
                b next = it.next();
                if ("event".equals(next.d())) {
                    jSONArrayArr[0].put(next.b());
                } else if ("eventv3".equals(next.d())) {
                    jSONArrayArr[1].put(next.b());
                }
            }
            iVar.a(jSONObject, null, null, null, jSONArrayArr, jArr, null);
            return iVar.e().toString().getBytes();
        } catch (JSONException e) {
            v.a(e);
            return null;
        }
    }

    @Override // b.b.a.j.b
    public List<String> a() {
        return Arrays.asList("_id", "integer primary key autoincrement", "local_time_ms", "integer", "_data", "blob", "_fail", "integer");
    }

    @Override // b.b.a.j.b
    public JSONObject b() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("magic_tag", "ss_app_log");
        jSONObject.put("header", this.v);
        jSONObject.put("time_sync", b.b.a.i.a.f1096b);
        jSONObject.put("local_time", System.currentTimeMillis() / 1000);
        if (this.s != null) {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(this.s.e());
            jSONObject.put("launch", jSONArray);
        }
        m mVar = this.u;
        if (mVar != null) {
            JSONObject jSONObjectE = mVar.e();
            JSONArray jSONArray2 = this.t;
            int length = jSONArray2 != null ? jSONArray2.length() : 0;
            JSONArray jSONArray3 = new JSONArray();
            for (int i = 0; i < length; i++) {
                JSONArray jSONArray4 = new JSONArray();
                JSONObject jSONObject2 = new JSONObject(new JSONObject(this.t.optString(i)).optString("params"));
                jSONArray4.put(0, jSONObject2.optString("page_key", ""));
                jSONArray4.put(1, (jSONObject2.optInt("duration", 0) + com.bytedance.bdturing.a.i) / 1000);
                jSONArray3.put(jSONArray4);
            }
            if (length > 0) {
                jSONObjectE.put("activites", jSONArray3);
            }
            int i2 = com.apm.applog.a.f2739b;
            if (i2 > 0) {
                jSONObjectE.put("launch_from", i2);
                com.apm.applog.a.f2739b = 0;
            }
            JSONArray jSONArray5 = new JSONArray();
            jSONArray5.put(jSONObjectE);
            jSONObject.put("terminate", jSONArray5);
        }
        JSONArray jSONArray6 = this.o;
        int length2 = jSONArray6 != null ? jSONArray6.length() : 0;
        if (length2 > 0) {
            jSONObject.put("event", this.o);
        }
        JSONArray jSONArray7 = this.t;
        int length3 = jSONArray7 != null ? jSONArray7.length() : 0;
        b.b.a.d.b.a();
        JSONArray jSONArray8 = this.q;
        int length4 = jSONArray8 != null ? jSONArray8.length() : 0;
        if (length4 > 0) {
            jSONObject.put("event_v3", this.q);
        }
        JSONArray jSONArray9 = this.w;
        int length5 = jSONArray9 != null ? jSONArray9.length() : 0;
        if (length5 > 0) {
            jSONObject.put("log_data", this.w);
        }
        JSONArray jSONArray10 = this.y;
        int length6 = jSONArray10 != null ? jSONArray10.length() : 0;
        if (length6 > 0) {
            jSONObject.put("item_impression", this.y);
        }
        StringBuilder sb = new StringBuilder("pack {");
        sb.append("ts:");
        sb.append(this.f1110c);
        sb.append(", la:");
        Object obj = this.s;
        if (obj == null) {
            obj = OnekeyLoginConstants.CU_RESULT_SUCCESS;
        }
        sb.append(obj);
        sb.append(", te:");
        Object obj2 = this.u;
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
        v.a(sb.toString());
        return jSONObject;
    }

    @Override // b.b.a.j.b
    @NonNull
    public String d() {
        return "pack";
    }

    @Override // b.b.a.j.b
    public String g() {
        return String.valueOf(this.f1109b);
    }

    public byte[] i() {
        this.l = null;
        try {
            this.l = b.b.a.a.b.a(e().toString());
            return this.l;
        } catch (OutOfMemoryError e) {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (true) {
                d.b[] bVarArr = d.f1117c;
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

    @Override // b.b.a.j.b
    public void a(@NonNull ContentValues contentValues) {
        contentValues.put("local_time_ms", Long.valueOf(this.f1110c));
        contentValues.put("_data", i());
    }

    public void a(JSONObject jSONObject, h hVar, m mVar, JSONArray jSONArray, JSONArray[] jSONArrayArr, long[] jArr, JSONArray jSONArray2) {
        a(0L);
        this.v = jSONObject;
        this.s = hVar;
        this.u = mVar;
        this.t = jSONArray;
        this.o = jSONArrayArr[0];
        this.p = jArr[0];
        this.q = jSONArrayArr[1];
        this.r = jArr[1];
        this.w = jSONArrayArr[2];
        this.x = jArr[2];
        this.y = jSONArray2;
    }

    @Override // b.b.a.j.b
    public int a(@NonNull Cursor cursor) {
        this.f1109b = cursor.getLong(0);
        this.f1110c = cursor.getLong(1);
        this.l = cursor.getBlob(2);
        this.m = cursor.getInt(3);
        this.e = "";
        this.v = null;
        this.s = null;
        this.u = null;
        this.t = null;
        this.o = null;
        this.q = null;
        this.w = null;
        this.y = null;
        return 4;
    }

    @Override // b.b.a.j.b
    public b a(@NonNull JSONObject jSONObject) {
        v.a((Throwable) null);
        return null;
    }
}
