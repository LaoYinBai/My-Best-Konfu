package com.kwai.filedownloader;

import android.os.IBinder;
import android.os.RemoteException;
import com.kwai.filedownloader.b.a;
import com.kwai.filedownloader.b.b;
import com.kwai.filedownloader.message.MessageSnapshot;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class p extends com.kwai.filedownloader.services.a<a, com.kwai.filedownloader.b.b> {

    protected static class a extends a.AbstractBinderC0092a {
        protected a() {
        }

        @Override // com.kwai.filedownloader.b.a
        public void a(MessageSnapshot messageSnapshot) {
            com.kwai.filedownloader.message.e.a().a(messageSnapshot);
        }
    }

    p(Class<?> cls) {
        super(cls);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.kwai.filedownloader.services.a
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public com.kwai.filedownloader.b.b b(IBinder iBinder) {
        return b.a.a(iBinder);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.kwai.filedownloader.services.a
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public a b() {
        return new a();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.kwai.filedownloader.services.a
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void b(com.kwai.filedownloader.b.b bVar, a aVar) {
        bVar.a(aVar);
    }

    @Override // com.kwai.filedownloader.u
    public boolean a(int i) {
        if (!c()) {
            return com.kwai.filedownloader.e.a.a(i);
        }
        try {
            return d().a(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.kwai.filedownloader.u
    public boolean a(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, com.kwai.filedownloader.c.b bVar, boolean z3) {
        if (!c()) {
            return com.kwai.filedownloader.e.a.a(str, str2, z);
        }
        try {
            d().a(str, str2, z, i, i2, i3, z2, bVar, z3);
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.kwai.filedownloader.u
    public byte b(int i) {
        if (!c()) {
            return com.kwai.filedownloader.e.a.b(i);
        }
        try {
            return d().e(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return (byte) 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.kwai.filedownloader.services.a
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void a(com.kwai.filedownloader.b.b bVar, a aVar) {
        bVar.b(aVar);
    }

    @Override // com.kwai.filedownloader.u
    public boolean c(int i) {
        if (!c()) {
            return com.kwai.filedownloader.e.a.c(i);
        }
        try {
            return d().f(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }
}
