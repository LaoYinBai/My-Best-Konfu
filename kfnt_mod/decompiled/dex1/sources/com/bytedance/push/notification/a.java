package com.bytedance.push.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.os.Build;
import android.text.TextUtils;
import com.bytedance.push.PushBody;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class a {

    /* renamed from: com.bytedance.push.notification.a$a, reason: collision with other inner class name */
    public interface InterfaceC0147a {
        void a();

        void a(Bitmap bitmap);
    }

    public abstract void a(String str, InterfaceC0147a interfaceC0147a);

    public abstract Intent b(Context context, int i, PushBody pushBody);

    void a(final Context context, final int i, final PushBody pushBody) {
        if (pushBody == null) {
            return;
        }
        com.bytedance.push.i.l().a("Show", "show message :" + pushBody);
        if (pushBody.imageType != 0 && !TextUtils.isEmpty(pushBody.imageUrl)) {
            a(pushBody.imageUrl, new InterfaceC0147a() { // from class: com.bytedance.push.notification.a.1
                @Override // com.bytedance.push.notification.a.InterfaceC0147a
                public void a(Bitmap bitmap) {
                    a.this.b(context, i, pushBody, bitmap);
                }

                @Override // com.bytedance.push.notification.a.InterfaceC0147a
                public void a() {
                    a.this.b(context, i, pushBody, null);
                }
            });
        } else {
            b(context, i, pushBody, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Context context, int i, PushBody pushBody, Bitmap bitmap) {
        try {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            Intent intentB = b(context, i, pushBody);
            if (intentB == null) {
                return;
            }
            if (pushBody.mIsPassThough) {
                Notification notificationA = a(context, i, pushBody, bitmap);
                if (notificationA == null) {
                    return;
                }
                int i2 = (int) (pushBody.id % 2147483647L);
                notificationA.contentIntent = PendingIntent.getActivity(context, i2, intentB, 134217728);
                notificationManager.notify("app_notify", i2, notificationA);
                com.bytedance.push.i.l().a("Show", "show notification finish. " + pushBody);
            } else {
                context.startActivity(intentB);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    protected Notification a(Context context, int i, PushBody pushBody, Bitmap bitmap) throws PackageManager.NameNotFoundException {
        Notification.Builder builder;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            if (com.bytedance.common.push.utility.i.a(pushBody.title)) {
                pushBody.title = context.getString(packageInfo.applicationInfo.labelRes);
            }
            String notificationChannel = pushBody.getNotificationChannel();
            if (!f.a(context, notificationChannel)) {
                notificationChannel = "push";
            }
            if (Build.VERSION.SDK_INT >= 26) {
                builder = new Notification.Builder(context, notificationChannel);
            } else {
                builder = new Notification.Builder(context);
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            builder.setTicker(pushBody.title).setAutoCancel(true);
            builder.setWhen(jCurrentTimeMillis);
            builder.setShowWhen(pushBody.showWhen);
            if (Build.VERSION.SDK_INT > 20) {
                builder.setSmallIcon(com.ss.android.message.a.a.b(context, "status_icon_l"));
            } else {
                builder.setSmallIcon(com.ss.android.message.a.a.b(context, "status_icon"));
            }
            if (pushBody.useLED) {
                builder.setLights(-16711936, 1000, 2500);
            }
            if (Build.VERSION.SDK_INT > 20) {
                builder.setPriority(1);
                builder.setVibrate(new long[0]);
            }
            Notification notificationA = a(builder, context, pushBody, bitmap);
            if (pushBody.useSound) {
                notificationA.defaults |= 1;
            }
            if (pushBody.useVibrator) {
                try {
                    switch (((AudioManager) context.getSystemService("audio")).getRingerMode()) {
                        case 1:
                        case 2:
                            notificationA.defaults |= 2;
                            break;
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
            return notificationA;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Notification a(Notification.Builder builder, Context context, PushBody pushBody, Bitmap bitmap) {
        if (builder == null || context == null || pushBody == null) {
            return null;
        }
        if (bitmap == null || bitmap.isRecycled()) {
            pushBody.imageType = 0;
        }
        switch (pushBody.imageType) {
            case 0:
                return a(builder, context, pushBody.title, pushBody.text);
            case 1:
                return a(builder, context, pushBody.title, pushBody.text, bitmap);
            case 2:
                return a(builder, pushBody.title, pushBody.text, bitmap);
            default:
                return a(builder, context, pushBody.title, pushBody.text);
        }
    }

    private static Notification a(Notification.Builder builder, Context context, String str, String str2) throws PackageManager.NameNotFoundException {
        Bitmap bitmapDecodeResource;
        try {
            bitmapDecodeResource = BitmapFactory.decodeResource(context.getResources(), context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.icon);
        } catch (Exception unused) {
            bitmapDecodeResource = null;
        }
        Notification.Builder contentText = builder.setContentTitle(str).setContentText(str2);
        if (Build.VERSION.SDK_INT >= 16) {
            contentText.setStyle(new Notification.BigTextStyle().bigText(str2));
        }
        if (bitmapDecodeResource != null && !bitmapDecodeResource.isRecycled()) {
            contentText = contentText.setLargeIcon(bitmapDecodeResource);
        }
        return contentText.build();
    }

    private static Notification a(Notification.Builder builder, Context context, String str, String str2, Bitmap bitmap) throws PackageManager.NameNotFoundException {
        Bitmap bitmapDecodeResource;
        try {
            bitmapDecodeResource = BitmapFactory.decodeResource(context.getResources(), context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.icon);
        } catch (Exception unused) {
            bitmapDecodeResource = null;
        }
        Notification.Builder contentText = builder.setContentTitle(str).setContentText(str2);
        if (Build.VERSION.SDK_INT >= 16) {
            Notification.BigPictureStyle bigContentTitle = new Notification.BigPictureStyle().bigPicture(bitmap).setSummaryText(str2).setBigContentTitle(str);
            if (bitmapDecodeResource != null && !bitmapDecodeResource.isRecycled()) {
                bigContentTitle = bigContentTitle.bigLargeIcon(bitmapDecodeResource);
            }
            contentText.setStyle(bigContentTitle).setLargeIcon(bitmap);
        }
        return contentText.build();
    }

    private static Notification a(Notification.Builder builder, String str, String str2, Bitmap bitmap) {
        builder.setContentTitle(str).setContentText(str2).setLargeIcon(bitmap);
        return builder.build();
    }
}
