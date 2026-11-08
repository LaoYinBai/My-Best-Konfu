package com.kwai.filedownloader;

import android.content.Context;
import android.text.TextUtils;
import com.kwai.filedownloader.a;
import com.kwai.filedownloader.services.c;
import com.liulishuo.filedownloader.event.DownloadServiceConnectChangedEvent;
import java.io.File;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class r {
    private static final Object a = new Object();
    private static final Object c = new Object();
    private w b;
    private v d;

    private static final class a {
        private static final r a = new r();
    }

    public static r a() {
        return a.a;
    }

    public static void a(Context context) {
        com.kwai.filedownloader.e.c.a(context.getApplicationContext());
    }

    public static void a(Context context, c.a aVar) {
        if (com.kwai.filedownloader.e.d.a) {
            com.kwai.filedownloader.e.d.c(r.class, "init Downloader with params: %s %s", context, aVar);
        }
        if (context == null) {
            throw new IllegalArgumentException("the provided context must not be null!");
        }
        com.kwai.filedownloader.e.c.a(context.getApplicationContext());
        com.kwai.filedownloader.download.b.a().a(aVar);
    }

    public int a(int i) {
        List<a.b> listC = h.a().c(i);
        if (listC == null || listC.isEmpty()) {
            com.kwai.filedownloader.e.d.d(this, "request pause but not exist %d", Integer.valueOf(i));
            return 0;
        }
        Iterator<a.b> it = listC.iterator();
        while (it.hasNext()) {
            it.next().F().f();
        }
        return listC.size();
    }

    public com.kwai.filedownloader.a a(String str) {
        return new c(str);
    }

    public void a(e eVar) {
        f.a().a(DownloadServiceConnectChangedEvent.ID, eVar);
    }

    public boolean a(int i, String str) {
        a(i);
        if (!n.a().c(i)) {
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        File file = new File(com.kwai.filedownloader.e.f.d(str));
        if (file.exists()) {
            file.delete();
        }
        File file2 = new File(str);
        if (file2.exists()) {
            file2.delete();
        }
        return true;
    }

    public void b() {
        if (c()) {
            return;
        }
        n.a().a(com.kwai.filedownloader.e.c.a());
    }

    public boolean c() {
        return n.a().c();
    }

    w d() {
        if (this.b == null) {
            synchronized (a) {
                if (this.b == null) {
                    this.b = new ab();
                }
            }
        }
        return this.b;
    }

    v e() {
        if (this.d == null) {
            synchronized (c) {
                if (this.d == null) {
                    this.d = new z();
                    a((e) this.d);
                }
            }
        }
        return this.d;
    }
}
