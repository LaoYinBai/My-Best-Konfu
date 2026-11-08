package com.baidu.mobads.sdk.api;

import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class RequestParameters {
    public static final int ADS_TYPE_DOWNLOAD = 2;
    public static final int ADS_TYPE_OPENPAGE = 1;
    public static final int DOWNLOAD_APP_CONFIRM_ALWAYS = 2;
    public static final int DOWNLOAD_APP_CONFIRM_CUSTOM_BY_APP = 4;
    public static final int DOWNLOAD_APP_CONFIRM_NEVER = 3;
    public static final int DOWNLOAD_APP_CONFIRM_ONLY_MOBILE = 1;
    public static final int MAX_ASSETS_RESERVED = 15;
    public static final String TAG = "RequestParameters";
    private int adsType;
    private boolean confirmDownloading;
    private boolean customSize;
    private int downloadAppConfirmPolicy;
    private Map<String, String> extras;
    private int height;
    private final String mKeywords;
    protected String mPlacementId;
    private int width;

    public static class Builder {
        private String keywords;
        private Map<String, String> extras = new HashMap();
        private int adsType = 3;
        private boolean clickConfirm = false;
        private int width = 640;
        private int height = 480;
        private int downloadAppConfirmPolicy = 1;
        private boolean customSize = false;

        public final Builder setWidth(int i) {
            this.width = i;
            this.customSize = true;
            return this;
        }

        public final Builder setHeight(int i) {
            this.height = i;
            this.customSize = true;
            return this;
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
            this.downloadAppConfirmPolicy = i;
            return this;
        }

        public final Builder addExtra(String str, String str2) {
            this.extras.put(str, str2);
            return this;
        }

        public final RequestParameters build() {
            return new RequestParameters(this);
        }
    }

    private RequestParameters(Builder builder) {
        this.width = 0;
        this.height = 0;
        this.mKeywords = builder.keywords;
        this.adsType = builder.adsType;
        this.width = builder.width;
        this.height = builder.height;
        this.customSize = builder.customSize;
        this.confirmDownloading = builder.clickConfirm;
        this.downloadAppConfirmPolicy = builder.downloadAppConfirmPolicy;
        setExtras(builder.extras);
    }

    public final String getKeywords() {
        return this.mKeywords;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean isCustomSize() {
        return this.customSize;
    }

    public int getAdsType() {
        return this.adsType;
    }

    public void setAdsType(int i) {
        this.adsType = i;
    }

    public boolean isConfirmDownloading() {
        return this.confirmDownloading;
    }

    public Map<String, String> getExtras() {
        return this.extras;
    }

    public void setExtras(Map<String, String> map) {
        this.extras = map;
    }

    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("mKeywords", this.mKeywords);
        map.put("adsType", Integer.valueOf(this.adsType));
        map.put("confirmDownloading", Boolean.valueOf(this.confirmDownloading));
        HashMap map2 = new HashMap();
        if (this.extras != null) {
            for (Map.Entry<String, String> entry : this.extras.entrySet()) {
                map2.put(entry.getKey(), entry.getValue());
            }
        }
        map.put("extras", map2);
        return map;
    }

    public String getAdPlacementId() {
        return this.mPlacementId;
    }

    public int getAPPConfirmPolicy() {
        return this.downloadAppConfirmPolicy;
    }
}
