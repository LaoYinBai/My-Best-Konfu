package com.ss.union.game.sdk.core.base.debug.automatic_detection;

import android.app.Activity;
import android.os.Bundle;
import com.ss.union.game.sdk.common.ui.floatview.FloatIntent;
import com.ss.union.game.sdk.common.ui.floatview.FloatViewManager;
import com.ss.union.game.sdk.common.util.ActivityUtils;
import com.ss.union.game.sdk.common.util.MainThreadExecutor;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AutomaticDetectionMonitor {
    private static List<String> blackListActivityName = new ArrayList();

    private interface a {
        void a(Activity activity);
    }

    public static void init() {
        insertBlackListActivityName("com.ss.union.game.sdk.core.splashEffect");
        runOnBottomActivity(new a() { // from class: com.ss.union.game.sdk.core.base.debug.automatic_detection.AutomaticDetectionMonitor.1
            @Override // com.ss.union.game.sdk.core.base.debug.automatic_detection.AutomaticDetectionMonitor.a
            public void a(final Activity activity) {
                FloatViewManager.getInstance().create(AutomaticDetectionMonitor.createDetectionFloatView(activity, 0, 0));
                final String className = activity.getComponentName().getClassName();
                ActivityUtils.registerActivityLifecycleCallbacks(new ActivityUtils.LifecycleCallbacks() { // from class: com.ss.union.game.sdk.core.base.debug.automatic_detection.AutomaticDetectionMonitor.1.1
                    @Override // com.ss.union.game.sdk.common.util.ActivityUtils.LifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
                    public void onActivityCreated(Activity activity2, Bundle bundle) {
                        super.onActivityCreated(activity2, bundle);
                        if (activity2.getComponentName().getClassName().equals(className)) {
                            FloatViewManager.getInstance().create(AutomaticDetectionMonitor.createDetectionFloatView(activity, 0, 0));
                        }
                    }
                });
            }
        });
    }

    public static void insertBlackListActivityName(String str) {
        if (blackListActivityName.contains(str)) {
            return;
        }
        blackListActivityName.add(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static FloatIntent createDetectionFloatView(Activity activity, int i, int i2) {
        return new FloatIntent(activity, com.ss.union.game.sdk.core.base.debug.automatic_detection.ui.a.class).setX(i).setY(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void runOnBottomActivity(final a aVar) {
        Activity bottomActivity = ActivityUtils.getBottomActivity();
        if (bottomActivity == null || currentActivityInBlackList(bottomActivity.getComponentName().getClassName())) {
            MainThreadExecutor.postDelayed(new Runnable() { // from class: com.ss.union.game.sdk.core.base.debug.automatic_detection.AutomaticDetectionMonitor.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        AutomaticDetectionMonitor.runOnBottomActivity(aVar);
                    } catch (Exception unused) {
                    }
                }
            }, 1000L);
        } else {
            aVar.a(bottomActivity);
        }
    }

    private static boolean currentActivityInBlackList(String str) {
        for (int i = 0; i < blackListActivityName.size(); i++) {
            if (str.contains(blackListActivityName.get(i))) {
                return true;
            }
        }
        return false;
    }
}
