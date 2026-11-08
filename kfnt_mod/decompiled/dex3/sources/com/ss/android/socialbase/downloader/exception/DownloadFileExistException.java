package com.ss.android.socialbase.downloader.exception;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DownloadFileExistException extends BaseException {
    private String existTargetFileName;

    public DownloadFileExistException(String str) {
        this.existTargetFileName = str;
    }

    public String getExistTargetFileName() {
        return this.existTargetFileName;
    }
}
