package com.ss.android.downloadlib.downloader;

import com.ss.android.download.api.config.IApkUpdateHandler;
import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.addownload.model.ModelManager;
import com.ss.android.downloadlib.utils.ToolUtils;
import com.ss.android.socialbase.appdownloader.constants.Constants;
import com.ss.android.socialbase.downloader.depend.IDownloadCompleteHandler;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ApkUpdateManager implements IDownloadCompleteHandler {
    @Override // com.ss.android.socialbase.downloader.depend.IDownloadCompleteHandler
    public void handle(DownloadInfo downloadInfo) throws BaseException {
        IApkUpdateHandler apkUpdateHandler = GlobalInfo.getApkUpdateHandler();
        if (downloadInfo == null || apkUpdateHandler == null) {
            return;
        }
        String packageName = downloadInfo.getPackageName();
        String targetFilePath = downloadInfo.getTargetFilePath();
        File properOutputPath = getProperOutputPath(packageName, targetFilePath);
        NativeDownloadModel nativeModelByInfo = ModelManager.getInstance().getNativeModelByInfo(downloadInfo);
        apkUpdateHandler.tryApkUpdate(packageName, targetFilePath, properOutputPath, nativeModelByInfo != null ? ToolUtils.copyJson(nativeModelByInfo.getExtra()) : null);
        downloadInfo.setMimeType("application/vnd.android.package-archive");
        downloadInfo.setName(properOutputPath.getName());
        downloadInfo.setMd5(null);
    }

    @Override // com.ss.android.socialbase.downloader.depend.IDownloadCompleteHandler
    public boolean needHandle(DownloadInfo downloadInfo) {
        if (downloadInfo != null) {
            return com.ss.android.download.api.utils.ToolUtils.shouldDownloadWithPatchApply(DownloadSetting.obtain(downloadInfo.getId()), downloadInfo.getMimeType());
        }
        return false;
    }

    private File getProperOutputPath(String str, String str2) {
        File file = new File(str2);
        String name = file.getName();
        int iLastIndexOf = name.lastIndexOf(46);
        if (iLastIndexOf > 0) {
            str = name.substring(0, iLastIndexOf);
        }
        return new File(file.getParent(), str + Constants.APK_SUFFIX);
    }
}
