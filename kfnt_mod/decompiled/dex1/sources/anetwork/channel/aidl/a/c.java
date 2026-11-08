package anetwork.channel.aidl.a;

import android.os.RemoteException;
import anet.channel.util.ALog;
import anet.channel.util.ErrorConstant;
import anetwork.channel.aidl.NetworkResponse;
import anetwork.channel.aidl.e;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c extends e.a {

    /* renamed from: a, reason: collision with root package name */
    Future<anetwork.channel.f> f654a;

    /* renamed from: b, reason: collision with root package name */
    NetworkResponse f655b;

    public c(Future<anetwork.channel.f> future) {
        this.f654a = future;
    }

    @Override // anetwork.channel.aidl.e
    public boolean a(boolean z) throws RemoteException {
        if (this.f654a == null) {
            return true;
        }
        return this.f654a.cancel(z);
    }

    @Override // anetwork.channel.aidl.e
    public boolean a() throws RemoteException {
        if (this.f654a == null) {
            return true;
        }
        return this.f654a.isCancelled();
    }

    @Override // anetwork.channel.aidl.e
    public boolean b() throws RemoteException {
        if (this.f654a == null) {
            return true;
        }
        return this.f654a.isDone();
    }

    @Override // anetwork.channel.aidl.e
    public NetworkResponse a(long j) throws RemoteException {
        if (this.f654a == null) {
            if (this.f655b != null) {
                return this.f655b;
            }
            return new NetworkResponse(ErrorConstant.ERROR_REQUEST_FAIL);
        }
        try {
            return (NetworkResponse) this.f654a.get(j, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            if ("NO SUPPORT".equalsIgnoreCase(e.getMessage())) {
                ALog.e("anet.ParcelableFutureResponse", "[get]有listener将不支持future.get()方法，如有需要请listener传入null", null, e, new Object[0]);
            }
            return new NetworkResponse(ErrorConstant.ERROR_REQUEST_FAIL);
        }
    }
}
