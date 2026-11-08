package com.ss.android;

import com.ss.android.downloadlib.constants.DownloadConstants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TTAccountExtraConfig {
    private long mUpdateInfoInterval = DownloadConstants.DOWNLOAD_OPTIMIZATION_SPACE_INTERNAL;

    public long getUpdateInfoInterval() {
        return this.mUpdateInfoInterval;
    }

    public TTAccountExtraConfig setUpdateInfoInterval(long j) {
        this.mUpdateInfoInterval = j;
        return this;
    }
}
