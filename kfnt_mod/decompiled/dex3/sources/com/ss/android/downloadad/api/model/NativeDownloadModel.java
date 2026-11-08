package com.ss.android.downloadad.api.model;

import com.ss.android.download.api.download.DownloadController;
import com.ss.android.download.api.download.DownloadEventConfig;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.download.api.model.DeepLink;
import com.ss.android.download.api.utils.ToolUtils;
import com.ss.android.downloadad.api.constant.AdBaseConstants;
import com.ss.android.downloadad.api.download.AdDownloadController;
import com.ss.android.downloadad.api.download.AdDownloadEventConfig;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NativeDownloadModel implements InnerUnifyData {
    private long availableSpaceBytes;

    @AdBaseConstants.FunnelType
    private int funnelType;
    public final AtomicBoolean hasSendDownloadFailedFinally;
    public final AtomicBoolean hasSendInstallFinish;
    private boolean isV3Event;
    private String mAppIcon;
    private String mAppName;
    private long mCancelInstallTime;
    private long mClickDownloadSize;
    private long mClickDownloadTime;
    private int mClickPauseTimes;
    private boolean mDeeplinkAfterBackApp;
    private int mDownloadFailedTimes;
    private String mDownloadFinishReason;
    private int mDownloadId;
    private int mDownloadMode;
    private int mDownloadStatus;
    private String mDownloadUrl;
    protected boolean mEnableAH;
    protected boolean mEnableAM;
    private boolean mEnableBackDialog;
    private boolean mEnableNewActivity;
    private boolean mEnablePause;
    private boolean mEnableShowComplianceDialog;
    private String mEventRefer;
    private String mEventTag;
    private long mExtValue;
    private JSONObject mExtras;
    private boolean mHandleNoEnoughSpaceDownload;
    private boolean mHandleNoEnoughSpaceInstall;
    private long mId;
    private boolean mInstallAfterBackApp;
    private boolean mInstallAfterCleanSpace;
    private boolean mIsAd;
    private boolean mIsAutoDownloadOnCardShow;
    private boolean mIsCleanSpaceBeforeDownload;
    private transient boolean mIsListeningInstallFinish;
    private boolean mIsPatchApplyHandled;
    private boolean mIsShowApkSizeRetain;
    private boolean mIsShowAppDialog;
    private boolean mIsShowDownloadPercentRetain;
    private boolean mIsUpdateDownload;
    private long mJumpInstallTime;
    private int mLastFailedErrCode;
    private String mLastFailedErrMsg;
    private int mLastFailedResumeCount;
    private int mLinkMode;
    private String mLogExtra;
    private int mModelType;
    private String mOpenUrl;
    private String mOriginMimeType;
    private String mPackageName;
    private long mRecentDownloadResumeTime;
    private long mTimeStamp;
    private int mVersionCode;
    private String mVersionName;
    private String mWebUrl;
    private int scene;

    interface JsonKey {
        public static final String APP_ICON = "mAppIcon";
        public static final String APP_NAME = "mAppName";
        public static final String AUTO_DOWNLOAD_ON_CARD_SHOW = "isAutoDownloadOnCardShow";
        public static final String CANCEL_INSTALL_TIME = "mCancelInstallTime";
        public static final String CLICK_DOWNLOAD_SIZE = "clickDownloadSize";
        public static final String CLICK_DOWNLOAD_TIME = "clickDownloadTime";
        public static final String CLICK_PAUSE_TIMES = "mClickPauseTimes";
        public static final String DOWNLOAD_FAILED_TIMES = "mDownloadFailedTimes";
        public static final String DOWNLOAD_FINISH_REASON = "downloadFinishReason";
        public static final String DOWNLOAD_ID = "mDownloadId";
        public static final String DOWNLOAD_MODE = "mDownloadMode";
        public static final String DOWNLOAD_STATUS = "mDownloadStatus";
        public static final String DOWNLOAD_URL = "mDownloadUrl";
        public static final String ENABLE_AH = "enable_ah";
        public static final String ENABLE_AM = "enable_am";
        public static final String ENABLE_BACK_DIALOG = "mEnableBackDialog";
        public static final String ENABLE_COMPLIANCE_DIALOG = "enableShowComplianceDialog";
        public static final String ENABLE_NEW_ACTIVITY = "enable_new_activity";
        public static final String ENABLE_PAUSE = "enable_pause";
        public static final String EVENT_REFER = "mEventRefer";
        public static final String EVENT_TAG = "mEventTag";
        public static final String EXTRA = "mExtras";
        public static final String EXT_VALUE = "mExtValue";
        public static final String FUNNEL_TYPE = "funnelType";
        public static final String HAS_SEND_DOWNLOAD_FAILED_FINALLY = "hasSendDownloadFailedFinally";
        public static final String HAS_SEND_INSTALL_FINISH = "hasSendInstallFinish";
        public static final String ID = "mId";
        public static final String INSTALL_AFTER_CLEAN = "installAfterCleanSpace";
        public static final String IS_AD = "mIsAd";
        public static final String IS_UPDATE_DOWNLOAD = "mIsUpdateDownload";
        public static final String IS_V3_EVENT = "mIsV3Event";
        public static final String JUMP_INSTALL_TIME = "mJumpInstallTime";
        public static final String LAST_FAILED_CODE = "mLastFailedErrCode";
        public static final String LAST_FAILED_MSG = "mLastFailedErrMsg";
        public static final String LAST_FAILED_RESUME_COUNT = "mLastFailedResumeCount";
        public static final String LINK_MODE = "mLinkMode";
        public static final String LOG_EXTRA = "mLogExtra";
        public static final String MIME_TYPE = "mOriginMimeType";
        public static final String MODEL_TYPE = "mModelType";
        public static final String OPEN_URL = "mOpenUrl";
        public static final String PACKAGE_NAME = "mPackageName";
        public static final String PATCH_APPLY_HANDLED = "mIsPatchApplyHandled";
        public static final String RECENT_DOWNLOAD_RESUME_TIME = "mRecentDownloadResumeTime";
        public static final String SCENE = "mScene";
        public static final String TIME_STAMP = "mTimeStamp";
        public static final String VERSION_CODE = "mVersionCode";
        public static final String VERSION_NAME = "mVersionName";
        public static final String WEB_URL = "webUrl";
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public List<String> getClickTrackUrl() {
        return null;
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public int getDownloadScene() {
        return -1;
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public JSONObject getDownloadSettings() {
        return null;
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public JSONObject getEventExtra() {
        return null;
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public Object getExtraEventObject() {
        return null;
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public JSONObject getParamsJson() {
        return null;
    }

    private NativeDownloadModel() {
        this.mDownloadStatus = 1;
        this.mIsAd = true;
        this.mEnableBackDialog = false;
        this.mDownloadFailedTimes = 0;
        this.mClickPauseTimes = 0;
        this.mIsUpdateDownload = false;
        this.mIsPatchApplyHandled = false;
        this.mEnableNewActivity = true;
        this.mEnablePause = true;
        this.mEnableAH = true;
        this.mEnableAM = true;
        this.hasSendInstallFinish = new AtomicBoolean(false);
        this.hasSendDownloadFailedFinally = new AtomicBoolean(false);
        this.funnelType = 1;
        this.mEnableShowComplianceDialog = true;
        this.availableSpaceBytes = -1L;
    }

    public NativeDownloadModel(DownloadModel downloadModel, DownloadEventConfig downloadEventConfig, DownloadController downloadController) {
        this(downloadModel, downloadEventConfig, downloadController, 0);
    }

    public NativeDownloadModel(DownloadModel downloadModel, DownloadEventConfig downloadEventConfig, DownloadController downloadController, int i) {
        this.mDownloadStatus = 1;
        this.mIsAd = true;
        this.mEnableBackDialog = false;
        this.mDownloadFailedTimes = 0;
        this.mClickPauseTimes = 0;
        this.mIsUpdateDownload = false;
        this.mIsPatchApplyHandled = false;
        this.mEnableNewActivity = true;
        this.mEnablePause = true;
        this.mEnableAH = true;
        this.mEnableAM = true;
        this.hasSendInstallFinish = new AtomicBoolean(false);
        this.hasSendDownloadFailedFinally = new AtomicBoolean(false);
        this.funnelType = 1;
        this.mEnableShowComplianceDialog = true;
        this.availableSpaceBytes = -1L;
        this.mId = downloadModel.getId();
        this.mExtValue = downloadModel.getExtraValue();
        this.mLogExtra = downloadModel.getLogExtra();
        this.mPackageName = downloadModel.getPackageName();
        this.mExtras = downloadModel.getExtra();
        this.mIsAd = downloadModel.isAd();
        this.mVersionCode = downloadModel.getVersionCode();
        this.mVersionName = downloadModel.getVersionName();
        this.mDownloadUrl = downloadModel.getDownloadUrl();
        if (downloadModel.getDeepLink() != null) {
            this.mOpenUrl = downloadModel.getDeepLink().getOpenUrl();
            this.mWebUrl = downloadModel.getDeepLink().getWebUrl();
        }
        this.mModelType = downloadModel.getModelType();
        this.mAppName = downloadModel.getName();
        this.mAppIcon = downloadModel.getAppIcon();
        this.mOriginMimeType = downloadModel.getMimeType();
        this.mEventTag = downloadEventConfig.getClickButtonTag();
        this.mEventRefer = downloadEventConfig.getRefer();
        this.isV3Event = downloadEventConfig.isEnableV3Event();
        this.mEnableBackDialog = downloadController.isEnableBackDialog();
        this.mLinkMode = downloadController.getLinkMode();
        this.mDownloadMode = downloadController.getDownloadMode();
        this.mEnableShowComplianceDialog = downloadController.enableShowComplianceDialog();
        this.mIsAutoDownloadOnCardShow = downloadController.isAutoDownloadOnCardShow();
        this.mEnableNewActivity = downloadController.enableNewActivity();
        this.mEnableAH = downloadController.enableAH();
        this.mEnableAM = downloadController.enableAM();
        this.mDownloadId = i;
        this.mTimeStamp = System.currentTimeMillis();
        this.mRecentDownloadResumeTime = this.mTimeStamp;
        this.mIsPatchApplyHandled = downloadModel.shouldDownloadWithPatchApply();
    }

    public int getDownloadFailedTimes() {
        return this.mDownloadFailedTimes;
    }

    public void setDownloadFailedTimes(int i) {
        this.mDownloadFailedTimes = i;
    }

    public synchronized void increaseDownloadFailedTimes() {
        this.mDownloadFailedTimes++;
    }

    public int getClickPauseTimes() {
        return this.mClickPauseTimes;
    }

    public void setClickPauseTimes(int i) {
        this.mClickPauseTimes = i;
    }

    public synchronized void increaseClickPauseTimes() {
        this.mClickPauseTimes++;
    }

    public long getRecentDownloadResumeTime() {
        if (this.mRecentDownloadResumeTime == 0) {
            return this.mTimeStamp;
        }
        return this.mRecentDownloadResumeTime;
    }

    public void setRecentDownloadResumeTime(long j) {
        this.mRecentDownloadResumeTime = j;
    }

    public long getJumpInstallTime() {
        return this.mJumpInstallTime;
    }

    public void setJumpInstallTime(long j) {
        this.mJumpInstallTime = j;
    }

    public long getCancelInstallTime() {
        return this.mCancelInstallTime;
    }

    public void setCancelInstallTime(long j) {
        this.mCancelInstallTime = j;
    }

    public int getLastFailedResumeCount() {
        return this.mLastFailedResumeCount;
    }

    public void setLastFailedResumeCount(int i) {
        this.mLastFailedResumeCount = i;
    }

    public int getLastFailedErrCode() {
        return this.mLastFailedErrCode;
    }

    public void setLastFailedErrCode(int i) {
        this.mLastFailedErrCode = i;
    }

    public String getLastFailedErrMsg() {
        return this.mLastFailedErrMsg;
    }

    public void setLastFailedErrMsg(String str) {
        this.mLastFailedErrMsg = str;
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public long getId() {
        return this.mId;
    }

    public void setId(long j) {
        this.mId = j;
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public long getExtValue() {
        return this.mExtValue;
    }

    public void setExtValue(long j) {
        this.mExtValue = j;
    }

    public int getDownloadStatus() {
        return this.mDownloadStatus;
    }

    public void setDownloadStatus(int i) {
        this.mDownloadStatus = i;
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public String getPackageName() {
        return this.mPackageName;
    }

    public void setPackageName(String str) {
        this.mPackageName = str;
    }

    public long getTimeStamp() {
        return this.mTimeStamp;
    }

    public void setTimeStamp(long j) {
        if (j > 0) {
            this.mTimeStamp = j;
        }
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public String getLogExtra() {
        return this.mLogExtra;
    }

    public void setLogExtra(String str) {
        this.mLogExtra = str;
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public boolean isAd() {
        return this.mIsAd;
    }

    public void setIsAd(boolean z) {
        this.mIsAd = z;
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public JSONObject getExtra() {
        return this.mExtras;
    }

    public void setExtras(JSONObject jSONObject) {
        this.mExtras = jSONObject;
    }

    public void setVersionCode(int i) {
        this.mVersionCode = i;
    }

    public void setVersionName(String str) {
        this.mVersionName = str;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public String getVersionName() {
        return this.mVersionName;
    }

    public void setDownloadId(int i) {
        this.mDownloadId = i;
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public int getDownloadId() {
        return this.mDownloadId;
    }

    public void setIsV3Event(boolean z) {
        this.isV3Event = z;
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public boolean isV3Event() {
        return this.isV3Event;
    }

    public void setInstallScene(int i) {
        this.scene = i;
    }

    public int getInstallScene() {
        return this.scene;
    }

    public void setFunnelType(int i) {
        this.funnelType = i;
    }

    public void setWebUrl(String str) {
        this.mWebUrl = str;
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public int getFunnelType() {
        return this.funnelType;
    }

    public void setEnableBackDialog(boolean z) {
        this.mEnableBackDialog = z;
    }

    public boolean enableBackDialog() {
        return this.mEnableBackDialog;
    }

    public void setEventTag(String str) {
        this.mEventTag = str;
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public String getEventTag() {
        return this.mEventTag;
    }

    public void setEventRefer(String str) {
        this.mEventRefer = str;
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public String getEventRefer() {
        return this.mEventRefer;
    }

    public void setDownloadUrl(String str) {
        this.mDownloadUrl = str;
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public String getDownloadUrl() {
        return this.mDownloadUrl;
    }

    public void setOpenUrl(String str) {
        this.mOpenUrl = str;
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public String getOpenUrl() {
        return this.mOpenUrl;
    }

    public void setAvailableSpaceBytes(long j) {
        this.availableSpaceBytes = j;
    }

    public long getAvailableSpaceBytes() {
        return this.availableSpaceBytes;
    }

    public void setAppName(String str) {
        this.mAppName = str;
    }

    public String getAppName() {
        return this.mAppName;
    }

    public void setAppIcon(String str) {
        this.mAppIcon = str;
    }

    public String getAppIcon() {
        return this.mAppIcon;
    }

    public void setLinkMode(int i) {
        this.mLinkMode = i;
    }

    public int getLinkMode() {
        return this.mLinkMode;
    }

    public void setDownloadMode(int i) {
        this.mDownloadMode = i;
    }

    public int getDownloadMode() {
        return this.mDownloadMode;
    }

    public void setModelMode(int i) {
        this.mModelType = i;
    }

    public int geModelType() {
        return this.mModelType;
    }

    public boolean isIsCleanSpaceBeforeDownload() {
        return this.mIsCleanSpaceBeforeDownload;
    }

    public void setIsCleanSpaceBeforeDownload(boolean z) {
        this.mIsCleanSpaceBeforeDownload = z;
    }

    public String getDownloadFinishReason() {
        return this.mDownloadFinishReason;
    }

    public void setDownloadFinishReason(String str) {
        this.mDownloadFinishReason = str;
    }

    public boolean isHandleNoEnoughSpaceDownload() {
        return this.mHandleNoEnoughSpaceDownload;
    }

    public void setHandleNoEnoughSpaceDownload(boolean z) {
        this.mHandleNoEnoughSpaceDownload = z;
    }

    public boolean isInstallAfterCleanSpace() {
        return this.mInstallAfterCleanSpace;
    }

    public void setInstallAfterCleanSpace(boolean z) {
        this.mInstallAfterCleanSpace = z;
    }

    public boolean isHandleNoEnoughSpaceInstall() {
        return this.mHandleNoEnoughSpaceInstall;
    }

    public void setHandleNoEnoughSpaceInstall(boolean z) {
        this.mHandleNoEnoughSpaceInstall = z;
    }

    public long getClickDownloadTime() {
        return this.mClickDownloadTime;
    }

    public void setClickDownloadTime(long j) {
        this.mClickDownloadTime = j;
    }

    public long getClickDownloadSize() {
        return this.mClickDownloadSize;
    }

    public void setClickDownloadSize(long j) {
        this.mClickDownloadSize = j;
    }

    public boolean isUpdateDownload() {
        return this.mIsUpdateDownload;
    }

    public void setIsUpdateDownload(boolean z) {
        this.mIsUpdateDownload = z;
    }

    public String getMimeType() {
        return this.mOriginMimeType;
    }

    public void setOriginMimeType(String str) {
        this.mOriginMimeType = str;
    }

    public boolean isPatchApplyHandled() {
        return this.mIsPatchApplyHandled;
    }

    public void setPatchApplyHandled(boolean z) {
        this.mIsPatchApplyHandled = z;
    }

    public boolean isListeningInstallFinish() {
        return this.mIsListeningInstallFinish;
    }

    public void setIsListeningInstallFinish(boolean z) {
        this.mIsListeningInstallFinish = z;
    }

    public boolean isShowAppDialog() {
        return this.mIsShowAppDialog;
    }

    public void setIsShowAppDialog(boolean z) {
        this.mIsShowAppDialog = z;
    }

    public boolean isDeeplinkAfterBackApp() {
        return this.mDeeplinkAfterBackApp;
    }

    public void setDeeplinkAfterBackApp(boolean z) {
        this.mDeeplinkAfterBackApp = z;
    }

    public boolean isInstallAfterBackApp() {
        return this.mInstallAfterBackApp;
    }

    public void setInstallAfterBackApp(boolean z) {
        this.mInstallAfterBackApp = z;
    }

    public boolean isIsShowApkSizeRetain() {
        return this.mIsShowApkSizeRetain;
    }

    public void setIsShowApkSizeRetain(boolean z) {
        this.mIsShowApkSizeRetain = z;
    }

    public boolean isIsShowDownloadPercentRetain() {
        return this.mIsShowDownloadPercentRetain;
    }

    public void setIsShowDownloadPercentRetain(boolean z) {
        this.mIsShowDownloadPercentRetain = z;
    }

    public boolean enableShowComplianceDialog() {
        return this.mEnableShowComplianceDialog;
    }

    public void setEnableShowComplianceDialog(boolean z) {
        this.mEnableShowComplianceDialog = z;
    }

    public boolean isAutoDownloadOnCardShow() {
        return this.mIsAutoDownloadOnCardShow;
    }

    public void setIsAutoDownloadOnCardShow(boolean z) {
        this.mIsAutoDownloadOnCardShow = z;
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public boolean enableNewActivity() {
        return this.mEnableNewActivity;
    }

    public void setEnableNewActivity(boolean z) {
        this.mEnableNewActivity = z;
    }

    public void setEnablePause(boolean z) {
        this.mEnablePause = z;
    }

    public void setEnableAH(boolean z) {
        this.mEnableAH = z;
    }

    public void setEnableAM(boolean z) {
        this.mEnableAM = z;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(JsonKey.ID, this.mId);
            jSONObject.put(JsonKey.EXT_VALUE, this.mExtValue);
            jSONObject.put(JsonKey.LOG_EXTRA, this.mLogExtra);
            jSONObject.put(JsonKey.DOWNLOAD_STATUS, this.mDownloadStatus);
            jSONObject.put(JsonKey.PACKAGE_NAME, this.mPackageName);
            jSONObject.put(JsonKey.IS_AD, this.mIsAd);
            jSONObject.put(JsonKey.TIME_STAMP, this.mTimeStamp);
            jSONObject.put(JsonKey.EXTRA, this.mExtras);
            jSONObject.put(JsonKey.VERSION_CODE, this.mVersionCode);
            jSONObject.put(JsonKey.VERSION_NAME, this.mVersionName);
            jSONObject.put(JsonKey.DOWNLOAD_ID, this.mDownloadId);
            jSONObject.put(JsonKey.IS_V3_EVENT, this.isV3Event);
            jSONObject.put(JsonKey.SCENE, this.scene);
            jSONObject.put(JsonKey.EVENT_TAG, this.mEventTag);
            jSONObject.put(JsonKey.EVENT_REFER, this.mEventRefer);
            jSONObject.put(JsonKey.DOWNLOAD_URL, this.mDownloadUrl);
            jSONObject.put(JsonKey.ENABLE_BACK_DIALOG, this.mEnableBackDialog);
            jSONObject.put(JsonKey.HAS_SEND_INSTALL_FINISH, this.hasSendInstallFinish.get());
            jSONObject.put(JsonKey.HAS_SEND_DOWNLOAD_FAILED_FINALLY, this.hasSendDownloadFailedFinally.get());
            jSONObject.put(JsonKey.LAST_FAILED_CODE, this.mLastFailedErrCode);
            jSONObject.put(JsonKey.LAST_FAILED_MSG, this.mLastFailedErrMsg);
            jSONObject.put(JsonKey.OPEN_URL, this.mOpenUrl);
            jSONObject.put(JsonKey.LINK_MODE, this.mLinkMode);
            jSONObject.put(JsonKey.DOWNLOAD_MODE, this.mDownloadMode);
            jSONObject.put(JsonKey.MODEL_TYPE, this.mModelType);
            jSONObject.put(JsonKey.APP_NAME, this.mAppName);
            jSONObject.put(JsonKey.APP_ICON, this.mAppIcon);
            jSONObject.put(JsonKey.DOWNLOAD_FAILED_TIMES, this.mDownloadFailedTimes);
            jSONObject.put(JsonKey.RECENT_DOWNLOAD_RESUME_TIME, this.mRecentDownloadResumeTime == 0 ? this.mTimeStamp : this.mRecentDownloadResumeTime);
            jSONObject.put(JsonKey.CLICK_PAUSE_TIMES, this.mClickPauseTimes);
            jSONObject.put(JsonKey.JUMP_INSTALL_TIME, this.mJumpInstallTime);
            jSONObject.put(JsonKey.CANCEL_INSTALL_TIME, this.mCancelInstallTime);
            jSONObject.put(JsonKey.LAST_FAILED_RESUME_COUNT, this.mLastFailedResumeCount);
            jSONObject.put(JsonKey.IS_UPDATE_DOWNLOAD, this.mIsUpdateDownload);
            jSONObject.put(JsonKey.MIME_TYPE, this.mOriginMimeType);
            jSONObject.put(JsonKey.PATCH_APPLY_HANDLED, this.mIsPatchApplyHandled);
            jSONObject.put(JsonKey.DOWNLOAD_FINISH_REASON, this.mDownloadFinishReason);
            jSONObject.put(JsonKey.CLICK_DOWNLOAD_TIME, this.mClickDownloadTime);
            jSONObject.put(JsonKey.CLICK_DOWNLOAD_SIZE, this.mClickDownloadSize);
            jSONObject.put(JsonKey.INSTALL_AFTER_CLEAN, this.mInstallAfterCleanSpace);
            jSONObject.put(JsonKey.FUNNEL_TYPE, this.funnelType);
            jSONObject.put(JsonKey.WEB_URL, this.mWebUrl);
            jSONObject.put(JsonKey.ENABLE_COMPLIANCE_DIALOG, this.mEnableShowComplianceDialog);
            jSONObject.put(JsonKey.AUTO_DOWNLOAD_ON_CARD_SHOW, this.mIsAutoDownloadOnCardShow);
            jSONObject.put("enable_new_activity", this.mEnableNewActivity ? 1 : 0);
            jSONObject.put("enable_pause", this.mEnablePause ? 1 : 0);
            jSONObject.put("enable_ah", this.mEnableAH ? 1 : 0);
            jSONObject.put("enable_am", this.mEnableAM ? 1 : 0);
        } catch (Exception e) {
            GlobalInfo.getTTMonitor().monitorException(e, "NativeDownloadModel toJson");
        }
        return jSONObject;
    }

    public static NativeDownloadModel fromJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        NativeDownloadModel nativeDownloadModel = new NativeDownloadModel();
        try {
            nativeDownloadModel.setId(ToolUtils.optLong(jSONObject, JsonKey.ID));
            nativeDownloadModel.setExtValue(ToolUtils.optLong(jSONObject, JsonKey.EXT_VALUE));
            nativeDownloadModel.setLogExtra(jSONObject.optString(JsonKey.LOG_EXTRA));
            nativeDownloadModel.setDownloadStatus(jSONObject.optInt(JsonKey.DOWNLOAD_STATUS));
            nativeDownloadModel.setPackageName(jSONObject.optString(JsonKey.PACKAGE_NAME));
            boolean z = true;
            nativeDownloadModel.setIsAd(jSONObject.optBoolean(JsonKey.IS_AD, true));
            nativeDownloadModel.setTimeStamp(ToolUtils.optLong(jSONObject, JsonKey.TIME_STAMP));
            nativeDownloadModel.setVersionCode(jSONObject.optInt(JsonKey.VERSION_CODE));
            nativeDownloadModel.setVersionName(jSONObject.optString(JsonKey.VERSION_NAME));
            nativeDownloadModel.setDownloadId(jSONObject.optInt(JsonKey.DOWNLOAD_ID));
            nativeDownloadModel.setIsV3Event(jSONObject.optBoolean(JsonKey.IS_V3_EVENT));
            nativeDownloadModel.setInstallScene(jSONObject.optInt(JsonKey.SCENE));
            nativeDownloadModel.setEventTag(jSONObject.optString(JsonKey.EVENT_TAG));
            nativeDownloadModel.setEventRefer(jSONObject.optString(JsonKey.EVENT_REFER));
            nativeDownloadModel.setDownloadUrl(jSONObject.optString(JsonKey.DOWNLOAD_URL));
            nativeDownloadModel.setEnableBackDialog(jSONObject.optBoolean(JsonKey.ENABLE_BACK_DIALOG));
            nativeDownloadModel.hasSendInstallFinish.set(jSONObject.optBoolean(JsonKey.HAS_SEND_INSTALL_FINISH));
            nativeDownloadModel.hasSendDownloadFailedFinally.set(jSONObject.optBoolean(JsonKey.HAS_SEND_DOWNLOAD_FAILED_FINALLY));
            nativeDownloadModel.setLastFailedErrCode(jSONObject.optInt(JsonKey.LAST_FAILED_CODE));
            nativeDownloadModel.setLastFailedErrMsg(jSONObject.optString(JsonKey.LAST_FAILED_MSG));
            nativeDownloadModel.setOpenUrl(jSONObject.optString(JsonKey.OPEN_URL));
            nativeDownloadModel.setLinkMode(jSONObject.optInt(JsonKey.LINK_MODE));
            nativeDownloadModel.setDownloadMode(jSONObject.optInt(JsonKey.DOWNLOAD_MODE));
            nativeDownloadModel.setModelMode(jSONObject.optInt(JsonKey.MODEL_TYPE));
            nativeDownloadModel.setAppName(jSONObject.optString(JsonKey.APP_NAME));
            nativeDownloadModel.setAppIcon(jSONObject.optString(JsonKey.APP_ICON));
            nativeDownloadModel.setDownloadFailedTimes(jSONObject.optInt(JsonKey.DOWNLOAD_FAILED_TIMES, 0));
            nativeDownloadModel.setRecentDownloadResumeTime(ToolUtils.optLong(jSONObject, JsonKey.RECENT_DOWNLOAD_RESUME_TIME));
            nativeDownloadModel.setClickPauseTimes(jSONObject.optInt(JsonKey.CLICK_PAUSE_TIMES));
            nativeDownloadModel.setJumpInstallTime(ToolUtils.optLong(jSONObject, JsonKey.JUMP_INSTALL_TIME));
            nativeDownloadModel.setCancelInstallTime(ToolUtils.optLong(jSONObject, JsonKey.CANCEL_INSTALL_TIME));
            nativeDownloadModel.setLastFailedResumeCount(jSONObject.optInt(JsonKey.LAST_FAILED_RESUME_COUNT));
            nativeDownloadModel.setDownloadFinishReason(jSONObject.optString(JsonKey.DOWNLOAD_FINISH_REASON));
            nativeDownloadModel.setClickDownloadSize(jSONObject.optLong(JsonKey.CLICK_DOWNLOAD_SIZE));
            nativeDownloadModel.setClickDownloadTime(jSONObject.optLong(JsonKey.CLICK_DOWNLOAD_TIME));
            nativeDownloadModel.setIsUpdateDownload(jSONObject.optBoolean(JsonKey.IS_UPDATE_DOWNLOAD));
            nativeDownloadModel.setOriginMimeType(jSONObject.optString(JsonKey.MIME_TYPE));
            nativeDownloadModel.setPatchApplyHandled(jSONObject.optBoolean(JsonKey.PATCH_APPLY_HANDLED));
            nativeDownloadModel.setInstallAfterCleanSpace(jSONObject.optBoolean(JsonKey.INSTALL_AFTER_CLEAN));
            nativeDownloadModel.setFunnelType(jSONObject.optInt(JsonKey.FUNNEL_TYPE, 1));
            nativeDownloadModel.setWebUrl(jSONObject.optString(JsonKey.WEB_URL));
            nativeDownloadModel.setEnableShowComplianceDialog(jSONObject.optBoolean(JsonKey.ENABLE_COMPLIANCE_DIALOG, true));
            nativeDownloadModel.setIsAutoDownloadOnCardShow(jSONObject.optBoolean(JsonKey.AUTO_DOWNLOAD_ON_CARD_SHOW));
            nativeDownloadModel.setEnableNewActivity(jSONObject.optInt("enable_new_activity", 1) == 1);
            nativeDownloadModel.setEnablePause(jSONObject.optInt("enable_pause", 1) == 1);
            nativeDownloadModel.setEnableAH(jSONObject.optInt("enable_ah", 1) == 1);
            if (jSONObject.optInt("enable_am", 1) != 1) {
                z = false;
            }
            nativeDownloadModel.setEnableAM(z);
            nativeDownloadModel.setExtras(jSONObject.optJSONObject(JsonKey.EXTRA));
        } catch (Exception e) {
            GlobalInfo.getTTMonitor().monitorException(e, "NativeDownloadModel fromJson");
        }
        return nativeDownloadModel;
    }

    public static JSONObject getNotNullExtJson(NativeDownloadModel nativeDownloadModel) {
        if (nativeDownloadModel != null && nativeDownloadModel.getExtra() != null) {
            return nativeDownloadModel.getExtra();
        }
        return new JSONObject();
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public DownloadModel getModel() {
        return generateDownloadModel();
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public DownloadEventConfig getEvent() {
        return generateEventConfig();
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public DownloadController getController() {
        return generateDownloadController();
    }

    public AdDownloadModel generateDownloadModel() {
        return new AdDownloadModel.Builder().setAdId(this.mId).setExtraValue(this.mExtValue).setLogExtra(this.mLogExtra).setPackageName(this.mPackageName).setExtra(this.mExtras).setIsAd(this.mIsAd).setVersionCode(this.mVersionCode).setVersionName(this.mVersionName).setDownloadUrl(this.mDownloadUrl).setModelType(this.mModelType).setMimeType(this.mOriginMimeType).setAppName(this.mAppName).setAppIcon(this.mAppIcon).setDeepLink(new DeepLink(this.mOpenUrl, this.mWebUrl, null)).build();
    }

    public AdDownloadEventConfig generateEventConfig() {
        return new AdDownloadEventConfig.Builder().setClickButtonTag(this.mEventTag).setRefer(this.mEventRefer).setIsEnableV3Event(this.isV3Event).build();
    }

    public AdDownloadController generateDownloadController() {
        return new AdDownloadController.Builder().setIsEnableBackDialog(this.mEnableBackDialog).setLinkMode(this.mLinkMode).setDownloadMode(this.mDownloadMode).setEnableShowComplianceDialog(this.mEnableShowComplianceDialog).setEnableAH(this.mEnableAH).setEnableAM(this.mEnableAM).build();
    }
}
