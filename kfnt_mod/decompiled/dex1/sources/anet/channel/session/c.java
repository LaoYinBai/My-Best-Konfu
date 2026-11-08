package anet.channel.session;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class c implements HostnameVerifier {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f497a;

    c(String str) {
        this.f497a = str;
    }

    @Override // javax.net.ssl.HostnameVerifier
    public boolean verify(String str, SSLSession sSLSession) {
        return HttpsURLConnection.getDefaultHostnameVerifier().verify(this.f497a, sSLSession);
    }
}
