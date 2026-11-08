package com.ss.android.socialbase.appdownloader.depend;

import com.ss.android.socialbase.downloader.model.DownloadInfo;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IAppDownloadLaunchResumeListener {
    void onLaunchResume(List<DownloadInfo> list);

    void onResumeDownload(DownloadInfo downloadInfo, boolean z);
}
