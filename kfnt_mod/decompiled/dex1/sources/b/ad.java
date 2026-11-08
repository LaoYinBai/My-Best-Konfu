package b;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import javax.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class ad {
    public abstract void a(c.d dVar) throws IOException;

    @Nullable
    public abstract x b();

    public long c() throws IOException {
        return -1L;
    }

    public static ad a(@Nullable x xVar, String str) {
        Charset charsetC = b.a.c.e;
        if (xVar != null && (charsetC = xVar.c()) == null) {
            charsetC = b.a.c.e;
            xVar = x.a(xVar + "; charset=utf-8");
        }
        return a(xVar, str.getBytes(charsetC));
    }

    public static ad a(@Nullable final x xVar, final c.f fVar) {
        return new ad() { // from class: b.ad.1
            @Override // b.ad
            @Nullable
            public x b() {
                return xVar;
            }

            @Override // b.ad
            public long c() throws IOException {
                return fVar.k();
            }

            @Override // b.ad
            public void a(c.d dVar) throws IOException {
                dVar.g(fVar);
            }
        };
    }

    public static ad a(@Nullable x xVar, byte[] bArr) {
        return a(xVar, bArr, 0, bArr.length);
    }

    public static ad a(@Nullable final x xVar, final byte[] bArr, final int i, final int i2) {
        if (bArr == null) {
            throw new NullPointerException("content == null");
        }
        b.a.c.a(bArr.length, i, i2);
        return new ad() { // from class: b.ad.2
            @Override // b.ad
            @Nullable
            public x b() {
                return xVar;
            }

            @Override // b.ad
            public long c() {
                return i2;
            }

            @Override // b.ad
            public void a(c.d dVar) throws IOException {
                dVar.c(bArr, i, i2);
            }
        };
    }

    public static ad a(@Nullable final x xVar, final File file) {
        if (file == null) {
            throw new NullPointerException("content == null");
        }
        return new ad() { // from class: b.ad.3
            @Override // b.ad
            @Nullable
            public x b() {
                return xVar;
            }

            @Override // b.ad
            public long c() {
                return file.length();
            }

            @Override // b.ad
            public void a(c.d dVar) throws Throwable {
                c.y yVarA;
                c.y yVar = null;
                try {
                    yVarA = c.p.a(file);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    dVar.a(yVarA);
                    b.a.c.a(yVarA);
                } catch (Throwable th2) {
                    th = th2;
                    yVar = yVarA;
                    b.a.c.a(yVar);
                    throw th;
                }
            }
        };
    }
}
