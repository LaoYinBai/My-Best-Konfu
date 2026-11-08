package com.ss.android.downloadlib.addownload;

import android.text.TextUtils;
import com.ss.android.download.api.download.DownloadEventConfig;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.downloadad.api.download.AdDownloadController;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.downloadlib.DownloadDispatcher;
import com.ss.android.downloadlib.addownload.model.ModelManager;
import com.ss.android.downloadlib.applink.AppLinkEventCallback;
import com.ss.android.downloadlib.applink.AppLinkMonitor;
import com.ss.android.downloadlib.constants.DownloadConstants;
import com.ss.android.downloadlib.event.AdEventHandler;
import com.ss.android.downloadlib.utils.OpenAppUtils;
import com.ss.android.downloadlib.utils.TLogger;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AdQuickAppManager {
    public static final long DEFAULT_QUICK_APP_CHECK_TIME = 1200;
    private static String TAG = "AdQuickAppManager";
    private static volatile AdQuickAppManager sInstance;
    private ConcurrentHashMap<Long, Runnable> mQuickAppRunnableMap;

    public AdQuickAppManager() {
        this.mQuickAppRunnableMap = null;
        this.mQuickAppRunnableMap = new ConcurrentHashMap<>();
    }

    public static AdQuickAppManager getsInstance() {
        if (sInstance == null) {
            synchronized (AdQuickAppManager.class) {
                if (sInstance == null) {
                    sInstance = new AdQuickAppManager();
                }
            }
        }
        return sInstance;
    }

    public void checkResult(CommonDownloadHandler commonDownloadHandler, boolean z, int i, DownloadModel downloadModel) {
        if (downloadModel instanceof AdDownloadModel) {
            ((AdDownloadModel) downloadModel).setFunnelType(3);
        }
        long id = downloadModel.getId();
        if (i == 7) {
            Runnable runnableRemove = this.mQuickAppRunnableMap.remove(Long.valueOf(id));
            if (z) {
                AdEventHandler.getInstance().sendClickEvent(id, 1);
                sendQuickAppEvent(id, true, 1);
                return;
            } else {
                if (runnableRemove != null) {
                    DownloadDispatcher.getInstance().getMainHandler().post(runnableRemove);
                }
                sendQuickAppEvent(id, false, 1);
            }
        }
        switch (i) {
            case 4:
                if (!z) {
                    sendQuickAppEvent(id, false, 2);
                    commonDownloadHandler.tryPerformButtonClick(false);
                    break;
                } else {
                    sendQuickAppEvent(id, true, 2);
                    break;
                }
            case 5:
                if (!z) {
                    sendQuickAppEvent(id, false, 1);
                    commonDownloadHandler.tryPerformItemClick(false);
                    break;
                } else {
                    sendQuickAppEvent(id, true, 1);
                    break;
                }
        }
    }

    private void sendQuickAppEvent(long j, boolean z, int i) {
        AdEventHandler.getInstance().sendQuickAppEvent(j, z, i);
        if (z) {
            GlobalInfo.getOpenAppListener().onOpenApp(null, null, null, null, null, 3);
        }
    }

    public void check(final CommonDownloadHandler commonDownloadHandler, final int i, final DownloadModel downloadModel) {
        AppLinkMonitor.getInstance().checkAppLinkResult(new AppLinkEventCallback() { // from class: com.ss.android.downloadlib.addownload.AdQuickAppManager.1
            @Override // com.ss.android.downloadlib.applink.AppLinkEventCallback
            public void onResult(boolean z) {
                AdQuickAppManager.this.checkResult(commonDownloadHandler, z, i, downloadModel);
            }
        }, getQuickAppCheckTime());
    }

    public void bindQuickApp(DownloadModel downloadModel, DownloadEventConfig downloadEventConfig, Runnable runnable) {
        TLogger.v(TAG, "bindQuickApp start", null);
        if (downloadModel == null || downloadModel.getQuickAppModel() == null || TextUtils.isEmpty(downloadModel.getQuickAppModel().getQuickOpenUrl())) {
            return;
        }
        OpenAppUtils.tryOpenByQuickAppUrl(GlobalInfo.getContext(), downloadModel.getQuickAppModel().getQuickOpenUrl());
        TLogger.v(TAG, "bindQuickApp next", null);
        this.mQuickAppRunnableMap.put(Long.valueOf(downloadModel.getId()), runnable);
        ModelManager.getInstance().addDownloadModel(downloadModel);
        ModelManager.getInstance().addDownloadEventConfig(downloadModel.getId(), downloadEventConfig);
        ModelManager.getInstance().addDownloadController(downloadModel.getId(), new AdDownloadController.Builder().build());
        check(null, 7, downloadModel);
    }

    public void unBindQuickApp(long j) {
        TLogger.v(TAG, "unBindQuickApp start", null);
        TLogger.v(TAG, "unBindQuickApp next", null);
        if (this.mQuickAppRunnableMap != null) {
            this.mQuickAppRunnableMap.remove(Long.valueOf(j));
        }
    }

    public long getQuickAppCheckTime() {
        return GlobalInfo.getDownloadSettings().optLong(DownloadConstants.KEY_QUICK_APP_CHECK_INTERNAL, DEFAULT_QUICK_APP_CHECK_TIME);
    }

    public static boolean isDownloadTaskClean(DownloadInfo downloadInfo) {
        return downloadInfo == null || downloadInfo.getStatus() == 0 || downloadInfo.getStatus() == -4;
    }
}
