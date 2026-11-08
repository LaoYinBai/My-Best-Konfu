package com.ss.android.downloadad.api.download;

import com.ss.android.download.api.download.DownloadController;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AdDownloadController implements DownloadController {
    protected int mDownloadMode;
    protected Object mExtraClickOperation;
    protected JSONObject mExtraJson;
    protected Object mExtraObject;
    protected int mInterceptFlag;
    protected boolean mIsAddToDownloadManage;
    protected boolean mIsAutoDownloadOnCardShow;
    protected boolean mIsEnableBackDialog;
    protected int mLinkMode;
    protected boolean mShouldUseNewWebView;
    protected boolean mEnableShowComplianceDialog = true;
    protected boolean mEnableNewActivity = true;
    protected boolean mEnableAH = true;
    protected boolean mEnableAM = true;

    interface JsonKey {
        public static final String ADD_TO_MANAGE = "add_to_manage";
        public static final String AUTO_DOWNLOAD_ON_CARD_SHOW = "is_auto_download_on_card_show";
        public static final String DOWNLOAD_MODE = "download_mode";
        public static final String ENABLE_AH = "enable_ah";
        public static final String ENABLE_AM = "enable_am";
        public static final String ENABLE_BACK_DIALOG = "enable_back_dialog";
        public static final String ENABLE_NEW_ACTIVITY = "enable_new_activity";
        public static final String ENABLE_SHOW_COMPLIANCE_DIALOG = "enable_show_compliance_dialog";
        public static final String EXTRA = "extra";
        public static final String INTERCEPT_FLAG = "intercept_flag";
        public static final String LINK_MODE = "link_mode";
        public static final String USE_NEW_WEBVIEW = "use_new_webview";
    }

    @Override // com.ss.android.download.api.download.DownloadController
    public int getDowloadChunkCount() {
        return 1;
    }

    @Override // com.ss.android.download.api.download.DownloadController
    public boolean isEnableMultipleDownload() {
        return false;
    }

    @Override // com.ss.android.download.api.download.DownloadController
    public int getLinkMode() {
        return this.mLinkMode;
    }

    @Override // com.ss.android.download.api.download.DownloadController
    public int getDownloadMode() {
        return this.mDownloadMode;
    }

    @Override // com.ss.android.download.api.download.DownloadController
    public boolean isEnableBackDialog() {
        return this.mIsEnableBackDialog;
    }

    @Override // com.ss.android.download.api.download.DownloadController
    public boolean isAddToDownloadManage() {
        return this.mIsAddToDownloadManage;
    }

    @Override // com.ss.android.download.api.download.DownloadController
    public Object getExtraClickOperation() {
        return this.mExtraClickOperation;
    }

    @Override // com.ss.android.download.api.download.DownloadController
    public boolean shouldUseNewWebView() {
        return this.mShouldUseNewWebView;
    }

    @Override // com.ss.android.download.api.download.DownloadController
    public int getInterceptFlag() {
        return this.mInterceptFlag;
    }

    @Override // com.ss.android.download.api.download.DownloadController
    public JSONObject getExtraJson() {
        return this.mExtraJson;
    }

    @Override // com.ss.android.download.api.download.DownloadController
    public Object getExtraObject() {
        return this.mExtraObject;
    }

    @Override // com.ss.android.download.api.download.DownloadController
    public void setDownloadMode(int i) {
        this.mDownloadMode = i;
    }

    @Override // com.ss.android.download.api.download.DownloadController
    public void setLinkMode(int i) {
        this.mLinkMode = i;
    }

    @Override // com.ss.android.download.api.download.DownloadController
    public boolean enableShowComplianceDialog() {
        return this.mEnableShowComplianceDialog;
    }

    @Override // com.ss.android.download.api.download.DownloadController
    public void setEnableShowComplianceDialog(boolean z) {
        this.mEnableShowComplianceDialog = z;
    }

    @Override // com.ss.android.download.api.download.DownloadController
    public boolean isAutoDownloadOnCardShow() {
        return this.mIsAutoDownloadOnCardShow;
    }

    @Override // com.ss.android.download.api.download.DownloadController
    public boolean enableNewActivity() {
        return this.mEnableNewActivity;
    }

    public void setIsAutoDownloadOnCardShow(boolean z) {
        this.mIsAutoDownloadOnCardShow = z;
    }

    @Override // com.ss.android.download.api.download.DownloadController
    public void setEnableNewActivity(boolean z) {
        this.mEnableNewActivity = z;
    }

    @Override // com.ss.android.download.api.download.DownloadController
    public boolean enableAH() {
        return this.mEnableAH;
    }

    @Override // com.ss.android.download.api.download.DownloadController
    public boolean enableAM() {
        return this.mEnableAM;
    }

    public void setExtraObject(Object obj) {
        this.mExtraObject = obj;
    }

    public void setExtraJson(JSONObject jSONObject) {
        this.mExtraJson = jSONObject;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("link_mode", Integer.valueOf(this.mLinkMode));
            jSONObject.putOpt(JsonKey.DOWNLOAD_MODE, Integer.valueOf(this.mDownloadMode));
            jSONObject.putOpt(JsonKey.ENABLE_BACK_DIALOG, Integer.valueOf(this.mIsEnableBackDialog ? 1 : 0));
            jSONObject.putOpt(JsonKey.ADD_TO_MANAGE, Integer.valueOf(this.mIsAddToDownloadManage ? 1 : 0));
            jSONObject.putOpt(JsonKey.USE_NEW_WEBVIEW, Integer.valueOf(this.mShouldUseNewWebView ? 1 : 0));
            jSONObject.putOpt(JsonKey.INTERCEPT_FLAG, Integer.valueOf(this.mInterceptFlag));
            jSONObject.putOpt(JsonKey.ENABLE_SHOW_COMPLIANCE_DIALOG, Integer.valueOf(this.mEnableShowComplianceDialog ? 1 : 0));
            jSONObject.putOpt(JsonKey.AUTO_DOWNLOAD_ON_CARD_SHOW, Integer.valueOf(this.mIsAutoDownloadOnCardShow ? 1 : 0));
            jSONObject.putOpt("extra", this.mExtraJson);
            jSONObject.putOpt("enable_new_activity", Integer.valueOf(this.mEnableNewActivity ? 1 : 0));
            jSONObject.putOpt("enable_ah", Integer.valueOf(this.mEnableAH ? 1 : 0));
            jSONObject.putOpt("enable_am", Integer.valueOf(this.mEnableAM ? 1 : 0));
        } catch (Exception e) {
            GlobalInfo.getTTMonitor().monitorException(e, "AdDownloadController toJson");
        }
        return jSONObject;
    }

    public static AdDownloadController fromJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        Builder builder = new Builder();
        try {
            builder.setLinkMode(jSONObject.optInt("link_mode"));
            builder.setDownloadMode(jSONObject.optInt(JsonKey.DOWNLOAD_MODE));
            builder.setIsEnableBackDialog(jSONObject.optInt(JsonKey.ENABLE_BACK_DIALOG) == 1);
            builder.setIsAddToDownloadManage(jSONObject.optInt(JsonKey.ADD_TO_MANAGE) == 1);
            builder.setShouldUseNewWebView(jSONObject.optInt(JsonKey.USE_NEW_WEBVIEW) == 1);
            builder.setInterceptFlag(jSONObject.optInt(JsonKey.INTERCEPT_FLAG));
            builder.setEnableShowComplianceDialog(jSONObject.optInt(JsonKey.ENABLE_SHOW_COMPLIANCE_DIALOG, 1) == 1);
            builder.setIsAutoDownloadOnCardShow(jSONObject.optInt(JsonKey.AUTO_DOWNLOAD_ON_CARD_SHOW) == 1);
            builder.setEnableNewActivity(jSONObject.optInt("enable_new_activity", 1) == 1);
            builder.setEnableAH(jSONObject.optInt("enable_ah", 1) == 1);
            builder.setEnableAM(jSONObject.optInt("enable_am", 1) == 1);
            builder.setExtraJson(jSONObject.optJSONObject("extra"));
        } catch (Exception e) {
            GlobalInfo.getTTMonitor().monitorException(e, "AdDownloadController fromJson");
        }
        return builder.build();
    }

    public static final class Builder {
        AdDownloadController controller = new AdDownloadController();

        @Deprecated
        public Builder setDowloadChunkCount(int i) {
            return this;
        }

        @Deprecated
        public Builder setIsEnableMultipleDownload(boolean z) {
            return this;
        }

        public Builder setLinkMode(int i) {
            this.controller.mLinkMode = i;
            return this;
        }

        public Builder setDownloadMode(int i) {
            this.controller.mDownloadMode = i;
            return this;
        }

        public Builder setIsEnableBackDialog(boolean z) {
            this.controller.mIsEnableBackDialog = z;
            return this;
        }

        public Builder setIsAddToDownloadManage(boolean z) {
            this.controller.mIsAddToDownloadManage = z;
            return this;
        }

        @Deprecated
        public Builder setExtraOperation(Object obj) {
            this.controller.mExtraClickOperation = obj;
            return this;
        }

        public Builder setShouldUseNewWebView(boolean z) {
            this.controller.mShouldUseNewWebView = z;
            return this;
        }

        public Builder setInterceptFlag(int i) {
            this.controller.mInterceptFlag = i;
            return this;
        }

        public Builder setExtraJson(JSONObject jSONObject) {
            this.controller.mExtraJson = jSONObject;
            return this;
        }

        public Builder setExtraObject(Object obj) {
            this.controller.mExtraObject = obj;
            return this;
        }

        public Builder setEnableShowComplianceDialog(boolean z) {
            this.controller.mEnableShowComplianceDialog = z;
            return this;
        }

        public Builder setIsAutoDownloadOnCardShow(boolean z) {
            this.controller.mIsAutoDownloadOnCardShow = z;
            return this;
        }

        public Builder setEnableNewActivity(boolean z) {
            this.controller.mEnableNewActivity = z;
            return this;
        }

        public Builder setEnableAH(boolean z) {
            this.controller.mEnableAH = z;
            return this;
        }

        public Builder setEnableAM(boolean z) {
            this.controller.mEnableAM = z;
            return this;
        }

        public AdDownloadController build() {
            return this.controller;
        }
    }
}
