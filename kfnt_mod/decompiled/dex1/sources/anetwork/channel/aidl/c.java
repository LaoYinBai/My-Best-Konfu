package anetwork.channel.aidl;

import android.os.RemoteException;
import anetwork.channel.aidl.b;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class c extends b.a {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ NetworkService f669a;

    c(NetworkService networkService) {
        this.f669a = networkService;
    }

    @Override // anetwork.channel.aidl.b
    public h a(int i) throws RemoteException {
        return i == 1 ? this.f669a.f643c : this.f669a.d;
    }
}
