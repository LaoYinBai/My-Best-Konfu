package com.apm.insight.k;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.apm.insight.CrashType;
import com.apm.insight.Npth;
import com.apm.insight.entity.Header;
import com.apm.insight.entity.b;
import com.apm.insight.l.o;
import com.apm.insight.l.p;
import com.apm.insight.l.r;
import com.apm.insight.l.w;
import com.apm.insight.nativecrash.NativeImpl;
import com.apm.insight.runtime.q;
import com.bytedance.sdk.openadsdk.api.plugin.PluginConstants;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class b {
    private static volatile b d;

    /* renamed from: c, reason: collision with root package name */
    private Context f2895c;
    private C0085b f;
    private HashMap<String, C0085b> g;

    /* renamed from: a, reason: collision with root package name */
    List<File> f2893a = new ArrayList();

    /* renamed from: b, reason: collision with root package name */
    List<File> f2894b = new ArrayList();
    private int e = -1;
    private volatile boolean h = false;
    private Runnable i = new Runnable() { // from class: com.apm.insight.k.b.1
        @Override // java.lang.Runnable
        public void run() throws Throwable {
            b.this.g();
        }
    };
    private Runnable j = new Runnable() { // from class: com.apm.insight.k.b.2
        @Override // java.lang.Runnable
        public void run() throws Throwable {
            b.this.e();
        }
    };

    static class a {

        /* renamed from: a, reason: collision with root package name */
        File f2904a;

        /* renamed from: b, reason: collision with root package name */
        long f2905b;

        /* renamed from: c, reason: collision with root package name */
        long f2906c;

        @Nullable
        CrashType d;
        String e;

        a(File file, long j, @Nullable CrashType crashType) {
            this.f2905b = -1L;
            this.f2906c = -1L;
            this.f2904a = file;
            this.f2905b = j;
            this.d = crashType;
            this.e = file.getName();
        }

        a(File file, @Nullable CrashType crashType) {
            this.f2905b = -1L;
            this.f2906c = -1L;
            this.f2904a = file;
            this.d = crashType;
            this.e = file.getName();
        }
    }

    /* renamed from: com.apm.insight.k.b$b, reason: collision with other inner class name */
    static class C0085b {

        /* renamed from: a, reason: collision with root package name */
        String f2907a;
        a d;
        a e;

        /* renamed from: b, reason: collision with root package name */
        List<a> f2908b = new ArrayList();

        /* renamed from: c, reason: collision with root package name */
        List<a> f2909c = new ArrayList();
        boolean f = false;
        int g = 0;

        C0085b(String str) {
            this.f2907a = str;
        }
    }

    private b(Context context) {
        this.f2895c = context;
    }

    @Nullable
    private com.apm.insight.entity.e a(File file, CrashType crashType, String str, long j, long j2) {
        com.apm.insight.entity.e eVar = null;
        try {
            if (file.isFile()) {
                com.apm.insight.l.i.a(file);
                return null;
            }
            boolean z = crashType == CrashType.LAUNCH;
            if (crashType == null) {
                return com.apm.insight.l.i.d(new File(file, file.getName()).getAbsolutePath());
            }
            com.apm.insight.entity.e eVarA = com.apm.insight.l.i.a(file, crashType);
            try {
                JSONObject jSONObjectB = eVarA.b();
                if (eVarA.b() == null) {
                    com.apm.insight.l.i.a(file);
                } else {
                    if (crashType == CrashType.ANR) {
                        return eVarA;
                    }
                    jSONObjectB.put("crash_time", j);
                    jSONObjectB.put("app_start_time", j2);
                    JSONObject jSONObjectOptJSONObject = jSONObjectB.optJSONObject("header");
                    if (jSONObjectOptJSONObject == null) {
                        jSONObjectOptJSONObject = Header.a(this.f2895c, j).f();
                    } else if (z) {
                        jSONObjectB.remove("header");
                    }
                    String strOptString = jSONObjectOptJSONObject.optString("sdk_version_name", null);
                    if (strOptString == null) {
                        strOptString = "1.4.0";
                    }
                    com.apm.insight.entity.a.a(jSONObjectB, "filters", PluginConstants.KEY_SDK_VERSION, strOptString);
                    if (com.apm.insight.l.i.a(jSONObjectB.optJSONArray("logcat"))) {
                        jSONObjectB.put("logcat", com.apm.insight.runtime.k.b(str));
                    }
                    com.apm.insight.entity.a.a(jSONObjectB, "filters", "has_dump", "true");
                    com.apm.insight.entity.a.a(jSONObjectB, "filters", "has_logcat", String.valueOf(!com.apm.insight.l.l.a(jSONObjectB, "logcat")));
                    com.apm.insight.entity.a.a(jSONObjectB, "filters", "memory_leak", String.valueOf(com.apm.insight.entity.a.b(str)));
                    com.apm.insight.entity.a.a(jSONObjectB, "filters", "fd_leak", String.valueOf(com.apm.insight.entity.a.c(str)));
                    com.apm.insight.entity.a.a(jSONObjectB, "filters", "threads_leak", String.valueOf(com.apm.insight.entity.a.d(str)));
                    com.apm.insight.entity.a.a(jSONObjectB, "filters", "is_64_devices", String.valueOf(Header.a()));
                    com.apm.insight.entity.a.a(jSONObjectB, "filters", "is_64_runtime", String.valueOf(NativeImpl.e()));
                    com.apm.insight.entity.a.a(jSONObjectB, "filters", "is_x86_devices", String.valueOf(Header.b()));
                    com.apm.insight.entity.a.a(jSONObjectB, "filters", "has_meminfo_file", String.valueOf(com.apm.insight.entity.a.a(str)));
                    com.apm.insight.entity.a.a(jSONObjectB, "filters", "is_root", String.valueOf(com.apm.insight.nativecrash.b.m()));
                    jSONObjectB.put("launch_did", com.apm.insight.i.a.a(this.f2895c));
                    jSONObjectB.put("crash_uuid", file.getName());
                    jSONObjectB.put("jiffy", q.a.a());
                    try {
                        long j3 = Long.parseLong(com.apm.insight.runtime.b.a(j, str));
                        com.apm.insight.entity.a.a(jSONObjectB, "filters", "lastAliveTime", Math.abs(j3 - j) < 60000 ? "< 60s" : "> 60s");
                        jSONObjectB.put("lastAliveTime", String.valueOf(j3));
                    } catch (Throwable unused) {
                        jSONObjectB.put("lastAliveTime", "unknown");
                        com.apm.insight.entity.a.a(jSONObjectB, "filters", "lastAliveTime", "unknown");
                    }
                    jSONObjectB.put("has_dump", "true");
                    if (jSONObjectB.opt("storage") == null) {
                        com.apm.insight.entity.a.a(jSONObjectB, w.a(com.apm.insight.h.g()));
                    }
                    if (Header.b(jSONObjectOptJSONObject)) {
                        com.apm.insight.entity.a.a(jSONObjectB, "filters", "unauthentic_version", "unauthentic_version");
                    }
                    com.apm.insight.entity.d.b(jSONObjectB);
                    eVarA.b().put("upload_scene", "launch_scan");
                    if (z) {
                        JSONObject jSONObject = new JSONObject();
                        jSONObjectB.put("event_type", "start_crash");
                        jSONObjectB.put("stack", jSONObjectB.remove("data"));
                        jSONObject.put("data", new JSONArray().put(jSONObjectB));
                        jSONObject.put("header", jSONObjectOptJSONObject);
                        eVarA.a(jSONObject);
                    } else {
                        jSONObjectB.put("isJava", 1);
                    }
                }
                return eVarA;
            } catch (Throwable th) {
                th = th;
                eVar = eVarA;
                com.apm.insight.l.i.a(file);
                com.apm.insight.b.a().a("NPTH_CATCH", th);
                return eVar;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static b a() {
        if (d == null) {
            synchronized (b.class) {
                if (d == null) {
                    d = new b(com.apm.insight.h.g());
                }
            }
        }
        return d;
    }

    private JSONObject a(com.apm.insight.nativecrash.b bVar) throws Throwable {
        JSONObject jSONObjectD = bVar.d();
        if (jSONObjectD != null && jSONObjectD.length() != 0) {
            return jSONObjectD;
        }
        if (com.apm.insight.h.d()) {
            bVar.l();
        }
        if (!bVar.c()) {
            bVar.k();
            return null;
        }
        if (!bVar.f()) {
            bVar.k();
            return null;
        }
        if (bVar.g()) {
            bVar.k();
            return null;
        }
        bVar.e();
        return bVar.j();
    }

    private void a(C0085b c0085b) {
        com.apm.insight.l.i.a(o.a(this.f2895c, c0085b.f2907a));
    }

    private void a(final C0085b c0085b, boolean z, @Nullable com.apm.insight.runtime.h hVar) {
        JSONObject jSONObjectB;
        JSONObject jSONObjectOptJSONObject;
        JSONArray jSONArray;
        if (c0085b.f2908b.isEmpty()) {
            return;
        }
        if (c0085b.e == null) {
            c0085b.e = c0085b.d;
        }
        for (a aVar : c0085b.f2908b) {
            try {
                final File file = aVar.f2904a;
                CrashType crashType = aVar.d;
                final com.apm.insight.entity.e eVarA = a(file, crashType, c0085b.f2907a, aVar.f2905b, aVar.f2906c);
                if (eVarA == null || (jSONObjectB = eVarA.b()) == null || (jSONObjectOptJSONObject = jSONObjectB.optJSONObject("header")) == null) {
                    com.apm.insight.l.i.a(file);
                } else if (crashType != null || (!new File(file, file.getName()).exists() && file.getName().split("_").length >= 5)) {
                    File fileA = com.apm.insight.entity.b.a(file);
                    if (fileA.exists()) {
                        try {
                            jSONArray = new JSONArray(com.apm.insight.l.i.c(fileA));
                            JSONObject jSONObjectOptJSONObject2 = crashType == CrashType.LAUNCH ? ((JSONArray) jSONObjectB.opt("data")).optJSONObject(0) : jSONObjectB;
                            if (!(z || c0085b.e == aVar) || aVar.e.contains("ignore")) {
                                try {
                                    com.apm.insight.entity.a.a(jSONObjectOptJSONObject2, "filters", "aid", String.valueOf(String.valueOf(jSONObjectOptJSONObject.opt("aid"))));
                                    com.apm.insight.entity.a.a(jSONObjectOptJSONObject2, "filters", "has_ignore", String.valueOf(aVar.e.contains("ignore")));
                                    jSONObjectOptJSONObject.put("aid", 2010);
                                } catch (Throwable th) {
                                    com.apm.insight.b.a().a("NPTH_CATCH", th);
                                }
                            } else if (hVar != null && !hVar.a(jSONObjectOptJSONObject2.optString("crash_md5", com.bytedance.b.a.a.d.m))) {
                                com.apm.insight.l.i.a(aVar.f2904a);
                            }
                            com.apm.insight.entity.a.a(jSONObjectOptJSONObject2, "filters", "start_uuid", c0085b.f2907a);
                            com.apm.insight.entity.a.a(jSONObjectOptJSONObject2, "filters", "leak_threads_count", String.valueOf(c0085b.g));
                            com.apm.insight.entity.a.a(jSONObjectOptJSONObject2, "filters", "crash_thread_name", jSONObjectOptJSONObject2.optString("crash_thread_name", "unknown"));
                            r.a(jSONObjectB);
                        } catch (Throwable unused) {
                        }
                        try {
                            com.apm.insight.entity.b.a(jSONObjectB, jSONArray, new b.a() { // from class: com.apm.insight.k.b.3
                                @Override // com.apm.insight.entity.b.a
                                public void a(JSONObject jSONObject) {
                                    e.a(eVarA.a(), jSONObject.toString(), new File(file, "logZip"), o.a(b.this.f2895c, c0085b.f2907a));
                                }
                            });
                            if (!com.apm.insight.l.i.a(file)) {
                                com.apm.insight.e.a.a().a(com.apm.insight.e.a.a.a(file.getAbsolutePath()));
                            }
                            c.a(crashType, jSONObjectB);
                        } catch (Throwable th2) {
                            th = th2;
                            com.apm.insight.b.a().a("NPTH_CATCH", th);
                            com.apm.insight.l.i.a(aVar.f2904a);
                        }
                    } else {
                        com.apm.insight.l.i.a(file);
                    }
                } else if (e.b(eVarA.a(), jSONObjectB.toString()).a()) {
                    com.apm.insight.l.i.a(file);
                }
            } catch (Throwable th3) {
                th = th3;
                com.apm.insight.b.a().a("NPTH_CATCH", th);
                com.apm.insight.l.i.a(aVar.f2904a);
            }
        }
    }

    private void a(File file, C0085b c0085b) {
    }

    private void a(HashMap<String, C0085b> map) {
        File[] fileArrListFiles = o.f(this.f2895c).listFiles();
        if (fileArrListFiles == null || fileArrListFiles.length == 0) {
            return;
        }
        for (int i = 0; i < fileArrListFiles.length && i < 5; i++) {
            File file = fileArrListFiles[i];
            try {
                if (file.isDirectory() && file.getName().endsWith("G")) {
                    String name = file.getName();
                    C0085b c0085b = map.get(name);
                    if (c0085b == null) {
                        c0085b = new C0085b(name);
                        map.put(name, c0085b);
                    }
                    JSONArray jSONArrayA = com.apm.insight.nativecrash.c.a(o.l(file), o.m(file));
                    c0085b.g = jSONArrayA.length();
                    if (c0085b.g > 0) {
                        try {
                            com.apm.insight.l.i.a(o.n(file), jSONArrayA, false);
                        } catch (Throwable unused) {
                        }
                    }
                } else {
                    com.apm.insight.l.i.a(file);
                }
            } catch (Throwable th) {
                com.apm.insight.b.a().a("NPTH_CATCH", th);
                com.apm.insight.l.i.a(file);
            }
        }
    }

    private void a(HashMap<String, C0085b> map, C0085b c0085b) {
        File[] fileArrListFiles = o.d(this.f2895c).listFiles();
        if (fileArrListFiles == null || fileArrListFiles.length == 0) {
            return;
        }
        for (int i = 0; i < fileArrListFiles.length && i < 5; i++) {
            File file = fileArrListFiles[i];
            try {
                if (file.isDirectory() && file.getName().endsWith("G")) {
                    String name = file.getName();
                    C0085b c0085b2 = map.get(name);
                    if (c0085b2 == null) {
                        c0085b2 = new C0085b(name);
                        map.put(name, c0085b2);
                    }
                    c0085b2.f2909c.add(new a(file, CrashType.NATIVE));
                } else {
                    com.apm.insight.l.i.a(file);
                }
            } catch (Throwable th) {
                com.apm.insight.b.a().a("NPTH_CATCH", th);
                com.apm.insight.l.i.a(file);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x006c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(java.util.HashMap<java.lang.String, com.apm.insight.k.b.C0085b> r17, com.apm.insight.k.b.C0085b r18, java.io.File r19, java.lang.String r20) {
        /*
            Method dump skipped, instructions count: 236
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apm.insight.k.b.a(java.util.HashMap, com.apm.insight.k.b$b, java.io.File, java.lang.String):void");
    }

    private boolean a(File file) {
        String[] list = file.list();
        if (list == null) {
            return false;
        }
        for (String str : list) {
            if (!TextUtils.isEmpty(str) && str.endsWith("")) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x00df A[Catch: Throwable -> 0x0125, TryCatch #0 {Throwable -> 0x0125, blocks: (B:13:0x003c, B:15:0x0045, B:18:0x004c, B:21:0x0054, B:23:0x005e, B:25:0x0064, B:46:0x00c5, B:48:0x00df, B:50:0x00f4, B:52:0x00fd, B:54:0x011b, B:51:0x00f9, B:55:0x011e, B:28:0x0070, B:30:0x0074, B:32:0x007c, B:34:0x0080, B:37:0x0089, B:39:0x008f, B:40:0x0092, B:41:0x0095, B:43:0x00bc, B:27:0x006c), top: B:60:0x003c }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void b(final com.apm.insight.k.b.C0085b r13, boolean r14, @androidx.annotation.Nullable com.apm.insight.runtime.h r15) {
        /*
            Method dump skipped, instructions count: 309
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apm.insight.k.b.b(com.apm.insight.k.b$b, boolean, com.apm.insight.runtime.h):void");
    }

    private void b(HashMap<String, C0085b> map, C0085b c0085b) {
        File[] fileArrListFiles = o.a(this.f2895c).listFiles();
        if (fileArrListFiles == null) {
            return;
        }
        Arrays.sort(fileArrListFiles, Collections.reverseOrder());
        for (File file : fileArrListFiles) {
            try {
                if (com.apm.insight.e.a.a().a(file.getAbsolutePath())) {
                    com.apm.insight.l.i.a(file);
                } else if (!com.apm.insight.l.i.g(file) && !com.apm.insight.g.a.a().b(file.getName())) {
                    if (file.isFile()) {
                        com.apm.insight.l.i.a(file);
                    } else {
                        a(map, c0085b, file, file.getName());
                    }
                }
            } catch (Throwable th) {
                com.apm.insight.b.a().a("NPTH_CATCH", th);
            }
        }
    }

    private void c(HashMap<String, C0085b> map, C0085b c0085b) {
        com.apm.insight.l.i.a(o.b(this.f2895c));
    }

    private void d() throws Throwable {
        if (this.f != null) {
            return;
        }
        this.f = new C0085b("old_uuid");
        this.g = new HashMap<>();
        a(this.g);
        b(this.g, this.f);
        c(this.g, this.f);
        a(this.g, this.f);
        b(this.f, true, null);
        a(this.f, true, null);
        this.f = null;
        if (this.g.isEmpty()) {
            f();
        } else {
            g();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() throws Throwable {
        if (this.h || this.g == null) {
            return;
        }
        if (!p.b(this.f2895c)) {
            f();
        }
        boolean zH = h();
        com.apm.insight.runtime.h hVar = new com.apm.insight.runtime.h(this.f2895c);
        Iterator<C0085b> it = this.g.values().iterator();
        while (it.hasNext()) {
            b(it.next(), zH, hVar);
        }
        Iterator<C0085b> it2 = this.g.values().iterator();
        while (it2.hasNext()) {
            a(it2.next(), zH, hVar);
        }
        Iterator<C0085b> it3 = this.g.values().iterator();
        while (it3.hasNext()) {
            a(it3.next());
        }
        hVar.a();
        com.apm.insight.runtime.b.a();
        f();
    }

    private void f() {
        this.h = true;
        this.g = null;
        NativeImpl.i();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() throws Throwable {
        if (this.h) {
            return;
        }
        if (!p.b(this.f2895c) || (System.currentTimeMillis() - com.apm.insight.h.j() <= 5000 && com.apm.insight.h.i().isApmExists() && !Npth.hasCrash())) {
            com.apm.insight.runtime.p.b().a(this.i, 5000L);
        } else {
            e();
        }
    }

    private boolean h() {
        if (this.e == -1) {
            if (com.apm.insight.runtime.a.b() && com.apm.insight.runtime.a.g()) {
                this.e = 1;
            } else {
                this.e = 0;
            }
        }
        return this.e == 1;
    }

    private void i() {
        File[] fileArrListFiles = o.i(this.f2895c).listFiles();
        if (fileArrListFiles == null) {
            return;
        }
        for (int i = 0; i < fileArrListFiles.length && i < 5; i++) {
            File file = fileArrListFiles[i];
            if (file.getName().endsWith(".atmp")) {
                com.apm.insight.a.a.a().a(file.getAbsolutePath());
            } else {
                try {
                    com.apm.insight.entity.e eVarE = com.apm.insight.l.i.e(file.getAbsolutePath());
                    if (eVarE != null) {
                        if (eVarE.b() != null) {
                            eVarE.b().put("upload_scene", "launch_scan");
                        }
                        if (e.a(e.d(), eVarE.e(), eVarE.d(), eVarE.f(), eVarE.g())) {
                            com.apm.insight.l.i.a(file);
                            com.apm.insight.l.i.a(eVarE.c());
                        }
                    } else {
                        com.apm.insight.l.i.a(file);
                    }
                } catch (Throwable th) {
                    com.apm.insight.b.a().a("NPTH_CATCH", th);
                }
            }
        }
    }

    public void a(boolean z) throws Throwable {
        if (!Npth.isStopUpload() && z) {
            d();
            i();
            com.apm.insight.c.a.a();
        }
    }

    public void b() {
        try {
            if (!this.h && com.apm.insight.l.a.b(com.apm.insight.h.g())) {
                com.apm.insight.runtime.p.b().a(this.j);
            }
        } catch (Throwable unused) {
        }
    }

    public boolean c() {
        return this.h;
    }
}
