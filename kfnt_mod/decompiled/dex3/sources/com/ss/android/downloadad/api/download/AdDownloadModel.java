package com.ss.android.downloadad.api.download;

import android.text.TextUtils;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.download.api.model.DeepLink;
import com.ss.android.download.api.model.QuickAppModel;
import com.ss.android.download.api.utils.ToolUtils;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.socialbase.downloader.constants.ExecutorGroup;
import com.ss.android.socialbase.downloader.depend.IDownloadFileUriProvider;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AdDownloadModel implements DownloadModel {
    protected String mAppIcon;
    protected String mAppName;
    protected boolean mAutoInstallWithoutNotification;
    protected List<String> mBackupUrls;
    protected List<String> mClickTrackUrl;
    protected DeepLink mDeepLink;
    protected boolean mDistinctDir;
    protected JSONObject mDownloadSettings;
    protected String mDownloadUrl;
    protected long mExpectFileLength;
    protected JSONObject mExtra;
    protected long mExtraValue;
    protected String mFileName;
    protected String mFilePath;
    protected IDownloadFileUriProvider mFileUriProvider;
    protected Map<String, String> mHeaders;
    protected long mId;
    protected boolean mIndependentProcess;

    @Deprecated
    protected boolean mIsInExternalPublicDir;
    protected String mLogExtra;
    protected String mMd5;
    protected String mMimeType;
    protected int mModelType;
    protected String mNotificationJumpUrl;
    protected String mPackageName;
    protected QuickAppModel mQuickAppModel;
    protected String mSdkMonitorScene;
    protected String mStartToast;
    protected int mVersionCode;
    protected String mVersionName;
    protected boolean mIsAd = true;
    protected boolean mIsShowToast = true;
    protected boolean mIsShowNotification = true;
    protected boolean mAutoInstall = true;
    protected boolean mNeedWifi = false;

    @ExecutorGroup
    protected int mExecutorGroup = 2;
    protected boolean mEnablePause = true;
    protected int mFunnelType = 1;

    interface JsonKey {
        public static final String APP_ICON = "app_icon";
        public static final String APP_NAME = "app_name";
        public static final String AUTO_INSTALL = "auto_install";
        public static final String AUTO_INSTALL_WITHOUT_NOTIFY = "auto_install_without_notify";
        public static final String BACKUP_URLS = "backup_urls";
        public static final String CLICK_TRACK_URLS = "click_track_urls";
        public static final String DISTINCT_DIR = "distinct_dir";
        public static final String DOWNLOAD_SETTINGS = "download_settings";
        public static final String DOWNLOAD_URL = "download_url";
        public static final String ENABLE_PAUSE = "enable_pause";
        public static final String EXECUTOR_GROUP = "executor_group";
        public static final String EXPECT_FILE_LENGTH = "expect_file_length";
        public static final String EXTRA = "extra";
        public static final String EXT_VALUE = "ext_value";
        public static final String FILE_NAME = "file_name";
        public static final String FILE_PATH = "file_path";
        public static final String HEADER_KEYS = "header_keys";
        public static final String HEADER_VALUES = "header_values";
        public static final String ID = "id";
        public static final String INDEPENDENT_PROCESS = "independent_process";
        public static final String IS_AD = "is_ad";
        public static final String IS_SHOW_TOAST = "is_show_toast";
        public static final String LOG_EXTRA = "log_extra";
        public static final String MD5 = "md5";
        public static final String MIME_TYPE = "mime_type";
        public static final String MODEL_TYPE = "model_type";
        public static final String NEED_WIFI = "need_wifi";
        public static final String NOTIFICATION_JUMP_URL = "notification_jump_url";
        public static final String OPEN_URL = "open_url";
        public static final String PACKAGE_NAME = "package_name";
        public static final String QUICK_APP_URL = "quick_app_url";
        public static final String SDK_MONITOR_SCENE = "sdk_monitor_scene";
        public static final String SHOW_NOTIFICATION = "show_notification";
        public static final String START_TOAST = "start_toast";
        public static final String VERSION_CODE = "version_code";
        public static final String VERSION_NAME = "version_name";
        public static final String WEB_URL = "web_url";
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public boolean isVisibleInDownloadsUi() {
        return true;
    }

    protected AdDownloadModel() {
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public long getId() {
        return this.mId;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public String getMd5() {
        return this.mMd5;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public long getExpectFileLength() {
        return this.mExpectFileLength;
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
        return this.mAppName;
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
        return this.mNeedWifi;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public boolean isInExternalPublicDir() {
        return this.mIsInExternalPublicDir;
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
        this.mNeedWifi = true;
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
    public boolean isAd() {
        return this.mIsAd;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public String getLogExtra() {
        return this.mLogExtra;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public String getPackageName() {
        return this.mPackageName;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public String getAppIcon() {
        return this.mAppIcon;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public DeepLink getDeepLink() {
        return this.mDeepLink;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public List<String> getClickTrackUrl() {
        return this.mClickTrackUrl;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public JSONObject getExtra() {
        return this.mExtra;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public int getModelType() {
        return this.mModelType;
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

    @Override // com.ss.android.download.api.download.DownloadModel
    public int getExecutorGroup() {
        return this.mExecutorGroup;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public int getFunnelType() {
        return this.mFunnelType;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public String getStartToast() {
        return this.mStartToast;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public String getSdkMonitorScene() {
        return this.mSdkMonitorScene;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public boolean isAutoInstall() {
        return this.mAutoInstall;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public boolean distinctDir() {
        return this.mDistinctDir;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public boolean enablePause() {
        return this.mEnablePause;
    }

    public void setMd5(String str) {
        this.mMd5 = str;
    }

    public void setExpectFileLength(long j) {
        this.mExpectFileLength = j;
    }

    public void setNeedWifi(boolean z) {
        this.mNeedWifi = z;
    }

    public void setIsShowToast(boolean z) {
        this.mIsShowToast = z;
    }

    public void setExtraValue(long j) {
        this.mExtraValue = j;
    }

    public void setAppName(String str) {
        this.mAppName = str;
    }

    public void setExtra(JSONObject jSONObject) {
        this.mExtra = jSONObject;
    }

    public void setStartToast(String str) {
        this.mStartToast = str;
    }

    public void setSdkMonitorScene(String str) {
        this.mSdkMonitorScene = str;
    }

    @Deprecated
    public AdDownloadModel setAdId(long j) {
        return setId(j);
    }

    public AdDownloadModel setId(long j) {
        this.mId = j;
        return this;
    }

    public AdDownloadModel setIsAd(boolean z) {
        this.mIsAd = z;
        return this;
    }

    public AdDownloadModel setModelType(int i) {
        this.mModelType = i;
        return this;
    }

    public AdDownloadModel setLogExtra(String str) {
        this.mLogExtra = str;
        return this;
    }

    public AdDownloadModel setPackageName(String str) {
        this.mPackageName = str;
        return this;
    }

    public AdDownloadModel setAppIcon(String str) {
        this.mAppIcon = str;
        return this;
    }

    public AdDownloadModel setDeepLink(DeepLink deepLink) {
        this.mDeepLink = deepLink;
        return this;
    }

    public AdDownloadModel setClickTrackUrl(List<String> list) {
        this.mClickTrackUrl = list;
        return this;
    }

    public AdDownloadModel setDownloadUrl(String str) {
        this.mDownloadUrl = str;
        return this;
    }

    public AdDownloadModel setBackupUrls(List<String> list) {
        this.mBackupUrls = list;
        return this;
    }

    public AdDownloadModel setNotificationJumpUrl(String str) {
        this.mNotificationJumpUrl = str;
        return this;
    }

    public AdDownloadModel setMimeType(String str) {
        this.mMimeType = str;
        return this;
    }

    public AdDownloadModel setHeaders(Map<String, String> map) {
        this.mHeaders = map;
        return this;
    }

    public AdDownloadModel setIsShowNotification(boolean z) {
        this.mIsShowNotification = z;
        return this;
    }

    @Override // com.ss.android.download.api.download.DownloadModel
    public AdDownloadModel setFilePath(String str) {
        this.mFilePath = str;
        return this;
    }

    public AdDownloadModel setFileName(String str) {
        this.mFileName = str;
        return this;
    }

    public AdDownloadModel setNeedIndependentProcess(boolean z) {
        this.mIndependentProcess = z;
        return this;
    }

    public AdDownloadModel setVersionCode(int i) {
        this.mVersionCode = i;
        return this;
    }

    public AdDownloadModel setVersionName(String str) {
        this.mVersionName = str;
        return this;
    }

    public AdDownloadModel setQuickAppModel(QuickAppModel quickAppModel) {
        this.mQuickAppModel = quickAppModel;
        return this;
    }

    public AdDownloadModel setAutoInstallWithoutNotification(boolean z) {
        this.mAutoInstallWithoutNotification = z;
        return this;
    }

    public AdDownloadModel setFileUriProvider(IDownloadFileUriProvider iDownloadFileUriProvider) {
        this.mFileUriProvider = iDownloadFileUriProvider;
        return this;
    }

    public AdDownloadModel setFunnelType(int i) {
        this.mFunnelType = i;
        return this;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(JsonKey.ID, this.mId);
            jSONObject.put(JsonKey.IS_AD, this.mIsAd ? 1 : 0);
            jSONObject.putOpt(JsonKey.MODEL_TYPE, Integer.valueOf(this.mModelType));
            jSONObject.putOpt("mime_type", this.mMimeType);
            jSONObject.putOpt("ext_value", Long.valueOf(this.mExtraValue));
            jSONObject.putOpt("log_extra", this.mLogExtra);
            jSONObject.putOpt("package_name", this.mPackageName);
            jSONObject.putOpt("download_url", this.mDownloadUrl);
            jSONObject.putOpt("app_name", this.mAppName);
            jSONObject.putOpt(JsonKey.APP_ICON, this.mAppIcon);
            jSONObject.putOpt(JsonKey.IS_SHOW_TOAST, Integer.valueOf(this.mIsShowToast ? 1 : 0));
            jSONObject.putOpt(JsonKey.SHOW_NOTIFICATION, Integer.valueOf(this.mIsShowNotification ? 1 : 0));
            jSONObject.put(JsonKey.NEED_WIFI, this.mNeedWifi ? 1 : 0);
            jSONObject.put("md5", this.mMd5);
            jSONObject.put("expect_file_length", this.mExpectFileLength);
            jSONObject.put(JsonKey.INDEPENDENT_PROCESS, this.mIndependentProcess ? 1 : 0);
            jSONObject.put("version_code", this.mVersionCode);
            jSONObject.putOpt(JsonKey.VERSION_NAME, this.mVersionName);
            jSONObject.putOpt(JsonKey.FILE_PATH, this.mFilePath);
            jSONObject.putOpt("file_name", this.mFileName);
            jSONObject.putOpt("notification_jump_url", this.mNotificationJumpUrl);
            jSONObject.putOpt(JsonKey.AUTO_INSTALL_WITHOUT_NOTIFY, Integer.valueOf(this.mAutoInstallWithoutNotification ? 1 : 0));
            jSONObject.putOpt("executor_group", Integer.valueOf(this.mExecutorGroup));
            jSONObject.putOpt(JsonKey.START_TOAST, this.mStartToast);
            jSONObject.putOpt(JsonKey.SDK_MONITOR_SCENE, this.mSdkMonitorScene);
            jSONObject.putOpt("auto_install", Integer.valueOf(this.mAutoInstall ? 1 : 0));
            jSONObject.putOpt(JsonKey.DISTINCT_DIR, Integer.valueOf(this.mDistinctDir ? 1 : 0));
            jSONObject.putOpt("enable_pause", Integer.valueOf(this.mEnablePause ? 1 : 0));
            if (this.mDownloadSettings != null) {
                jSONObject.put(JsonKey.DOWNLOAD_SETTINGS, this.mDownloadSettings);
            }
            if (this.mBackupUrls != null && !this.mBackupUrls.isEmpty()) {
                JSONArray jSONArray = new JSONArray();
                for (String str : this.mBackupUrls) {
                    if (!TextUtils.isEmpty(str)) {
                        jSONArray.put(str);
                    }
                }
                jSONObject.put(JsonKey.BACKUP_URLS, jSONArray);
            }
            if (this.mDeepLink != null) {
                if (!TextUtils.isEmpty(this.mDeepLink.getOpenUrl())) {
                    jSONObject.put("open_url", this.mDeepLink.getOpenUrl());
                }
                if (!TextUtils.isEmpty(this.mDeepLink.getWebUrl())) {
                    jSONObject.put(JsonKey.WEB_URL, this.mDeepLink.getWebUrl());
                }
            }
            if (this.mQuickAppModel != null) {
                jSONObject.putOpt(JsonKey.QUICK_APP_URL, this.mQuickAppModel.getQuickOpenUrl());
            }
            if (this.mClickTrackUrl != null && !this.mClickTrackUrl.isEmpty()) {
                JSONArray jSONArray2 = new JSONArray();
                Iterator<String> it = this.mClickTrackUrl.iterator();
                while (it.hasNext()) {
                    jSONArray2.put(it.next());
                }
                jSONObject.put(JsonKey.CLICK_TRACK_URLS, jSONArray2);
            }
            if (this.mExtra != null) {
                jSONObject.put("extra", this.mExtra);
            }
            if (this.mHeaders != null && !this.mHeaders.isEmpty()) {
                JSONArray jSONArray3 = new JSONArray();
                JSONArray jSONArray4 = new JSONArray();
                for (Map.Entry<String, String> entry : this.mHeaders.entrySet()) {
                    jSONArray3.put(entry.getKey());
                    jSONArray4.put(entry.getKey());
                }
                jSONObject.put(JsonKey.HEADER_KEYS, jSONArray3);
                jSONObject.put(JsonKey.HEADER_VALUES, jSONArray4);
            }
        } catch (Exception e) {
            GlobalInfo.getTTMonitor().monitorException(e, "AdDownloadModel toJson");
        }
        return jSONObject;
    }

    public static AdDownloadModel fromJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        Builder builder = new Builder();
        try {
            boolean z = true;
            Builder distinctDir = builder.setAdId(ToolUtils.optLong(jSONObject, JsonKey.ID)).setIsAd(jSONObject.optInt(JsonKey.IS_AD, 1) == 1).setModelType(jSONObject.optInt(JsonKey.MODEL_TYPE)).setMimeType(jSONObject.optString("mime_type")).setExtraValue(ToolUtils.optLong(jSONObject, "ext_value")).setLogExtra(jSONObject.optString("log_extra")).setPackageName(jSONObject.optString("package_name")).setDownloadUrl(jSONObject.optString("download_url")).setAppName(jSONObject.optString("app_name")).setAppIcon(jSONObject.optString(JsonKey.APP_ICON)).setIsShowToast(jSONObject.optInt(JsonKey.IS_SHOW_TOAST, 1) == 1).setIsShowNotification(jSONObject.optInt(JsonKey.SHOW_NOTIFICATION, 1) == 1).setNeedWifi(jSONObject.optInt(JsonKey.NEED_WIFI) == 1).setMd5(jSONObject.optString("md5")).setExpectFileLength(jSONObject.optLong("expect_file_length")).setNeedIndependentProcess(jSONObject.optInt(JsonKey.INDEPENDENT_PROCESS) == 1).setVersionCode(jSONObject.optInt("version_code")).setVersionName(jSONObject.optString(JsonKey.VERSION_NAME)).setFilePath(jSONObject.optString(JsonKey.FILE_PATH)).setFileName(jSONObject.optString("file_name")).setNotificationJumpUrl(jSONObject.optString("notification_jump_url")).setAutoInstallWithoutNotification(jSONObject.optInt(JsonKey.AUTO_INSTALL_WITHOUT_NOTIFY) == 1).setExecutorGroup(jSONObject.optInt("executor_group")).setDownloadSettings(jSONObject.optJSONObject(JsonKey.DOWNLOAD_SETTINGS)).setExtra(jSONObject.optJSONObject("extra")).setStartToast(jSONObject.optString(JsonKey.START_TOAST)).setSdkMonitorScene(jSONObject.optString(JsonKey.SDK_MONITOR_SCENE)).setAutoInstall(jSONObject.optInt("auto_install", 1) == 1).setDistinctDir(jSONObject.optInt(JsonKey.DISTINCT_DIR) == 1);
            if (jSONObject.optInt("enable_pause", 1) != 1) {
                z = false;
            }
            distinctDir.setEnablePause(z);
            appendDeepLinkFromJson(jSONObject, builder);
            appendQuickAppUrlFromJson(jSONObject, builder);
            appendTrackUrlFromJson(jSONObject, builder);
            appendHeaderMapFromJson(jSONObject, builder);
            appendBackupUrlsFromJson(jSONObject, builder);
        } catch (Exception e) {
            GlobalInfo.getTTMonitor().monitorException(e, "AdDownloadModel fromJson");
        }
        return builder.build();
    }

    private static void appendDeepLinkFromJson(JSONObject jSONObject, Builder builder) {
        builder.setDeepLink(new DeepLink(jSONObject.optString("open_url"), jSONObject.optString(JsonKey.WEB_URL), null));
    }

    private static void appendQuickAppUrlFromJson(JSONObject jSONObject, Builder builder) {
        String strOptString = jSONObject.optString(JsonKey.QUICK_APP_URL);
        if (TextUtils.isEmpty(strOptString)) {
            return;
        }
        builder.setQuickAppModel(new QuickAppModel.Builder().setOpenUrl(strOptString).build());
    }

    private static void appendTrackUrlFromJson(JSONObject jSONObject, Builder builder) {
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(JsonKey.CLICK_TRACK_URLS);
        if (jSONArrayOptJSONArray != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(jSONArrayOptJSONArray.optString(i));
            }
            builder.setClickTrackUrl(arrayList);
        }
    }

    private static void appendHeaderMapFromJson(JSONObject jSONObject, Builder builder) {
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(JsonKey.HEADER_KEYS);
        JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray(JsonKey.HEADER_VALUES);
        if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray2 == null) {
            return;
        }
        HashMap map = new HashMap();
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            map.put(jSONArrayOptJSONArray.optString(i), jSONArrayOptJSONArray2.optString(i));
        }
        builder.setHeaders(map);
    }

    private static void appendBackupUrlsFromJson(JSONObject jSONObject, Builder builder) {
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(JsonKey.BACKUP_URLS);
        if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            arrayList.add(jSONArrayOptJSONArray.optString(i));
        }
        builder.setBackupUrls(arrayList);
    }

    public static final class Builder {
        AdDownloadModel model = new AdDownloadModel();

        public Builder setExpectFileLength(long j) {
            this.model.mExpectFileLength = j;
            return this;
        }

        public Builder setMd5(String str) {
            this.model.mMd5 = str;
            return this;
        }

        @Deprecated
        public Builder setAdId(long j) {
            return setId(j);
        }

        public Builder setId(long j) {
            this.model.mId = j;
            return this;
        }

        public Builder setExtraValue(long j) {
            this.model.mExtraValue = j;
            return this;
        }

        public Builder setIsAd(boolean z) {
            this.model.mIsAd = z;
            return this;
        }

        public Builder setModelType(int i) {
            this.model.mModelType = i;
            return this;
        }

        public Builder setLogExtra(String str) {
            this.model.mLogExtra = str;
            return this;
        }

        public Builder setPackageName(String str) {
            this.model.mPackageName = str;
            return this;
        }

        public Builder setAppIcon(String str) {
            this.model.mAppIcon = str;
            return this;
        }

        public Builder setDeepLink(DeepLink deepLink) {
            this.model.mDeepLink = deepLink;
            return this;
        }

        public Builder setClickTrackUrl(List<String> list) {
            this.model.mClickTrackUrl = list;
            return this;
        }

        public Builder setExtra(JSONObject jSONObject) {
            this.model.mExtra = jSONObject;
            return this;
        }

        public Builder setDownloadUrl(String str) {
            this.model.mDownloadUrl = str;
            return this;
        }

        public Builder setBackupUrls(List<String> list) {
            this.model.mBackupUrls = list;
            return this;
        }

        public Builder setNotificationJumpUrl(String str) {
            this.model.mNotificationJumpUrl = str;
            return this;
        }

        public Builder setAppName(String str) {
            this.model.mAppName = str;
            return this;
        }

        public Builder setMimeType(String str) {
            this.model.mMimeType = str;
            return this;
        }

        public Builder setHeaders(Map<String, String> map) {
            this.model.mHeaders = map;
            return this;
        }

        public Builder setIsShowToast(boolean z) {
            this.model.mIsShowToast = z;
            return this;
        }

        public Builder setIsShowNotification(boolean z) {
            this.model.mIsShowNotification = z;
            return this;
        }

        public Builder setNeedWifi(boolean z) {
            this.model.mNeedWifi = z;
            return this;
        }

        public Builder setDownloadSettings(JSONObject jSONObject) {
            this.model.mDownloadSettings = jSONObject;
            return this;
        }

        @Deprecated
        public Builder setIsInExternalPublicDir(boolean z) {
            this.model.mIsInExternalPublicDir = z;
            return this;
        }

        public Builder setFilePath(String str) {
            this.model.mFilePath = str;
            return this;
        }

        public Builder setFileName(String str) {
            this.model.mFileName = str;
            return this;
        }

        public Builder setNeedIndependentProcess(boolean z) {
            this.model.mIndependentProcess = z;
            return this;
        }

        public Builder setVersionCode(int i) {
            this.model.mVersionCode = i;
            return this;
        }

        public Builder setVersionName(String str) {
            this.model.mVersionName = str;
            return this;
        }

        public Builder setQuickAppModel(QuickAppModel quickAppModel) {
            this.model.mQuickAppModel = quickAppModel;
            return this;
        }

        public Builder setAutoInstallWithoutNotification(boolean z) {
            this.model.mAutoInstallWithoutNotification = z;
            return this;
        }

        public Builder setFileUriProvider(IDownloadFileUriProvider iDownloadFileUriProvider) {
            this.model.mFileUriProvider = iDownloadFileUriProvider;
            return this;
        }

        public Builder setExecutorGroup(@ExecutorGroup int i) {
            this.model.mExecutorGroup = i;
            return this;
        }

        public Builder setStartToast(String str) {
            this.model.mStartToast = str;
            return this;
        }

        public Builder setSdkMonitorScene(String str) {
            this.model.mSdkMonitorScene = str;
            return this;
        }

        public Builder setAutoInstall(boolean z) {
            this.model.mAutoInstall = z;
            return this;
        }

        public Builder setDistinctDir(boolean z) {
            this.model.mDistinctDir = z;
            return this;
        }

        public Builder setEnablePause(boolean z) {
            this.model.mEnablePause = z;
            return this;
        }

        public AdDownloadModel build() {
            return this.model;
        }
    }
}
