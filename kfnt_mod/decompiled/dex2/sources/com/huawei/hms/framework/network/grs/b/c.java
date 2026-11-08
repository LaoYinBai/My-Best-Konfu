package com.huawei.hms.framework.network.grs.b;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.hms.framework.network.grs.c.d;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONArray;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c implements a {
    private static final String a = "c";
    private GrsBaseInfo b;
    private Context c;
    private com.huawei.hms.framework.network.grs.a.a d;
    private d e;
    private com.huawei.hms.framework.network.grs.b.b.c k;
    private ArrayList<Future<d>> f = new ArrayList<>();
    private ArrayList<d> g = new ArrayList<>();
    private JSONArray h = new JSONArray();
    private ArrayList<String> i = new ArrayList<>();
    private ArrayList<String> j = new ArrayList<>();
    private long l = 1;

    public c(GrsBaseInfo grsBaseInfo, Context context, com.huawei.hms.framework.network.grs.a.a aVar) {
        this.b = grsBaseInfo;
        this.c = context;
        this.d = aVar;
        b();
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0083 A[LOOP:0: B:3:0x0004->B:34:0x0083, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x007b A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.huawei.hms.framework.network.grs.b.d a(java.util.concurrent.ExecutorService r12, java.util.ArrayList<java.lang.String> r13, java.lang.String r14) throws java.util.concurrent.ExecutionException, java.lang.InterruptedException, java.util.concurrent.TimeoutException {
        /*
            r11 = this;
            r0 = 0
            r1 = 0
            r8 = r1
            r1 = 0
        L4:
            int r2 = r13.size()
            if (r1 >= r2) goto L87
            java.lang.Object r2 = r13.get(r1)
            r3 = r2
            java.lang.String r3 = (java.lang.String) r3
            boolean r2 = android.text.TextUtils.isEmpty(r3)
            r9 = 1
            if (r2 != 0) goto L78
            com.huawei.hms.framework.network.grs.b.b r10 = new com.huawei.hms.framework.network.grs.b.b
            android.content.Context r6 = r11.c
            r2 = r10
            r4 = r1
            r5 = r11
            r7 = r14
            r2.<init>(r3, r4, r5, r6, r7)
            java.util.concurrent.Callable r2 = r10.f()
            java.util.concurrent.Future r2 = r12.submit(r2)
            java.util.ArrayList<java.util.concurrent.Future<com.huawei.hms.framework.network.grs.b.d>> r3 = r11.f
            r3.add(r2)
            long r3 = r11.l     // Catch: java.util.concurrent.TimeoutException -> L50 java.lang.InterruptedException -> L59 java.util.concurrent.ExecutionException -> L63 java.util.concurrent.CancellationException -> L6e
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.SECONDS     // Catch: java.util.concurrent.TimeoutException -> L50 java.lang.InterruptedException -> L59 java.util.concurrent.ExecutionException -> L63 java.util.concurrent.CancellationException -> L6e
            java.lang.Object r2 = r2.get(r3, r5)     // Catch: java.util.concurrent.TimeoutException -> L50 java.lang.InterruptedException -> L59 java.util.concurrent.ExecutionException -> L63 java.util.concurrent.CancellationException -> L6e
            com.huawei.hms.framework.network.grs.b.d r2 = (com.huawei.hms.framework.network.grs.b.d) r2     // Catch: java.util.concurrent.TimeoutException -> L50 java.lang.InterruptedException -> L59 java.util.concurrent.ExecutionException -> L63 java.util.concurrent.CancellationException -> L6e
            if (r2 == 0) goto L4e
            boolean r3 = r2.h()     // Catch: java.lang.InterruptedException -> L4a java.util.concurrent.ExecutionException -> L4c java.util.concurrent.TimeoutException -> L51 java.util.concurrent.CancellationException -> L6f
            if (r3 == 0) goto L4e
            java.lang.String r3 = com.huawei.hms.framework.network.grs.b.c.a     // Catch: java.lang.InterruptedException -> L4a java.util.concurrent.ExecutionException -> L4c java.util.concurrent.TimeoutException -> L51 java.util.concurrent.CancellationException -> L6f
            java.lang.String r4 = "grs request return body is not null and is OK."
            com.huawei.hms.framework.common.Logger.i(r3, r4)     // Catch: java.lang.InterruptedException -> L4a java.util.concurrent.ExecutionException -> L4c java.util.concurrent.TimeoutException -> L51 java.util.concurrent.CancellationException -> L6f
            goto L76
        L4a:
            r3 = move-exception
            goto L5b
        L4c:
            r3 = move-exception
            goto L65
        L4e:
            r9 = 0
            goto L76
        L50:
            r2 = r8
        L51:
            java.lang.String r3 = com.huawei.hms.framework.network.grs.b.c.a
            java.lang.String r4 = "the wait timed out"
            com.huawei.hms.framework.common.Logger.w(r3, r4)
            goto L6c
        L59:
            r3 = move-exception
            r2 = r8
        L5b:
            java.lang.String r4 = com.huawei.hms.framework.network.grs.b.c.a
            java.lang.String r5 = "the current thread was interrupted while waiting"
            com.huawei.hms.framework.common.Logger.w(r4, r5, r3)
            goto L76
        L63:
            r3 = move-exception
            r2 = r8
        L65:
            java.lang.String r4 = com.huawei.hms.framework.network.grs.b.c.a
            java.lang.String r5 = "the computation threw an ExecutionException"
            com.huawei.hms.framework.common.Logger.w(r4, r5, r3)
        L6c:
            r8 = r2
            goto L78
        L6e:
            r2 = r8
        L6f:
            java.lang.String r3 = com.huawei.hms.framework.network.grs.b.c.a
            java.lang.String r4 = "{requestServer} the computation was cancelled"
            com.huawei.hms.framework.common.Logger.i(r3, r4)
        L76:
            r8 = r2
            goto L79
        L78:
            r9 = 0
        L79:
            if (r9 == 0) goto L83
            java.lang.String r12 = com.huawei.hms.framework.network.grs.b.c.a
            java.lang.String r13 = "needBreak is true so need break current circulation"
            com.huawei.hms.framework.common.Logger.v(r12, r13)
            goto L87
        L83:
            int r1 = r1 + 1
            goto L4
        L87:
            com.huawei.hms.framework.network.grs.b.d r12 = r11.b(r8)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.framework.network.grs.b.c.a(java.util.concurrent.ExecutorService, java.util.ArrayList, java.lang.String):com.huawei.hms.framework.network.grs.b.d");
    }

    private d b(d dVar) throws ExecutionException, InterruptedException, TimeoutException {
        String str;
        String str2;
        int size = this.f.size();
        for (int i = 0; i < size && (dVar == null || !dVar.h()); i++) {
            try {
                dVar = this.f.get(i).get(40000L, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e = e;
                str = a;
                str2 = "{checkResponse} when check result, find InterruptedException, check others";
                Logger.w(str, str2, e);
            } catch (CancellationException unused) {
                Logger.i(a, "{checkResponse} when check result, find CancellationException, check others");
            } catch (ExecutionException e2) {
                e = e2;
                str = a;
                str2 = "{checkResponse} when check result, find ExecutionException, check others";
                Logger.w(str, str2, e);
            } catch (TimeoutException unused2) {
                Logger.w(a, "{checkResponse} when check result, find TimeoutException, cancel current request task");
                if (!this.f.get(i).isCancelled()) {
                    this.f.get(i).cancel(true);
                }
            }
        }
        return dVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public d b(ExecutorService executorService, String str) throws ExecutionException, InterruptedException, TimeoutException {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        d dVarA = a(executorService, this.j, str);
        int iL = dVarA == null ? 0 : dVarA.l();
        Logger.v(a, "use 2.0 interface return http's code is：{%s}", Integer.valueOf(iL));
        if (iL == 404 || iL == 401) {
            if (TextUtils.isEmpty(c()) && TextUtils.isEmpty(this.b.getAppName())) {
                Logger.i(a, "request grs server use 1.0 API must set appName,please check.");
                return null;
            }
            this.f.clear();
            Logger.i(a, "this env has not deploy new interface,so use old interface.");
            dVarA = a(executorService, this.i, str);
        }
        e.a(this.g, SystemClock.elapsedRealtime() - jElapsedRealtime, this.h, this.c);
        return dVarA;
    }

    private void b() {
        com.huawei.hms.framework.network.grs.b.b.c cVarA = com.huawei.hms.framework.network.grs.b.a.a.a(this.c);
        if (cVarA == null) {
            Logger.w(a, "g*s***_se****er_conf*** maybe has a big error");
            return;
        }
        a(cVarA);
        List<String> listC = cVarA.c();
        if (listC == null || listC.size() <= 0) {
            Logger.v(a, "maybe grs_base_url config with [],please check.");
            return;
        }
        if (listC.size() > 10) {
            throw new IllegalArgumentException("grs_base_url's count is larger than MAX value 10");
        }
        String strA = cVarA.a();
        String strB = cVarA.b();
        if (listC.size() > 0) {
            for (String str : listC) {
                if (str.startsWith("https://")) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    Locale locale = Locale.ROOT;
                    Object[] objArr = new Object[1];
                    objArr[0] = TextUtils.isEmpty(c()) ? this.b.getAppName() : c();
                    sb.append(String.format(locale, strA, objArr));
                    String grsReqParamJoint = this.b.getGrsReqParamJoint(false, false, "1.0", this.c);
                    if (!TextUtils.isEmpty(grsReqParamJoint)) {
                        sb.append("?");
                        sb.append(grsReqParamJoint);
                    }
                    this.i.add(sb.toString());
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str);
                    sb2.append(strB);
                    String grsReqParamJoint2 = this.b.getGrsReqParamJoint(false, false, c(), this.c);
                    if (!TextUtils.isEmpty(grsReqParamJoint2)) {
                        sb2.append("?");
                        sb2.append(grsReqParamJoint2);
                    }
                    this.j.add(sb2.toString());
                } else {
                    Logger.w(a, "grs server just support https scheme url,please check.");
                }
            }
        }
        Logger.v(a, "request to GRS server url is{%s} and {%s}", this.i, this.j);
    }

    private String c() {
        com.huawei.hms.framework.network.grs.local.model.a aVarA = com.huawei.hms.framework.network.grs.local.b.a(this.c.getPackageName()).a();
        if (aVarA == null) {
            return "";
        }
        String strA = aVarA.a();
        Logger.v(a, "get appName from local assets is{%s}", strA);
        return strA;
    }

    public com.huawei.hms.framework.network.grs.b.b.c a() {
        return this.k;
    }

    public d a(final ExecutorService executorService, final String str) {
        String str2;
        String str3;
        if (this.i == null || this.j == null) {
            return null;
        }
        try {
            return (d) executorService.submit(new Callable<d>() { // from class: com.huawei.hms.framework.network.grs.b.c.1
                @Override // java.util.concurrent.Callable
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public d call() {
                    return c.this.b(executorService, str);
                }
            }).get(a() != null ? r0.d() : 10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e = e;
            str2 = a;
            str3 = "{submitExcutorTaskWithTimeout} the current thread was interrupted while waiting";
            Logger.w(str2, str3, e);
            return null;
        } catch (CancellationException unused) {
            Logger.i(a, "{submitExcutorTaskWithTimeout} the computation was cancelled");
            return null;
        } catch (ExecutionException e2) {
            e = e2;
            str2 = a;
            str3 = "{submitExcutorTaskWithTimeout} the computation threw an ExecutionException";
            Logger.w(str2, str3, e);
            return null;
        } catch (TimeoutException unused2) {
            Logger.w(a, "{submitExcutorTaskWithTimeout} the wait timed out");
            return null;
        } catch (Exception e3) {
            e = e3;
            str2 = a;
            str3 = "{submitExcutorTaskWithTimeout} catch Exception";
            Logger.w(str2, str3, e);
            return null;
        }
    }

    public void a(com.huawei.hms.framework.network.grs.b.b.c cVar) {
        this.k = cVar;
    }

    @Override // com.huawei.hms.framework.network.grs.b.a
    public synchronized void a(d dVar) {
        this.g.add(dVar);
        if (this.e != null && this.e.h()) {
            Logger.v(a, "grsResponseResult is ok");
            return;
        }
        if (dVar.i()) {
            Logger.i(a, "GRS server open 503 limiting strategy.");
            com.huawei.hms.framework.network.grs.c.d.a(this.b.getGrsParasKey(false, true, this.c), new d.a(dVar.e(), SystemClock.elapsedRealtime()));
            return;
        }
        if (!dVar.h()) {
            Logger.v(a, "grsResponseResult has exception so need return");
            return;
        }
        this.e = dVar;
        this.d.a(this.b, this.e, this.c);
        for (int i = 0; i < this.f.size(); i++) {
            if (!this.i.get(i).equals(dVar.k()) && !this.j.get(i).equals(dVar.k()) && !this.f.get(i).isCancelled()) {
                Logger.i(a, "future cancel");
                this.f.get(i).cancel(true);
            }
        }
    }
}
