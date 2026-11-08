package com.xiaomi.push;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.ss.android.downloadlib.applink.AppLinkMonitor;
import com.ss.android.socialbase.downloader.constants.DownloadConstants;
import com.taobao.accs.common.Constants;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class gu {

    /* renamed from: a, reason: collision with other field name */
    private static al f441a = new al(true);
    private static volatile int a = -1;

    /* renamed from: a, reason: collision with other field name */
    private static long f440a = System.currentTimeMillis();

    /* renamed from: a, reason: collision with other field name */
    private static final Object f443a = new Object();

    /* renamed from: a, reason: collision with other field name */
    private static List<a> f445a = Collections.synchronizedList(new ArrayList());

    /* renamed from: a, reason: collision with other field name */
    private static String f444a = "";

    /* renamed from: a, reason: collision with other field name */
    private static com.xiaomi.push.providers.a f442a = null;

    static class a {
        public int a;

        /* renamed from: a, reason: collision with other field name */
        public long f446a;

        /* renamed from: a, reason: collision with other field name */
        public String f447a;
        public int b;

        /* renamed from: b, reason: collision with other field name */
        public long f448b;

        /* renamed from: b, reason: collision with other field name */
        public String f449b;

        public a(String str, long j, int i, int i2, String str2, long j2) {
            this.f447a = "";
            this.f446a = 0L;
            this.a = -1;
            this.b = -1;
            this.f449b = "";
            this.f448b = 0L;
            this.f447a = str;
            this.f446a = j;
            this.a = i;
            this.b = i2;
            this.f449b = str2;
            this.f448b = j2;
        }

        public boolean a(a aVar) {
            return TextUtils.equals(aVar.f447a, this.f447a) && TextUtils.equals(aVar.f449b, this.f449b) && aVar.a == this.a && aVar.b == this.b && Math.abs(aVar.f446a - this.f446a) <= AppLinkMonitor.DELAY_TIME;
        }
    }

    public static int a(Context context) {
        if (a == -1) {
            a = b(context);
        }
        return a;
    }

    public static int a(String str) {
        try {
            return str.getBytes("UTF-8").length;
        } catch (UnsupportedEncodingException unused) {
            return str.getBytes().length;
        }
    }

    private static long a(int i, long j, boolean z, long j2, boolean z2) {
        if (z && z2) {
            long j3 = f440a;
            f440a = j2;
            if (j2 - j3 > com.umeng.commonsdk.proguard.b.d && j > DownloadConstants.KB) {
                return j * 2;
            }
        }
        return (j * (i == 0 ? 13 : 11)) / 10;
    }

    /* renamed from: a, reason: collision with other method in class */
    private static com.xiaomi.push.providers.a m367a(Context context) {
        if (f442a != null) {
            return f442a;
        }
        f442a = new com.xiaomi.push.providers.a(context);
        return f442a;
    }

    /* renamed from: a, reason: collision with other method in class */
    private static synchronized String m368a(Context context) {
        if (TextUtils.isEmpty(f444a)) {
            return "";
        }
        return f444a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public static void m370a(Context context) {
        a = b(context);
    }

    private static void a(Context context, String str, long j, boolean z, long j2) {
        int iA;
        boolean zIsEmpty;
        if (context == null || TextUtils.isEmpty(str) || !"com.xiaomi.xmsf".equals(context.getPackageName()) || "com.xiaomi.xmsf".equals(str) || -1 == (iA = a(context))) {
            return;
        }
        synchronized (f443a) {
            zIsEmpty = f445a.isEmpty();
            a(new a(str, j2, iA, z ? 1 : 0, iA == 0 ? m368a(context) : "", j));
        }
        if (zIsEmpty) {
            f441a.a(new gv(context), AppLinkMonitor.DELAY_TIME);
        }
    }

    public static void a(Context context, String str, long j, boolean z, boolean z2, long j2) {
        a(context, str, a(a(context), j, z, j2, z2), z, j2);
    }

    private static void a(a aVar) {
        for (a aVar2 : f445a) {
            if (aVar2.a(aVar)) {
                aVar2.f448b += aVar.f448b;
                return;
            }
        }
        f445a.add(aVar);
    }

    /* renamed from: a, reason: collision with other method in class */
    public static synchronized void m371a(String str) {
        if (!l.d() && !TextUtils.isEmpty(str)) {
            f444a = str;
        }
    }

    private static int b(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return -1;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return -1;
            }
            return activeNetworkInfo.getType();
        } catch (Exception unused) {
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context, List<a> list) {
        try {
            synchronized (com.xiaomi.push.providers.a.f826a) {
                SQLiteDatabase writableDatabase = m367a(context).getWritableDatabase();
                writableDatabase.beginTransaction();
                try {
                    for (a aVar : list) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("package_name", aVar.f447a);
                        contentValues.put("message_ts", Long.valueOf(aVar.f446a));
                        contentValues.put("network_type", Integer.valueOf(aVar.a));
                        contentValues.put("bytes", Long.valueOf(aVar.f448b));
                        contentValues.put("rcv", Integer.valueOf(aVar.b));
                        contentValues.put(Constants.KEY_IMSI, aVar.f449b);
                        writableDatabase.insert("traffic", null, contentValues);
                    }
                    writableDatabase.setTransactionSuccessful();
                } finally {
                    writableDatabase.endTransaction();
                }
            }
        } catch (SQLiteException e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
        }
    }
}
