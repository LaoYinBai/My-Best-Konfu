package com.qq.e.ads;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.qq.e.comm.a;
import com.qq.e.comm.adevent.ADEvent;
import com.qq.e.comm.managers.GDTADManager;
import com.qq.e.comm.pi.POFactory;
import com.qq.e.comm.util.GDTLogger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class AbstractAD<T> {

    /* renamed from: a, reason: collision with root package name */
    protected T f5481a;
    private volatile boolean e;

    /* renamed from: c, reason: collision with root package name */
    private volatile boolean f5483c = false;
    private volatile boolean d = false;

    /* renamed from: b, reason: collision with root package name */
    private final Handler f5482b = new Handler(Looper.getMainLooper());

    protected static final void a(Class cls, ADEvent aDEvent) {
        GDTLogger.e(cls.getSimpleName() + ":ADEvent参数错误(" + aDEvent + ")");
    }

    private void b(final Context context, final String str, final String str2) {
        this.e = true;
        if (!GDTADManager.getInstance().isInitialized()) {
            a(2003);
            return;
        }
        final String appid = GDTADManager.getInstance().getAppStatus().getAPPID();
        if (a.a(context)) {
            this.d = true;
            GDTADManager.INIT_EXECUTOR.execute(new Runnable() { // from class: com.qq.e.ads.AbstractAD.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        final POFactory pOFactory = GDTADManager.getInstance().getPM().getPOFactory();
                        AbstractAD.this.f5482b.post(new Runnable() { // from class: com.qq.e.ads.AbstractAD.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                try {
                                    if (pOFactory != null) {
                                        AbstractAD.this.f5481a = (T) AbstractAD.this.a(context, pOFactory, appid, str, str2);
                                        AbstractAD.this.f5483c = true;
                                        if (AbstractAD.this.f5481a == null) {
                                            AbstractAD.this.a(200103);
                                        } else {
                                            AbstractAD.this.a((AbstractAD) AbstractAD.this.f5481a);
                                        }
                                    } else {
                                        AbstractAD.this.f5483c = true;
                                        AbstractAD.this.a(200102);
                                    }
                                } catch (Throwable th) {
                                    GDTLogger.e("初始化错误：初始化广告实例时发生异常", th);
                                    AbstractAD.this.f5483c = true;
                                    AbstractAD.this.a(2001);
                                }
                            }
                        });
                    } catch (Throwable th) {
                        GDTLogger.e("初始化错误：初始化插件时发生异常", th);
                        AbstractAD.this.f5483c = true;
                        AbstractAD.this.a(200102);
                    }
                }
            });
        } else {
            GDTLogger.e("Manifest文件中Activity/Service/Permission的声明有问题或者Permission权限未授予");
            a(4002);
        }
    }

    protected abstract T a(Context context, POFactory pOFactory, String str, String str2, String str3);

    protected final void a(final int i) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            b(i);
        } else {
            this.f5482b.post(new Runnable() { // from class: com.qq.e.ads.AbstractAD.2
                @Override // java.lang.Runnable
                public void run() {
                    AbstractAD.this.b(i);
                }
            });
        }
    }

    protected final void a(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str)) {
            b(context, str, "");
        } else {
            GDTLogger.e("初始化错误：参数错误context或posId为空");
            a(2001);
        }
    }

    protected final void a(Context context, String str, String str2) {
        if (context != null && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            b(context, str, str2);
        } else {
            GDTLogger.e("初始化错误：参数错误，context、posId、token 不可为空");
            a(2001);
        }
    }

    protected abstract void a(T t);

    protected final void a(String str) {
        GDTLogger.e(getClass().getSimpleName() + ":调用方法 " + str + "异常，广告实例还未初始化");
    }

    protected final boolean a() {
        return this.e && this.d;
    }

    protected abstract void b(int i);

    protected final boolean b() {
        return this.f5483c;
    }

    protected final void c() {
        GDTLogger.e(getClass().getSimpleName() + ":此构造方法已废弃，请在 Application 中初始化 SDK 后，使用不带 appId 的构造方法，详细请参考Demo，构造函数中传入的appId将被忽略，实际使用的是GDTADManager.getInstance().initWith() 传入的appId");
    }
}
