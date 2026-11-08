package com.ss.union.game.sdk.core;

import android.os.Handler;
import com.bytedance.applog.AppLog;
import com.ss.union.game.sdk.common.util.flow.FlowList;
import com.ss.union.game.sdk.core.base.config.LocalConfigManager;
import com.ss.union.game.sdk.core.base.init.callback.LGRequestAppLogCallback;
import com.ss.union.game.sdk.core.base.init.callback.LGSplashInitCallback;
import com.ss.union.game.sdk.core.deviceInfo.in.IDeviceInfoService;
import com.ss.union.game.sdk.core.init.b.b;
import com.ss.union.game.sdk.core.init.b.c;
import com.ss.union.game.sdk.core.init.b.d;
import com.ss.union.game.sdk.core.init.b.e;
import com.ss.union.game.sdk.core.init.b.f;
import com.ss.union.game.sdk.core.init.b.g;
import com.ss.union.game.sdk.core.init.b.h;
import com.ss.union.game.sdk.core.init.b.i;
import com.ss.union.game.sdk.core.init.b.j;
import com.ss.union.game.sdk.core.init.b.k;
import com.ss.union.game.sdk.core.init.b.l;
import com.ss.union.game.sdk.core.init.callback.LGSdkInitCallback;
import com.ss.union.game.sdk.core.privacy.LGPrivacyPolicyManager;
import com.ss.union.game.sdk.core.realName.LGRealNameManager;
import com.ss.union.game.sdk.core.realName.LGRealNameManagerImpl;
import com.ss.union.game.sdk.core.valueAdded.IValueAddedService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LGSDKDevKit {
    private static void initSplash(final LGSplashInitCallback lGSplashInitCallback) {
        new FlowList().then(new l()).register(new FlowList.FlowListenerAdapter() { // from class: com.ss.union.game.sdk.core.LGSDKDevKit.1
            @Override // com.ss.union.game.sdk.common.util.flow.FlowList.FlowListenerAdapter, com.ss.union.game.sdk.common.util.flow.FlowList.IFlowListener
            public void onFinish() {
                super.onFinish();
                if (lGSplashInitCallback != null) {
                    lGSplashInitCallback.onFinish();
                }
            }
        }).start();
    }

    private static void init(final LGSdkInitCallback lGSdkInitCallback, LGRequestAppLogCallback lGRequestAppLogCallback) {
        new FlowList().then(new k()).then(new j()).then(new e(lGRequestAppLogCallback)).then(new i()).then(new g()).then(new b()).then(new d()).then(new c()).then(new f()).then(new h()).then(new com.ss.union.game.sdk.core.init.b.a()).register(new FlowList.FlowListenerAdapter() { // from class: com.ss.union.game.sdk.core.LGSDKDevKit.2
            @Override // com.ss.union.game.sdk.common.util.flow.FlowList.FlowListenerAdapter, com.ss.union.game.sdk.common.util.flow.FlowList.IFlowListener
            public void onFinish() {
                super.onFinish();
                if (lGSdkInitCallback != null) {
                    lGSdkInitCallback.onInitSuccess(AppLog.getDid(), AppLog.getIid(), AppLog.getSsid(), AppLog.getUserUniqueID());
                }
                com.ss.union.game.sdk.core.init.a.a.a();
            }
        }).start();
    }

    public static void triggerCrash() {
        if (LocalConfigManager.canTriggerCrash) {
            new Handler().post(new Runnable() { // from class: com.ss.union.game.sdk.core.LGSDKDevKit.3
                @Override // java.lang.Runnable
                public void run() {
                    throw new NullPointerException("test crash");
                }
            });
        }
    }

    public static void reportException(String str, String str2) {
        com.ss.union.game.sdk.core.a.a.a(str, str2);
    }

    public static void addApmTags(String str, String str2) {
        com.ss.union.game.sdk.core.a.a.b(str, str2);
    }

    public static LGRealNameManager getRealNameManager() {
        return LGRealNameManagerImpl.getInstance();
    }

    public static IValueAddedService getAddedService() {
        return com.ss.union.game.sdk.core.valueAdded.a.a();
    }

    public static IDeviceInfoService getDeviceInfoService() {
        return com.ss.union.game.sdk.core.deviceInfo.a.a.a();
    }

    public static void openUserProtocol() {
        LGPrivacyPolicyManager.openUserProtocol(null);
    }

    public static void openPrivacyProtocol() {
        LGPrivacyPolicyManager.openPrivacyProtocol(null);
    }

    public static void openIdentifyProtocol() {
        LGPrivacyPolicyManager.openIdentifyProtocol(null);
    }
}
