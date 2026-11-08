package com.ss.android.downloadlib.addownload.model;

import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.utils.ToolUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AppInfo {
    public long mAdId;
    public String mAppName;
    public long mDownloadId;
    public long mExtValue;
    public String mFileName;
    public String mLogExtra;
    public String mPackageName;
    public volatile long mTimeStamp;

    public AppInfo() {
    }

    public AppInfo(long j, long j2, long j3, String str, String str2, String str3, String str4) {
        this.mDownloadId = j;
        this.mAdId = j2;
        this.mExtValue = j3;
        this.mPackageName = str;
        this.mAppName = str2;
        this.mLogExtra = str3;
        this.mFileName = str4;
    }

    public void refreshTimeStamp() {
        this.mTimeStamp = System.currentTimeMillis();
    }

    public JSONObject toJSon() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(NativeDownloadModel.JsonKey.DOWNLOAD_ID, this.mDownloadId);
            jSONObject.put("mAdId", this.mAdId);
            jSONObject.put(NativeDownloadModel.JsonKey.EXT_VALUE, this.mExtValue);
            jSONObject.put(NativeDownloadModel.JsonKey.PACKAGE_NAME, this.mPackageName);
            jSONObject.put(NativeDownloadModel.JsonKey.APP_NAME, this.mAppName);
            jSONObject.put(NativeDownloadModel.JsonKey.LOG_EXTRA, this.mLogExtra);
            jSONObject.put("mFileName", this.mFileName);
            jSONObject.put(NativeDownloadModel.JsonKey.TIME_STAMP, this.mTimeStamp);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public static AppInfo fromJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        AppInfo appInfo = new AppInfo();
        try {
            appInfo.mDownloadId = ToolUtils.optLong(jSONObject, NativeDownloadModel.JsonKey.DOWNLOAD_ID);
            appInfo.mAdId = ToolUtils.optLong(jSONObject, "mAdId");
            appInfo.mExtValue = ToolUtils.optLong(jSONObject, NativeDownloadModel.JsonKey.EXT_VALUE);
            appInfo.mPackageName = jSONObject.optString(NativeDownloadModel.JsonKey.PACKAGE_NAME);
            appInfo.mAppName = jSONObject.optString(NativeDownloadModel.JsonKey.APP_NAME);
            appInfo.mLogExtra = jSONObject.optString(NativeDownloadModel.JsonKey.LOG_EXTRA);
            appInfo.mFileName = jSONObject.optString("mFileName");
            appInfo.mTimeStamp = ToolUtils.optLong(jSONObject, NativeDownloadModel.JsonKey.TIME_STAMP);
            return appInfo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
