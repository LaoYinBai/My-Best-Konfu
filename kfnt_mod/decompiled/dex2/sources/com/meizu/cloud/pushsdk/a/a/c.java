package com.meizu.cloud.pushsdk.a.a;

import com.heytap.mcssdk.mode.CommandMessage;
import com.mbridge.msdk.playercommon.exoplayer2.text.ttml.TtmlNode;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {
    private final int a;
    private final String b;

    public c(int i, String str) {
        this.a = i;
        this.b = str;
    }

    public String toString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(CommandMessage.CODE, this.a);
            jSONObject.put(TtmlNode.TAG_BODY, this.b);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "[NetResponse] " + jSONObject.toString();
    }
}
