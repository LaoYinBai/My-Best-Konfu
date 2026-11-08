package com.bun.miitmdid.supplier.msa;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import androidx.annotation.Keep;
import com.bun.lib.c;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class MsaClient {

    @Keep
    private static String TAG = "MSA Client library";

    @Keep
    private static String TARGET_PACKAGE = "com.mdid.msa";

    @Keep
    private com.bun.miitmdid.c.e.a _BindService;

    @Keep
    private ServiceConnection mConnection;

    @Keep
    private Context mContext;

    @Keep
    private c mDeviceidInterface;

    @Keep
    class a implements ServiceConnection {

        /* renamed from: a, reason: collision with root package name */
        @Keep
        final /* synthetic */ com.bun.miitmdid.c.e.a f3321a;

        @Keep
        a(com.bun.miitmdid.c.e.a aVar) {
            this.f3321a = aVar;
        }

        @Override // android.content.ServiceConnection
        @Keep
        public native synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder);

        @Override // android.content.ServiceConnection
        @Keep
        public native void onServiceDisconnected(ComponentName componentName);
    }

    @Keep
    public MsaClient(Context context, com.bun.miitmdid.c.e.a aVar) {
        if (context == null) {
            throw new NullPointerException("Context can not be null.");
        }
        this.mContext = context;
        this._BindService = aVar;
        this.mConnection = new a(aVar);
    }

    @Keep
    public static native boolean CheckService(Context context);

    @Keep
    public static native void StartMsaKlService(Context context, String str);

    @Keep
    static native /* synthetic */ c access$000(MsaClient msaClient);

    @Keep
    static native /* synthetic */ c access$002(MsaClient msaClient, c cVar);

    @Keep
    static native /* synthetic */ String access$100();

    @Keep
    public native void BindService(String str);

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
