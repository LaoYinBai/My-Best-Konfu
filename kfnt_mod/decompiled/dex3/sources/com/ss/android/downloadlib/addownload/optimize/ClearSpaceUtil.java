package com.ss.android.downloadlib.addownload.optimize;

import android.content.Context;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.constants.DownloadSettingKeys;
import com.ss.android.downloadlib.utils.ToolUtils;
import com.ss.android.socialbase.appdownloader.AppDownloader;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import java.io.File;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ClearSpaceUtil {
    public static void clearUnCompleteApk() throws Throwable {
        List<DownloadInfo> unCompletedDownloadAppInfos = AppDownloader.getInstance().getUnCompletedDownloadAppInfos(GlobalInfo.getContext());
        if (unCompletedDownloadAppInfos == null || unCompletedDownloadAppInfos.size() <= 0) {
            return;
        }
        for (int i = 0; i < unCompletedDownloadAppInfos.size(); i++) {
            DownloadInfo downloadInfo = unCompletedDownloadAppInfos.get(i);
            File file = new File(downloadInfo.getTempPath(), downloadInfo.getTempName());
            long jLastModified = file.lastModified();
            long jOptInt = DownloadSetting.obtain(downloadInfo.getId()).optInt(DownloadSettingKeys.KEY_DOWNLOAD_FILE_EXPIRE_HOURS, 0) * 3600000;
            if (jOptInt <= 0) {
                jOptInt = 604800000;
            }
            if (file.isFile() && file.exists() && System.currentTimeMillis() - jLastModified >= jOptInt) {
                deleteFile(file);
                Downloader.getInstance(GlobalInfo.getContext()).clearDownloadData(downloadInfo.getId());
            }
        }
    }

    public static void clearCompleteApk() throws Throwable {
        List successedDownloadInfosWithMimeType = Downloader.getInstance(GlobalInfo.getContext()).getSuccessedDownloadInfosWithMimeType("application/vnd.android.package-archive");
        if (successedDownloadInfosWithMimeType == null || successedDownloadInfosWithMimeType.isEmpty()) {
            return;
        }
        for (int i = 0; i < successedDownloadInfosWithMimeType.size(); i++) {
            DownloadInfo downloadInfo = (DownloadInfo) successedDownloadInfosWithMimeType.get(i);
            if (downloadInfo != null) {
                String str = downloadInfo.getSavePath() + File.separator + downloadInfo.getName();
                File file = new File(str);
                if (file.exists()) {
                    long jCurrentTimeMillis = System.currentTimeMillis() - file.lastModified();
                    long jOptInt = DownloadSetting.obtain(downloadInfo.getId()).optInt(DownloadSettingKeys.KEY_DOWNLOAD_COMPLETE_FILE_EXPIRE_HOURS, 0) * 3600000;
                    if (jOptInt <= 0) {
                        jOptInt = 604800000;
                    }
                    boolean z = true;
                    if (jCurrentTimeMillis < jOptInt && !ToolUtils.isApkInstalled(GlobalInfo.getContext(), str)) {
                        z = false;
                    }
                    if (z) {
                        deleteFile(file);
                    }
                }
            }
        }
    }

    public static void clearAppCacheDir(Context context) {
        File externalCacheDir;
        if (context == null || (externalCacheDir = context.getExternalCacheDir()) == null) {
            return;
        }
        try {
            deletePath(externalCacheDir.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0030 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void deleteFile(java.io.File r3) throws java.lang.Throwable {
        /*
            r0 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L15 java.lang.Exception -> L18
            r1.<init>(r3)     // Catch: java.lang.Throwable -> L15 java.lang.Exception -> L18
            java.lang.String r0 = "1"
            byte[] r0 = r0.getBytes()     // Catch: java.lang.Exception -> L13 java.lang.Throwable -> L2d
            r1.write(r0)     // Catch: java.lang.Exception -> L13 java.lang.Throwable -> L2d
            r1.close()     // Catch: java.lang.Exception -> L13 java.lang.Throwable -> L2d
            goto L29
        L13:
            r0 = move-exception
            goto L1c
        L15:
            r3 = move-exception
            r1 = r0
            goto L2e
        L18:
            r1 = move-exception
            r2 = r1
            r1 = r0
            r0 = r2
        L1c:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L2d
            if (r1 == 0) goto L29
            r1.close()     // Catch: java.lang.Exception -> L25
            goto L29
        L25:
            r0 = move-exception
            r0.printStackTrace()
        L29:
            r3.delete()
            return
        L2d:
            r3 = move-exception
        L2e:
            if (r1 == 0) goto L38
            r1.close()     // Catch: java.lang.Exception -> L34
            goto L38
        L34:
            r0 = move-exception
            r0.printStackTrace()
        L38:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.downloadlib.addownload.optimize.ClearSpaceUtil.deleteFile(java.io.File):void");
    }

    private static void deletePath(String str) {
        File file = new File(str);
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
                return;
            }
            String[] list = file.list();
            if (list == null) {
                return;
            }
            for (String str2 : list) {
                if (str2 != null) {
                    String str3 = str.endsWith(File.separator) ? str + str2 : str + File.separator + str2;
                    File file2 = new File(str3);
                    if (file2.isFile()) {
                        file2.delete();
                    }
                    if (file2.isDirectory()) {
                        deletePath(str3);
                    }
                }
            }
            file.delete();
        }
    }
}
