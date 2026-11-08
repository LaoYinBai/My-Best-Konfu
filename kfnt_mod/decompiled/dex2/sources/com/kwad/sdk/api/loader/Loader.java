package com.kwad.sdk.api.loader;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import com.kwad.sdk.api.KsAdSDK;
import com.kwad.sdk.api.SdkConfig;
import com.kwad.sdk.api.core.IKsAdSDK;
import com.kwad.sdk.api.core.KsAdSdkDynamicApi;
import com.kwad.sdk.api.proxy.IComponentProxy;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class Loader {
    static final /* synthetic */ boolean a = !Loader.class.desiredAssertionStatus();
    private volatile Context b;
    private IKsAdSDK c;
    private j d;
    private AtomicBoolean e;

    private static class a {
        private static final Loader a = new Loader();
    }

    private Loader() {
        this.c = null;
        this.d = null;
        this.e = new AtomicBoolean(false);
    }

    @NonNull
    @MainThread
    static synchronized IKsAdSDK a(ClassLoader classLoader) {
        Object objInvoke;
        try {
            KsAdSdkDynamicApi ksAdSdkDynamicApi = (KsAdSdkDynamicApi) IKsAdSDK.class.getAnnotation(KsAdSdkDynamicApi.class);
            if (!a && ksAdSdkDynamicApi == null) {
                throw new AssertionError();
            }
            objInvoke = Class.forName(ksAdSdkDynamicApi.value(), true, classLoader).getDeclaredMethod("get", new Class[0]).invoke(null, new Object[0]);
            if (objInvoke == null) {
                throw new RuntimeException("Can not get sdk form " + classLoader);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return (IKsAdSDK) objInvoke;
    }

    private void a(Context context, String str) {
        g.g(context, str);
    }

    private boolean c(Context context) {
        String strA = a(context);
        String strB = b(context);
        if (TextUtils.isEmpty(strA) && TextUtils.isEmpty(strB)) {
            return false;
        }
        if (!TextUtils.isEmpty(strB) && f.a(strB, strA)) {
            f.a(context, f.b, strB);
            a(context, strA);
            f.a(context, f.a, "");
            strA = strB;
        }
        return !TextUtils.isEmpty(strA);
    }

    private void d(Context context) {
        String strA = f.a(context);
        if (TextUtils.isEmpty(strA) || !strA.equals("3.3.17")) {
            String strA2 = f.a(context, f.b);
            f.a(context, f.b, "");
            f.a(context, f.a, "");
            g.b(g.c(context, strA2));
            f.b(context, "3.3.17");
        }
    }

    public static Loader get() {
        return a.a;
    }

    String a(Context context) {
        return f.a(context, f.b);
    }

    String b(Context context) {
        return f.a(context, f.a);
    }

    public void checkUpdate(IKsAdSDK iKsAdSDK) {
        r.a(this.b, iKsAdSDK);
    }

    public Context getContext() {
        return this.b;
    }

    @MainThread
    public ClassLoader getExternalClassLoader() {
        if (this.d != null) {
            return this.d.b();
        }
        return null;
    }

    @MainThread
    public Resources getExternalResource() {
        if (this.d != null) {
            return this.d.a();
        }
        return null;
    }

    @MainThread
    public IKsAdSDK getKsAdSDKImpl() {
        if (this.d != null) {
            IKsAdSDK iKsAdSDKC = this.d.c();
            iKsAdSDKC.setIsExternal(true);
            return iKsAdSDKC;
        }
        if (this.c == null) {
            this.c = a(this.b != null ? this.b.getClassLoader() : getClass().getClassLoader());
        }
        this.c.setIsExternal(false);
        return this.c;
    }

    public ClassLoader getRealClassLoader() {
        return this.d != null ? this.d.b() : getClass().getClassLoader();
    }

    @MainThread
    public void init(Context context) {
        if (this.e.get()) {
            return;
        }
        this.e.set(true);
        this.b = context.getApplicationContext();
        d(this.b);
        if (c(context)) {
            this.d = j.a(this.b, a(context));
        }
        if (this.d == null) {
            this.c = a(getClass().getClassLoader());
        }
    }

    public boolean isExternalLoaded() {
        return this.d != null;
    }

    @MainThread
    public <T extends IComponentProxy> T newComponentProxy(Context context, Class<?> cls, Object obj) {
        if (!this.e.get()) {
            KsAdSDK.init(context, SdkConfig.create(q.a(context, "sdkconfig")));
        }
        return (T) getKsAdSDKImpl().newComponentProxy(cls, obj);
    }

    @MainThread
    public <T extends IComponentProxy> T newComponentProxyNewProcess(Context context, Class<?> cls, Object obj) {
        if (!this.e.get()) {
            KsAdSDK.init(context, SdkConfig.create(q.a(context, "sdkconfig")));
        }
        return (T) getKsAdSDKImpl().newComponentProxy(cls, obj);
    }

    @MainThread
    public <T> T newInstance(Class<T> cls) {
        return (T) getKsAdSDKImpl().newInstance(cls);
    }
}
