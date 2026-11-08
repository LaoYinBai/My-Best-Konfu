package com.meizu.cloud.pushsdk.notification.a;

import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.handler.MessageV3;
import com.meizu.cloud.pushsdk.notification.PushNotificationBuilder;
import com.meizu.cloud.pushsdk.notification.model.AppIconSetting;
import com.meizu.cloud.pushsdk.util.MzSystemUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c extends com.meizu.cloud.pushsdk.notification.a {
    public c(Context context, PushNotificationBuilder pushNotificationBuilder) {
        super(context, pushNotificationBuilder);
    }

    @Override // com.meizu.cloud.pushsdk.notification.a
    protected void b(Notification.Builder builder, MessageV3 messageV3) {
        AppIconSetting appIconSetting;
        Bitmap bitmapA;
        String str;
        String str2;
        if ((!MzSystemUtils.isInternational() || MzSystemUtils.isMeizuAndFlyme()) && (appIconSetting = messageV3.getAppIconSetting()) != null) {
            if (appIconSetting.isDefaultLargeIcon()) {
                if (this.b != null && this.b.getLargeIcon() != 0) {
                    bitmapA = BitmapFactory.decodeResource(this.a.getResources(), this.b.getLargeIcon());
                    str = "AbstractPushNotification";
                    str2 = "set largeIcon by resource id";
                } else if (this.b == null || this.b.getAppLargeIcon() == null) {
                    bitmapA = a(this.a, messageV3.getUploadDataPackageName());
                    str = "AbstractPushNotification";
                    str2 = "set largeIcon by package default large icon";
                } else {
                    bitmapA = this.b.getAppLargeIcon();
                    str = "AbstractPushNotification";
                    str2 = "set largeIcon by bitmap provided by user setting";
                }
                DebugLogger.i(str, str2);
            } else {
                if (Thread.currentThread() == this.a.getMainLooper().getThread()) {
                    return;
                }
                Bitmap bitmapA2 = a(appIconSetting.getLargeIconUrl());
                if (bitmapA2 != null) {
                    DebugLogger.i("AbstractPushNotification", "On other Thread down load largeIcon image success");
                    builder.setLargeIcon(bitmapA2);
                    return;
                }
                bitmapA = a(this.a, messageV3.getUploadDataPackageName());
            }
            builder.setLargeIcon(bitmapA);
        }
    }
}
