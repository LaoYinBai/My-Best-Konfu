package com.mbridge.msdk.shell;

import android.content.Intent;
import android.os.IBinder;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.mbdownload.c;
import com.mbridge.msdk.pluginFramework.PluginService;
import com.mbridge.msdk.pluginFramework.a;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBService extends PluginService {
    @Override // com.mbridge.msdk.pluginFramework.PluginService, android.app.Service
    public void onCreate() {
        super.onCreate();
        this.a.a.a();
    }

    @Override // com.mbridge.msdk.pluginFramework.PluginService
    public final a a() {
        try {
            return new a(new a.C0134a(new c()));
        } catch (Exception e) {
            s.b("Download", "Find Provider Error", e);
            return null;
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.a.a.a(intent);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return this.a.a.a(intent, i, i2);
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.a.a.b();
    }
}
