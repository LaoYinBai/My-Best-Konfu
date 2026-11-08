package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import java.util.Iterator;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class ar implements ServiceConnection {
    final /* synthetic */ an a;

    ar(an anVar) {
        this.a = anVar;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        synchronized (this.a) {
            this.a.f70a = new Messenger(iBinder);
            this.a.c = false;
            Iterator it = this.a.f73a.iterator();
            while (it.hasNext()) {
                try {
                    this.a.f70a.send((Message) it.next());
                } catch (RemoteException e) {
                    com.xiaomi.channel.commonutils.logger.b.a(e);
                }
            }
            this.a.f73a.clear();
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        this.a.f70a = null;
        this.a.c = false;
    }
}
