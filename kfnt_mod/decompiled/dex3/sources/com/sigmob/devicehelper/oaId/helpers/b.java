package com.sigmob.devicehelper.oaId.helpers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.sigmob.devicehelper.oaId.a.b;
import com.sigmob.devicehelper.oaId.helpers.DevicesIDsHelper;
import com.sigmob.logger.SigmobLog;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b {
    public final LinkedBlockingQueue<IBinder> a = new LinkedBlockingQueue<>(1);
    ServiceConnection b = new ServiceConnection() { // from class: com.sigmob.devicehelper.oaId.helpers.b.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) throws InterruptedException {
            try {
                b.this.a.put(iBinder);
            } catch (Exception e) {
                SigmobLog.e(e.getMessage());
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    };
    private Context c;

    public b(Context context) {
        this.c = context;
    }

    public void a(DevicesIDsHelper.AppIdsUpdater appIdsUpdater) {
        if (a()) {
            Intent intent = new Intent("com.uodis.opendevice.OPENIDS_SERVICE");
            intent.setPackage("com.huawei.hwid");
            if (this.c.bindService(intent, this.b, 1)) {
                try {
                    try {
                        b.a aVar = new b.a(this.a.take(), this.c);
                        String strA = aVar.a();
                        aVar.b();
                        if (appIdsUpdater != null) {
                            appIdsUpdater.OnIdsAvalid(strA);
                        }
                    } catch (Exception e) {
                        SigmobLog.e(e.getMessage());
                    }
                } finally {
                    this.c.unbindService(this.b);
                }
            }
        }
    }

    public boolean a() {
        try {
            return this.c.getPackageManager().getPackageInfo("com.huawei.hwid", 0) != null;
        } catch (Throwable unused) {
            return false;
        }
    }
}
