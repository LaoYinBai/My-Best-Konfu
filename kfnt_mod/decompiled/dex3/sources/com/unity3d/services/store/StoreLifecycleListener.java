package com.unity3d.services.store;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import java.util.ArrayList;

@TargetApi(14)
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class StoreLifecycleListener implements Application.ActivityLifecycleCallbacks {
    private ArrayList<String> _exceptions;
    private ArrayList<String> _purchaseTypes;
    private boolean _trackAllActivities;

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
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
    }

    public StoreLifecycleListener(boolean z, ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        this._trackAllActivities = z;
        this._exceptions = arrayList;
        this._purchaseTypes = arrayList2;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        boolean z = this._exceptions != null && this._exceptions.contains(activity.getLocalClassName());
        if (((!this._trackAllActivities || z) && (this._trackAllActivities || !z)) || this._purchaseTypes == null) {
            return;
        }
        StoreMonitor.sendPurchaseStatusOnResume(activity.getLocalClassName(), this._purchaseTypes);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        boolean z = this._exceptions != null && this._exceptions.contains(activity.getLocalClassName());
        if (((!this._trackAllActivities || z) && (this._trackAllActivities || !z)) || this._purchaseTypes == null) {
            return;
        }
        StoreMonitor.sendPurchaseStatusOnStop(activity.getLocalClassName(), this._purchaseTypes);
    }
}
