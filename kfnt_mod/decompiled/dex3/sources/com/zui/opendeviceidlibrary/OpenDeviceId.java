package com.zui.opendeviceidlibrary;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import androidx.annotation.Keep;
import com.zui.deviceidservice.IDeviceidInterface;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class OpenDeviceId {

    @Keep
    private static String c = "OpenDeviceId library";

    @Keep
    private static boolean d;

    @Keep
    private IDeviceidInterface a;

    @Keep
    private CallBack b;

    @Keep
    /* renamed from: com.zui.opendeviceidlibrary.OpenDeviceId$1, reason: invalid class name */
    class AnonymousClass1 implements ServiceConnection {

        @Keep
        final /* synthetic */ OpenDeviceId a;

        @Override // android.content.ServiceConnection
        @Keep
        public native synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder);

        @Override // android.content.ServiceConnection
        @Keep
        public native void onServiceDisconnected(ComponentName componentName);
    }

    @Keep
    public interface CallBack {
        @Keep
        void a(OpenDeviceId openDeviceId);
    }

    @Keep
    static native /* synthetic */ IDeviceidInterface a(OpenDeviceId openDeviceId, IDeviceidInterface iDeviceidInterface);

    @Keep
    static native /* synthetic */ CallBack a(OpenDeviceId openDeviceId);

    @Keep
    static native /* synthetic */ void a(OpenDeviceId openDeviceId, String str);

    @Keep
    private native void a(String str);
}
