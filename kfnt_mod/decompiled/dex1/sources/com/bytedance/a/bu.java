package com.bytedance.a;

import android.content.ContentValues;
import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bytedance.sdk.account.platform.api.IWeiboService;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class bu implements Cloneable {

    /* renamed from: a, reason: collision with root package name */
    public static final SimpleDateFormat f3432a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

    /* renamed from: b, reason: collision with root package name */
    public long f3433b;

    /* renamed from: c, reason: collision with root package name */
    public long f3434c;
    public long d;
    public String e;
    public long f;
    public String g;
    public String h;
    public String i;
    public int j;
    public int k;
    public String l;

    public bu() {
        a(0L);
    }

    public static bu a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            return ce.f3458b.get(jSONObject.optString("k_cls", "")).clone().b(jSONObject);
        } catch (Throwable th) {
            de.a("U SHALL NOT PASS!", th);
            return null;
        }
    }

    public int a(@NonNull Cursor cursor) {
        this.f3433b = cursor.getLong(0);
        this.f3434c = cursor.getLong(1);
        this.d = cursor.getLong(2);
        this.j = cursor.getInt(3);
        this.f = cursor.getLong(4);
        this.e = cursor.getString(5);
        this.g = cursor.getString(6);
        this.h = cursor.getString(7);
        this.i = cursor.getString(8);
        this.k = cursor.getInt(9);
        return 10;
    }

    public List<String> a() {
        return Arrays.asList("_id", "integer primary key autoincrement", "local_time_ms", "integer", "tea_event_index", "integer", "nt", "integer", "user_id", "integer", com.bytedance.b.a.a.d.p, "varchar", "user_unique_id", "varchar", "ssid", "varchar", "ab_sdk_version", "varchar", "event_type", "integer");
    }

    public void a(long j) {
        if (j == 0) {
            j = System.currentTimeMillis();
        }
        this.f3434c = j;
    }

    public void a(@NonNull ContentValues contentValues) {
        contentValues.put("local_time_ms", Long.valueOf(this.f3434c));
        contentValues.put("tea_event_index", Long.valueOf(this.d));
        contentValues.put("nt", Integer.valueOf(this.j));
        contentValues.put("user_id", Long.valueOf(this.f));
        contentValues.put(com.bytedance.b.a.a.d.p, this.e);
        contentValues.put("user_unique_id", this.g);
        contentValues.put("ssid", this.h);
        contentValues.put("ab_sdk_version", this.i);
        contentValues.put("event_type", Integer.valueOf(this.k));
    }

    public void a(@NonNull JSONObject jSONObject) throws JSONException {
        jSONObject.put("local_time_ms", this.f3434c);
    }

    public final ContentValues b(@Nullable ContentValues contentValues) {
        if (contentValues == null) {
            contentValues = new ContentValues();
        } else {
            contentValues.clear();
        }
        a(contentValues);
        return contentValues;
    }

    public bu b(@NonNull JSONObject jSONObject) {
        this.f3434c = jSONObject.optLong("local_time_ms", 0L);
        this.f3433b = 0L;
        this.d = 0L;
        this.j = 0;
        this.f = 0L;
        this.e = null;
        this.g = null;
        this.h = null;
        this.i = null;
        return this;
    }

    public abstract JSONObject b();

    public final String c() {
        List<String> listA = a();
        if (listA == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(128);
        sb.append("create table if not exists ");
        sb.append(d());
        sb.append("(");
        for (int i = 0; i < listA.size(); i += 2) {
            sb.append(listA.get(i));
            sb.append(" ");
            sb.append(listA.get(i + 1));
            sb.append(IWeiboService.Scope.EMPTY_SCOPE);
        }
        sb.delete(sb.length() - 1, sb.length());
        sb.append(")");
        return sb.toString();
    }

    @NonNull
    public abstract String d();

    @NonNull
    public final JSONObject e() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("k_cls", d());
            a(jSONObject);
        } catch (JSONException e) {
            de.a("U SHALL NOT PASS!", e);
        }
        return jSONObject;
    }

    @NonNull
    public final JSONObject f() {
        try {
            this.l = f3432a.format(new Date(this.f3434c));
            return b();
        } catch (JSONException e) {
            de.a("U SHALL NOT PASS!", e);
            return null;
        }
    }

    /* renamed from: g, reason: merged with bridge method [inline-methods] */
    public bu clone() {
        try {
            return (bu) super.clone();
        } catch (CloneNotSupportedException e) {
            de.a("U SHALL NOT PASS!", e);
            return null;
        }
    }

    public String h() {
        StringBuilder sbA = a.a("sid:");
        sbA.append(this.e);
        return sbA.toString();
    }

    public String i() {
        return null;
    }

    @NonNull
    public String toString() {
        String strD = d();
        if (!getClass().getSimpleName().equalsIgnoreCase(strD)) {
            strD = strD + ", " + getClass().getSimpleName();
        }
        String strSubstring = this.e;
        if (strSubstring != null) {
            int iIndexOf = strSubstring.indexOf("-");
            if (iIndexOf >= 0) {
                strSubstring = strSubstring.substring(0, iIndexOf);
            }
        } else {
            strSubstring = "-";
        }
        return "{" + strD + ", " + h() + ", " + strSubstring + ", " + this.f3434c + com.alipay.sdk.util.f.d;
    }
}
