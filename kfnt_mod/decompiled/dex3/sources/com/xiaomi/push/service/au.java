package com.xiaomi.push.service;

import android.os.RemoteException;
import android.text.TextUtils;
import com.sigmob.sdk.common.Constants;
import com.taobao.accs.utl.BaseMonitor;
import com.xiaomi.push.cq;
import com.xiaomi.push.cu;
import com.xiaomi.push.dd;
import com.xiaomi.push.eb;
import com.xiaomi.push.fc;
import com.xiaomi.push.fg;
import com.xiaomi.push.fq;
import com.xiaomi.push.gd;
import com.xiaomi.push.ge;
import com.xiaomi.push.gf;
import com.xiaomi.push.gg;
import com.xiaomi.push.gu;
import com.xiaomi.push.hd;
import com.xiaomi.push.service.av;
import java.util.Date;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class au {
    private XMPushService a;

    au(XMPushService xMPushService) {
        this.a = xMPushService;
    }

    private void a(gd gdVar) {
        String strC = gdVar.c();
        if (TextUtils.isEmpty(strC)) {
            return;
        }
        String[] strArrSplit = strC.split(";");
        cq cqVarA = cu.a().a(fq.a(), false);
        if (cqVarA == null || strArrSplit.length <= 0) {
            return;
        }
        cqVarA.a(strArrSplit);
        this.a.a(20, (Exception) null);
        this.a.a(true);
    }

    private void b(gg ggVar) {
        av.b bVarA;
        String strL = ggVar.l();
        String strK = ggVar.k();
        if (TextUtils.isEmpty(strL) || TextUtils.isEmpty(strK) || (bVarA = av.a().a(strK, strL)) == null) {
            return;
        }
        gu.a(this.a, bVarA.f903a, gu.a(ggVar.mo354a()), true, true, System.currentTimeMillis());
    }

    private void c(fg fgVar) {
        av.b bVarA;
        String strG = fgVar.g();
        String string = Integer.toString(fgVar.a());
        if (TextUtils.isEmpty(strG) || TextUtils.isEmpty(string) || (bVarA = av.a().a(string, strG)) == null) {
            return;
        }
        gu.a(this.a, bVarA.f903a, fgVar.c(), true, true, System.currentTimeMillis());
    }

    public void a(fg fgVar) {
        if (5 != fgVar.a()) {
            c(fgVar);
        }
        try {
            b(fgVar);
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.a("handle Blob chid = " + fgVar.a() + " cmd = " + fgVar.m312a() + " packetid = " + fgVar.e() + " failure ", e);
        }
    }

    public void a(gg ggVar) {
        if (!"5".equals(ggVar.k())) {
            b(ggVar);
        }
        String strK = ggVar.k();
        if (TextUtils.isEmpty(strK)) {
            strK = "1";
            ggVar.l("1");
        }
        if (strK.equals(Constants.FAIL)) {
            com.xiaomi.channel.commonutils.logger.b.m22a("Received wrong packet with chid = 0 : " + ggVar.mo354a());
        }
        if (ggVar instanceof ge) {
            gd gdVarA = ggVar.a("kick");
            if (gdVarA != null) {
                String strL = ggVar.l();
                String strA = gdVarA.a("type");
                String strA2 = gdVarA.a("reason");
                com.xiaomi.channel.commonutils.logger.b.m22a("kicked by server, chid=" + strK + " res=" + av.b.a(strL) + " type=" + strA + " reason=" + strA2);
                if (!"wait".equals(strA)) {
                    this.a.a(strK, strL, 3, strA2, strA);
                    av.a().m609a(strK, strL);
                    return;
                }
                av.b bVarA = av.a().a(strK, strL);
                if (bVarA != null) {
                    this.a.a(bVarA);
                    bVarA.a(av.c.unbind, 3, 0, strA2, strA);
                    return;
                }
                return;
            }
        } else if (ggVar instanceof gf) {
            gf gfVar = (gf) ggVar;
            if ("redir".equals(gfVar.b())) {
                gd gdVarA2 = gfVar.a("hosts");
                if (gdVarA2 != null) {
                    a(gdVarA2);
                    return;
                }
                return;
            }
        }
        this.a.b().a(this.a, strK, ggVar);
    }

    public void b(fg fgVar) throws RemoteException {
        StringBuilder sb;
        String strM271a;
        String string;
        av.c cVar;
        int i;
        int i2;
        String strM312a = fgVar.m312a();
        if (fgVar.a() != 0) {
            String string2 = Integer.toString(fgVar.a());
            if (!"SECMSG".equals(fgVar.m312a())) {
                if (!"BIND".equals(strM312a)) {
                    if ("KICK".equals(strM312a)) {
                        eb.g gVarA = eb.g.a(fgVar.m316a());
                        String strG = fgVar.g();
                        String strM266a = gVarA.m266a();
                        String strM268b = gVarA.m268b();
                        com.xiaomi.channel.commonutils.logger.b.m22a("kicked by server, chid=" + string2 + " res= " + av.b.a(strG) + " type=" + strM266a + " reason=" + strM268b);
                        if (!"wait".equals(strM266a)) {
                            this.a.a(string2, strG, 3, strM268b, strM266a);
                            av.a().m609a(string2, strG);
                            return;
                        }
                        av.b bVarA = av.a().a(string2, strG);
                        if (bVarA != null) {
                            this.a.a(bVarA);
                            bVarA.a(av.c.unbind, 3, 0, strM268b, strM266a);
                            return;
                        }
                        return;
                    }
                    return;
                }
                eb.d dVarA = eb.d.a(fgVar.m316a());
                String strG2 = fgVar.g();
                av.b bVarA2 = av.a().a(string2, strG2);
                if (bVarA2 == null) {
                    return;
                }
                if (dVarA.m245a()) {
                    com.xiaomi.channel.commonutils.logger.b.m22a("SMACK: channel bind succeeded, chid=" + fgVar.a());
                    bVarA2.a(av.c.binded, 1, 0, (String) null, (String) null);
                    return;
                }
                String strM244a = dVarA.m244a();
                if (BaseMonitor.ALARM_POINT_AUTH.equals(strM244a)) {
                    if ("invalid-sig".equals(dVarA.m246b())) {
                        com.xiaomi.channel.commonutils.logger.b.m22a("SMACK: bind error invalid-sig token = " + bVarA2.c + " sec = " + bVarA2.h);
                        hd.a(0, fc.BIND_INVALID_SIG.a(), 1, null, 0);
                    }
                    cVar = av.c.unbind;
                    i = 1;
                    i2 = 5;
                } else {
                    if (!"cancel".equals(strM244a)) {
                        if ("wait".equals(strM244a)) {
                            this.a.a(bVarA2);
                            bVarA2.a(av.c.unbind, 1, 7, dVarA.m246b(), strM244a);
                        }
                        string = "SMACK: channel bind failed, chid=" + string2 + " reason=" + dVarA.m246b();
                        com.xiaomi.channel.commonutils.logger.b.m22a(string);
                    }
                    cVar = av.c.unbind;
                    i = 1;
                    i2 = 7;
                }
                bVarA2.a(cVar, i, i2, dVarA.m246b(), strM244a);
                av.a().m609a(string2, strG2);
                string = "SMACK: channel bind failed, chid=" + string2 + " reason=" + dVarA.m246b();
                com.xiaomi.channel.commonutils.logger.b.m22a(string);
            }
            if (!fgVar.m315a()) {
                this.a.b().a(this.a, string2, fgVar);
                return;
            }
            sb = new StringBuilder();
            sb.append("Recv SECMSG errCode = ");
            sb.append(fgVar.b());
            sb.append(" errStr = ");
            strM271a = fgVar.m319c();
        } else {
            if ("PING".equals(strM312a)) {
                byte[] bArrM316a = fgVar.m316a();
                if (bArrM316a != null && bArrM316a.length > 0) {
                    eb.j jVarA = eb.j.a(bArrM316a);
                    if (jVarA.m279b()) {
                        bk.a().a(jVarA.m277a());
                    }
                }
                if (!"com.xiaomi.xmsf".equals(this.a.getPackageName())) {
                    this.a.m569a();
                }
                if ("1".equals(fgVar.e())) {
                    com.xiaomi.channel.commonutils.logger.b.m22a("received a server ping");
                } else {
                    hd.b();
                }
                this.a.m572b();
                return;
            }
            if ("SYNC".equals(strM312a)) {
                if ("CONF".equals(fgVar.m318b())) {
                    bk.a().a(eb.b.a(fgVar.m316a()));
                    return;
                }
                if (TextUtils.equals("U", fgVar.m318b())) {
                    eb.k kVarA = eb.k.a(fgVar.m316a());
                    dd.a(this.a).a(kVarA.m281a(), kVarA.m284b(), new Date(kVarA.m280a()), new Date(kVarA.m283b()), kVarA.c() * com.ss.union.game.sdk.feedback.picture.select.d.d, kVarA.e());
                    fg fgVar2 = new fg();
                    fgVar2.a(0);
                    fgVar2.a(fgVar.m312a(), "UCA");
                    fgVar2.a(fgVar.e());
                    this.a.a(new bg(this.a, fgVar2));
                    return;
                }
                if (!TextUtils.equals("P", fgVar.m318b())) {
                    return;
                }
                eb.i iVarA = eb.i.a(fgVar.m316a());
                fg fgVar3 = new fg();
                fgVar3.a(0);
                fgVar3.a(fgVar.m312a(), "PCA");
                fgVar3.a(fgVar.e());
                eb.i iVar = new eb.i();
                if (iVarA.m275a()) {
                    iVar.a(iVarA.m274a());
                }
                fgVar3.a(iVar.a(), (String) null);
                this.a.a(new bg(this.a, fgVar3));
                sb = new StringBuilder();
                sb.append("ACK msgP: id = ");
                strM271a = fgVar.e();
            } else {
                if (!"NOTIFY".equals(fgVar.m312a())) {
                    return;
                }
                eb.h hVarA = eb.h.a(fgVar.m316a());
                sb = new StringBuilder();
                sb.append("notify by server err = ");
                sb.append(hVarA.c());
                sb.append(" desc = ");
                strM271a = hVarA.m271a();
            }
        }
        sb.append(strM271a);
        string = sb.toString();
        com.xiaomi.channel.commonutils.logger.b.m22a(string);
    }
}
