package com.ss.android.downloadlib.addownload.model;

import android.text.TextUtils;
import com.ss.android.download.api.model.DeepLink;
import com.ss.android.download.api.utils.ToolUtils;
import com.ss.android.downloadad.api.download.AdDownloadEventConfig;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.downloadlib.OrderDownloader;
import com.ss.android.downloadlib.constants.EventConstants;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class OrderDownloadItem {
    public String bizType;
    public AdDownloadModel downloadModel;
    public boolean enableDownload = false;
    public AdDownloadEventConfig eventConfig;
    public long nextRequestInterval;
    public String orderId;
    public int orderStatus;

    @Retention(RetentionPolicy.SOURCE)
    public @interface OrderCheckStatus {
        public static final int DELETE_LATE_ORDER = 5;
        public static final int INSTALLED = 2;
        public static final int NORMAL = 1;
        public static final int NO_WIFI_PARAM = 4;
        public static final int REPEAT_DOWNLOAD = 3;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface OrderStatus {
        public static final int HANDLE_FAILED = -2;
        public static final int ORDERING = 0;
        public static final int ORDER_CANCEL = 2;
        public static final int ORDER_OVERDUE = 3;
        public static final int QUERY_FAILED = -1;
        public static final int SHELVED = 1;
    }

    private OrderDownloadItem() {
    }

    public String getKey() {
        return this.bizType + this.orderId;
    }

    public static OrderDownloadItem fromJson(String str, JSONObject jSONObject) {
        boolean z;
        if (jSONObject == null) {
            return null;
        }
        OrderDownloadItem orderDownloadItem = new OrderDownloadItem();
        try {
            orderDownloadItem.bizType = str;
            orderDownloadItem.orderId = jSONObject.optString("order_id");
            orderDownloadItem.orderStatus = jSONObject.optInt(EventConstants.ExtraJson.KEY_ORDER_STATUS);
            orderDownloadItem.nextRequestInterval = ToolUtils.optLong(jSONObject, "next_time");
            boolean z2 = true;
            if (orderDownloadItem.orderStatus == 1) {
                String strOptString = jSONObject.optString("app_name");
                StringBuilder sb = new StringBuilder();
                sb.append("您预约的游戏");
                if (!TextUtils.isEmpty(strOptString)) {
                    sb.append("《");
                    sb.append(strOptString);
                    sb.append("》");
                }
                sb.append("已上线，Wi-Fi下自动下载");
                orderDownloadItem.enableDownload = jSONObject.optInt(AdDownloadModel.JsonKey.NEED_WIFI, 0) > 0;
                OrderItem orderItem = OrderDownloader.getOrderItem(orderDownloadItem.bizType, orderDownloadItem.orderId);
                if (orderItem == null || orderItem.downloadModel == null) {
                    orderDownloadItem.downloadModel = new AdDownloadModel.Builder().build();
                } else {
                    orderDownloadItem.downloadModel = orderItem.downloadModel;
                    orderDownloadItem.downloadModel.setExtra(com.ss.android.downloadlib.utils.ToolUtils.copyJson(jSONObject.optJSONObject("extra"), orderItem.downloadModel.getExtra()));
                }
                z = orderItem != null ? orderItem.eventV3 : false;
                try {
                    orderDownloadItem.downloadModel.setId(ToolUtils.optLong(jSONObject, "cid"));
                    orderDownloadItem.downloadModel.setIsAd("ad".equals(str));
                    orderDownloadItem.downloadModel.setLogExtra(jSONObject.optString("log_extra"));
                    orderDownloadItem.downloadModel.setDownloadUrl(jSONObject.optString("download_url"));
                    JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(AdDownloadModel.JsonKey.BACKUP_URLS);
                    if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0) {
                        ArrayList arrayList = new ArrayList();
                        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                            arrayList.add(jSONArrayOptJSONArray.optString(i));
                        }
                        orderDownloadItem.downloadModel.setBackupUrls(arrayList);
                    }
                    orderDownloadItem.downloadModel.setPackageName(jSONObject.optString("package_name"));
                    orderDownloadItem.downloadModel.setAppName(strOptString);
                    orderDownloadItem.downloadModel.setAppIcon(jSONObject.optString(AdDownloadModel.JsonKey.APP_ICON));
                    orderDownloadItem.downloadModel.setExpectFileLength(ToolUtils.optLong(jSONObject, "file_length"));
                    orderDownloadItem.downloadModel.setMd5(jSONObject.optString("md5"));
                    AdDownloadModel adDownloadModel = orderDownloadItem.downloadModel;
                    if (jSONObject.optInt(AdDownloadModel.JsonKey.NEED_WIFI, 0) != 1) {
                        z2 = false;
                    }
                    adDownloadModel.setNeedWifi(z2);
                    orderDownloadItem.downloadModel.setDeepLink(new DeepLink(jSONObject.optString("open_url"), null, null));
                    orderDownloadItem.downloadModel.setStartToast(sb.toString());
                    orderDownloadItem.downloadModel.setSdkMonitorScene(jSONObject.optString(AdDownloadModel.JsonKey.SDK_MONITOR_SCENE));
                } catch (Exception e) {
                    e = e;
                    e.printStackTrace();
                    orderDownloadItem.eventConfig = new AdDownloadEventConfig.Builder().setClickButtonTag(EventConstants.Tag.ORDER_DOWNLOAD).setClickItemTag(EventConstants.Tag.ORDER_DOWNLOAD).setIsEnableV3Event(z).setIsEnableClickEvent(false).build();
                    return orderDownloadItem;
                }
            } else {
                z = false;
            }
        } catch (Exception e2) {
            e = e2;
            z = false;
        }
        orderDownloadItem.eventConfig = new AdDownloadEventConfig.Builder().setClickButtonTag(EventConstants.Tag.ORDER_DOWNLOAD).setClickItemTag(EventConstants.Tag.ORDER_DOWNLOAD).setIsEnableV3Event(z).setIsEnableClickEvent(false).build();
        return orderDownloadItem;
    }

    public OrderItem generateOrderItem() {
        OrderItem orderItem = new OrderItem();
        orderItem.bizType = this.bizType;
        orderItem.orderId = this.orderId;
        orderItem.nextRequestInterval = this.nextRequestInterval;
        orderItem.lastRequestTime = System.currentTimeMillis();
        return orderItem;
    }

    public String toString() {
        return "OrderDownloadItem{bizType='" + this.bizType + "', orderId='" + this.orderId + "', orderStatus=" + this.orderStatus + ", nextRequestInterval=" + this.nextRequestInterval + ", downloadModel=" + this.downloadModel + ", eventConfig=" + this.eventConfig + ", enableDownload=" + this.enableDownload + '}';
    }
}
