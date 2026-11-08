package com.ss.android.socialbase.appdownloader;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.IBinder;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.ss.android.socialbase.appdownloader.constants.Constants;
import com.ss.android.socialbase.appdownloader.depend.IAppDownloadEventHandler;
import com.ss.android.socialbase.appdownloader.setting.DownloadSettingKeys;
import com.ss.android.socialbase.appdownloader.view.DownloadTaskDeleteActivity;
import com.ss.android.socialbase.downloader.constants.DownloadConstants;
import com.ss.android.socialbase.downloader.depend.IDownloadNotificationEventListener;
import com.ss.android.socialbase.downloader.depend.INotificationClickCallback;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.downloader.DownloadProcessDispatcher;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.logger.Logger;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.notification.AbsNotificationItem;
import com.ss.android.socialbase.downloader.notification.DownloadNotificationManager;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import java.io.File;
import java.util.ArrayList;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DownloadHandlerService extends Service {
    private static final String TAG = "DownloadHandlerService";

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        DownloadComponentManager.setAppContext(this);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        if (Logger.debug()) {
            Logger.d(TAG, "onStartCommand");
        }
        handleIntent(intent);
        stopSelf();
        return 2;
    }

    private boolean handleIntent(Intent intent) {
        if (intent == null) {
            return false;
        }
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            return false;
        }
        int intExtra = intent.getIntExtra(Constants.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS, 0);
        intent.getIntExtra(Constants.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_TYPE, 0);
        IAppDownloadEventHandler appDownloadEventHandler = AppDownloader.getInstance().getAppDownloadEventHandler();
        IDownloadNotificationEventListener downloadNotificationEventListener = Downloader.getInstance(this).getDownloadNotificationEventListener(intExtra);
        if (intent.getBooleanExtra(Constants.EXTRA_FROM_NOTIFICATION, false) && DownloadSetting.obtain(intExtra).optInt(DownloadSettingKeys.NOTIFICATION_OPT_2) == 1) {
            DownloadNotificationManager.getInstance().cancelNotification(intExtra);
        }
        DownloadInfo downloadInfo = Downloader.getInstance(this).getDownloadInfo(intExtra);
        if (downloadInfo == null) {
            return false;
        }
        if (action.equals(Constants.ACTION_CLICK_CONTENT)) {
            handleClickNonBtnAreaWhenUnSuccess(downloadInfo, appDownloadEventHandler, downloadNotificationEventListener);
        } else if (action.equals(Constants.ACTION_OPEN)) {
            handleActionClickWithoutType(this, downloadInfo, appDownloadEventHandler, downloadNotificationEventListener);
        } else if (action.equals(Constants.ACTION_CLICK_BTN)) {
            if (downloadInfo.getStatus() == 0) {
                return false;
            }
            handleActionClickWithoutType(this, downloadInfo, appDownloadEventHandler, downloadNotificationEventListener);
            if (downloadInfo.isDownloadOverStatus() && DownloadSetting.obtain(intExtra).optInt("no_hide_notification", 0) == 0) {
                if (!(DownloadSetting.obtain(intExtra).optInt("enable_notification_ui") >= 2 && downloadInfo.getStatus() == -1)) {
                    DownloadNotificationManager.getInstance().hideNotification(intExtra);
                    DownloadNotificationManager.getInstance().cancelNotification(intExtra);
                }
            }
        } else if (action.equals(Constants.ACTION_DELETE)) {
            handleDelete(downloadInfo, appDownloadEventHandler, downloadNotificationEventListener);
        } else if (action.equals(Constants.ACTION_HIDE)) {
            DownloadNotificationManager.getInstance().hideNotification(intExtra);
        } else if (action.equals("android.intent.action.BOOT_COMPLETED") || action.equals("android.intent.action.MEDIA_MOUNTED")) {
            DownloadComponentManager.getCPUThreadExecutor().execute(new Runnable() { // from class: com.ss.android.socialbase.appdownloader.DownloadHandlerService.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add("application/vnd.android.package-archive");
                        arrayList.add(DownloadConstants.MIME_PLG);
                        Downloader.getInstance(DownloadComponentManager.getAppContext()).restartAllFailedDownloadTasks(arrayList);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return true;
        }
        return false;
    }

    private static void cancelReserveWifi(Context context, DownloadInfo downloadInfo) throws JSONException {
        if (DownloadUtils.isWifi(context.getApplicationContext()) && downloadInfo.isPauseReserveOnWifi()) {
            downloadInfo.stopPauseReserveOnWifi();
        }
    }

    private static void handleClickNotificationInstall(Context context, final IAppDownloadEventHandler iAppDownloadEventHandler, final DownloadInfo downloadInfo) {
        if (downloadInfo == null) {
            return;
        }
        final IDownloadNotificationEventListener downloadNotificationEventListener = Downloader.getInstance(context).getDownloadNotificationEventListener(downloadInfo.getId());
        if (iAppDownloadEventHandler == null && downloadNotificationEventListener == null) {
            return;
        }
        DownloadComponentManager.getCPUThreadExecutor().execute(new Runnable() { // from class: com.ss.android.socialbase.appdownloader.DownloadHandlerService.2
            @Override // java.lang.Runnable
            public void run() {
                PackageInfo packageInfo;
                try {
                    File file = new File(downloadInfo.getSavePath(), downloadInfo.getName());
                    if (file.exists()) {
                        try {
                            String str = "";
                            if (DownloadComponentManager.getAppContext() != null && (packageInfo = AppDownloadUtils.getPackageInfo(downloadInfo, file)) != null) {
                                str = packageInfo.packageName;
                            }
                            String str2 = str;
                            if (iAppDownloadEventHandler != null) {
                                iAppDownloadEventHandler.handleDownloadEvent(downloadInfo.getId(), 3, str2, -3, downloadInfo.getDownloadTime());
                            }
                            if (downloadNotificationEventListener != null) {
                                downloadNotificationEventListener.onNotificationEvent(3, downloadInfo, str2, "");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    private void handleClickNonBtnAreaWhenUnSuccess(@NonNull DownloadInfo downloadInfo, IAppDownloadEventHandler iAppDownloadEventHandler, IDownloadNotificationEventListener iDownloadNotificationEventListener) {
        boolean zOnClickWhenUnSuccess;
        int id = downloadInfo.getId();
        INotificationClickCallback notificationClickCallback = DownloadProcessDispatcher.getInstance().getNotificationClickCallback(id);
        if (notificationClickCallback != null) {
            try {
                zOnClickWhenUnSuccess = notificationClickCallback.onClickWhenUnSuccess(downloadInfo);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } else {
            zOnClickWhenUnSuccess = false;
        }
        if (zOnClickWhenUnSuccess) {
            return;
        }
        Intent intent = new Intent(this, (Class<?>) DownloadTaskDeleteActivity.class);
        intent.putExtra(Constants.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS, id);
        intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        startActivity(intent);
        DownloadNotificationManager.getInstance().hideNotification(id);
        downloadInfo.updateDownloadTime();
        if (iAppDownloadEventHandler != null) {
            iAppDownloadEventHandler.handleDownloadEvent(id, 7, "", downloadInfo.getStatus(), downloadInfo.getDownloadTime());
        }
        if (iDownloadNotificationEventListener != null) {
            iDownloadNotificationEventListener.onNotificationEvent(7, downloadInfo, "", "");
        }
    }

    private void handleDelete(@NonNull DownloadInfo downloadInfo, IAppDownloadEventHandler iAppDownloadEventHandler, IDownloadNotificationEventListener iDownloadNotificationEventListener) {
        int id = downloadInfo.getId();
        Intent intent = new Intent(this, (Class<?>) DownloadTaskDeleteActivity.class);
        intent.putExtra(Constants.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS, id);
        intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        startActivity(intent);
        DownloadNotificationManager.getInstance().hideNotification(id);
        downloadInfo.updateDownloadTime();
        if (iAppDownloadEventHandler != null) {
            iAppDownloadEventHandler.handleDownloadEvent(id, 7, "", downloadInfo.getStatus(), downloadInfo.getDownloadTime());
        }
        if (iDownloadNotificationEventListener != null) {
            iDownloadNotificationEventListener.onNotificationEvent(7, downloadInfo, "", "");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0020  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void openDownload(android.content.Context r2, int r3, boolean r4) {
        /*
            r0 = 0
            if (r4 == 0) goto L20
            com.ss.android.socialbase.downloader.downloader.DownloadProcessDispatcher r4 = com.ss.android.socialbase.downloader.downloader.DownloadProcessDispatcher.getInstance()
            com.ss.android.socialbase.downloader.depend.INotificationClickCallback r4 = r4.getNotificationClickCallback(r3)
            if (r4 == 0) goto L20
            com.ss.android.socialbase.downloader.downloader.Downloader r1 = com.ss.android.socialbase.downloader.downloader.Downloader.getInstance(r2)     // Catch: java.lang.Throwable -> L1c
            com.ss.android.socialbase.downloader.model.DownloadInfo r1 = r1.getDownloadInfo(r3)     // Catch: java.lang.Throwable -> L1c
            if (r1 == 0) goto L20
            boolean r4 = r4.onClickWhenSuccess(r1)     // Catch: java.lang.Throwable -> L1c
            goto L21
        L1c:
            r4 = move-exception
            r4.printStackTrace()
        L20:
            r4 = 0
        L21:
            if (r4 == 0) goto L24
            return
        L24:
            r4 = 1
            int r3 = com.ss.android.socialbase.appdownloader.AppDownloadUtils.startViewIntent(r2, r3, r4)
            if (r3 != 0) goto L34
            java.lang.String r3 = "Open Fail!"
            android.widget.Toast r2 = android.widget.Toast.makeText(r2, r3, r0)
            r2.show()
        L34:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.socialbase.appdownloader.DownloadHandlerService.openDownload(android.content.Context, int, boolean):void");
    }

    public static void handleActionClickWithoutType(Context context, DownloadInfo downloadInfo, IAppDownloadEventHandler iAppDownloadEventHandler, IDownloadNotificationEventListener iDownloadNotificationEventListener) {
        AbsNotificationItem notificationItem;
        int id = downloadInfo.getId();
        INotificationClickCallback notificationClickCallback = DownloadProcessDispatcher.getInstance().getNotificationClickCallback(id);
        if ("application/vnd.android.package-archive".equals(downloadInfo.getMimeType()) && notificationClickCallback != null && AppDownloadUtils.isApkInstalled(context, downloadInfo) && notificationClickCallback.onClickWhenInstalled(downloadInfo)) {
        }
        boolean z = false;
        switch (downloadInfo.getStatus()) {
            case -4:
            case -1:
                if (DownloadSetting.obtain(id).optInt("enable_notification_ui") >= 2 && downloadInfo.isOnlyWifi()) {
                    downloadInfo.setOnlyWifi(false);
                }
                Downloader.getInstance(context).restart(id);
                break;
            case -3:
                openDownload(DownloadComponentManager.getAppContext(), id, true);
                handleClickNotificationInstall(context, iAppDownloadEventHandler, downloadInfo);
                if (DownloadSetting.obtain(id).optInt(DownloadSettingKeys.NOTIFICATION_CLICK_INSTALL_AUTO_CANCEL, 1) != 0 || (notificationItem = DownloadNotificationManager.getInstance().getNotificationItem(id)) == null) {
                    z = true;
                } else {
                    notificationItem.recordClickInstall();
                    notificationItem.refreshStatus(-3, null, false, true);
                }
                if (z) {
                    DownloadNotificationManager.getInstance().hideNotification(id);
                    break;
                }
                break;
            case -2:
                if (DownloadProcessDispatcher.getInstance().canResume(id)) {
                    Downloader.getInstance(context).resume(id);
                } else {
                    AppDownloadUtils.createDownloadTask(downloadInfo, true, false);
                }
                if (iAppDownloadEventHandler != null) {
                    iAppDownloadEventHandler.handleDownloadEvent(id, 6, "", downloadInfo.getStatus(), downloadInfo.getDownloadTime());
                }
                if (iDownloadNotificationEventListener != null) {
                    iDownloadNotificationEventListener.onNotificationEvent(6, downloadInfo, "", "");
                    break;
                }
                break;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                Downloader.getInstance(context).pause(id);
                cancelReserveWifi(context, downloadInfo);
                if (iAppDownloadEventHandler != null) {
                    iAppDownloadEventHandler.handleDownloadEvent(id, 5, "", downloadInfo.getStatus(), downloadInfo.getDownloadTime());
                }
                if (iDownloadNotificationEventListener != null) {
                    iDownloadNotificationEventListener.onNotificationEvent(5, downloadInfo, "", "");
                    break;
                }
                break;
        }
    }
}
