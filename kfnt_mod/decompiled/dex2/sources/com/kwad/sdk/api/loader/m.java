package com.kwad.sdk.api.loader;

import android.annotation.SuppressLint;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Resources;
import androidx.appcompat.view.ContextThemeWrapper;
import com.kwad.sdk.api.core.ResContext;

@SuppressLint({"RestrictedApi"})
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class m extends ContextThemeWrapper implements ResContext {
    private final ContextThemeWrapper a;
    private Resources.Theme b;
    private int c;

    public m(ContextThemeWrapper contextThemeWrapper) {
        super(contextThemeWrapper, contextThemeWrapper.getThemeResId());
        this.a = contextThemeWrapper;
        this.c = ((Integer) Reflect.a(contextThemeWrapper).d("getThemeResId").a()).intValue();
    }

    public Context getApplicationContext() {
        return Wrapper.wrapContextIfNeed(super.getApplicationContext());
    }

    public Context getBaseContext() {
        return Wrapper.wrapContextIfNeed(super.getBaseContext());
    }

    public ClassLoader getClassLoader() {
        return Wrapper.replaceExternalClassLoader(super.getClassLoader());
    }

    @Override // com.kwad.sdk.api.core.ResContext
    public Context getDelegatedContext() {
        return this.a;
    }

    public Resources getResources() {
        return Wrapper.replaceExternalResources(super.getResources());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Object getSystemService(String str) {
        return Wrapper.wrapSystemService(this.a.getSystemService(str), str, this);
    }

    public Resources.Theme getTheme() {
        Resources.Theme theme;
        try {
            theme = super.getTheme();
        } catch (Exception e) {
            e.printStackTrace();
            theme = null;
        }
        if (this.b == null || this.b == theme) {
            this.b = Wrapper.replaceTheme(theme, this.b, this.c);
        }
        return this.b;
    }

    public void registerComponentCallbacks(ComponentCallbacks componentCallbacks) {
        this.a.registerComponentCallbacks(componentCallbacks);
    }

    public void setTheme(int i) {
        this.c = i;
        super.setTheme(i);
    }

    public void unregisterComponentCallbacks(ComponentCallbacks componentCallbacks) {
        this.a.unregisterComponentCallbacks(componentCallbacks);
    }
}
