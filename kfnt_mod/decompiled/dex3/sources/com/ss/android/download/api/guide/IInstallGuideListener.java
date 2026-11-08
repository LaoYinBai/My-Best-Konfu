package com.ss.android.download.api.guide;

import androidx.annotation.NonNull;
import com.ss.android.socialbase.downloader.model.DownloadInfo;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IInstallGuideListener {
    void showInstallGuide(@NonNull DownloadInfo downloadInfo, @NonNull IInstallGuideEndCallback iInstallGuideEndCallback);
}
