package com.ss.android.download.api.model;

import android.text.TextUtils;
import com.ss.android.download.api.constant.BaseConstants;
import com.ss.android.download.api.utils.ToolUtils;
import com.ss.android.downloadad.api.download.AdDownloadEventConfig;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DownloadEventModel {
    private final long mAdId;
    private String mCategory;
    private final List<String> mClickTrackUrl;
    private final int mEventSource;
    private final JSONObject mExtJson;
    private final long mExtValue;
    private final Object mExtraObject;
    private final boolean mIsAd;
    private final boolean mIsV3;
    private final String mLabel;
    private final String mLogExtra;
    private final JSONObject mParamsJson;
    private final String mRefer;
    private final String mTag;
    private final String mV3EventName;
    private final JSONObject mV3EventParams;

    DownloadEventModel(Builder builder) {
        this.mCategory = builder.mCategory;
        this.mTag = builder.mTag;
        this.mLabel = builder.mLabel;
        this.mIsAd = builder.mIsAd;
        this.mAdId = builder.mAdId;
        this.mLogExtra = builder.mLogExtra;
        this.mExtValue = builder.mExtValue;
        this.mExtJson = builder.mExtJson;
        this.mParamsJson = builder.mParamsJson;
        this.mClickTrackUrl = builder.mClickTrackUrl;
        this.mEventSource = builder.mEventSource;
        this.mExtraObject = builder.mExtraObject;
        this.mIsV3 = builder.mIsV3;
        this.mV3EventName = builder.mV3EventName;
        this.mV3EventParams = builder.mV3EventParams;
        this.mRefer = builder.mRefer;
    }

    public static class Builder {
        private long mAdId;
        private String mCategory;
        private List<String> mClickTrackUrl;
        private Map<String, Object> mEventMap;
        private int mEventSource;
        private JSONObject mExtJson;
        private long mExtValue;
        private Object mExtraObject;
        private boolean mIsAd = false;
        private boolean mIsV3 = false;
        private String mLabel;
        private String mLogExtra;
        private JSONObject mParamsJson;
        private String mRefer;
        private String mTag;
        private String mV3EventName;
        private JSONObject mV3EventParams;

        public Builder setIsV3(boolean z) {
            this.mIsV3 = z;
            return this;
        }

        public Builder setCategory(String str) {
            this.mCategory = str;
            return this;
        }

        public Builder setTag(String str) {
            this.mTag = str;
            return this;
        }

        public Builder setLabel(String str) {
            this.mLabel = str;
            return this;
        }

        public Builder setAdId(long j) {
            this.mAdId = j;
            return this;
        }

        public Builder setExtValue(long j) {
            this.mExtValue = j;
            return this;
        }

        public Builder setLogExtra(String str) {
            this.mLogExtra = str;
            return this;
        }

        public Builder setIsAd(boolean z) {
            this.mIsAd = z;
            return this;
        }

        public Builder setExtJson(JSONObject jSONObject) {
            this.mExtJson = jSONObject;
            return this;
        }

        public Builder setParamsJson(JSONObject jSONObject) {
            this.mParamsJson = jSONObject;
            return this;
        }

        public Builder setEventMap(Map<String, Object> map) {
            this.mEventMap = map;
            return this;
        }

        public Builder setClickTrackUrl(List<String> list) {
            this.mClickTrackUrl = list;
            return this;
        }

        public Builder setEventSource(int i) {
            this.mEventSource = i;
            return this;
        }

        public Builder setExtraObject(Object obj) {
            this.mExtraObject = obj;
            return this;
        }

        public Builder setRefer(String str) {
            this.mRefer = str;
            return this;
        }

        public DownloadEventModel build() throws JSONException {
            if (TextUtils.isEmpty(this.mCategory)) {
                this.mCategory = BaseConstants.CATEGORY_UMENG;
            }
            JSONObject jSONObject = new JSONObject();
            if (this.mExtJson == null) {
                this.mExtJson = new JSONObject();
            }
            try {
                if (this.mEventMap != null && !this.mEventMap.isEmpty()) {
                    for (Map.Entry<String, Object> entry : this.mEventMap.entrySet()) {
                        if (!this.mExtJson.has(entry.getKey())) {
                            this.mExtJson.putOpt(entry.getKey(), entry.getValue());
                        }
                    }
                }
                if (this.mIsV3) {
                    this.mV3EventName = this.mLabel;
                    this.mV3EventParams = new JSONObject();
                    if (this.mIsAd) {
                        this.mV3EventParams.put(BaseConstants.EVENT_LABEL_AD_EXTRA_DATA, this.mExtJson.toString());
                    } else {
                        Iterator<String> itKeys = this.mExtJson.keys();
                        while (itKeys.hasNext()) {
                            String next = itKeys.next();
                            this.mV3EventParams.put(next, this.mExtJson.get(next));
                        }
                    }
                    this.mV3EventParams.put("category", this.mCategory);
                    this.mV3EventParams.put("tag", this.mTag);
                    this.mV3EventParams.put("value", this.mAdId);
                    this.mV3EventParams.put("ext_value", this.mExtValue);
                    if (!TextUtils.isEmpty(this.mRefer)) {
                        this.mV3EventParams.put(AdDownloadEventConfig.JsonKey.REFER, this.mRefer);
                    }
                    if (this.mParamsJson != null) {
                        this.mV3EventParams = ToolUtils.mergeJson(this.mParamsJson, this.mV3EventParams);
                    }
                    if (this.mIsAd) {
                        if (!this.mV3EventParams.has("log_extra") && !TextUtils.isEmpty(this.mLogExtra)) {
                            this.mV3EventParams.put("log_extra", this.mLogExtra);
                        }
                        this.mV3EventParams.put(BaseConstants.EVENT_LABEL_IS_AD_EVENT, "1");
                    }
                }
                if (this.mIsAd) {
                    jSONObject.put(BaseConstants.EVENT_LABEL_AD_EXTRA_DATA, this.mExtJson.toString());
                    if (!jSONObject.has("log_extra") && !TextUtils.isEmpty(this.mLogExtra)) {
                        jSONObject.put("log_extra", this.mLogExtra);
                    }
                    jSONObject.put(BaseConstants.EVENT_LABEL_IS_AD_EVENT, "1");
                } else {
                    jSONObject.put("extra", this.mExtJson);
                }
                if (!TextUtils.isEmpty(this.mRefer)) {
                    jSONObject.putOpt(AdDownloadEventConfig.JsonKey.REFER, this.mRefer);
                }
                if (this.mParamsJson != null) {
                    jSONObject = ToolUtils.mergeJson(this.mParamsJson, jSONObject);
                }
                this.mExtJson = jSONObject;
            } catch (Exception e) {
                GlobalInfo.getTTMonitor().monitorException(e, "DownloadEventModel build");
            }
            return new DownloadEventModel(this);
        }
    }

    public String getCategory() {
        return this.mCategory;
    }

    public String getTag() {
        return this.mTag;
    }

    public String getLabel() {
        return this.mLabel;
    }

    public boolean isAd() {
        return this.mIsAd;
    }

    public long getAdId() {
        return this.mAdId;
    }

    public String getLogExtra() {
        return this.mLogExtra;
    }

    public long getExtValue() {
        return this.mExtValue;
    }

    public JSONObject getExtJson() {
        return this.mExtJson;
    }

    public JSONObject getParamsJson() {
        return this.mParamsJson;
    }

    public List<String> getClickTrackUrl() {
        return this.mClickTrackUrl;
    }

    public int getEventSource() {
        return this.mEventSource;
    }

    public Object getExtraObject() {
        return this.mExtraObject;
    }

    public boolean isV3() {
        return this.mIsV3;
    }

    public String getV3EventName() {
        return this.mV3EventName;
    }

    public JSONObject getV3EventParams() {
        return this.mV3EventParams;
    }

    public String getRefer() {
        return this.mRefer;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("category: ");
        sb.append(this.mCategory);
        sb.append("\ttag: ");
        sb.append(this.mTag);
        sb.append("\tlabel: ");
        sb.append(this.mLabel);
        sb.append("\nisAd: ");
        sb.append(this.mIsAd);
        sb.append("\tadId: ");
        sb.append(this.mAdId);
        sb.append("\tlogExtra: ");
        sb.append(this.mLogExtra);
        sb.append("\textValue: ");
        sb.append(this.mExtValue);
        sb.append("\nextJson: ");
        sb.append(this.mExtJson);
        sb.append("\nparamsJson: ");
        sb.append(this.mParamsJson);
        sb.append("\nclickTrackUrl: ");
        sb.append(this.mClickTrackUrl != null ? this.mClickTrackUrl.toString() : "");
        sb.append("\teventSource: ");
        sb.append(this.mEventSource);
        sb.append("\textraObject: ");
        sb.append(this.mExtraObject != null ? this.mExtraObject.toString() : "");
        sb.append("\nisV3: ");
        sb.append(this.mIsV3);
        sb.append("\tV3EventName: ");
        sb.append(this.mV3EventName);
        sb.append("\tV3EventParams: ");
        sb.append(this.mV3EventParams != null ? this.mV3EventParams.toString() : "");
        return sb.toString();
    }
}
