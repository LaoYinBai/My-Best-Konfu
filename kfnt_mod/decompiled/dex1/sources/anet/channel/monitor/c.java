package anet.channel.monitor;

import anet.channel.status.NetworkStatusHelper;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class c implements NetworkStatusHelper.INetworkStatusChangeListener {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ b f465a;

    c(b bVar) {
        this.f465a = bVar;
    }

    @Override // anet.channel.status.NetworkStatusHelper.INetworkStatusChangeListener
    public void onNetworkStatusChanged(NetworkStatusHelper.NetworkStatus networkStatus) {
        this.f465a.n.a();
        b.f = 0L;
        this.f465a.d();
    }
}
