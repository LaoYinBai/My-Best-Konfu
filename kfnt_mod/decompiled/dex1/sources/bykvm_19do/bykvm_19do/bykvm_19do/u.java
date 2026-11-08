package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.ContentValues;
import android.database.Cursor;
import com.bytedance.sdk.account.platform.api.IWeiboService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class u implements Cloneable {
    private static final SimpleDateFormat h = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

    /* renamed from: a, reason: collision with root package name */
    public long f1385a = System.currentTimeMillis();

    /* renamed from: b, reason: collision with root package name */
    public long f1386b;

    /* renamed from: c, reason: collision with root package name */
    public String f1387c;
    public String d;
    public String e;
    public String f;
    String g;

    public static String a(long j) {
        return h.format(new Date(j));
    }

    public abstract u a(Cursor cursor);

    protected abstract void a(ContentValues contentValues);

    protected abstract void a(JSONObject jSONObject);

    protected abstract String[] a();

    final ContentValues b(ContentValues contentValues) {
        if (contentValues == null) {
            contentValues = new ContentValues();
        } else {
            contentValues.clear();
        }
        a(contentValues);
        return contentValues;
    }

    protected abstract u b(JSONObject jSONObject);

    protected abstract JSONObject b();

    final String c() {
        String[] strArrA = a();
        if (strArrA == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(128);
        sb.append("create table if not exists ");
        sb.append(d());
        sb.append("(");
        for (int i = 0; i < strArrA.length; i += 2) {
            sb.append(strArrA[i]);
            sb.append(" ");
            sb.append(strArrA[i + 1]);
            sb.append(IWeiboService.Scope.EMPTY_SCOPE);
        }
        sb.delete(sb.length() - 1, sb.length());
        sb.append(")");
        return sb.toString();
    }

    abstract String d();

    public final JSONObject e() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("k_cls", d());
            a(jSONObject);
        } catch (JSONException e) {
            l0.a(e);
        }
        return jSONObject;
    }

    final JSONObject f() {
        try {
            this.g = a(this.f1385a);
            return b();
        } catch (JSONException e) {
            l0.a(e);
            return null;
        }
    }

    /* renamed from: g, reason: merged with bridge method [inline-methods] */
    public u clone() {
        try {
            return (u) super.clone();
        } catch (CloneNotSupportedException e) {
            l0.a(e);
            return null;
        }
    }

    protected String h() {
        return "sid:" + this.f1387c;
    }

    public String toString() {
        if (!l0.f1356a) {
            return super.toString();
        }
        String strD = d();
        if (!getClass().getSimpleName().equalsIgnoreCase(strD)) {
            strD = strD + ", " + getClass().getSimpleName();
        }
        String strSubstring = this.f1387c;
        if (strSubstring != null) {
            int iIndexOf = strSubstring.indexOf("-");
            if (iIndexOf >= 0) {
                strSubstring = strSubstring.substring(0, iIndexOf);
            }
        } else {
            strSubstring = "-";
        }
        return "{" + strD + ", " + h() + ", " + strSubstring + ", " + this.f1385a + com.alipay.sdk.util.f.d;
    }

    public static u a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            return v.e.get(jSONObject.optString("k_cls", "")).clone().b(jSONObject);
        } catch (Throwable th) {
            l0.a(th);
            return null;
        }
    }
}
