package com.mbridge.msdk.foundation.tools;

import com.heytap.mcssdk.mode.CommandMessage;
import com.kwad.sdk.core.config.item.TipsConfigItem;
import com.mbridge.msdk.playercommon.exoplayer2.text.ttml.TtmlNode;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: JsonUtils.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class i {
    public static ArrayList<String> a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        int length = jSONArray.length();
        ArrayList<String> arrayList = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            arrayList.add(jSONArray.optString(i));
        }
        return arrayList;
    }

    public static String a(int i, int i2, int i3, int i4, int i5) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            try {
                jSONObject.put(CommandMessage.CODE, 0);
                jSONObject.put("message", "Sucess");
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("rotateAngle", i);
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put(TtmlNode.LEFT, i2);
                jSONObject3.put(TtmlNode.RIGHT, i3);
                jSONObject3.put("top", i4);
                jSONObject3.put(TipsConfigItem.TipConfigData.BOTTOM, i5);
                jSONObject2.put("cutoutInfo", jSONObject3);
                jSONObject.put("data", jSONObject2);
            } catch (Exception e) {
                s.d("JSONUtils", e.getMessage());
            }
        } catch (Throwable th) {
            s.d("JSONUtils", th.getMessage());
            try {
                jSONObject.put(CommandMessage.CODE, 1);
                jSONObject.put("message", "Fail");
            } catch (JSONException e2) {
                s.d("JSONUtils", e2.getMessage());
            }
        }
        return jSONObject.toString();
    }
}
