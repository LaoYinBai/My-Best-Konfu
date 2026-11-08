package com.ss.android.downloadlib.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.os.StatFs;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.ss.android.download.api.config.IDownloadCustomChecker;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.addownload.model.DownloadInstallInfo;
import com.ss.android.downloadlib.constants.DownloadConstants;
import com.ss.android.downloadlib.constants.DownloadSettingKeys;
import com.ss.android.socialbase.appdownloader.AppDownloadUtils;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.logger.Logger;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import com.ss.union.game.sdk.common.ui.roundedimageview.RoundedDrawable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.UByte;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ToolUtils {
    private static final int CACHE_SIZE = 73;
    private static final double COLOR_MAX = 120.0d;
    private static final int FLAGS_GET_ONLY_FROM_ANDROID = 16777216;
    private static final long G = 1073741824;
    private static final long K = 1024;
    private static final long M = 1048576;
    private static final long T = 1099511627776L;
    public static final String TAG = "ToolUtils";
    private static Object[] EMPTY = new Object[0];
    private static Object[] sCache = new Object[73];
    static final char[] HEX_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static String sPACKAGE_NAME = null;

    public static int idealByteArraySize(int i) {
        for (int i2 = 4; i2 < 32; i2++) {
            int i3 = (1 << i2) - 12;
            if (i <= i3) {
                return i3;
            }
        }
        return i;
    }

    public static int idealLongArraySize(int i) {
        return idealByteArraySize(i * 8) / 8;
    }

    public static String md5Hex(File file) throws Throwable {
        FileInputStream fileInputStream;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            if (messageDigest == null) {
                return null;
            }
            fileInputStream = new FileInputStream(file);
            try {
                byte[] bArr = new byte[8192];
                while (true) {
                    int i = fileInputStream.read(bArr, 0, bArr.length);
                    if (i <= 0) {
                        break;
                    }
                    messageDigest.update(bArr, 0, i);
                }
                String hexString = toHexString(messageDigest.digest());
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return hexString;
            } catch (Exception unused) {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                return null;
            } catch (Throwable th) {
                th = th;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Exception unused2) {
            fileInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
        }
    }

    public static String md5Hex(String str) throws NoSuchAlgorithmException {
        if (str != null) {
            try {
                if (str.length() != 0) {
                    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                    messageDigest.update(str.getBytes("UTF-8"));
                    return toHexString(messageDigest.digest());
                }
            } catch (Exception unused) {
                return null;
            }
        }
        return null;
    }

    public static String toHexString(byte[] bArr) {
        return toHexString(bArr, 0, bArr.length);
    }

    public static String toHexString(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new NullPointerException("bytes is null");
        }
        if (i < 0 || i + i2 > bArr.length) {
            throw new IndexOutOfBoundsException();
        }
        int i3 = i2 * 2;
        char[] cArr = new char[i3];
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = bArr[i5 + i] & UByte.MAX_VALUE;
            int i7 = i4 + 1;
            cArr[i4] = HEX_CHARS[i6 >> 4];
            i4 = i7 + 1;
            cArr[i7] = HEX_CHARS[i6 & 15];
        }
        return new String(cArr, 0, i3);
    }

    public static String hexToString(String str) {
        return DownloadUtils.hexToString(str);
    }

    public static boolean exists(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return new File(str).exists();
    }

    public static boolean isDownloadFileExist(String str) {
        return !TextUtils.isEmpty(str) && new File(str).exists();
    }

    public static long optLong(JSONObject jSONObject, String str) {
        return com.ss.android.download.api.utils.ToolUtils.optLong(jSONObject, str);
    }

    public static JSONObject copyJson(JSONObject jSONObject, JSONObject jSONObject2) {
        return com.ss.android.download.api.utils.ToolUtils.copyJson(jSONObject, jSONObject2);
    }

    @NonNull
    public static JSONObject copyJson(JSONObject jSONObject) {
        return com.ss.android.download.api.utils.ToolUtils.copyJson(jSONObject);
    }

    @NonNull
    public static JSONObject mergeJson(JSONObject... jSONObjectArr) {
        return com.ss.android.download.api.utils.ToolUtils.mergeJson(jSONObjectArr);
    }

    public static boolean isIntentAvailable(Context context, Intent intent) {
        try {
            List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 65536);
            if (listQueryIntentActivities != null) {
                return !listQueryIntentActivities.isEmpty();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isAvailable();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isWifi(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                return 1 == activeNetworkInfo.getType();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static String bytesToHuman(long j) {
        long[] jArr = {1099511627776L, 1073741824, 1048576, 1024, 1};
        String[] strArr = {"TB", "GB", "MB", "KB", "B"};
        if (j < 1) {
            return "0 " + strArr[strArr.length - 1];
        }
        for (int i = 0; i < jArr.length; i++) {
            long j2 = jArr[i];
            if (j >= j2) {
                return format(j, j2, strArr[i]);
            }
        }
        return null;
    }

    private static String format(long j, long j2, String str) {
        return new DecimalFormat("#.##").format(j2 > 1 ? j / j2 : j) + " " + str;
    }

    public static String getAppNameFromApK(Context context, String str) {
        PackageManager packageManager;
        PackageInfo packageArchiveInfo;
        if (context == null || TextUtils.isEmpty(str) || (packageArchiveInfo = (packageManager = context.getPackageManager()).getPackageArchiveInfo(str, 0)) == null) {
            return null;
        }
        ApplicationInfo applicationInfo = packageArchiveInfo.applicationInfo;
        applicationInfo.sourceDir = str;
        applicationInfo.publicSourceDir = str;
        return applicationInfo.loadLabel(packageManager).toString();
    }

    public static String getPackageNameFromApk(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return null;
        }
        PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(str, 0);
        return packageArchiveInfo != null ? packageArchiveInfo.applicationInfo.packageName : "";
    }

    public static PackageInfo getApkFilePackageInfo(NativeDownloadModel nativeDownloadModel) {
        DownloadInfo downloadInfo;
        if (nativeDownloadModel == null || (downloadInfo = Downloader.getInstance(GlobalInfo.getContext()).getDownloadInfo(nativeDownloadModel.getDownloadId())) == null) {
            return null;
        }
        try {
            return AppDownloadUtils.parseApkFile(GlobalInfo.getContext(), downloadInfo, downloadInfo.getSavePath(), downloadInfo.getName());
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Drawable getApkIconFromApk(Context context, String str) {
        PackageManager packageManager;
        PackageInfo packageArchiveInfo;
        if (context != null && !TextUtils.isEmpty(str) && (packageArchiveInfo = (packageManager = context.getPackageManager()).getPackageArchiveInfo(str, 0)) != null) {
            ApplicationInfo applicationInfo = packageArchiveInfo.applicationInfo;
            applicationInfo.sourceDir = str;
            applicationInfo.publicSourceDir = str;
            try {
                return applicationInfo.loadIcon(packageManager);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static int getVersionCode(Context context, String str) throws PackageManager.NameNotFoundException {
        if (context == null || TextUtils.isEmpty(str)) {
            return -1;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
            if (packageInfo != null) {
                return packageInfo.versionCode;
            }
            return -1;
        } catch (PackageManager.NameNotFoundException unused) {
            return -1;
        }
    }

    public static String getVersionName(Context context, String str) throws PackageManager.NameNotFoundException {
        if (context == null || TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
            return packageInfo != null ? packageInfo.versionName : "";
        } catch (PackageManager.NameNotFoundException unused) {
            return "";
        }
    }

    public static Drawable getAppIcon(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str)) {
            try {
                PackageManager packageManager = context.getPackageManager();
                return packageManager.getApplicationInfo(str, 0).loadIcon(packageManager);
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return null;
    }

    public static String getPackageName() {
        if (TextUtils.isEmpty(sPACKAGE_NAME) && GlobalInfo.getContext() != null) {
            try {
                sPACKAGE_NAME = GlobalInfo.getContext().getPackageName();
            } catch (Exception unused) {
            }
        }
        return sPACKAGE_NAME;
    }

    public static boolean isInstalledApp(Context context, String str) {
        if (context == null) {
            context = GlobalInfo.getContext();
        }
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            return context.getPackageManager().getPackageInfo(str, 0) != null;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isInstalledApp(String str, int i, String str2) {
        return getInstalledAppInfo(str, i, str2).isInstall();
    }

    public static DownloadInstallInfo getInstalledAppInfo(String str, int i, String str2) throws PackageManager.NameNotFoundException {
        DownloadInstallInfo downloadInstallInfo = new DownloadInstallInfo();
        if (TextUtils.isEmpty(str)) {
            return downloadInstallInfo;
        }
        try {
            PackageInfo packageInfo = GlobalInfo.getContext().getPackageManager().getPackageInfo(str, 0);
            if (packageInfo != null) {
                downloadInstallInfo.setVersionCode(packageInfo.versionCode);
                downloadInstallInfo.setInstallStatus(DownloadInstallInfo.DOWNLOAD_INSTALL_NORMAL);
                IDownloadCustomChecker downloadCustomChecker = GlobalInfo.getDownloadCustomChecker();
                if (downloadCustomChecker != null && downloadCustomChecker.isOpenUpgradeChecker() && !isAppNewVersion(packageInfo.versionCode, i, packageInfo.versionName, str2)) {
                    downloadInstallInfo.setInstallStatus(DownloadInstallInfo.DOWNLOAD_INSTALL_UPGRADE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return downloadInstallInfo;
    }

    public static int getInstalledAppStatus(String str, int i, String str2) throws PackageManager.NameNotFoundException {
        int i2 = DownloadInstallInfo.DOWNLOAD_INSTALL_DEFAULT;
        if (TextUtils.isEmpty(str)) {
            return i2;
        }
        try {
            PackageInfo packageInfo = GlobalInfo.getContext().getPackageManager().getPackageInfo(str, 0);
            if (packageInfo == null) {
                return i2;
            }
            int i3 = DownloadInstallInfo.DOWNLOAD_INSTALL_NORMAL;
            try {
                return !isAppNewVersion(packageInfo.versionCode, i, packageInfo.versionName, str2) ? DownloadInstallInfo.DOWNLOAD_INSTALL_UPGRADE : i3;
            } catch (Exception e) {
                e = e;
                i2 = i3;
                e.printStackTrace();
                return i2;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    private static boolean isAppNewVersion(int i, int i2, String str, String str2) {
        if (i2 == 0 && TextUtils.isEmpty(str2)) {
            return true;
        }
        return (i2 > 0 && i >= i2) || compareVersion(str, str2) >= 0;
    }

    public static boolean isInstalledApp(NativeDownloadModel nativeDownloadModel) {
        if (nativeDownloadModel == null) {
            return false;
        }
        return getInstalledAppInfo(nativeDownloadModel.getPackageName(), nativeDownloadModel.getVersionCode(), nativeDownloadModel.getVersionName()).isInstall();
    }

    public static boolean isInstalledApp(DownloadModel downloadModel) {
        if (downloadModel == null) {
            return false;
        }
        return getInstalledAppInfo(downloadModel.getPackageName(), downloadModel.getVersionCode(), downloadModel.getVersionName()).isInstall();
    }

    public static boolean isInstalledApp(Context context, Intent intent) {
        if (intent == null) {
            return false;
        }
        if (context == null) {
            context = GlobalInfo.getContext();
        }
        List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 65536);
        return listQueryIntentActivities != null && listQueryIntentActivities.size() > 0;
    }

    public static boolean isApkFileInstalled(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        String packageNameFromApk = getPackageNameFromApk(context, str);
        return !TextUtils.isEmpty(packageNameFromApk) && isInstalledApp(context, packageNameFromApk);
    }

    public static boolean isInstalledAppNeedUpgrade(String str, int i, String str2) {
        return getInstalledAppInfo(str, i, str2).isNeedUpgrade();
    }

    public static boolean isInstalledPackageMatch(Context context, String str, String str2) {
        return isPackageMatchApk(context, str, str2) || isInstalledApp(context, str2);
    }

    public static boolean isPackageMatchApk(Context context, String str, String str2) throws PackageManager.NameNotFoundException {
        PackageInfo packageArchiveInfo;
        PackageInfo packageInfo;
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        try {
            File file = new File(str);
            if (!file.exists() || (packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(file.getAbsolutePath(), 0)) == null || !packageArchiveInfo.packageName.equals(str2)) {
                return false;
            }
            int i = packageArchiveInfo.versionCode;
            try {
                packageInfo = context.getPackageManager().getPackageInfo(str2, 0);
            } catch (PackageManager.NameNotFoundException unused) {
                packageInfo = null;
            }
            if (packageInfo == null) {
                return false;
            }
            return i == packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isApkInstalled(Context context, String str) throws PackageManager.NameNotFoundException {
        PackageInfo packageArchiveInfo;
        PackageInfo packageInfo;
        if (context == null || str == null || TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            File file = new File(str);
            if (!file.exists() || (packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(file.getAbsolutePath(), 0)) == null) {
                return false;
            }
            String str2 = packageArchiveInfo.packageName;
            int i = packageArchiveInfo.versionCode;
            try {
                packageInfo = context.getPackageManager().getPackageInfo(str2, 0);
            } catch (PackageManager.NameNotFoundException unused) {
                packageInfo = null;
            }
            if (packageInfo == null) {
                return false;
            }
            return i <= packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Intent getLaunchIntentForPackage(Context context, String str) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage == null) {
            return null;
        }
        if (!launchIntentForPackage.hasCategory("android.intent.category.LAUNCHER")) {
            launchIntentForPackage.addCategory("android.intent.category.LAUNCHER");
        }
        launchIntentForPackage.setPackage(null);
        launchIntentForPackage.addFlags(2097152);
        launchIntentForPackage.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        return launchIntentForPackage;
    }

    public static int getTargetSdkVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.targetSdkVersion;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static Signature[] getSignaturesByPackageName(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 64);
            if (packageInfo != null) {
                return packageInfo.signatures;
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static Signature[] getSignaturesByApkFilePath(Context context, String str) {
        try {
            PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(str, 64);
            if (packageArchiveInfo != null) {
                return packageArchiveInfo.signatures;
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static boolean isSignatureMatch(Signature[] signatureArr, Signature[] signatureArr2) {
        if (signatureArr == signatureArr2) {
            return true;
        }
        if (signatureArr == null || signatureArr2 == null || signatureArr.length != signatureArr2.length) {
            return false;
        }
        for (int i = 0; i < signatureArr.length; i++) {
            if ((signatureArr[i] == null && signatureArr2[i] != null) || (signatureArr[i] != null && !signatureArr[i].equals(signatureArr2[i]))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isOpenUrlValid(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return !GlobalInfo.getContext().getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse(str)), 0).isEmpty();
    }

    @WorkerThread
    public static boolean isAppActivatedByUrl(String str) {
        String str2;
        Iterator<ResolveInfo> it = GlobalInfo.getContext().getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse(str)), 0).iterator();
        while (true) {
            if (!it.hasNext()) {
                str2 = null;
                break;
            }
            ResolveInfo next = it.next();
            if (next.activityInfo != null) {
                Logger.d(TAG, "activity.resolvePackageName-->" + next.activityInfo.packageName);
                str2 = next.activityInfo.packageName;
                break;
            }
        }
        return isAppActivatedByPKG(str2);
    }

    @WorkerThread
    public static boolean isAppActivatedByPKG(String str) throws PackageManager.NameNotFoundException {
        File file;
        Context context = GlobalInfo.getContext();
        if (TextUtils.isEmpty(str) || !isInstalledApp(context, str)) {
            return false;
        }
        int i = context.getApplicationInfo().targetSdkVersion;
        if (GlobalInfo.getDownloadSettings().optInt(DownloadSettingKeys.KEY_GET_EXT_DIR_MODE) == 0 && Build.VERSION.SDK_INT >= 29 && ((i == 29 && !Environment.isExternalStorageLegacy()) || i > 29)) {
            return true;
        }
        try {
            if (Build.VERSION.SDK_INT >= 29 && context.getApplicationInfo().targetSdkVersion >= 29 && GlobalInfo.getDownloadSettings().optInt(DownloadSettingKeys.KEY_GET_EXT_DIR_MODE) == 1) {
                file = getExtDir(context, str);
            } else {
                file = new File(Environment.getExternalStorageDirectory().getPath(), "android/data/" + str);
            }
            if (!file.exists()) {
                return false;
            }
            long lastModifiedTime = FileUtils.getLastModifiedTime(file);
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
            if (packageInfo != null) {
                if (packageInfo.lastUpdateTime < lastModifiedTime) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public static File getExtDir(Context context, String str) {
        File parentFile = context.getExternalFilesDir(null).getParentFile();
        File file = new File((parentFile != null ? parentFile.getParent() : null) + File.separator + str);
        StringBuilder sb = new StringBuilder();
        sb.append("getExtDir: file.toString()-->");
        sb.append(file.toString());
        Logger.d(TAG, sb.toString());
        return file;
    }

    public static int dp2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2dp(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static boolean isColorSimilar(int i, int i2) {
        if (i == i2) {
            return true;
        }
        int i3 = i | RoundedDrawable.DEFAULT_BORDER_COLOR;
        int i4 = i2 | RoundedDrawable.DEFAULT_BORDER_COLOR;
        int iRed = Color.red(i3) - Color.red(i4);
        int iGreen = Color.green(i3) - Color.green(i4);
        int iBlue = Color.blue(i3) - Color.blue(i4);
        return Math.sqrt((double) (((iRed * iRed) + (iGreen * iGreen)) + (iBlue * iBlue))) < COLOR_MAX;
    }

    public static String getFixLengthString(String str, int i) {
        return i == 0 ? "" : (TextUtils.isEmpty(str) || str.length() <= i) ? str : str.substring(0, i);
    }

    public static boolean isNoSpaceLeftStatus(BaseException baseException) {
        if (baseException == null) {
            return false;
        }
        int errorCode = baseException.getErrorCode();
        String message = baseException.getMessage();
        if (errorCode == 1006 || errorCode == 1052) {
            return true;
        }
        return (errorCode == 1023 || errorCode == 1039 || errorCode == 1040 || errorCode == 1036) && !TextUtils.isEmpty(message) && message.contains(DownloadConstants.ERROR_MSG_NO_SPACE_LEFT);
    }

    public static int compareVersion(String str, String str2) {
        try {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                if (str.equals(str2)) {
                    return 0;
                }
                String[] strArrSplit = str.split("\\.");
                String[] strArrSplit2 = str2.split("\\.");
                int iMin = Math.min(strArrSplit.length, strArrSplit2.length);
                int i = 0;
                int i2 = 0;
                while (i < iMin) {
                    i2 = Integer.parseInt(strArrSplit[i]) - Integer.parseInt(strArrSplit2[i]);
                    if (i2 != 0) {
                        break;
                    }
                    i++;
                }
                if (i2 != 0) {
                    return i2 > 0 ? 1 : -1;
                }
                for (int i3 = i; i3 < strArrSplit.length; i3++) {
                    if (Integer.parseInt(strArrSplit[i3]) > 0) {
                        return 1;
                    }
                }
                while (i < strArrSplit2.length) {
                    if (Integer.parseInt(strArrSplit2[i]) > 0) {
                        return -1;
                    }
                    i++;
                }
                return 0;
            }
            return -2;
        } catch (Exception unused) {
            return -2;
        }
    }

    public static String getNotEmptyStr(String... strArr) {
        return com.ss.android.download.api.utils.ToolUtils.getNotEmptyStr(strArr);
    }

    public static String getDownloadAppPackageName(Context context, String str) {
        PackageInfo packageArchiveInfo;
        if (context == null || TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            File file = new File(str);
            return (!file.exists() || (packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(file.getAbsolutePath(), 0)) == null) ? "" : packageArchiveInfo.packageName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @NonNull
    public static <T> T getNonNull(T... tArr) {
        if (tArr == null) {
            throw new IllegalArgumentException("args is null");
        }
        for (T t : tArr) {
            if (t != null) {
                return t;
            }
        }
        throw new IllegalArgumentException("args is null");
    }

    public static long getExternalAvailableSpaceBytes(long j) {
        try {
            return getAvailableSpaceBytes(Environment.getExternalStorageDirectory(), j);
        } catch (Exception e) {
            e.printStackTrace();
            return j;
        }
    }

    public static long getAvailableSpaceBytes(File file, long j) {
        if (file == null) {
            return j;
        }
        try {
            return DownloadUtils.getAvailableSpaceBytes(file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            return j;
        }
    }

    public static long getTotalSpaceBytes(File file) {
        if (file == null) {
            return -1L;
        }
        try {
            StatFs statFs = new StatFs(file.getAbsolutePath());
            if (Build.VERSION.SDK_INT >= 18) {
                return statFs.getTotalBytes();
            }
            return -1L;
        } catch (Throwable th) {
            th.printStackTrace();
            return -1L;
        }
    }

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static void safePut(JSONObject jSONObject, String str, Object obj) throws JSONException {
        if (jSONObject == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            jSONObject.putOpt(str, obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint({"MissingPermission"})
    public static void tryMoveAppToFront() {
        try {
            if (GlobalInfo.getDownloadPermissionChecker().hasPermission(GlobalInfo.getContext(), "android.permission.REORDER_TASKS")) {
                ActivityManager activityManager = (ActivityManager) GlobalInfo.getContext().getSystemService("activity");
                for (ActivityManager.RunningTaskInfo runningTaskInfo : activityManager.getRunningTasks(20)) {
                    if (GlobalInfo.getContext().getPackageName().equals(runningTaskInfo.topActivity.getPackageName())) {
                        activityManager.moveTaskToFront(runningTaskInfo.id, 1);
                        return;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NonNull
    public static HashMap<String, String> jsonToMap(JSONObject jSONObject) {
        HashMap<String, String> map = new HashMap<>();
        if (jSONObject != null) {
            try {
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    map.put(next, jSONObject.optString(next));
                }
                return map;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
