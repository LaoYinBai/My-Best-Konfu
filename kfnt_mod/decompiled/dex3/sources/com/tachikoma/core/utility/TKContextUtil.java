package com.tachikoma.core.utility;

import android.app.Application;
import android.content.Context;
import com.tachikoma.core.debug.TKDebuggerUtils;
import java.lang.reflect.InvocationTargetException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKContextUtil {
    static Context ApplicationContext;
    private final TKContextUtil self = this;

    public static Context getContext() {
        if (ApplicationContext != null) {
            return ApplicationContext;
        }
        try {
            Application application = (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke(null, (Object[]) null);
            if (application != null) {
                ApplicationContext = application;
                return application;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Application application2 = (Application) Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, (Object[]) null);
            if (application2 != null) {
                ApplicationContext = application2;
                return application2;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        throw new IllegalStateException("ContextHolder is not initialed, it is recommend to init with application context.");
    }

    public static void init(Context context) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        ApplicationContext = context.getApplicationContext();
        if (TKDebuggerUtils.hasDebugger(context.getClassLoader())) {
            TKDebuggerUtils.initializeTKDebugger(context);
        }
    }
}
