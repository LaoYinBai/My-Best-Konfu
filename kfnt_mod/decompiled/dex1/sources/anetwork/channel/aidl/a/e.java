package anetwork.channel.aidl.a;

import android.os.Handler;
import android.os.RemoteException;
import anet.channel.util.ALog;
import anetwork.channel.aidl.DefaultFinishEvent;
import anetwork.channel.aidl.DefaultProgressEvent;
import anetwork.channel.aidl.ParcelableHeader;
import anetwork.channel.aidl.g;
import anetwork.channel.b;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e extends g.a {

    /* renamed from: a, reason: collision with root package name */
    private anetwork.channel.d f659a;

    /* renamed from: b, reason: collision with root package name */
    private Handler f660b;

    /* renamed from: c, reason: collision with root package name */
    private Object f661c;
    private byte d;

    public e(anetwork.channel.d dVar, Handler handler, Object obj) {
        this.d = (byte) 0;
        this.f659a = dVar;
        if (dVar != null) {
            if (b.a.class.isAssignableFrom(dVar.getClass())) {
                this.d = (byte) (this.d | 1);
            }
            if (b.c.class.isAssignableFrom(dVar.getClass())) {
                this.d = (byte) (this.d | 2);
            }
            if (b.d.class.isAssignableFrom(dVar.getClass())) {
                this.d = (byte) (this.d | 4);
            }
            if (b.InterfaceC0019b.class.isAssignableFrom(dVar.getClass())) {
                this.d = (byte) (this.d | 8);
            }
        }
        this.f660b = handler;
        this.f661c = obj;
    }

    private void a(byte b2, Object obj) {
        if (this.f660b == null) {
            b(b2, obj);
        } else {
            this.f660b.post(new f(this, b2, obj));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(byte b2, Object obj) {
        try {
            if (b2 == 4) {
                ParcelableHeader parcelableHeader = (ParcelableHeader) obj;
                ((b.d) this.f659a).a(parcelableHeader.b(), parcelableHeader.a(), this.f661c);
                if (ALog.isPrintLog(1)) {
                    ALog.d("anet.ParcelableNetworkListenerWrapper", "[onResponseCode]" + parcelableHeader, null, new Object[0]);
                    return;
                }
                return;
            }
            if (b2 == 2) {
                DefaultProgressEvent defaultProgressEvent = (DefaultProgressEvent) obj;
                if (defaultProgressEvent != null) {
                    defaultProgressEvent.a(this.f661c);
                }
                ((b.c) this.f659a).a(defaultProgressEvent, this.f661c);
                if (ALog.isPrintLog(1)) {
                    ALog.d("anet.ParcelableNetworkListenerWrapper", "[onDataReceived]" + defaultProgressEvent, null, new Object[0]);
                    return;
                }
                return;
            }
            if (b2 != 1) {
                if (b2 == 8) {
                    ((b.InterfaceC0019b) this.f659a).a((anetwork.channel.aidl.f) obj, this.f661c);
                    if (ALog.isPrintLog(1)) {
                        ALog.d("anet.ParcelableNetworkListenerWrapper", "[onInputStreamReceived]", null, new Object[0]);
                        return;
                    }
                    return;
                }
                return;
            }
            DefaultFinishEvent defaultFinishEvent = (DefaultFinishEvent) obj;
            if (defaultFinishEvent != null) {
                defaultFinishEvent.a(this.f661c);
            }
            ((b.a) this.f659a).a(defaultFinishEvent, this.f661c);
            if (ALog.isPrintLog(1)) {
                ALog.d("anet.ParcelableNetworkListenerWrapper", "[onFinished]" + defaultFinishEvent, null, new Object[0]);
            }
        } catch (Exception unused) {
            ALog.e("anet.ParcelableNetworkListenerWrapper", "dispatchCallback error", null, new Object[0]);
        }
    }

    @Override // anetwork.channel.aidl.g
    public void a(DefaultProgressEvent defaultProgressEvent) throws RemoteException {
        if ((this.d & 2) != 0) {
            a((byte) 2, defaultProgressEvent);
        }
    }

    @Override // anetwork.channel.aidl.g
    public void a(DefaultFinishEvent defaultFinishEvent) throws RemoteException {
        if ((this.d & 1) != 0) {
            a((byte) 1, defaultFinishEvent);
        }
        this.f659a = null;
        this.f661c = null;
        this.f660b = null;
    }

    @Override // anetwork.channel.aidl.g
    public boolean a(int i, ParcelableHeader parcelableHeader) throws RemoteException {
        if ((this.d & 4) == 0) {
            return false;
        }
        a((byte) 4, (Object) parcelableHeader);
        return false;
    }

    @Override // anetwork.channel.aidl.g
    public void a(anetwork.channel.aidl.f fVar) throws RemoteException {
        if ((this.d & 8) != 0) {
            a((byte) 8, fVar);
        }
    }

    @Override // anetwork.channel.aidl.g
    public byte a() throws RemoteException {
        return this.d;
    }
}
