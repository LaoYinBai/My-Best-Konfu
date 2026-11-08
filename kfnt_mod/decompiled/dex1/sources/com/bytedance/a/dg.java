package com.bytedance.a;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.bytedance.a.b;
import com.bytedance.applog.AppLog;
import com.bytedance.applog.IDataObserver;
import com.bytedance.applog.IOaidObserver;
import com.bytedance.applog.InitConfig;
import com.bytedance.applog.UriConfig;
import com.bytedance.applog.collector.Collector;
import com.bytedance.applog.migrate.MigrateDetectorActivity;
import com.bytedance.applog.util.UriConstants;
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
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class dg implements Handler.Callback, Comparator<bu> {

    /* renamed from: a, reason: collision with root package name */
    public static dg f3498a;
    public ag A;

    /* renamed from: b, reason: collision with root package name */
    public cr f3499b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f3500c;
    public Application d;
    public az e;
    public db f;
    public volatile ce h;
    public be i;
    public volatile Handler j;
    public dq k;
    public dv l;
    public volatile cm m;
    public UriConfig o;
    public Handler p;
    public long q;
    public volatile boolean r;
    public cw s;
    public volatile dl t;
    public volatile boolean v;
    public volatile long w;
    public volatile g y;
    public volatile InitConfig.IpcDataChecker z;
    public final ArrayList<bu> g = new ArrayList<>(32);
    public CopyOnWriteArrayList<cw> u = new CopyOnWriteArrayList<>();
    public final List<e> x = new ArrayList();
    public com.bytedance.a.b n = new com.bytedance.a.b(this);

    public class a implements IOaidObserver {
        public a(dg dgVar) {
        }

        @Override // com.bytedance.applog.IOaidObserver
        public void onOaidLoaded(@NonNull IOaidObserver.Oaid oaid) {
            SharedPreferences.Editor editorEdit;
            SharedPreferences.Editor editorPutString;
            String str = oaid.id;
            if (str != null) {
                Intrinsics.checkParameterIsNotNull("alink_oaid", "key");
                SharedPreferences sharedPreferences = p.f3536a;
                if (sharedPreferences == null || (editorEdit = sharedPreferences.edit()) == null || (editorPutString = editorEdit.putString("alink_oaid", str)) == null) {
                    return;
                }
                editorPutString.apply();
            }
        }
    }

    public class b implements Application.ActivityLifecycleCallbacks {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Application f3501a;

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                u.g.a(dg.this.j, dg.this.c(), dg.this.i, 1);
            }
        }

        public b(Application application) {
            this.f3501a = application;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) throws JSONException {
            JSONObject jSONObjectA = cc.f3455a.a(activity.getIntent().getData());
            u.g.a(dg.this.i);
            if ((jSONObjectA != null ? jSONObjectA.optString("tr_token") : null) == null) {
                dg.this.p.postDelayed(new a(), 1000L);
            }
            this.f3501a.unregisterActivityLifecycleCallbacks(this);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }
    }

    public class c implements IDataObserver {
        public c() {
        }

        @Override // com.bytedance.applog.IDataObserver
        public void onAbVidsChange(String str, String str2) {
        }

        @Override // com.bytedance.applog.IDataObserver
        public void onIdLoaded(String str, String str2, String str3) {
            if (TextUtils.isEmpty(dg.this.i.g())) {
                return;
            }
            u.g.a(dg.this.j, dg.this.c(), dg.this.i, 2);
        }

        @Override // com.bytedance.applog.IDataObserver
        public void onRemoteAbConfigGet(boolean z, JSONObject jSONObject) {
        }

        @Override // com.bytedance.applog.IDataObserver
        public void onRemoteConfigGet(boolean z, JSONObject jSONObject) {
        }

        @Override // com.bytedance.applog.IDataObserver
        public void onRemoteIdGet(boolean z, String str, String str2, String str3, String str4, String str5, String str6) {
            u.g.a(dg.this.j, dg.this.c(), dg.this.i, 2);
        }
    }

    public class d extends e<JSONObject> {
        public d(JSONObject jSONObject) {
            super(dg.this, jSONObject);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.bytedance.a.dg.e
        public void a() {
            u uVar = u.g;
            JSONObject jSONObject = (JSONObject) this.f3506b;
            dg dgVar = dg.this;
            uVar.a(jSONObject, dgVar.i, dgVar.c(), dg.this.j);
        }
    }

    public abstract class e<T> {

        /* renamed from: b, reason: collision with root package name */
        public T f3506b;

        public e(dg dgVar, T t) {
            this.f3506b = t;
        }

        public abstract void a();
    }

    public class f extends e<String> {
        public f(String str) {
            super(dg.this, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.bytedance.a.dg.e
        public void a() {
            dg.this.a((String) this.f3506b);
        }
    }

    public dg(Application application, az azVar, be beVar) {
        int componentEnabledSetting;
        int i;
        this.d = application;
        this.e = azVar;
        this.i = beVar;
        HandlerThread handlerThread = new HandlerThread("bd_tracker_w");
        handlerThread.start();
        this.p = new Handler(handlerThread.getLooper(), this);
        Intrinsics.checkParameterIsNotNull(application, "applicationContext");
        p.f3536a = application.getSharedPreferences("ALINK_CACHE_SP", 0);
        boolean z = this.e.e.getBoolean("is_first_app_launch", true);
        String userUniqueId = this.e.f3386b.getUserUniqueId();
        if (!TextUtils.isEmpty(userUniqueId) && z) {
            this.i.e(userUniqueId);
        }
        if (z) {
            this.e.e.edit().putBoolean("is_first_app_launch", false).apply();
        }
        if (this.e.f3386b.isDeferredALinkEnabled()) {
            AppLog.setOaidObserver(new a(this));
            application.registerActivityLifecycleCallbacks(new b(application));
            n.a().a(new c());
        }
        be beVar2 = this.i;
        ((ah) beVar2.h).i.a(this.p);
        Context context = beVar2.f3397c;
        Context applicationContext = context.getApplicationContext();
        SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("bdtracker_dr_migrate_detector", 0);
        PackageManager packageManager = applicationContext.getPackageManager();
        ComponentName componentName = new ComponentName(context, (Class<?>) MigrateDetectorActivity.class);
        try {
            componentEnabledSetting = packageManager.getComponentEnabledSetting(componentName);
            i = sharedPreferences.getInt("component_state", 0);
            StringBuilder sbA = com.bytedance.a.a.a("MigrateDetector#isMigrateInternal cs=");
            sbA.append(dw.a(componentEnabledSetting));
            sbA.append(" ss=");
            sbA.append(dw.a(i));
            de.a(sbA.toString(), null);
        } catch (Exception unused) {
        }
        boolean z2 = componentEnabledSetting == 0 && i == 2;
        de.a("MigrateDetector#constructor migrate=" + z2, null);
        if (z2) {
            az azVar2 = beVar2.d;
            if (azVar2 != null) {
                azVar2.e.edit().remove("google_aid").apply();
            }
            SharedPreferences sharedPreferences2 = beVar2.g;
            String strG = ((ah) beVar2.h).g();
            if (sharedPreferences2 != null) {
                SharedPreferences.Editor editorEdit = sharedPreferences2.edit();
                editorEdit.putString("old_did", strG);
                editorEdit.putBoolean("is_migrate", true);
                editorEdit.apply();
            }
            ((ah) beVar2.h).b("openudid");
            ((ah) beVar2.h).b("clientudid");
            ((ah) beVar2.h).b("serial_number");
            ((ah) beVar2.h).b("sim_serial_number");
            ((ah) beVar2.h).b("udid");
            ((ah) beVar2.h).b("udid_list");
            ((ah) beVar2.h).b(com.bytedance.b.a.c.h.x);
            beVar2.a("clearMigrationInfo");
        }
        de.a("MigrateDetector#disableComponent", null);
        packageManager.setComponentEnabledSetting(componentName, 2, 1);
        sharedPreferences.edit().putInt("component_state", 2).apply();
        o.f3535b.b(beVar2.f3397c).b();
        this.A = new ag(this);
        if (this.e.f3386b.isClearDidAndIid()) {
            this.i.a(this.e.f3386b.getClearKey());
        }
        if (this.e.f3386b.getIpcDataChecker() != null && !this.e.c()) {
            this.z = this.e.f3386b.getIpcDataChecker();
        }
        this.p.sendEmptyMessage(10);
        if (this.e.f3386b.autoStart()) {
            this.r = true;
            this.p.sendEmptyMessage(1);
        }
        f3498a = this;
    }

    public static boolean a() {
        dg dgVar = f3498a;
        if (dgVar == null) {
            return true;
        }
        az azVar = dgVar.e;
        return azVar.q == 1 && azVar.f();
    }

    public void a(Uri uri) {
        u.g.a(this.i);
        JSONObject jSONObjectA = cc.f3455a.a(uri);
        if (jSONObjectA == null || jSONObjectA.optString("tr_token") == null) {
            return;
        }
        if (this.j == null) {
            this.x.add(new d(jSONObjectA));
        } else {
            u.g.a(jSONObjectA, this.i, c(), this.j);
        }
    }

    public final void a(cw cwVar) {
        if (this.j == null || cwVar == null || AppLog.isPrivacyMode()) {
            return;
        }
        cwVar.g();
        if (Looper.myLooper() == this.j.getLooper()) {
            cwVar.f();
        } else {
            this.j.removeMessages(6);
            this.j.sendEmptyMessage(6);
        }
    }

    public void a(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() == 0) {
            return;
        }
        this.A.a(jSONObject);
    }

    public boolean a(boolean z) {
        if ((!this.f3500c || z) && this.j != null) {
            this.f3500c = true;
            this.j.removeMessages(11);
            this.j.sendEmptyMessage(11);
        }
        return this.f3500c;
    }

    public ce b() {
        if (this.h == null) {
            synchronized (this) {
                ce ceVar = this.h;
                if (ceVar == null) {
                    ceVar = new ce(this, this.e.f3386b.getDbName());
                }
                this.h = ceVar;
            }
        }
        return this.h;
    }

    public void b(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() == 0) {
            return;
        }
        this.A.b(jSONObject);
    }

    @NonNull
    public UriConfig c() {
        if (this.o == null) {
            this.o = this.e.f3386b.getUriConfig();
            if (this.o == null) {
                this.o = UriConstants.createUriConfig(0);
            }
        }
        return this.o;
    }

    public void c(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() == 0) {
            return;
        }
        this.A.d(jSONObject);
    }

    @Override // java.util.Comparator
    public int compare(bu buVar, bu buVar2) {
        long j = buVar.f3434c - buVar2.f3434c;
        if (j < 0) {
            return -1;
        }
        return j > 0 ? 1 : 0;
    }

    public void d(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() == 0) {
            return;
        }
        this.A.c(jSONObject);
    }

    public void e(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() == 0) {
            return;
        }
        this.A.e(jSONObject);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) throws JSONException {
        g qVar;
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
                az azVar = this.e;
                azVar.q = azVar.e.getBoolean("bav_log_collect", false) ? 1 : 0;
                if (!this.i.d()) {
                    this.p.removeMessages(1);
                    this.p.sendEmptyMessageDelayed(1, 1000L);
                } else if (this.e.c()) {
                    HandlerThread handlerThread = new HandlerThread("bd_tracker_n");
                    handlerThread.start();
                    this.j = new Handler(handlerThread.getLooper(), this);
                    this.j.sendEmptyMessage(2);
                    if (this.g.size() > 0) {
                        this.p.removeMessages(4);
                        this.p.sendEmptyMessageDelayed(4, 1000L);
                    }
                    Application application = this.d;
                    ca.f3450a = true;
                    ThreadPlus.submitRunnable(new cf(application));
                    de.a("net|worker start", null);
                }
                return true;
            case 2:
                this.k = new dq(this);
                this.u.add(this.k);
                this.l = new dv(this);
                this.u.add(this.l);
                UriConfig uriConfigC = c();
                if (!TextUtils.isEmpty(uriConfigC.getSettingUri())) {
                    this.f = new db(this);
                    this.u.add(this.f);
                }
                if (!TextUtils.isEmpty(uriConfigC.getProfileUri())) {
                    Handler handler = this.A.f3342b;
                    handler.sendMessage(handler.obtainMessage(106));
                }
                this.j.removeMessages(13);
                this.j.sendEmptyMessage(13);
                if (this.i.g.getInt("version_code", 0) != this.i.b() || !TextUtils.equals(this.e.e.getString("channel", ""), this.e.a())) {
                    dq dqVar = this.k;
                    if (dqVar != null) {
                        dqVar.g();
                    }
                    db dbVar = this.f;
                    if (dbVar != null) {
                        dbVar.g();
                    }
                    if (this.e.f3386b.isEventFilterEnable()) {
                        this.y = g.a(this.d, (JSONObject) null);
                    }
                } else if (this.e.f3386b.isEventFilterEnable()) {
                    try {
                        sharedPreferences = this.d.getSharedPreferences("sp_filter_name", 0);
                        hashSet = new HashSet();
                        map = new HashMap();
                        try {
                            all = sharedPreferences.getAll();
                        } catch (Throwable unused) {
                            all = null;
                        }
                    } catch (Throwable unused2) {
                    }
                    if (all == null || all.size() <= 0) {
                        qVar = null;
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
                                    } catch (Throwable unused3) {
                                        set2 = null;
                                    }
                                    if (set2 != null && set2.size() > 0) {
                                        hashSet.addAll(set2);
                                    }
                                } else if (!TextUtils.isEmpty(key)) {
                                    HashSet hashSet2 = new HashSet();
                                    try {
                                        set = (Set) entry.getValue();
                                    } catch (Throwable unused4) {
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
                        qVar = i > 0 ? new q(hashSet, map) : new l(hashSet, map);
                    }
                    this.y = qVar;
                }
                this.j.removeMessages(6);
                this.j.sendEmptyMessage(6);
                return true;
            case 3:
            case 5:
            default:
                de.a("U SHALL NOT PASS!", null);
                return true;
            case 4:
                a((String[]) message.obj, false);
                return true;
            case 6:
                this.j.removeMessages(6);
                long j = 5000;
                if (!AppLog.isPrivacyMode() && (!this.e.f3386b.isSilenceInBackground() || this.n.c())) {
                    long j2 = Long.MAX_VALUE;
                    Iterator<cw> it = this.u.iterator();
                    while (it.hasNext()) {
                        cw next = it.next();
                        if (!next.e) {
                            long jF = next.f();
                            if (jF < j2) {
                                j2 = jF;
                            }
                        }
                    }
                    long jCurrentTimeMillis = j2 - System.currentTimeMillis();
                    if (jCurrentTimeMillis <= 5000) {
                        j = jCurrentTimeMillis;
                    }
                }
                this.j.sendEmptyMessageDelayed(6, j);
                if (this.x.size() > 0) {
                    synchronized (this.x) {
                        for (e eVar : this.x) {
                            if (eVar != null) {
                                eVar.a();
                            }
                        }
                        this.x.clear();
                    }
                }
                return true;
            case 7:
                synchronized (this.g) {
                    ArrayList<bu> arrayList = this.g;
                    if (com.bytedance.a.b.f3390c == null) {
                        com.bytedance.a.b.f3390c = new b.C0096b(objArr == true ? 1 : 0);
                    }
                    com.bytedance.a.b.f3390c.a(0L);
                    arrayList.add(com.bytedance.a.b.f3390c);
                }
                a((String[]) null, false);
                return true;
            case 8:
                ArrayList<bu> arrayList2 = (ArrayList) message.obj;
                if (!a(arrayList2)) {
                    b().b(arrayList2);
                }
                return true;
            case 9:
                cw cwVar = this.s;
                if (!cwVar.e) {
                    long jF2 = cwVar.f();
                    if (!cwVar.e) {
                        this.j.sendEmptyMessageDelayed(9, jF2 - System.currentTimeMillis());
                    }
                }
                return true;
            case 10:
                synchronized (this.g) {
                    aa.a(this.g);
                }
                int size = aa.f3331b.size();
                if (size > 0) {
                    strArr = new String[size];
                    aa.f3331b.toArray(strArr);
                    aa.f3331b.clear();
                }
                a(strArr, false);
                return true;
            case 11:
                cr crVar = this.f3499b;
                if (crVar == null) {
                    this.f3499b = new cr(this);
                    this.u.add(this.f3499b);
                } else {
                    crVar.e = false;
                }
                a(this.f3499b);
                return true;
            case 12:
                Object[] objArr2 = (Object[]) message.obj;
                String str = (String) objArr2[0];
                di diVar = (di) objArr2[1];
                a(this.l);
                if (diVar == null) {
                    diVar = ch.f3469b;
                    di diVar2 = ch.f3470c;
                    if (diVar2 != null) {
                        diVar = diVar2;
                    } else if (diVar == null) {
                        diVar = null;
                    }
                    if (diVar != null) {
                        diVar = (di) diVar.clone();
                    }
                }
                ArrayList<bu> arrayList3 = new ArrayList<>();
                long jCurrentTimeMillis2 = System.currentTimeMillis();
                if (diVar != null) {
                    long j3 = jCurrentTimeMillis2 - diVar.f3434c;
                    diVar.a(jCurrentTimeMillis2);
                    diVar.m = j3 >= 0 ? j3 : 0L;
                    diVar.q = this.n.o;
                    this.n.b(diVar);
                    arrayList3.add(diVar);
                }
                if (this.i.e(str)) {
                    if (str != null) {
                        this.e.e.edit().putInt("is_first_time_launch", 1).apply();
                    }
                    this.v = true;
                    a(this.k);
                    a(true);
                }
                if (diVar != null) {
                    di diVar3 = (di) diVar.clone();
                    diVar3.a(jCurrentTimeMillis2 + 1);
                    diVar3.m = -1L;
                    this.n.a(diVar3, arrayList3, true).p = this.n.o;
                    this.n.b(diVar3);
                    arrayList3.add(diVar3);
                }
                if (!arrayList3.isEmpty()) {
                    b().b(arrayList3);
                }
                a(this.l);
                return true;
            case 13:
                if (!this.e.e.getBoolean("bav_ab_config", false) || !this.e.f3386b.isAbEnable() || TextUtils.isEmpty(c().getAbUri())) {
                    if (this.m != null) {
                        this.m.e = true;
                        this.u.remove(this.m);
                        this.m = null;
                    }
                    be beVar = this.i;
                    beVar.d(null);
                    beVar.b("");
                    beVar.d.a((JSONObject) null);
                    beVar.a((JSONObject) null);
                } else if (this.m == null) {
                    this.m = new cm(this);
                    this.u.add(this.m);
                    a(this.m);
                }
                return true;
            case 14:
                a((String[]) null, true);
                return true;
            case 15:
                Object[] objArr3 = (Object[]) message.obj;
                boolean zBooleanValue = ((Boolean) objArr3[0]).booleanValue();
                String str2 = (String) objArr3[1];
                if (zBooleanValue) {
                    if (this.t == null) {
                        this.t = new dl(this, str2);
                        this.u.add(this.t);
                        this.j.removeMessages(6);
                        this.j.sendEmptyMessage(6);
                    }
                } else if (this.t != null) {
                    this.t.e = true;
                    this.u.remove(this.t);
                    this.t = null;
                }
                return true;
            case 16:
                b((bu) message.obj);
                return true;
        }
    }

    public static void a(bu buVar) {
        int size;
        if (buVar.f3434c == 0) {
            de.a("U SHALL NOT PASS!", null);
        }
        dg dgVar = f3498a;
        if (dgVar == null) {
            aa.a(buVar);
            return;
        }
        synchronized (dgVar.g) {
            size = dgVar.g.size();
            dgVar.g.add(buVar);
        }
        boolean z = buVar instanceof di;
        if (size % 10 == 0 || z) {
            dgVar.p.removeMessages(4);
            if (z || size != 0) {
                dgVar.p.sendEmptyMessage(4);
            } else {
                dgVar.p.sendEmptyMessageDelayed(4, 300L);
            }
        }
    }

    public void a(String[] strArr, boolean z) {
        ArrayList<bu> arrayList;
        ArrayList<bu> arrayListA;
        if (AppLog.isPrivacyMode()) {
            return;
        }
        synchronized (this.g) {
            arrayList = (ArrayList) this.g.clone();
            this.g.clear();
        }
        if (strArr != null) {
            arrayList.ensureCapacity(arrayList.size() + strArr.length);
            for (String str : strArr) {
                arrayList.add(bu.a(str));
            }
        }
        if (!arrayList.isEmpty()) {
            boolean zIsEventFilterEnable = this.e.f3386b.isEventFilterEnable();
            g gVar = this.y;
            g gVar2 = AppLog.sEventFilterFromClient;
            if ((zIsEventFilterEnable && gVar != null) || gVar2 != null) {
                Iterator<bu> it = arrayList.iterator();
                while (it.hasNext()) {
                    bu next = it.next();
                    if (next instanceof ct) {
                        ct ctVar = (ct) next;
                        String str2 = ctVar.o;
                        String strI = ctVar.i();
                        if ((gVar2 != null && !gVar2.a(str2, strI)) || (gVar != null && !gVar.a(str2, strI))) {
                            it.remove();
                        }
                    } else if (next instanceof cj) {
                        cj cjVar = (cj) next;
                        if (gVar2 != null && !gVar2.a(cjVar.n, cjVar.p)) {
                            it.remove();
                        }
                    }
                }
            }
        }
        boolean zB = this.e.b(arrayList);
        if (arrayList.size() > 0) {
            boolean zCheckIpcData = true;
            if (!this.e.c()) {
                Intent intent = new Intent(this.d, (Class<?>) Collector.class);
                int size = arrayList.size();
                String[] strArr2 = new String[size];
                int length = 0;
                for (int i = 0; i < size; i++) {
                    strArr2[i] = arrayList.get(i).e().toString();
                    length += strArr2[i].length();
                }
                if (length >= 307200 && this.z != null) {
                    try {
                        zCheckIpcData = this.z.checkIpcData(strArr2);
                    } catch (Throwable th) {
                        de.a("check ipc data", th);
                    }
                    de.a("U SHALL NOT PASS!", null);
                }
                if (zCheckIpcData) {
                    intent.putExtra("K_DATA", strArr2);
                    this.d.sendBroadcast(intent);
                }
            } else if (zB || arrayList.size() > 100) {
                if (bb.a()) {
                    Iterator<bu> it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        bu next2 = it2.next();
                        String str3 = next2 instanceof cj ? "event" : next2 instanceof ct ? "event_v3" : next2 instanceof co ? "log_data" : next2 instanceof cy ? "launch" : next2 instanceof dx ? "terminate" : null;
                        if (str3 != null) {
                            JSONArray jSONArray = new JSONArray();
                            jSONArray.put(next2.f());
                            bb.a(str3, jSONArray);
                        }
                    }
                }
                Collections.sort(arrayList, this);
                ArrayList<bu> arrayList2 = new ArrayList<>(arrayList.size());
                Iterator<bu> it3 = arrayList.iterator();
                boolean zA = false;
                boolean z2 = false;
                boolean zA2 = false;
                while (it3.hasNext()) {
                    bu next3 = it3.next();
                    zA |= this.n.a(next3, arrayList2);
                    if (next3 instanceof di) {
                        zA2 = com.bytedance.a.b.a(next3);
                        z2 = true;
                    }
                    if (Looper.myLooper() == Looper.getMainLooper()) {
                        this.j.obtainMessage(16, next3).sendToTarget();
                    } else {
                        b(next3);
                    }
                }
                String[] realUris = c().getRealUris();
                if (this.j != null && realUris != null && realUris.length > 0 && System.currentTimeMillis() - this.q > 900000 && (arrayListA = this.e.a(arrayList2)) != null && arrayListA.size() > 0) {
                    this.j.obtainMessage(8, arrayListA).sendToTarget();
                }
                b().b(arrayList2);
                if (z2) {
                    if (zA2) {
                        this.p.removeMessages(7);
                    } else {
                        this.p.sendEmptyMessageDelayed(7, this.e.g());
                    }
                }
                if (zA) {
                    a(this.l);
                }
                if (!this.f3500c && this.n.k && this.j != null && this.e.f3386b.isAutoActive()) {
                    a(false);
                }
            } else {
                Iterator<bu> it4 = arrayList.iterator();
                while (it4.hasNext()) {
                    a(it4.next());
                }
            }
        }
        if (z && this.e.c()) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (Math.abs(jCurrentTimeMillis - this.w) > 10000) {
                this.w = jCurrentTimeMillis;
                a(this.l);
            }
        }
    }

    public final boolean a(ArrayList<bu> arrayList) throws JSONException {
        boolean z = true;
        String[] strArrA = bk.a(this, this.i.a(), true, 0);
        JSONObject jSONObjectA = dj.a(this.i.a());
        if (strArrA.length <= 0) {
            z = false;
        } else {
            int iA = bf.a(strArrA, dd.a(arrayList, jSONObjectA), this.e);
            if (iA != 200) {
                if (bf.a(iA)) {
                    this.q = System.currentTimeMillis();
                }
                z = false;
            } else {
                this.q = 0L;
            }
        }
        de.a("sendRealTime, " + z, null);
        return z;
    }

    public void b(bu buVar) throws JSONException {
        dl dlVar = this.t;
        if (((buVar instanceof ct) || (buVar instanceof dn)) && dlVar != null) {
            bf.a(buVar.f(), dlVar.f);
        }
    }

    public void a(boolean z, Context context) {
        bv bvVar = bb.f3394a;
        if (bvVar != null) {
            bvVar.a(z, context);
        } else {
            de.a("can't find ET, should compile with ET", null);
        }
    }

    public void a(String str) {
        String strE = this.i.e();
        if ((!TextUtils.isEmpty(str) || TextUtils.isEmpty(strE)) && (TextUtils.isEmpty(str) || TextUtils.equals(str, strE))) {
            return;
        }
        if (this.j == null) {
            synchronized (this.x) {
                this.x.add(new f(str));
            }
            return;
        }
        di diVar = null;
        di diVar2 = ch.f3469b;
        di diVar3 = ch.f3470c;
        if (diVar3 != null) {
            diVar = diVar3;
        } else if (diVar2 != null) {
            diVar = diVar2;
        }
        if (diVar != null) {
            diVar = (di) diVar.clone();
        }
        Message messageObtainMessage = this.j.obtainMessage(12, new Object[]{str, diVar});
        this.j.removeMessages(12);
        if (diVar == null || TextUtils.isEmpty(this.n.o)) {
            this.j.sendMessageDelayed(messageObtainMessage, 300L);
        } else {
            messageObtainMessage.sendToTarget();
        }
    }
}
