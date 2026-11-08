package b.b.a.d;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import b.b.a.d.f;
import b.b.a.f.g;
import b.b.a.f.h;
import b.b.a.j.i;
import b.b.a.j.j;
import b.b.a.j.m;
import b.b.a.k.k;
import b.b.a.k.o;
import b.b.a.k.p;
import b.b.a.k.q;
import b.b.a.k.v;
import b.b.a.k.w;
import b.b.a.k.x;
import com.apm.applog.migrate.MigrateDetectorActivity;
import com.bytedance.common.utility.concurrent.ThreadPlus;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b implements Handler.Callback, Comparator<b.b.a.j.b> {

    /* renamed from: a, reason: collision with root package name */
    public static HandlerThread f1052a;

    /* renamed from: b, reason: collision with root package name */
    public boolean f1053b;

    /* renamed from: c, reason: collision with root package name */
    public Application f1054c;
    public g d;
    public volatile b.b.a.j.d f;
    public h g;
    public volatile Handler h;
    public d i;
    public e j;
    public com.apm.applog.e l;
    public Handler m;
    public long n;
    public volatile boolean o;
    public b.b.a.d.a p;
    public volatile c q;
    public volatile boolean s;
    public volatile long t;
    public volatile b.b.a.e.a v;
    public final ArrayList<b.b.a.j.b> e = new ArrayList<>(32);
    public CopyOnWriteArrayList<b.b.a.d.a> r = new CopyOnWriteArrayList<>();
    public final List<a> u = new ArrayList();
    public f k = new f(this);

    public abstract class a<T> {

        /* renamed from: a, reason: collision with root package name */
        public T f1055a;

        public a(b bVar, T t) {
            this.f1055a = t;
        }
    }

    /* renamed from: b.b.a.d.b$b, reason: collision with other inner class name */
    public class C0023b extends a<String> {
        public C0023b(String str) {
            super(b.this, str);
        }
    }

    public b(Application application, g gVar, h hVar) {
        int componentEnabledSetting;
        int i;
        this.f1054c = application;
        this.d = gVar;
        this.g = hVar;
        if (f1052a == null) {
            synchronized (b.class) {
                if (f1052a == null) {
                    HandlerThread handlerThread = new HandlerThread("bd_tracker_w");
                    handlerThread.start();
                    f1052a = handlerThread;
                }
            }
        }
        this.m = new Handler(f1052a.getLooper(), this);
        boolean z = this.d.e.getBoolean("is_first_app_launch", true);
        String strC = this.d.f1070b.C();
        if (!TextUtils.isEmpty(strC) && z) {
            this.g.e(strC);
        }
        if (z) {
            this.d.e.edit().putBoolean("is_first_app_launch", false).apply();
        }
        h hVar2 = this.g;
        ((b.b.a.k.g) hVar2.g).f.a(this.m);
        Context context = hVar2.f1073b;
        String strF = hVar2.f();
        Context applicationContext = context.getApplicationContext();
        SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("bdtracker_dr_migrate_detector_" + strF, 0);
        PackageManager packageManager = applicationContext.getPackageManager();
        ComponentName componentName = new ComponentName(context, (Class<?>) MigrateDetectorActivity.class);
        try {
            componentEnabledSetting = packageManager.getComponentEnabledSetting(componentName);
            i = sharedPreferences.getInt("component_state", 0);
            StringBuilder sbA = b.a.a.a.a.a("MigrateDetector#isMigrateInternal cs=");
            sbA.append(b.b.a.g.a.a(componentEnabledSetting));
            sbA.append(" ss=");
            sbA.append(b.b.a.g.a.a(i));
            v.a(sbA.toString());
        } catch (Exception unused) {
        }
        boolean z2 = componentEnabledSetting == 0 && i == 2;
        v.a("MigrateDetector#constructor migrate=" + z2);
        if (z2) {
            g gVar2 = hVar2.f1074c;
            if (gVar2 != null) {
                gVar2.e.edit().remove("google_aid").apply();
            }
            SharedPreferences sharedPreferences2 = hVar2.f;
            String strC2 = ((b.b.a.k.g) hVar2.g).c();
            if (sharedPreferences2 != null) {
                SharedPreferences.Editor editorEdit = sharedPreferences2.edit();
                editorEdit.putString("old_did", strC2);
                editorEdit.putBoolean("is_migrate", true);
                editorEdit.apply();
            }
            ((b.b.a.k.g) hVar2.g).b("openudid");
            ((b.b.a.k.g) hVar2.g).b("clientudid");
            ((b.b.a.k.g) hVar2.g).b("serial_number");
            ((b.b.a.k.g) hVar2.g).b("sim_serial_number");
            ((b.b.a.k.g) hVar2.g).b("udid");
            ((b.b.a.k.g) hVar2.g).b("udid_list");
            ((b.b.a.k.g) hVar2.g).b(com.bytedance.b.a.c.h.x);
            hVar2.a("clearMigrationInfo");
        }
        v.a("MigrateDetector#disableComponent");
        packageManager.setComponentEnabledSetting(componentName, 2, 1);
        sharedPreferences.edit().putInt("component_state", 2).apply();
        b.b.b.f.f1163b.b(hVar2.f1073b).b();
        if (this.d.f1070b.v()) {
            this.g.a(this.d.f1070b.w());
        }
        if (this.d.f1070b.ac() != null && !this.d.d()) {
            this.d.f1070b.ac();
        }
        this.m.sendEmptyMessage(10);
        if (this.d.f1070b.c()) {
            this.o = true;
            this.m.sendEmptyMessage(1);
        }
    }

    public static boolean a() {
        return false;
    }

    public void a(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() == 0) {
        }
    }

    public void a(boolean z, Context context) {
        o oVar = k.f1138a;
        if (oVar != null) {
            oVar.a(z, context);
        } else {
            v.a("can't find ET, should compile with ET", (Throwable) null);
        }
    }

    public void b(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() == 0) {
        }
    }

    @NonNull
    public com.apm.applog.e c() {
        if (this.l == null) {
            this.l = this.d.f1070b.q();
            if (this.l == null) {
                this.l = w.f1152a;
            }
        }
        return this.l;
    }

    public void c(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() == 0) {
        }
    }

    @Override // java.util.Comparator
    public int compare(b.b.a.j.b bVar, b.b.a.j.b bVar2) {
        long j = bVar.f1110c - bVar2.f1110c;
        if (j < 0) {
            return -1;
        }
        return j > 0 ? 1 : 0;
    }

    public void d() {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        b.b.a.e.a cVar;
        SharedPreferences sharedPreferences;
        HashSet hashSet;
        HashMap map;
        Map<String, ?> all;
        Set set;
        Set set2;
        String[] strArr = null;
        Object[] objArr = 0;
        switch (message.what) {
            case 1:
                g gVar = this.d;
                gVar.a(gVar.e.getBoolean("bav_log_collect", false));
                if (!this.g.d()) {
                    this.m.removeMessages(1);
                    this.m.sendEmptyMessageDelayed(1, 1000L);
                } else if (this.d.d()) {
                    HandlerThread handlerThread = new HandlerThread("bd_tracker_n");
                    handlerThread.start();
                    this.h = new Handler(handlerThread.getLooper(), this);
                    this.h.sendEmptyMessage(2);
                    if (this.e.size() > 0) {
                        this.m.removeMessages(4);
                        this.m.sendEmptyMessageDelayed(4, 1000L);
                    }
                    Application application = this.f1054c;
                    p.f1140a = true;
                    ThreadPlus.submitRunnable(new q(application));
                    v.a("net|worker start", (Throwable) null);
                }
                return true;
            case 2:
                this.i = new d(this);
                this.r.add(this.i);
                this.j = new e(this);
                this.r.add(this.j);
                c();
                if (this.g.f.getInt("version_code", 0) != this.g.b() || !TextUtils.equals(this.d.e.getString("channel", ""), this.d.b())) {
                    d dVar = this.i;
                    if (dVar != null) {
                        dVar.g();
                    }
                    if (this.d.f1070b.ab()) {
                        try {
                            this.f1054c.getSharedPreferences("sp_filter_name", 0).edit().clear().commit();
                        } catch (Throwable unused) {
                        }
                        a((b.b.a.e.a) null);
                    }
                } else if (this.d.f1070b.ab()) {
                    try {
                        sharedPreferences = this.f1054c.getSharedPreferences("sp_filter_name", 0);
                        hashSet = new HashSet();
                        map = new HashMap();
                        try {
                            all = sharedPreferences.getAll();
                        } catch (Throwable unused2) {
                            all = null;
                        }
                    } catch (Throwable unused3) {
                    }
                    if (all == null || all.size() <= 0) {
                        cVar = null;
                    } else {
                        int i = 0;
                        for (Map.Entry<String, ?> entry : all.entrySet()) {
                            if (entry != null) {
                                String key = entry.getKey();
                                if ("is_block".equals(key)) {
                                    i = sharedPreferences.getInt("is_block", 0);
                                } else if ("events".equals(key)) {
                                    try {
                                        set2 = (Set) entry.getValue();
                                    } catch (Throwable unused4) {
                                        set2 = null;
                                    }
                                    if (set2 != null && set2.size() > 0) {
                                        hashSet.addAll(set2);
                                    }
                                } else if (!TextUtils.isEmpty(key)) {
                                    HashSet hashSet2 = new HashSet();
                                    try {
                                        set = (Set) entry.getValue();
                                    } catch (Throwable unused5) {
                                        set = null;
                                    }
                                    if (set != null && set.size() > 0) {
                                        hashSet2.addAll(set);
                                    }
                                    if (hashSet2.size() > 0) {
                                        map.put(key, hashSet2);
                                    }
                                }
                            }
                        }
                        cVar = i > 0 ? new b.b.a.e.c(hashSet, map) : new b.b.a.e.b(hashSet, map);
                    }
                    a(cVar);
                }
                this.h.removeMessages(6);
                this.h.sendEmptyMessage(6);
                return true;
            case 3:
            case 5:
            default:
                v.a((Throwable) null);
                return true;
            case 4:
                a((String[]) message.obj, false);
                return true;
            case 6:
                this.h.removeMessages(6);
                long j = 5000;
                if (!this.d.f1070b.I() || this.k.c()) {
                    long j2 = Long.MAX_VALUE;
                    Iterator<b.b.a.d.a> it = this.r.iterator();
                    while (it.hasNext()) {
                        b.b.a.d.a next = it.next();
                        if (!next.e) {
                            long jA = next.a();
                            if (jA < j2) {
                                j2 = jA;
                            }
                        }
                    }
                    long jCurrentTimeMillis = j2 - System.currentTimeMillis();
                    if (jCurrentTimeMillis <= 5000) {
                        j = jCurrentTimeMillis;
                    }
                }
                this.h.sendEmptyMessageDelayed(6, j);
                if (this.u.size() > 0) {
                    synchronized (this.u) {
                        for (a aVar : this.u) {
                            if (aVar != null) {
                                C0023b c0023b = (C0023b) aVar;
                                b.this.a((String) c0023b.f1055a);
                            }
                        }
                        this.u.clear();
                    }
                }
                return true;
            case 7:
                synchronized (this.e) {
                    ArrayList<b.b.a.j.b> arrayList = this.e;
                    if (f.f1059c == null) {
                        f.f1059c = new f.b(objArr == true ? 1 : 0);
                    }
                    f.f1059c.a(0L);
                    arrayList.add(f.f1059c);
                }
                a((String[]) null, false);
                return true;
            case 8:
                ArrayList<b.b.a.j.b> arrayList2 = (ArrayList) message.obj;
                if (!a(arrayList2)) {
                    b().a(arrayList2);
                }
                return true;
            case 9:
                b.b.a.d.a aVar2 = this.p;
                if (!aVar2.e) {
                    long jA2 = aVar2.a();
                    if (!aVar2.e) {
                        this.h.sendEmptyMessageDelayed(9, jA2 - System.currentTimeMillis());
                    }
                }
                return true;
            case 10:
                synchronized (this.e) {
                    b.b.a.f.b.a(this.e);
                }
                int size = b.b.a.f.b.f1064b.size();
                if (size > 0) {
                    strArr = new String[size];
                    b.b.a.f.b.f1064b.toArray(strArr);
                    b.b.a.f.b.f1064b.clear();
                }
                a(strArr, false);
                return true;
            case 11:
            case 13:
                return true;
            case 12:
                Object[] objArr2 = (Object[]) message.obj;
                String str = (String) objArr2[0];
                j jVarA = (j) objArr2[1];
                a(this.j);
                if (jVarA == null && (jVarA = b.b.a.c.a.a()) != null) {
                    jVarA = (j) jVarA.clone();
                }
                ArrayList<b.b.a.j.b> arrayList3 = new ArrayList<>();
                long jCurrentTimeMillis2 = System.currentTimeMillis();
                if (jVarA != null) {
                    long j3 = jCurrentTimeMillis2 - jVarA.f1110c;
                    jVarA.a(jCurrentTimeMillis2);
                    jVarA.l = j3 >= 0 ? j3 : 0L;
                    jVarA.p = this.k.p;
                    this.k.b(jVarA);
                    arrayList3.add(jVarA);
                }
                if (this.g.e(str)) {
                    if (str != null) {
                        this.d.a(1);
                    }
                    this.s = true;
                    a(this.i);
                    a(true);
                }
                if (jVarA != null) {
                    j jVar = (j) jVarA.clone();
                    jVar.a(jCurrentTimeMillis2 + 1);
                    jVar.l = -1L;
                    this.k.a(jVar, arrayList3, true).o = this.k.p;
                    this.k.b(jVar);
                    arrayList3.add(jVar);
                }
                if (!arrayList3.isEmpty()) {
                    b().a(arrayList3);
                }
                a(this.j);
                return true;
            case 14:
                a((String[]) null, true);
                return true;
            case 15:
                Object[] objArr3 = (Object[]) message.obj;
                boolean zBooleanValue = ((Boolean) objArr3[0]).booleanValue();
                String str2 = (String) objArr3[1];
                if (zBooleanValue) {
                    if (this.q == null) {
                        this.q = new c(this, str2);
                        this.r.add(this.q);
                        this.h.removeMessages(6);
                        this.h.sendEmptyMessage(6);
                    }
                } else if (this.q != null) {
                    this.q.a(true);
                    this.r.remove(this.q);
                    this.q = null;
                }
                return true;
            case 16:
                a((b.b.a.j.b) message.obj);
                return true;
        }
    }

    public b.b.a.j.d b() {
        if (this.f == null) {
            synchronized (this) {
                b.b.a.j.d dVar = this.f;
                if (dVar == null) {
                    dVar = new b.b.a.j.d(this, this.d.f1070b.O());
                }
                this.f = dVar;
            }
        }
        return this.f;
    }

    public void a(b.b.a.j.b bVar) {
        c cVar = this.q;
        if (((bVar instanceof b.b.a.j.g) || (bVar instanceof b.b.a.j.k)) && cVar != null) {
            b.b.a.i.a.a(this, bVar.e(), cVar.f);
        }
    }

    public void d(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() == 0) {
        }
    }

    public final boolean a(ArrayList<b.b.a.j.b> arrayList) throws JSONException {
        boolean z = true;
        String[] strArrA = b.b.a.i.b.a(this, this.g.a(), true);
        JSONObject jSONObjectA = x.a(this.g.a());
        if (strArrA.length <= 0) {
            z = false;
        } else {
            int iA = b.b.a.i.a.a(strArrA, i.a(arrayList, jSONObjectA), this.d);
            if (iA != 200) {
                if (b.b.a.i.a.a(iA)) {
                    this.n = System.currentTimeMillis();
                }
                z = false;
            } else {
                this.n = 0L;
            }
        }
        v.a("sendRealTime, " + z);
        return z;
    }

    public boolean a(boolean z) {
        if ((!this.f1053b || z) && this.h != null) {
            this.f1053b = true;
            this.h.removeMessages(11);
            this.h.sendEmptyMessage(11);
        }
        return this.f1053b;
    }

    public void a(String[] strArr, boolean z) {
        ArrayList<b.b.a.j.b> arrayList;
        ArrayList<b.b.a.j.b> arrayListA;
        String str;
        synchronized (this.e) {
            arrayList = (ArrayList) this.e.clone();
            this.e.clear();
        }
        if (strArr != null) {
            arrayList.ensureCapacity(arrayList.size() + strArr.length);
            for (String str2 : strArr) {
                arrayList.add(b.b.a.j.b.a(str2));
            }
        }
        if (!arrayList.isEmpty()) {
            boolean zAb = this.d.f1070b.ab();
            b.b.a.e.a aVar = this.v;
            b.b.a.e.a aVar2 = com.apm.applog.a.g;
            if ((zAb && aVar != null) || aVar2 != null) {
                Iterator<b.b.a.j.b> it = arrayList.iterator();
                while (it.hasNext()) {
                    b.b.a.j.b next = it.next();
                    if (next instanceof b.b.a.j.g) {
                        b.b.a.j.g gVar = (b.b.a.j.g) next;
                        String str3 = gVar.n;
                        String strH = gVar.h();
                        if ((aVar2 != null && !aVar2.a(str3, strH)) || (aVar != null && !aVar.a(str3, strH))) {
                            it.remove();
                        }
                    } else if (next instanceof b.b.a.j.e) {
                        b.b.a.j.e eVar = (b.b.a.j.e) next;
                        if (aVar2 != null && !aVar2.a(eVar.m, eVar.o)) {
                            it.remove();
                        }
                    }
                }
            }
        }
        boolean zB = this.d.b(arrayList);
        if (arrayList.size() > 0 && this.d.d()) {
            if (!zB && arrayList.size() <= 100) {
                Iterator<b.b.a.j.b> it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    b(it2.next());
                }
            } else {
                if (k.a()) {
                    Iterator<b.b.a.j.b> it3 = arrayList.iterator();
                    while (it3.hasNext()) {
                        b.b.a.j.b next2 = it3.next();
                        if (next2 instanceof b.b.a.j.e) {
                            str = "event";
                        } else if (next2 instanceof b.b.a.j.g) {
                            str = "event_v3";
                        } else if (next2 instanceof b.b.a.j.f) {
                            str = "log_data";
                        } else if (next2 instanceof b.b.a.j.h) {
                            str = "launch";
                        } else {
                            str = next2 instanceof m ? "terminate" : null;
                        }
                        if (str != null) {
                            JSONArray jSONArray = new JSONArray();
                            jSONArray.put(next2.e());
                            k.a(str, jSONArray);
                        }
                    }
                }
                Collections.sort(arrayList, this);
                ArrayList<b.b.a.j.b> arrayList2 = new ArrayList<>(arrayList.size());
                Iterator<b.b.a.j.b> it4 = arrayList.iterator();
                boolean zA = false;
                boolean z2 = false;
                boolean zA2 = false;
                while (it4.hasNext()) {
                    b.b.a.j.b next3 = it4.next();
                    zA |= this.k.a(next3, arrayList2);
                    if (next3 instanceof j) {
                        zA2 = f.a(next3);
                        z2 = true;
                    }
                    if (Looper.myLooper() == Looper.getMainLooper()) {
                        this.h.obtainMessage(16, next3).sendToTarget();
                    } else {
                        a(next3);
                    }
                }
                String[] strArrD = c().d();
                if (this.h != null && strArrD != null && strArrD.length > 0 && System.currentTimeMillis() - this.n > 900000 && (arrayListA = this.d.a(arrayList2)) != null && arrayListA.size() > 0) {
                    this.h.obtainMessage(8, arrayListA).sendToTarget();
                }
                b().a(arrayList2);
                if (z2) {
                    if (zA2) {
                        this.m.removeMessages(7);
                    } else {
                        this.m.sendEmptyMessageDelayed(7, this.d.g());
                    }
                }
                if (zA) {
                    a(this.j);
                }
                if (!this.f1053b && this.k.l && this.h != null && this.d.f1070b.J()) {
                    a(false);
                }
            }
        }
        if (z && this.d.d()) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (Math.abs(jCurrentTimeMillis - this.t) > 10000) {
                this.t = jCurrentTimeMillis;
                a(this.j);
            }
        }
    }

    public final void a(b.b.a.d.a aVar) {
        if (this.h == null || aVar == null) {
            return;
        }
        aVar.g();
        if (Looper.myLooper() == this.h.getLooper()) {
            aVar.a();
        } else {
            this.h.removeMessages(6);
            this.h.sendEmptyMessage(6);
        }
    }

    public void b(b.b.a.j.b bVar) {
        int size;
        if (bVar.f1110c == 0) {
            v.a((Throwable) null);
        }
        synchronized (this.e) {
            size = this.e.size();
            this.e.add(bVar);
        }
        boolean z = bVar instanceof j;
        if (size % 10 == 0 || z) {
            this.m.removeMessages(4);
            if (!z && size == 0) {
                this.m.sendEmptyMessageDelayed(4, 300L);
            } else {
                this.m.sendEmptyMessage(4);
            }
        }
    }

    public void a(String str) {
        String strE = this.g.e();
        if ((!TextUtils.isEmpty(str) || TextUtils.isEmpty(strE)) && (TextUtils.isEmpty(str) || TextUtils.equals(str, strE))) {
            return;
        }
        if (this.h != null) {
            j jVar = null;
            j jVar2 = b.b.a.c.a.f1047b;
            j jVar3 = b.b.a.c.a.f1048c;
            if (jVar3 != null) {
                jVar = jVar3;
            } else if (jVar2 != null) {
                jVar = jVar2;
            }
            if (jVar != null) {
                jVar = (j) jVar.clone();
            }
            Message messageObtainMessage = this.h.obtainMessage(12, new Object[]{str, jVar});
            this.h.removeMessages(12);
            if (jVar != null && !TextUtils.isEmpty(this.k.p)) {
                messageObtainMessage.sendToTarget();
                return;
            } else {
                this.h.sendMessageDelayed(messageObtainMessage, 300L);
                return;
            }
        }
        synchronized (this.u) {
            this.u.add(new C0023b(str));
        }
    }

    public void a(b.b.a.e.a aVar) {
        this.v = aVar;
    }

    public void e(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() == 0) {
        }
    }
}
