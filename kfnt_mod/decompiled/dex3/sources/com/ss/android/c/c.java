package com.ss.android.c;

import com.xiaomi.mipush.sdk.Constants;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.security.MessageDigest;
import kotlin.UByte;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c {
    private static final String a = "TTMd5";
    private static final String b = "ttmd5";
    private static final int c = 1;
    private static final int d = 1;
    private static final char[] e = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final int f = 9;
    private static final int g = 8192;

    @Retention(RetentionPolicy.SOURCE)
    public @interface a {
        public static final int a = 0;
        public static final int b = 1;
        public static final int c = 2;
        public static final int d = 3;
        public static final int e = 4;
        public static final int f = 5;
        public static final int g = 6;
        public static final int h = 99;
    }

    public static String a(File file) {
        return a(file, 9, 8192L);
    }

    public static String a(File file, int i) {
        return a(file, i, 8192L);
    }

    public static String a(File file, int i, long j) {
        if (file == null) {
            return "";
        }
        try {
            return !file.exists() ? "" : b(file, i, j);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static String b(File file) {
        return a(file, -1, -1L);
    }

    public static String a(com.ss.android.c.b bVar) {
        return a(bVar, 9, 8192L);
    }

    public static String a(com.ss.android.c.b bVar, int i) {
        return a(bVar, i, 8192L);
    }

    public static String a(com.ss.android.c.b bVar, int i, long j) {
        if (bVar == null) {
            return "";
        }
        try {
            return b(bVar, i, j);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static int a(String str, File file) {
        String strB;
        b bVarA;
        if (str == null || str.length() == 0) {
            return 2;
        }
        if (file == null) {
            return 5;
        }
        try {
            if (!file.exists()) {
                return 5;
            }
            int i = -1;
            long j = -1;
            try {
                b bVarA2 = a(str);
                if (bVarA2 != null) {
                    if (bVarA2.a > 1) {
                        return 3;
                    }
                    i = bVarA2.c;
                    j = bVarA2.d;
                }
                try {
                    strB = b(file, i, j);
                } catch (Throwable th) {
                    th.printStackTrace();
                    strB = null;
                }
                if (strB != null && strB.length() != 0) {
                    if (bVarA2 == null || (bVarA2.a == 1 && bVarA2.b == 1)) {
                        if (strB.equals(str)) {
                            return 0;
                        }
                    } else if (bVarA2.e != null) {
                        try {
                            bVarA = a(strB);
                        } catch (Throwable unused) {
                            bVarA = null;
                        }
                        if (bVarA != null && bVarA2.c == bVarA.c && bVarA2.d == bVarA.d && bVarA2.e.equals(bVarA.e)) {
                            return 0;
                        }
                    }
                    return 1;
                }
                return 6;
            } catch (Throwable unused2) {
                return 4;
            }
        } catch (Throwable unused3) {
            return 99;
        }
    }

    public static int a(File file, File file2) {
        if (file == null || file2 == null) {
            return 5;
        }
        try {
            if (file.exists() && file2.exists()) {
                if (file == file2) {
                    return 0;
                }
                return b(file, 9, 8192L).equals(b(file2, 9, 8192L)) ? 0 : 1;
            }
            return 5;
        } catch (Throwable th) {
            th.printStackTrace();
            return 99;
        }
    }

    private static String b(File file, int i, long j) throws Exception {
        return b(new com.ss.android.c.a(file), i, j);
    }

    private static String b(com.ss.android.c.b bVar, int i, long j) throws Exception {
        long j2;
        int i2 = i;
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        if (messageDigest == null) {
            return "";
        }
        try {
            long jA = bVar.a();
            if (i2 <= 0 || j <= 0 || i2 * j > (8 * jA) / 10) {
                j2 = jA;
                i2 = 1;
            } else {
                j2 = j;
            }
            byte[] bArr = new byte[8192];
            a(bVar, messageDigest, bArr, 0L, j2);
            if (i2 > 2) {
                int i3 = i2 - 1;
                long j3 = (jA - (i2 * j2)) / i3;
                long j4 = 0;
                int i4 = 1;
                while (i4 < i3) {
                    long j5 = j4 + j2 + j3;
                    a(bVar, messageDigest, bArr, j5, j2);
                    i4++;
                    i3 = i3;
                    j4 = j5;
                }
            }
            if (i2 > 1) {
                a(bVar, messageDigest, bArr, jA - j2, j2);
            }
            String strA = a(messageDigest.digest());
            if (i2 == 1 && j2 == jA) {
                return strA;
            }
            String str = a(i2, j2) + ";" + strA;
            try {
                bVar.b();
            } catch (Throwable unused) {
            }
            return str;
        } finally {
            try {
                bVar.b();
            } catch (Throwable unused2) {
            }
        }
    }

    private static void a(com.ss.android.c.b bVar, MessageDigest messageDigest, byte[] bArr, long j, long j2) throws IOException {
        bVar.a(j, j2);
        long j3 = 0;
        while (j3 < j2) {
            int iA = bVar.a(bArr, 0, (int) Math.min(j2 - j3, bArr.length));
            if (iA <= 0) {
                throw new IOException("updateSample unexpected readCount <= 0, readCount = " + iA + ", readTotalCount = " + j3 + ", sampleSize = " + j2);
            }
            messageDigest.update(bArr, 0, iA);
            j3 += iA;
        }
    }

    private static String a(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException("bytes is null");
        }
        int length = bArr.length;
        int i = length * 2;
        char[] cArr = new char[i];
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            int i4 = bArr[i3 + 0] & UByte.MAX_VALUE;
            int i5 = i2 + 1;
            cArr[i2] = e[i4 >> 4];
            i2 = i5 + 1;
            cArr[i5] = e[i4 & 15];
        }
        return new String(cArr, 0, i);
    }

    private static String a(int i, long j) {
        return "ttmd5:1:1:" + a(i) + "g" + a(j);
    }

    private static b a(String str) throws Exception {
        if (!str.startsWith("ttmd5:")) {
            return null;
        }
        String[] strArrSplit = str.split(";");
        String[] strArrSplit2 = strArrSplit[0].split(Constants.COLON_SEPARATOR);
        b bVar = new b();
        bVar.a = Integer.parseInt(strArrSplit2[1]);
        if (bVar.a > 1) {
            return bVar;
        }
        bVar.b = Integer.parseInt(strArrSplit2[2]);
        String[] strArrSplit3 = strArrSplit2[3].split("g");
        bVar.c = (int) b(strArrSplit3[0]);
        bVar.d = b(strArrSplit3[1]);
        bVar.e = strArrSplit[1];
        return bVar;
    }

    private static String a(long j) {
        return Long.toHexString((j << 4) + 31);
    }

    private static long b(String str) throws RuntimeException {
        return (Long.parseLong(str, 16) - 31) >> 4;
    }

    private static class b {
        private int a;
        private int b;
        private int c;
        private long d;
        private String e;

        private b() {
        }
    }
}
