package com.bytedance.sdk.open.aweme.share;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import com.bytedance.sdk.open.aweme.base.TikTokMediaContent;
import com.bytedance.sdk.open.aweme.base.TikTokMicroAppInfo;
import com.bytedance.sdk.open.aweme.common.constants.ParamKeyConstants;
import com.bytedance.sdk.open.aweme.common.model.BaseReq;
import com.bytedance.sdk.open.aweme.common.model.BaseResp;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class Share {
    public static final int IMAGE = 1;
    private static final String TAG = "Aweme.OpenSDK.Share";
    public static final int VIDEO = 0;

    public static class Request extends BaseReq {
        public Bundle extras;
        public String mCallerPackage;
        public String mCallerSDKVersion;
        public String mClientKey;
        public String mHashTag;
        public TikTokMediaContent mMediaContent;
        public TikTokMicroAppInfo mMicroAppInfo;
        public String mState;
        public int mTargetSceneType = 0;

        @Deprecated
        public int mTargetApp = 2;

        @Override // com.bytedance.sdk.open.aweme.common.model.BaseReq
        public int getType() {
            return 3;
        }

        public Request() {
        }

        public Request(Bundle bundle) {
            fromBundle(bundle);
        }

        @Override // com.bytedance.sdk.open.aweme.common.model.BaseReq
        @SuppressLint({"MissingSuperCall"})
        public void fromBundle(Bundle bundle) {
            this.mCallerPackage = bundle.getString(ParamKeyConstants.ShareParams.CALLER_PKG);
            this.mCallerSDKVersion = bundle.getString(ParamKeyConstants.ShareParams.CALLER_SDK_VERSION);
            this.extras = bundle.getBundle(ParamKeyConstants.ShareParams.EXTRA);
            this.callerLocalEntry = bundle.getString(ParamKeyConstants.ShareParams.CALLER_LOCAL_ENTRY);
            this.mState = bundle.getString(ParamKeyConstants.ShareParams.STATE);
            this.mClientKey = bundle.getString(ParamKeyConstants.ShareParams.CLIENT_KEY);
            this.mTargetSceneType = bundle.getInt("_aweme_open_sdk_params_target_scene", 0);
            this.mHashTag = bundle.getString("_aweme_open_sdk_params_target_scene", "");
            this.mMediaContent = TikTokMediaContent.Builder.fromBundle(bundle);
            this.mMicroAppInfo = TikTokMicroAppInfo.unserialize(bundle);
        }

        @Override // com.bytedance.sdk.open.aweme.common.model.BaseReq
        @SuppressLint({"MissingSuperCall"})
        public void toBundle(Bundle bundle) {
            bundle.putInt(ParamKeyConstants.ShareParams.TYPE, getType());
            bundle.putBundle(ParamKeyConstants.ShareParams.EXTRA, this.extras);
            bundle.putString(ParamKeyConstants.ShareParams.CALLER_LOCAL_ENTRY, this.callerLocalEntry);
            bundle.putString(ParamKeyConstants.ShareParams.CLIENT_KEY, this.mClientKey);
            bundle.putString(ParamKeyConstants.ShareParams.CALLER_SDK_VERSION, this.mCallerSDKVersion);
            bundle.putString(ParamKeyConstants.ShareParams.CALLER_PKG, this.mCallerPackage);
            bundle.putString(ParamKeyConstants.ShareParams.STATE, this.mState);
            bundle.putAll(TikTokMediaContent.Builder.toBundle(this.mMediaContent, false));
            bundle.putInt("_aweme_open_sdk_params_target_scene", this.mTargetSceneType);
            bundle.putString("_aweme_open_sdk_params_target_scene", this.mHashTag);
            if (this.mMicroAppInfo != null) {
                this.mMicroAppInfo.serialize(bundle);
            }
        }

        public void toBundleForOldVersion(Bundle bundle) {
            bundle.putInt(ParamKeyConstants.ShareParams.TYPE, getType());
            bundle.putBundle(ParamKeyConstants.ShareParams.EXTRA, this.extras);
            bundle.putString(ParamKeyConstants.ShareParams.CALLER_LOCAL_ENTRY, this.callerLocalEntry);
            bundle.putString(ParamKeyConstants.ShareParams.CLIENT_KEY, this.mClientKey);
            bundle.putString(ParamKeyConstants.ShareParams.CALLER_SDK_VERSION, this.mCallerSDKVersion);
            bundle.putString(ParamKeyConstants.ShareParams.CALLER_PKG, this.mCallerPackage);
            bundle.putString(ParamKeyConstants.ShareParams.STATE, this.mState);
            bundle.putAll(TikTokMediaContent.Builder.toBundle(this.mMediaContent, true));
            bundle.putInt("_aweme_open_sdk_params_target_scene", this.mTargetSceneType);
            bundle.putString("_aweme_open_sdk_params_target_scene", this.mHashTag);
            if (this.mMicroAppInfo != null) {
                this.mMicroAppInfo.serialize(bundle);
            }
        }

        @Override // com.bytedance.sdk.open.aweme.common.model.BaseReq
        @SuppressLint({"MissingSuperCall"})
        public boolean checkArgs() {
            if (this.mMediaContent == null) {
                Log.e(Share.TAG, "checkArgs fail ,mediaContent is null");
                return false;
            }
            return this.mMediaContent.checkArgs();
        }
    }

    public static class Response extends BaseResp {
        public int errorCode;
        public String errorMsg;
        public Bundle extras;
        public String state;

        @Override // com.bytedance.sdk.open.aweme.common.model.BaseResp
        public int getType() {
            return 4;
        }

        public Response() {
        }

        public Response(Bundle bundle) {
            fromBundle(bundle);
        }

        @Override // com.bytedance.sdk.open.aweme.common.model.BaseResp
        @SuppressLint({"MissingSuperCall"})
        public void fromBundle(Bundle bundle) {
            this.errorCode = bundle.getInt(ParamKeyConstants.ShareParams.ERROR_CODE);
            this.errorMsg = bundle.getString(ParamKeyConstants.ShareParams.ERROR_MSG);
            this.extras = bundle.getBundle(ParamKeyConstants.ShareParams.EXTRA);
            this.state = bundle.getString(ParamKeyConstants.ShareParams.STATE);
        }

        @Override // com.bytedance.sdk.open.aweme.common.model.BaseResp
        @SuppressLint({"MissingSuperCall"})
        public void toBundle(Bundle bundle) {
            bundle.putInt(ParamKeyConstants.ShareParams.ERROR_CODE, this.errorCode);
            bundle.putString(ParamKeyConstants.ShareParams.ERROR_MSG, this.errorMsg);
            bundle.putInt(ParamKeyConstants.ShareParams.TYPE, getType());
            bundle.putBundle(ParamKeyConstants.ShareParams.EXTRA, this.extras);
            bundle.putString(ParamKeyConstants.ShareParams.STATE, this.state);
        }
    }
}
