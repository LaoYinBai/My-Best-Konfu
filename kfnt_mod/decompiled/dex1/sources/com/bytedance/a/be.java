package com.bytedance.a;

import android.accounts.Account;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bytedance.applog.AppLog;
import com.bytedance.sdk.account.platform.api.IWeiboService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class be {

    /* renamed from: a, reason: collision with root package name */
    public static final String[] f3395a = {"channel", "package", "app_version"};

    /* renamed from: b, reason: collision with root package name */
    public volatile boolean f3396b;

    /* renamed from: c, reason: collision with root package name */
    public final Context f3397c;
    public final az d;
    public final SharedPreferences g;
    public bq h;
    public boolean j;
    public boolean k;
    public final ArrayList<af> f = new ArrayList<>(32);
    public int i = 0;

    @NonNull
    public volatile JSONObject e = new JSONObject();

    public be(Context context, az azVar) {
        this.k = false;
        this.f3397c = context;
        this.d = azVar;
        this.g = azVar.e;
        this.h = bo.a(this.f3397c, this.d);
        this.k = this.g.getBoolean("forbid_report_phone_detail_info", false);
    }

    public static void a(JSONObject jSONObject, String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        jSONObject.put(str, str2);
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

    @Nullable
    public JSONObject a() {
        if (this.f3396b) {
            return this.e;
        }
        return null;
    }

    @SuppressLint({"ApplySharedPref"})
    public void a(String str) {
        bq bqVar = this.h;
        if (bqVar instanceof ah) {
            ((ah) bqVar).a(this.f3397c, str);
        }
        this.d.e.edit().remove("device_token").commit();
    }

    public void a(HashMap<String, Object> map) {
        JSONObject jSONObject;
        if (map == null || map.isEmpty()) {
            jSONObject = null;
        } else {
            jSONObject = new JSONObject();
            JSONObject jSONObjectK = k();
            if (jSONObjectK != null) {
                dj.b(jSONObject, jSONObjectK);
            }
            try {
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    if (!TextUtils.isEmpty(entry.getKey())) {
                        jSONObject.put(entry.getKey(), entry.getValue());
                    }
                }
            } catch (Exception e) {
                de.a("", e);
            }
        }
        b(jSONObject);
    }

    public final boolean a(af afVar) {
        boolean z = !this.d.c() && afVar.e;
        de.a("needSyncFromSub " + afVar + " " + z, null);
        return z;
    }

    public int b() throws JSONException {
        int iOptInt = this.f3396b ? this.e.optInt("version_code", -1) : -1;
        for (int i = 0; i < 3 && iOptInt == -1; i++) {
            d();
            iOptInt = this.f3396b ? this.e.optInt("version_code", -1) : -1;
        }
        return iOptInt;
    }

    public String c() throws JSONException {
        String strOptString = this.f3396b ? this.e.optString("app_version", null) : null;
        for (int i = 0; i < 3 && strOptString == null; i++) {
            d();
            strOptString = this.f3396b ? this.e.optString("app_version", null) : null;
        }
        return strOptString;
    }

    public void c(String str) {
        JSONObject jSONObjectK;
        if (TextUtils.isEmpty(str) || (jSONObjectK = k()) == null || !jSONObjectK.has(str)) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        dj.b(jSONObject, jSONObjectK);
        jSONObject.remove(str);
        b(jSONObject);
    }

    public boolean d() throws JSONException {
        synchronized (this.f) {
            if (this.f.size() == 0) {
                this.f.add(new ak(this.f3397c));
                this.f.add(new au(this.f3397c, this.d));
                this.f.add(new bt(this.f3397c));
                this.f.add(new by(this.f3397c));
                this.f.add(new dc(this.f3397c, this.d, this));
                this.f.add(new cd(this.f3397c));
                this.f.add(new cs(this.f3397c, this.d));
                this.f.add(new cx());
                this.f.add(new dh(this.f3397c, this.d, this));
                this.f.add(new dm(this.f3397c));
                this.f.add(new dr(this.f3397c));
                this.f.add(new bj(this.f3397c, this));
                this.f.add(new ci(this.f3397c));
                this.f.add(new cn(this.f3397c, this.d));
                this.f.add(new ap(this.d));
                this.f.add(new v(this.f3397c));
            }
        }
        JSONObject jSONObject = this.e;
        JSONObject jSONObject2 = new JSONObject();
        dj.b(jSONObject2, jSONObject);
        Iterator<af> it = this.f.iterator();
        boolean z = true;
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            af next = it.next();
            if (!next.f3339b || next.d || a(next)) {
                try {
                    next.f3339b = next.a(jSONObject2);
                } catch (SecurityException e) {
                    if (!next.f3340c) {
                        i++;
                        StringBuilder sbA = a.a("loadHeader, ");
                        sbA.append(this.i);
                        de.a(sbA.toString(), e);
                        if (!next.f3339b && this.i > 10) {
                            next.f3339b = true;
                        }
                    }
                } catch (JSONException e2) {
                    de.a("U SHALL NOT PASS!", e2);
                }
                if (!next.f3339b && !next.f3340c) {
                    i2++;
                }
            }
            z &= next.f3339b || next.f3340c;
        }
        if (z) {
            for (String str : f3395a) {
                boolean zIsEmpty = TextUtils.isEmpty(jSONObject2.optString(str));
                z &= !zIsEmpty;
                if (zIsEmpty) {
                    StringBuilder sbA2 = a.a("loadHeader, ");
                    sbA2.append(this.f3396b);
                    sbA2.append(", ");
                    sbA2.append(str);
                    de.a(sbA2.toString(), null);
                }
            }
        }
        JSONObject jSONObject3 = this.e;
        this.e = jSONObject2;
        Iterator<String> itKeys = jSONObject3.keys();
        while (itKeys.hasNext()) {
            String next2 = itKeys.next();
            a(next2, jSONObject3.opt(next2));
        }
        this.f3396b = z;
        if (de.f3494c) {
            StringBuilder sbA3 = a.a("loadHeader, ");
            sbA3.append(this.f3396b);
            sbA3.append(", ");
            sbA3.append(this.i);
            sbA3.append(", ");
            sbA3.append(this.e.toString());
            de.a(sbA3.toString(), null);
        } else {
            StringBuilder sbA4 = a.a("loadHeader, ");
            sbA4.append(this.f3396b);
            sbA4.append(", ");
            sbA4.append(this.i);
            de.a(sbA4.toString(), null);
        }
        if (i > 0 && i == i2) {
            this.i++;
            if (i() != 0) {
                this.i += 10;
            }
        }
        if (this.f3396b) {
            n.a().onIdLoaded(AppLog.getDid(), g(), h());
        }
        return this.f3396b;
    }

    public String e() {
        if (this.f3396b) {
            return this.e.optString("user_unique_id", "");
        }
        az azVar = this.d;
        return azVar != null ? azVar.f3387c.getString("user_unique_id", null) : "";
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

    public String g() {
        return this.e.optString("install_id", "");
    }

    public final synchronized void g(String str) {
        String strOptString = this.e.optString("ab_sdk_version");
        if (!TextUtils.isEmpty(strOptString)) {
            for (String str2 : strOptString.split(IWeiboService.Scope.EMPTY_SCOPE)) {
                if (!TextUtils.isEmpty(str2) && str2.equals(str)) {
                    if (de.f3494c) {
                        de.a("addExposedVid ready added " + strOptString, null);
                    }
                    return;
                }
            }
            str = strOptString + IWeiboService.Scope.EMPTY_SCOPE + str;
        }
        d(str);
        a(str, this.d.e());
    }

    public String h() {
        return this.e.optString("ssid", "");
    }

    public int i() {
        String strOptString = this.e.optString(com.bytedance.b.a.c.h.x, "");
        String strOptString2 = this.e.optString("install_id", "");
        String strOptString3 = this.e.optString("bd_did", "");
        if ((dj.b(strOptString) || dj.b(strOptString3)) && dj.b(strOptString2)) {
            return this.g.getInt("version_code", 0) == this.e.optInt("version_code", -1) ? 1 : 2;
        }
        return 0;
    }

    public boolean j() {
        return !this.k;
    }

    public synchronized void b(String str) {
        Set<String> setF = f(this.d.e());
        Set<String> setF2 = f(this.e.optString("ab_sdk_version"));
        setF2.removeAll(setF);
        setF2.addAll(f(str));
        this.d.a(str);
        d(a(setF2));
    }

    public String f() {
        return this.e.optString("bd_did", "");
    }

    public final JSONObject k() {
        if (this.f3396b) {
            return this.e.optJSONObject("custom");
        }
        az azVar = this.d;
        if (azVar == null) {
            return null;
        }
        try {
            return new JSONObject(azVar.f3387c.getString("header_custom_info", null));
        } catch (Exception unused) {
            return null;
        }
    }

    public boolean e(String str) {
        if (!a("user_unique_id", (Object) str)) {
            return false;
        }
        a.a(this.d.f3387c, "user_unique_id", str);
        return true;
    }

    public final void a(String str, String str2) {
        if (this.d.e.getBoolean("bav_ab_config", false) && this.d.f3386b.isAbEnable()) {
            Set<String> setF = f(str);
            setF.removeAll(f(str2));
            n.a().onAbVidsChange(a(setF), str2);
        }
    }

    public final synchronized void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            try {
                de.a("null abconfig", null);
            } catch (Throwable th) {
                throw th;
            }
        }
        String strOptString = this.e.optString("ab_sdk_version");
        if (!TextUtils.isEmpty(strOptString)) {
            Set<String> setF = f(strOptString);
            HashSet hashSet = new HashSet();
            if (jSONObject != null) {
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    if (next instanceof String) {
                        String str = next;
                        if (!TextUtils.isEmpty(str)) {
                            try {
                                hashSet.add(jSONObject.getJSONObject(str).optString("vid"));
                            } catch (JSONException e) {
                                de.a("U SHALL NOT PASS!", e);
                            }
                        }
                    }
                }
            }
            String strE = this.d.e();
            hashSet.addAll(f(strE));
            setF.retainAll(hashSet);
            String strA = a(setF);
            d(strA);
            if (!TextUtils.equals(strOptString, strA)) {
                a(strA, strE);
            }
        }
    }

    public boolean a(JSONObject jSONObject, String str, String str2, String str3, String str4, String str5) throws JSONException {
        StringBuilder sb;
        boolean z;
        boolean z2;
        if (de.f3494c) {
            sb = new StringBuilder();
            sb.append("saveRegisterInfo, ");
            sb.append(str);
            sb.append(", ");
            sb.append(str2);
            sb.append(", ");
            sb.append(str3);
            sb.append(", ");
            sb.append(str4);
            sb.append(", ");
            sb.append(str5);
            sb.append(", ");
            sb.append(jSONObject);
        } else {
            sb = new StringBuilder();
            sb.append("saveRegisterInfo, ");
            sb.append(str);
            sb.append(", ");
            sb.append(str2);
            sb.append(", ");
            sb.append(str3);
            sb.append(", ");
            sb.append(str4);
            sb.append(", ");
            sb.append(str5);
        }
        de.a(sb.toString(), null);
        this.j = jSONObject.optInt("new_user", 0) > 0;
        String strOptString = jSONObject.optString("device_token", "");
        boolean zB = dj.b(str);
        boolean zB2 = dj.b(str2);
        boolean zB3 = dj.b(str4);
        boolean zB4 = dj.b(str5);
        try {
            boolean zB5 = dj.b(str3);
            int i = this.g.getInt("version_code", 0);
            int iOptInt = this.e.optInt("version_code", 0);
            SharedPreferences.Editor editorEdit = this.g.edit();
            if (i != iOptInt) {
                editorEdit.putInt("version_code", iOptInt);
            }
            String string = this.g.getString("channel", "");
            String strOptString2 = this.e.optString("channel", "");
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
                AppLog.onEventV3("tt_fetch_did_error", jSONObject2);
            }
            String strG = ((ah) this.h).g();
            String string2 = this.g.getString("bd_did", null);
            if (de.f3494c) {
                de.a("od=" + strG + " nd=" + str + " ck=" + zB, null);
            }
            if (zB) {
                if (str.equals(this.e.optString(com.bytedance.b.a.c.h.x))) {
                    z = false;
                } else {
                    JSONObject jSONObject3 = this.e;
                    JSONObject jSONObject4 = new JSONObject();
                    dj.b(jSONObject4, jSONObject3);
                    jSONObject4.put(com.bytedance.b.a.c.h.x, str);
                    this.e = jSONObject4;
                    ((ah) this.h).a(str);
                    z = true;
                }
                if (!str.equals(strG)) {
                    z = true;
                }
            } else {
                z = false;
            }
            if (zB3 && a("bd_did", (Object) str4)) {
                editorEdit.putString("bd_did", str4);
                z = true;
            }
            String strOptString3 = this.e.optString("install_id", "");
            if (zB2 && a("install_id", (Object) str2)) {
                editorEdit.putString("install_id", str2);
                z = true;
            }
            String strOptString4 = this.e.optString("ssid", "");
            if (zB5 && a("ssid", (Object) str3)) {
                editorEdit.putString(this.d.i(), str3);
                z2 = true;
            } else {
                z2 = z;
            }
            n.a().onRemoteIdGet(z2, string2, str4, strOptString3, str2, strOptString4, str3);
            editorEdit.apply();
        } catch (JSONException e) {
            de.a("U SHALL NOT PASS!", e);
        }
        return (zB || (zB3 && zB4)) && zB2;
    }

    public void d(String str) {
        if (a("ab_sdk_version", (Object) str)) {
            a.a(this.d.f3387c, "ab_sdk_version", str);
        }
    }

    public void a(Account account) {
        if (bo.f3417a instanceof ah) {
            bp bpVar = ((ah) bo.f3417a).j;
            if (bpVar != null) {
                bpVar.a(account);
            }
        } else {
            bo.f3419c = account;
        }
        m.f3530a = account;
    }

    public final void b(JSONObject jSONObject) {
        if (a("custom", jSONObject)) {
            this.d.f3387c.edit().putString("header_custom_info", jSONObject != null ? jSONObject.toString() : "").apply();
        }
    }

    public final boolean a(String str, Object obj) {
        boolean z;
        Object objOpt = this.e.opt(str);
        if ((obj == null || obj.equals(objOpt)) && (obj != null || objOpt == null)) {
            z = false;
        } else {
            synchronized (this) {
                try {
                    JSONObject jSONObject = this.e;
                    JSONObject jSONObject2 = new JSONObject();
                    dj.b(jSONObject2, jSONObject);
                    jSONObject2.put(str, obj);
                    this.e = jSONObject2;
                } catch (JSONException e) {
                    de.a("U SHALL NOT PASS!", e);
                }
            }
            z = true;
        }
        de.a("updateHeader, " + str + ", " + objOpt + ", " + obj, null);
        return z;
    }
}
