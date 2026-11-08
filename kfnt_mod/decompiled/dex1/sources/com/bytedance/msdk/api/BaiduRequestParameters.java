package com.bytedance.msdk.api;

import com.baidu.mobads.sdk.api.ArticleInfo;
import com.baidu.mobads.sdk.api.IAdInterListener;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class BaiduRequestParameters {
    public static final int ADS_TYPE_DOWNLOAD = 2;
    public static final int ADS_TYPE_OPENPAGE = 1;
    public static final int DOWNLOAD_APP_CONFIRM_ALWAYS = 2;
    public static final int DOWNLOAD_APP_CONFIRM_CUSTOM_BY_APP = 4;
    public static final int DOWNLOAD_APP_CONFIRM_NEVER = 3;
    public static final int DOWNLOAD_APP_CONFIRM_ONLY_MOBILE = 1;
    public static final int MAX_ASSETS_RESERVED = 15;

    /* renamed from: a, reason: collision with root package name */
    private final String f4273a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f4274b;

    /* renamed from: c, reason: collision with root package name */
    private Map<String, String> f4275c;
    private int d;
    private int e;
    private int f;

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private String f4276a;

        /* renamed from: b, reason: collision with root package name */
        private Map<String, String> f4277b = new HashMap();

        /* renamed from: c, reason: collision with root package name */
        private boolean f4278c = false;
        private int d = 640;
        private int e = 480;
        private int f = 1;

        public final Builder addExtra(String str, String str2) {
            if (ArticleInfo.PAGE_TITLE.equals(str)) {
                this.f4277b.put(IAdInterListener.AdReqParam.MPT, String.valueOf(1));
            }
            this.f4277b.put(str, str2);
            return this;
        }

        public final BaiduRequestParameters build() {
            return new BaiduRequestParameters(this);
        }

        @Deprecated
        public final Builder confirmDownloading(boolean z) {
            if (z) {
                downloadAppConfirmPolicy(2);
            } else {
                downloadAppConfirmPolicy(3);
            }
            return this;
        }

        public final Builder downloadAppConfirmPolicy(int i) {
            this.f = i;
            return this;
        }

        public final Builder setHeight(int i) {
            this.e = i;
            return this;
        }

        public final Builder setKeywords(String str) {
            this.f4276a = str;
            return this;
        }

        public final Builder setWidth(int i) {
            this.d = i;
            return this;
        }
    }

    public int getAPPConfirmPolicy() {
        return this.f;
    }

    public Map<String, String> getExtras() {
        return this.f4275c;
    }

    public int getHeight() {
        return this.e;
    }

    public final String getKeywords() {
        return this.f4273a;
    }

    public int getWidth() {
        return this.d;
    }

    public boolean isConfirmDownloading() {
        return this.f4274b;
    }

    public void setExtras(Map<String, String> map) {
        this.f4275c = map;
    }

    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("mKeywords", this.f4273a);
        map.put("confirmDownloading", Boolean.valueOf(this.f4274b));
        HashMap map2 = new HashMap();
        Map<String, String> map3 = this.f4275c;
        if (map3 != null) {
            for (Map.Entry<String, String> entry : map3.entrySet()) {
                map2.put(entry.getKey(), entry.getValue());
            }
        }
        map.put("extras", map2);
        return map;
    }

    private BaiduRequestParameters(Builder builder) {
        this.d = 0;
        this.e = 0;
        this.f4273a = builder.f4276a;
        this.d = builder.d;
        this.e = builder.e;
        this.f4274b = builder.f4278c;
        this.f = builder.f;
        setExtras(builder.f4277b);
    }
}
