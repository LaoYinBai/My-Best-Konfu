package com.ss.union.game.sdk.core.antiAddiction.bean;

import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AntiAddictionInfo {
    public static final int DEFAULT_REMAINING_TIME = -1;
    public String id = "";
    public int remainingTime = -1;
    public boolean isHoliday = false;
    public boolean isInMinorLimit = true;
    public long serverTime = System.currentTimeMillis() / 1000;

    public void parse(JSONObject jSONObject, long j, String str) {
        JSONObject jSONObjectOptJSONObject;
        if (jSONObject == null || (jSONObjectOptJSONObject = jSONObject.optJSONObject("data")) == null) {
            return;
        }
        this.serverTime = j;
        this.id = str;
        this.remainingTime = jSONObjectOptJSONObject.optInt("remaining_game_time", -1);
        this.isHoliday = jSONObjectOptJSONObject.optBoolean("is_legal_holiday", this.isHoliday);
        this.isInMinorLimit = jSONObjectOptJSONObject.optBoolean("is_prohibition_time_for_minors", this.isInMinorLimit);
    }
}
