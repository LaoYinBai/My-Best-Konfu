package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_for12;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Address;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.a;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.g;
import com.bytedance.common.utility.date.DateDef;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a implements g.a {

    /* renamed from: a, reason: collision with root package name */
    private final boolean f1418a;
    private final Context i;
    private bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.a k;
    private int l;

    /* renamed from: b, reason: collision with root package name */
    private volatile boolean f1419b = false;

    /* renamed from: c, reason: collision with root package name */
    private boolean f1420c = true;
    private boolean d = false;
    private long e = 0;
    private long f = 0;
    private ThreadPoolExecutor g = null;
    private AtomicBoolean h = new AtomicBoolean(false);
    private volatile boolean j = false;
    final bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.g m = new bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.g(Looper.getMainLooper(), this);

    /* renamed from: bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_for12.a$a, reason: collision with other inner class name */
    class RunnableC0047a implements Runnable {
        RunnableC0047a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.c();
        }
    }

    class b implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f1422a;

        b(boolean z) {
            this.f1422a = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.c(this.f1422a);
        }
    }

    public a(Context context, int i) {
        this.i = context;
        this.f1418a = bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.f.b(context);
        this.l = i;
    }

    private void d(boolean z) {
        if (this.d) {
            return;
        }
        if (this.f1420c) {
            this.f1420c = false;
            this.e = 0L;
            this.f = 0L;
        }
        long j = z ? 10800000L : 43200000L;
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.e <= j || jCurrentTimeMillis - this.f <= com.bytedance.b.a.a.c.g.g) {
            return;
        }
        boolean zA = bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.e.a(this.i);
        if (!this.j || zA) {
            a(zA);
        }
    }

    private boolean g() {
        String[] strArrA = a();
        if (strArrA != null && strArrA.length != 0) {
            a(0);
        }
        return false;
    }

    private bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.a h() {
        if (this.k == null) {
            this.k = new a.b().a(10L, TimeUnit.SECONDS).b(10L, TimeUnit.SECONDS).c(10L, TimeUnit.SECONDS).a();
        }
        return this.k;
    }

    synchronized void c() {
        if (System.currentTimeMillis() - this.e > DateDef.HOUR) {
            this.e = System.currentTimeMillis();
            try {
                if (h.a().a(this.l).d() != null) {
                    h.a().a(this.l).d().c();
                }
            } catch (Exception unused) {
            }
        }
    }

    public void e() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            return;
        }
        try {
            if (this.f1418a) {
                d();
            } else {
                c();
            }
        } catch (Throwable unused) {
        }
    }

    public void f() {
        b(false);
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.g.a
    public void handleMsg(Message message) {
        int i = message.what;
        if (i == 101) {
            this.d = false;
            this.e = System.currentTimeMillis();
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.b.a("TNCManager", "doRefresh, succ");
            if (this.f1420c) {
                f();
            }
            this.h.set(false);
            return;
        }
        if (i != 102) {
            return;
        }
        this.d = false;
        if (this.f1420c) {
            f();
        }
        bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.b.a("TNCManager", "doRefresh, error");
        this.h.set(false);
    }

    public synchronized void b(boolean z) {
        if (this.f1418a) {
            d(z);
        } else if (this.e <= 0) {
            try {
                b().execute(new RunnableC0047a());
            } catch (Throwable unused) {
            }
        }
    }

    public boolean a(boolean z) {
        bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.b.a("TNCManager", "doRefresh: updating state " + this.h.get());
        if (!this.h.compareAndSet(false, true)) {
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.b.a("TNCManager", "doRefresh, already running");
            return false;
        }
        if (z) {
            this.f = System.currentTimeMillis();
        }
        b().execute(new b(z));
        return true;
    }

    void c(boolean z) {
        bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.b.a("TNCManager", "doRefresh, actual request");
        d();
        this.d = true;
        if (!z) {
            this.m.sendEmptyMessage(102);
            return;
        }
        try {
            g();
        } catch (Exception unused) {
            this.h.set(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.g gVar = this.m;
        if (gVar != null) {
            gVar.sendEmptyMessage(i);
        }
    }

    public ThreadPoolExecutor b() {
        if (this.g == null) {
            synchronized (a.class) {
                if (this.g == null) {
                    this.g = new ThreadPoolExecutor(2, 2, 20L, TimeUnit.SECONDS, new LinkedBlockingQueue());
                    this.g.allowCoreThreadTimeOut(true);
                }
            }
        }
        return this.g;
    }

    public String[] a() {
        String[] strArrA = h.a().a(this.l).b() != null ? h.a().a(this.l).b().a() : null;
        return (strArrA == null || strArrA.length <= 0) ? new String[0] : strArrA;
    }

    public synchronized void d() {
        if (this.j) {
            return;
        }
        this.j = true;
        long j = this.i.getSharedPreferences("m_ss_app_config", 0).getLong("last_refresh_time", 0L);
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (j > jCurrentTimeMillis) {
            j = jCurrentTimeMillis;
        }
        this.e = j;
        if (h.a().a(this.l).d() != null) {
            h.a().a(this.l).d().b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(Object obj) throws Exception {
        JSONObject jSONObject;
        if (obj instanceof String) {
            String str = (String) obj;
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            jSONObject = new JSONObject(str);
            if (!"success".equals(jSONObject.getString("message"))) {
                return false;
            }
        } else {
            jSONObject = obj instanceof JSONObject ? (JSONObject) obj : null;
        }
        if (jSONObject == null) {
            return false;
        }
        JSONObject jSONObject2 = jSONObject.getJSONObject("data");
        synchronized (this) {
            SharedPreferences.Editor editorEdit = this.i.getSharedPreferences("m_ss_app_config", 0).edit();
            editorEdit.putLong("last_refresh_time", System.currentTimeMillis());
            editorEdit.apply();
        }
        if (h.a().a(this.l).d() == null) {
            return true;
        }
        h.a().a(this.l).d().a(jSONObject2);
        return true;
    }

    class c extends bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.a {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f1424a;

        c(int i) {
            this.f1424a = i;
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.a
        public void a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_if122.b bVar, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.b bVar2) throws JSONException {
            JSONObject jSONObject;
            if (bVar2 == null || !bVar2.e()) {
                a.this.a(this.f1424a + 1);
                return;
            }
            String string = null;
            try {
                jSONObject = new JSONObject(bVar2.a());
            } catch (Exception unused) {
                jSONObject = null;
            }
            if (jSONObject == null) {
                a.this.a(this.f1424a + 1);
                return;
            }
            try {
                string = jSONObject.getString("message");
            } catch (Exception unused2) {
            }
            if (!"success".equals(string)) {
                a.this.a(this.f1424a + 1);
                return;
            }
            try {
                if (a.this.a(jSONObject)) {
                    a.this.b(101);
                } else {
                    a.this.a(this.f1424a + 1);
                }
            } catch (Exception unused3) {
            }
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.a
        public void a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_if122.b bVar, IOException iOException) {
            a.this.a(this.f1424a + 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        String[] strArrA = a();
        if (strArrA != null && strArrA.length > i) {
            String str = strArrA[i];
            if (TextUtils.isEmpty(str)) {
                b(102);
                return;
            }
            try {
                String strA = a(str);
                if (TextUtils.isEmpty(strA)) {
                    b(102);
                    return;
                }
                bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_if122.a aVarA = h().a();
                aVarA.b(strA);
                a(aVarA);
                aVarA.a(new c(i));
                return;
            } catch (Throwable th) {
                bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.b.a("AppConfig", "try app config exception: " + th);
                return;
            }
        }
        b(102);
    }

    private String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return "https://" + str + "/get_domains/v4/";
    }

    private void a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_if122.a aVar) {
        String str;
        if (aVar == null) {
            return;
        }
        Address addressA = h.a().a(this.l).b() != null ? h.a().a(this.l).b().a(this.i) : null;
        if (addressA != null && addressA.hasLatitude() && addressA.hasLongitude()) {
            aVar.b("latitude", addressA.getLatitude() + "");
            aVar.b("longitude", addressA.getLongitude() + "");
            String locality = addressA.getLocality();
            if (!TextUtils.isEmpty(locality)) {
                aVar.b("city", Uri.encode(locality));
            }
        }
        if (this.f1419b) {
            aVar.b(TTDownloadField.TT_FORCE, "1");
        }
        try {
            if (Build.VERSION.SDK_INT < 21) {
                str = Build.CPU_ABI;
            } else {
                str = Build.SUPPORTED_ABIS[0];
            }
            aVar.b("abi", str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (h.a().a(this.l).b() != null) {
            aVar.b("aid", h.a().a(this.l).b().c() + "");
            aVar.b("device_platform", h.a().a(this.l).b().b());
            aVar.b("channel", h.a().a(this.l).b().d());
            aVar.b("version_code", h.a().a(this.l).b().e() + "");
            aVar.b("custom_info_1", h.a().a(this.l).b().f());
        }
    }
}
