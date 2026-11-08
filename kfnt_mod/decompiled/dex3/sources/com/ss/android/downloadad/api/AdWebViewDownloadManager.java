package com.ss.android.downloadad.api;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import com.ss.android.download.api.download.DownloadController;
import com.ss.android.download.api.download.DownloadEventConfig;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.download.api.download.DownloadStatusChangeListener;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface AdWebViewDownloadManager {
    void action(long j);

    void action(long j, DownloadEventConfig downloadEventConfig, DownloadController downloadController);

    boolean bind(Context context, long j, String str, DownloadStatusChangeListener downloadStatusChangeListener, int i);

    boolean isDownloadInfoExisted(long j);

    boolean tryOpenMarket(Context context, Uri uri, DownloadModel downloadModel);

    boolean tryOpenMarket(Context context, Uri uri, DownloadModel downloadModel, DownloadEventConfig downloadEventConfig, DownloadController downloadController);

    Dialog tryStartDownload(Context context, String str, boolean z, DownloadModel downloadModel, DownloadEventConfig downloadEventConfig, DownloadController downloadController, DownloadStatusChangeListener downloadStatusChangeListener, int i);

    Dialog tryStartDownload(Context context, String str, boolean z, DownloadModel downloadModel, DownloadEventConfig downloadEventConfig, DownloadController downloadController, DownloadStatusChangeListener downloadStatusChangeListener, int i, boolean z2);

    Dialog tryStartDownload(Context context, String str, boolean z, DownloadModel downloadModel, DownloadStatusChangeListener downloadStatusChangeListener, int i);

    boolean unbind(long j, int i);
}
