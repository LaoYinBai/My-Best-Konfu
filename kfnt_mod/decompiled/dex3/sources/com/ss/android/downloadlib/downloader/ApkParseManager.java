package com.ss.android.downloadlib.downloader;

import android.content.pm.PackageInfo;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.utils.DownloadSettingUtils;
import com.ss.android.socialbase.appdownloader.AppDownloadUtils;
import com.ss.android.socialbase.downloader.depend.IDownloadCompleteHandler;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.model.DownloadInfo;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ApkParseManager implements IDownloadCompleteHandler {
    @Override // com.ss.android.socialbase.downloader.depend.IDownloadCompleteHandler
    public void handle(DownloadInfo downloadInfo) throws BaseException {
        PackageInfo apkFile = AppDownloadUtils.parseApkFile(GlobalInfo.getContext(), downloadInfo, downloadInfo.getSavePath(), downloadInfo.getName());
        if (apkFile != null) {
            downloadInfo.setAppVersionCode(apkFile.versionCode);
        }
    }

    @Override // com.ss.android.socialbase.downloader.depend.IDownloadCompleteHandler
    public boolean needHandle(DownloadInfo downloadInfo) {
        return downloadInfo != null && DownloadSettingUtils.shouldParsePackageInfo() && downloadInfo.getPackageInfo() == null;
    }
}
