package com.ss.android.socialbase.appdownloader.depend;

import android.content.Context;
import com.ss.android.socialbase.downloader.model.DownloadInfo;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IAppDownloadEventListener {
    public static final int APP_EV_CLICK_CONTINUE = 6;
    public static final int APP_EV_CLICK_DELETE = 7;
    public static final int APP_EV_CLICK_INSTALL = 3;
    public static final int APP_EV_CLICK_OPEN = 2;
    public static final int APP_EV_CLICK_PAUSE = 5;
    public static final int APP_EV_DOWNLOAD_CANCELED = 10;
    public static final int APP_EV_DOWNLOAD_FINISH = 1;
    public static final int APP_EV_INSTALL_FINISH = 4;
    public static final int APP_EV_INSTALL_PACKAGE_NAME_ERROR = 8;
    public static final int APP_EV_INSTALL_SUCCESS = 9;

    String getNotifyProcessName();

    boolean installIntercept(boolean z);

    void onAppDownloadCancel(DownloadInfo downloadInfo);

    void onAppDownloadEvent(int i, String str, int i2, long j);

    void onAppInstallError(int i, String str, String str2, String str3);

    void onAppInstalled(Context context, String str);
}
