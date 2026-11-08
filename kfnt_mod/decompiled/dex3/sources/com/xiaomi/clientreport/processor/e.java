package com.xiaomi.clientreport.processor;

import android.text.TextUtils;
import com.xiaomi.clientreport.data.PerfClientReport;
import com.xiaomi.push.y;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class e {
    private static PerfClientReport a(PerfClientReport perfClientReport, String str) {
        long[] jArrM33a;
        if (perfClientReport == null || (jArrM33a = m33a(str)) == null) {
            return null;
        }
        perfClientReport.perfCounts = jArrM33a[0];
        perfClientReport.perfLatencies = jArrM33a[1];
        return perfClientReport;
    }

    private static PerfClientReport a(String str) {
        PerfClientReport perfClientReport = null;
        try {
            String[] strArrM34a = m34a(str);
            if (strArrM34a == null || strArrM34a.length < 4 || TextUtils.isEmpty(strArrM34a[0]) || TextUtils.isEmpty(strArrM34a[1]) || TextUtils.isEmpty(strArrM34a[2]) || TextUtils.isEmpty(strArrM34a[3])) {
                return null;
            }
            PerfClientReport blankInstance = PerfClientReport.getBlankInstance();
            try {
                blankInstance.production = Integer.parseInt(strArrM34a[0]);
                blankInstance.clientInterfaceId = strArrM34a[1];
                blankInstance.reportType = Integer.parseInt(strArrM34a[2]);
                blankInstance.code = Integer.parseInt(strArrM34a[3]);
                return blankInstance;
            } catch (Exception unused) {
                perfClientReport = blankInstance;
                com.xiaomi.channel.commonutils.logger.b.c("parse per key error");
                return perfClientReport;
            }
        } catch (Exception unused2) {
        }
    }

    public static String a(PerfClientReport perfClientReport) {
        return perfClientReport.production + "#" + perfClientReport.clientInterfaceId + "#" + perfClientReport.reportType + "#" + perfClientReport.code;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10, types: [int] */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r5v8, types: [java.lang.String[]] */
    /* JADX WARN: Type inference failed for: r5v9, types: [java.lang.Object] */
    /* renamed from: a, reason: collision with other method in class */
    private static HashMap<String, String> m32a(String str) throws Throwable {
        BufferedReader bufferedReader;
        HashMap map = new HashMap();
        if (TextUtils.isEmpty(str) || !new File(str).exists()) {
            return map;
        }
        BufferedReader bufferedReader2 = null;
        ?? length = 0;
        BufferedReader bufferedReader3 = null;
        try {
            try {
                bufferedReader = new BufferedReader(new FileReader(str));
                while (true) {
                    try {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        ?? Split = line.split("%%%");
                        length = Split.length;
                        if (length >= 2) {
                            length = 0;
                            length = 0;
                            if (!TextUtils.isEmpty(Split[0]) && !TextUtils.isEmpty(Split[1])) {
                                length = Split[0];
                                map.put(length, Split[1]);
                            }
                        }
                    } catch (Exception e) {
                        e = e;
                        bufferedReader3 = bufferedReader;
                        com.xiaomi.channel.commonutils.logger.b.a(e);
                        y.a(bufferedReader3);
                        bufferedReader2 = bufferedReader3;
                        return map;
                    } catch (Throwable th) {
                        th = th;
                        y.a(bufferedReader);
                        throw th;
                    }
                }
                y.a(bufferedReader);
                bufferedReader2 = length;
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = bufferedReader2;
            }
        } catch (Exception e2) {
            e = e2;
        }
        return map;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:113:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00d5 A[PHI: r1
  0x00d5: PHI (r1v7 java.io.File) = (r1v6 java.io.File), (r1v8 java.io.File) binds: [B:65:0x00d3, B:32:0x0094] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00f4  */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6, types: [java.io.BufferedReader, java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r4v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.List<java.lang.String> a(android.content.Context r7, java.lang.String r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 249
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.clientreport.processor.e.a(android.content.Context, java.lang.String):java.util.List");
    }

    private static void a(String str, HashMap<String, String> map) throws Throwable {
        BufferedWriter bufferedWriter;
        Throwable th;
        Exception e;
        if (TextUtils.isEmpty(str) || map == null || map.size() == 0) {
            return;
        }
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            try {
                try {
                    for (String str2 : map.keySet()) {
                        bufferedWriter.write(str2 + "%%%" + map.get(str2));
                        bufferedWriter.newLine();
                    }
                } catch (Exception e2) {
                    e = e2;
                    com.xiaomi.channel.commonutils.logger.b.a(e);
                    y.a(bufferedWriter);
                }
            } catch (Throwable th2) {
                th = th2;
                y.a(bufferedWriter);
                throw th;
            }
        } catch (Exception e3) {
            bufferedWriter = null;
            e = e3;
        } catch (Throwable th3) {
            bufferedWriter = null;
            th = th3;
            y.a(bufferedWriter);
            throw th;
        }
        y.a(bufferedWriter);
    }

    public static void a(String str, com.xiaomi.clientreport.data.a[] aVarArr) throws Throwable {
        RandomAccessFile randomAccessFile;
        FileLock fileLockLock;
        if (aVarArr == null || aVarArr.length <= 0 || TextUtils.isEmpty(str)) {
            return;
        }
        FileLock fileLock = null;
        try {
            File file = new File(str + ".lock");
            y.m650a(file);
            randomAccessFile = new RandomAccessFile(file, "rw");
            try {
                try {
                    fileLockLock = randomAccessFile.getChannel().lock();
                } catch (Throwable unused) {
                }
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable unused2) {
            randomAccessFile = null;
        }
        try {
            HashMap<String, String> mapM32a = m32a(str);
            for (com.xiaomi.clientreport.data.a aVar : aVarArr) {
                if (aVar != null) {
                    String strA = a((PerfClientReport) aVar);
                    long j = ((PerfClientReport) aVar).perfCounts;
                    long j2 = ((PerfClientReport) aVar).perfLatencies;
                    if (!TextUtils.isEmpty(strA) && j > 0 && j2 >= 0) {
                        a(mapM32a, strA, j, j2);
                    }
                }
            }
            a(str, mapM32a);
            if (fileLockLock != null && fileLockLock.isValid()) {
                try {
                    fileLockLock.release();
                } catch (IOException e) {
                    e = e;
                    com.xiaomi.channel.commonutils.logger.b.a(e);
                    y.a(randomAccessFile);
                }
            }
        } catch (Throwable th2) {
            th = th2;
            fileLock = fileLockLock;
            if (fileLock != null && fileLock.isValid()) {
                try {
                    fileLock.release();
                } catch (IOException e2) {
                    com.xiaomi.channel.commonutils.logger.b.a(e2);
                }
            }
            y.a(randomAccessFile);
            throw th;
        }
        y.a(randomAccessFile);
    }

    private static void a(HashMap<String, String> map, String str, long j, long j2) {
        long[] jArrM33a;
        StringBuilder sb;
        String str2 = map.get(str);
        if (!TextUtils.isEmpty(str2) && (jArrM33a = m33a(str2)) != null && jArrM33a[0] > 0 && jArrM33a[1] >= 0) {
            j += jArrM33a[0];
            j2 += jArrM33a[1];
            sb = new StringBuilder();
        } else {
            sb = new StringBuilder();
        }
        sb.append(j);
        sb.append("#");
        sb.append(j2);
        map.put(str, sb.toString());
    }

    /* renamed from: a, reason: collision with other method in class */
    protected static long[] m33a(String str) {
        long[] jArr = new long[2];
        try {
            String[] strArrSplit = str.split("#");
            if (strArrSplit.length >= 2) {
                jArr[0] = Long.parseLong(strArrSplit[0].trim());
                jArr[1] = Long.parseLong(strArrSplit[1].trim());
            }
            return jArr;
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
            return null;
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    private static String[] m34a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str.split("#");
    }
}
