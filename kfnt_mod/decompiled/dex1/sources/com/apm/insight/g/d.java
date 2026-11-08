package com.apm.insight.g;

import android.content.Context;
import android.os.Process;
import android.os.SystemClock;
import com.apm.insight.CrashType;
import com.apm.insight.Npth;
import com.apm.insight.b.g;
import com.apm.insight.b.k;
import com.apm.insight.h;
import com.apm.insight.l.i;
import com.apm.insight.l.o;
import com.apm.insight.l.r;
import com.apm.insight.l.v;
import com.apm.insight.nativecrash.NativeImpl;
import com.apm.insight.runtime.a.c;
import com.apm.insight.runtime.a.f;
import java.io.File;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d implements c {

    /* renamed from: a, reason: collision with root package name */
    private Context f2865a;

    public d(Context context) {
        this.f2865a = context;
    }

    public static int a() {
        return 6;
    }

    @Override // com.apm.insight.g.c
    public void a(final long j, final Thread thread, final Throwable th, final String str, File file, final String str2, final boolean z) {
        final File file2 = new File(o.a(this.f2865a), str);
        a.a().a(file2.getName());
        file2.mkdirs();
        i.f(file2);
        final boolean zC = v.c(th);
        com.apm.insight.entity.a aVarA = f.a().a(CrashType.JAVA, null, new c.a() { // from class: com.apm.insight.g.d.1

            /* renamed from: a, reason: collision with root package name */
            long f2866a = 0;

            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            @Override // com.apm.insight.runtime.a.c.a
            public com.apm.insight.entity.a a(int i, com.apm.insight.entity.a aVar) {
                String str3;
                boolean zA;
                this.f2866a = SystemClock.uptimeMillis();
                switch (i) {
                    case 0:
                        aVar.a("data", (Object) v.a(th));
                        aVar.a("isOOM", Boolean.valueOf(zC));
                        aVar.a("isJava", (Object) 1);
                        aVar.a("crash_time", Long.valueOf(j));
                        aVar.a("launch_mode", Integer.valueOf(com.apm.insight.runtime.a.b.b()));
                        aVar.a("launch_time", Long.valueOf(com.apm.insight.runtime.a.b.c()));
                        if (str2 != null) {
                            aVar.a("crash_md5", (Object) str2);
                            aVar.a("crash_md5", str2);
                            if (z) {
                                str3 = "has_ignore";
                                zA = z;
                                aVar.a(str3, String.valueOf(zA));
                            }
                        }
                        return aVar;
                    case 1:
                        aVar.a("crash_thread_name", (Object) (thread != null ? thread.getName() : ""));
                        aVar.a("tid", Integer.valueOf(Process.myTid()));
                        aVar.a("crash_after_crash", Npth.hasCrashWhenJavaCrash() ? "true" : "false");
                        aVar.a("crash_after_native", NativeImpl.d() ? "true" : "false");
                        a.a().a(thread, th, false, aVar);
                        return aVar;
                    case 2:
                        if (zC) {
                            com.apm.insight.l.a.a(d.this.f2865a, aVar.h());
                        }
                        JSONArray jSONArrayC = g.b().c();
                        long jUptimeMillis = SystemClock.uptimeMillis();
                        JSONObject jSONObjectA = g.b().a(jUptimeMillis).a();
                        JSONArray jSONArrayA = k.a(100, jUptimeMillis);
                        aVar.a("history_message", (Object) jSONArrayC);
                        aVar.a("current_message", jSONObjectA);
                        aVar.a("pending_messages", (Object) jSONArrayA);
                        aVar.a("disable_looper_monitor", String.valueOf(com.apm.insight.runtime.a.d()));
                        str3 = "npth_force_apm_crash";
                        zA = com.apm.insight.c.b.a();
                        aVar.a(str3, String.valueOf(zA));
                        return aVar;
                    case 3:
                        JSONObject jSONObjectB = v.b(Thread.currentThread().getName());
                        if (jSONObjectB != null) {
                            aVar.a("all_thread_stacks", jSONObjectB);
                        }
                        aVar.a("logcat", (Object) com.apm.insight.runtime.k.b(h.f()));
                        return aVar;
                    case 4:
                        if (!zC) {
                            com.apm.insight.l.a.a(d.this.f2865a, aVar.h());
                        }
                        return aVar;
                    case 5:
                        aVar.a("crash_uuid", (Object) str);
                        return aVar;
                    default:
                        return aVar;
                }
            }

            @Override // com.apm.insight.runtime.a.c.a
            public com.apm.insight.entity.a a(int i, com.apm.insight.entity.a aVar, boolean z2) throws Throwable {
                if (r.a(r.b(i))) {
                    return aVar;
                }
                try {
                    i.a(new File(file2, file2.getName() + "." + i), aVar.h(), false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return aVar;
            }

            @Override // com.apm.insight.runtime.a.c.a
            public void a(Throwable th2) {
            }
        }, true);
        long jCurrentTimeMillis = System.currentTimeMillis() - j;
        try {
            aVarA.a("crash_type", "normal");
            aVarA.b("crash_cost", String.valueOf(jCurrentTimeMillis));
            aVarA.a("crash_cost", String.valueOf(jCurrentTimeMillis / 1000));
        } catch (Throwable th2) {
            com.apm.insight.b.a().a("NPTH_CATCH", th2);
        }
        if (!r.a(4) && r.a(2048)) {
        }
    }

    @Override // com.apm.insight.g.c
    public boolean a(Throwable th) {
        return true;
    }
}
