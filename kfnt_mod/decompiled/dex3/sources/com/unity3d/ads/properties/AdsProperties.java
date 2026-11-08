package com.unity3d.ads.properties;

import com.unity3d.ads.IUnityAdsListener;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AdsProperties {
    private static IUnityAdsListener _listener;
    private static Set<IUnityAdsListener> _listeners = Collections.synchronizedSet(new LinkedHashSet());

    public static void setListener(IUnityAdsListener iUnityAdsListener) {
        if (_listener != null) {
            _listeners.remove(_listener);
        }
        _listener = iUnityAdsListener;
    }

    public static IUnityAdsListener getListener() {
        return _listener;
    }

    public static void addListener(IUnityAdsListener iUnityAdsListener) {
        if (_listener == null) {
            _listener = iUnityAdsListener;
        }
        if (iUnityAdsListener == null || _listeners.contains(iUnityAdsListener)) {
            return;
        }
        _listeners.add(iUnityAdsListener);
    }

    public static Set<IUnityAdsListener> getListeners() {
        LinkedHashSet linkedHashSet = new LinkedHashSet(_listeners);
        if (_listener != null) {
            linkedHashSet.add(_listener);
        }
        return linkedHashSet;
    }

    public static void removeListener(IUnityAdsListener iUnityAdsListener) {
        if (_listener != null && _listener.equals(iUnityAdsListener)) {
            _listener = null;
        }
        _listeners.remove(iUnityAdsListener);
    }
}
