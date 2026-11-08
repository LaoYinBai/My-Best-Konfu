package com.ss.sys.ces;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import c.a.a.a.a.aj;
import c.a.a.a.a.ap;
import c.a.a.a.a.at;
import c.a.a.a.a.az;
import c.a.a.a.a.bb;
import c.a.a.a.a.bd;
import c.a.a.a.a.bf;
import c.a.a.a.a.bg;
import c.a.a.a.a.bh;
import c.a.a.a.a.bj;
import c.a.a.a.a.bl;
import c.a.a.a.a.bm;
import com.sigmob.sdk.common.Constants;
import com.ss.android.downloadlib.applink.AppLinkMonitor;
import com.ss.android.downloadlib.constants.DownloadConstants;
import com.ss.android.socialbase.downloader.constants.DownloadErrorCode;
import com.ss.sys.ces.gg.tt;
import com.ss.sys.ces.out.ISdk;
import com.ss.sys.ces.out.ISdkEx;
import com.ss.sys.ces.utils.NetInterface;
import com.ss.union.game.sdk.core.splashEffect.view.a;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b extends Thread implements ISdk {
    private static volatile b d = null;
    public static Context e = null;
    public static long f = 0;
    public static String g = "";
    public static String h = "";
    private static boolean i = false;
    public static String j = "";
    public static boolean k;
    public static boolean l;
    private static int m;
    private static int n;
    private static int o;
    private static boolean p;
    private static Map<String, Object> q;
    public boolean a = false;
    private boolean b = false;
    private boolean c = true;

    static class a extends Thread {
        final /* synthetic */ Context a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(String str, Context context) {
            super(str);
            this.a = context;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            d.a = 0;
            d.b(this.a);
        }
    }

    /* renamed from: com.ss.sys.ces.b$b, reason: collision with other inner class name */
    static class C0044b implements ap {
        C0044b() {
        }

        public void a(String str) {
            Log.d("CZL_META", "[+] Welcome back, Mr Anderson, we all missed you");
            boolean unused = b.p = true;
        }
    }

    class c extends Thread {
        final /* synthetic */ String a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(b bVar, String str, String str2) {
            super(str);
            this.a = str2;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                long timeInMillis = b.b().a ? Calendar.getInstance().getTimeInMillis() : 0L;
                byte[] bArr = (byte[]) com.ss.sys.ces.a.meta(a.c.b, b.e, this.a);
                long timeInMillis2 = b.b().a ? Calendar.getInstance().getTimeInMillis() : 0L;
                if (b.b().a) {
                    Log.d("CZL_Efficient", "[Efficient] report : " + (timeInMillis2 - timeInMillis));
                }
                if (bArr == null || bArr.length <= 0) {
                    throw new NullPointerException("NullPointerException");
                }
                try {
                    new c.a.a.a.a.c(b.e, b.h).a(bArr);
                } catch (Throwable unused) {
                    new bl(b.e, b.h).a(1, 2, bArr);
                }
            } catch (Throwable unused2) {
                Log.e("CZL_META", "[-] a little bird cut off");
            }
        }
    }

    private b(Context context, long j2) {
        setName("CZL-00");
        e = context;
        f = j2;
    }

    public static b a(Context context, long j2, int i2) {
        if (d == null) {
            synchronized (b.class) {
                if (d == null) {
                    d = new b(context, j2);
                }
                at.a(i2);
                at.b((int) f);
                try {
                    tt.init_gorgon();
                } catch (Throwable unused) {
                    Log.e("CZL_META", "[-] ****** CRITICAL ERROR: network init failed!!!");
                }
                a(context);
            }
        }
        return d;
    }

    private static void a(Context context) {
        synchronized (b.class) {
            if (context != null) {
                if (!i) {
                    try {
                        com.ss.sys.ces.a.meta(101, null, Constants.FAIL);
                        com.ss.sys.ces.a.meta(102, null, "" + ((int) f));
                        com.ss.sys.ces.a.meta(DownloadErrorCode.ERROR_THREAD_INTERRUPT, null, "" + g);
                        com.ss.sys.ces.a.meta(105, null, "" + az.b(context));
                        com.ss.sys.ces.a.meta(106, null, "" + az.a(context));
                        com.ss.sys.ces.a.meta(107, null, "" + az.c(context));
                        com.ss.sys.ces.a.meta(108, null, "" + az.d(context));
                        com.ss.sys.ces.a.meta(109, null, "" + az.a());
                        com.ss.sys.ces.a.meta(110, null, "" + az.b());
                        i = true;
                    } catch (Throwable unused) {
                    }
                }
            }
        }
    }

    private static void a(String str, Context context) {
        new a("CZL-" + str, context).start();
    }

    private boolean a() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) e.getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return activeNetworkInfo.isConnected();
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static b b() {
        return d;
    }

    private static void c() {
        aj.a(e, f, new C0044b());
    }

    private boolean d() {
        return this.b;
    }

    @Override // com.ss.sys.ces.out.ISdk
    public void InitSelas() {
        c();
    }

    @Override // com.ss.sys.ces.out.ISdk
    public void SetRegionType(int i2) {
        at.a(i2);
    }

    public Object a(int i2, Object obj) {
        String string;
        if (i2 == 123) {
            return bh.a(e);
        }
        if (i2 == 121) {
            return bd.c(e);
        }
        if (i2 == 122) {
            return bd.a();
        }
        if (i2 == 126) {
            return bd.d(e);
        }
        if (i2 == 127) {
            return bd.a(e);
        }
        if (i2 == 128) {
            return bd.b(e);
        }
        if (i2 == 120) {
            return bb.a();
        }
        if (i2 == 124) {
            return bj.b(e);
        }
        if (i2 == 130) {
            return bj.a(e);
        }
        if (i2 == 125) {
            return bj.c(e);
        }
        if (i2 == 129) {
            return bf.a(e);
        }
        if (i2 == 131) {
            return aj.a();
        }
        if (i2 == 132) {
            reportNow((String) obj);
            return null;
        }
        if (i2 == 134) {
            return null;
        }
        if (i2 == 133) {
            try {
                JSONObject jSONObject = new JSONObject();
                for (Map.Entry<String, Object> entry : q.entrySet()) {
                    if (entry.getValue() == null) {
                        jSONObject.put(entry.getKey(), "");
                    } else {
                        jSONObject.put(entry.getKey(), entry.getValue());
                    }
                }
                string = jSONObject.toString();
            } catch (Throwable unused) {
                string = null;
            }
            if (string == null) {
                return "{}";
            }
        } else {
            if (i2 != 135) {
                if (i2 != 136) {
                    return null;
                }
                try {
                    return e.getPackageManager().getPackageInfo(e.getPackageName(), 64).signatures[0].toByteArray();
                } catch (Throwable th) {
                    th.printStackTrace();
                    return null;
                }
            }
            string = com.ss.sys.ces.c.b();
            if (string == null) {
                return "0000000000000000000000000000000000000000";
            }
        }
        return string.trim();
    }

    @Override // com.ss.sys.ces.out.ISdk
    public String debugEntry(Context context, int i2) {
        return com.ss.sys.ces.a.DebugPrint(context, i2);
    }

    @Override // com.ss.sys.ces.out.ISdk
    public byte[] encode(byte[] bArr) {
        return com.ss.sys.ces.a.encode(bArr);
    }

    @Override // com.ss.sys.ces.out.ISdk
    public String onEvent() {
        return aj.a();
    }

    @Override // com.ss.sys.ces.out.ISdk
    public String pullSg() {
        return null;
    }

    @Override // com.ss.sys.ces.out.ISdk
    public String pullVer(String str) {
        return null;
    }

    @Override // com.ss.sys.ces.out.ISdk
    public void reportNow(String str) {
        try {
            new c(this, "CZL-5", str).start();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        this.b = true;
        a("SP1", e);
        c();
        long j2 = 60000;
        int i2 = 0;
        while (true) {
            i2++;
            bg.c(String.format("[LOOP]times : %d settings=%b reported=%b", Integer.valueOf(i2), Boolean.valueOf(l), Boolean.valueOf(k)));
            boolean zA = a();
            if (this.c) {
                bm.a(DownloadConstants.DEFAULT_NEXT_INSTALL_MIN_INTERVAL);
            }
            if (zA && p) {
                reportNow("META");
            } else {
                Log.e("CZL_META", String.format(" *** SoundWave:%b | GriLock:%b", Boolean.valueOf(zA), Boolean.valueOf(p)));
            }
            if (zA) {
                m = 0;
                int i3 = n + 1;
                n = i3;
                if (i3 > 10) {
                    j2 = 7200000;
                }
            } else {
                int i4 = m + 1;
                m = i4;
                if (i4 > 10) {
                    n = 0;
                    m = 0;
                    Log.e("CZL_META", "[-] My patient has been ended");
                    j2 = 7200000;
                }
                bm.a(m * 10 * 1000);
            }
            if (this.c) {
                bm.a(AppLinkMonitor.DELAY_TIME);
            }
            if (k) {
                n = 0;
                m = 0;
                o = 0;
                Log.d("CZL_META", "[+] just finished");
                bm.a(7200000L);
            } else {
                int i5 = o + 1;
                o = i5;
                if (i5 < 5) {
                    bg.a("report error repeat times : " + o);
                    bm.a(((long) o) * j2);
                } else {
                    bg.b("not OK but never mind");
                    bm.a(7200000L);
                    this.c = false;
                }
            }
            j2 = 7200000;
        }
    }

    @Override // com.ss.sys.ces.out.ISdk
    public void setCustomInfo(HashMap<String, Object> map) {
        if (map != null) {
            q = map;
        }
    }

    @Override // com.ss.sys.ces.out.ISdk
    public void setEfficientDebug(boolean z) {
        this.a = z;
    }

    @Override // com.ss.sys.ces.out.ISdk
    public void setInterface(NetInterface netInterface) {
    }

    @Override // com.ss.sys.ces.out.ISdk
    public void setNetwork(boolean z) {
    }

    @Override // com.ss.sys.ces.out.ISdk
    public void setParams(String str, String str2) {
        if (str != null && str.length() > 0) {
            j = str;
            com.ss.sys.ces.a.meta(103, null, str);
        }
        if (str2 != null && str2.length() > 0) {
            com.ss.sys.ces.a.meta(104, null, str2);
        }
        if (isAlive() && d()) {
            return;
        }
        try {
            start();
        } catch (Throwable unused) {
        }
    }

    @Override // com.ss.sys.ces.out.ISdk
    public void setParams(String str, String str2, boolean z) {
        if (str != null && str.length() > 0) {
            j = str;
            com.ss.sys.ces.a.meta(103, null, str);
        }
        if (str2 != null && str2.length() > 0) {
            com.ss.sys.ces.a.meta(104, null, str2);
        }
        a("SP2", e);
        c();
        if (z) {
            if (isAlive() && d()) {
                return;
            }
            try {
                start();
            } catch (Throwable unused) {
            }
        }
    }

    @Override // com.ss.sys.ces.out.ISdk
    public void setSdkExpandInstance(ISdkEx iSdkEx) {
    }

    @Override // com.ss.sys.ces.out.ISdk
    public void setSession(String str) {
        if (str == null || str.length() <= 0) {
            return;
        }
        synchronized (this) {
            if (h.length() <= 0 || (h.length() > 0 && !str.equals(h))) {
                h = str;
            }
        }
    }

    @Override // com.ss.sys.ces.out.ISdk
    public void setUrlInterface(int i2, String str) {
        if (str == null || str.length() <= 0) {
            return;
        }
        at.a(i2, str);
    }
}
