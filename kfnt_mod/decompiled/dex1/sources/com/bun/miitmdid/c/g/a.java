package com.bun.miitmdid.c.g;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import androidx.annotation.Keep;
import com.zui.deviceidservice.IDeviceidInterface;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    @Keep
    private static String e = "OpenDeviceId library";

    @Keep
    private static boolean f;

    /* renamed from: a, reason: collision with root package name */
    @Keep
    private Context f3291a;

    /* renamed from: b, reason: collision with root package name */
    @Keep
    private IDeviceidInterface f3292b;

    /* renamed from: c, reason: collision with root package name */
    @Keep
    private ServiceConnection f3293c;

    @Keep
    private com.bun.miitmdid.c.e.a d;

    @Keep
    /* renamed from: com.bun.miitmdid.c.g.a$a, reason: collision with other inner class name */
    class ServiceConnectionC0093a implements ServiceConnection {
        @Keep
        ServiceConnectionC0093a() {
        }

        @Override // android.content.ServiceConnection
        @Keep
        public native synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder);

        @Override // android.content.ServiceConnection
        @Keep
        public native void onServiceDisconnected(ComponentName componentName);
    }

    @Keep
    public a(Context context, com.bun.miitmdid.c.e.a aVar) {
        this.f3291a = null;
        if (context == null) {
            throw new NullPointerException("Context can not be null.");
        }
        this.f3291a = context;
        this.d = aVar;
        this.f3293c = new ServiceConnectionC0093a();
        Intent intent = new Intent();
        intent.setClassName("com.zui.deviceidservice", "com.zui.deviceidservice.DeviceidService");
        if (this.f3291a.bindService(intent, this.f3293c, 1)) {
            b("bindService Successful!");
            return;
        }
        b("bindService Failed!");
        com.bun.miitmdid.c.e.a aVar2 = this.d;
        if (aVar2 != null) {
            aVar2.b();
        }
    }

    @Keep
    static native /* synthetic */ com.bun.miitmdid.c.e.a a(a aVar);

    @Keep
    static native /* synthetic */ IDeviceidInterface a(a aVar, IDeviceidInterface iDeviceidInterface);

    @Keep
    static native /* synthetic */ void a(a aVar, String str);

    @Keep
    private native void a(String str);

    @Keep
    private native void b(String str);

    @Keep
    public native String a();

    @Keep
    public native String b();

    @Keep
    public native String c();

    @Keep
    public native String d();

    @Keep
    public native boolean e();

    @Keep
    public native void f();
}
