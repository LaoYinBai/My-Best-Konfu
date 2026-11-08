package com.ss.android.downloadlib.addownload.optimize;

import com.ss.android.download.api.config.DownloadClearSpaceListener;
import com.ss.android.downloadlib.AdDownloadCompletedEventHandlerImpl;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.applink.AppLinkMonitor;
import com.ss.android.downloadlib.constants.DownloadConstants;
import com.ss.android.downloadlib.constants.DownloadSettingKeys;
import com.ss.android.downloadlib.utils.TLogger;
import com.ss.android.downloadlib.utils.ToolUtils;
import com.ss.android.socialbase.downloader.depend.IDownloadDiskSpaceCallback;
import com.ss.android.socialbase.downloader.depend.IDownloadDiskSpaceHandler;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AppDownloadDiskSpaceHandler implements IDownloadDiskSpaceHandler {
    private static final int CLEAR_SPACE_MAX_SLEEP_TIME = 5000;
    private static final String TAG = "AppDownloadDiskSpaceHandler";
    private int mDownloadId;

    public void setDownloadId(int i) {
        this.mDownloadId = i;
    }

    @Override // com.ss.android.socialbase.downloader.depend.IDownloadDiskSpaceHandler
    public boolean cleanUpDisk(long j, long j2, IDownloadDiskSpaceCallback iDownloadDiskSpaceCallback) throws Throwable {
        long j3;
        DownloadSetting downloadSettingObtain = DownloadSetting.obtain(this.mDownloadId);
        if (!canCleanSpace(downloadSettingObtain)) {
            return false;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        DownloadOptimizationManager.getInstance().updateLastClearSpaceTime();
        long externalAvailableSpaceBytes = ToolUtils.getExternalAvailableSpaceBytes(0L);
        doSpaceClean();
        long externalAvailableSpaceBytes2 = ToolUtils.getExternalAvailableSpaceBytes(0L);
        long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
        if (externalAvailableSpaceBytes2 < j2) {
            long jTrySleepToWaitCleanDone = trySleepToWaitCleanDone(downloadSettingObtain);
            if (jTrySleepToWaitCleanDone > 0) {
                j3 = jTrySleepToWaitCleanDone;
                externalAvailableSpaceBytes2 = ToolUtils.getExternalAvailableSpaceBytes(0L);
            } else {
                j3 = jTrySleepToWaitCleanDone;
            }
        } else {
            j3 = 0;
        }
        TLogger.d(TAG, "cleanUpDisk, byteRequired = " + j2 + ", byteAvailableAfter = " + externalAvailableSpaceBytes2 + ", cleaned = " + (externalAvailableSpaceBytes2 - externalAvailableSpaceBytes), null);
        long j4 = externalAvailableSpaceBytes2;
        sendClearSpaceEvent(externalAvailableSpaceBytes, externalAvailableSpaceBytes2, j2, jCurrentTimeMillis2, j3);
        if (j4 < j2) {
            return false;
        }
        if (iDownloadDiskSpaceCallback == null) {
            return true;
        }
        iDownloadDiskSpaceCallback.onDiskCleaned();
        return true;
    }

    private boolean canCleanSpace(DownloadSetting downloadSetting) {
        if (downloadSetting.optInt(DownloadSettingKeys.KEY_CLEAR_SPACE_USE_DISK_HANDLER, 0) != 1) {
            return false;
        }
        return System.currentTimeMillis() - DownloadOptimizationManager.getInstance().getLastClearSpaceTime() >= downloadSetting.optLong(DownloadSettingKeys.KEY_CLEAR_SPACE_MIN_TIME_INTERVAL, DownloadConstants.DOWNLOAD_OPTIMIZATION_SPACE_INTERNAL);
    }

    private void doSpaceClean() throws Throwable {
        DownloadClearSpaceListener downloadClearSpaceListener = GlobalInfo.getDownloadClearSpaceListener();
        if (downloadClearSpaceListener != null) {
            downloadClearSpaceListener.clearStorageSpace();
        }
        ClearSpaceUtil.clearUnCompleteApk();
        ClearSpaceUtil.clearCompleteApk();
    }

    private long trySleepToWaitCleanDone(DownloadSetting downloadSetting) throws InterruptedException {
        long jOptLong = downloadSetting.optLong(DownloadSettingKeys.KEY_CLEAR_SPACE_SLEEP_TIME, 0L);
        if (jOptLong <= 0) {
            return 0L;
        }
        long j = AppLinkMonitor.DELAY_TIME;
        if (jOptLong <= AppLinkMonitor.DELAY_TIME) {
            j = jOptLong;
        }
        TLogger.d(TAG, "waiting for space clear, sleepTime = " + j, null);
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TLogger.d(TAG, "waiting end!", null);
        return j;
    }

    private void sendClearSpaceEvent(long j, long j2, long j3, long j4, long j5) {
        DownloadInfo downloadInfo = Downloader.getInstance(GlobalInfo.getContext()).getDownloadInfo(this.mDownloadId);
        if (downloadInfo == null) {
            return;
        }
        try {
            AdDownloadCompletedEventHandlerImpl.getInstance().trySendClearSpaceEvent(downloadInfo, j, j2, j3, j4, j5, j2 > j3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
