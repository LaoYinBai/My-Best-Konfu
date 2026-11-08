package com.ss.android.socialbase.appdownloader;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import com.qq.e.comm.compliance.ApkDownloadComplianceInterface;
import com.ss.android.downloadlib.constants.EventConstants;
import com.ss.android.socialbase.appdownloader.constants.Constants;
import com.ss.android.socialbase.appdownloader.depend.AbsAppDownloadDepend;
import com.ss.android.socialbase.appdownloader.depend.IAppDownloadDepend;
import com.ss.android.socialbase.appdownloader.depend.IAppDownloadEventHandler;
import com.ss.android.socialbase.appdownloader.depend.IAppInstallInterceptCallback;
import com.ss.android.socialbase.appdownloader.depend.IBeforeAppInstallInterceptor;
import com.ss.android.socialbase.appdownloader.setting.DownloadSettingKeys;
import com.ss.android.socialbase.appdownloader.util.RomUtils;
import com.ss.android.socialbase.appdownloader.util.package_info.PackageInfoUtils;
import com.ss.android.socialbase.downloader.constants.DownloadConstants;
import com.ss.android.socialbase.downloader.constants.DownloadStatus;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import com.ss.android.socialbase.downloader.depend.IDownloadNotificationEventListener;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.monitor.IDownloadMonitorListener;
import com.ss.android.socialbase.downloader.notification.DownloadNotificationManager;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import com.ss.android.socialbase.downloader.utils.SystemUtils;
import com.taobao.accs.data.Message;
import com.umeng.message.entity.UMessage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AppDownloadUtils {
    public static final String BIND_APP_EXTRA = "bind_app";
    private static final String DEFAULT_CHANNEL_ID = "111111";
    private static final String DEFAULT_CHANNEL_NAME = "channel_appdownloader";
    public static final String MIUI_SECURITY_NAME = "com.miui.enterprise.service.EntInstallService";
    public static final String MIUI_SECURITY_PKG = "com.miui.securitycore";
    public static final int START_VIEW_INTENT_FAILED = 0;
    public static final int START_VIEW_INTENT_INTERCEPT = 2;
    public static final int START_VIEW_INTENT_SUCCESS = 1;
    private static final String TAG = "AppDownloadUtils";
    public static final int TYPE_ACTIVE = 1;
    public static final int TYPE_COMPLETE = 3;
    public static final int TYPE_PREPARE = 4;
    public static final int TYPE_WAITING = 2;
    private static NotificationChannel mNotificationChannel;
    private static int sStartViewIntentResult;

    private static String format(long j, long j2, String str, boolean z) {
        double d = j2 > 1 ? j / j2 : j;
        if (z || "GB".equals(str) || "TB".equals(str)) {
            return new DecimalFormat("#.##").format(d) + " " + str;
        }
        return new DecimalFormat("#").format(d) + " " + str;
    }

    public static String bytesToHuman(long j) {
        return bytesToHuman(j, true);
    }

    public static String bytesToHuman(long j, boolean z) {
        long[] jArr = {DownloadConstants.TB, DownloadConstants.GB, 1048576, DownloadConstants.KB, 1};
        String[] strArr = {"TB", "GB", "MB", "KB", "B"};
        if (j < 1) {
            return "0 " + strArr[strArr.length - 1];
        }
        for (int i = 0; i < jArr.length; i++) {
            long j2 = jArr[i];
            if (j >= j2) {
                return format(j, j2, strArr[i], z);
            }
        }
        return null;
    }

    private static String formatOnSpaceError(long j, long j2, String str) {
        double d = j2 > 1 ? j / j2 : j;
        if ("MB".equals(str)) {
            return new DecimalFormat("#").format(d) + str;
        }
        return new DecimalFormat("#.##").format(d) + str;
    }

    public static String bytesToHumanOnSpaceError(long j) {
        long[] jArr = {DownloadConstants.TB, DownloadConstants.GB, 1048576, DownloadConstants.KB, 1};
        String[] strArr = {"TB", "GB", "MB", "KB", "B"};
        if (j < 1) {
            return "0 " + strArr[strArr.length - 1];
        }
        for (int i = 0; i < jArr.length; i++) {
            long j2 = jArr[i];
            if (j >= j2) {
                return formatOnSpaceError(j, j2, strArr[i]);
            }
        }
        return null;
    }

    public static int startViewIntent(final Context context, final int i, final boolean z) {
        IBeforeAppInstallInterceptor beforeAppInstallInterceptor = AppDownloader.getInstance().getBeforeAppInstallInterceptor();
        if (beforeAppInstallInterceptor == null) {
            return startViewIntentInner(context, i, z);
        }
        DownloadInfo downloadInfo = Downloader.getInstance(context).getDownloadInfo(i);
        sStartViewIntentResult = 1;
        beforeAppInstallInterceptor.intercept(downloadInfo, new IAppInstallInterceptCallback() { // from class: com.ss.android.socialbase.appdownloader.AppDownloadUtils.1
            @Override // com.ss.android.socialbase.appdownloader.depend.IAppInstallInterceptCallback
            public void onInterceptFinish() {
                int unused = AppDownloadUtils.sStartViewIntentResult = AppDownloadUtils.startViewIntentInner(context, i, z);
            }
        });
        return sStartViewIntentResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int startViewIntentInner(Context context, int i, boolean z) {
        if (DownloadSetting.obtain(i).optInt(DownloadSettingKeys.NOTIFICATION_OPT_2) == 1) {
            DownloadNotificationManager.getInstance().cancelNotification(i);
        }
        safeFinish(InstallQueue.getInstance().popActivity());
        if (DownloadSetting.obtain(i).optInt(DownloadSettingKeys.KEY_INSTALL_QUEUE_ENABLE, 0) == 1) {
            return InstallQueue.getInstance().offerInstallRequest(context, i, z);
        }
        return proxyStartViewIntent(context, i, z);
    }

    public static int proxyStartViewIntent(final Context context, final int i, final boolean z) throws JSONException {
        final DownloadInfo downloadInfo = Downloader.getInstance(context).getDownloadInfo(i);
        if (downloadInfo != null && "application/vnd.android.package-archive".equals(downloadInfo.getMimeType()) && !TextUtils.isEmpty(downloadInfo.getSavePath()) && !TextUtils.isEmpty(downloadInfo.getName())) {
            final File file = new File(downloadInfo.getSavePath(), downloadInfo.getName());
            if (file.exists()) {
                DownloadComponentManager.submitIOTask(new Runnable() { // from class: com.ss.android.socialbase.appdownloader.AppDownloadUtils.2
                    @Override // java.lang.Runnable
                    public void run() throws Throwable {
                        int iRealStartViewIntent = AppDownloadUtils.realStartViewIntent(context, i, z, downloadInfo, file);
                        if (iRealStartViewIntent == 1 && AppDownloader.getInstance().getOpenInstallerListener() != null) {
                            AppDownloader.getInstance().getOpenInstallerListener().onOpenInstaller(downloadInfo, null);
                        }
                        AppDownloadUtils.sendInstallViewResultEvent(downloadInfo, z, iRealStartViewIntent);
                    }
                });
                return 1;
            }
        }
        sendInstallViewResultEvent(downloadInfo, z, 2);
        return 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void sendInstallViewResultEvent(DownloadInfo downloadInfo, boolean z, int i) throws JSONException {
        if (downloadInfo == null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("by_user", z ? 1 : 2);
            jSONObject.put("view_result", i);
            jSONObject.put(EventConstants.ExtraJson.REAL_PACKAGE_NAME, downloadInfo.getFilePackageName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        DownloadComponentManager.getEventListener().onEvent(downloadInfo.getId(), MonitorConstants.EventLabel.INSTALL_VIEW_RESULT, jSONObject);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0041 A[PHI: r0
  0x0041: PHI (r0v5 java.lang.Process) = (r0v4 java.lang.Process), (r0v8 java.lang.Process) binds: [B:15:0x003f, B:6:0x0033] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0097 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0053 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11, types: [android.content.pm.PackageInfo] */
    /* JADX WARN: Type inference failed for: r0v18, types: [android.content.pm.PackageInfo] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int realStartViewIntent(android.content.Context r5, int r6, boolean r7, com.ss.android.socialbase.downloader.model.DownloadInfo r8, java.io.File r9) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 299
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.socialbase.appdownloader.AppDownloadUtils.realStartViewIntent(android.content.Context, int, boolean, com.ss.android.socialbase.downloader.model.DownloadInfo, java.io.File):int");
    }

    public static int startPackageInstaller(Context context, Intent intent) {
        try {
            if (AppDownloader.getInstance().getInstallAppHandler() != null) {
                if (AppDownloader.getInstance().getInstallAppHandler().installApp(intent)) {
                    return 1;
                }
            }
        } catch (Throwable unused) {
        }
        try {
            context.startActivity(intent);
            return 1;
        } catch (Throwable unused2) {
            return 0;
        }
    }

    public static boolean forbidInstallWhenPkgNameError(Context context, DownloadInfo downloadInfo, PackageInfo packageInfo) {
        if (packageInfo == null || packageInfo.packageName.equals(downloadInfo.getPackageName())) {
            return false;
        }
        IAppDownloadEventHandler appDownloadEventHandler = AppDownloader.getInstance().getAppDownloadEventHandler();
        if (appDownloadEventHandler != null) {
            appDownloadEventHandler.handleAppInstallError(downloadInfo.getId(), 8, downloadInfo.getPackageName(), packageInfo.packageName, "");
            if (appDownloadEventHandler.isForbidInvalidatePackageInstall()) {
                return true;
            }
        }
        IDownloadNotificationEventListener downloadNotificationEventListener = Downloader.getInstance(context).getDownloadNotificationEventListener(downloadInfo.getId());
        if (downloadNotificationEventListener == null) {
            return false;
        }
        downloadNotificationEventListener.onNotificationEvent(8, downloadInfo, packageInfo.packageName, "");
        IAppDownloadDepend appDownloadDepend = AppDownloader.getInstance().getAppDownloadDepend();
        return (appDownloadDepend instanceof AbsAppDownloadDepend) && ((AbsAppDownloadDepend) appDownloadDepend).isForbiddenInstallForInvalidatePackageName();
    }

    public static boolean silentInstallOnMiui(Context context, int i, File file) {
        if (DownloadSetting.obtain(i).optInt(DownloadSettingKeys.BACK_MIUI_SILENT_INSTALL, 1) == 1) {
            return false;
        }
        if ((!RomUtils.isMiuiV10() && !RomUtils.isMiuiV11()) || !SystemUtils.checkServiceExists(context, MIUI_SECURITY_PKG, MIUI_SECURITY_NAME)) {
            return false;
        }
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(MIUI_SECURITY_PKG, MIUI_SECURITY_NAME));
        Bundle bundle = new Bundle();
        bundle.putInt("userId", 0);
        bundle.putInt("flag", ApkDownloadComplianceInterface.INSTALL_BITS);
        bundle.putString("apkPath", file.getPath());
        bundle.putString("installerPkg", MIUI_SECURITY_PKG);
        intent.putExtras(bundle);
        try {
            context.startService(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int getPackageInfoFlag() {
        if (AppDownloader.getInstance().isUsePackageFlagConfiguration()) {
            return Message.FLAG_REQ_BIT1;
        }
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0021 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.net.Uri getUriForFile(int r1, com.ss.android.socialbase.downloader.depend.IDownloadFileUriProvider r2, android.content.Context r3, java.lang.String r4, java.io.File r5) {
        /*
            if (r2 == 0) goto Lb
            java.lang.String r1 = r5.getAbsolutePath()     // Catch: java.lang.Throwable -> L1e
            android.net.Uri r1 = r2.getUriForFile(r4, r1)     // Catch: java.lang.Throwable -> L1e
            goto L1f
        Lb:
            com.ss.android.socialbase.appdownloader.AppDownloader r2 = com.ss.android.socialbase.appdownloader.AppDownloader.getInstance()
            com.ss.android.socialbase.appdownloader.depend.IAppDownloadFileUriProvider r2 = r2.getAppFileUriProvider()
            if (r2 == 0) goto L1e
            java.lang.String r0 = r5.getAbsolutePath()     // Catch: java.lang.Throwable -> L1e
            android.net.Uri r1 = r2.getUriForFile(r1, r4, r0)     // Catch: java.lang.Throwable -> L1e
            goto L1f
        L1e:
            r1 = 0
        L1f:
            if (r1 != 0) goto L3c
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L38
            r0 = 24
            if (r2 < r0) goto L32
            boolean r2 = android.text.TextUtils.isEmpty(r4)     // Catch: java.lang.Throwable -> L38
            if (r2 != 0) goto L32
            android.net.Uri r2 = androidx.core.content.FileProvider.getUriForFile(r3, r4, r5)     // Catch: java.lang.Throwable -> L38
            goto L36
        L32:
            android.net.Uri r2 = android.net.Uri.fromFile(r5)     // Catch: java.lang.Throwable -> L38
        L36:
            r1 = r2
            goto L3c
        L38:
            r2 = move-exception
            r2.printStackTrace()
        L3c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.socialbase.appdownloader.AppDownloadUtils.getUriForFile(int, com.ss.android.socialbase.downloader.depend.IDownloadFileUriProvider, android.content.Context, java.lang.String, java.io.File):android.net.Uri");
    }

    private static Intent buildViewIntent(Context context, DownloadInfo downloadInfo, @NonNull File file, boolean z, int[] iArr) {
        Uri uriForFile = getUriForFile(downloadInfo.getId(), Downloader.getInstance(context).getDownloadFileUriProvider(downloadInfo.getId()), context, AppDownloader.getInstance().getFileProviderAuthority(), file);
        if (uriForFile == null) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        if (Build.VERSION.SDK_INT >= 24) {
            intent.addFlags(1);
        }
        intent.setDataAndType(uriForFile, "application/vnd.android.package-archive");
        IAppDownloadEventHandler appDownloadEventHandler = AppDownloader.getInstance().getAppDownloadEventHandler();
        boolean zInstallIntercept = appDownloadEventHandler != null ? appDownloadEventHandler.installIntercept(downloadInfo.getId(), z) : false;
        IDownloadNotificationEventListener downloadNotificationEventListener = Downloader.getInstance(context).getDownloadNotificationEventListener(downloadInfo.getId());
        if (downloadNotificationEventListener != null) {
            zInstallIntercept = downloadNotificationEventListener.interceptAfterNotificationSuccess(z);
        }
        iArr[0] = zInstallIntercept ? 1 : 0;
        if (zInstallIntercept) {
            return null;
        }
        return intent;
    }

    public static boolean isPackageNameValid(DownloadInfo downloadInfo, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (TextUtils.isEmpty(downloadInfo.getPackageName()) || !str.equals(downloadInfo.getPackageName())) {
            return !TextUtils.isEmpty(downloadInfo.getName()) && isPackageNameEqualsWithApk(DownloadComponentManager.getAppContext(), downloadInfo, str);
        }
        return true;
    }

    public static boolean isPackageNameEqualsWithApk(Context context, DownloadInfo downloadInfo, String str) throws PackageManager.NameNotFoundException {
        PackageInfo packageInfo;
        PackageInfo packageInfo2;
        if (context == null) {
            return false;
        }
        try {
            File file = new File(downloadInfo.getSavePath(), downloadInfo.getName());
            if (file.exists()) {
                Log.e(TAG, "isPackageNameEqualsWithApk fileName:" + downloadInfo.getName() + " apkFileSize：" + file.length() + " fileUrl：" + downloadInfo.getUrl());
                PackageInfo packageInfo3 = getPackageInfo(downloadInfo, file);
                if (packageInfo3 == null || !packageInfo3.packageName.equals(str)) {
                    return false;
                }
                int i = packageInfo3.versionCode;
                try {
                    packageInfo2 = context.getPackageManager().getPackageInfo(str, getPackageInfoFlag());
                } catch (PackageManager.NameNotFoundException unused) {
                    packageInfo2 = null;
                }
                if (packageInfo2 == null || i != packageInfo2.versionCode) {
                    return false;
                }
            } else {
                if (!DownloadSetting.obtain(downloadInfo.getId()).optBugFix(DownloadSettingKeys.BugFix.BUGFIX_INSTALL_CALLBACK_ERROR)) {
                    return false;
                }
                String string = DownloadUtils.getString(downloadInfo.getTempCacheData().get(Constants.EXTRA_APK_PACKAGE_NAME), null);
                int i2 = DownloadUtils.getInt(downloadInfo.getTempCacheData().get(Constants.EXTRA_APK_VERSION_CODE), 0);
                if (string == null || TextUtils.isEmpty(string) || !string.equals(str)) {
                    return false;
                }
                try {
                    packageInfo = context.getPackageManager().getPackageInfo(str, getPackageInfoFlag());
                } catch (PackageManager.NameNotFoundException unused2) {
                    packageInfo = null;
                }
                if (packageInfo == null || i2 != packageInfo.versionCode) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isApkInstalled(Context context, DownloadInfo downloadInfo, PackageInfo packageInfo) {
        return isApkInstalled(context, downloadInfo, packageInfo, false);
    }

    public static boolean isApkInstalled(Context context, DownloadInfo downloadInfo, PackageInfo packageInfo, boolean z) throws PackageManager.NameNotFoundException {
        PackageInfo packageInfo2;
        if (packageInfo == null) {
            return false;
        }
        String str = packageInfo.packageName;
        int i = packageInfo.versionCode;
        if (downloadInfo != null) {
            downloadInfo.setAppVersionCode(i);
        }
        try {
            packageInfo2 = context.getPackageManager().getPackageInfo(str, getPackageInfoFlag());
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo2 = null;
        }
        if (packageInfo2 == null) {
            return false;
        }
        int i2 = packageInfo2.versionCode;
        return z ? i < i2 : (downloadInfo == null || DownloadSetting.obtain(downloadInfo.getId()).optInt(DownloadSettingKeys.INSTALL_WITH_SAME_VERSION_CODE, 0) != 1) ? i <= i2 : i < i2;
    }

    public static boolean isApkInstalled(Context context, DownloadInfo downloadInfo) {
        return isApkInstalled(context, downloadInfo, true);
    }

    public static boolean isApkInstalled(Context context, DownloadInfo downloadInfo, boolean z) throws PackageManager.NameNotFoundException {
        PackageInfo packageInfo;
        if (downloadInfo == null) {
            return false;
        }
        String packageName = downloadInfo.getPackageName();
        int appVersionCode = downloadInfo.getAppVersionCode();
        if (appVersionCode > 0 || !z) {
            try {
                packageInfo = context.getPackageManager().getPackageInfo(packageName, getPackageInfoFlag());
            } catch (PackageManager.NameNotFoundException unused) {
                packageInfo = null;
            }
            if (packageInfo == null) {
                return false;
            }
            return DownloadSetting.obtain(downloadInfo.getId()).optInt(DownloadSettingKeys.INSTALL_WITH_SAME_VERSION_CODE, 0) == 1 ? appVersionCode < packageInfo.versionCode : appVersionCode <= packageInfo.versionCode;
        }
        return isApkInstalledByFile(context, downloadInfo);
    }

    private static void reportViaMonitorListener(@NonNull String str, @NonNull String str2) throws JSONException {
        IDownloadMonitorListener downloadMonitorListener = DownloadComponentManager.getDownloadMonitorListener();
        if (downloadMonitorListener == null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("message", str2);
        } catch (JSONException unused) {
        }
        downloadMonitorListener.monitorEvent(str, jSONObject, null, null);
    }

    public static int getApkFileVersionCode(Context context, DownloadInfo downloadInfo) {
        if (context == null || downloadInfo == null || TextUtils.isEmpty(downloadInfo.getSavePath()) || TextUtils.isEmpty(downloadInfo.getName())) {
            return 0;
        }
        int appVersionCode = downloadInfo.getAppVersionCode();
        if (appVersionCode > 0) {
            return appVersionCode;
        }
        try {
            PackageInfo apkFile = parseApkFile(context, downloadInfo, downloadInfo.getSavePath(), downloadInfo.getName());
            if (apkFile != null) {
                int i = apkFile.versionCode;
                downloadInfo.setAppVersionCode(i);
                return i;
            }
        } catch (Throwable unused) {
        }
        return 0;
    }

    public static boolean isApkInstalledByFile(Context context, DownloadInfo downloadInfo) {
        if (context == null || downloadInfo == null || TextUtils.isEmpty(downloadInfo.getSavePath()) || TextUtils.isEmpty(downloadInfo.getName())) {
            return false;
        }
        return isApkInstalled(context, downloadInfo, parseApkFile(context, downloadInfo, downloadInfo.getSavePath(), downloadInfo.getName()));
    }

    public static boolean isApkInstalled(Context context, String str, String str2) {
        return isApkInstalled(context, (DownloadInfo) null, parseApkFile(context, null, str, str2));
    }

    public static boolean isApkInstalled(Context context, String str, String str2, boolean z) {
        return isApkInstalled(context, (DownloadInfo) null, parseApkFile(context, null, str, str2), z);
    }

    public static PackageInfo parseApkFile(Context context, DownloadInfo downloadInfo, String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        File file = new File(str, str2);
        if (!file.exists()) {
            return null;
        }
        Log.e(TAG, "isApkInstalled apkFileSize：fileName:" + file.getPath() + " apkFileSize" + file.length());
        return getPackageInfo(downloadInfo, file);
    }

    public static String getValidName(String str, String str2, String str3) {
        return getValidName(str, str2, str3, false);
    }

    public static String getValidName(String str, String str2, String str3, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        Uri uri = Uri.parse(str);
        if (z) {
            if (TextUtils.isEmpty(str2)) {
                str2 = !TextUtils.isEmpty(uri.getLastPathSegment()) ? uri.getLastPathSegment() : "default.apk";
            }
        } else {
            String lastPathSegment = uri.getLastPathSegment();
            if (!TextUtils.isEmpty(lastPathSegment)) {
                str2 = lastPathSegment;
            } else if (TextUtils.isEmpty(str2)) {
                str2 = "default.apk";
            }
        }
        if (!isApkMineType(str3) || str2.endsWith(Constants.APK_SUFFIX)) {
            return str2;
        }
        return str2 + Constants.APK_SUFFIX;
    }

    public static String getAppDownloadPath(Context context) {
        return getAppDownloadPath();
    }

    public static String getAppDownloadPath() {
        return DownloadUtils.getDownloadPath();
    }

    public static String getRedirectSavePath(String str, DownloadSetting downloadSetting) {
        JSONObject jSONObjectOptJSONObject;
        String str2;
        if (downloadSetting == null || (jSONObjectOptJSONObject = downloadSetting.optJSONObject(com.ss.android.socialbase.downloader.setting.DownloadSettingKeys.KEY_ANTI_HIJACK_DIR)) == null) {
            return "";
        }
        String strOptString = jSONObjectOptJSONObject.optString(DownloadSettingKeys.AntiHijackDir.KEY_ANTI_HIJACK_DIR_NAME);
        if (!TextUtils.isEmpty(strOptString) && strOptString.startsWith("/")) {
            strOptString = strOptString.substring(1);
        }
        String str3 = strOptString;
        if (TextUtils.isEmpty(str3)) {
            return str3;
        }
        if (!str3.contains("%s")) {
            str2 = str3 + str;
        } else {
            try {
                str2 = String.format(str3, str);
            } catch (Throwable unused) {
            }
        }
        str3 = str2;
        return str3.length() > 255 ? str3.substring(str3.length() - 255) : str3;
    }

    public static boolean isBindApp(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONObject(str).optBoolean(BIND_APP_EXTRA, false);
    }

    public static boolean canNotAutoInstall(String str) {
        JSONObject jSONObject;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (!jSONObject.optBoolean(BIND_APP_EXTRA, false)) {
            if (jSONObject.optBoolean(Constants.AUTO_INSTALL_WITH_NOTIFICATION, true)) {
                return false;
            }
        }
        return true;
    }

    public static int getNotificationType(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == -2) {
            return 2;
        }
        if (i == 1) {
            return 4;
        }
        if (DownloadStatus.isDownloading(i) || i == 11) {
            return 1;
        }
        return DownloadStatus.isDownloadOver(i) ? 3 : 0;
    }

    public static boolean isApkMineType(String str) {
        return !TextUtils.isEmpty(str) && str.equals("application/vnd.android.package-archive");
    }

    public static boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    public static boolean isMaterialNotification(Context context) throws Throwable {
        int color;
        TypedArray typedArrayObtainStyledAttributes;
        if (Build.VERSION.SDK_INT <= 20 || context == null) {
            return false;
        }
        TypedArray typedArray = null;
        try {
            try {
                color = context.getResources().getColor(AppResourceUtils.getNotificationTitleColor());
                typedArrayObtainStyledAttributes = context.obtainStyledAttributes(AppResourceUtils.getNotificationTitleStyle(), new int[]{AppResourceUtils.getAndroidTextColor(), AppResourceUtils.getAndroidTextSize()});
            } catch (Throwable unused) {
            }
            try {
            } catch (Throwable unused2) {
                typedArray = typedArrayObtainStyledAttributes;
                if (typedArray != null) {
                    typedArray.recycle();
                }
                return false;
            }
        } catch (Throwable th) {
            th = th;
        }
        if (color == typedArrayObtainStyledAttributes.getColor(0, 0)) {
            if (typedArrayObtainStyledAttributes != null) {
                try {
                    typedArrayObtainStyledAttributes.recycle();
                } catch (Throwable unused3) {
                }
            }
            return true;
        }
        if (typedArrayObtainStyledAttributes != null) {
            typedArrayObtainStyledAttributes.recycle();
        }
        return false;
    }

    @TargetApi(26)
    public static String getNotificationChannelId(@NonNull Context context) {
        try {
            if (mNotificationChannel != null) {
                return DEFAULT_CHANNEL_ID;
            }
            mNotificationChannel = new NotificationChannel(DEFAULT_CHANNEL_ID, DEFAULT_CHANNEL_NAME, 3);
            mNotificationChannel.setSound(null, null);
            mNotificationChannel.setShowBadge(false);
            ((NotificationManager) context.getSystemService(UMessage.DISPLAY_TYPE_NOTIFICATION)).createNotificationChannel(mNotificationChannel);
            return DEFAULT_CHANNEL_ID;
        } catch (Throwable th) {
            th.printStackTrace();
            return DEFAULT_CHANNEL_ID;
        }
    }

    public static List<String> getApplicationMimeTypes() {
        ArrayList arrayList = new ArrayList(2);
        arrayList.add("application/vnd.android.package-archive");
        arrayList.add(Constants.MIME_PATCH);
        return arrayList;
    }

    public static void createDownloadTask(DownloadInfo downloadInfo, boolean z, boolean z2) {
        AppDownloader.getInstance().addDownloadTask(new AppTaskBuilder(DownloadComponentManager.getAppContext(), downloadInfo.getUrl()).name(downloadInfo.getTitle()).saveName(downloadInfo.getName()).savePath(downloadInfo.getSavePath()).showNotification(downloadInfo.isShowNotification()).autoInstallWithoutNotification(downloadInfo.isAutoInstallWithoutNotification()).needWifi(downloadInfo.isOnlyWifi() || z2).extra(downloadInfo.getExtra()).mimeType(downloadInfo.getMimeType()).headers(downloadInfo.getExtraHeaders()).autoResumed(true).retryCount(downloadInfo.getRetryCount()).backUpUrlRetryCount(downloadInfo.getBackUpUrlRetryCount()).backUpUrls(downloadInfo.getBackUpUrls()).minProgressTimeMsInterval(downloadInfo.getMinProgressTimeMsInterval()).maxProgressCount(downloadInfo.getMaxProgressCount()).showNotificationForAutoResumed(z).needHttpsToHttpRetry(downloadInfo.isNeedHttpsToHttpRetry()).packageName(downloadInfo.getPackageName()).md5(downloadInfo.getMd5()).expectFileLength(downloadInfo.getExpectFileLength()).needDefaultHttpServiceBackUp(downloadInfo.isNeedDefaultHttpServiceBackUp()).needReuseFirstConnection(downloadInfo.isNeedReuseFirstConnection()).needIndependentProcess(downloadInfo.isNeedIndependentProcess()).enqueueType(downloadInfo.getEnqueueType()).force(downloadInfo.isForce()).headConnectionAvailable(downloadInfo.isHeadConnectionAvailable()).needRetryDelay(downloadInfo.isNeedRetryDelay()).retryDelayTimeArray(downloadInfo.getRetryDelayTimeArray()).downloadSetting(createJSONObject(downloadInfo.getDownloadSettingString())).iconUrl(downloadInfo.getIconUrl()).executorGroup(downloadInfo.getExecutorGroup()).autoInstall(downloadInfo.isAutoInstall()));
    }

    private static JSONObject createJSONObject(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new JSONObject(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void safeFinish(Activity activity) {
        if (activity != null) {
            try {
                if (activity.isFinishing()) {
                    return;
                }
                activity.finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static PackageInfo getPackageInfo(DownloadInfo downloadInfo, File file) {
        if (downloadInfo == null) {
            return PackageInfoUtils.getPackageInfo(DownloadComponentManager.getAppContext(), file, getPackageInfoFlag());
        }
        PackageInfo packageInfo = downloadInfo.getPackageInfo();
        if (packageInfo != null) {
            return packageInfo;
        }
        PackageInfo packageInfo2 = PackageInfoUtils.getPackageInfo(DownloadComponentManager.getAppContext(), file, getPackageInfoFlag());
        downloadInfo.setPackageInfo(packageInfo2);
        return packageInfo2;
    }

    public static int dp2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
