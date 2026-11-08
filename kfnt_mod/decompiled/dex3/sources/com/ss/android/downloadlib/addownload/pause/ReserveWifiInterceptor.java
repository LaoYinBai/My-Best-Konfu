package com.ss.android.downloadlib.addownload.pause;

import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.TTDownloader;
import com.ss.android.downloadlib.addownload.ReverseWifiHelper;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ReserveWifiInterceptor implements IPauseInterceptor {
    @Override // com.ss.android.downloadlib.addownload.pause.IPauseInterceptor
    public boolean interceptor(NativeDownloadModel nativeDownloadModel, int i, IPauseCallback iPauseCallback) {
        if (nativeDownloadModel == null) {
            return false;
        }
        return ReverseWifiHelper.tryReverseWifi(nativeDownloadModel, TTDownloader.inst(null).getDownloadInfo(nativeDownloadModel.getDownloadUrl()), i, iPauseCallback);
    }
}
