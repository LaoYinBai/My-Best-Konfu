package com.ss.android.socialbase.appdownloader.impls;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.ss.android.socialbase.appdownloader.AppDownloadUtils;
import com.ss.android.socialbase.appdownloader.AppDownloader;
import com.ss.android.socialbase.appdownloader.depend.IAppDownloadLaunchResumeListener;
import com.ss.android.socialbase.appdownloader.notification.AppNotificationItem;
import com.ss.android.socialbase.appdownloader.setting.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.downloader.IDownloadLaunchHandler;
import com.ss.android.socialbase.downloader.logger.Logger;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.notification.AbsNotificationItem;
import com.ss.android.socialbase.downloader.notification.DownloadNotificationManager;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DefaultDownloadLaunchHandler implements IDownloadLaunchHandler {
    private static final String TAG = "LaunchResume";
    private BroadcastReceiver mReceiver;
    private List<Integer> mWaitingWifiTasks;

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadLaunchHandler
    public List<String> getResumeMimeTypes() {
        return AppDownloadUtils.getApplicationMimeTypes();
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadLaunchHandler
    public void onLaunchResume(final List<DownloadInfo> list, final int i) throws JSONException {
        if (DownloadUtils.isMainThread()) {
            DownloadComponentManager.getCPUThreadExecutor().execute(new Runnable() { // from class: com.ss.android.socialbase.appdownloader.impls.DefaultDownloadLaunchHandler.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        DefaultDownloadLaunchHandler.this.launchResumeInSubThread(list, i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            launchResumeInSubThread(list, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void launchResumeInSubThread(List<DownloadInfo> list, int i) throws JSONException {
        if (list == null || list.isEmpty()) {
            return;
        }
        IAppDownloadLaunchResumeListener appDownloadLaunchResumeListener = AppDownloader.getInstance().getAppDownloadLaunchResumeListener();
        if (appDownloadLaunchResumeListener != null) {
            appDownloadLaunchResumeListener.onLaunchResume(list);
        }
        Context appContext = DownloadComponentManager.getAppContext();
        if (appContext == null) {
            return;
        }
        boolean zIsWifi = DownloadUtils.isWifi(appContext);
        Iterator<DownloadInfo> it = list.iterator();
        while (it.hasNext()) {
            doLaunchResume(appContext, it.next(), zIsWifi, i);
        }
        if (this.mWaitingWifiTasks == null || this.mWaitingWifiTasks.isEmpty() || this.mReceiver != null) {
            return;
        }
        this.mReceiver = new BroadcastReceiver() { // from class: com.ss.android.socialbase.appdownloader.impls.DefaultDownloadLaunchHandler.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                final Context applicationContext = context.getApplicationContext();
                if (DownloadUtils.isWifi(applicationContext)) {
                    Logger.d(DefaultDownloadLaunchHandler.TAG, "onReceive : wifi connected !!!");
                    DownloadComponentManager.getCPUThreadExecutor().execute(new Runnable() { // from class: com.ss.android.socialbase.appdownloader.impls.DefaultDownloadLaunchHandler.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                if (DefaultDownloadLaunchHandler.this.mWaitingWifiTasks != null && !DefaultDownloadLaunchHandler.this.mWaitingWifiTasks.isEmpty()) {
                                    Integer[] numArr = new Integer[DefaultDownloadLaunchHandler.this.mWaitingWifiTasks.size()];
                                    DefaultDownloadLaunchHandler.this.mWaitingWifiTasks.toArray(numArr);
                                    DefaultDownloadLaunchHandler.this.mWaitingWifiTasks.clear();
                                    for (Integer num : numArr) {
                                        DownloadInfo downloadInfo = Downloader.getInstance(applicationContext).getDownloadInfo(num.intValue());
                                        if (downloadInfo != null && (downloadInfo.getRealStatus() == -5 || (downloadInfo.getRealStatus() == -2 && downloadInfo.isPauseReserveOnWifi()))) {
                                            DefaultDownloadLaunchHandler.this.doLaunchResume(applicationContext, downloadInfo, true, 2);
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    try {
                        applicationContext.unregisterReceiver(DefaultDownloadLaunchHandler.this.mReceiver);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                    DefaultDownloadLaunchHandler.this.mReceiver = null;
                }
            }
        };
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            appContext.registerReceiver(this.mReceiver, intentFilter);
        } catch (Throwable th) {
            th.printStackTrace();
            this.mReceiver = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:99:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void doLaunchResume(android.content.Context r19, com.ss.android.socialbase.downloader.model.DownloadInfo r20, boolean r21, int r22) throws org.json.JSONException {
        /*
            Method dump skipped, instructions count: 630
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.socialbase.appdownloader.impls.DefaultDownloadLaunchHandler.doLaunchResume(android.content.Context, com.ss.android.socialbase.downloader.model.DownloadInfo, boolean, int):void");
    }

    private void notifyInPausedResume(DownloadInfo downloadInfo, Context context) throws JSONException {
        DownloadSetting downloadSettingObtain = DownloadSetting.obtain(downloadInfo.getId());
        int iOptInt = downloadSettingObtain.optInt(DownloadSettingKeys.PAUSED_RESUME_MAX_COUNT, 0);
        double dOptDouble = downloadSettingObtain.optDouble(DownloadSettingKeys.PAUSED_RESUME_MAX_HOURS, 72.0d);
        int pausedResumeCount = downloadInfo.getPausedResumeCount();
        if (pausedResumeCount < iOptInt && ((double) (System.currentTimeMillis() - downloadInfo.getLastDownloadTime())) < dOptDouble * 3600000.0d) {
            AbsNotificationItem notificationItem = DownloadNotificationManager.getInstance().getNotificationItem(downloadInfo.getId());
            if (notificationItem == null) {
                notificationItem = new AppNotificationItem(context, downloadInfo.getId(), downloadInfo.getTitle(), downloadInfo.getSavePath(), downloadInfo.getName(), downloadInfo.getExtra());
                DownloadNotificationManager.getInstance().addNotification(notificationItem);
            } else {
                notificationItem.updateNotificationItem(downloadInfo);
            }
            notificationItem.setTotalBytes(downloadInfo.getTotalBytes());
            notificationItem.setCurBytes(downloadInfo.getCurBytes());
            notificationItem.refreshStatus(downloadInfo.getStatus(), null, false, false);
            downloadInfo.setPausedResumeCount(pausedResumeCount + 1);
            downloadInfo.updateSpData();
        }
    }

    private boolean isDownloadedForSucceed(DownloadInfo downloadInfo) {
        if (DownloadSetting.obtain(downloadInfo.getId()).optBugFix(DownloadSettingKeys.BugFix.UNINSTALL_CAN_NOT_RESUME_FOR_FORCE_TASK, false)) {
            return DownloadUtils.isFileDownloaded(downloadInfo, false, downloadInfo.getMd5());
        }
        return downloadInfo.isDownloaded();
    }
}
