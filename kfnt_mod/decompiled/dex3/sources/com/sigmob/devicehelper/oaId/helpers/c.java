package com.sigmob.devicehelper.oaId.helpers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.sigmob.devicehelper.oaId.a.c;
import com.sigmob.devicehelper.oaId.helpers.DevicesIDsHelper;
import com.sigmob.logger.SigmobLog;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c {
    com.sigmob.devicehelper.oaId.a.c a;
    public final LinkedBlockingQueue<IBinder> b = new LinkedBlockingQueue<>(1);
    ServiceConnection c = new ServiceConnection() { // from class: com.sigmob.devicehelper.oaId.helpers.c.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                c.this.b.put(iBinder);
            } catch (Throwable unused) {
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    };
    private Context d;

    public c(Context context) {
        this.d = context;
    }

    private boolean a() {
        try {
            return this.d.getPackageManager().getPackageInfo("com.zui.deviceidservice", 0) != null;
        } catch (Throwable unused) {
            return false;
        }
    }

    public void a(DevicesIDsHelper.AppIdsUpdater appIdsUpdater) {
        if (a()) {
            Intent intent = new Intent();
            intent.setClassName("com.zui.deviceidservice", "com.zui.deviceidservice.DeviceidService");
            if (this.d.bindService(intent, this.c, 1)) {
                try {
                    try {
                        this.a = new c.a.C0005a(this.b.take());
                        if (this.a != null) {
                            String strA = this.a.a();
                            if (appIdsUpdater != null) {
                                appIdsUpdater.OnIdsAvalid(strA);
                            }
                        }
                    } catch (Throwable th) {
                        SigmobLog.e(th.getMessage());
                    }
                } finally {
                    this.d.unbindService(this.c);
                }
            }
        }
    }
}
