package com.xiaomi.push.service;

import android.content.Context;
import android.os.Messenger;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.push.Cif;
import com.xiaomi.push.da;
import com.xiaomi.push.fg;
import com.xiaomi.push.fp;
import com.xiaomi.push.ga;
import com.xiaomi.push.gg;
import com.xiaomi.push.hj;
import com.xiaomi.push.hy;
import com.xiaomi.push.ii;
import com.xiaomi.push.it;
import com.xiaomi.push.iu;
import com.xiaomi.push.iz;
import com.xiaomi.push.service.av;
import java.nio.ByteBuffer;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class y {
    static fg a(XMPushService xMPushService, byte[] bArr) {
        Cif cif = new Cif();
        try {
            it.a(cif, bArr);
            return a(m.a((Context) xMPushService), xMPushService, cif);
        } catch (iz e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
            return null;
        }
    }

    static fg a(l lVar, Context context, Cif cif) throws NumberFormatException {
        try {
            fg fgVar = new fg();
            fgVar.a(5);
            fgVar.c(lVar.f960a);
            fgVar.b(a(cif));
            fgVar.a("SECMSG", "message");
            String str = lVar.f960a;
            cif.f633a.f558a = str.substring(0, str.indexOf("@"));
            cif.f633a.f562c = str.substring(str.indexOf("/") + 1);
            fgVar.a(it.a(cif), lVar.c);
            fgVar.a((short) 1);
            com.xiaomi.channel.commonutils.logger.b.m22a("try send mi push message. packagename:" + cif.f638b + " action:" + cif.f631a);
            return fgVar;
        } catch (NullPointerException e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
            return null;
        }
    }

    static Cif a(String str, String str2) {
        ii iiVar = new ii();
        iiVar.b(str2);
        iiVar.c("package uninstalled");
        iiVar.a(gg.i());
        iiVar.a(false);
        return a(str, str2, iiVar, hj.Notification);
    }

    static <T extends iu<T, ?>> Cif a(String str, String str2, T t, hj hjVar) {
        return a(str, str2, t, hjVar, true);
    }

    private static <T extends iu<T, ?>> Cif a(String str, String str2, T t, hj hjVar, boolean z) {
        byte[] bArrA = it.a(t);
        Cif cif = new Cif();
        hy hyVar = new hy();
        hyVar.f557a = 5L;
        hyVar.f558a = "fakeid";
        cif.a(hyVar);
        cif.a(ByteBuffer.wrap(bArrA));
        cif.a(hjVar);
        cif.b(z);
        cif.b(str);
        cif.a(false);
        cif.a(str2);
        return cif;
    }

    private static String a(Cif cif) {
        if (cif.f632a != null && cif.f632a.f548b != null) {
            String str = cif.f632a.f548b.get("ext_traffic_source_pkg");
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
        }
        return cif.f638b;
    }

    static String a(String str) {
        return str + ".permission.MIPUSH_RECEIVE";
    }

    static void a(XMPushService xMPushService) {
        l lVarA = m.a(xMPushService.getApplicationContext());
        if (lVarA != null) {
            av.b bVarA = m.a(xMPushService.getApplicationContext()).a(xMPushService);
            a(xMPushService, bVarA);
            av.a().a(bVarA);
            bm.a(xMPushService).a(new z("GAID", 172800L, xMPushService, lVarA));
            a(xMPushService, lVarA, 172800);
        }
    }

    static void a(XMPushService xMPushService, Cif cif) {
        da.a(cif.b(), xMPushService.getApplicationContext(), cif, -1);
        fp fpVarM567a = xMPushService.m567a();
        if (fpVarM567a == null) {
            throw new ga("try send msg while connection is null.");
        }
        if (!fpVarM567a.mo326a()) {
            throw new ga("Don't support XMPP connection.");
        }
        fg fgVarA = a(m.a((Context) xMPushService), xMPushService, cif);
        if (fgVarA != null) {
            fpVarM567a.b(fgVarA);
        }
    }

    static void a(XMPushService xMPushService, av.b bVar) {
        bVar.a((Messenger) null);
        bVar.a(new ab(xMPushService));
    }

    private static void a(XMPushService xMPushService, l lVar, int i) {
        bm.a(xMPushService).a(new aa("MSAID", i, xMPushService, lVar));
    }

    static void a(XMPushService xMPushService, String str, byte[] bArr) throws ga {
        da.a(str, xMPushService.getApplicationContext(), bArr);
        fp fpVarM567a = xMPushService.m567a();
        if (fpVarM567a == null) {
            throw new ga("try send msg while connection is null.");
        }
        if (!fpVarM567a.mo326a()) {
            throw new ga("Don't support XMPP connection.");
        }
        fg fgVarA = a(xMPushService, bArr);
        if (fgVarA != null) {
            fpVarM567a.b(fgVarA);
        } else {
            p.a(xMPushService, str, bArr, ErrorCode.ERROR_INVALID_PAYLOAD, "not a valid message");
        }
    }

    static <T extends iu<T, ?>> Cif b(String str, String str2, T t, hj hjVar) {
        return a(str, str2, t, hjVar, false);
    }
}
