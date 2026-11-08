package com.huawei.hms.framework.network.grs.b;

import android.content.Context;
import com.huawei.hms.framework.common.ExecutorsUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.hms.framework.network.grs.c.d;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class h {
    private ExecutorService a = ExecutorsUtils.newCachedThreadPool("GRS_RequestController-Task");
    private Map<String, com.huawei.hms.framework.network.grs.b.b.b> b = new ConcurrentHashMap(16);
    private final Object c = new Object();
    private com.huawei.hms.framework.network.grs.a.a d;

    /* JADX INFO: Access modifiers changed from: private */
    public void a(d dVar, com.huawei.hms.framework.network.grs.b bVar) {
        if (bVar != null) {
            if (dVar == null) {
                Logger.v("RequestController", "GrsResponse is null");
                bVar.a();
            } else {
                Logger.v("RequestController", "GrsResponse is not null");
                bVar.a(dVar);
            }
        }
    }

    public d a(final GrsBaseInfo grsBaseInfo, final Context context, final String str) {
        Future<d> futureSubmit;
        String str2;
        String str3;
        Logger.d("RequestController", "request to server with service name is: " + str);
        String grsParasKey = grsBaseInfo.getGrsParasKey(false, true, context);
        synchronized (this.c) {
            d.a aVarA = com.huawei.hms.framework.network.grs.c.d.a(grsParasKey);
            com.huawei.hms.framework.network.grs.b.b.b bVar = this.b.get(grsParasKey);
            if (bVar == null || !bVar.b()) {
                if (aVarA != null && aVarA.a()) {
                    return null;
                }
                Logger.d("RequestController", "hitGrsRequestBean == null or request block is released.");
                futureSubmit = this.a.submit(new Callable<d>() { // from class: com.huawei.hms.framework.network.grs.b.h.1
                    @Override // java.util.concurrent.Callable
                    /* renamed from: a, reason: merged with bridge method [inline-methods] */
                    public d call() {
                        return new c(grsBaseInfo, context, h.this.d).a(h.this.a, str);
                    }
                });
                this.b.put(grsParasKey, new com.huawei.hms.framework.network.grs.b.b.b(futureSubmit));
            } else {
                futureSubmit = bVar.a();
            }
            try {
                return futureSubmit.get();
            } catch (InterruptedException e) {
                e = e;
                str2 = "RequestController";
                str3 = "when check result, find InterruptedException, check others";
                Logger.w(str2, str3, e);
                return null;
            } catch (CancellationException e2) {
                e = e2;
                str2 = "RequestController";
                str3 = "when check result, find CancellationException, check others";
                Logger.w(str2, str3, e);
                return null;
            } catch (ExecutionException e3) {
                e = e3;
                str2 = "RequestController";
                str3 = "when check result, find ExecutionException, check others";
                Logger.w(str2, str3, e);
                return null;
            }
        }
    }

    public void a(final GrsBaseInfo grsBaseInfo, final Context context, final com.huawei.hms.framework.network.grs.b bVar, final String str) {
        this.a.submit(new Runnable() { // from class: com.huawei.hms.framework.network.grs.b.h.2
            @Override // java.lang.Runnable
            public void run() {
                h.this.a(h.this.a(grsBaseInfo, context, str), bVar);
            }
        });
    }

    public void a(com.huawei.hms.framework.network.grs.a.a aVar) {
        this.d = aVar;
    }

    public void a(String str) {
        synchronized (this.c) {
            this.b.remove(str);
        }
    }
}
