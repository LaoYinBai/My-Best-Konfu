package com.ss.android.download.api.inner;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface ITTDownloaderMonitor {
    void monitorDataError(String str);

    void monitorDataError(boolean z, String str);

    void monitorException(Throwable th, String str);

    void monitorException(boolean z, Throwable th, String str);

    void monitorPathError(String str);

    void monitorPathError(boolean z, String str);
}
