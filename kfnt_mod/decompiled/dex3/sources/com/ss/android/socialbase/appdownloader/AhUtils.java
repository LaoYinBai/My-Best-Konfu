package com.ss.android.socialbase.appdownloader;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.ss.android.download.api.constant.BaseConstants;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.socialbase.appdownloader.AppUtils;
import com.ss.android.socialbase.appdownloader.ah.AbsDevicePlan;
import com.ss.android.socialbase.appdownloader.ah.AndroidUnknownSourcePlan;
import com.ss.android.socialbase.appdownloader.ah.DevicePlans;
import com.ss.android.socialbase.appdownloader.ah.M1UnknownSourcePlan;
import com.ss.android.socialbase.appdownloader.util.AnUtils;
import com.ss.android.socialbase.appdownloader.util.RomUtils;
import com.ss.android.socialbase.appdownloader.view.JumpUnknownSourceActivity;
import com.ss.android.socialbase.downloader.common.AppStatusManager;
import com.ss.android.socialbase.downloader.constants.DownloadConstants;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import com.ss.android.socialbase.downloader.constants.SpJsonConstants;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.thread.WeakDownloadHandler;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.ss.union.game.sdk.account.a.a;
import com.xiaomi.mipush.sdk.Constants;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AhUtils {
    private static final boolean DEBUG = false;
    private static final String TAG = "AhUtils";
    private static AppStatusObserver sLastObserver;
    private static OnAhAttemptListener sOnAhAttemptListener;

    interface ConditionCheckCallable {
        boolean isConditionFit(@NonNull Context context);
    }

    public interface OnAhAttemptListener {
        void onAhAttemptResult(DownloadInfo downloadInfo, AhAttempt ahAttempt);
    }

    public interface Plan {
        public static final String CUSTOM_INSTALLER_MI = "plan_h";
        public static final String CUSTOM_SAVE_PATH = "plan_d";
        public static final String JUMP_BROWSER_INSTALLER = "plan_g";
        public static final String JUMP_FILE_MANAGER = "plan_a";
        public static final String JUMP_FILE_MANAGER2 = "plan_e";
        public static final String JUMP_FILE_MANAGER3 = "plan_f";
        public static final String JUMP_FILE_MANAGER_CUSTOM = "plan_b";
        public static final String JUMP_UNKNOWN_SOURCE = "plan_c";
    }

    public static boolean antiHijack(Context context, DownloadInfo downloadInfo, Intent intent, boolean z) {
        JSONArray jSONArrayOptJSONArray = DownloadSetting.obtain(downloadInfo.getId()).optJSONArray(DownloadSettingKeys.KEY_AH_PLANS);
        if (jSONArrayOptJSONArray == null) {
            return false;
        }
        int length = jSONArrayOptJSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
            if (AnUtils.checkAnConfig(jSONObjectOptJSONObject) && runAntiHijack(context, downloadInfo, intent, jSONObjectOptJSONObject, z)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:101:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0167 A[PHI: r13
  0x0167: PHI (r13v11 com.ss.android.socialbase.appdownloader.AhAttempt) = 
  (r13v2 com.ss.android.socialbase.appdownloader.AhAttempt)
  (r13v9 com.ss.android.socialbase.appdownloader.AhAttempt)
  (r13v12 com.ss.android.socialbase.appdownloader.AhAttempt)
 binds: [B:92:0x0165, B:82:0x013d, B:70:0x010e] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean runAntiHijack(android.content.Context r9, com.ss.android.socialbase.downloader.model.DownloadInfo r10, android.content.Intent r11, org.json.JSONObject r12, boolean r13) throws org.json.JSONException, java.lang.IllegalAccessException, java.lang.NoSuchFieldException, java.lang.IllegalArgumentException {
        /*
            Method dump skipped, instructions count: 470
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.socialbase.appdownloader.AhUtils.runAntiHijack(android.content.Context, com.ss.android.socialbase.downloader.model.DownloadInfo, android.content.Intent, org.json.JSONObject, boolean):boolean");
    }

    private static boolean tryBrowserInstaller(Context context, @NonNull DownloadInfo downloadInfo, JSONObject jSONObject, @NonNull AhAttempt ahAttempt, DownloadSetting downloadSetting) {
        boolean zTryStartActivity;
        String strOptString = jSONObject.optString("type");
        ahAttempt.anti_plan_type = strOptString;
        Intent jumpIntent = DevicePlans.createDevicePlan(context, DevicePlans.DEVICE_PLAN_VIVO_BI, jSONObject, downloadInfo).getJumpIntent();
        StringBuilder sb = new StringBuilder();
        try {
            zTryStartActivity = tryStartActivity(context, jumpIntent);
        } catch (Throwable th) {
            sb.append(strOptString);
            sb.append(" startActivity failed : ");
            sb.append(parseThrowable(th));
            appendErrorCode(ahAttempt, 1);
            zTryStartActivity = false;
        }
        if (!zTryStartActivity) {
            ahAttempt.error_msg = sb.toString();
        } else {
            ahAttempt.error_code = 0;
        }
        return true;
    }

    private static boolean checkDeviceRomMatch(JSONObject jSONObject) {
        return AnUtils.checkDeviceRomMatch(jSONObject);
    }

    public static boolean checkOsApiLevel(JSONObject jSONObject) {
        return AnUtils.checkOsApiLevel(jSONObject);
    }

    public static boolean checkSecure(JSONObject jSONObject) {
        return AnUtils.checkSecure(jSONObject);
    }

    private static boolean jumpFileManagerPage(Context context, DownloadInfo downloadInfo, JSONObject jSONObject, AhAttempt ahAttempt) {
        boolean z;
        if (context == null || jSONObject == null) {
            return false;
        }
        String strOptString = jSONObject.optString("device_plans");
        ahAttempt.device_plans = strOptString;
        if (TextUtils.isEmpty(strOptString)) {
            return false;
        }
        String[] strArrSplit = strOptString.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
        String savePath = downloadInfo.getSavePath();
        if (TextUtils.isEmpty(savePath)) {
            return false;
        }
        File file = new File(savePath);
        StringBuilder sb = new StringBuilder();
        String str = null;
        int length = strArrSplit.length;
        int i = 0;
        while (true) {
            z = true;
            if (i >= length) {
                z = false;
                break;
            }
            String str2 = strArrSplit[i];
            AbsDevicePlan absDevicePlanCreateDevicePlan = DevicePlans.createDevicePlan(context, str2, jSONObject, downloadInfo);
            if (absDevicePlanCreateDevicePlan != null) {
                Intent jumpIntent = absDevicePlanCreateDevicePlan.getJumpIntent();
                if (jumpIntent != null) {
                    if (createDescFile(file, downloadInfo, jSONObject)) {
                        try {
                            tryStartActivity(context, jumpIntent, false);
                            str = str2;
                            break;
                        } catch (Throwable th) {
                            sb.append(str2);
                            sb.append(" startActivity failed : ");
                            sb.append(parseThrowable(th));
                            appendErrorCode(ahAttempt, 1);
                        }
                    } else {
                        appendErrorCode(ahAttempt, 6);
                        sb.append(str2);
                        sb.append(" createDescFile failed! ");
                    }
                } else {
                    appendErrorCode(ahAttempt, 3);
                    sb.append(str2);
                    sb.append(" resolveActivity failed! ");
                }
            }
            sb.append("  ");
            i++;
        }
        if (!z) {
            ahAttempt.error_msg = sb.toString();
        } else {
            ahAttempt.real_device_plan = str;
            ahAttempt.error_code = 0;
        }
        return z;
    }

    private static boolean jumpCustomBrowserPage(Context context, @NonNull DownloadInfo downloadInfo, JSONObject jSONObject, @NonNull AhAttempt ahAttempt) {
        if (context == null || jSONObject == null) {
            return false;
        }
        String savePath = downloadInfo.getSavePath();
        if (TextUtils.isEmpty(savePath)) {
            return false;
        }
        ahAttempt.real_device_plan = "custom";
        AbsDevicePlan absDevicePlanCreateDevicePlan = DevicePlans.createDevicePlan(context, "custom", jSONObject, downloadInfo);
        if (absDevicePlanCreateDevicePlan == null || !absDevicePlanCreateDevicePlan.isValid()) {
            ahAttempt.error_code = 3;
            return false;
        }
        Intent jumpIntent = absDevicePlanCreateDevicePlan.getJumpIntent();
        if (jumpIntent == null) {
            return false;
        }
        if (createDescFile(new File(savePath), downloadInfo, jSONObject)) {
            if (tryStartActivity(context, jumpIntent)) {
                ahAttempt.error_code = 0;
                return true;
            }
            ahAttempt.error_code = 1;
        } else {
            ahAttempt.error_code = 6;
        }
        return false;
    }

    public static int getSavePathRedirectedCode(@NonNull DownloadSetting downloadSetting) {
        int i;
        if (!(downloadSetting.optJSONObject(DownloadSettingKeys.KEY_ANTI_HIJACK_DIR) != null ? !TextUtils.isEmpty(r0.optString(DownloadSettingKeys.AntiHijackDir.KEY_ANTI_HIJACK_DIR_NAME)) : false)) {
            return 5;
        }
        if (!DownloadSetting.obtainGlobal().optBugFix(DownloadSettingKeys.BugFix.BUGFIX_GET_DOWNLOAD_INFO_BY_LIST)) {
            return 4;
        }
        JSONArray jSONArrayOptJSONArray = downloadSetting.optJSONArray(DownloadSettingKeys.KEY_AH_PLANS);
        if (jSONArrayOptJSONArray == null) {
            return -1;
        }
        int length = jSONArrayOptJSONArray.length();
        int i2 = -1;
        for (int i3 = 0; i3 < length; i3++) {
            JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i3);
            if (AnUtils.checkAnConfig(jSONObjectOptJSONObject)) {
                String strOptString = jSONObjectOptJSONObject.optString("type");
                if (Plan.JUMP_FILE_MANAGER.equals(strOptString) || Plan.JUMP_FILE_MANAGER_CUSTOM.equals(strOptString) || Plan.JUMP_FILE_MANAGER2.equals(strOptString) || Plan.JUMP_FILE_MANAGER3.equals(strOptString)) {
                    AhAttempt ahAttemptCheckJumpFileManagerConfig = checkJumpFileManagerConfig(jSONObjectOptJSONObject, downloadSetting);
                    i = ahAttemptCheckJumpFileManagerConfig.error_code;
                    if (ahAttemptCheckJumpFileManagerConfig.error_code == 0) {
                        return 0;
                    }
                } else {
                    if (Plan.CUSTOM_SAVE_PATH.equalsIgnoreCase(strOptString) || Plan.CUSTOM_INSTALLER_MI.equalsIgnoreCase(strOptString)) {
                        return 0;
                    }
                    if (Plan.JUMP_BROWSER_INSTALLER.equalsIgnoreCase(strOptString)) {
                        AhAttempt ahAttemptCheckBrowserInstallConfig = checkBrowserInstallConfig(jSONObjectOptJSONObject, downloadSetting);
                        i = ahAttemptCheckBrowserInstallConfig.error_code;
                        if (ahAttemptCheckBrowserInstallConfig.error_code == 0) {
                            return 0;
                        }
                    } else {
                        continue;
                    }
                }
                i2 = i;
            }
        }
        return i2;
    }

    @NonNull
    public static AhAttempt checkJumpFileManagerConfig(JSONObject jSONObject, DownloadSetting downloadSetting) {
        AhAttempt ahAttempt = new AhAttempt();
        if (jSONObject == null) {
            return ahAttempt;
        }
        String strOptString = jSONObject.optString("type");
        ahAttempt.anti_plan_type = strOptString;
        if (Plan.JUMP_FILE_MANAGER_CUSTOM.equals(strOptString)) {
            ahAttempt.device_plans = "custom";
            if (DevicePlans.checkDevicePlanValid(DownloadComponentManager.getAppContext(), "custom", jSONObject, downloadSetting)) {
                ahAttempt.error_code = 0;
                return ahAttempt;
            }
            appendErrorCode(ahAttempt, 3);
        } else {
            String strOptString2 = jSONObject.optString("device_plans");
            ahAttempt.device_plans = strOptString2;
            if (!TextUtils.isEmpty(strOptString2)) {
                for (String str : strOptString2.split(Constants.ACCEPT_TIME_SEPARATOR_SP)) {
                    if (DevicePlans.checkDevicePlanValid(DownloadComponentManager.getAppContext(), str, jSONObject, downloadSetting)) {
                        ahAttempt.error_code = 0;
                        return ahAttempt;
                    }
                    appendErrorCode(ahAttempt, 3);
                }
            }
        }
        return ahAttempt;
    }

    public static AhAttempt checkBrowserInstallConfig(JSONObject jSONObject, DownloadSetting downloadSetting) {
        AhAttempt ahAttempt = new AhAttempt();
        if (jSONObject == null) {
            return ahAttempt;
        }
        ahAttempt.anti_plan_type = jSONObject.optString("type");
        ahAttempt.device_plans = DevicePlans.DEVICE_PLAN_VIVO_BI;
        if (DevicePlans.checkDevicePlanValid(DownloadComponentManager.getAppContext(), DevicePlans.DEVICE_PLAN_VIVO_BI, jSONObject, downloadSetting)) {
            ahAttempt.error_code = 0;
        } else {
            appendErrorCode(ahAttempt, 3);
        }
        return ahAttempt;
    }

    public static AhAttempt checkCustomSavePathConfig(JSONObject jSONObject) {
        AhAttempt ahAttempt = new AhAttempt();
        if (jSONObject == null) {
            return ahAttempt;
        }
        ahAttempt.error_code = 0;
        return ahAttempt;
    }

    public static AhAttempt checkMInstallConfig(JSONObject jSONObject, String str, Context context, DownloadSetting downloadSetting) {
        AhAttempt ahAttempt = new AhAttempt();
        if (jSONObject == null || !RomUtils.isMiui()) {
            return ahAttempt;
        }
        ahAttempt.anti_plan_type = jSONObject.optString("type");
        if (downloadSetting.optInt("bi", 0) == 1) {
            ahAttempt.error_code = 0;
            return ahAttempt;
        }
        if (isUnknownSourceEnabled(context)) {
            ahAttempt.error_code = 2;
        } else if (AnUtils.queryPackageInfo(str) != null) {
            ahAttempt.error_code = 0;
        } else {
            ahAttempt.error_code = 9;
        }
        return ahAttempt;
    }

    private static void appendErrorCode(AhAttempt ahAttempt, int i) {
        if (ahAttempt.error_code != -1) {
            ahAttempt.error_code = (ahAttempt.error_code * 10) + i;
        } else {
            ahAttempt.error_code = i;
        }
    }

    public static boolean checkRequirements(JSONArray jSONArray) {
        return AnUtils.checkRequirements(jSONArray);
    }

    public static AppUtils.AppInfo queryPackageInfo(String str) {
        return AnUtils.queryPackageInfo(str);
    }

    private static boolean createDescFile(File file, DownloadInfo downloadInfo, @NonNull JSONObject jSONObject) {
        if (file == null) {
            return false;
        }
        String path = file.getPath();
        JSONObject jSONObjectOptJSONObject = DownloadSetting.obtain(downloadInfo.getId()).optJSONObject(DownloadSettingKeys.KEY_ANTI_HIJACK_DIR);
        File file2 = null;
        String strOptString = jSONObjectOptJSONObject != null ? jSONObjectOptJSONObject.optString(DownloadSettingKeys.AntiHijackDir.KEY_ANTI_HIJACK_INSTALL_DESC) : null;
        if (!TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(strOptString)) {
            file2 = new File(path + File.separator + strOptString);
        }
        if (file2 == null) {
            return true;
        }
        try {
            if (!file2.createNewFile()) {
                return true;
            }
            file2.deleteOnExit();
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public static boolean tryShowUnknownSource(Context context, @Nullable Intent intent, JSONObject jSONObject, int i, @Nullable AhAttempt ahAttempt) throws JSONException {
        if (context == null || jSONObject == null) {
            return false;
        }
        long jOptLong = jSONObject.optLong(DownloadSettingKeys.AhPlans.KEY_JUMP_INTERVAL, 0L);
        if (jOptLong <= 0) {
            return false;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(DownloadConstants.SP_ANTI_HIJACK_CONFIG, 0);
        if ((System.currentTimeMillis() - sharedPreferences.getLong(SpJsonConstants.KEY_LAST_JUMP_UNKNOWN_SOURCE_TIME, 0L)) / 60000 < jOptLong || isUnknownSourceEnabled(context)) {
            return false;
        }
        sharedPreferences.edit().putLong(SpJsonConstants.KEY_LAST_JUMP_UNKNOWN_SOURCE_TIME, System.currentTimeMillis()).apply();
        if (jSONObject.optInt(DownloadSettingKeys.AhPlans.KEY_SHOW_UNKNOWN_SOURCE_DIALOG, 0) == 1) {
            Intent intent2 = new Intent(context, (Class<?>) JumpUnknownSourceActivity.class);
            intent2.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
            intent2.putExtra(com.ss.android.socialbase.appdownloader.constants.Constants.INTENT, intent);
            intent2.putExtra("config", jSONObject.toString());
            intent2.putExtra(AdDownloadModel.JsonKey.ID, i);
            try {
                if (tryStartActivity(context, intent2, false)) {
                    sendGuideAuthDialogShowEvent(i, jSONObject);
                }
                return true;
            } catch (Throwable th) {
                if (ahAttempt != null) {
                    ahAttempt.error_code = 1;
                    ahAttempt.error_msg = "tryShowUnknownSourceDialog" + parseThrowable(th);
                }
                return false;
            }
        }
        if (realJumpUnknownSource(context, intent, i, jSONObject)) {
            sendGuideAuthOpenSettingEvent(i, jSONObject);
        }
        return true;
    }

    public static boolean realJumpUnknownSource(Context context, @Nullable Intent intent, int i, JSONObject jSONObject) {
        try {
            if (RomUtils.isMiui() && Build.VERSION.SDK_INT < 26 && !isXiaomiUnknownSourcesEnabled(context)) {
                M1UnknownSourcePlan m1UnknownSourcePlan = new M1UnknownSourcePlan(context);
                if (m1UnknownSourcePlan.isValid()) {
                    monitor(context, intent, i, jSONObject, new ConditionCheckCallable() { // from class: com.ss.android.socialbase.appdownloader.AhUtils.1
                        @Override // com.ss.android.socialbase.appdownloader.AhUtils.ConditionCheckCallable
                        public boolean isConditionFit(@NonNull Context context2) {
                            return AhUtils.isXiaomiUnknownSourcesEnabled(context2);
                        }
                    });
                    return tryStartActivity(context, m1UnknownSourcePlan.getJumpIntent());
                }
            } else {
                if (Build.VERSION.SDK_INT < 26 || context.getApplicationInfo().targetSdkVersion < 26 || isAndroidOUnknownSourcesEnabled(context)) {
                    return false;
                }
                AndroidUnknownSourcePlan androidUnknownSourcePlan = new AndroidUnknownSourcePlan(context);
                if (androidUnknownSourcePlan.isValid()) {
                    monitor(context, intent, i, jSONObject, new ConditionCheckCallable() { // from class: com.ss.android.socialbase.appdownloader.AhUtils.2
                        @Override // com.ss.android.socialbase.appdownloader.AhUtils.ConditionCheckCallable
                        public boolean isConditionFit(@NonNull Context context2) {
                            return AhUtils.isAndroidOUnknownSourcesEnabled(context2);
                        }
                    });
                    return tryStartActivity(context, androidUnknownSourcePlan.getJumpIntent());
                }
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean isUnknownSourceEnabled(Context context) {
        if (context == null) {
            return true;
        }
        try {
            if (RomUtils.isMiui() && Build.VERSION.SDK_INT < 26) {
                return isXiaomiUnknownSourcesEnabled(context);
            }
            if (Build.VERSION.SDK_INT < 26 || context.getApplicationInfo().targetSdkVersion < 26) {
                return true;
            }
            return isAndroidOUnknownSourcesEnabled(context);
        } catch (Throwable unused) {
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isXiaomiUnknownSourcesEnabled(Context context) {
        if (context == null) {
            return true;
        }
        try {
            return Settings.Secure.getInt(context.getContentResolver(), "install_non_market_apps", 1) > 0;
        } catch (Throwable unused) {
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(api = 26)
    public static boolean isAndroidOUnknownSourcesEnabled(Context context) {
        if (context == null) {
            return true;
        }
        try {
            return context.getPackageManager().canRequestPackageInstalls();
        } catch (Throwable unused) {
            return true;
        }
    }

    public static boolean isProgressInJumpUnKnowSource() {
        return QueryHandlerImpl.currentStatus == 1;
    }

    private static void sendGuideAuthDialogShowEvent(int i, JSONObject jSONObject) throws JSONException {
        int i2 = 1;
        boolean z = jSONObject.optInt(DownloadSettingKeys.AhPlans.KEY_ALLOW_UNKNOWN_SOURCE_ON_STARTUP) == 1;
        JSONObject jSONObject2 = new JSONObject();
        if (!z) {
            i2 = 2;
        }
        try {
            jSONObject2.put(com.ss.android.downloadlib.constants.DownloadConstants.EVENT_SCENE, i2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DownloadComponentManager.getEventListener().onUnityEvent(i, MonitorConstants.UnityLabel.GUIDE_AUTH_DIALOG_SHOW, jSONObject2);
    }

    public static void sendGuideAuthDialogConfirmEvent(int i, JSONObject jSONObject) throws JSONException {
        int i2 = 1;
        boolean z = jSONObject.optInt(DownloadSettingKeys.AhPlans.KEY_ALLOW_UNKNOWN_SOURCE_ON_STARTUP) == 1;
        JSONObject jSONObject2 = new JSONObject();
        if (!z) {
            i2 = 2;
        }
        try {
            jSONObject2.put(com.ss.android.downloadlib.constants.DownloadConstants.EVENT_SCENE, i2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DownloadComponentManager.getEventListener().onUnityEvent(i, MonitorConstants.UnityLabel.GUIDE_AUTH_DIALOG_CONFIRM, jSONObject2);
    }

    public static void sendGuideAuthDialogCancelEvent(int i, JSONObject jSONObject) throws JSONException {
        int i2 = 1;
        boolean z = jSONObject.optInt(DownloadSettingKeys.AhPlans.KEY_ALLOW_UNKNOWN_SOURCE_ON_STARTUP) == 1;
        JSONObject jSONObject2 = new JSONObject();
        if (!z) {
            i2 = 2;
        }
        try {
            jSONObject2.put(com.ss.android.downloadlib.constants.DownloadConstants.EVENT_SCENE, i2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DownloadComponentManager.getEventListener().onUnityEvent(i, MonitorConstants.UnityLabel.GUIDE_AUTH_DIALOG_CANCEL, jSONObject2);
    }

    public static void sendGuideAuthOpenSettingEvent(int i, JSONObject jSONObject) throws JSONException {
        int i2 = 1;
        boolean z = jSONObject.optInt(DownloadSettingKeys.AhPlans.KEY_ALLOW_UNKNOWN_SOURCE_ON_STARTUP) == 1;
        JSONObject jSONObject2 = new JSONObject();
        if (!z) {
            i2 = 2;
        }
        try {
            jSONObject2.put(com.ss.android.downloadlib.constants.DownloadConstants.EVENT_SCENE, i2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DownloadComponentManager.getEventListener().onUnityEvent(i, MonitorConstants.UnityLabel.GUIDE_AUTH_OPEN_SETTING, jSONObject2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void sendGuideAuthResultEvent(int i, boolean z, boolean z2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(com.ss.android.downloadlib.constants.DownloadConstants.EVENT_SCENE, z ? 1 : 2);
            jSONObject.put(a.V, z2 ? 1 : 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DownloadComponentManager.getEventListener().onUnityEvent(i, MonitorConstants.UnityLabel.GUIDE_AUTH_RESULT, jSONObject);
    }

    private static void monitor(Context context, Intent intent, int i, JSONObject jSONObject, ConditionCheckCallable conditionCheckCallable) {
        if (sLastObserver != null) {
            AppStatusManager.getInstance().unregisterAppSwitchListener(sLastObserver);
            sLastObserver = null;
        }
        sLastObserver = new AppStatusObserver(context, intent, i, jSONObject, conditionCheckCallable);
        AppStatusManager.getInstance().registerAppSwitchListener(sLastObserver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean tryStartActivity(Context context, Intent intent) {
        return tryStartActivity(context, intent, true);
    }

    public static boolean tryStartActivity(Context context, Intent intent, boolean z) {
        if (context == null || intent == null) {
            return false;
        }
        if (z) {
            try {
                intent.putExtra(BaseConstants.START_ONLY_FOR_ANDROID, true);
                context.startActivity(intent);
                return true;
            } catch (Throwable unused) {
                return false;
            }
        }
        intent.putExtra(BaseConstants.START_ONLY_FOR_ANDROID, true);
        context.startActivity(intent);
        return true;
    }

    public static String parseThrowable(Throwable th) {
        String string = th.toString();
        return string.length() > 800 ? string.substring(0, 500) : string;
    }

    private static class QueryCallable implements Callable<Boolean> {
        private final ConditionCheckCallable mConditionCheck;
        private final Context mContext;
        private final Handler mainHandler;
        private final long queryInterval;

        public QueryCallable(Handler handler, Context context, ConditionCheckCallable conditionCheckCallable, long j) {
            this.mContext = context;
            this.mConditionCheck = conditionCheckCallable;
            this.mainHandler = handler;
            this.queryInterval = j;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public Boolean call() throws Exception {
            if (this.mConditionCheck != null && this.queryInterval > 0 && this.queryInterval <= com.ss.android.downloadlib.constants.DownloadConstants.DEFAULT_NEXT_INSTALL_MIN_INTERVAL) {
                Context context = this.mContext;
                boolean zIsConditionFit = context != null ? this.mConditionCheck.isConditionFit(context) : false;
                Message messageObtain = Message.obtain();
                if (zIsConditionFit) {
                    messageObtain.what = 2;
                    this.mainHandler.sendMessage(messageObtain);
                } else {
                    messageObtain.what = 1;
                    this.mainHandler.sendMessageDelayed(messageObtain, this.queryInterval);
                }
                return false;
            }
            return false;
        }
    }

    public static void setOnAhAttemptListener(OnAhAttemptListener onAhAttemptListener) {
        sOnAhAttemptListener = onAhAttemptListener;
    }

    private static class AppStatusObserver implements AppStatusManager.AppStatusChangeListener {
        private JSONObject antiConfig;
        private final QueryHandlerImpl queryHandlerImpl;
        private final int queryInterval;

        public AppStatusObserver(Context context, Intent intent, int i, JSONObject jSONObject, ConditionCheckCallable conditionCheckCallable) {
            this.antiConfig = jSONObject;
            this.queryInterval = jSONObject.optInt(DownloadSettingKeys.AhPlans.KEY_JUMP_UNKNWON_SOURCE_QUERY_INTERVAL, 1000);
            this.queryHandlerImpl = new QueryHandlerImpl(context, intent, i, conditionCheckCallable, this.queryInterval);
        }

        @Override // com.ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
        public void onAppForeground() {
            if (!this.queryHandlerImpl.isOriginActivityStarted) {
                Message messageObtain = Message.obtain();
                messageObtain.what = 2;
                this.queryHandlerImpl.mainHandler.sendMessage(messageObtain);
            }
            AppStatusManager.getInstance().unregisterAppSwitchListener(this);
            AppStatusObserver unused = AhUtils.sLastObserver = null;
        }

        @Override // com.ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
        public void onAppBackground() {
            int iOptInt = this.antiConfig.optInt(DownloadSettingKeys.AhPlans.KEY_JUMP_UNKNWON_SOURCE_WAIT_TIME_OUT, 20);
            Message messageObtain = Message.obtain();
            messageObtain.what = 1;
            this.queryHandlerImpl.mainHandler.sendMessage(messageObtain);
            if (iOptInt <= 0 || iOptInt >= 60) {
                return;
            }
            Message messageObtain2 = Message.obtain();
            messageObtain2.what = 2;
            this.queryHandlerImpl.mainHandler.sendMessageDelayed(messageObtain2, iOptInt * 1000);
        }
    }

    private static class QueryHandlerImpl implements WeakDownloadHandler.IHandler {
        public static final int WHAT_CANCEL_FUTURE_AND_START_ACTIVITY = 2;
        public static final int WHAT_QUERY_NEXT = 1;
        public static int currentStatus;
        private static int id;
        private final ConditionCheckCallable conditionCheck;
        private final Context context;
        private Future<Boolean> future;
        private boolean isOriginActivityStarted = false;
        private final Handler mainHandler;
        private final Intent originIntent;
        private final long queryInterval;

        public QueryHandlerImpl(Context context, Intent intent, int i, ConditionCheckCallable conditionCheckCallable, long j) {
            this.context = context;
            this.originIntent = intent;
            id = i;
            this.conditionCheck = conditionCheckCallable;
            this.mainHandler = new WeakDownloadHandler(Looper.getMainLooper(), this);
            this.queryInterval = j;
        }

        @Override // com.ss.android.socialbase.downloader.thread.WeakDownloadHandler.IHandler
        public void handleMsg(Message message) throws JSONException {
            if (message != null) {
                if (message.what == 1) {
                    if (this.queryInterval <= 0 || this.queryInterval > com.ss.android.downloadlib.constants.DownloadConstants.DEFAULT_NEXT_INSTALL_MIN_INTERVAL) {
                        return;
                    }
                    currentStatus = 1;
                    this.future = DownloadComponentManager.getCPUThreadExecutor().submit(new QueryCallable(this.mainHandler, this.context, this.conditionCheck, this.queryInterval));
                    return;
                }
                if (message.what == 2) {
                    currentStatus = 2;
                    this.mainHandler.removeMessages(2);
                    this.mainHandler.removeMessages(1);
                    if (this.future != null) {
                        this.future.cancel(true);
                    }
                    if (!this.isOriginActivityStarted && (Build.VERSION.SDK_INT < 29 || AppStatusManager.getInstance().isAppForeground())) {
                        if (this.originIntent != null) {
                            AhUtils.tryStartActivity(this.context, this.originIntent);
                        } else {
                            DownloadInfo downloadInfo = Downloader.getInstance(this.context).getDownloadInfo(id);
                            if (downloadInfo != null && downloadInfo.isDownloadOverStatus()) {
                                AppDownloadUtils.proxyStartViewIntent(this.context, id, false);
                            }
                        }
                        this.isOriginActivityStarted = true;
                    }
                    AhUtils.sendGuideAuthResultEvent(id, this.originIntent == null, AhUtils.isUnknownSourceEnabled(this.context));
                }
            }
        }
    }
}
