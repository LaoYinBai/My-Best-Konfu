package com.umeng.message.common.inter;

import com.ss.union.game.sdk.redemptionCode.a;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMLog;
import java.util.Hashtable;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface ITagManager {
    public static final String FAIL = "fail";
    public static final String STATUS_FALSE = "false";
    public static final String STATUS_TRUE = "true";
    public static final String SUCCESS = "ok";

    Result addTags(JSONObject jSONObject, String... strArr) throws Exception;

    Result addWeightedTags(JSONObject jSONObject, Hashtable<String, Integer> hashtable) throws Exception;

    Result deleteTags(JSONObject jSONObject, String... strArr) throws Exception;

    Result deleteWeightedTags(JSONObject jSONObject, String... strArr) throws Exception;

    List<String> getTags(JSONObject jSONObject) throws Exception;

    Hashtable<String, Integer> getWeightedTags(JSONObject jSONObject) throws Exception;

    Result reset(JSONObject jSONObject) throws Exception;

    Result update(JSONObject jSONObject, String... strArr) throws Exception;

    public static class Result {
        private int a;
        public String errors;
        public long interval;
        public String jsonString;
        public long last_requestTime;
        public String msg;
        public int remain;
        public String status;

        public void setStatus(String str) {
            this.status = str;
        }

        public void setRemain(int i) {
            this.remain = i;
        }

        public void setInterval(long j) {
            this.interval = j;
        }

        public void setErrors(String str) {
            this.errors = str;
        }

        public void setLast_requestTime(long j) {
            this.last_requestTime = j;
        }

        public Result() {
            this.status = "fail";
            this.a = 0;
            this.msg = "";
            this.remain = 0;
            this.interval = 0L;
            this.errors = "";
            this.last_requestTime = 0L;
            this.jsonString = "";
        }

        public Result(JSONObject jSONObject, boolean z) throws JSONException {
            this.status = "fail";
            this.a = 0;
            this.msg = "";
            this.remain = 0;
            this.interval = 0L;
            this.errors = "";
            this.last_requestTime = 0L;
            this.jsonString = "";
            try {
                if (!z) {
                    this.status = jSONObject.optString("success", "fail");
                    this.remain = jSONObject.optInt("remain", 0);
                    this.interval = jSONObject.optLong("interval", 0L);
                    this.errors = jSONObject.optString("errors");
                    if (!jSONObject.has("last_requestTime")) {
                        jSONObject.put("last_requestTime", System.currentTimeMillis());
                    } else {
                        this.last_requestTime = jSONObject.optLong("last_requestTime", 0L);
                    }
                } else {
                    this.a = jSONObject.optInt("code");
                    this.msg = jSONObject.optString("msg", "");
                    this.status = jSONObject.optString("status", "false");
                    this.interval = jSONObject.optLong("interval", 0L);
                    this.last_requestTime = jSONObject.optLong(a.t, System.currentTimeMillis());
                    JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
                    if (jSONObjectOptJSONObject != null) {
                        this.remain = jSONObjectOptJSONObject.optInt("remain", 0);
                    }
                }
                this.jsonString = jSONObject.toString();
            } catch (Exception unused) {
                UMLog uMLog = UMConfigure.umDebugLog;
                UMLog.mutlInfo("com.umeng.message.common.inter.ITagManager.Result", 0, "Json error");
            }
        }

        public String toString() {
            return this.jsonString;
        }
    }
}
