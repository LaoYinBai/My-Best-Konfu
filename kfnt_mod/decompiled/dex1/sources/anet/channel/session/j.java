package anet.channel.session;

import anet.channel.security.ISecurity;
import anet.channel.util.ALog;
import org.android.spdy.AccsSSLCallback;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class j implements AccsSSLCallback {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ TnetSpdySession f506a;

    j(TnetSpdySession tnetSpdySession) {
        this.f506a = tnetSpdySession;
    }

    public byte[] getSSLPublicKey(int i, byte[] bArr) {
        byte[] bArrDecrypt;
        try {
            bArrDecrypt = this.f506a.G.decrypt(this.f506a.f372a, ISecurity.CIPHER_ALGORITHM_AES128, "tnet_pksg_key", bArr);
            if (bArrDecrypt != null) {
                try {
                    if (ALog.isPrintLog(2)) {
                        ALog.i("getSSLPublicKey", null, "decrypt", new String(bArrDecrypt));
                    }
                } catch (Throwable th) {
                    th = th;
                    ALog.e("awcn.TnetSpdySession", "getSSLPublicKey", null, th, new Object[0]);
                    return bArrDecrypt;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            bArrDecrypt = null;
        }
        return bArrDecrypt;
    }
}
