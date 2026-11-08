package com.mbridge.msdk.pluginFramework;

import android.app.Service;
import android.content.res.AssetManager;
import android.content.res.Resources;
import com.mbridge.msdk.foundation.tools.s;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class PluginService extends Service {
    protected a a = null;

    public abstract a a();

    @Override // android.content.ContextWrapper, android.content.Context
    public ClassLoader getClassLoader() {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        try {
            this.a = a();
            try {
                this.a.a.a.a(this);
            } catch (Exception e) {
                s.b("PluginServiceContext", "invoke onDestroy error", e);
            }
            super.onCreate();
        } catch (Exception e2) {
            s.b("Download", "", e2);
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        if (this.a != null) {
            return null;
        }
        return super.getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        if (this.a != null) {
            try {
                return super.getResources();
            } catch (Exception unused) {
                return super.getResources();
            }
        }
        return super.getResources();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        return super.getTheme();
    }
}
