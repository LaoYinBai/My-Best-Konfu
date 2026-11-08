package com.ss.android.newmedia.redbadge;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import com.bytedance.common.push.utility.a.c;
import com.ss.android.pushmanager.MessageConstants;
import com.ss.android.pushmanager.a.a;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class RedbadgeHandler extends Service implements c.a {
    private com.bytedance.common.push.utility.a.c a;
    private Messenger b;

    public void handleMsg(Message message) {
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        com.bytedance.common.push.utility.a.b("RedbadgeHandler", "onCreate");
        this.a = new com.bytedance.common.push.utility.a.c(this);
        this.b = new Messenger((Handler) this.a);
    }

    @Override // android.app.Service
    public int onStartCommand(final Intent intent, int i, int i2) {
        com.ss.android.message.d.a().a(new Runnable() { // from class: com.ss.android.newmedia.redbadge.RedbadgeHandler.1
            @Override // java.lang.Runnable
            public void run() {
                RedbadgeHandler.this.a(intent);
                RedbadgeHandler.this.stopSelf();
            }
        });
        int iOnStartCommand = super.onStartCommand(intent, i, i2);
        if (((a.b) com.ss.android.b.a.b.a(a.b.class)).g()) {
            return 2;
        }
        return iOnStartCommand;
    }

    @Override // android.app.Service
    public IBinder onBind(final Intent intent) {
        com.ss.android.message.d.a().a(new Runnable() { // from class: com.ss.android.newmedia.redbadge.RedbadgeHandler.2
            @Override // java.lang.Runnable
            public void run() {
                RedbadgeHandler.this.a(intent);
                RedbadgeHandler.this.stopSelf();
            }
        });
        return this.b.getBinder();
    }

    protected void a(Intent intent) {
        com.bytedance.common.push.utility.a.b("RedbadgeHandler", "onHandleIntent = " + intent);
        if (intent == null) {
            return;
        }
        try {
            if ("com.ss.android.redbadge.message".equals(intent.getAction())) {
                b.a(this).a(intent.getStringExtra(MessageConstants.MESSAGE_KEY_DATA));
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
