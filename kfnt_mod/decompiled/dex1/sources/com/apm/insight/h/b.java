package com.apm.insight.h;

import com.apm.insight.h;
import com.apm.insight.l.i;
import com.apm.insight.l.q;
import com.apm.insight.runtime.p;
import com.apm.insight.runtime.r;
import com.appsflyer.share.Constants;
import com.bytedance.sdk.account.platform.onekey.OnekeyLoginConfig;
import java.io.File;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static HashMap<String, String> f2875a;

    public static String a() {
        return h.g().getFilesDir() + "/apminsight/selflib/";
    }

    public static String a(String str) {
        return h.g().getFilesDir() + "/apminsight/selflib/lib" + str + ".so";
    }

    public static void b(final String str) {
        p.b().a(new Runnable() { // from class: com.apm.insight.h.b.1

            /* renamed from: a, reason: collision with root package name */
            boolean f2876a = false;

            @Override // java.lang.Runnable
            public void run() {
                String str2;
                b.d();
                if (b.f(str)) {
                    return;
                }
                r.a("updateSo", str);
                File file = new File(b.a(str));
                file.getParentFile().mkdirs();
                if (file.exists()) {
                    file.delete();
                }
                q.a("doUnpackLibrary: " + str);
                String strA = null;
                try {
                    strA = c.a(h.g(), str, file);
                } catch (Throwable th) {
                    r.a("updateSoError", str);
                    com.apm.insight.b.a().a("NPTH_CATCH", th);
                }
                if (strA == null) {
                    b.f2875a.put(file.getName(), "1.4.0");
                    try {
                        i.a(new File(b.e(str)), "1.4.0", false);
                    } catch (Throwable unused) {
                    }
                    str2 = "updateSoSuccess";
                } else {
                    if (!this.f2876a) {
                        this.f2876a = true;
                        r.a("updateSoPostRetry", str);
                        p.b().a(this, OnekeyLoginConfig.OVER_TIME);
                        return;
                    }
                    str2 = "updateSoFailed";
                }
                r.a(str2, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d() {
        if (f2875a != null) {
            return;
        }
        f2875a = new HashMap<>();
        File file = new File(h.g().getFilesDir(), "/apminsight/selflib/");
        String[] list = file.list();
        if (list == null) {
            return;
        }
        for (String str : list) {
            if (str.endsWith(".ver")) {
                try {
                    f2875a.put(str.substring(0, str.length() - ".ver".length()), i.c(file.getAbsolutePath() + Constants.URL_PATH_DELIMITER + str));
                } catch (Throwable th) {
                    com.apm.insight.b.a().a("NPTH_CATCH", th);
                }
            } else if (!str.endsWith(".so")) {
                i.a(new File(file, str));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String e(String str) {
        return h.g().getFilesDir() + "/apminsight/selflib/" + str + ".ver";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean f(String str) {
        return "1.4.0".equals(f2875a.get(str)) && new File(a(str)).exists();
    }
}
