package anetwork.channel.aidl.a;

import android.os.RemoteException;
import anetwork.channel.aidl.d;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b extends d.a {

    /* renamed from: a, reason: collision with root package name */
    private anetwork.channel.a f653a;

    @Override // anetwork.channel.aidl.d
    public int a(byte[] bArr) throws RemoteException {
        if (this.f653a != null) {
            return this.f653a.a(bArr);
        }
        return 0;
    }

    @Override // anetwork.channel.aidl.d
    public boolean a() throws RemoteException {
        if (this.f653a != null) {
            return this.f653a.a();
        }
        return true;
    }

    public String toString() {
        return super.toString() + " handle:" + this.f653a;
    }
}
