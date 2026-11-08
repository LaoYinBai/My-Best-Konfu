package com.ss.union.game.sdk.pay.e;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.sigmob.sdk.common.Constants;
import com.ss.union.game.sdk.core.base.account.model.User;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import kotlin.UByte;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b {
    public static String a(@NonNull String str, @NonNull String str2, @NonNull String str3, String str4, @NonNull String str5, @NonNull String str6, String str7, long j, int i, String str8, String str9) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put(User.KEY_OPEN_ID, str);
            } else {
                a.a("open_id 为空");
            }
            if (!TextUtils.isEmpty(str2)) {
                jSONObject.put("order_no", str2);
            }
            if (!TextUtils.isEmpty(str3)) {
                jSONObject.put("subject", str3);
            }
            if (!TextUtils.isEmpty(str4)) {
                try {
                    jSONObject.put("total_amount", Long.parseLong(str4));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (!TextUtils.isEmpty(str5)) {
                jSONObject.put("notify_url", str5);
            }
            if (!TextUtils.isEmpty(str6)) {
                jSONObject.put("product_id", str6);
            }
            if (!TextUtils.isEmpty(str7)) {
                jSONObject.put("body", str7);
            } else {
                jSONObject.put("body", "");
            }
            if (j <= 0) {
                j = System.currentTimeMillis();
            }
            jSONObject.put("trade_time", j);
            if (i <= 0) {
                i = 600;
            }
            jSONObject.put("valid_time", i);
            if (!TextUtils.isEmpty(str8)) {
                jSONObject.put("custom_callback_info", str8);
            } else {
                jSONObject.put("custom_callback_info", "");
            }
            if (!TextUtils.isEmpty(str9)) {
                jSONObject.put("sign_type", str9);
            } else {
                jSONObject.put("sign_type", "MD5");
            }
            String payKey = ConfigManager.PayConfig.getPayKey();
            if (!TextUtils.isEmpty(payKey)) {
                jSONObject.put("sign", a(jSONObject, payKey));
            }
            return jSONObject.toString();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static String b(@NonNull String str, @NonNull String str2, @NonNull String str3, String str4, @NonNull String str5, @NonNull String str6, String str7, long j, int i, String str8, String str9) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put("cp_user_id", str);
            } else {
                a.a("cp_user_id 为空");
            }
            if (!TextUtils.isEmpty(str2)) {
                jSONObject.put("order_no", str2);
            } else {
                a.a("order_no 为空");
            }
            if (!TextUtils.isEmpty(str3)) {
                jSONObject.put("subject", str3);
            } else {
                a.a("subject 为空");
            }
            if (!TextUtils.isEmpty(str4)) {
                try {
                    jSONObject.put("total_amount", Long.parseLong(str4));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                a.a("total_amount 为空");
            }
            if (!TextUtils.isEmpty(str5)) {
                jSONObject.put("notify_url", str5);
            } else {
                a.a("notify_url 为空");
            }
            if (!TextUtils.isEmpty(str6)) {
                jSONObject.put("product_id", str6);
            } else {
                a.a("product_id 为空");
            }
            if (!TextUtils.isEmpty(str7)) {
                jSONObject.put("body", str7);
            } else {
                jSONObject.put("body", "");
            }
            if (j <= 0) {
                j = System.currentTimeMillis();
            }
            jSONObject.put("trade_time", j);
            if (i <= 0) {
                i = 600;
            }
            jSONObject.put("valid_time", i);
            if (!TextUtils.isEmpty(str8)) {
                jSONObject.put("custom_callback_info", str8);
            } else {
                jSONObject.put("custom_callback_info", "");
            }
            if (!TextUtils.isEmpty(str9)) {
                jSONObject.put("sign_type", str9);
            } else {
                jSONObject.put("sign_type", "MD5");
            }
            String payKey = ConfigManager.PayConfig.getPayKey();
            if (!TextUtils.isEmpty(payKey)) {
                jSONObject.put("sign", a(jSONObject, payKey));
            }
            return jSONObject.toString();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private static String a(JSONObject jSONObject, String str) {
        TreeMap treeMap = new TreeMap();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            treeMap.put(next, jSONObject.optString(next));
        }
        return a(treeMap, str);
    }

    private static String a(Map<String, String> map, String str) {
        Set<Map.Entry<String, String>> setEntrySet = map.entrySet();
        StringBuilder sb = new StringBuilder(str);
        Iterator<Map.Entry<String, String>> it = setEntrySet.iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> next = it.next();
            if (it.hasNext()) {
                sb.append(next.getKey());
                sb.append("=");
                sb.append(next.getValue());
                sb.append("&");
            } else {
                sb.append(next.getKey());
                sb.append("=");
                sb.append(next.getValue());
            }
        }
        sb.append(str);
        a.a("buildPaySign: sign--" + sb.toString());
        return a(sb.toString());
    }

    private static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            byte[] bArrDigest = MessageDigest.getInstance("MD5").digest(str.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bArrDigest) {
                String hexString = Integer.toHexString(b & UByte.MAX_VALUE);
                if (hexString.length() == 1) {
                    hexString = Constants.FAIL + hexString;
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
