package com.ss.android.download.api.clean;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.ss.android.socialbase.appdownloader.util.package_info.PackageInfoUtils;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class CleanApkType extends CleanType {
    public static final String CLEAN_APK_TYPE = "clean_apk_type";

    @Override // com.ss.android.download.api.clean.CleanFolder, com.ss.android.download.api.clean.CleanFile
    public String getClassifyType() {
        return CLEAN_APK_TYPE;
    }

    public void addCleanItem(Context context, CleanFile cleanFile) {
        if (cleanFile == null || context == null) {
            return;
        }
        ApkCleanFile apkCleanFile = new ApkCleanFile();
        apkCleanFile.setFolderName(cleanFile.getFolderName());
        apkCleanFile.setName(cleanFile.getName());
        apkCleanFile.setAbsolutePath(cleanFile.getAbsolutePath());
        try {
            PackageInfo packageInfo = PackageInfoUtils.getPackageInfo(context, new File(cleanFile.getAbsolutePath()), 0);
            if (packageInfo == null || TextUtils.isEmpty(packageInfo.packageName)) {
                return;
            }
            ApkInfo apkInfo = new ApkInfo();
            apkInfo.setAppName(cleanFile.getName());
            apkInfo.setPackageName(packageInfo.packageName);
            apkInfo.setVersion(packageInfo.versionName);
            apkCleanFile.setApkInfo(apkInfo);
            boolean zAddCleanFile = apkCleanFile.addCleanFile(cleanFile);
            apkCleanFile.setParent(this);
            if (zAddCleanFile) {
                this.cleanFolders.add(apkCleanFile);
                this.size += cleanFile.getSize();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
