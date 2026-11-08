package com.huawei.updatesdk.service.d.a;

import android.os.AsyncTask;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {
    private static List<AsyncTask> a = new ArrayList();

    public static com.huawei.updatesdk.a.b.d.b a(com.huawei.updatesdk.service.a.c cVar, com.huawei.updatesdk.a.b.d.a.a aVar) {
        com.huawei.updatesdk.a.b.d.b bVar = new com.huawei.updatesdk.a.b.d.b(cVar, aVar);
        a(bVar, cVar);
        return bVar;
    }

    public static List<AsyncTask> a() {
        return a;
    }

    public static void a(AsyncTask asyncTask) {
        for (int size = a.size() - 1; size >= 0; size--) {
            AsyncTask asyncTask2 = a.get(size);
            if (asyncTask2.getStatus() == AsyncTask.Status.FINISHED || asyncTask2.isCancelled()) {
                a.remove(asyncTask);
            }
        }
        a.add(asyncTask);
    }

    private static void a(com.huawei.updatesdk.a.b.d.b bVar, com.huawei.updatesdk.service.a.c cVar) {
        com.huawei.updatesdk.a.a.b.a.a.a.a("StoreAgent", "executeTask, ActiveCount:" + d.a.getActiveCount() + ", TaskCount:" + d.a.getTaskCount());
        bVar.a(d.a);
    }
}
