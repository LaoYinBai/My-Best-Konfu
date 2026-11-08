package com.bytedance.sdk.open.aweme.base;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.bytedance.sdk.open.aweme.common.constants.ParamKeyConstants;
import com.bytedance.sdk.open.aweme.utils.AppUtil;
import com.bytedance.sdk.open.aweme.utils.SignatureUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class BaseCheckHelperImpl implements IAPPCheckHelper {
    private Context mContext;

    protected abstract int getAuthRequestApi();

    @Override // com.bytedance.sdk.open.aweme.base.IAPPCheckHelper
    @NonNull
    public String getRemoteAuthEntryActivity() {
        return "openauthorize.AwemeAuthorizedActivity";
    }

    public abstract String getSignature();

    public BaseCheckHelperImpl(Context context) {
        this.mContext = context;
    }

    @Override // com.bytedance.sdk.open.aweme.base.IAPPCheckHelper
    public boolean isAppSupportAuthorization() {
        return isAppInstalled() && isAppSupportAuthApi() && SignatureUtils.validateSign(this.mContext, getPackageName(), getSignature());
    }

    @Override // com.bytedance.sdk.open.aweme.base.IAPPCheckHelper
    public boolean isAppSupportShare() {
        return isAppInstalled() && isAppSupportShareApi(getPackageName(), getRemoteAuthEntryActivity(), 2);
    }

    private boolean isAppSupportAPI(String str, String str2, int i) {
        if (this.mContext == null || TextUtils.isEmpty(str) || !isAppInstalled()) {
            return false;
        }
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(str, AppUtil.buildComponentClassName(str, str2)));
        ActivityInfo activityInfoResolveActivityInfo = intent.resolveActivityInfo(this.mContext.getPackageManager(), 65536);
        return activityInfoResolveActivityInfo != null && activityInfoResolveActivityInfo.exported && getPlatformSDKVersion(str, str2) >= i;
    }

    private boolean isAppSupportAuthApi() {
        return isAppSupportAPI(getPackageName(), getRemoteAuthEntryActivity(), getAuthRequestApi());
    }

    public int getPlatformSDKVersion(String str, String str2) throws PackageManager.NameNotFoundException {
        if (this.mContext == null || TextUtils.isEmpty(str) || !AppUtil.isAppInstalled(this.mContext, getPackageName())) {
            return -1;
        }
        try {
            ActivityInfo activityInfo = this.mContext.getPackageManager().getActivityInfo(new ComponentName(str, AppUtil.buildComponentClassName(str, str2)), 128);
            if (activityInfo != null && activityInfo.metaData != null) {
                return activityInfo.metaData.getInt(ParamKeyConstants.META_PLATFORM_SDK_VERSION, -1);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private boolean isAppSupportShareApi(String str, String str2, int i) {
        return isAppSupportAPI(str, str2, i);
    }

    @Override // com.bytedance.sdk.open.aweme.base.IAPPCheckHelper
    public boolean isAppInstalled() {
        return AppUtil.isAppInstalled(this.mContext, getPackageName());
    }

    @Override // com.bytedance.sdk.open.aweme.base.IAPPCheckHelper
    public boolean isSupportNewTiktokApi() {
        return getPlatformSDKVersion(getPackageName(), getRemoteAuthEntryActivity()) >= 3;
    }
}
