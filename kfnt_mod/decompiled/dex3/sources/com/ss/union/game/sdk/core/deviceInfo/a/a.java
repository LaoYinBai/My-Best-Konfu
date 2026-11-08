package com.ss.union.game.sdk.core.deviceInfo.a;

import android.util.Log;
import com.ss.union.game.sdk.common.callback.IFailCallback;
import com.ss.union.game.sdk.common.net.CoreNetClient;
import com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestPost;
import com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback;
import com.ss.union.game.sdk.common.net.http.base.response.CoreNetResponse;
import com.ss.union.game.sdk.common.util.DeviceUtils;
import com.ss.union.game.sdk.common.util.NetworkUtils;
import com.ss.union.game.sdk.core.LGSDKCore;
import com.ss.union.game.sdk.core.applog.AppLogManager;
import com.ss.union.game.sdk.core.base.config.AppIdManager;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.base.constant.CoreUrls;
import com.ss.union.game.sdk.core.deviceInfo.callback.ICloseDeviceInfoCallback;
import com.ss.union.game.sdk.core.deviceInfo.callback.IQueryDevicePrivacyInfoCallback;
import com.ss.union.game.sdk.core.deviceInfo.common.AppInfoHelper;
import com.ss.union.game.sdk.core.deviceInfo.in.IDeviceInfoService;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a implements IDeviceInfoService {
    private a() {
    }

    public static a a() {
        return C0076a.a;
    }

    /* renamed from: com.ss.union.game.sdk.core.deviceInfo.a.a$a, reason: collision with other inner class name */
    private static class C0076a {
        private static final a a = new a();

        private C0076a() {
        }
    }

    @Override // com.ss.union.game.sdk.core.deviceInfo.in.IDeviceInfoService
    public void queryDevicePrivacyInfo(final IQueryDevicePrivacyInfoCallback iQueryDevicePrivacyInfoCallback) {
        if (a(iQueryDevicePrivacyInfoCallback)) {
            if (ConfigManager.LoginConfig.isNoUserLogin()) {
                CoreNetClient.post(CoreUrls.DeviceInfo.URL_POST_QUERY_DEVICE_INFO).param("app_id", AppIdManager.lgAppID()).param(com.umeng.message.common.a.u, DeviceUtils.getAppPackageName()).param("bd_did", AppLogManager.getInstance().getDid()).api(new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.core.deviceInfo.a.a.1
                    @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
                    /* renamed from: a, reason: merged with bridge method [inline-methods] */
                    public void onNetError(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                        super.onNetError(aCoreRequestPost, coreNetResponse);
                        if (iQueryDevicePrivacyInfoCallback != null) {
                            iQueryDevicePrivacyInfoCallback.onFail(coreNetResponse.code(), coreNetResponse.message());
                        }
                    }

                    @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
                    /* renamed from: b, reason: merged with bridge method [inline-methods] */
                    public void onNetSuccess(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) throws JSONException {
                        super.onNetSuccess(aCoreRequestPost, coreNetResponse);
                        if (iQueryDevicePrivacyInfoCallback != null) {
                            iQueryDevicePrivacyInfoCallback.onQuerySuccess(AppInfoHelper.insertAppInfoToJsonObject(coreNetResponse.data.optJSONObject("data")).toString());
                        }
                    }
                });
            } else if (iQueryDevicePrivacyInfoCallback != null) {
                iQueryDevicePrivacyInfoCallback.onFail(-2, "接入账号功能, 不允许调用该接口");
            } else {
                Log.e("LGSDK", "callback is null...");
            }
        }
    }

    @Override // com.ss.union.game.sdk.core.deviceInfo.in.IDeviceInfoService
    public void closeDeviceInfo(final ICloseDeviceInfoCallback iCloseDeviceInfoCallback) {
        if (a(iCloseDeviceInfoCallback)) {
            if (ConfigManager.LoginConfig.isNoUserLogin()) {
                com.ss.union.game.sdk.core.deviceInfo.b.a.a();
                CoreNetClient.post(CoreUrls.DeviceInfo.URL_POST_CLOSE_DEVICE_INFO).param("app_id", AppIdManager.lgAppID()).param(com.umeng.message.common.a.u, DeviceUtils.getAppPackageName()).param("bd_did", AppLogManager.getInstance().getDid()).api(new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.core.deviceInfo.a.a.2
                    @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
                    /* renamed from: a, reason: merged with bridge method [inline-methods] */
                    public void onNetError(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                        super.onNetError(aCoreRequestPost, coreNetResponse);
                        com.ss.union.game.sdk.core.deviceInfo.b.a.c();
                        if (iCloseDeviceInfoCallback != null) {
                            iCloseDeviceInfoCallback.onFail(coreNetResponse.code(), coreNetResponse.message());
                        }
                    }

                    @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
                    /* renamed from: b, reason: merged with bridge method [inline-methods] */
                    public void onNetSuccess(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                        super.onNetSuccess(aCoreRequestPost, coreNetResponse);
                        com.ss.union.game.sdk.core.deviceInfo.b.a.b();
                        com.ss.union.game.sdk.core.realName.b.a.a();
                        if (iCloseDeviceInfoCallback != null) {
                            iCloseDeviceInfoCallback.onSuccess(coreNetResponse.data.optString("message"));
                        }
                    }
                });
            } else if (iCloseDeviceInfoCallback != null) {
                iCloseDeviceInfoCallback.onFail(-2, "接入账号功能, 不允许调用该接口");
            } else {
                Log.e("LGSDK", "callback is null...");
            }
        }
    }

    private boolean a(IFailCallback iFailCallback) {
        if (!LGSDKCore.isSdkInitSuccess()) {
            if (iFailCallback != null) {
                iFailCallback.onFail(-204, "SDK还未初始化完成");
                Log.e("LGSDK", "SDK has not yet been initialized");
            } else {
                Log.e("LGSDK", "callback is null...");
            }
            return false;
        }
        if (NetworkUtils.isNetworkAvailable()) {
            return true;
        }
        if (iFailCallback != null) {
            iFailCallback.onFail(100, "网络异常，请保持网络连接畅通，再重新操作");
            Log.e("LGSDK", "网络异常，请保持网络连接畅通，再重新操作");
        } else {
            Log.e("LGSDK", "callback is null...");
        }
        return false;
    }
}
