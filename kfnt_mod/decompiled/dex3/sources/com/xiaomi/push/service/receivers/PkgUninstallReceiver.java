package com.xiaomi.push.service.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.bd;
import com.xiaomi.push.service.bh;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class PkgUninstallReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null || !"android.intent.action.PACKAGE_REMOVED".equals(intent.getAction())) {
            return;
        }
        boolean z = intent.getExtras().getBoolean("android.intent.extra.REPLACING");
        Uri data = intent.getData();
        if (data == null || z) {
            return;
        }
        try {
            Intent intent2 = new Intent(context, (Class<?>) XMPushService.class);
            intent2.setAction(bd.a);
            intent2.putExtra("uninstall_pkg_name", data.getEncodedSchemeSpecificPart());
            bh.a(context).m618a(intent2);
        } catch (Exception e) {
            b.a(e);
        }
    }
}
