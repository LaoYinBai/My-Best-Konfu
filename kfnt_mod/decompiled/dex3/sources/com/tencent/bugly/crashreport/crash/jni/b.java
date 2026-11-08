package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import com.taobao.accs.utl.UtilityImpl;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import com.umeng.message.common.inter.ITagManager;
import com.umeng.message.proguard.l;
import com.xiaomi.mipush.sdk.Constants;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/* compiled from: BUGLY */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class b {
    private static String a;

    private static Map<String, Integer> c(String str) {
        if (str == null) {
            return null;
        }
        try {
            HashMap map = new HashMap();
            for (String str2 : str.split(Constants.ACCEPT_TIME_SEPARATOR_SP)) {
                String[] strArrSplit = str2.split(Constants.COLON_SEPARATOR);
                if (strArrSplit.length != 2) {
                    x.e("error format at %s", str2);
                    return null;
                }
                map.put(strArrSplit[0], Integer.valueOf(Integer.parseInt(strArrSplit[1])));
            }
            return map;
        } catch (Exception e) {
            x.e("error format intStateStr %s", str);
            e.printStackTrace();
            return null;
        }
    }

    protected static String a(String str) {
        if (str == null) {
            return "";
        }
        String[] strArrSplit = str.split("\n");
        if (strArrSplit == null || strArrSplit.length == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : strArrSplit) {
            if (!str2.contains("java.lang.Thread.getStackTrace(")) {
                sb.append(str2);
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    private static CrashDetailBean a(Context context, Map<String, String> map, NativeExceptionHandler nativeExceptionHandler) {
        String str;
        String str2;
        HashMap map2;
        if (map == null) {
            return null;
        }
        if (com.tencent.bugly.crashreport.common.info.a.a(context) == null) {
            x.e("abnormal com info not created", new Object[0]);
            return null;
        }
        String str3 = map.get("intStateStr");
        if (str3 == null || str3.trim().length() <= 0) {
            x.e("no intStateStr", new Object[0]);
            return null;
        }
        Map<String, Integer> mapC = c(str3);
        if (mapC == null) {
            x.e("parse intSateMap fail", Integer.valueOf(map.size()));
            return null;
        }
        try {
            mapC.get("sino").intValue();
            mapC.get("sud").intValue();
            String str4 = map.get("soVersion");
            if (str4 == null) {
                x.e("error format at version", new Object[0]);
                return null;
            }
            String str5 = map.get("errorAddr");
            if (str5 == null) {
                str5 = UtilityImpl.NET_TYPE_UNKNOWN;
            }
            String str6 = str5;
            String str7 = map.get("codeMsg");
            if (str7 == null) {
                str7 = UtilityImpl.NET_TYPE_UNKNOWN;
            }
            String str8 = map.get("tombPath");
            if (str8 == null) {
                str8 = UtilityImpl.NET_TYPE_UNKNOWN;
            }
            String str9 = str8;
            String str10 = map.get("signalName");
            if (str10 == null) {
                str10 = UtilityImpl.NET_TYPE_UNKNOWN;
            }
            map.get("errnoMsg");
            String str11 = map.get("stack");
            if (str11 == null) {
                str11 = UtilityImpl.NET_TYPE_UNKNOWN;
            }
            String str12 = map.get("jstack");
            if (str12 != null) {
                str11 = str11 + "java:\n" + str12;
            }
            Integer num = mapC.get("sico");
            if (num == null || num.intValue() <= 0) {
                str = str7;
                str2 = str10;
            } else {
                str2 = str10 + l.s + str7 + l.t;
                str = "KERNEL";
            }
            String str13 = map.get("nativeLog");
            byte[] bArrA = (str13 == null || str13.isEmpty()) ? null : z.a((File) null, str13, "BuglyNativeLog.txt");
            String str14 = map.get("sendingProcess");
            if (str14 == null) {
                str14 = UtilityImpl.NET_TYPE_UNKNOWN;
            }
            Integer num2 = mapC.get("spd");
            if (num2 != null) {
                str14 = str14 + l.s + num2 + l.t;
            }
            String str15 = str14;
            String str16 = map.get("threadName");
            if (str16 == null) {
                str16 = UtilityImpl.NET_TYPE_UNKNOWN;
            }
            Integer num3 = mapC.get("et");
            if (num3 != null) {
                str16 = str16 + l.s + num3 + l.t;
            }
            String str17 = str16;
            String str18 = map.get("processName");
            if (str18 == null) {
                str18 = UtilityImpl.NET_TYPE_UNKNOWN;
            }
            Integer num4 = mapC.get("ep");
            if (num4 != null) {
                str18 = str18 + l.s + num4 + l.t;
            }
            String str19 = map.get("key-value");
            if (str19 != null) {
                HashMap map3 = new HashMap();
                String[] strArrSplit = str19.split("\n");
                int length = strArrSplit.length;
                int i = 0;
                while (i < length) {
                    String[] strArrSplit2 = strArrSplit[i].split("=");
                    String[] strArr = strArrSplit;
                    if (strArrSplit2.length == 2) {
                        map3.put(strArrSplit2[0], strArrSplit2[1]);
                    }
                    i++;
                    strArrSplit = strArr;
                }
                map2 = map3;
            } else {
                map2 = null;
            }
            CrashDetailBean crashDetailBeanPackageCrashDatas = nativeExceptionHandler.packageCrashDatas(str18, str17, (mapC.get("ets").intValue() * 1000) + (mapC.get("etms").intValue() / 1000), str2, str6, a(str11), str, str15, str9, map.get("sysLogPath"), str4, bArrA, map2, false);
            if (crashDetailBeanPackageCrashDatas != null) {
                String str20 = map.get("userId");
                if (str20 != null) {
                    x.c("[Native record info] userId: %s", str20);
                    crashDetailBeanPackageCrashDatas.m = str20;
                }
                String str21 = map.get("sysLog");
                if (str21 != null) {
                    crashDetailBeanPackageCrashDatas.w = str21;
                }
                String str22 = map.get(com.taobao.accs.common.Constants.KEY_APP_VERSION);
                if (str22 != null) {
                    x.c("[Native record info] appVersion: %s", str22);
                    crashDetailBeanPackageCrashDatas.f = str22;
                }
                String str23 = map.get("isAppForeground");
                if (str23 != null) {
                    x.c("[Native record info] isAppForeground: %s", str23);
                    crashDetailBeanPackageCrashDatas.M = str23.equalsIgnoreCase(ITagManager.STATUS_TRUE);
                }
                String str24 = map.get("launchTime");
                if (str24 != null) {
                    x.c("[Native record info] launchTime: %s", str24);
                    try {
                        crashDetailBeanPackageCrashDatas.L = Long.parseLong(str24);
                    } catch (NumberFormatException e) {
                        if (!x.a(e)) {
                            e.printStackTrace();
                        }
                    }
                }
                crashDetailBeanPackageCrashDatas.y = null;
                crashDetailBeanPackageCrashDatas.k = true;
            }
            return crashDetailBeanPackageCrashDatas;
        } catch (Throwable th) {
            x.e("error format", new Object[0]);
            th.printStackTrace();
            return null;
        }
    }

    private static String a(BufferedInputStream bufferedInputStream) throws IOException {
        if (bufferedInputStream == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        while (true) {
            int i = bufferedInputStream.read();
            if (i == -1) {
                return null;
            }
            if (i == 0) {
                return sb.toString();
            }
            sb.append((char) i);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r7v6 */
    public static CrashDetailBean a(Context context, String str, NativeExceptionHandler nativeExceptionHandler) throws Throwable {
        BufferedInputStream bufferedInputStream;
        String str2;
        String strA;
        if (context == null || str == null || nativeExceptionHandler == null) {
            x.e("get eup record file args error", new Object[0]);
            return null;
        }
        File file = new File(str, "rqd_record.eup");
        if (file.exists()) {
            BufferedInputStream bufferedInputStreamCanRead = file.canRead();
            try {
                if (bufferedInputStreamCanRead != 0) {
                    try {
                        bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                        try {
                            String strA2 = a(bufferedInputStream);
                            if (strA2 != null && strA2.equals("NATIVE_RQD_REPORT")) {
                                HashMap map = new HashMap();
                                loop0: while (true) {
                                    str2 = null;
                                    while (true) {
                                        strA = a(bufferedInputStream);
                                        if (strA == null) {
                                            break loop0;
                                        }
                                        if (str2 == null) {
                                            str2 = strA;
                                        }
                                    }
                                    map.put(str2, strA);
                                }
                                if (str2 != null) {
                                    x.e("record not pair! drop! %s", str2);
                                    try {
                                        bufferedInputStream.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    return null;
                                }
                                CrashDetailBean crashDetailBeanA = a(context, map, nativeExceptionHandler);
                                try {
                                    bufferedInputStream.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                                return crashDetailBeanA;
                            }
                            x.e("record read fail! %s", strA2);
                            try {
                                bufferedInputStream.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                            return null;
                        } catch (IOException e4) {
                            e = e4;
                            e.printStackTrace();
                            if (bufferedInputStream != null) {
                                try {
                                    bufferedInputStream.close();
                                } catch (IOException e5) {
                                    e5.printStackTrace();
                                }
                            }
                            return null;
                        }
                    } catch (IOException e6) {
                        e = e6;
                        bufferedInputStream = null;
                    } catch (Throwable th) {
                        th = th;
                        bufferedInputStreamCanRead = 0;
                        if (bufferedInputStreamCanRead != 0) {
                            try {
                                bufferedInputStreamCanRead.close();
                            } catch (IOException e7) {
                                e7.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return null;
    }

    private static String b(String str, String str2) throws IOException {
        BufferedReader bufferedReaderA = z.a(str, "reg_record.txt");
        if (bufferedReaderA == null) {
            return null;
        }
        try {
            try {
                StringBuilder sb = new StringBuilder();
                String line = bufferedReaderA.readLine();
                if (line != null && line.startsWith(str2)) {
                    int i = 0;
                    int i2 = 18;
                    int length = 0;
                    while (true) {
                        String line2 = bufferedReaderA.readLine();
                        if (line2 == null) {
                            break;
                        }
                        if (i % 4 == 0) {
                            if (i > 0) {
                                sb.append("\n");
                            }
                            sb.append("  ");
                        } else {
                            if (line2.length() > 16) {
                                i2 = 28;
                            }
                            sb.append("                ".substring(0, i2 - length));
                        }
                        length = line2.length();
                        sb.append(line2);
                        i++;
                    }
                    sb.append("\n");
                    String string = sb.toString();
                    if (bufferedReaderA != null) {
                        try {
                            bufferedReaderA.close();
                        } catch (Exception e) {
                            x.a(e);
                        }
                    }
                    return string;
                }
                if (bufferedReaderA != null) {
                    try {
                        bufferedReaderA.close();
                    } catch (Exception e2) {
                        x.a(e2);
                    }
                }
                return null;
            } catch (Throwable th) {
                x.a(th);
                if (bufferedReaderA != null) {
                    try {
                        bufferedReaderA.close();
                    } catch (Exception e3) {
                        x.a(e3);
                    }
                }
                return null;
            }
        } catch (Throwable th2) {
            if (bufferedReaderA != null) {
                try {
                    bufferedReaderA.close();
                } catch (Exception e4) {
                    x.a(e4);
                }
            }
            throw th2;
        }
    }

    private static String c(String str, String str2) throws IOException {
        BufferedReader bufferedReaderA = z.a(str, "map_record.txt");
        if (bufferedReaderA == null) {
            return null;
        }
        try {
            try {
                StringBuilder sb = new StringBuilder();
                String line = bufferedReaderA.readLine();
                if (line != null && line.startsWith(str2)) {
                    while (true) {
                        String line2 = bufferedReaderA.readLine();
                        if (line2 == null) {
                            break;
                        }
                        sb.append("  ");
                        sb.append(line2);
                        sb.append("\n");
                    }
                    String string = sb.toString();
                    if (bufferedReaderA != null) {
                        try {
                            bufferedReaderA.close();
                        } catch (Exception e) {
                            x.a(e);
                        }
                    }
                    return string;
                }
                if (bufferedReaderA != null) {
                    try {
                        bufferedReaderA.close();
                    } catch (Exception e2) {
                        x.a(e2);
                    }
                }
                return null;
            } catch (Throwable th) {
                x.a(th);
                if (bufferedReaderA != null) {
                    try {
                        bufferedReaderA.close();
                    } catch (Exception e3) {
                        x.a(e3);
                    }
                }
                return null;
            }
        } catch (Throwable th2) {
            if (bufferedReaderA != null) {
                try {
                    bufferedReaderA.close();
                } catch (Exception e4) {
                    x.a(e4);
                }
            }
            throw th2;
        }
    }

    public static String a(String str, String str2) throws IOException {
        if (str == null || str2 == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String strB = b(str, str2);
        if (strB != null && !strB.isEmpty()) {
            sb.append("Register infos:\n");
            sb.append(strB);
        }
        String strC = c(str, str2);
        if (strC != null && !strC.isEmpty()) {
            if (sb.length() > 0) {
                sb.append("\n");
            }
            sb.append("System SO infos:\n");
            sb.append(strC);
        }
        return sb.toString();
    }

    public static String b(String str) {
        if (str == null) {
            return null;
        }
        File file = new File(str, "backup_record.txt");
        if (file.exists()) {
            return file.getAbsolutePath();
        }
        return null;
    }

    public static void a(boolean z, String str) {
        File[] fileArrListFiles;
        if (str != null) {
            File file = new File(str, "rqd_record.eup");
            if (file.exists() && file.canWrite()) {
                file.delete();
                x.c("delete record file %s", file.getAbsoluteFile());
            }
            File file2 = new File(str, "reg_record.txt");
            if (file2.exists() && file2.canWrite()) {
                file2.delete();
                x.c("delete record file %s", file2.getAbsoluteFile());
            }
            File file3 = new File(str, "map_record.txt");
            if (file3.exists() && file3.canWrite()) {
                file3.delete();
                x.c("delete record file %s", file3.getAbsoluteFile());
            }
            File file4 = new File(str, "backup_record.txt");
            if (file4.exists() && file4.canWrite()) {
                file4.delete();
                x.c("delete record file %s", file4.getAbsoluteFile());
            }
            if (a != null) {
                File file5 = new File(a);
                if (file5.exists() && file5.canWrite()) {
                    file5.delete();
                    x.c("delete record file %s", file5.getAbsoluteFile());
                }
            }
            if (z) {
                File file6 = new File(str);
                if (file6.canRead() && file6.isDirectory() && (fileArrListFiles = file6.listFiles()) != null) {
                    for (File file7 : fileArrListFiles) {
                        if (file7.canRead() && file7.canWrite() && file7.length() == 0) {
                            file7.delete();
                            x.c("delete invalid record file %s", file7.getAbsoluteFile());
                        }
                    }
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r2v5 */
    public static String a(String str, int i, String str2) throws Throwable {
        BufferedReader bufferedReader;
        String string;
        if (str == null || i <= 0) {
            return null;
        }
        File file = new File(str);
        if (!file.exists() || !file.canRead()) {
            return null;
        }
        a = str;
        ?? r2 = "Read system log from native record file(length: %s bytes): %s";
        x.a("Read system log from native record file(length: %s bytes): %s", Long.valueOf(file.length()), file.getAbsolutePath());
        try {
            if (str2 == null) {
                string = z.a(new File(str));
            } else {
                try {
                    StringBuilder sb = new StringBuilder();
                    bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
                    while (true) {
                        try {
                            String line = bufferedReader.readLine();
                            if (line == null) {
                                break;
                            }
                            if (Pattern.compile(str2 + "[ ]*:").matcher(line).find()) {
                                sb.append(line);
                                sb.append("\n");
                            }
                        } catch (Throwable th) {
                            th = th;
                            x.a(th);
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (Exception e) {
                                    x.a(e);
                                }
                            }
                            return null;
                        }
                    }
                    string = sb.toString();
                    try {
                        bufferedReader.close();
                    } catch (Exception e2) {
                        x.a(e2);
                    }
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader = null;
                }
            }
            return (string == null || string.length() <= i) ? string : string.substring(string.length() - i);
        } catch (Throwable th3) {
            th = th3;
        }
    }
}
