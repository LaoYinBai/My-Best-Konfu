package com.ycbjie.ycupdatelib;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.net.Uri;
import android.os.Build;
import android.widget.RemoteViews;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import com.umeng.message.entity.UMessage;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NotificationUtils extends ContextWrapper {
    public static final String CHANNEL_ID = "default";
    private static final String CHANNEL_NAME = "Default_Channel";
    private int defaults;
    private int[] flags;
    private PendingIntent intent;
    private NotificationManager mManager;
    private boolean ongoing;
    private boolean onlyAlertOnce;
    private long[] pattern;
    private int priority;
    private RemoteViews remoteViews;
    private Uri sound;
    private String ticker;
    private long when;

    public NotificationUtils(Context context) {
        super(context);
        this.ongoing = false;
        this.remoteViews = null;
        this.intent = null;
        this.ticker = "";
        this.priority = 0;
        this.onlyAlertOnce = false;
        this.when = 0L;
        this.sound = null;
        this.defaults = 0;
        this.pattern = null;
        if (Build.VERSION.SDK_INT >= 26) {
            createNotificationChannel();
        }
    }

    @TargetApi(26)
    private void createNotificationChannel() {
        NotificationChannel notificationChannel = new NotificationChannel("default", CHANNEL_NAME, 2);
        notificationChannel.canBypassDnd();
        notificationChannel.enableLights(true);
        notificationChannel.setLockscreenVisibility(-1);
        notificationChannel.setLightColor(-65536);
        notificationChannel.canShowBadge();
        notificationChannel.getAudioAttributes();
        notificationChannel.getGroup();
        notificationChannel.setBypassDnd(true);
        notificationChannel.setSound(null, null);
        notificationChannel.shouldShowLights();
        notificationChannel.setShowBadge(true);
        getManager().createNotificationChannel(notificationChannel);
    }

    public NotificationManager getManager() {
        if (this.mManager == null) {
            this.mManager = (NotificationManager) getSystemService(UMessage.DISPLAY_TYPE_NOTIFICATION);
        }
        return this.mManager;
    }

    public void clearNotification() {
        getManager().cancelAll();
    }

    public Notification getNotification(String str, String str2, int i) {
        Notification notificationBuild;
        if (Build.VERSION.SDK_INT >= 26) {
            notificationBuild = getChannelNotification(str, str2, i).build();
        } else {
            notificationBuild = getNotificationCompat(str, str2, i).build();
        }
        if (this.flags != null && this.flags.length > 0) {
            for (int i2 = 0; i2 < this.flags.length; i2++) {
                notificationBuild.flags |= this.flags[i2];
            }
        }
        return notificationBuild;
    }

    public void sendNotification(int i, String str, String str2, int i2) {
        Notification notificationBuild;
        if (Build.VERSION.SDK_INT >= 26) {
            notificationBuild = getChannelNotification(str, str2, i2).build();
        } else {
            notificationBuild = getNotificationCompat(str, str2, i2).build();
        }
        if (this.flags != null && this.flags.length > 0) {
            for (int i3 = 0; i3 < this.flags.length; i3++) {
                notificationBuild.flags |= this.flags[i3];
            }
        }
        getManager().notify(i, notificationBuild);
    }

    public void sendNotificationCompat(int i, String str, String str2, int i2) {
        Notification notificationBuild = getNotificationCompat(str, str2, i2).build();
        if (this.flags != null && this.flags.length > 0) {
            for (int i3 = 0; i3 < this.flags.length; i3++) {
                notificationBuild.flags |= this.flags[i3];
            }
        }
        getManager().notify(i, notificationBuild);
    }

    private NotificationCompat.Builder getNotificationCompat(String str, String str2, int i) {
        NotificationCompat.Builder builder;
        if (Build.VERSION.SDK_INT >= 26) {
            builder = new NotificationCompat.Builder(getApplicationContext(), "default");
        } else {
            builder = new NotificationCompat.Builder(getApplicationContext());
            builder.setPriority(0);
        }
        builder.setContentTitle(str);
        builder.setContentText(str2);
        builder.setSmallIcon(i);
        builder.setPriority(this.priority);
        builder.setOnlyAlertOnce(this.onlyAlertOnce);
        builder.setOngoing(this.ongoing);
        if (this.remoteViews != null) {
            builder.setContent(this.remoteViews);
        }
        if (this.intent != null) {
            builder.setContentIntent(this.intent);
        }
        if (this.ticker != null && this.ticker.length() > 0) {
            builder.setTicker(this.ticker);
        }
        if (this.when != 0) {
            builder.setWhen(this.when);
        }
        if (this.sound != null) {
            builder.setSound(this.sound);
        }
        if (this.defaults != 0) {
            builder.setDefaults(this.defaults);
        }
        builder.setAutoCancel(true);
        return builder;
    }

    @RequiresApi(api = 26)
    private Notification.Builder getChannelNotification(String str, String str2, int i) {
        Notification.Builder autoCancel = new Notification.Builder(getApplicationContext(), "default").setContentTitle(str).setContentText(str2).setSmallIcon(i).setOngoing(this.ongoing).setPriority(this.priority).setOnlyAlertOnce(this.onlyAlertOnce).setAutoCancel(true);
        if (this.remoteViews != null) {
            autoCancel.setContent(this.remoteViews);
        }
        if (this.intent != null) {
            autoCancel.setContentIntent(this.intent);
        }
        if (this.ticker != null && this.ticker.length() > 0) {
            autoCancel.setTicker(this.ticker);
        }
        if (this.when != 0) {
            autoCancel.setWhen(this.when);
        }
        if (this.sound != null) {
            autoCancel.setSound(this.sound);
        }
        if (this.defaults != 0) {
            autoCancel.setDefaults(this.defaults);
        }
        if (this.pattern != null) {
            autoCancel.setVibrate(this.pattern);
        }
        return autoCancel;
    }

    public NotificationUtils setOngoing(boolean z) {
        this.ongoing = z;
        return this;
    }

    public NotificationUtils setContent(RemoteViews remoteViews) {
        this.remoteViews = remoteViews;
        return this;
    }

    public NotificationUtils setContentIntent(PendingIntent pendingIntent) {
        this.intent = pendingIntent;
        return this;
    }

    public NotificationUtils setTicker(String str) {
        this.ticker = str;
        return this;
    }

    public NotificationUtils setPriority(int i) {
        this.priority = i;
        return this;
    }

    public NotificationUtils setOnlyAlertOnce(boolean z) {
        this.onlyAlertOnce = z;
        return this;
    }

    public NotificationUtils setWhen(long j) {
        this.when = j;
        return this;
    }

    public NotificationUtils setSound(Uri uri) {
        this.sound = uri;
        return this;
    }

    public NotificationUtils setDefaults(int i) {
        this.defaults = i;
        return this;
    }

    public NotificationUtils setVibrate(long[] jArr) {
        this.pattern = jArr;
        return this;
    }

    public NotificationUtils setFlags(int... iArr) {
        this.flags = iArr;
        return this;
    }
}
