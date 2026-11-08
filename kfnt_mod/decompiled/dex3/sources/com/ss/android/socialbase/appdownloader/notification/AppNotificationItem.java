package com.ss.android.socialbase.appdownloader.notification;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.widget.RemoteViews;
import com.ss.android.socialbase.appdownloader.AppDownloadUtils;
import com.ss.android.socialbase.appdownloader.AppResourceUtils;
import com.ss.android.socialbase.appdownloader.DownloadHandlerService;
import com.ss.android.socialbase.appdownloader.ResourceUtils;
import com.ss.android.socialbase.appdownloader.constants.Constants;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.notification.AbsNotificationItem;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.umeng.commonsdk.proguard.b;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AppNotificationItem extends AbsNotificationItem {
    private static final long HOUR_IN_MILLIS = 3600000;
    private static final long MINUTE_IN_MILLIS = 60000;
    private static final String NOTIFICATION_GROUP = "com.ss.android.socialbase.APP_DOWNLOADER";
    private static final long SECOND_IN_MILLIS = 1000;
    private String extra;
    private final Context mContext;
    private final Resources res;
    private String targetFileName;
    private String targetSavePath;

    public AppNotificationItem(Context context, int i, String str, String str2, String str3, String str4) {
        super(i, str);
        this.targetSavePath = str2;
        this.targetFileName = str3;
        this.extra = str4;
        this.mContext = context.getApplicationContext();
        this.res = this.mContext.getResources();
    }

    @Override // com.ss.android.socialbase.downloader.notification.AbsNotificationItem
    public void updateNotificationItem(DownloadInfo downloadInfo) {
        super.updateNotificationItem(downloadInfo);
        this.targetSavePath = downloadInfo.getSavePath();
        this.targetFileName = downloadInfo.getName();
        this.extra = downloadInfo.getExtra();
    }

    @Override // com.ss.android.socialbase.downloader.notification.AbsNotificationItem
    public void updateNotification(BaseException baseException, boolean z) {
        if (this.mContext == null) {
            return;
        }
        try {
            this.notification = createNotification(baseException, z);
            notify(this.notification);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:172:0x0498  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x04c1  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x04c6  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x04ec  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0502  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0543  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0547  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0553  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x057c  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0580  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x058b  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x05a7  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x05b1  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x022e  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0232  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.app.Notification createNotification(com.ss.android.socialbase.downloader.exception.BaseException r24, boolean r25) throws android.content.res.Resources.NotFoundException {
        /*
            Method dump skipped, instructions count: 1463
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.socialbase.appdownloader.notification.AppNotificationItem.createNotification(com.ss.android.socialbase.downloader.exception.BaseException, boolean):android.app.Notification");
    }

    private boolean needShowWaitNetText(BaseException baseException, DownloadSetting downloadSetting, DownloadInfo downloadInfo) {
        return baseException != null && (baseException.getErrorCode() == 1013 || baseException.getErrorCode() == 1049) && downloadInfo != null && "application/vnd.android.package-archive".contains(downloadInfo.getMimeType()) && downloadSetting.optInt(DownloadSettingKeys.NOTIFICATION_TEXT_OPT, 0) == 1;
    }

    private RemoteViews createRemoteViews() {
        RemoteViews remoteViews = new RemoteViews(this.mContext.getPackageName(), AppResourceUtils.getNotificationLayout());
        if (Build.VERSION.SDK_INT > 20) {
            try {
                if (AppDownloadUtils.isMaterialNotification(this.mContext)) {
                    remoteViews.setInt(AppResourceUtils.getNotificationRootId(), "setBackgroundColor", this.mContext.getResources().getColor(AppResourceUtils.getNotificationColorId()));
                }
            } catch (Throwable unused) {
            }
        }
        return remoteViews;
    }

    private int getSmallIcon(int i, int i2) {
        if (DownloadSetting.obtain(i2).optInt(com.ss.android.socialbase.appdownloader.setting.DownloadSettingKeys.NOTIFICATION_OPT_2) == 1) {
            return AppResourceUtils.getAndroidCompleteDrawable();
        }
        if (i == 1 || i == 4) {
            return AppResourceUtils.getAndroidDownloadDrawable();
        }
        if (i == 2) {
            return AppResourceUtils.getAndroidWarningDrawable();
        }
        if (i == 3) {
            return AppResourceUtils.getAndroidCompleteDrawable();
        }
        return 0;
    }

    private PendingIntent getCommonHandleIntent(String str, int i, int i2) {
        Intent intent = new Intent(this.mContext, (Class<?>) DownloadHandlerService.class);
        intent.setAction(str);
        intent.putExtra(Constants.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS, i2);
        intent.putExtra(Constants.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_TYPE, i);
        intent.putExtra(Constants.EXTRA_FROM_NOTIFICATION, true);
        return PendingIntent.getService(this.mContext, i2, intent, DownloadExpSwitchCode.BUGFIX_SIGBUS_24_25);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:13:0x0044
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1178)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    private androidx.core.app.NotificationCompat.Builder getNotificationBuilder() {
        /*
            r3 = this;
            com.ss.android.socialbase.appdownloader.AppDownloader r0 = com.ss.android.socialbase.appdownloader.AppDownloader.getInstance()
            java.lang.String r0 = r0.getNotificationChannelId()
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 26
            if (r1 >= r2) goto L16
            androidx.core.app.NotificationCompat$Builder r0 = new androidx.core.app.NotificationCompat$Builder
            android.content.Context r1 = r3.mContext
            r0.<init>(r1)
            goto L4b
        L16:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L22
            android.content.Context r0 = r3.mContext
            java.lang.String r0 = com.ss.android.socialbase.appdownloader.AppDownloadUtils.getNotificationChannelId(r0)
        L22:
            com.ss.android.socialbase.appdownloader.AppDownloader r1 = com.ss.android.socialbase.appdownloader.AppDownloader.getInstance()     // Catch: java.lang.NoSuchMethodError -> L44
            com.ss.android.socialbase.appdownloader.depend.IDownloadNotificationBuilder r1 = r1.getAppDownloadNotificationBuilder()     // Catch: java.lang.NoSuchMethodError -> L44
            if (r1 == 0) goto L3b
            com.ss.android.socialbase.appdownloader.AppDownloader r1 = com.ss.android.socialbase.appdownloader.AppDownloader.getInstance()     // Catch: java.lang.NoSuchMethodError -> L44
            com.ss.android.socialbase.appdownloader.depend.IDownloadNotificationBuilder r1 = r1.getAppDownloadNotificationBuilder()     // Catch: java.lang.NoSuchMethodError -> L44
            android.content.Context r2 = r3.mContext     // Catch: java.lang.NoSuchMethodError -> L44
            androidx.core.app.NotificationCompat$Builder r0 = r1.createNotification(r2, r0)     // Catch: java.lang.NoSuchMethodError -> L44
            goto L4b
        L3b:
            androidx.core.app.NotificationCompat$Builder r1 = new androidx.core.app.NotificationCompat$Builder     // Catch: java.lang.NoSuchMethodError -> L44
            android.content.Context r2 = r3.mContext     // Catch: java.lang.NoSuchMethodError -> L44
            r1.<init>(r2, r0)     // Catch: java.lang.NoSuchMethodError -> L44
            r0 = r1
            goto L4b
        L44:
            androidx.core.app.NotificationCompat$Builder r0 = new androidx.core.app.NotificationCompat$Builder
            android.content.Context r1 = r3.mContext
            r0.<init>(r1)
        L4b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.socialbase.appdownloader.notification.AppNotificationItem.getNotificationBuilder():androidx.core.app.NotificationCompat$Builder");
    }

    private CharSequence formatDuration(Resources resources, long j) {
        if (j >= 3600000) {
            return resources.getString(ResourceUtils.getStringId(this.mContext, "tt_appdownloader_duration_hours"), Integer.valueOf((int) ((j + 1800000) / 3600000)));
        }
        if (j < MINUTE_IN_MILLIS) {
            return resources.getString(ResourceUtils.getStringId(this.mContext, "tt_appdownloader_duration_seconds"), Integer.valueOf((int) ((j + 500) / 1000)));
        }
        return resources.getString(ResourceUtils.getStringId(this.mContext, "tt_appdownloader_duration_minutes"), Integer.valueOf((int) ((j + b.d) / MINUTE_IN_MILLIS)));
    }

    private int getDownloadProgressId(int i) {
        if (DownloadSetting.obtain(i).optInt("enable_notification_ui") >= 1) {
            return AppResourceUtils.getNewDownloadProgressId();
        }
        return AppResourceUtils.getDownloadProgressId();
    }
}
