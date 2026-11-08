package com.huawei.android.hms.pps.a;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import androidx.annotation.Keep;
import java.util.concurrent.LinkedBlockingQueue;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a implements ServiceConnection {

    @Keep
    public boolean a = false;

    @Keep
    public final LinkedBlockingQueue<IBinder> b = new LinkedBlockingQueue<>(1);

    @Keep
    public a() {
    }

    @Override // android.content.ServiceConnection
    @Keep
    public final native void onServiceConnected(ComponentName componentName, IBinder iBinder);

    @Override // android.content.ServiceConnection
    @Keep
    public final native void onServiceDisconnected(ComponentName componentName);
}
