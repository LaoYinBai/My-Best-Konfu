package com.ss.android.downloadlib;

import android.content.SharedPreferences;
import android.util.SparseArray;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.constants.DownloadConstants;
import com.ss.android.downloadlib.utils.ToolUtils;
import com.ss.android.socialbase.appdownloader.AppDownloader;
import com.ss.android.socialbase.appdownloader.constants.Constants;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.downloader.IDownloadCache;
import com.ss.android.socialbase.downloader.impls.DefaultDownloadCache;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.thread.DefaultThreadFactory;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DownloadComponentManager {
    private ExecutorService cpuExecutor;
    private ExecutorService ioExecutor;
    private ScheduledExecutorService scheduledExecutor;

    private static class SingleTonHolder {
        private static DownloadComponentManager INSTANCE = new DownloadComponentManager();

        private SingleTonHolder() {
        }
    }

    public static DownloadComponentManager getInstance() {
        return SingleTonHolder.INSTANCE;
    }

    private DownloadComponentManager() {
    }

    public void submitCPUTask(Runnable runnable) {
        submitCPUTask(runnable, false);
    }

    public void submitIOTask(Runnable runnable) {
        submitIOTask(runnable, false);
    }

    public void submitCPUTask(Runnable runnable, boolean z) {
        if (runnable == null) {
            return;
        }
        if (z && !ToolUtils.isMainThread()) {
            runnable.run();
        } else {
            getCPUExecutor().execute(runnable);
        }
    }

    public void submitIOTask(Runnable runnable, boolean z) {
        if (runnable == null) {
            return;
        }
        if (z && !ToolUtils.isMainThread()) {
            runnable.run();
        } else {
            getIOExecutor().execute(runnable);
        }
    }

    public void submitScheduledTask(Runnable runnable, long j) {
        try {
            getScheduledExecutor().schedule(runnable, j, TimeUnit.MILLISECONDS);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setCPUExecutor(ExecutorService executorService) {
        this.cpuExecutor = executorService;
    }

    public void setIOExecutor(ExecutorService executorService) {
        this.ioExecutor = executorService;
    }

    public void setScheduledExecutor(ScheduledExecutorService scheduledExecutorService) {
        this.scheduledExecutor = scheduledExecutorService;
    }

    public ExecutorService getCPUExecutor() {
        if (this.cpuExecutor == null) {
            synchronized (DownloadComponentManager.class) {
                if (this.cpuExecutor == null) {
                    this.cpuExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 30L, TimeUnit.SECONDS, new SynchronousQueue(), new DefaultThreadFactory(TTDownloader.class.getName() + "-CPUThreadPool"));
                }
            }
        }
        return this.cpuExecutor;
    }

    public ExecutorService getIOExecutor() {
        if (this.ioExecutor == null) {
            synchronized (DownloadComponentManager.class) {
                if (this.ioExecutor == null) {
                    this.ioExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 30L, TimeUnit.SECONDS, new SynchronousQueue(), new DefaultThreadFactory(TTDownloader.class.getName() + "-IOThreadPool"));
                }
            }
        }
        return this.ioExecutor;
    }

    public ScheduledExecutorService getScheduledExecutor() {
        if (this.scheduledExecutor == null) {
            synchronized (DownloadComponentManager.class) {
                if (this.scheduledExecutor == null) {
                    this.scheduledExecutor = new ScheduledThreadPoolExecutor(0, new DefaultThreadFactory(TTDownloader.class.getName() + "-ScheduledThreadPool"));
                }
            }
        }
        return this.scheduledExecutor;
    }

    public void destroyComponents() {
        AppDownloader.getInstance().unRegisterDownloadReceiver();
        Downloader.getInstance(GlobalInfo.getContext()).destoryDownloader();
    }

    public void clearAllData() {
        submitCPUTask(new Runnable() { // from class: com.ss.android.downloadlib.DownloadComponentManager.1
            @Override // java.lang.Runnable
            public void run() {
                IDownloadCache downloadCache;
                synchronized (DownloadComponentManager.class) {
                    try {
                        for (String str : new String[]{DownloadConstants.SP_AD_DOWNLOAD_EVENT, DownloadConstants.SP_CACHE_NAME, DownloadConstants.SP_DELAY_OPERATION_INFO, DownloadConstants.SP_MD5, DownloadConstants.SP_NAME_INSTALLED_APP, DownloadConstants.SP_NAME_MISC_CONFIG, DownloadConstants.SP_NAME_UNINSTALLED_APP, DownloadConstants.SP_CLEAN, DownloadConstants.SP_ORDER_DOWNLOAD, DownloadConstants.SP_ANTI_MI, com.ss.android.socialbase.downloader.constants.DownloadConstants.SP_ANTI_HIJACK_CONFIG, com.ss.android.socialbase.downloader.constants.DownloadConstants.SP_DOWNLOAD_INFO, Constants.SP_APP_DOWNLOADER}) {
                            SharedPreferences sharedPreferences = GlobalInfo.getContext().getSharedPreferences(str, 0);
                            if (sharedPreferences != null) {
                                sharedPreferences.edit().clear().apply();
                            }
                        }
                        downloadCache = com.ss.android.socialbase.downloader.downloader.DownloadComponentManager.getDownloadCache();
                    } catch (Throwable unused) {
                    }
                    if (downloadCache instanceof DefaultDownloadCache) {
                        SparseArray<DownloadInfo> downloadInfoMap = ((DefaultDownloadCache) downloadCache).getDownloadCache().getDownloadInfoMap();
                        for (int size = downloadInfoMap.size() - 1; size >= 0; size--) {
                            DownloadInfo downloadInfo = downloadInfoMap.get(downloadInfoMap.keyAt(size));
                            if (downloadInfo != null) {
                                Downloader.getInstance(GlobalInfo.getContext()).clearDownloadData(downloadInfo.getId());
                            }
                        }
                    }
                }
            }
        });
    }
}
