package com.vivo.push.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.HandlerThread;
import com.umeng.message.proguard.l;
import com.vivo.push.PushClient;
import com.vivo.push.cache.ClientConfigManagerImpl;
import com.vivo.push.d;
import com.vivo.push.util.ContextDelegate;
import com.vivo.push.util.n;
import com.vivo.push.util.p;
import com.xiaomi.mipush.sdk.Constants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class PushServiceReceiver extends BroadcastReceiver {
    private static HandlerThread a;
    private static Handler b;
    private static a c = new a();

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Context context2 = ContextDelegate.getContext(context);
        String action = intent.getAction();
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action) || "android.intent.action.ACTION_POWER_CONNECTED".equals(action) || "android.intent.action.ACTION_POWER_DISCONNECTED".equals(action)) {
            if (a == null) {
                HandlerThread handlerThread = new HandlerThread("PushServiceReceiver");
                a = handlerThread;
                handlerThread.start();
                b = new Handler(a.getLooper());
            }
            n.d("PushServiceReceiver", context2.getPackageName() + ": start PushSerevice for by " + action + "  ; handler : " + b);
            a.a(c, context2, action);
            b.removeCallbacks(c);
            b.postDelayed(c, 2000L);
        }
    }

    static class a implements Runnable {
        private Context a;
        private String b;

        a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            NetworkInfo networkInfoA = p.a(this.a);
            if (!(networkInfoA != null ? networkInfoA.isConnectedOrConnecting() : false)) {
                n.d("PushServiceReceiver", this.a.getPackageName() + ": 无网络  by " + this.b);
                n.a(this.a, "触发静态广播:无网络(" + this.b + Constants.ACCEPT_TIME_SEPARATOR_SP + this.a.getPackageName() + l.t);
                return;
            }
            n.d("PushServiceReceiver", this.a.getPackageName() + ": 执行开始出发动作: " + this.b);
            n.a(this.a, "触发静态广播(" + this.b + Constants.ACCEPT_TIME_SEPARATOR_SP + this.a.getPackageName() + l.t);
            d.a().a(this.a);
            if (ClientConfigManagerImpl.getInstance(this.a).isCancleBroadcastReceiver()) {
                return;
            }
            PushClient.getInstance(this.a).initialize();
        }

        static /* synthetic */ void a(a aVar, Context context, String str) {
            aVar.a = ContextDelegate.getContext(context).getApplicationContext();
            aVar.b = str;
        }
    }
}
