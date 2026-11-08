package com.ss.union.game.sdk.common.download;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.socialbase.downloader.constants.DBDefinition;
import com.ss.union.game.sdk.common.util.logger.LogUtils;
import com.ss.union.game.sdk.common.util.reflect.Reflect;
import com.ss.union.game.sdk.common.util.reflect.ReflectException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class GameDownloadManager {
    public static String TAG = "Core_Common_GameDownloadManager";
    private static final String a = "com.ss.union.game.sdk.common.download.DownloadManager";
    private static boolean b;

    public static void init(Context context) {
        try {
            Reflect.onClass(a).call("getInstance").call("doInit", context);
        } catch (ReflectException e) {
            LogUtils.log(TAG, "No Download component is connected... exception: " + Log.getStackTraceString(e));
        }
    }

    public static void bind(Context context, long j, String str, String str2, String str3, String str4, final GameDownloadStatusChangeListener gameDownloadStatusChangeListener) {
        try {
            Reflect.onClass(a).call("getInstance").call("bind", context, Long.valueOf(j), str, str2, str3, str4, Proxy.newProxyInstance(GameDownloadManager.class.getClassLoader(), new Class[]{Class.forName("com.ss.android.download.api.download.DownloadStatusChangeListener")}, new InvocationHandler() { // from class: com.ss.union.game.sdk.common.download.GameDownloadManager.1
                @Override // java.lang.reflect.InvocationHandler
                public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                    try {
                        if (method.getName().equals("toString")) {
                            return obj.getClass() + "@" + Integer.toHexString(System.identityHashCode(obj));
                        }
                        if (method.getName().equals("hashCode")) {
                            return Integer.valueOf(System.identityHashCode(obj));
                        }
                        boolean z = true;
                        if (method.getName().equals("equals") && method.getParameterTypes()[0] == Object.class) {
                            if (obj != objArr[0]) {
                                z = false;
                            }
                            return Boolean.valueOf(z);
                        }
                        if (method.getName().equals("onIdle")) {
                            if (gameDownloadStatusChangeListener != null) {
                                gameDownloadStatusChangeListener.onIdle();
                            }
                            return null;
                        }
                        if (method.getName().equals("onDownloadStart") && objArr != null && objArr.length == 2) {
                            if (gameDownloadStatusChangeListener != null) {
                                gameDownloadStatusChangeListener.onDownloadStart();
                            }
                            return null;
                        }
                        if (method.getName().equals("onDownloadActive")) {
                            if (gameDownloadStatusChangeListener != null && objArr != null && objArr.length == 2) {
                                gameDownloadStatusChangeListener.onDownloadActive(GameDownloadManager.b(objArr[0]), ((Integer) objArr[0]).intValue());
                            }
                            return null;
                        }
                        if (method.getName().equals("onDownloadPaused") && objArr != null && objArr.length == 2) {
                            if (gameDownloadStatusChangeListener != null) {
                                gameDownloadStatusChangeListener.onDownloadPaused(GameDownloadManager.b(objArr[0]), ((Integer) objArr[1]).intValue());
                            }
                            return null;
                        }
                        if (method.getName().equals("onDownloadFailed") && objArr != null && objArr.length == 1) {
                            if (gameDownloadStatusChangeListener != null) {
                                gameDownloadStatusChangeListener.onDownloadFailed(GameDownloadManager.b(objArr[0]));
                            }
                            return null;
                        }
                        if (method.getName().equals("onInstalled") && objArr != null && objArr.length == 1) {
                            if (gameDownloadStatusChangeListener != null) {
                                gameDownloadStatusChangeListener.onInstalled(GameDownloadManager.b(objArr[0]));
                            }
                            return null;
                        }
                        if (method.getName().equals("onDownloadFinished") && objArr != null && objArr.length == 1) {
                            if (gameDownloadStatusChangeListener != null) {
                                gameDownloadStatusChangeListener.onDownloadFinished(GameDownloadManager.b(objArr[0]));
                            }
                            return null;
                        }
                        throw new IllegalStateException("Method not implemented: " + method);
                    } catch (Throwable th) {
                        if (gameDownloadStatusChangeListener != null) {
                            gameDownloadStatusChangeListener.onDownloadFailed(null);
                        }
                        LogUtils.log(GameDownloadManager.TAG, "invoke method error" + method.getName() + " exception: " + Log.getStackTraceString(th));
                        return null;
                    }
                }
            }));
        } catch (Throwable th) {
            LogUtils.log(TAG, "No Download component is connected... exception: " + Log.getStackTraceString(th));
            if (gameDownloadStatusChangeListener != null) {
                gameDownloadStatusChangeListener.onDownloadFailed(null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static GameDownloadShortInfo b(Object obj) {
        if (obj != null) {
            GameDownloadShortInfo gameDownloadShortInfo = new GameDownloadShortInfo();
            gameDownloadShortInfo.id = ((Long) Reflect.on(obj).field(AdDownloadModel.JsonKey.ID).get()).longValue();
            gameDownloadShortInfo.status = ((Integer) Reflect.on(obj).field("status").get()).intValue();
            gameDownloadShortInfo.totalBytes = ((Long) Reflect.on(obj).field(DBDefinition.TOTAL_BYTES).get()).longValue();
            gameDownloadShortInfo.currentBytes = ((Long) Reflect.on(obj).field("currentBytes").get()).longValue();
            gameDownloadShortInfo.fileName = (String) Reflect.on(obj).field("fileName").get();
            gameDownloadShortInfo.failStatus = ((Integer) Reflect.on(obj).field("failStatus").get()).intValue();
            gameDownloadShortInfo.onlyWifi = ((Boolean) Reflect.on(obj).field(DBDefinition.ONLY_WIFI).get()).booleanValue();
            return gameDownloadShortInfo;
        }
        return new GameDownloadShortInfo();
    }

    public static void download(Activity activity, long j, long j2, long j3, long j4, String str, String str2) {
        try {
            Reflect.onClass(a).call("getInstance").call("download", activity, Long.valueOf(j), Long.valueOf(j2), Long.valueOf(j3), Long.valueOf(j4), str, str2);
        } catch (Throwable th) {
            LogUtils.log(TAG, "No Download component is connected... exception: " + Log.getStackTraceString(th));
        }
    }

    public static void setAutoInstall(Context context, boolean z) {
        try {
            Reflect.onClass(a).call("getInstance").call("setAutoInstall", context, Boolean.valueOf(z));
        } catch (Throwable th) {
            LogUtils.log(TAG, "No Download component is connected... exception: " + Log.getStackTraceString(th));
        }
    }

    public static boolean hasDownloadComponent() {
        if (b) {
            return true;
        }
        try {
            Reflect.onClass(a);
            b = true;
            return true;
        } catch (ReflectException unused) {
            return false;
        }
    }
}
