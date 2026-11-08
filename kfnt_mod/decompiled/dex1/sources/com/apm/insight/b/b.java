package com.apm.insight.b;

import android.content.Context;
import android.os.Build;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.apm.insight.CrashType;
import com.apm.insight.ICrashCallback;
import com.apm.insight.Npth;
import com.apm.insight.l.l;
import com.apm.insight.l.o;
import com.apm.insight.nativecrash.NativeImpl;
import com.apm.insight.runtime.r;
import com.bytedance.common.utility.date.DateDef;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    static volatile boolean f2761a = true;
    private static volatile boolean f;

    /* renamed from: c, reason: collision with root package name */
    private c f2763c;
    private final Context d;
    private volatile boolean e;
    private JSONObject j;
    private JSONObject k;
    private JSONArray p;
    private JSONObject q;
    private JSONArray t;
    private JSONArray u;
    private JSONObject v;
    private boolean w;
    private volatile boolean y;
    private long g = -1;
    private File h = null;
    private boolean i = true;
    private String l = "unknown";
    private String m = "unknown";
    private String n = "unknown";
    private String o = "npth_inner_default";
    private int r = 0;
    private long s = -1;
    private final Object x = new Object();
    private long z = -1;
    private long A = 0;
    private final Runnable B = new Runnable() { // from class: com.apm.insight.b.b.1
        @Override // java.lang.Runnable
        public void run() {
            try {
                b.this.a(200, 25);
            } catch (Throwable th) {
                com.apm.insight.b.a().a("NPTH_CATCH", th);
            }
        }
    };
    private int C = 0;
    private List<Pattern> D = null;

    /* renamed from: b, reason: collision with root package name */
    Pattern f2762b = null;
    private File E = null;

    public b(Context context) {
        this.d = context;
    }

    private static String a(float f2) {
        return f2 <= 0.0f ? "0%" : f2 <= 0.1f ? "0% - 10%" : f2 <= 0.3f ? "10% - 30%" : f2 <= 0.6f ? "30% - 60%" : f2 <= 0.9f ? "60% - 90%" : "90% - 100%";
    }

    private static String a(float f2, float f3) {
        return f3 > 0.0f ? a(f2 / f3) : f2 > 0.0f ? "100%" : "0%";
    }

    private JSONObject a(String str, JSONArray jSONArray) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArrayA = l.a(256, 128, jSONArray);
        if (jSONArrayA.length() != jSONArray.length()) {
            this.r++;
        }
        try {
            jSONObject.put("thread_name", str);
            jSONObject.put("thread_stack", jSONArrayA);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:138:0x02b8 A[LOOP:2: B:96:0x01f2->B:138:0x02b8, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:140:0x02c5  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0361  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0365 A[PHI: r5 r14 r15 r20 r21 r23 r26
  0x0365: PHI (r5v4 java.lang.String) = (r5v2 java.lang.String), (r5v1 java.lang.String) binds: [B:164:0x035f, B:141:0x02cb] A[DONT_GENERATE, DONT_INLINE]
  0x0365: PHI (r14v6 java.lang.String) = (r14v5 java.lang.String), (r14v1 java.lang.String) binds: [B:164:0x035f, B:141:0x02cb] A[DONT_GENERATE, DONT_INLINE]
  0x0365: PHI (r15v8 java.lang.String) = (r15v6 java.lang.String), (r15v1 java.lang.String) binds: [B:164:0x035f, B:141:0x02cb] A[DONT_GENERATE, DONT_INLINE]
  0x0365: PHI (r20v2 java.lang.String[]) = (r20v1 java.lang.String[]), (r20v3 java.lang.String[]) binds: [B:164:0x035f, B:141:0x02cb] A[DONT_GENERATE, DONT_INLINE]
  0x0365: PHI (r21v2 char) = (r21v1 char), (r21v3 char) binds: [B:164:0x035f, B:141:0x02cb] A[DONT_GENERATE, DONT_INLINE]
  0x0365: PHI (r23v2 int) = (r23v1 int), (r23v3 int) binds: [B:164:0x035f, B:141:0x02cb] A[DONT_GENERATE, DONT_INLINE]
  0x0365: PHI (r26v4 java.util.HashMap) = (r26v3 java.util.HashMap), (r26v5 java.util.HashMap) binds: [B:164:0x035f, B:141:0x02cb] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0371  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x03f4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:217:0x03e3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:219:0x03db A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:220:0x02c1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01f5  */
    /* JADX WARN: Type inference failed for: r12v12 */
    /* JADX WARN: Type inference failed for: r12v15 */
    /* JADX WARN: Type inference failed for: r12v18 */
    /* JADX WARN: Type inference failed for: r12v21 */
    /* JADX WARN: Type inference failed for: r12v24 */
    /* JADX WARN: Type inference failed for: r12v27 */
    /* JADX WARN: Type inference failed for: r12v28 */
    /* JADX WARN: Type inference failed for: r12v31 */
    /* JADX WARN: Unreachable blocks removed: 1, instructions: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(java.lang.String r28, org.json.JSONObject r29) throws org.json.JSONException {
        /*
            Method dump skipped, instructions count: 1168
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apm.insight.b.b.a(java.lang.String, org.json.JSONObject):void");
    }

    private static void a(HashMap<String, Float> map, JSONObject jSONObject, String str) throws JSONException {
        String str2;
        String strA;
        String str3 = "npth_anr_" + str;
        if (map.isEmpty()) {
            str2 = str3 + "_total";
            strA = "not found";
        } else {
            float fFloatValue = 0.0f;
            float fFloatValue2 = 0.0f;
            float fFloatValue3 = 0.0f;
            float fFloatValue4 = 0.0f;
            float fFloatValue5 = 0.0f;
            for (Map.Entry<String, Float> entry : map.entrySet()) {
                String key = entry.getKey();
                if (key.endsWith("user")) {
                    fFloatValue += entry.getValue().floatValue();
                } else if (key.endsWith("kernel")) {
                    fFloatValue2 += entry.getValue().floatValue();
                } else if (key.endsWith("iowait")) {
                    fFloatValue3 += entry.getValue().floatValue();
                } else if (key.endsWith("irq")) {
                    fFloatValue4 += entry.getValue().floatValue();
                } else if (key.endsWith("softirq")) {
                    fFloatValue5 += entry.getValue().floatValue();
                }
            }
            float f2 = fFloatValue + fFloatValue2 + fFloatValue3 + fFloatValue4 + fFloatValue5;
            jSONObject.put(str3 + "_total", b(f2));
            jSONObject.put(str3 + "_kernel_user_ratio", a(fFloatValue2, f2));
            str2 = str3 + "_iowait_user_ratio";
            strA = a(fFloatValue3, f2);
        }
        jSONObject.put(str2, strA);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:33:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0192  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(org.json.JSONArray r17) throws org.json.JSONException {
        /*
            Method dump skipped, instructions count: 518
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apm.insight.b.b.a(org.json.JSONArray):void");
    }

    private boolean a(long j) {
        if (this.y) {
            this.y = false;
            b(j);
        }
        return false;
    }

    private boolean a(String str) {
        if (this.D == null) {
            JSONArray jSONArrayC = com.apm.insight.runtime.a.c();
            if (jSONArrayC != null) {
                this.D = new LinkedList();
                this.o = jSONArrayC.optString(0);
                for (int i = 1; i < jSONArrayC.length(); i++) {
                    try {
                        this.D.add(Pattern.compile(jSONArrayC.optString(i)));
                    } catch (Throwable unused) {
                    }
                }
            }
            if (this.D == null) {
                this.D = new LinkedList();
                this.D.add(Pattern.compile("^main$"));
                this.D.add(Pattern.compile("^default_npth_thread$"));
                this.D.add(Pattern.compile("^RenderThread$"));
                this.D.add(Pattern.compile("^Jit thread pool worker thread.*$"));
            }
        }
        Iterator<Pattern> it = this.D.iterator();
        while (it.hasNext()) {
            if (it.next().matcher(str).matches()) {
                return true;
            }
        }
        return false;
    }

    private static String b(float f2) {
        return a(f2 / 100.0f);
    }

    private void b(long j) {
        if (this.A != this.z) {
            try {
                this.s = System.currentTimeMillis();
                this.u = g.b().c();
                this.t = k.a(100, j);
                this.k = g.b().a(j).a();
                this.v = new JSONObject();
                com.apm.insight.l.a.a(this.d, this.v);
                this.w = g();
                this.i = !Npth.hasCrash();
            } catch (Throwable unused) {
            }
            try {
                if (Build.VERSION.SDK_INT >= 21) {
                    this.g = this.s;
                    String strB = o.b();
                    File file = new File(new File(o.f(this.d), strB), "trace_" + com.apm.insight.l.a.c(this.d).replace(':', '_') + ".txt");
                    file.getParentFile().mkdirs();
                    com.apm.insight.l.i.a(file, com.apm.insight.l.b.a().format(new Date(System.currentTimeMillis())) + "\n", false);
                    r.a("anr_trace", strB);
                    NativeImpl.i(file.getAbsolutePath());
                    try {
                        this.p = com.apm.insight.l.i.b(file.getAbsolutePath());
                        a(this.p);
                    } catch (IOException unused2) {
                    } catch (Throwable th) {
                        com.apm.insight.b.a().a("NPTH_CATCH", th);
                    }
                }
                if (this.j == null) {
                    this.j = d.a(true);
                }
            } catch (Throwable th2) {
                com.apm.insight.b.a().a("NPTH_CATCH", th2);
            }
            com.apm.insight.l.f.a();
        } else {
            try {
                this.g = this.s;
                if (Build.VERSION.SDK_INT >= 21) {
                    String strB2 = o.b();
                    File file2 = new File(new File(o.f(this.d), strB2), "trace" + com.apm.insight.l.a.c(this.d).replace(':', '_') + ".txt");
                    file2.getParentFile().mkdirs();
                    com.apm.insight.l.i.a(file2, com.apm.insight.l.b.a().format(new Date(System.currentTimeMillis())) + "\n", false);
                    r.a("anr_trace", strB2);
                    NativeImpl.i(file2.getAbsolutePath());
                    try {
                        this.p = com.apm.insight.l.i.b(file2.getAbsolutePath());
                        a(this.p);
                    } catch (IOException unused3) {
                    } catch (Throwable th3) {
                        com.apm.insight.b.a().a("NPTH_CATCH", th3);
                    }
                }
                if (this.j == null) {
                    this.j = d.a(true);
                }
            } catch (Throwable th4) {
                com.apm.insight.b.a().a("NPTH_CATCH", th4);
            }
        }
        this.A = this.z;
        this.z = -1L;
        if (this.A == this.z) {
            this.A = this.z - 1;
        }
    }

    private static void b(String str) {
        Iterator<ICrashCallback> it = com.apm.insight.runtime.o.a().e().iterator();
        while (it.hasNext()) {
            try {
                it.next().onCrash(CrashType.ANR, str, null);
            } catch (Throwable th) {
                com.apm.insight.b.a().a("NPTH_CATCH", th);
            }
        }
    }

    @Nullable
    private int[] b(@NonNull JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.length(); i++) {
            String strOptString = jSONArray.optString(i);
            int iIndexOf = (strOptString == null || strOptString.isEmpty()) ? -1 : strOptString.indexOf("utm=");
            if (iIndexOf > 0) {
                if (this.f2762b == null) {
                    this.f2762b = Pattern.compile("[^0-9]+");
                }
                String[] strArrSplit = this.f2762b.split(strOptString.substring(iIndexOf));
                if (strArrSplit == null || strArrSplit.length < 2) {
                    return null;
                }
                int iIntValue = Integer.decode(strArrSplit[1]).intValue();
                int iIntValue2 = Integer.decode(strArrSplit[2]).intValue();
                return new int[]{iIntValue, iIntValue2, iIntValue + iIntValue2};
            }
        }
        return null;
    }

    private String c(long j) {
        long j2 = j - com.apm.insight.h.j();
        return j2 < com.bytedance.b.a.a.c.g.e ? "0 - 30s" : j2 < 60000 ? "30s - 1min" : j2 < com.bytedance.b.a.a.c.g.g ? "1min - 2min" : j2 < 300000 ? "2min - 5min" : j2 < 600000 ? "5min - 10min" : j2 < 1800000 ? "10min - 30min" : j2 < DateDef.HOUR ? "30min - 1h" : "1h - ";
    }

    private JSONObject c(@NonNull JSONArray jSONArray) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArrayA = l.a(256, 128, jSONArray);
        if (jSONArrayA.length() != jSONArray.length()) {
            this.r++;
        }
        try {
            jSONObject.put("thread_number", 1);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < jSONArrayA.length(); i++) {
                sb.append(jSONArrayA.getString(i));
                sb.append('\n');
            }
            jSONObject.put("mainStackFromTrace", sb.toString());
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    private boolean g() {
        boolean z = !com.apm.insight.l.a.a(this.d);
        if (!z || com.apm.insight.runtime.a.b.d().e() > 2000) {
            return z;
        }
        return false;
    }

    private File h() {
        if (this.E == null) {
            this.E = new File(this.d.getFilesDir(), "has_anr_signal_" + com.apm.insight.l.a.c(this.d).replaceAll(":", "_"));
        }
        return this.E;
    }

    private boolean i() {
        return com.apm.insight.runtime.a.i();
    }

    public void a() {
        if (this.e) {
            return;
        }
        this.f2763c = new c(this);
        this.g = com.apm.insight.h.j();
        this.e = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x0289  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0341  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0344  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0367  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x036a  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x039d A[Catch: Throwable -> 0x0285, TRY_LEAVE, TryCatch #5 {Throwable -> 0x0285, blocks: (B:100:0x027e, B:104:0x028a, B:108:0x0346, B:112:0x036c, B:114:0x039d), top: B:162:0x027e }] */
    /* JADX WARN: Removed duplicated region for block: B:116:0x03aa  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x03bc A[Catch: Throwable -> 0x03ea, TryCatch #7 {Throwable -> 0x03ea, blocks: (B:121:0x03b2, B:122:0x03bc, B:124:0x03c4, B:117:0x03ac), top: B:167:0x03a8, inners: #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:126:0x03ec A[Catch: Throwable -> 0x0492, TRY_ENTER, TryCatch #10 {Throwable -> 0x0492, blocks: (B:82:0x0184, B:86:0x0201, B:88:0x0206, B:91:0x020f, B:93:0x0213, B:95:0x021b, B:97:0x0223, B:126:0x03ec, B:127:0x0415, B:129:0x0419, B:130:0x0421, B:137:0x0476, B:96:0x0221), top: B:173:0x0184 }] */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0415 A[Catch: Throwable -> 0x0492, TryCatch #10 {Throwable -> 0x0492, blocks: (B:82:0x0184, B:86:0x0201, B:88:0x0206, B:91:0x020f, B:93:0x0213, B:95:0x021b, B:97:0x0223, B:126:0x03ec, B:127:0x0415, B:129:0x0419, B:130:0x0421, B:137:0x0476, B:96:0x0221), top: B:173:0x0184 }] */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0279 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01ff  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0206 A[Catch: Throwable -> 0x0492, TryCatch #10 {Throwable -> 0x0492, blocks: (B:82:0x0184, B:86:0x0201, B:88:0x0206, B:91:0x020f, B:93:0x0213, B:95:0x021b, B:97:0x0223, B:126:0x03ec, B:127:0x0415, B:129:0x0419, B:130:0x0421, B:137:0x0476, B:96:0x0221), top: B:173:0x0184 }] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0221 A[Catch: Throwable -> 0x0492, TryCatch #10 {Throwable -> 0x0492, blocks: (B:82:0x0184, B:86:0x0201, B:88:0x0206, B:91:0x020f, B:93:0x0213, B:95:0x021b, B:97:0x0223, B:126:0x03ec, B:127:0x0415, B:129:0x0419, B:130:0x0421, B:137:0x0476, B:96:0x0221), top: B:173:0x0184 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    boolean a(int r31, int r32) {
        /*
            Method dump skipped, instructions count: 1184
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apm.insight.b.b.a(int, int):boolean");
    }

    public void b() {
        if (this.e) {
            this.e = false;
            if (this.f2763c != null) {
                this.f2763c.b();
            }
            this.f2763c = null;
        }
    }

    public void c() {
        if (this.f2763c != null) {
            this.f2763c.a();
        }
    }

    public void d() {
        if (f) {
            return;
        }
        synchronized (this.x) {
            if (f) {
                return;
            }
            this.B.run();
        }
    }

    public void e() {
        if (NativeImpl.g()) {
            try {
                com.apm.insight.l.i.a(h(), String.valueOf(this.C + 1), false);
            } catch (Throwable th) {
                com.apm.insight.b.a().a("NPTH_CATCH", th);
            }
        }
        this.z = SystemClock.uptimeMillis();
        this.y = true;
    }

    public void f() {
        File fileH = h();
        try {
            this.C = Integer.decode(com.apm.insight.l.i.c(fileH.getAbsolutePath())).intValue();
            if (this.C >= 2) {
                NativeImpl.a(false);
            } else {
                NativeImpl.a(true);
            }
        } catch (IOException unused) {
            NativeImpl.a(true);
        } catch (Throwable unused2) {
            com.apm.insight.l.i.a(fileH);
        }
    }
}
