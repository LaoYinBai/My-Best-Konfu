package anet.channel.session;

import anet.channel.IAuth;
import anet.channel.util.ALog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class i implements IAuth.AuthCallback {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ TnetSpdySession f505a;

    i(TnetSpdySession tnetSpdySession) {
        this.f505a = tnetSpdySession;
    }

    @Override // anet.channel.IAuth.AuthCallback
    public void onAuthSuccess() {
        this.f505a.notifyStatus(4, null);
        this.f505a.z = System.currentTimeMillis();
        if (this.f505a.D != null) {
            this.f505a.D.start(this.f505a);
        }
        this.f505a.q.ret = 1;
        ALog.d("awcn.TnetSpdySession", "spdyOnStreamResponse", this.f505a.p, "authTime", Long.valueOf(this.f505a.q.authTime));
        if (this.f505a.A > 0) {
            this.f505a.q.authTime = System.currentTimeMillis() - this.f505a.A;
        }
    }

    @Override // anet.channel.IAuth.AuthCallback
    public void onAuthFail(int i, String str) {
        this.f505a.notifyStatus(5, null);
        if (this.f505a.q != null) {
            this.f505a.q.closeReason = "Accs_Auth_Fail:" + i;
            this.f505a.q.errorCode = (long) i;
        }
        this.f505a.close();
    }
}
