package anetwork.channel.entity;

import android.os.RemoteException;
import anetwork.channel.aidl.ParcelableHeader;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class e implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ anetwork.channel.aidl.g f690a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ int f691b;

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Map f692c;
    final /* synthetic */ d d;

    e(d dVar, anetwork.channel.aidl.g gVar, int i, Map map) {
        this.d = dVar;
        this.f690a = gVar;
        this.f691b = i;
        this.f692c = map;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.f690a.a(this.f691b, new ParcelableHeader(this.f691b, this.f692c));
        } catch (RemoteException unused) {
        }
    }
}
