package com.ss.android.downloadlib.addownload;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.ss.android.downloadlib.DownloadDispatcher;
import com.ss.android.downloadlib.constants.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AdDownloadSecurityManager {
    private static volatile AdDownloadSecurityManager sInstance;
    private Handler mMainHandler = null;

    public static AdDownloadSecurityManager inst() {
        if (sInstance == null) {
            synchronized (AdDownloadSecurityManager.class) {
                if (sInstance == null) {
                    sInstance = new AdDownloadSecurityManager();
                }
            }
        }
        return sInstance;
    }

    public void dealInvalidateDownloadFile(Context context, DownloadInfo downloadInfo) {
        if (isEnableInvalidateDownloadFile() && downloadInfo != null) {
            try {
                File file = new File(downloadInfo.getSavePath(), downloadInfo.getName());
                if (file.isFile() && file.exists()) {
                    file.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (this.mMainHandler == null) {
                this.mMainHandler = new Handler(Looper.getMainLooper());
            }
            final String url = downloadInfo.getUrl();
            Downloader.getInstance(context).clearDownloadData(downloadInfo.getId());
            this.mMainHandler.post(new Runnable() { // from class: com.ss.android.downloadlib.addownload.AdDownloadSecurityManager.1
                @Override // java.lang.Runnable
                public void run() {
                    GlobalInfo.getDownloadUIFactory().showToastWithDuration(3, GlobalInfo.getContext(), null, "下载失败，请重试！", null, 0);
                    CommonDownloadHandler commonDownloadHandler = DownloadDispatcher.getInstance().getCommonDownloadHandler(url);
                    if (commonDownloadHandler != null) {
                        commonDownloadHandler.resetDownloadStatus();
                    }
                }
            });
        }
    }

    public boolean isEnableInvalidateDownloadFile() {
        return GlobalInfo.getDownloadSettings().optInt(DownloadSettingKeys.KEY_FORBID_INVALIDATE_FILE_INSTALL, 0) == 1;
    }
}
