package b.b.b;

import android.content.Context;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.AnyThread;
import androidx.annotation.Nullable;
import b.b.a.e;
import b.b.b.j;
import b.b.b.p;
import com.bytedance.common.utility.DeviceUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class o {

    /* renamed from: a, reason: collision with root package name */
    public static final String f1172a = o.class.getSimpleName() + "#";

    /* renamed from: b, reason: collision with root package name */
    public static final String f1173b = f1172a;

    /* renamed from: c, reason: collision with root package name */
    public static final List<b.b.a.e> f1174c = new ArrayList();
    public static String d;
    public final p f;
    public final boolean g;
    public final r h;
    public final Context i;
    public Map<String, String> k;
    public Long l;
    public final ReentrantLock e = new ReentrantLock();
    public final AtomicBoolean j = new AtomicBoolean(false);

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            o.this.c();
        }
    }

    public o(Context context) throws IOException {
        boolean zContains;
        this.i = context.getApplicationContext();
        p jVar = null;
        if (b.b.a.k.s.e()) {
            jVar = new t(new z());
        } else if (z.a()) {
            jVar = new z();
        } else if (s.a()) {
            jVar = new s(context);
        } else if (b.b.a.k.s.f().toUpperCase().contains("HUAWEI") || b.b.a.k.s.c()) {
            jVar = new j();
        } else if ("OnePlus".equalsIgnoreCase(Build.MANUFACTURER)) {
            jVar = new t(null);
        } else {
            String str = Build.BRAND;
            if (str == null ? false : str.toLowerCase(Locale.ENGLISH).contains("meizu")) {
                jVar = new m();
            } else if (Build.VERSION.SDK_INT > 28) {
                if (DeviceUtils.ROM_SAMSUNG.equalsIgnoreCase(Build.BRAND) || DeviceUtils.ROM_SAMSUNG.equalsIgnoreCase(Build.MANUFACTURER)) {
                    jVar = new w();
                } else if (b.b.a.k.s.f().toUpperCase().contains("NUBIA")) {
                    jVar = new n();
                } else {
                    String str2 = Build.FINGERPRINT;
                    if (TextUtils.isEmpty(str2)) {
                        String strA = b.b.a.k.s.a("ro.build.version.incremental");
                        zContains = !TextUtils.isEmpty(strA) && strA.contains("VIBEUI_V2");
                    } else {
                        zContains = str2.contains("VIBEUI_V2");
                    }
                    jVar = zContains ? new l() : b.b.a.k.s.f().toUpperCase().contains("ASUS") ? new b.b.b.a() : new e();
                }
            } else if (!b.b.a.k.s.d() && j.a(context)) {
                jVar = new j();
            }
        }
        this.f = jVar;
        p pVar = this.f;
        if (pVar != null) {
            this.g = pVar.b(context);
        } else {
            this.g = false;
        }
        this.h = new r(context);
    }

    @AnyThread
    public static void a(@Nullable b.b.a.e eVar) {
        synchronized (f1174c) {
            f1174c.add(eVar);
        }
        String str = d;
        if (str != null) {
            a(new e.a(str), new Object[]{eVar});
        }
    }

    public static Object[] a() {
        Object[] array;
        synchronized (f1174c) {
            array = f1174c.size() > 0 ? f1174c.toArray() : null;
        }
        return array;
    }

    public static void b(b.b.a.e eVar) {
        synchronized (f1174c) {
            f1174c.remove(eVar);
        }
    }

    public static void a(@Nullable e.a aVar, Object[] objArr) {
        if (aVar == null || objArr == null) {
            return;
        }
        for (Object obj : objArr) {
            ((b.b.a.e) obj).a(aVar);
        }
    }

    public static void a(JSONObject jSONObject, String str, Object obj) throws JSONException {
        if (TextUtils.isEmpty(str) || obj == null) {
            return;
        }
        try {
            jSONObject.put(str, obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public final void c() {
        String str;
        Boolean boolValueOf;
        q qVar;
        p.a aVarC;
        g.b(f1173b, "Oaid#initOaid");
        try {
            this.e.lock();
            g.b(f1173b, "Oaid#initOaid exec");
            q qVarA = this.h.a();
            g.b(f1173b, "Oaid#initOaid fetch=" + qVarA);
            if (qVarA != null) {
                d = qVarA.f1178a;
                this.k = qVarA.a();
            }
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            Context context = this.i;
            p pVar = this.f;
            String string = null;
            if (pVar == null || (aVarC = pVar.c(context)) == null) {
                str = null;
                boolValueOf = null;
            } else {
                str = aVarC.f1176b;
                boolValueOf = Boolean.valueOf(aVarC.f1177c);
                if (aVarC instanceof j.b) {
                    this.l = Long.valueOf(((j.b) aVarC).f1170a);
                }
            }
            Pair pair = new Pair(str, boolValueOf);
            long jElapsedRealtime2 = SystemClock.elapsedRealtime() - jElapsedRealtime;
            if (pair.first != null) {
                int iIntValue = -1;
                if (qVarA != null) {
                    string = qVarA.f1179b;
                    iIntValue = qVarA.f.intValue() + 1;
                }
                if (TextUtils.isEmpty(string)) {
                    string = UUID.randomUUID().toString();
                }
                if (iIntValue <= 0) {
                    iIntValue = 1;
                }
                qVar = new q((String) pair.first, string, (Boolean) pair.second, Long.valueOf(jElapsedRealtime2), Long.valueOf(System.currentTimeMillis()), Integer.valueOf(iIntValue), this.l);
                this.h.a(qVar);
            } else {
                qVar = null;
            }
            if (qVar != null) {
                d = qVar.f1178a;
                this.k = qVar.a();
            }
            g.b(f1173b, "Oaid#initOaid oaidModel=" + qVar);
        } finally {
            this.e.unlock();
            a(new e.a(d), a());
        }
    }

    public static <K, V> void a(Map<K, V> map, K k, V v) {
        if (k == null || v == null) {
            return;
        }
        map.put(k, v);
    }

    public void b() {
        if (this.j.compareAndSet(false, true)) {
            a aVar = new a();
            String strA = b.a.a.a.a.a(new StringBuilder(), f1173b, "-query");
            if (TextUtils.isEmpty(strA)) {
                strA = "TrackerDr";
            }
            new Thread(new i(aVar, strA), strA).start();
        }
    }
}
