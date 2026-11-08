package com.kwad.tachikoma.config;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.core.diskcache.a.c;
import com.kwad.sdk.core.network.j;
import com.kwad.sdk.core.report.CommercialAction;
import com.kwad.sdk.utils.aq;
import com.kwad.sdk.utils.at;
import com.kwad.sdk.utils.i;
import com.kwad.tachikoma.config.TKConfigResultData;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    private static volatile boolean a = false;
    private static Context b = null;
    private static a c = null;
    private static volatile TKConfigResultData d = null;
    private static volatile boolean e = false;
    private static long f;
    private static final String[] g = {"ksad-video-top-bar.js", "ksad-video-bottom-card.js"};

    public interface a {
        void a(TKConfigResultData tKConfigResultData);

        void b(@NonNull TKConfigResultData tKConfigResultData);
    }

    /* renamed from: com.kwad.tachikoma.config.b$b, reason: collision with other inner class name */
    private static class RunnableC0085b implements Runnable {
        RunnableC0085b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (b.b == null) {
                return;
            }
            TKConfigResultData unused = b.d = b.c(b.b);
            String str = "1.0.0";
            if (b.d != null && b.d.data != null && !TextUtils.isEmpty(b.d.data.b)) {
                str = b.d.data.b;
            }
            if (b.c != null) {
                b.c.a(b.d);
            }
            b.b("2.1", str);
        }
    }

    public static TKConfigResultData a() {
        return d;
    }

    public static synchronized void a(Context context, a aVar) {
        b = context;
        if (!a) {
            a = true;
            c = aVar;
            i.a(new RunnableC0085b());
        }
    }

    private static void a(String str) {
        f = SystemClock.elapsedRealtime();
        com.kwad.sdk.core.report.i.a("ad_client_apm_log", new CommercialAction.TKDownloadMsg().setDownloadState(0).setTemplateId("ksad-old").setVersionCode(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(TKConfigResultData.a aVar) throws Throwable {
        String str;
        Context context = KsAdSDKImpl.get().getContext();
        com.kwad.sdk.core.d.a.a("TKConfigRequestManager", "downloadAndReplaceJsFile downloadAndReplaceJsFile");
        if (context == null) {
            return;
        }
        a(aVar.b);
        File file = new File(aq.e(context));
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(file, "js.zip");
        boolean zA = c.a(file2, aVar.c);
        com.kwad.sdk.core.d.a.a("TKConfigRequestManager", "downloadAndReplaceJsFile success");
        if (!zA) {
            str = "net";
        } else {
            if (TextUtils.isEmpty(aVar.d) || aVar.d.equalsIgnoreCase(com.kwad.sdk.utils.c.b(file2))) {
                try {
                    File file3 = new File(aq.e(context));
                    if (!file3.exists()) {
                        file3.mkdirs();
                    }
                    e = com.kwad.tachikoma.a.a.a(new FileInputStream(file2), file3.getAbsolutePath());
                    com.kwad.sdk.core.d.a.a("TKConfigRequestManager", "downloadAndReplaceJsFile sLoadFileFinished " + e);
                } catch (IOException e2) {
                    com.kwad.sdk.core.d.a.b(e2);
                    com.kwad.sdk.core.d.a.a("TKConfigRequestManager", "downloadAndReplaceJsFile IOException " + e);
                }
                if (e) {
                    b(aVar.b);
                    return;
                } else {
                    c("unzip", aVar.b);
                    return;
                }
            }
            str = "md5";
        }
        c(str, aVar.b);
    }

    private static void b(String str) {
        com.kwad.sdk.core.report.i.a("ad_client_apm_log", new CommercialAction.TKDownloadMsg().setDownloadTime(f > 0 ? SystemClock.elapsedRealtime() - f : 0L).setDownloadState(1).setTemplateId("ksad-old").setVersionCode(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(final String str, final String str2) {
        com.kwad.sdk.core.d.a.a("TKConfigRequestManager", "load() tkVersion " + str + " jsVersion " + str2);
        new com.kwad.sdk.core.network.i<com.kwad.tachikoma.config.a, TKConfigResultData>() { // from class: com.kwad.tachikoma.config.b.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.kwad.sdk.core.network.i
            @NonNull
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public TKConfigResultData b(String str3) {
                com.kwad.sdk.core.d.a.a("TKConfigRequestManager", "parseData = " + str3);
                TKConfigResultData tKConfigResultData = new TKConfigResultData();
                if (str3 != null) {
                    try {
                        tKConfigResultData.parseJson(new JSONObject(str3));
                    } catch (Throwable th) {
                        com.kwad.sdk.core.d.a.a(th);
                    }
                }
                return tKConfigResultData;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.kwad.sdk.core.network.a
            @NonNull
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public com.kwad.tachikoma.config.a b() {
                return new com.kwad.tachikoma.config.a(str, str2);
            }
        }.a(new j<com.kwad.tachikoma.config.a, TKConfigResultData>() { // from class: com.kwad.tachikoma.config.b.2
            @Override // com.kwad.sdk.core.network.j, com.kwad.sdk.core.network.h
            public void a(@NonNull com.kwad.tachikoma.config.a aVar) {
                com.kwad.sdk.core.d.a.a("TKConfigRequestManager", "onStartRequest request url = " + aVar.a());
                super.a((AnonymousClass2) aVar);
            }

            @Override // com.kwad.sdk.core.network.j, com.kwad.sdk.core.network.h
            public void a(@NonNull com.kwad.tachikoma.config.a aVar, int i, String str3) {
                super.a((AnonymousClass2) aVar, i, str3);
                com.kwad.sdk.core.d.a.a("TKConfigRequestManager", "onError " + str3);
            }

            @Override // com.kwad.sdk.core.network.j, com.kwad.sdk.core.network.h
            @SuppressLint({"NewApi"})
            public void a(@NonNull com.kwad.tachikoma.config.a aVar, @NonNull TKConfigResultData tKConfigResultData) throws Throwable {
                TKConfigResultData tKConfigResultData2 = new TKConfigResultData();
                String string = b.b.getSharedPreferences("ksadsdk_JS_CONFIG", 0).getString("js_config", "");
                try {
                    if (!at.a(string)) {
                        tKConfigResultData2.parseJson(new JSONObject(string));
                    }
                } catch (JSONException unused) {
                }
                if (tKConfigResultData.result == 1 && tKConfigResultData.data.e == -1) {
                    b.i();
                    b.b.getSharedPreferences("ksadsdk_JS_CONFIG", 0).edit().clear().apply();
                    if (b.c == null) {
                        return;
                    }
                } else if (tKConfigResultData.result == 1 && !at.a(tKConfigResultData.data.c)) {
                    TKConfigResultData unused2 = b.d = tKConfigResultData;
                    if (tKConfigResultData.data.d != null && tKConfigResultData.data.d.equals(tKConfigResultData2.data.d) && b.d(b.b)) {
                        boolean unused3 = b.e = true;
                    } else if (b.d.data.e == 1) {
                        b.b(tKConfigResultData.data);
                        b.b.getSharedPreferences("ksadsdk_JS_CONFIG", 0).edit().putString("js_config", tKConfigResultData.toJson().toString()).apply();
                    } else {
                        boolean unused4 = b.e = b.d(b.b);
                        if (!b.e) {
                            b.b(tKConfigResultData.data);
                        }
                    }
                    if (b.c == null) {
                        return;
                    }
                } else {
                    if (tKConfigResultData2.result != 1 || tKConfigResultData2.data.c == null) {
                        return;
                    }
                    boolean unused5 = b.e = b.d(b.b);
                    TKConfigResultData unused6 = b.d = tKConfigResultData2;
                    if (b.c == null) {
                        return;
                    }
                }
                b.c.b(tKConfigResultData);
            }
        });
    }

    public static boolean b() {
        return e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static TKConfigResultData c(Context context) throws Throwable {
        String string = context.getSharedPreferences("ksadsdk_JS_CONFIG", 0).getString("js_config", null);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        TKConfigResultData tKConfigResultData = new TKConfigResultData();
        try {
            tKConfigResultData.parseJson(new JSONObject(string));
            return tKConfigResultData;
        } catch (JSONException e2) {
            com.kwad.sdk.core.d.a.b(e2);
            return null;
        }
    }

    public static void c() {
        if (b != null) {
            b.getSharedPreferences("ksadsdk_JS_CONFIG", 0).edit().clear().apply();
            i();
            e = false;
        }
    }

    private static void c(String str, String str2) {
        com.kwad.sdk.core.report.i.a("ad_client_error_log", new CommercialAction.TKDownloadMsg().setDownloadState(2).setErrorReason(str).setTemplateId("ksad-old").setVersionCode(str2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean d(Context context) {
        File file = new File(aq.e(context));
        for (String str : g) {
            File file2 = new File(file.getAbsolutePath(), str);
            if (!file2.exists() || file2.length() <= 0) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void i() {
        e = false;
        Context context = KsAdSDKImpl.get().getContext();
        if (context != null) {
            File file = new File(aq.e(context));
            for (String str : g) {
                System.out.println("Jifenglei " + str + " delete " + new File(file.getAbsolutePath(), str).delete());
            }
        }
    }
}
