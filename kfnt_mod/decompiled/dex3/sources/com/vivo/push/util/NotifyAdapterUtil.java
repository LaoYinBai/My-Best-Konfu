package com.vivo.push.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.umeng.message.entity.UMessage;
import com.vivo.push.NoPorGuard;
import com.vivo.push.c.l;
import com.vivo.push.model.InsideNotificationItem;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@NoPorGuard
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NotifyAdapterUtil {
    private static final int HIDE_TITLE = 1;
    public static final int NOTIFY_MULTITERM_STYLE = 1;
    public static final int NOTIFY_SINGLE_STYLE = 0;
    public static final String PRIMARY_CHANNEL = "vivo_push_channel";
    private static final String PUSH_EN = "PUSH";
    private static final String PUSH_ZH = "推送通知";
    private static final String TAG = "NotifyManager";
    private static NotificationManager sNotificationManager = null;
    private static int sNotifyId = 20000000;

    @TargetApi(16)
    public static void pushNotification(Context context, List<Bitmap> list, InsideNotificationItem insideNotificationItem, long j, int i, l.a aVar) {
        n.d(TAG, "pushNotification");
        initAdapter(context);
        int notifyMode = NotifyUtil.getNotifyDataAdapter(context).getNotifyMode(insideNotificationItem);
        if (!TextUtils.isEmpty(insideNotificationItem.getPurePicUrl()) && list != null && list.size() > 1 && list.get(1) != null) {
            notifyMode = 1;
        }
        if (notifyMode == 2) {
            pushNotificationBySystem(context, list, insideNotificationItem, j, i, aVar);
        } else if (notifyMode == 1) {
            pushNotificationByCustom(context, list, insideNotificationItem, j, aVar);
        }
    }

    private static synchronized void initAdapter(Context context) {
        if (sNotificationManager == null) {
            sNotificationManager = (NotificationManager) context.getSystemService(UMessage.DISPLAY_TYPE_NOTIFICATION);
        }
        if (Build.VERSION.SDK_INT >= 26 && sNotificationManager != null) {
            NotificationChannel notificationChannel = sNotificationManager.getNotificationChannel("default");
            if (notificationChannel != null) {
                CharSequence name = notificationChannel.getName();
                if (PUSH_ZH.equals(name) || PUSH_EN.equals(name)) {
                    sNotificationManager.deleteNotificationChannel("default");
                }
            }
            NotificationChannel notificationChannel2 = new NotificationChannel(PRIMARY_CHANNEL, isZh(context) ? PUSH_ZH : PUSH_EN, 4);
            notificationChannel2.setLightColor(-16711936);
            notificationChannel2.enableVibration(true);
            notificationChannel2.setLockscreenVisibility(1);
            sNotificationManager.createNotificationChannel(notificationChannel2);
        }
    }

    @TargetApi(16)
    private static void pushNotificationByCustom(Context context, List<Bitmap> list, InsideNotificationItem insideNotificationItem, long j, l.a aVar) {
        Notification notification;
        Bitmap bitmap;
        Resources resources = context.getResources();
        String packageName = context.getPackageName();
        String title = insideNotificationItem.getTitle();
        int defaultNotifyIcon = NotifyUtil.getNotifyDataAdapter(context).getDefaultNotifyIcon();
        int iIntValue = Integer.valueOf(context.getApplicationInfo().icon).intValue();
        if (Build.VERSION.SDK_INT >= 26) {
            Notification.Builder builder = new Notification.Builder(context, PRIMARY_CHANNEL);
            if (defaultNotifyIcon > 0) {
                Bundle bundle = new Bundle();
                bundle.putInt("vivo.summaryIconRes", defaultNotifyIcon);
                builder.setExtras(bundle);
            }
            notification = builder.build();
        } else {
            notification = new Notification();
        }
        notification.priority = 2;
        notification.flags = 16;
        notification.tickerText = title;
        int defaultSmallIconId = NotifyUtil.getNotifyDataAdapter(context).getDefaultSmallIconId();
        if (defaultSmallIconId <= 0) {
            defaultSmallIconId = iIntValue;
        }
        notification.icon = defaultSmallIconId;
        RemoteViews remoteViews = new RemoteViews(packageName, NotifyUtil.getNotifyLayoutAdapter(context).getNotificationLayout());
        remoteViews.setTextViewText(resources.getIdentifier("notify_title", AdDownloadModel.JsonKey.ID, packageName), title);
        remoteViews.setTextColor(resources.getIdentifier("notify_title", AdDownloadModel.JsonKey.ID, packageName), NotifyUtil.getNotifyLayoutAdapter(context).getTitleColor());
        remoteViews.setTextViewText(resources.getIdentifier("notify_msg", AdDownloadModel.JsonKey.ID, packageName), insideNotificationItem.getContent());
        if (insideNotificationItem.isShowTime()) {
            remoteViews.setTextViewText(resources.getIdentifier("notify_when", AdDownloadModel.JsonKey.ID, packageName), new SimpleDateFormat("HH:mm", Locale.CHINA).format(new Date()));
            remoteViews.setViewVisibility(resources.getIdentifier("notify_when", AdDownloadModel.JsonKey.ID, packageName), 0);
        } else {
            remoteViews.setViewVisibility(resources.getIdentifier("notify_when", AdDownloadModel.JsonKey.ID, packageName), 8);
        }
        int suitIconId = NotifyUtil.getNotifyLayoutAdapter(context).getSuitIconId();
        remoteViews.setViewVisibility(suitIconId, 0);
        if (list != null && !list.isEmpty() && (bitmap = list.get(0)) != null) {
            remoteViews.setImageViewBitmap(suitIconId, bitmap);
        } else {
            if (defaultNotifyIcon <= 0) {
                defaultNotifyIcon = iIntValue;
            }
            remoteViews.setImageViewResource(suitIconId, defaultNotifyIcon);
        }
        Bitmap bitmap2 = null;
        if (list != null && list.size() > 1) {
            bitmap2 = list.get(1);
        }
        if (bitmap2 != null) {
            if (!TextUtils.isEmpty(insideNotificationItem.getPurePicUrl())) {
                remoteViews.setViewVisibility(resources.getIdentifier("notify_content", AdDownloadModel.JsonKey.ID, packageName), 8);
                remoteViews.setViewVisibility(resources.getIdentifier("notify_cover", AdDownloadModel.JsonKey.ID, packageName), 8);
                remoteViews.setViewVisibility(resources.getIdentifier("notify_pure_cover", AdDownloadModel.JsonKey.ID, packageName), 0);
                remoteViews.setImageViewBitmap(resources.getIdentifier("notify_pure_cover", AdDownloadModel.JsonKey.ID, packageName), bitmap2);
            } else {
                remoteViews.setViewVisibility(resources.getIdentifier("notify_cover", AdDownloadModel.JsonKey.ID, packageName), 0);
                remoteViews.setImageViewBitmap(resources.getIdentifier("notify_cover", AdDownloadModel.JsonKey.ID, packageName), bitmap2);
            }
        } else {
            remoteViews.setViewVisibility(resources.getIdentifier("notify_cover", AdDownloadModel.JsonKey.ID, packageName), 8);
        }
        notification.contentView = remoteViews;
        if (Build.VERSION.SDK_INT >= 16 && TextUtils.isEmpty(insideNotificationItem.getPurePicUrl())) {
            notification.bigContentView = remoteViews;
        }
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        int ringerMode = audioManager.getRingerMode();
        int vibrateSetting = audioManager.getVibrateSetting(0);
        n.d(TAG, "ringMode=" + ringerMode + " callVibrateSetting=" + vibrateSetting);
        switch (insideNotificationItem.getNotifyType()) {
            case 2:
                if (ringerMode == 2) {
                    notification.defaults = 1;
                    break;
                }
                break;
            case 3:
                if (vibrateSetting == 1) {
                    notification.defaults = 2;
                    notification.vibrate = new long[]{0, 100, 200, 300};
                    break;
                }
                break;
            case 4:
                if (ringerMode == 2) {
                    notification.defaults = 1;
                }
                if (vibrateSetting == 1) {
                    notification.defaults |= 2;
                    notification.vibrate = new long[]{0, 100, 200, 300};
                    break;
                }
                break;
        }
        Intent intent = new Intent("com.vivo.pushservice.action.RECEIVE");
        intent.setPackage(context.getPackageName());
        intent.setClassName(context.getPackageName(), "com.vivo.push.sdk.service.CommandService");
        intent.putExtra("command_type", "reflect_receiver");
        new com.vivo.push.b.p(packageName, j, insideNotificationItem).a(intent);
        notification.contentIntent = PendingIntent.getService(context, (int) SystemClock.uptimeMillis(), intent, DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        if (sNotificationManager != null) {
            int i = com.vivo.push.d.a().l;
            try {
                if (i == 0) {
                    sNotificationManager.notify(sNotifyId, notification);
                    if (aVar != null) {
                        aVar.a();
                        return;
                    }
                    return;
                }
                if (i == 1) {
                    sNotificationManager.notify((int) j, notification);
                    if (aVar != null) {
                        aVar.a();
                        return;
                    }
                    return;
                }
                n.a(TAG, "unknow notify style " + i);
            } catch (Exception e) {
                n.a(TAG, e);
                if (aVar != null) {
                    aVar.b();
                }
            }
        }
    }

    @SuppressLint({"NewApi"})
    private static void pushNotificationBySystem(Context context, List<Bitmap> list, InsideNotificationItem insideNotificationItem, long j, int i, l.a aVar) {
        Bitmap bitmapA;
        Notification.Builder builder;
        int i2;
        Bitmap bitmap;
        Bitmap bitmapDecodeResource;
        String packageName = context.getPackageName();
        String title = insideNotificationItem.getTitle();
        String content = insideNotificationItem.getContent();
        int iIntValue = Integer.valueOf(context.getApplicationInfo().icon).intValue();
        boolean zIsShowTime = insideNotificationItem.isShowTime();
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        int defaultNotifyIcon = NotifyUtil.getNotifyDataAdapter(context).getDefaultNotifyIcon();
        if (list == null || list.isEmpty()) {
            bitmapA = null;
        } else {
            Bitmap bitmap2 = list.get(0);
            if (bitmap2 == null || defaultNotifyIcon <= 0 || (bitmapDecodeResource = BitmapFactory.decodeResource(context.getResources(), defaultNotifyIcon)) == null) {
                bitmapA = bitmap2;
            } else {
                int width = bitmapDecodeResource.getWidth();
                int height = bitmapDecodeResource.getHeight();
                bitmapDecodeResource.recycle();
                bitmapA = b.a(bitmap2, width, height);
            }
        }
        if (Build.VERSION.SDK_INT >= 26) {
            builder = new Notification.Builder(context, PRIMARY_CHANNEL);
            if (defaultNotifyIcon > 0) {
                Bundle bundle = new Bundle();
                bundle.putInt("vivo.summaryIconRes", defaultNotifyIcon);
                builder.setExtras(bundle);
            }
            if (bitmapA != null) {
                builder.setLargeIcon(bitmapA);
            }
        } else {
            builder = new Notification.Builder(context);
            if (bitmapA != null) {
                builder.setLargeIcon(bitmapA);
            } else if (Build.VERSION.SDK_INT <= 22) {
                builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), iIntValue));
            }
        }
        int defaultSmallIconId = NotifyUtil.getNotifyDataAdapter(context).getDefaultSmallIconId();
        if (defaultSmallIconId <= 0) {
            defaultSmallIconId = iIntValue;
        }
        builder.setSmallIcon(defaultSmallIconId);
        if (insideNotificationItem.getCompatibleType() != 1) {
            builder.setContentTitle(title);
        }
        builder.setPriority(2);
        builder.setContentText(content);
        builder.setWhen(zIsShowTime ? System.currentTimeMillis() : 0L);
        builder.setShowWhen(zIsShowTime);
        builder.setTicker(title);
        int ringerMode = audioManager.getRingerMode();
        switch (insideNotificationItem.getNotifyType()) {
            case 2:
                if (ringerMode == 2) {
                    builder.setDefaults(1);
                    break;
                }
                break;
            case 3:
                if (ringerMode == 2) {
                    builder.setDefaults(2);
                    builder.setVibrate(new long[]{0, 100, 200, 300});
                    break;
                }
                break;
            case 4:
                if (ringerMode != 2) {
                    if (ringerMode == 1) {
                        builder.setDefaults(2);
                        builder.setVibrate(new long[]{0, 100, 200, 300});
                        break;
                    }
                } else {
                    builder.setDefaults(3);
                    builder.setVibrate(new long[]{0, 100, 200, 300});
                    break;
                }
                break;
        }
        if (list == null || list.size() <= 1) {
            i2 = i;
            bitmap = null;
        } else {
            bitmap = list.get(1);
            i2 = i;
        }
        if (i2 != 1 && bitmap != null) {
            Notification.BigPictureStyle bigPictureStyle = new Notification.BigPictureStyle();
            bigPictureStyle.setBigContentTitle(title);
            bigPictureStyle.setSummaryText(content);
            bigPictureStyle.bigPicture(bitmap);
            builder.setStyle(bigPictureStyle);
        }
        builder.setAutoCancel(true);
        Intent intent = new Intent("com.vivo.pushservice.action.RECEIVE");
        intent.setPackage(context.getPackageName());
        intent.setClassName(context.getPackageName(), "com.vivo.push.sdk.service.CommandService");
        intent.putExtra("command_type", "reflect_receiver");
        new com.vivo.push.b.p(packageName, j, insideNotificationItem).a(intent);
        builder.setContentIntent(PendingIntent.getService(context, (int) SystemClock.uptimeMillis(), intent, DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP));
        Notification notificationBuild = builder.build();
        int i3 = com.vivo.push.d.a().l;
        if (sNotificationManager != null) {
            try {
                if (i3 == 0) {
                    sNotificationManager.notify(sNotifyId, notificationBuild);
                    if (aVar != null) {
                        aVar.a();
                        return;
                    }
                    return;
                }
                if (i3 == 1) {
                    sNotificationManager.notify((int) j, notificationBuild);
                    if (aVar != null) {
                        aVar.a();
                        return;
                    }
                    return;
                }
                n.a(TAG, "unknow notify style " + i3);
            } catch (Exception e) {
                n.a(TAG, e);
                if (aVar != null) {
                    aVar.b();
                }
            }
        }
    }

    public static boolean repealNotifyById(Context context, long j) {
        int i = com.vivo.push.d.a().l;
        if (i != 0) {
            if (i == 1) {
                return cancelNotify(context, (int) j);
            }
            n.a(TAG, "unknow cancle notify style " + i);
            return false;
        }
        long jB = v.b().b("com.vivo.push.notify_key", -1L);
        if (jB == j) {
            n.d(TAG, "undo showed message " + j);
            n.a(context, "回收已展示的通知： " + j);
            return cancelNotify(context, sNotifyId);
        }
        n.d(TAG, "current showing message id " + jB + " not match " + j);
        n.a(context, "与已展示的通知" + jB + "与待回收的通知" + j + "不匹配");
        return false;
    }

    private static boolean cancelNotify(Context context, int i) {
        initAdapter(context);
        if (sNotificationManager == null) {
            return false;
        }
        sNotificationManager.cancel(i);
        return true;
    }

    public static void cancelNotify(Context context) {
        cancelNotify(context, sNotifyId);
    }

    public static void setNotifyId(int i) {
        sNotifyId = i;
    }

    private static boolean isZh(Context context) {
        return context.getResources().getConfiguration().locale.getLanguage().endsWith("zh");
    }
}
