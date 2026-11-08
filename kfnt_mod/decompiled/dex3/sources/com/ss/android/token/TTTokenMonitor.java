package com.ss.android.token;

import android.text.TextUtils;
import com.bytedance.sdk.account.monitor.AccountMonitorUtil;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TTTokenMonitor {
    public static final String TT_LOGID_KEY = "X-TT-LOGID";
    public static final String TT_SESSION_EXPIRE = "tt_token_logout";
    public static final String TT_TOKEN_BEAT = "tt_token_beat";
    public static final String TT_TOKEN_DECRYPT_ERR = "tt_token_decrypt_err";
    private static final String TT_TOKEN_LOST = "x_tt_token_lost";

    public static void monitorTokenLost(String str) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("url", str);
            AccountMonitorUtil.onTokenEvent(TT_TOKEN_LOST, jSONObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void monitorDecryptError(String str, String str2, String str3, String str4) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", str);
            if (!TextUtils.isEmpty(str2)) {
                jSONObject.put("tokenSign", str2);
            }
            if (!TextUtils.isEmpty(str3)) {
                jSONObject.put("token", str3);
            }
            if (!TextUtils.isEmpty(str4)) {
                jSONObject.put("errorMsg", str4);
            }
            AccountMonitorUtil.onTokenEvent(TT_TOKEN_DECRYPT_ERR, jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void monitorToken(String str, List<TTTokenHeader> list, int i, String str2) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("error_code", i);
            jSONObject.put("error_code_msg", str2);
            StringBuilder sb = new StringBuilder();
            if (list != null && !list.isEmpty()) {
                for (TTTokenHeader tTTokenHeader : list) {
                    if (tTTokenHeader instanceof TTTokenHeader) {
                        jSONObject.put(tTTokenHeader.getName(), tTTokenHeader.getValue());
                    } else if (tTTokenHeader != null) {
                        sb.append(tTTokenHeader.toString());
                        sb.append(";");
                    }
                }
            }
            jSONObject.put("extra", sb.toString());
            AccountMonitorUtil.onTokenEvent(str, jSONObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void monitorSessionExpire(String str, List list) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("url", str);
            String value = "";
            StringBuilder sb = new StringBuilder();
            if (list != null && !list.isEmpty()) {
                for (Object obj : list) {
                    if (obj instanceof TTTokenHeader) {
                        if (TT_LOGID_KEY.equalsIgnoreCase(((TTTokenHeader) obj).getName())) {
                            value = ((TTTokenHeader) obj).getValue();
                        }
                        jSONObject.put(((TTTokenHeader) obj).getName(), ((TTTokenHeader) obj).getValue());
                    } else if (obj != null) {
                        sb.append(obj.toString());
                        sb.append(";");
                    }
                }
            }
            String string = sb.toString();
            jSONObject.put("extra", string);
            AccountMonitorUtil.onSessionExpriedEvent(TT_SESSION_EXPIRE, str, value, string);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
