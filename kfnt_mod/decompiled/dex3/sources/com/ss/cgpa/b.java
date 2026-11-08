package com.ss.cgpa;

import android.util.Log;
import c.a.a.a.a.g;
import com.qq.e.comm.compliance.ApkDownloadComplianceInterface;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.CRC32;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b {
    private static String j = a.class.getEnclosingClass().getName();
    private String a = a("c6ccd8");
    private String b = a("c6ccd8a6cacac9a6d7d2d8caaac9fafdf3f0f5fe");
    private SecretKeySpec c = null;
    private String d = a("c3ccd8");
    private String e = a("c3ccd8a6cacac9a6d7d2d8caaac9fafdf3f0f5fe");
    private SecretKey f = null;
    private String g = a("a2b9b3d1");
    private String h = a("d4c1caa4bdbcbd");
    private String i = b.class.getName();

    static class a {
        a() {
        }
    }

    /* renamed from: com.ss.cgpa.b$b, reason: collision with other inner class name */
    static /* synthetic */ class C0043b {
        static final /* synthetic */ int[] a;
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[d.values().length];
            b = iArr;
            try {
                iArr[d.DES56.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[d.DES64.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[c.values().length];
            a = iArr2;
            try {
                iArr2[c.AES128.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[c.AES192.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[c.AES256.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public enum c {
        AES128(128),
        AES192(192),
        AES256(ApkDownloadComplianceInterface.INSTALL_BITS);

        c(int i) {
        }
    }

    public enum d {
        DES56(56),
        DES64(64);

        d(int i) {
        }
    }

    public static String a(String str) {
        try {
            return new String(a(c.a.a.a.a.d.a(str)));
        } catch (Exception e) {
            Log.e(j, e.toString());
            return null;
        }
    }

    private static byte[] a() {
        byte[] bArr = new byte[16];
        for (int i = 135; i < 151; i++) {
            bArr[i - 135] = (byte) i;
        }
        return bArr;
    }

    private byte[] a(c cVar, byte[] bArr) {
        StringBuilder sb;
        String strD;
        String string;
        int i = C0043b.a[cVar.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    return null;
                }
                if (32 == bArr.length) {
                    return bArr;
                }
                string = d(bArr);
                return string.getBytes();
            }
            if (24 == bArr.length) {
                return bArr;
            }
            sb = new StringBuilder();
            sb.append(c(bArr));
            sb.append(c(d(bArr).getBytes()));
            strD = e(bArr);
        } else {
            if (16 == bArr.length) {
                return bArr;
            }
            sb = new StringBuilder();
            sb.append(c(bArr));
            strD = d(bArr);
        }
        sb.append(c(strD.getBytes()));
        string = sb.toString();
        return string.getBytes();
    }

    private byte[] a(d dVar, byte[] bArr) {
        String strC;
        int i = C0043b.b[dVar.ordinal()];
        if (i != 1) {
            if (i != 2) {
                return null;
            }
            if (bArr.length % 8 <= 0) {
                return bArr;
            }
            strC = d(bArr);
        } else {
            if (8 == bArr.length) {
                return bArr;
            }
            strC = c(bArr);
        }
        return strC.getBytes();
    }

    public static byte[] a(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        byte[] bArrA = a();
        for (int i = 0; i < bArr.length; i++) {
            bArr2[i] = (byte) ((bArr[i] ^ bArrA[i % bArrA.length]) ^ (i & 255));
        }
        return bArr2;
    }

    public byte[] a(byte[] bArr, c cVar, byte[] bArr2) {
        return b(bArr, new SecretKeySpec(a(cVar, bArr2), this.a));
    }

    public byte[] a(byte[] bArr, SecretKeySpec secretKeySpec) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            Cipher cipher = Cipher.getInstance(this.b);
            cipher.init(2, secretKeySpec);
            return cipher.doFinal(bArr);
        } catch (Exception e) {
            Log.e(this.i, e.toString());
            return null;
        }
    }

    public String b(String str) {
        return g.c(str);
    }

    public void b(c cVar, byte[] bArr) {
        this.c = new SecretKeySpec(a(cVar, bArr), this.a);
    }

    public void b(d dVar, byte[] bArr) {
        try {
            this.f = SecretKeyFactory.getInstance(this.d).generateSecret(new DESKeySpec(a(dVar, bArr)));
        } catch (Exception e) {
            Log.e(this.i, e.toString());
        }
    }

    public byte[] b(byte[] bArr) {
        return a(bArr, this.c);
    }

    public byte[] b(byte[] bArr, SecretKeySpec secretKeySpec) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            Cipher cipher = Cipher.getInstance(this.b);
            cipher.init(1, secretKeySpec);
            return cipher.doFinal(bArr);
        } catch (Exception e) {
            Log.e(this.i, e.toString());
            return null;
        }
    }

    public String c(byte[] bArr) {
        CRC32 crc32 = new CRC32();
        crc32.update(bArr);
        return String.format(this.g, Long.valueOf(crc32.getValue()));
    }

    public String d(byte[] bArr) {
        return g.b(bArr);
    }

    public String e(byte[] bArr) {
        return g.c(bArr);
    }

    public String f(byte[] bArr) {
        try {
            return c.a.a.a.a.d.a(MessageDigest.getInstance(this.h).digest(bArr));
        } catch (Exception e) {
            Log.e(this.i, e.toString());
            return null;
        }
    }

    public byte[] g(byte[] bArr) {
        return b(bArr, this.c);
    }

    public byte[] h(byte[] bArr) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            Cipher cipher = Cipher.getInstance(this.e);
            cipher.init(1, this.f);
            return cipher.doFinal(bArr);
        } catch (Exception e) {
            Log.e(this.i, e.toString());
            return null;
        }
    }
}
