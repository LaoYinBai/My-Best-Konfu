package c.a.a.a.a;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.util.HashMap;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class bb {

    /* renamed from: a, reason: collision with root package name */
    private static final FileFilter f2078a = new a();

    static class a implements FileFilter {
        a() {
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            return Pattern.matches("cpu[0-9]", file.getName());
        }
    }

    public static String a() {
        JSONObject jSONObject = new JSONObject();
        try {
            HashMap<String, String> mapB = b();
            jSONObject.put("core", c());
            jSONObject.put("hw", a(mapB, "Hardware"));
            jSONObject.put("max", a("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"));
            jSONObject.put("min", a("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq"));
            jSONObject.put("ft", a(mapB, "Features"));
        } catch (Throwable unused) {
        }
        String string = jSONObject.toString();
        return string == null ? "{}" : string.trim();
    }

    /* JADX WARN: Can't wrap try/catch for region: R(7:0|2|(4:28|3|26|4)|(5:30|5|24|6|7)|22|13|(1:15)(2:16|17)) */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0024 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String a(java.lang.String r3) {
        /*
            r0 = 0
            java.io.FileReader r1 = new java.io.FileReader     // Catch: java.lang.Throwable -> L16
            r1.<init>(r3)     // Catch: java.lang.Throwable -> L16
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L14
            r3.<init>(r1)     // Catch: java.lang.Throwable -> L14
            java.lang.String r2 = r3.readLine()     // Catch: java.lang.Throwable -> L18
            r3.close()     // Catch: java.lang.Throwable -> L12
        L12:
            r0 = r2
            goto L1f
        L14:
            r3 = r0
            goto L18
        L16:
            r3 = r0
            r1 = r3
        L18:
            if (r3 == 0) goto L1d
            r3.close()     // Catch: java.lang.Throwable -> L1d
        L1d:
            if (r1 == 0) goto L22
        L1f:
            r1.close()     // Catch: java.lang.Throwable -> L22
        L22:
            if (r0 != 0) goto L27
            java.lang.String r3 = "0"
            goto L2b
        L27:
            java.lang.String r3 = r0.trim()
        L2b:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.a.a.bb.a(java.lang.String):java.lang.String");
    }

    private static String a(HashMap<String, String> map, String str) {
        String str2;
        try {
            str2 = map.get(str);
        } catch (Throwable unused) {
            str2 = null;
        }
        return str2 == null ? "" : str2.trim();
    }

    private static HashMap<String, String> b() {
        FileReader fileReader;
        HashMap<String, String> map = new HashMap<>();
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader("/proc/cpuinfo");
            try {
                BufferedReader bufferedReader2 = new BufferedReader(fileReader);
                while (true) {
                    try {
                        String line = bufferedReader2.readLine();
                        if (line == null) {
                            try {
                                break;
                            } catch (Throwable unused) {
                            }
                        } else {
                            String[] strArrSplit = line.split(":", 2);
                            if (strArrSplit.length >= 2) {
                                String strTrim = strArrSplit[0].trim();
                                String strTrim2 = strArrSplit[1].trim();
                                if (map.get(strTrim) == null) {
                                    map.put(strTrim, strTrim2);
                                }
                            }
                        }
                    } catch (Throwable unused2) {
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Throwable unused3) {
                            }
                        }
                        if (fileReader != null) {
                            fileReader.close();
                        }
                        return map;
                    }
                }
                bufferedReader2.close();
            } catch (Throwable unused4) {
            }
        } catch (Throwable unused5) {
            fileReader = null;
        }
        try {
            fileReader.close();
        } catch (Throwable unused6) {
        }
        return map;
    }

    private static int c() {
        try {
            return new File("/sys/devices/system/cpu/").listFiles(f2078a).length;
        } catch (Throwable unused) {
            return -1;
        }
    }
}
