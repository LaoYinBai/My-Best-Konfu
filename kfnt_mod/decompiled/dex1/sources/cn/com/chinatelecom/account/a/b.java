package cn.com.chinatelecom.account.a;

import android.content.Context;
import android.text.TextUtils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {
    /* JADX WARN: Removed duplicated region for block: B:106:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x008b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0095 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x009f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:57:0x007e -> B:90:0x0081). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(android.content.Context r5) throws java.lang.Throwable {
        /*
            java.io.File r5 = c(r5)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            if (r5 == 0) goto La8
            boolean r1 = r5.exists()
            if (r1 != 0) goto L13
            goto La8
        L13:
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L59 java.lang.Throwable -> L5d
            r2.<init>(r5)     // Catch: java.lang.Throwable -> L59 java.lang.Throwable -> L5d
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L52 java.lang.Throwable -> L55
            r5.<init>(r2)     // Catch: java.lang.Throwable -> L52 java.lang.Throwable -> L55
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L4a java.lang.Throwable -> L4c
            r3.<init>(r5)     // Catch: java.lang.Throwable -> L4a java.lang.Throwable -> L4c
        L23:
            java.lang.String r1 = r3.readLine()     // Catch: java.lang.Throwable -> L41 java.lang.Throwable -> L44
            if (r1 == 0) goto L2d
            r0.append(r1)     // Catch: java.lang.Throwable -> L41 java.lang.Throwable -> L44
            goto L23
        L2d:
            r3.close()     // Catch: java.lang.Exception -> L31
            goto L35
        L31:
            r1 = move-exception
            r1.printStackTrace()
        L35:
            r5.close()     // Catch: java.lang.Exception -> L39
            goto L3d
        L39:
            r5 = move-exception
            r5.printStackTrace()
        L3d:
            r2.close()     // Catch: java.lang.Exception -> L7d
            goto L81
        L41:
            r0 = move-exception
            r1 = r3
            goto L89
        L44:
            r1 = move-exception
            r4 = r2
            r2 = r5
            r5 = r1
            r1 = r3
            goto L50
        L4a:
            r0 = move-exception
            goto L89
        L4c:
            r3 = move-exception
            r4 = r2
            r2 = r5
            r5 = r3
        L50:
            r3 = r4
            goto L60
        L52:
            r0 = move-exception
            r5 = r1
            goto L89
        L55:
            r5 = move-exception
            r3 = r2
            r2 = r1
            goto L60
        L59:
            r0 = move-exception
            r5 = r1
            r2 = r5
            goto L89
        L5d:
            r5 = move-exception
            r2 = r1
            r3 = r2
        L60:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L86
            if (r1 == 0) goto L6d
            r1.close()     // Catch: java.lang.Exception -> L69
            goto L6d
        L69:
            r5 = move-exception
            r5.printStackTrace()
        L6d:
            if (r2 == 0) goto L77
            r2.close()     // Catch: java.lang.Exception -> L73
            goto L77
        L73:
            r5 = move-exception
            r5.printStackTrace()
        L77:
            if (r3 == 0) goto L81
            r3.close()     // Catch: java.lang.Exception -> L7d
            goto L81
        L7d:
            r5 = move-exception
            r5.printStackTrace()
        L81:
            java.lang.String r5 = r0.toString()
            return r5
        L86:
            r0 = move-exception
            r5 = r2
            r2 = r3
        L89:
            if (r1 == 0) goto L93
            r1.close()     // Catch: java.lang.Exception -> L8f
            goto L93
        L8f:
            r1 = move-exception
            r1.printStackTrace()
        L93:
            if (r5 == 0) goto L9d
            r5.close()     // Catch: java.lang.Exception -> L99
            goto L9d
        L99:
            r5 = move-exception
            r5.printStackTrace()
        L9d:
            if (r2 == 0) goto La7
            r2.close()     // Catch: java.lang.Exception -> La3
            goto La7
        La3:
            r5 = move-exception
            r5.printStackTrace()
        La7:
            throw r0
        La8:
            java.lang.String r5 = ""
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.chinatelecom.account.a.b.a(android.content.Context):java.lang.String");
    }

    public static void a(Context context, String str) throws Throwable {
        File fileC = c(context);
        if (fileC == null || !fileC.exists()) {
            a(b(context), str);
        } else {
            a(fileC, str);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x004f -> B:53:0x006a). Please report as a decompilation issue!!! */
    private static void a(File file, String str) throws Throwable {
        BufferedWriter bufferedWriter;
        FileWriter fileWriter;
        if (file == null || !file.exists()) {
            return;
        }
        BufferedWriter bufferedWriter2 = null;
        bufferedWriter2 = null;
        bufferedWriter2 = null;
        bufferedWriter2 = null;
        try {
            try {
                try {
                    fileWriter = new FileWriter(file, false);
                    try {
                        bufferedWriter = new BufferedWriter(fileWriter);
                    } catch (Exception e) {
                        e = e;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    bufferedWriter2 = bufferedWriter2;
                }
                try {
                    boolean zIsEmpty = TextUtils.isEmpty(str);
                    if (zIsEmpty != 0) {
                        str = "";
                    }
                    bufferedWriter.write(str);
                    bufferedWriter.flush();
                    try {
                        bufferedWriter.close();
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                    fileWriter.close();
                    bufferedWriter2 = zIsEmpty;
                } catch (Exception e4) {
                    e = e4;
                    bufferedWriter2 = bufferedWriter;
                    e.printStackTrace();
                    if (bufferedWriter2 != null) {
                        try {
                            bufferedWriter2.close();
                        } catch (Exception e5) {
                            e5.printStackTrace();
                        }
                    }
                    if (fileWriter != null) {
                        fileWriter.close();
                        bufferedWriter2 = bufferedWriter2;
                    }
                } catch (Throwable th) {
                    th = th;
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (Exception e6) {
                            e6.printStackTrace();
                        }
                    }
                    if (fileWriter == null) {
                        throw th;
                    }
                    try {
                        fileWriter.close();
                        throw th;
                    } catch (Exception e7) {
                        e7.printStackTrace();
                        throw th;
                    }
                }
            } catch (Exception e8) {
                e = e8;
                fileWriter = null;
            } catch (Throwable th2) {
                th = th2;
                bufferedWriter = null;
                fileWriter = null;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedWriter = bufferedWriter2;
        }
    }

    private static File b(Context context) throws IOException {
        if (context == null) {
            return null;
        }
        try {
            File file = new File(context.getFilesDir() + "/eAccount/Log/");
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(file, "ipa_ol.ds");
            if (file2.exists()) {
                file2.delete();
            }
            file2.createNewFile();
            return file2;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static File c(Context context) {
        if (context != null) {
            try {
                File file = new File(context.getFilesDir() + "/eAccount/Log/");
                if (!file.exists()) {
                    return null;
                }
                File file2 = new File(file, "ipa_ol.ds");
                if (file2.exists()) {
                    return file2;
                }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
