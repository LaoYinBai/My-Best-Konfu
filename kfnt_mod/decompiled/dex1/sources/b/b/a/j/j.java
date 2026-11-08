package b.b.a.j;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class j extends b {
    public long l;
    public String m;
    public String n;
    public int o;
    public String p;

    @Override // b.b.a.j.b
    public int a(@NonNull Cursor cursor) {
        super.a(cursor);
        this.n = cursor.getString(9);
        this.m = cursor.getString(10);
        this.l = cursor.getLong(11);
        this.o = cursor.getInt(12);
        this.p = cursor.getString(13);
        return 14;
    }

    @Override // b.b.a.j.b
    public List<String> a() {
        List<String> listA = super.a();
        ArrayList arrayList = new ArrayList(listA.size());
        arrayList.addAll(listA);
        arrayList.addAll(Arrays.asList("page_key", "varchar", "refer_page_key", "varchar", "duration", "integer", "is_back", "integer", "last_session", "varchar"));
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
        jSONObject.put("event", "bav2b_page");
        jSONObject.put("is_bav", 1);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("page_key", this.n);
        jSONObject2.put("refer_page_key", this.m);
        jSONObject2.put("is_back", this.o);
        jSONObject2.put("duration", this.l);
        jSONObject.put("params", jSONObject2);
        jSONObject.put("datetime", this.k);
        return jSONObject;
    }

    @Override // b.b.a.j.b
    @NonNull
    public String d() {
        return "page";
    }

    @Override // b.b.a.j.b
    public String g() {
        return this.n + ", " + this.l;
    }

    public boolean i() {
        return this.l == -1;
    }

    @Override // b.b.a.j.b
    public void a(@NonNull ContentValues contentValues) {
        super.a(contentValues);
        contentValues.put("page_key", this.n);
        contentValues.put("refer_page_key", this.m);
        contentValues.put("duration", Long.valueOf(this.l));
        contentValues.put("is_back", Integer.valueOf(this.o));
        contentValues.put("last_session", this.p);
    }

    @Override // b.b.a.j.b
    public b a(@NonNull JSONObject jSONObject) {
        super.a(jSONObject);
        this.n = jSONObject.optString("page_key", null);
        this.m = jSONObject.optString("refer_page_key", null);
        this.l = jSONObject.optLong("duration", 0L);
        this.o = jSONObject.optInt("is_back", 0);
        return this;
    }
}
