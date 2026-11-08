package com.huawei.updatesdk.a.b.d;

import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.updatesdk.a.a.c.g;
import com.huawei.updatesdk.a.b.d.a.c;
import com.huawei.updatesdk.a.b.d.a.d;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.Executor;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b extends AsyncTask<c, Void, d> {
    protected c a;
    private com.huawei.updatesdk.a.b.d.a.a c;
    private d b = null;
    private com.huawei.updatesdk.a.a.a.b d = null;
    private int e = 0;
    private int f = 0;

    public b(c cVar, com.huawei.updatesdk.a.b.d.a.a aVar) {
        this.a = null;
        this.c = null;
        this.a = cVar;
        this.c = aVar;
    }

    private d a(String str, String str2, d dVar) {
        try {
            dVar.fromJson(new JSONObject(str2));
            dVar.a(0);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | JSONException e) {
            com.huawei.updatesdk.a.a.b.a.a.a.a("StoreTask", "parse json error", e);
        }
        return dVar;
    }

    private void a(d dVar, int i, d.a aVar, Throwable th) {
        if (dVar != null) {
            dVar.a(i);
            dVar.a(aVar);
            dVar.a(th.toString());
        }
    }

    private void a(d dVar, Throwable th, String str) {
        int i;
        d.a aVar;
        d.a aVar2;
        if (!(th instanceof ConnectException)) {
            if ((th instanceof SocketTimeoutException) || (th instanceof ConnectTimeoutException)) {
                a(dVar, 2, d.a.CONNECT_EXCEPTION, th);
            } else if (th instanceof IOException) {
                aVar2 = d.a.IO_EXCEPTION;
            } else {
                if (th instanceof IllegalArgumentException) {
                    i = 5;
                    aVar = d.a.PARAM_ERROR;
                } else if (th instanceof IllegalAccessException) {
                    aVar2 = d.a.UNKNOWN_EXCEPTION;
                } else if (th instanceof ArrayIndexOutOfBoundsException) {
                    i = 6;
                    aVar = d.a.NO_PROGUARD;
                } else if (dVar != null) {
                    dVar.a(th.toString());
                }
                a(dVar, i, aVar, th);
            }
            a(str, th);
        }
        aVar2 = d.a.CONNECT_EXCEPTION;
        a(dVar, 1, aVar2, th);
        a(str, th);
    }

    private void a(String str, Throwable th) {
        StringBuilder sb = new StringBuilder();
        sb.append("invoke store error");
        sb.append(", exceptionType:");
        sb.append(th.toString());
        sb.append(", url:");
        sb.append(str);
        sb.append(", method:");
        sb.append(this.a.a());
        sb.append(", retryTimes:" + this.e);
        com.huawei.updatesdk.a.a.b.a.a.a.a("StoreTask", sb.toString(), th);
        Log.e("StoreTask", "UpdateSDK call store error: " + th.toString());
    }

    private boolean a(String str) {
        return TextUtils.isEmpty(str) || !g.b(str);
    }

    private void b(String str) {
        String str2;
        String str3;
        if (TextUtils.isEmpty(str)) {
            str2 = "StoreTask";
            str3 = "UpdateSDK response error, resData == null";
        } else {
            str2 = "StoreTask";
            str3 = "UpdateSDK response error, resData is not json string";
        }
        Log.e(str2, str3);
    }

    private boolean c(d dVar) {
        if (isCancelled()) {
            return false;
        }
        if (dVar.a() != 1 && dVar.a() != 2) {
            return false;
        }
        int i = this.e;
        this.e = i + 1;
        if (i >= 3) {
            com.huawei.updatesdk.a.a.b.a.a.a.a("StoreTask", "retry completed total times = " + this.e + ",response.responseCode = " + dVar.a());
            return false;
        }
        com.huawei.updatesdk.a.a.b.a.a.a.a("StoreTask", "retry times = " + this.e + ",response.responseCode = " + dVar.a());
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0052  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void d(com.huawei.updatesdk.a.b.d.a.d r5) throws java.lang.IllegalAccessException, java.lang.InstantiationException {
        /*
            r4 = this;
            boolean r0 = r4.isCancelled()
            if (r0 != 0) goto L60
            com.huawei.updatesdk.a.b.d.a.a r0 = r4.c
            if (r0 == 0) goto L60
            if (r5 != 0) goto L59
            java.lang.String r0 = "StoreTask"
            java.lang.String r1 = "notifyResult, response is null"
            com.huawei.updatesdk.a.a.b.a.a.a.d(r0, r1)
            com.huawei.updatesdk.a.b.d.a.c r0 = r4.a     // Catch: java.lang.IllegalAccessException -> L1f java.lang.InstantiationException -> L28
            java.lang.String r0 = r0.a()     // Catch: java.lang.IllegalAccessException -> L1f java.lang.InstantiationException -> L28
            com.huawei.updatesdk.a.b.d.a.d r0 = com.huawei.updatesdk.a.b.d.a.a(r0)     // Catch: java.lang.IllegalAccessException -> L1f java.lang.InstantiationException -> L28
            r5 = r0
            goto L45
        L1f:
            r0 = move-exception
            java.lang.String r1 = "StoreTask"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            goto L30
        L28:
            r0 = move-exception
            java.lang.String r1 = "StoreTask"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
        L30:
            java.lang.String r3 = "notifyResult, create response error, method:"
            r2.append(r3)
            com.huawei.updatesdk.a.b.d.a.c r3 = r4.a
            java.lang.String r3 = r3.a()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.huawei.updatesdk.a.a.b.a.a.a.a(r1, r2, r0)
        L45:
            if (r5 != 0) goto L52
            com.huawei.updatesdk.a.b.d.a.d r5 = new com.huawei.updatesdk.a.b.d.a.d
            r5.<init>()
            com.huawei.updatesdk.a.b.d.a.d$a r0 = com.huawei.updatesdk.a.b.d.a.d.a.PARAM_ERROR
        L4e:
            r5.a(r0)
            goto L55
        L52:
            com.huawei.updatesdk.a.b.d.a.d$a r0 = com.huawei.updatesdk.a.b.d.a.d.a.UNKNOWN_EXCEPTION
            goto L4e
        L55:
            r0 = 1
            r5.a(r0)
        L59:
            com.huawei.updatesdk.a.b.d.a.a r0 = r4.c
            com.huawei.updatesdk.a.b.d.a.c r1 = r4.a
            r0.b(r1, r5)
        L60:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.updatesdk.a.b.d.b.d(com.huawei.updatesdk.a.b.d.a.d):void");
    }

    private void e() throws IllegalAccessException, InstantiationException {
        d(this.b);
    }

    public final d a() {
        d dVarB = null;
        do {
            if (this.e > 0 && dVarB != null) {
                com.huawei.updatesdk.a.a.b.a.a.a.a("StoreTask", "call store error! responseCode:" + dVarB.a() + ", retryTimes:" + this.e);
            }
            dVarB = b();
            a(dVarB);
        } while (c(dVarB));
        this.b = dVarB;
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public d doInBackground(c... cVarArr) {
        com.huawei.updatesdk.a.a.b.a.a.a.a("StoreTask", "doInBackground, method:" + this.a.a());
        com.huawei.updatesdk.service.d.a.c.a(this);
        d dVarA = a();
        if (this.c != null) {
            this.c.a(this.a, dVarA);
        }
        return dVarA;
    }

    public void a(d dVar) {
    }

    public final void a(Executor executor) {
        executeOnExecutor(executor, this.a);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected com.huawei.updatesdk.a.b.d.a.d b() {
        /*
            Method dump skipped, instructions count: 282
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.updatesdk.a.b.d.b.b():com.huawei.updatesdk.a.b.d.a.d");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(d dVar) throws IllegalAccessException, InstantiationException {
        com.huawei.updatesdk.a.a.b.a.a.a.a("StoreTask", "onPostExecute, method:" + this.a.a());
        com.huawei.updatesdk.service.d.a.c.a().remove(this);
        e();
    }

    protected String c() throws PackageManager.NameNotFoundException {
        String strA = com.huawei.updatesdk.service.d.a.a.a();
        return TextUtils.isEmpty(strA) ? "Android/1.0" : strA;
    }

    public void d() {
        if (this.d != null) {
            this.d.a();
            this.d = null;
        }
    }

    @Override // android.os.AsyncTask
    protected void onCancelled() {
        super.onCancelled();
        d();
    }
}
