package com.ss.android.download.api;

import android.content.Context;
import androidx.annotation.MainThread;
import com.ss.android.download.api.config.IDownloadButtonClickListener;
import com.ss.android.download.api.config.OnItemClickListener;
import com.ss.android.download.api.download.DownloadController;
import com.ss.android.download.api.download.DownloadEventConfig;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.download.api.download.DownloadStatusChangeListener;
import com.ss.android.download.api.download.extend.DownloadCompletedListener;
import com.ss.android.downloadad.api.AdDownloadCompletedEventHandler;
import com.ss.android.downloadad.api.AdWebViewDownloadManager;
import com.ss.android.socialbase.downloader.model.DownloadInfo;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface ITTDownloader {
    @MainThread
    @Deprecated
    void action(String str, int i, DownloadEventConfig downloadEventConfig);

    @MainThread
    void action(String str, long j, int i);

    @MainThread
    void action(String str, long j, int i, DownloadEventConfig downloadEventConfig, DownloadController downloadController);

    @MainThread
    void action(String str, long j, int i, DownloadEventConfig downloadEventConfig, DownloadController downloadController, OnItemClickListener onItemClickListener, IDownloadButtonClickListener iDownloadButtonClickListener);

    @MainThread
    void action(String str, long j, int i, DownloadEventConfig downloadEventConfig, DownloadController downloadController, DownloadModel downloadModel);

    void addDownloadCompletedListener(DownloadCompletedListener downloadCompletedListener);

    @MainThread
    void bind(int i, DownloadStatusChangeListener downloadStatusChangeListener, DownloadModel downloadModel);

    @MainThread
    void bind(Context context, int i, DownloadStatusChangeListener downloadStatusChangeListener, DownloadModel downloadModel);

    @MainThread
    void cancel(String str);

    @MainThread
    void cancel(String str, boolean z);

    AdDownloadCompletedEventHandler getAdDownloadCompletedEventHandler();

    AdWebViewDownloadManager getAdWebViewDownloadManager();

    DownloadConfigure getDownloadConfigure();

    DownloadInfo getDownloadInfo(String str);

    DownloadInfo getDownloadInfo(String str, String str2);

    boolean isStarted(String str);

    void removeDownloadCompletedListener(DownloadCompletedListener downloadCompletedListener);

    @MainThread
    void unbind(String str, int i);
}
