package com.xiaomi.push.service;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import java.util.Iterator;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class bj implements ServiceConnection {
    final /* synthetic */ bh a;

    bj(bh bhVar) {
        this.a = bhVar;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        synchronized (this.a) {
            this.a.b = new Messenger(iBinder);
            this.a.f926b = false;
            Iterator it = this.a.f924a.iterator();
            while (it.hasNext()) {
                try {
                    this.a.b.send((Message) it.next());
                } catch (RemoteException e) {
                    com.xiaomi.channel.commonutils.logger.b.a(e);
                }
            }
            this.a.f924a.clear();
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        this.a.b = null;
        this.a.f926b = false;
    }
}
