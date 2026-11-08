package anetwork.channel.entity;

import android.os.RemoteException;
import anet.channel.bytes.ByteArray;
import anetwork.channel.aidl.DefaultProgressEvent;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class f implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ int f693a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ ByteArray f694b;

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ int f695c;
    final /* synthetic */ anetwork.channel.aidl.g d;
    final /* synthetic */ d e;

    f(d dVar, int i, ByteArray byteArray, int i2, anetwork.channel.aidl.g gVar) {
        this.e = dVar;
        this.f693a = i;
        this.f694b = byteArray;
        this.f695c = i2;
        this.d = gVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (this.e.d) {
                try {
                    if (this.e.f689c != null) {
                        this.e.f689c.a(this.f694b);
                    } else {
                        this.e.f689c = new anetwork.channel.aidl.a.d();
                        this.e.f689c.a(this.e.e, this.f695c);
                        this.e.f689c.a(this.f694b);
                        this.d.a(this.e.f689c);
                    }
                } catch (Exception unused) {
                    if (this.e.f689c == null) {
                    } else {
                        this.e.f689c.c();
                    }
                }
            } else {
                this.d.a(new DefaultProgressEvent(this.f693a, this.f694b.getDataLength(), this.f695c, this.f694b.getBuffer()));
            }
        } catch (RemoteException unused2) {
        }
    }
}
