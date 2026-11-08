package com.ss.union.game.sdk.core.realName.entity;

import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class CheckDeviceRealNameInfo {
    private static final String KEY_IDENTIFY_VALIDATED = "has_identify_validated";
    private static final String KEY_IS_ADULT = "is_adult";
    private static final String KEY_IS_LINK_TIPS = "show_link_tips";
    private static final String KEY_LINK_TIPS = "link_tips";
    public boolean is_adult;
    public boolean is_identify_validated;
    public String link_tips;
    public boolean show_link_tips;

    public void parse(JSONObject jSONObject) {
        try {
            this.is_identify_validated = jSONObject.optBoolean("has_identify_validated", this.is_identify_validated);
            this.is_adult = jSONObject.optBoolean("is_adult", this.is_adult);
            this.link_tips = jSONObject.optString(KEY_LINK_TIPS, "");
            this.show_link_tips = jSONObject.optBoolean(KEY_IS_LINK_TIPS, this.show_link_tips);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
