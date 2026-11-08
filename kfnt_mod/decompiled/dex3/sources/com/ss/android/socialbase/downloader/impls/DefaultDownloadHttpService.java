package com.ss.android.socialbase.downloader.impls;

import android.net.Uri;
import android.text.TextUtils;
import b.ac;
import b.ae;
import b.af;
import b.e;
import b.q;
import b.z;
import com.ss.android.socialbase.downloader.constants.DownloadConstants;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.model.HttpHeader;
import com.ss.android.socialbase.downloader.network.IDefaultDownloadHttpConnection;
import com.ss.android.socialbase.downloader.network.IDownloadHttpConnection;
import com.ss.android.socialbase.downloader.network.IDownloadHttpService;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import com.ss.android.socialbase.downloader.utils.LruCache;
import com.umeng.message.util.HttpRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;
import java.util.zip.GZIPInputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DefaultDownloadHttpService implements IDownloadHttpService {
    private final LruCache<String, z> hostIpClientCache = new LruCache<>(4, 8);

    @Override // com.ss.android.socialbase.downloader.network.IDownloadHttpService
    public IDownloadHttpConnection downloadWithConnection(int i, String str, List<HttpHeader> list) throws IOException {
        String value;
        z downloadClient;
        ac.a aVarA = new ac.a().a(str);
        if (list == null || list.size() <= 0) {
            value = null;
        } else {
            value = null;
            for (HttpHeader httpHeader : list) {
                String name = httpHeader.getName();
                if (value == null && DownloadConstants.EXTRA_REQUEST_HOST_IP.equals(name)) {
                    value = httpHeader.getValue();
                } else {
                    aVarA.b(name, DownloadUtils.getEncodedStr(httpHeader.getValue()));
                }
            }
        }
        if (!TextUtils.isEmpty(value)) {
            downloadClient = createSpecialHostIpClient(str, value);
        } else {
            downloadClient = DownloadComponentManager.getDownloadClient();
        }
        if (downloadClient == null) {
            throw new IOException("can't get httpClient");
        }
        final e eVarA = downloadClient.a(aVarA.d());
        final ae aeVarB = eVarA.b();
        if (aeVarB == null) {
            throw new IOException("can't get response");
        }
        final af afVarH = aeVarB.h();
        if (afVarH == null) {
            return null;
        }
        InputStream inputStreamD = afVarH.d();
        String strB = aeVarB.b(HttpRequest.HEADER_CONTENT_ENCODING);
        final InputStream gZIPInputStream = (strB == null || !HttpRequest.ENCODING_GZIP.equalsIgnoreCase(strB) || (inputStreamD instanceof GZIPInputStream)) ? inputStreamD : new GZIPInputStream(inputStreamD);
        return new IDefaultDownloadHttpConnection() { // from class: com.ss.android.socialbase.downloader.impls.DefaultDownloadHttpService.1
            @Override // com.ss.android.socialbase.downloader.network.AbsDownloadHttpConnection
            public String getHostIp() {
                return "";
            }

            @Override // com.ss.android.socialbase.downloader.network.IDownloadHttpConnection
            public InputStream getInputStream() throws IOException {
                return gZIPInputStream;
            }

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

            @Override // com.ss.android.socialbase.downloader.network.IDownloadHttpConnection
            public void end() {
                try {
                    if (afVarH != null) {
                        afVarH.close();
                    }
                    if (eVarA == null || eVarA.e()) {
                        return;
                    }
                    eVarA.c();
                } catch (Throwable unused) {
                }
            }
        };
    }

    private z createSpecialHostIpClient(String str, final String str2) {
        try {
            final String host = Uri.parse(str).getHost();
            if (!TextUtils.isEmpty(host) && !TextUtils.isEmpty(str2)) {
                String str3 = host + "_" + str2;
                synchronized (this.hostIpClientCache) {
                    z zVar = this.hostIpClientCache.get(str3);
                    if (zVar != null) {
                        return zVar;
                    }
                    z.a aVarCreateDownloadClientBuilder = DownloadComponentManager.createDownloadClientBuilder();
                    aVarCreateDownloadClientBuilder.a(new q() { // from class: com.ss.android.socialbase.downloader.impls.DefaultDownloadHttpService.2
                        public List<InetAddress> lookup(String str4) throws UnknownHostException {
                            if (TextUtils.equals(host, str4)) {
                                return Collections.singletonList(InetAddress.getByName(str2));
                            }
                            return q.a.lookup(str4);
                        }
                    });
                    z zVarC = aVarCreateDownloadClientBuilder.c();
                    synchronized (this.hostIpClientCache) {
                        this.hostIpClientCache.put(str3, zVarC);
                    }
                    return zVarC;
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return DownloadComponentManager.getDownloadClient();
    }
}
