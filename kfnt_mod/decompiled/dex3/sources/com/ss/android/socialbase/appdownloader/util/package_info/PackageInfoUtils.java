package com.ss.android.socialbase.appdownloader.util.package_info;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.annotation.NonNull;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.monitor.IDownloadMonitorListener;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class PackageInfoUtils {
    private static String getPackage(int i) {
        return (i >>> 24) == 1 ? "android:" : "";
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x0074, code lost:
    
        r3 = null;
        r11 = r1.getInputStream(r2);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:119:0x017a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:125:0x017f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0184 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0189 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:169:? A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r11v0, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r11v16, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r11v20 */
    /* JADX WARN: Type inference failed for: r11v21 */
    /* JADX WARN: Type inference failed for: r11v22 */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Type inference failed for: r11v7, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r11v9 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.util.zip.ZipInputStream] */
    /* JADX WARN: Type inference failed for: r2v17, types: [com.ss.android.socialbase.appdownloader.util.package_info.AXmlResourceParser] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.content.pm.PackageInfo getPackageInfo(@androidx.annotation.NonNull java.io.File r11) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 397
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.socialbase.appdownloader.util.package_info.PackageInfoUtils.getPackageInfo(java.io.File):android.content.pm.PackageInfo");
    }

    public static PackageInfo getPackageInfo(@NonNull Context context, @NonNull File file, int i) {
        if (DownloadExpSwitchCode.isSwitchEnable(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP) && Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT < 26) {
            try {
                return getPackageInfo(file);
            } catch (Throwable th) {
                reportViaMonitorListener("getPackageInfo::unzip_getpackagearchiveinfo", th.getMessage());
                return getPackageInfoByPackageManager(context, file, i);
            }
        }
        return getPackageInfoByPackageManager(context, file, i);
    }

    private static PackageInfo getPackageInfoByPackageManager(@NonNull Context context, @NonNull File file, int i) throws JSONException {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            reportViaMonitorListener("unzip_getpackagearchiveinfo", "packageManager == null");
            return null;
        }
        try {
            return packageManager.getPackageArchiveInfo(file.getPath(), i);
        } catch (Throwable th) {
            reportViaMonitorListener("unzip_getpackagearchiveinfo", "pm.getPackageArchiveInfo failed: " + th.getMessage());
            return null;
        }
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

    private static String getAttributeValue(AXmlResourceParser aXmlResourceParser, int i) {
        int attributeValueType = aXmlResourceParser.getAttributeValueType(i);
        int attributeValueData = aXmlResourceParser.getAttributeValueData(i);
        if (attributeValueType == 3) {
            return aXmlResourceParser.getAttributeValue(i);
        }
        return attributeValueType == 2 ? String.format("?%s%08X", getPackage(attributeValueData), Integer.valueOf(attributeValueData)) : (attributeValueType < 16 || attributeValueType > 31) ? String.format("<0x%X, type 0x%02X>", Integer.valueOf(attributeValueData), Integer.valueOf(attributeValueType)) : String.valueOf(attributeValueData);
    }

    public static String getAppNameByPackageInfo(Context context, PackageInfo packageInfo, String str) throws JSONException {
        if (packageInfo == null || packageInfo.applicationInfo == null) {
            return null;
        }
        ApplicationInfo applicationInfo = packageInfo.applicationInfo;
        applicationInfo.sourceDir = str;
        applicationInfo.publicSourceDir = str;
        try {
            return applicationInfo.loadLabel(context.getPackageManager()).toString();
        } catch (OutOfMemoryError e) {
            reportViaMonitorListener("getPackageInfo::fail_load_label", e.getMessage());
            return null;
        }
    }
}
