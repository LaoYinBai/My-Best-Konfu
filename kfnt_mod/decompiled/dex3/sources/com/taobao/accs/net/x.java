package com.taobao.accs.net;

import com.taobao.accs.utl.UtilityImpl;
import org.android.spdy.AccsSSLCallback;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class x implements AccsSSLCallback {
    final /* synthetic */ t a;

    x(t tVar) {
        this.a = tVar;
    }

    public byte[] getSSLPublicKey(int i, byte[] bArr) {
        return UtilityImpl.a(this.a.d, this.a.m, this.a.b, bArr);
    }
}
