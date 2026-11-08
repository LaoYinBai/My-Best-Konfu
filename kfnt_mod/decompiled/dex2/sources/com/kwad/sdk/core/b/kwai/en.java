package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.live.mode.LiveInfo;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class en implements com.kwad.sdk.core.d<LiveInfo.User> {
    @Override // com.kwad.sdk.core.d
    public void a(LiveInfo.User user, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        user.headurls = new ArrayList();
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("headurls");
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                LiveInfo.User.HeadUrl headUrl = new LiveInfo.User.HeadUrl();
                headUrl.parseJson(jSONArrayOptJSONArray.optJSONObject(i));
                user.headurls.add(headUrl);
            }
        }
        user.user_id = jSONObject.optLong("user_id");
        user.user_name = jSONObject.optString("user_name");
        if (jSONObject.opt("user_name") == JSONObject.NULL) {
            user.user_name = "";
        }
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(LiveInfo.User user, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "headurls", user.headurls);
        com.kwad.sdk.utils.t.a(jSONObject, "user_id", user.user_id);
        com.kwad.sdk.utils.t.a(jSONObject, "user_name", user.user_name);
        return jSONObject;
    }
}
