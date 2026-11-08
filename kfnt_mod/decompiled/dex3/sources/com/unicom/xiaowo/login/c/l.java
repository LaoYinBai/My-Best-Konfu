package com.unicom.xiaowo.login.c;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class l implements HostnameVerifier {
    l(k kVar) {
    }

    @Override // javax.net.ssl.HostnameVerifier
    public final boolean verify(String str, SSLSession sSLSession) {
        return "opencloud.wostore.cn".equals(str);
    }
}
