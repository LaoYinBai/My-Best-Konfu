package com.ss.union.game.sdk.common.util;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import com.ss.android.socialbase.appdownloader.constants.Constants;
import com.ss.union.game.sdk.common.util.logger.LogUtils;
import java.io.File;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DownloadUtils {
    public static long downloadUrlLink(String str, String str2, boolean z) {
        return downloadUrlLink(str, str2, z, "", null, true, true, false);
    }

    public static long downloadUrlLink(String str, String str2, boolean z, String str3, Map<String, String> map, boolean z2, boolean z3, boolean z4) {
        if (StringUtils.isEmpty(str) || GlobalApplicationUtils.getContext() == null) {
            return -1L;
        }
        long jB = a.b(str, str2, z, str3, map, z2);
        if (jB >= 0) {
            return jB;
        }
        try {
            GlobalApplicationUtils.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        } catch (Exception unused) {
        }
        return -1L;
    }

    private static class a {
        private a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static long b(String str, String str2, boolean z, String str3, Map<String, String> map, boolean z2) {
            Context context = GlobalApplicationUtils.getContext();
            if (context == null) {
                return -1L;
            }
            try {
                DownloadManager downloadManager = (DownloadManager) context.getSystemService("download");
                if (downloadManager == null) {
                    return -1L;
                }
                try {
                    Uri uri = Uri.parse(str);
                    String lastPathSegment = uri.getLastPathSegment();
                    if (StringUtils.isEmpty(lastPathSegment)) {
                        if (!StringUtils.isEmpty(str2)) {
                            lastPathSegment = str2;
                        } else {
                            if (!z) {
                                return -1L;
                            }
                            lastPathSegment = "default.apk";
                        }
                    }
                    if (StringUtils.isEmpty(str2)) {
                        str2 = lastPathSegment;
                    }
                    DownloadManager.Request request = new DownloadManager.Request(uri);
                    if (map != null) {
                        for (Map.Entry<String, String> entry : map.entrySet()) {
                            request.addRequestHeader(entry.getKey(), entry.getValue());
                        }
                    }
                    try {
                        request.setNotificationVisibility(1);
                    } catch (Exception unused) {
                    }
                    if (z && !lastPathSegment.endsWith(Constants.APK_SUFFIX)) {
                        lastPathSegment = lastPathSegment + Constants.APK_SUFFIX;
                    }
                    if (z || lastPathSegment.endsWith(Constants.APK_SUFFIX)) {
                        str3 = "application/vnd.android.package-archive";
                    }
                    if (!StringUtils.isEmpty(str3)) {
                        request.setMimeType(str3);
                    }
                    request.setTitle(str2);
                    File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                    if (externalStoragePublicDirectory != null && !externalStoragePublicDirectory.exists() && !externalStoragePublicDirectory.mkdirs()) {
                        return -1L;
                    }
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, lastPathSegment);
                    request.setAllowedOverRoaming(false);
                    long jEnqueue = downloadManager.enqueue(request);
                    if (z2) {
                        ToastUtils.getInstance().toast("已开始下载");
                    }
                    return jEnqueue;
                } catch (Throwable th) {
                    LogUtils.log(6, "DownloadManager", "add download task error:" + th, th);
                    return -1L;
                }
            } catch (Exception unused2) {
                return -1L;
            }
        }
    }
}
