package com.heytap.openid.sdk;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import androidx.annotation.Keep;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b implements ServiceConnection {

    @Keep
    public final /* synthetic */ c a;

    @Keep
    public b(c cVar) {
        this.a = cVar;
    }

    @Override // android.content.ServiceConnection
    @Keep
    public native void onServiceConnected(ComponentName componentName, IBinder iBinder);

    @Override // android.content.ServiceConnection
    @Keep
    public native void onServiceDisconnected(ComponentName componentName);
}
