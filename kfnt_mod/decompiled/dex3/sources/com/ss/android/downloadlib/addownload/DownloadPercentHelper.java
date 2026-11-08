package com.ss.android.downloadlib.addownload;

import com.ss.android.download.api.model.DownloadShortInfo;
import com.ss.android.downloadlib.constants.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DownloadPercentHelper {
    public static int handlePercent(int i, int i2) {
        return (i2 <= 0 || i2 >= 100 || !enable(i)) ? i2 : (int) (Math.sqrt(i2) * 10.0d);
    }

    public static long getCurrBytes(int i, long j, long j2) {
        if (!enable(i)) {
            return j;
        }
        if (j <= 0) {
            return 0L;
        }
        return j2 <= 0 ? j : (j2 * handlePercent(i, (int) ((j * 100) / j2))) / 100;
    }

    public static DownloadShortInfo handleDownloadShortInfoSize(DownloadShortInfo downloadShortInfo) {
        if (downloadShortInfo == null || !enable((int) downloadShortInfo.id)) {
            return downloadShortInfo;
        }
        downloadShortInfo.currentBytes = getCurrBytes((int) downloadShortInfo.id, downloadShortInfo.currentBytes, downloadShortInfo.totalBytes);
        return downloadShortInfo;
    }

    private static boolean enable(int i) {
        return DownloadSetting.obtain(i).optInt(DownloadSettingKeys.PAUSE_OPTIMISE_PRETEND_DOWNLOAD_PERCENT_SWITCH, 0) == 1 && DownloadSetting.obtain(i).optInt(DownloadSettingKeys.PAUSE_OPTIMISE_SWITCH, 0) == 1;
    }
}
