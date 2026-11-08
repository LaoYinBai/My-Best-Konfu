package com.ss.android.downloadlib;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.MainThread;
import com.ss.android.download.api.DownloadConfigure;
import com.ss.android.download.api.ITTDownloader;
import com.ss.android.download.api.PreDownloadManager;
import com.ss.android.download.api.config.IDownloadButtonClickListener;
import com.ss.android.download.api.config.OnItemClickListener;
import com.ss.android.download.api.download.DownloadController;
import com.ss.android.download.api.download.DownloadEventConfig;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.download.api.download.DownloadStatusChangeListener;
import com.ss.android.download.api.download.extend.DownloadCompletedListener;
import com.ss.android.downloadad.api.AdDownloadCompletedEventHandler;
import com.ss.android.downloadad.api.AdWebViewDownloadManager;
import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.addownload.AdQuickAppManager;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.addownload.ReserveWifiStatusImpl;
import com.ss.android.downloadlib.addownload.dialog.AdDownloadDialogManager;
import com.ss.android.downloadlib.addownload.manager.DownloadInstallHelper;
import com.ss.android.downloadlib.addownload.model.DownloadInstallInfo;
import com.ss.android.downloadlib.addownload.model.ModelManager;
import com.ss.android.downloadlib.constants.DownloadConstants;
import com.ss.android.downloadlib.constants.DownloadSettingKeys;
import com.ss.android.downloadlib.downloader.AppDownloadLaunchResumeListener;
import com.ss.android.downloadlib.downloader.InnerEventListenerImpl;
import com.ss.android.downloadlib.downloader.NewDownloadCompletedEventDispatcher;
import com.ss.android.downloadlib.downloader.NewDownloadDepend;
import com.ss.android.downloadlib.exception.Safe;
import com.ss.android.downloadlib.install.BeforeAppInstallInterceptorManager;
import com.ss.android.downloadlib.scheme.SchemeListHelper;
import com.ss.android.downloadlib.utils.AsyncTaskUtils;
import com.ss.android.downloadlib.utils.ToolUtils;
import com.ss.android.socialbase.appdownloader.AppDownloader;
import com.ss.android.socialbase.downloader.constants.DownloadStatus;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TTDownloader implements ITTDownloader {
    private static volatile TTDownloader sInstance;
    private final AdDownloadCompletedEventHandler mAdDownloadCompletedEventHandler;
    private AdWebViewDownloadManager mAdWebViewDownloadManager;
    private final DownloadConfigure mDownloadConfigure;
    private final DownloadDispatcher mDownloadDispatcher;
    private long mLastActiveTimpstamp;
    private PreDownloadManager mPreDownloadManager;

    public static TTDownloader inst(final Context context) {
        if (sInstance == null) {
            synchronized (TTDownloader.class) {
                if (sInstance == null) {
                    Safe.noCatch(new Runnable() { // from class: com.ss.android.downloadlib.TTDownloader.1
                        @Override // java.lang.Runnable
                        public void run() {
                            TTDownloader unused = TTDownloader.sInstance = new TTDownloader(context);
                        }
                    });
                }
            }
        }
        return sInstance;
    }

    private TTDownloader(Context context) {
        this.mDownloadDispatcher = DownloadDispatcher.getInstance();
        this.mDownloadConfigure = new DownloadConfigureImpl();
        this.mLastActiveTimpstamp = System.currentTimeMillis();
        init(context);
        this.mAdDownloadCompletedEventHandler = AdDownloadCompletedEventHandlerImpl.getInstance();
    }

    private void init(Context context) {
        GlobalInfo.setContext(context);
        Downloader.getInstance(GlobalInfo.getContext());
        ModelManager.getInstance().init();
        AppDownloader.getInstance().init(GlobalInfo.getContext(), DownloadConstants.SP_NAME_MISC_CONFIG, new NewDownloadDepend(), new NewDownloadCompletedEventDispatcher(context), new BaseDownloadMonitorListener());
        AppDownloadLaunchResumeListener appDownloadLaunchResumeListener = new AppDownloadLaunchResumeListener();
        AppDownloader.getInstance().setAppDownloadLaunchResumeListener(appDownloadLaunchResumeListener);
        Downloader.getInstance(context).registerDownloadCacheSyncListener(appDownloadLaunchResumeListener);
        AppDownloader.getInstance().setReserveWifiStatusListener(new ReserveWifiStatusImpl());
        com.ss.android.socialbase.downloader.downloader.DownloadComponentManager.setDownloadEventListener(new InnerEventListenerImpl());
        AppDownloader.getInstance().setBeforeAppInstallInterceptor(BeforeAppInstallInterceptorManager.getInstance());
    }

    @Override // com.ss.android.download.api.ITTDownloader
    public DownloadConfigure getDownloadConfigure() {
        return this.mDownloadConfigure;
    }

    @Deprecated
    public PreDownloadManager getPreDownloadManager() {
        if (this.mPreDownloadManager == null) {
            this.mPreDownloadManager = PreDownloadMangerImpl.getInstance();
        }
        return this.mPreDownloadManager;
    }

    public OrderDownloader getOrderDownloader() {
        return OrderDownloader.getInstance();
    }

    public AdDownloadDialogManager getDialogManager() {
        return AdDownloadDialogManager.Instance();
    }

    public DataProvider getDataProvider() {
        return DataProvider.getInstance();
    }

    public long getLastActiveTimpstamp() {
        return this.mLastActiveTimpstamp;
    }

    public void updateLastActiveTimpstamp() {
        this.mLastActiveTimpstamp = System.currentTimeMillis();
    }

    @Override // com.ss.android.download.api.ITTDownloader
    public AdDownloadCompletedEventHandler getAdDownloadCompletedEventHandler() {
        return this.mAdDownloadCompletedEventHandler;
    }

    @Override // com.ss.android.download.api.ITTDownloader
    public AdWebViewDownloadManager getAdWebViewDownloadManager() {
        if (this.mAdWebViewDownloadManager == null) {
            this.mAdWebViewDownloadManager = AdWebViewDownloadManagerImpl.inst();
        }
        return this.mAdWebViewDownloadManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DownloadDispatcher getDownloadDispatcher() {
        return this.mDownloadDispatcher;
    }

    @Override // com.ss.android.download.api.ITTDownloader
    @MainThread
    public void bind(final Context context, final int i, final DownloadStatusChangeListener downloadStatusChangeListener, final DownloadModel downloadModel) {
        Safe.noCatch(new Runnable() { // from class: com.ss.android.downloadlib.TTDownloader.2
            @Override // java.lang.Runnable
            public void run() {
                TTDownloader.this.getDownloadDispatcher().bind(context, i, downloadStatusChangeListener, downloadModel);
            }
        });
    }

    @Override // com.ss.android.download.api.ITTDownloader
    @MainThread
    public void bind(final int i, final DownloadStatusChangeListener downloadStatusChangeListener, final DownloadModel downloadModel) {
        Safe.noCatch(new Runnable() { // from class: com.ss.android.downloadlib.TTDownloader.3
            @Override // java.lang.Runnable
            public void run() {
                TTDownloader.this.getDownloadDispatcher().bind(null, i, downloadStatusChangeListener, downloadModel);
            }
        });
    }

    @Override // com.ss.android.download.api.ITTDownloader
    @MainThread
    public void action(final String str, final long j, final int i, final DownloadEventConfig downloadEventConfig, final DownloadController downloadController, final OnItemClickListener onItemClickListener, final IDownloadButtonClickListener iDownloadButtonClickListener) {
        Safe.noCatch(new Runnable() { // from class: com.ss.android.downloadlib.TTDownloader.4
            @Override // java.lang.Runnable
            public void run() {
                TTDownloader.this.getDownloadDispatcher().action(str, j, i, downloadEventConfig, downloadController, onItemClickListener, iDownloadButtonClickListener);
            }
        });
    }

    @Override // com.ss.android.download.api.ITTDownloader
    @MainThread
    public void action(final String str, final long j, final int i, final DownloadEventConfig downloadEventConfig, final DownloadController downloadController) {
        Safe.noCatch(new Runnable() { // from class: com.ss.android.downloadlib.TTDownloader.5
            @Override // java.lang.Runnable
            public void run() {
                TTDownloader.this.getDownloadDispatcher().action(str, j, i, downloadEventConfig, downloadController);
            }
        });
    }

    @Override // com.ss.android.download.api.ITTDownloader
    @MainThread
    public void action(final String str, final long j, final int i) {
        Safe.noCatch(new Runnable() { // from class: com.ss.android.downloadlib.TTDownloader.6
            @Override // java.lang.Runnable
            public void run() {
                TTDownloader.this.getDownloadDispatcher().action(str, j, i);
            }
        });
    }

    @Override // com.ss.android.download.api.ITTDownloader
    @MainThread
    @Deprecated
    public void action(final String str, final int i, final DownloadEventConfig downloadEventConfig) {
        Safe.noCatch(new Runnable() { // from class: com.ss.android.downloadlib.TTDownloader.7
            @Override // java.lang.Runnable
            public void run() {
                TTDownloader.this.action(str, 0L, i, downloadEventConfig, null);
            }
        });
    }

    @Override // com.ss.android.download.api.ITTDownloader
    @MainThread
    public void action(final String str, final long j, final int i, final DownloadEventConfig downloadEventConfig, final DownloadController downloadController, final DownloadModel downloadModel) {
        Safe.noCatch(new Runnable() { // from class: com.ss.android.downloadlib.TTDownloader.8
            @Override // java.lang.Runnable
            public void run() {
                TTDownloader.this.getDownloadDispatcher().action(str, j, i, downloadEventConfig, downloadController, downloadModel);
            }
        });
    }

    @Override // com.ss.android.download.api.ITTDownloader
    @MainThread
    public void unbind(final String str, final int i) {
        Safe.noCatch(new Runnable() { // from class: com.ss.android.downloadlib.TTDownloader.9
            @Override // java.lang.Runnable
            public void run() {
                TTDownloader.this.getDownloadDispatcher().unbind(str, i);
            }
        });
    }

    @Override // com.ss.android.download.api.ITTDownloader
    @MainThread
    public void cancel(final String str, final boolean z) {
        Safe.noCatch(new Runnable() { // from class: com.ss.android.downloadlib.TTDownloader.10
            @Override // java.lang.Runnable
            public void run() {
                TTDownloader.this.getDownloadDispatcher().cancel(str, z);
            }
        });
    }

    @Override // com.ss.android.download.api.ITTDownloader
    @MainThread
    public void cancel(final String str) {
        Safe.noCatch(new Runnable() { // from class: com.ss.android.downloadlib.TTDownloader.11
            @Override // java.lang.Runnable
            public void run() {
                TTDownloader.this.getDownloadDispatcher().cancel(str);
            }
        });
    }

    @Override // com.ss.android.download.api.ITTDownloader
    public boolean isStarted(String str) {
        return getDownloadDispatcher().isStarted(str);
    }

    @Override // com.ss.android.download.api.ITTDownloader
    public void addDownloadCompletedListener(DownloadCompletedListener downloadCompletedListener) {
        getDownloadDispatcher().addDownloadCompletedListener(downloadCompletedListener);
    }

    @Override // com.ss.android.download.api.ITTDownloader
    public void removeDownloadCompletedListener(DownloadCompletedListener downloadCompletedListener) {
        getDownloadDispatcher().removeDownloadCompletedListener(downloadCompletedListener);
    }

    public List<DownloadModel> getDownloadingTask() {
        List<DownloadInfo> downloadingDownloadInfosWithMimeType;
        NativeDownloadModel nativeModelByInfo;
        ArrayList arrayList = new ArrayList();
        Context context = GlobalInfo.getContext();
        if (context == null || (downloadingDownloadInfosWithMimeType = AppDownloader.getInstance().getDownloadingDownloadInfosWithMimeType(context)) == null || downloadingDownloadInfosWithMimeType.size() == 0) {
            return arrayList;
        }
        for (DownloadInfo downloadInfo : downloadingDownloadInfosWithMimeType) {
            if (DownloadStatus.isDownloading(downloadInfo.getStatus()) && (nativeModelByInfo = ModelManager.getInstance().getNativeModelByInfo(downloadInfo)) != null) {
                arrayList.add(nativeModelByInfo.generateDownloadModel());
            }
        }
        return arrayList;
    }

    public List<DownloadModel> getDownloadPauseTask() {
        List<DownloadInfo> unCompletedDownloadInfosWithMimeType;
        NativeDownloadModel nativeModelByInfo;
        ArrayList arrayList = new ArrayList();
        if (GlobalInfo.getContext() == null || (unCompletedDownloadInfosWithMimeType = Downloader.getInstance(GlobalInfo.getContext()).getUnCompletedDownloadInfosWithMimeType(com.ss.android.socialbase.downloader.constants.DownloadConstants.MIME_APK)) == null || unCompletedDownloadInfosWithMimeType.size() == 0) {
            return arrayList;
        }
        for (DownloadInfo downloadInfo : unCompletedDownloadInfosWithMimeType) {
            if (-2 == downloadInfo.getStatus() && (nativeModelByInfo = ModelManager.getInstance().getNativeModelByInfo(downloadInfo)) != null) {
                arrayList.add(nativeModelByInfo.generateDownloadModel());
            }
        }
        return arrayList;
    }

    public void getDownloadInstallStatus(List<DownloadInstallInfo> list, DownloadInstallHelper.DownloadInstallCallback downloadInstallCallback) {
        AsyncTaskUtils.executeAsyncTask(new DownloadInstallHelper(list, downloadInstallCallback), new Void[0]);
    }

    public void bindQuickApp(DownloadModel downloadModel, DownloadEventConfig downloadEventConfig, Runnable runnable) {
        AdQuickAppManager.getsInstance().bindQuickApp(downloadModel, downloadEventConfig, runnable);
    }

    public void unBindQuickApp(long j) {
        AdQuickAppManager.getsInstance().unBindQuickApp(j);
    }

    public String getSDKVersion() {
        return GlobalInfo.getSdkVersion();
    }

    public void destroy() {
        DownloadComponentManager.getInstance().destroyComponents();
    }

    public void clearAllData() {
        DownloadComponentManager.getInstance().clearAllData();
    }

    @Override // com.ss.android.download.api.ITTDownloader
    public DownloadInfo getDownloadInfo(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return AppDownloader.getInstance().getAppDownloadInfo(GlobalInfo.getContext(), str);
    }

    @Override // com.ss.android.download.api.ITTDownloader
    public DownloadInfo getDownloadInfo(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return getDownloadInfo(str);
        }
        return Downloader.getInstance(GlobalInfo.getContext()).getDownloadInfo(str, str2);
    }

    public JSONObject getDownloadModelInfo() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        long externalAvailableSpaceBytes = ToolUtils.getExternalAvailableSpaceBytes(0L) / com.ss.android.socialbase.downloader.constants.DownloadConstants.KB;
        int iOptInt = GlobalInfo.getDownloadSettings().optInt(DownloadSettingKeys.KEY_REPORT_DOWNLOAD_INFO_MIN_AVAILABLE_SIZE, 0);
        if (iOptInt > 0 && externalAvailableSpaceBytes / com.ss.android.socialbase.downloader.constants.DownloadConstants.KB >= iOptInt) {
            return jSONObject;
        }
        try {
            jSONObject.putOpt("space_unoccupied", Long.valueOf(externalAvailableSpaceBytes));
            if (GlobalInfo.getCleanManager() != null) {
                jSONObject.putOpt("space_cleanable", Long.valueOf(GlobalInfo.getCleanManager().getLastScanSpaceSize() / com.ss.android.socialbase.downloader.constants.DownloadConstants.KB));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public SchemeListHelper getSchemeListHelper() {
        return SchemeListHelper.inst();
    }
}
