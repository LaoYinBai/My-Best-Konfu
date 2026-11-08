package com.apm.insight;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.apm.insight.l.r;
import com.apm.insight.runtime.ConfigManager;
import com.apm.insight.runtime.o;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class Npth {
    private static boolean sInit;

    public static void addAttachLongUserData(AttachUserData attachUserData, CrashType crashType) {
        if (attachUserData != null) {
            h.b().b(attachUserData, crashType);
        }
    }

    public static void addAttachUserData(AttachUserData attachUserData, CrashType crashType) {
        if (attachUserData != null) {
            h.b().a(attachUserData, crashType);
        }
    }

    public static void addTags(Map<? extends String, ? extends String> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        h.b().a(map);
    }

    public static void dumpHprof(String str) {
        o.c(str);
    }

    public static void enableALogCollector(String str, com.apm.insight.a.b bVar, com.apm.insight.a.c cVar) {
        o.a(str, bVar, cVar);
    }

    public static void enableThreadsBoost() {
        h.a(1);
    }

    public static ConfigManager getConfigManager() {
        return h.i();
    }

    public static boolean hasCrash() {
        return o.k();
    }

    public static boolean hasCrashWhenJavaCrash() {
        return o.l();
    }

    public static boolean hasCrashWhenNativeCrash() {
        return o.m();
    }

    public static synchronized void init(@NonNull Application application, @NonNull Context context, @NonNull ICommonParams iCommonParams, boolean z, boolean z2, boolean z3, boolean z4, long j) {
        if (sInit) {
            return;
        }
        sInit = true;
        o.a(application, context, true, true, true, true, j);
        h.a(application, context, iCommonParams);
        Map<String, Object> mapA = h.a().a();
        MonitorCrash monitorCrashInit = MonitorCrash.init(context, String.valueOf(r.a(mapA.get("aid"), 4444)), r.a(mapA.get("update_version_code"), 0), String.valueOf(mapA.get("app_version")));
        if (monitorCrashInit != null) {
            monitorCrashInit.config().setDeviceId(h.a().d()).setChannel(String.valueOf(mapA.get("channel")));
        }
    }

    public static synchronized void init(@NonNull Context context, @NonNull ICommonParams iCommonParams) {
        init(context, iCommonParams, true, false, false);
    }

    public static synchronized void init(@NonNull Context context, @NonNull ICommonParams iCommonParams, boolean z, boolean z2, boolean z3) {
        init(context, iCommonParams, z, z, z2, z3);
    }

    public static synchronized void init(@NonNull Context context, @NonNull ICommonParams iCommonParams, boolean z, boolean z2, boolean z3, boolean z4) {
        init(context, iCommonParams, z, z2, z3, z4, 0L);
    }

    public static synchronized void init(@NonNull Context context, @NonNull ICommonParams iCommonParams, boolean z, boolean z2, boolean z3, boolean z4, long j) {
        Application applicationH;
        Context baseContext = context;
        synchronized (Npth.class) {
            if (h.h() != null) {
                applicationH = h.h();
            } else if (baseContext instanceof Application) {
                applicationH = (Application) baseContext;
                if (applicationH.getBaseContext() == null) {
                    throw new IllegalArgumentException("初始化时传入的Application还未attach, 请在init时传入attachBaseContext的参数, 并在init之前手动调用Npth.setApplication(Application).");
                }
            } else {
                applicationH = (Application) context.getApplicationContext();
                if (applicationH == null) {
                    throw new IllegalArgumentException("初始化时传入了baseContext, 导致无法获取Application实例, 请在init之前手动调用Npth.setApplication(Application).");
                }
                if (applicationH.getBaseContext() != null) {
                    baseContext = applicationH.getBaseContext();
                }
            }
            init(applicationH, baseContext, iCommonParams, z, z2, z3, z4, j);
        }
    }

    public static synchronized void initMiniApp(@NonNull Context context, @NonNull ICommonParams iCommonParams) {
        h.a(true);
        init(context, iCommonParams, true, false, true, true);
    }

    public static synchronized void initMiniApp(@NonNull Context context, @NonNull ICommonParams iCommonParams, int i, String str) {
        h.a(true);
        h.b(i, str);
        init(context, iCommonParams, true, true, true, true);
    }

    public static boolean isANREnable() {
        return o.c();
    }

    public static boolean isInit() {
        return sInit;
    }

    public static boolean isJavaCrashEnable() {
        return o.b();
    }

    public static boolean isNativeCrashEnable() {
        return o.d();
    }

    public static boolean isRunning() {
        return o.i();
    }

    public static boolean isStopUpload() {
        return o.n();
    }

    public static void openANRMonitor() {
        o.g();
    }

    public static void openJavaCrashMonitor() {
        o.f();
    }

    public static boolean openNativeCrashMonitor() {
        return o.h();
    }

    public static void registerCrashCallback(ICrashCallback iCrashCallback, CrashType crashType) {
        o.a(iCrashCallback, crashType);
    }

    public static void registerOOMCallback(IOOMCallback iOOMCallback) {
        o.a(iOOMCallback);
    }

    public static void registerSdk(int i, String str) {
        h.a(i, str);
    }

    public static void removeAttachLongUserData(AttachUserData attachUserData, CrashType crashType) {
        if (attachUserData != null) {
            h.b().b(crashType, attachUserData);
        }
    }

    public static void removeAttachUserData(AttachUserData attachUserData, CrashType crashType) {
        if (attachUserData != null) {
            h.b().a(crashType, attachUserData);
        }
    }

    public static void reportDartError(String str) {
        o.a(str);
    }

    public static void reportDartError(String str, @Nullable Map<? extends String, ? extends String> map, @Nullable Map<String, String> map2, @Nullable f fVar) {
        o.a(str, map, map2, fVar);
    }

    public static void reportDartError(String str, @Nullable Map<? extends String, ? extends String> map, @Nullable Map<String, String> map2, @Nullable Map<String, String> map3, @Nullable f fVar) {
        o.a(str, map, map2, map3, fVar);
    }

    @Deprecated
    public static void reportError(String str) {
        o.b(str);
    }

    @Deprecated
    public static void reportError(@NonNull Throwable th) {
        o.a(th);
    }

    public static void setAlogFlushAddr(long j) {
        o.a(j);
    }

    public static void setAlogFlushV2Addr(long j) {
        o.b(j);
    }

    public static void setAlogLogDirAddr(long j) {
        o.c(j);
    }

    public static void setAlogWriteAddr(long j) {
    }

    public static void setAnrInfoFileObserver(String str, e eVar) {
        o.a(str, eVar);
    }

    public static void setApplication(Application application) {
        h.a(application);
    }

    @Deprecated
    public static void setAttachUserData(AttachUserData attachUserData, CrashType crashType) {
        if (attachUserData != null) {
            h.b().a(attachUserData, crashType);
        }
    }

    public static void setBusiness(String str) {
        if (str != null) {
            h.a(str);
        }
    }

    public static void setCrashFilter(ICrashFilter iCrashFilter) {
        h.b().a(iCrashFilter);
    }

    public static void setCurProcessName(String str) {
        com.apm.insight.l.a.a(str);
    }

    public static void setEncryptImpl(@NonNull d dVar) {
        o.a(dVar);
    }

    public static void setLogcatImpl(com.apm.insight.runtime.j jVar) {
        o.a(jVar);
    }

    public static void setRequestIntercept(com.apm.insight.k.h hVar) {
        o.a(hVar);
    }

    public static void stopAnr() {
        o.j();
    }

    public static void stopUpload() {
        o.o();
    }

    public static void unregisterCrashCallback(ICrashCallback iCrashCallback, CrashType crashType) {
        o.b(iCrashCallback, crashType);
    }

    public static void unregisterOOMCallback(IOOMCallback iOOMCallback, CrashType crashType) {
        o.a(iOOMCallback, crashType);
    }
}
