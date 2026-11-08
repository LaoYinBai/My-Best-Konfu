package com.baidu.mobads.sdk.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.Base64;
import com.baidu.mobads.sdk.internal.bh;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.security.DigestInputStream;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ba extends File {

    /* renamed from: a, reason: collision with root package name */
    public static final String f3145a = "LocalApkFile";

    /* renamed from: b, reason: collision with root package name */
    private static final long f3146b = 6916965592955692235L;
    private static ClassLoader h;

    /* renamed from: c, reason: collision with root package name */
    private bf f3147c;
    private Class<?> d;
    private Context e;
    private PublicKey f;
    private bb g;

    public ba(String str, Context context) {
        this(str, context, null);
    }

    public ba(String str, Context context, bf bfVar) {
        super(str);
        this.d = null;
        this.e = null;
        this.g = bb.a();
        this.e = context;
        this.f3147c = bfVar;
        if (bfVar != null) {
            try {
                this.f = c("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDBcp8gg3O7bjdnz+pSxg+JH/mbcKfm7dEjcRqVNAFwG7bTpLwDQh40bZJzrcBKQWbD6kArR6TPuQUCMQ09/y55Vk1P2Kq7vJGGisFpjlqv2qlg8drLdhXkLQUt/SeZVJgT+CNxVbuzxAF61EEf8M0MHi1I2dm6n6lOA6fomiCD9wIDAQAB");
            } catch (Exception unused) {
                this.f = null;
            }
        }
    }

    protected void a() throws Throwable {
        if (this.f3147c != null) {
            String strA = a(new File(getAbsolutePath()));
            String strB = b(this.f3147c.d());
            if (strA.equalsIgnoreCase(strB)) {
                return;
            }
            throw new bh.a("doCheckApkIntegrity failed, md5sum: " + strA + ", checksum in json info: " + strB);
        }
        this.g.a(f3145a, "built-in apk, no need to check");
    }

    protected Class<?> b() {
        if (this.d == null) {
            File file = new File(getAbsolutePath());
            try {
                this.d = b(file);
            } catch (Exception unused) {
                file.delete();
            }
        }
        return this.d;
    }

    protected void a(String str) {
        renameTo(new File(str));
    }

    protected double c() {
        if (this.f3147c == null) {
            return 0.0d;
        }
        return this.f3147c.b();
    }

    private String b(String str) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        if (this.f == null) {
            return null;
        }
        byte[] bArrDecode = Base64.decode(str, 0);
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(2, this.f);
            return new String(cipher.doFinal(bArrDecode), "UTF-8").trim();
        } catch (Exception e) {
            this.g.c("ErrorWhileVerifySigNature", e);
            return null;
        }
    }

    private static PublicKey c(String str) throws Exception {
        try {
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str, 0)));
        } catch (NullPointerException unused) {
            throw new Exception("NullPointerException");
        } catch (NoSuchAlgorithmException unused2) {
            throw new Exception("NoSuchAlgorithmException");
        } catch (InvalidKeySpecException unused3) {
            throw new Exception("InvalidKeySpecException");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r2v5, types: [java.io.FileInputStream, java.io.InputStream] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x004f -> B:34:0x0076). Please report as a decompilation issue!!! */
    private String a(File file) throws Throwable {
        ?? fileInputStream;
        String str = "";
        bb bbVar = null;
        ?? r1 = 0;
        try {
            try {
                try {
                    fileInputStream = new FileInputStream(file);
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = bbVar;
                }
            } catch (Exception e) {
                e = e;
            }
        } catch (Exception e2) {
            bb bbVar2 = this.g;
            bbVar2.a(f3145a, e2.getMessage());
            bbVar = bbVar2;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            while (new DigestInputStream(fileInputStream, messageDigest).read(new byte[4096]) != -1) {
            }
            byte[] bArrDigest = messageDigest.digest();
            ?? r12 = 0;
            while (r12 < bArrDigest.length) {
                str = str + Integer.toString((bArrDigest[r12] & 255) + 256, 16).substring(1);
                r12++;
            }
            fileInputStream.close();
            bbVar = r12;
        } catch (Exception e3) {
            e = e3;
            r1 = fileInputStream;
            this.g.a(f3145a, e.getMessage());
            str = "";
            bbVar = r1;
            if (r1 != 0) {
                r1.close();
                bbVar = r1;
            }
            return str;
        } catch (Throwable th2) {
            th = th2;
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (Exception e4) {
                    this.g.a(f3145a, e4.getMessage());
                }
            }
            throw th;
        }
        return str;
    }

    private void d() {
        File[] fileArrListFiles = this.e.getFilesDir().listFiles(new a());
        for (int i = 0; fileArrListFiles != null && i < fileArrListFiles.length; i++) {
            if (fileArrListFiles[i].getAbsolutePath().contains("__xadsdk__remote__final__")) {
                this.g.a(f3145a, "clearDexCacheFiles-->" + i + "--" + fileArrListFiles[i].getAbsolutePath());
                fileArrListFiles[i].delete();
            }
        }
    }

    @TargetApi(14)
    private Class<?> b(File file) {
        Class<?> cls;
        this.g.a(f3145a, "Android version:" + av.a(this.e).d());
        Class<?> cls2 = null;
        try {
            synchronized (bh.class) {
                String absolutePath = file.getAbsolutePath();
                ClassLoader classLoader = getClass().getClassLoader();
                String absolutePath2 = this.e.getFilesDir().getAbsolutePath();
                String str = r.av;
                h = af.a(absolutePath, absolutePath2, null, classLoader);
                this.g.a(f3145a, "dexPath=" + absolutePath + ", cl=" + classLoader + ", dir=" + absolutePath2 + ", len=" + file.length() + ", list=" + file.list());
                cls = Class.forName(str, true, h);
            }
            cls2 = cls;
        } catch (Exception e) {
            this.g.a(f3145a, e.getMessage());
        }
        this.g.a(f3145a, "jar.path=" + file.getAbsolutePath() + ", clz=" + cls2);
        return cls2;
    }

    public static ClassLoader a(Context context) {
        if (h != null) {
            return h;
        }
        return aj.a(context.getApplicationContext());
    }

    class a implements FilenameFilter {
        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            return true;
        }

        a() {
        }
    }
}
