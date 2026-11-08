package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.ss.android.downloadlib.applink.AppLinkMonitor;
import com.xiaomi.mipush.sdk.b;
import com.xiaomi.push.bm;
import com.xiaomi.push.fb;
import com.xiaomi.push.g;
import com.xiaomi.push.hj;
import com.xiaomi.push.ht;
import com.xiaomi.push.hw;
import com.xiaomi.push.hx;
import com.xiaomi.push.hz;
import com.xiaomi.push.ii;
import com.xiaomi.push.ij;
import com.xiaomi.push.ik;
import com.xiaomi.push.ip;
import com.xiaomi.push.iq;
import com.xiaomi.push.it;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class MiPushClient4Hybrid {
    private static MiPushCallback sCallback;
    private static Map<String, b.a> dataMap = new HashMap();
    private static Map<String, Long> sRegisterTimeMap = new HashMap();

    public static class MiPushCallback {
        public void onCommandResult(String str, MiPushCommandMessage miPushCommandMessage) {
        }

        public void onReceiveRegisterResult(String str, MiPushCommandMessage miPushCommandMessage) {
        }

        public void onReceiveUnregisterResult(String str, MiPushCommandMessage miPushCommandMessage) {
        }
    }

    private static void addPullNotificationTime(Context context, String str) {
        context.getSharedPreferences("mipush_extra", 0).edit().putLong("last_pull_notification_" + str, System.currentTimeMillis()).commit();
    }

    private static short getDeviceStatus(MiPushMessage miPushMessage, boolean z) {
        String str = miPushMessage.getExtra() == null ? "" : miPushMessage.getExtra().get(Constants.EXTRA_KEY_HYBRID_DEVICE_STATUS);
        int iIntValue = TextUtils.isEmpty(str) ? 0 : Integer.valueOf(str).intValue();
        if (!z) {
            iIntValue = (iIntValue & (-4)) + g.a.NOT_ALLOWED.a();
        }
        return (short) iIntValue;
    }

    public static boolean isRegistered(Context context, String str) {
        return b.m63a(context).a(str) != null;
    }

    public static void onReceiveRegisterResult(Context context, ik ikVar) {
        b.a aVar;
        String strB = ikVar.b();
        if (ikVar.a() == 0 && (aVar = dataMap.get(strB)) != null) {
            aVar.a(ikVar.f704e, ikVar.f705f);
            b.m63a(context).a(strB, aVar);
        }
        ArrayList arrayList = null;
        if (!TextUtils.isEmpty(ikVar.f704e)) {
            arrayList = new ArrayList();
            arrayList.add(ikVar.f704e);
        }
        MiPushCommandMessage miPushCommandMessageGenerateCommandMessage = PushMessageHelper.generateCommandMessage(fb.COMMAND_REGISTER.f347a, arrayList, ikVar.f694a, ikVar.f703d, null);
        if (sCallback != null) {
            sCallback.onReceiveRegisterResult(strB, miPushCommandMessageGenerateCommandMessage);
        }
    }

    public static void onReceiveUnregisterResult(Context context, iq iqVar) {
        MiPushCommandMessage miPushCommandMessageGenerateCommandMessage = PushMessageHelper.generateCommandMessage(fb.COMMAND_UNREGISTER.f347a, null, iqVar.f770a, iqVar.f778d, null);
        String strA = iqVar.a();
        if (sCallback != null) {
            sCallback.onReceiveUnregisterResult(strA, miPushCommandMessageGenerateCommandMessage);
        }
    }

    public static void registerPush(Context context, String str, String str2, String str3) {
        if (b.m63a(context).m68a(str2, str3, str)) {
            ArrayList arrayList = new ArrayList();
            b.a aVarA = b.m63a(context).a(str);
            if (aVarA != null) {
                arrayList.add(aVarA.c);
                MiPushCommandMessage miPushCommandMessageGenerateCommandMessage = PushMessageHelper.generateCommandMessage(fb.COMMAND_REGISTER.f347a, arrayList, 0L, null, null);
                if (sCallback != null) {
                    sCallback.onReceiveRegisterResult(str, miPushCommandMessageGenerateCommandMessage);
                }
            }
            if (shouldPullNotification(context, str)) {
                ii iiVar = new ii();
                iiVar.b(str2);
                iiVar.c(ht.PullOfflineMessage.f509a);
                iiVar.a(com.xiaomi.push.service.at.a());
                iiVar.a(false);
                an.a(context).a(iiVar, hj.Notification, false, true, null, false, str, str2);
                com.xiaomi.channel.commonutils.logger.b.b("MiPushClient4Hybrid pull offline pass through message");
                addPullNotificationTime(context, str);
                return;
            }
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (Math.abs(jCurrentTimeMillis - (sRegisterTimeMap.get(str) != null ? sRegisterTimeMap.get(str).longValue() : 0L)) < AppLinkMonitor.DELAY_TIME) {
            com.xiaomi.channel.commonutils.logger.b.m22a("MiPushClient4Hybrid  Could not send register message within 5s repeatedly.");
            return;
        }
        sRegisterTimeMap.put(str, Long.valueOf(jCurrentTimeMillis));
        String strA = bm.a(6);
        b.a aVar = new b.a(context);
        aVar.c(str2, str3, strA);
        dataMap.put(str, aVar);
        ij ijVar = new ij();
        ijVar.a(com.xiaomi.push.service.at.a());
        ijVar.b(str2);
        ijVar.e(str3);
        ijVar.d(str);
        ijVar.f(strA);
        ijVar.c(com.xiaomi.push.g.m345a(context, context.getPackageName()));
        ijVar.b(com.xiaomi.push.g.a(context, context.getPackageName()));
        ijVar.h("3_8_2");
        ijVar.a(30802);
        ijVar.a(hx.Init);
        if (!com.xiaomi.push.l.d()) {
            String strG = com.xiaomi.push.i.g(context);
            if (!TextUtils.isEmpty(strG)) {
                ijVar.i(bm.a(strG));
            }
        }
        int iA = com.xiaomi.push.i.a();
        if (iA >= 0) {
            ijVar.c(iA);
        }
        ii iiVar2 = new ii();
        iiVar2.c(ht.HybridRegister.f509a);
        iiVar2.b(b.m63a(context).m64a());
        iiVar2.d(context.getPackageName());
        iiVar2.a(it.a(ijVar));
        iiVar2.a(com.xiaomi.push.service.at.a());
        an.a(context).a((an) iiVar2, hj.Notification, (hw) null);
    }

    public static void removeDuplicateCache(Context context, MiPushMessage miPushMessage) {
        String messageId = miPushMessage.getExtra() != null ? miPushMessage.getExtra().get("jobkey") : null;
        if (TextUtils.isEmpty(messageId)) {
            messageId = miPushMessage.getMessageId();
        }
        al.a(context, messageId);
    }

    public static void reportMessageArrived(Context context, MiPushMessage miPushMessage, boolean z) {
        if (miPushMessage != null) {
            try {
                if (miPushMessage.getExtra() != null) {
                    try {
                        hz hzVar = new hz();
                        hzVar.b(b.m63a(context).m64a());
                        hzVar.a(miPushMessage.getMessageId());
                        hzVar.a(Long.valueOf(miPushMessage.getExtra().get(Constants.EXTRA_KEY_HYBRID_MESSAGE_TS)).longValue());
                        hzVar.a(getDeviceStatus(miPushMessage, z));
                        if (!TextUtils.isEmpty(miPushMessage.getTopic())) {
                            hzVar.c(miPushMessage.getTopic());
                        }
                        an.a(context).a((an) hzVar, hj.AckMessage, false, PushMessageHelper.generateMessage(miPushMessage));
                        com.xiaomi.channel.commonutils.logger.b.b("MiPushClient4Hybrid ack mina message, messageId is " + miPushMessage.getMessageId());
                    } catch (Throwable th) {
                        com.xiaomi.channel.commonutils.logger.b.a(th);
                    }
                    return;
                }
            } finally {
                miPushMessage.getExtra().remove(Constants.EXTRA_KEY_HYBRID_MESSAGE_TS);
                miPushMessage.getExtra().remove(Constants.EXTRA_KEY_HYBRID_DEVICE_STATUS);
            }
        }
        com.xiaomi.channel.commonutils.logger.b.m22a("do not ack message, message is null");
    }

    public static void reportMessageClicked(Context context, MiPushMessage miPushMessage) {
        MiPushClient.reportMessageClicked(context, miPushMessage);
    }

    public static void setCallback(MiPushCallback miPushCallback) {
        sCallback = miPushCallback;
    }

    private static boolean shouldPullNotification(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
        StringBuilder sb = new StringBuilder();
        sb.append("last_pull_notification_");
        sb.append(str);
        return Math.abs(System.currentTimeMillis() - sharedPreferences.getLong(sb.toString(), -1L)) > 300000;
    }

    public static void unregisterPush(Context context, String str) {
        sRegisterTimeMap.remove(str);
        b.a aVarA = b.m63a(context).a(str);
        if (aVarA == null) {
            return;
        }
        ip ipVar = new ip();
        ipVar.a(com.xiaomi.push.service.at.a());
        ipVar.d(str);
        ipVar.b(aVarA.f84a);
        ipVar.c(aVarA.c);
        ipVar.e(aVarA.b);
        ii iiVar = new ii();
        iiVar.c(ht.HybridUnregister.f509a);
        iiVar.b(b.m63a(context).m64a());
        iiVar.d(context.getPackageName());
        iiVar.a(it.a(ipVar));
        iiVar.a(com.xiaomi.push.service.at.a());
        an.a(context).a((an) iiVar, hj.Notification, (hw) null);
        b.m63a(context).b(str);
    }

    public static void uploadClearMessageData(Context context, LinkedList<? extends Object> linkedList) {
        com.xiaomi.push.service.ac.a(context, linkedList);
    }
}
