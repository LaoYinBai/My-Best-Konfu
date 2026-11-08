package com.kwad.sdk.core.network;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.kwad.sdk.utils.at;
import com.kwad.sdk.utils.t;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.io.Serializable;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class BaseResultData implements com.kwad.sdk.core.b, Serializable {
    private static final int CODE_RESULT_OK = 1;
    private static final long serialVersionUID = -8657363515914699792L;
    public String cookie;
    public String errorMsg;
    public String extra;
    public long llsid;
    public int result;

    public boolean isDataEmpty() {
        return false;
    }

    public boolean isResultOk() {
        return this.result == 1;
    }

    @Override // com.kwad.sdk.core.b
    public void parseJson(@Nullable JSONObject jSONObject) throws Throwable {
        if (jSONObject == null) {
            return;
        }
        this.llsid = jSONObject.optLong("llsid");
        this.result = jSONObject.optInt("result");
        this.errorMsg = jSONObject.optString("errorMsg");
        String strOptString = jSONObject.optString(PushConstants.EXTRA);
        if (!at.a(strOptString)) {
            this.extra = com.kwad.sdk.core.a.d.b(strOptString);
        }
        this.cookie = jSONObject.optString("cookie");
        if (TextUtils.isEmpty(this.cookie)) {
            return;
        }
        e.a().a(this.cookie);
    }

    @Override // com.kwad.sdk.core.b
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        t.a(jSONObject, "llsid", this.llsid);
        t.a(jSONObject, PushConstants.EXTRA, this.extra);
        t.a(jSONObject, "result", this.result);
        t.a(jSONObject, "errorMsg", this.errorMsg);
        t.a(jSONObject, "cookie", this.cookie);
        return jSONObject;
    }
}
