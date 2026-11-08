package b.b.a.j;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.bytedance.common.utility.NetworkUtils;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e extends b {
    public String l;
    public String m;
    public String n;
    public String o;
    public long p;
    public long q;

    public e(String str, String str2, String str3, long j, long j2, String str4) {
        a(0L);
        this.l = str;
        this.m = str2;
        this.n = str3;
        this.p = j;
        this.q = j2;
        this.o = str4;
    }

    @Override // b.b.a.j.b
    public int a(@NonNull Cursor cursor) {
        super.a(cursor);
        this.l = cursor.getString(9);
        this.m = cursor.getString(10);
        this.p = cursor.getLong(11);
        this.q = cursor.getLong(12);
        this.o = cursor.getString(13);
        this.n = cursor.getString(14);
        return 15;
    }

    @Override // b.b.a.j.b
    public List<String> a() {
        List<String> listA = super.a();
        ArrayList arrayList = new ArrayList(listA.size());
        arrayList.addAll(listA);
        arrayList.addAll(Arrays.asList("category", "varchar", "tag", "varchar", com.bytedance.b.a.a.a.n, "integer", "ext_value", "integer", "params", "varchar", TTDownloadField.TT_LABEL, "varchar"));
        return arrayList;
    }

    @Override // b.b.a.j.b
    public JSONObject b() throws JSONException {
        JSONObject jSONObject = !TextUtils.isEmpty(this.o) ? new JSONObject(this.o) : null;
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        jSONObject.put("local_time_ms", this.f1110c);
        jSONObject.put("tea_event_index", this.d);
        jSONObject.put(com.bytedance.b.a.a.d.p, this.e);
        long j = this.f;
        if (j > 0) {
            jSONObject.put("user_id", j);
        }
        if (this.j != NetworkUtils.NetworkType.UNKNOWN.getValue()) {
            jSONObject.put("nt", this.j);
        }
        if (!TextUtils.isEmpty(this.g)) {
            jSONObject.put("user_unique_id", this.g);
        }
        if (!TextUtils.isEmpty(this.h)) {
            jSONObject.put("ssid", this.h);
        }
        jSONObject.put("category", this.l);
        jSONObject.put("tag", this.m);
        jSONObject.put(com.bytedance.b.a.a.a.n, this.p);
        jSONObject.put("ext_value", this.q);
        jSONObject.put(TTDownloadField.TT_LABEL, this.n);
        jSONObject.put("datetime", this.k);
        if (!TextUtils.isEmpty(this.i)) {
            jSONObject.put("ab_sdk_version", this.i);
        }
        return jSONObject;
    }

    @Override // b.b.a.j.b
    @NonNull
    public String d() {
        return "event";
    }

    @Override // b.b.a.j.b
    public String g() {
        StringBuilder sbA = b.a.a.a.a.a("");
        sbA.append(this.m);
        sbA.append(", ");
        sbA.append(this.n);
        return sbA.toString();
    }

    @Override // b.b.a.j.b
    public String h() {
        return this.o;
    }

    @Override // b.b.a.j.b
    public void a(@NonNull ContentValues contentValues) {
        super.a(contentValues);
        contentValues.put("category", this.l);
        contentValues.put("tag", this.m);
        contentValues.put(com.bytedance.b.a.a.a.n, Long.valueOf(this.p));
        contentValues.put("ext_value", Long.valueOf(this.q));
        contentValues.put("params", this.o);
        contentValues.put(TTDownloadField.TT_LABEL, this.n);
    }

    @Override // b.b.a.j.b
    public b a(@NonNull JSONObject jSONObject) {
        super.a(jSONObject);
        this.d = jSONObject.optLong("tea_event_index", 0L);
        this.l = jSONObject.optString("category", null);
        this.m = jSONObject.optString("tag", null);
        this.p = jSONObject.optLong(com.bytedance.b.a.a.a.n, 0L);
        this.q = jSONObject.optLong("ext_value", 0L);
        this.o = jSONObject.optString("params", null);
        this.n = jSONObject.optString(TTDownloadField.TT_LABEL, null);
        return this;
    }

    public e() {
    }
}
