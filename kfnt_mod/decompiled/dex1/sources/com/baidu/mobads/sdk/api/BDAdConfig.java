package com.baidu.mobads.sdk.api;

import android.content.Context;
import anet.channel.strategy.dispatch.DispatchConstants;
import com.baidu.mobads.sdk.internal.al;
import com.baidu.mobads.sdk.internal.by;
import com.baidu.mobads.sdk.internal.u;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class BDAdConfig {
    private Context mAppContext;
    private String mAppName;
    private String mAppsid;
    private String mChannelId;
    private JSONObject mConfigObj;
    private JSONObject mDialogParams;
    private boolean mHttps;
    private boolean mLpMultiProcess;
    private boolean mMtjSwitch;
    private boolean mUseActivityDialog;
    private int mVideoCacheCapacityMb;

    private BDAdConfig(Context context, Builder builder) {
        this.mUseActivityDialog = true;
        this.mHttps = builder.mHttps;
        this.mAppContext = context;
        this.mVideoCacheCapacityMb = builder.mVideoCacheCapacityMb;
        this.mAppName = builder.mAppName;
        this.mAppsid = builder.mAppsid;
        this.mChannelId = builder.mChannelId;
        this.mLpMultiProcess = builder.mLpSupportMultiProcess;
        this.mUseActivityDialog = builder.mUseActivityDialog;
        this.mDialogParams = builder.mDialogParams;
        this.mMtjSwitch = builder.mMtjSwitch;
    }

    public void init() throws JSONException {
        this.mConfigObj = new JSONObject();
        try {
            this.mConfigObj.put("https", "" + this.mHttps);
            this.mConfigObj.put(DispatchConstants.APP_NAME, this.mAppName);
            this.mConfigObj.put("videoCacheSize", "" + this.mVideoCacheCapacityMb);
            this.mConfigObj.put("appsid", this.mAppsid);
            this.mConfigObj.put("channelId", this.mChannelId);
            this.mConfigObj.put("lpMultiProcess", "" + this.mLpMultiProcess);
            this.mConfigObj.put("useActivityDialog", "" + this.mUseActivityDialog);
            this.mConfigObj.put("dialog_params", this.mDialogParams);
            this.mConfigObj.put("mtj_switch", this.mMtjSwitch);
            by.a().a(this.mLpMultiProcess);
            by.a().b(this.mHttps);
        } catch (Exception e) {
            e.printStackTrace();
        }
        u.a().a(this.mAppContext, new u.a() { // from class: com.baidu.mobads.sdk.api.BDAdConfig.1
            @Override // com.baidu.mobads.sdk.internal.u.a
            public void onFailure() {
            }

            @Override // com.baidu.mobads.sdk.internal.u.a
            public void onSuccess() {
                IXAdContainerFactory iXAdContainerFactoryC = u.a().c();
                if (iXAdContainerFactoryC != null) {
                    iXAdContainerFactoryC.initConfig(BDAdConfig.this.mConfigObj);
                    iXAdContainerFactoryC.onTaskDistribute(al.f3114a, MobadsPermissionSettings.getPermissionInfo());
                }
            }
        });
    }

    public static class Builder {
        private String mAppName;
        private String mAppsid;
        private String mChannelId;
        private JSONObject mDialogParams;
        private boolean mLpSupportMultiProcess;
        private int mVideoCacheCapacityMb;
        private boolean mHttps = true;
        private boolean mUseActivityDialog = true;
        private boolean mMtjSwitch = true;

        public Builder setMtjSwitch(boolean z) {
            this.mMtjSwitch = z;
            return this;
        }

        public Builder setAppName(String str) {
            this.mAppName = str;
            return this;
        }

        public Builder setHttps(boolean z) {
            this.mHttps = z;
            return this;
        }

        public Builder setVideoCacheCapacityMb(int i) {
            this.mVideoCacheCapacityMb = i;
            return this;
        }

        public Builder setAppsid(String str) {
            this.mAppsid = str;
            return this;
        }

        @Deprecated
        public Builder useActivityDialog(Boolean bool) {
            this.mUseActivityDialog = bool.booleanValue();
            return this;
        }

        public Builder setDialogParams(BDDialogParams bDDialogParams) {
            this.mDialogParams = bDDialogParams.toJson();
            return this;
        }

        public Builder setLpMultiProcess(boolean z) {
            this.mLpSupportMultiProcess = z;
            return this;
        }

        public Builder setChannelId(String str) {
            this.mChannelId = str;
            return this;
        }

        public BDAdConfig build(Context context) {
            return new BDAdConfig(context, this);
        }
    }
}
