package com.tachikoma.core;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.tachikoma.core.api.ILogInner;
import com.tachikoma.core.api.ITKExceptionHandlerInner;
import com.tachikoma.core.base.TKPerformancePlugin;
import com.tachikoma.core.bridge.Env;
import com.tachikoma.core.bridge.TKJSContext;
import com.tachikoma.core.manager.TKExportManager;
import com.tachikoma.core.module.handler.TKLifeCycle;
import com.tachikoma.core.utility.TKAsync;
import com.tachikoma.core.utility.TKContextUtil;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class Tachikoma {
    private static final Tachikoma instance = new Tachikoma();
    public static Application sApplication;
    private Map<String, Object> config;
    private ITKExceptionHandlerInner mExceptionHandlerInner;
    private TKExportManager mTKExportManager;
    private ILogInner mTKLog;
    private TKPerformancePlugin performancePlugin;
    private volatile boolean mInit = false;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    private Tachikoma() {
    }

    public static Tachikoma getInstance() {
        return instance;
    }

    public void asyncNewTKJSContext(final TKJSContextInitCallback tKJSContextInitCallback) {
        TKAsync.execute(new Runnable() { // from class: com.tachikoma.core.Tachikoma.1
            @Override // java.lang.Runnable
            public void run() {
                final TKJSContext tKJSContext = new TKJSContext(Tachikoma.sApplication, Tachikoma.this.mTKExportManager);
                tKJSContext.onCreate();
                tKJSContext.context().getContextRef().getLocker().release();
                Tachikoma.this.mHandler.post(new Runnable() { // from class: com.tachikoma.core.Tachikoma.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        tKJSContext.context().getContextRef().getLocker().acquire();
                        tKJSContextInitCallback.onSuccess(tKJSContext);
                    }
                });
            }
        });
    }

    public void destroy() {
    }

    public Map<String, Object> getConfig() {
        return this.config;
    }

    public TKExportManager getTKExportManager() {
        return this.mTKExportManager;
    }

    public void init(Application application) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        sApplication = application;
        TKContextUtil.init(application);
        Env.initEnvironment(application);
        this.mTKExportManager = TKExportManager.getInstance();
        this.mTKExportManager.onCreate();
        registerLifeCycle(application);
    }

    public boolean isInitSuccess() {
        return this.mInit;
    }

    public ILogInner logger() {
        return this.mTKLog;
    }

    @Deprecated
    public TKJSContext newTKJSContext() {
        TKJSContext tKJSContext = new TKJSContext(sApplication, this.mTKExportManager);
        tKJSContext.onCreate();
        return tKJSContext;
    }

    public TKJSContext newTKJSContext(Context context) {
        TKJSContext tKJSContext = new TKJSContext(context, this.mTKExportManager);
        tKJSContext.onCreate();
        return tKJSContext;
    }

    public TKJSContext newTKJSContext(@NonNull ViewGroup viewGroup) {
        TKJSContext tKJSContext = new TKJSContext(viewGroup.getContext(), viewGroup, this.mTKExportManager);
        tKJSContext.onCreate();
        return tKJSContext;
    }

    public void printPerformance(String str, int i) {
        if (getInstance().performancePlugin != null) {
            getInstance().performancePlugin.printPerformance(str, i);
        }
    }

    public void registerLifeCycle(Application application) {
        application.registerActivityLifecycleCallbacks(new TKLifeCycle.ActivityLifecycleCallbacksProxy());
    }

    public void setConfig(Map<String, Object> map) {
        this.config = map;
    }

    public void setExceptionHandler(ITKExceptionHandlerInner iTKExceptionHandlerInner) {
        this.mExceptionHandlerInner = iTKExceptionHandlerInner;
    }

    public void setInitResult(boolean z) {
        this.mInit = z;
    }

    public void setLoggerDelegate(ILogInner iLogInner) {
        this.mTKLog = iLogInner;
    }

    public void setPerformancePlugin(TKPerformancePlugin tKPerformancePlugin) {
        this.performancePlugin = tKPerformancePlugin;
    }
}
