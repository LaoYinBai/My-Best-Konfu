package com.ss.android.downloadlib.addownload.compliance;

import com.ss.android.downloadlib.addownload.model.AppInfoX;
import com.ss.android.socialbase.downloader.utils.LruCache;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AppInfoCache extends LruCache<Long, AppInfoX> {

    private static class SingleTonHolder {
        private static AppInfoCache INSTANCE = new AppInfoCache();

        private SingleTonHolder() {
        }
    }

    public static AppInfoCache getInstance() {
        return SingleTonHolder.INSTANCE;
    }

    private AppInfoCache() {
        super(16, 16);
    }

    public void putAppInfoX(AppInfoX appInfoX) {
        if (appInfoX == null) {
            return;
        }
        put(Long.valueOf(appInfoX.getId()), appInfoX);
    }

    public AppInfoX getAppInfoX(long j, long j2) {
        return (AppInfoX) get(get(Long.valueOf(j)) != null ? Long.valueOf(j) : Long.valueOf(j2));
    }

    public AppInfoX getAppInfoX(long j) {
        return (AppInfoX) get(Long.valueOf(j));
    }
}
