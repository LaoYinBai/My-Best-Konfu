package c;

import com.bytedance.common.utility.StringEncryptUtils;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class n extends i {

    /* renamed from: a, reason: collision with root package name */
    private final MessageDigest f2155a;

    /* renamed from: b, reason: collision with root package name */
    private final Mac f2156b;

    public static n a(y yVar) {
        return new n(yVar, "MD5");
    }

    public static n b(y yVar) {
        return new n(yVar, StringEncryptUtils.SHA_1);
    }

    public static n c(y yVar) {
        return new n(yVar, StringEncryptUtils.SHA_256);
    }

    public static n a(y yVar, f fVar) {
        return new n(yVar, fVar, "HmacSHA1");
    }

    public static n b(y yVar, f fVar) {
        return new n(yVar, fVar, "HmacSHA256");
    }

    private n(y yVar, String str) {
        super(yVar);
        try {
            this.f2155a = MessageDigest.getInstance(str);
            this.f2156b = null;
        } catch (NoSuchAlgorithmException unused) {
            throw new AssertionError();
        }
    }

    private n(y yVar, f fVar, String str) throws InvalidKeyException {
        super(yVar);
        try {
            this.f2156b = Mac.getInstance(str);
            this.f2156b.init(new SecretKeySpec(fVar.l(), str));
            this.f2155a = null;
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        } catch (NoSuchAlgorithmException unused) {
            throw new AssertionError();
        }
    }

    @Override // c.i, c.y
    public long a(c cVar, long j) throws IllegalStateException, IOException {
        long jA = super.a(cVar, j);
        if (jA != -1) {
            long j2 = cVar.f2132c - jA;
            long j3 = cVar.f2132c;
            u uVar = cVar.f2131b;
            while (j3 > j2) {
                uVar = uVar.i;
                j3 -= uVar.e - uVar.d;
            }
            while (j3 < cVar.f2132c) {
                int i = (int) ((uVar.d + j2) - j3);
                if (this.f2155a != null) {
                    this.f2155a.update(uVar.f2184c, i, uVar.e - i);
                } else {
                    this.f2156b.update(uVar.f2184c, i, uVar.e - i);
                }
                j2 = (uVar.e - uVar.d) + j3;
                uVar = uVar.h;
                j3 = j2;
            }
        }
        return jA;
    }

    public f c() {
        return f.a(this.f2155a != null ? this.f2155a.digest() : this.f2156b.doFinal());
    }
}
