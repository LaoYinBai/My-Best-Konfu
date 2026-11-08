package com.xiaomi.push.service;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class bx extends Handler {
    final /* synthetic */ XMPushService a;

    bx(XMPushService xMPushService) {
        this.a = xMPushService;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (message != null) {
            try {
                switch (message.what) {
                    case 17:
                        if (message.obj != null) {
                            this.a.onStart((Intent) message.obj, 1);
                            break;
                        }
                        break;
                    case 18:
                        Message messageObtain = Message.obtain((Handler) null, 0);
                        messageObtain.what = 18;
                        Bundle bundle = new Bundle();
                        bundle.putString("xmsf_region", this.a.f845a);
                        messageObtain.setData(bundle);
                        message.replyTo.send(messageObtain);
                        break;
                }
            } catch (Throwable unused) {
            }
        }
    }
}
