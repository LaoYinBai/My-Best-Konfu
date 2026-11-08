package com.ss.android.socialbase.downloader.network.connectionpool;

import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.model.HttpHeader;
import com.ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class FakeDownloadHeadHttpConnection implements IDownloadHeadHttpConnection, IFakeDownloadHttpConnection {
    private static final String TAG = "FakeDownloadHeadHttpCon";
    private static final ArrayList<String> usedHeaders = new ArrayList<>(6);
    private boolean isRequesting;
    private boolean isSuccessful;
    private long mCreateTime;
    protected List<HttpHeader> mRequestHeaders;
    private int mResponseCode;
    protected final long mStartOffset;
    protected final String mUrl;
    private IDownloadHeadHttpConnection realConnection;
    private Map<String, String> mResponseHeaders = null;
    protected final Object mJoinLock = new Object();

    public boolean isSuccessful(int i) {
        return i >= 200 && i < 300;
    }

    static {
        usedHeaders.add("Content-Length");
        usedHeaders.add("Content-Range");
        usedHeaders.add(DownloadUtils.TRANSFER_ENCODING);
        usedHeaders.add(DownloadUtils.ACCEPT_RANGES);
        usedHeaders.add(DownloadUtils.ETAG);
        usedHeaders.add(DownloadUtils.CONTENT_DISPOSITION);
    }

    public FakeDownloadHeadHttpConnection(String str, List<HttpHeader> list, long j) {
        this.mUrl = str;
        this.mRequestHeaders = list;
        this.mStartOffset = j;
    }

    @Override // com.ss.android.socialbase.downloader.network.connectionpool.IFakeDownloadHttpConnection
    public void execute() throws Exception {
        if (this.mResponseHeaders != null) {
            return;
        }
        try {
            this.isRequesting = true;
            this.realConnection = DownloadComponentManager.downloadWithHeadConnection(this.mUrl, this.mRequestHeaders);
            synchronized (this.mJoinLock) {
                if (this.realConnection != null) {
                    this.mResponseHeaders = new HashMap();
                    parseHeaders(this.realConnection, this.mResponseHeaders);
                    this.mResponseCode = this.realConnection.getResponseCode();
                    this.mCreateTime = System.currentTimeMillis();
                    this.isSuccessful = isSuccessful(this.mResponseCode);
                }
                this.isRequesting = false;
                this.mJoinLock.notifyAll();
            }
        } catch (Throwable th) {
            synchronized (this.mJoinLock) {
                if (this.realConnection != null) {
                    this.mResponseHeaders = new HashMap();
                    parseHeaders(this.realConnection, this.mResponseHeaders);
                    this.mResponseCode = this.realConnection.getResponseCode();
                    this.mCreateTime = System.currentTimeMillis();
                    this.isSuccessful = isSuccessful(this.mResponseCode);
                }
                this.isRequesting = false;
                this.mJoinLock.notifyAll();
                throw th;
            }
        }
    }

    private void parseHeaders(IDownloadHeadHttpConnection iDownloadHeadHttpConnection, Map<String, String> map) {
        if (iDownloadHeadHttpConnection == null || map == null) {
            return;
        }
        Iterator<String> it = usedHeaders.iterator();
        while (it.hasNext()) {
            String next = it.next();
            map.put(next, iDownloadHeadHttpConnection.getResponseHeaderField(next));
        }
    }

    @Override // com.ss.android.socialbase.downloader.network.connectionpool.IFakeDownloadHttpConnection
    public void joinExecute() throws InterruptedException {
        synchronized (this.mJoinLock) {
            if (this.isRequesting && this.mResponseHeaders == null) {
                this.mJoinLock.wait();
            }
        }
    }

    @Override // com.ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection
    public String getResponseHeaderField(String str) {
        if (this.mResponseHeaders != null) {
            return this.mResponseHeaders.get(str);
        }
        if (this.realConnection != null) {
            return this.realConnection.getResponseHeaderField(str);
        }
        return null;
    }

    @Override // com.ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection
    public int getResponseCode() throws IOException {
        return this.mResponseCode;
    }

    @Override // com.ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection
    public void cancel() {
        if (this.realConnection != null) {
            this.realConnection.cancel();
        }
    }

    @Override // com.ss.android.socialbase.downloader.network.connectionpool.IFakeDownloadHttpConnection
    public boolean isSuccessful() {
        return this.isSuccessful;
    }

    @Override // com.ss.android.socialbase.downloader.network.connectionpool.IFakeDownloadHttpConnection
    public boolean isValid() {
        return System.currentTimeMillis() - this.mCreateTime < DownloadPreconnecter.sHeadInfoOutdatedTime;
    }

    @Override // com.ss.android.socialbase.downloader.network.connectionpool.IFakeDownloadHttpConnection
    public boolean isRequesting() {
        return this.isRequesting;
    }

    public List<HttpHeader> getRequestHeaders() {
        return this.mRequestHeaders;
    }

    public Map<String, String> getResponseHeaders() {
        return this.mResponseHeaders;
    }
}
