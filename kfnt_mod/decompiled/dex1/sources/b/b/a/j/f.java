package b.b.a.j;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import b.b.a.k.v;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class f extends b {

    @NonNull
    public String l;

    @NonNull
    public String m;

    public f(@NonNull String str, @NonNull JSONObject jSONObject) {
        this.m = str;
        this.l = jSONObject.toString();
    }

    @Override // b.b.a.j.b
    public int a(@NonNull Cursor cursor) {
        super.a(cursor);
        this.l = cursor.getString(9);
        this.m = cursor.getString(10);
        return 11;
    }

    @Override // b.b.a.j.b
    public List<String> a() {
        List<String> listA = super.a();
        ArrayList arrayList = new ArrayList(listA.size());
        arrayList.addAll(listA);
        arrayList.addAll(Arrays.asList("params", "varchar", com.bytedance.b.a.a.d.o, "varchar"));
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
        jSONObject.put(com.bytedance.b.a.a.d.o, this.m);
        try {
            JSONObject jSONObject2 = new JSONObject(this.l);
            Iterator<String> itKeys = jSONObject2.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                Object obj = jSONObject2.get(next);
                if (jSONObject.opt(next) != null) {
                    v.a("misc事件存在重复的key", (Throwable) null);
                }
                jSONObject.put(next, obj);
            }
        } catch (Exception e) {
            v.a("解析 event misc 失败", e);
        }
        return jSONObject;
    }

    @Override // b.b.a.j.b
    @NonNull
    public String d() {
        return "event_misc";
    }

    @Override // b.b.a.j.b
    public String g() {
        StringBuilder sbA = b.a.a.a.a.a("param:");
        sbA.append(this.l);
        sbA.append(" logType:");
        sbA.append(this.m);
        return sbA.toString();
    }

    @Override // b.b.a.j.b
    public String h() {
        return this.l;
    }

    @Override // b.b.a.j.b
    public b a(@NonNull JSONObject jSONObject) {
        super.a(jSONObject);
        this.l = jSONObject.optString("params", null);
        this.m = jSONObject.optString(com.bytedance.b.a.a.d.o, null);
        return this;
    }

    @Override // b.b.a.j.b
    public void a(@NonNull ContentValues contentValues) {
        super.a(contentValues);
        contentValues.put("params", this.l);
        contentValues.put(com.bytedance.b.a.a.d.o, this.m);
    }
}
