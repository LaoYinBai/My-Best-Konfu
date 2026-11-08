package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.bytedance.sdk.account.platform.api.IWeiboService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class j2 {
    private static final String[] h = {"channel", "package", "app_version"};

    /* renamed from: a, reason: collision with root package name */
    private boolean f1353a;

    /* renamed from: b, reason: collision with root package name */
    private final Context f1354b;

    /* renamed from: c, reason: collision with root package name */
    private final i2 f1355c;
    private final SharedPreferences f;
    private final ArrayList<d2> e = new ArrayList<>(32);
    private int g = 0;
    private JSONObject d = new JSONObject();

    public j2(Context context, i2 i2Var) {
        this.f1354b = context;
        this.f1355c = i2Var;
        this.f = i2Var.t();
        s0.a(this.f1354b);
    }

    private JSONObject m() {
        return this.d;
    }

    public JSONObject a() {
        if (this.f1353a) {
            return m();
        }
        return null;
    }

    public JSONObject b() throws JSONException {
        JSONObject jSONObjectA = a();
        if (jSONObjectA != null) {
            try {
                String strA = s0.a(jSONObjectA.optJSONObject("oaid"));
                if (!TextUtils.isEmpty(strA)) {
                    JSONObject jSONObject = new JSONObject();
                    m0.b(jSONObject, jSONObjectA);
                    jSONObject.put("oaid", strA);
                    return jSONObject;
                }
            } catch (Exception e) {
                l0.a(e);
            }
        }
        return jSONObjectA;
    }

    public int c() throws JSONException {
        int iOptInt = this.f1353a ? m().optInt("version_code", -1) : -1;
        for (int i = 0; i < 3 && iOptInt == -1; i++) {
            e();
            iOptInt = this.f1353a ? m().optInt("version_code", -1) : -1;
        }
        return iOptInt;
    }

    public String d() throws JSONException {
        String strOptString = this.f1353a ? m().optString("app_version", null) : null;
        for (int i = 0; i < 3 && strOptString == null; i++) {
            e();
            strOptString = this.f1353a ? m().optString("app_version", null) : null;
        }
        return strOptString;
    }

    public boolean e() throws JSONException {
        synchronized (this.e) {
            if (this.e.size() == 0) {
                this.e.add(new e2(this.f1354b));
                this.e.add(new g2(this.f1354b));
                this.e.add(new h2(this.f1354b, this.f1355c));
                this.e.add(new k2(this.f1354b));
                this.e.add(new l2(this.f1354b));
                this.e.add(new m2(this.f1354b, this.f1355c));
                this.e.add(new j(this.f1354b));
                this.e.add(new l(this.f1354b));
                this.e.add(new m(this.f1354b, this.f1355c));
                this.e.add(new n());
                this.e.add(new o(this.f1355c));
                this.e.add(new p(this.f1354b));
                this.e.add(new q(this.f1354b));
                this.e.add(new r(this.f1354b, this.f1355c));
                this.e.add(new b2(this.f1354b, this.f1355c));
                this.e.add(new k(this.f1354b, this.f1355c));
                this.e.add(new f2(this.f1354b, this.f1355c));
            }
        }
        JSONObject jSONObjectM = m();
        JSONObject jSONObject = new JSONObject();
        m0.b(jSONObject, jSONObjectM);
        Iterator<d2> it = this.e.iterator();
        boolean z = true;
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            d2 next = it.next();
            if (!next.f1323a || next.f1325c || a(next)) {
                try {
                    next.f1323a = next.a(jSONObject);
                } catch (SecurityException e) {
                    if (!next.f1324b) {
                        i++;
                        l0.b("loadHeader, " + this.g, e);
                        if (!next.f1323a && this.g > 10) {
                            next.f1323a = true;
                        }
                    }
                } catch (JSONException e2) {
                    l0.a(e2);
                }
                if (!next.f1323a && !next.f1324b) {
                    i2++;
                }
            }
            z &= next.f1323a || next.f1324b;
        }
        if (z) {
            int length = h.length;
            for (int i3 = 0; i3 < length; i3++) {
                z &= !TextUtils.isEmpty(jSONObject.optString(r7[i3]));
            }
            String strOptString = jSONObject.optString("user_unique_id", null);
            if (!TextUtils.isEmpty(strOptString)) {
                try {
                    jSONObject.put("user_unique_id", strOptString);
                } catch (JSONException unused) {
                }
            }
        }
        this.d = jSONObject;
        this.f1353a = z;
        if (l0.f1356a) {
            l0.a("loadHeader, " + this.f1353a + ", " + this.g + ", " + this.d.toString(), null);
        } else {
            l0.d("loadHeader, " + this.f1353a + ", " + this.g, null);
        }
        if (i > 0 && i == i2) {
            this.g++;
            if (j() != 0) {
                this.g += 10;
            }
        }
        if (this.f1353a) {
            a.f().a(g(), h(), i());
        }
        return this.f1353a;
    }

    public String f() {
        return m().optString("user_unique_id", "");
    }

    public String g() {
        return m().optString(com.bytedance.b.a.c.h.x, "");
    }

    public String h() {
        return m().optString("install_id", "");
    }

    public String i() {
        return m().optString("ssid", "");
    }

    public int j() {
        String strOptString = m().optString(com.bytedance.b.a.c.h.x, "");
        m().optString("install_id", "");
        if (b(strOptString)) {
            return this.f.getInt("version_code", 0) == m().optInt("version_code", -1) ? 1 : 2;
        }
        return 0;
    }

    public long k() {
        return m().optLong("register_time", 0L);
    }

    public String l() {
        return m().optString("ab_sdk_version", "");
    }

    public <T> T a(String str, T t) {
        T t2;
        JSONObject jSONObjectM = m();
        if (jSONObjectM == null || (t2 = (T) jSONObjectM.opt(str)) == null) {
            t2 = null;
        }
        return t2 == null ? t : t2;
    }

    private boolean a(d2 d2Var) {
        boolean z = !this.f1355c.G() && d2Var.d;
        if (l0.f1356a) {
            l0.a("needSyncFromSub " + d2Var + " " + z, null);
        }
        return z;
    }

    public void a(JSONObject jSONObject) {
        this.f1355c.b(jSONObject);
        b(jSONObject);
    }

    private String a(Set<String> set) {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(IWeiboService.Scope.EMPTY_SCOPE);
            }
        }
        return sb.toString();
    }

    private synchronized void b(JSONObject jSONObject) {
        if (jSONObject == null) {
            l0.b("null abconfig", null);
            return;
        }
        String strOptString = m().optString("ab_version");
        if (!TextUtils.isEmpty(strOptString)) {
            String[] strArrSplit = strOptString.split(IWeiboService.Scope.EMPTY_SCOPE);
            Set<String> hashSet = new HashSet<>();
            for (String str : strArrSplit) {
                if (!TextUtils.isEmpty(str)) {
                    hashSet.add(str);
                }
            }
            Iterator<String> itKeys = jSONObject.keys();
            HashSet hashSet2 = new HashSet();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                if (next instanceof String) {
                    String str2 = next;
                    if (!TextUtils.isEmpty(str2)) {
                        try {
                            hashSet2.add(jSONObject.getJSONObject(str2).optString("vid"));
                        } catch (JSONException e) {
                            l0.a(e);
                        }
                    }
                }
            }
            hashSet.retainAll(hashSet2);
            b("ab_version", a(hashSet));
        }
    }

    static void a(JSONObject jSONObject, String str, String str2) throws JSONException {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        jSONObject.put(str, str2);
    }

    static boolean a(String str) {
        int length = str != null ? str.length() : 0;
        if (length < 13 || length > 128) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if ((cCharAt < '0' || cCharAt > '9') && ((cCharAt < 'a' || cCharAt > 'f') && ((cCharAt < 'A' || cCharAt > 'F') && cCharAt != '-'))) {
                return false;
            }
        }
        return true;
    }

    public boolean a(JSONObject jSONObject, String str, String str2, String str3) throws JSONException {
        boolean z;
        boolean z2;
        if (l0.f1356a) {
            l0.a("saveRegisterInfo, " + str + ", " + str2 + ", " + str3 + ", " + jSONObject, null);
        }
        boolean zB = b(str);
        boolean zB2 = b(str2);
        try {
            boolean zB3 = b(str3);
            int i = this.f.getInt("version_code", 0);
            int iOptInt = m().optInt("version_code", 0);
            SharedPreferences.Editor editorEdit = this.f.edit();
            if (i != iOptInt) {
                editorEdit.putInt("version_code", iOptInt);
            }
            if (zB) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                editorEdit.putLong("register_time", jCurrentTimeMillis);
                b("register_time", Long.valueOf(jCurrentTimeMillis));
            } else if (!zB) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("response", jSONObject);
                a.a("tt_fetch_did_error", jSONObject2);
            }
            String strOptString = m().optString(com.bytedance.b.a.c.h.x, "");
            if (zB && b(com.bytedance.b.a.c.h.x, str)) {
                editorEdit.putString(com.bytedance.b.a.c.h.x, str);
                z = true;
            } else {
                z = false;
            }
            String strOptString2 = m().optString("install_id", "");
            if (zB2 && b("install_id", str2)) {
                editorEdit.putString("install_id", str2);
                z = true;
            }
            String strOptString3 = m().optString("ssid", "");
            if (zB3 && b("ssid", str3)) {
                editorEdit.putString("ssid", str3);
                z2 = true;
            } else {
                z2 = z;
            }
            a.f().a(z2, strOptString, str, strOptString2, str2, strOptString3, str3);
            editorEdit.apply();
        } catch (JSONException e) {
            l0.a(e);
        }
        return zB && zB2;
    }

    private boolean b(String str, Object obj) {
        boolean z;
        Object objOpt = m().opt(str);
        if ((obj == null || obj.equals(objOpt)) && (obj != null || objOpt == null)) {
            z = false;
        } else {
            synchronized (this) {
                try {
                    JSONObject jSONObject = this.d;
                    JSONObject jSONObject2 = new JSONObject();
                    m0.b(jSONObject2, jSONObject);
                    jSONObject2.put(str, obj);
                    this.d = jSONObject2;
                } catch (JSONException e) {
                    l0.a(e);
                }
            }
            z = true;
        }
        l0.a("updateHeader, " + str + ", " + objOpt + ", " + obj, null);
        return z;
    }

    public static boolean b(String str) {
        boolean z = false;
        if (TextUtils.isEmpty(str) || "unknown".equalsIgnoreCase(str) || "Null".equalsIgnoreCase(str)) {
            return false;
        }
        int i = 0;
        while (true) {
            if (i >= str.length()) {
                z = true;
                break;
            }
            if (str.charAt(i) != '0') {
                break;
            }
            i++;
        }
        return !z;
    }
}
