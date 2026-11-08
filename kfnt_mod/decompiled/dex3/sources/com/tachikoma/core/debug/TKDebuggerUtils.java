package com.tachikoma.core.debug;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.v8.V8;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKDebuggerUtils {
    private static Class<?> sDebuggerHelper;
    private static Method sDisableAll;
    private static Method sDisableDebug;
    private static Method sEnableDebug;
    private static Method sGenContextName;
    private static Method sGenScriptName;
    private static Method sInitializeTKDebugger;
    private static Method sOnExecuteScript;

    public static void disableDebug(@NonNull String str) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (sDebuggerHelper != null) {
            try {
                if (sDisableDebug == null) {
                    sDisableDebug = sDebuggerHelper.getDeclaredMethod("disableDebug", String.class);
                }
                sDisableDebug.invoke(null, str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void disableDebugAll() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (sDebuggerHelper != null) {
            try {
                if (sDisableAll == null) {
                    sDisableAll = sDebuggerHelper.getDeclaredMethod("disableDebug", new Class[0]);
                }
                sDisableAll.invoke(null, new Object[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void enableDebug(@NonNull V8 v8, @NonNull String str, @Nullable View view) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (sDebuggerHelper != null) {
            try {
                if (sEnableDebug == null) {
                    sEnableDebug = sDebuggerHelper.getDeclaredMethod("enableDebug", V8.class, String.class, View.class);
                }
                sEnableDebug.invoke(null, v8, str, view);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String genContextName() {
        if (sDebuggerHelper == null) {
            return "";
        }
        try {
            if (sGenContextName == null) {
                sGenContextName = sDebuggerHelper.getDeclaredMethod("genContextName", new Class[0]);
            }
            return sGenContextName.invoke(null, new Object[0]).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String genScriptName(@Nullable String str) {
        if (sDebuggerHelper == null) {
            return str;
        }
        try {
            if (sGenScriptName == null) {
                sGenScriptName = sDebuggerHelper.getDeclaredMethod("genScriptName", new Class[0]);
            }
            return sGenScriptName.invoke(null, new Object[0]).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public static boolean hasDebugger(@NonNull ClassLoader classLoader) {
        try {
            sDebuggerHelper = classLoader.loadClass("com.tachikoma.debugger.TKDebuggerHelper");
            return sDebuggerHelper != null;
        } catch (Exception unused) {
            return false;
        }
    }

    public static void initializeTKDebugger(@NonNull Context context) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (sDebuggerHelper != null) {
            try {
                if (sInitializeTKDebugger == null) {
                    sInitializeTKDebugger = sDebuggerHelper.getDeclaredMethod("initializeTKDebugger", Context.class);
                }
                sInitializeTKDebugger.invoke(null, context);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void onExecuteScript(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable String str4, int i) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (sDebuggerHelper != null) {
            try {
                if (sOnExecuteScript == null) {
                    sOnExecuteScript = sDebuggerHelper.getDeclaredMethod("onExecuteScript", String.class, String.class, String.class, String.class, Integer.TYPE);
                }
                sOnExecuteScript.invoke(null, str, str2, str3, str4, Integer.valueOf(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
