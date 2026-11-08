package cn.com.chinatelecom.account.api.e;

import android.content.Context;
import cn.com.chinatelecom.account.api.CtAuth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class f {

    /* renamed from: a, reason: collision with root package name */
    private static final String f2294a = "f";

    /* renamed from: b, reason: collision with root package name */
    private static int f2295b;

    /* renamed from: c, reason: collision with root package name */
    private static Map<String, e> f2296c = new HashMap();
    private static List<e> d = new ArrayList();
    private static e e = null;

    public static synchronized e a(String str) {
        e eVar;
        try {
            eVar = f2296c.containsKey(str) ? f2296c.get(str) : null;
            if (eVar == null) {
                eVar = new e(str);
                f2296c.put(str, eVar);
            }
        } catch (Throwable th) {
            th.printStackTrace();
            return new e(str);
        }
        return eVar;
    }

    public static void a(Context context, String str) {
        cn.com.chinatelecom.account.a.c.a(context, str);
    }

    public static synchronized void a(String str, JSONObject jSONObject, String str2) {
        try {
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (f2296c.containsKey(str)) {
            f2296c.get(str).g(str2);
            return;
        }
        if (d.size() > 0) {
            for (e eVar : d) {
                if (eVar.a() != null && eVar.a().equals(str) && jSONObject != null) {
                    jSONObject.remove("data");
                    eVar.g(jSONObject.toString());
                    eVar.g(str2);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context) {
        if (context == null) {
            return;
        }
        try {
            ArrayList arrayList = new ArrayList();
            synchronized (f.class) {
                if (e != null) {
                    arrayList.add(e.toString());
                    e = null;
                }
                Iterator<e> it = d.iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next().toString());
                }
                f2295b = 0;
                d.clear();
            }
            if (arrayList.isEmpty()) {
                return;
            }
            cn.com.chinatelecom.account.a.c.a(context, arrayList);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static synchronized void b(String str) {
        try {
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (f2296c.containsKey(str)) {
            e = f2296c.get(str);
            e.b();
            f2296c.remove(str);
        }
    }

    public static void b(String str, JSONObject jSONObject, String str2) {
        try {
            int iOptInt = jSONObject.optInt("result");
            String strOptString = jSONObject.optString("msg");
            if (iOptInt == 0) {
                a(str).a(iOptInt).e(strOptString);
            } else {
                a(str).a(iOptInt).e(strOptString).d(str2);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void c(String str) {
        try {
            synchronized (f.class) {
                if (f2296c.containsKey(str)) {
                    e eVar = f2296c.get(str);
                    eVar.b();
                    d.add(eVar);
                    f2296c.remove(str);
                }
                if (f2295b != 1 && !d.isEmpty()) {
                    f2295b = 1;
                    new Timer().schedule(new TimerTask() { // from class: cn.com.chinatelecom.account.api.e.f.1
                        @Override // java.util.TimerTask, java.lang.Runnable
                        public void run() {
                            f.b(CtAuth.mContext);
                        }
                    }, 8000L);
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
