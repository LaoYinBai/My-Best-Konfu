package com.bytedance.sdk.adnet.game.a;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c implements HostnameVerifier {

    /* renamed from: a, reason: collision with root package name */
    public static final c f5011a = new c();

    private c() {
    }

    @Override // javax.net.ssl.HostnameVerifier
    public boolean verify(String str, SSLSession sSLSession) {
        return true;
    }
}
