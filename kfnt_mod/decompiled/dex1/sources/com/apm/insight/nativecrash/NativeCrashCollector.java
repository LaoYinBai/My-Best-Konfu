package com.apm.insight.nativecrash;

import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.apm.insight.CrashType;
import com.apm.insight.ICrashCallback;
import com.apm.insight.Npth;
import com.apm.insight.b.g;
import com.apm.insight.b.k;
import com.apm.insight.h;
import com.apm.insight.l.i;
import com.apm.insight.l.q;
import com.apm.insight.l.v;
import com.apm.insight.runtime.a.c;
import com.apm.insight.runtime.a.f;
import com.apm.insight.runtime.o;
import com.baidu.mobads.sdk.internal.ad;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class NativeCrashCollector {
    public static int a() {
        return 6;
    }

    private static void a(String str, Thread thread) {
        Iterator<ICrashCallback> it = o.a().d().iterator();
        while (it.hasNext()) {
            try {
                it.next().onCrash(CrashType.NATIVE, "", thread);
            } catch (Throwable th) {
                com.apm.insight.b.a().a("NPTH_CATCH", th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NonNull
    public static String b(String str) {
        Thread thread;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (!"main".equalsIgnoreCase(str)) {
            ThreadGroup threadGroup = Looper.getMainLooper().getThread().getThreadGroup();
            int iActiveCount = threadGroup.activeCount();
            Thread[] threadArr = new Thread[iActiveCount + (iActiveCount / 2)];
            int iEnumerate = threadGroup.enumerate(threadArr);
            for (int i = 0; i < iEnumerate; i++) {
                String name = threadArr[i].getName();
                if (!TextUtils.isEmpty(name) && (name.equals(str) || name.startsWith(str) || name.endsWith(str))) {
                    thread = threadArr[i];
                }
            }
            try {
                for (Map.Entry<Thread, StackTraceElement[]> entry : Thread.getAllStackTraces().entrySet()) {
                    String name2 = entry.getKey().getName();
                    if (name2.equals(str) || name2.startsWith(str) || name2.endsWith(str)) {
                        return v.a(entry.getValue());
                    }
                }
                return "";
            } catch (Throwable th) {
                com.apm.insight.b.a().a("NPTH_CATCH", th);
                return "";
            }
        }
        thread = Looper.getMainLooper().getThread();
        return v.a(thread.getStackTrace());
    }

    @Keep
    public static void onNativeCrash(final String str) {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        q.a((Object) "[onNativeCrash] enter");
        try {
            try {
                com.apm.insight.k.b.a().b();
                final File fileE = com.apm.insight.l.o.e(new File(com.apm.insight.l.o.a(), h.f()));
                com.apm.insight.entity.a aVarA = f.a().a(CrashType.NATIVE, null, new c.a() { // from class: com.apm.insight.nativecrash.NativeCrashCollector.1
                    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
                    @Override // com.apm.insight.runtime.a.c.a
                    public com.apm.insight.entity.a a(int i, com.apm.insight.entity.a aVar) {
                        String str2;
                        String strValueOf;
                        switch (i) {
                            case 1:
                                if (str != null && str.length() != 0) {
                                    aVar.a("java_data", (Object) NativeCrashCollector.b(str));
                                }
                                str2 = "crash_after_crash";
                                strValueOf = Npth.hasCrashWhenNativeCrash() ? "true" : "false";
                                aVar.a(str2, strValueOf);
                                return aVar;
                            case 2:
                                JSONArray jSONArrayC = g.b().c();
                                long jUptimeMillis = SystemClock.uptimeMillis();
                                JSONObject jSONObjectA = g.b().a(jUptimeMillis).a();
                                JSONArray jSONArrayA = k.a(100, jUptimeMillis);
                                aVar.a("history_message", (Object) jSONArrayC);
                                aVar.a("current_message", jSONObjectA);
                                aVar.a("pending_messages", (Object) jSONArrayA);
                                aVar.a("disable_looper_monitor", String.valueOf(com.apm.insight.runtime.a.d()));
                                str2 = "npth_force_apm_crash";
                                strValueOf = String.valueOf(com.apm.insight.c.b.a());
                                aVar.a(str2, strValueOf);
                                return aVar;
                            case 3:
                                if (com.apm.insight.runtime.a.e()) {
                                    aVar.a("all_thread_stacks", v.b(str));
                                    str2 = "has_all_thread_stack";
                                    aVar.a(str2, strValueOf);
                                }
                                return aVar;
                            case 4:
                                com.apm.insight.l.a.a(h.g(), aVar.h());
                                return aVar;
                            default:
                                return aVar;
                        }
                    }

                    @Override // com.apm.insight.runtime.a.c.a
                    public com.apm.insight.entity.a a(int i, com.apm.insight.entity.a aVar, boolean z) throws Throwable {
                        try {
                            JSONObject jSONObjectH = aVar.h();
                            if (jSONObjectH.length() > 0) {
                                i.a(new File(fileE.getAbsolutePath() + '.' + i), jSONObjectH, false);
                            }
                        } catch (IOException e) {
                            com.apm.insight.b.a().a("NPTH_CATCH", e);
                        }
                        if (i == 0) {
                            com.apm.insight.a.a.a().b();
                            com.apm.insight.a.a.a().a(CrashType.NATIVE, jCurrentTimeMillis, h.f());
                        }
                        return aVar;
                    }

                    @Override // com.apm.insight.runtime.a.c.a
                    public void a(Throwable th) {
                    }
                }, true);
                JSONObject jSONObjectH = aVarA.h();
                if (jSONObjectH != null && jSONObjectH.length() != 0) {
                    long jCurrentTimeMillis2 = System.currentTimeMillis();
                    long j = jCurrentTimeMillis2 - jCurrentTimeMillis;
                    try {
                        jSONObjectH.put("java_end", jCurrentTimeMillis2);
                        aVarA.b("crash_cost", String.valueOf(j));
                        aVarA.a("crash_cost", String.valueOf(j / 1000));
                    } catch (Throwable unused) {
                    }
                    File file = new File(fileE.getAbsolutePath() + ad.k);
                    i.a(file, jSONObjectH, false);
                    file.renameTo(fileE);
                }
            } finally {
                SystemClock.uptimeMillis();
                a("", null);
            }
        } catch (Throwable th) {
            com.apm.insight.b.a().a("NPTH_CATCH", th);
        }
    }
}
