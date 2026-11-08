package com.ss.android.socialbase.downloader.common;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.MainThread;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AppStatusManager {
    private static final int STATUS_BACKGROUND = 0;
    private static final int STATUS_FOREGROUND = 1;
    private static final int STATUS_UNKNOWN = -1;
    private static final String TAG = "AppStatusManager";
    private volatile boolean isActivityOnPause;
    private volatile int mAppStatus;
    private final List<AppStatusChangeListener> mAppStatusChangeListeners;
    private Application mApplication;
    private final Application.ActivityLifecycleCallbacks mCallbacks;
    private InnerAppStatusChangeCaller mInnerAppStatusChangeCaller;
    private WeakReference<Activity> mTopActivity;
    private int mTopActivityHashCode;

    public interface AppStatusChangeListener {
        @MainThread
        void onAppBackground();

        @MainThread
        void onAppForeground();
    }

    public interface InnerAppStatusChangeCaller {
        boolean isAppInBackground();
    }

    public static AppStatusManager getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final AppStatusManager INSTANCE = new AppStatusManager();

        private Holder() {
        }
    }

    private AppStatusManager() {
        this.mAppStatusChangeListeners = new ArrayList();
        this.mAppStatus = -1;
        this.isActivityOnPause = false;
        this.mCallbacks = new Application.ActivityLifecycleCallbacks() { // from class: com.ss.android.socialbase.downloader.common.AppStatusManager.1
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity, Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity) {
                AppStatusManager.this.mTopActivity = new WeakReference(activity);
                int i = AppStatusManager.this.mTopActivityHashCode;
                AppStatusManager.this.mTopActivityHashCode = activity != null ? activity.hashCode() : i;
                AppStatusManager.this.isActivityOnPause = false;
                if (i == 0) {
                    AppStatusManager.this.dispatchAppForeground();
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(Activity activity) {
                int i = AppStatusManager.this.mTopActivityHashCode;
                AppStatusManager.this.isActivityOnPause = false;
                AppStatusManager.this.mTopActivityHashCode = activity != null ? activity.hashCode() : i;
                if (i == 0) {
                    AppStatusManager.this.dispatchAppForeground();
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity) {
                AppStatusManager.this.isActivityOnPause = true;
                if (AppStatusManager.this.mTopActivityHashCode != 0 || activity == null) {
                    return;
                }
                AppStatusManager.this.mTopActivityHashCode = activity.hashCode();
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
                if (activity != null && activity.hashCode() == AppStatusManager.this.mTopActivityHashCode) {
                    AppStatusManager.this.mTopActivityHashCode = 0;
                    AppStatusManager.this.dispatchAppBackground();
                }
                AppStatusManager.this.isActivityOnPause = false;
            }
        };
    }

    public void init(Context context) {
        if (this.mApplication == null && (context instanceof Application)) {
            synchronized (this) {
                if (this.mApplication == null) {
                    this.mApplication = (Application) context;
                    this.mApplication.registerActivityLifecycleCallbacks(this.mCallbacks);
                }
            }
        }
    }

    public void setInnerAppStatusChangeCaller(InnerAppStatusChangeCaller innerAppStatusChangeCaller) {
        this.mInnerAppStatusChangeCaller = innerAppStatusChangeCaller;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [boolean, int] */
    public boolean isAppForeground() {
        int i = this.mAppStatus;
        int i2 = i;
        if (i == -1) {
            ?? CheckAppForeground = checkAppForeground();
            this.mAppStatus = CheckAppForeground;
            i2 = CheckAppForeground;
        }
        return i2 == 1;
    }

    public boolean isAppFocus() {
        return isAppForeground() && !this.isActivityOnPause;
    }

    public void registerAppSwitchListener(AppStatusChangeListener appStatusChangeListener) {
        if (appStatusChangeListener == null) {
            return;
        }
        synchronized (this.mAppStatusChangeListeners) {
            if (!this.mAppStatusChangeListeners.contains(appStatusChangeListener)) {
                this.mAppStatusChangeListeners.add(appStatusChangeListener);
            }
        }
    }

    public void unregisterAppSwitchListener(AppStatusChangeListener appStatusChangeListener) {
        synchronized (this.mAppStatusChangeListeners) {
            this.mAppStatusChangeListeners.remove(appStatusChangeListener);
        }
    }

    public Activity getTopActivity() {
        if (this.mTopActivity == null) {
            return null;
        }
        return this.mTopActivity.get();
    }

    private Object[] collectAppSwitchListeners() {
        Object[] array;
        synchronized (this.mAppStatusChangeListeners) {
            array = this.mAppStatusChangeListeners.size() > 0 ? this.mAppStatusChangeListeners.toArray() : null;
        }
        return array;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchAppForeground() {
        this.mAppStatus = 1;
        Object[] objArrCollectAppSwitchListeners = collectAppSwitchListeners();
        if (objArrCollectAppSwitchListeners != null) {
            for (Object obj : objArrCollectAppSwitchListeners) {
                ((AppStatusChangeListener) obj).onAppForeground();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchAppBackground() {
        this.mAppStatus = 0;
        Object[] objArrCollectAppSwitchListeners = collectAppSwitchListeners();
        if (objArrCollectAppSwitchListeners != null) {
            for (Object obj : objArrCollectAppSwitchListeners) {
                ((AppStatusChangeListener) obj).onAppBackground();
            }
        }
    }

    private boolean checkAppForeground() {
        try {
            Application application = this.mApplication;
            if (application == null) {
                return false;
            }
            ActivityManager activityManager = (ActivityManager) application.getSystemService("activity");
            String packageName = application.getPackageName();
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses != null && !runningAppProcesses.isEmpty()) {
                for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                    if (runningAppProcessInfo.importance == 100 && TextUtils.equals(runningAppProcessInfo.processName, packageName)) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }
}
