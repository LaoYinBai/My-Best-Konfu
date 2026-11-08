package com.bytedance.pangle.f;

import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Arrays;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class p extends r {

    /* renamed from: a, reason: collision with root package name */
    private final byte[] f4627a;

    /* renamed from: b, reason: collision with root package name */
    private int f4628b;

    p(X509Certificate x509Certificate, byte[] bArr) {
        super(x509Certificate);
        this.f4628b = -1;
        this.f4627a = bArr;
    }

    @Override // com.bytedance.pangle.f.r, java.security.cert.Certificate
    public final byte[] getEncoded() {
        return this.f4627a;
    }

    @Override // java.security.cert.Certificate
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof p)) {
            return false;
        }
        try {
            return Arrays.equals(getEncoded(), ((p) obj).getEncoded());
        } catch (CertificateEncodingException unused) {
            return false;
        }
    }

    @Override // java.security.cert.Certificate
    public final int hashCode() {
        if (this.f4628b == -1) {
            try {
                this.f4628b = Arrays.hashCode(getEncoded());
            } catch (CertificateEncodingException unused) {
                this.f4628b = 0;
            }
        }
        return this.f4628b;
    }
}
