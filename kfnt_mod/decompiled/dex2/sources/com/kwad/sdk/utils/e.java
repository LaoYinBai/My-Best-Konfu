package com.kwad.sdk.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.kwad.sdk.utils.bc;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e implements bc.a {
    private a b;
    private String c;
    private BroadcastReceiver d = new BroadcastReceiver() { // from class: com.kwad.sdk.utils.e.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String schemeSpecificPart = (!TextUtils.equals("android.intent.action.PACKAGE_ADDED", intent.getAction()) || intent.getData() == null) ? null : intent.getData().getSchemeSpecificPart();
            if (TextUtils.isEmpty(schemeSpecificPart)) {
                return;
            }
            Message messageObtainMessage = e.this.a.obtainMessage(242);
            messageObtainMessage.obj = schemeSpecificPart;
            messageObtainMessage.sendToTarget();
        }
    };
    private Handler a = new bc(this);

    public interface a {
        void a(String str);
    }

    public e(String str) {
        this.c = str;
    }

    public void a(Context context) {
        if (context != null) {
            try {
                context.unregisterReceiver(this.d);
            } catch (Throwable th) {
                com.kwad.sdk.core.d.a.b(th);
            }
        }
        this.b = null;
    }

    public void a(Context context, a aVar) {
        if (context == null) {
            return;
        }
        this.b = aVar;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        context.registerReceiver(this.d, intentFilter);
    }

    @Override // com.kwad.sdk.utils.bc.a
    public void a(Message message) {
        if (message.what != 242 || message.obj == null || !message.obj.equals(this.c) || this.b == null) {
            return;
        }
        this.b.a(this.c);
    }

    public void a(String str) {
        this.c = str;
    }
}
