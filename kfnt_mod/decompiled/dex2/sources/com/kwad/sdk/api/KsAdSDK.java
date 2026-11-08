package com.kwad.sdk.api;

import android.content.Context;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.api.core.IKsAdSDK;
import com.kwad.sdk.api.core.KsAdSdkApi;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.atomic.AtomicBoolean;

@KsAdSdkApi
@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class KsAdSDK {
    private static Context mOriginalAppContext;
    private static String sAppTag;
    private static final AtomicBoolean sInited = new AtomicBoolean(false);

    @Keep
    private static IKsAdSDK sSdk;

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    public @interface KsThemeModeType {
        public static final int NIGHT = 1;
        public static final int NORMAL = 0;
    }

    @KsAdSdkApi
    @Keep
    public static void deleteCache() {
        if (sSdk != null) {
            sSdk.deleteCache();
        }
    }

    @KsAdSdkApi
    @Keep
    public static String getAppId() {
        if (sSdk != null) {
            return sSdk.getAppId();
        }
        return null;
    }

    @KsAdSdkApi
    @Keep
    public static String getAppName() {
        if (sSdk != null) {
            return sSdk.getAppName();
        }
        return null;
    }

    @Nullable
    @KsAdSdkApi
    @Keep
    public static Context getContext() {
        return mOriginalAppContext;
    }

    @KsAdSdkApi
    @Keep
    public static String getDid() {
        if (sSdk != null) {
            return sSdk.getDid();
        }
        return null;
    }

    @NonNull
    @KsAdSdkApi
    @Keep
    public static KsLoadManager getLoadManager() {
        if (sSdk != null) {
            return sSdk.getAdManager();
        }
        return null;
    }

    @KsAdSdkApi
    @Keep
    public static String getSDKVersion() {
        return "3.3.17";
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0069 A[Catch: all -> 0x0056, TryCatch #0 {, blocks: (B:5:0x0004, B:7:0x001f, B:8:0x003e, B:12:0x0047, B:14:0x004c, B:20:0x0061, B:22:0x0069, B:23:0x0072, B:19:0x0059), top: B:28:0x0004, inners: #1 }] */
    @com.kwad.sdk.api.core.KsAdSdkApi
    @androidx.annotation.Keep
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static synchronized boolean init(android.content.Context r4, com.kwad.sdk.api.SdkConfig r5) {
        /*
            java.lang.Class<com.kwad.sdk.api.KsAdSDK> r0 = com.kwad.sdk.api.KsAdSDK.class
            monitor-enter(r0)
            r1 = 0
            com.kwad.sdk.api.KsAdSDK.mOriginalAppContext = r4     // Catch: java.lang.Throwable -> L56 java.lang.Throwable -> L58
            android.content.Context r4 = com.kwad.sdk.api.loader.c.a(r4)     // Catch: java.lang.Throwable -> L56 java.lang.Throwable -> L58
            com.kwad.sdk.api.loader.Loader r2 = com.kwad.sdk.api.loader.Loader.get()     // Catch: java.lang.Throwable -> L56 java.lang.Throwable -> L58
            r2.init(r4)     // Catch: java.lang.Throwable -> L56 java.lang.Throwable -> L58
            com.kwad.sdk.api.loader.Loader r2 = com.kwad.sdk.api.loader.Loader.get()     // Catch: java.lang.Throwable -> L56 java.lang.Throwable -> L58
            com.kwad.sdk.api.core.IKsAdSDK r2 = r2.getKsAdSDKImpl()     // Catch: java.lang.Throwable -> L56 java.lang.Throwable -> L58
            com.kwad.sdk.api.KsAdSDK.sSdk = r2     // Catch: java.lang.Throwable -> L56 java.lang.Throwable -> L58
            com.kwad.sdk.api.core.IKsAdSDK r2 = com.kwad.sdk.api.KsAdSDK.sSdk     // Catch: java.lang.Throwable -> L56 java.lang.Throwable -> L58
            if (r2 == 0) goto L3e
            com.kwad.sdk.api.core.IKsAdSDK r2 = com.kwad.sdk.api.KsAdSDK.sSdk     // Catch: java.lang.Throwable -> L56 java.lang.Throwable -> L58
            java.lang.String r3 = "3.3.17"
            r2.setApiVersion(r3)     // Catch: java.lang.Throwable -> L56 java.lang.Throwable -> L58
            com.kwad.sdk.api.core.IKsAdSDK r2 = com.kwad.sdk.api.KsAdSDK.sSdk     // Catch: java.lang.Throwable -> L56 java.lang.Throwable -> L58
            r3 = 3031700(0x2e4294, float:4.248317E-39)
            r2.setApiVersionCode(r3)     // Catch: java.lang.Throwable -> L56 java.lang.Throwable -> L58
            com.kwad.sdk.api.core.IKsAdSDK r2 = com.kwad.sdk.api.KsAdSDK.sSdk     // Catch: java.lang.Throwable -> L56 java.lang.Throwable -> L58
            java.lang.String r3 = com.kwad.sdk.api.KsAdSDK.sAppTag     // Catch: java.lang.Throwable -> L56 java.lang.Throwable -> L58
            r2.setAppTag(r3)     // Catch: java.lang.Throwable -> L56 java.lang.Throwable -> L58
            com.kwad.sdk.api.core.IKsAdSDK r2 = com.kwad.sdk.api.KsAdSDK.sSdk     // Catch: java.lang.Throwable -> L56 java.lang.Throwable -> L58
            android.content.Context r3 = com.kwad.sdk.api.loader.Wrapper.wrapContextIfNeed(r4)     // Catch: java.lang.Throwable -> L56 java.lang.Throwable -> L58
            r2.init(r3, r5)     // Catch: java.lang.Throwable -> L56 java.lang.Throwable -> L58
        L3e:
            java.util.concurrent.atomic.AtomicBoolean r2 = com.kwad.sdk.api.KsAdSDK.sInited     // Catch: java.lang.Throwable -> L56 java.lang.Throwable -> L58
            com.kwad.sdk.api.core.IKsAdSDK r3 = com.kwad.sdk.api.KsAdSDK.sSdk     // Catch: java.lang.Throwable -> L56 java.lang.Throwable -> L58
            if (r3 == 0) goto L46
            r3 = 1
            goto L47
        L46:
            r3 = 0
        L47:
            r2.set(r3)     // Catch: java.lang.Throwable -> L56 java.lang.Throwable -> L58
            if (r5 == 0) goto L61
            java.lang.String r2 = "sdkconfig"
            java.lang.String r5 = r5.toJson()     // Catch: java.lang.Throwable -> L56 java.lang.Throwable -> L58
            com.kwad.sdk.api.loader.q.a(r4, r2, r5)     // Catch: java.lang.Throwable -> L56 java.lang.Throwable -> L58
            goto L61
        L56:
            r4 = move-exception
            goto L7a
        L58:
            r4 = move-exception
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L56
            java.util.concurrent.atomic.AtomicBoolean r4 = com.kwad.sdk.api.KsAdSDK.sInited     // Catch: java.lang.Throwable -> L56
            r4.set(r1)     // Catch: java.lang.Throwable -> L56
        L61:
            java.lang.Boolean r4 = com.kwad.sdk.api.a.a     // Catch: java.lang.Throwable -> L56
            boolean r4 = r4.booleanValue()     // Catch: java.lang.Throwable -> L56
            if (r4 == 0) goto L72
            com.kwad.sdk.api.loader.Loader r4 = com.kwad.sdk.api.loader.Loader.get()     // Catch: java.lang.Throwable -> L56
            com.kwad.sdk.api.core.IKsAdSDK r5 = com.kwad.sdk.api.KsAdSDK.sSdk     // Catch: java.lang.Throwable -> L56
            r4.checkUpdate(r5)     // Catch: java.lang.Throwable -> L56
        L72:
            java.util.concurrent.atomic.AtomicBoolean r4 = com.kwad.sdk.api.KsAdSDK.sInited     // Catch: java.lang.Throwable -> L56
            boolean r4 = r4.get()     // Catch: java.lang.Throwable -> L56
            monitor-exit(r0)
            return r4
        L7a:
            monitor-exit(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.api.KsAdSDK.init(android.content.Context, com.kwad.sdk.api.SdkConfig):boolean");
    }

    @KsAdSdkApi
    @Keep
    public static boolean isDebugLogEnable() {
        if (sSdk != null) {
            return sSdk.isDebugLogEnable();
        }
        return false;
    }

    @KsAdSdkApi
    @Keep
    public static void pauseCurrentPlayer() {
        if (sSdk != null) {
            sSdk.pauseCurrentPlayer();
        }
    }

    public static void re(Object obj) {
        if (sSdk != null) {
            sSdk.re(obj);
        }
    }

    @KsAdSdkApi
    @Keep
    public static void resumeCurrentPlayer() {
        if (sSdk != null) {
            sSdk.resumeCurrentPlayer();
        }
    }

    @KsAdSdkApi
    @Keep
    public static void setAppTag(String str) {
        if (sSdk != null) {
            sSdk.setAppTag(str);
        } else {
            sAppTag = str;
        }
    }

    @KsAdSdkApi
    @Keep
    public static void setPersonalRecommend(boolean z) {
        if (sSdk != null) {
            sSdk.setPersonalRecommend(z);
        }
    }

    @KsAdSdkApi
    @Keep
    public static void setProgrammaticRecommend(boolean z) {
        if (sSdk != null) {
            sSdk.setProgrammaticRecommend(z);
        }
    }

    public static void setThemeMode(int i) {
        if (sSdk != null) {
            sSdk.setThemeMode(i);
        }
    }

    @KsAdSdkApi
    @Keep
    public static void unInit() {
        if (sSdk != null) {
            sSdk.unInit();
        }
        sSdk = null;
    }
}
