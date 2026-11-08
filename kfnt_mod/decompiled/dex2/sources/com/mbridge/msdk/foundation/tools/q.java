package com.mbridge.msdk.foundation.tools;

import android.text.TextUtils;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.playercommon.exoplayer2.source.ExtractorMediaSource;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* compiled from: SameFileTool.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class q extends e {
    private static long c(File file) throws Exception {
        long jAvailable = 0;
        FileInputStream fileInputStream = null;
        try {
            try {
                try {
                    if (file.exists()) {
                        FileInputStream fileInputStream2 = new FileInputStream(file);
                        try {
                            jAvailable = fileInputStream2.available();
                            fileInputStream = fileInputStream2;
                        } catch (Exception e) {
                            e = e;
                            fileInputStream = fileInputStream2;
                            e.printStackTrace();
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            return jAvailable;
                        } catch (Throwable th) {
                            th = th;
                            fileInputStream = fileInputStream2;
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } else {
                        file.createNewFile();
                        s.d("获取文件大小", "文件不存在!");
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e3) {
                e = e3;
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        return jAvailable;
    }

    private static long d(File file) throws Exception {
        long jC;
        File[] fileArrListFiles = file.listFiles();
        long j = 0;
        if (fileArrListFiles != null) {
            for (int i = 0; i < fileArrListFiles.length; i++) {
                if (fileArrListFiles[i].isDirectory()) {
                    jC = d(fileArrListFiles[i]);
                } else {
                    jC = c(fileArrListFiles[i]);
                }
                j += jC;
            }
        }
        return j;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(2:(3:40|7|(1:9)(0))|34) */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x004a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x004e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(java.io.File r4) throws java.lang.Throwable {
        /*
            r0 = 0
            if (r4 != 0) goto L4
            return r0
        L4:
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3a
            java.io.FileReader r2 = new java.io.FileReader     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3a
            r2.<init>(r4)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3a
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3a
            java.lang.StringBuffer r4 = new java.lang.StringBuffer     // Catch: java.io.IOException -> L34 java.lang.Throwable -> L4b
            r4.<init>()     // Catch: java.io.IOException -> L34 java.lang.Throwable -> L4b
        L13:
            java.lang.String r2 = r1.readLine()     // Catch: java.io.IOException -> L32 java.lang.Throwable -> L4b
            if (r2 == 0) goto L2e
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L32 java.lang.Throwable -> L4b
            r3.<init>()     // Catch: java.io.IOException -> L32 java.lang.Throwable -> L4b
            r3.append(r2)     // Catch: java.io.IOException -> L32 java.lang.Throwable -> L4b
            java.lang.String r2 = "\n"
            r3.append(r2)     // Catch: java.io.IOException -> L32 java.lang.Throwable -> L4b
            java.lang.String r2 = r3.toString()     // Catch: java.io.IOException -> L32 java.lang.Throwable -> L4b
            r4.append(r2)     // Catch: java.io.IOException -> L32 java.lang.Throwable -> L4b
            goto L13
        L2e:
            r1.close()     // Catch: java.io.IOException -> L43
            goto L43
        L32:
            r2 = move-exception
            goto L3d
        L34:
            r2 = move-exception
            r4 = r0
            goto L3d
        L37:
            r4 = move-exception
            r1 = r0
            goto L4c
        L3a:
            r2 = move-exception
            r4 = r0
            r1 = r4
        L3d:
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L4b
            if (r1 == 0) goto L43
            goto L2e
        L43:
            if (r4 == 0) goto L4a
            java.lang.String r4 = r4.toString()
            return r4
        L4a:
            return r0
        L4b:
            r4 = move-exception
        L4c:
            if (r1 == 0) goto L51
            r1.close()     // Catch: java.io.IOException -> L51
        L51:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.tools.q.a(java.io.File):java.lang.String");
    }

    public static boolean a(String str) {
        if (com.mbridge.msdk.foundation.same.c.e.a(str)) {
            return false;
        }
        File file = new File(str);
        return file.exists() && file.isFile();
    }

    public static File[] b(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                return file.listFiles();
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public static void a() {
        new Thread(new Runnable() { // from class: com.mbridge.msdk.foundation.tools.q.1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    if (com.mbridge.msdk.foundation.controller.a.f().j() != null) {
                        String strB = com.mbridge.msdk.foundation.same.b.e.b(com.mbridge.msdk.foundation.same.b.c.MBRIDGE_VC);
                        try {
                            File file = new File(strB);
                            if (file.exists() && file.isDirectory()) {
                                for (File file2 : q.b(strB)) {
                                    if (file2.exists() && file2.isFile()) {
                                        file2.delete();
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e2) {
                    if (MBridgeConstans.DEBUG) {
                        e2.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public static void c(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
            if (aVarB == null) {
                aVarB = com.mbridge.msdk.b.b.a().b();
            }
            long jCurrentTimeMillis = System.currentTimeMillis() - (aVarB.C() * 1000);
            File file = new File(str);
            if (file.exists() && file.isDirectory()) {
                long jCurrentTimeMillis2 = System.currentTimeMillis();
                File[] fileArrListFiles = file.listFiles();
                if (fileArrListFiles != null) {
                    for (File file2 : fileArrListFiles) {
                        if (file2.lastModified() + jCurrentTimeMillis < jCurrentTimeMillis2) {
                            b(file2);
                            try {
                                File file3 = new File(str + ".zip");
                                if (file3.exists() && file3.isFile()) {
                                    b(file3);
                                }
                            } catch (Exception unused) {
                            }
                        }
                    }
                }
            }
        } catch (Exception unused2) {
        }
    }

    public static void b() {
        new Thread(new Runnable() { // from class: com.mbridge.msdk.foundation.tools.q.2
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
                    if (aVarB == null) {
                        aVarB = com.mbridge.msdk.b.b.a().b();
                    }
                    q.a(com.mbridge.msdk.foundation.same.b.e.b(com.mbridge.msdk.foundation.same.b.c.MBRIDGE_RES_MANAGER_DIR), aVarB.c());
                    q.a(System.currentTimeMillis() - (aVarB.C() * 1000));
                } catch (Exception e) {
                    if (MBridgeConstans.DEBUG) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public static String b(File file) {
        try {
            if (file.isFile()) {
                file.delete();
                return "";
            }
            if (!file.isDirectory()) {
                return "";
            }
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles != null && fileArrListFiles.length != 0) {
                for (File file2 : fileArrListFiles) {
                    b(file2);
                }
                file.delete();
                return "";
            }
            file.delete();
            return "";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private static ArrayList<File> e(File file) {
        ArrayList<File> arrayList = new ArrayList<>();
        for (File file2 : file.listFiles(new FileFilter() { // from class: com.mbridge.msdk.foundation.tools.q.3
            @Override // java.io.FileFilter
            public final boolean accept(File file3) {
                return !file3.isHidden() || file3.isDirectory();
            }
        })) {
            if (!file2.isDirectory()) {
                arrayList.add(file2);
            } else {
                arrayList.addAll(e(file2));
            }
        }
        return arrayList;
    }

    private static void e(String str) {
        try {
            ArrayList<File> arrayListE = e(new File(str));
            Collections.sort(arrayListE, new Comparator<File>() { // from class: com.mbridge.msdk.foundation.tools.q.4
                @Override // java.util.Comparator
                public final boolean equals(Object obj) {
                    return true;
                }

                @Override // java.util.Comparator
                public final /* synthetic */ int compare(File file, File file2) {
                    long jLastModified = file.lastModified() - file2.lastModified();
                    if (jLastModified > 0) {
                        return 1;
                    }
                    return jLastModified == 0 ? 0 : -1;
                }
            });
            int size = (arrayListE.size() - 1) / 2;
            for (int i = 0; i < size; i++) {
                File file = arrayListE.get(i);
                if (file.exists() && file.isFile()) {
                    file.delete();
                }
            }
        } catch (Exception unused) {
            s.d("SameFileTool", "del memory failed");
        }
    }

    public static void a(long j) {
        try {
            Iterator<File> it = e(new File(com.mbridge.msdk.foundation.same.b.e.b(com.mbridge.msdk.foundation.same.b.c.MBRIDGE_RES_MANAGER_DIR))).iterator();
            while (it.hasNext()) {
                File next = it.next();
                if (next.lastModified() < j && next.exists() && next.isFile()) {
                    next.delete();
                }
            }
        } catch (Throwable th) {
            s.b("SameFileTool", th.getMessage(), th);
        }
    }

    public static boolean a(byte[] bArr, File file) throws Throwable {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                if (file.getParentFile() != null && !file.exists()) {
                    file.getParentFile().mkdirs();
                }
                fileOutputStream = new FileOutputStream(file);
            } catch (Exception e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            fileOutputStream.write(bArr);
            try {
                fileOutputStream.close();
                return true;
            } catch (IOException e2) {
                e2.printStackTrace();
                return true;
            }
        } catch (Exception e3) {
            e = e3;
            fileOutputStream2 = fileOutputStream;
            e.printStackTrace();
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            return false;
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static String d(String str) {
        return !TextUtils.isEmpty(str) ? SameMD5.getMD5(ab.a(str.trim())) : "";
    }

    public static String a(String str, String str2) throws Throwable {
        FileOutputStream fileOutputStream;
        InputStream inputStream;
        if (str == null || str2 == null) {
            return "unzip srcFile or destDir is null ";
        }
        if (!str2.endsWith("/")) {
            str2 = str2 + "/";
        }
        File file = new File(str);
        if (!file.exists()) {
            return "unzip file not exists";
        }
        InputStream inputStream2 = null;
        try {
            ZipFile zipFile = new ZipFile(file);
            Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
            fileOutputStream = null;
            while (enumerationEntries.hasMoreElements()) {
                try {
                    try {
                        ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                        if (zipEntryNextElement == null) {
                            if (inputStream2 != null) {
                                try {
                                    inputStream2.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            return "unzip zipEntry is null";
                        }
                        File file2 = new File(str2 + zipEntryNextElement.getName());
                        if (zipEntryNextElement.isDirectory()) {
                            file2.mkdirs();
                        } else {
                            if (!file2.getParentFile().exists()) {
                                file2.getParentFile().mkdirs();
                            }
                            FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
                            try {
                                inputStream = zipFile.getInputStream(zipEntryNextElement);
                            } catch (IOException e3) {
                                e = e3;
                            } catch (Throwable th) {
                                th = th;
                            }
                            try {
                                byte[] bArr = new byte[1024];
                                while (true) {
                                    int i = inputStream.read(bArr, 0, 1024);
                                    if (i == -1) {
                                        break;
                                    }
                                    fileOutputStream2.write(bArr, 0, i);
                                    fileOutputStream2.flush();
                                }
                                inputStream2 = inputStream;
                                fileOutputStream = fileOutputStream2;
                            } catch (IOException e4) {
                                e = e4;
                                inputStream2 = inputStream;
                                fileOutputStream = fileOutputStream2;
                                if (MBridgeConstans.DEBUG) {
                                    e.printStackTrace();
                                }
                                String message = e.getMessage();
                                if (inputStream2 != null) {
                                    try {
                                        inputStream2.close();
                                    } catch (IOException e5) {
                                        e5.printStackTrace();
                                    }
                                }
                                if (fileOutputStream != null) {
                                    try {
                                        fileOutputStream.close();
                                    } catch (IOException e6) {
                                        e6.printStackTrace();
                                    }
                                }
                                return message;
                            } catch (Throwable th2) {
                                th = th2;
                                inputStream2 = inputStream;
                                fileOutputStream = fileOutputStream2;
                                if (inputStream2 != null) {
                                    try {
                                        inputStream2.close();
                                    } catch (IOException e7) {
                                        e7.printStackTrace();
                                    }
                                }
                                if (fileOutputStream == null) {
                                    throw th;
                                }
                                try {
                                    fileOutputStream.close();
                                    throw th;
                                } catch (IOException e8) {
                                    e8.printStackTrace();
                                    throw th;
                                }
                            }
                        }
                    } catch (IOException e9) {
                        e = e9;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            }
            zipFile.close();
            if (inputStream2 != null) {
                try {
                    inputStream2.close();
                } catch (IOException e10) {
                    e10.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e11) {
                    e11.printStackTrace();
                }
            }
            return "";
        } catch (IOException e12) {
            e = e12;
            fileOutputStream = null;
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream = null;
        }
    }

    protected static boolean a(String str, int i, int i2, int i3) {
        try {
            Class.forName("android.os.FileUtils").getMethod("setPermissions", String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE).invoke(null, str, Integer.valueOf(i), -1, -1);
            return true;
        } catch (ClassNotFoundException e) {
            s.a("SameFileTool", "error when set permissions:", e);
            return false;
        } catch (IllegalAccessException e2) {
            s.a("SameFileTool", "error when set permissions:", e2);
            return false;
        } catch (IllegalArgumentException e3) {
            s.a("SameFileTool", "error when set permissions:", e3);
            return false;
        } catch (NoSuchMethodException e4) {
            s.a("SameFileTool", "error when set permissions:", e4);
            return false;
        } catch (InvocationTargetException e5) {
            s.a("SameFileTool", "error when set permissions:", e5);
            return false;
        }
    }

    static /* synthetic */ void a(String str, int i) {
        try {
            if (d(new File(str)) > i * ExtractorMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) {
                e(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable unused) {
            s.d("SameFileTool", "clean memory failed");
        }
    }
}
