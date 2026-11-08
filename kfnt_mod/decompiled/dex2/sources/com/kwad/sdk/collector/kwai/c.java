package com.kwad.sdk.collector.kwai;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.util.Log;
import androidx.annotation.WorkerThread;
import com.kwad.sdk.utils.ag;
import com.kwad.sdk.utils.au;
import com.kwad.sdk.utils.q;
import com.kwad.v8.Platform;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {
    public static com.kwad.sdk.collector.kwai.d a;
    private static Context b;

    static class a extends com.kwad.sdk.collector.kwai.a {
        public a() {
            b();
        }

        private void b() {
            this.b = new ArrayList();
            this.b.add(new com.kwad.sdk.collector.kwai.a(this.a) { // from class: com.kwad.sdk.collector.kwai.c.a.1
                @Override // com.kwad.sdk.collector.kwai.a
                protected boolean b(Context context) {
                    int i = (Build.PRODUCT.contains("sdk") || Build.PRODUCT.contains("Andy") || Build.PRODUCT.contains("ttVM_Hdragon") || Build.PRODUCT.contains("google_sdk") || Build.PRODUCT.contains("Droid4X") || Build.PRODUCT.contains("nox") || Build.PRODUCT.contains("sdk_x86") || Build.PRODUCT.contains("sdk_google") || Build.PRODUCT.contains("vbox86p") || Build.PRODUCT.contains("aries")) ? 1 : 0;
                    if (Build.MANUFACTURER.equals(Platform.UNKNOWN) || Build.MANUFACTURER.equals("Genymotion") || Build.MANUFACTURER.contains("Andy") || Build.MANUFACTURER.contains("MIT") || Build.MANUFACTURER.contains("nox") || Build.MANUFACTURER.contains("TiantianVM")) {
                        i++;
                    }
                    if (Build.BRAND.equals("generic") || Build.BRAND.equals("generic_x86") || Build.BRAND.equals("TTVM") || Build.BRAND.contains("Andy")) {
                        i++;
                    }
                    if (Build.DEVICE.contains("generic") || Build.DEVICE.contains("generic_x86") || Build.DEVICE.contains("Andy") || Build.DEVICE.contains("ttVM_Hdragon") || Build.DEVICE.contains("Droid4X") || Build.DEVICE.contains("nox") || Build.DEVICE.contains("generic_x86_64") || Build.DEVICE.contains("vbox86p") || Build.DEVICE.contains("aries")) {
                        i++;
                    }
                    if (Build.MODEL.equals("sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.equals("google_sdk") || Build.MODEL.contains("Droid4X") || Build.MODEL.contains("TiantianVM") || Build.MODEL.contains("Andy") || Build.MODEL.equals("Android SDK built for x86_64") || Build.MODEL.equals("Android SDK built for x86")) {
                        i++;
                    }
                    if (Build.HARDWARE.equals("goldfish") || Build.HARDWARE.equals("vbox86") || Build.HARDWARE.contains("nox") || Build.HARDWARE.contains("ttVM_x86")) {
                        i++;
                    }
                    if (Build.FINGERPRINT.contains("generic/sdk/generic") || Build.FINGERPRINT.contains("generic_x86/sdk_x86/generic_x86") || Build.FINGERPRINT.contains("Andy") || Build.FINGERPRINT.contains("ttVM_Hdragon") || Build.FINGERPRINT.contains("generic_x86_64") || Build.FINGERPRINT.contains("generic/google_sdk/generic") || Build.FINGERPRINT.contains("vbox86p") || Build.FINGERPRINT.contains("generic/vbox86p/vbox86p")) {
                        i++;
                    }
                    try {
                        if (new File(Environment.getExternalStorageDirectory().toString() + File.separatorChar + Platform.WINDOWS + File.separatorChar + "BstSharedFolder").exists()) {
                            i += 10;
                        }
                    } catch (Exception unused) {
                    }
                    return i > 3;
                }
            });
            this.b.add(new com.kwad.sdk.collector.kwai.a(this.a) { // from class: com.kwad.sdk.collector.kwai.c.a.2
                @Override // com.kwad.sdk.collector.kwai.a
                protected boolean b(Context context) {
                    return "1".equals(au.a("ro.kernel.qemu"));
                }
            });
        }
    }

    static class b extends com.kwad.sdk.collector.kwai.a {
    }

    /* renamed from: com.kwad.sdk.collector.kwai.c$c, reason: collision with other inner class name */
    static class C0029c extends com.kwad.sdk.collector.kwai.a {
    }

    static class d extends com.kwad.sdk.collector.kwai.a {
        public d() {
            b();
        }

        private void b() {
            this.b = new ArrayList();
            this.b.add(new com.kwad.sdk.collector.kwai.a(this.a) { // from class: com.kwad.sdk.collector.kwai.c.d.1
                @Override // com.kwad.sdk.collector.kwai.a
                protected boolean b(Context context) {
                    return new File("/system/app/Superuser.apk").exists();
                }
            });
            this.b.add(new com.kwad.sdk.collector.kwai.a(this.a) { // from class: com.kwad.sdk.collector.kwai.c.d.2
                @Override // com.kwad.sdk.collector.kwai.a
                protected boolean b(Context context) {
                    for (String str : new String[]{"/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"}) {
                        if (new File(str + "su").exists()) {
                            return true;
                        }
                    }
                    return false;
                }
            });
            this.b.add(new com.kwad.sdk.collector.kwai.a(this.a) { // from class: com.kwad.sdk.collector.kwai.c.d.3
                @Override // com.kwad.sdk.collector.kwai.a
                protected boolean b(Context context) {
                    return c.a(new String[]{"/system/xbin/which", "su"}) != null;
                }
            });
            this.b.add(new com.kwad.sdk.collector.kwai.a(this.a) { // from class: com.kwad.sdk.collector.kwai.c.d.4
                @Override // com.kwad.sdk.collector.kwai.a
                protected boolean b(Context context) {
                    Charset charsetForName = Charset.forName(C.UTF8_NAME);
                    File file = new File("/data/su_test");
                    try {
                        q.a(file, "ok", charsetForName, false);
                        return q.a(file, charsetForName).equals("ok");
                    } catch (Throwable unused) {
                        return false;
                    }
                }
            });
        }
    }

    static class e extends com.kwad.sdk.collector.kwai.a {
        public e() {
            b();
        }

        private void b() {
            this.b = new ArrayList();
            this.b.add(new com.kwad.sdk.collector.kwai.a(this.a) { // from class: com.kwad.sdk.collector.kwai.c.e.1
                @Override // com.kwad.sdk.collector.kwai.a
                protected boolean b(Context context) {
                    return ag.a(context, "de.robv.android.xposed.installer") || ag.a(context, "com.saurik.substrate");
                }
            });
            this.b.add(new com.kwad.sdk.collector.kwai.a(this.a) { // from class: com.kwad.sdk.collector.kwai.c.e.2
                @Override // com.kwad.sdk.collector.kwai.a
                protected boolean b(Context context) throws Exception {
                    try {
                        throw new Exception("empty");
                    } catch (Exception e) {
                        boolean z = false;
                        int i = 0;
                        for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                            String className = stackTraceElement.getClassName();
                            String methodName = stackTraceElement.getMethodName();
                            if (className.equals("com.android.internal.os.ZygoteInit") && (i = i + 1) == 2) {
                                z = true;
                            }
                            if (className.equals("com.saurik.substrate.MS$2") && methodName.equals("invoked")) {
                                Log.wtf("HookDetection", "A method on the stack trace has been hooked using Substrate.");
                                z = true;
                            }
                            if (className.equals("de.robv.android.xposed.XposedBridge") && methodName.equals("main")) {
                                z = true;
                            }
                            if (className.equals("de.robv.android.xposed.XposedBridge") && methodName.equals("handleHookedMethod")) {
                                z = true;
                            }
                        }
                        return z;
                    }
                }
            });
            this.b.add(new com.kwad.sdk.collector.kwai.a(this.a) { // from class: com.kwad.sdk.collector.kwai.c.e.3
                @Override // com.kwad.sdk.collector.kwai.a
                protected boolean b(Context context) throws IOException {
                    boolean z = false;
                    try {
                        HashSet<String> hashSet = new HashSet();
                        BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/" + Process.myPid() + "/maps"));
                        while (true) {
                            String line = bufferedReader.readLine();
                            if (line == null) {
                                break;
                            }
                            if (line.endsWith(".so") || line.endsWith(".jar")) {
                                hashSet.add(line.substring(line.lastIndexOf(" ") + 1));
                            }
                        }
                        for (String str : hashSet) {
                            if (str.contains("com.saurik.substrate")) {
                                Log.wtf("HookDetection", "Substrate shared object found: " + str);
                                z = true;
                            }
                            if (str.contains("XposedBridge.jar")) {
                                Log.wtf("HookDetection", "Xposed JAR found: " + str);
                                z = true;
                            }
                        }
                        bufferedReader.close();
                    } catch (Exception unused) {
                    }
                    return z;
                }
            });
        }
    }

    @WorkerThread
    public static synchronized com.kwad.sdk.collector.kwai.d a() {
        if (!com.kwad.sdk.core.config.b.M()) {
            return null;
        }
        if (a != null) {
            return a;
        }
        com.kwad.sdk.collector.kwai.d dVar = new com.kwad.sdk.collector.kwai.d(b);
        boolean zA = new d().a(b);
        boolean zA2 = new e().a(b);
        boolean zA3 = new b().a(b);
        boolean zA4 = new a().a(b);
        boolean zA5 = new C0029c().a(b);
        dVar.a(zA);
        dVar.b(zA2);
        dVar.c(zA3);
        dVar.e(zA4);
        dVar.f(zA5);
        a = dVar;
        return dVar;
    }

    public static ArrayList<String> a(String[] strArr) throws IOException {
        ArrayList<String> arrayList;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(strArr).getInputStream()));
            try {
                try {
                    arrayList = new ArrayList<>();
                    while (true) {
                        try {
                            String line = bufferedReader.readLine();
                            if (line == null) {
                                break;
                            }
                            arrayList.add(line);
                        } catch (Exception unused) {
                        }
                    }
                } catch (Exception unused2) {
                    arrayList = null;
                }
                return arrayList;
            } finally {
                com.kwad.sdk.crash.utils.b.a((Reader) bufferedReader);
            }
        } catch (Exception unused3) {
            return null;
        }
    }

    public static synchronized void a(Context context) {
        if (context == null) {
            return;
        }
        b = context.getApplicationContext();
    }
}
