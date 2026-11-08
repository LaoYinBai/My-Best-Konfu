package com.huawei.hms.api;

import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.core.aidl.ResponseHeader;
import com.huawei.hms.core.aidl.c;
import com.huawei.hms.core.aidl.e;
import com.huawei.hms.support.api.transport.DatagramTransport;
import com.huawei.hms.support.log.HMSLog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class IPCCallback extends c.a {
    private final Class<? extends IMessageEntity> a;
    private final DatagramTransport.a b;

    public IPCCallback(Class<? extends IMessageEntity> cls, DatagramTransport.a aVar) {
        this.a = cls;
        this.b = aVar;
    }

    @Override // com.huawei.hms.core.aidl.c
    public void call(com.huawei.hms.core.aidl.b bVar) throws RemoteException {
        if (bVar == null || TextUtils.isEmpty(bVar.a)) {
            HMSLog.e("IPCCallback", "In call, URI cannot be empty.");
            throw new RemoteException();
        }
        e eVarA = com.huawei.hms.core.aidl.a.a(bVar.c());
        IMessageEntity iMessageEntityNewResponseInstance = null;
        if (bVar.b() > 0 && (iMessageEntityNewResponseInstance = newResponseInstance()) != null) {
            eVarA.a(bVar.a(), iMessageEntityNewResponseInstance);
        }
        if (bVar.b != null) {
            ResponseHeader responseHeader = new ResponseHeader();
            eVarA.a(bVar.b, responseHeader);
            this.b.a(responseHeader.getStatusCode(), iMessageEntityNewResponseInstance);
            return;
        }
        this.b.a(0, iMessageEntityNewResponseInstance);
    }

    protected IMessageEntity newResponseInstance() {
        if (this.a == null) {
            return null;
        }
        try {
            return this.a.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            HMSLog.e("IPCCallback", "In newResponseInstance, instancing exception." + e.getMessage());
            return null;
        }
    }
}
