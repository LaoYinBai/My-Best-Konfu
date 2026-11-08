package b.b.a.f;

import android.accounts.Account;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.b.a.k.v;
import b.b.a.k.x;
import com.bytedance.sdk.account.platform.api.IWeiboService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class h {

    /* renamed from: a, reason: collision with root package name */
    public volatile boolean f1072a;

    /* renamed from: b, reason: collision with root package name */
    public final Context f1073b;

    /* renamed from: c, reason: collision with root package name */
    public final g f1074c;
    public final SharedPreferences f;
    public b.b.a.k.n g;
    public boolean i;
    public boolean j;
    public final ArrayList<c> e = new ArrayList<>(32);
    public int h = 0;

    @NonNull
    public volatile JSONObject d = new JSONObject();

    static {
        new String[]{"channel", "package", "app_version"};
    }

    public h(Context context, g gVar) throws JSONException {
        JSONObject jSONObject;
        this.j = false;
        this.f1073b = context;
        this.f1074c = gVar;
        this.f = gVar.e;
        this.g = j.a(this.f1073b, this.f1074c);
        this.j = this.f.getBoolean("forbid_report_phone_detail_info", false);
        Map<String, String> mapF = gVar.f1070b.f();
        if (mapF == null || mapF.isEmpty()) {
            jSONObject = null;
        } else {
            jSONObject = new JSONObject(mapF);
            JSONObject jSONObjectJ = j();
            if (jSONObjectJ != null) {
                x.a(jSONObject, jSONObjectJ);
            }
        }
        a(jSONObject);
    }

    @Nullable
    public JSONObject a() {
        if (this.f1072a) {
            return this.d;
        }
        return null;
    }

    public void a(Account account) {
        b.b.a.j.a aVar;
        j.f1077b = account;
        for (j jVar : j.f1076a.values()) {
            if ((jVar.f1078c instanceof b.b.a.k.g) && (aVar = ((b.b.a.k.g) jVar.f1078c).g) != null) {
                aVar.a(account);
            }
        }
        b.b.a.a.b.f1032a = account;
    }

    @SuppressLint({"ApplySharedPref"})
    public void a(String str) {
        b.b.a.k.n nVar = this.g;
        if (nVar instanceof b.b.a.k.g) {
            ((b.b.a.k.g) nVar).a(this.f1073b, str);
        }
        this.f1074c.e.edit().remove("device_token").commit();
    }

    public int b() throws JSONException {
        int iOptInt = this.f1072a ? this.d.optInt("version_code", -1) : -1;
        for (int i = 0; i < 3 && iOptInt == -1; i++) {
            d();
            iOptInt = this.f1072a ? this.d.optInt("version_code", -1) : -1;
        }
        return iOptInt;
    }

    public synchronized void b(String str) {
        Set<String> setF = f(this.f1074c.f());
        Set<String> setF2 = f(this.d.optString("ab_sdk_version"));
        setF2.removeAll(setF);
        setF2.addAll(f(str));
        this.f1074c.a(str);
        d(a(setF2));
    }

    public String c() throws JSONException {
        String strOptString = this.f1072a ? this.d.optString("app_version", null) : null;
        for (int i = 0; i < 3 && strOptString == null; i++) {
            d();
            strOptString = this.f1072a ? this.d.optString("app_version", null) : null;
        }
        return strOptString;
    }

    public boolean d() throws JSONException {
        synchronized (this.e) {
            if (this.e.size() == 0) {
                this.e.add(new d(this.f1073b));
                this.e.add(new f(this.f1073b, this.f1074c));
                this.e.add(new k(this.f1073b));
                this.e.add(new l(this.f1073b));
                this.e.add(new r(this.f1073b, this.f1074c, this));
                this.e.add(new m(this.f1073b));
                this.e.add(new p(this.f1073b, this.f1074c));
                this.e.add(new q());
                this.e.add(new s(this.f1073b, this.f1074c, this));
                this.e.add(new t(this.f1073b));
                this.e.add(new u(this.f1073b));
                this.e.add(new i(this.f1073b, this));
                this.e.add(new n(this.f1073b));
                this.e.add(new o(this.f1073b, this.f1074c));
                this.e.add(new e(this.f1074c));
                this.e.add(new a(this.f1073b));
            }
        }
        JSONObject jSONObject = this.d;
        JSONObject jSONObject2 = new JSONObject();
        x.a(jSONObject2, jSONObject);
        Iterator<c> it = this.e.iterator();
        boolean z = true;
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            c next = it.next();
            if (!next.f1065b || next.d || a(next)) {
                try {
                    next.f1065b = next.a(jSONObject2);
                } catch (SecurityException e) {
                    if (!next.f1066c) {
                        i++;
                        StringBuilder sbA = b.a.a.a.a.a("loadHeader, ");
                        sbA.append(this.h);
                        v.a(sbA.toString(), e);
                        if (!next.f1065b && this.h > 10) {
                            next.f1065b = true;
                        }
                    }
                } catch (JSONException e2) {
                    v.a(e2);
                }
                if (!next.f1065b && !next.f1066c) {
                    i2++;
                }
            }
            z &= next.f1065b || next.f1066c;
        }
        JSONObject jSONObject3 = this.d;
        this.d = jSONObject2;
        Iterator<String> itKeys = jSONObject3.keys();
        while (itKeys.hasNext()) {
            String next2 = itKeys.next();
            a(next2, jSONObject3.opt(next2));
        }
        this.f1072a = z;
        if (v.f1150b) {
            StringBuilder sbA2 = b.a.a.a.a.a("loadHeader, ");
            sbA2.append(this.f1072a);
            sbA2.append(", ");
            sbA2.append(this.h);
            sbA2.append(", ");
            sbA2.append(this.d.toString());
            v.a(sbA2.toString(), (Throwable) null);
        } else {
            StringBuilder sbA3 = b.a.a.a.a.a("loadHeader, ");
            sbA3.append(this.f1072a);
            sbA3.append(", ");
            sbA3.append(this.h);
            v.a(sbA3.toString(), (Throwable) null);
        }
        if (i > 0 && i == i2) {
            this.h++;
            if (h() != 0) {
                this.h += 10;
            }
        }
        if (this.f1072a) {
            b.b.a.k.c.a(f()).a(com.apm.applog.a.a(this.f1074c.a()).u(), this.d.optString("install_id", ""), g());
        }
        return this.f1072a;
    }

    public String g() {
        return this.d.optString("ssid", "");
    }

    public int h() {
        String strOptString = this.d.optString(com.bytedance.b.a.c.h.x, "");
        String strOptString2 = this.d.optString("install_id", "");
        String strOptString3 = this.d.optString("bd_did", "");
        if ((x.b(strOptString) || x.b(strOptString3)) && x.b(strOptString2)) {
            return this.f.getInt("version_code", 0) == this.d.optInt("version_code", -1) ? 1 : 2;
        }
        return 0;
    }

    public boolean i() {
        return !this.j;
    }

    public void d(String str) {
        if (a("ab_sdk_version", str)) {
            b.a.a.a.a.a(this.f1074c.f1071c, "ab_sdk_version", str);
        }
    }

    public final Set<String> f(String str) {
        String[] strArrSplit;
        HashSet hashSet = new HashSet();
        if (!TextUtils.isEmpty(str) && (strArrSplit = str.split(IWeiboService.Scope.EMPTY_SCOPE)) != null && strArrSplit.length > 0) {
            for (String str2 : strArrSplit) {
                if (!TextUtils.isEmpty(str2)) {
                    hashSet.add(str2);
                }
            }
        }
        return hashSet;
    }

    public final JSONObject j() {
        if (this.f1072a) {
            return this.d.optJSONObject("custom");
        }
        g gVar = this.f1074c;
        if (gVar == null) {
            return null;
        }
        try {
            return new JSONObject(gVar.f1071c.getString("header_custom_info", null));
        } catch (Exception unused) {
            return null;
        }
    }

    public boolean e(String str) {
        if (!a("user_unique_id", str)) {
            return false;
        }
        b.a.a.a.a.a(this.f1074c.f1071c, "user_unique_id", str);
        return true;
    }

    public final synchronized void g(String str) {
        String strOptString = this.d.optString("ab_sdk_version");
        if (!TextUtils.isEmpty(strOptString)) {
            for (String str2 : strOptString.split(IWeiboService.Scope.EMPTY_SCOPE)) {
                if (!TextUtils.isEmpty(str2) && str2.equals(str)) {
                    if (v.f1150b) {
                        v.a("addExposedVid ready added " + strOptString, (Throwable) null);
                    }
                    return;
                }
            }
            str = strOptString + IWeiboService.Scope.EMPTY_SCOPE + str;
        }
        d(str);
        String strF = this.f1074c.f();
        if (this.f1074c.e.getBoolean("bav_ab_config", false) && this.f1074c.f1070b.K()) {
            Set<String> setF = f(str);
            setF.removeAll(f(strF));
            b.b.a.k.c.a(f()).a(a(setF), strF);
        }
    }

    public void c(String str) {
        JSONObject jSONObjectJ;
        if (TextUtils.isEmpty(str) || (jSONObjectJ = j()) == null || !jSONObjectJ.has(str)) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        x.a(jSONObject, jSONObjectJ);
        jSONObject.remove(str);
        a(jSONObject);
    }

    public String e() {
        if (this.f1072a) {
            return this.d.optString("user_unique_id", "");
        }
        g gVar = this.f1074c;
        return gVar != null ? gVar.f1071c.getString("user_unique_id", null) : "";
    }

    public String f() {
        return this.d.optString("aid", this.f1074c.a());
    }

    public final boolean a(String str, Object obj) {
        boolean z;
        Object objOpt = this.d.opt(str);
        if ((obj == null || obj.equals(objOpt)) && (obj != null || objOpt == null)) {
            z = false;
        } else {
            synchronized (this) {
                try {
                    JSONObject jSONObject = this.d;
                    JSONObject jSONObject2 = new JSONObject();
                    x.a(jSONObject2, jSONObject);
                    jSONObject2.put(str, obj);
                    this.d = jSONObject2;
                } catch (JSONException e) {
                    v.a(e);
                }
            }
            z = true;
        }
        v.a("updateHeader, " + str + ", " + objOpt + ", " + obj, (Throwable) null);
        return z;
    }

    public final boolean a(c cVar) {
        boolean z = !this.f1074c.d() && cVar.e;
        v.a("needSyncFromSub " + cVar + " " + z, (Throwable) null);
        return z;
    }

    public final String a(Set<String> set) {
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

    public void a(Map<String, Object> map) {
        JSONObject jSONObject;
        if (map == null || map.isEmpty()) {
            jSONObject = null;
        } else {
            jSONObject = new JSONObject(map);
            JSONObject jSONObjectJ = j();
            if (jSONObjectJ != null) {
                x.a(jSONObject, jSONObjectJ);
            }
        }
        a(jSONObject);
    }

    public final void a(JSONObject jSONObject) {
        if (a("custom", jSONObject)) {
            this.f1074c.f1071c.edit().putString("header_custom_info", jSONObject != null ? jSONObject.toString() : "").apply();
        }
    }

    public static void a(JSONObject jSONObject, String str, String str2) throws JSONException {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        jSONObject.put(str, str2);
    }

    public boolean a(JSONObject jSONObject, String str, String str2, String str3, String str4, String str5) throws JSONException {
        boolean z;
        boolean z2;
        if (v.f1150b) {
            v.a("saveRegisterInfo, " + str + ", " + str2 + ", " + str3 + ", " + str4 + ", " + str5 + ", " + jSONObject, (Throwable) null);
        } else {
            v.a("saveRegisterInfo, " + str + ", " + str2 + ", " + str3 + ", " + str4 + ", " + str5, (Throwable) null);
        }
        this.i = jSONObject.optInt("new_user", 0) > 0;
        String strOptString = jSONObject.optString("device_token", "");
        boolean zB = x.b(str);
        boolean zB2 = x.b(str2);
        boolean zB3 = x.b(str4);
        boolean zB4 = x.b(str5);
        try {
            boolean zB5 = x.b(str3);
            int i = this.f.getInt("version_code", 0);
            int iOptInt = this.d.optInt("version_code", 0);
            SharedPreferences.Editor editorEdit = this.f.edit();
            if (i != iOptInt) {
                editorEdit.putInt("version_code", iOptInt);
            }
            String string = this.f.getString("channel", "");
            String strOptString2 = this.d.optString("channel", "");
            if (!TextUtils.equals(string, strOptString2)) {
                editorEdit.putString("channel", strOptString2);
            }
            editorEdit.putString("device_token", strOptString);
            if ((zB || (zB3 && zB4)) && zB2) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                editorEdit.putLong("register_time", jCurrentTimeMillis);
                a("register_time", Long.valueOf(jCurrentTimeMillis));
            } else if (!zB && (!zB3 || !zB4)) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("response", jSONObject);
                com.apm.applog.a.a(this.f1074c.a()).a("tt_fetch_did_error", jSONObject2);
            }
            String strC = ((b.b.a.k.g) this.g).c();
            String string2 = this.f.getString("bd_did", null);
            if (v.f1150b) {
                v.a("od=" + strC + " nd=" + str + " ck=" + zB);
            }
            if (zB) {
                if (str.equals(this.d.optString(com.bytedance.b.a.c.h.x))) {
                    z = false;
                } else {
                    JSONObject jSONObject3 = this.d;
                    JSONObject jSONObject4 = new JSONObject();
                    x.a(jSONObject4, jSONObject3);
                    jSONObject4.put(com.bytedance.b.a.c.h.x, str);
                    this.d = jSONObject4;
                    ((b.b.a.k.g) this.g).a(str);
                    z = true;
                }
                if (!str.equals(strC)) {
                    z = true;
                }
            } else {
                z = false;
            }
            if (zB3 && a("bd_did", str4)) {
                editorEdit.putString("bd_did", str4);
                z = true;
            }
            String strOptString3 = this.d.optString("install_id", "");
            if (zB2 && a("install_id", str2)) {
                editorEdit.putString("install_id", str2);
                z = true;
            }
            String strOptString4 = this.d.optString("ssid", "");
            if (zB5 && a("ssid", str3)) {
                editorEdit.putString(this.f1074c.j(), str3);
                z2 = true;
            } else {
                z2 = z;
            }
            b.b.a.k.c.a(f()).a(z2, string2, str4, strOptString3, str2, strOptString4, str3);
            editorEdit.apply();
        } catch (JSONException e) {
            v.a(e);
        }
        return (zB || (zB3 && zB4)) && zB2;
    }
}
