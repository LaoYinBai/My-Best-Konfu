package com.ksad.download;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Api;
import com.ksad.download.DownloadTask;
import com.ksad.download.h;
import com.kwai.filedownloader.r;
import com.kwai.filedownloader.services.c;
import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d {
    private c c;
    private final Map<Integer, DownloadTask> a = new ConcurrentHashMap();
    private final Map<String, Integer> b = new ConcurrentHashMap();
    private boolean d = false;

    private static final class a {
        private static final d a = new d();
    }

    public static d a() {
        return a.a;
    }

    private void a(int i, DownloadTask.DownloadRequest downloadRequest) {
        DownloadTask downloadTask = this.a.get(Integer.valueOf(i));
        if (downloadTask != null) {
            downloadTask.resume(downloadRequest);
        }
    }

    public static void a(@NonNull Context context, @NonNull File file, @Nullable f fVar) {
        h.a aVar;
        b.a(context);
        b.a(file);
        e.a().a(fVar);
        c.a aVarA = new c.a().a(Api.BaseClientBuilder.API_PRIORITY_OTHER);
        try {
            aVar = new h.a(false);
            try {
                aVar.a("");
            } catch (Throwable unused) {
            }
        } catch (Throwable unused2) {
            aVar = null;
        }
        if (aVar != null) {
            aVarA.a(aVar);
        }
        r.a(context, aVarA);
    }

    public int a(@NonNull DownloadTask.DownloadRequest downloadRequest, c cVar) {
        int id;
        c[] cVarArr;
        DownloadTask downloadTask = new DownloadTask(downloadRequest);
        if (downloadRequest.getDownloadUrl().contains("downali.game.uc.cn")) {
            b();
        } else if (this.d) {
            c();
        }
        if (this.a.get(Integer.valueOf(downloadTask.getId())) != null) {
            a(downloadTask.getId(), downloadRequest);
            b(downloadTask.getId());
            id = downloadTask.getId();
            cVarArr = new c[]{cVar, this.c};
        } else {
            this.a.put(Integer.valueOf(downloadTask.getId()), downloadTask);
            this.b.put(downloadTask.getUrl(), Integer.valueOf(downloadTask.getId()));
            downloadTask.submit();
            id = downloadTask.getId();
            cVarArr = new c[]{cVar, this.c};
        }
        a(id, cVarArr);
        return downloadTask.getId();
    }

    public DownloadTask a(int i) {
        return this.a.get(Integer.valueOf(i));
    }

    public void a(int i, c... cVarArr) {
        DownloadTask downloadTask = this.a.get(Integer.valueOf(i));
        if (downloadTask == null || cVarArr == null) {
            return;
        }
        for (c cVar : cVarArr) {
            if (cVar != null) {
                cVar.a(i);
                downloadTask.addListener(cVar);
            }
        }
    }

    void a(@NonNull DownloadTask downloadTask) {
        this.a.remove(Integer.valueOf(downloadTask.getId()));
        this.b.remove(downloadTask.getUrl());
    }

    public void a(c cVar) {
        this.c = cVar;
    }

    public void b() {
        h.a aVar;
        try {
            aVar = new h.a(true);
        } catch (Throwable th) {
            th.printStackTrace();
            aVar = null;
        }
        if (aVar != null) {
            com.kwai.filedownloader.download.b.a().b(new c.a().a(Api.BaseClientBuilder.API_PRIORITY_OTHER).a(aVar));
            this.d = true;
        }
    }

    public void b(int i) {
        DownloadTask downloadTask = this.a.get(Integer.valueOf(i));
        if (downloadTask != null) {
            downloadTask.clearListener();
        }
    }

    public void c() {
        h.a aVar;
        try {
            aVar = new h.a(false);
        } catch (Throwable th) {
            th.printStackTrace();
            aVar = null;
        }
        if (aVar != null) {
            com.kwai.filedownloader.download.b.a().b(new c.a().a(Api.BaseClientBuilder.API_PRIORITY_OTHER).a(aVar));
        }
    }

    public void c(int i) {
        DownloadTask downloadTask = this.a.get(Integer.valueOf(i));
        if (downloadTask != null) {
            downloadTask.cancel();
            a(downloadTask);
        }
    }

    public void d(int i) {
        DownloadTask downloadTask = this.a.get(Integer.valueOf(i));
        if (downloadTask != null) {
            downloadTask.userPause();
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:9:0x0024. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0012  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean d() {
        /*
            r6 = this;
            java.util.Map<java.lang.Integer, com.ksad.download.DownloadTask> r0 = r6.a
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
            r1 = 0
        Lb:
            r2 = 0
        Lc:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L3e
            java.lang.Object r3 = r0.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r3 = r3.getValue()
            com.ksad.download.DownloadTask r3 = (com.ksad.download.DownloadTask) r3
            if (r3 == 0) goto Lc
            int r2 = r3.getStatus()
            switch(r2) {
                case -2: goto Lb;
                case -1: goto L27;
                case 0: goto L27;
                case 1: goto Lb;
                case 2: goto Lb;
                case 3: goto Lb;
                case 4: goto L27;
                case 5: goto Lb;
                case 6: goto Lb;
                case 7: goto L27;
                case 8: goto L27;
                case 9: goto L27;
                case 10: goto Lb;
                case 11: goto Lb;
                default: goto L27;
            }
        L27:
            long r2 = r3.getStatusUpdateTime()
            long r4 = java.lang.System.currentTimeMillis()
            long r2 = r2 - r4
            long r2 = java.lang.Math.abs(r2)
            r4 = 120000(0x1d4c0, double:5.9288E-319)
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 > 0) goto L3c
            goto Lb
        L3c:
            r2 = 1
            goto Lc
        L3e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ksad.download.d.d():boolean");
    }

    public void e(int i) {
        a(i, (DownloadTask.DownloadRequest) null);
    }

    public void f(int i) {
        DownloadTask downloadTaskA = a(i);
        if (downloadTaskA == null) {
            return;
        }
        if (downloadTaskA.isUserPause()) {
            e(i);
        } else {
            d(i);
        }
    }
}
