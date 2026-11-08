package com.huawei.hms.support.api.push.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.huawei.hms.support.log.HMSLog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private ServiceConnection a;
    private Messenger b = null;

    public boolean a(Context context, final Bundle bundle, Intent intent) {
        final Context applicationContext = context.getApplicationContext();
        this.a = new ServiceConnection() { // from class: com.huawei.hms.support.api.push.a.a.1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) throws RemoteException {
                HMSLog.i("RemoteService", "remote service onConnected");
                a.this.b = new Messenger(iBinder);
                Message messageObtain = Message.obtain();
                messageObtain.setData(bundle);
                try {
                    a.this.b.send(messageObtain);
                } catch (RemoteException unused) {
                    HMSLog.i("RemoteService", "remote service message send failed");
                }
                HMSLog.i("RemoteService", "remote service unbindservice");
                applicationContext.unbindService(a.this.a);
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                HMSLog.i("RemoteService", "remote service onDisconnected");
                a.this.b = null;
            }
        };
        HMSLog.i("RemoteService", "remote service bind service start");
        return applicationContext.bindService(intent, this.a, 1);
    }
}
