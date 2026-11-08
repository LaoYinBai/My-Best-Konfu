package com.tencent.bugly.agent;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.umeng.commonsdk.proguard.o;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class GameAgent {
    private static final String CLASS_COCOS_ACTIVITY = "org.cocos2dx.lib.Cocos2dxActivity";
    private static final String CLASS_UNITY_PLAYER = "com.unity3d.player.UnityPlayer";
    private static final String CRASH_REPORT_CLASS_SUFFIX = "crashreport.CrashReport";
    public static final int GAME_TYPE_COCOS = 1;
    public static final int GAME_TYPE_UNITY = 2;
    public static final int GAME_TYPE_UNKNOWN = 0;
    private static final int LOG_LEVEL_DEBUG = 1;
    private static final int LOG_LEVEL_ERROR = 4;
    private static final int LOG_LEVEL_INFO = 2;
    private static final int LOG_LEVEL_VERBOSE = 0;
    private static final int LOG_LEVEL_WARN = 3;
    private static final String LOG_TAG = "CrashReport-GameAgent";
    private static final String OLD_STRATEGY_CLASS_SUFFIX = "crashreport.CrashReport$UserStrategy";
    private static final String STRATEGY_CLASS_SUFFIX = "BuglyStrategy";
    private static final int TYPE_COCOS2DX_JS_CRASH = 5;
    private static final int TYPE_COCOS2DX_LUA_CRASH = 6;
    private static final int TYPE_U3D_CRASH = 4;
    private static final String VERSION = "3.2";
    private static boolean hasGuessed = false;
    private static WeakReference<Activity> sActivity = null;
    private static String sAppChannel = null;
    private static String sAppVersion = null;
    private static WeakReference<Context> sContext = null;
    private static int sGameType = 0;
    private static Handler sHandler = null;
    private static boolean sIsDebug = false;
    private static String sUserId = null;
    private static String sdkPackageName = "com.tencent.bugly";

    public static String getVersion() {
        return VERSION;
    }

    public static void printLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (str.startsWith("<Log>")) {
            printLog(2, str);
            return;
        }
        if (str.startsWith("<LogDebug>")) {
            printLog(1, str);
            return;
        }
        if (str.startsWith("<LogInfo>")) {
            printLog(2, str);
            return;
        }
        if (str.startsWith("<LogWarning>")) {
            printLog(3, str);
            return;
        }
        if (str.startsWith("<LogAssert>")) {
            printLog(3, str);
            return;
        }
        if (str.startsWith("<LogError>")) {
            printLog(4, str);
        } else if (str.startsWith("<LogException>")) {
            printLog(4, str);
        } else {
            printLog(0, str);
        }
    }

    private static void printLog(int i, String str) {
        setLog(i, LOG_TAG, str);
    }

    public static Activity getUnityActivity() {
        try {
            Object staticField = Reflection.getStaticField(CLASS_UNITY_PLAYER, "currentActivity", null);
            if (staticField != null && (staticField instanceof Activity)) {
                return (Activity) staticField;
            }
        } catch (Exception unused) {
            Log.w(LOG_TAG, "Failed to get activity of Unity.");
        }
        return null;
    }

    public static Activity getCocosActivity() {
        try {
            Object objInvokeStaticMethod = Reflection.invokeStaticMethod(CLASS_COCOS_ACTIVITY, "getContext", null, new Class[0]);
            if (objInvokeStaticMethod != null && (objInvokeStaticMethod instanceof Activity)) {
                return (Activity) objInvokeStaticMethod;
            }
        } catch (Exception unused) {
            Log.w(LOG_TAG, "Failed to get activity of Cocos.");
        }
        return null;
    }

    private static Activity guessActivity() {
        if (hasGuessed) {
            return null;
        }
        hasGuessed = true;
        Activity activity = sGameType != 0 ? getActivity() : null;
        if (activity == null) {
            sGameType = 1;
            activity = getActivity();
        }
        if (activity == null) {
            sGameType = 2;
            activity = getActivity();
        }
        if (activity == null) {
            sGameType = 0;
        }
        return activity;
    }

    private static Activity getActivity() {
        Activity cocosActivity;
        if (sActivity == null || sActivity.get() == null) {
            switch (sGameType) {
                case 1:
                    cocosActivity = getCocosActivity();
                    break;
                case 2:
                    cocosActivity = getUnityActivity();
                    break;
                default:
                    Log.w(LOG_TAG, "Game type has not been set.");
                    cocosActivity = guessActivity();
                    break;
            }
            if (cocosActivity != null) {
                sActivity = new WeakReference<>(cocosActivity);
            }
        }
        if (sActivity != null) {
            return sActivity.get();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Context getApplicationContext() {
        Activity activity;
        if ((sContext == null || sContext.get() == null) && (activity = getActivity()) != null) {
            sContext = new WeakReference<>(activity.getApplicationContext());
        }
        if (sContext != null) {
            return sContext.get();
        }
        return null;
    }

    private static void runTaskInUiThread(Runnable runnable) {
        Activity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(runnable);
        } else {
            new Thread(runnable).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void exitApplication() {
        int iMyPid = Process.myPid();
        printLog(3, String.format(Locale.US, "Exit application by kill process[%d]", Integer.valueOf(iMyPid)));
        Process.killProcess(iMyPid);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void delayExit(long j) throws InterruptedException {
        long jMax = Math.max(0L, j);
        if (sHandler != null) {
            sHandler.postDelayed(new Runnable() { // from class: com.tencent.bugly.agent.GameAgent.1
                @Override // java.lang.Runnable
                public void run() {
                    GameAgent.exitApplication();
                }
            }, jMax);
            return;
        }
        try {
            Thread.sleep(jMax);
            exitApplication();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String convertToCanonicalName(String str) {
        StringBuilder sb = new StringBuilder();
        if (sdkPackageName == null) {
            sdkPackageName = "com.tencent.bugly";
        }
        sb.append(sdkPackageName);
        sb.append(".");
        sb.append(str);
        return sb.toString();
    }

    public static void setSdkPackageName(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        sdkPackageName = str;
    }

    public static void setGameType(int i) {
        sGameType = i;
    }

    public static void setLogEnable(boolean z) {
        sIsDebug = z;
    }

    private static Object newStrategy(Context context, String str, String str2, long j) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        Object objNewInstance;
        if (context != null && ((!TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2)) && (objNewInstance = Reflection.newInstance(convertToCanonicalName(OLD_STRATEGY_CLASS_SUFFIX), new Object[]{context}, Context.class)) != null)) {
            try {
                Class<?> cls = Class.forName(convertToCanonicalName(STRATEGY_CLASS_SUFFIX));
                cls.getDeclaredMethod("setAppChannel", String.class).invoke(objNewInstance, str);
                cls.getDeclaredMethod("setAppVersion", String.class).invoke(objNewInstance, str2);
                cls.getDeclaredMethod("setAppReportDelay", Long.TYPE).invoke(objNewInstance, Long.valueOf(j));
                return objNewInstance;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e2) {
                e2.printStackTrace();
            } catch (NoSuchMethodException e3) {
                e3.printStackTrace();
            } catch (InvocationTargetException e4) {
                e4.printStackTrace();
            } catch (Exception e5) {
                e5.printStackTrace();
            }
        }
        return null;
    }

    public static void initCrashReport(String str, boolean z) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        setLogEnable(z);
        initCrashReport(str, sAppChannel, sAppVersion, sUserId, 0L);
    }

    private static void initCrashReport(final String str, String str2, String str3, final String str4, long j) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        final Context applicationContext = getApplicationContext();
        if (applicationContext == null) {
            printLog(4, "Context is null. bugly initialize terminated.");
        } else {
            if (TextUtils.isEmpty(str)) {
                printLog(4, "Please input appid when initCrashReport.");
                return;
            }
            sHandler = new Handler(Looper.getMainLooper());
            final Object objNewStrategy = newStrategy(applicationContext, str2, str3, j);
            runTaskInUiThread(new Runnable() { // from class: com.tencent.bugly.agent.GameAgent.2
                /* JADX WARN: Removed duplicated region for block: B:13:0x0056  */
                @Override // java.lang.Runnable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void run() throws java.lang.NoSuchMethodException, java.lang.ClassNotFoundException, java.lang.SecurityException {
                    /*
                        r11 = this;
                        boolean r0 = com.tencent.bugly.agent.GameAgent.access$400()
                        java.lang.Object r1 = r1
                        r2 = 2
                        r3 = 3
                        r4 = 1
                        r5 = 0
                        if (r1 == 0) goto L56
                        r1 = 0
                        java.lang.String r6 = "crashreport.CrashReport$UserStrategy"
                        java.lang.String r6 = com.tencent.bugly.agent.GameAgent.access$500(r6)     // Catch: java.lang.Exception -> L19 java.lang.ClassNotFoundException -> L1e
                        java.lang.Class r6 = java.lang.Class.forName(r6)     // Catch: java.lang.Exception -> L19 java.lang.ClassNotFoundException -> L1e
                        r1 = r6
                        goto L22
                    L19:
                        r6 = move-exception
                        r6.printStackTrace()
                        goto L22
                    L1e:
                        r6 = move-exception
                        r6.printStackTrace()
                    L22:
                        if (r1 == 0) goto L56
                        java.lang.String r6 = "crashreport.CrashReport"
                        java.lang.String r6 = com.tencent.bugly.agent.GameAgent.access$500(r6)
                        java.lang.String r7 = "initCrashReport"
                        r8 = 4
                        java.lang.Object[] r9 = new java.lang.Object[r8]
                        android.content.Context r10 = r2
                        r9[r5] = r10
                        java.lang.String r10 = r3
                        r9[r4] = r10
                        java.lang.Boolean r10 = java.lang.Boolean.valueOf(r0)
                        r9[r2] = r10
                        java.lang.Object r10 = r1
                        r9[r3] = r10
                        java.lang.Class[] r8 = new java.lang.Class[r8]
                        java.lang.Class<android.content.Context> r10 = android.content.Context.class
                        r8[r5] = r10
                        java.lang.Class<java.lang.String> r10 = java.lang.String.class
                        r8[r4] = r10
                        java.lang.Class r10 = java.lang.Boolean.TYPE
                        r8[r2] = r10
                        r8[r3] = r1
                        com.tencent.bugly.agent.GameAgent.Reflection.access$100(r6, r7, r9, r8)
                        r1 = 1
                        goto L57
                    L56:
                        r1 = 0
                    L57:
                        if (r1 != 0) goto L82
                        java.lang.String r1 = "crashreport.CrashReport"
                        java.lang.String r1 = com.tencent.bugly.agent.GameAgent.access$500(r1)
                        java.lang.String r6 = "initCrashReport"
                        java.lang.Object[] r7 = new java.lang.Object[r3]
                        android.content.Context r8 = r2
                        r7[r5] = r8
                        java.lang.String r8 = r3
                        r7[r4] = r8
                        java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
                        r7[r2] = r0
                        java.lang.Class[] r0 = new java.lang.Class[r3]
                        java.lang.Class<android.content.Context> r3 = android.content.Context.class
                        r0[r5] = r3
                        java.lang.Class<java.lang.String> r3 = java.lang.String.class
                        r0[r4] = r3
                        java.lang.Class r3 = java.lang.Boolean.TYPE
                        r0[r2] = r3
                        com.tencent.bugly.agent.GameAgent.Reflection.access$100(r1, r6, r7, r0)
                    L82:
                        java.lang.String r0 = r4
                        com.tencent.bugly.agent.GameAgent.setUserId(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.agent.GameAgent.AnonymousClass2.run():void");
                }
            });
        }
    }

    public static void setAppVersion(final String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        sAppVersion = str;
        runTaskInUiThread(new Runnable() { // from class: com.tencent.bugly.agent.GameAgent.3
            @Override // java.lang.Runnable
            public void run() throws NoSuchMethodException, SecurityException {
                Reflection.invokeStaticMethod(GameAgent.convertToCanonicalName(GameAgent.CRASH_REPORT_CLASS_SUFFIX), "setAppVersion", new Object[]{GameAgent.getApplicationContext(), str}, Context.class, String.class);
            }
        });
    }

    public static void setAppChannel(final String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        sAppChannel = str;
        runTaskInUiThread(new Runnable() { // from class: com.tencent.bugly.agent.GameAgent.4
            @Override // java.lang.Runnable
            public void run() throws NoSuchMethodException, SecurityException {
                Reflection.invokeStaticMethod(GameAgent.convertToCanonicalName(GameAgent.CRASH_REPORT_CLASS_SUFFIX), "setAppChannel", new Object[]{GameAgent.getApplicationContext(), str}, Context.class, String.class);
            }
        });
    }

    public static void setUserId(final String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        sUserId = str;
        runTaskInUiThread(new Runnable() { // from class: com.tencent.bugly.agent.GameAgent.5
            @Override // java.lang.Runnable
            public void run() throws NoSuchMethodException, SecurityException {
                Reflection.invokeStaticMethod(GameAgent.convertToCanonicalName(GameAgent.CRASH_REPORT_CLASS_SUFFIX), "setUserId", new Object[]{GameAgent.getApplicationContext(), str}, Context.class, String.class);
            }
        });
    }

    public static void setUserSceneTag(final int i) {
        runTaskInUiThread(new Runnable() { // from class: com.tencent.bugly.agent.GameAgent.6
            @Override // java.lang.Runnable
            public void run() throws NoSuchMethodException, SecurityException {
                Reflection.invokeStaticMethod(GameAgent.convertToCanonicalName(GameAgent.CRASH_REPORT_CLASS_SUFFIX), "setUserSceneTag", new Object[]{GameAgent.getApplicationContext(), Integer.valueOf(i)}, Context.class, Integer.TYPE);
            }
        });
    }

    public static void putUserData(final String str, final String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        runTaskInUiThread(new Runnable() { // from class: com.tencent.bugly.agent.GameAgent.7
            @Override // java.lang.Runnable
            public void run() throws NoSuchMethodException, SecurityException {
                Reflection.invokeStaticMethod(GameAgent.convertToCanonicalName(GameAgent.CRASH_REPORT_CLASS_SUFFIX), "putUserData", new Object[]{GameAgent.getApplicationContext(), str, str2}, Context.class, String.class, String.class);
            }
        });
    }

    public static void removeUserData(final String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        runTaskInUiThread(new Runnable() { // from class: com.tencent.bugly.agent.GameAgent.8
            @Override // java.lang.Runnable
            public void run() throws NoSuchMethodException, SecurityException {
                Reflection.invokeStaticMethod(GameAgent.convertToCanonicalName(GameAgent.CRASH_REPORT_CLASS_SUFFIX), "removeUserData", new Object[]{GameAgent.getApplicationContext(), str}, Context.class, String.class);
            }
        });
    }

    public static void setSdkConfig(final String str, final String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        runTaskInUiThread(new Runnable() { // from class: com.tencent.bugly.agent.GameAgent.9
            @Override // java.lang.Runnable
            public void run() throws NoSuchMethodException, SecurityException {
                Reflection.invokeStaticMethod(GameAgent.convertToCanonicalName(GameAgent.CRASH_REPORT_CLASS_SUFFIX), "putSdkData", new Object[]{GameAgent.getApplicationContext(), "SDK_" + str, str2}, Context.class, String.class, String.class);
            }
        });
    }

    public static void setLog(int i, final String str, final String str2) {
        final String str3;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        switch (i) {
            case 0:
                str3 = "v";
                break;
            case 1:
                str3 = o.aq;
                break;
            case 2:
                str3 = o.au;
                break;
            case 3:
                str3 = "w";
                break;
            case 4:
                str3 = "e";
                break;
            default:
                str3 = null;
                break;
        }
        if (str3 != null) {
            runTaskInUiThread(new Runnable() { // from class: com.tencent.bugly.agent.GameAgent.10
                @Override // java.lang.Runnable
                public void run() throws NoSuchMethodException, SecurityException {
                    Reflection.invokeStaticMethod(GameAgent.convertToCanonicalName("crashreport.BuglyLog"), str3, new Object[]{str, str2}, String.class, String.class);
                }
            });
        }
    }

    private static void postCocosLuaException(final int i, String str, final String str2, String str3, final boolean z) {
        final String str4;
        try {
            if (str3.startsWith("stack traceback")) {
                str3 = str3.substring(str3.indexOf("\n") + 1, str3.length()).trim();
            }
            int iIndexOf = str3.indexOf("\n");
            if (iIndexOf > 0) {
                str3 = str3.substring(iIndexOf + 1, str3.length());
            }
            int iIndexOf2 = str3.indexOf("\n");
            String strSubstring = iIndexOf2 > 0 ? str3.substring(0, iIndexOf2) : str3;
            int iIndexOf3 = strSubstring.indexOf("]:");
            if (str == null || str.length() == 0) {
                str = iIndexOf3 != -1 ? strSubstring.substring(0, iIndexOf3 + 1) : str2;
            }
        } catch (Throwable unused) {
            if (str == null || str.length() == 0) {
                str4 = str2;
            }
        }
        str4 = str;
        final String str5 = str3;
        runTaskInUiThread(new Runnable() { // from class: com.tencent.bugly.agent.GameAgent.11
            @Override // java.lang.Runnable
            public void run() throws InterruptedException, NoSuchMethodException, SecurityException {
                Reflection.invokeStaticMethod(GameAgent.convertToCanonicalName("crashreport.inner.InnerApi"), "postCocos2dxCrashAsync", new Object[]{Integer.valueOf(i), str4, str2, str5}, Integer.TYPE, String.class, String.class, String.class);
                if (z) {
                    GameAgent.delayExit(3000L);
                }
            }
        });
    }

    private static void postCocosJsException(final int i, final String str, final String str2, final String str3, final boolean z) {
        runTaskInUiThread(new Runnable() { // from class: com.tencent.bugly.agent.GameAgent.12
            @Override // java.lang.Runnable
            public void run() throws InterruptedException, NoSuchMethodException, SecurityException {
                Reflection.invokeStaticMethod(GameAgent.convertToCanonicalName("crashreport.inner.InnerApi"), "postCocos2dxCrashAsync", new Object[]{Integer.valueOf(i), str, str2, str3}, Integer.TYPE, String.class, String.class, String.class);
                if (z) {
                    GameAgent.delayExit(3000L);
                }
            }
        });
    }

    private static void postUnityException(final String str, final String str2, final String str3, final boolean z) {
        runTaskInUiThread(new Runnable() { // from class: com.tencent.bugly.agent.GameAgent.13
            @Override // java.lang.Runnable
            public void run() throws InterruptedException, NoSuchMethodException, SecurityException {
                Reflection.invokeStaticMethod(GameAgent.convertToCanonicalName("crashreport.inner.InnerApi"), "postU3dCrashAsync", new Object[]{str, str2, str3}, String.class, String.class, String.class);
                if (z) {
                    GameAgent.delayExit(3000L);
                }
            }
        });
    }

    public static void postException(int i, String str, String str2, String str3, boolean z) {
        switch (i) {
            case 4:
                postUnityException(str, str2, str3, z);
                break;
            case 5:
                postCocosJsException(i, str, str2, str3, z);
                break;
            case 6:
                postCocosLuaException(i, str, str2, str3, z);
                break;
            default:
                printLog(4, "The category of exception posted is unknown: " + String.valueOf(i));
                break;
        }
    }

    private static class Reflection {
        private Reflection() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Object getStaticField(String str, String str2, Object obj) throws NoSuchFieldException {
            try {
                Field declaredField = Class.forName(str).getDeclaredField(str2);
                declaredField.setAccessible(true);
                return declaredField.get(obj);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
                return null;
            } catch (NoSuchFieldException e3) {
                e3.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Object invokeStaticMethod(String str, String str2, Object[] objArr, Class<?>... clsArr) throws NoSuchMethodException, SecurityException {
            try {
                Method declaredMethod = Class.forName(str).getDeclaredMethod(str2, clsArr);
                declaredMethod.setAccessible(true);
                return declaredMethod.invoke(null, objArr);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
                return null;
            } catch (NoSuchMethodException e3) {
                e3.printStackTrace();
                return null;
            } catch (InvocationTargetException e4) {
                e4.printStackTrace();
                return null;
            } catch (Exception e5) {
                e5.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Object newInstance(String str, Object[] objArr, Class<?>... clsArr) throws ClassNotFoundException {
            try {
                Class<?> cls = Class.forName(str);
                if (objArr == null) {
                    return cls.newInstance();
                }
                return cls.getConstructor(clsArr).newInstance(objArr);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
                return null;
            } catch (InstantiationException e3) {
                e3.printStackTrace();
                return null;
            } catch (NoSuchMethodException e4) {
                e4.printStackTrace();
                return null;
            } catch (InvocationTargetException e5) {
                e5.printStackTrace();
                return null;
            } catch (Exception e6) {
                e6.printStackTrace();
                return null;
            }
        }
    }
}
