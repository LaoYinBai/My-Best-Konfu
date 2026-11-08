package anet.channel.f;

import anet.channel.status.NetworkStatusHelper;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class e implements NetworkStatusHelper.INetworkStatusChangeListener {
    e() {
    }

    @Override // anet.channel.status.NetworkStatusHelper.INetworkStatusChangeListener
    public void onNetworkStatusChanged(NetworkStatusHelper.NetworkStatus networkStatus) {
        a.a(networkStatus);
    }
}
