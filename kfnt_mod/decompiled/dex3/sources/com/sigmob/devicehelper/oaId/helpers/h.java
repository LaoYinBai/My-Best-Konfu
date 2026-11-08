package com.sigmob.devicehelper.oaId.helpers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import com.sigmob.devicehelper.oaId.a.f;
import com.sigmob.devicehelper.oaId.helpers.DevicesIDsHelper;
import com.sigmob.logger.SigmobLog;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class h {
    public final LinkedBlockingQueue<IBinder> a = new LinkedBlockingQueue<>(1);
    ServiceConnection b = new ServiceConnection() { // from class: com.sigmob.devicehelper.oaId.helpers.h.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) throws InterruptedException {
            Log.d("oaid", "oaid onServiceConnected() called with: name = [" + componentName + "], service = [" + iBinder + "]");
            try {
                h.this.a.put(iBinder);
            } catch (Exception e) {
                SigmobLog.e(e.getMessage());
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    };
    private Context c;

    public h(Context context) {
        this.c = context;
    }

    public void a(DevicesIDsHelper.AppIdsUpdater appIdsUpdater) {
        if (a()) {
            Intent intent = new Intent();
            intent.setClassName("com.samsung.android.deviceidservice", "com.samsung.android.deviceidservice.DeviceIdService");
            try {
                if (this.c.bindService(intent, this.b, 1)) {
                    try {
                        String strA = new f.a(this.a.take()).a();
                        if (appIdsUpdater != null) {
                            appIdsUpdater.OnIdsAvalid(strA);
                        }
                    } catch (Exception e) {
                        SigmobLog.e(e.getMessage());
                    }
                }
            } finally {
                this.c.unbindService(this.b);
            }
        }
    }

    public boolean a() {
        try {
            return this.c.getPackageManager().getPackageInfo("com.samsung.android.deviceidservice", 0) != null;
        } catch (Throwable unused) {
            return false;
        }
    }
}
