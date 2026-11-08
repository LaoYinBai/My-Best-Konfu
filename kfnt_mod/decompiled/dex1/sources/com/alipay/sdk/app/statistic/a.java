package com.alipay.sdk.app.statistic;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.packet.impl.d;
import com.alipay.sdk.packet.impl.e;
import com.alipay.sdk.util.g;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    static final class c {

        /* renamed from: a, reason: collision with root package name */
        private static final String f2570a = "alipay_cashier_statistic_v";

        c() {
        }

        static synchronized long a(Context context) throws NumberFormatException {
            long j;
            long j2 = 0;
            try {
                String strB = g.b(null, context, f2570a, null);
                if (!TextUtils.isEmpty(strB)) {
                    j2 = Long.parseLong(strB);
                }
            } catch (Throwable unused) {
            }
            j = j2 + 1;
            try {
                g.a(null, context, f2570a, Long.toString(j));
            } catch (Throwable unused2) {
            }
            return j;
        }
    }

    /* renamed from: com.alipay.sdk.app.statistic.a$a, reason: collision with other inner class name */
    private static final class C0076a {

        /* renamed from: a, reason: collision with root package name */
        private static final String f2565a = "RecordPref";

        /* renamed from: b, reason: collision with root package name */
        private static final String f2566b = "alipay_cashier_statistic_record";

        private C0076a() {
        }

        static synchronized String a(Context context, String str, String str2) {
            com.alipay.sdk.util.c.a(f2565a, "stat append " + str2 + " , " + str);
            if (context != null && !TextUtils.isEmpty(str)) {
                if (TextUtils.isEmpty(str2)) {
                    str2 = UUID.randomUUID().toString();
                }
                C0077a c0077aB = b(context);
                if (c0077aB.f2567a.size() > 20) {
                    c0077aB.f2567a.clear();
                }
                c0077aB.f2567a.put(str2, str);
                a(context, c0077aB);
                return str2;
            }
            return null;
        }

        static synchronized String a(Context context) {
            com.alipay.sdk.util.c.a(f2565a, "stat peek");
            if (context == null) {
                return null;
            }
            C0077a c0077aB = b(context);
            if (c0077aB.f2567a.isEmpty()) {
                return null;
            }
            try {
                return c0077aB.f2567a.entrySet().iterator().next().getValue();
            } catch (Throwable th) {
                com.alipay.sdk.util.c.a(th);
                return null;
            }
        }

        static synchronized int a(Context context, String str) {
            com.alipay.sdk.util.c.a(f2565a, "stat remove " + str);
            if (context != null && !TextUtils.isEmpty(str)) {
                C0077a c0077aB = b(context);
                if (c0077aB.f2567a.isEmpty()) {
                    return 0;
                }
                try {
                    ArrayList arrayList = new ArrayList();
                    for (Map.Entry<String, String> entry : c0077aB.f2567a.entrySet()) {
                        if (str.equals(entry.getValue())) {
                            arrayList.add(entry.getKey());
                        }
                    }
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        c0077aB.f2567a.remove((String) it.next());
                    }
                    a(context, c0077aB);
                    return arrayList.size();
                } catch (Throwable th) {
                    com.alipay.sdk.util.c.a(th);
                    int size = c0077aB.f2567a.size();
                    a(context, new C0077a());
                    return size;
                }
            }
            return 0;
        }

        private static synchronized C0077a b(Context context) {
            try {
                String strB = g.b(null, context, f2566b, null);
                if (TextUtils.isEmpty(strB)) {
                    return new C0077a();
                }
                return new C0077a(strB);
            } catch (Throwable th) {
                com.alipay.sdk.util.c.a(th);
                return new C0077a();
            }
        }

        private static synchronized void a(Context context, C0077a c0077a) {
            if (c0077a == null) {
                try {
                    try {
                        c0077a = new C0077a();
                    } catch (Throwable th) {
                        com.alipay.sdk.util.c.a(th);
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            }
            g.a(null, context, f2566b, c0077a.a());
        }

        /* renamed from: com.alipay.sdk.app.statistic.a$a$a, reason: collision with other inner class name */
        private static final class C0077a {

            /* renamed from: a, reason: collision with root package name */
            final LinkedHashMap<String, String> f2567a = new LinkedHashMap<>();

            C0077a() {
            }

            C0077a(String str) {
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONArray jSONArray2 = jSONArray.getJSONArray(i);
                        this.f2567a.put(jSONArray2.getString(0), jSONArray2.getString(1));
                    }
                } catch (Throwable th) {
                    com.alipay.sdk.util.c.a(th);
                }
            }

            String a() {
                try {
                    JSONArray jSONArray = new JSONArray();
                    for (Map.Entry<String, String> entry : this.f2567a.entrySet()) {
                        JSONArray jSONArray2 = new JSONArray();
                        jSONArray2.put(entry.getKey()).put(entry.getValue());
                        jSONArray.put(jSONArray2);
                    }
                    return jSONArray.toString();
                } catch (Throwable th) {
                    com.alipay.sdk.util.c.a(th);
                    return new JSONArray().toString();
                }
            }
        }
    }

    private static final class b {
        private b() {
        }

        static synchronized void a(Context context, com.alipay.sdk.app.statistic.b bVar, String str, String str2) {
            if (context == null || bVar == null || str == null) {
                return;
            }
            a(context, bVar.a(str), str2);
        }

        static synchronized void a(Context context) {
            a(context, null, null);
        }

        private static synchronized void a(final Context context, final String str, String str2) {
            if (context == null) {
                return;
            }
            if (!TextUtils.isEmpty(str)) {
                C0076a.a(context, str, str2);
            }
            new Thread(new Runnable() { // from class: com.alipay.sdk.app.statistic.a.b.1
                @Override // java.lang.Runnable
                public void run() {
                    if (TextUtils.isEmpty(str) || b.b(context, str)) {
                        for (int i = 0; i < 4; i++) {
                            String strA = C0076a.a(context);
                            if (TextUtils.isEmpty(strA) || !b.b(context, strA)) {
                                return;
                            }
                        }
                    }
                }
            }).start();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static synchronized boolean b(Context context, String str) {
            com.alipay.sdk.util.c.a(com.alipay.sdk.cons.a.x, "stat sub " + str);
            try {
                if ((com.alipay.sdk.data.a.p().g() ? new d() : new e()).a((com.alipay.sdk.sys.a) null, context, str) == null) {
                    return false;
                }
                C0076a.a(context, str);
                return true;
            } catch (Throwable th) {
                com.alipay.sdk.util.c.a(th);
                return false;
            }
        }
    }

    private a() {
    }

    public static synchronized void a(Context context, com.alipay.sdk.sys.a aVar, String str, String str2) {
        if (context != null) {
            if (aVar != null) {
                try {
                    C0076a.a(context, aVar.u.a(str), str2);
                } catch (Throwable th) {
                    com.alipay.sdk.util.c.a(th);
                }
            }
        }
    }

    public static synchronized void b(Context context, com.alipay.sdk.sys.a aVar, String str, String str2) {
        if (context == null || aVar == null) {
            return;
        }
        b.a(context, aVar.u, str, str2);
    }

    public static synchronized void a(Context context) {
        b.a(context);
    }

    public static void a(com.alipay.sdk.sys.a aVar, String str, Throwable th) {
        if (aVar == null || th == null || th.getClass() == null) {
            return;
        }
        aVar.u.a(str, th.getClass().getSimpleName(), th);
    }

    public static void a(com.alipay.sdk.sys.a aVar, String str, String str2, Throwable th, String str3) {
        if (aVar == null) {
            return;
        }
        aVar.u.a(str, str2, th, str3);
    }

    public static void a(com.alipay.sdk.sys.a aVar, String str, String str2, Throwable th) {
        if (aVar == null) {
            return;
        }
        aVar.u.a(str, str2, th);
    }

    public static void a(com.alipay.sdk.sys.a aVar, String str, String str2, String str3) {
        if (aVar == null) {
            return;
        }
        aVar.u.a(str, str2, str3);
    }

    public static void b(com.alipay.sdk.sys.a aVar, String str, String str2, String str3) {
        if (aVar == null) {
            return;
        }
        aVar.u.b(str, str2, str3);
    }

    public static void a(com.alipay.sdk.sys.a aVar, String str, String str2) {
        if (aVar == null) {
            return;
        }
        aVar.u.a(str, str2);
    }
}
