package com.alibaba.sdk.android.utils.crashdefend;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: b, reason: collision with root package name */
    private static b f2414b;

    /* renamed from: a, reason: collision with root package name */
    private Context f2415a;

    /* renamed from: a, reason: collision with other field name */
    private com.alibaba.sdk.android.utils.c f50a;

    /* renamed from: a, reason: collision with other field name */
    private c f52a;

    /* renamed from: b, reason: collision with other field name */
    private ExecutorService f55b;

    /* renamed from: a, reason: collision with other field name */
    private com.alibaba.sdk.android.utils.crashdefend.a f51a = new com.alibaba.sdk.android.utils.crashdefend.a();

    /* renamed from: a, reason: collision with other field name */
    private final List<c> f53a = new ArrayList();
    private Map<String, String> e = new HashMap();

    /* renamed from: a, reason: collision with other field name */
    private final int[] f54a = new int[5];

    public void d(String str, String str2) {
    }

    public static synchronized b a(Context context, com.alibaba.sdk.android.utils.c cVar) {
        if (f2414b == null) {
            f2414b = new b(context, cVar);
        }
        return f2414b;
    }

    private b(Context context, com.alibaba.sdk.android.utils.c cVar) {
        this.f55b = null;
        this.f2415a = context;
        this.f50a = cVar;
        this.f55b = new f().a();
        for (int i = 0; i < 5; i++) {
            this.f54a[i] = (i * 5) + 5;
        }
        this.e.put("sdkId", "utils");
        this.e.put(AccountMonitorConstants.NEW_SDK_VERSION_KEY, "1.1.4");
        try {
            a();
            b();
        } catch (Exception e) {
            Log.d("UtilsSDK", e.getMessage(), e);
        }
    }

    private void a() {
        if (e.m35a(this.f2415a, this.f51a, this.f53a)) {
            this.f51a.f2413a++;
        } else {
            this.f51a.f2413a = 1L;
        }
    }

    private void b() {
        this.f52a = null;
        ArrayList arrayList = new ArrayList();
        synchronized (this.f53a) {
            for (c cVar : this.f53a) {
                if (cVar.crashCount >= cVar.f2418a) {
                    arrayList.add(cVar);
                }
            }
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                c cVar2 = (c) it.next();
                if (cVar2.f2420c < 5) {
                    if (cVar2.f56a < this.f51a.f2413a - this.f54a[cVar2.f2420c]) {
                        this.f52a = cVar2;
                        break;
                    }
                } else {
                    Log.i("UtilsSDK", "SDK " + cVar2.f58a + " has been closed");
                }
            }
            if (this.f52a == null) {
                Log.i("UtilsSDK", "NO SDK restore");
            } else {
                this.f52a.f2420c++;
                Log.i("UtilsSDK", this.f52a.f58a + " will restore --- startSerialNumber:" + this.f52a.f56a + "   crashCount:" + this.f52a.crashCount);
            }
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m33a(c cVar, SDKMessageCallback sDKMessageCallback) {
        c cVarA;
        if (cVar == null || sDKMessageCallback == null) {
            return false;
        }
        try {
            if (TextUtils.isEmpty(cVar.f60b) || TextUtils.isEmpty(cVar.f58a) || (cVarA = a(cVar, sDKMessageCallback)) == null) {
                return false;
            }
            boolean zM32a = m32a(cVarA);
            if (cVarA.crashCount == cVarA.f2418a) {
                a(cVarA.f58a, cVarA.f60b, cVarA.crashCount, cVarA.f2418a);
            }
            cVarA.crashCount++;
            e.a(this.f2415a, this.f51a, this.f53a);
            if (zM32a) {
                a(cVarA);
                Log.i("UtilsSDK", "START:" + cVarA.f58a + " --- limit:" + cVarA.f2418a + "  count:" + (cVarA.crashCount - 1) + "  restore:" + cVarA.f2420c + "  startSerialNumber:" + cVarA.f56a + "  registerSerialNumber:" + cVarA.f59b);
            } else {
                sDKMessageCallback.crashDefendMessage(cVarA.f2418a, cVarA.crashCount - 1);
                Log.i("UtilsSDK", "STOP:" + cVarA.f58a + " --- limit:" + cVarA.f2418a + "  count:" + (cVarA.crashCount - 1) + "  restore:" + cVarA.f2420c + "  startSerialNumber:" + cVarA.f56a + "  registerSerialNumber:" + cVarA.f59b);
            }
            return true;
        } catch (Exception e) {
            Log.d("UtilsSDK", e.getMessage(), e);
            return false;
        }
    }

    private c a(c cVar, SDKMessageCallback sDKMessageCallback) {
        synchronized (this.f53a) {
            c cVar2 = null;
            if (this.f53a != null && this.f53a.size() > 0) {
                Iterator<c> it = this.f53a.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    c next = it.next();
                    if (next != null && next.f58a.equals(cVar.f58a)) {
                        if (!next.f60b.equals(cVar.f60b)) {
                            next.f60b = cVar.f60b;
                            next.f2418a = cVar.f2418a;
                            next.f2419b = cVar.f2419b;
                            next.crashCount = 0;
                            next.f2420c = 0;
                        }
                        if (next.d) {
                            Log.i("UtilsSDK", "SDK " + cVar.f58a + " has been registered");
                            return null;
                        }
                        next.d = true;
                        next.f57a = sDKMessageCallback;
                        next.f59b = this.f51a.f2413a;
                        cVar2 = next;
                    }
                }
            }
            if (cVar2 == null) {
                cVar2 = (c) cVar.clone();
                cVar2.d = true;
                cVar2.f57a = sDKMessageCallback;
                cVar2.crashCount = 0;
                cVar2.f59b = this.f51a.f2413a;
                this.f53a.add(cVar2);
            }
            return cVar2;
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    private boolean m32a(c cVar) {
        if (cVar.crashCount < cVar.f2418a) {
            cVar.f56a = cVar.f59b;
            return true;
        }
        if (this.f52a == null || !this.f52a.f58a.equals(cVar.f58a)) {
            return false;
        }
        cVar.crashCount = cVar.f2418a - 1;
        cVar.f56a = cVar.f59b;
        return true;
    }

    private void a(c cVar) {
        if (cVar == null) {
            return;
        }
        d dVar = new d();
        dVar.f2421b = cVar;
        dVar.d = cVar.f2419b;
        a(dVar);
        if (cVar.f57a != null) {
            cVar.f57a.crashDefendMessage(cVar.f2418a, cVar.crashCount - 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(c cVar) {
        if (cVar == null) {
            return;
        }
        if (cVar.f2420c > 0) {
            b(cVar.f58a, cVar.f60b, cVar.f2420c, 5);
        }
        cVar.crashCount = 0;
        cVar.f2420c = 0;
    }

    private void a(d dVar) {
        if (dVar == null || dVar.f2421b == null) {
            return;
        }
        this.f55b.execute(new a(dVar));
    }

    private void a(String str, String str2, int i, int i2) {
        if (this.f50a == null) {
            return;
        }
        HashMap map = new HashMap();
        map.putAll(this.e);
        map.put("crashSdkId", str);
        map.put("crashSdkVer", str2);
        map.put("curCrashCount", String.valueOf(i));
        map.put("crashThreshold", String.valueOf(i2));
        this.f50a.sendCustomHit("utils_biz_crash", 0L, map);
    }

    private void b(String str, String str2, int i, int i2) {
        if (this.f50a == null) {
            return;
        }
        HashMap map = new HashMap();
        map.putAll(this.e);
        map.put("crashSdkId", str);
        map.put("crashSdkVer", str2);
        map.put("recoverCount", String.valueOf(i));
        map.put("recoverThreshold", String.valueOf(i2));
        this.f50a.sendCustomHit("utils_biz_recover", 0L, map);
    }

    private class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private d f2416a;

        a(d dVar) {
            this.f2416a = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            do {
                try {
                    Thread.sleep(1000L);
                    d dVar = this.f2416a;
                    dVar.d--;
                } catch (InterruptedException unused) {
                    return;
                } catch (Exception e) {
                    Log.d("UtilsSDK", e.getMessage(), e);
                }
            } while (this.f2416a.d > 0);
            if (this.f2416a.d <= 0) {
                b.this.b(this.f2416a.f2421b);
                e.a(b.this.f2415a, b.this.f51a, (List<c>) b.this.f53a);
                return;
            }
            return;
        }
    }
}
