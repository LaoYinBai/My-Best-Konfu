package com.vivo.push;

import android.content.Intent;
import android.text.TextUtils;
import com.ss.android.socialbase.appdownloader.constants.InstallErrorCode;
import com.ss.union.game.sdk.core.splashEffect.view.a;
import com.taobao.accs.common.Constants;
import com.vivo.push.b.j;
import com.vivo.push.b.k;
import com.vivo.push.b.l;
import com.vivo.push.b.m;
import com.vivo.push.b.n;
import com.vivo.push.b.o;
import com.vivo.push.b.p;
import com.vivo.push.b.q;
import com.vivo.push.b.r;
import com.vivo.push.b.t;
import com.vivo.push.b.u;
import com.vivo.push.c.s;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class c implements IPushClientFactory {
    private s a = new s();

    @Override // com.vivo.push.IPushClientFactory
    public final g createReceiverCommand(Intent intent) {
        g uVar;
        g tVar;
        int intExtra = intent.getIntExtra(Constants.KEY_COMMAND, -1);
        if (intExtra < 0) {
            intExtra = intent.getIntExtra("method", -1);
        }
        if (intExtra == 20) {
            uVar = new u();
        } else if (intExtra != 2016) {
            switch (intExtra) {
                case 1:
                case 2:
                    tVar = new t(intExtra);
                    uVar = tVar;
                    break;
                case 3:
                    uVar = new o();
                    break;
                case 4:
                    uVar = new q();
                    break;
                case 5:
                    uVar = new p();
                    break;
                case 6:
                    uVar = new r();
                    break;
                case 7:
                    uVar = new n();
                    break;
                case 8:
                    uVar = new m();
                    break;
                case 9:
                    uVar = new k();
                    break;
                case 10:
                case 11:
                    tVar = new com.vivo.push.b.i(intExtra);
                    uVar = tVar;
                    break;
                case 12:
                    uVar = new j();
                    break;
                default:
                    uVar = null;
                    break;
            }
        } else {
            uVar = new l();
        }
        if (uVar != null) {
            a aVarA = a.a(intent);
            if (aVarA == null) {
                com.vivo.push.util.n.b("PushCommand", "bundleWapper is null");
            } else {
                String str = aVarA.b;
                if (!TextUtils.isEmpty(str)) {
                    uVar.b = str;
                } else {
                    uVar.b = aVarA.a("client_pkgname");
                }
                uVar.c(aVarA);
            }
        }
        return uVar;
    }

    @Override // com.vivo.push.IPushClientFactory
    public final e createTask(g gVar) {
        int i = gVar.a;
        if (i != 20) {
            switch (i) {
                case 0:
                    break;
                case 1:
                    return new com.vivo.push.c.p(gVar);
                case 2:
                    return new com.vivo.push.c.g(gVar);
                case 3:
                    return new com.vivo.push.c.k(gVar);
                case 4:
                    return new com.vivo.push.c.l(gVar);
                case 5:
                    return new com.vivo.push.c.m(gVar);
                case 6:
                    return new com.vivo.push.c.n(gVar);
                case 7:
                    return new com.vivo.push.c.j(gVar);
                case 8:
                    return new com.vivo.push.c.i(gVar);
                case 9:
                    return new com.vivo.push.c.f(gVar);
                case 10:
                    return new com.vivo.push.c.d(gVar);
                case 11:
                    return new com.vivo.push.c.q(gVar);
                case 12:
                    return new com.vivo.push.c.e(gVar);
                default:
                    switch (i) {
                        case 100:
                            return new com.vivo.push.c.b(gVar);
                        case 101:
                            return new com.vivo.push.c.c(gVar);
                        default:
                            switch (i) {
                                case 2000:
                                case 2001:
                                case 2002:
                                case 2003:
                                case InstallErrorCode.ERROR_CANCELED /* 2004 */:
                                case 2005:
                                case a.h.d /* 2008 */:
                                case a.h.e /* 2009 */:
                                case a.h.f /* 2010 */:
                                case 2011:
                                case 2012:
                                case 2013:
                                case 2014:
                                case 2015:
                                    break;
                                case 2006:
                                    return new com.vivo.push.c.a(gVar);
                                case a.h.c /* 2007 */:
                                    return new com.vivo.push.c.u(gVar);
                                default:
                                    return null;
                            }
                    }
            }
            return new com.vivo.push.c.t(gVar);
        }
        return new com.vivo.push.c.r(gVar);
    }

    @Override // com.vivo.push.IPushClientFactory
    public final com.vivo.push.c.o createReceiveTask(g gVar) {
        int i = gVar.a;
        if (i == 20) {
            return new com.vivo.push.c.r(gVar);
        }
        if (i != 2016) {
            switch (i) {
                case 1:
                    return new com.vivo.push.c.p(gVar);
                case 2:
                    return new com.vivo.push.c.g(gVar);
                case 3:
                    return new com.vivo.push.c.k(gVar);
                case 4:
                    return new com.vivo.push.c.l(gVar);
                case 5:
                    return new com.vivo.push.c.m(gVar);
                case 6:
                    return new com.vivo.push.c.n(gVar);
                case 7:
                    return new com.vivo.push.c.j(gVar);
                case 8:
                    return new com.vivo.push.c.i(gVar);
                case 9:
                    return new com.vivo.push.c.f(gVar);
                case 10:
                    return new com.vivo.push.c.d(gVar);
                case 11:
                    return new com.vivo.push.c.q(gVar);
                default:
                    return null;
            }
        }
        return new com.vivo.push.c.h(gVar);
    }
}
