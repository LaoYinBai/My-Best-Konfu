package com.ss.android.downloadlib;

import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.ss.android.download.api.config.IHttpCallback;
import com.ss.android.downloadad.api.download.AdDownloadController;
import com.ss.android.downloadad.api.download.AdDownloadEventConfig;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.addownload.model.OrderDownloadItem;
import com.ss.android.downloadlib.addownload.model.OrderItem;
import com.ss.android.downloadlib.constants.DownloadConstants;
import com.ss.android.downloadlib.constants.DownloadSettingKeys;
import com.ss.android.downloadlib.constants.EventConstants;
import com.ss.android.downloadlib.event.AdEventHandler;
import com.ss.android.downloadlib.utils.ToolUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class OrderDownloader {
    private final Map<String, String> apiList;
    private final Map<String, OrderItem> orderItemMap;
    private final AtomicInteger requestCount;
    private final List<OrderDownloadItem> shelvedItems;

    @Retention(RetentionPolicy.SOURCE)
    public @interface BizType {
        public static final String AD = "ad";
        public static final String GAME = "game";
    }

    private static class SingleTonHolder {
        private static OrderDownloader INSTANCE = new OrderDownloader();

        private SingleTonHolder() {
        }
    }

    static class Request {
        String baseUrl;
        String bizType;
        String orderIds;
        Map<String, Object> params;

        public Request(String str, String str2, String str3, Map<String, Object> map) {
            this.orderIds = str;
            this.bizType = str2;
            this.baseUrl = str3;
            this.params = map;
        }
    }

    public static OrderDownloader getInstance() {
        return SingleTonHolder.INSTANCE;
    }

    private OrderDownloader() {
        this.requestCount = new AtomicInteger();
        this.shelvedItems = new ArrayList();
        this.orderItemMap = new ConcurrentHashMap();
        this.apiList = new HashMap();
        this.apiList.put("ad", GlobalInfo.getDownloadSettings().optString(DownloadSettingKeys.AD_ORDER_API, "https://apps.oceanengine.com/booking/detail"));
    }

    public boolean addOrder(String str, String str2) {
        if (GlobalInfo.getDownloadSettings().optInt(DownloadSettingKeys.DISABLE_ORDER) == 1 || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        OrderItem orderItem = new OrderItem();
        orderItem.orderId = str2;
        orderItem.bizType = str;
        orderItem.orderTime = System.currentTimeMillis();
        SharedPreferences sharedPreferences = GlobalInfo.getContext().getSharedPreferences(DownloadConstants.SP_ORDER_DOWNLOAD, 0);
        Map<String, ?> all = sharedPreferences.getAll();
        if (all != null && all.containsKey(orderItem.getKey())) {
            return false;
        }
        sharedPreferences.edit().putString(orderItem.getKey(), orderItem.toString()).apply();
        return true;
    }

    public boolean addOrder(OrderItem orderItem) throws JSONException {
        SharedPreferences sharedPreferences = GlobalInfo.getContext().getSharedPreferences(DownloadConstants.SP_ORDER_DOWNLOAD, 0);
        Map<String, ?> all = sharedPreferences.getAll();
        if (all != null && all.containsKey(orderItem.getKey())) {
            return false;
        }
        orderItem.orderTime = System.currentTimeMillis();
        sharedPreferences.edit().putString(orderItem.getKey(), orderItem.toString()).apply();
        sendOrderDownloadEvent(orderItem, EventConstants.Label.ADD_ORDER_DOWNLOAD, (JSONObject) null);
        return true;
    }

    public void start() {
        start(0L);
    }

    public void start(long j) {
        if (GlobalInfo.getDownloadSettings().optInt(DownloadSettingKeys.DISABLE_ORDER) == 1) {
            return;
        }
        DownloadComponentManager.getInstance().submitScheduledTask(new Runnable() { // from class: com.ss.android.downloadlib.OrderDownloader.1
            @Override // java.lang.Runnable
            public void run() throws JSONException {
                Map<String, ?> all = GlobalInfo.getContext().getSharedPreferences(DownloadConstants.SP_ORDER_DOWNLOAD, 0).getAll();
                if (all == null) {
                    return;
                }
                OrderDownloader.this.orderItemMap.clear();
                Iterator<Map.Entry<String, ?>> it = all.entrySet().iterator();
                while (it.hasNext()) {
                    OrderItem orderItemFromString = OrderItem.fromString(String.valueOf(it.next().getValue()));
                    OrderDownloader.this.orderItemMap.put(orderItemFromString.getKey(), orderItemFromString);
                }
                List listCreateAdRequest = OrderDownloader.this.createAdRequest(OrderDownloader.this.orderItemMap);
                List listCreateGameRequest = OrderDownloader.this.createGameRequest(OrderDownloader.this.orderItemMap);
                OrderDownloader.this.requestCount.set(listCreateAdRequest.size() + listCreateGameRequest.size());
                Iterator it2 = listCreateAdRequest.iterator();
                while (it2.hasNext()) {
                    OrderDownloader.this.requestAdApi((Request) it2.next());
                }
                Iterator it3 = listCreateGameRequest.iterator();
                while (it3.hasNext()) {
                    OrderDownloader.this.requestGameApi((Request) it3.next());
                }
            }
        }, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<Request> createAdRequest(Map<String, OrderItem> map) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (OrderItem orderItem : map.values()) {
            if ("ad".equals(orderItem.bizType) && orderItem.lastRequestTime + orderItem.nextRequestInterval <= jCurrentTimeMillis) {
                sb.insert(0, orderItem.orderId).insert(0, ',');
            }
        }
        ArrayList arrayList = new ArrayList();
        if (sb.length() <= 1) {
            return arrayList;
        }
        HashMap map2 = new HashMap();
        map2.put("did", GlobalInfo.getUserInfoListener().getDeviceId());
        String strSubstring = sb.toString().substring(1);
        map2.put("order_ids", strSubstring);
        arrayList.add(new Request(strSubstring, "ad", this.apiList.get("ad"), map2));
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<Request> createGameRequest(Map<String, OrderItem> map) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        for (OrderItem orderItem : map.values()) {
            if (BizType.GAME.equals(orderItem.bizType) && orderItem.lastRequestTime + orderItem.nextRequestInterval <= jCurrentTimeMillis) {
                HashMap map2 = new HashMap();
                map2.put("order_ids", orderItem.orderId);
                String string = "";
                if (orderItem.downloadModel != null && orderItem.downloadModel.getExtra() != null) {
                    string = orderItem.downloadModel.getExtra().toString();
                }
                map2.put("extra", string);
                arrayList.add(new Request(orderItem.orderId, BizType.GAME, orderItem.orderUrl, map2));
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestAdApi(final Request request) {
        GlobalInfo.getDownloadNetworkFactory().execute("POST", request.baseUrl, request.params, new IHttpCallback() { // from class: com.ss.android.downloadlib.OrderDownloader.2
            @Override // com.ss.android.download.api.config.IHttpCallback
            public void onResponse(String str) {
                try {
                    OrderDownloader.this.handleResponse(request.bizType, new JSONObject(str), null);
                } catch (JSONException e) {
                    GlobalInfo.getTTMonitor().monitorException(e, "OrderDownloader requestAdApi handleResponse");
                }
                OrderDownloader.this.checkRequestCount();
            }

            @Override // com.ss.android.download.api.config.IHttpCallback
            public void onError(Throwable th) {
                GlobalInfo.getTTMonitor().monitorException(th, "OrderDownloader requestAdApi onError");
                OrderDownloader.this.checkRequestCount();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestGameApi(final Request request) throws JSONException {
        final OrderItem orderItem = this.orderItemMap.get(request.bizType + request.orderIds);
        sendOrderDownloadEvent(orderItem, EventConstants.Label.ORDER_DOWNLOAD_QUERY, (JSONObject) null);
        GlobalInfo.getDownloadNetworkFactory().execute("GET", request.baseUrl, request.params, new IHttpCallback() { // from class: com.ss.android.downloadlib.OrderDownloader.3
            @Override // com.ss.android.download.api.config.IHttpCallback
            public void onResponse(String str) {
                try {
                    OrderDownloader.this.handleResponse(request.bizType, new JSONObject(str), orderItem);
                } catch (JSONException e) {
                    GlobalInfo.getTTMonitor().monitorException(e, "OrderDownloader requestGameApi handleResponse");
                }
                OrderDownloader.this.checkRequestCount();
            }

            @Override // com.ss.android.download.api.config.IHttpCallback
            public void onError(Throwable th) {
                if (th != null) {
                    OrderDownloader.this.sendOrderDownloadQueryResultEvent(orderItem, -1, th.getMessage());
                } else {
                    OrderDownloader.this.sendOrderDownloadQueryResultEvent(orderItem, -1, "");
                }
                OrderDownloader.this.checkRequestCount();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleResponse(String str, JSONObject jSONObject, OrderItem orderItem) {
        if (jSONObject.optInt("code") != 0 || jSONObject.isNull("data")) {
            sendOrderDownloadQueryResultEvent(orderItem, -2, "");
            return;
        }
        List<OrderDownloadItem> ordersFromJson = getOrdersFromJson(str, jSONObject.optJSONArray("data"));
        SharedPreferences.Editor editorEdit = GlobalInfo.getContext().getSharedPreferences(DownloadConstants.SP_ORDER_DOWNLOAD, 0).edit();
        for (OrderDownloadItem orderDownloadItem : ordersFromJson) {
            if (orderDownloadItem != null) {
                if (BizType.GAME.equals(orderDownloadItem.bizType)) {
                    sendOrderDownloadQueryResultEvent(orderDownloadItem, orderDownloadItem.orderStatus);
                }
                switch (orderDownloadItem.orderStatus) {
                    case 0:
                        OrderItem orderItem2 = this.orderItemMap.get(orderDownloadItem.getKey());
                        if (orderItem2 != null) {
                            orderItem2.nextRequestInterval = orderDownloadItem.nextRequestInterval;
                            orderItem2.lastRequestTime = System.currentTimeMillis();
                            editorEdit.putString(orderDownloadItem.getKey(), orderItem2.toString());
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        this.shelvedItems.add(orderDownloadItem);
                        break;
                    default:
                        editorEdit.remove(orderDownloadItem.getKey());
                        this.orderItemMap.remove(orderDownloadItem.getKey());
                        break;
                }
            }
        }
        editorEdit.apply();
    }

    @NonNull
    private List<OrderDownloadItem> getOrdersFromJson(String str, JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                arrayList.add(OrderDownloadItem.fromJson(str, jSONArray.getJSONObject(i)));
            } catch (Exception e) {
                GlobalInfo.getTTMonitor().monitorException(e, "OrderDownloader getOrdersFromJson");
            }
        }
        return arrayList;
    }

    public static OrderItem getOrderItem(String str, String str2) {
        Map<String, ?> all = GlobalInfo.getContext().getSharedPreferences(DownloadConstants.SP_ORDER_DOWNLOAD, 0).getAll();
        String str3 = str + str2;
        if (all == null || !all.containsKey(str3)) {
            return null;
        }
        return OrderItem.fromString(String.valueOf(all.get(str3)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkRequestCount() {
        if (this.requestCount.decrementAndGet() == 0) {
            try {
                doDownload();
            } catch (Throwable th) {
                GlobalInfo.getTTMonitor().monitorException(th, "OrderDownloader checkRequestCount");
            }
        }
    }

    private void doDownload() {
        int i;
        int i2;
        SharedPreferences.Editor editorEdit = GlobalInfo.getContext().getSharedPreferences(DownloadConstants.SP_ORDER_DOWNLOAD, 0).edit();
        int size = this.shelvedItems.size() - 1;
        while (size >= 0) {
            String packageName = this.shelvedItems.get(size).downloadModel.getPackageName();
            if (TextUtils.isEmpty(packageName)) {
                i = size;
            } else {
                i = size;
                for (int i3 = size - 1; i3 >= 0; i3--) {
                    String packageName2 = this.shelvedItems.get(i3).downloadModel.getPackageName();
                    if (!TextUtils.isEmpty(packageName2) && packageName2.equals(packageName) && getOrderTime(this.shelvedItems.get(i3)) != 0 && getOrderTime(this.shelvedItems.get(size)) != 0) {
                        if (getOrderTime(this.shelvedItems.get(i3)) < getOrderTime(this.shelvedItems.get(size))) {
                            i2 = i3;
                        } else {
                            i2 = size - 1;
                            size = i3;
                        }
                        OrderDownloadItem orderDownloadItem = this.shelvedItems.get(size);
                        sendOrderDownloadCheckEvent(orderDownloadItem, 5);
                        editorEdit.remove(orderDownloadItem.getKey());
                        this.orderItemMap.remove(orderDownloadItem.getKey());
                        this.shelvedItems.remove(size);
                        i--;
                        size = i2;
                    }
                }
            }
            final OrderDownloadItem orderDownloadItem2 = this.shelvedItems.get(size);
            editorEdit.remove(orderDownloadItem2.getKey());
            this.orderItemMap.remove(orderDownloadItem2.getKey());
            this.shelvedItems.remove(size);
            int iDoOrderDownloadCheck = doOrderDownloadCheck(orderDownloadItem2);
            if (iDoOrderDownloadCheck == 1) {
                DownloadDispatcher.getInstance().getMainHandler().post(new Runnable() { // from class: com.ss.android.downloadlib.OrderDownloader.4
                    @Override // java.lang.Runnable
                    public void run() {
                        TTDownloader.inst(GlobalInfo.getContext()).bind(1, null, orderDownloadItem2.downloadModel);
                        TTDownloader.inst(GlobalInfo.getContext()).action(orderDownloadItem2.downloadModel.getDownloadUrl(), orderDownloadItem2.downloadModel.getId(), 2, orderDownloadItem2.eventConfig, new AdDownloadController.Builder().build());
                    }
                });
            }
            sendOrderDownloadCheckEvent(orderDownloadItem2, iDoOrderDownloadCheck);
            size = i - 1;
        }
        editorEdit.apply();
    }

    private int doOrderDownloadCheck(OrderDownloadItem orderDownloadItem) {
        if (!orderDownloadItem.enableDownload) {
            return 4;
        }
        if (TTDownloader.inst(GlobalInfo.getContext()).isStarted(orderDownloadItem.downloadModel.getDownloadUrl())) {
            return 3;
        }
        return ToolUtils.isInstalledApp(orderDownloadItem.downloadModel) ? 2 : 1;
    }

    private void sendOrderDownloadEvent(OrderDownloadItem orderDownloadItem, String str, JSONObject jSONObject) throws JSONException {
        if (orderDownloadItem == null || orderDownloadItem.downloadModel == null) {
            return;
        }
        AdEventHandler.getInstance().sendEvent(EventConstants.Tag.ORDER_DOWNLOAD, str, jSONObject, orderDownloadItem.downloadModel, orderDownloadItem.eventConfig, new AdDownloadController.Builder().build());
    }

    private void sendOrderDownloadEvent(OrderItem orderItem, String str, JSONObject jSONObject) throws JSONException {
        if (orderItem == null || orderItem.downloadModel == null) {
            return;
        }
        AdEventHandler.getInstance().sendEvent(EventConstants.Tag.ORDER_DOWNLOAD, str, jSONObject, orderItem.downloadModel, new AdDownloadEventConfig.Builder().setClickButtonTag(EventConstants.Tag.ORDER_DOWNLOAD).setClickItemTag(EventConstants.Tag.ORDER_DOWNLOAD).setIsEnableV3Event(orderItem.eventV3).setIsEnableClickEvent(false).build(), new AdDownloadController.Builder().build());
    }

    private void sendOrderDownloadQueryResultEvent(OrderDownloadItem orderDownloadItem, int i) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.putOpt(EventConstants.ExtraJson.KEY_ORDER_STATUS, Integer.valueOf(i));
            sendOrderDownloadEvent(orderDownloadItem, EventConstants.Label.ORDER_DOWNLOAD_QUERY_RESULT, jSONObject);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendOrderDownloadQueryResultEvent(OrderItem orderItem, int i, String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.putOpt(EventConstants.ExtraJson.KEY_ORDER_STATUS, Integer.valueOf(i));
            jSONObject.putOpt(EventConstants.ExtraJson.FAIL_MSG, str);
            sendOrderDownloadEvent(orderItem, EventConstants.Label.ORDER_DOWNLOAD_QUERY_RESULT, jSONObject);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void sendOrderDownloadCheckEvent(OrderDownloadItem orderDownloadItem, int i) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.putOpt("download_status", Integer.valueOf(i));
            sendOrderDownloadEvent(orderDownloadItem, EventConstants.Label.ORDER_DOWNLOAD_CHECK, jSONObject);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private long getOrderTime(OrderDownloadItem orderDownloadItem) {
        OrderItem orderItem = getOrderItem(orderDownloadItem.bizType, orderDownloadItem.orderId);
        if (orderItem != null) {
            return orderItem.orderTime;
        }
        return 0L;
    }
}
