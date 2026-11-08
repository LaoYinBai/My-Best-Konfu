package anetwork.channel.aidl.a;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import anet.channel.util.ALog;
import anetwork.channel.aidl.b;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class h implements ServiceConnection {
    h() {
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        if (ALog.isPrintLog(2)) {
            ALog.i("anet.RemoteGetter", "ANet_Service Disconnected", null, new Object[0]);
        }
        g.f665a = null;
        g.f667c = false;
        if (g.d != null) {
            g.d.countDown();
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (ALog.isPrintLog(2)) {
            ALog.i("anet.RemoteGetter", "[onServiceConnected]ANet_Service start success. ANet run with service mode", null, new Object[0]);
        }
        synchronized (g.class) {
            g.f665a = b.a.a(iBinder);
            if (g.d != null) {
                g.d.countDown();
            }
        }
        g.f666b = false;
        g.f667c = false;
    }
}
