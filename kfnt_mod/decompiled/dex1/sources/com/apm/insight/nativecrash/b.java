package com.apm.insight.nativecrash;

import android.content.Context;
import android.os.Environment;
import androidx.annotation.Nullable;
import com.apm.insight.CrashType;
import com.apm.insight.ICrashFilter;
import com.apm.insight.entity.Header;
import com.apm.insight.h;
import com.apm.insight.l.i;
import com.apm.insight.l.k;
import com.apm.insight.l.o;
import com.apm.insight.l.s;
import com.apm.insight.l.v;
import com.apm.insight.l.w;
import com.apm.insight.runtime.q;
import com.appsflyer.share.Constants;
import com.bytedance.pangle.provider.ContentProviderManager;
import com.bytedance.sdk.openadsdk.api.plugin.PluginConstants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class b {
    private static Boolean d;

    /* renamed from: a, reason: collision with root package name */
    private final Context f2980a;

    /* renamed from: b, reason: collision with root package name */
    private JSONObject f2981b = null;

    /* renamed from: c, reason: collision with root package name */
    private C0086b f2982c;

    private static class a {
        /* JADX INFO: Access modifiers changed from: private */
        public static String b(File file) throws Throwable {
            BufferedReader bufferedReader;
            BufferedReader bufferedReader2 = null;
            try {
                try {
                    bufferedReader = new BufferedReader(new FileReader(file));
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                }
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                String line = bufferedReader.readLine();
                if (line == null) {
                    k.a(bufferedReader);
                    return "";
                }
                if (!line.startsWith("[FATAL:jni_android.cc") || !line.contains("Please include Java exception stack in crash report ttwebview:")) {
                    k.a(bufferedReader);
                    return "";
                }
                StringBuilder sb = new StringBuilder();
                int iIndexOf = line.indexOf(" ttwebview:");
                sb.append("Caused by: ");
                sb.append("Please include Java exception stack in crash report");
                sb.append("\n");
                sb.append(line.substring(iIndexOf + " ttwebview:".length()));
                String str = "\n";
                while (true) {
                    sb.append(str);
                    String line2 = bufferedReader.readLine();
                    if (line2 == null) {
                        String string = sb.toString();
                        k.a(bufferedReader);
                        return string;
                    }
                    sb.append(line2);
                    str = "\n";
                }
            } catch (Throwable th3) {
                th = th3;
                k.a(bufferedReader);
                throw th;
            }
        }
    }

    /* renamed from: com.apm.insight.nativecrash.b$b, reason: collision with other inner class name */
    private class C0086b {

        /* renamed from: b, reason: collision with root package name */
        private final com.apm.insight.nativecrash.d f2984b;

        /* renamed from: c, reason: collision with root package name */
        private final com.apm.insight.nativecrash.a f2985c;
        private final File d;
        private final File e;

        public C0086b(File file) throws Throwable {
            this.d = file;
            this.e = o.a(h.g(), file.getName());
            this.f2985c = new com.apm.insight.nativecrash.a(file);
            this.f2984b = new com.apm.insight.nativecrash.d(file);
            if (this.f2985c.a() && this.f2984b.a() == null) {
                this.f2984b.a(file);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:9:0x0019  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public long a() {
            /*
                r3 = this;
                com.apm.insight.nativecrash.a r0 = r3.f2985c
                java.util.Map r0 = r0.c()
                if (r0 == 0) goto L19
                boolean r1 = r0.isEmpty()     // Catch: java.lang.Throwable -> L17
                if (r1 != 0) goto L19
                java.lang.String r1 = "start_time"
                java.lang.Object r0 = r0.get(r1)     // Catch: java.lang.Throwable -> L17
                java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.Throwable -> L17
                goto L1a
            L17:
                r0 = move-exception
                goto L21
            L19:
                r0 = 0
            L1a:
                if (r0 == 0) goto L2a
                long r0 = java.lang.Long.parseLong(r0)     // Catch: java.lang.Throwable -> L17
                return r0
            L21:
                com.apm.insight.c r1 = com.apm.insight.b.a()
                java.lang.String r2 = "NPTH_CATCH"
                r1.a(r2, r0)
            L2a:
                long r0 = java.lang.System.currentTimeMillis()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.apm.insight.nativecrash.b.C0086b.a():long");
        }

        public File b() {
            return this.d;
        }

        public boolean c() {
            return this.f2985c.a();
        }
    }

    public class c extends e {
        c() {
            super();
            this.f2989c = "Total FD Count:";
            this.f2988b = o.i(b.this.f2982c.b());
            this.d = ":";
            this.e = -2;
        }
    }

    public class d extends e {
        d() {
            super();
            this.f2989c = "VmSize:";
            this.f2988b = o.k(b.this.f2982c.b());
            this.d = "\\s+";
            this.e = -1;
        }
    }

    public class e {

        /* renamed from: b, reason: collision with root package name */
        protected File f2988b;

        /* renamed from: c, reason: collision with root package name */
        protected String f2989c;
        protected String d;
        protected int e;

        public e() {
        }

        public int a() throws Throwable {
            int i = -1;
            if (!this.f2988b.exists() || !this.f2988b.isFile()) {
                return -1;
            }
            BufferedReader bufferedReader = null;
            try {
                try {
                    BufferedReader bufferedReader2 = new BufferedReader(new FileReader(this.f2988b));
                    int i2 = -1;
                    while (true) {
                        try {
                            String line = bufferedReader2.readLine();
                            if (line != null) {
                                int iA = a(line);
                                if (iA != -1) {
                                    i2 = iA;
                                    break;
                                }
                                i2 = iA;
                            } else {
                                break;
                            }
                        } catch (Throwable th) {
                            th = th;
                            i = i2;
                            bufferedReader = bufferedReader2;
                            com.apm.insight.b.a().a("NPTH_CATCH", th);
                            if (bufferedReader != null) {
                                k.a(bufferedReader);
                            }
                            return i;
                        }
                    }
                    k.a(bufferedReader2);
                    return i2;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }

        public int a(String str) throws NumberFormatException {
            int i = this.e;
            if (!str.startsWith(this.f2989c)) {
                return i;
            }
            try {
                i = Integer.parseInt(str.split(this.d)[1].trim());
            } catch (NumberFormatException e) {
                com.apm.insight.b.a().a("NPTH_CATCH", e);
            }
            if (i < 0) {
                return -2;
            }
            return i;
        }
    }

    public class f extends e {
        f() {
            super();
            this.f2989c = "Total Threads Count:";
            this.f2988b = o.j(b.this.f2982c.b());
            this.d = ":";
            this.e = -2;
        }
    }

    public b(Context context) {
        this.f2980a = context;
    }

    private String a(String str) {
        StringBuilder sb = new StringBuilder();
        try {
            if (str.length() < 16) {
                sb.append(str);
            } else {
                sb.append(str.charAt(6));
                sb.append(str.charAt(7));
                sb.append(str.charAt(4));
                sb.append(str.charAt(5));
                sb.append(str.charAt(2));
                sb.append(str.charAt(3));
                sb.append(str.charAt(0));
                sb.append(str.charAt(1));
                sb.append(str.charAt(10));
                sb.append(str.charAt(11));
                sb.append(str.charAt(8));
                sb.append(str.charAt(9));
                sb.append(str.charAt(14));
                sb.append(str.charAt(15));
                sb.append(str.charAt(12));
                sb.append(str.charAt(13));
                if (str.length() >= 32) {
                    sb.append((CharSequence) str, 16, 32);
                    sb.append('0');
                }
            }
        } catch (Throwable th) {
            com.apm.insight.b.a().a("NPTH_CATCH", th);
        }
        return sb.toString().toUpperCase();
    }

    private void a(com.apm.insight.entity.a aVar) {
        aVar.a(n());
        aVar.a("is_native_crash", (Object) 1);
        aVar.a("repack_time", Long.valueOf(System.currentTimeMillis()));
        aVar.a("crash_uuid", (Object) this.f2982c.b().getName());
        aVar.a("jiffy", Long.valueOf(q.a.a()));
    }

    private void a(Map<String, String> map) {
        map.put("has_fds_file", o.i(this.f2982c.b()).exists() ? "true" : "false");
        File fileH = o.h(this.f2982c.b());
        map.put("has_logcat_file", (!fileH.exists() || fileH.length() <= 128) ? "false" : "true");
        map.put("has_maps_file", o.d(this.f2982c.b()).exists() ? "true" : "false");
        map.put("has_tombstone_file", o.b(this.f2982c.b()).exists() ? "true" : "false");
        map.put("has_meminfo_file", o.k(this.f2982c.b()).exists() ? "true" : "false");
        map.put("has_threads_file", o.j(this.f2982c.b()).exists() ? "true" : "false");
    }

    private void b(com.apm.insight.entity.a aVar) throws JSONException {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        com.apm.insight.entity.d.b(aVar.h());
        HashMap map = new HashMap();
        if (m()) {
            map.put("is_root", "true");
            str = "is_root";
            str2 = "true";
        } else {
            map.put("is_root", "false");
            str = "is_root";
            str2 = "false";
        }
        aVar.a(str, (Object) str2);
        a(map);
        int iO = o();
        if (iO > 0) {
            if (iO > 960) {
                str7 = "fd_leak";
                str8 = "true";
            } else {
                str7 = "fd_leak";
                str8 = "false";
            }
            map.put(str7, str8);
            aVar.a("fd_count", Integer.valueOf(iO));
        }
        int iP = p();
        if (iP > 0) {
            if (iP > 350) {
                str5 = "threads_leak";
                str6 = "true";
            } else {
                str5 = "threads_leak";
                str6 = "false";
            }
            map.put(str5, str6);
            aVar.a("threads_count", Integer.valueOf(iP));
        }
        int iQ = q();
        if (iQ > 0) {
            if (iQ > i()) {
                str3 = "memory_leak";
                str4 = "true";
            } else {
                str3 = "memory_leak";
                str4 = "false";
            }
            map.put(str3, str4);
            aVar.a("memory_size", Integer.valueOf(iQ));
        }
        map.put(PluginConstants.KEY_SDK_VERSION, "1.4.0");
        map.put("has_java_stack", String.valueOf(aVar.h().opt("java_data") != null));
        JSONArray jSONArrayA = com.apm.insight.nativecrash.c.a(o.l(this.f2982c.d), o.m(this.f2982c.d));
        map.put("leak_threads_count", String.valueOf(jSONArrayA.length()));
        if (jSONArrayA.length() > 0) {
            try {
                i.a(o.n(this.f2982c.d), jSONArrayA, false);
            } catch (Throwable unused) {
            }
        }
        aVar.b();
        aVar.c();
        aVar.c(map);
    }

    private void c(com.apm.insight.entity.a aVar) throws JSONException {
        Map<String, String> mapB = this.f2982c.f2984b.b();
        if (mapB.isEmpty()) {
            return;
        }
        JSONArray jSONArray = new JSONArray();
        for (String str : mapB.keySet()) {
            String strA = a(mapB.get(str));
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("lib_name", str);
                jSONObject.put("lib_uuid", strA);
                jSONArray.put(jSONObject);
            } catch (JSONException e2) {
                com.apm.insight.b.a().a("NPTH_CATCH", e2);
            }
        }
        aVar.a("crash_lib_uuid", (Object) jSONArray);
    }

    private void d(com.apm.insight.entity.a aVar) {
        File fileE = o.e(this.f2982c.b());
        if (!fileE.exists() && this.f2981b == null) {
            aVar.b(w.a(h.g()));
            aVar.a("has_callback", "false");
            return;
        }
        try {
            aVar.c(this.f2981b == null ? new JSONObject(i.c(fileE.getAbsolutePath())) : this.f2981b);
            aVar.a("has_callback", "true");
            if (aVar.h().opt("storage") == null) {
                aVar.b(w.a(h.g()));
            }
            s.a(aVar, aVar.i(), CrashType.NATIVE);
        } catch (Throwable th) {
            com.apm.insight.b.a().a("NPTH_CATCH", th);
        }
        long j = -1;
        long jOptLong = aVar.h().optLong("crash_time", -1L);
        long jOptLong2 = aVar.h().optLong("java_end", -1L);
        if (jOptLong2 != -1 && jOptLong != -1) {
            j = jOptLong2 - jOptLong;
        }
        try {
            aVar.b("total_cost", String.valueOf(j));
            aVar.a("total_cost", String.valueOf(j / 1000));
        } catch (Throwable unused) {
        }
    }

    private void e(com.apm.insight.entity.a aVar) throws Throwable {
        String strA;
        File fileG = o.g(this.f2982c.b());
        if (fileG.exists()) {
            try {
                strA = v.a(fileG.getAbsolutePath());
            } catch (Throwable th) {
                com.apm.insight.b.a().a("NPTH_CATCH", th);
            }
        } else {
            strA = "";
        }
        File fileO = o.o(this.f2982c.b());
        if (fileO.exists()) {
            String strB = a.b(fileO);
            if (strA.isEmpty()) {
                strA = strB;
            } else {
                strA = strA + "\n" + strB;
            }
        }
        try {
            if (strA.isEmpty()) {
                return;
            }
            aVar.a("java_data", (Object) strA);
        } catch (Throwable th2) {
            com.apm.insight.b.a().a("NPTH_CATCH", th2);
        }
    }

    private void f(com.apm.insight.entity.a aVar) {
        File fileA = o.a(this.f2982c.b());
        if (fileA.exists()) {
            try {
                aVar.a("native_log", (Object) i.b(i.a(fileA.getAbsolutePath(), "\n"), "\n"));
            } catch (Throwable th) {
                com.apm.insight.b.a().a("NPTH_CATCH", th);
            }
        }
    }

    private void g(com.apm.insight.entity.a aVar) throws Throwable {
        BufferedReader bufferedReader;
        File fileH = o.h(this.f2982c.b());
        if (!fileH.exists()) {
            NativeImpl.a(fileH.getAbsolutePath(), String.valueOf(h.i().getLogcatDumpCount()), String.valueOf(h.i().getLogcatLevel()));
        }
        JSONArray jSONArray = new JSONArray();
        String str = " " + this.f2982c.f2985c.c().get(Constants.URL_MEDIA_SOURCE) + " ";
        try {
            bufferedReader = new BufferedReader(new FileReader(fileH));
            try {
                if (fileH.length() > 512000) {
                    bufferedReader.skip(fileH.length() - 512000);
                }
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    if ((line.length() > 32 ? line.substring(0, 31) : line).contains(str)) {
                        jSONArray.put(line);
                    }
                }
            } catch (Throwable th) {
                th = th;
                k.a(bufferedReader);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedReader = null;
        }
        k.a(bufferedReader);
        aVar.a("logcat", (Object) jSONArray);
    }

    private void h(com.apm.insight.entity.a aVar) {
        Map<String, String> mapA = a();
        if (mapA == null || aVar == null) {
            return;
        }
        String str = mapA.get(ContentProviderManager.PLUGIN_PROCESS_NAME);
        if (str != null) {
            aVar.a(ContentProviderManager.PLUGIN_PROCESS_NAME, (Object) str);
        }
        String str2 = mapA.get("start_time");
        if (str2 != null) {
            try {
                aVar.a(Long.decode(str2).longValue());
            } catch (Throwable th) {
                com.apm.insight.b.a().a("NPTH_CATCH", th);
            }
        }
        String str3 = mapA.get(Constants.URL_MEDIA_SOURCE);
        if (str3 != null) {
            try {
                aVar.a(Constants.URL_MEDIA_SOURCE, Long.decode(str3));
            } catch (Throwable th2) {
                com.apm.insight.b.a().a("NPTH_CATCH", th2);
            }
        }
        String str4 = mapA.get("crash_thread_name");
        if (str4 != null) {
            aVar.a("crash_thread_name", (Object) str4);
        }
        String str5 = mapA.get("crash_time");
        if (str5 != null) {
            try {
                aVar.a("crash_time", Long.decode(str5));
            } catch (Throwable th3) {
                com.apm.insight.b.a().a("NPTH_CATCH", th3);
            }
        }
        aVar.a("data", (Object) b());
    }

    public static long i() {
        if (NativeImpl.e()) {
            return Long.MAX_VALUE;
        }
        return Header.a() ? 3891200L : 2867200L;
    }

    public static boolean m() {
        Boolean bool;
        if (d != null) {
            bool = d;
        } else {
            for (String str : new String[]{"/data/local/su", "/data/local/bin/su", "/data/local/xbin/su", "/system/xbin/su", "/system/bin/su", "/system/bin/.ext/su", "/system/bin/failsafe/su", "/system/sd/xbin/su", "/system/usr/we-need-root/su", "/sbin/su", "/su/bin/su"}) {
                try {
                } catch (Throwable th) {
                    com.apm.insight.b.a().a("NPTH_CATCH", th);
                }
                if (new File(str).exists()) {
                    Boolean bool2 = true;
                    d = bool2;
                    return bool2.booleanValue();
                }
                continue;
            }
            bool = false;
            d = bool;
        }
        return bool.booleanValue();
    }

    private Header n() throws JSONException {
        Header header = new Header(this.f2980a);
        JSONObject jSONObjectA = com.apm.insight.runtime.s.a().a(this.f2982c.a());
        if (jSONObjectA != null) {
            header.a(jSONObjectA);
            header.d();
            header.e();
        }
        Header.b(header);
        return header;
    }

    private int o() {
        return new c().a();
    }

    private int p() {
        return new f().a();
    }

    private int q() {
        return new d().a();
    }

    public Map<String, String> a() {
        if (this.f2982c != null) {
            return this.f2982c.f2985c.c();
        }
        return null;
    }

    public void a(File file) {
        this.f2982c = new C0086b(file);
    }

    @Nullable
    public String b() {
        if (this.f2982c == null) {
            return null;
        }
        String strC = this.f2982c.f2984b.c();
        return (strC == null || strC.isEmpty()) ? this.f2982c.f2985c.b() : strC;
    }

    public boolean c() {
        if (this.f2982c != null) {
            return this.f2982c.c();
        }
        return false;
    }

    public JSONObject d() {
        File fileF = o.f(this.f2982c.b());
        if (!fileF.exists()) {
            return null;
        }
        try {
            String strC = i.c(fileF.getAbsolutePath());
            if (strC != null && !strC.isEmpty()) {
                return new JSONObject(strC);
            }
        } catch (Throwable th) {
            com.apm.insight.b.a().a("NPTH_CATCH", th);
        }
        return null;
    }

    public void e() throws Throwable {
        try {
            File fileE = o.e(this.f2982c.b());
            File file = new File(fileE.getAbsolutePath() + ".tmp'");
            if (file.exists()) {
                file.delete();
            }
            int i = 0;
            if (fileE.exists()) {
                while (i < NativeCrashCollector.a()) {
                    File file2 = new File(fileE.getAbsolutePath() + '.' + i);
                    if (file2.exists()) {
                        file2.delete();
                    }
                    i++;
                }
                return;
            }
            com.apm.insight.entity.a aVar = new com.apm.insight.entity.a();
            for (int i2 = 0; i2 < NativeCrashCollector.a(); i2++) {
                File file3 = new File(fileE.getAbsolutePath() + '.' + i2);
                if (file3.exists()) {
                    try {
                        JSONObject jSONObject = new JSONObject(i.c(file3.getAbsolutePath()));
                        if (jSONObject.length() > 0) {
                            aVar.c(jSONObject);
                        }
                    } catch (JSONException e2) {
                        com.apm.insight.b.a().a("NPTH_CATCH", e2);
                    }
                }
            }
            JSONObject jSONObjectH = aVar.h();
            try {
                if (jSONObjectH.length() != 0 && jSONObjectH.opt("storage") == null) {
                    com.apm.insight.entity.a.a(jSONObjectH, w.a(h.g()));
                }
            } catch (Throwable unused) {
            }
            if (jSONObjectH.length() != 0) {
                this.f2981b = jSONObjectH;
                i.b(file, jSONObjectH, false);
                if (file.renameTo(fileE)) {
                    while (i < NativeCrashCollector.a()) {
                        File file4 = new File(fileE.getAbsolutePath() + '.' + i);
                        if (file4.exists()) {
                            file4.delete();
                        }
                        i++;
                    }
                }
            }
        } catch (IOException e3) {
            com.apm.insight.b.a().a("NPTH_CATCH", e3);
        }
    }

    public boolean f() {
        ICrashFilter iCrashFilterB = h.b().b();
        if (iCrashFilterB == null) {
            return true;
        }
        try {
            return iCrashFilterB.onNativeCrashFilter(b(), "");
        } catch (Throwable th) {
            com.apm.insight.b.a().a("NPTH_CATCH", th);
            return true;
        }
    }

    public boolean g() {
        return com.apm.insight.e.a.a().a(o.f(this.f2982c.b()).getAbsolutePath());
    }

    public void h() {
        com.apm.insight.e.a.a().a(com.apm.insight.e.a.a.a(o.f(this.f2982c.b()).getAbsolutePath()));
    }

    public JSONObject j() {
        try {
            com.apm.insight.entity.a aVar = new com.apm.insight.entity.a();
            a(aVar);
            h(aVar);
            c(aVar);
            d(aVar);
            e(aVar);
            g(aVar);
            f(aVar);
            b(aVar);
            File fileF = o.f(this.f2982c.b());
            JSONObject jSONObjectH = aVar.h();
            i.a(fileF, jSONObjectH, false);
            return jSONObjectH;
        } catch (Throwable th) {
            com.apm.insight.b.a().a("NPTH_CATCH", th);
            return null;
        }
    }

    public boolean k() {
        return i.a(this.f2982c.b());
    }

    public void l() {
        try {
            i.c(this.f2982c.b().getAbsolutePath(), Environment.getExternalStorageDirectory().getAbsolutePath() + Constants.URL_PATH_DELIMITER + "localDebug" + Constants.URL_PATH_DELIMITER + h.g().getPackageName() + Constants.URL_PATH_DELIMITER + this.f2982c.b().getName() + ".zip");
        } catch (Throwable th) {
            com.apm.insight.b.a().a("NPTH_CATCH", th);
        }
    }
}
