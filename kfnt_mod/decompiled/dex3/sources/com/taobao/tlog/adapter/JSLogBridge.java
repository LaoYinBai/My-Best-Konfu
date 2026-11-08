package com.taobao.tlog.adapter;

import android.taobao.windvane.jsbridge.WVApiPlugin;
import android.taobao.windvane.jsbridge.WVCallBackContext;
import android.taobao.windvane.jsbridge.WVPluginManager;
import android.taobao.windvane.jsbridge.WVResult;
import android.text.TextUtils;
import com.umeng.commonsdk.framework.c;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class JSLogBridge extends WVApiPlugin {
    private static final String GETLOGLEVEL = "getLogLevel";
    private static final String LOGD = "logd";
    private static final String LOGE = "loge";
    private static final String LOGI = "logi";
    private static final String LOGV = "logv";
    private static final String LOGW = "logw";
    private static final String tlogBridgeName = "tlogBridge";

    public static void init() {
        WVPluginManager.registerPlugin(tlogBridgeName, JSLogBridge.class, true);
    }

    public boolean execute(String str, String str2, WVCallBackContext wVCallBackContext) {
        if (TextUtils.isEmpty(str) || wVCallBackContext == null) {
            return false;
        }
        if (GETLOGLEVEL.equals(str)) {
            WVResult wVResult = new WVResult();
            wVResult.addData("logLevel", AdapterForTLog.getLogLevel());
            wVCallBackContext.success(wVResult);
            return true;
        }
        if (LOGV.equals(str)) {
            logv(str2, wVCallBackContext);
            return true;
        }
        if (LOGD.equals(str)) {
            logd(str2, wVCallBackContext);
            return true;
        }
        if (LOGI.equals(str)) {
            logi(str2, wVCallBackContext);
            return true;
        }
        if (LOGW.equals(str)) {
            logw(str2, wVCallBackContext);
            return true;
        }
        if (!LOGE.equals(str)) {
            return false;
        }
        loge(str2, wVCallBackContext);
        return true;
    }

    public void logv(String str, WVCallBackContext wVCallBackContext) {
        LogBody log = getLog(str);
        if (log != null) {
            AdapterForTLog.logv(log.tag, str);
            wVCallBackContext.success();
        } else {
            wVCallBackContext.error("the tag is null!");
        }
    }

    public void logd(String str, WVCallBackContext wVCallBackContext) {
        LogBody log = getLog(str);
        if (log != null) {
            AdapterForTLog.logd(log.tag, str);
            wVCallBackContext.success();
        } else {
            wVCallBackContext.error("the tag is null!");
        }
    }

    public void logi(String str, WVCallBackContext wVCallBackContext) {
        LogBody log = getLog(str);
        if (log != null) {
            AdapterForTLog.logi(log.tag, str);
            wVCallBackContext.success();
        } else {
            wVCallBackContext.error("the tag is null!");
        }
    }

    public void logw(String str, WVCallBackContext wVCallBackContext) {
        LogBody log = getLog(str);
        if (log != null) {
            AdapterForTLog.logw(log.tag, str);
            wVCallBackContext.success();
        } else {
            wVCallBackContext.error("the tag is null!");
        }
    }

    public void loge(String str, WVCallBackContext wVCallBackContext) {
        LogBody log = getLog(str);
        if (log != null) {
            AdapterForTLog.loge(log.tag, str);
            wVCallBackContext.success();
        } else {
            wVCallBackContext.error("the tag is null!");
        }
    }

    private LogBody getLog(String str) {
        try {
            LogBody logBody = new LogBody();
            JSONObject jSONObject = new JSONObject(str);
            logBody.tag = jSONObject.optString("tag", "jsTag");
            logBody.data = jSONObject.optString(c.a, "");
            return logBody;
        } catch (JSONException unused) {
            return null;
        }
    }

    class LogBody {
        String data;
        String module;
        String tag;

        LogBody() {
        }
    }
}
