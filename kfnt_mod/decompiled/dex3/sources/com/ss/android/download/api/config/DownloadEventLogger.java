package com.ss.android.download.api.config;

import androidx.annotation.NonNull;
import com.ss.android.download.api.model.DownloadEventModel;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface DownloadEventLogger {
    void onEvent(@NonNull DownloadEventModel downloadEventModel);

    void onV3Event(@NonNull DownloadEventModel downloadEventModel);
}
