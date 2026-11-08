package com.ss.union.game.sdk.common.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.ss.union.game.sdk.common.util.logger.LogUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ActivityUtils {
    private static volatile boolean a;
    private static List<Activity> b;
    private static int d;
    private static List<LifecycleCallbacks> c = new ArrayList();
    private static List<ForegroundListener> e = new ArrayList();

    public interface ForegroundListener {
        void onBackground();

        void onForeground(Activity activity);
    }

    public interface IActivityStartListener {
        void onFail(Throwable th);

        void onSuccess();
    }

    public static abstract class LifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
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
    }

    static /* synthetic */ int c() {
        int i = d;
        d = i + 1;
        return i;
    }

    static /* synthetic */ int f() {
        int i = d;
        d = i - 1;
        return i;
    }

    public static synchronized void init(Context context) {
        if (!a) {
            if (context == null) {
                context = GlobalApplicationUtils.getContext();
            }
            if (context == null) {
                return;
            }
            a = true;
            b = g();
            ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.ss.union.game.sdk.common.util.ActivityUtils.1
                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityCreated(Activity activity, Bundle bundle) {
                    LifecycleCallbacks[] lifecycleCallbacksArrH = ActivityUtils.h();
                    if (lifecycleCallbacksArrH == null || lifecycleCallbacksArrH.length <= 0) {
                        return;
                    }
                    for (LifecycleCallbacks lifecycleCallbacks : lifecycleCallbacksArrH) {
                        if (lifecycleCallbacks != null) {
                            lifecycleCallbacks.onActivityCreated(activity, bundle);
                        }
                    }
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStarted(Activity activity) {
                    LifecycleCallbacks[] lifecycleCallbacksArrH = ActivityUtils.h();
                    if (lifecycleCallbacksArrH == null || lifecycleCallbacksArrH.length <= 0) {
                        return;
                    }
                    for (LifecycleCallbacks lifecycleCallbacks : lifecycleCallbacksArrH) {
                        if (lifecycleCallbacks != null) {
                            lifecycleCallbacks.onActivityStarted(activity);
                        }
                    }
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityResumed(Activity activity) {
                    LifecycleCallbacks[] lifecycleCallbacksArrH = ActivityUtils.h();
                    if (lifecycleCallbacksArrH != null && lifecycleCallbacksArrH.length > 0) {
                        for (LifecycleCallbacks lifecycleCallbacks : lifecycleCallbacksArrH) {
                            if (lifecycleCallbacks != null) {
                                lifecycleCallbacks.onActivityResumed(activity);
                            }
                        }
                    }
                    if (ActivityUtils.b.size() <= 0 || ActivityUtils.getTopActivity() != activity) {
                        ActivityUtils.b.add(activity);
                    }
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityPaused(Activity activity) {
                    LifecycleCallbacks[] lifecycleCallbacksArrH = ActivityUtils.h();
                    if (lifecycleCallbacksArrH != null && lifecycleCallbacksArrH.length > 0) {
                        for (LifecycleCallbacks lifecycleCallbacks : lifecycleCallbacksArrH) {
                            if (lifecycleCallbacks != null) {
                                lifecycleCallbacks.onActivityPaused(activity);
                            }
                        }
                    }
                    if (activity.isFinishing()) {
                        ActivityUtils.b.remove(activity);
                    }
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStopped(Activity activity) {
                    LifecycleCallbacks[] lifecycleCallbacksArrH = ActivityUtils.h();
                    if (lifecycleCallbacksArrH == null || lifecycleCallbacksArrH.length <= 0) {
                        return;
                    }
                    for (LifecycleCallbacks lifecycleCallbacks : lifecycleCallbacksArrH) {
                        if (lifecycleCallbacks != null) {
                            lifecycleCallbacks.onActivityStopped(activity);
                        }
                    }
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                    LifecycleCallbacks[] lifecycleCallbacksArrH = ActivityUtils.h();
                    if (lifecycleCallbacksArrH == null || lifecycleCallbacksArrH.length <= 0) {
                        return;
                    }
                    for (LifecycleCallbacks lifecycleCallbacks : lifecycleCallbacksArrH) {
                        if (lifecycleCallbacks != null) {
                            lifecycleCallbacks.onActivitySaveInstanceState(activity, bundle);
                        }
                    }
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityDestroyed(Activity activity) {
                    if (ActivityUtils.b.contains(activity)) {
                        ActivityUtils.b.remove(activity);
                    }
                    LifecycleCallbacks[] lifecycleCallbacksArrH = ActivityUtils.h();
                    if (lifecycleCallbacksArrH == null || lifecycleCallbacksArrH.length <= 0) {
                        return;
                    }
                    for (LifecycleCallbacks lifecycleCallbacks : lifecycleCallbacksArrH) {
                        if (lifecycleCallbacks != null) {
                            lifecycleCallbacks.onActivityDestroyed(activity);
                        }
                    }
                }
            });
            i();
        }
    }

    public static Activity getTopActivity() {
        if (b.size() > 0) {
            return b.get(b.size() - 1);
        }
        return null;
    }

    public static Activity getBottomActivity() {
        if (b.size() > 0) {
            return b.get(0);
        }
        return null;
    }

    private static List<Activity> g() {
        LinkedList linkedList = new LinkedList();
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Method declaredMethod = cls.getDeclaredMethod("currentActivityThread", new Class[0]);
            declaredMethod.setAccessible(true);
            Object objInvoke = declaredMethod.invoke(null, new Object[0]);
            Field declaredField = cls.getDeclaredField("mActivities");
            declaredField.setAccessible(true);
            Iterator it = ((Map) declaredField.get(objInvoke)).entrySet().iterator();
            while (it.hasNext()) {
                Object value = ((Map.Entry) it.next()).getValue();
                Field declaredField2 = value.getClass().getDeclaredField("activity");
                declaredField2.setAccessible(true);
                linkedList.add((Activity) declaredField2.get(value));
            }
        } catch (Throwable unused) {
        }
        return linkedList;
    }

    public static void exit() {
        if (b != null) {
            Iterator<Activity> it = b.iterator();
            while (it.hasNext()) {
                it.next().finish();
            }
        }
    }

    public static synchronized void registerActivityLifecycleCallbacks(LifecycleCallbacks lifecycleCallbacks) {
        c.add(lifecycleCallbacks);
    }

    public static synchronized void unregisterActivityLifecycleCallbacks(LifecycleCallbacks lifecycleCallbacks) {
        c.remove(lifecycleCallbacks);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized LifecycleCallbacks[] h() {
        if (c != null && c.size() > 0) {
            return (LifecycleCallbacks[]) c.toArray(new LifecycleCallbacks[c.size()]);
        }
        return null;
    }

    private static void i() {
        if (getTopActivity() != null && ProcessUtils.appIsForeground()) {
            d = 1;
        } else {
            d = 0;
        }
        registerActivityLifecycleCallbacks(new LifecycleCallbacks() { // from class: com.ss.union.game.sdk.common.util.ActivityUtils.2
            @Override // com.ss.union.game.sdk.common.util.ActivityUtils.LifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity) {
                ForegroundListener[] foregroundListenerArrJ;
                super.onActivityStarted(activity);
                ActivityUtils.c();
                if (ActivityUtils.d != 1 || (foregroundListenerArrJ = ActivityUtils.j()) == null || foregroundListenerArrJ.length <= 0) {
                    return;
                }
                for (ForegroundListener foregroundListener : foregroundListenerArrJ) {
                    if (foregroundListener != null) {
                        foregroundListener.onForeground(activity);
                    }
                }
            }

            @Override // com.ss.union.game.sdk.common.util.ActivityUtils.LifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
                ForegroundListener[] foregroundListenerArrJ;
                super.onActivityStopped(activity);
                ActivityUtils.f();
                if (ActivityUtils.d != 0 || (foregroundListenerArrJ = ActivityUtils.j()) == null || foregroundListenerArrJ.length <= 0) {
                    return;
                }
                for (ForegroundListener foregroundListener : foregroundListenerArrJ) {
                    if (foregroundListener != null) {
                        foregroundListener.onBackground();
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized ForegroundListener[] j() {
        if (e != null && e.size() > 0) {
            return (ForegroundListener[]) e.toArray(new ForegroundListener[e.size()]);
        }
        return null;
    }

    public static synchronized void registerForegroundListener(ForegroundListener foregroundListener) {
        if (foregroundListener != null) {
            e.add(foregroundListener);
        }
    }

    public static synchronized void unregisterForegroundListener(ForegroundListener foregroundListener) {
        e.remove(foregroundListener);
    }

    public static boolean startActivity(Context context, Intent intent, IActivityStartListener iActivityStartListener) {
        if (context == null || intent == null) {
            return false;
        }
        try {
            if (unwrap(context) == null) {
                intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
            }
            context.startActivity(intent);
            if (iActivityStartListener == null) {
                return true;
            }
            iActivityStartListener.onSuccess();
            return true;
        } catch (Throwable th) {
            if (iActivityStartListener != null) {
                iActivityStartListener.onFail(th);
            }
            return false;
        }
    }

    public static boolean isPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == 1;
    }

    public static Activity unwrap(Context context) {
        boolean z;
        while (true) {
            z = context instanceof Activity;
            if (z || !(context instanceof ContextWrapper)) {
                break;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        if (z) {
            return (Activity) context;
        }
        return null;
    }

    public static int replaceOrientation(@NonNull Activity activity, int i) {
        try {
            int requestedOrientation = activity.getRequestedOrientation();
            if (requestedOrientation != i) {
                activity.setRequestedOrientation(i);
            }
            return requestedOrientation;
        } catch (Exception e2) {
            LogUtils.log("ActivityUtils", "replaceOrientation fail: " + Log.getStackTraceString(e2));
            return 1;
        }
    }
}
