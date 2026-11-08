package com.ss.android.socialbase.appdownloader.depend;

import android.content.Context;
import com.ss.android.socialbase.downloader.model.DownloadInfo;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IAppDownloadEventHandler {
    public static final int EV_CLICK_CONTINUE = 6;
    public static final int EV_CLICK_DELETE = 7;
    public static final int EV_CLICK_INSTALL = 3;
    public static final int EV_CLICK_OPEN = 2;
    public static final int EV_CLICK_PAUSE = 5;
    public static final int EV_DOWNLOAD_CANCEL = 7;
    public static final int EV_DOWNLOAD_FINISH = 1;
    public static final int EV_INSTALL_FINISH = 4;
    public static final int EV_INSTALL_PACKAGE_NAME_ERROR = 8;

    void handleAppInstallError(int i, int i2, String str, String str2, String str3);

    void handleAppInstalled(Context context, String str);

    void handleDownloadCancel(DownloadInfo downloadInfo);

    void handleDownloadEvent(int i, int i2, String str, int i3, long j);

    boolean installIntercept(int i, boolean z);

    boolean isForbidInvalidatePackageInstall();
}
