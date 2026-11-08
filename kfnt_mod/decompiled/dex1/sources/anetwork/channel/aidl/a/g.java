package anetwork.channel.aidl.a;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Looper;
import anet.channel.util.ALog;
import anetwork.channel.aidl.NetworkService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class g {

    /* renamed from: a, reason: collision with root package name */
    static volatile anetwork.channel.aidl.b f665a = null;

    /* renamed from: b, reason: collision with root package name */
    static volatile boolean f666b = false;

    /* renamed from: c, reason: collision with root package name */
    static volatile boolean f667c = false;
    static volatile CountDownLatch d;
    static Handler e = new Handler(Looper.getMainLooper());
    private static ServiceConnection f = new h();

    public static void a(Context context, boolean z) {
        if (f665a == null && !f666b) {
            a(context);
            if (f666b || !z) {
                return;
            }
            try {
                synchronized (g.class) {
                    if (f665a != null) {
                        return;
                    }
                    if (d == null) {
                        d = new CountDownLatch(1);
                    }
                    ALog.i("anet.RemoteGetter", "[initRemoteGetterAndWait]begin to wait", null, new Object[0]);
                    if (d.await(anetwork.channel.b.b.d(), TimeUnit.SECONDS)) {
                        ALog.i("anet.RemoteGetter", "mServiceBindLock count down to 0", null, new Object[0]);
                    } else {
                        ALog.i("anet.RemoteGetter", "mServiceBindLock wait timeout", null, new Object[0]);
                    }
                }
            } catch (InterruptedException unused) {
                ALog.e("anet.RemoteGetter", "mServiceBindLock wait interrupt", null, new Object[0]);
            }
        }
    }

    private static void a(Context context) {
        if (ALog.isPrintLog(2)) {
            ALog.i("anet.RemoteGetter", "[asyncBindService] mContext:" + context + " bBindFailed:" + f666b + " bBinding:" + f667c, null, new Object[0]);
        }
        if (context == null || f666b || f667c) {
            return;
        }
        f667c = true;
        Intent intent = new Intent(context, (Class<?>) NetworkService.class);
        intent.setAction(anetwork.channel.aidl.b.class.getName());
        intent.addCategory("android.intent.category.DEFAULT");
        f666b = !context.bindService(intent, f, 1);
        if (f666b) {
            f667c = false;
            ALog.w("anet.RemoteGetter", "[asyncBindService]ANet_Service start not success. ANet run with local mode!", null, new Object[0]);
        }
        e.postDelayed(new i(), 10000L);
    }
}
