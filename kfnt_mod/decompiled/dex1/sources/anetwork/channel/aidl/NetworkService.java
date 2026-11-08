package anetwork.channel.aidl;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import anet.channel.util.ALog;
import anetwork.channel.aidl.b;
import anetwork.channel.aidl.h;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class NetworkService extends Service {

    /* renamed from: b, reason: collision with root package name */
    private Context f642b;

    /* renamed from: c, reason: collision with root package name */
    private h.a f643c = null;
    private h.a d = null;

    /* renamed from: a, reason: collision with root package name */
    b.a f641a = new c(this);

    @Override // android.app.Service
    public void onDestroy() {
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return 2;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        this.f642b = getApplicationContext();
        if (ALog.isPrintLog(2)) {
            ALog.i("anet.NetworkService", "onBind:" + intent.getAction(), null, new Object[0]);
        }
        this.f643c = new anetwork.channel.d.a(this.f642b);
        this.d = new anetwork.channel.e.a(this.f642b);
        if (b.class.getName().equals(intent.getAction())) {
            return this.f641a;
        }
        return null;
    }
}
