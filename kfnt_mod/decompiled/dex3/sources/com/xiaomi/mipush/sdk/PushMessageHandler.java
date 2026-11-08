package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.text.TextUtils;
import com.sigmob.sdk.common.mta.PointType;
import com.xiaomi.mipush.sdk.MessageHandleService;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.push.es;
import com.xiaomi.push.fb;
import com.xiaomi.push.hn;
import com.xiaomi.push.it;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class PushMessageHandler extends BaseService {
    private static List<MiPushClient.ICallbackResult> a = new ArrayList();
    private static List<MiPushClient.MiPushClientCallback> b = new ArrayList();

    /* renamed from: a, reason: collision with other field name */
    private static ThreadPoolExecutor f58a = new ThreadPoolExecutor(1, 1, 15, TimeUnit.SECONDS, new LinkedBlockingQueue());

    interface a extends Serializable {
    }

    protected static void a() {
        synchronized (b) {
            b.clear();
        }
    }

    public static void a(long j, String str, String str2) {
        synchronized (b) {
            Iterator<MiPushClient.MiPushClientCallback> it = b.iterator();
            while (it.hasNext()) {
                it.next().onInitializeResult(j, str, str2);
            }
        }
    }

    public static void a(Context context) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(context, (Class<?>) PushMessageHandler.class));
        try {
            context.startService(intent);
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.m22a(e.getMessage());
        }
    }

    public static void a(Context context, Intent intent) {
        com.xiaomi.channel.commonutils.logger.b.c("addjob PushMessageHandler " + intent);
        if (intent != null) {
            c(context, intent);
            a(context);
        }
    }

    private static void a(Context context, Intent intent, ResolveInfo resolveInfo, boolean z) {
        try {
            MessageHandleService.a aVar = new MessageHandleService.a(intent, (PushMessageReceiver) com.xiaomi.push.t.a(context, resolveInfo.activityInfo.name).newInstance());
            if (z) {
                MessageHandleService.a(context.getApplicationContext(), aVar);
            } else {
                MessageHandleService.addJob(context.getApplicationContext(), aVar);
            }
            MessageHandleService.a(context, new Intent(context.getApplicationContext(), (Class<?>) MessageHandleService.class));
        } catch (Throwable th) {
            com.xiaomi.channel.commonutils.logger.b.a(th);
        }
    }

    protected static void a(Context context, MiPushCommandMessage miPushCommandMessage) {
        synchronized (a) {
            for (MiPushClient.ICallbackResult iCallbackResult : a) {
                if (iCallbackResult instanceof MiPushClient.UPSRegisterCallBack) {
                    MiPushClient.TokenResult tokenResult = new MiPushClient.TokenResult();
                    if (miPushCommandMessage != null && miPushCommandMessage.getCommandArguments() != null && miPushCommandMessage.getCommandArguments().size() > 0) {
                        tokenResult.setResultCode(miPushCommandMessage.getResultCode());
                        tokenResult.setToken(miPushCommandMessage.getCommandArguments().get(0));
                    }
                    iCallbackResult.onResult(tokenResult);
                }
            }
        }
    }

    public static void a(Context context, MiPushMessage miPushMessage) {
        synchronized (b) {
            for (MiPushClient.MiPushClientCallback miPushClientCallback : b) {
                if (a(miPushMessage.getCategory(), miPushClientCallback.getCategory())) {
                    miPushClientCallback.onReceiveMessage(miPushMessage.getContent(), miPushMessage.getAlias(), miPushMessage.getTopic(), miPushMessage.isNotified());
                    miPushClientCallback.onReceiveMessage(miPushMessage);
                }
            }
        }
    }

    public static void a(Context context, a aVar) {
        if (aVar instanceof MiPushMessage) {
            a(context, (MiPushMessage) aVar);
            return;
        }
        if (aVar instanceof MiPushCommandMessage) {
            MiPushCommandMessage miPushCommandMessage = (MiPushCommandMessage) aVar;
            String command = miPushCommandMessage.getCommand();
            String str = null;
            if (fb.COMMAND_REGISTER.f347a.equals(command)) {
                List<String> commandArguments = miPushCommandMessage.getCommandArguments();
                if (commandArguments != null && !commandArguments.isEmpty()) {
                    str = commandArguments.get(0);
                }
                a(miPushCommandMessage.getResultCode(), miPushCommandMessage.getReason(), str);
                return;
            }
            if (fb.COMMAND_SET_ALIAS.f347a.equals(command) || fb.COMMAND_UNSET_ALIAS.f347a.equals(command) || fb.COMMAND_SET_ACCEPT_TIME.f347a.equals(command)) {
                a(context, miPushCommandMessage.getCategory(), command, miPushCommandMessage.getResultCode(), miPushCommandMessage.getReason(), miPushCommandMessage.getCommandArguments());
                return;
            }
            if (fb.COMMAND_SUBSCRIBE_TOPIC.f347a.equals(command)) {
                List<String> commandArguments2 = miPushCommandMessage.getCommandArguments();
                if (commandArguments2 != null && !commandArguments2.isEmpty()) {
                    str = commandArguments2.get(0);
                }
                a(context, miPushCommandMessage.getCategory(), miPushCommandMessage.getResultCode(), miPushCommandMessage.getReason(), str);
                return;
            }
            if (fb.COMMAND_UNSUBSCRIBE_TOPIC.f347a.equals(command)) {
                List<String> commandArguments3 = miPushCommandMessage.getCommandArguments();
                if (commandArguments3 != null && !commandArguments3.isEmpty()) {
                    str = commandArguments3.get(0);
                }
                b(context, miPushCommandMessage.getCategory(), miPushCommandMessage.getResultCode(), miPushCommandMessage.getReason(), str);
            }
        }
    }

    protected static void a(Context context, String str, long j, String str2, String str3) {
        synchronized (b) {
            for (MiPushClient.MiPushClientCallback miPushClientCallback : b) {
                if (a(str, miPushClientCallback.getCategory())) {
                    miPushClientCallback.onSubscribeResult(j, str2, str3);
                }
            }
        }
    }

    protected static void a(Context context, String str, String str2, long j, String str3, List<String> list) {
        synchronized (b) {
            for (MiPushClient.MiPushClientCallback miPushClientCallback : b) {
                if (a(str, miPushClientCallback.getCategory())) {
                    miPushClientCallback.onCommandResult(str2, j, str3, list);
                }
            }
        }
    }

    protected static void a(MiPushClient.ICallbackResult iCallbackResult) {
        synchronized (a) {
            if (!a.contains(iCallbackResult)) {
                a.add(iCallbackResult);
            }
        }
    }

    protected static void a(MiPushClient.MiPushClientCallback miPushClientCallback) {
        synchronized (b) {
            if (!b.contains(miPushClientCallback)) {
                b.add(miPushClientCallback);
            }
        }
    }

    protected static boolean a(String str, String str2) {
        return (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) || TextUtils.equals(str, str2);
    }

    protected static void b() {
        synchronized (a) {
            a.clear();
        }
    }

    private static void b(Context context) {
        try {
            Intent intent = new Intent();
            intent.setPackage(context.getPackageName());
            intent.setAction("action_clicked_activity_finish");
            context.sendBroadcast(intent, d.a(context));
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.m22a("callback sync error" + e);
        }
    }

    protected static void b(Context context, Intent intent) {
        boolean z = false;
        if (intent != null && intent.getBooleanExtra("is_clicked_activity_call", false)) {
            z = true;
        }
        try {
            try {
                ResolveInfo resolveInfo = null;
                if ("com.xiaomi.mipush.sdk.WAKEUP".equals(intent.getAction())) {
                    o.a(context, intent, null);
                } else if ("com.xiaomi.mipush.SEND_TINYDATA".equals(intent.getAction())) {
                    hn hnVar = new hn();
                    it.a(hnVar, intent.getByteArrayExtra("mipush_payload"));
                    com.xiaomi.channel.commonutils.logger.b.c("PushMessageHandler.onHandleIntent " + hnVar.d());
                    MiTinyDataClient.upload(context, hnVar);
                } else if (1 == PushMessageHelper.getPushMode(context)) {
                    if (m40b()) {
                        com.xiaomi.channel.commonutils.logger.b.d("receive a message before application calling initialize");
                        if (z) {
                            b(context);
                            return;
                        }
                        return;
                    }
                    a aVarA = al.a(context).a(intent);
                    if (aVarA != null) {
                        a(context, aVarA);
                    }
                } else if (!"com.xiaomi.mipush.sdk.SYNC_LOG".equals(intent.getAction())) {
                    Intent intent2 = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
                    intent2.setPackage(context.getPackageName());
                    intent2.putExtras(intent);
                    try {
                        List<ResolveInfo> listQueryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent2, 32);
                        if (listQueryBroadcastReceivers != null) {
                            Iterator<ResolveInfo> it = listQueryBroadcastReceivers.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                ResolveInfo next = it.next();
                                if (next.activityInfo != null && next.activityInfo.packageName.equals(context.getPackageName()) && PushMessageReceiver.class.isAssignableFrom(com.xiaomi.push.t.a(context, next.activityInfo.name))) {
                                    resolveInfo = next;
                                    break;
                                }
                            }
                        }
                        if (resolveInfo != null) {
                            a(context, intent2, resolveInfo, z);
                        } else {
                            com.xiaomi.channel.commonutils.logger.b.d("cannot find the receiver to handler this message, check your manifest");
                            es.a(context).a(context.getPackageName(), intent, "11");
                        }
                    } catch (Exception e) {
                        com.xiaomi.channel.commonutils.logger.b.a(e);
                        es.a(context).a(context.getPackageName(), intent, "9");
                    }
                }
                if (!z) {
                    return;
                }
            } catch (Throwable th) {
                com.xiaomi.channel.commonutils.logger.b.a(th);
                es.a(context).a(context.getPackageName(), intent, PointType.SIGMOB_APP);
                if (!z) {
                    return;
                }
            }
            b(context);
        } catch (Throwable th2) {
            if (z) {
                b(context);
            }
            throw th2;
        }
    }

    protected static void b(Context context, String str, long j, String str2, String str3) {
        synchronized (b) {
            for (MiPushClient.MiPushClientCallback miPushClientCallback : b) {
                if (a(str, miPushClientCallback.getCategory())) {
                    miPushClientCallback.onUnsubscribeResult(j, str2, str3);
                }
            }
        }
    }

    /* renamed from: b, reason: collision with other method in class */
    public static boolean m40b() {
        return b.isEmpty();
    }

    private static void c(Context context, Intent intent) {
        if (intent == null || f58a.isShutdown()) {
            return;
        }
        f58a.execute(new ak(context, intent));
    }

    @Override // com.xiaomi.mipush.sdk.BaseService
    /* renamed from: a, reason: collision with other method in class */
    protected boolean mo41a() {
        return (f58a == null || f58a.getQueue() == null || f58a.getQueue().size() <= 0) ? false : true;
    }

    @Override // com.xiaomi.mipush.sdk.BaseService, android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // com.xiaomi.mipush.sdk.BaseService, android.app.Service
    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
        c(getApplicationContext(), intent);
    }
}
