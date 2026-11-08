package com.ss.android.download.api.clean;

import android.text.TextUtils;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ApkCleanFile extends CleanFolder {
    public static final String APK_CLEAN_FILE = "apk_clean_file";
    private ApkInfo mApkInfo;

    @Override // com.ss.android.download.api.clean.CleanFolder, com.ss.android.download.api.clean.CleanFile
    public String getClassifyType() {
        return APK_CLEAN_FILE;
    }

    public ApkInfo getApkInfo() {
        return this.mApkInfo;
    }

    public void setApkInfo(ApkInfo apkInfo) {
        this.mApkInfo = apkInfo;
    }

    @Override // com.ss.android.download.api.clean.CleanFolder, com.ss.android.download.api.clean.BaseCleanItem
    public String getName() {
        if (this.mApkInfo != null && !TextUtils.isEmpty(this.mApkInfo.getAppName())) {
            return this.mApkInfo.getAppName();
        }
        return super.getName();
    }

    @Override // com.ss.android.download.api.clean.CleanFolder, com.ss.android.download.api.clean.CleanFile, com.ss.android.download.api.clean.BaseCleanItem, com.ss.android.download.api.clean.IJsonable
    public JSONObject toJson() {
        return super.toJson();
    }

    @Override // com.ss.android.download.api.clean.CleanFolder, com.ss.android.download.api.clean.CleanFile, com.ss.android.download.api.clean.BaseCleanItem, com.ss.android.download.api.clean.IJsonable
    public void parse(JSONObject jSONObject) {
        super.parse(jSONObject);
    }
}
