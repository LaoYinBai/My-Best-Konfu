package com.ss.android.downloadlib.addownload;

import android.content.Context;
import com.ss.android.download.api.config.IDownloadButtonClickListener;
import com.ss.android.download.api.config.OnItemClickListener;
import com.ss.android.download.api.download.DownloadController;
import com.ss.android.download.api.download.DownloadEventConfig;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.download.api.download.DownloadStatusChangeListener;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface DownloadHandler {
    DownloadHandler addStatusChangeListener(int i, DownloadStatusChangeListener downloadStatusChangeListener);

    void cancelDownload(boolean z);

    long getDownloadAdId();

    long getLastWorkTime();

    void handleDownload(int i);

    boolean isBind();

    boolean isDownloadStarted();

    boolean isSupportSilentDownload();

    void onBind();

    boolean onUnbind(int i);

    void resetHandlerInfo();

    DownloadHandler setContext(Context context);

    DownloadHandler setDownloadButtonClickListener(IDownloadButtonClickListener iDownloadButtonClickListener);

    DownloadHandler setDownloadController(DownloadController downloadController);

    DownloadHandler setDownloadEventConfig(DownloadEventConfig downloadEventConfig);

    DownloadHandler setDownloadModel(DownloadModel downloadModel);

    DownloadHandler setModelId(long j);

    DownloadHandler setOnItemClickListener(OnItemClickListener onItemClickListener);
}
