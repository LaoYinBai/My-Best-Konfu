package com.ss.android.message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.bytedance.common.push.utility.i;
import com.ss.android.pushmanager.setting.PushSetting;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class MessageReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (context == null || intent == null) {
            return;
        }
        a(context.getApplicationContext(), intent.getAction(), intent.getData());
    }

    private void a(final Context context, final String str, final Uri uri) {
        com.bytedance.common.push.utility.b.b.a(new Runnable() { // from class: com.ss.android.message.MessageReceiver.1
            @Override // java.lang.Runnable
            public void run() {
                MessageReceiver.this.b(context, str, uri);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Context context, String str, Uri uri) {
        com.bytedance.common.push.utility.a.b("MessageReceiver", "doOnReceiveInWorkThread");
        com.bytedance.push.alive.b.a(context.getApplicationContext()).a();
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if ("android.intent.action.BOOT_COMPLETED".equals(str) && PushSetting.getInstance().isAllowCloseBootReceiver()) {
            return;
        }
        if (PushSetting.getInstance().isShutPushOnStopService()) {
            try {
                com.bytedance.push.utils.c.a(context);
                return;
            } catch (Throwable unused) {
                return;
            }
        }
        try {
            if ("android.intent.action.BOOT_COMPLETED".equals(str)) {
                if (PushSetting.getInstance().isAllowCloseBootReceiver()) {
                    return;
                }
                if (com.bytedance.common.push.utility.a.b()) {
                    com.bytedance.common.push.utility.a.b("MessageProcess", "BootReceiver");
                }
                c.c(context);
                return;
            }
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(str)) {
                if (com.bytedance.common.push.utility.a.b()) {
                    com.bytedance.common.push.utility.a.b("MessageProcess", "ConnectivityReceiver");
                }
                c.c(context);
                return;
            }
            if ("android.intent.action.DATE_CHANGED".equals(str)) {
                if (com.bytedance.common.push.utility.a.b()) {
                    com.bytedance.common.push.utility.a.b("MessageProcess", "DateChangeReceiver");
                }
                c.c(context);
                return;
            }
            if ("android.intent.action.MEDIA_MOUNTED".equals(str)) {
                if (com.bytedance.common.push.utility.a.b()) {
                    com.bytedance.common.push.utility.a.b("MessageProcess", "MediaMountedReceiver");
                }
                c.c(context);
                return;
            }
            if ("android.intent.action.MEDIA_UNMOUNTED".equals(str)) {
                if (com.bytedance.common.push.utility.a.b()) {
                    com.bytedance.common.push.utility.a.b("MessageProcess", "MediaUnmountedReceiver");
                }
                c.c(context);
                return;
            }
            if ("android.intent.action.USER_PRESENT".equals(str)) {
                if (com.bytedance.common.push.utility.a.b()) {
                    com.bytedance.common.push.utility.a.b("MessageProcess", "UserPresentReceiver");
                }
                c.c(context);
                return;
            }
            if ("android.intent.action.SCREEN_OFF".equals(str)) {
                if (com.bytedance.common.push.utility.a.b()) {
                    com.bytedance.common.push.utility.a.b("MessageProcess", "ScreenOffReceiver");
                }
                c.c(context);
                return;
            }
            if ("android.intent.action.SCREEN_ON".equals(str)) {
                if (com.bytedance.common.push.utility.a.b()) {
                    com.bytedance.common.push.utility.a.b("MessageProcess", "ScreenOnReceiver");
                }
                c.c(context);
                return;
            }
            if ("android.intent.action.PACKAGE_ADDED".equals(str)) {
                if (com.bytedance.common.push.utility.a.b()) {
                    com.bytedance.common.push.utility.a.b("MessageProcess", "AppAddedReceiver");
                }
                c.c(context);
                return;
            }
            if ("android.intent.action.ACTION_POWER_CONNECTED".equals(str)) {
                if (com.bytedance.common.push.utility.a.b()) {
                    com.bytedance.common.push.utility.a.b("MessageProcess", "PowerConnectedReceiver");
                }
                c.c(context);
                return;
            }
            if ("android.intent.action.ACTION_POWER_DISCONNECTED".equals(str)) {
                if (com.bytedance.common.push.utility.a.b()) {
                    com.bytedance.common.push.utility.a.b("MessageProcess", "PowerDisconnectedReceiver");
                }
                c.c(context);
            } else if ("android.intent.action.PACKAGE_REMOVED".equals(str)) {
                String schemeSpecificPart = uri != null ? uri.getSchemeSpecificPart() : null;
                if (com.bytedance.common.push.utility.a.b()) {
                    com.bytedance.common.push.utility.a.b("PushService", "AppRemovedReceiver : packageName = " + schemeSpecificPart);
                }
                if (i.a(schemeSpecificPart)) {
                    return;
                }
                Intent intentB = c.b(context);
                intentB.putExtra("remove_app", true);
                intentB.putExtra("remove_app_package", schemeSpecificPart);
                context.startService(intentB);
            }
        } catch (Exception unused2) {
        }
    }
}
