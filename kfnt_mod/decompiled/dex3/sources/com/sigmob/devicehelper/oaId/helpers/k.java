package com.sigmob.devicehelper.oaId.helpers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import com.sigmob.devicehelper.oaId.a.g;
import com.sigmob.devicehelper.oaId.helpers.DevicesIDsHelper;
import com.sigmob.logger.SigmobLog;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class k {
    Context a;
    public final LinkedBlockingQueue<IBinder> b = new LinkedBlockingQueue<>(1);
    ServiceConnection c = new ServiceConnection() { // from class: com.sigmob.devicehelper.oaId.helpers.k.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) throws InterruptedException {
            try {
                k.this.b.put(iBinder);
            } catch (Exception e) {
                SigmobLog.e(e.getMessage());
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    };

    public k(Context context) {
        this.a = context;
    }

    private void a(String str) {
        try {
            Intent intent = new Intent("com.bun.msa.action.start.service");
            intent.setClassName("com.mdid.msa", "com.mdid.msa.service.MsaKlService");
            intent.putExtra("com.bun.msa.param.pkgname", str);
            intent.putExtra("com.bun.msa.param.runinset", true);
            if (this.a.startService(intent) != null) {
                Log.d("oaid", "oaid startMsaklServer()  success called with: pkgName = [" + str + "]");
            }
        } catch (Exception e) {
            SigmobLog.e(e.getMessage());
        }
    }

    private boolean a() {
        try {
            return this.a.getPackageManager().getPackageInfo("com.mdid.msa", 0) != null;
        } catch (Exception e) {
            SigmobLog.e(e.getMessage());
            return false;
        }
    }

    public void a(DevicesIDsHelper.AppIdsUpdater appIdsUpdater) {
        if (!a()) {
            Log.d("oaid", "oaid  checkService false ");
            return;
        }
        String packageName = this.a.getPackageName();
        a(packageName);
        Intent intent = new Intent();
        intent.setClassName("com.mdid.msa", "com.mdid.msa.service.MsaIdService");
        intent.setAction("com.bun.msa.action.bindto.service");
        intent.putExtra("com.bun.msa.param.pkgname", packageName);
        if (this.a.bindService(intent, this.c, 1)) {
            try {
                try {
                    Log.d("oaid", "oaid getID()  bindService success called with: pkgName = [" + packageName + "]");
                    String strA = new g.a.C0008a(this.b.take()).a();
                    if (appIdsUpdater != null) {
                        appIdsUpdater.OnIdsAvalid(strA);
                    }
                } catch (Exception e) {
                    SigmobLog.e(e.getMessage());
                }
            } finally {
                this.a.unbindService(this.c);
            }
        }
    }
}
