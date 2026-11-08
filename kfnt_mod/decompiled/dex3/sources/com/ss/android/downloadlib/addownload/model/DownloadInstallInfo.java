package com.ss.android.downloadlib.addownload.model;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DownloadInstallInfo {
    public static int DOWNLOAD_INSTALL_DEFAULT = 0;
    public static int DOWNLOAD_INSTALL_NORMAL = 1;
    public static int DOWNLOAD_INSTALL_UPGRADE = 2;
    private int mInstallStatus = DOWNLOAD_INSTALL_DEFAULT;
    private long mDownloadTaskId = 0;
    private JSONObject mDownloadExtraObject = null;
    private int mVersionCode = 0;
    private String mVersionName = "";
    private String mAppPackageName = "";

    public boolean isInstall() {
        return this.mInstallStatus == DOWNLOAD_INSTALL_NORMAL;
    }

    public boolean isNeedUpgrade() {
        return this.mInstallStatus == DOWNLOAD_INSTALL_UPGRADE;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public String getVersionName() {
        return this.mVersionName;
    }

    public int getInstallStatus() {
        return this.mInstallStatus;
    }

    public long getmDownloadTaskId() {
        return this.mDownloadTaskId;
    }

    public JSONObject getmDownloadExtraObject() {
        return this.mDownloadExtraObject;
    }

    public String getAppPackageName() {
        return this.mAppPackageName;
    }

    public DownloadInstallInfo setInstallStatus(int i) {
        this.mInstallStatus = i;
        return this;
    }

    public DownloadInstallInfo setDownloadTaskId(long j) {
        this.mDownloadTaskId = j;
        return this;
    }

    public DownloadInstallInfo setDownloadExtraObject(JSONObject jSONObject) {
        this.mDownloadExtraObject = jSONObject;
        return this;
    }

    public DownloadInstallInfo setVersionCode(int i) {
        this.mVersionCode = i;
        return this;
    }

    public DownloadInstallInfo setVersionName(String str) {
        this.mVersionName = str;
        return this;
    }

    public DownloadInstallInfo setAppPackageName(String str) {
        this.mAppPackageName = str;
        return this;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("mInstallStatus", this.mInstallStatus);
            jSONObject.put("mDownloadTaskId", this.mDownloadTaskId);
            jSONObject.put("mDownloadExtraObject", this.mDownloadExtraObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
