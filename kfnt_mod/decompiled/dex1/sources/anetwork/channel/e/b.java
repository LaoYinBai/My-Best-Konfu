package anetwork.channel.e;

import android.content.Context;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.SessionCenter;
import anet.channel.entity.ENV;
import anet.channel.util.ALog;
import anet.channel.util.Utils;
import java.io.Serializable;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b implements Serializable {

    /* renamed from: c, reason: collision with root package name */
    private static Context f683c;

    /* renamed from: a, reason: collision with root package name */
    public static ENV f681a = ENV.ONLINE;

    /* renamed from: b, reason: collision with root package name */
    private static AtomicBoolean f682b = new AtomicBoolean(false);
    private static HashMap<String, Object> d = null;

    public static void a(Context context) {
        if (context == null) {
            return;
        }
        try {
            if (f682b.compareAndSet(false, true)) {
                ALog.e("anet.NetworkSdkSetting", "NetworkSdkSetting init", null, new Object[0]);
                f683c = context;
                GlobalAppRuntimeInfo.setContext(context);
                b();
                anetwork.channel.g.a.a();
                anetwork.channel.b.b.a();
                anetwork.channel.c.a.a(context);
                SessionCenter.init(context);
            }
        } catch (Throwable th) {
            ALog.e("anet.NetworkSdkSetting", "Network SDK initial failed!", null, th, new Object[0]);
        }
    }

    public static Context a() {
        return f683c;
    }

    private static void b() {
        try {
            Utils.invokeStaticMethodThrowException("anet.channel.TaobaoNetworkAdapter", "init", new Class[]{Context.class, HashMap.class}, f683c, d);
            ALog.i("anet.NetworkSdkSetting", "init taobao adapter success", null, new Object[0]);
        } catch (Exception e) {
            ALog.i("anet.NetworkSdkSetting", "initTaobaoAdapter failed. maybe not taobao app", null, e);
        }
    }
}
