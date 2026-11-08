package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import com.ss.android.socialbase.appdownloader.constants.InstallErrorCode;
import com.ss.android.socialbase.downloader.constants.DownloadErrorCode;
import com.xiaomi.mipush.sdk.PushMessageHandler;
import com.xiaomi.push.es;
import com.xiaomi.push.fb;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class MessageHandleService extends BaseService {
    private static ConcurrentLinkedQueue<a> a = new ConcurrentLinkedQueue<>();

    /* renamed from: a, reason: collision with other field name */
    private static ExecutorService f46a = new ThreadPoolExecutor(1, 1, 15, TimeUnit.SECONDS, new LinkedBlockingQueue());

    public static class a {
        private Intent a;

        /* renamed from: a, reason: collision with other field name */
        private PushMessageReceiver f47a;

        public a(Intent intent, PushMessageReceiver pushMessageReceiver) {
            this.f47a = pushMessageReceiver;
            this.a = intent;
        }

        public Intent a() {
            return this.a;
        }

        /* renamed from: a, reason: collision with other method in class */
        public PushMessageReceiver m35a() {
            return this.f47a;
        }
    }

    protected static void a(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        b(context);
    }

    static void a(Context context, a aVar) {
        String[] stringArrayExtra;
        if (aVar == null) {
            return;
        }
        try {
            PushMessageReceiver pushMessageReceiverM35a = aVar.m35a();
            Intent intentA = aVar.a();
            int intExtra = intentA.getIntExtra(PushMessageHelper.MESSAGE_TYPE, 1);
            if (intExtra != 1) {
                switch (intExtra) {
                    case 3:
                        MiPushCommandMessage miPushCommandMessage = (MiPushCommandMessage) intentA.getSerializableExtra(PushMessageHelper.KEY_COMMAND);
                        com.xiaomi.channel.commonutils.logger.b.e("(Local) begin execute onCommandResult, command=" + miPushCommandMessage.getCommand() + ", resultCode=" + miPushCommandMessage.getResultCode() + ", reason=" + miPushCommandMessage.getReason());
                        pushMessageReceiverM35a.onCommandResult(context, miPushCommandMessage);
                        if (!TextUtils.equals(miPushCommandMessage.getCommand(), fb.COMMAND_REGISTER.f347a)) {
                            return;
                        }
                        pushMessageReceiverM35a.onReceiveRegisterResult(context, miPushCommandMessage);
                        PushMessageHandler.a(context, miPushCommandMessage);
                        if (miPushCommandMessage.getResultCode() != 0) {
                            return;
                        }
                        break;
                    case 4:
                        return;
                    case 5:
                        if (!PushMessageHelper.ERROR_TYPE_NEED_PERMISSION.equals(intentA.getStringExtra(PushMessageHelper.ERROR_TYPE)) || (stringArrayExtra = intentA.getStringArrayExtra(PushMessageHelper.ERROR_MESSAGE)) == null) {
                            return;
                        }
                        com.xiaomi.channel.commonutils.logger.b.e("begin execute onRequirePermissions, lack of necessary permissions");
                        pushMessageReceiverM35a.onRequirePermissions(context, stringArrayExtra);
                        return;
                    default:
                        return;
                }
            } else {
                PushMessageHandler.a aVarA = al.a(context).a(intentA);
                int intExtra2 = intentA.getIntExtra("eventMessageType", -1);
                if (aVarA == null) {
                    return;
                }
                if (aVarA instanceof MiPushMessage) {
                    MiPushMessage miPushMessage = (MiPushMessage) aVarA;
                    if (!miPushMessage.isArrivedMessage()) {
                        pushMessageReceiverM35a.onReceiveMessage(context, miPushMessage);
                    }
                    if (miPushMessage.getPassThrough() == 1) {
                        es.a(context.getApplicationContext()).a(context.getPackageName(), intentA, InstallErrorCode.ERROR_CANCELED, (String) null);
                        com.xiaomi.channel.commonutils.logger.b.e("begin execute onReceivePassThroughMessage from " + miPushMessage.getMessageId());
                        pushMessageReceiverM35a.onReceivePassThroughMessage(context, miPushMessage);
                        return;
                    }
                    if (!miPushMessage.isNotified()) {
                        com.xiaomi.channel.commonutils.logger.b.e("begin execute onNotificationMessageArrived from " + miPushMessage.getMessageId());
                        pushMessageReceiverM35a.onNotificationMessageArrived(context, miPushMessage);
                        return;
                    }
                    if (intExtra2 == 1000) {
                        es.a(context.getApplicationContext()).a(context.getPackageName(), intentA, DownloadErrorCode.ERROR_DEVICE_NOT_FOUND, (String) null);
                    } else {
                        es.a(context.getApplicationContext()).a(context.getPackageName(), intentA, 3007, (String) null);
                    }
                    com.xiaomi.channel.commonutils.logger.b.e("begin execute onNotificationMessageClicked from\u3000" + miPushMessage.getMessageId());
                    pushMessageReceiverM35a.onNotificationMessageClicked(context, miPushMessage);
                    return;
                }
                if (!(aVarA instanceof MiPushCommandMessage)) {
                    return;
                }
                MiPushCommandMessage miPushCommandMessage2 = (MiPushCommandMessage) aVarA;
                com.xiaomi.channel.commonutils.logger.b.e("begin execute onCommandResult, command=" + miPushCommandMessage2.getCommand() + ", resultCode=" + miPushCommandMessage2.getResultCode() + ", reason=" + miPushCommandMessage2.getReason());
                pushMessageReceiverM35a.onCommandResult(context, miPushCommandMessage2);
                if (!TextUtils.equals(miPushCommandMessage2.getCommand(), fb.COMMAND_REGISTER.f347a)) {
                    return;
                }
                pushMessageReceiverM35a.onReceiveRegisterResult(context, miPushCommandMessage2);
                PushMessageHandler.a(context, miPushCommandMessage2);
                if (miPushCommandMessage2.getResultCode() != 0) {
                    return;
                }
            }
            i.b(context);
        } catch (RuntimeException e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
        }
    }

    public static void addJob(Context context, a aVar) {
        if (aVar != null) {
            a.add(aVar);
            b(context);
            startService(context);
        }
    }

    private static void b(Context context) {
        if (f46a.isShutdown()) {
            return;
        }
        f46a.execute(new y(context));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(Context context) {
        try {
            a(context, a.poll());
        } catch (RuntimeException e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
        }
    }

    public static void startService(Context context) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(context, (Class<?>) MessageHandleService.class));
        com.xiaomi.push.ai.a(context).a(new x(context, intent));
    }

    @Override // com.xiaomi.mipush.sdk.BaseService
    /* renamed from: a */
    protected boolean mo41a() {
        return a != null && a.size() > 0;
    }

    @Override // com.xiaomi.mipush.sdk.BaseService, android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // com.xiaomi.mipush.sdk.BaseService, android.app.Service
    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
    }
}
