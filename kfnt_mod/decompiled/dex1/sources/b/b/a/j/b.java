package b.b.a.j;

import android.content.ContentValues;
import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.b.a.k.v;
import com.bytedance.sdk.account.platform.api.IWeiboService;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class b implements Cloneable {

    /* renamed from: a, reason: collision with root package name */
    public static final SimpleDateFormat f1108a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

    /* renamed from: b, reason: collision with root package name */
    public long f1109b;

    /* renamed from: c, reason: collision with root package name */
    public long f1110c;
    public long d;
    public String e;
    public long f;
    public String g;
    public String h;
    public String i;
    public int j;
    public String k;

    public b() {
        a(0L);
    }

    public List<String> a() {
        return Arrays.asList("_id", "integer primary key autoincrement", "local_time_ms", "integer", "tea_event_index", "integer", "nt", "integer", "user_id", "integer", com.bytedance.b.a.a.d.p, "varchar", "user_unique_id", "varchar", "ssid", "varchar", "ab_sdk_version", "varchar");
    }

    public void a(long j) {
        if (j == 0) {
            j = System.currentTimeMillis();
        }
        this.f1110c = j;
    }

    public abstract JSONObject b();

    @NonNull
    public abstract String d();

    @NonNull
    public final JSONObject e() {
        try {
            this.k = f1108a.format(new Date(this.f1110c));
            return b();
        } catch (JSONException e) {
            v.a(e);
            return null;
        }
    }

    public String g() {
        StringBuilder sbA = b.a.a.a.a.a("sid:");
        sbA.append(this.e);
        return sbA.toString();
    }

    public String h() {
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
        return "{" + strD + ", " + g() + ", " + strSubstring + ", " + this.f1110c + com.alipay.sdk.util.f.d;
    }

    public void a(@NonNull ContentValues contentValues) {
        contentValues.put("local_time_ms", Long.valueOf(this.f1110c));
        contentValues.put("tea_event_index", Long.valueOf(this.d));
        contentValues.put("nt", Integer.valueOf(this.j));
        contentValues.put("user_id", Long.valueOf(this.f));
        contentValues.put(com.bytedance.b.a.a.d.p, this.e);
        contentValues.put("user_unique_id", this.g);
        contentValues.put("ssid", this.h);
        contentValues.put("ab_sdk_version", this.i);
    }

    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public b clone() {
        try {
            return (b) super.clone();
        } catch (CloneNotSupportedException e) {
            v.a(e);
            return null;
        }
    }

    public int a(@NonNull Cursor cursor) {
        this.f1109b = cursor.getLong(0);
        this.f1110c = cursor.getLong(1);
        this.d = cursor.getLong(2);
        this.j = cursor.getInt(3);
        this.f = cursor.getLong(4);
        this.e = cursor.getString(5);
        this.g = cursor.getString(6);
        this.h = cursor.getString(7);
        this.i = cursor.getString(8);
        return 9;
    }

    public b a(@NonNull JSONObject jSONObject) {
        this.f1110c = jSONObject.optLong("local_time_ms", 0L);
        this.f1109b = 0L;
        this.d = 0L;
        this.j = 0;
        this.f = 0L;
        this.e = null;
        this.g = null;
        this.h = null;
        this.i = null;
        return this;
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

    public static b a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            return d.f1115a.get(jSONObject.optString("k_cls", "")).clone().a(jSONObject);
        } catch (Throwable th) {
            v.a(th);
            return null;
        }
    }
}
