package com.vivo.push;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.SparseArray;
import com.vivo.push.b.z;
import com.vivo.push.util.ContextDelegate;
import com.vivo.push.util.n;
import com.vivo.push.util.r;
import com.vivo.push.util.v;
import com.vivo.push.util.y;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class d {
    private static volatile d m;
    public Context e;
    public com.vivo.push.util.a g;
    String h;
    public String i;
    public boolean j;
    public int l;
    private Boolean r;
    private Long s;
    long a = -1;
    long b = -1;
    long c = -1;
    long d = -1;
    private long n = -1;
    private long o = -1;
    public boolean f = true;
    private SparseArray<a> p = new SparseArray<>();
    private int q = 0;
    public IPushClientFactory k = new c();

    private d() {
    }

    public static synchronized d a() {
        if (m == null) {
            m = new d();
        }
        return m;
    }

    public final synchronized void a(Context context) {
        if (this.e == null) {
            this.e = ContextDelegate.getContext(context).getApplicationContext();
            this.j = r.a(context, context.getPackageName(), "com.vivo.pushclient.action.RECEIVE");
            v.b().a(this.e);
            a(new com.vivo.push.b.g());
            this.g = new com.vivo.push.util.a();
            this.g.a(context, "com.vivo.push_preferences.appconfig_v1");
            this.h = e();
            this.i = this.g.a("APP_ALIAS");
        }
    }

    public final List<String> b() {
        String strA = this.g.a("APP_TAGS");
        ArrayList arrayList = new ArrayList();
        try {
        } catch (JSONException unused) {
            this.g.c("APP_TAGS");
            arrayList.clear();
            n.d("PushClientManager", "getTags error");
        }
        if (TextUtils.isEmpty(strA)) {
            return arrayList;
        }
        Iterator<String> itKeys = new JSONObject(strA).keys();
        while (itKeys.hasNext()) {
            arrayList.add(itKeys.next());
        }
        return arrayList;
    }

    public final void a(List<String> list) throws JSONException {
        JSONObject jSONObject;
        try {
            if (list.size() <= 0) {
                return;
            }
            String strA = this.g.a("APP_TAGS");
            if (TextUtils.isEmpty(strA)) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = new JSONObject(strA);
            }
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                jSONObject.put(it.next(), System.currentTimeMillis());
            }
            String string = jSONObject.toString();
            if (TextUtils.isEmpty(string)) {
                this.g.c("APP_TAGS");
            } else {
                this.g.a("APP_TAGS", string);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            this.g.c("APP_TAGS");
        }
    }

    public final void b(List<String> list) {
        JSONObject jSONObject;
        try {
            if (list.size() <= 0) {
                return;
            }
            String strA = this.g.a("APP_TAGS");
            if (TextUtils.isEmpty(strA)) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = new JSONObject(strA);
            }
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                jSONObject.remove(it.next());
            }
            String string = jSONObject.toString();
            if (TextUtils.isEmpty(string)) {
                this.g.c("APP_TAGS");
            } else {
                this.g.a("APP_TAGS", string);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            this.g.c("APP_TAGS");
        }
    }

    public final boolean c() {
        if (this.e == null) {
            n.d("PushClientManager", "support:context is null");
            return false;
        }
        this.r = Boolean.valueOf(f());
        return this.r.booleanValue();
    }

    public final void d() {
        this.i = null;
        this.g.c("APP_ALIAS");
    }

    final String e() {
        String strA = this.g.a("APP_TOKEN");
        if (TextUtils.isEmpty(strA) || !y.a(this.e, this.e.getPackageName(), strA)) {
            return strA;
        }
        this.g.a();
        return null;
    }

    public final void a(String str) {
        this.h = str;
        this.g.a("APP_TOKEN", this.h);
    }

    final a a(final com.vivo.push.b.b bVar, IPushActionListener iPushActionListener) {
        a aVar = new a(bVar, iPushActionListener);
        final String strA = a(aVar);
        bVar.e = strA;
        aVar.b = new Runnable() { // from class: com.vivo.push.d.2
            @Override // java.lang.Runnable
            public final void run() {
                d.this.a(bVar);
                d.this.c(strA);
            }
        };
        return aVar;
    }

    public final void a(String str, int i, Object... objArr) {
        a aVarB = b(str);
        if (aVarB != null) {
            aVarB.a(i, objArr);
        } else {
            n.d("PushClientManager", "notifyApp token is null");
        }
    }

    static boolean a(long j) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        return j == -1 || jElapsedRealtime <= j || jElapsedRealtime >= j + 2000;
    }

    public final void a(String str, int i) {
        a aVarB = b(str);
        if (aVarB != null) {
            aVarB.a(i, new Object[0]);
        } else {
            n.d("PushClientManager", "notifyStatusChanged token is null");
        }
    }

    final synchronized String a(a aVar) {
        int i;
        this.p.put(this.q, aVar);
        i = this.q;
        this.q = i + 1;
        return Integer.toString(i);
    }

    final synchronized a b(String str) {
        if (str != null) {
            try {
                int i = Integer.parseInt(str);
                a aVar = this.p.get(i);
                this.p.delete(i);
                return aVar;
            } catch (Exception unused) {
            }
        }
        return null;
    }

    final void a(ArrayList<String> arrayList, IPushActionListener iPushActionListener) {
        if (this.e == null) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(102);
                return;
            }
            return;
        }
        z zVar = new z(true, this.e.getPackageName(), arrayList);
        zVar.h = 500;
        if (this.j) {
            if (!f()) {
                if (iPushActionListener != null) {
                    iPushActionListener.onStateChanged(101);
                    return;
                }
                return;
            }
            if (!a(this.n)) {
                if (iPushActionListener != null) {
                    iPushActionListener.onStateChanged(1002);
                    return;
                }
                return;
            }
            this.n = SystemClock.elapsedRealtime();
            String strA = a(new a(zVar, iPushActionListener));
            zVar.e = strA;
            if (TextUtils.isEmpty(this.h)) {
                a(strA, 20001);
                return;
            }
            if (arrayList.size() < 0) {
                a(strA, 20002);
                return;
            }
            if (arrayList.size() + b().size() > 500) {
                a(strA, 20004);
                return;
            }
            Iterator<String> it = arrayList.iterator();
            while (it.hasNext()) {
                if (it.next().length() > 70) {
                    a(strA, 20003);
                    return;
                }
            }
            a(zVar);
            c(strA);
            return;
        }
        a(zVar);
        if (iPushActionListener != null) {
            iPushActionListener.onStateChanged(0);
        }
    }

    final void b(ArrayList<String> arrayList, IPushActionListener iPushActionListener) {
        if (this.e == null) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(102);
                return;
            }
            return;
        }
        z zVar = new z(false, this.e.getPackageName(), arrayList);
        zVar.h = 500;
        if (this.j) {
            if (!f()) {
                if (iPushActionListener != null) {
                    iPushActionListener.onStateChanged(101);
                    return;
                }
                return;
            }
            if (!a(this.o)) {
                if (iPushActionListener != null) {
                    iPushActionListener.onStateChanged(1002);
                    return;
                }
                return;
            }
            this.o = SystemClock.elapsedRealtime();
            String strA = a(new a(zVar, iPushActionListener));
            zVar.e = strA;
            if (TextUtils.isEmpty(this.h)) {
                a(strA, 20001);
                return;
            }
            if (arrayList.size() < 0) {
                a(strA, 20002);
                return;
            }
            if (arrayList.size() > 500) {
                a(strA, 20004);
                return;
            }
            Iterator<String> it = arrayList.iterator();
            while (it.hasNext()) {
                if (it.next().length() > 70) {
                    a(strA, 20003);
                    return;
                }
            }
            a(zVar);
            c(strA);
            return;
        }
        a(zVar);
        if (iPushActionListener != null) {
            iPushActionListener.onStateChanged(0);
        }
    }

    public final void a(g gVar) {
        Context context = a().e;
        if (gVar == null) {
            n.a("PushClientManager", "sendCommand, null command!");
            if (context != null) {
                n.c(context, "[执行指令失败]指令空！");
                return;
            }
            return;
        }
        e eVarCreateTask = this.k.createTask(gVar);
        if (eVarCreateTask == null) {
            n.a("PushClientManager", "sendCommand, null command task! pushCommand = " + gVar);
            if (context != null) {
                n.c(context, "[执行指令失败]指令" + gVar + "任务空！");
                return;
            }
            return;
        }
        n.d("PushClientManager", "client--sendCommand, command = " + gVar);
        f.a(eVarCreateTask);
    }

    final void c(final String str) {
        f.a(new Runnable() { // from class: com.vivo.push.d.5
            @Override // java.lang.Runnable
            public final void run() {
                a aVarB = d.this.b(str);
                if (aVarB != null) {
                    aVarB.a(1003, new Object[0]);
                }
            }
        });
    }

    final boolean f() {
        long jLongValue;
        if (this.r == null) {
            if (this.e == null) {
                jLongValue = -1;
            } else {
                if (this.s == null) {
                    this.s = Long.valueOf(y.b(this.e));
                }
                jLongValue = this.s.longValue();
            }
            this.r = Boolean.valueOf(jLongValue >= 1230 && y.e(this.e));
        }
        return this.r.booleanValue();
    }

    public static class a {
        IPushActionListener a;
        Runnable b;
        Object[] c;
        private IPushActionListener d;
        private com.vivo.push.b.c e;

        public a(com.vivo.push.b.c cVar, IPushActionListener iPushActionListener) {
            this.e = cVar;
            this.d = iPushActionListener;
        }

        public final void a(int i, Object... objArr) {
            this.c = objArr;
            if (this.a != null) {
                this.a.onStateChanged(i);
            }
            if (this.d != null) {
                this.d.onStateChanged(i);
            }
        }

        public final void a() {
            if (this.b == null) {
                n.a("PushClientManager", "task is null");
            } else {
                this.b.run();
            }
        }
    }
}
