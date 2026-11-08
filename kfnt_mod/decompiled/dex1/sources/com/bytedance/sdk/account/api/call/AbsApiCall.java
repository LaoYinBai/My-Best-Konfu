package com.bytedance.sdk.account.api.call;

import com.bytedance.sdk.account.api.call.BaseApiResponse;
import com.bytedance.sdk.account.impl.AbsControllerApiCall;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class AbsApiCall<T extends BaseApiResponse> extends AbsControllerApiCall {
    private boolean cancel;

    public abstract void onResponse(T t);

    public JSONObject onSendEvent(String str) {
        return null;
    }

    public boolean isCancel() {
        return this.cancel;
    }

    public void cancel() {
        this.cancel = true;
        cancelApi();
    }

    public final void dispatchOnResponse(T t) {
        if (isCancel()) {
            return;
        }
        onResponse(t);
    }
}
