package com.umeng.commonsdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.commonsdk.config.FieldManager;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.framework.UMModuleRegister;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.internal.d;
import com.umeng.commonsdk.internal.utils.c;
import com.umeng.commonsdk.internal.utils.j;
import com.umeng.commonsdk.internal.utils.l;
import com.umeng.commonsdk.stateless.UMSLEnvelopeBuild;
import com.umeng.commonsdk.stateless.f;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.commonsdk.utils.b;
import com.umeng.commonsdk.utils.onMessageSendListener;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class UMConfigureImpl {
    private static boolean a = false;
    private static boolean b = false;
    private static String c = "delayed_transmission_flag_new";
    private static CopyOnWriteArrayList<onMessageSendListener> d = new CopyOnWriteArrayList<>();
    private static int e = 0;
    private static boolean f = false;
    private static final int g = 1000;

    public static void init(final Context context) {
        if (context == null) {
            return;
        }
        g(context);
        try {
            if (e >= 1 && !e(context)) {
                com.umeng.commonsdk.framework.a.a(false);
                d(context);
                new com.umeng.commonsdk.utils.a(1000L, 100L) { // from class: com.umeng.commonsdk.UMConfigureImpl.1
                    @Override // com.umeng.commonsdk.utils.a
                    public void a(long j) {
                        if (UMConfigureImpl.e == 0) {
                            UMConfigureImpl.b(context);
                            boolean unused = UMConfigureImpl.f = true;
                            b();
                        }
                    }

                    @Override // com.umeng.commonsdk.utils.a
                    public void a() {
                        if (UMConfigureImpl.f) {
                            return;
                        }
                        UMConfigureImpl.b(context);
                    }
                }.c();
            } else {
                com.umeng.commonsdk.framework.a.a(true);
                h(context);
                f(context);
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void b(Context context) {
        try {
            com.umeng.commonsdk.framework.a.a(true);
            c(context);
            if (d != null && d.size() > 0) {
                Iterator<onMessageSendListener> it = d.iterator();
                while (it.hasNext()) {
                    it.next().onMessageSend();
                }
            }
        } catch (Exception unused) {
        }
    }

    public static synchronized void registerMessageSendListener(onMessageSendListener onmessagesendlistener) {
        try {
            if (d != null) {
                d.add(onmessagesendlistener);
            }
            if (com.umeng.commonsdk.framework.a.b() && d != null && d.size() > 0) {
                Iterator<onMessageSendListener> it = d.iterator();
                while (it.hasNext()) {
                    it.next().onMessageSend();
                }
            }
        } catch (Exception unused) {
        }
    }

    public static synchronized void removeMessageSendListener(onMessageSendListener onmessagesendlistener) {
        try {
            if (d != null) {
                d.remove(onmessagesendlistener);
            }
        } catch (Exception unused) {
        }
    }

    public static synchronized void registerInterruptFlag() {
        try {
            e++;
        } catch (Exception unused) {
        }
    }

    public static synchronized void removeInterruptFlag() {
        try {
            e--;
        } catch (Exception unused) {
        }
    }

    private static synchronized void c(final Context context) {
        try {
            if (context != null) {
                try {
                    new Thread(new Runnable() { // from class: com.umeng.commonsdk.UMConfigureImpl.2
                        @Override // java.lang.Runnable
                        public void run() throws Throwable {
                            try {
                                d.c(context);
                            } catch (Throwable th) {
                                ULog.e(UMModuleRegister.INNER, "e is " + th);
                            }
                            try {
                                d.e(context);
                            } catch (Throwable unused) {
                            }
                        }
                    }).start();
                } catch (Throwable th) {
                    UMCrashManager.reportCrash(context, th);
                }
            }
            f(context);
        } catch (Throwable th2) {
            throw th2;
        }
    }

    private static void d(Context context) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(c, 0);
            if (sharedPreferences == null || sharedPreferences == null) {
                return;
            }
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putBoolean(c, true);
            editorEdit.commit();
        } catch (Throwable unused) {
        }
    }

    private static boolean e(Context context) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(c, 0);
            if (sharedPreferences == null || sharedPreferences == null) {
                return false;
            }
            return sharedPreferences.getBoolean(c, false);
        } catch (Throwable unused) {
            return false;
        }
    }

    private static synchronized void f(final Context context) {
        try {
            if (FieldManager.allow(b.ag) && context != null && !b) {
                String currentProcessName = UMFrUtils.getCurrentProcessName(context);
                String packageName = context.getPackageName();
                if (!TextUtils.isEmpty(currentProcessName) && !TextUtils.isEmpty(packageName) && currentProcessName.equals(packageName)) {
                    new Thread(new Runnable() { // from class: com.umeng.commonsdk.UMConfigureImpl.3
                        @Override // java.lang.Runnable
                        public void run() throws JSONException {
                            try {
                                JSONArray jSONArrayB = com.umeng.commonsdk.proguard.b.b(context);
                                if (jSONArrayB == null || jSONArrayB.length() <= 0) {
                                    return;
                                }
                                f.a(context, context.getFilesDir() + "/" + com.umeng.commonsdk.stateless.a.e + "/" + Base64.encodeToString(com.umeng.commonsdk.internal.a.o.getBytes(), 0), 10);
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put("lbs", jSONArrayB);
                                JSONObject jSONObject2 = new JSONObject();
                                jSONObject2.put("tp", jSONObject);
                                UMSLEnvelopeBuild uMSLEnvelopeBuild = new UMSLEnvelopeBuild();
                                uMSLEnvelopeBuild.buildSLEnvelope(context, uMSLEnvelopeBuild.buildSLBaseHeader(context), jSONObject2, com.umeng.commonsdk.internal.a.o);
                            } catch (Exception e2) {
                                UMCrashManager.reportCrash(context, e2);
                            }
                        }
                    }).start();
                }
                b = true;
            }
        } catch (Throwable th) {
            UMCrashManager.reportCrash(context, th);
        }
    }

    private static synchronized void g(final Context context) {
        if (context != null) {
            try {
                if (!a) {
                    new Thread(new Runnable() { // from class: com.umeng.commonsdk.UMConfigureImpl.4
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                String currentProcessName = UMFrUtils.getCurrentProcessName(context);
                                String packageName = context.getPackageName();
                                if (TextUtils.isEmpty(currentProcessName) || TextUtils.isEmpty(packageName) || !currentProcessName.equals(packageName)) {
                                    return;
                                }
                                try {
                                    if (FieldManager.allow(b.ac)) {
                                        com.umeng.commonsdk.proguard.b.a(context);
                                    }
                                } catch (Throwable th) {
                                    ULog.e(UMModuleRegister.INNER, "e is " + th);
                                }
                                try {
                                    if (FieldManager.allow(b.F) && !c.a(context).a()) {
                                        c.a(context).b();
                                    }
                                } catch (Throwable th2) {
                                    ULog.e(UMModuleRegister.INNER, "e is " + th2);
                                }
                                try {
                                    l.b(context);
                                } catch (Throwable th3) {
                                    ULog.e(UMModuleRegister.INNER, "e is " + th3);
                                }
                                try {
                                    if (FieldManager.allow(b.T)) {
                                        com.umeng.commonsdk.internal.utils.a.d(context);
                                    }
                                } catch (Throwable th4) {
                                    ULog.e(UMModuleRegister.INNER, "e is " + th4);
                                }
                                try {
                                    if (FieldManager.allow(b.Q)) {
                                        j.b(context);
                                    }
                                } catch (Throwable th5) {
                                    ULog.e(UMModuleRegister.INNER, "e is " + th5);
                                }
                                try {
                                    if (com.umeng.commonsdk.internal.utils.b.a(context).a() || !FieldManager.allow(b.Z)) {
                                        return;
                                    }
                                    com.umeng.commonsdk.internal.utils.b.a(context).b();
                                } catch (Throwable unused) {
                                    ULog.e(UMModuleRegister.INNER, "get station is null ");
                                }
                            } catch (Throwable th6) {
                                UMCrashManager.reportCrash(context, th6);
                            }
                        }
                    }).start();
                    a = true;
                }
            } catch (Throwable th) {
                ULog.e(UMModuleRegister.INNER, "e is " + th.getMessage());
                UMCrashManager.reportCrash(context, th);
            }
        }
    }

    private static synchronized void h(final Context context) {
        if (context != null) {
            try {
                new Thread(new Runnable() { // from class: com.umeng.commonsdk.UMConfigureImpl.5
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            String currentProcessName = UMFrUtils.getCurrentProcessName(context);
                            String packageName = context.getPackageName();
                            if (TextUtils.isEmpty(currentProcessName) || TextUtils.isEmpty(packageName) || !currentProcessName.equals(packageName)) {
                                return;
                            }
                            try {
                                d.b(context);
                            } catch (Throwable th) {
                                ULog.e(UMModuleRegister.INNER, "e is " + th);
                            }
                            try {
                                d.d(context);
                            } catch (Throwable unused) {
                            }
                        } catch (Throwable th2) {
                            UMCrashManager.reportCrash(context, th2);
                        }
                    }
                }).start();
                a = true;
            } catch (Throwable th) {
                ULog.e(UMModuleRegister.INNER, "e is " + th.getMessage());
                UMCrashManager.reportCrash(context, th);
            }
        }
    }

    private static synchronized void i(final Context context) {
        if (context != null) {
            try {
                if (!a) {
                    new Thread(new Runnable() { // from class: com.umeng.commonsdk.UMConfigureImpl.6
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                String currentProcessName = UMFrUtils.getCurrentProcessName(context);
                                String packageName = context.getPackageName();
                                if (TextUtils.isEmpty(currentProcessName) || TextUtils.isEmpty(packageName) || !currentProcessName.equals(packageName)) {
                                    return;
                                }
                                try {
                                    if (FieldManager.allow(b.ac)) {
                                        com.umeng.commonsdk.proguard.b.a(context);
                                    }
                                } catch (Throwable th) {
                                    ULog.e(UMModuleRegister.INNER, "e is " + th);
                                }
                                try {
                                    if (FieldManager.allow(b.F) && !c.a(context).a()) {
                                        c.a(context).b();
                                    }
                                } catch (Throwable th2) {
                                    ULog.e(UMModuleRegister.INNER, "e is " + th2);
                                }
                                try {
                                    l.b(context);
                                } catch (Throwable th3) {
                                    ULog.e(UMModuleRegister.INNER, "e is " + th3);
                                }
                                try {
                                    if (FieldManager.allow(b.T)) {
                                        com.umeng.commonsdk.internal.utils.a.d(context);
                                    }
                                } catch (Throwable th4) {
                                    ULog.e(UMModuleRegister.INNER, "e is " + th4);
                                }
                                try {
                                    if (FieldManager.allow(b.Q)) {
                                        j.b(context);
                                    }
                                } catch (Throwable th5) {
                                    ULog.e(UMModuleRegister.INNER, "e is " + th5);
                                }
                                try {
                                    d.b(context);
                                } catch (Throwable th6) {
                                    ULog.e(UMModuleRegister.INNER, "e is " + th6);
                                }
                                try {
                                    d.d(context);
                                } catch (Throwable unused) {
                                }
                            } catch (Throwable th7) {
                                UMCrashManager.reportCrash(context, th7);
                            }
                        }
                    }).start();
                    try {
                        if (!com.umeng.commonsdk.internal.utils.b.a(context).a() && FieldManager.allow(b.Z)) {
                            com.umeng.commonsdk.internal.utils.b.a(context).b();
                        }
                    } catch (Throwable unused) {
                        ULog.e(UMModuleRegister.INNER, "get station is null ");
                    }
                    a = true;
                }
            } catch (Throwable th) {
                ULog.e(UMModuleRegister.INNER, "e is " + th.getMessage());
                UMCrashManager.reportCrash(context, th);
            }
        }
    }
}
