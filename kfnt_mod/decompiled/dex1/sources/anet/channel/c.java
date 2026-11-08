package anet.channel;

import anet.channel.security.ISecurity;
import anet.channel.strategy.dispatch.IAmdcSign;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class c implements IAmdcSign {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f409a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ ISecurity f410b;

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ SessionCenter f411c;

    c(SessionCenter sessionCenter, String str, ISecurity iSecurity) {
        this.f411c = sessionCenter;
        this.f409a = str;
        this.f410b = iSecurity;
    }

    @Override // anet.channel.strategy.dispatch.IAmdcSign
    public String getAppkey() {
        return this.f409a;
    }

    @Override // anet.channel.strategy.dispatch.IAmdcSign
    public String sign(String str) {
        return this.f410b.sign(this.f411c.f377b, ISecurity.SIGN_ALGORITHM_HMAC_SHA1, getAppkey(), str);
    }

    @Override // anet.channel.strategy.dispatch.IAmdcSign
    public boolean useSecurityGuard() {
        return !this.f410b.isSecOff();
    }
}
