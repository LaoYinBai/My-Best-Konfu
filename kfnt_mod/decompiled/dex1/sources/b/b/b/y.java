package b.b.b;

import android.content.Context;
import android.text.TextUtils;
import b.b.b.p;
import com.bun.miitmdid.core.MdidSdkHelper;
import com.bun.miitmdid.interfaces.IIdentifierListener;
import com.bun.miitmdid.interfaces.IdSupplier;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class y {

    /* renamed from: b, reason: collision with root package name */
    public String f1197b;

    /* renamed from: a, reason: collision with root package name */
    public final AtomicBoolean f1196a = new AtomicBoolean();

    /* renamed from: c, reason: collision with root package name */
    public volatile boolean f1198c = false;
    public final Object d = new Object();
    public final IIdentifierListener e = new a();

    public class a implements IIdentifierListener {
        public a() {
        }

        public void a(boolean z, IdSupplier idSupplier) {
            try {
                g.b(o.f1173b, "OaidMiit#OnSupport isSupport=" + z + ", supplier=" + idSupplier);
                if (z && idSupplier != null) {
                    y.this.a(idSupplier.getOAID());
                    synchronized (y.this.d) {
                        y.this.f1198c = false;
                        y.this.d.notifyAll();
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public final synchronized void a(String str) {
        g.b(o.f1173b, "OaidMiit#setupOaid id=" + str + ", oaid=" + this.f1197b);
        if (!TextUtils.isEmpty(str) && !str.equals(this.f1197b)) {
            this.f1197b = str;
        }
    }

    public void a(Context context) {
        synchronized (this.d) {
            this.f1198c = true;
            try {
                int iInitSdk = MdidSdkHelper.InitSdk(context, true, this.e);
                if (iInitSdk == 1008612) {
                    g.b(o.f1173b, "OaidMiit#getDeviceIds 不支持的设备");
                } else if (iInitSdk == 1008613) {
                    g.b(o.f1173b, "OaidMiit#getDeviceIds 加载配置文件出错");
                } else if (iInitSdk == 1008611) {
                    g.b(o.f1173b, "OaidMiit#getDeviceIds 不支持的设备厂商");
                } else if (iInitSdk == 1008614) {
                    g.b(o.f1173b, "OaidMiit#getDeviceIds 获取接口是异步的，结果会在回调中返回，回调执行的回调可能在工作线程");
                    if (this.f1196a.compareAndSet(false, true)) {
                        a(context);
                    }
                } else if (iInitSdk == 1008615) {
                    g.b(o.f1173b, "OaidMiit#getDeviceIds 反射调用出错");
                } else if (iInitSdk == 0) {
                    g.b(o.f1173b, "OaidMiit#getDeviceIds 正确");
                } else {
                    String str = "未知 resultCode=" + iInitSdk;
                    g.b(o.f1173b, "OaidMiit#getDeviceIds 未知 resultCode=" + iInitSdk);
                }
            } catch (Throwable th) {
                th.printStackTrace();
                this.f1198c = false;
            }
        }
    }

    public p.a a() {
        synchronized (this.d) {
            if (this.f1198c) {
                try {
                    this.d.wait(10000L);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
        p.a aVar = new p.a();
        aVar.f1176b = this.f1197b;
        return aVar;
    }
}
