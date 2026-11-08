package com.ss.android.message.log;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import com.bytedance.common.push.utility.a.c;
import com.ss.android.message.d;
import com.ss.android.pushmanager.a.a;
import org.json.JSONArray;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LogService extends Service implements c.a {
    private Messenger a;

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        com.bytedance.common.push.utility.a.b("PushLog", "onUnBind " + this);
        return super.onUnbind(intent);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        com.bytedance.common.push.utility.a.b("PushLog", "onBind " + this);
        return this.a.getBinder();
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        com.bytedance.common.push.utility.a.b("PushLog", "onCreate " + this);
        try {
            a.InterfaceC0036a interfaceC0036aA = com.ss.android.pushmanager.a.a.a();
            if (interfaceC0036aA != null) {
                interfaceC0036aA.a(this);
            }
        } catch (Throwable unused) {
        }
        this.a = new Messenger((Handler) new com.bytedance.common.push.utility.a.c(d.a().b(), this));
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        int iOnStartCommand = super.onStartCommand(intent, i, i2);
        a.b bVarB = com.ss.android.pushmanager.a.a.b();
        if (bVarB == null || !bVarB.g()) {
            return iOnStartCommand;
        }
        return 2;
    }

    public void handleMsg(Message message) {
        a aVarA;
        try {
            try {
                aVarA = a.a(this);
            } catch (Exception e) {
                e.printStackTrace();
                aVarA = null;
            }
            com.bytedance.common.push.utility.a.b("PushLog", "handleMsg msg.what = " + message.what);
            switch (message.what) {
                case 0:
                    if (message.replyTo != null) {
                        Messenger messenger = message.replyTo;
                        long j = ((Bundle) message.obj).getLong("start_id", 0L);
                        if (aVarA == null) {
                            messenger.send(Message.obtain((Handler) null, 2));
                            break;
                        } else {
                            JSONArray jSONArrayA = aVarA.a(j, 5);
                            if (jSONArrayA == null) {
                                messenger.send(Message.obtain((Handler) null, 2));
                                break;
                            } else {
                                Message messageObtain = Message.obtain((Handler) null, 0);
                                Bundle bundle = new Bundle();
                                bundle.putString("events", jSONArrayA.toString());
                                if (jSONArrayA.length() >= 5) {
                                    bundle.putBoolean("has_more", true);
                                } else {
                                    bundle.putBoolean("has_more", false);
                                }
                                com.bytedance.common.push.utility.a.b("PushLog", "events = " + bundle.get("events") + " has_more = " + bundle.get("has_more"));
                                messageObtain.obj = bundle;
                                messenger.send(messageObtain);
                                break;
                            }
                        }
                    }
                    break;
                case 1:
                    if (message.replyTo != null) {
                        Messenger messenger2 = message.replyTo;
                        long[] longArray = ((Bundle) message.obj).getLongArray("event_ids");
                        if (longArray != null) {
                            com.bytedance.common.push.utility.a.b("PushLog", "event_ids = " + com.bytedance.common.push.utility.a.a.a(longArray));
                            if (aVarA != null) {
                                for (long j2 : longArray) {
                                    aVarA.a(j2);
                                }
                                break;
                            } else {
                                messenger2.send(Message.obtain((Handler) null, 2));
                                break;
                            }
                        }
                    }
                    break;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
