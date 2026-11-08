package com.xiaomi.push.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.xiaomi.push.fg;
import com.xiaomi.push.ge;
import com.xiaomi.push.gf;
import com.xiaomi.push.gg;
import com.xiaomi.push.gi;
import com.xiaomi.push.service.av;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class e {
    private q a = new q();

    public static String a(av.b bVar) {
        StringBuilder sb;
        String str;
        if ("9".equals(bVar.g)) {
            sb = new StringBuilder();
            sb.append(bVar.f903a);
            str = ".permission.MIMC_RECEIVE";
        } else {
            sb = new StringBuilder();
            sb.append(bVar.f903a);
            str = ".permission.MIPUSH_RECEIVE";
        }
        sb.append(str);
        return sb.toString();
    }

    private static void a(Context context, Intent intent, av.b bVar) {
        if ("com.xiaomi.xmsf".equals(context.getPackageName())) {
            context.sendBroadcast(intent);
        } else {
            context.sendBroadcast(intent, a(bVar));
        }
    }

    av.b a(fg fgVar) {
        Collection<av.b> collectionM605a = av.a().m605a(Integer.toString(fgVar.a()));
        if (collectionM605a.isEmpty()) {
            return null;
        }
        Iterator<av.b> it = collectionM605a.iterator();
        if (collectionM605a.size() == 1) {
            return it.next();
        }
        String strG = fgVar.g();
        while (it.hasNext()) {
            av.b next = it.next();
            if (TextUtils.equals(strG, next.f906b)) {
                return next;
            }
        }
        return null;
    }

    av.b a(gg ggVar) {
        Collection<av.b> collectionM605a = av.a().m605a(ggVar.k());
        if (collectionM605a.isEmpty()) {
            return null;
        }
        Iterator<av.b> it = collectionM605a.iterator();
        if (collectionM605a.size() == 1) {
            return it.next();
        }
        String strM = ggVar.m();
        String strL = ggVar.l();
        while (it.hasNext()) {
            av.b next = it.next();
            if (TextUtils.equals(strM, next.f906b) || TextUtils.equals(strL, next.f906b)) {
                return next;
            }
        }
        return null;
    }

    @SuppressLint({"WrongConstant"})
    public void a(Context context) {
        Intent intent = new Intent();
        intent.setAction("com.xiaomi.push.service_started");
        if (com.xiaomi.push.l.c()) {
            intent.addFlags(DownloadExpSwitchCode.BACK_PARTIAL);
        }
        context.sendBroadcast(intent);
    }

    public void a(Context context, av.b bVar, int i) throws RemoteException {
        if ("5".equalsIgnoreCase(bVar.g)) {
            return;
        }
        Intent intent = new Intent();
        intent.setAction("com.xiaomi.push.channel_closed");
        intent.setPackage(bVar.f903a);
        intent.putExtra(az.r, bVar.g);
        intent.putExtra("ext_reason", i);
        intent.putExtra(az.p, bVar.f906b);
        intent.putExtra(az.C, bVar.i);
        if (bVar.f897a == null || !"9".equals(bVar.g)) {
            a(context, intent, bVar);
            return;
        }
        try {
            bVar.f897a.send(Message.obtain(null, 17, intent));
        } catch (RemoteException unused) {
            bVar.f897a = null;
            com.xiaomi.channel.commonutils.logger.b.m22a("peer may died: " + bVar.f906b.substring(bVar.f906b.lastIndexOf(64)));
        }
    }

    public void a(Context context, av.b bVar, String str, String str2) {
        if ("5".equalsIgnoreCase(bVar.g)) {
            com.xiaomi.channel.commonutils.logger.b.d("mipush kicked by server");
            return;
        }
        Intent intent = new Intent();
        intent.setAction("com.xiaomi.push.kicked");
        intent.setPackage(bVar.f903a);
        intent.putExtra("ext_kick_type", str);
        intent.putExtra("ext_kick_reason", str2);
        intent.putExtra("ext_chid", bVar.g);
        intent.putExtra(az.p, bVar.f906b);
        intent.putExtra(az.C, bVar.i);
        a(context, intent, bVar);
    }

    public void a(Context context, av.b bVar, boolean z, int i, String str) {
        if ("5".equalsIgnoreCase(bVar.g)) {
            this.a.a(context, bVar, z, i, str);
            return;
        }
        Intent intent = new Intent();
        intent.setAction("com.xiaomi.push.channel_opened");
        intent.setPackage(bVar.f903a);
        intent.putExtra("ext_succeeded", z);
        if (!z) {
            intent.putExtra("ext_reason", i);
        }
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra("ext_reason_msg", str);
        }
        intent.putExtra("ext_chid", bVar.g);
        intent.putExtra(az.p, bVar.f906b);
        intent.putExtra(az.C, bVar.i);
        a(context, intent, bVar);
    }

    public void a(XMPushService xMPushService, String str, fg fgVar) throws RemoteException {
        av.b bVarA = a(fgVar);
        if (bVarA == null) {
            com.xiaomi.channel.commonutils.logger.b.d("error while notify channel closed! channel " + str + " not registered");
            return;
        }
        if ("5".equalsIgnoreCase(str)) {
            this.a.a(xMPushService, fgVar, bVarA);
            return;
        }
        String str2 = bVarA.f903a;
        Intent intent = new Intent();
        intent.setAction("com.xiaomi.push.new_msg");
        intent.setPackage(str2);
        intent.putExtra("ext_chid", str);
        intent.putExtra("ext_raw_packet", fgVar.m317a(bVarA.h));
        intent.putExtra(az.C, bVarA.i);
        intent.putExtra(az.v, bVarA.h);
        if (bVarA.f897a != null) {
            try {
                bVarA.f897a.send(Message.obtain(null, 17, intent));
                return;
            } catch (RemoteException unused) {
                bVarA.f897a = null;
                com.xiaomi.channel.commonutils.logger.b.m22a("peer may died: " + bVarA.f906b.substring(bVarA.f906b.lastIndexOf(64)));
            }
        }
        if ("com.xiaomi.xmsf".equals(str2)) {
            return;
        }
        a(xMPushService, intent, bVarA);
    }

    public void a(XMPushService xMPushService, String str, gg ggVar) {
        String str2;
        String str3;
        av.b bVarA = a(ggVar);
        if (bVarA != null) {
            if ("5".equalsIgnoreCase(str)) {
                this.a.a(xMPushService, ggVar, bVarA);
                return;
            }
            String str4 = bVarA.f903a;
            if (ggVar instanceof gf) {
                str3 = "com.xiaomi.push.new_msg";
            } else if (ggVar instanceof ge) {
                str3 = "com.xiaomi.push.new_iq";
            } else if (ggVar instanceof gi) {
                str3 = "com.xiaomi.push.new_pres";
            } else {
                str2 = "unknown packet type, drop it";
            }
            Intent intent = new Intent();
            intent.setAction(str3);
            intent.setPackage(str4);
            intent.putExtra("ext_chid", str);
            intent.putExtra("ext_packet", ggVar.a());
            intent.putExtra(az.C, bVarA.i);
            intent.putExtra(az.v, bVarA.h);
            a(xMPushService, intent, bVarA);
            return;
        }
        str2 = "error while notify channel closed! channel " + str + " not registered";
        com.xiaomi.channel.commonutils.logger.b.d(str2);
    }
}
