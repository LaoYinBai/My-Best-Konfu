package com.xiaomi.push.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.umeng.message.entity.UMessage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ak {
    private static boolean a = Log.isLoggable("NCHelper", 3);

    @TargetApi(26)
    private static NotificationChannel a(String str, NotificationChannel notificationChannel) {
        NotificationChannel notificationChannel2 = new NotificationChannel(str, notificationChannel.getName(), notificationChannel.getImportance());
        notificationChannel2.setDescription(notificationChannel.getDescription());
        notificationChannel2.enableVibration(notificationChannel.shouldVibrate());
        notificationChannel2.enableLights(notificationChannel.shouldShowLights());
        notificationChannel2.setSound(notificationChannel.getSound(), notificationChannel.getAudioAttributes());
        notificationChannel2.setLockscreenVisibility(notificationChannel.getLockscreenVisibility());
        return notificationChannel2;
    }

    private static SharedPreferences a(Context context) {
        return context.getSharedPreferences("mipush_channel_copy_sp", 0);
    }

    @TargetApi(26)
    public static String a(ao aoVar) {
        String strB = aoVar.b();
        try {
            NotificationChannel notificationChannel = new NotificationChannel(strB, com.xiaomi.push.g.m348b(aoVar.m596a(), aoVar.m597a()), 3);
            if (a) {
                a("create default channel:" + notificationChannel);
            }
            a(aoVar, notificationChannel);
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.m23a("NCHelper", "create default channel error " + e);
        }
        return strB;
    }

    @TargetApi(26)
    public static String a(ao aoVar, String str, CharSequence charSequence, String str2, int i, int i2, String str3) {
        String strM598a = aoVar.m598a(str);
        try {
            if (a) {
                a("createChannel: appChannelId:" + strM598a + " serverChannelId:" + str + " serverChannelName:" + ((Object) charSequence) + " serverChannelDesc:" + str2 + " serverChannelNotifyType:" + i + " serverChannelName:" + ((Object) charSequence) + " serverChannelImportance:" + i2 + " channelSoundStr:" + str3);
            }
            NotificationChannel notificationChannel = new NotificationChannel(strM598a, charSequence, i2);
            notificationChannel.setDescription(str2);
            notificationChannel.enableVibration((i & 2) != 0);
            notificationChannel.enableLights((i & 4) != 0);
            if ((i & 1) == 0) {
                notificationChannel.setSound(null, null);
            } else if (!TextUtils.isEmpty(str3)) {
                if (str3.startsWith("android.resource://" + aoVar.m597a())) {
                    notificationChannel.setSound(Uri.parse(str3), Notification.AUDIO_ATTRIBUTES_DEFAULT);
                }
            }
            if (a) {
                a("create channel:" + notificationChannel);
            }
            a(aoVar, notificationChannel);
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.m23a("NCHelper", "create channel error " + e);
        }
        return strM598a;
    }

    public static void a(Context context, String str) {
        if (!com.xiaomi.push.l.m548a(context) || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            ao aoVarA = ao.a(context, str);
            Set<String> setKeySet = a(context).getAll().keySet();
            ArrayList arrayList = new ArrayList();
            for (String str2 : setKeySet) {
                if (aoVarA.m600a(str2)) {
                    arrayList.add(str2);
                    if (a) {
                        a("delete channel copy record:" + str2);
                    }
                }
            }
            a(context, arrayList);
        } catch (Exception unused) {
        }
    }

    private static void a(Context context, List<String> list) {
        if (a) {
            a("deleteCopiedChannelRecord:" + list);
        }
        if (list.isEmpty()) {
            return;
        }
        SharedPreferences.Editor editorEdit = a(context).edit();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            editorEdit.remove(it.next());
        }
        editorEdit.apply();
    }

    @TargetApi(26)
    private static void a(ao aoVar, NotificationChannel notificationChannel) throws Exception {
        if (aoVar == null || notificationChannel == null) {
            throw new Exception("params cannot be null");
        }
        if (TextUtils.isEmpty(notificationChannel.getId())) {
            throw new Exception("serverChannel id cannot be empty");
        }
        Context contextM596a = aoVar.m596a();
        String id = notificationChannel.getId();
        String strA = ao.a(id, aoVar.m597a());
        if (a) {
            a("appChannelId:" + id + " oldChannelId:" + strA);
        }
        if (!com.xiaomi.push.l.m548a(contextM596a) || TextUtils.equals(id, strA)) {
            NotificationChannel notificationChannelM595a = aoVar.m595a(id);
            if (a) {
                a("elseLogic getNotificationChannel:" + notificationChannelM595a);
            }
            if (notificationChannelM595a != null) {
                return;
            }
        } else {
            NotificationManager notificationManager = (NotificationManager) contextM596a.getSystemService(UMessage.DISPLAY_TYPE_NOTIFICATION);
            NotificationChannel notificationChannel2 = notificationManager.getNotificationChannel(strA);
            NotificationChannel notificationChannelM595a2 = aoVar.m595a(id);
            if (a) {
                a("xmsfChannel:" + notificationChannel2);
                a("appChannel:" + notificationChannelM595a2);
            }
            if (notificationChannel2 != null) {
                NotificationChannel notificationChannelA = a(id, notificationChannel2);
                if (a) {
                    a("copyXmsf copyXmsfChannel:" + notificationChannelA);
                }
                if (notificationChannelM595a2 != null) {
                    aoVar.a(notificationChannelA, !a(notificationChannelM595a2));
                } else {
                    aoVar.a(notificationChannelA);
                }
                b(contextM596a, id);
                notificationManager.deleteNotificationChannel(strA);
                return;
            }
            if (notificationChannelM595a2 != null) {
                if (m588a(contextM596a, id) || !a(notificationChannel, notificationChannelM595a2)) {
                    return;
                }
                if (a) {
                    a("appHack updateNotificationChannel:" + notificationChannel);
                }
                aoVar.a(notificationChannel, !a(notificationChannelM595a2));
                return;
            }
            if (a) {
                a("appHack createNotificationChannel:" + notificationChannel);
            }
        }
        aoVar.a(notificationChannel);
    }

    private static void a(String str) {
        com.xiaomi.channel.commonutils.logger.b.m23a("NCHelper", str);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x004c A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean a(android.app.NotificationChannel r5) {
        /*
            r0 = 0
            java.lang.String r1 = "getUserLockedFields"
            java.lang.Object[] r2 = new java.lang.Object[r0]     // Catch: java.lang.Exception -> L32
            java.lang.Object r1 = com.xiaomi.push.bh.b(r5, r1, r2)     // Catch: java.lang.Exception -> L32
            java.lang.Integer r1 = (java.lang.Integer) r1     // Catch: java.lang.Exception -> L32
            int r1 = r1.intValue()     // Catch: java.lang.Exception -> L32
            boolean r2 = com.xiaomi.push.service.ak.a     // Catch: java.lang.Exception -> L30
            if (r2 == 0) goto L4a
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L30
            r2.<init>()     // Catch: java.lang.Exception -> L30
            java.lang.String r3 = "isUserLockedChannel:"
            r2.append(r3)     // Catch: java.lang.Exception -> L30
            r2.append(r1)     // Catch: java.lang.Exception -> L30
            java.lang.String r3 = " "
            r2.append(r3)     // Catch: java.lang.Exception -> L30
            r2.append(r5)     // Catch: java.lang.Exception -> L30
            java.lang.String r5 = r2.toString()     // Catch: java.lang.Exception -> L30
            a(r5)     // Catch: java.lang.Exception -> L30
            goto L4a
        L30:
            r5 = move-exception
            goto L34
        L32:
            r5 = move-exception
            r1 = 0
        L34:
            java.lang.String r2 = "NCHelper"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "is user locked error"
            r3.append(r4)
            r3.append(r5)
            java.lang.String r5 = r3.toString()
            com.xiaomi.channel.commonutils.logger.b.m23a(r2, r5)
        L4a:
            if (r1 <= 0) goto L4d
            r0 = 1
        L4d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.ak.a(android.app.NotificationChannel):boolean");
    }

    @TargetApi(26)
    private static boolean a(NotificationChannel notificationChannel, NotificationChannel notificationChannel2) {
        boolean z;
        if (notificationChannel == null || notificationChannel2 == null) {
            return false;
        }
        if (TextUtils.equals(notificationChannel.getName(), notificationChannel2.getName())) {
            z = false;
        } else {
            if (a) {
                a("appHack channelConfigLowerCompare:getName");
            }
            z = true;
        }
        if (!TextUtils.equals(notificationChannel.getDescription(), notificationChannel2.getDescription())) {
            if (a) {
                a("appHack channelConfigLowerCompare:getDescription");
            }
            z = true;
        }
        if (notificationChannel.getImportance() != notificationChannel2.getImportance()) {
            notificationChannel.setImportance(Math.min(notificationChannel.getImportance(), notificationChannel2.getImportance()));
            if (a) {
                a("appHack channelConfigLowerCompare:getImportance  " + notificationChannel.getImportance() + " " + notificationChannel2.getImportance());
            }
            z = true;
        }
        if (notificationChannel.shouldVibrate() != notificationChannel2.shouldVibrate()) {
            notificationChannel.enableVibration(false);
            if (a) {
                a("appHack channelConfigLowerCompare:enableVibration");
            }
            z = true;
        }
        if (notificationChannel.shouldShowLights() != notificationChannel2.shouldShowLights()) {
            notificationChannel.enableLights(false);
            if (a) {
                a("appHack channelConfigLowerCompare:enableLights");
            }
            z = true;
        }
        if ((notificationChannel.getSound() != null) != (notificationChannel2.getSound() != null)) {
            notificationChannel.setSound(null, null);
            if (a) {
                a("appHack channelConfigLowerCompare:setSound");
            }
            z = true;
        }
        if (a) {
            a("appHack channelConfigLowerCompare:isDifferent:" + z);
        }
        return z;
    }

    /* renamed from: a, reason: collision with other method in class */
    private static boolean m588a(Context context, String str) {
        if (a) {
            a("checkCopeidChannel:newFullChannelId:" + str + "  " + a(context).getBoolean(str, false));
        }
        return a(context).getBoolean(str, false);
    }

    private static void b(Context context, String str) {
        if (a) {
            a("recordCopiedChannel:" + str);
        }
        a(context).edit().putBoolean(str, true).apply();
    }
}
