package com.umeng.commonsdk.stateless;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.common.ULog;
import java.io.File;
import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class d {
    public static final int a = 273;
    private static Context b = null;
    private static HandlerThread c = null;
    private static Handler d = null;
    private static final int f = 512;
    private static IntentFilter g;
    private static Object e = new Object();
    private static boolean h = false;
    private static BroadcastReceiver i = new BroadcastReceiver() { // from class: com.umeng.commonsdk.stateless.d.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager;
            if (context == null || intent == null) {
                return;
            }
            try {
                if (intent.getAction() != null && intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                    Context unused = d.b = context.getApplicationContext();
                    if (d.b != null && (connectivityManager = (ConnectivityManager) d.b.getSystemService("connectivity")) != null) {
                        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                            boolean unused2 = d.h = true;
                            ULog.i("walle", "[stateless] net reveiver ok --->>>");
                            d.b(d.a);
                        } else {
                            ULog.i("walle", "[stateless] net reveiver disconnected --->>>");
                            boolean unused3 = d.h = false;
                        }
                    }
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(context, th);
            }
        }
    };

    /* JADX WARN: Removed duplicated region for block: B:24:0x007d A[Catch: all -> 0x0077, DONT_GENERATE, TryCatch #0 {, blocks: (B:5:0x0008, B:7:0x0012, B:9:0x0016, B:11:0x0028, B:12:0x0035, B:14:0x003f, B:16:0x0050, B:18:0x0062, B:24:0x007d, B:23:0x007a), top: B:28:0x0006, inners: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public d(android.content.Context r7) {
        /*
            r6 = this;
            r6.<init>()
            java.lang.Object r0 = com.umeng.commonsdk.stateless.d.e
            monitor-enter(r0)
            if (r7 == 0) goto L7d
            android.content.Context r1 = r7.getApplicationContext()     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            com.umeng.commonsdk.stateless.d.b = r1     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            android.content.Context r1 = com.umeng.commonsdk.stateless.d.b     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            if (r1 == 0) goto L7d
            android.os.HandlerThread r1 = com.umeng.commonsdk.stateless.d.c     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            if (r1 != 0) goto L7d
            android.os.HandlerThread r1 = new android.os.HandlerThread     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            java.lang.String r2 = "SL-NetWorkSender"
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            com.umeng.commonsdk.stateless.d.c = r1     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            android.os.HandlerThread r1 = com.umeng.commonsdk.stateless.d.c     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            r1.start()     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            android.os.Handler r1 = com.umeng.commonsdk.stateless.d.d     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            if (r1 != 0) goto L35
            com.umeng.commonsdk.stateless.d$2 r1 = new com.umeng.commonsdk.stateless.d$2     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            android.os.HandlerThread r2 = com.umeng.commonsdk.stateless.d.c     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            android.os.Looper r2 = r2.getLooper()     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            com.umeng.commonsdk.stateless.d.d = r1     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
        L35:
            android.content.Context r1 = com.umeng.commonsdk.stateless.d.b     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            java.lang.String r2 = "android.permission.ACCESS_NETWORK_STATE"
            boolean r1 = com.umeng.commonsdk.statistics.common.DeviceConfig.checkPermission(r1, r2)     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            if (r1 == 0) goto L7d
            java.lang.String r1 = "walle"
            r2 = 1
            java.lang.Object[] r3 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            java.lang.String r4 = "[stateless] begin register receiver"
            r5 = 0
            r3[r5] = r4     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            com.umeng.commonsdk.statistics.common.ULog.i(r1, r3)     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            android.content.IntentFilter r1 = com.umeng.commonsdk.stateless.d.g     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            if (r1 != 0) goto L7d
            android.content.IntentFilter r1 = new android.content.IntentFilter     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            r1.<init>()     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            com.umeng.commonsdk.stateless.d.g = r1     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            android.content.IntentFilter r1 = com.umeng.commonsdk.stateless.d.g     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            java.lang.String r3 = "android.net.conn.CONNECTIVITY_CHANGE"
            r1.addAction(r3)     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            android.content.BroadcastReceiver r1 = com.umeng.commonsdk.stateless.d.i     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            if (r1 == 0) goto L7d
            java.lang.String r1 = "walle"
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            java.lang.String r3 = "[stateless] register receiver ok"
            r2[r5] = r3     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            com.umeng.commonsdk.statistics.common.ULog.i(r1, r2)     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            android.content.Context r1 = com.umeng.commonsdk.stateless.d.b     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            android.content.BroadcastReceiver r2 = com.umeng.commonsdk.stateless.d.i     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            android.content.IntentFilter r3 = com.umeng.commonsdk.stateless.d.g     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            r1.registerReceiver(r2, r3)     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            goto L7d
        L77:
            r7 = move-exception
            goto L7f
        L79:
            r1 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r7, r1)     // Catch: java.lang.Throwable -> L77
        L7d:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L77
            return
        L7f:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L77
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.stateless.d.<init>(android.content.Context):void");
    }

    public static void a(int i2) {
        if (!h || d == null) {
            return;
        }
        Message messageObtainMessage = d.obtainMessage();
        messageObtainMessage.what = i2;
        d.sendMessage(messageObtainMessage);
    }

    public static void b(int i2) {
        try {
            if (!h || d == null || d.hasMessages(i2)) {
                return;
            }
            ULog.i("walle", "[stateless] sendMsgOnce !!!!");
            Message messageObtainMessage = d.obtainMessage();
            messageObtainMessage.what = i2;
            d.sendMessage(messageObtainMessage);
        } catch (Throwable th) {
            UMCrashManager.reportCrash(b, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e() throws IOException {
        if (!h || b == null) {
            return;
        }
        try {
            File fileA = f.a(b);
            if (fileA == null || fileA.getParentFile() == null || TextUtils.isEmpty(fileA.getParentFile().getName())) {
                return;
            }
            e eVar = new e(b);
            String str = new String(Base64.decode(fileA.getParentFile().getName(), 0));
            ULog.i("walle", "[stateless] handleProcessNext, pathUrl is " + str);
            byte[] bArrA = null;
            try {
                bArrA = f.a(fileA.getAbsolutePath());
            } catch (Exception unused) {
            }
            if (eVar.a(bArrA, str)) {
                ULog.i("walle", "[stateless] Send envelope file success, delete it.");
                File file = new File(fileA.getAbsolutePath());
                if (!file.delete()) {
                    ULog.i("walle", "[stateless] Failed to delete already processed file. We try again after delete failed.");
                    file.delete();
                }
                b(a);
                return;
            }
            ULog.i("walle", "[stateless] Send envelope file failed, abandon and wait next trigger!");
        } catch (Throwable th) {
            UMCrashManager.reportCrash(b, th);
        }
    }

    public static void a() {
        b(512);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void f() {
        if (g != null) {
            if (i != null) {
                if (b != null) {
                    b.unregisterReceiver(i);
                }
                i = null;
            }
            g = null;
        }
        if (c != null) {
            c.quit();
            if (c != null) {
                c = null;
            }
            if (d != null) {
                d = null;
            }
        }
    }
}
