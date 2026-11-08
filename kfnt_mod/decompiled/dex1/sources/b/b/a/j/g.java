package b.b.a.j;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.bytedance.common.utility.NetworkUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class g extends b {
    public String l;
    public boolean m;
    public String n;

    public g(String str, boolean z, String str2) {
        this.n = str;
        this.m = z;
        this.l = str2;
    }

    @Override // b.b.a.j.b
    public int a(@NonNull Cursor cursor) {
        super.a(cursor);
        this.n = cursor.getString(9);
        this.l = cursor.getString(10);
        this.m = cursor.getInt(11) == 1;
        return 12;
    }

    @Override // b.b.a.j.b
    public List<String> a() {
        List<String> listA = super.a();
        ArrayList arrayList = new ArrayList(listA.size());
        arrayList.addAll(listA);
        arrayList.addAll(Arrays.asList("event", "varchar", "params", "varchar", "is_bav", "integer"));
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
        jSONObject.put("event", this.n);
        if (this.m) {
            jSONObject.put("is_bav", 1);
        }
        boolean z = this.m;
        if (!TextUtils.isEmpty(this.l)) {
            jSONObject.put("params", new JSONObject(this.l));
        }
        if (this.j != NetworkUtils.NetworkType.UNKNOWN.getValue()) {
            jSONObject.put("nt", this.j);
        }
        jSONObject.put("datetime", this.k);
        if (!TextUtils.isEmpty(this.i)) {
            jSONObject.put("ab_sdk_version", this.i);
        }
        return jSONObject;
    }

    @Override // b.b.a.j.b
    @NonNull
    public String d() {
        return "eventv3";
    }

    @Override // b.b.a.j.b
    public String g() {
        return this.n;
    }

    @Override // b.b.a.j.b
    public String h() {
        return this.l;
    }

    @Override // b.b.a.j.b
    public b a(@NonNull JSONObject jSONObject) {
        super.a(jSONObject);
        this.n = jSONObject.optString("event", null);
        this.l = jSONObject.optString("params", null);
        this.m = jSONObject.optBoolean("is_bav", false);
        return this;
    }

    @Override // b.b.a.j.b
    public void a(@NonNull ContentValues contentValues) {
        super.a(contentValues);
        contentValues.put("event", this.n);
        boolean z = this.m;
        contentValues.put("params", this.l);
        contentValues.put("is_bav", Integer.valueOf(this.m ? 1 : 0));
    }
}
