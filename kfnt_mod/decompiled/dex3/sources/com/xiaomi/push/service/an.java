package com.xiaomi.push.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class an {
    private static long a;

    public static class a {
        int a;

        /* renamed from: a, reason: collision with other field name */
        byte[] f883a;

        public a(byte[] bArr, int i) {
            this.f883a = bArr;
            this.a = i;
        }
    }

    public static class b {
        public long a;

        /* renamed from: a, reason: collision with other field name */
        public Bitmap f884a;

        public b(Bitmap bitmap, long j) {
            this.f884a = bitmap;
            this.a = j;
        }
    }

    private static int a(Context context, InputStream inputStream) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, options);
        if (options.outWidth == -1 || options.outHeight == -1) {
            com.xiaomi.channel.commonutils.logger.b.m22a("decode dimension failed for bitmap.");
            return 1;
        }
        int iRound = Math.round((context.getResources().getDisplayMetrics().densityDpi / 160.0f) * 48.0f);
        if (options.outWidth <= iRound || options.outHeight <= iRound) {
            return 1;
        }
        return Math.min(options.outWidth / iRound, options.outHeight / iRound);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r3v5 */
    public static Bitmap a(Context context, String str) throws Throwable {
        InputStream inputStreamOpenInputStream;
        InputStream inputStreamOpenInputStream2;
        Uri uri = Uri.parse(str);
        try {
            try {
                inputStreamOpenInputStream = context.getContentResolver().openInputStream(uri);
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e) {
            e = e;
            inputStreamOpenInputStream2 = null;
            inputStreamOpenInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            context = 0;
            inputStreamOpenInputStream = null;
        }
        try {
            int iA = a((Context) context, inputStreamOpenInputStream);
            inputStreamOpenInputStream2 = context.getContentResolver().openInputStream(uri);
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = iA;
                Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpenInputStream2, null, options);
                com.xiaomi.push.y.a(inputStreamOpenInputStream2);
                com.xiaomi.push.y.a(inputStreamOpenInputStream);
                return bitmapDecodeStream;
            } catch (IOException e2) {
                e = e2;
                com.xiaomi.channel.commonutils.logger.b.a(e);
                com.xiaomi.push.y.a(inputStreamOpenInputStream2);
                com.xiaomi.push.y.a(inputStreamOpenInputStream);
                return null;
            }
        } catch (IOException e3) {
            e = e3;
            inputStreamOpenInputStream2 = null;
        } catch (Throwable th3) {
            th = th3;
            context = 0;
            com.xiaomi.push.y.a((Closeable) context);
            com.xiaomi.push.y.a(inputStreamOpenInputStream);
            throw th;
        }
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0101: MOVE (r0 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:64:0x0101 */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00df A[PHI: r1
  0x00df: PHI (r1v5 java.net.HttpURLConnection) = (r1v4 java.net.HttpURLConnection), (r1v6 java.net.HttpURLConnection) binds: [B:55:0x00dd, B:60:0x00fc] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static com.xiaomi.push.service.an.a a(java.lang.String r10, boolean r11) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 267
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.an.a(java.lang.String, boolean):com.xiaomi.push.service.an$a");
    }

    public static b a(Context context, String str, boolean z) throws Throwable {
        a aVarA;
        ByteArrayInputStream byteArrayInputStream = null;
        b bVar = new b(null, 0L);
        Bitmap bitmapB = b(context, str);
        try {
            if (bitmapB != null) {
                bVar.f884a = bitmapB;
                return bVar;
            }
            try {
                aVarA = a(str, z);
            } catch (Exception e) {
                e = e;
            }
            if (aVarA == null) {
                com.xiaomi.push.y.a((Closeable) null);
                return bVar;
            }
            bVar.a = aVarA.a;
            byte[] bArr = aVarA.f883a;
            if (bArr != null) {
                if (z) {
                    ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(bArr);
                    try {
                        int iA = a(context, byteArrayInputStream2);
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = iA;
                        bVar.f884a = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
                        byteArrayInputStream = byteArrayInputStream2;
                    } catch (Exception e2) {
                        e = e2;
                        byteArrayInputStream = byteArrayInputStream2;
                        com.xiaomi.channel.commonutils.logger.b.a(e);
                        com.xiaomi.push.y.a(byteArrayInputStream);
                        return bVar;
                    } catch (Throwable th) {
                        th = th;
                        byteArrayInputStream = byteArrayInputStream2;
                        com.xiaomi.push.y.a(byteArrayInputStream);
                        throw th;
                    }
                } else {
                    bVar.f884a = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
                }
            }
            a(context, aVarA.f883a, str);
            com.xiaomi.push.y.a(byteArrayInputStream);
            return bVar;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static void a(Context context) {
        File file = new File(context.getCacheDir().getPath() + File.separator + "mipush_icon");
        if (file.exists()) {
            if (a == 0) {
                a = com.xiaomi.push.x.a(file);
            }
            if (a > 15728640) {
                try {
                    File[] fileArrListFiles = file.listFiles();
                    for (int i = 0; i < fileArrListFiles.length; i++) {
                        if (!fileArrListFiles[i].isDirectory() && Math.abs(System.currentTimeMillis() - fileArrListFiles[i].lastModified()) > 1209600) {
                            fileArrListFiles[i].delete();
                        }
                    }
                } catch (Exception e) {
                    com.xiaomi.channel.commonutils.logger.b.a(e);
                }
                a = 0L;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:30:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:42:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.io.FileOutputStream, java.io.OutputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void a(android.content.Context r4, byte[] r5, java.lang.String r6) throws java.lang.Throwable {
        /*
            if (r5 != 0) goto L8
            java.lang.String r4 = "cannot save small icon cause bitmap is null"
            com.xiaomi.channel.commonutils.logger.b.m22a(r4)
            return
        L8:
            a(r4)
            java.io.File r0 = new java.io.File
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.io.File r2 = r4.getCacheDir()
            java.lang.String r2 = r2.getPath()
            r1.append(r2)
            java.lang.String r2 = java.io.File.separator
            r1.append(r2)
            java.lang.String r2 = "mipush_icon"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            boolean r1 = r0.exists()
            if (r1 != 0) goto L37
            r0.mkdirs()
        L37:
            java.io.File r1 = new java.io.File
            java.lang.String r6 = com.xiaomi.push.bm.a(r6)
            r1.<init>(r0, r6)
            r6 = 0
            boolean r0 = r1.exists()     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L69
            if (r0 != 0) goto L4a
            r1.createNewFile()     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L69
        L4a:
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L69
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L69
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch: java.lang.Exception -> L64 java.lang.Throwable -> Lab
            r2.<init>(r0)     // Catch: java.lang.Exception -> L64 java.lang.Throwable -> Lab
            r2.write(r5)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L61
            r2.flush()     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L61
            com.xiaomi.push.y.a(r2)
            goto L71
        L5e:
            r4 = move-exception
            r6 = r2
            goto Lac
        L61:
            r5 = move-exception
            r6 = r2
            goto L6b
        L64:
            r5 = move-exception
            goto L6b
        L66:
            r4 = move-exception
            r0 = r6
            goto Lac
        L69:
            r5 = move-exception
            r0 = r6
        L6b:
            com.xiaomi.channel.commonutils.logger.b.a(r5)     // Catch: java.lang.Throwable -> Lab
            com.xiaomi.push.y.a(r6)
        L71:
            com.xiaomi.push.y.a(r0)
            long r5 = com.xiaomi.push.service.an.a
            r2 = 0
            int r5 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r5 != 0) goto Laa
            java.io.File r5 = new java.io.File
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.io.File r4 = r4.getCacheDir()
            java.lang.String r4 = r4.getPath()
            r6.append(r4)
            java.lang.String r4 = java.io.File.separator
            r6.append(r4)
            java.lang.String r4 = "mipush_icon"
            r6.append(r4)
            java.lang.String r4 = r6.toString()
            r5.<init>(r4)
            long r4 = com.xiaomi.push.x.a(r5)
            long r0 = r1.length()
            long r4 = r4 + r0
            com.xiaomi.push.service.an.a = r4
        Laa:
            return
        Lab:
            r4 = move-exception
        Lac:
            com.xiaomi.push.y.a(r6)
            com.xiaomi.push.y.a(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.an.a(android.content.Context, byte[], java.lang.String):void");
    }

    private static Bitmap b(Context context, String str) throws Throwable {
        Bitmap bitmapDecodeStream;
        FileInputStream fileInputStream;
        File file = new File(context.getCacheDir().getPath() + File.separator + "mipush_icon", com.xiaomi.push.bm.a(str));
        FileInputStream fileInputStream2 = null;
        try {
            if (!file.exists()) {
                return null;
            }
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    try {
                        bitmapDecodeStream = BitmapFactory.decodeStream(fileInputStream);
                    } catch (Exception e) {
                        bitmapDecodeStream = null;
                        fileInputStream2 = fileInputStream;
                        e = e;
                    }
                } catch (Throwable th) {
                    fileInputStream2 = fileInputStream;
                    th = th;
                    com.xiaomi.push.y.a(fileInputStream2);
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
                bitmapDecodeStream = null;
            }
            try {
                file.setLastModified(System.currentTimeMillis());
                com.xiaomi.push.y.a(fileInputStream);
            } catch (Exception e3) {
                fileInputStream2 = fileInputStream;
                e = e3;
                com.xiaomi.channel.commonutils.logger.b.a(e);
                com.xiaomi.push.y.a(fileInputStream2);
                return bitmapDecodeStream;
            }
            return bitmapDecodeStream;
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
