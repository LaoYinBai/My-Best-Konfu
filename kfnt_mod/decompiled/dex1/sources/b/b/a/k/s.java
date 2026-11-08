package b.b.a.k;

import android.os.Build;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class s {

    /* renamed from: a, reason: collision with root package name */
    public static final CharSequence f1144a = "amigo";

    /* renamed from: b, reason: collision with root package name */
    public static final CharSequence f1145b = "funtouch";

    /* JADX WARN: Multi-variable type inference failed */
    public static String a() throws IOException {
        String str;
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop ro.build.version.emui").getInputStream()), 1024);
            try {
                String line = bufferedReader2.readLine();
                try {
                    bufferedReader2.close();
                    try {
                        bufferedReader2.close();
                    } catch (IOException unused) {
                    }
                    return line;
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = line;
                    str = bufferedReader;
                    bufferedReader = bufferedReader2;
                    try {
                        v.a(th);
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException unused2) {
                            }
                        }
                        return str;
                    } catch (Throwable th2) {
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException unused3) {
                            }
                        }
                        throw th2;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            th = th4;
            str = 0;
        }
    }

    public static boolean b() {
        return (!TextUtils.isEmpty(Build.DISPLAY) && Build.DISPLAY.contains("Flyme")) || com.bytedance.b.a.ag.equals(Build.USER);
    }

    public static boolean c() {
        return (!TextUtils.isEmpty(Build.BRAND) && Build.BRAND.toLowerCase().startsWith("honor")) || (!TextUtils.isEmpty(Build.MANUFACTURER) && Build.MANUFACTURER.toLowerCase().startsWith("honor"));
    }

    public static boolean d() {
        try {
            return Class.forName("miui.os.Build").getName().length() > 0;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean e() {
        String str = Build.MANUFACTURER;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.toLowerCase().contains("oppo");
    }

    public static String f() {
        String str = Build.MANUFACTURER;
        return str == null ? "" : str.trim();
    }

    public static String a(String str) throws IOException {
        BufferedReader bufferedReader;
        String str2 = "";
        try {
            Process processExec = Runtime.getRuntime().exec("getprop " + str);
            bufferedReader = new BufferedReader(new InputStreamReader(processExec.getInputStream()), 1024);
            try {
                String line = bufferedReader.readLine();
                try {
                    processExec.destroy();
                    try {
                        bufferedReader.close();
                    } catch (IOException unused) {
                    }
                    return line;
                } catch (Throwable unused2) {
                    str2 = line;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException unused3) {
                        }
                    }
                    return str2;
                }
            } catch (Throwable unused4) {
            }
        } catch (Throwable unused5) {
            bufferedReader = null;
        }
    }
}
