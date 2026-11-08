package com.ss.android.socialbase.downloader.exception;

import com.ss.android.socialbase.downloader.constants.DownloadErrorCode;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DownloadOutOfSpaceException extends BaseException {
    private final long avaliableSpaceBytes;
    private final long requiredSpaceBytes;

    public DownloadOutOfSpaceException(long j, long j2) {
        super(DownloadErrorCode.ERROR_INSUFFICIENT_SPACE, String.format("space is not enough required space is : %s but available space is :%s", String.valueOf(j2), String.valueOf(j)));
        this.avaliableSpaceBytes = j;
        this.requiredSpaceBytes = j2;
    }

    public long getAvaliableSpaceBytes() {
        return this.avaliableSpaceBytes;
    }

    public long getRequiredSpaceBytes() {
        return this.requiredSpaceBytes;
    }
}
