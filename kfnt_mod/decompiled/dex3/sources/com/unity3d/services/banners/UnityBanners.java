package com.unity3d.services.banners;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.ads.placement.Placement;
import com.unity3d.services.banners.BannerView;
import com.unity3d.services.banners.properties.BannerRefreshInfo;
import com.unity3d.services.banners.view.BannerPosition;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.misc.Utilities;
import com.unity3d.services.core.misc.ViewUtilities;
import com.unity3d.services.core.properties.ClientProperties;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class UnityBanners {
    private static UnityBanners instance;
    private IUnityBannerListener _bannerListener;
    private BannerPosition _currentBannerPosition = BannerPosition.NONE;
    private BannerWrapper _currentBannerWrapper;

    private static UnityBanners getInstance() {
        if (instance == null) {
            instance = new UnityBanners();
        }
        return instance;
    }

    private class BannerWrapper extends FrameLayout {
        private BannerAdRefreshView _bannerAdRefreshView;
        private BannerPosition _bannerPosition;

        public BannerWrapper(Context context, BannerAdRefreshView bannerAdRefreshView) {
            super(context);
            this._bannerPosition = BannerPosition.NONE;
            this._bannerAdRefreshView = bannerAdRefreshView;
            addView(this._bannerAdRefreshView);
            setupLayoutConstraints();
            setBackgroundColor(0);
        }

        public void setBannerPosition(BannerPosition bannerPosition) {
            this._bannerPosition = bannerPosition;
            setupLayoutConstraints();
        }

        private void setupLayoutConstraints() {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            layoutParams.gravity = this._bannerPosition.getGravity();
            setLayoutParams(layoutParams);
        }

        public void destroy() throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
            Utilities.runOnUiThread(new Runnable() { // from class: com.unity3d.services.banners.UnityBanners.BannerWrapper.1
                @Override // java.lang.Runnable
                public void run() throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
                    this.removeAllViews();
                    ViewUtilities.removeViewFromParent(this);
                }
            });
            if (this._bannerAdRefreshView != null) {
                this._bannerAdRefreshView.destroy();
                this._bannerAdRefreshView = null;
            }
        }
    }

    private UnityBanners() {
    }

    private void _loadBanner(Activity activity, String str) {
        if (this._currentBannerWrapper == null) {
            BannerAdRefreshView bannerAdRefreshView = new BannerAdRefreshView(activity, str, new UnityBannerSize(320, 50));
            final BannerWrapper bannerWrapper = new BannerWrapper(activity, bannerAdRefreshView);
            bannerWrapper.setBannerPosition(this._currentBannerPosition);
            this._currentBannerWrapper = bannerWrapper;
            bannerAdRefreshView.setListener(new BannerView.Listener() { // from class: com.unity3d.services.banners.UnityBanners.1
                @Override // com.unity3d.services.banners.BannerView.Listener, com.unity3d.services.banners.BannerView.IListener
                public void onBannerLoaded(BannerView bannerView) {
                    if (this._bannerListener != null) {
                        this._bannerListener.onUnityBannerLoaded(bannerView.getPlacementId(), bannerWrapper);
                    }
                }

                @Override // com.unity3d.services.banners.BannerView.Listener, com.unity3d.services.banners.BannerView.IListener
                public void onBannerFailedToLoad(BannerView bannerView, BannerErrorInfo bannerErrorInfo) {
                    if (this._bannerListener != null) {
                        this._bannerListener.onUnityBannerError(bannerView.getPlacementId() + " " + bannerErrorInfo.errorMessage);
                    }
                }

                @Override // com.unity3d.services.banners.BannerView.Listener, com.unity3d.services.banners.BannerView.IListener
                public void onBannerClick(BannerView bannerView) {
                    if (this._bannerListener != null) {
                        this._bannerListener.onUnityBannerClick(bannerView.getPlacementId());
                    }
                }
            });
            bannerAdRefreshView.load();
            return;
        }
        sendError("A Banner is already in use, please call destroy before loading another banner!");
    }

    private void _destroy() throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (this._currentBannerWrapper != null) {
            this._currentBannerWrapper.destroy();
            this._currentBannerWrapper = null;
        }
    }

    private static void sendError(final String str) {
        Utilities.runOnUiThread(new Runnable() { // from class: com.unity3d.services.banners.UnityBanners.2
            @Override // java.lang.Runnable
            public void run() {
                IUnityBannerListener bannerListener = UnityBanners.getBannerListener();
                if (bannerListener != null) {
                    bannerListener.onUnityBannerError(str);
                }
            }
        });
    }

    @Deprecated
    public static void loadBanner(Activity activity) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        loadBanner(activity, Placement.getDefaultBannerPlacement());
    }

    @Deprecated
    public static void loadBanner(Activity activity, String str) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        DeviceLog.entered();
        if (!UnityAds.isSupported()) {
            sendError("Unity Ads is not supported on this device.");
        }
        if (!UnityAds.isInitialized()) {
            sendError("UnityAds is not initialized.");
            return;
        }
        if (!UnityAds.isReady(str)) {
            sendError("Banner placement " + str + " is not ready");
            return;
        }
        ClientProperties.setActivity(activity);
        getInstance()._loadBanner(activity, str);
    }

    @Deprecated
    public static void destroy() throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        getInstance()._destroy();
    }

    @Deprecated
    public static void setBannerListener(IUnityBannerListener iUnityBannerListener) {
        getInstance()._bannerListener = iUnityBannerListener;
    }

    @Deprecated
    public static IUnityBannerListener getBannerListener() {
        return getInstance()._bannerListener;
    }

    @Deprecated
    public static void setBannerPosition(BannerPosition bannerPosition) {
        getInstance()._currentBannerPosition = bannerPosition;
    }

    private class BannerAdRefreshView extends RelativeLayout {
        private BannerView bannerView;
        private boolean didLoad;
        private boolean didShow;
        private boolean didSubscribeToLifecycle;
        private LifecycleListener lifecycleListener;
        private String placementId;
        private Handler refreshHandler;
        private long refreshRate;
        private long refreshTime;
        private Runnable reloadRunnable;

        public BannerAdRefreshView(Activity activity, String str, UnityBannerSize unityBannerSize) {
            super(activity);
            this.didLoad = false;
            this.refreshRate = 30L;
            this.didShow = false;
            this.didSubscribeToLifecycle = false;
            this.placementId = str;
            this.refreshHandler = new Handler();
            this.reloadRunnable = new Runnable() { // from class: com.unity3d.services.banners.UnityBanners.BannerAdRefreshView.1
                @Override // java.lang.Runnable
                public void run() {
                    this.reload();
                }
            };
            setupLayoutParams();
            setBackgroundColor(0);
            this.bannerView = new BannerView(activity, str, unityBannerSize);
            addView(this.bannerView);
        }

        public String getPlacementId() {
            return this.bannerView.getPlacementId();
        }

        public UnityBannerSize getSize() {
            return this.bannerView.getSize();
        }

        public void setListener(BannerView.IListener iListener) {
            this.bannerView.setListener(iListener);
        }

        public BannerView.IListener getListener() {
            return this.bannerView.getListener();
        }

        public void load() {
            if (this.didLoad) {
                return;
            }
            this.didLoad = true;
            Integer refreshRate = BannerRefreshInfo.getInstance().getRefreshRate(this.placementId);
            if (refreshRate != null) {
                this.refreshRate = refreshRate.longValue();
            }
            reload();
        }

        public void destroy() throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
            stopReloadTask();
            this.bannerView.destroy();
            Utilities.runOnUiThread(new Runnable() { // from class: com.unity3d.services.banners.UnityBanners.BannerAdRefreshView.2
                @Override // java.lang.Runnable
                public void run() {
                    this.removeAllViews();
                }
            });
            this.bannerView = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void startReloadTask() {
            if (SystemClock.uptimeMillis() < this.refreshTime) {
                this.refreshHandler.postAtTime(this.reloadRunnable, this.refreshTime);
            } else {
                reload();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void stopReloadTask() {
            if (this.refreshHandler == null || this.reloadRunnable == null) {
                return;
            }
            this.refreshHandler.removeCallbacks(this.reloadRunnable);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void reload() {
            this.bannerView.load();
            this.refreshTime = SystemClock.uptimeMillis() + TimeUnit.SECONDS.toMillis(this.refreshRate);
            this.refreshHandler.postAtTime(this.reloadRunnable, this.refreshTime);
        }

        private void subscribeToLifecycle() {
            if (this.didSubscribeToLifecycle || Build.VERSION.SDK_INT < 14 || ClientProperties.getApplication() == null) {
                return;
            }
            this.lifecycleListener = new LifecycleListener() { // from class: com.unity3d.services.banners.UnityBanners.BannerAdRefreshView.3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super();
                }

                @Override // com.unity3d.services.banners.UnityBanners.BannerAdRefreshView.LifecycleListener, android.app.Application.ActivityLifecycleCallbacks
                public void onActivityPaused(Activity activity) {
                    this.stopReloadTask();
                }

                @Override // com.unity3d.services.banners.UnityBanners.BannerAdRefreshView.LifecycleListener, android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStopped(Activity activity) {
                    this.stopReloadTask();
                }

                @Override // com.unity3d.services.banners.UnityBanners.BannerAdRefreshView.LifecycleListener, android.app.Application.ActivityLifecycleCallbacks
                public void onActivityDestroyed(Activity activity) {
                    this.stopReloadTask();
                }

                @Override // com.unity3d.services.banners.UnityBanners.BannerAdRefreshView.LifecycleListener, android.app.Application.ActivityLifecycleCallbacks
                public void onActivityResumed(Activity activity) {
                    this.startReloadTask();
                }
            };
            this.didSubscribeToLifecycle = true;
            ClientProperties.getApplication().registerActivityLifecycleCallbacks(this.lifecycleListener);
        }

        private void unsubscribeFromLifecycle() {
            if (!this.didSubscribeToLifecycle || Build.VERSION.SDK_INT < 14 || this.lifecycleListener == null || ClientProperties.getApplication() == null) {
                return;
            }
            this.didSubscribeToLifecycle = false;
            ClientProperties.getApplication().unregisterActivityLifecycleCallbacks(this.lifecycleListener);
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            stopReloadTask();
            unsubscribeFromLifecycle();
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void onAttachedToWindow() {
            super.onAttachedToWindow();
            subscribeToLifecycle();
            if (this.didShow) {
                startReloadTask();
            } else {
                this.didShow = true;
            }
        }

        private void setupLayoutParams() {
            setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        }

        @TargetApi(14)
        private class LifecycleListener implements Application.ActivityLifecycleCallbacks {
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity, Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
            }

            private LifecycleListener() {
            }
        }
    }
}
