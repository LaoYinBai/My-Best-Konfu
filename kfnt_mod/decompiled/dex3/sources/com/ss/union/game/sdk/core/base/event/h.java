package com.ss.union.game.sdk.core.base.event;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.ss.union.game.sdk.common.util.ProcessUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class h {

    interface b {
        void a();
    }

    h() {
    }

    public static void a(Context context, b bVar) {
        if (ProcessUtils.isMainProcess(context)) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            context.registerReceiver(new a(bVar), intentFilter);
        }
    }

    static class a extends BroadcastReceiver {
        private b a;

        public a(b bVar) {
            this.a = bVar;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || !TextUtils.equals(intent.getAction(), "android.net.conn.CONNECTIVITY_CHANGE") || this.a == null) {
                return;
            }
            this.a.a();
        }
    }
}
