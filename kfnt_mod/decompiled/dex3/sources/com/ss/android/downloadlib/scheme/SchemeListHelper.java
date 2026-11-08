package com.ss.android.downloadlib.scheme;

import android.text.TextUtils;
import com.ss.android.downloadad.api.constant.AdBaseConstants;
import com.ss.android.downloadlib.constants.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SchemeListHelper {
    private static volatile SchemeListHelper sInstance;
    private static SchemeListChecker sSchemeListChecker;

    private SchemeListHelper() {
    }

    public static SchemeListHelper inst() {
        if (sInstance == null) {
            synchronized (SchemeListHelper.class) {
                if (sInstance == null) {
                    sInstance = new SchemeListHelper();
                }
            }
        }
        return sInstance;
    }

    public synchronized void start() {
        if (sSchemeListChecker == null) {
            sSchemeListChecker = new SchemeListChecker();
        }
        sSchemeListChecker.startCheck();
    }

    public static synchronized void start(long j) {
        if (sSchemeListChecker == null) {
            sSchemeListChecker = new SchemeListChecker();
        }
        sSchemeListChecker.startCheck(j);
    }

    static boolean schemeListSwitch() {
        return DownloadSetting.obtainGlobal().optInt(DownloadSettingKeys.KEY_SCHEME_LIST_CHECK_SWITCH) == 1;
    }

    static long schemeListCheckInterval() {
        return DownloadSetting.obtainGlobal().optInt(DownloadSettingKeys.KEY_SCHEME_LIST_CHECK_INTERVAL, 15) * 60 * 1000;
    }

    static long schemeListCheckDelay() {
        return Math.max(DownloadSetting.obtainGlobal().optInt(DownloadSettingKeys.SCHEME_LIST_CHECK_DELAY) * 60 * 1000, 0);
    }

    static String getSchemeListDomain() {
        String strOptString = DownloadSetting.obtainGlobal().optString(DownloadSettingKeys.KEY_SCHEME_LIST_CHECK_DOMAIN);
        return TextUtils.isEmpty(strOptString) ? AdBaseConstants.HTTP_DOMAIN_AD : strOptString;
    }
}
