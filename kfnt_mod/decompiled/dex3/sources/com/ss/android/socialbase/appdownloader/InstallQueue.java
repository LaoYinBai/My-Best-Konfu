package com.ss.android.socialbase.appdownloader;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.ss.android.socialbase.appdownloader.setting.DownloadSettingKeys;
import com.ss.android.socialbase.appdownloader.view.JumpUnknownSourceActivity;
import com.ss.android.socialbase.downloader.common.AppStatusManager;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.logger.Logger;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import java.lang.ref.SoftReference;
import java.util.ArrayDeque;
import java.util.Queue;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class InstallQueue {
    private static final long DEFAULT_DELAY_RETRY_TIME = 1000;
    private static final long INSTALL_TIMEOUT = 20000;
    private static final int MAX_INSTALL_QUEUE_SIZE = 3;
    private static final long ON_RESUME_INSTALL_INTERVAL = 120000;
    private static final long SHORT_INTERVAL = 1000;
    private SoftReference<JumpUnknownSourceActivity> activityRef;
    private final Queue<Integer> installQueue;
    private boolean isListeningOnResume;
    private long lastInstallTimeStamp;
    private long lastOnResumeInstallTimeStamp;
    private Handler mMainThreadHandler;
    private Runnable mTimeoutRunnable;

    private InstallQueue() {
        this.installQueue = new ArrayDeque();
        this.isListeningOnResume = false;
        this.mMainThreadHandler = new Handler(Looper.getMainLooper());
        this.mTimeoutRunnable = new Runnable() { // from class: com.ss.android.socialbase.appdownloader.InstallQueue.1
            @Override // java.lang.Runnable
            public void run() throws JSONException {
                InstallQueue.this.tryNextInstall();
            }
        };
        AppStatusManager.getInstance().registerAppSwitchListener(new AppStatusManager.AppStatusChangeListener() { // from class: com.ss.android.socialbase.appdownloader.InstallQueue.2
            @Override // com.ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
            public void onAppBackground() {
            }

            @Override // com.ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
            public void onAppForeground() throws JSONException {
                if (InstallQueue.this.installQueue.isEmpty()) {
                    return;
                }
                long jOptLong = DownloadSetting.obtainGlobal().optLong(DownloadSettingKeys.KEY_INSTALL_ON_RESUME_INSTALL_INTERVAL, InstallQueue.ON_RESUME_INSTALL_INTERVAL);
                long jCurrentTimeMillis = System.currentTimeMillis() - InstallQueue.this.lastOnResumeInstallTimeStamp;
                if (jCurrentTimeMillis < jOptLong) {
                    if (InstallQueue.this.mMainThreadHandler.hasCallbacks(InstallQueue.this.mTimeoutRunnable)) {
                        return;
                    }
                    InstallQueue.this.mMainThreadHandler.postDelayed(InstallQueue.this.mTimeoutRunnable, jOptLong - jCurrentTimeMillis);
                } else {
                    InstallQueue.this.lastOnResumeInstallTimeStamp = System.currentTimeMillis();
                    InstallQueue.this.tryNextInstall();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryNextInstall() throws JSONException {
        final Integer numPoll;
        if (Build.VERSION.SDK_INT < 29 || AppStatusManager.getInstance().isAppForeground()) {
            synchronized (this.installQueue) {
                numPoll = this.installQueue.poll();
            }
            this.mMainThreadHandler.removeCallbacks(this.mTimeoutRunnable);
            if (numPoll != null) {
                final Context appContext = DownloadComponentManager.getAppContext();
                if (Looper.myLooper() != Looper.getMainLooper()) {
                    this.mMainThreadHandler.post(new Runnable() { // from class: com.ss.android.socialbase.appdownloader.InstallQueue.3
                        @Override // java.lang.Runnable
                        public void run() throws JSONException {
                            InstallQueue.this.startIntent(appContext, numPoll.intValue(), false);
                        }
                    });
                } else {
                    startIntent(appContext, numPoll.intValue(), false);
                }
                this.mMainThreadHandler.postDelayed(this.mTimeoutRunnable, INSTALL_TIMEOUT);
                return;
            }
            this.isListeningOnResume = false;
        }
    }

    void onInstallFinish(DownloadInfo downloadInfo, String str) throws JSONException {
        if (downloadInfo == null || TextUtils.isEmpty(str)) {
            return;
        }
        tryNextInstall();
    }

    private static class Holder {
        private static final InstallQueue INSTANCE = new InstallQueue();

        private Holder() {
        }
    }

    public static InstallQueue getInstance() {
        return Holder.INSTANCE;
    }

    public int offerInstallRequest(final Context context, final int i, final boolean z) {
        if (z) {
            return startIntent(context, i, z);
        }
        if (isShortInterval()) {
            this.mMainThreadHandler.postDelayed(new Runnable() { // from class: com.ss.android.socialbase.appdownloader.InstallQueue.4
                @Override // java.lang.Runnable
                public void run() {
                    InstallQueue.this.offerInstallRequest(context, i, z);
                }
            }, 1000L);
            return 1;
        }
        if (AppStatusManager.getInstance().isAppForeground()) {
            Logger.i("leaves", "on Foreground");
            return startIntent(context, i, z);
        }
        if (AhUtils.isProgressInJumpUnKnowSource()) {
            return 1;
        }
        boolean z2 = Build.VERSION.SDK_INT < 29;
        if (this.installQueue.isEmpty() && !this.isListeningOnResume && z2) {
            return startIntent(context, i, z);
        }
        int iOptInt = DownloadSetting.obtainGlobal().optInt(DownloadSettingKeys.KEY_INSTALL_QUEUE_SIZE, 3);
        synchronized (this.installQueue) {
            while (this.installQueue.size() > iOptInt) {
                this.installQueue.poll();
            }
        }
        if (z2) {
            this.mMainThreadHandler.removeCallbacks(this.mTimeoutRunnable);
            this.mMainThreadHandler.postDelayed(this.mTimeoutRunnable, DownloadSetting.obtain(i).optLong(DownloadSettingKeys.KEY_INSTALL_QUEUE_TIMEOUT, INSTALL_TIMEOUT));
        }
        synchronized (this.installQueue) {
            if (!this.installQueue.contains(Integer.valueOf(i))) {
                this.installQueue.offer(Integer.valueOf(i));
            }
        }
        return 1;
    }

    private boolean isShortInterval() {
        return System.currentTimeMillis() - this.lastInstallTimeStamp < 1000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int startIntent(Context context, int i, boolean z) throws JSONException {
        int iProxyStartViewIntent = AppDownloadUtils.proxyStartViewIntent(context, i, z);
        if (iProxyStartViewIntent == 1) {
            this.isListeningOnResume = true;
        }
        this.lastInstallTimeStamp = System.currentTimeMillis();
        return iProxyStartViewIntent;
    }

    public void pushActivity(JumpUnknownSourceActivity jumpUnknownSourceActivity) {
        this.activityRef = new SoftReference<>(jumpUnknownSourceActivity);
    }

    public JumpUnknownSourceActivity popActivity() {
        JumpUnknownSourceActivity jumpUnknownSourceActivity = this.activityRef == null ? null : this.activityRef.get();
        this.activityRef = null;
        return jumpUnknownSourceActivity;
    }
}
