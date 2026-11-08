package com.ss.android.download.api.download;

import com.ss.android.download.api.model.DeepLink;
import com.ss.android.download.api.model.QuickAppModel;
import com.ss.android.download.api.utils.ToolUtils;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.socialbase.downloader.depend.IDownloadFileUriProvider;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

@Deprecated
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SimpleDownloadModel implements DownloadModel {
    private boolean mAutoInstallWithoutNotification;
    private List<String> mBackupUrls;
    private JSONObject mDownloadSettings;
    private String mDownloadUrl;
    private long mExtraValue;
    private String mFileName;
    private String mFilePath;
    private IDownloadFileUriProvider mFileUriProvider;
    private Map<String, String> mHeaders;
    private long mId;
    private boolean mIndependentProcess;
    private boolean mIsInExternalPublicDir;
    private boolean mIsNeedWifi;
    private boolean mIsShowNotification;
    private boolean mIsShowToast;
    private boolean mIsVisibleInDownloadsUi;
    private String mMimeType;
    private String mName;
    private String mNotificationJumpUrl;
    private String mPackageName;
    private QuickAppModel mQuickAppModel;
    private int mVersionCode;
    private String mVersionName;

    @Override // com.ss.android.download.api.download.DownloadModel
    public boolean distinctDir() {
        return false;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public boolean enablePause() {
        return true;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public String getAppIcon() {
        return null;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public List<String> getClickTrackUrl() {
        return null;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public DeepLink getDeepLink() {
        return null;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public int getExecutorGroup() {
        return 0;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public long getExpectFileLength() {
        return 0L;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public JSONObject getExtra() {
        return null;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public int getFunnelType() {
        return 0;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public String getLogExtra() {
        return null;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public String getMd5() {
        return null;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public int getModelType() {
        return 0;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public String getSdkMonitorScene() {
        return null;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public String getStartToast() {
        return null;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public boolean isAd() {
        return false;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public boolean isAutoInstall() {
        return true;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public DownloadModel setFilePath(String str) {
        return this;
    }

    private SimpleDownloadModel(Builder builder) {
        this.mId = builder.mId;
        this.mExtraValue = builder.mExtraValue;
        this.mDownloadUrl = builder.mDownloadUrl;
        this.mName = builder.mName;
        this.mMimeType = builder.mMimeType;
        this.mHeaders = builder.mHeaders;
        this.mIsShowToast = builder.mIsShowToast;
        this.mIsShowNotification = builder.mIsShowNotification;
        this.mIsNeedWifi = builder.mNeedWifi;
        this.mDownloadSettings = builder.mDownloadSettings;
        this.mIsInExternalPublicDir = builder.mIsInExternalPublicDir;
        this.mIsVisibleInDownloadsUi = builder.mIsVisibleInDownloadsUi;
        this.mFilePath = builder.mFilePath;
        this.mFileName = builder.mFileName;
        this.mPackageName = builder.mPackageName;
        this.mIndependentProcess = builder.mIndependentProcess;
        this.mVersionCode = builder.mVersionCode;
        this.mVersionName = builder.mVersionName;
        this.mQuickAppModel = builder.mQuickAppModel;
        this.mAutoInstallWithoutNotification = builder.mAutoInstallWithoutNotification;
        this.mFileUriProvider = builder.mFileUriProvider;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public long getId() {
        return this.mId;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public long getExtraValue() {
        return this.mExtraValue;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public String getDownloadUrl() {
        return this.mDownloadUrl;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public List<String> getBackupUrls() {
        return this.mBackupUrls;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public String getNotificationJumpUrl() {
        return this.mNotificationJumpUrl;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public String getName() {
        return this.mName;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public String getMimeType() {
        return this.mMimeType;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public Map<String, String> getHeaders() {
        return this.mHeaders;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public boolean isShowToast() {
        return this.mIsShowToast;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public boolean isShowNotification() {
        return this.mIsShowNotification;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public boolean isNeedWifi() {
        return this.mIsNeedWifi;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public boolean isInExternalPublicDir() {
        return this.mIsInExternalPublicDir;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public boolean isVisibleInDownloadsUi() {
        return this.mIsVisibleInDownloadsUi;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public String getFilePath() {
        return this.mFilePath;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public String getFileName() {
        return this.mFileName;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public void forceWifi() {
        this.mIsNeedWifi = true;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public JSONObject getDownloadSettings() {
        return this.mDownloadSettings;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public void forceHideToast() {
        this.mIsShowToast = false;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public void forceHideNotification() {
        this.mIsShowNotification = false;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public boolean needIndependentProcess() {
        return this.mIndependentProcess;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public int getVersionCode() {
        return this.mVersionCode;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public String getVersionName() {
        return this.mVersionName;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public String getPackageName() {
        return this.mPackageName;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public QuickAppModel getQuickAppModel() {
        return this.mQuickAppModel;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public boolean autoInstallWithoutNotification() {
        return this.mAutoInstallWithoutNotification;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public IDownloadFileUriProvider getDownloadFileUriProvider() {
        return this.mFileUriProvider;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public boolean shouldDownloadWithPatchApply() {
        return ToolUtils.shouldDownloadWithPatchApply(DownloadSetting.obtain(getDownloadSettings()), getMimeType());
    }

    public static class Builder {
        private boolean mAutoInstallWithoutNotification;
        private List<String> mBackupUrls;
        private JSONObject mDownloadSettings;
        private String mDownloadUrl;
        private long mExtraValue;
        private String mFileName;
        private String mFilePath;
        private IDownloadFileUriProvider mFileUriProvider;
        private Map<String, String> mHeaders;
        private long mId;
        private boolean mIndependentProcess;
        private String mName;
        private String mNotificationJumpUrl;
        private String mPackageName;
        private QuickAppModel mQuickAppModel;
        private int mVersionCode;
        private String mVersionName;
        private String mMimeType = "application/vnd.android.package-archive";
        private boolean mIsShowToast = true;
        private boolean mIsShowNotification = true;
        private boolean mNeedWifi = false;
        private boolean mIsInExternalPublicDir = true;
        private boolean mIsVisibleInDownloadsUi = true;

        public Builder setId(long j) {
            this.mId = j;
            return this;
        }

        public Builder setExtraValue(long j) {
            this.mExtraValue = j;
            return this;
        }

        public Builder setDownloadUrl(String str) {
            this.mDownloadUrl = str;
            return this;
        }

        public Builder setName(String str) {
            this.mName = str;
            return this;
        }

        public Builder setMimeType(String str) {
            this.mMimeType = str;
            return this;
        }

        public Builder setHeaders(Map<String, String> map) {
            this.mHeaders = map;
            return this;
        }

        public Builder setIsShowToast(boolean z) {
            this.mIsShowToast = z;
            return this;
        }

        public Builder setIsShowNotification(boolean z) {
            this.mIsShowNotification = z;
            return this;
        }

        public Builder setNeedWifi(boolean z) {
            this.mNeedWifi = z;
            return this;
        }

        public Builder setDownloadSettings(JSONObject jSONObject) {
            this.mDownloadSettings = jSONObject;
            return this;
        }

        public Builder setBackupUrls(List<String> list) {
            this.mBackupUrls = list;
            return this;
        }

        public Builder setNotificationJumpUrl(String str) {
            this.mNotificationJumpUrl = str;
            return this;
        }

        public Builder setIsInExternalPublicDir(boolean z) {
            this.mIsInExternalPublicDir = z;
            return this;
        }

        public Builder setIsVisibleInDownloadsUi(boolean z) {
            this.mIsVisibleInDownloadsUi = z;
            return this;
        }

        public Builder setFilePath(String str) {
            this.mFilePath = str;
            return this;
        }

        public Builder setFileName(String str) {
            this.mFileName = str;
            return this;
        }

        public Builder setPackagName(String str) {
            this.mPackageName = str;
            return this;
        }

        public Builder setNeedIndependentProcess(boolean z) {
            this.mIndependentProcess = z;
            return this;
        }

        public Builder setVersionCode(int i) {
            this.mVersionCode = i;
            return this;
        }

        public Builder setVersionName(String str) {
            this.mVersionName = str;
            return this;
        }

        public Builder setQuickAppModel(QuickAppModel quickAppModel) {
            this.mQuickAppModel = quickAppModel;
            return this;
        }

        public Builder setAutoInstallWithoutNotification(boolean z) {
            this.mAutoInstallWithoutNotification = z;
            return this;
        }

        public Builder setFileUriProvider(IDownloadFileUriProvider iDownloadFileUriProvider) {
            this.mFileUriProvider = iDownloadFileUriProvider;
            return this;
        }

        public SimpleDownloadModel build() {
            GlobalInfo.getTTMonitor().monitorPathError(false, "SimpleDownloadModel");
            return new SimpleDownloadModel(this);
        }
    }
}
