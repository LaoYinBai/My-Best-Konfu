package com.ss.union.game.sdk.core.deviceInfo.common;

import com.ss.union.game.sdk.common.util.DeviceUtils;
import com.ss.union.game.sdk.common.util.UIUtils;
import com.ss.union.game.sdk.common.util.logger.LogUtils;
import com.umeng.commonsdk.proguard.o;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AppInfoHelper {
    public static JSONObject insertAppInfoToJsonObject(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
            LogUtils.log("LGSDK", "queryDeviceInfo server data is null");
        }
        try {
            jSONObject.putOpt(o.y, UIUtils.getScreenResolution());
            jSONObject.putOpt(o.M, DeviceUtils.getLanguage());
            jSONObject.putOpt("tz_name", Integer.valueOf(DeviceUtils.getTimeZoneOffset()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
