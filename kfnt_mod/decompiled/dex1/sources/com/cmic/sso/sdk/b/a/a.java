package com.cmic.sso.sdk.b.a;

import android.text.TextUtils;
import com.cmic.sso.sdk.d.q;
import java.io.Closeable;
import java.io.IOException;
import java.net.HttpURLConnection;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a implements b {
    /* JADX WARN: Removed duplicated region for block: B:100:0x023e  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0259  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x019c A[Catch: all -> 0x0207, TryCatch #2 {all -> 0x0207, blocks: (B:72:0x017b, B:74:0x019c, B:75:0x01a1), top: B:107:0x017b }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0210  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x023b  */
    @Override // com.cmic.sso.sdk.b.a.b
    @android.annotation.TargetApi(21)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.cmic.sso.sdk.b.c.c r17, com.cmic.sso.sdk.b.d.c r18, com.cmic.sso.sdk.a r19) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 630
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cmic.sso.sdk.b.a.a.a(com.cmic.sso.sdk.b.c.c, com.cmic.sso.sdk.b.d.c, com.cmic.sso.sdk.a):void");
    }

    private void a(Closeable closeable) throws IOException {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void a(HttpURLConnection httpURLConnection, com.cmic.sso.sdk.a aVar, String str) {
        String strA;
        if (!(httpURLConnection instanceof HttpsURLConnection) || aVar.b("CLOSE_CERT_VERIFY", true) || str.contains("https://config.cmpassport.com/client/uniConfig")) {
            return;
        }
        com.cmic.sso.sdk.d.c.b("ConnectionInterceptor", "开启证书校验");
        if (str.contains("getAuthToken")) {
            strA = q.a(q.f());
        } else {
            strA = q.a(q.g());
        }
        com.cmic.sso.sdk.d.c.b("ConnectionInterceptor", "cer: " + strA);
        if (TextUtils.isEmpty(strA)) {
            return;
        }
        ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(new com.cmic.sso.sdk.b.e.a(strA).a().getSocketFactory());
    }
}
