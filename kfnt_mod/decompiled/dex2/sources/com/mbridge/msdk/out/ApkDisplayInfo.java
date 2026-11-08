package com.mbridge.msdk.out;

import android.text.TextUtils;
import com.mbridge.msdk.foundation.tools.k;
import com.mbridge.msdk.system.NoProGuard;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ApkDisplayInfo implements NoProGuard, Serializable {
    private static String JSON_KEY_APP_NAME = "app_name";
    private static String JSON_KEY_APP_VER = "app_ver";
    private static String JSON_KEY_DEV_NAME = "dev_name";
    private static String JSON_KEY_PERM_DESC = "perm_desc";
    private static String JSON_KEY_PERM_DESC_ALL = "ori_perm_all";
    private static String JSON_KEY_PERM_DESC_ORI = "ori_perm_desc";
    private static String JSON_KEY_PRI_URL = "pri_url";
    private static String JSON_KEY_UPD_TIME = "upd_time";
    private String appName;
    private String appVersion;
    private String devName;
    private String priUrl;
    private String updateTime;
    private ArrayList<String> permDescList = new ArrayList<>();
    private ArrayList<String> permDescOriList = new ArrayList<>();
    private ArrayList<String> permDescAllList = new ArrayList<>();

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public ArrayList<String> getPermDescList() {
        return this.permDescList;
    }

    public void setPermDescList(ArrayList<String> arrayList) {
        this.permDescList = arrayList;
    }

    public ArrayList<String> getPermDescOriList() {
        return this.permDescOriList;
    }

    public void setPermDescOriList(ArrayList<String> arrayList) {
        this.permDescOriList = arrayList;
    }

    public ArrayList<String> getPermDescAllList() {
        return this.permDescAllList;
    }

    public void setPermDescAllList(ArrayList<String> arrayList) {
        this.permDescAllList = arrayList;
    }

    public String getPriUrl() {
        return this.priUrl;
    }

    public void setPriUrl(String str) {
        this.priUrl = str;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(String str) {
        this.updateTime = str;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }

    public String getDevName() {
        return this.devName;
    }

    public void setDevName(String str) {
        this.devName = str;
    }

    private static ApkDisplayInfo checkApkDisplayInfoIfNullWillInit(ApkDisplayInfo apkDisplayInfo) {
        return apkDisplayInfo == null ? new ApkDisplayInfo() : apkDisplayInfo;
    }

    public String toString() {
        return "ApkDisplayInfo{appName='" + this.appName + "', permDescJArray=" + this.permDescList + ", permDescOriJArray=" + this.permDescOriList + ", permDescAll=" + this.permDescAllList + ", priUrl='" + this.priUrl + "', updateTime='" + this.updateTime + "', appVersion='" + this.appVersion + "', devName='" + this.devName + "'}";
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(this.appName)) {
                jSONObject.put(JSON_KEY_APP_NAME, this.appName);
            }
            if (this.permDescList != null && this.permDescList.size() > 0) {
                jSONObject.put(JSON_KEY_PERM_DESC, arrayListArrayCovertToJsonArray(this.permDescList));
            }
            if (this.permDescOriList != null && this.permDescOriList.size() > 0) {
                jSONObject.put(JSON_KEY_PERM_DESC_ORI, arrayListArrayCovertToJsonArray(this.permDescOriList));
            }
            if (this.permDescAllList != null && this.permDescAllList.size() > 0) {
                jSONObject.put(JSON_KEY_PERM_DESC_ALL, arrayListArrayCovertToJsonArray(this.permDescAllList));
            }
            if (!TextUtils.isEmpty(this.priUrl)) {
                jSONObject.put(JSON_KEY_PRI_URL, this.priUrl);
            }
            if (!TextUtils.isEmpty(this.updateTime)) {
                jSONObject.put(JSON_KEY_UPD_TIME, this.updateTime);
            }
            if (!TextUtils.isEmpty(this.appVersion)) {
                jSONObject.put(JSON_KEY_APP_VER, this.appVersion);
            }
            if (!TextUtils.isEmpty(this.devName)) {
                jSONObject.put(JSON_KEY_DEV_NAME, this.devName);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (jSONObject.length() > 0) {
            return jSONObject;
        }
        return null;
    }

    public static ApkDisplayInfo parseByString(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return parse(new JSONObject(str));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<String> jsonArrayCovertToArrayList(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() <= 0) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.optString(i));
        }
        return arrayList;
    }

    public static JSONArray arrayListArrayCovertToJsonArray(ArrayList<String> arrayList) {
        if (arrayList == null || arrayList.size() <= 0) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < arrayList.size(); i++) {
            jSONArray.put(arrayList.get(i));
        }
        return jSONArray;
    }

    public static ApkDisplayInfo parse(JSONObject jSONObject) throws JSONException {
        ApkDisplayInfo apkDisplayInfoCheckApkDisplayInfoIfNullWillInit = null;
        if (jSONObject == null) {
            return null;
        }
        if (jSONObject.has(JSON_KEY_APP_NAME)) {
            apkDisplayInfoCheckApkDisplayInfoIfNullWillInit = checkApkDisplayInfoIfNullWillInit(null);
            apkDisplayInfoCheckApkDisplayInfoIfNullWillInit.setAppName(jSONObject.optString(JSON_KEY_APP_NAME));
        }
        if (jSONObject.has(JSON_KEY_PERM_DESC)) {
            apkDisplayInfoCheckApkDisplayInfoIfNullWillInit = checkApkDisplayInfoIfNullWillInit(apkDisplayInfoCheckApkDisplayInfoIfNullWillInit);
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(JSON_KEY_PERM_DESC);
            if (jSONArrayOptJSONArray != null) {
                apkDisplayInfoCheckApkDisplayInfoIfNullWillInit.setPermDescList(jsonArrayCovertToArrayList(jSONArrayOptJSONArray));
                ArrayList<String> permDescAllList = apkDisplayInfoCheckApkDisplayInfoIfNullWillInit.getPermDescAllList();
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    try {
                        Object obj = jSONArrayOptJSONArray.get(i);
                        if (obj instanceof String) {
                            permDescAllList.add(k.a((String) obj));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                apkDisplayInfoCheckApkDisplayInfoIfNullWillInit.setPermDescAllList(permDescAllList);
            }
        }
        if (jSONObject.has(JSON_KEY_PERM_DESC_ORI)) {
            apkDisplayInfoCheckApkDisplayInfoIfNullWillInit = checkApkDisplayInfoIfNullWillInit(apkDisplayInfoCheckApkDisplayInfoIfNullWillInit);
            JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray(JSON_KEY_PERM_DESC_ORI);
            if (jSONArrayOptJSONArray2 != null) {
                apkDisplayInfoCheckApkDisplayInfoIfNullWillInit.setPermDescOriList(jsonArrayCovertToArrayList(jSONArrayOptJSONArray2));
                ArrayList<String> permDescAllList2 = apkDisplayInfoCheckApkDisplayInfoIfNullWillInit.getPermDescAllList();
                for (int i2 = 0; i2 < jSONArrayOptJSONArray2.length(); i2++) {
                    try {
                        Object obj2 = jSONArrayOptJSONArray2.get(i2);
                        if (obj2 instanceof String) {
                            permDescAllList2.add((String) obj2);
                        }
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
                apkDisplayInfoCheckApkDisplayInfoIfNullWillInit.setPermDescAllList(permDescAllList2);
            }
        }
        if (jSONObject.has(JSON_KEY_PRI_URL)) {
            apkDisplayInfoCheckApkDisplayInfoIfNullWillInit = checkApkDisplayInfoIfNullWillInit(apkDisplayInfoCheckApkDisplayInfoIfNullWillInit);
            apkDisplayInfoCheckApkDisplayInfoIfNullWillInit.setPriUrl(jSONObject.optString(JSON_KEY_PRI_URL));
        }
        if (jSONObject.has(JSON_KEY_UPD_TIME)) {
            apkDisplayInfoCheckApkDisplayInfoIfNullWillInit = checkApkDisplayInfoIfNullWillInit(apkDisplayInfoCheckApkDisplayInfoIfNullWillInit);
            apkDisplayInfoCheckApkDisplayInfoIfNullWillInit.setUpdateTime(jSONObject.optString(JSON_KEY_UPD_TIME));
        }
        if (jSONObject.has(JSON_KEY_APP_VER)) {
            apkDisplayInfoCheckApkDisplayInfoIfNullWillInit = checkApkDisplayInfoIfNullWillInit(apkDisplayInfoCheckApkDisplayInfoIfNullWillInit);
            apkDisplayInfoCheckApkDisplayInfoIfNullWillInit.setAppVersion(jSONObject.optString(JSON_KEY_APP_VER));
        }
        if (!jSONObject.has(JSON_KEY_DEV_NAME)) {
            return apkDisplayInfoCheckApkDisplayInfoIfNullWillInit;
        }
        ApkDisplayInfo apkDisplayInfoCheckApkDisplayInfoIfNullWillInit2 = checkApkDisplayInfoIfNullWillInit(apkDisplayInfoCheckApkDisplayInfoIfNullWillInit);
        apkDisplayInfoCheckApkDisplayInfoIfNullWillInit2.setDevName(jSONObject.optString(JSON_KEY_DEV_NAME));
        return apkDisplayInfoCheckApkDisplayInfoIfNullWillInit2;
    }
}
