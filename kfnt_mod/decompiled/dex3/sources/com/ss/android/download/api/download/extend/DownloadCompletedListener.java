package com.ss.android.download.api.download.extend;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.ss.android.download.api.download.DownloadController;
import com.ss.android.download.api.download.DownloadEventConfig;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.model.DownloadInfo;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface DownloadCompletedListener {
    void onCanceled(@NonNull DownloadInfo downloadInfo);

    void onDownloadFailed(@NonNull DownloadInfo downloadInfo, BaseException baseException, String str);

    void onDownloadFinished(@NonNull DownloadInfo downloadInfo, String str);

    void onDownloadStart(@NonNull DownloadModel downloadModel, @Nullable DownloadController downloadController, @Nullable DownloadEventConfig downloadEventConfig);

    void onInstalled(@Nullable DownloadInfo downloadInfo, String str);
}
