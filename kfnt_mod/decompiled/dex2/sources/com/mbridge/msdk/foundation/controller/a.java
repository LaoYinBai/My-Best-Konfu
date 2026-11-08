package com.mbridge.msdk.foundation.controller;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.entity.j;
import com.mbridge.msdk.foundation.tools.h;
import com.mbridge.msdk.foundation.tools.n;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.y;
import com.mbridge.msdk.foundation.tools.z;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.LinkedBlockingQueue;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: MBSDKContext.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    public static final String a = "a";
    private static a c;
    private Context d;
    private String e;
    private String f;
    private String g;
    private int h;
    private WeakReference<Context> i;
    private WeakReference<Activity> j;
    private JSONObject l;
    private int m;
    private String q;
    public static List<String> b = new ArrayList();
    private static CopyOnWriteArraySet<j> p = new CopyOnWriteArraySet<>();
    private int k = 0;
    private boolean n = false;
    private List<String> o = null;

    /* compiled from: MBSDKContext.java */
    public interface b {
        void a();
    }

    public final WeakReference<Activity> a() {
        return this.j;
    }

    public final void a(WeakReference<Activity> weakReference) {
        this.j = weakReference;
    }

    public final int b() {
        return this.h;
    }

    public final void a(int i) {
        this.h = i;
    }

    public final Context c() {
        if (this.i == null) {
            return null;
        }
        return this.i.get();
    }

    public final void a(Context context) {
        this.i = new WeakReference<>(context);
    }

    public final void b(int i) {
        this.m = i;
    }

    public final String d() {
        try {
            if (this.d != null) {
                return this.d.getPackageName();
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public final void a(String str) {
        try {
            this.q = str;
            if (TextUtils.isEmpty(str) || this.d == null) {
                return;
            }
            y.a(this.d, "applicationIds", str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Set<j> e() {
        return p;
    }

    private a() {
    }

    public static a f() {
        if (c == null) {
            synchronized (a.class) {
                if (c == null) {
                    c = new a();
                }
            }
        }
        return c;
    }

    public final void a(final b bVar, final Handler handler) throws JSONException {
        if (this.n) {
            return;
        }
        try {
            this.l = new JSONObject();
            this.l.put("webgl", 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            Object objB = y.b(this.d, "ga_id", "-1");
            if (objB != null && (objB instanceof String)) {
                String str = (String) objB;
                if (z.b(str) && !"-1".equals(str)) {
                    s.b(a, "sp init gaid:" + str);
                    if (com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_DEVICE_ID)) {
                        o.a(str);
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        new Thread(new Runnable() { // from class: com.mbridge.msdk.foundation.controller.a.1
            @Override // java.lang.Runnable
            public final void run() {
                if (com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_DEVICE_ID)) {
                    try {
                        Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
                        AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(a.this.d);
                        o.a(advertisingIdInfo.getId());
                        a.a(a.this, advertisingIdInfo.getId());
                    } catch (Exception unused) {
                        s.c(a.a, "GET ADID ERROR TRY TO GET FROM GOOGLE PLAY APP");
                        try {
                            C0108a.C0109a c0109aA = a.this.new C0108a().a(a.this.d);
                            o.a(c0109aA.a());
                            a.a(a.this, c0109aA.a());
                        } catch (Exception unused2) {
                            s.c(a.a, "GET ADID FROM GOOGLE PLAY APP ERROR");
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
                try {
                    com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(a.f().k());
                    if (aVarB == null) {
                        aVarB = com.mbridge.msdk.b.b.a().b();
                    }
                    Message messageObtain = Message.obtain();
                    messageObtain.obj = aVarB;
                    messageObtain.what = 9;
                    handler.sendMessage(messageObtain);
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
                try {
                    o.q(a.this.d);
                    a.g();
                    com.mbridge.msdk.b.b.a(a.this.d, a.this.f);
                    a.this.a(false);
                    a.this.a(bVar);
                } catch (Exception unused3) {
                }
            }
        }).start();
    }

    public static synchronized void g() {
        com.mbridge.msdk.b.a aVarB;
        try {
            if (!TextUtils.isEmpty(f().k()) && (aVarB = com.mbridge.msdk.b.b.a().b(f().k())) != null) {
                String strQ = aVarB.Q();
                if (!TextUtils.isEmpty(strQ)) {
                    String strB = n.b(strQ);
                    if (!TextUtils.isEmpty(strB)) {
                        JSONArray jSONArray = new JSONArray(strB);
                        if (jSONArray.length() > 0) {
                            ArrayList arrayList = new ArrayList();
                            for (int i = 0; i < jSONArray.length(); i++) {
                                arrayList.add(jSONArray.optString(i));
                            }
                            f().o = arrayList;
                        }
                    }
                }
            }
        } catch (Exception unused) {
        }
    }

    public final synchronized void a(b bVar) {
        try {
            p = h.a(this.d).a(this.f);
        } catch (Throwable th) {
            throw th;
        }
        if (p != null && p.size() != 0) {
            CopyOnWriteArraySet copyOnWriteArraySet = new CopyOnWriteArraySet();
            Iterator<j> it = p.iterator();
            if (it != null) {
                while (it.hasNext()) {
                    try {
                        j next = it.next();
                        if (b != null && b.size() > 0 && next != null) {
                            for (int i = 0; i < b.size(); i++) {
                                String str = b.get(i);
                                String strB = next.b();
                                if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(strB) && str.equals(strB)) {
                                    copyOnWriteArraySet.add(next);
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        s.d(a, "remove list error");
                    }
                }
            }
            if (p != null) {
                p.clear();
            }
            if (copyOnWriteArraySet.size() > 0) {
                p.addAll(copyOnWriteArraySet);
            }
            h.a(this.d).a(p);
            if (bVar != null) {
                bVar.a();
            }
            return;
        }
        if (bVar != null) {
            bVar.a();
        }
    }

    public final void h() {
        try {
            if (p == null || p.size() <= 0) {
                return;
            }
            h.a(this.d).a(p);
        } catch (Throwable unused) {
        }
    }

    public final List<Long> i() {
        try {
            if (p == null || p.size() <= 0) {
                return null;
            }
            Iterator<j> it = p.iterator();
            ArrayList arrayList = new ArrayList();
            while (it.hasNext()) {
                j next = it.next();
                if (!arrayList.contains(next.a())) {
                    try {
                        arrayList.add(Long.valueOf(Long.parseLong(next.a())));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
            return arrayList;
        } catch (Throwable unused) {
            return null;
        }
    }

    public final Context j() {
        return this.d;
    }

    public final void b(Context context) {
        this.d = context;
    }

    public final void b(String str) {
        this.e = str;
    }

    public final String k() {
        try {
            if (TextUtils.isEmpty(this.f)) {
                return this.d != null ? (String) y.b(this.d, "sp_appId", "") : "";
            }
            return this.f;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public final void c(String str) {
        try {
            this.f = str;
            if (TextUtils.isEmpty(str) || this.d == null) {
                return;
            }
            y.a(this.d, "sp_appId", str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final String l() {
        if (!TextUtils.isEmpty(this.g)) {
            return this.g;
        }
        if (this.d != null) {
            return (String) y.b(this.d, "sp_appKey", "");
        }
        return null;
    }

    public final void d(String str) {
        try {
            this.g = str;
            if (TextUtils.isEmpty(str) || this.d == null) {
                return;
            }
            y.a(this.d, "sp_appKey", str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final List<String> a(boolean z) {
        try {
            List<String> list = f().o;
            if (z) {
                return b;
            }
            if (b != null && b.size() > 0) {
                return b;
            }
            return b;
        } catch (Exception unused) {
            s.d(a, "get package info list error");
            return null;
        }
    }

    /* compiled from: MBSDKContext.java */
    /* renamed from: com.mbridge.msdk.foundation.controller.a$a, reason: collision with other inner class name */
    public class C0108a {
        public C0108a() {
        }

        /* compiled from: MBSDKContext.java */
        /* renamed from: com.mbridge.msdk.foundation.controller.a$a$a, reason: collision with other inner class name */
        public final class C0109a {
            private final String b;
            private final boolean c;

            C0109a(String str, boolean z) {
                this.b = str;
                this.c = z;
            }

            public final String a() {
                return this.b;
            }
        }

        public final C0109a a(Context context) throws Exception {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                throw new IllegalStateException("Cannot be called from the main thread");
            }
            try {
                context.getPackageManager().getPackageInfo("com.android.vending", 0);
                b bVar = new b();
                Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
                intent.setPackage("com.google.android.gms");
                if (context.bindService(intent, bVar, 1)) {
                    try {
                        try {
                            c cVar = new c(bVar.a());
                            return new C0109a(cVar.a(), cVar.a(true));
                        } catch (Exception e) {
                            throw e;
                        }
                    } finally {
                        context.unbindService(bVar);
                    }
                }
                throw new IOException("Google Play connection failed");
            } catch (Exception e2) {
                throw e2;
            }
        }

        /* compiled from: MBSDKContext.java */
        /* renamed from: com.mbridge.msdk.foundation.controller.a$a$b */
        private final class b implements ServiceConnection {
            boolean a;
            private final LinkedBlockingQueue<IBinder> c;

            @Override // android.content.ServiceConnection
            public final void onServiceDisconnected(ComponentName componentName) {
            }

            private b() {
                this.a = false;
                this.c = new LinkedBlockingQueue<>(1);
            }

            @Override // android.content.ServiceConnection
            public final void onServiceConnected(ComponentName componentName, IBinder iBinder) throws InterruptedException {
                try {
                    this.c.put(iBinder);
                } catch (InterruptedException unused) {
                }
            }

            public final IBinder a() throws InterruptedException {
                if (this.a) {
                    throw new IllegalStateException();
                }
                this.a = true;
                return this.c.take();
            }
        }

        /* compiled from: MBSDKContext.java */
        /* renamed from: com.mbridge.msdk.foundation.controller.a$a$c */
        private final class c implements IInterface {
            private IBinder b;

            public c(IBinder iBinder) {
                this.b = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.b;
            }

            public final String a() throws RemoteException {
                String string;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    try {
                        parcelObtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                        this.b.transact(1, parcelObtain, parcelObtain2, 0);
                        parcelObtain2.readException();
                        string = parcelObtain2.readString();
                    } catch (Throwable th) {
                        th.printStackTrace();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        string = null;
                    }
                    return string;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            public final boolean a(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                boolean z2 = false;
                try {
                    try {
                        parcelObtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                        parcelObtain.writeInt(z ? 1 : 0);
                        this.b.transact(2, parcelObtain, parcelObtain2, 0);
                        parcelObtain2.readException();
                        if (parcelObtain2.readInt() != 0) {
                            z2 = true;
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                    return z2;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }

    public final int m() {
        return this.k;
    }

    public final void c(int i) {
        this.k = i;
    }

    public final JSONObject n() {
        return this.l;
    }

    public final void a(JSONObject jSONObject) {
        this.l = jSONObject;
    }

    static /* synthetic */ void a(a aVar, String str) {
        try {
            if (z.b(str)) {
                s.b(a, "saveGAID gaid:" + str);
                y.a(aVar.d, "ga_id", str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
