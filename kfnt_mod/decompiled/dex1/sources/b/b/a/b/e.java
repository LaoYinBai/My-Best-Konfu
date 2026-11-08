package b.b.a.b;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.baidu.mobads.sdk.internal.ae;
import com.bytedance.common.utility.NetworkUtils;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public static final Handler f1043a = new Handler(Looper.getMainLooper());

    /* renamed from: b, reason: collision with root package name */
    public String f1044b;

    /* renamed from: c, reason: collision with root package name */
    public String f1045c;
    public String d;
    public final a e;
    public Context f;

    public e(String str, String str2, String str3, a aVar, Context context) {
        this.f1044b = str;
        this.f1045c = str2;
        this.d = str3;
        this.e = aVar;
        this.f = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (!NetworkUtils.isNetworkAvailable(this.f)) {
                f1043a.post(new c(this, 0));
                return;
            }
            HashMap map = new HashMap();
            map.put("Content-Type", ae.d);
            map.put("X-APIKEY", this.f1045c);
            ((b.b.a.k.d) com.apm.applog.a.b()).a(this.f1044b, this.d.getBytes(), map);
            f1043a.post(new d(this));
        } catch (Throwable th) {
            th.printStackTrace();
            f1043a.post(new c(this, 1));
        }
    }
}
