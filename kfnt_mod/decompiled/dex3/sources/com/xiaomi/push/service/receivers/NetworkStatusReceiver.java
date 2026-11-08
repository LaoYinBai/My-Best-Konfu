package com.xiaomi.push.service.receivers;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.xiaomi.mipush.sdk.COSPushHelper;
import com.xiaomi.mipush.sdk.FTOSPushHelper;
import com.xiaomi.mipush.sdk.HWPushHelper;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.ae;
import com.xiaomi.mipush.sdk.an;
import com.xiaomi.mipush.sdk.at;
import com.xiaomi.mipush.sdk.b;
import com.xiaomi.push.bg;
import com.xiaomi.push.gu;
import com.xiaomi.push.service.bh;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NetworkStatusReceiver extends BroadcastReceiver {

    /* renamed from: b, reason: collision with other field name */
    private boolean f971b;

    /* renamed from: a, reason: collision with other field name */
    private static BlockingQueue<Runnable> f968a = new LinkedBlockingQueue();
    private static int a = 1;
    private static int b = 1;
    private static int c = 2;

    /* renamed from: a, reason: collision with other field name */
    private static ThreadPoolExecutor f969a = new ThreadPoolExecutor(a, b, c, TimeUnit.SECONDS, f968a);

    /* renamed from: a, reason: collision with other field name */
    private static boolean f970a = false;

    public NetworkStatusReceiver() {
        this.f971b = false;
        this.f971b = true;
    }

    public NetworkStatusReceiver(Object obj) {
        this.f971b = false;
        f970a = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context) {
        if (!an.a(context).m56a() && b.m63a(context).m72c() && !b.m63a(context).m74e()) {
            try {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(context, "com.xiaomi.push.service.XMPushService"));
                intent.setAction("com.xiaomi.push.network_status_changed");
                bh.a(context).m618a(intent);
            } catch (Exception e) {
                com.xiaomi.channel.commonutils.logger.b.a(e);
            }
        }
        gu.m370a(context);
        if (bg.b(context) && an.a(context).m59b()) {
            an.a(context).m60c();
        }
        if (bg.b(context)) {
            if ("syncing".equals(ae.a(context).a(at.DISABLE_PUSH))) {
                MiPushClient.disablePush(context);
            }
            if ("syncing".equals(ae.a(context).a(at.ENABLE_PUSH))) {
                MiPushClient.enablePush(context);
            }
            if ("syncing".equals(ae.a(context).a(at.UPLOAD_HUAWEI_TOKEN))) {
                MiPushClient.syncAssemblePushToken(context);
            }
            if ("syncing".equals(ae.a(context).a(at.UPLOAD_FCM_TOKEN))) {
                MiPushClient.syncAssembleFCMPushToken(context);
            }
            if ("syncing".equals(ae.a(context).a(at.UPLOAD_COS_TOKEN))) {
                MiPushClient.syncAssembleCOSPushToken(context);
            }
            if ("syncing".equals(ae.a(context).a(at.UPLOAD_FTOS_TOKEN))) {
                MiPushClient.syncAssembleFTOSPushToken(context);
            }
            if (HWPushHelper.needConnect() && HWPushHelper.shouldTryConnect(context)) {
                HWPushHelper.setConnectTime(context);
                HWPushHelper.registerHuaWeiAssemblePush(context);
            }
            COSPushHelper.doInNetworkChange(context);
            FTOSPushHelper.doInNetworkChange(context);
        }
    }

    public static boolean a() {
        return f970a;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (this.f971b) {
            return;
        }
        f969a.execute(new a(this, context));
    }
}
