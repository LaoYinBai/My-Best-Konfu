package com.ss.android.downloadlib.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.downloadad.api.model.InnerUnifyData;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.constants.DownloadConstants;
import com.ss.android.downloadlib.constants.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DownloadSettingUtils {
    @Nullable
    public static JSONObject getAdSettingJson() {
        return GlobalInfo.getDownloadSettings().optJSONObject("ad");
    }

    public static JSONObject getTaskSettingJson(DownloadModel downloadModel) {
        if (downloadModel == null) {
            return null;
        }
        if (downloadModel.isAd()) {
            return getAdSettingJson();
        }
        return downloadModel.getDownloadSettings();
    }

    @NonNull
    public static DownloadSetting getSetting(DownloadModel downloadModel) {
        return DownloadSetting.obtain(getTaskSettingJson(downloadModel));
    }

    @NonNull
    public static DownloadSetting getSetting(InnerUnifyData innerUnifyData) {
        if (innerUnifyData == null) {
            return DownloadSetting.obtainGlobal();
        }
        if (innerUnifyData.getDownloadId() != 0) {
            return DownloadSetting.obtain(innerUnifyData.getDownloadId());
        }
        if (innerUnifyData.isAd()) {
            return DownloadSetting.obtain(getAdSettingJson());
        }
        if (innerUnifyData.getDownloadSettings() != null) {
            return DownloadSetting.obtain(innerUnifyData.getDownloadSettings());
        }
        return DownloadSetting.obtainGlobal();
    }

    public static boolean getInstallGuideEnable(int i) {
        return DownloadSetting.obtain(i).optInt(DownloadSettingKeys.KEY_INSTALL_GUIDE_SWITCH, 0) == 1;
    }

    public static boolean getInstallGuideVivoEnable(int i) {
        return DownloadSetting.obtain(i).optInt(DownloadSettingKeys.KEY_INSTALL_GUIDE_VIVO, 1) == 1;
    }

    public static boolean getInstallGuideHuaweiEnable(int i) {
        return DownloadSetting.obtain(i).optInt(DownloadSettingKeys.KEY_INSTALL_GUIDE_HUAWEI, 1) == 1;
    }

    public static boolean getInstallGuideXiaomiEnable(int i) {
        return DownloadSetting.obtain(i).optInt(DownloadSettingKeys.KEY_INSTALL_GUIDE_XIAOMI, 1) == 1;
    }

    public static boolean getInstallGuideOppoEnable(int i) {
        return DownloadSetting.obtain(i).optInt(DownloadSettingKeys.KEY_INSTALL_GUIDE_OPPO, 1) == 1;
    }

    public static String getInstallGuideLottieUrlXiaomi(int i) {
        return DownloadSetting.obtain(i).optString(DownloadSettingKeys.KEY_INSTALL_GUIDE_LOTTIE_URL_XIAOMI);
    }

    public static String getInstallGuideLottieUrlOppo(int i) {
        return DownloadSetting.obtain(i).optString(DownloadSettingKeys.KEY_INSTALL_GUIDE_LOTTIE_URL_OPPO);
    }

    public static String getInstallGuideLottieUrlVivo(int i) {
        return DownloadSetting.obtain(i).optString(DownloadSettingKeys.KEY_INSTALL_GUIDE_LOTTIE_URL_VIVO);
    }

    public static String getInstallGuideLottieUrlHuawei(int i) {
        return DownloadSetting.obtain(i).optString(DownloadSettingKeys.KEY_INSTALL_GUIDE_LOTTIE_URL_HUAWEI);
    }

    public static int getInstallHijackDays(int i) {
        return DownloadSetting.obtain(i).optInt(DownloadSettingKeys.KEY_INSTALL_HIJACK_DAYS, 15);
    }

    public static int getInstallHijackInterval(int i) {
        return DownloadSetting.obtain(i).optInt(DownloadSettingKeys.KEY_INSTALL_GUIDE_INTERVAL, 3);
    }

    public static double getInstallHijackRatio(int i) {
        return DownloadSetting.obtain(i).optDouble(DownloadSettingKeys.KEY_INSTALL_HIJACK_RATIO, 0.5d);
    }

    public static long getInstallGuideShowTime(int i) {
        return DownloadSetting.obtain(i).optLong(DownloadSettingKeys.KEY_INSTALL_GUIDE_SHOW_TIME, 3000L);
    }

    public static int getSavePathType(@NonNull DownloadSetting downloadSetting) {
        return downloadSetting.optInt(DownloadSettingKeys.KEY_SAVE_PATH_TYPE, 0);
    }

    public static int getSavePathType(@NonNull DownloadModel downloadModel) {
        return getSavePathType(getSetting(downloadModel));
    }

    public static boolean pauseReserveOnWifiIsOpen(InnerUnifyData innerUnifyData) {
        return getSetting(innerUnifyData).optInt("pause_reserve_on_wifi", 0) == 1 && innerUnifyData.enableNewActivity();
    }

    public static double minInstallSize(int i) {
        return DownloadSetting.obtain(i).optDouble(DownloadSettingKeys.KEY_MIN_INSTALL_SIZE, 0.0d);
    }

    public static long getStorageMinSize(int i) {
        return DownloadSetting.obtain(i).optLong(DownloadSettingKeys.KEY_STORAGE_MIN_SIZE, 0L);
    }

    public static long getFetchApkHeadTimeout(int i) {
        return DownloadSetting.obtain(i).optLong(DownloadSettingKeys.KEY_FETCH_APK_HEAD_TIME_OUT, 800L);
    }

    public static boolean fetchApkSizeSwitch(int i) {
        return DownloadSetting.obtain(i).optLong(DownloadSettingKeys.KEY_FETCH_APK_SIZE_SWITCH, 0L) == 1;
    }

    public static boolean cleanSpaceBeforeDownloadSwitch(int i) {
        return DownloadSetting.obtain(i).optLong(DownloadSettingKeys.KEY_CLEAN_SPACE_BEFORE_DOWNLOAD_SWITCH, 0L) == 1;
    }

    public static boolean cleanSpaceEnable(int i) {
        return DownloadSetting.obtain(i).optInt(DownloadSettingKeys.KEY_EXEC_CLEAR_SPACE_SWITCH, 0) == 1;
    }

    public static boolean cleanAppCacheDir(int i) {
        return DownloadSetting.obtain(i).optInt(DownloadSettingKeys.KEY_CLEAN_APP_CACHE_DIR, 0) == 1;
    }

    public static boolean shouldModifyApkName(DownloadSetting downloadSetting) {
        return downloadSetting != null && downloadSetting.optInt(DownloadSettingKeys.KLLK_NEED_RENAME_APK, 0) == 1;
    }

    public static boolean shouldParsePackageInfo() {
        return DownloadSetting.obtainGlobal().optBugFix(DownloadSettingKeys.BugFix.FIX_NOTIFICATION_ANR);
    }

    public static boolean isEnableStartInstallAgain() {
        return GlobalInfo.getDownloadSettings().optInt(DownloadSettingKeys.KEY_IS_ENABLE_START_INSTALL_AGAIN) == 1;
    }

    public static long getInstallInterval() {
        long jOptLong = GlobalInfo.getDownloadSettings().optLong(DownloadSettingKeys.KEY_START_INSTALL_INTERVAL);
        if (jOptLong == 0) {
            return 300000L;
        }
        return jOptLong;
    }

    public static long getNextInstallMinInterval() {
        long jOptLong = GlobalInfo.getDownloadSettings().optLong(DownloadSettingKeys.KEY_NEXT_INSTALL_MIN_INTERVAL);
        return jOptLong == 0 ? DownloadConstants.DEFAULT_NEXT_INSTALL_MIN_INTERVAL : jOptLong;
    }
}
