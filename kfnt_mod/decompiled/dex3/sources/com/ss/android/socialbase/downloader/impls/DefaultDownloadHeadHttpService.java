package com.ss.android.socialbase.downloader.impls;

import b.ac;
import b.ae;
import b.e;
import b.z;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.model.HttpHeader;
import com.ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection;
import com.ss.android.socialbase.downloader.network.IDownloadHeadHttpService;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import java.io.IOException;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DefaultDownloadHeadHttpService implements IDownloadHeadHttpService {
    @Override // com.ss.android.socialbase.downloader.network.IDownloadHeadHttpService
    public IDownloadHeadHttpConnection downloadWithConnection(String str, List<HttpHeader> list) throws IOException {
        z downloadClient = DownloadComponentManager.getDownloadClient();
        if (downloadClient == null) {
            throw new IOException("can't get httpClient");
        }
        ac.a aVarB = new ac.a().a(str).b();
        if (list != null && list.size() > 0) {
            for (HttpHeader httpHeader : list) {
                aVarB.b(httpHeader.getName(), DownloadUtils.getEncodedStr(httpHeader.getValue()));
            }
        }
        final e eVarA = downloadClient.a(aVarB.d());
        final ae aeVarB = eVarA.b();
        if (aeVarB == null) {
            throw new IOException("can't get response");
        }
        if (DownloadExpSwitchCode.isSwitchEnable(2097152)) {
            aeVarB.close();
        }
        return new IDownloadHeadHttpConnection() { // from class: com.ss.android.socialbase.downloader.impls.DefaultDownloadHeadHttpService.1
            @Override // com.ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection
            public String getResponseHeaderField(String str2) {
                return aeVarB.b(str2);
            }

            @Override // com.ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection
            public int getResponseCode() throws IOException {
                return aeVarB.c();
            }

            @Override // com.ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection
            public void cancel() {
                if (eVarA == null || eVarA.e()) {
                    return;
                }
                eVarA.c();
            }
        };
    }
}
