package com.kwad.sdk.core.webview.request;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.kwad.sdk.core.a.d;
import com.kwad.sdk.core.a.e;
import com.kwad.sdk.core.network.BaseResultData;
import com.kwad.sdk.utils.at;
import com.kwad.sdk.utils.t;
import java.io.Serializable;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class WebCardGetDataResponse extends BaseResultData implements com.kwad.sdk.core.b, Serializable {
    private static final String TAG = "WebCardGetDataResponse";
    private static final long serialVersionUID = 2407409365862659643L;
    public String data;

    @Override // com.kwad.sdk.core.network.BaseResultData, com.kwad.sdk.core.b
    public void parseJson(@Nullable JSONObject jSONObject) throws Throwable {
        super.parseJson(jSONObject);
        if (jSONObject == null) {
            return;
        }
        com.kwad.sdk.core.d.a.a(TAG, "jo=" + jSONObject.toString());
        String strOptString = jSONObject.optString("egid");
        if (!TextUtils.isEmpty(strOptString)) {
            e.a(strOptString);
        }
        try {
            String strOptString2 = jSONObject.optString("data");
            com.kwad.sdk.core.d.a.a(TAG, "WebCardGetDataResponse dataStr=" + strOptString2);
            if (at.a(strOptString2)) {
                return;
            }
            this.data = d.b(strOptString2);
            com.kwad.sdk.core.d.a.a(TAG, "WebCardGetDataResponse data=" + this.data);
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.a(e);
        }
    }

    @Override // com.kwad.sdk.core.network.BaseResultData, com.kwad.sdk.core.b
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        t.a(json, "data", this.data);
        return json;
    }
}
