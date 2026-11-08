package com.bun.miitmdid.supplier.sumsung;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import androidx.annotation.Keep;
import com.samsung.android.deviceidservice.IDeviceIdService;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class SumsungCore {

    @Keep
    private static boolean DBG = false;

    @Keep
    private static String SAMSUNGTAG = "Samsung_DeviceIdService";

    @Keep
    private static String TAG = "SumsungCore library";

    @Keep
    private com.bun.miitmdid.c.e.a mCallerCallBack;

    @Keep
    private ServiceConnection mConnection;

    @Keep
    private Context mContext;

    @Keep
    private IDeviceIdService mDeviceidInterface;

    @Keep
    class a implements ServiceConnection {
        @Keep
        a() {
        }

        @Override // android.content.ServiceConnection
        @Keep
        public native synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder);

        @Override // android.content.ServiceConnection
        @Keep
        public native void onServiceDisconnected(ComponentName componentName);
    }

    @Keep
    public SumsungCore(Context context, com.bun.miitmdid.c.e.a aVar) {
        this.mContext = null;
        this.mCallerCallBack = null;
        if (context == null) {
            throw new NullPointerException("Context can not be null.");
        }
        this.mContext = context;
        this.mCallerCallBack = aVar;
        this.mConnection = new a();
        Intent intent = new Intent();
        intent.setClassName("com.samsung.android.deviceidservice", "com.samsung.android.deviceidservice.DeviceIdService");
        if (this.mContext.bindService(intent, this.mConnection, 1)) {
            com.bun.lib.a.b(TAG, "bindService Successful!");
            return;
        }
        this.mContext.unbindService(this.mConnection);
        com.bun.lib.a.b(TAG, "bindService Failed!");
        com.bun.miitmdid.c.e.a aVar2 = this.mCallerCallBack;
        if (aVar2 != null) {
            aVar2.b();
        }
    }

    @Keep
    static native /* synthetic */ IDeviceIdService access$002(SumsungCore sumsungCore, IDeviceIdService iDeviceIdService);

    @Keep
    static native /* synthetic */ com.bun.miitmdid.c.e.a access$100(SumsungCore sumsungCore);

    @Keep
    static native /* synthetic */ String access$200();

    @Keep
    public native String getAAID();

    @Keep
    public native String getOAID();

    @Keep
    public native String getUDID();

    @Keep
    public native String getVAID();

    @Keep
    public native boolean isSupported();

    @Keep
    public native void shutdown();
}
