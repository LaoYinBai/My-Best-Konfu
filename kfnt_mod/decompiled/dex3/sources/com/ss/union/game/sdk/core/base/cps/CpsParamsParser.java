package com.ss.union.game.sdk.core.base.cps;

import com.sigmob.sdk.common.mta.PointCategory;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class CpsParamsParser {
    public JSONObject params;
    public String normalizedId = "";
    public String applogChannel = "";
    public String yidongAppId = "";
    public String yidongAppKey = "";
    public String liantongAppId = "";
    public String liantongAppKey = "";
    public String dianxinAppId = "";
    public String dianxinAppKey = "";
    public Map<String, String> adParams = new HashMap();
    public boolean hasPush = false;
    public Map<String, String> sdkCommonParams = new HashMap();
    public Map<String, String> cpSelfParams = new HashMap();

    public CpsParamsParser() {
    }

    public CpsParamsParser(JSONObject jSONObject) {
        this.params = jSONObject;
        parseInit();
        parseAppLog();
        parseOneKeyLogin();
        parsePush();
        parseAd();
        parseCpSelf();
    }

    private void parseInit() {
        JSONObject jSONObjectOptJSONObject = this.params.optJSONObject(PointCategory.INIT);
        if (jSONObjectOptJSONObject != null) {
            this.normalizedId = jSONObjectOptJSONObject.optString("normalizedId");
        }
    }

    private void parseAppLog() {
        JSONObject jSONObjectOptJSONObject = this.params.optJSONObject("applog");
        if (jSONObjectOptJSONObject != null) {
            this.applogChannel = jSONObjectOptJSONObject.optString("applogChannel");
        }
    }

    private void parseOneKeyLogin() {
        JSONObject jSONObjectOptJSONObject = this.params.optJSONObject("oneKeyLoginId");
        if (jSONObjectOptJSONObject != null) {
            this.yidongAppId = jSONObjectOptJSONObject.optString("yidongAppId");
            this.yidongAppKey = jSONObjectOptJSONObject.optString("yidongAppKey");
            this.liantongAppId = jSONObjectOptJSONObject.optString("liantongAppId");
            this.liantongAppKey = jSONObjectOptJSONObject.optString("liantongAppKey");
            this.dianxinAppId = jSONObjectOptJSONObject.optString("dianxinAppId");
            this.dianxinAppKey = jSONObjectOptJSONObject.optString("dianxinAppKey");
        }
    }

    private void parsePush() {
        JSONObject jSONObjectOptJSONObject = this.params.optJSONObject("push");
        if (jSONObjectOptJSONObject == null) {
            CpsLog.d("push参数为null，默认cps没有接push功能");
            this.hasPush = false;
            return;
        }
        CpsLog.d("push参数为： " + jSONObjectOptJSONObject);
        if (jSONObjectOptJSONObject.length() > 0) {
            CpsLog.d("push.length() > 0,有push参数");
            this.hasPush = true;
        } else {
            CpsLog.d("push.length()< 0,没有push参数");
            this.hasPush = false;
        }
    }

    private void parseAd() {
        JSONObject jSONObjectOptJSONObject = this.params.optJSONObject("ad");
        JSONArray jSONArrayOptJSONArray = jSONObjectOptJSONObject != null ? jSONObjectOptJSONObject.optJSONArray("adCodeIds") : null;
        if (jSONArrayOptJSONArray == null) {
            return;
        }
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            JSONObject jSONObjectOptJSONObject2 = jSONArrayOptJSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject2 != null) {
                this.adParams.put(jSONObjectOptJSONObject2.optString("originAdCodeId"), jSONObjectOptJSONObject2.optString("newAdCodeId"));
            }
        }
    }

    private void parseCpSelf() {
        this.cpSelfParams = new HashMap();
        JSONArray jSONArrayOptJSONArray = this.params.optJSONArray("cpSelfParams");
        if (jSONArrayOptJSONArray == null) {
            return;
        }
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
            this.cpSelfParams.put(jSONObjectOptJSONObject.optString("cpApkValue"), jSONObjectOptJSONObject.optString("cpNewValue"));
        }
    }
}
