package com.taobao.accs.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import com.ss.android.socialbase.appdownloader.constants.Constants;
import com.taobao.accs.utl.ALog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class b implements ServiceConnection {
    final /* synthetic */ Intent a;
    final /* synthetic */ Context b;

    b(Intent intent, Context context) {
        this.a = intent;
        this.b = context;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        ALog.d(a.TAG, "bindService connected", "componentName", componentName.toString());
        Messenger messenger = new Messenger(iBinder);
        Message message = new Message();
        try {
            try {
                message.getData().putParcelable(Constants.INTENT, this.a);
                messenger.send(message);
            } catch (Exception e) {
                ALog.e(a.TAG, "dispatch intent with exception", e.toString());
            }
        } finally {
            this.b.unbindService(this);
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        ALog.d(a.TAG, "bindService on disconnect", "componentName", componentName.toString());
        this.b.unbindService(this);
    }
}
