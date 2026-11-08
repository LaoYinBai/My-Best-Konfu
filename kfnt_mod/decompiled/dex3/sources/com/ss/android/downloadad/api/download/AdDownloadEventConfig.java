package com.ss.android.downloadad.api.download;

import com.ss.android.download.api.download.DownloadEventConfig;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AdDownloadEventConfig implements DownloadEventConfig {
    protected String mClickButtonTag;
    protected String mClickContinueLabel;
    protected String mClickInstallLabel;
    protected String mClickItemTag;
    protected String mClickLabel;
    protected String mClickPauseLabel;
    protected String mClickStartLabel;
    protected int mDownloadScene;
    protected transient Object mExtraEventObject;
    protected JSONObject mExtraJson;
    protected boolean mIsEnableClickEvent;
    protected boolean mIsEnableV3Event;
    protected JSONObject mParamsJson;
    protected String mRefer;
    protected String mStorageDenyLabel;

    interface JsonKey {
        public static final String CLICK_BTN_TAG = "click_button_tag";
        public static final String CLICK_CONTINUE_LABEL = "click_continue_label";
        public static final String CLICK_INSTALL_LABEL = "click_install_label";
        public static final String CLICK_ITEM_TAG = "click_item_tag";
        public static final String CLICK_LABEL = "click_label";
        public static final String CLICK_PAUSE_LABEL = "click_pause_label";
        public static final String CLICK_START_LABEL = "click_start_label";
        public static final String DOWNLOAD_SCENE = "download_scene";
        public static final String ENABLE_CLICK_EVENT = "enable_click_event";
        public static final String ENABLE_V3_EVENT = "enable_v3_event";
        public static final String EXTRA = "extra";
        public static final String PARAMS_JSON = "params_json";
        public static final String REFER = "refer";
        public static final String STORAGE_DENY_LABEL = "storage_deny_label";
    }

    @Deprecated
    public void setQuickAppEventTag(String str) {
    }

    public AdDownloadEventConfig() {
        this.mIsEnableClickEvent = true;
        this.mIsEnableV3Event = false;
    }

    public AdDownloadEventConfig(AdDownloadEventConfig adDownloadEventConfig) {
        this.mIsEnableClickEvent = true;
        this.mIsEnableV3Event = false;
        if (adDownloadEventConfig == null) {
            return;
        }
        this.mClickButtonTag = adDownloadEventConfig.mClickButtonTag;
        this.mClickItemTag = adDownloadEventConfig.mClickItemTag;
        this.mClickLabel = adDownloadEventConfig.mClickLabel;
        this.mClickStartLabel = adDownloadEventConfig.mClickStartLabel;
        this.mClickPauseLabel = adDownloadEventConfig.mClickPauseLabel;
        this.mClickContinueLabel = adDownloadEventConfig.mClickContinueLabel;
        this.mClickInstallLabel = adDownloadEventConfig.mClickInstallLabel;
        this.mStorageDenyLabel = adDownloadEventConfig.mStorageDenyLabel;
        this.mDownloadScene = adDownloadEventConfig.mDownloadScene;
        this.mIsEnableClickEvent = adDownloadEventConfig.mIsEnableClickEvent;
        this.mIsEnableV3Event = adDownloadEventConfig.mIsEnableV3Event;
        this.mRefer = adDownloadEventConfig.mRefer;
        this.mExtraJson = adDownloadEventConfig.mExtraJson;
        this.mParamsJson = adDownloadEventConfig.mParamsJson;
    }

    @Override // com.ss.android.download.api.download.DownloadEventConfig
    public String getRefer() {
        return this.mRefer;
    }

    @Override // com.ss.android.download.api.download.DownloadEventConfig
    public String getClickButtonTag() {
        return this.mClickButtonTag;
    }

    @Override // com.ss.android.download.api.download.DownloadEventConfig
    public String getClickItemTag() {
        return this.mClickItemTag;
    }

    @Override // com.ss.android.download.api.download.DownloadEventConfig
    public String getClickLabel() {
        return this.mClickLabel;
    }

    @Override // com.ss.android.download.api.download.DownloadEventConfig
    public String getClickStartLabel() {
        return this.mClickStartLabel;
    }

    @Override // com.ss.android.download.api.download.DownloadEventConfig
    public String getClickPauseLabel() {
        return this.mClickPauseLabel;
    }

    @Override // com.ss.android.download.api.download.DownloadEventConfig
    public String getClickContinueLabel() {
        return this.mClickContinueLabel;
    }

    @Override // com.ss.android.download.api.download.DownloadEventConfig
    public String getClickInstallLabel() {
        return this.mClickInstallLabel;
    }

    @Override // com.ss.android.download.api.download.DownloadEventConfig
    public String getStorageDenyLabel() {
        return this.mStorageDenyLabel;
    }

    @Override // com.ss.android.download.api.download.DownloadEventConfig
    public Object getExtraEventObject() {
        return this.mExtraEventObject;
    }

    @Override // com.ss.android.download.api.download.DownloadEventConfig
    public int getDownloadScene() {
        return this.mDownloadScene;
    }

    @Override // com.ss.android.download.api.download.DownloadEventConfig
    public boolean isEnableClickEvent() {
        return this.mIsEnableClickEvent;
    }

    @Override // com.ss.android.download.api.download.DownloadEventConfig
    public boolean isEnableV3Event() {
        return this.mIsEnableV3Event;
    }

    @Override // com.ss.android.download.api.download.DownloadEventConfig
    public JSONObject getExtraJson() {
        return this.mExtraJson;
    }

    @Override // com.ss.android.download.api.download.DownloadEventConfig
    public JSONObject getParamsJson() {
        return this.mParamsJson;
    }

    public void setExtraEventObject(Object obj) {
        this.mExtraEventObject = obj;
    }

    public void setClickButtonTag(String str) {
        this.mClickButtonTag = str;
    }

    public void setExtraJson(JSONObject jSONObject) {
        this.mExtraJson = jSONObject;
    }

    public void setParamsJson(JSONObject jSONObject) {
        this.mParamsJson = jSONObject;
    }

    public void setClickItemTag(String str) {
        this.mClickItemTag = str;
    }

    @Override // com.ss.android.download.api.download.DownloadEventConfig
    public void setDownloadScene(int i) {
        this.mDownloadScene = i;
    }

    @Override // com.ss.android.download.api.download.DownloadEventConfig
    public void setRefer(String str) {
        this.mRefer = str;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt(JsonKey.CLICK_BTN_TAG, this.mClickButtonTag);
            jSONObject.putOpt(JsonKey.CLICK_ITEM_TAG, this.mClickItemTag);
            jSONObject.putOpt(JsonKey.CLICK_LABEL, this.mClickLabel);
            jSONObject.putOpt(JsonKey.CLICK_START_LABEL, this.mClickStartLabel);
            jSONObject.putOpt(JsonKey.CLICK_CONTINUE_LABEL, this.mClickContinueLabel);
            jSONObject.putOpt(JsonKey.CLICK_PAUSE_LABEL, this.mClickPauseLabel);
            jSONObject.putOpt(JsonKey.CLICK_INSTALL_LABEL, this.mClickInstallLabel);
            jSONObject.putOpt(JsonKey.STORAGE_DENY_LABEL, this.mStorageDenyLabel);
            jSONObject.putOpt(JsonKey.REFER, this.mRefer);
            jSONObject.putOpt("download_scene", Integer.valueOf(this.mDownloadScene));
            jSONObject.putOpt(JsonKey.ENABLE_CLICK_EVENT, Integer.valueOf(this.mIsEnableClickEvent ? 1 : 0));
            jSONObject.putOpt(JsonKey.ENABLE_V3_EVENT, Integer.valueOf(this.mIsEnableV3Event ? 1 : 0));
            jSONObject.putOpt("extra", this.mExtraJson);
            jSONObject.putOpt(JsonKey.PARAMS_JSON, this.mParamsJson);
        } catch (Exception e) {
            GlobalInfo.getTTMonitor().monitorException(e, "AdDownloadEventConfig toJson");
        }
        return jSONObject;
    }

    public static AdDownloadEventConfig fromJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        Builder builder = new Builder();
        try {
            builder.setClickButtonTag(jSONObject.optString(JsonKey.CLICK_BTN_TAG));
            builder.setClickItemTag(jSONObject.optString(JsonKey.CLICK_ITEM_TAG));
            builder.setClickLabel(jSONObject.optString(JsonKey.CLICK_LABEL));
            builder.setClickStartLabel(jSONObject.optString(JsonKey.CLICK_START_LABEL));
            builder.setClickContinueLabel(jSONObject.optString(JsonKey.CLICK_CONTINUE_LABEL));
            builder.setClickPauseLabel(jSONObject.optString(JsonKey.CLICK_PAUSE_LABEL));
            builder.setClickInstallLabel(jSONObject.optString(JsonKey.CLICK_INSTALL_LABEL));
            builder.setStorageDenyLabel(jSONObject.optString(JsonKey.STORAGE_DENY_LABEL));
            builder.setRefer(jSONObject.optString(JsonKey.REFER));
            builder.setDownloadScene(jSONObject.optInt("download_scene"));
            builder.setIsEnableClickEvent(jSONObject.optInt(JsonKey.ENABLE_CLICK_EVENT) == 1);
            builder.setIsEnableV3Event(jSONObject.optInt(JsonKey.ENABLE_V3_EVENT) == 1);
            builder.setExtraJson(jSONObject.optJSONObject("extra"));
            builder.setParamsJson(jSONObject.optJSONObject(JsonKey.PARAMS_JSON));
        } catch (Exception e) {
            GlobalInfo.getTTMonitor().monitorException(e, "AdDownloadEventConfig fromJson");
        }
        return builder.build();
    }

    public static final class Builder {
        AdDownloadEventConfig eventConfig = new AdDownloadEventConfig();

        @Deprecated
        public Builder setClickContinueTag(String str) {
            return this;
        }

        @Deprecated
        public Builder setClickInstallTag(String str) {
            return this;
        }

        @Deprecated
        public Builder setClickOpenLabel(String str) {
            return this;
        }

        @Deprecated
        public Builder setClickOpenTag(String str) {
            return this;
        }

        @Deprecated
        public Builder setClickPauseTag(String str) {
            return this;
        }

        @Deprecated
        public Builder setClickStartTag(String str) {
            return this;
        }

        @Deprecated
        public Builder setClickTag(String str) {
            return this;
        }

        @Deprecated
        public Builder setCompletedEventTag(String str) {
            return this;
        }

        @Deprecated
        public Builder setDownloadFailedLabel(String str) {
            return this;
        }

        @Deprecated
        public Builder setIsEnableCompletedEvent(boolean z) {
            return this;
        }

        @Deprecated
        public Builder setIsEnableNoChargeClickEvent(boolean z) {
            return this;
        }

        @Deprecated
        public Builder setOpenLabel(String str) {
            return this;
        }

        @Deprecated
        public Builder setOpenTag(String str) {
            return this;
        }

        @Deprecated
        public Builder setQuickAppEventTag(String str) {
            return this;
        }

        @Deprecated
        public Builder setStorageDenyTag(String str) {
            return this;
        }

        public Builder setClickButtonTag(String str) {
            this.eventConfig.mClickButtonTag = str;
            return this;
        }

        public Builder setClickItemTag(String str) {
            this.eventConfig.mClickItemTag = str;
            return this;
        }

        public Builder setClickLabel(String str) {
            this.eventConfig.mClickLabel = str;
            return this;
        }

        public Builder setClickStartLabel(String str) {
            this.eventConfig.mClickStartLabel = str;
            return this;
        }

        public Builder setClickPauseLabel(String str) {
            this.eventConfig.mClickPauseLabel = str;
            return this;
        }

        public Builder setClickContinueLabel(String str) {
            this.eventConfig.mClickContinueLabel = str;
            return this;
        }

        public Builder setClickInstallLabel(String str) {
            this.eventConfig.mClickInstallLabel = str;
            return this;
        }

        public Builder setStorageDenyLabel(String str) {
            this.eventConfig.mStorageDenyLabel = str;
            return this;
        }

        public Builder setExtraEventObject(Object obj) {
            this.eventConfig.mExtraEventObject = obj;
            return this;
        }

        public Builder setDownloadScene(int i) {
            this.eventConfig.mDownloadScene = i;
            return this;
        }

        public Builder setIsEnableClickEvent(boolean z) {
            this.eventConfig.mIsEnableClickEvent = z;
            return this;
        }

        public Builder setIsEnableV3Event(boolean z) {
            this.eventConfig.mIsEnableV3Event = z;
            return this;
        }

        public Builder setRefer(String str) {
            this.eventConfig.mRefer = str;
            return this;
        }

        public Builder setExtraJson(JSONObject jSONObject) {
            this.eventConfig.mExtraJson = jSONObject;
            return this;
        }

        public Builder setParamsJson(JSONObject jSONObject) {
            this.eventConfig.mParamsJson = jSONObject;
            return this;
        }

        public AdDownloadEventConfig build() {
            return this.eventConfig;
        }
    }
}
