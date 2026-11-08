package com.ss.android.pushmanager.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import com.bytedance.common.push.utility.a;
import com.bytedance.common.push.utility.a.c;
import com.ss.android.message.d;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class MessageLogClient implements c.a {
    private static final String LOG_SERVICE = "com.ss.android.message.log.LogService";
    private static MessageLogClient sMessageLogClient;
    private WeakReference<Context> mContextRef;
    private Handler mHandler;
    private Messenger mMessenger;
    private Messenger mService = null;
    private long mStartId = 0;
    private ServiceConnection mConnection = new ServiceConnection() { // from class: com.ss.android.pushmanager.client.MessageLogClient.1
        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            MessageLogClient.this.mService = null;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MessageLogClient.this.mService = new Messenger(iBinder);
            MessageLogClient.this.getMessageLog();
        }
    };

    public static void start(Context context) {
        if (context == null) {
            return;
        }
        if (sMessageLogClient == null) {
            sMessageLogClient = new MessageLogClient(context);
        } else if (sMessageLogClient.mService == null) {
            sMessageLogClient.doBindService();
        }
    }

    public static void end(Context context) {
        if (context == null || sMessageLogClient == null) {
            return;
        }
        sMessageLogClient.doUnbindService();
    }

    private MessageLogClient(Context context) {
        this.mContextRef = new WeakReference<>(context.getApplicationContext());
        Looper looperB = d.a().b();
        this.mHandler = new c(looperB == null ? Looper.getMainLooper() : looperB, this);
        this.mMessenger = new Messenger(this.mHandler);
        doBindService();
    }

    private void doBindService() {
        if (this.mContextRef == null || this.mContextRef.get() == null) {
            return;
        }
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(this.mContextRef.get(), LOG_SERVICE));
            this.mContextRef.get().bindService(intent, this.mConnection, 1);
        } catch (Throwable unused) {
        }
    }

    private void doUnbindService() {
        try {
            if (this.mService == null || this.mContextRef.get() == null) {
                return;
            }
            this.mContextRef.get().unbindService(this.mConnection);
        } catch (Throwable unused) {
        }
    }

    private void hanldeEnd() {
        if (a.b()) {
            a.b("PushLog", "hanldeEnd");
        }
        if (this.mService != null) {
            try {
                doUnbindService();
                this.mService = null;
            } catch (Exception unused) {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00da A[Catch: Throwable -> 0x0154, TryCatch #0 {Throwable -> 0x0154, blocks: (B:3:0x0004, B:5:0x000a, B:6:0x0022, B:11:0x002b, B:13:0x0031, B:14:0x0038, B:15:0x003d, B:17:0x0043, B:19:0x004b, B:21:0x0061, B:22:0x007f, B:24:0x0085, B:26:0x0096, B:42:0x0139, B:29:0x00a2, B:31:0x00cd, B:34:0x00d4, B:36:0x00da, B:38:0x0111, B:40:0x0130, B:41:0x0132, B:43:0x0141, B:45:0x0147, B:47:0x014c, B:48:0x0150), top: B:54:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0130 A[Catch: Throwable -> 0x0154, TryCatch #0 {Throwable -> 0x0154, blocks: (B:3:0x0004, B:5:0x000a, B:6:0x0022, B:11:0x002b, B:13:0x0031, B:14:0x0038, B:15:0x003d, B:17:0x0043, B:19:0x004b, B:21:0x0061, B:22:0x007f, B:24:0x0085, B:26:0x0096, B:42:0x0139, B:29:0x00a2, B:31:0x00cd, B:34:0x00d4, B:36:0x00da, B:38:0x0111, B:40:0x0130, B:41:0x0132, B:43:0x0141, B:45:0x0147, B:47:0x014c, B:48:0x0150), top: B:54:0x0004 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void handleMsg(android.os.Message r21) {
        /*
            Method dump skipped, instructions count: 345
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.pushmanager.client.MessageLogClient.handleMsg(android.os.Message):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getMessageLog() {
        try {
            Message messageObtain = Message.obtain((Handler) null, 0);
            messageObtain.replyTo = this.mMessenger;
            Bundle bundle = new Bundle();
            bundle.putLong("start_id", this.mStartId);
            if (a.b()) {
                a.b("PushLog", "start_id = " + this.mStartId);
            }
            messageObtain.obj = bundle;
            this.mService.send(messageObtain);
        } catch (Throwable th) {
            th.printStackTrace();
            hanldeEnd();
        }
    }

    private void sendHanledMsg(List<Long> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        try {
            Message messageObtain = Message.obtain((Handler) null, 1);
            messageObtain.replyTo = this.mMessenger;
            Bundle bundle = new Bundle();
            bundle.putLongArray("event_ids", com.bytedance.common.push.utility.a.a.a(list));
            if (a.b()) {
                a.b("PushLog", "event_ids = " + list);
            }
            messageObtain.obj = bundle;
            this.mService.send(messageObtain);
        } catch (Throwable th) {
            th.printStackTrace();
            hanldeEnd();
        }
    }
}
