package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashSet;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class i2 {

    /* renamed from: a, reason: collision with root package name */
    private final Context f1347a;

    /* renamed from: b, reason: collision with root package name */
    private final h f1348b;

    /* renamed from: c, reason: collision with root package name */
    private final SharedPreferences f1349c;
    private final SharedPreferences d;
    private final SharedPreferences e;
    private volatile JSONObject f;
    private volatile JSONObject g;

    public i2(Context context, h hVar) {
        this.f1347a = context;
        this.f1348b = hVar;
        this.e = this.f1347a.getSharedPreferences("embed_applog_stats", 0);
        this.f1349c = this.f1347a.getSharedPreferences("embed_header_custom", 0);
        this.d = this.f1347a.getSharedPreferences("embed_last_sp_session", 0);
    }

    String A() {
        return this.f1348b.j();
    }

    String B() {
        return this.f1348b.k();
    }

    String C() {
        return this.f1348b.o();
    }

    String D() {
        return this.f1349c.getString("header_custom_info", null);
    }

    String E() {
        return this.f1349c.getString("ab_sdk_version", "");
    }

    String F() {
        return this.f1349c.getString("user_unique_id", null);
    }

    public boolean G() {
        if (this.f1348b.n() == 0) {
            String strA = m0.a(this.f1347a);
            if (TextUtils.isEmpty(strA)) {
                this.f1348b.a(0);
            } else {
                this.f1348b.a(strA.contains(":") ? 2 : 1);
            }
        }
        return this.f1348b.n() == 1;
    }

    public long H() {
        return this.e.getLong("abtest_fetch_interval", 0L);
    }

    public String I() {
        return !TextUtils.isEmpty(this.f1348b.d()) ? this.f1348b.d() : this.f1349c.getString("ab_version", null);
    }

    public JSONObject J() {
        JSONObject jSONObject = this.f;
        if (jSONObject == null) {
            synchronized (this) {
                try {
                    if (K()) {
                        jSONObject = new JSONObject(this.f1349c.getString("ab_configure", ""));
                    }
                } catch (JSONException unused) {
                }
                if (jSONObject == null) {
                    jSONObject = new JSONObject();
                }
                this.f = jSONObject;
            }
        }
        return jSONObject;
    }

    public boolean K() {
        return this.e.getBoolean("bav_ab_config", false);
    }

    public boolean L() {
        return this.e.getBoolean("bav_log_collect", false);
    }

    public long M() {
        return this.e.getLong("session_interval", com.bytedance.b.a.a.c.g.e);
    }

    public long N() {
        return this.e.getLong("batch_event_interval", com.bytedance.b.a.a.c.g.e);
    }

    String O() {
        return null;
    }

    String P() {
        return this.f1348b.p();
    }

    public void a(long j) {
    }

    public void a(String str, int i) {
        this.d.edit().putString("session_last_day", str).putInt("session_order", i).apply();
    }

    public void a(HashSet<String> hashSet, HashSet<String> hashSet2) {
    }

    public boolean a(ArrayList<u> arrayList) {
        return true;
    }

    public long b() {
        return 10000L;
    }

    void b(JSONObject jSONObject) {
        l0.a("setAbConfig, " + jSONObject.toString(), null);
        this.f1349c.edit().putString("ab_configure", jSONObject.toString()).apply();
        this.f = null;
    }

    public String c() {
        return this.f1348b.h();
    }

    public int d() {
        return this.f1348b.v();
    }

    public int e() {
        return this.f1348b.s();
    }

    public int f() {
        return this.f1348b.l();
    }

    public String g() {
        return this.f1348b.u();
    }

    public String h() {
        return this.f1348b.r();
    }

    public String i() {
        return this.f1348b.a();
    }

    public String j() {
        return this.f1348b.c();
    }

    public String k() {
        return this.f1348b.b();
    }

    public String l() {
        return this.f1348b.w();
    }

    public String m() {
        return this.f1348b.g() == null ? "" : this.f1348b.g();
    }

    public boolean n() {
        return this.f1348b.y();
    }

    public h o() {
        return this.f1348b;
    }

    public CharSequence p() {
        return this.f1348b.x();
    }

    String q() {
        return this.f1348b.f();
    }

    public String r() {
        return this.d.getString("session_last_day", "");
    }

    public int s() {
        return this.d.getInt("session_order", 0);
    }

    public SharedPreferences t() {
        return this.e;
    }

    public boolean u() {
        return this.f1348b.z();
    }

    public JSONObject v() {
        return this.g;
    }

    public long w() {
        return this.e.getLong("app_log_last_config_time", 0L);
    }

    public int x() {
        return this.e.getInt("bav_monitor_rate", 0);
    }

    public String y() {
        return this.f1348b.e();
    }

    String z() {
        return this.f1348b.i();
    }

    public void a(JSONObject jSONObject) {
        if (l0.f1356a) {
            l0.a("setConfig, " + jSONObject.toString(), null);
        }
        this.g = jSONObject;
        long jCurrentTimeMillis = System.currentTimeMillis();
        SharedPreferences.Editor editorEdit = this.e.edit();
        long jOptInt = jSONObject.optInt("session_interval", 0);
        if (jOptInt <= 0 || jOptInt > 604800) {
            editorEdit.remove("session_interval");
        } else {
            editorEdit.putLong("session_interval", jOptInt * 1000);
        }
        long jOptInt2 = jSONObject.optInt("batch_event_interval", 0);
        if (jOptInt2 <= 0 || jOptInt2 > 604800) {
            editorEdit.remove("batch_event_interval");
        } else {
            editorEdit.putLong("batch_event_interval", jOptInt2 * 1000);
        }
        int iOptInt = jSONObject.optInt("send_launch_timely", 0);
        if (iOptInt <= 0 || iOptInt > 604800) {
            editorEdit.remove("send_launch_timely");
        } else {
            editorEdit.putInt("send_launch_timely", iOptInt);
        }
        long jOptInt3 = jSONObject.optInt("abtest_fetch_interval", 0);
        if (jOptInt3 <= 20 || jOptInt3 > 604800) {
            editorEdit.remove("abtest_fetch_interval");
        } else {
            editorEdit.putLong("abtest_fetch_interval", jOptInt3 * 1000);
        }
        if (jSONObject.optBoolean("bav_log_collect", true)) {
            editorEdit.putBoolean("bav_log_collect", true);
        } else {
            editorEdit.remove("bav_log_collect");
        }
        if (jSONObject.optBoolean("bav_ab_config", false)) {
            editorEdit.putBoolean("bav_ab_config", true);
        } else {
            editorEdit.remove("bav_ab_config");
        }
        int iOptInt2 = jSONObject.optInt("bav_monitor_rate", 0);
        if (iOptInt2 <= 0 || iOptInt2 > 100) {
            editorEdit.remove("bav_monitor_rate");
            k0.a(false);
        } else {
            editorEdit.putInt("bav_monitor_rate", iOptInt2);
            k0.a(true);
        }
        editorEdit.putLong("app_log_last_config_time", jCurrentTimeMillis);
        editorEdit.apply();
    }

    String a() {
        return this.e.getString(com.alipay.sdk.cons.b.f2578b, null);
    }
}
