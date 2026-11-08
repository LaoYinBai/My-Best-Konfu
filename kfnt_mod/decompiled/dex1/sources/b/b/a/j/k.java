package b.b.a.j;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.bytedance.common.utility.NetworkUtils;
import com.bytedance.sdk.account.platform.api.IAppAuthService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class k extends b {
    public String l;
    public String m;

    public k(String str, String str2) {
        this.m = str;
        this.l = str2;
    }

    @Override // b.b.a.j.b
    public int a(@NonNull Cursor cursor) {
        super.a(cursor);
        this.m = cursor.getString(9);
        this.l = cursor.getString(10);
        return 11;
    }

    @Override // b.b.a.j.b
    public List<String> a() {
        List<String> listA = super.a();
        ArrayList arrayList = new ArrayList(listA.size());
        arrayList.addAll(listA);
        arrayList.addAll(Arrays.asList("event", "varchar", "params", "varchar"));
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
        jSONObject.put("event", this.m);
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
        return IAppAuthService.Scope.PROFILE;
    }

    @Override // b.b.a.j.b
    public String g() {
        return this.m;
    }

    @Override // b.b.a.j.b
    public String h() {
        return this.l;
    }

    @Override // b.b.a.j.b
    public b a(@NonNull JSONObject jSONObject) {
        super.a(jSONObject);
        this.m = jSONObject.optString("event", null);
        this.l = jSONObject.optString("params", null);
        return this;
    }

    @Override // b.b.a.j.b
    public void a(@NonNull ContentValues contentValues) {
        super.a(contentValues);
        contentValues.put("event", this.m);
        String str = this.l;
        contentValues.put("params", this.l);
    }
}
