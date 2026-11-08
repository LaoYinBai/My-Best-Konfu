package com.sigmob.sdk.common.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.sigmob.logger.SigmobLog;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class d {
    public static String a = "SigHtmlResource";
    public static String b = "SigHtmlPrivacy";
    private static String c;
    private static String d;

    public static File a(Context context) {
        return new File(d, "SigDownload");
    }

    public static File a(String str, String str2) {
        try {
            File file = new File(d, a);
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(file, str2);
            SigmobLog.d("SigHtmlResource: " + file2.getAbsolutePath());
            if (file2.exists()) {
                file2.delete();
            }
            file2.createNewFile();
            FileWriter fileWriter = new FileWriter(file2, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(str);
            bufferedWriter.newLine();
            bufferedWriter.close();
            fileWriter.close();
            return file2;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static String a() {
        return c;
    }

    public static String a(Context context, Uri uri) {
        Cursor cursorQuery;
        int columnIndex;
        String string = null;
        if (uri == null) {
            return null;
        }
        String scheme = uri.getScheme();
        if (scheme == null || "file".equals(scheme)) {
            return uri.getPath();
        }
        if (!com.umeng.commonsdk.framework.c.a.equals(scheme) || (cursorQuery = context.getContentResolver().query(uri, new String[]{"_data"}, null, null, null)) == null) {
            return null;
        }
        if (cursorQuery.moveToFirst() && (columnIndex = cursorQuery.getColumnIndex("_data")) > -1) {
            string = cursorQuery.getString(columnIndex);
        }
        cursorQuery.close();
        return string;
    }

    public static void a(Context context, String str) {
        File cacheDir = context.getCacheDir();
        File externalCacheDir = context.getExternalCacheDir();
        if (str != null) {
            File file = new File(cacheDir, str);
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(externalCacheDir, str);
            if (!file2.exists()) {
                file2.mkdirs();
            }
            externalCacheDir = file2;
            cacheDir = file;
        }
        c = cacheDir.getAbsolutePath();
        d = externalCacheDir.getAbsolutePath();
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x007d A[Catch: Throwable -> 0x0079, TRY_LEAVE, TryCatch #7 {Throwable -> 0x0079, blocks: (B:37:0x0075, B:41:0x007d), top: B:48:0x0075 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0075 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(java.lang.Object r3, java.lang.String r4) throws java.lang.Throwable {
        /*
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> L4e java.lang.Throwable -> L52
            r1.<init>(r4)     // Catch: java.lang.Throwable -> L4e java.lang.Throwable -> L52
            java.io.File r4 = r1.getParentFile()     // Catch: java.lang.Throwable -> L4e java.lang.Throwable -> L52
            boolean r4 = r4.exists()     // Catch: java.lang.Throwable -> L4e java.lang.Throwable -> L52
            if (r4 != 0) goto L17
            java.io.File r4 = r1.getParentFile()     // Catch: java.lang.Throwable -> L4e java.lang.Throwable -> L52
            r4.mkdirs()     // Catch: java.lang.Throwable -> L4e java.lang.Throwable -> L52
        L17:
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L4e java.lang.Throwable -> L52
            r4.<init>(r1)     // Catch: java.lang.Throwable -> L4e java.lang.Throwable -> L52
            java.io.ObjectOutputStream r2 = new java.io.ObjectOutputStream     // Catch: java.lang.Throwable -> L47 java.lang.Throwable -> L4a
            r2.<init>(r4)     // Catch: java.lang.Throwable -> L47 java.lang.Throwable -> L4a
            r2.writeObject(r3)     // Catch: java.lang.Throwable -> L43 java.lang.Throwable -> L45
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L43 java.lang.Throwable -> L45
            r3.<init>()     // Catch: java.lang.Throwable -> L43 java.lang.Throwable -> L45
            java.lang.String r0 = "writeCache :"
            r3.append(r0)     // Catch: java.lang.Throwable -> L43 java.lang.Throwable -> L45
            java.lang.String r0 = r1.getName()     // Catch: java.lang.Throwable -> L43 java.lang.Throwable -> L45
            r3.append(r0)     // Catch: java.lang.Throwable -> L43 java.lang.Throwable -> L45
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L43 java.lang.Throwable -> L45
            com.sigmob.logger.SigmobLog.d(r3)     // Catch: java.lang.Throwable -> L43 java.lang.Throwable -> L45
            r4.close()     // Catch: java.lang.Throwable -> L61
            r2.close()     // Catch: java.lang.Throwable -> L61
            goto L70
        L43:
            r3 = move-exception
            goto L73
        L45:
            r3 = move-exception
            goto L4c
        L47:
            r3 = move-exception
            r2 = r0
            goto L73
        L4a:
            r3 = move-exception
            r2 = r0
        L4c:
            r0 = r4
            goto L54
        L4e:
            r3 = move-exception
            r4 = r0
            r2 = r4
            goto L73
        L52:
            r3 = move-exception
            r2 = r0
        L54:
            java.lang.String r3 = r3.getMessage()     // Catch: java.lang.Throwable -> L71
            com.sigmob.logger.SigmobLog.e(r3)     // Catch: java.lang.Throwable -> L71
            if (r0 == 0) goto L63
            r0.close()     // Catch: java.lang.Throwable -> L61
            goto L63
        L61:
            r3 = move-exception
            goto L69
        L63:
            if (r2 == 0) goto L70
            r2.close()     // Catch: java.lang.Throwable -> L61
            goto L70
        L69:
            java.lang.String r3 = r3.getMessage()
            com.sigmob.logger.SigmobLog.e(r3)
        L70:
            return
        L71:
            r3 = move-exception
            r4 = r0
        L73:
            if (r4 == 0) goto L7b
            r4.close()     // Catch: java.lang.Throwable -> L79
            goto L7b
        L79:
            r4 = move-exception
            goto L81
        L7b:
            if (r2 == 0) goto L88
            r2.close()     // Catch: java.lang.Throwable -> L79
            goto L88
        L81:
            java.lang.String r4 = r4.getMessage()
            com.sigmob.logger.SigmobLog.e(r4)
        L88:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sigmob.sdk.common.utils.d.a(java.lang.Object, java.lang.String):void");
    }

    public static boolean a(String str) {
        try {
            if (!str.endsWith(File.separator)) {
                str = str + File.separator;
            }
            File file = new File(str);
            if (file.exists() && file.isDirectory()) {
                boolean zA = true;
                for (File file2 : file.listFiles()) {
                    if (!file2.isFile()) {
                        if (file2.isDirectory() && !(zA = a(file2.getAbsolutePath()))) {
                            break;
                        }
                    } else {
                        zA = b(file2.getAbsolutePath());
                        if (!zA) {
                            break;
                        }
                    }
                }
                if (!zA || !file.delete()) {
                    return false;
                }
                SigmobLog.d("删除目录" + str + "成功！");
                return true;
            }
            SigmobLog.d("删除目录失败：" + str + "不存在！");
            return false;
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
            return false;
        }
    }

    public static File[] a(File[] fileArr, int i) {
        if (fileArr == null || fileArr.length == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(Arrays.asList(fileArr));
        for (int i2 = 0; i2 < fileArr.length && arrayList.size() > i; i2++) {
            File file = fileArr[i2];
            if (file.exists()) {
                file.delete();
                arrayList.remove(file);
                SigmobLog.d("file delete " + file.getName());
            }
        }
        return (File[]) arrayList.toArray(new File[0]);
    }

    public static String b() {
        String str = c + File.separator + "splashAd";
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str;
    }

    public static boolean b(String str) {
        try {
            new SecurityManager().checkDelete(str);
            File file = new File(str);
            if (!file.exists() || !file.isFile()) {
                SigmobLog.d("删除单个文件失败：" + str + "不存在！");
                return false;
            }
            if (file.delete()) {
                SigmobLog.d("删除单个文件" + str + "成功！");
                return true;
            }
            SigmobLog.d("删除单个文件" + str + "失败！");
            return false;
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
            return false;
        }
    }

    public static String c() {
        String str = c + File.separator + "nativeAd";
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str;
    }

    public static String c(String str) {
        int iLastIndexOf;
        return (str == null || str.length() <= 0 || (iLastIndexOf = str.lastIndexOf(46)) <= -1 || iLastIndexOf >= str.length() + (-1)) ? str : str.substring(iLastIndexOf + 1);
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x0068 A[Catch: Throwable -> 0x0064, TRY_LEAVE, TryCatch #6 {Throwable -> 0x0064, blocks: (B:37:0x0060, B:41:0x0068), top: B:52:0x0060 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0060 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:61:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.Object d(java.lang.String r4) throws java.lang.Throwable {
        /*
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> L37 java.lang.Throwable -> L3a
            r1.<init>(r4)     // Catch: java.lang.Throwable -> L37 java.lang.Throwable -> L3a
            boolean r4 = r1.exists()     // Catch: java.lang.Throwable -> L37 java.lang.Throwable -> L3a
            if (r4 != 0) goto Ld
            return r0
        Ld:
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L37 java.lang.Throwable -> L3a
            r4.<init>(r1)     // Catch: java.lang.Throwable -> L37 java.lang.Throwable -> L3a
            java.io.ObjectInputStream r1 = new java.io.ObjectInputStream     // Catch: java.lang.Throwable -> L2e java.lang.Throwable -> L34
            r1.<init>(r4)     // Catch: java.lang.Throwable -> L2e java.lang.Throwable -> L34
            java.lang.Object r2 = r1.readObject()     // Catch: java.lang.Throwable -> L2c java.lang.Throwable -> L5a
            r4.close()     // Catch: java.lang.Throwable -> L22
            r1.close()     // Catch: java.lang.Throwable -> L22
            goto L2a
        L22:
            r4 = move-exception
            java.lang.String r4 = r4.getMessage()
            com.sigmob.logger.SigmobLog.e(r4)
        L2a:
            r0 = r2
            goto L59
        L2c:
            r2 = move-exception
            goto L3d
        L2e:
            r1 = move-exception
            r3 = r0
            r0 = r4
            r4 = r1
            r1 = r3
            goto L5e
        L34:
            r2 = move-exception
            r1 = r0
            goto L3d
        L37:
            r4 = move-exception
            r1 = r0
            goto L5e
        L3a:
            r2 = move-exception
            r4 = r0
            r1 = r4
        L3d:
            java.lang.String r2 = r2.getMessage()     // Catch: java.lang.Throwable -> L5a
            com.sigmob.logger.SigmobLog.e(r2)     // Catch: java.lang.Throwable -> L5a
            if (r4 == 0) goto L4c
            r4.close()     // Catch: java.lang.Throwable -> L4a
            goto L4c
        L4a:
            r4 = move-exception
            goto L52
        L4c:
            if (r1 == 0) goto L59
            r1.close()     // Catch: java.lang.Throwable -> L4a
            goto L59
        L52:
            java.lang.String r4 = r4.getMessage()
            com.sigmob.logger.SigmobLog.e(r4)
        L59:
            return r0
        L5a:
            r0 = move-exception
            r3 = r0
            r0 = r4
            r4 = r3
        L5e:
            if (r0 == 0) goto L66
            r0.close()     // Catch: java.lang.Throwable -> L64
            goto L66
        L64:
            r0 = move-exception
            goto L6c
        L66:
            if (r1 == 0) goto L73
            r1.close()     // Catch: java.lang.Throwable -> L64
            goto L73
        L6c:
            java.lang.String r0 = r0.getMessage()
            com.sigmob.logger.SigmobLog.e(r0)
        L73:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sigmob.sdk.common.utils.d.d(java.lang.String):java.lang.Object");
    }

    public static String d() {
        String str = c + File.separator + "downloadAPKLog";
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str;
    }

    public static synchronized String e() {
        return c + File.separator + "splashAdUnit";
    }

    public static byte[] e(String str) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(str));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[com.ss.union.game.sdk.feedback.picture.select.d.d];
            while (true) {
                int i = fileInputStream.read(bArr);
                if (i == -1) {
                    fileInputStream.close();
                    byteArrayOutputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
                byteArrayOutputStream.write(bArr, 0, i);
            }
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
            return null;
        }
    }

    public static void f() {
        if (c != null) {
            File file = new File(c);
            if (file.exists()) {
                a(c);
            }
            if (!file.exists() || file.isFile()) {
                file.mkdirs();
            }
        }
    }

    public static File[] f(String str) {
        File[] fileArrListFiles = new File(str).listFiles();
        if (fileArrListFiles != null) {
            Arrays.sort(fileArrListFiles, new Comparator<File>() { // from class: com.sigmob.sdk.common.utils.d.1
                @Override // java.util.Comparator
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public int compare(File file, File file2) {
                    long jLastModified = file.lastModified() - file2.lastModified();
                    if (jLastModified > 0) {
                        return 1;
                    }
                    return jLastModified == 0 ? 0 : -1;
                }

                @Override // java.util.Comparator
                public boolean equals(Object obj) {
                    return true;
                }
            });
        }
        return fileArrListFiles;
    }

    public static File g(String str) {
        File file = new File(d, str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }
}
