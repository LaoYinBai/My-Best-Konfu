package com.ss.android.downloadlib.addownload.pause;

import com.ss.android.downloadad.api.model.NativeDownloadModel;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IPauseInterceptor {
    boolean interceptor(NativeDownloadModel nativeDownloadModel, int i, IPauseCallback iPauseCallback);
}
