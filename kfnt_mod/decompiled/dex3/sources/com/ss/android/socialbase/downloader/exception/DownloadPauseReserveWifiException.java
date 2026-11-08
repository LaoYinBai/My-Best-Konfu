package com.ss.android.socialbase.downloader.exception;

import com.ss.android.socialbase.downloader.constants.DownloadErrorCode;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DownloadPauseReserveWifiException extends DownloadRetryNeedlessException {
    public DownloadPauseReserveWifiException() {
        super(DownloadErrorCode.ERROR_ONLY_WIFI, "download reserve wifi");
    }
}
