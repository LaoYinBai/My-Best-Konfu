package org.android.spdy;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
public interface SslCertcb {
    void getPerformance(SpdySession spdySession, SslPermData sslPermData);

    SslPublickey getPublicKey(SpdySession spdySession);

    int putCertificate(SpdySession spdySession, byte[] bArr, int i);
}
