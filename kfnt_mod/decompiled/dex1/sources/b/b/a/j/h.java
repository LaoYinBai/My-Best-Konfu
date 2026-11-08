package b.b.a.j;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import b.b.a.k.v;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class h extends b {
    public int l;
    public String m;
    public boolean n;
    public String o;
    public int p;

    @Override // b.b.a.j.b
    public int a(@NonNull Cursor cursor) {
        super.a(cursor);
        this.m = cursor.getString(9);
        this.l = cursor.getInt(10);
        this.o = cursor.getString(11);
        this.p = cursor.getInt(12);
        return 13;
    }

    @Override // b.b.a.j.b
    public List<String> a() {
        List<String> listA = super.a();
        ArrayList arrayList = new ArrayList(listA.size());
        arrayList.addAll(listA);
        arrayList.addAll(Arrays.asList("ver_name", "varchar", "ver_code", "integer", "last_session", "varchar", "is_first_time", "integer"));
        return arrayList;
    }

    @Override // b.b.a.j.b
    public JSONObject b() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("local_time_ms", this.f1110c);
        jSONObject.put("tea_event_index", this.d);
        jSONObject.put(com.bytedance.b.a.a.d.p, this.e);
        long j = this.f;
        if (j > 0) {
            jSONObject.put("user_id", j);
        }
        if (!TextUtils.isEmpty(this.g)) {
            jSONObject.put("user_unique_id", this.g);
        }
        if (!TextUtils.isEmpty(this.h)) {
            jSONObject.put("ssid", this.h);
        }
        boolean z = this.n;
        if (z) {
            jSONObject.put("is_background", z);
        }
        jSONObject.put("datetime", this.k);
        if (!TextUtils.isEmpty(this.i)) {
            jSONObject.put("ab_sdk_version", this.i);
        }
        if (!TextUtils.isEmpty(this.o)) {
            jSONObject.put("uuid_changed", true);
            jSONObject.put("original_session_id", this.o);
        }
        if (this.p == 1) {
            jSONObject.put("$is_first_time", "true");
        }
        return jSONObject;
    }

    @Override // b.b.a.j.b
    @NonNull
    public String d() {
        return "launch";
    }

    @Override // b.b.a.j.b
    public String g() {
        return this.n ? "bg" : "fg";
    }

    @Override // b.b.a.j.b
    public void a(@NonNull ContentValues contentValues) {
        super.a(contentValues);
        contentValues.put("ver_name", this.m);
        contentValues.put("ver_code", Integer.valueOf(this.l));
        contentValues.put("last_session", this.o);
        contentValues.put("is_first_time", Integer.valueOf(this.p));
    }

    @Override // b.b.a.j.b
    public b a(@NonNull JSONObject jSONObject) {
        v.a((Throwable) null);
        return null;
    }
}
