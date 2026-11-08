package com.ss.union.game.sdk.core.init.b;

import android.util.Log;
import com.ss.union.game.sdk.common.net.CoreNetClient;
import com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestGet;
import com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback;
import com.ss.union.game.sdk.common.net.http.base.response.CoreNetResponse;
import com.ss.union.game.sdk.common.util.flow.FlowItem;
import com.ss.union.game.sdk.core.base.config.AppIdManager;
import com.ss.union.game.sdk.core.base.constant.CoreUrls;
import com.ss.union.game.sdk.core.base.event.PageStater;
import java.util.HashMap;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class d extends FlowItem {
    @Override // com.ss.union.game.sdk.common.util.flow.FlowItem
    public String toString() {
        return "AppIdInit";
    }

    @Override // com.ss.union.game.sdk.common.util.flow.FlowItem
    public void doIt() throws JSONException {
        a();
        finish();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void a() throws JSONException {
        PageStater.V1.onEvent("request_appid", AppIdManager.secretAppId());
        final String string = UUID.randomUUID().toString();
        ((ACoreRequestGet) ((ACoreRequestGet) ((ACoreRequestGet) CoreNetClient.get(CoreUrls.Init.URL_INIT_UPDATE_APP_ID).urlParam("old", AppIdManager.secretAppId())).urlParam("uuid", string)).urlParam(com.ss.union.game.sdk.redemptionCode.a.t, System.currentTimeMillis() + "")).api(new ICoreNetCallback<JSONObject, ACoreRequestGet>() { // from class: com.ss.union.game.sdk.core.init.b.d.1
            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNetError(ACoreRequestGet aCoreRequestGet, CoreNetResponse<JSONObject, ACoreRequestGet> coreNetResponse) throws JSONException {
                super.onNetError(aCoreRequestGet, coreNetResponse);
                HashMap map = new HashMap();
                map.put("error_mgs", Log.getStackTraceString(coreNetResponse.error));
                PageStater.V1.onEvent("request_appid_error", string, null, 0, 0L, "", map);
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onNetSuccess(ACoreRequestGet aCoreRequestGet, CoreNetResponse<JSONObject, ACoreRequestGet> coreNetResponse) throws JSONException {
                super.onNetSuccess(aCoreRequestGet, coreNetResponse);
                if (coreNetResponse == null || coreNetResponse.data == null || !"success".equals(coreNetResponse.data.optString("message"))) {
                    return;
                }
                String strOptString = coreNetResponse.data.optString("data");
                AppIdManager.syncAppId(strOptString);
                PageStater.V1.onEvent("get_appid", strOptString);
            }
        });
    }
}
