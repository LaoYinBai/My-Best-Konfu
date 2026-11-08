package com.bytedance.sdk.open.aweme.base;

import android.os.Bundle;
import android.text.TextUtils;
import com.bytedance.sdk.open.aweme.common.constants.ParamKeyConstants;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class TikTokMicroAppInfo {
    private String appId;
    private String appTitle;
    private String appUrl;
    private String description;

    public void serialize(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        bundle.putString(ParamKeyConstants.ShareParams.SHARE_MICROAPP_INFO, new Gson().toJson(this));
    }

    public static TikTokMicroAppInfo unserialize(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String string = bundle.getString(ParamKeyConstants.ShareParams.SHARE_MICROAPP_INFO);
        try {
            if (!TextUtils.isEmpty(string)) {
                return (TikTokMicroAppInfo) new Gson().fromJson(string, TikTokMicroAppInfo.class);
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public String getAppTitle() {
        return this.appTitle;
    }

    public void setAppTitle(String str) {
        this.appTitle = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getAppUrl() {
        return this.appUrl;
    }

    public void setAppUrl(String str) {
        this.appUrl = str;
    }
}
