package com.alipay.sdk.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import com.alipay.android.app.IAlixPay;
import com.alipay.android.app.IRemoteServiceCallback;
import com.alipay.sdk.app.AlipayResultActivity;
import com.alipay.sdk.data.a;
import com.alipay.sdk.sys.a;
import com.alipay.sdk.util.k;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e {

    /* renamed from: a, reason: collision with root package name */
    public static final String f2649a = "failed";

    /* renamed from: b, reason: collision with root package name */
    public static final String f2650b = "scheme_failed";

    /* renamed from: c, reason: collision with root package name */
    private Activity f2651c;
    private volatile IAlixPay d;
    private boolean f;
    private c g;
    private final com.alipay.sdk.sys.a h;
    private final Object e = IAlixPay.class;
    private String i = null;

    public interface c {
        void a();

        void b();
    }

    public e(Activity activity, com.alipay.sdk.sys.a aVar, c cVar) {
        this.f2651c = activity;
        this.h = aVar;
        this.g = cVar;
    }

    public String a(String str) {
        k.a aVarA;
        String strA;
        String str2 = "";
        try {
            List<a.C0078a> listO = com.alipay.sdk.data.a.p().o();
            if (!com.alipay.sdk.data.a.p().f2585a || listO == null) {
                listO = com.alipay.sdk.app.a.f2557a;
            }
            aVarA = k.a(this.h, this.f2651c, listO);
        } catch (Throwable th) {
            com.alipay.sdk.app.statistic.a.a(this.h, com.alipay.sdk.app.statistic.b.f2572b, com.alipay.sdk.app.statistic.b.H, th);
        }
        if (aVarA == null || aVarA.a(this.h) || aVarA.a() || k.a(aVarA.f2671a)) {
            return f2649a;
        }
        if (aVarA.f2671a == null || "com.eg.android.AlipayGphone".equals(aVarA.f2671a.packageName)) {
            strA = k.a();
        } else {
            strA = aVarA.f2671a.packageName;
        }
        str2 = strA;
        packageInfo = aVarA.f2671a != null ? aVarA.f2671a : null;
        if (!com.alipay.sdk.data.a.p().m()) {
            a(aVarA);
        }
        return a(str, str2, packageInfo);
    }

    private void a(k.a aVar) throws InterruptedException {
        PackageInfo packageInfo;
        if (aVar == null || (packageInfo = aVar.f2671a) == null) {
            return;
        }
        String str = packageInfo.packageName;
        Intent intent = new Intent();
        intent.setClassName(str, "com.alipay.android.app.TransProcessPayActivity");
        try {
            this.f2651c.startActivity(intent);
        } catch (Throwable th) {
            com.alipay.sdk.app.statistic.a.a(this.h, com.alipay.sdk.app.statistic.b.f2572b, com.alipay.sdk.app.statistic.b.X, th);
        }
        Thread.sleep(200L);
    }

    private String a(String str, String str2, PackageInfo packageInfo) throws Throwable {
        String str3;
        int i = packageInfo != null ? packageInfo.versionCode : 0;
        String str4 = packageInfo != null ? packageInfo.versionName : "";
        com.alipay.sdk.util.c.b(com.alipay.sdk.cons.a.x, "pay bind or scheme");
        com.alipay.sdk.app.statistic.a.b(this.h, com.alipay.sdk.app.statistic.b.f2572b, com.alipay.sdk.app.statistic.b.R, str2 + "|" + str4);
        if (k.e()) {
            com.alipay.sdk.app.statistic.a.a(this.h, com.alipay.sdk.app.statistic.b.f2572b, "BindSkipByModel");
            str3 = f2649a;
        } else {
            Pair<String, Boolean> pairA = a(str, str2, this.h);
            String str5 = (String) pairA.first;
            try {
            } catch (Throwable th) {
                com.alipay.sdk.app.statistic.a.a(this.h, com.alipay.sdk.app.statistic.b.f2572b, "BindRetryEx", th);
            }
            if (f2649a.equals(str5) && ((Boolean) pairA.second).booleanValue() && com.alipay.sdk.data.a.p().l()) {
                com.alipay.sdk.app.statistic.a.a(this.h, com.alipay.sdk.app.statistic.b.f2572b, "BindRetry");
                str3 = (String) a(str, str2, this.h).first;
            } else {
                str3 = str5;
            }
        }
        com.alipay.sdk.util.c.b(com.alipay.sdk.cons.a.x, "pay bind result: " + str3);
        com.alipay.sdk.app.statistic.a.a(this.f2651c, this.h, str, this.h.q);
        if (!f2649a.equals(str3)) {
            return str3;
        }
        if (!com.alipay.sdk.data.a.p().c()) {
            com.alipay.sdk.app.statistic.a.b(this.h, com.alipay.sdk.app.statistic.b.f2572b, "BSPNotStartByConfig", "");
            return str3;
        }
        if (!"com.eg.android.AlipayGphone".equals(str2) || i <= 125) {
            com.alipay.sdk.app.statistic.a.b(this.h, com.alipay.sdk.app.statistic.b.f2572b, "BSPNotStartByPkg", str2 + "|" + i);
            return str3;
        }
        if (!com.alipay.sdk.data.a.p().i() || this.h == null || k.b(this.h.s) == 0) {
            return (this.f2651c == null || !a(str2, this.f2651c, this.h)) ? f2650b : a(str, str2);
        }
        com.alipay.sdk.app.statistic.a.a(this.h, com.alipay.sdk.app.statistic.b.f2572b, "BSPNotStartByUsr");
        return str3;
    }

    /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
    private String a(String str, String str2) {
        String str3;
        String strSubstring;
        String strSubstring2;
        String str4;
        JSONObject jSONObject;
        String strReplace = str;
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        String strA = k.a(32);
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        com.alipay.sdk.app.statistic.a.b(this.h, com.alipay.sdk.app.statistic.b.f2572b, "BSPStart", strA + "|" + jElapsedRealtime);
        a.C0080a.a(this.h, strA);
        AlipayResultActivity.f2525a.put(strA, new AlipayResultActivity.a() { // from class: com.alipay.sdk.util.e.1
            @Override // com.alipay.sdk.app.AlipayResultActivity.a
            public void a(int i, String str5, String str6) {
                e.this.i = com.alipay.sdk.app.b.a(i, str5, str6);
                countDownLatch.countDown();
            }
        });
        try {
            try {
                try {
                    String[] strArrSplit = strReplace.split("&", -1);
                    strSubstring = "";
                    strSubstring2 = "";
                    int length = strArrSplit.length;
                    int i = 0;
                    while (true) {
                        str4 = null;
                        if (i >= length) {
                            jSONObject = null;
                            break;
                        }
                        str4 = strArrSplit[i];
                        if (str4.startsWith(com.alipay.sdk.sys.a.d)) {
                            String strSubstring3 = str4.substring(str4.indexOf("{"), str4.lastIndexOf(f.d) + 1);
                            int iIndexOf = str4.indexOf(strSubstring3);
                            strSubstring = str4.substring(0, iIndexOf);
                            strSubstring2 = str4.substring(iIndexOf + strSubstring3.length());
                            jSONObject = new JSONObject(strSubstring3);
                            if (jSONObject.optString("sc").equals("h5tonative")) {
                                jSONObject.put("sc", "h5tonative_scheme");
                            } else {
                                jSONObject.put("sc", "h5tonative_sdkscheme");
                            }
                        } else {
                            i++;
                        }
                    }
                } catch (InterruptedException e) {
                    com.alipay.sdk.app.statistic.a.a(this.h, com.alipay.sdk.app.statistic.b.f2572b, "BSPWaiting", e);
                    return com.alipay.sdk.app.b.a(com.alipay.sdk.app.c.PAY_WAITTING.a(), com.alipay.sdk.app.c.PAY_WAITTING.b(), "");
                }
            } catch (Exception e2) {
                com.alipay.sdk.app.statistic.a.a(this.h, com.alipay.sdk.app.statistic.b.f2572b, "BSPSCReplaceEx", e2, Base64.encodeToString(str.getBytes(), 2));
            }
            if (TextUtils.isEmpty(str4)) {
                throw new RuntimeException("empty ctx_args");
            }
            if (strReplace.indexOf(str4) != strReplace.lastIndexOf(str4)) {
                throw new RuntimeException("multi ctx_args");
            }
            strReplace = strReplace.replace(str4, strSubstring + jSONObject.toString() + strSubstring2);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("sourcePid", Binder.getCallingPid());
            jSONObject2.put(com.alipay.sdk.cons.b.d, strReplace);
            jSONObject2.put("pkgName", this.f2651c.getPackageName());
            jSONObject2.put("session", strA);
            String strEncodeToString = Base64.encodeToString(jSONObject2.toString().getBytes("UTF-8"), 2);
            Uri.Builder builderAppendQueryParameter = new Uri.Builder().scheme("alipays").authority("platformapi").path("startapp").appendQueryParameter("appId", "20000125");
            builderAppendQueryParameter.appendQueryParameter("mqpSchemePay", strEncodeToString);
            try {
                HashMap<String, String> mapA = com.alipay.sdk.sys.a.a(this.h);
                mapA.put("ts_scheme", String.valueOf(jElapsedRealtime));
                builderAppendQueryParameter.appendQueryParameter("mqpLoc", new JSONObject(mapA).toString());
            } catch (Throwable th) {
                com.alipay.sdk.app.statistic.a.a(this.h, com.alipay.sdk.app.statistic.b.f2572b, "BSPLocEx", th);
            }
            String string = builderAppendQueryParameter.build().toString();
            Intent intent = new Intent();
            intent.setPackage(str2);
            intent.addFlags(268435456);
            intent.setData(Uri.parse(string));
            com.alipay.sdk.app.statistic.a.a(this.f2651c, this.h, strReplace, this.h.q);
            this.f2651c.startActivity(intent);
            com.alipay.sdk.data.a.p().a(this.h, this.f2651c.getApplicationContext());
            com.alipay.sdk.util.c.b(com.alipay.sdk.cons.a.x, "pay scheme waiting " + string);
            countDownLatch.await();
            String str5 = this.i;
            try {
                String str6 = i.a(this.h, str5).get(i.f2663a);
                str3 = str6 == null ? "null" : str6;
            } catch (Throwable th2) {
                com.alipay.sdk.app.statistic.a.a(this.h, com.alipay.sdk.app.statistic.b.f2572b, "BSPStatEx", th2);
                str3 = "unknown";
            }
            com.alipay.sdk.app.statistic.a.a(this.h, com.alipay.sdk.app.statistic.b.f2572b, "BSPDone-" + str3);
            if (!TextUtils.isEmpty(str5)) {
                return str5;
            }
            com.alipay.sdk.app.statistic.a.a(this.h, com.alipay.sdk.app.statistic.b.f2572b, "BSPEmpty");
            return f2650b;
        } catch (Throwable th3) {
            com.alipay.sdk.app.statistic.a.a(this.h, com.alipay.sdk.app.statistic.b.f2572b, "BSPEx", th3);
            return f2650b;
        }
    }

    private static boolean a(String str, Context context, com.alipay.sdk.sys.a aVar) {
        try {
            Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
            intent.setClassName(str, "com.alipay.android.msp.ui.views.MspContainerActivity");
            if (intent.resolveActivityInfo(context.getPackageManager(), 0) != null) {
                return true;
            }
            com.alipay.sdk.app.statistic.a.a(aVar, com.alipay.sdk.app.statistic.b.f2572b, "BSPDetectFail");
            return false;
        } catch (Throwable th) {
            com.alipay.sdk.app.statistic.a.a(aVar, com.alipay.sdk.app.statistic.b.f2572b, "BSPDetectFail", th);
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:132:0x02e0  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0332  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x029f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:189:0x02f1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:199:? A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r12v10 */
    /* JADX WARN: Type inference failed for: r12v11 */
    /* JADX WARN: Type inference failed for: r12v13 */
    /* JADX WARN: Type inference failed for: r12v14 */
    /* JADX WARN: Type inference failed for: r12v16 */
    /* JADX WARN: Type inference failed for: r12v23 */
    /* JADX WARN: Type inference failed for: r12v6 */
    /* JADX WARN: Type inference failed for: r12v7, types: [com.alipay.android.app.IRemoteServiceCallback] */
    /* JADX WARN: Type inference failed for: r12v8 */
    /* JADX WARN: Type inference failed for: r3v12, types: [com.alipay.android.app.IRemoteServiceCallback, com.alipay.sdk.util.e$a] */
    /* JADX WARN: Type inference failed for: r5v2, types: [com.alipay.android.app.IAlixPay] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.util.Pair<java.lang.String, java.lang.Boolean> a(java.lang.String r18, java.lang.String r19, com.alipay.sdk.sys.a r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 863
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.util.e.a(java.lang.String, java.lang.String, com.alipay.sdk.sys.a):android.util.Pair");
    }

    public void a() {
        this.f2651c = null;
        this.g = null;
    }

    private class b implements ServiceConnection {
        private b() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            com.alipay.sdk.app.statistic.a.a(e.this.h, com.alipay.sdk.app.statistic.b.f2572b, "srvDis");
            e.this.d = null;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            com.alipay.sdk.app.statistic.a.a(e.this.h, com.alipay.sdk.app.statistic.b.f2572b, "srvCon");
            synchronized (e.this.e) {
                e.this.d = IAlixPay.Stub.asInterface(iBinder);
                e.this.e.notify();
            }
        }
    }

    private class a extends IRemoteServiceCallback.Stub {
        @Override // com.alipay.android.app.IRemoteServiceCallback
        public int getVersion() throws RemoteException {
            return 3;
        }

        @Override // com.alipay.android.app.IRemoteServiceCallback
        public boolean isHideLoadingScreen() throws RemoteException {
            return false;
        }

        @Override // com.alipay.android.app.IRemoteServiceCallback
        public void payEnd(boolean z, String str) throws RemoteException {
        }

        private a() {
        }

        @Override // com.alipay.android.app.IRemoteServiceCallback
        public void r03(String str, String str2, Map map) throws RemoteException {
            com.alipay.sdk.app.statistic.a.b(e.this.h, com.alipay.sdk.app.statistic.b.g, str, str2);
        }

        @Override // com.alipay.android.app.IRemoteServiceCallback
        public void startActivity(String str, String str2, int i, Bundle bundle) throws RemoteException {
            Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
            if (bundle == null) {
                bundle = new Bundle();
            }
            try {
                bundle.putInt("CallingPid", i);
                intent.putExtras(bundle);
            } catch (Exception e) {
                com.alipay.sdk.app.statistic.a.a(e.this.h, com.alipay.sdk.app.statistic.b.f2572b, com.alipay.sdk.app.statistic.b.T, e);
            }
            intent.setClassName(str, str2);
            try {
                if (Build.VERSION.SDK_INT >= 16) {
                    ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
                    ActivityManager.getMyMemoryState(runningAppProcessInfo);
                    com.alipay.sdk.app.statistic.a.b(e.this.h, com.alipay.sdk.app.statistic.b.f2572b, "isFg", runningAppProcessInfo.processName + "|" + runningAppProcessInfo.importance + "|");
                }
            } catch (Throwable unused) {
            }
            try {
                if (e.this.f2651c == null) {
                    com.alipay.sdk.app.statistic.a.a(e.this.h, com.alipay.sdk.app.statistic.b.f2572b, "ErrActNull", "");
                    Context contextD = e.this.h.d();
                    if (contextD != null) {
                        contextD.startActivity(intent);
                    }
                } else {
                    long jElapsedRealtime = SystemClock.elapsedRealtime();
                    e.this.f2651c.startActivity(intent);
                    com.alipay.sdk.app.statistic.a.b(e.this.h, com.alipay.sdk.app.statistic.b.f2572b, "stAct2", "" + (SystemClock.elapsedRealtime() - jElapsedRealtime));
                }
                e.this.g.b();
            } catch (Throwable th) {
                com.alipay.sdk.app.statistic.a.a(e.this.h, com.alipay.sdk.app.statistic.b.f2572b, "ErrActNull", th);
                throw th;
            }
        }
    }
}
