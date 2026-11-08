package com.ss.android.downloadlib.downloader;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.utils.DownloadSettingUtils;
import com.ss.android.socialbase.appdownloader.AppDownloadUtils;
import com.ss.android.socialbase.appdownloader.constants.Constants;
import com.ss.android.socialbase.appdownloader.util.package_info.PackageInfoUtils;
import com.ss.android.socialbase.downloader.constants.DbJsonConstants;
import com.ss.android.socialbase.downloader.depend.IDownloadCompleteHandler;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import java.io.File;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ApkModifyNameManager implements IDownloadCompleteHandler {
    @Override // com.ss.android.socialbase.downloader.depend.IDownloadCompleteHandler
    public void handle(DownloadInfo downloadInfo) throws BaseException {
        if (downloadInfo == null || !renameApk(downloadInfo)) {
            return;
        }
        setContentUri(GlobalInfo.getContext(), downloadInfo);
    }

    @Override // com.ss.android.socialbase.downloader.depend.IDownloadCompleteHandler
    public boolean needHandle(DownloadInfo downloadInfo) {
        if (downloadInfo != null) {
            return DownloadSettingUtils.shouldModifyApkName(DownloadSetting.obtain(downloadInfo.getId()));
        }
        return false;
    }

    private boolean renameApk(DownloadInfo downloadInfo) throws JSONException {
        String str = downloadInfo.getSavePath() + File.separator + downloadInfo.getName();
        File file = new File(str);
        String appNameByPackageInfo = PackageInfoUtils.getAppNameByPackageInfo(GlobalInfo.getContext(), AppDownloadUtils.getPackageInfo(downloadInfo, file), str);
        boolean z = false;
        if (TextUtils.isEmpty(appNameByPackageInfo)) {
            return false;
        }
        String str2 = appNameByPackageInfo + Constants.APK_SUFFIX;
        if (str2.equals(downloadInfo.getName())) {
            return true;
        }
        try {
            boolean zRenameTo = file.renameTo(new File(downloadInfo.getSavePath() + File.separator + str2));
            if (zRenameTo) {
                try {
                    downloadInfo.setName(str2);
                } catch (Exception e) {
                    e = e;
                    z = zRenameTo;
                    e.printStackTrace();
                    return z;
                }
            }
            return zRenameTo;
        } catch (Exception e2) {
            e = e2;
        }
    }

    private void setContentUri(Context context, final DownloadInfo downloadInfo) {
        String str = downloadInfo.getSavePath() + File.separator + downloadInfo.getName();
        Cursor cursorQuery = context.getContentResolver().query(MediaStore.Files.getContentUri("external"), new String[]{"_id"}, "_data=? ", new String[]{str}, null);
        if (cursorQuery != null && cursorQuery.moveToFirst()) {
            downloadInfo.safePutToDBJsonData(DbJsonConstants.CONTENT_URI, ContentUris.withAppendedId(MediaStore.Files.getContentUri("external"), cursorQuery.getInt(cursorQuery.getColumnIndex("_id"))).toString());
        } else {
            MediaScannerConnection.scanFile(context, new String[]{str}, new String[]{"application/vnd.android.package-archive"}, new MediaScannerConnection.OnScanCompletedListener() { // from class: com.ss.android.downloadlib.downloader.ApkModifyNameManager.1
                @Override // android.media.MediaScannerConnection.OnScanCompletedListener
                public void onScanCompleted(String str2, Uri uri) {
                    if (uri != null) {
                        downloadInfo.safePutToDBJsonData(DbJsonConstants.CONTENT_URI, uri.toString());
                        DownloadComponentManager.getDownloadCache().updateDownloadInfo(downloadInfo);
                    }
                }
            });
        }
        DownloadUtils.safeClose(cursorQuery);
    }
}
