package com.umeng.message;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.downloadlib.constants.DownloadConstants;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.message.common.c;
import com.umeng.message.entity.UMessage;
import com.umeng.message.entity.UNotificationItem;
import com.umeng.message.proguard.h;
import com.umeng.message.service.UMJobIntentService;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class UmengMessageHandler implements UHandler {
    public static final String DEFAULT_NOTIFICATION_CHANNEL_NAME = "Default";
    public static final String PRIMARY_CHANNEL = "upush_default";
    private static int a = 64;
    private static final String b = "com.umeng.message.UmengMessageHandler";
    private static Date c = null;
    private static String d = "9999999999999";
    private static final String g = "umeng_push_notification_default_large_icon";
    private static final String h = "umeng_push_notification_default_small_icon";
    private static final String i = "umeng_push_notification_default_sound";
    public static boolean isChannelSet;
    private UMessage e = null;
    private int f;

    public void dealWithCustomMessage(Context context, UMessage uMessage) {
    }

    public Notification getNotification(Context context, UMessage uMessage) {
        return null;
    }

    public void setPrevMessage(UMessage uMessage) {
        this.e = uMessage;
    }

    @Override // com.umeng.message.UHandler
    public void handleMessage(Context context, UMessage uMessage) {
        if (UMessage.DISPLAY_TYPE_NOTIFICATION.equals(uMessage.display_type)) {
            dealWithNotificationMessage(context, uMessage);
            return;
        }
        if ("custom".equals(uMessage.display_type)) {
            UTrack.getInstance(context).setClearPrevMessage(false);
            if (!TextUtils.isEmpty(uMessage.recall)) {
                a(context, uMessage);
            } else {
                dealWithCustomMessage(context, uMessage);
            }
        }
    }

    public void dealWithNotificationMessage(Context context, UMessage uMessage) {
        String strSubstring;
        Notification.Builder builder;
        UMLog uMLog = UMConfigure.umDebugLog;
        UMLog.mutlInfo(b, 2, "notify: " + uMessage.getRaw().toString());
        if (uMessage.hasResourceFromInternet() && !MessageSharedPrefs.getInstance(context).hasMessageResourceDownloaded(uMessage.msg_id) && startDownloadResourceService(context, uMessage)) {
            return;
        }
        String lastMessageMsgID = MessageSharedPrefs.getInstance(context).getLastMessageMsgID();
        String strSubstring2 = "".equals(lastMessageMsgID) ? "" : lastMessageMsgID.substring(7, 20);
        if (uMessage.msg_id != null && 22 == uMessage.msg_id.length() && uMessage.msg_id.startsWith("u")) {
            MessageSharedPrefs.getInstance(context).setLastMessageMsgID(uMessage.msg_id);
            strSubstring = uMessage.msg_id.substring(7, 20);
        } else {
            strSubstring = d;
        }
        boolean z = "".equals(strSubstring2) || strSubstring.compareToIgnoreCase(strSubstring2) >= 0;
        MessageSharedPrefs.getInstance(context).removeMessageResouceRecord(uMessage.msg_id);
        Notification notification = getNotification(context, uMessage);
        if (notification != null) {
            int i2 = notification.icon;
        }
        if (notification == null) {
            if (Build.VERSION.SDK_INT >= 26) {
                if (!isChannelSet) {
                    isChannelSet = true;
                    NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL, a(context), 3);
                    NotificationManager notificationManager = (NotificationManager) context.getSystemService(UMessage.DISPLAY_TYPE_NOTIFICATION);
                    if (notificationManager != null) {
                        notificationManager.createNotificationChannel(notificationChannel);
                    }
                }
                builder = new Notification.Builder(context, PRIMARY_CHANNEL);
            } else {
                builder = new Notification.Builder(context);
            }
            if (!TextUtils.isEmpty(uMessage.expand_image) && Build.VERSION.SDK_INT >= 16) {
                builder.setStyle(new Notification.BigPictureStyle().bigPicture(getExpandImage(context, uMessage)));
            }
            if (!TextUtils.isEmpty(uMessage.bar_image)) {
                int smallIconId = getSmallIconId(context, uMessage);
                if (smallIconId < 0) {
                    return;
                }
                RemoteViews remoteViews = new RemoteViews(context.getPackageName(), c.a(context).e("upush_bar_image_notification"));
                remoteViews.setImageViewBitmap(c.a(context).b("notification_bar_image"), getBarImage(context, uMessage));
                builder.setContent(remoteViews).setSmallIcon(smallIconId).setTicker(uMessage.ticker).setAutoCancel(true);
            } else if (!a(context, builder, uMessage)) {
                return;
            } else {
                builder.setContentTitle(uMessage.title).setContentText(uMessage.text).setTicker(uMessage.ticker).setAutoCancel(true);
            }
            notification = builder.getNotification();
        }
        this.f = new Random(System.nanoTime()).nextInt();
        PendingIntent clickPendingIntent = getClickPendingIntent(context, uMessage);
        notification.deleteIntent = getDismissPendingIntent(context, uMessage);
        notification.contentIntent = clickPendingIntent;
        int notificationDefaults = getNotificationDefaults(context, uMessage);
        if ((notificationDefaults & 1) != 0) {
            Uri sound = getSound(context, uMessage);
            if (sound != null) {
                notification.sound = getSound(context, uMessage);
            }
            if (sound != null) {
                notificationDefaults ^= 1;
            }
        }
        notification.defaults = notificationDefaults;
        a(context, notification, z, uMessage);
    }

    private String a(Context context) {
        String notificationChannelName = PushAgent.getInstance(context).getNotificationChannelName();
        return TextUtils.isEmpty(notificationChannelName) ? DEFAULT_NOTIFICATION_CHANNEL_NAME : notificationChannelName;
    }

    private void a(Context context, Notification notification, boolean z, UMessage uMessage) {
        try {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(UMessage.DISPLAY_TYPE_NOTIFICATION);
            int i2 = this.f;
            if ((h.c(context) && h.b(context) && !PushAgent.getInstance(context).getNotificationOnForeground()) || (MessageSharedPrefs.getInstance(context).getDisplayNotificationNumber() == 1 && !z)) {
                UTrack.getInstance(context).setClearPrevMessage(false);
                UTrack.getInstance(context).trackMsgDismissed(uMessage);
                return;
            }
            if (MessageSharedPrefs.getInstance(context).getDisplayNotificationNumber() > 0) {
                while (MessageNotificationQueue.getInstance().size() >= MessageSharedPrefs.getInstance(context).getDisplayNotificationNumber()) {
                    UNotificationItem uNotificationItemPollFirst = MessageNotificationQueue.getInstance().pollFirst();
                    notificationManager.cancel(uNotificationItemPollFirst.id);
                    UTrack.getInstance(context).setClearPrevMessage(false);
                    UTrack.getInstance(context).trackMsgDismissed(uNotificationItemPollFirst.message);
                }
                MessageNotificationQueue.getInstance().addLast(new UNotificationItem(i2, uMessage));
            } else {
                MessageNotificationQueue.getInstance().addLast(new UNotificationItem(i2, uMessage));
            }
            notificationManager.notify(i2, notification);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Type inference failed for: r0v8, types: [com.umeng.message.UmengMessageHandler$1] */
    @SuppressLint({"NewApi", "Wakelock"})
    private void b(Context context) {
        boolean zA;
        try {
            final PowerManager powerManager = (PowerManager) context.getSystemService("power");
            if (Build.VERSION.SDK_INT >= 7) {
                zA = new Object() { // from class: com.umeng.message.UmengMessageHandler.1
                    boolean a() {
                        return powerManager.isScreenOn();
                    }
                }.a();
            } else {
                UMLog uMLog = UMConfigure.umDebugLog;
                UMLog.mutlInfo(b, 2, "android os version < 7, skip checking screen on status");
                zA = false;
            }
            UMLog uMLog2 = UMConfigure.umDebugLog;
            UMLog.mutlInfo(b, 2, "屏幕开启状态：" + zA);
            if (zA) {
                return;
            }
            powerManager.newWakeLock(805306374, "MyLock").acquire(DownloadConstants.DEFAULT_NEXT_INSTALL_MIN_INTERVAL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PendingIntent getClickPendingIntent(Context context, UMessage uMessage) {
        Intent intent = new Intent();
        intent.setClass(context, NotificationProxyBroadcastReceiver.class);
        intent.putExtra(NotificationProxyBroadcastReceiver.EXTRA_KEY_MSG, uMessage.getRaw().toString());
        intent.putExtra(NotificationProxyBroadcastReceiver.EXTRA_KEY_ACTION, 10);
        intent.putExtra(NotificationProxyBroadcastReceiver.EXTRA_KEY_MESSAGE_ID, uMessage.message_id);
        intent.putExtra(NotificationProxyBroadcastReceiver.EXTRA_KEY_NOTIFICATION_ID, this.f);
        intent.putExtra(NotificationProxyBroadcastReceiver.EXTRA_KEY_TASK_ID, uMessage.task_id);
        return PendingIntent.getBroadcast(context, (int) System.currentTimeMillis(), intent, DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
    }

    public PendingIntent getDismissPendingIntent(Context context, UMessage uMessage) {
        Intent intent = new Intent();
        intent.setClass(context, NotificationProxyBroadcastReceiver.class);
        intent.putExtra(NotificationProxyBroadcastReceiver.EXTRA_KEY_MSG, uMessage.getRaw().toString());
        intent.putExtra(NotificationProxyBroadcastReceiver.EXTRA_KEY_ACTION, 11);
        intent.putExtra(NotificationProxyBroadcastReceiver.EXTRA_KEY_MESSAGE_ID, uMessage.message_id);
        intent.putExtra(NotificationProxyBroadcastReceiver.EXTRA_KEY_NOTIFICATION_ID, this.f);
        intent.putExtra(NotificationProxyBroadcastReceiver.EXTRA_KEY_TASK_ID, uMessage.task_id);
        return PendingIntent.getBroadcast(context, (int) (System.currentTimeMillis() + 1), intent, DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
    }

    public boolean isInNoDisturbTime(Context context) {
        int i2 = (Calendar.getInstance().get(11) * 60) + Calendar.getInstance().get(12);
        boolean z = i2 >= (PushAgent.getInstance(context).getNoDisturbStartHour() * 60) + PushAgent.getInstance(context).getNoDisturbStartMinute();
        boolean z2 = i2 <= (PushAgent.getInstance(context).getNoDisturbEndHour() * 60) + PushAgent.getInstance(context).getNoDisturbEndMinute();
        if ((PushAgent.getInstance(context).getNoDisturbEndHour() * 60) + PushAgent.getInstance(context).getNoDisturbEndMinute() >= (PushAgent.getInstance(context).getNoDisturbStartHour() * 60) + PushAgent.getInstance(context).getNoDisturbStartMinute()) {
            if (!z || !z2) {
                return false;
            }
        } else if (!z && !z2) {
            return false;
        }
        return true;
    }

    public int getNotificationDefaults(Context context, UMessage uMessage) {
        long muteDuration = MessageSharedPrefs.getInstance(context).getMuteDuration() * 1 * 1000;
        int i2 = 0;
        if (!isInNoDisturbTime(context) && (c == null || Calendar.getInstance().getTimeInMillis() - c.getTime() >= muteDuration)) {
            int notificationPlayVibrate = MessageSharedPrefs.getInstance(context).getNotificationPlayVibrate();
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(b, 2, "playVibrate:" + notificationPlayVibrate);
            int i3 = (notificationPlayVibrate != 1 && (notificationPlayVibrate == 2 || !uMessage.play_vibrate)) ? 0 : 2;
            int notificationPlayLights = MessageSharedPrefs.getInstance(context).getNotificationPlayLights();
            UMLog uMLog2 = UMConfigure.umDebugLog;
            UMLog.mutlInfo(b, 2, "playLights:" + notificationPlayLights);
            if (notificationPlayLights == 1) {
                i3 |= 4;
            } else if (notificationPlayLights != 2 && uMessage.play_lights) {
                i3 |= 4;
            }
            int notificationPlaySound = MessageSharedPrefs.getInstance(context).getNotificationPlaySound();
            UMLog uMLog3 = UMConfigure.umDebugLog;
            UMLog.mutlInfo(b, 2, "playSound:" + notificationPlaySound);
            if (notificationPlaySound == 1) {
                i3 |= 1;
            } else if (notificationPlaySound != 2 && uMessage.play_sound) {
                i3 |= 1;
            }
            i2 = i3;
            c = Calendar.getInstance().getTime();
            if (uMessage.screen_on) {
                b(context);
            }
        }
        return i2;
    }

    public boolean startDownloadResourceService(Context context, UMessage uMessage) {
        try {
            Intent intent = new Intent(context, (Class<?>) UmengDownloadResourceService.class);
            intent.putExtra("body", uMessage.getRaw().toString());
            intent.putExtra(AdDownloadModel.JsonKey.ID, uMessage.message_id);
            intent.putExtra(MonitorConstants.EXTRA_DOWNLOAD_TASK_ID, uMessage.task_id);
            UMJobIntentService.enqueueWork(context, (Class<? extends UMJobIntentService>) UmengDownloadResourceService.class, intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean a(Context context, Notification.Builder builder, UMessage uMessage) {
        int smallIconId = getSmallIconId(context, uMessage);
        Bitmap largeIcon = getLargeIcon(context, uMessage);
        if (smallIconId < 0) {
            return false;
        }
        builder.setSmallIcon(smallIconId);
        builder.setLargeIcon(largeIcon);
        return true;
    }

    public int getSmallIconId(Context context, UMessage uMessage) {
        try {
            iD = TextUtils.isEmpty(uMessage.icon) ? -1 : c.a(context).d(uMessage.icon);
            if (iD < 0) {
                iD = c.a(context).d(h);
            }
            if (iD < 0) {
                UMLog uMLog = UMConfigure.umDebugLog;
                UMLog.mutlInfo(b, 2, "没有自定义通知图标，改用应用图标");
                iD = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.icon;
            }
            if (iD < 0) {
                UMLog uMLog2 = UMConfigure.umDebugLog;
                UMLog.mutlInfo(b, 0, "找不到适当的通知图标，请确保您为此通知指定了一个图标，或者应用已经定义了一个图标");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return iD;
    }

    public Bitmap getLargeIcon(Context context, UMessage uMessage) {
        Bitmap bitmapDecodeFile;
        try {
            if (uMessage.isLargeIconFromInternet()) {
                bitmapDecodeFile = BitmapFactory.decodeFile(UmengDownloadResourceService.getMessageResourceFolder(context, uMessage) + uMessage.img.hashCode());
            } else {
                bitmapDecodeFile = null;
            }
            if (bitmapDecodeFile != null) {
                return bitmapDecodeFile;
            }
            int iD = TextUtils.isEmpty(uMessage.largeIcon) ? -1 : c.a(context).d(uMessage.largeIcon);
            if (iD < 0) {
                iD = c.a(context).d(g);
            }
            return iD > 0 ? BitmapFactory.decodeResource(context.getResources(), iD) : bitmapDecodeFile;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Bitmap getBarImage(Context context, UMessage uMessage) {
        return BitmapFactory.decodeFile(UmengDownloadResourceService.getMessageResourceFolder(context, uMessage) + uMessage.bar_image.hashCode());
    }

    public Bitmap getExpandImage(Context context, UMessage uMessage) {
        return BitmapFactory.decodeFile(UmengDownloadResourceService.getMessageResourceFolder(context, uMessage) + uMessage.expand_image.hashCode());
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.net.Uri getSound(android.content.Context r5, com.umeng.message.entity.UMessage r6) {
        /*
            r4 = this;
            r0 = 0
            boolean r1 = r6.isSoundFromInternet()     // Catch: java.lang.Throwable -> L73
            if (r1 == 0) goto L2b
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L73
            r1.<init>()     // Catch: java.lang.Throwable -> L73
            java.lang.String r2 = com.umeng.message.UmengDownloadResourceService.getMessageResourceFolder(r5, r6)     // Catch: java.lang.Throwable -> L73
            r1.append(r2)     // Catch: java.lang.Throwable -> L73
            java.lang.String r2 = r6.sound     // Catch: java.lang.Throwable -> L73
            int r2 = r2.hashCode()     // Catch: java.lang.Throwable -> L73
            r1.append(r2)     // Catch: java.lang.Throwable -> L73
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L73
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L73
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L73
            boolean r2 = r2.exists()     // Catch: java.lang.Throwable -> L73
            if (r2 != 0) goto L2c
        L2b:
            r1 = r0
        L2c:
            if (r1 != 0) goto L6c
            r2 = -1
            java.lang.String r3 = r6.sound     // Catch: java.lang.Throwable -> L73
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Throwable -> L73
            if (r3 != 0) goto L41
            com.umeng.message.common.c r2 = com.umeng.message.common.c.a(r5)     // Catch: java.lang.Throwable -> L73
            java.lang.String r6 = r6.sound     // Catch: java.lang.Throwable -> L73
            int r2 = r2.j(r6)     // Catch: java.lang.Throwable -> L73
        L41:
            if (r2 >= 0) goto L4d
            com.umeng.message.common.c r6 = com.umeng.message.common.c.a(r5)     // Catch: java.lang.Throwable -> L73
            java.lang.String r2 = "umeng_push_notification_default_sound"
            int r2 = r6.j(r2)     // Catch: java.lang.Throwable -> L73
        L4d:
            if (r2 <= 0) goto L6c
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L73
            r6.<init>()     // Catch: java.lang.Throwable -> L73
            java.lang.String r1 = "android.resource://"
            r6.append(r1)     // Catch: java.lang.Throwable -> L73
            java.lang.String r5 = r5.getPackageName()     // Catch: java.lang.Throwable -> L73
            r6.append(r5)     // Catch: java.lang.Throwable -> L73
            java.lang.String r5 = "/"
            r6.append(r5)     // Catch: java.lang.Throwable -> L73
            r6.append(r2)     // Catch: java.lang.Throwable -> L73
            java.lang.String r1 = r6.toString()     // Catch: java.lang.Throwable -> L73
        L6c:
            if (r1 == 0) goto L73
            android.net.Uri r5 = android.net.Uri.parse(r1)     // Catch: java.lang.Throwable -> L73
            return r5
        L73:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.message.UmengMessageHandler.getSound(android.content.Context, com.umeng.message.entity.UMessage):android.net.Uri");
    }

    private void a(Context context, UMessage uMessage) {
        try {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(UMessage.DISPLAY_TYPE_NOTIFICATION);
            if (notificationManager == null) {
                return;
            }
            Iterator<UNotificationItem> it = MessageNotificationQueue.getInstance().getQueue().iterator();
            while (it.hasNext()) {
                UNotificationItem next = it.next();
                if (uMessage.recall.equals(next.message.msg_id)) {
                    notificationManager.cancel(next.id);
                    MessageNotificationQueue.getInstance().remove(next);
                    UTrack.getInstance(context).a(uMessage);
                    return;
                }
            }
            UTrack.getInstance(context).b(uMessage);
        } catch (Exception unused) {
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(b, 0, "通知召回异常");
        }
    }
}
