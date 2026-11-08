package com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.tls;

import java.security.cert.X509Certificate;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface TrustRootIndex {
    X509Certificate findByIssuerAndSignature(X509Certificate x509Certificate);
}
