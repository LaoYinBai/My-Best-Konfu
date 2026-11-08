package com.kwad.sdk.api.loader;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import com.kwad.sdk.api.core.ResContext;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class Wrapper {
    private static final String CLAZZ_NAME = "com.kwad.sdk.api.loader.Wrapper";
    private static final int COUNT_LIMIT_AUTO_UN_WRAP = 5;
    private static final int COUNT_LIMIT_AUTO_UN_WRAP_APPLICATION = 15;
    private static final int COUNT_LIMIT_SAME_STACK_TRACE = 5;
    private static final String METHOD_GET_BASE_CONTEXT = "getBaseContext";
    private static final String METHOD_WRAP_CONTEXT = "wrapContextIfNeed";
    private static final String TAG = "Wrapper";
    private static final ThreadLocal<a> sAutoUnWrapModelTL = new ThreadLocal<>();
    private static final List<String> sAutoUnWrapStackList = new ArrayList();
    private static Map<Context, Context> sResContextCache = new WeakHashMap();

    private static class a {
        private WeakReference<Context> a;
        private int b;
        private StackTraceElement[] c;
        private int d;

        private a() {
            this.a = new WeakReference<>(null);
            this.b = 0;
            this.c = null;
            this.d = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a() {
            this.a = new WeakReference<>(null);
            this.b = 0;
            this.c = null;
            this.d = 0;
        }

        static /* synthetic */ int b(a aVar) {
            int i = aVar.b;
            aVar.b = i + 1;
            return i;
        }

        static /* synthetic */ int f(a aVar) {
            int i = aVar.d;
            aVar.d = i + 1;
            return i;
        }
    }

    private static List<String> getAutoUnWrapStackList() {
        if (sAutoUnWrapStackList.isEmpty()) {
            sAutoUnWrapStackList.add("com.sensorsdata.analytics.android.sdk");
        }
        return sAutoUnWrapStackList;
    }

    private static boolean needAutoUnWrap(Context context, a aVar) {
        String str;
        String str2;
        Context context2 = sResContextCache.get(context);
        String name = context2 != null ? context2.getClass().getName() : "";
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (!Arrays.equals(stackTrace, aVar.c)) {
            if (aVar.c != null) {
                aVar.a();
                return false;
            }
            aVar.c = stackTrace;
            int i = 0;
            int i2 = 0;
            while (i < stackTrace.length) {
                StackTraceElement stackTraceElement = stackTrace[i];
                String className = stackTraceElement.getClassName();
                Iterator<String> it = getAutoUnWrapStackList().iterator();
                while (it.hasNext()) {
                    if (className.contains(it.next())) {
                        str = TAG;
                        str2 = "needAutoUnWrap true 命中白名单";
                    }
                }
                String methodName = stackTraceElement.getMethodName();
                i++;
                if (i < stackTrace.length && CLAZZ_NAME.equals(className) && METHOD_WRAP_CONTEXT.equals(methodName)) {
                    StackTraceElement stackTraceElement2 = stackTrace[i];
                    if (TextUtils.equals(name, stackTraceElement2.getClassName()) && METHOD_GET_BASE_CONTEXT.equals(stackTraceElement2.getMethodName()) && (i2 = i2 + 1) >= 5) {
                        return true;
                    }
                }
            }
            return false;
        }
        a.f(aVar);
        aVar.c = stackTrace;
        if (aVar.d < 5) {
            return false;
        }
        str = TAG;
        str2 = "needAutoUnWrap true 连续相同堆栈";
        Log.d(str, str2);
        return true;
    }

    static ClassLoader replaceExternalClassLoader(ClassLoader classLoader) {
        ClassLoader externalClassLoader = Loader.get().getExternalClassLoader();
        return externalClassLoader != null ? externalClassLoader : classLoader;
    }

    static Resources replaceExternalResources(Resources resources) {
        Resources externalResource = Loader.get().getExternalResource();
        return externalResource != null ? externalResource : resources;
    }

    static Resources.Theme replaceTheme(Resources.Theme theme, Resources.Theme theme2, int i) {
        Resources externalResource = Loader.get().getExternalResource();
        if (externalResource == null) {
            return theme;
        }
        if (theme2 != null) {
            return theme2;
        }
        Resources.Theme themeNewTheme = externalResource.newTheme();
        themeNewTheme.applyStyle(i, true);
        return themeNewTheme;
    }

    private static boolean returnUnWrappedContext(Context context) {
        a aVar = sAutoUnWrapModelTL.get();
        if (aVar == null) {
            sAutoUnWrapModelTL.set(new a());
            return false;
        }
        if (aVar.a.get() != context) {
            aVar.a();
            aVar.a = new WeakReference(context);
            return false;
        }
        a.b(aVar);
        if (aVar.b < (context instanceof Application ? 15 : 5) || !needAutoUnWrap(context, aVar)) {
            return false;
        }
        aVar.a();
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Keep
    public static Context unwrapContextIfNeed(Context context) {
        ResContext resContext = context instanceof ResContext ? (ResContext) context : null;
        Context delegatedContext = context;
        while (delegatedContext instanceof ContextWrapper) {
            if (delegatedContext instanceof Activity) {
                return delegatedContext;
            }
            if (delegatedContext instanceof ResContext) {
                resContext = delegatedContext;
                delegatedContext = resContext.getDelegatedContext();
            } else {
                delegatedContext = ((ContextWrapper) delegatedContext).getBaseContext();
            }
        }
        return resContext != null ? resContext.getDelegatedContext() : context;
    }

    @Nullable
    @Keep
    public static Context wrapContextIfNeed(@Nullable Context context) {
        if (!Loader.get().isExternalLoaded()) {
            return context;
        }
        if (context == null) {
            return null;
        }
        if ((context instanceof ResContext) || returnUnWrappedContext(context)) {
            return context;
        }
        if (context instanceof ContextThemeWrapper) {
            Context context2 = sResContextCache.get(context);
            if (context2 != null) {
                return context2;
            }
            l lVar = new l((ContextThemeWrapper) context);
            sResContextCache.put(context, lVar);
            return lVar;
        }
        if (context instanceof androidx.appcompat.view.ContextThemeWrapper) {
            Context context3 = sResContextCache.get(context);
            if (context3 != null) {
                return context3;
            }
            androidx.appcompat.view.ContextThemeWrapper mVar = new m((androidx.appcompat.view.ContextThemeWrapper) context);
            sResContextCache.put(context, mVar);
            return mVar;
        }
        if (context instanceof ContextWrapper) {
            Context context4 = sResContextCache.get(context);
            if (context4 != null) {
                return context4;
            }
            n nVar = new n(context);
            sResContextCache.put(context, nVar);
            return nVar;
        }
        Context context5 = sResContextCache.get(context);
        if (context5 != null) {
            return context5;
        }
        n nVar2 = new n(context);
        sResContextCache.put(context, nVar2);
        return nVar2;
    }

    @Keep
    public static LayoutInflater wrapInflaterIfNeed(LayoutInflater layoutInflater) {
        if (!Loader.get().isExternalLoaded()) {
            return layoutInflater;
        }
        Context context = layoutInflater.getContext();
        if (context instanceof ResContext) {
            return layoutInflater;
        }
        Context contextWrapContextIfNeed = wrapContextIfNeed(context);
        return contextWrapContextIfNeed instanceof ResContext ? layoutInflater.cloneInContext(contextWrapContextIfNeed) : layoutInflater;
    }

    static Object wrapSystemService(Object obj, String str, Context context) {
        if (!"layout_inflater".equals(str) || !(obj instanceof LayoutInflater)) {
            return obj;
        }
        LayoutInflater layoutInflater = (LayoutInflater) obj;
        return layoutInflater.getContext() instanceof ResContext ? layoutInflater : layoutInflater.cloneInContext(context);
    }
}
