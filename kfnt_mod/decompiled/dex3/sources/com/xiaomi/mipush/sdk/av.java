package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.push.ax;
import com.xiaomi.push.bm;
import com.xiaomi.push.hj;
import com.xiaomi.push.ht;
import com.xiaomi.push.hw;
import com.xiaomi.push.ii;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class av implements Runnable {
    final /* synthetic */ Context a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ boolean f78a;

    av(Context context, boolean z) {
        this.a = context;
        this.f78a = z;
    }

    @Override // java.lang.Runnable
    public void run() throws NoSuchAlgorithmException {
        Map<String, String> map;
        String str;
        String strD;
        com.xiaomi.channel.commonutils.logger.b.m22a("do sync info");
        ii iiVar = new ii(com.xiaomi.push.service.at.a(), false);
        b bVarM63a = b.m63a(this.a);
        iiVar.c(ht.SyncInfo.f509a);
        iiVar.b(bVarM63a.m64a());
        iiVar.d(this.a.getPackageName());
        iiVar.f650a = new HashMap();
        com.xiaomi.push.n.a(iiVar.f650a, "app_version", com.xiaomi.push.g.m345a(this.a, this.a.getPackageName()));
        com.xiaomi.push.n.a(iiVar.f650a, Constants.EXTRA_KEY_APP_VERSION_CODE, Integer.toString(com.xiaomi.push.g.a(this.a, this.a.getPackageName())));
        com.xiaomi.push.n.a(iiVar.f650a, "push_sdk_vn", "3_8_2");
        com.xiaomi.push.n.a(iiVar.f650a, "push_sdk_vc", Integer.toString(30802));
        com.xiaomi.push.n.a(iiVar.f650a, "token", bVarM63a.b());
        com.xiaomi.push.i.a(this.a, iiVar.f650a);
        if (!com.xiaomi.push.l.d()) {
            String strA = bm.a(com.xiaomi.push.i.f(this.a));
            String strH = com.xiaomi.push.i.h(this.a);
            if (!TextUtils.isEmpty(strH)) {
                strA = strA + Constants.ACCEPT_TIME_SEPARATOR_SP + strH;
            }
            if (!TextUtils.isEmpty(strA)) {
                com.xiaomi.push.n.a(iiVar.f650a, Constants.EXTRA_KEY_IMEI_MD5, strA);
            }
        }
        ax.a(this.a).a(iiVar.f650a);
        com.xiaomi.push.n.a(iiVar.f650a, Constants.EXTRA_KEY_REG_ID, bVarM63a.m71c());
        com.xiaomi.push.n.a(iiVar.f650a, Constants.EXTRA_KEY_REG_SECRET, bVarM63a.d());
        com.xiaomi.push.n.a(iiVar.f650a, Constants.EXTRA_KEY_ACCEPT_TIME, MiPushClient.getAcceptTime(this.a).replace(Constants.ACCEPT_TIME_SEPARATOR_SP, Constants.ACCEPT_TIME_SEPARATOR_SERVER));
        if (this.f78a) {
            com.xiaomi.push.n.a(iiVar.f650a, Constants.EXTRA_KEY_ALIASES_MD5, au.c(MiPushClient.getAllAlias(this.a)));
            com.xiaomi.push.n.a(iiVar.f650a, Constants.EXTRA_KEY_TOPICS_MD5, au.c(MiPushClient.getAllTopic(this.a)));
            map = iiVar.f650a;
            str = Constants.EXTRA_KEY_ACCOUNTS_MD5;
            strD = au.c(MiPushClient.getAllUserAccount(this.a));
        } else {
            com.xiaomi.push.n.a(iiVar.f650a, Constants.EXTRA_KEY_ALIASES, au.d(MiPushClient.getAllAlias(this.a)));
            com.xiaomi.push.n.a(iiVar.f650a, Constants.EXTRA_KEY_TOPICS, au.d(MiPushClient.getAllTopic(this.a)));
            map = iiVar.f650a;
            str = Constants.EXTRA_KEY_ACCOUNTS;
            strD = au.d(MiPushClient.getAllUserAccount(this.a));
        }
        com.xiaomi.push.n.a(map, str, strD);
        an.a(this.a).a((an) iiVar, hj.Notification, false, (hw) null);
    }
}
