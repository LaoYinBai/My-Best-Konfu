package com.ss.android.downloadlib;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.ss.android.download.api.config.IDownloadButtonClickListener;
import com.ss.android.download.api.config.OnItemClickListener;
import com.ss.android.download.api.download.DownloadController;
import com.ss.android.download.api.download.DownloadEventConfig;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.download.api.download.DownloadStatusChangeListener;
import com.ss.android.download.api.download.extend.DownloadCompletedListener;
import com.ss.android.downloadlib.addownload.CommonDownloadHandler;
import com.ss.android.downloadlib.addownload.DownloadHandler;
import com.ss.android.downloadlib.constants.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DownloadDispatcher {
    private static volatile DownloadDispatcher sAdDownloadDispatcher;
    private long mLastClearHandlerPoolTimeStamp;
    private final List<DownloadHandler> mUsableDownloadHandlerPool = new CopyOnWriteArrayList();
    private final Map<String, DownloadHandler> mUsingDownloadHandlerPool = new ConcurrentHashMap();
    private final CopyOnWriteArrayList<Object> mDownloadCompletedListenerList = new CopyOnWriteArrayList<>();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    private DownloadDispatcher() {
    }

    public static DownloadDispatcher getInstance() {
        if (sAdDownloadDispatcher == null) {
            synchronized (DownloadDispatcher.class) {
                if (sAdDownloadDispatcher == null) {
                    sAdDownloadDispatcher = new DownloadDispatcher();
                }
            }
        }
        return sAdDownloadDispatcher;
    }

    public void bind(Context context, int i, DownloadStatusChangeListener downloadStatusChangeListener, DownloadModel downloadModel) {
        if (downloadModel == null || TextUtils.isEmpty(downloadModel.getDownloadUrl())) {
            return;
        }
        DownloadHandler downloadHandler = this.mUsingDownloadHandlerPool.get(downloadModel.getDownloadUrl());
        if (downloadHandler != null) {
            downloadHandler.setContext(context).addStatusChangeListener(i, downloadStatusChangeListener).setDownloadModel(downloadModel).onBind();
        } else if (!this.mUsableDownloadHandlerPool.isEmpty()) {
            tryReuseIdleHandler(context, i, downloadStatusChangeListener, downloadModel);
        } else {
            createNewHandler(context, i, downloadStatusChangeListener, downloadModel);
        }
    }

    public CommonDownloadHandler getCommonDownloadHandler(String str) {
        if (this.mUsingDownloadHandlerPool == null || this.mUsingDownloadHandlerPool.size() == 0 || TextUtils.isEmpty(str)) {
            return null;
        }
        DownloadHandler downloadHandler = this.mUsingDownloadHandlerPool.get(str);
        if (downloadHandler instanceof CommonDownloadHandler) {
            return (CommonDownloadHandler) downloadHandler;
        }
        return null;
    }

    private synchronized void tryReuseIdleHandler(Context context, int i, DownloadStatusChangeListener downloadStatusChangeListener, DownloadModel downloadModel) {
        if (this.mUsableDownloadHandlerPool.size() <= 0) {
            createNewHandler(context, i, downloadStatusChangeListener, downloadModel);
        } else {
            DownloadHandler downloadHandlerRemove = this.mUsableDownloadHandlerPool.remove(0);
            downloadHandlerRemove.setContext(context).addStatusChangeListener(i, downloadStatusChangeListener).setDownloadModel(downloadModel).onBind();
            this.mUsingDownloadHandlerPool.put(downloadModel.getDownloadUrl(), downloadHandlerRemove);
        }
    }

    private void createNewHandler(Context context, int i, DownloadStatusChangeListener downloadStatusChangeListener, DownloadModel downloadModel) {
        if (downloadModel == null) {
            return;
        }
        CommonDownloadHandler commonDownloadHandler = new CommonDownloadHandler();
        commonDownloadHandler.setContext(context).addStatusChangeListener(i, downloadStatusChangeListener).setDownloadModel(downloadModel).onBind();
        this.mUsingDownloadHandlerPool.put(downloadModel.getDownloadUrl(), commonDownloadHandler);
    }

    public void unbind(String str, int i) {
        DownloadHandler downloadHandler;
        if (TextUtils.isEmpty(str) || (downloadHandler = this.mUsingDownloadHandlerPool.get(str)) == null) {
            return;
        }
        if (downloadHandler.onUnbind(i)) {
            this.mUsableDownloadHandlerPool.add(downloadHandler);
            this.mUsingDownloadHandlerPool.remove(str);
        }
        tryClearHandlerPool();
    }

    public void cancel(String str) {
        cancel(str, false);
    }

    public void cancel(String str, boolean z) {
        DownloadHandler downloadHandler;
        if (TextUtils.isEmpty(str) || (downloadHandler = this.mUsingDownloadHandlerPool.get(str)) == null) {
            return;
        }
        downloadHandler.cancelDownload(z);
    }

    public void action(String str, long j, int i) {
        action(str, j, i, null, null);
    }

    public void action(String str, long j, int i, DownloadEventConfig downloadEventConfig, DownloadController downloadController) {
        action(str, j, i, downloadEventConfig, downloadController, (OnItemClickListener) null, null);
    }

    public void action(String str, long j, int i, DownloadEventConfig downloadEventConfig, DownloadController downloadController, OnItemClickListener onItemClickListener, IDownloadButtonClickListener iDownloadButtonClickListener) {
        DownloadHandler downloadHandler;
        if (TextUtils.isEmpty(str) || (downloadHandler = this.mUsingDownloadHandlerPool.get(str)) == null) {
            return;
        }
        downloadHandler.setModelId(j).setDownloadEventConfig(downloadEventConfig).setDownloadController(downloadController).setOnItemClickListener(onItemClickListener).setDownloadButtonClickListener(iDownloadButtonClickListener).handleDownload(i);
    }

    public void action(String str, long j, int i, DownloadEventConfig downloadEventConfig, DownloadController downloadController, DownloadModel downloadModel) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        DownloadHandler downloadHandler = this.mUsingDownloadHandlerPool.get(str);
        if (downloadHandler != null) {
            downloadHandler.setDownloadModel(downloadModel);
        }
        action(str, j, i, downloadEventConfig, downloadController);
    }

    public void addDownloadCompletedListener(DownloadCompletedListener downloadCompletedListener) {
        if (downloadCompletedListener != null) {
            if (DownloadSetting.obtainGlobal().optBugFix(DownloadSettingKeys.BugFix.FIX_LISTENER_OOM, false)) {
                this.mDownloadCompletedListenerList.add(new SoftReference(downloadCompletedListener));
            } else {
                this.mDownloadCompletedListenerList.add(downloadCompletedListener);
            }
        }
    }

    public void removeDownloadCompletedListener(DownloadCompletedListener downloadCompletedListener) {
        if (DownloadSetting.obtainGlobal().optBugFix(DownloadSettingKeys.BugFix.FIX_LISTENER_OOM, false)) {
            Iterator<Object> it = this.mDownloadCompletedListenerList.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if ((next instanceof SoftReference) && ((SoftReference) next).get() == downloadCompletedListener) {
                    this.mDownloadCompletedListenerList.remove(next);
                    return;
                }
            }
            return;
        }
        this.mDownloadCompletedListenerList.remove(downloadCompletedListener);
    }

    public boolean isStarted(String str) {
        DownloadHandler downloadHandler;
        return (TextUtils.isEmpty(str) || (downloadHandler = this.mUsingDownloadHandlerPool.get(str)) == null || !downloadHandler.isDownloadStarted()) ? false : true;
    }

    private void tryClearHandlerPool() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.mLastClearHandlerPoolTimeStamp < 300000) {
            return;
        }
        this.mLastClearHandlerPoolTimeStamp = jCurrentTimeMillis;
        if (this.mUsableDownloadHandlerPool.isEmpty()) {
            return;
        }
        clearUsableHandlerPool();
    }

    private void clearUsableHandlerPool() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        for (DownloadHandler downloadHandler : this.mUsableDownloadHandlerPool) {
            if (!downloadHandler.isBind() && jCurrentTimeMillis - downloadHandler.getLastWorkTime() > 300000) {
                downloadHandler.resetHandlerInfo();
                arrayList.add(downloadHandler);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        this.mUsableDownloadHandlerPool.removeAll(arrayList);
    }

    public void notifyDownloadStart(final DownloadModel downloadModel, @Nullable final DownloadController downloadController, @Nullable final DownloadEventConfig downloadEventConfig) {
        this.mainHandler.post(new Runnable() { // from class: com.ss.android.downloadlib.DownloadDispatcher.1
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = DownloadDispatcher.this.mDownloadCompletedListenerList.iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    if (next instanceof DownloadCompletedListener) {
                        ((DownloadCompletedListener) next).onDownloadStart(downloadModel, downloadController, downloadEventConfig);
                    } else if (next instanceof SoftReference) {
                        SoftReference softReference = (SoftReference) next;
                        if (softReference.get() instanceof DownloadCompletedListener) {
                            ((DownloadCompletedListener) softReference.get()).onDownloadStart(downloadModel, downloadController, downloadEventConfig);
                        }
                    }
                }
            }
        });
    }

    public void notifyDownloadFailed(final DownloadInfo downloadInfo, final BaseException baseException, final String str) {
        this.mainHandler.post(new Runnable() { // from class: com.ss.android.downloadlib.DownloadDispatcher.2
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = DownloadDispatcher.this.mDownloadCompletedListenerList.iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    if (next instanceof DownloadCompletedListener) {
                        ((DownloadCompletedListener) next).onDownloadFailed(downloadInfo, baseException, str);
                    } else if (next instanceof SoftReference) {
                        SoftReference softReference = (SoftReference) next;
                        if (softReference.get() instanceof DownloadCompletedListener) {
                            ((DownloadCompletedListener) softReference.get()).onDownloadFailed(downloadInfo, baseException, str);
                        }
                    }
                }
            }
        });
    }

    public void notifyDownloadFinished(final DownloadInfo downloadInfo, final String str) {
        this.mainHandler.post(new Runnable() { // from class: com.ss.android.downloadlib.DownloadDispatcher.3
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = DownloadDispatcher.this.mDownloadCompletedListenerList.iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    if (next instanceof DownloadCompletedListener) {
                        ((DownloadCompletedListener) next).onDownloadFinished(downloadInfo, str);
                    } else if (next instanceof SoftReference) {
                        SoftReference softReference = (SoftReference) next;
                        if (softReference.get() instanceof DownloadCompletedListener) {
                            ((DownloadCompletedListener) softReference.get()).onDownloadFinished(downloadInfo, str);
                        }
                    }
                }
            }
        });
    }

    public void notifyInstalled(final DownloadInfo downloadInfo, final String str) {
        this.mainHandler.post(new Runnable() { // from class: com.ss.android.downloadlib.DownloadDispatcher.4
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = DownloadDispatcher.this.mDownloadCompletedListenerList.iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    if (next instanceof DownloadCompletedListener) {
                        ((DownloadCompletedListener) next).onInstalled(downloadInfo, str);
                    } else if (next instanceof SoftReference) {
                        SoftReference softReference = (SoftReference) next;
                        if (softReference.get() instanceof DownloadCompletedListener) {
                            ((DownloadCompletedListener) softReference.get()).onInstalled(downloadInfo, str);
                        }
                    }
                }
            }
        });
    }

    public void notifyDownloadCanceled(final DownloadInfo downloadInfo) {
        this.mainHandler.post(new Runnable() { // from class: com.ss.android.downloadlib.DownloadDispatcher.5
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = DownloadDispatcher.this.mDownloadCompletedListenerList.iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    if (next instanceof DownloadCompletedListener) {
                        ((DownloadCompletedListener) next).onCanceled(downloadInfo);
                    } else if (next instanceof SoftReference) {
                        SoftReference softReference = (SoftReference) next;
                        if (softReference.get() instanceof DownloadCompletedListener) {
                            ((DownloadCompletedListener) softReference.get()).onCanceled(downloadInfo);
                        }
                    }
                }
            }
        });
    }

    public Handler getMainHandler() {
        return this.mainHandler;
    }
}
