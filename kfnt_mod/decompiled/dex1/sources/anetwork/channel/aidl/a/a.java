package anetwork.channel.aidl.a;

import android.os.Build;
import android.os.RemoteException;
import anet.channel.util.ErrorConstant;
import anetwork.channel.aidl.a;
import anetwork.channel.b;
import anetwork.channel.c;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a extends a.AbstractBinderC0011a implements b.a, b.InterfaceC0019b, b.d {

    /* renamed from: a, reason: collision with root package name */
    private d f649a;

    /* renamed from: b, reason: collision with root package name */
    private int f650b;

    /* renamed from: c, reason: collision with root package name */
    private String f651c;
    private Map<String, List<String>> d;
    private anetwork.channel.i.a e;
    private CountDownLatch f = new CountDownLatch(1);
    private CountDownLatch g = new CountDownLatch(1);
    private anetwork.channel.aidl.e h;
    private anetwork.channel.entity.h i;

    public a(anetwork.channel.entity.h hVar) {
        this.i = hVar;
    }

    @Override // anetwork.channel.aidl.a
    public String c() throws RemoteException {
        a(this.f);
        return this.f651c;
    }

    @Override // anetwork.channel.aidl.a
    public anetwork.channel.i.a e() {
        return this.e;
    }

    @Override // anetwork.channel.aidl.a
    public anetwork.channel.aidl.f a() throws RemoteException {
        a(this.g);
        return this.f649a;
    }

    @Override // anetwork.channel.aidl.a
    public int b() throws RemoteException {
        a(this.f);
        return this.f650b;
    }

    @Override // anetwork.channel.aidl.a
    public Map<String, List<String>> d() throws RemoteException {
        a(this.f);
        return this.d;
    }

    @Override // anetwork.channel.aidl.a
    public void f() throws RemoteException {
        if (this.h != null) {
            this.h.a(true);
        }
    }

    public void a(anetwork.channel.aidl.e eVar) {
        this.h = eVar;
    }

    @Override // anetwork.channel.b.a
    public void a(c.a aVar, Object obj) {
        this.f650b = aVar.a();
        this.f651c = aVar.b() != null ? aVar.b() : ErrorConstant.getErrMsg(this.f650b);
        this.e = aVar.c();
        if (this.f649a != null) {
            this.f649a.a();
        }
        this.g.countDown();
        this.f.countDown();
    }

    private void a(CountDownLatch countDownLatch) throws RemoteException {
        try {
            if (countDownLatch.await(this.i.b() + 1000, TimeUnit.MILLISECONDS)) {
                return;
            }
            if (this.h != null) {
                this.h.a(true);
            }
            throw a("wait time out");
        } catch (InterruptedException unused) {
            throw a("thread interrupt");
        }
    }

    private RemoteException a(String str) {
        if (Build.VERSION.SDK_INT >= 15) {
            return new RemoteException(str);
        }
        return new RemoteException();
    }

    @Override // anetwork.channel.b.d
    public boolean a(int i, Map<String, List<String>> map, Object obj) {
        this.f650b = i;
        this.f651c = ErrorConstant.getErrMsg(this.f650b);
        this.d = map;
        this.f.countDown();
        return false;
    }

    @Override // anetwork.channel.b.InterfaceC0019b
    public void a(anetwork.channel.aidl.f fVar, Object obj) {
        this.f649a = (d) fVar;
        this.g.countDown();
    }
}
