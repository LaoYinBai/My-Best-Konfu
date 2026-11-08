package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.ss.android.downloadlib.constants.DownloadConstants;
import com.umeng.message.common.inter.ITagManager;
import com.xiaomi.push.Cif;
import com.xiaomi.push.es;
import com.xiaomi.push.fg;
import com.xiaomi.push.ga;
import com.xiaomi.push.gd;
import com.xiaomi.push.gf;
import com.xiaomi.push.gg;
import com.xiaomi.push.gu;
import com.xiaomi.push.hj;
import com.xiaomi.push.ht;
import com.xiaomi.push.hw;
import com.xiaomi.push.hz;
import com.xiaomi.push.ii;
import com.xiaomi.push.it;
import com.xiaomi.push.service.av;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class q {
    public static Intent a(byte[] bArr, long j) {
        Cif cifA = a(bArr);
        if (cifA == null) {
            return null;
        }
        Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
        intent.putExtra("mipush_payload", bArr);
        intent.putExtra("mrt", Long.toString(j));
        intent.setPackage(cifA.f638b);
        return intent;
    }

    public static Cif a(Context context, Cif cif) {
        hz hzVar = new hz();
        hzVar.b(cif.m462a());
        hw hwVarM461a = cif.m461a();
        if (hwVarM461a != null) {
            hzVar.a(hwVarM461a.m418a());
            hzVar.a(hwVarM461a.m416a());
            if (!TextUtils.isEmpty(hwVarM461a.m423b())) {
                hzVar.c(hwVarM461a.m423b());
            }
        }
        hzVar.a(it.a(context, cif));
        Cif cifA = y.a(cif.b(), cif.m462a(), hzVar, hj.AckMessage);
        hw hwVarM417a = cif.m461a().m417a();
        hwVarM417a.a("mat", Long.toString(System.currentTimeMillis()));
        cifA.a(hwVarM417a);
        return cifA;
    }

    public static Cif a(byte[] bArr) {
        Cif cif = new Cif();
        try {
            it.a(cif, bArr);
            return cif;
        } catch (Throwable th) {
            com.xiaomi.channel.commonutils.logger.b.a(th);
            return null;
        }
    }

    private static void a(XMPushService xMPushService, Cif cif) {
        xMPushService.a(new r(4, xMPushService, cif));
    }

    private static void a(XMPushService xMPushService, Cif cif, ii iiVar) {
        xMPushService.a(new x(4, iiVar, cif, xMPushService));
    }

    private static void a(XMPushService xMPushService, Cif cif, String str) {
        xMPushService.a(new v(4, xMPushService, cif, str));
    }

    private static void a(XMPushService xMPushService, Cif cif, String str, String str2) {
        xMPushService.a(new w(4, xMPushService, cif, str, str2));
    }

    /* JADX WARN: Removed duplicated region for block: B:134:0x03d7  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0461  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0464  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(com.xiaomi.push.service.XMPushService r22, java.lang.String r23, byte[] r24, android.content.Intent r25) throws java.lang.NumberFormatException {
        /*
            Method dump skipped, instructions count: 1268
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.q.a(com.xiaomi.push.service.XMPushService, java.lang.String, byte[], android.content.Intent):void");
    }

    private static void a(XMPushService xMPushService, byte[] bArr, long j) throws NumberFormatException {
        Map<String, String> mapM419a;
        Cif cifA = a(bArr);
        if (cifA == null) {
            return;
        }
        if (TextUtils.isEmpty(cifA.f638b)) {
            com.xiaomi.channel.commonutils.logger.b.m22a("receive a mipush message without package name");
            return;
        }
        Long lValueOf = Long.valueOf(System.currentTimeMillis());
        Intent intentA = a(bArr, lValueOf.longValue());
        String strA = ac.a(cifA);
        gu.a(xMPushService, strA, j, true, true, System.currentTimeMillis());
        hw hwVarM461a = cifA.m461a();
        if (hwVarM461a != null && hwVarM461a.m418a() != null) {
            com.xiaomi.channel.commonutils.logger.b.e(String.format("receive a message. appid=%1$s, msgid= %2$s, action=%3$s", cifA.m462a(), hwVarM461a.m418a(), cifA.a()));
        }
        if (hwVarM461a != null) {
            hwVarM461a.a("mrt", Long.toString(lValueOf.longValue()));
        }
        if (hj.SendMessage == cifA.a() && n.a(xMPushService).m640a(cifA.f638b) && !ac.m581a(cifA)) {
            String strM418a = "";
            if (hwVarM461a != null) {
                strM418a = hwVarM461a.m418a();
                if (ac.e(cifA)) {
                    es.a(xMPushService.getApplicationContext()).a(cifA.b(), ac.b(cifA), strM418a, "1");
                }
            }
            com.xiaomi.channel.commonutils.logger.b.m22a("Drop a message for unregistered, msgid=" + strM418a);
            a(xMPushService, cifA, cifA.f638b);
            return;
        }
        if (hj.SendMessage == cifA.a() && n.a(xMPushService).m642c(cifA.f638b) && !ac.m581a(cifA)) {
            String strM418a2 = "";
            if (hwVarM461a != null) {
                strM418a2 = hwVarM461a.m418a();
                if (ac.e(cifA)) {
                    es.a(xMPushService.getApplicationContext()).a(cifA.b(), ac.b(cifA), strM418a2, "2");
                }
            }
            com.xiaomi.channel.commonutils.logger.b.m22a("Drop a message for push closed, msgid=" + strM418a2);
            a(xMPushService, cifA, cifA.f638b);
            return;
        }
        if (hj.SendMessage == cifA.a() && !TextUtils.equals(xMPushService.getPackageName(), "com.xiaomi.xmsf") && !TextUtils.equals(xMPushService.getPackageName(), cifA.f638b)) {
            com.xiaomi.channel.commonutils.logger.b.m22a("Receive a message with wrong package name, expect " + xMPushService.getPackageName() + ", received " + cifA.f638b);
            a(xMPushService, cifA, "unmatched_package", "package should be " + xMPushService.getPackageName() + ", but got " + cifA.f638b);
            if (hwVarM461a == null || !ac.e(cifA)) {
                return;
            }
            es.a(xMPushService.getApplicationContext()).a(cifA.b(), ac.b(cifA), hwVarM461a.m418a(), "3");
            return;
        }
        if (hwVarM461a != null && (mapM419a = hwVarM461a.m419a()) != null && mapM419a.containsKey("hide") && ITagManager.STATUS_TRUE.equalsIgnoreCase(mapM419a.get("hide"))) {
            b(xMPushService, cifA);
            return;
        }
        if (hwVarM461a != null && hwVarM461a.m419a() != null && hwVarM461a.m419a().containsKey("__miid")) {
            String str = hwVarM461a.m419a().get("__miid");
            String strA2 = com.xiaomi.push.t.a(xMPushService.getApplicationContext());
            if (TextUtils.isEmpty(strA2) || !TextUtils.equals(str, strA2)) {
                if (ac.e(cifA)) {
                    es.a(xMPushService.getApplicationContext()).a(cifA.b(), ac.b(cifA), hwVarM461a.m418a(), DownloadConstants.DOWNLOAD_FINISH_REASON_SHOW_DIALOG_ERROR);
                }
                com.xiaomi.channel.commonutils.logger.b.m22a(str + " should be login, but got " + strA2);
                a(xMPushService, cifA, "miid already logout or anther already login", str + " should be login, but got " + strA2);
                return;
            }
        }
        a(xMPushService, strA, bArr, intentA);
    }

    private static boolean a(Context context, Intent intent) {
        try {
            List<ResolveInfo> listQueryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 32);
            if (listQueryBroadcastReceivers != null) {
                if (!listQueryBroadcastReceivers.isEmpty()) {
                    return true;
                }
            }
            return false;
        } catch (Exception unused) {
            return true;
        }
    }

    private static boolean a(Context context, String str) {
        Intent intent = new Intent("com.xiaomi.mipush.miui.CLICK_MESSAGE");
        intent.setPackage(str);
        Intent intent2 = new Intent("com.xiaomi.mipush.miui.RECEIVE_MESSAGE");
        intent2.setPackage(str);
        PackageManager packageManager = context.getPackageManager();
        try {
            List<ResolveInfo> listQueryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent2, 32);
            List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 32);
            if (listQueryBroadcastReceivers.isEmpty()) {
                if (listQueryIntentServices.isEmpty()) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
            return false;
        }
    }

    private static boolean a(Cif cif) {
        return "com.xiaomi.xmsf".equals(cif.f638b) && cif.m461a() != null && cif.m461a().m419a() != null && cif.m461a().m419a().containsKey("miui_package_name");
    }

    private static boolean a(XMPushService xMPushService, String str, Cif cif, hw hwVar) {
        boolean z = true;
        if (hwVar != null && hwVar.m419a() != null && hwVar.m419a().containsKey("__check_alive") && hwVar.m419a().containsKey("__awake")) {
            ii iiVar = new ii();
            iiVar.b(cif.m462a());
            iiVar.d(str);
            iiVar.c(ht.AwakeSystemApp.f509a);
            iiVar.a(hwVar.m418a());
            iiVar.f650a = new HashMap();
            boolean zM347a = com.xiaomi.push.g.m347a(xMPushService.getApplicationContext(), str);
            iiVar.f650a.put("app_running", Boolean.toString(zM347a));
            if (!zM347a) {
                boolean z2 = Boolean.parseBoolean(hwVar.m419a().get("__awake"));
                iiVar.f650a.put("awaked", Boolean.toString(z2));
                if (!z2) {
                    z = false;
                }
            }
            try {
                y.a(xMPushService, y.a(cif.b(), cif.m462a(), iiVar, hj.Notification));
            } catch (ga e) {
                com.xiaomi.channel.commonutils.logger.b.a(e);
            }
        }
        return z;
    }

    private static void b(XMPushService xMPushService, Cif cif) {
        xMPushService.a(new s(4, xMPushService, cif));
    }

    private static boolean b(Cif cif) {
        Map<String, String> mapM419a = cif.m461a().m419a();
        return mapM419a != null && mapM419a.containsKey("notify_effect");
    }

    private static void c(XMPushService xMPushService, Cif cif) {
        xMPushService.a(new t(4, xMPushService, cif));
    }

    private static boolean c(Cif cif) {
        if (cif.m461a() == null || cif.m461a().m419a() == null) {
            return false;
        }
        return "1".equals(cif.m461a().m419a().get("obslete_ads_message"));
    }

    private static void d(XMPushService xMPushService, Cif cif) {
        xMPushService.a(new u(4, xMPushService, cif));
    }

    public void a(Context context, av.b bVar, boolean z, int i, String str) {
        l lVarA;
        if (z || (lVarA = m.a(context)) == null || !"token-expired".equals(str)) {
            return;
        }
        try {
            m.a(context, lVarA.f, lVarA.d, lVarA.e);
        } catch (IOException | JSONException e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
        }
    }

    public void a(XMPushService xMPushService, fg fgVar, av.b bVar) {
        try {
            a(xMPushService, fgVar.m317a(bVar.h), fgVar.c());
        } catch (IllegalArgumentException e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
        }
    }

    public void a(XMPushService xMPushService, gg ggVar, av.b bVar) {
        if (!(ggVar instanceof gf)) {
            com.xiaomi.channel.commonutils.logger.b.m22a("not a mipush message");
            return;
        }
        gf gfVar = (gf) ggVar;
        gd gdVarA = gfVar.a(com.umeng.commonsdk.proguard.o.at);
        if (gdVarA != null) {
            try {
                a(xMPushService, be.a(be.a(bVar.h, gfVar.j()), gdVarA.c()), gu.a(ggVar.mo354a()));
            } catch (IllegalArgumentException e) {
                com.xiaomi.channel.commonutils.logger.b.a(e);
            }
        }
    }
}
