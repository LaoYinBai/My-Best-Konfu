package com.ss.android.socialbase.downloader.segment;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class UrlRecord {
    private final AtomicLong downloadBytes;
    private int failedTimes;
    private int hashCode;
    final String ip;
    final String ipFamily;
    private boolean isCurrentFailed;
    final boolean isMainUrl;
    private String key;
    private final List<SegmentReader> readers;
    final String url;

    public UrlRecord(String str, boolean z) {
        this.readers = new ArrayList();
        this.downloadBytes = new AtomicLong();
        this.url = str;
        this.isMainUrl = z;
        this.ip = null;
        this.ipFamily = null;
    }

    public UrlRecord(String str, String str2) {
        this.readers = new ArrayList();
        this.downloadBytes = new AtomicLong();
        this.url = str;
        this.isMainUrl = false;
        this.ip = str2;
        this.ipFamily = getIpFamily(str2);
    }

    private String getIpFamily(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            int iLastIndexOf = str.lastIndexOf(".");
            if (iLastIndexOf <= 0 || iLastIndexOf >= str.length()) {
                return null;
            }
            return str.substring(0, iLastIndexOf);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public synchronized void recordUse(SegmentReader segmentReader) {
        this.readers.add(segmentReader);
    }

    public synchronized void recordUnUse(SegmentReader segmentReader) {
        try {
            this.readers.remove(segmentReader);
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized int getCurrentUsers() {
        return this.readers.size();
    }

    public synchronized void recordFailed() {
        this.failedTimes++;
        this.isCurrentFailed = true;
    }

    public synchronized void recordSucceed() {
        this.isCurrentFailed = false;
    }

    public synchronized boolean isCurrentFailed() {
        return this.isCurrentFailed;
    }

    public void increaseDownloadBytes(long j) {
        this.downloadBytes.addAndGet(j);
    }

    public long getDownloadBytes() {
        long readingBytes = this.downloadBytes.get();
        Iterator<SegmentReader> it = this.readers.iterator();
        while (it.hasNext()) {
            readingBytes += it.next().getReadingBytes();
        }
        return readingBytes;
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = getKey().hashCode();
        }
        return this.hashCode;
    }

    private String getKey() {
        if (this.key == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.url);
            sb.append("_");
            sb.append(this.ip == null ? "" : this.ip);
            sb.append("_");
            sb.append(this.isMainUrl);
            this.key = sb.toString();
        }
        return this.key;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof UrlRecord) {
            return getKey().equals(((UrlRecord) obj).getKey());
        }
        return false;
    }

    public String toString() {
        return "UrlRecord{url='" + this.url + "', ip='" + this.ip + "', ipFamily='" + this.ipFamily + "', isMainUrl=" + this.isMainUrl + ", failedTimes=" + this.failedTimes + ", isCurrentFailed=" + this.isCurrentFailed + '}';
    }
}
