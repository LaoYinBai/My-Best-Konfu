package com.baidu.mobads.sdk.api;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.baidu.mobads.sdk.api.IBasicCPUData;
import com.baidu.mobads.sdk.internal.af;
import com.baidu.mobads.sdk.internal.ah;
import com.baidu.mobads.sdk.internal.ai;
import com.baidu.mobads.sdk.internal.aj;
import com.baidu.mobads.sdk.internal.ba;
import com.baidu.mobads.sdk.internal.r;
import com.baidu.mobads.sdk.internal.y;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class NativeCPUAdData implements IBasicCPUData, Observer {
    private static final String CLASS_NAME = r.l;
    private final ClassLoader classLoader;
    private IBasicCPUData.CpuNativeStatusCB mCpuNativeStatusCBListener;
    private final Context mCtx;
    public Object mInstance;
    private final HashMap<String, Object> mSettings;
    public ai remoteUtils;

    public NativeCPUAdData(Context context, Object obj, HashMap<String, Object> map) {
        this.mCtx = context;
        this.mInstance = obj;
        this.mSettings = map;
        this.remoteUtils = ai.a(context, CLASS_NAME);
        this.classLoader = ba.a(context);
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public String getType() {
        return (String) this.remoteUtils.b(this.mInstance, "getType", new Object[0]);
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public String getAuthor() {
        return (String) this.remoteUtils.b(this.mInstance, "getAuthor", new Object[0]);
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public String getIconUrl() {
        return (String) this.remoteUtils.b(this.mInstance, "getAvatar", new Object[0]);
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public String getUpdateTime() {
        return (String) this.remoteUtils.b(this.mInstance, "getUpdateTime", new Object[0]);
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public String getTitle() {
        return (String) this.remoteUtils.b(this.mInstance, "getTitle", new Object[0]);
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public String getDesc() {
        return (String) this.remoteUtils.b(this.mInstance, "getContent", new Object[0]);
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public boolean isTop() {
        Object objB = this.remoteUtils.b(this.mInstance, "isTop", new Object[0]);
        if (objB instanceof Boolean) {
            return ((Boolean) objB).booleanValue();
        }
        return false;
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public String getChannelId() {
        return (String) this.remoteUtils.b(this.mInstance, "getCatId", new Object[0]);
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public String getChannelName() {
        return (String) this.remoteUtils.b(this.mInstance, "getCatName", new Object[0]);
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public boolean isAutoplay() {
        return ((Boolean) this.remoteUtils.b(this.mInstance, "isAutoplay", new Object[0])).booleanValue();
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public boolean isCanGoLp() {
        return ((Boolean) this.remoteUtils.b(this.mInstance, "isCanGoLp", new Object[0])).booleanValue();
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public int getCommentCounts() {
        return ((Integer) this.remoteUtils.b(this.mInstance, "getCommentCounts", new Object[0])).intValue();
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public int getStyleTypeCpu() {
        return ((Integer) this.remoteUtils.b(this.mInstance, "getStyleTypeCpu", new Object[0])).intValue();
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public List<String> getSmallImageUrls() {
        Object objB = this.remoteUtils.b(this.mInstance, "getSmallImageList", new Object[0]);
        if (objB instanceof List) {
            return (List) objB;
        }
        return null;
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public List<String> getImageUrls() {
        Object objB = this.remoteUtils.b(this.mInstance, "getImageList", new Object[0]);
        if (objB instanceof List) {
            return (List) objB;
        }
        return null;
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public JSONObject getExtra() {
        return (JSONObject) this.remoteUtils.b(this.mInstance, "getExtra", new Object[0]);
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public int getAdWidth() {
        return ((Integer) this.remoteUtils.b(this.mInstance, "getAdWidth", new Object[0])).intValue();
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public int getAdHeight() {
        return ((Integer) this.remoteUtils.b(this.mInstance, "getAdHeight", new Object[0])).intValue();
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public String getAdLogoUrl() {
        return (String) this.remoteUtils.b(this.mInstance, "getAdLogoUrl", new Object[0]);
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public String getBaiduLogoUrl() {
        return (String) this.remoteUtils.b(this.mInstance, "getBaiduLogoUrl", new Object[0]);
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public String getThumbUrl() {
        return (String) this.remoteUtils.b(this.mInstance, "getThumbUrl", new Object[0]);
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public int getThumbWidth() {
        return ((Integer) this.remoteUtils.b(this.mInstance, "getThumbWidth", new Object[0])).intValue();
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public int getThumbHeight() {
        return ((Integer) this.remoteUtils.b(this.mInstance, "getThumbHeight", new Object[0])).intValue();
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public int getPlayCounts() {
        Object objB = this.remoteUtils.b(this.mInstance, "getPlayCounts", new Object[0]);
        if (objB instanceof Integer) {
            return ((Integer) objB).intValue();
        }
        return 0;
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public String getVUrl() {
        return (String) this.remoteUtils.b(this.mInstance, "getVUrl", new Object[0]);
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public int getDuration() {
        return ((Integer) this.remoteUtils.b(this.mInstance, "getDuration", new Object[0])).intValue();
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public int getPresentationType() {
        return ((Integer) this.remoteUtils.b(this.mInstance, "getPresentationType", new Object[0])).intValue();
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public JSONArray getDislikeReasons() {
        return (JSONArray) this.remoteUtils.b(this.mInstance, "getDislikeReasons", new Object[0]);
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public int getActionType() {
        return ((Integer) this.remoteUtils.b(this.mInstance, "getActionType", new Object[0])).intValue();
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public String getAppPackageName() {
        return (String) this.remoteUtils.b(this.mInstance, "getPackageName", new Object[0]);
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public String getBrandName() {
        Object objB = this.remoteUtils.b(this.mInstance, "getBrandName", new Object[0]);
        if (objB instanceof String) {
            return (String) objB;
        }
        return null;
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public String getContentClickUrl() {
        Object objB = this.remoteUtils.b(this.mInstance, "getContentClickUrl", new Object[0]);
        if (objB instanceof String) {
            return (String) objB;
        }
        return null;
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public int getDownloadStatus() {
        return ((Integer) this.remoteUtils.b(this.mInstance, "getDownloadStatus", new Object[0])).intValue();
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public void pauseAppDownload() {
        if (this.mCtx == null || !isNeedDownloadApp()) {
            return;
        }
        ah.a(this.mCtx.getApplicationContext()).a(getAppPackageName());
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public boolean isNeedDownloadApp() {
        return ((Boolean) this.remoteUtils.b(this.mInstance, "isDownloadApp", new Object[0])).booleanValue();
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public void onImpression(View view) {
        this.remoteUtils.a(this.mInstance, "onImpression", view);
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public void handleClick(View view, Object... objArr) {
        if (this.mSettings != null) {
            this.remoteUtils.a(this.mInstance, "setConfigParams", this.mSettings);
        }
        long jLongValue = 0;
        if (objArr != null && objArr.length > 0 && (objArr[0] instanceof Long)) {
            jLongValue = ((Long) objArr[0]).longValue();
        }
        if (view instanceof CpuVideoView) {
            this.remoteUtils.a(this.mInstance, "handClickVideo", 1, Long.valueOf(jLongValue));
        } else if (view instanceof TextView) {
            this.remoteUtils.a(this.mInstance, "handClickVideo", 2, Long.valueOf(jLongValue));
        }
        this.remoteUtils.a(this.mInstance, "handleClick", view);
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public void handleDislikeClick(View view, int i) {
        this.remoteUtils.a(this.mInstance, "handleDislikeClick", view, Integer.valueOf(i));
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public void markDislike(String[] strArr) {
        this.remoteUtils.a(this.mInstance, "markDislike", strArr);
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public void registerViewForInteraction(View view, List<View> list, List<View> list2, IBasicCPUData.CpuNativeStatusCB cpuNativeStatusCB) throws IllegalArgumentException {
        setStatusListener(cpuNativeStatusCB);
        onImpression(view);
        if (list != null && list.size() > 0) {
            for (View view2 : list) {
                if (view2 != null) {
                    view2.setOnClickListener(new View.OnClickListener() { // from class: com.baidu.mobads.sdk.api.NativeCPUAdData.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view3) {
                            NativeCPUAdData.this.handleCanClickView(view3);
                        }
                    });
                }
            }
        }
        if (list2 == null || list2.size() <= 0) {
            return;
        }
        for (View view3 : list2) {
            if (view3 != null) {
                view3.setOnClickListener(new View.OnClickListener() { // from class: com.baidu.mobads.sdk.api.NativeCPUAdData.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view4) {
                        NativeCPUAdData.this.handleCreativeView(view4);
                    }
                });
            }
        }
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public void handleCreativeView(View view) {
        if (this.mSettings != null) {
            this.remoteUtils.a(this.mInstance, "setConfigParams", this.mSettings);
        }
        if (view instanceof CpuVideoView) {
            this.remoteUtils.a(this.mInstance, "handClickVideo", 1, 0);
        } else if (view instanceof TextView) {
            this.remoteUtils.a(this.mInstance, "handClickVideo", 2, 0);
        }
        this.remoteUtils.a(this.mInstance, "handleCreativeView", view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCanClickView(View view) {
        if (this.mSettings != null) {
            this.remoteUtils.a(this.mInstance, "setConfigParams", this.mSettings);
        }
        if (view instanceof CpuVideoView) {
            this.remoteUtils.a(this.mInstance, "handClickVideo", 1, 0);
        } else if (view instanceof TextView) {
            this.remoteUtils.a(this.mInstance, "handClickVideo", 2, 0);
        }
        this.remoteUtils.a(this.mInstance, "handleCanClickView", view);
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public String getAppPublisher() {
        return this.remoteUtils.c(this.mInstance, "getAppPublisher", new Object[0]);
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public String getAppVersion() {
        return this.remoteUtils.c(this.mInstance, "getAppVersion", new Object[0]);
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public String getAppPrivacyUrl() {
        return this.remoteUtils.c(this.mInstance, "getAppPrivacyUrl", new Object[0]);
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public String getAppPermissionUrl() {
        return this.remoteUtils.c(this.mInstance, "getAppPermissionUrl", new Object[0]);
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public int getAttribute() {
        return ((Integer) this.remoteUtils.b(this.mInstance, "getAttribute", new Object[0])).intValue();
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public long getCtime() {
        return ((Long) this.remoteUtils.b(this.mInstance, "getCtime", new Object[0])).longValue();
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public long getHotId() {
        return ((Long) this.remoteUtils.b(this.mInstance, "getHotId", new Object[0])).longValue();
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public String getHotWord() {
        return this.remoteUtils.c(this.mInstance, "getHotWord", new Object[0]);
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public String getImage() {
        return this.remoteUtils.c(this.mInstance, "getImage", new Object[0]);
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public double getScore() {
        return ((Double) this.remoteUtils.b(this.mInstance, "getScore", new Object[0])).doubleValue();
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public String getLabel() {
        return this.remoteUtils.c(this.mInstance, "getLabel", new Object[0]);
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public void clickHotItem(View view) {
        this.remoteUtils.a(this.mInstance, "clickHotItem", view);
    }

    @Override // com.baidu.mobads.sdk.api.IBasicCPUData
    public void setStatusListener(final IBasicCPUData.CpuNativeStatusCB cpuNativeStatusCB) throws IllegalArgumentException {
        this.mCpuNativeStatusCBListener = cpuNativeStatusCB;
        try {
            this.remoteUtils.a(this.mInstance, "setStatusListener", Proxy.newProxyInstance(this.classLoader, new Class[]{aj.a(r.m, this.classLoader)}, new InvocationHandler() { // from class: com.baidu.mobads.sdk.api.NativeCPUAdData.3
                @Override // java.lang.reflect.InvocationHandler
                public Object invoke(Object obj, Method method, Object[] objArr) {
                    Log.d("NativeCPUAdData", "invoke: " + method.getName());
                    if (cpuNativeStatusCB == null) {
                        return null;
                    }
                    String name = method.getName();
                    if ("onAdDownloadWindowShow".equals(name)) {
                        cpuNativeStatusCB.onAdDownloadWindowShow();
                    } else if ("onPermissionShow".equals(name)) {
                        cpuNativeStatusCB.onPermissionShow();
                    } else if ("onPermissionClose".equals(name)) {
                        cpuNativeStatusCB.onPermissionClose();
                    } else if ("onPrivacyClick".equals(name)) {
                        cpuNativeStatusCB.onPrivacyClick();
                    } else if ("onPrivacyLpClose".equals(name)) {
                        cpuNativeStatusCB.onPrivacyLpClose();
                    } else if ("onNotifyPerformance".equals(name)) {
                        if (objArr != null && objArr.length >= 1 && (objArr[0] instanceof String)) {
                            cpuNativeStatusCB.onNotifyPerformance((String) objArr[0]);
                        }
                    } else if ("startRouter".equals(name)) {
                        af.a((Context) objArr[0], (String) objArr[1]);
                    }
                    return null;
                }
            }));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // java.util.Observer
    public void update(Observable observable, Object obj) {
        if ((observable instanceof y) && this.mCpuNativeStatusCBListener != null && isNeedDownloadApp() && (obj instanceof IOAdEvent)) {
            IOAdEvent iOAdEvent = (IOAdEvent) obj;
            String message = iOAdEvent.getMessage();
            Map<String, Object> data = iOAdEvent.getData();
            if (data != null) {
                Object obj2 = data.get("adid");
                if ((obj2 instanceof String) && ((String) obj2).equals(getAdid())) {
                    this.mCpuNativeStatusCBListener.onAdStatusChanged(message, getDownloadStatus());
                }
            }
        }
    }

    private String getAdid() {
        return (String) this.remoteUtils.b(this.mInstance, "getAdid", new Object[0]);
    }
}
