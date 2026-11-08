package com.ss.android.downloadlib.addownload.pause;

import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.TTDownloader;
import com.ss.android.downloadlib.constants.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class PauseInterceptorManager {
    private static PauseInterceptorManager sInstance;
    private List<IPauseInterceptor> mPauseInterceptorList = new ArrayList();

    public static PauseInterceptorManager getInstance() {
        if (sInstance == null) {
            synchronized (PauseInterceptorManager.class) {
                if (sInstance == null) {
                    sInstance = new PauseInterceptorManager();
                }
            }
        }
        return sInstance;
    }

    private PauseInterceptorManager() {
        this.mPauseInterceptorList.add(new MistakeClickInterceptor());
        this.mPauseInterceptorList.add(new ReserveWifiInterceptor());
        this.mPauseInterceptorList.add(new DownloadPercentInterceptor());
        this.mPauseInterceptorList.add(new ApkSizeInterceptor());
    }

    public void handlePause(NativeDownloadModel nativeDownloadModel, int i, IPauseCallback iPauseCallback) {
        if (this.mPauseInterceptorList == null || this.mPauseInterceptorList.size() == 0 || nativeDownloadModel == null) {
            iPauseCallback.pause(nativeDownloadModel);
            return;
        }
        DownloadInfo downloadInfo = TTDownloader.inst(null).getDownloadInfo(nativeDownloadModel.getDownloadUrl());
        if (downloadInfo == null || !"application/vnd.android.package-archive".equals(downloadInfo.getMimeType())) {
            iPauseCallback.pause(nativeDownloadModel);
            return;
        }
        boolean z = DownloadSetting.obtain(nativeDownloadModel.getDownloadId()).optInt(DownloadSettingKeys.PAUSE_OPTIMISE_SWITCH, 0) == 1;
        for (IPauseInterceptor iPauseInterceptor : this.mPauseInterceptorList) {
            if (z || (iPauseInterceptor instanceof ReserveWifiInterceptor)) {
                if (iPauseInterceptor.interceptor(nativeDownloadModel, i, iPauseCallback)) {
                    return;
                }
            }
        }
        iPauseCallback.pause(nativeDownloadModel);
    }
}
