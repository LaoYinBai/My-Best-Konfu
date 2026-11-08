package anetwork.channel.j;

import android.content.Context;
import android.os.RemoteException;
import android.text.TextUtils;
import anet.channel.bytes.ByteArray;
import anet.channel.bytes.a;
import anet.channel.util.ALog;
import anet.channel.util.ErrorConstant;
import anet.channel.util.StringUtils;
import anetwork.channel.aidl.NetworkResponse;
import anetwork.channel.aidl.ParcelableRequest;
import anetwork.channel.aidl.h;
import java.io.ByteArrayOutputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class b extends h.a {

    /* renamed from: a, reason: collision with root package name */
    public int f710a = 1;

    public b(Context context) {
        anetwork.channel.e.b.a(context);
    }

    @Override // anetwork.channel.aidl.h
    public NetworkResponse a(ParcelableRequest parcelableRequest) throws RemoteException {
        return c(parcelableRequest);
    }

    @Override // anetwork.channel.aidl.h
    public anetwork.channel.aidl.e a(ParcelableRequest parcelableRequest, anetwork.channel.aidl.g gVar) throws RemoteException {
        try {
            return a(new anetwork.channel.entity.h(parcelableRequest, this.f710a, false), gVar);
        } catch (Exception e) {
            ALog.e("anet.UnifiedNetworkDelegate", "asyncSend failed", parcelableRequest.m, e, new Object[0]);
            throw new RemoteException(e.getMessage());
        }
    }

    private anetwork.channel.aidl.e a(anetwork.channel.entity.h hVar, anetwork.channel.aidl.g gVar) throws RemoteException {
        return new anetwork.channel.aidl.a.c(new m(hVar, new anetwork.channel.entity.d(gVar, hVar)).a());
    }

    @Override // anetwork.channel.aidl.h
    public anetwork.channel.aidl.a b(ParcelableRequest parcelableRequest) throws RemoteException {
        try {
            anetwork.channel.entity.h hVar = new anetwork.channel.entity.h(parcelableRequest, this.f710a, true);
            anetwork.channel.aidl.a.a aVar = new anetwork.channel.aidl.a.a(hVar);
            aVar.a(a(hVar, new anetwork.channel.aidl.a.e(aVar, null, null)));
            return aVar;
        } catch (Exception e) {
            ALog.e("anet.UnifiedNetworkDelegate", "asyncSend failed", parcelableRequest.m, e, new Object[0]);
            throw new RemoteException(e.getMessage());
        }
    }

    private NetworkResponse c(ParcelableRequest parcelableRequest) {
        NetworkResponse networkResponse = new NetworkResponse();
        try {
            anetwork.channel.aidl.a.a aVar = (anetwork.channel.aidl.a.a) b(parcelableRequest);
            anetwork.channel.aidl.f fVarA = aVar.a();
            if (fVarA != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(fVarA.e() > 0 ? fVarA.e() : 1024);
                ByteArray byteArrayA = a.C0007a.f408a.a(2048);
                while (true) {
                    int iA = fVarA.a(byteArrayA.getBuffer());
                    if (iA == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(byteArrayA.getBuffer(), 0, iA);
                }
                networkResponse.a(byteArrayOutputStream.toByteArray());
            }
            int iB = aVar.b();
            if (iB < 0) {
                networkResponse.a((byte[]) null);
            } else {
                networkResponse.a(aVar.d());
            }
            networkResponse.a(iB);
            networkResponse.a(aVar.e());
            return networkResponse;
        } catch (RemoteException e) {
            networkResponse.a(ErrorConstant.ERROR_REMOTE_CALL_FAIL);
            String message = e.getMessage();
            if (!TextUtils.isEmpty(message)) {
                networkResponse.a(StringUtils.concatString(networkResponse.a(), "|", message));
            }
            return networkResponse;
        } catch (Exception unused) {
            networkResponse.a(ErrorConstant.ERROR_REQUEST_FAIL);
            return networkResponse;
        }
    }
}
