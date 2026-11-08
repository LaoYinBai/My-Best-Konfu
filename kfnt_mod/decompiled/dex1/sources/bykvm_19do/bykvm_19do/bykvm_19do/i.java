package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.bytedance.common.utility.DeviceUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class i {
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
                        l0.a(th);
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
        return (!TextUtils.isEmpty(Build.BRAND) && Build.BRAND.toLowerCase().startsWith(com.bytedance.b.a.ah)) || (!TextUtils.isEmpty(Build.MANUFACTURER) && Build.MANUFACTURER.toLowerCase().startsWith(com.bytedance.b.a.ah));
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

    public static boolean f() {
        String str = Build.BRAND;
        if (str == null) {
            return false;
        }
        return str.toLowerCase(Locale.ENGLISH).contains("meizu");
    }

    public static boolean g() {
        return "OnePlus".equalsIgnoreCase(Build.MANUFACTURER);
    }

    public static boolean h() {
        return DeviceUtils.ROM_SAMSUNG.equalsIgnoreCase(Build.BRAND) || DeviceUtils.ROM_SAMSUNG.equalsIgnoreCase(Build.MANUFACTURER);
    }

    public static boolean i() throws IOException {
        String str = Build.FINGERPRINT;
        if (!TextUtils.isEmpty(str)) {
            return str.contains("VIBEUI_V2");
        }
        String strB = b("ro.build.version.incremental");
        return !TextUtils.isEmpty(strB) && strB.contains("VIBEUI_V2");
    }

    public static boolean j() {
        return l().toUpperCase().contains("NUBIA");
    }

    public static boolean k() {
        return l().toUpperCase().contains("ASUS");
    }

    private static String l() {
        String str = Build.MANUFACTURER;
        return str == null ? "" : str.trim();
    }

    private static String b(String str) throws IOException {
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

    public static boolean a(String str) throws IOException {
        if (TextUtils.isEmpty(str)) {
            str = a();
        }
        return (!TextUtils.isEmpty(str) && (str.toLowerCase().contains("emotionui") || str.toLowerCase().contains("magicui"))) || b() || c();
    }

    public static boolean a(Context context) {
        return l().toUpperCase().contains("HUAWEI");
    }
}
