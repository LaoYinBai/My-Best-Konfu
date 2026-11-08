package com.meizu.cloud.pushsdk.notification.a;

import android.app.Notification;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import com.kwad.sdk.collector.AppStatusRules;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.handler.MessageV3;
import com.meizu.cloud.pushsdk.handler.MessageV4;
import com.meizu.cloud.pushsdk.notification.PushNotificationBuilder;
import com.meizu.cloud.pushsdk.notification.c.e;
import com.meizu.cloud.pushsdk.util.MinSdkChecker;
import java.io.File;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d extends c {
    public d(Context context, PushNotificationBuilder pushNotificationBuilder) {
        super(context, pushNotificationBuilder);
    }

    @Override // com.meizu.cloud.pushsdk.notification.a
    protected void a(Notification.Builder builder, MessageV3 messageV3) {
        if (MinSdkChecker.isSupportNotificationBuild()) {
            Notification.BigTextStyle bigTextStyle = new Notification.BigTextStyle();
            bigTextStyle.setBigContentTitle(messageV3.getTitle());
            bigTextStyle.bigText(messageV3.getNotificationStyle().getExpandableText());
            builder.setStyle(bigTextStyle);
        }
    }

    @Override // com.meizu.cloud.pushsdk.notification.a
    protected void a(Notification notification, MessageV3 messageV3) throws JSONException {
        super.a(notification, messageV3);
        MessageV4 messageV4 = MessageV4.parse(messageV3);
        if (messageV4.getActVideoSetting() == null) {
            DebugLogger.e("AbstractPushNotification", "only wifi can download act");
            return;
        }
        if (messageV4.getActVideoSetting().isWifiDisplay() && !com.meizu.cloud.pushsdk.util.a.b(this.a)) {
            DebugLogger.e("AbstractPushNotification", "only wifi can download act");
            return;
        }
        final String str = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/pushSdkAct/" + messageV3.getUploadDataPackageName();
        String strValueOf = String.valueOf(System.currentTimeMillis());
        String actUrl = messageV4.getActVideoSetting().getActUrl();
        if (!TextUtils.isEmpty(actUrl) && com.meizu.cloud.pushsdk.b.a.a(actUrl, str, strValueOf).a().c().b()) {
            DebugLogger.i("AbstractPushNotification", "down load " + actUrl + " success");
            String str2 = str + File.separator + "ACT-" + strValueOf;
            boolean zA = new e(str + File.separator + strValueOf, str2).a();
            StringBuilder sb = new StringBuilder();
            sb.append("zip file ");
            sb.append(zA);
            DebugLogger.i("AbstractPushNotification", sb.toString());
            if (zA) {
                Bundle bundle = new Bundle();
                bundle.putString(FileDownloadModel.PATH, str2);
                Bundle bundle2 = new Bundle();
                bundle2.putBundle("big", bundle);
                if (MinSdkChecker.isSupportVideoNotification()) {
                    notification.extras.putBundle("flyme.active", bundle2);
                }
            }
        }
        com.meizu.cloud.pushsdk.c.b.a.b.a(new Runnable() { // from class: com.meizu.cloud.pushsdk.notification.a.d.1
            @Override // java.lang.Runnable
            public void run() {
                for (File file : com.meizu.cloud.pushsdk.notification.c.a.b(str, String.valueOf(System.currentTimeMillis() - AppStatusRules.DEFAULT_START_TIME))) {
                    com.meizu.cloud.pushsdk.notification.c.a.b(file.getPath());
                    DebugLogger.i("AbstractPushNotification", "Delete file directory " + file.getName() + "\n");
                }
            }
        });
    }
}
