package c;

import com.bytedance.common.utility.StringEncryptUtils;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.annotation.Nullable;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class m extends h {

    /* renamed from: a, reason: collision with root package name */
    @Nullable
    private final MessageDigest f2153a;

    /* renamed from: b, reason: collision with root package name */
    @Nullable
    private final Mac f2154b;

    public static m a(x xVar) {
        return new m(xVar, "MD5");
    }

    public static m b(x xVar) {
        return new m(xVar, StringEncryptUtils.SHA_1);
    }

    public static m c(x xVar) {
        return new m(xVar, StringEncryptUtils.SHA_256);
    }

    public static m d(x xVar) {
        return new m(xVar, "SHA-512");
    }

    public static m a(x xVar, f fVar) {
        return new m(xVar, fVar, "HmacSHA1");
    }

    public static m b(x xVar, f fVar) {
        return new m(xVar, fVar, "HmacSHA256");
    }

    public static m c(x xVar, f fVar) {
        return new m(xVar, fVar, "HmacSHA512");
    }

    private m(x xVar, String str) {
        super(xVar);
        try {
            this.f2153a = MessageDigest.getInstance(str);
            this.f2154b = null;
        } catch (NoSuchAlgorithmException unused) {
            throw new AssertionError();
        }
    }

    private m(x xVar, f fVar, String str) throws InvalidKeyException {
        super(xVar);
        try {
            this.f2154b = Mac.getInstance(str);
            this.f2154b.init(new SecretKeySpec(fVar.l(), str));
            this.f2153a = null;
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        } catch (NoSuchAlgorithmException unused) {
            throw new AssertionError();
        }
    }

    @Override // c.h, c.x
    public void a_(c cVar, long j) throws IllegalStateException, IOException {
        ab.a(cVar.f2132c, 0L, j);
        u uVar = cVar.f2131b;
        long j2 = 0;
        while (j2 < j) {
            int iMin = (int) Math.min(j - j2, uVar.e - uVar.d);
            if (this.f2153a != null) {
                this.f2153a.update(uVar.f2184c, uVar.d, iMin);
            } else {
                this.f2154b.update(uVar.f2184c, uVar.d, iMin);
            }
            j2 += iMin;
            uVar = uVar.h;
        }
        super.a_(cVar, j);
    }

    public f c() {
        return f.a(this.f2153a != null ? this.f2153a.digest() : this.f2154b.doFinal());
    }
}
