package c.a.a.a.a;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import com.bytedance.JProtect;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

@JProtect
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ac implements af {
    private static int m = 2000;
    private static String n = bo.f2091a;
    private static String o = bo.f2092b;

    /* renamed from: a, reason: collision with root package name */
    public String f2045a = "";

    /* renamed from: b, reason: collision with root package name */
    public String f2046b = "";

    /* renamed from: c, reason: collision with root package name */
    public String f2047c = "";
    public String d = "";
    public String e = "";
    public String f = "";
    public String g = "";
    public String h = "";
    public String i = "";
    public String j = "";
    public String k = "";
    public String l = "";

    protected static ac a(Intent intent) {
        String dataString = intent.getDataString();
        if (TextUtils.isEmpty(dataString) || !dataString.startsWith(o)) {
            return null;
        }
        ac acVar = new ac();
        acVar.k = dataString;
        Map<String, String> mapA = a(dataString.substring(o.length()));
        acVar.f2045a = mapA.containsKey(bo.p) ? mapA.get(bo.p) : "";
        if (!mapA.containsKey(bo.q)) {
            return null;
        }
        try {
            acVar.c(URLDecoder.decode(new JSONObject(new String(Base64.decode(mapA.get(bo.q), 0))).optString(bo.r)));
            return acVar.c();
        } catch (Throwable unused) {
            return null;
        }
    }

    public static ac a(Intent intent, String str) {
        ac acVarB = b(intent);
        if (acVarB == null) {
            acVarB = a(intent);
        }
        if (acVarB != null) {
            acVarB.b(str);
        }
        return acVarB;
    }

    public static ac a(String str, String str2) {
        ac acVar = new ac();
        acVar.k = str;
        acVar.c(str);
        acVar.l = str2;
        return acVar.c();
    }

    protected static Map<String, String> a(String str) {
        HashMap map = new HashMap();
        try {
            for (String str2 : str.split("[&]")) {
                String[] strArrSplit = str2.split("[=]");
                if (strArrSplit.length == 2) {
                    map.put(strArrSplit[0], URLDecoder.decode(strArrSplit[1]));
                }
            }
        } catch (Throwable unused) {
        }
        return map;
    }

    protected static ac b(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return null;
        }
        try {
            String string = extras.getString(bo.s);
            if (TextUtils.isEmpty(string) || !string.startsWith(n)) {
                return null;
            }
            ac acVar = new ac();
            String strSubstring = string.substring(n.length());
            acVar.k = string;
            Map<String, String> mapA = a(strSubstring);
            acVar.f2045a = mapA.containsKey(bo.e) ? mapA.get(bo.e) : "";
            acVar.d = mapA.containsKey(bo.h) ? mapA.get(bo.h) : "";
            String str = mapA.containsKey(bo.f) ? mapA.get(bo.f) : "";
            acVar.f2047c = str;
            acVar.f2047c = ax.a(str);
            if (mapA.containsKey(bo.t) && acVar.d(mapA.get(bo.t))) {
                return acVar.c();
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    private ac c() {
        if (TextUtils.isEmpty(this.e)) {
            this.e = "null";
            return null;
        }
        String str = this.f2045a;
        if (str == null) {
            str = "";
        }
        this.f2045a = str;
        String str2 = this.d;
        if (str2 == null) {
            str2 = "";
        }
        this.d = str2;
        String str3 = this.j;
        if (str3 == null) {
            str3 = "";
        }
        this.j = str3;
        this.f2047c = TextUtils.isEmpty(this.f2047c) ? ax.a() : this.f2047c;
        String str4 = this.h;
        if (str4 == null) {
            str4 = "";
        }
        this.h = str4;
        String str5 = this.i;
        if (str5 == null) {
            str5 = "";
        }
        this.i = str5;
        String str6 = this.f2046b;
        if (str6 == null) {
            str6 = "";
        }
        this.f2046b = str6;
        String str7 = this.f;
        if (str7 == null) {
            str7 = "";
        }
        this.f = str7;
        String str8 = this.g;
        if (str8 == null) {
            str8 = "";
        }
        this.g = str8;
        return this;
    }

    @Override // c.a.a.a.a.af
    public JSONObject a() {
        JSONObject jSONObjectA = a(true, true, true, true, true);
        try {
            if (jSONObjectA.toString().length() > m) {
                jSONObjectA = a(false, true, true, true, true);
            }
            if (jSONObjectA.toString().length() > m) {
                jSONObjectA = a(false, false, true, true, true);
            }
            if (jSONObjectA.toString().length() > m) {
                jSONObjectA = a(false, false, false, true, true);
            }
            if (jSONObjectA.toString().length() > m) {
                jSONObjectA = a(false, false, false, false, true);
            }
            int length = jSONObjectA.toString().length();
            if (length > m) {
                if (length - m > this.l.length()) {
                    return a(false, false, false, false, false);
                }
                this.l = this.l.substring(0, this.l.length() - (length - m));
                return a(false, false, false, false, true);
            }
        } catch (Throwable unused) {
        }
        return jSONObjectA;
    }

    protected JSONObject a(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(bo.i, this.e);
            jSONObject.put(bo.e, this.f2045a);
            jSONObject.put(bo.f, this.f2047c);
            jSONObject.put(bo.g, this.f2046b);
            if (z2) {
                jSONObject.put(bo.h, this.d);
            }
            jSONObject.put(bo.j, this.f);
            jSONObject.put(bo.k, this.g);
            if (z3) {
                jSONObject.put(bo.l, this.h);
            }
            if (z4) {
                jSONObject.put(bo.m, this.i);
            }
            jSONObject.put(bo.n, this.j);
            if (z) {
                jSONObject.put(bo.o, this.k);
            }
            if (z5) {
                jSONObject.put("st", this.l);
            }
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    @Override // c.a.a.a.a.af
    public int b() {
        return 2;
    }

    public void b(String str) {
        this.l = str;
    }

    protected boolean c(String str) {
        try {
            Map<String, String> mapA = a(str);
            this.f2045a = mapA.containsKey(bo.e) ? mapA.get(bo.e).replace("\"", "") : this.f2045a;
            this.d = mapA.containsKey(bo.h) ? mapA.get(bo.h).replace("\"", "") : "";
            String strReplace = mapA.containsKey(bo.f) ? mapA.get(bo.f).replace("\"", "") : "";
            this.f2047c = strReplace;
            this.f2047c = ax.a(strReplace);
            if (mapA.containsKey(bo.t)) {
                d(mapA.get(bo.t));
            } else {
                this.f2046b = mapA.containsKey(bo.u) ? mapA.get(bo.u).replace("\"", "") : "";
                this.e = mapA.containsKey(bo.i) ? mapA.get(bo.i).replace("\"", "") : "";
                this.f = mapA.containsKey(bo.j) ? mapA.get(bo.j).replace("\"", "") : "";
                this.h = mapA.containsKey(bo.l) ? mapA.get(bo.l).replace("\"", "") : "";
                this.i = mapA.containsKey(bo.m) ? mapA.get(bo.m).replace("\"", "") : "";
                this.j = mapA.containsKey(bo.n) ? mapA.get(bo.n).replace("\"", "") : "";
                this.g = mapA.containsKey(bo.k) ? mapA.get(bo.k).replace("\"", "") : "";
            }
            this.d = URLDecoder.decode(this.d);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    protected boolean d(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(bo.i)) {
                this.e = jSONObject.optString(bo.i);
            }
            if (jSONObject.has(bo.v)) {
                this.j = jSONObject.optString(bo.v);
            }
            if (jSONObject.has(bo.l)) {
                this.h = jSONObject.optString(bo.l);
            }
            if (jSONObject.has(bo.m)) {
                this.i = jSONObject.optString(bo.m);
            }
            if (!jSONObject.has(bo.k)) {
                return true;
            }
            this.g = jSONObject.optString(bo.k);
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }
}
