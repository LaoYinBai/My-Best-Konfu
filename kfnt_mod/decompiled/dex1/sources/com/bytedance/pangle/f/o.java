package com.bytedance.pangle.f;

import android.content.pm.Signature;
import android.util.ArraySet;
import androidx.annotation.Nullable;
import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class o {

    /* renamed from: a, reason: collision with root package name */
    public static final o f4624a = new o(null, 0, null, null, null);

    /* renamed from: b, reason: collision with root package name */
    @Nullable
    public final Signature[] f4625b;

    /* renamed from: c, reason: collision with root package name */
    public final int f4626c;

    @Nullable
    public final ArraySet<PublicKey> d;

    @Nullable
    public final Signature[] e;

    @Nullable
    public final int[] f;

    private static ArraySet<PublicKey> a(Signature[] signatureArr) {
        ArraySet<PublicKey> arraySet = new ArraySet<>(signatureArr.length);
        for (Signature signature : signatureArr) {
            Method methodA = com.bytedance.pangle.a.a.a.a(Signature.class, "getPublicKey", new Class[0]);
            if (methodA != null && methodA.isAccessible()) {
                try {
                    arraySet.add((PublicKey) methodA.invoke(signature, new Object[0]));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e2) {
                    e2.printStackTrace();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        }
        return arraySet;
    }

    public static boolean a(Signature[] signatureArr, Signature[] signatureArr2) {
        return signatureArr.length == signatureArr2.length && com.bytedance.pangle.util.b.a((Object[]) signatureArr, (Object[]) signatureArr2) && com.bytedance.pangle.util.b.a((Object[]) signatureArr2, (Object[]) signatureArr);
    }

    public static boolean b(Signature[] signatureArr, Signature[] signatureArr2) throws CertificateException {
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        Signature[] signatureArr3 = new Signature[signatureArr.length];
        for (int i = 0; i < signatureArr.length; i++) {
            signatureArr3[i] = a(certificateFactory, signatureArr[i]);
        }
        Signature[] signatureArr4 = new Signature[signatureArr2.length];
        for (int i2 = 0; i2 < signatureArr2.length; i2++) {
            signatureArr4[i2] = a(certificateFactory, signatureArr2[i2]);
        }
        return a(signatureArr3, signatureArr4);
    }

    private static Signature a(CertificateFactory certificateFactory, Signature signature) throws CertificateException {
        Signature signature2 = new Signature(((X509Certificate) certificateFactory.generateCertificate(new ByteArrayInputStream(signature.toByteArray()))).getEncoded());
        int length = signature2.toByteArray().length;
        int length2 = signature.toByteArray().length;
        if (Math.abs(length - length2) <= 2) {
            return signature2;
        }
        throw new CertificateException("Bounced cert length looks fishy; before " + length2 + ", after " + length);
    }

    public o(Signature[] signatureArr, int i, ArraySet<PublicKey> arraySet, Signature[] signatureArr2, int[] iArr) {
        this.f4625b = signatureArr;
        this.f4626c = i;
        this.d = arraySet;
        this.e = signatureArr2;
        this.f = iArr;
    }

    public o(Signature[] signatureArr, int i, Signature[] signatureArr2, int[] iArr) {
        this(signatureArr, i, a(signatureArr), signatureArr2, iArr);
    }

    public o(Signature[] signatureArr) {
        this(signatureArr, 2, null, null);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof o)) {
            return false;
        }
        o oVar = (o) obj;
        if (this.f4626c != oVar.f4626c || !a(this.f4625b, oVar.f4625b)) {
            return false;
        }
        if (this.d != null) {
            if (!this.d.equals(oVar.d)) {
                return false;
            }
        } else if (oVar.d != null) {
            return false;
        }
        return Arrays.equals(this.e, oVar.e) && Arrays.equals(this.f, oVar.f);
    }

    public final int hashCode() {
        return (((((((Arrays.hashCode(this.f4625b) * 31) + this.f4626c) * 31) + (this.d != null ? this.d.hashCode() : 0)) * 31) + Arrays.hashCode(this.e)) * 31) + Arrays.hashCode(this.f);
    }
}
