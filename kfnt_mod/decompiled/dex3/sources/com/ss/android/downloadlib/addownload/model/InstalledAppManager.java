package com.ss.android.downloadlib.addownload.model;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import java.util.Iterator;
import java.util.LinkedList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class InstalledAppManager {
    private static final long EXPIRE_TIME = 1800000;
    private static volatile InstalledAppManager sInstance;
    private final LinkedList<AppInfo> mApkList = new LinkedList<>();
    private static final String[] PKG_NAME_FUZZY_MATCH_EXCLUDES = {"com", "android", "ss"};
    private static final int[] INSTALL_FINISH_HIJACK_ERROR_CODES = {3101, 3102, 3103, 3201, 3202, 3203};

    private InstalledAppManager() {
    }

    public static InstalledAppManager getInstance() {
        if (sInstance == null) {
            synchronized (InstalledAppManager.class) {
                if (sInstance == null) {
                    sInstance = new InstalledAppManager();
                }
            }
        }
        return sInstance;
    }

    public void add(String str) {
        AppInfo appInfoCreateApkInfo;
        checkExpireTime();
        if (TextUtils.isEmpty(str) || (appInfoCreateApkInfo = createApkInfo(str)) == null) {
            return;
        }
        synchronized (this.mApkList) {
            this.mApkList.add(appInfoCreateApkInfo);
        }
    }

    public AppInfo get(String str) {
        checkExpireTime();
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        synchronized (this.mApkList) {
            Iterator<AppInfo> it = this.mApkList.iterator();
            while (it.hasNext()) {
                AppInfo next = it.next();
                if (str.equals(next.pkgName)) {
                    return next;
                }
            }
            return null;
        }
    }

    @Nullable
    public AppInfo peek() {
        AppInfo appInfoPeek;
        checkExpireTime();
        synchronized (this.mApkList) {
            appInfoPeek = this.mApkList.peek();
        }
        return appInfoPeek;
    }

    public void remove(String str) {
        checkExpireTime();
        if (TextUtils.isEmpty(str)) {
            return;
        }
        synchronized (this.mApkList) {
            Iterator<AppInfo> it = this.mApkList.iterator();
            while (it.hasNext()) {
                if (str.equals(it.next().pkgName)) {
                    it.remove();
                    return;
                }
            }
        }
    }

    public AppInfo getLatestInstalledApp(NativeDownloadModel nativeDownloadModel) {
        if (nativeDownloadModel == null) {
            return null;
        }
        checkExpireTime();
        synchronized (this.mApkList) {
            Iterator<AppInfo> it = this.mApkList.iterator();
            while (it.hasNext()) {
                AppInfo next = it.next();
                if (next.installedTime > nativeDownloadModel.getJumpInstallTime()) {
                    return next;
                }
            }
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x0098, code lost:
    
        r7[1] = r11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.util.Pair<com.ss.android.downloadlib.addownload.model.InstalledAppManager.AppInfo, java.lang.Integer> checkIfHijackedByAnotherInstalledApp(com.ss.android.downloadad.api.model.NativeDownloadModel r18) {
        /*
            Method dump skipped, instructions count: 346
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.downloadlib.addownload.model.InstalledAppManager.checkIfHijackedByAnotherInstalledApp(com.ss.android.downloadad.api.model.NativeDownloadModel):android.util.Pair");
    }

    private void checkExpireTime() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        synchronized (this.mApkList) {
            Iterator<AppInfo> it = this.mApkList.iterator();
            while (it.hasNext() && jCurrentTimeMillis - it.next().installedTime > EXPIRE_TIME) {
                it.remove();
            }
        }
    }

    private AppInfo createApkInfo(String str) {
        try {
            PackageManager packageManager = GlobalInfo.getContext().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
            if (packageInfo != null) {
                return new AppInfo(str, packageInfo.versionCode, packageInfo.versionName, (String) packageManager.getApplicationLabel(packageInfo.applicationInfo), System.currentTimeMillis());
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private static boolean isPkgNameFuzzyMatched(String str, String str2) {
        String[] strArrSplit;
        String[] strArrSplit2;
        int i;
        boolean z;
        try {
            strArrSplit = str.split("\\.");
            strArrSplit2 = str2.split("\\.");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (strArrSplit.length != 0 && strArrSplit2.length != 0) {
            int i2 = 0;
            int i3 = 0;
            for (String str3 : strArrSplit) {
                String[] strArr = PKG_NAME_FUZZY_MATCH_EXCLUDES;
                int length = strArr.length;
                int i4 = 0;
                while (true) {
                    if (i4 >= length) {
                        i = i2;
                        z = false;
                        break;
                    }
                    String str4 = strArr[i4];
                    if (str4.equals(str3)) {
                        if (i2 < strArrSplit2.length && str4.equals(strArrSplit2[i2])) {
                            i2++;
                        }
                        i = i2;
                        z = true;
                    } else {
                        i4++;
                    }
                }
                if (z) {
                    i2 = i;
                } else {
                    i2 = i;
                    while (i < strArrSplit2.length) {
                        if (str3.equals(strArrSplit2[i])) {
                            if (i == i2) {
                                i2++;
                            }
                            i3++;
                            if (i3 >= 2) {
                                return true;
                            }
                        }
                        i++;
                    }
                }
            }
            return false;
        }
        return false;
    }

    public static class AppInfo {
        public final String appName;
        public final long installedTime;
        public final String pkgName;
        public final int versionCode;
        public final String versionName;

        private AppInfo(String str, int i, String str2, String str3, long j) {
            this.pkgName = str;
            this.versionCode = i;
            this.versionName = str2 != null ? str2.toLowerCase() : null;
            this.appName = str3 != null ? str3.toLowerCase() : null;
            this.installedTime = j;
        }
    }
}
