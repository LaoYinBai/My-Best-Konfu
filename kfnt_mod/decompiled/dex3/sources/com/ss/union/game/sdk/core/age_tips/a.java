package com.ss.union.game.sdk.core.age_tips;

import android.text.TextUtils;
import com.ss.union.game.sdk.common.net.CoreNetClient;
import com.ss.union.game.sdk.common.net.http.base.callback.CoreNetCallback;
import com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestPost;
import com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback;
import com.ss.union.game.sdk.common.net.http.base.response.CoreNetResponse;
import com.ss.union.game.sdk.common.util.DeviceUtils;
import com.ss.union.game.sdk.core.base.config.AppIdManager;
import com.ss.union.game.sdk.core.base.constant.CoreUrls;
import com.ss.union.game.sdk.core.init.bean.GameSDKOption;
import com.ss.union.game.sdk.core.init.config.GameOptionConfig;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private static final String a = "LGAgeTipsHttp";

    public static void a(final CoreNetCallback<String> coreNetCallback) {
        try {
            CoreNetClient.post(CoreUrls.URL_CONFIG).param("sup_record_screen", "1").param("ram_mem", DeviceUtils.getTotalMemory() / 1048576).param("cpu_freq", DeviceUtils.getCPUMaxFreqKHz() / 1000).param(AppIdManager.KEY_APP_ID_IN_SP, AppIdManager.lgAppID()).api(new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.core.age_tips.a.1
                @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetError(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                    super.onNetError(aCoreRequestPost, coreNetResponse);
                    coreNetCallback.onError(coreNetResponse.code(), coreNetResponse.message());
                }

                @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onNetSuccess(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) throws JSONException {
                    GameSDKOption gameSDKOptionA = GameSDKOption.a(coreNetResponse.data);
                    GameOptionConfig.GameOption.updateGameOption(gameSDKOptionA);
                    if (!TextUtils.isEmpty(gameSDKOptionA.i.a)) {
                        coreNetCallback.onSuccess(gameSDKOptionA.i.a);
                    } else {
                        coreNetCallback.onError(-202, "系统错误，请稍后再试");
                    }
                }
            });
        } catch (Exception e) {
            coreNetCallback.onError(-202, e.getMessage());
        }
    }
}
