package com.ss.android.newmedia.message;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.PowerManager;
import com.bytedance.common.push.utility.a;
import com.bytedance.common.push.utility.a.c;
import com.bytedance.common.push.utility.i;
import com.ss.android.message.d;
import com.ss.android.pushmanager.MessageConstants;
import com.ss.android.pushmanager.setting.PushSetting;
import java.lang.reflect.Field;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class MessageReceiverService extends IntentService implements c.a {
    private static final String TAG = "MessageReceiverService";
    private c mHandler;
    private Messenger mMessenger;

    public void handleMsg(Message message) {
    }

    public abstract void onHandleMessage(Context context, int i, String str, int i2, String str2);

    public MessageReceiverService() {
        super(TAG);
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    @Override // android.app.IntentService, android.app.Service
    public int onStartCommand(final Intent intent, int i, final int i2) {
        try {
            return super.onStartCommand(intent, i, i2);
        } catch (NullPointerException e) {
            e.printStackTrace();
            if (getServiceHandler() != null) {
                return 2;
            }
            d.a().a(new Runnable() { // from class: com.ss.android.newmedia.message.MessageReceiverService.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        MessageReceiverService.this.onHandleIntent(intent);
                        MessageReceiverService.this.stopSelf(i2);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
            return 2;
        } catch (Throwable th) {
            th.printStackTrace();
            return 2;
        }
    }

    private Handler getServiceHandler() throws NoSuchFieldException {
        try {
            Field declaredField = IntentService.class.getDeclaredField("mServiceHandler");
            declaredField.setAccessible(true);
            return (Handler) declaredField.get(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // android.app.IntentService, android.app.Service
    public IBinder onBind(final Intent intent) {
        a.b(TAG, "onBind");
        if (intent != null) {
            d.a().a(new Runnable() { // from class: com.ss.android.newmedia.message.MessageReceiverService.2
                @Override // java.lang.Runnable
                public void run() {
                    MessageReceiverService.this.onHandleIntent(intent);
                    MessageReceiverService.this.stopSelf();
                }
            });
        }
        return getMessenger().getBinder();
    }

    private Messenger getMessenger() {
        if (this.mMessenger == null) {
            this.mHandler = new c(this);
            this.mMessenger = new Messenger((Handler) this.mHandler);
        }
        return this.mMessenger;
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        try {
            onReceive(this, intent);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    protected void onReceive(Context context, Intent intent) {
        if (context == null || intent == null) {
            return;
        }
        String action = intent.getAction();
        if (action != null) {
            a.b(TAG, "action = " + action);
        }
        if (!PushSetting.getInstance().isPushNotifyEnable()) {
            a.c(TAG, "notify enable = " + PushSetting.getInstance().isPushNotifyEnable());
            return;
        }
        try {
            if (MessageConstants.MESSAGE_ACTION.equals(action)) {
                String strHandleMySelfPushIntent = handleMySelfPushIntent(intent);
                if (i.a(strHandleMySelfPushIntent)) {
                    return;
                }
                a.b(TAG, "message received, msg is: " + strHandleMySelfPushIntent);
                onHandleMessage(context, 1, strHandleMySelfPushIntent, 2, null);
                tryWakeupScreen();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tryWakeupScreen() {
        boolean zIsScreenOn;
        PowerManager powerManager = (PowerManager) getSystemService("power");
        if (PushSetting.getInstance().isReceiverMessageWakeupScreen()) {
            if (Build.VERSION.SDK_INT >= 20) {
                zIsScreenOn = powerManager.isInteractive();
            } else {
                zIsScreenOn = powerManager.isScreenOn();
            }
            a.b(TAG, "tryWakeupScreen isScreenOn = " + zIsScreenOn);
            if (zIsScreenOn) {
                return;
            }
            powerManager.newWakeLock(268435462, TAG).acquire(PushSetting.getInstance().getReceiverMessageWakeupScreenTime());
        }
    }

    private String handleMySelfPushIntent(Intent intent) {
        if (intent != null && MessageConstants.MESSAGE_ACTION.equals(intent.getAction())) {
            return intent.getStringExtra(MessageConstants.MESSAGE_KEY_DATA);
        }
        return null;
    }
}
