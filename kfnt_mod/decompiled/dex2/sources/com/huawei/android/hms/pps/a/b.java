package com.huawei.android.hms.pps.a;

import android.os.IBinder;
import androidx.annotation.Keep;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class b implements c {

    @Keep
    private IBinder a;

    @Keep
    public b(IBinder iBinder) {
        this.a = iBinder;
    }

    @Override // android.os.IInterface
    @Keep
    public final native IBinder asBinder();

    @Override // com.huawei.android.hms.pps.a.c
    @Keep
    public final native boolean d();

    @Override // com.huawei.android.hms.pps.a.c
    @Keep
    public final native String e();
}
