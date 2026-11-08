package com.tachikoma.core.module.handler;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.kwad.v8.V8Object;
import com.tachikoma.annotation.TK_EXPORT_CLASS;
import com.tachikoma.core.base.NativeModule;
import com.tachikoma.core.bridge.TKJSContext;
import com.tachikoma.core.log.Logger;
import com.tachikoma.core.utility.V8Proxy;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@TK_EXPORT_CLASS
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKLifeCycle implements NativeModule {
    public V8Object mV8AssociatedObject;

    public static class ActivityLifecycleCallbacksProxy implements Application.ActivityLifecycleCallbacks {
        private static final Map<TKJSContext, TKLifeCycle> mCallBackMaps = new HashMap();

        public static void registerLifecycleCallbacks(@NonNull TKJSContext tKJSContext, @NonNull TKLifeCycle tKLifeCycle) {
            mCallBackMaps.put(tKJSContext, tKLifeCycle);
        }

        public static void unRegisterLifecycleCallbacks(@NonNull TKJSContext tKJSContext) {
            mCallBackMaps.remove(tKJSContext);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            Iterator<Map.Entry<TKJSContext, TKLifeCycle>> it = mCallBackMaps.entrySet().iterator();
            while (it.hasNext()) {
                it.next().getValue().onActivityCreated(activity, bundle);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            Iterator<Map.Entry<TKJSContext, TKLifeCycle>> it = mCallBackMaps.entrySet().iterator();
            while (it.hasNext()) {
                it.next().getValue().onActivityDestroyed(activity);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            Iterator<Map.Entry<TKJSContext, TKLifeCycle>> it = mCallBackMaps.entrySet().iterator();
            while (it.hasNext()) {
                it.next().getValue().onActivityPaused(activity);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            Iterator<Map.Entry<TKJSContext, TKLifeCycle>> it = mCallBackMaps.entrySet().iterator();
            while (it.hasNext()) {
                it.next().getValue().onActivityResumed(activity);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            Iterator<Map.Entry<TKJSContext, TKLifeCycle>> it = mCallBackMaps.entrySet().iterator();
            while (it.hasNext()) {
                it.next().getValue().onActivitySaveInstanceState(activity, bundle);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            Iterator<Map.Entry<TKJSContext, TKLifeCycle>> it = mCallBackMaps.entrySet().iterator();
            while (it.hasNext()) {
                it.next().getValue().onActivityStarted(activity);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            Iterator<Map.Entry<TKJSContext, TKLifeCycle>> it = mCallBackMaps.entrySet().iterator();
            while (it.hasNext()) {
                it.next().getValue().onActivityStopped(activity);
            }
        }
    }

    public TKLifeCycle(Context context, List<Object> list) {
        this.mV8AssociatedObject = ((V8Object) Objects.requireNonNull(V8Proxy.getAssociateJsObject(list))).twin();
        ActivityLifecycleCallbacksProxy.registerLifecycleCallbacks(V8Proxy.getTKContext(list), this);
    }

    private void safelyCallLifecycle(String str, @NonNull Activity activity) {
        try {
            if (V8Proxy.isV8Valid(this.mV8AssociatedObject)) {
                this.mV8AssociatedObject.executeJSFunction(str, new Object[]{Integer.valueOf(activity.hashCode())});
            }
        } catch (Exception e) {
            Logger.logE("safelyCallLifecycle", e);
        }
    }

    @Override // com.tachikoma.core.base.NativeModule
    public void destroy() {
        V8Proxy.release(this.mV8AssociatedObject);
    }

    @Override // com.tachikoma.core.base.NativeModule
    public String getName() {
        return "LifeCycle";
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        safelyCallLifecycle("onActivityCreated", activity);
    }

    public void onActivityDestroyed(Activity activity) {
        safelyCallLifecycle("onActivityDestroyed", activity);
    }

    public void onActivityPaused(Activity activity) {
        safelyCallLifecycle("onActivityPaused", activity);
    }

    public void onActivityResumed(Activity activity) {
        safelyCallLifecycle("onActivityResumed", activity);
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        safelyCallLifecycle("onActivitySaveInstanceState", activity);
    }

    public void onActivityStarted(Activity activity) {
        safelyCallLifecycle("onActivityStarted", activity);
    }

    public void onActivityStopped(Activity activity) {
        safelyCallLifecycle("onActivityStopped", activity);
    }
}
