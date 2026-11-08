package com.apm.insight.b;

import android.os.Looper;
import android.os.Process;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.core.internal.view.SupportMenu;
import com.apm.insight.runtime.u;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class h {

    /* renamed from: b, reason: collision with root package name */
    public static boolean f2785b = false;
    private static int t = 2;

    /* renamed from: a, reason: collision with root package name */
    c f2786a;

    /* renamed from: c, reason: collision with root package name */
    private int f2787c;
    private volatile int d;
    private int e;
    private int f;
    private f g;
    private b h;
    private long i;
    private long j;
    private int k;
    private long l;
    private String m;
    private String n;
    private com.apm.insight.b.e o;
    private volatile boolean p;
    private boolean q;
    private final u r;
    private volatile boolean s;
    private Runnable u;

    private static class a {

        /* renamed from: a, reason: collision with root package name */
        long f2792a;

        /* renamed from: b, reason: collision with root package name */
        long f2793b;

        /* renamed from: c, reason: collision with root package name */
        long f2794c;
        boolean d;
        int e;
        StackTraceElement[] f;

        private a() {
        }

        void a() {
            this.f2792a = -1L;
            this.f2793b = -1L;
            this.f2794c = -1L;
            this.e = -1;
            this.f = null;
        }
    }

    private static class b {

        /* renamed from: a, reason: collision with root package name */
        final int f2795a;

        /* renamed from: b, reason: collision with root package name */
        a f2796b;

        /* renamed from: c, reason: collision with root package name */
        final List<a> f2797c;
        private int d = 0;

        public b(int i) {
            this.f2795a = i;
            this.f2797c = new ArrayList(i);
        }

        a a() {
            if (this.f2796b == null) {
                return new a();
            }
            a aVar = this.f2796b;
            this.f2796b = null;
            return aVar;
        }

        void a(a aVar) {
            int size;
            if (this.f2797c.size() < this.f2795a) {
                this.f2797c.add(aVar);
                size = this.f2797c.size();
            } else {
                this.d %= this.f2795a;
                a aVar2 = this.f2797c.set(this.d, aVar);
                aVar2.a();
                this.f2796b = aVar2;
                size = this.d + 1;
            }
            this.d = size;
        }
    }

    public interface c {
    }

    public static class d {

        /* renamed from: a, reason: collision with root package name */
        long f2798a;

        /* renamed from: b, reason: collision with root package name */
        long f2799b;

        /* renamed from: c, reason: collision with root package name */
        long f2800c;
        long d;
        long e;
    }

    public static class e {

        /* renamed from: a, reason: collision with root package name */
        public long f2801a;

        /* renamed from: b, reason: collision with root package name */
        long f2802b;

        /* renamed from: c, reason: collision with root package name */
        long f2803c;
        int d;
        int e;
        long f;
        long g;
        String h;
        public String i;
        String j;
        d k;

        private void a(JSONObject jSONObject) throws JSONException {
            jSONObject.put("block_uuid", this.j);
            jSONObject.put("sblock_uuid", this.j);
            jSONObject.put("belong_frame", this.k != null);
            if (this.k != null) {
                jSONObject.put("vsyncDelayTime", this.f2803c - (this.k.f2798a / 1000000));
                jSONObject.put("doFrameTime", (this.k.f2799b / 1000000) - this.f2803c);
                jSONObject.put("inputHandlingTime", (this.k.f2800c / 1000000) - (this.k.f2799b / 1000000));
                jSONObject.put("animationsTime", (this.k.d / 1000000) - (this.k.f2800c / 1000000));
                jSONObject.put("performTraversalsTime", (this.k.e / 1000000) - (this.k.d / 1000000));
                jSONObject.put("drawTime", this.f2802b - (this.k.e / 1000000));
            }
        }

        public JSONObject a() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("msg", h.a(this.h));
                jSONObject.put("cpuDuration", this.g);
                jSONObject.put("duration", this.f);
                jSONObject.put("type", this.d);
                jSONObject.put("count", this.e);
                jSONObject.put("messageCount", this.e);
                jSONObject.put("lastDuration", this.f2802b - this.f2803c);
                jSONObject.put("start", this.f2801a);
                jSONObject.put("end", this.f2802b);
                a(jSONObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jSONObject;
        }

        void b() {
            this.d = -1;
            this.e = -1;
            this.f = -1L;
            this.h = null;
            this.j = null;
            this.k = null;
            this.i = null;
        }
    }

    private static class f {

        /* renamed from: a, reason: collision with root package name */
        int f2804a;

        /* renamed from: b, reason: collision with root package name */
        int f2805b;

        /* renamed from: c, reason: collision with root package name */
        e f2806c;
        List<e> d = new ArrayList();

        f(int i) {
            this.f2804a = i;
        }

        e a(int i) {
            if (this.f2806c == null) {
                e eVar = new e();
                eVar.d = i;
                return eVar;
            }
            this.f2806c.d = i;
            e eVar2 = this.f2806c;
            this.f2806c = null;
            return eVar2;
        }

        List<e> a() {
            ArrayList arrayList = new ArrayList();
            int i = 0;
            if (this.d.size() == this.f2804a) {
                for (int i2 = this.f2805b; i2 < this.d.size(); i2++) {
                    arrayList.add(this.d.get(i2));
                }
                while (i < this.f2805b - 1) {
                    arrayList.add(this.d.get(i));
                    i++;
                }
            } else {
                while (i < this.d.size()) {
                    arrayList.add(this.d.get(i));
                    i++;
                }
            }
            return arrayList;
        }

        void a(e eVar) {
            int size;
            if (this.d.size() < this.f2804a) {
                this.d.add(eVar);
                size = this.d.size();
            } else {
                this.f2805b %= this.f2804a;
                e eVar2 = this.d.set(this.f2805b, eVar);
                eVar2.b();
                this.f2806c = eVar2;
                size = this.f2805b + 1;
            }
            this.f2805b = size;
        }
    }

    public h(int i) {
        this(i, false);
    }

    public h(int i, boolean z) {
        this.f2787c = 0;
        this.d = 0;
        this.e = 100;
        this.f = 200;
        this.i = -1L;
        this.j = -1L;
        this.k = -1;
        this.l = -1L;
        this.p = false;
        this.q = false;
        this.s = false;
        this.u = new Runnable() { // from class: com.apm.insight.b.h.2

            /* renamed from: c, reason: collision with root package name */
            private long f2791c;

            /* renamed from: b, reason: collision with root package name */
            private long f2790b = 0;
            private int d = -1;
            private int e = 0;
            private int f = 0;

            @Override // java.lang.Runnable
            public void run() {
                long jUptimeMillis = SystemClock.uptimeMillis();
                a aVarA = h.this.h.a();
                if (this.d == h.this.d) {
                    this.e++;
                } else {
                    this.e = 0;
                    this.f = 0;
                    this.f2791c = jUptimeMillis;
                }
                this.d = h.this.d;
                if (this.e > 0 && this.e - this.f >= h.t && this.f2790b != 0 && jUptimeMillis - this.f2791c > 700 && h.this.s) {
                    aVarA.f = Looper.getMainLooper().getThread().getStackTrace();
                    this.f = this.e;
                }
                aVarA.d = h.this.s;
                aVarA.f2794c = (jUptimeMillis - this.f2790b) - 300;
                aVarA.f2792a = jUptimeMillis;
                this.f2790b = SystemClock.uptimeMillis();
                aVarA.f2793b = this.f2790b - jUptimeMillis;
                aVarA.e = h.this.d;
                h.this.r.a(h.this.u, 300L);
                h.this.h.a(aVarA);
            }
        };
        this.f2786a = new c() { // from class: com.apm.insight.b.h.1
        };
        if (!z && !f2785b) {
            this.r = null;
            return;
        }
        this.r = new u("looper_monitor");
        this.r.b();
        this.h = new b(TTAdConstant.DEFAULT_LIVE_SHOW_TIME_MAX);
        this.r.a(this.u, 300L);
    }

    private static long a(int i) {
        if (i < 0) {
            return 0L;
        }
        try {
            return com.apm.insight.runtime.g.a(i);
        } catch (Throwable unused) {
            return 0L;
        }
    }

    public static String a(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            return "unknown message";
        }
        try {
            String[] strArrSplit = str.split(":");
            String str3 = strArrSplit.length == 2 ? strArrSplit[1] : "";
            if (str.contains("{") && str.contains(com.alipay.sdk.util.f.d)) {
                str2 = str.split("\\{")[0];
                try {
                    str = str2 + str.split("\\}")[1];
                } catch (Throwable unused) {
                    return str2;
                }
            } else {
                str2 = str;
            }
            if (str.contains("@")) {
                String[] strArrSplit2 = str.split("@");
                if (strArrSplit2.length > 1) {
                    str = strArrSplit2[0];
                }
            }
            if (str.contains("(") && str.contains(")") && !str.endsWith(" null")) {
                String[] strArrSplit3 = str.split("\\(");
                if (strArrSplit3.length > 1) {
                    str = strArrSplit3[1];
                }
                str = str.replace(")", "");
            }
            if (str.startsWith(" ")) {
                str = str.replace(" ", "");
            }
            return str + str3;
        } catch (Throwable unused2) {
            return str;
        }
    }

    private void a(int i, long j, String str) {
        a(i, j, str, true);
    }

    private void a(int i, long j, String str, boolean z) {
        this.q = true;
        e eVarA = this.g.a(i);
        eVarA.f = j - this.i;
        if (z) {
            long jCurrentThreadTimeMillis = SystemClock.currentThreadTimeMillis();
            eVarA.g = jCurrentThreadTimeMillis - this.l;
            this.l = jCurrentThreadTimeMillis;
        } else {
            eVarA.g = -1L;
        }
        eVarA.e = this.f2787c;
        eVarA.h = str;
        eVarA.i = this.m;
        eVarA.f2801a = this.i;
        eVarA.f2802b = j;
        eVarA.f2803c = this.j;
        this.g.a(eVarA);
        this.f2787c = 0;
        this.i = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z, long j) {
        int i = this.d + 1;
        this.d = i;
        this.d = i & SupportMenu.USER_MASK;
        this.q = false;
        if (this.i < 0) {
            this.i = j;
        }
        if (this.j < 0) {
            this.j = j;
        }
        if (this.k < 0) {
            this.k = Process.myTid();
            this.l = SystemClock.currentThreadTimeMillis();
        }
        if (j - this.i > this.f) {
            if (j - this.j <= this.f) {
                a(9, j, this.n);
            } else if (!z) {
                if (this.f2787c != 0) {
                    a(9, this.j, this.m, false);
                }
                a(8, j, this.n, true);
            } else if (this.f2787c == 0) {
                a(1, j, "no message running");
            } else {
                a(9, this.j, this.m);
                a(1, j, "no message running", false);
            }
        }
        this.j = j;
        String str = this.n;
    }

    private void e() {
        this.e = 100;
        this.f = TTAdConstant.DEFAULT_LIVE_SHOW_TIME_MAX;
    }

    static /* synthetic */ int f(h hVar) {
        int i = hVar.f2787c;
        hVar.f2787c = i + 1;
        return i;
    }

    public e a(long j) {
        e eVar = new e();
        eVar.h = this.n;
        eVar.i = this.m;
        eVar.f = j - this.j;
        eVar.g = a(this.k) - this.l;
        eVar.e = this.f2787c;
        return eVar;
    }

    public void a() {
        if (this.p) {
            return;
        }
        this.p = true;
        e();
        this.g = new f(this.e);
        this.o = new com.apm.insight.b.e() { // from class: com.apm.insight.b.h.3
            @Override // com.apm.insight.b.e
            public void a(String str) {
                h.this.s = true;
                h.this.n = str;
                super.a(str);
                h.this.a(true, com.apm.insight.b.e.f2776a);
            }

            @Override // com.apm.insight.b.e
            public boolean a() {
                return true;
            }

            @Override // com.apm.insight.b.e
            public void b(String str) {
                super.b(str);
                h.f(h.this);
                h.this.a(false, com.apm.insight.b.e.f2776a);
                h.this.m = h.this.n;
                h.this.n = "no message running";
                h.this.s = false;
            }
        };
        i.a();
        i.a(this.o);
        k.a(k.a());
    }

    void b() {
        a();
    }

    public JSONArray c() {
        List<e> listA;
        JSONArray jSONArray = new JSONArray();
        try {
            listA = this.g.a();
        } catch (Throwable unused) {
        }
        if (listA == null) {
            return jSONArray;
        }
        int i = 0;
        for (e eVar : listA) {
            if (eVar != null) {
                i++;
                jSONArray.put(eVar.a().put("id", i));
            }
        }
        return jSONArray;
    }
}
