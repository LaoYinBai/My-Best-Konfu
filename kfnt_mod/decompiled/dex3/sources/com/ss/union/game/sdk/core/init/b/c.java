package com.ss.union.game.sdk.core.init.b;

import com.ss.union.game.sdk.common.net.CoreNetClient;
import com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestPost;
import com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback;
import com.ss.union.game.sdk.common.net.http.base.response.CoreNetResponse;
import com.ss.union.game.sdk.common.util.DeviceUtils;
import com.ss.union.game.sdk.common.util.flow.FlowItem;
import com.ss.union.game.sdk.core.base.config.AppIdManager;
import com.ss.union.game.sdk.core.base.constant.CoreUrls;
import com.ss.union.game.sdk.core.init.bean.GameSDKOption;
import com.ss.union.game.sdk.core.init.config.GameOptionConfig;
import com.ss.union.game.sdk.core.realName.event.RealNameLogEvent;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c extends FlowItem {
    @Override // com.ss.union.game.sdk.common.util.flow.FlowItem
    public String toString() {
        return "AppConfigInit";
    }

    @Override // com.ss.union.game.sdk.common.util.flow.FlowItem
    public void doIt() {
        a();
    }

    private void a() {
        int cPUMaxFreqKHz = DeviceUtils.getCPUMaxFreqKHz() / 1000;
        long totalMemory = DeviceUtils.getTotalMemory() / 1048576;
        RealNameLogEvent.reportRealNameAwardRequest(DeviceUtils.getAppPackageName());
        CoreNetClient.post(CoreUrls.URL_CONFIG).param("sup_record_screen", "1").param("ram_mem", totalMemory).param("cpu_freq", cPUMaxFreqKHz).param(AppIdManager.KEY_APP_ID_IN_SP, AppIdManager.lgAppID()).api(new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.core.init.b.c.1
            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNetError(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) throws JSONException {
                super.onNetError(aCoreRequestPost, coreNetResponse);
                RealNameLogEvent.onWindowCloseOrNotConfig();
                c.this.finish();
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onNetSuccess(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) throws JSONException {
                super.onNetSuccess(aCoreRequestPost, coreNetResponse);
                RealNameLogEvent.onWindowCloseOrNotConfig();
                GameSDKOption gameSDKOptionA = GameSDKOption.a(coreNetResponse.data);
                GameOptionConfig.GameOption.updateGameOption(gameSDKOptionA);
                if (gameSDKOptionA != null) {
                    RealNameLogEvent.reportDeviceRealNameDay(gameSDKOptionA.f.b.c);
                }
                c.this.finish();
            }
        });
    }
}
