package com.ss.union.game.sdk.core.base.init.provider;

import android.content.Context;
import android.util.Log;
import com.ss.union.game.sdk.common.util.NetworkUtils;
import com.ss.union.game.sdk.common.util.ProcessUtils;
import com.ss.union.game.sdk.common.util.flow.FlowList;
import com.ss.union.game.sdk.core.base.init.c.b;
import com.ss.union.game.sdk.core.base.init.c.c;
import com.ss.union.game.sdk.core.base.init.c.d;
import com.ss.union.game.sdk.core.base.init.c.e;
import com.ss.union.game.sdk.core.base.init.c.f;
import com.ss.union.game.sdk.core.base.init.c.g;
import com.ss.union.game.sdk.core.base.init.c.h;
import com.ss.union.game.sdk.core.base.init.c.i;
import com.ss.union.game.sdk.core.base.init.c.j;
import com.ss.union.game.sdk.core.base.init.callback.LGSplashInitCallback;
import com.ss.union.game.sdk.core.base.utils.LogCoreUtils;
import com.ss.union.game.sdk.core.init.callback.LGSdkInitCallback;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private static volatile boolean a;
    private static volatile boolean b;
    private static volatile boolean c;
    private static volatile boolean d;
    private static List<LGSdkInitCallback> e = new ArrayList();
    private static LGSdkInitCallback f;

    static synchronized void a(final Context context) {
        try {
            Log.e("LG_LOGGER", "LightGameSDK start init splash");
            a = true;
            new FlowList().then(new f(context.getApplicationContext())).then(new j()).then(new h(new LGSplashInitCallback() { // from class: com.ss.union.game.sdk.core.base.init.provider.a.1
                @Override // com.ss.union.game.sdk.core.base.init.callback.LGSplashInitCallback
                public void onFinish() {
                    a.c(context);
                }
            })).start();
        } catch (Throwable th) {
            c(context);
            Log.e("LG_LOGGER", "Failed to auto initialize the LightGameSDK, no splash...", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(Context context) {
        a = false;
        if (b) {
            b = false;
            d(context);
        }
    }

    public static synchronized void a(Context context, LGSdkInitCallback lGSdkInitCallback) {
        if (!ProcessUtils.isMainProcess(context)) {
            LogCoreUtils.logInit("Call init on non main process...");
            return;
        }
        if (a()) {
            if (lGSdkInitCallback != null) {
                lGSdkInitCallback.onInitFailed(-3, com.ss.union.game.sdk.core.base.init.a.a.f);
            }
            return;
        }
        if (c) {
            if (lGSdkInitCallback != null) {
                lGSdkInitCallback.onInitFailed(-2, com.ss.union.game.sdk.core.base.init.a.a.d);
            }
        } else {
            if (!NetworkUtils.isNetworkAvailable()) {
                if (lGSdkInitCallback != null) {
                    lGSdkInitCallback.onInitFailed(-1, com.ss.union.game.sdk.core.base.init.a.a.b);
                }
                return;
            }
            c = true;
            f = lGSdkInitCallback;
            if (a) {
                b = true;
            } else {
                d(context);
            }
        }
    }

    private static synchronized void d(Context context) {
        try {
            Log.e("LG_LOGGER", "LightGameSDK start init");
            new FlowList().then(new f(context.getApplicationContext())).then(new d()).then(new g()).then(new e()).then(new c()).then(new b()).then(new com.ss.union.game.sdk.core.base.init.c.a()).then(new i()).register(new com.ss.union.game.sdk.core.base.init.b.a()).start();
        } catch (Throwable th) {
            Log.e("LG_LOGGER", "Failed to manual initialize the LightGameSDK", th);
        }
    }

    public static synchronized void a(LGSdkInitCallback lGSdkInitCallback) {
        if (lGSdkInitCallback != null) {
            e.add(lGSdkInitCallback);
        }
    }

    public static synchronized void a(String str, String str2, String str3, String str4) {
        d = true;
        c = false;
        if (e != null) {
            for (LGSdkInitCallback lGSdkInitCallback : e) {
                if (lGSdkInitCallback != null) {
                    lGSdkInitCallback.onInitSuccess(str, str2, str3, str4);
                }
            }
            e.clear();
        }
        if (f != null) {
            f.onInitSuccess(str, str2, str3, str4);
            f = null;
        }
        Log.d("LG_LOGGER", "sdkInitSuccess");
    }

    public static synchronized void a(int i, String str) {
        c = false;
        if (e != null) {
            for (LGSdkInitCallback lGSdkInitCallback : e) {
                if (lGSdkInitCallback != null) {
                    lGSdkInitCallback.onInitFailed(i, str);
                }
            }
            e.clear();
        }
        if (f != null) {
            f.onInitFailed(i, str);
            f = null;
        }
        Log.d("LG_LOGGER", "sdkInitFailed");
    }

    public static synchronized boolean a() {
        return d;
    }
}
