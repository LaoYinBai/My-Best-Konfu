package com.ss.union.game.sdk.core.splashEffect;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.ss.union.game.sdk.common.util.ActivityUtils;
import com.ss.union.game.sdk.common.util.MainThreadExecutor;
import com.ss.union.game.sdk.common.util.ProcessUtils;
import com.ss.union.game.sdk.core.R;
import com.ss.union.game.sdk.core.base.event.PageStater;
import com.ss.union.game.sdk.core.splashEffect.activity.SplashEffectLandscapeActivity;
import com.ss.union.game.sdk.core.splashEffect.activity.SplashEffectPortraitActivity;
import com.ss.union.game.sdk.core.splashEffect.callback.LGSplashBrandEffectListener;
import com.ss.union.game.sdk.core.splashEffect.util.TimeCollector;
import com.ss.union.game.sdk.core.splashEffect.view.TextureViewPlayer;
import com.ss.union.game.sdk.core.splashEffect.view.VideoViewPlayer;
import com.ss.union.game.sdk.core.splashEffect.view.a.b;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private static final int a = 0;
    private static final int b = 1;
    private static LGSplashBrandEffectListener g;
    private static InterfaceC0090a c = new InterfaceC0090a() { // from class: com.ss.union.game.sdk.core.splashEffect.a.1
        @Override // com.ss.union.game.sdk.core.splashEffect.a.InterfaceC0090a
        public b a(Context context, int i) {
            if (i == 0) {
                return new TextureViewPlayer(context);
            }
            return new VideoViewPlayer(context);
        }
    };
    private static volatile boolean d = false;
    private static String e = "lg_splash_effect_portrait.mp4";
    private static String f = "lg_splash_effect_landscape.mp4";
    private static volatile boolean h = false;

    /* renamed from: com.ss.union.game.sdk.core.splashEffect.a$a, reason: collision with other inner class name */
    private interface InterfaceC0090a {
        b a(Context context, int i);
    }

    public static void a(Context context, LGSplashBrandEffectListener lGSplashBrandEffectListener) {
        try {
            g = lGSplashBrandEffectListener;
            if (!h || g == null) {
                return;
            }
            g.onFinish();
            g = null;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(Activity activity, boolean z) {
        c();
        a(activity, z);
    }

    public static void a(Activity activity) {
        com.ss.union.game.sdk.core.splashEffect.util.a.a(activity);
    }

    public static void a(Activity activity, boolean z) {
        com.ss.union.game.sdk.core.splashEffect.a.a.b("playSplashBrandEffect,屏幕方向：" + z);
        Intent intent = new Intent(activity, (Class<?>) (z ? SplashEffectPortraitActivity.class : SplashEffectLandscapeActivity.class));
        intent.addFlags(65536);
        ActivityUtils.startActivity(activity, intent, new ActivityUtils.IActivityStartListener() { // from class: com.ss.union.game.sdk.core.splashEffect.a.2
            @Override // com.ss.union.game.sdk.common.util.ActivityUtils.IActivityStartListener
            public void onSuccess() {
                com.ss.union.game.sdk.core.splashEffect.a.a.a("播放开屏特效启动activity成功");
            }

            @Override // com.ss.union.game.sdk.common.util.ActivityUtils.IActivityStartListener
            public void onFail(Throwable th) {
                com.ss.union.game.sdk.core.splashEffect.a.a.b("播放开屏特效失败：" + th.getMessage());
                th.printStackTrace();
                a.a();
            }
        });
        activity.overridePendingTransition(0, 0);
    }

    public static void a(final Application application) {
        if (d) {
            return;
        }
        d = true;
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.ss.union.game.sdk.core.splashEffect.a.3
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(@NonNull Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(@NonNull Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(@NonNull Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(@NonNull Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(@NonNull Activity activity) {
                application.unregisterActivityLifecycleCallbacks(this);
                if (ProcessUtils.isMainProcess(activity)) {
                    com.ss.union.game.sdk.core.splashEffect.a.a.b("准备播放开屏特效，监听到游戏主页面是：" + activity.getClass().getName());
                    TimeCollector.effect().start();
                    a.c(activity, ActivityUtils.isPortrait(activity));
                }
            }
        });
    }

    public static void a() {
        h = true;
        MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.core.splashEffect.a.4
            @Override // java.lang.Runnable
            public void run() {
                if (a.g != null) {
                    a.g.onFinish();
                    LGSplashBrandEffectListener unused = a.g = null;
                }
            }
        });
    }

    public static String a(Context context) {
        if (context.getResources().getConfiguration().orientation == 1) {
            return e;
        }
        return f;
    }

    public static String b(Context context) {
        String str = "android.resource://" + context.getPackageName() + "/";
        if (context.getResources().getConfiguration().orientation == 1) {
            return str + a(context, "lg_splash_effect_portrait", R.raw.lg_splash_effect_portrait);
        }
        return str + a(context, "lg_splash_effect_landscape", R.raw.lg_splash_effect_landscape);
    }

    private static int a(Context context, String str, int i) {
        int identifier;
        try {
            identifier = context.getResources().getIdentifier(str, "raw", context.getPackageName());
        } catch (Exception unused) {
        }
        return identifier != 0 ? identifier : i;
    }

    public static b a(ViewGroup viewGroup) {
        Context context = viewGroup.getContext();
        b bVarA = c.a(viewGroup.getContext(), 0);
        viewGroup.addView(bVarA.getView(), new ViewGroup.LayoutParams(-1, -1));
        bVarA.setUri(b(context));
        bVarA.a();
        return bVarA;
    }

    private static void c() {
        try {
            HashMap map = new HashMap();
            map.put("launchvideo_show", "video_show");
            PageStater.onEvent("ohayoo_sdk_launchvideo", map);
        } catch (Exception unused) {
        }
    }
}
