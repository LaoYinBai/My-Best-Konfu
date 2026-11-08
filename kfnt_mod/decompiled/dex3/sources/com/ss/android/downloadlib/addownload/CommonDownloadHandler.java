package com.ss.android.downloadlib.addownload;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.ss.android.download.api.config.AbsDownloadStatusChangeListener;
import com.ss.android.download.api.config.IDownloadButtonClickListener;
import com.ss.android.download.api.config.IPermissionCallback;
import com.ss.android.download.api.config.OnItemClickListener;
import com.ss.android.download.api.download.DownloadController;
import com.ss.android.download.api.download.DownloadEventConfig;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.download.api.download.DownloadStatusChangeListener;
import com.ss.android.download.api.download.SimpleDownloadController;
import com.ss.android.download.api.download.SimpleDownloadEventConfig;
import com.ss.android.download.api.model.DownloadShortInfo;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.addownload.DownloadHelper;
import com.ss.android.downloadlib.addownload.compliance.AdLpComplianceManager;
import com.ss.android.downloadlib.addownload.model.DownloadInstallInfo;
import com.ss.android.downloadlib.addownload.model.ModelBox;
import com.ss.android.downloadlib.addownload.model.ModelManager;
import com.ss.android.downloadlib.addownload.model.PackageManagerChecker;
import com.ss.android.downloadlib.addownload.model.SharedPrefsManager;
import com.ss.android.downloadlib.addownload.pause.IPauseCallback;
import com.ss.android.downloadlib.addownload.pause.PauseInterceptorManager;
import com.ss.android.downloadlib.constants.DownloadConstants;
import com.ss.android.downloadlib.constants.DownloadSettingKeys;
import com.ss.android.downloadlib.constants.EventConstants;
import com.ss.android.downloadlib.downloader.NotificationPusher;
import com.ss.android.downloadlib.event.AdEventHandler;
import com.ss.android.downloadlib.exception.TTDownloaderMonitor;
import com.ss.android.downloadlib.utils.AsyncTaskUtils;
import com.ss.android.downloadlib.utils.DownloadSettingUtils;
import com.ss.android.downloadlib.utils.OpenAppUtils;
import com.ss.android.downloadlib.utils.PermissionUtils;
import com.ss.android.downloadlib.utils.TLogger;
import com.ss.android.downloadlib.utils.ToolUtils;
import com.ss.android.downloadlib.utils.WeakHandler;
import com.ss.android.socialbase.appdownloader.AppDownloader;
import com.ss.android.socialbase.appdownloader.DownloadHandlerService;
import com.ss.android.socialbase.appdownloader.constants.Constants;
import com.ss.android.socialbase.appdownloader.depend.IAppDownloadEventHandler;
import com.ss.android.socialbase.downloader.depend.IDownloadListener;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.downloader.DownloadProcessDispatcher;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.notification.DownloadNotificationManager;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import com.umeng.message.MsgConstant;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class CommonDownloadHandler implements DownloadHandler, WeakHandler.IHandler {
    private static final int EMPTY_LISTENER_TOKEN = Integer.MIN_VALUE;
    private static final String TAG = "CommonDownloadHandler";
    private WeakReference<Context> mContextRef;
    private SoftReference<IDownloadButtonClickListener> mDownloadButtonClickListener;
    private DownloadInfo mDownloadInfo;
    private QueryDownloadInfoTaskWithNewDownloader mInfoTaskWithNewDownloader;
    private boolean mIsBind;
    private boolean mIsNormalScene;
    private SoftReference<OnItemClickListener> mItemClickListener;
    private long mLastWorkTime;
    private DownloadShortInfo mTempDownloadShortInfo;
    private final WeakHandler mDownloadInfoChangeHandler = new WeakHandler(Looper.getMainLooper(), this);
    private final Map<Integer, Object> mStatusChangeListenerMap = new ConcurrentHashMap();
    private final IDownloadListener mDownloadListener = new DownloadHelper.AppDownloadListener(this.mDownloadInfoChangeHandler);
    private long mCurrentId = -1;
    private DownloadModel mCurrentDownloadModel = null;
    private DownloadEventConfig mDownloadEventConfig = null;
    private DownloadController mDownloadController = null;
    private DownloadHelper mHelper = new DownloadHelper(this);
    private CleanSpaceHelper mCleanHelper = new CleanSpaceHelper(this.mDownloadInfoChangeHandler);
    private final boolean fixCallbackTwice = DownloadSetting.obtainGlobal().optBugFix(DownloadSettingKeys.BugFix.FIX_TTDOWNLOADER_CALLBACK_TWICE);

    interface DownloadInvoker {
        void invoke();
    }

    interface IFetchApkSizeListener {
        void onFetchEnd(long j);
    }

    @Override // com.ss.android.downloadlib.addownload.DownloadHandler
    public boolean isSupportSilentDownload() {
        return false;
    }

    @Override // com.ss.android.downloadlib.addownload.DownloadHandler
    public CommonDownloadHandler setContext(Context context) {
        if (context != null) {
            this.mContextRef = new WeakReference<>(context);
        }
        GlobalInfo.makeSureContext(context);
        return this;
    }

    @Override // com.ss.android.downloadlib.addownload.DownloadHandler
    public CommonDownloadHandler addStatusChangeListener(int i, DownloadStatusChangeListener downloadStatusChangeListener) {
        if (downloadStatusChangeListener != null) {
            if (GlobalInfo.getDownloadSettings().optInt(DownloadConstants.KEY_BACK_USE_SOFTREF_LISTENER) == 1) {
                this.mStatusChangeListenerMap.put(Integer.valueOf(i), downloadStatusChangeListener);
            } else {
                this.mStatusChangeListenerMap.put(Integer.valueOf(i), new SoftReference(downloadStatusChangeListener));
            }
        }
        return this;
    }

    @Override // com.ss.android.downloadlib.addownload.DownloadHandler
    public CommonDownloadHandler setDownloadModel(DownloadModel downloadModel) throws JSONException {
        if (downloadModel != null) {
            if (downloadModel.isAd()) {
                if (downloadModel.getId() <= 0 || TextUtils.isEmpty(downloadModel.getLogExtra())) {
                    TTDownloaderMonitor.inst().monitorDataError("setDownloadModel ad error");
                }
            } else if (downloadModel.getId() == 0 && (downloadModel instanceof AdDownloadModel)) {
                TTDownloaderMonitor.inst().monitorDataError(false, "setDownloadModel id=0");
                if (DownloadSetting.obtainGlobal().optBugFix(DownloadSettingKeys.BugFix.FIX_MODEL_ID)) {
                    ((AdDownloadModel) downloadModel).setId(downloadModel.getDownloadUrl().hashCode());
                }
            }
            ModelManager.getInstance().addDownloadModel(downloadModel);
            this.mCurrentId = downloadModel.getId();
            this.mCurrentDownloadModel = downloadModel;
            if (DownloadInsideHelper.isRecommendAd(downloadModel)) {
                ((AdDownloadModel) downloadModel).setExtraValue(3L);
                NativeDownloadModel nativeDownloadModel = ModelManager.getInstance().getNativeDownloadModel(this.mCurrentId);
                if (nativeDownloadModel != null && nativeDownloadModel.getExtValue() != 3) {
                    nativeDownloadModel.setExtValue(3L);
                    SharedPrefsManager.getInstance().saveNativeDownloadModel(nativeDownloadModel);
                }
            }
        }
        return this;
    }

    @Override // com.ss.android.downloadlib.addownload.DownloadHandler
    public CommonDownloadHandler setDownloadController(DownloadController downloadController) {
        JSONObject extra;
        this.mDownloadController = downloadController;
        if (DownloadSettingUtils.getSetting(this.mCurrentDownloadModel).optInt(DownloadSettingKeys.KEY_FORCE_AUTO_OPEN) == 1) {
            getDownloadController().setLinkMode(1);
        }
        if (DownloadSetting.obtainGlobal().optBugFix(DownloadSettingKeys.BugFix.FIX_SHOW_DIALOG) && (extra = this.mCurrentDownloadModel.getExtra()) != null && extra.optInt("subprocess") > 0) {
            getDownloadController().setEnableNewActivity(false);
        }
        ModelManager.getInstance().addDownloadController(this.mCurrentId, getDownloadController());
        return this;
    }

    @Override // com.ss.android.downloadlib.addownload.DownloadHandler
    public CommonDownloadHandler setDownloadEventConfig(DownloadEventConfig downloadEventConfig) {
        this.mDownloadEventConfig = downloadEventConfig;
        this.mIsNormalScene = getDownloadEventConfig().getDownloadScene() == 0;
        ModelManager.getInstance().addDownloadEventConfig(this.mCurrentId, getDownloadEventConfig());
        return this;
    }

    @Override // com.ss.android.downloadlib.addownload.DownloadHandler
    public DownloadHandler setOnItemClickListener(OnItemClickListener onItemClickListener) {
        if (onItemClickListener == null) {
            this.mItemClickListener = null;
        } else {
            this.mItemClickListener = new SoftReference<>(onItemClickListener);
        }
        return this;
    }

    @Override // com.ss.android.downloadlib.addownload.DownloadHandler
    public void onBind() {
        this.mIsBind = true;
        ModelManager.getInstance().addDownloadEventConfig(this.mCurrentId, getDownloadEventConfig());
        ModelManager.getInstance().addDownloadController(this.mCurrentId, getDownloadController());
        this.mHelper.setAdId(this.mCurrentId);
        resumeDownload();
        if (GlobalInfo.getDownloadSettings().optInt(DownloadSettingKeys.ENABLE_EMPTY_LISTENER, 1) == 1 && this.mStatusChangeListenerMap.get(Integer.MIN_VALUE) == null) {
            addStatusChangeListener(Integer.MIN_VALUE, (DownloadStatusChangeListener) new AbsDownloadStatusChangeListener());
        }
    }

    @Override // com.ss.android.downloadlib.addownload.DownloadHandler
    public boolean onUnbind(int i) {
        if (i == 0) {
            this.mStatusChangeListenerMap.clear();
        } else {
            this.mStatusChangeListenerMap.remove(Integer.valueOf(i));
        }
        if (this.mStatusChangeListenerMap.isEmpty()) {
            this.mIsBind = false;
            this.mLastWorkTime = System.currentTimeMillis();
            if (this.mDownloadInfo != null) {
                Downloader.getInstance(GlobalInfo.getContext()).removeTaskMainListener(this.mDownloadInfo.getId());
            }
            if (this.mInfoTaskWithNewDownloader != null && this.mInfoTaskWithNewDownloader.getStatus() != AsyncTask.Status.FINISHED) {
                this.mInfoTaskWithNewDownloader.cancel(true);
            }
            this.mHelper.resetData(this.mDownloadInfo);
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onUnbind removeCallbacksAndMessages, downloadUrl:");
            sb.append(this.mDownloadInfo == null ? "" : this.mDownloadInfo.getUrl());
            TLogger.v(str, sb.toString(), null);
            this.mDownloadInfoChangeHandler.removeCallbacksAndMessages(null);
            this.mTempDownloadShortInfo = null;
            this.mDownloadInfo = null;
            return true;
        }
        if (this.mStatusChangeListenerMap.size() == 1 && this.mStatusChangeListenerMap.containsKey(Integer.MIN_VALUE)) {
            this.mHelper.sendClickStartWhenUnbind(this.mDownloadInfo);
        }
        return false;
    }

    @Override // com.ss.android.downloadlib.addownload.DownloadHandler
    public void cancelDownload(boolean z) {
        if (this.mDownloadInfo != null) {
            if (z) {
                IAppDownloadEventHandler appDownloadEventHandler = AppDownloader.getInstance().getAppDownloadEventHandler();
                if (appDownloadEventHandler != null) {
                    appDownloadEventHandler.handleDownloadCancel(this.mDownloadInfo);
                }
                Downloader.getInstance(DownloadComponentManager.getAppContext()).cancel(this.mDownloadInfo.getId(), true);
                return;
            }
            Intent intent = new Intent(GlobalInfo.getContext(), (Class<?>) DownloadHandlerService.class);
            intent.setAction(Constants.ACTION_DELETE);
            intent.putExtra(Constants.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS, this.mDownloadInfo.getId());
            GlobalInfo.getContext().startService(intent);
        }
    }

    @Override // com.ss.android.downloadlib.addownload.DownloadHandler
    public long getDownloadAdId() {
        if (this.mCurrentDownloadModel == null) {
            return -1L;
        }
        return this.mCurrentDownloadModel.getId();
    }

    @Override // com.ss.android.downloadlib.addownload.DownloadHandler
    public boolean isBind() {
        return this.mIsBind;
    }

    @Override // com.ss.android.downloadlib.addownload.DownloadHandler
    public boolean isDownloadStarted() {
        return (this.mDownloadInfo == null || this.mDownloadInfo.getStatus() == 0) ? false : true;
    }

    @Override // com.ss.android.downloadlib.addownload.DownloadHandler
    public long getLastWorkTime() {
        return this.mLastWorkTime;
    }

    @Override // com.ss.android.downloadlib.addownload.DownloadHandler
    public DownloadHandler setModelId(long j) throws JSONException {
        if (j != 0) {
            DownloadModel downloadModel = ModelManager.getInstance().getDownloadModel(j);
            if (downloadModel != null) {
                this.mCurrentDownloadModel = downloadModel;
                this.mCurrentId = j;
                this.mHelper.setAdId(this.mCurrentId);
            }
        } else {
            TTDownloaderMonitor.inst().monitorDataError(false, "setModelId");
        }
        return this;
    }

    @Override // com.ss.android.downloadlib.addownload.DownloadHandler
    public void handleDownload(int i) throws JSONException {
        if (i != 1 && i != 2) {
            throw new IllegalArgumentException("error actionType");
        }
        this.mHelper.setAdId(this.mCurrentId);
        if (!ModelManager.getInstance().getModelBox(this.mCurrentId).isStrictValid()) {
            TTDownloaderMonitor.inst().monitorDataError("handleDownload ModelBox !isStrictValid");
        }
        if (this.mHelper.shouldInterceptClick(getContext(), i, this.mIsNormalScene)) {
            return;
        }
        boolean zTryOpenQuickApp = tryOpenQuickApp(i);
        switch (i) {
            case 1:
                if (zTryOpenQuickApp) {
                    return;
                }
                TLogger.v(TAG, "handleDownload id:" + this.mCurrentId + ",pIC:", null);
                tryPerformItemClick(true);
                return;
            case 2:
                if (zTryOpenQuickApp) {
                    return;
                }
                TLogger.v(TAG, "handleDownload id:" + this.mCurrentId + ",pBC:", null);
                tryPerformButtonClick(true);
                return;
            default:
                return;
        }
    }

    public boolean shouldOpenQuickApp() {
        return GlobalInfo.getDownloadSettings().optInt(DownloadConstants.KEY_QUICK_APP_ENABLE_SWITCH, 0) == 0 && this.mCurrentDownloadModel.getQuickAppModel() != null && !TextUtils.isEmpty(this.mCurrentDownloadModel.getQuickAppModel().getQuickOpenUrl()) && AdQuickAppManager.isDownloadTaskClean(this.mDownloadInfo) && ToolUtils.isIntentAvailable(getContext(), new Intent("android.intent.action.VIEW", Uri.parse(this.mCurrentDownloadModel.getQuickAppModel().getQuickOpenUrl())));
    }

    private boolean tryOpenQuickApp(int i) {
        if (!shouldOpenQuickApp()) {
            return false;
        }
        int i2 = -1;
        String quickOpenUrl = this.mCurrentDownloadModel.getQuickAppModel().getQuickOpenUrl();
        switch (i) {
            case 1:
                i2 = 5;
                break;
            case 2:
                i2 = 4;
                break;
        }
        if (this.mCurrentDownloadModel instanceof AdDownloadModel) {
            ((AdDownloadModel) this.mCurrentDownloadModel).setFunnelType(3);
        }
        boolean zTryOpenByQuickAppUrl = OpenAppUtils.tryOpenByQuickAppUrl(GlobalInfo.getContext(), quickOpenUrl);
        if (zTryOpenByQuickAppUrl) {
            AdEventHandler.getInstance().sendClickEvent(this.mCurrentId, i);
            Message messageObtain = Message.obtain();
            messageObtain.what = i2;
            messageObtain.obj = Long.valueOf(this.mCurrentDownloadModel.getId());
            AdQuickAppManager.getsInstance().check(this, i2, this.mCurrentDownloadModel);
        } else {
            AdEventHandler.getInstance().sendQuickAppEvent(this.mCurrentId, false, 0);
        }
        return zTryOpenByQuickAppUrl;
    }

    public void tryPerformButtonClick(boolean z) {
        performButtonClick(z);
    }

    private void onItemClick() {
        if (this.mItemClickListener != null && this.mItemClickListener.get() != null) {
            this.mItemClickListener.get().onItemClick(this.mCurrentDownloadModel, getDownloadEventConfig(), getDownloadController());
            this.mItemClickListener = null;
        } else {
            GlobalInfo.getDownloadActionListener().onItemClick(getContext(), this.mCurrentDownloadModel, getDownloadController(), getDownloadEventConfig());
        }
    }

    public void tryPerformItemClick(boolean z) {
        if (z) {
            AdEventHandler.getInstance().sendClickEvent(this.mCurrentId, 1);
        }
        performItemClickWithNewDownloader();
    }

    private void performButtonClick(boolean z) {
        if (DownloadSettingUtils.getSetting(this.mCurrentDownloadModel).optInt(com.ss.android.socialbase.appdownloader.setting.DownloadSettingKeys.NOTIFICATION_OPT_2) == 1 && this.mDownloadInfo != null) {
            DownloadNotificationManager.getInstance().cancelNotification(this.mDownloadInfo.getId());
        }
        performButtonClickWithNewDownloader(z);
    }

    public void notifyInstallFinish() {
        this.mDownloadInfoChangeHandler.post(new Runnable() { // from class: com.ss.android.downloadlib.addownload.CommonDownloadHandler.1
            @Override // java.lang.Runnable
            public void run() {
                Iterator<DownloadStatusChangeListener> it = DownloadHelper.getStatusListeners(CommonDownloadHandler.this.mStatusChangeListenerMap).iterator();
                while (it.hasNext()) {
                    it.next().onInstalled(CommonDownloadHandler.this.getTempDownloadShortInfo());
                }
            }
        });
    }

    @Override // com.ss.android.downloadlib.utils.WeakHandler.IHandler
    public void handleMsg(Message message) {
        if (message != null && this.mIsBind && message.what == 3) {
            this.mDownloadInfo = (DownloadInfo) message.obj;
            this.mHelper.handleUiChangeMessage(message, getTempDownloadShortInfo(), this.mStatusChangeListenerMap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Context getContext() {
        if (this.mContextRef != null && this.mContextRef.get() != null) {
            return this.mContextRef.get();
        }
        return GlobalInfo.getContext();
    }

    @NonNull
    private DownloadEventConfig getDownloadEventConfig() {
        return this.mDownloadEventConfig == null ? new SimpleDownloadEventConfig.Builder().build() : this.mDownloadEventConfig;
    }

    @NonNull
    private DownloadController getDownloadController() {
        if (this.mDownloadController == null) {
            this.mDownloadController = new SimpleDownloadController();
        }
        return this.mDownloadController;
    }

    private void performItemClickWithNewDownloader() {
        TLogger.v(TAG, "pICD", null);
        if (this.mHelper.shouldInstallOrOpenApp(this.mDownloadInfo)) {
            TLogger.v(TAG, "pICD BC", null);
            performButtonClickWithNewDownloader(false);
        } else {
            TLogger.v(TAG, "pICD IC", null);
            onItemClick();
        }
    }

    private void performButtonClickWithNewDownloader(final boolean z) {
        TLogger.v(TAG, "pBCD", null);
        if (needBeginDownload()) {
            ModelBox modelBox = ModelManager.getInstance().getModelBox(this.mCurrentId);
            if (this.mDownloadInfo != null && this.mDownloadInfo.getStatus() != 0) {
                innerStartDownload(z, true);
                return;
            }
            if (this.mIsNormalScene) {
                if (this.mCurrentDownloadModel.isAd() && this.mDownloadButtonClickListener != null) {
                    if (downloadButtonClickCallback() && modelBox.controller != null && modelBox.controller.isAutoDownloadOnCardShow()) {
                        innerStartDownload(z, true);
                        return;
                    }
                    return;
                }
                innerStartDownload(z, true);
                return;
            }
            if (this.mCurrentDownloadModel.isAd() && modelBox.controller != null && modelBox.controller.enableShowComplianceDialog() && modelBox.model != null && AdLpComplianceManager.getInstance().shouldShowDialog(modelBox.model) && AdLpComplianceManager.getInstance().requestAppInfo(modelBox)) {
                return;
            }
            innerStartDownload(z, true);
            return;
        }
        TLogger.v(TAG, "pBCD continue download, status:" + this.mDownloadInfo.getStatus(), null);
        if (this.mDownloadInfo != null && this.mCurrentDownloadModel != null) {
            this.mDownloadInfo.setOnlyWifi(this.mCurrentDownloadModel.isNeedWifi());
        }
        final int status = this.mDownloadInfo.getStatus();
        final int id = this.mDownloadInfo.getId();
        final NativeDownloadModel nativeModelByInfo = ModelManager.getInstance().getNativeModelByInfo(this.mDownloadInfo);
        if (status == -2 || status == -1) {
            this.mHelper.sendEventWithNewDownloader(this.mDownloadInfo, z);
            if (nativeModelByInfo != null) {
                nativeModelByInfo.setClickDownloadTime(System.currentTimeMillis());
                nativeModelByInfo.setClickDownloadSize(this.mDownloadInfo.getCurBytes());
            }
            this.mDownloadInfo.setDownloadFromReserveWifi(false);
            this.mCleanHelper.setModelBox(new ModelBox(this.mCurrentId, this.mCurrentDownloadModel, getDownloadEventConfig(), getDownloadController()));
            this.mCleanHelper.handleSpaceBeforeDownload(id, this.mDownloadInfo.getCurBytes(), this.mDownloadInfo.getTotalBytes(), new DownloadInvoker() { // from class: com.ss.android.downloadlib.addownload.CommonDownloadHandler.2
                @Override // com.ss.android.downloadlib.addownload.CommonDownloadHandler.DownloadInvoker
                public void invoke() {
                    if (CommonDownloadHandler.this.mCleanHelper.isDownloadActionCanceled()) {
                        return;
                    }
                    CommonDownloadHandler.this.handleStatusClick(id, status, CommonDownloadHandler.this.mDownloadInfo);
                }
            });
            return;
        }
        if (ReverseWifiHelper.willPause(status)) {
            if (this.mCurrentDownloadModel.enablePause()) {
                this.mCleanHelper.setDownloadActionCanceled(true);
                NotificationPusher.getInstance().delayNotifyContinueDownload(ModelManager.getInstance().getNativeDownloadModel(this.mCurrentId));
                PauseInterceptorManager.getInstance().handlePause(nativeModelByInfo, status, new IPauseCallback() { // from class: com.ss.android.downloadlib.addownload.CommonDownloadHandler.3
                    @Override // com.ss.android.downloadlib.addownload.pause.IPauseCallback
                    public void pause(NativeDownloadModel nativeDownloadModel) throws JSONException {
                        CommonDownloadHandler.this.mHelper.sendEventWithNewDownloader(CommonDownloadHandler.this.mDownloadInfo, z);
                        if (!DownloadUtils.isWifi(GlobalInfo.getContext()) || !CommonDownloadHandler.this.mDownloadInfo.isPauseReserveOnWifi()) {
                            CommonDownloadHandler.this.handleStatusClick(id, status, CommonDownloadHandler.this.mDownloadInfo);
                        } else {
                            CommonDownloadHandler.this.mDownloadInfo.stopPauseReserveOnWifi();
                            AdEventHandler.getInstance().sendEvent(EventConstants.Label.PAUSE_RESERVE_WIFI_CANCEL_ON_WIFI, nativeModelByInfo);
                        }
                    }
                });
                return;
            }
            return;
        }
        this.mHelper.sendEventWithNewDownloader(this.mDownloadInfo, z);
        handleStatusClick(id, status, this.mDownloadInfo);
    }

    public void innerStartDownload(boolean z, final boolean z2) {
        if (z) {
            AdEventHandler.getInstance().sendClickEvent(this.mCurrentId, 2);
        }
        if (!PermissionUtils.hasPermission(MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE) && !getDownloadController().enableNewActivity()) {
            this.mCurrentDownloadModel.setFilePath(this.mHelper.getExternalDownloadsDir());
        }
        if (DownloadSettingUtils.getSavePathType(this.mCurrentDownloadModel) == 0) {
            TLogger.v(TAG, "pBCD not start", null);
            this.mHelper.checkConditionBeforeDownload(new IPermissionCallback() { // from class: com.ss.android.downloadlib.addownload.CommonDownloadHandler.4
                @Override // com.ss.android.download.api.config.IPermissionCallback
                public void onGranted() {
                    TLogger.v(CommonDownloadHandler.TAG, "pBCD start download", null);
                    CommonDownloadHandler.this.download(z2);
                }

                @Override // com.ss.android.download.api.config.IPermissionCallback
                public void onDenied(String str) {
                    TLogger.v(CommonDownloadHandler.TAG, "pBCD onDenied", null);
                }
            });
        } else {
            download(z2);
        }
    }

    private boolean needBeginDownload() {
        if (DownloadSetting.obtainGlobal().optBugFix(DownloadSettingKeys.BugFix.FIX_CLICK_START)) {
            if (this.mDownloadInfo == null) {
                return true;
            }
            if ((this.mDownloadInfo.getStatus() == -3 && this.mDownloadInfo.getCurBytes() <= 0) || this.mDownloadInfo.getStatus() == 0 || this.mDownloadInfo.getStatus() == -4) {
                return true;
            }
            return DownloadUtils.isDownloadSuccessAndFileNotExist(this.mDownloadInfo.getStatus(), this.mDownloadInfo.getSavePath(), this.mDownloadInfo.getName());
        }
        if (this.mDownloadInfo == null) {
            return true;
        }
        return !(this.mDownloadInfo.getStatus() == -3 || Downloader.getInstance(GlobalInfo.getContext()).canResume(this.mDownloadInfo.getId())) || this.mDownloadInfo.getStatus() == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleStatusClick(int i, int i2, @NonNull DownloadInfo downloadInfo) {
        if (DownloadSetting.obtainGlobal().optBugFix(DownloadSettingKeys.BugFix.FIX_CLICK_START)) {
            if (i2 != -3 && !DownloadProcessDispatcher.getInstance().canResume(i)) {
                innerStartDownload(false, false);
                return;
            } else {
                AppDownloader.getInstance().handleStatusClick(GlobalInfo.getContext(), i, i2);
                return;
            }
        }
        AppDownloader.getInstance().handleStatusClick(GlobalInfo.getContext(), i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void download(final boolean z) {
        this.mCleanHelper.setModelBox(new ModelBox(this.mCurrentId, this.mCurrentDownloadModel, getDownloadEventConfig(), getDownloadController()));
        this.mCleanHelper.handleSpaceBeforeDownload(0, 0L, 0L, new DownloadInvoker() { // from class: com.ss.android.downloadlib.addownload.CommonDownloadHandler.5
            @Override // com.ss.android.downloadlib.addownload.CommonDownloadHandler.DownloadInvoker
            public void invoke() throws JSONException {
                if (CommonDownloadHandler.this.mCleanHelper.isDownloadActionCanceled()) {
                    return;
                }
                CommonDownloadHandler.this.beginDownloadWithNewDownloader(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void beginDownloadWithNewDownloader(boolean z) throws JSONException {
        Iterator<DownloadStatusChangeListener> it = DownloadHelper.getStatusListeners(this.mStatusChangeListenerMap).iterator();
        while (it.hasNext()) {
            it.next().onDownloadStart(this.mCurrentDownloadModel, getDownloadController());
        }
        int iStartDownloadWithNewDownloader = this.mHelper.startDownloadWithNewDownloader(GlobalInfo.getContext(), this.mDownloadListener);
        TLogger.v(TAG, "beginDown id:" + iStartDownloadWithNewDownloader, null);
        if (iStartDownloadWithNewDownloader != 0) {
            if (this.mDownloadInfo != null && !DownloadSetting.obtainGlobal().optBugFix(DownloadSettingKeys.BugFix.FIX_CLICK_START)) {
                this.mHelper.sendEventWithNewDownloader(this.mDownloadInfo, false);
            } else if (z) {
                this.mHelper.sendClickStartEventAfterReceivedProgress();
            }
        } else {
            DownloadInfo downloadInfoBuild = new DownloadInfo.Builder(this.mCurrentDownloadModel.getDownloadUrl()).build();
            downloadInfoBuild.setStatus(-1);
            sendUiChangeMessage(downloadInfoBuild);
            AdEventHandler.getInstance().sendDownloadFailedEvent(this.mCurrentId, new BaseException(2, "start download failed, id=0"));
            TTDownloaderMonitor.inst().monitorPathError("beginDown");
        }
        if (this.mHelper.shouldResponseItemClick(isDownloadStarted())) {
            TLogger.v(TAG, "beginDown IC id:" + iStartDownloadWithNewDownloader, null);
            onItemClick();
        }
    }

    public void resetDownloadStatus() {
        if (this.mStatusChangeListenerMap.size() == 0) {
            return;
        }
        Iterator<DownloadStatusChangeListener> it = DownloadHelper.getStatusListeners(this.mStatusChangeListenerMap).iterator();
        while (it.hasNext()) {
            it.next().onIdle();
        }
        if (this.mDownloadInfo != null) {
            this.mDownloadInfo.setStatus(-4);
        }
    }

    private class QueryDownloadInfoTaskWithNewDownloader extends AsyncTask<String, Void, DownloadInfo> {
        private QueryDownloadInfoTaskWithNewDownloader() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public DownloadInfo doInBackground(String... strArr) {
            DownloadInfo downloadInfo = null;
            if (strArr == null || (strArr.length >= 1 && TextUtils.isEmpty(strArr[0]))) {
                return null;
            }
            String str = strArr[0];
            if (CommonDownloadHandler.this.mCurrentDownloadModel != null && !TextUtils.isEmpty(CommonDownloadHandler.this.mCurrentDownloadModel.getFilePath())) {
                downloadInfo = Downloader.getInstance(GlobalInfo.getContext()).getDownloadInfo(str, CommonDownloadHandler.this.mCurrentDownloadModel.getFilePath());
            }
            return downloadInfo == null ? AppDownloader.getInstance().getAppDownloadInfo(GlobalInfo.getContext(), str) : downloadInfo;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(DownloadInfo downloadInfo) {
            super.onPostExecute((QueryDownloadInfoTaskWithNewDownloader) downloadInfo);
            if (isCancelled() || CommonDownloadHandler.this.mCurrentDownloadModel == null) {
                return;
            }
            try {
                DownloadInstallInfo installedAppInfo = ToolUtils.getInstalledAppInfo(CommonDownloadHandler.this.mCurrentDownloadModel.getPackageName(), CommonDownloadHandler.this.mCurrentDownloadModel.getVersionCode(), CommonDownloadHandler.this.mCurrentDownloadModel.getVersionName());
                PackageManagerChecker.getInstance().checkVersionCode(CommonDownloadHandler.this.mCurrentDownloadModel.getVersionCode(), installedAppInfo.getVersionCode(), ModelManager.getInstance().getNativeModelByInfo(downloadInfo));
                boolean zIsInstall = installedAppInfo.isInstall();
                if (downloadInfo != null && downloadInfo.getId() != 0 && (zIsInstall || !Downloader.getInstance(GlobalInfo.getContext()).isDownloadSuccessAndFileNotExist(downloadInfo))) {
                    Downloader.getInstance(GlobalInfo.getContext()).removeTaskMainListener(downloadInfo.getId());
                    if (CommonDownloadHandler.this.mDownloadInfo == null || CommonDownloadHandler.this.mDownloadInfo.getStatus() != -4) {
                        CommonDownloadHandler.this.mDownloadInfo = downloadInfo;
                        if (CommonDownloadHandler.this.fixCallbackTwice) {
                            Downloader.getInstance(GlobalInfo.getContext()).setMainThreadListener(CommonDownloadHandler.this.mDownloadInfo.getId(), CommonDownloadHandler.this.mDownloadListener, false);
                        } else {
                            Downloader.getInstance(GlobalInfo.getContext()).setMainThreadListener(CommonDownloadHandler.this.mDownloadInfo.getId(), CommonDownloadHandler.this.mDownloadListener);
                        }
                    } else {
                        CommonDownloadHandler.this.mDownloadInfo = null;
                    }
                    CommonDownloadHandler.this.mHelper.refreshUIFromDownloadInfo(CommonDownloadHandler.this.mDownloadInfo, CommonDownloadHandler.this.getTempDownloadShortInfo(), DownloadHelper.getStatusListeners(CommonDownloadHandler.this.mStatusChangeListenerMap));
                } else {
                    if (downloadInfo != null && Downloader.getInstance(GlobalInfo.getContext()).isDownloadSuccessAndFileNotExist(downloadInfo)) {
                        DownloadNotificationManager.getInstance().cancelNotification(downloadInfo.getId());
                        CommonDownloadHandler.this.mDownloadInfo = null;
                    }
                    if (CommonDownloadHandler.this.mDownloadInfo != null) {
                        Downloader.getInstance(GlobalInfo.getContext()).removeTaskMainListener(CommonDownloadHandler.this.mDownloadInfo.getId());
                        if (CommonDownloadHandler.this.fixCallbackTwice) {
                            Downloader.getInstance(CommonDownloadHandler.this.getContext()).setMainThreadListener(CommonDownloadHandler.this.mDownloadInfo.getId(), CommonDownloadHandler.this.mDownloadListener, false);
                        } else {
                            Downloader.getInstance(CommonDownloadHandler.this.getContext()).setMainThreadListener(CommonDownloadHandler.this.mDownloadInfo.getId(), CommonDownloadHandler.this.mDownloadListener);
                        }
                    }
                    if (!zIsInstall) {
                        Iterator<DownloadStatusChangeListener> it = DownloadHelper.getStatusListeners(CommonDownloadHandler.this.mStatusChangeListenerMap).iterator();
                        while (it.hasNext()) {
                            it.next().onIdle();
                        }
                        CommonDownloadHandler.this.mDownloadInfo = null;
                    } else {
                        CommonDownloadHandler.this.mDownloadInfo = new DownloadInfo.Builder(CommonDownloadHandler.this.mCurrentDownloadModel.getDownloadUrl()).build();
                        CommonDownloadHandler.this.mDownloadInfo.setStatus(-3);
                        CommonDownloadHandler.this.mHelper.refreshUIFromDownloadInfo(CommonDownloadHandler.this.mDownloadInfo, CommonDownloadHandler.this.getTempDownloadShortInfo(), DownloadHelper.getStatusListeners(CommonDownloadHandler.this.mStatusChangeListenerMap));
                    }
                }
                CommonDownloadHandler.this.mHelper.sendEventIfRecommend(CommonDownloadHandler.this.mDownloadInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void sendUiChangeMessage(DownloadInfo downloadInfo) {
        Message messageObtain = Message.obtain();
        messageObtain.what = 3;
        messageObtain.obj = downloadInfo;
        this.mDownloadInfoChangeHandler.sendMessage(messageObtain);
    }

    private void resumeDownload() {
        if (this.mInfoTaskWithNewDownloader != null && this.mInfoTaskWithNewDownloader.getStatus() != AsyncTask.Status.FINISHED) {
            this.mInfoTaskWithNewDownloader.cancel(true);
        }
        this.mInfoTaskWithNewDownloader = new QueryDownloadInfoTaskWithNewDownloader();
        AsyncTaskUtils.executeAsyncTask(this.mInfoTaskWithNewDownloader, this.mCurrentDownloadModel.getDownloadUrl(), this.mCurrentDownloadModel.getPackageName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DownloadShortInfo getTempDownloadShortInfo() {
        if (this.mTempDownloadShortInfo == null) {
            this.mTempDownloadShortInfo = new DownloadShortInfo();
        }
        return this.mTempDownloadShortInfo;
    }

    @Override // com.ss.android.downloadlib.addownload.DownloadHandler
    public void resetHandlerInfo() {
        ModelManager.getInstance().removeMemoryCaches(this.mCurrentId);
    }

    @Override // com.ss.android.downloadlib.addownload.DownloadHandler
    public DownloadHandler setDownloadButtonClickListener(IDownloadButtonClickListener iDownloadButtonClickListener) {
        if (iDownloadButtonClickListener == null) {
            this.mDownloadButtonClickListener = null;
        } else {
            this.mDownloadButtonClickListener = new SoftReference<>(iDownloadButtonClickListener);
        }
        return this;
    }

    private boolean downloadButtonClickCallback() throws JSONException {
        if (this.mDownloadButtonClickListener != null && this.mDownloadButtonClickListener.get() != null) {
            this.mDownloadButtonClickListener.get().handleComplianceDialog(true);
            this.mDownloadButtonClickListener = null;
            return true;
        }
        TTDownloaderMonitor.inst().monitorPathError("mDownloadButtonClickListener has recycled");
        return false;
    }
}
