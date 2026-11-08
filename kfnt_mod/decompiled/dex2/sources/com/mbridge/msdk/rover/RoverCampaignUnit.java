package com.mbridge.msdk.rover;

import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.system.NoProGuard;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class RoverCampaignUnit implements NoProGuard, Serializable {
    public static final String JSON_KEY_AD_TYPE = "ad_type";
    public static final String JSON_KEY_DATA = "data";
    public static final String JSON_KEY_END_SCREEN_URL = "end_screen_url";
    public static final String JSON_KEY_FRAME_ADS = "frames";
    public static final String JSON_KEY_HTML_URL = "html_url";
    public static final String JSON_KEY_ONLY_IMPRESSION_URL = "only_impression_url";
    public static final String JSON_KEY_PARENT_SESSION_ID = "parent_session_id";
    public static final String JSON_KEY_SESSION_ID = "session_id";
    public static final String JSON_KEY_TEMPLATE = "template";
    public static final String JSON_KEY_UNIT_SIZE = "unit_size";
    private static final long serialVersionUID = 1;
    private int adType;
    public ArrayList<CampaignEx> ads;
    private String htmlUrl;
    private String onlyImpressionUrl;
    private String parentSessionId;
    private String sessionId;
    private String unitSize;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(String str) {
        this.sessionId = str;
    }

    public String getParentSessionId() {
        return this.parentSessionId;
    }

    public void setParentSessionId(String str) {
        this.parentSessionId = str;
    }

    public String getUnitSize() {
        return this.unitSize;
    }

    public void setUnitSize(String str) {
        this.unitSize = str;
    }

    public ArrayList<CampaignEx> getAds() {
        return this.ads;
    }

    public void setAds(ArrayList<CampaignEx> arrayList) {
        this.ads = arrayList;
    }

    public static RoverCampaignUnit parseCampaignUnit(JSONObject jSONObject) {
        RoverCampaignUnit roverCampaignUnit;
        if (jSONObject == null) {
            return null;
        }
        try {
            roverCampaignUnit = new RoverCampaignUnit();
            try {
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("data");
                if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0) {
                    return roverCampaignUnit;
                }
                ArrayList<CampaignEx> arrayList = new ArrayList<>();
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    arrayList.add(CampaignEx.parseCampaign(jSONArrayOptJSONArray.optJSONObject(i), jSONObject.optString("only_impression_url"), jSONObject.optString("html_url"), jSONObject.optString("end_screen_url"), false, null));
                }
                roverCampaignUnit.setAds(arrayList);
                return roverCampaignUnit;
            } catch (Exception unused) {
                s.d("", "parse campaign unit exception");
                return roverCampaignUnit;
            }
        } catch (Exception unused2) {
            roverCampaignUnit = null;
        }
    }
}
