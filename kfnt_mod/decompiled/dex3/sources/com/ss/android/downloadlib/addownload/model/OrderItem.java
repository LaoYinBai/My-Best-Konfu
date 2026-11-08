package com.ss.android.downloadlib.addownload.model;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.downloadlib.utils.ToolUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class OrderItem {
    public String bizType;
    public AdDownloadModel downloadModel;
    public boolean eventV3;
    public long lastRequestTime;
    public long nextRequestInterval;
    public String orderId;
    public long orderTime;
    public String orderUrl;

    public String getKey() {
        return this.bizType + this.orderId;
    }

    @NonNull
    public static OrderItem fromString(String str) {
        OrderItem orderItem = new OrderItem();
        if (TextUtils.isEmpty(str)) {
            return orderItem;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            orderItem.nextRequestInterval = ToolUtils.optLong(jSONObject, "next_interval");
            orderItem.lastRequestTime = ToolUtils.optLong(jSONObject, "last_time");
            orderItem.orderId = jSONObject.optString("order_id");
            orderItem.bizType = jSONObject.optString("biz_type");
            orderItem.orderUrl = jSONObject.optString("order_url");
            orderItem.eventV3 = jSONObject.optInt("event_v3", 0) == 1;
            orderItem.downloadModel = AdDownloadModel.fromJson(jSONObject.optJSONObject("download_model"));
            orderItem.orderTime = ToolUtils.optLong(jSONObject, "order_time");
            return orderItem;
        } catch (JSONException unused) {
            return orderItem;
        }
    }

    public String toString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("next_interval", this.nextRequestInterval);
            jSONObject.put("last_time", this.lastRequestTime);
            jSONObject.put("order_id", this.orderId);
            jSONObject.put("biz_type", this.bizType);
            jSONObject.put("order_url", this.orderUrl);
            jSONObject.put("event_v3", this.eventV3 ? 1 : 0);
            jSONObject.put("download_model", this.downloadModel == null ? null : this.downloadModel.toJson());
            jSONObject.put("order_time", this.orderTime);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }
}
