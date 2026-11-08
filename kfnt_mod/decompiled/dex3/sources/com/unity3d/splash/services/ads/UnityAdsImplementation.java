package com.unity3d.splash.services.ads;

import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;
import com.tachikoma.core.component.anim.AnimationProperty;
import com.unity3d.splash.IUnityAdsListener;
import com.unity3d.splash.UnityAds;
import com.unity3d.splash.services.IUnityServicesListener;
import com.unity3d.splash.services.UnityServices;
import com.unity3d.splash.services.ads.adunit.AdUnitOpen;
import com.unity3d.splash.services.ads.load.LoadModule;
import com.unity3d.splash.services.ads.placement.Placement;
import com.unity3d.splash.services.ads.properties.AdsProperties;
import com.unity3d.splash.services.core.log.DeviceLog;
import com.unity3d.splash.services.core.misc.Utilities;
import com.unity3d.splash.services.core.properties.ClientProperties;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class UnityAdsImplementation {
    public static void addListener(IUnityAdsListener iUnityAdsListener) {
        AdsProperties.addListener(iUnityAdsListener);
    }

    public static boolean getDebugMode() {
        return UnityServices.getDebugMode();
    }

    public static String getDefaultPlacement() {
        return Placement.getDefaultPlacement();
    }

    @Deprecated
    public static IUnityAdsListener getListener() {
        Iterator it = AdsProperties.getListeners().iterator();
        if (it.hasNext()) {
            return (IUnityAdsListener) it.next();
        }
        return null;
    }

    public static UnityAds.PlacementState getPlacementState() {
        return (isSupported() && isInitialized()) ? Placement.getPlacementState() : UnityAds.PlacementState.NOT_AVAILABLE;
    }

    public static UnityAds.PlacementState getPlacementState(String str) {
        return (isSupported() && isInitialized() && str != null) ? Placement.getPlacementState(str) : UnityAds.PlacementState.NOT_AVAILABLE;
    }

    public static String getVersion() {
        return UnityServices.getVersion();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void handleShowError(final String str, final UnityAds.UnityAdsError unityAdsError, String str2) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        final String str3 = "Unity Ads show failed: " + str2;
        DeviceLog.error(str3);
        Utilities.runOnUiThread(new Runnable() { // from class: com.unity3d.splash.services.ads.UnityAdsImplementation.3
            @Override // java.lang.Runnable
            public final void run() {
                for (IUnityAdsListener iUnityAdsListener : AdsProperties.getListeners()) {
                    iUnityAdsListener.onUnityAdsError(unityAdsError, str3);
                    iUnityAdsListener.onUnityAdsFinish(str != null ? str : "", UnityAds.FinishState.ERROR);
                }
            }
        });
    }

    public static void initialize(Activity activity, String str, IUnityAdsListener iUnityAdsListener) {
        initialize(activity, str, iUnityAdsListener, false);
    }

    public static void initialize(Activity activity, String str, IUnityAdsListener iUnityAdsListener, boolean z) {
        initialize(activity, str, iUnityAdsListener, z, false);
    }

    public static void initialize(Activity activity, String str, final IUnityAdsListener iUnityAdsListener, boolean z, boolean z2) {
        DeviceLog.entered();
        addListener(iUnityAdsListener);
        UnityServices.initialize(activity, str, new IUnityServicesListener() { // from class: com.unity3d.splash.services.ads.UnityAdsImplementation.1
            @Override // com.unity3d.splash.services.IUnityServicesListener
            public final void onUnityServicesError(UnityServices.UnityServicesError unityServicesError, String str2) {
                if (unityServicesError == UnityServices.UnityServicesError.INIT_SANITY_CHECK_FAIL) {
                    iUnityAdsListener.onUnityAdsError(UnityAds.UnityAdsError.INIT_SANITY_CHECK_FAIL, str2);
                } else if (unityServicesError == UnityServices.UnityServicesError.INVALID_ARGUMENT) {
                    iUnityAdsListener.onUnityAdsError(UnityAds.UnityAdsError.INVALID_ARGUMENT, str2);
                }
            }
        }, z, z2);
    }

    public static boolean isInitialized() {
        return UnityServices.isInitialized();
    }

    public static boolean isReady() {
        return isSupported() && isInitialized() && Placement.isReady();
    }

    public static boolean isReady(String str) {
        return isSupported() && isInitialized() && str != null && Placement.isReady(str);
    }

    public static boolean isSupported() {
        return UnityServices.isSupported();
    }

    public static void load(String str) {
        LoadModule.getInstance().load(str);
    }

    public static void removeListener(IUnityAdsListener iUnityAdsListener) {
        AdsProperties.removeListener(iUnityAdsListener);
    }

    public static void setDebugMode(boolean z) {
        UnityServices.setDebugMode(z);
    }

    @Deprecated
    public static void setListener(IUnityAdsListener iUnityAdsListener) {
        AdsProperties.addListener(iUnityAdsListener);
    }

    public static void show(Activity activity) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (Placement.getDefaultPlacement() != null) {
            show(activity, Placement.getDefaultPlacement());
        } else {
            handleShowError("", UnityAds.UnityAdsError.NOT_INITIALIZED, "Unity Ads default placement is not initialized");
        }
    }

    public static void show(final Activity activity, final String str) {
        if (activity == null) {
            handleShowError(str, UnityAds.UnityAdsError.INVALID_ARGUMENT, "Activity must not be null");
            return;
        }
        if (isReady(str)) {
            DeviceLog.info("Unity Ads opening new ad unit for placement " + str);
            ClientProperties.setActivity(activity);
            new Thread(new Runnable() { // from class: com.unity3d.splash.services.ads.UnityAdsImplementation.2
                @Override // java.lang.Runnable
                public final void run() throws JSONException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
                    Display defaultDisplay = ((WindowManager) activity.getSystemService("window")).getDefaultDisplay();
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("requestedOrientation", activity.getRequestedOrientation());
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("rotation", defaultDisplay.getRotation());
                        if (Build.VERSION.SDK_INT >= 13) {
                            Point point = new Point();
                            defaultDisplay.getSize(point);
                            jSONObject2.put(AnimationProperty.WIDTH, point.x);
                            jSONObject2.put(AnimationProperty.HEIGHT, point.y);
                        } else {
                            jSONObject2.put(AnimationProperty.WIDTH, defaultDisplay.getWidth());
                            jSONObject2.put(AnimationProperty.HEIGHT, defaultDisplay.getHeight());
                        }
                        jSONObject.put("display", jSONObject2);
                    } catch (JSONException e) {
                        DeviceLog.exception("JSON error while constructing show options", e);
                    }
                    try {
                        if (AdUnitOpen.open(str, jSONObject)) {
                            return;
                        }
                        UnityAdsImplementation.handleShowError(str, UnityAds.UnityAdsError.INTERNAL_ERROR, "Webapp timeout, shutting down Unity Ads");
                    } catch (NoSuchMethodException e2) {
                        DeviceLog.exception("Could not get callback method", e2);
                        UnityAdsImplementation.handleShowError(str, UnityAds.UnityAdsError.SHOW_ERROR, "Could not get com.unity3d.ads.properties.showCallback method");
                    }
                }
            }).start();
            return;
        }
        if (!isSupported()) {
            handleShowError(str, UnityAds.UnityAdsError.NOT_INITIALIZED, "Unity Ads is not supported for this device");
            return;
        }
        if (!isInitialized()) {
            handleShowError(str, UnityAds.UnityAdsError.NOT_INITIALIZED, "Unity Ads is not initialized");
            return;
        }
        handleShowError(str, UnityAds.UnityAdsError.SHOW_ERROR, "Placement \"" + str + "\" is not ready");
    }
}
