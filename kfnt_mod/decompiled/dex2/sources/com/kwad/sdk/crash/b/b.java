package com.kwad.sdk.crash.b;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.crash.e;
import com.kwad.sdk.crash.utils.g;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class b {
    public static final String a = UUID.randomUUID().toString();
    public static File b;
    protected e c;
    protected AtomicInteger d = new AtomicInteger();
    protected File e;
    protected File f;
    protected File g;
    protected File h;
    protected File i;
    protected com.kwad.sdk.crash.report.c j;

    public static void a(File file) {
        b = file;
        if (b.exists()) {
            return;
        }
        b.mkdirs();
    }

    public final com.kwad.sdk.crash.report.c a() {
        return this.j;
    }

    public void a(File file, e eVar, com.kwad.sdk.crash.report.c cVar) {
        this.e = file;
        if (!this.e.exists()) {
            this.e.mkdirs();
        }
        this.f = new File(this.e, a + "-" + this.d + ".dump");
        this.g = new File(this.e, a + "-" + this.d + ".log");
        this.h = new File(this.e, a + "-" + this.d + ".jtrace");
        this.c = eVar;
        this.j = cVar;
    }

    protected abstract void a(@NonNull File[] fileArr, @Nullable CountDownLatch countDownLatch);

    void b() throws InterruptedException {
        File[] fileArrListFiles = this.e.listFiles(new FileFilter() { // from class: com.kwad.sdk.crash.b.b.1
            @Override // java.io.FileFilter
            public boolean accept(File file) {
                return file.getName().endsWith(".dump");
            }
        });
        if (fileArrListFiles == null || fileArrListFiles.length <= 2) {
            return;
        }
        CountDownLatch countDownLatch = new CountDownLatch(fileArrListFiles.length);
        a(fileArrListFiles, countDownLatch);
        try {
            countDownLatch.await(1L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            com.kwad.sdk.core.d.a.b(e);
        }
    }

    void b(File file) throws Throwable {
        if (b == null) {
            return;
        }
        if (!b.exists()) {
            b.mkdirs();
        }
        try {
            g.a(file.getParentFile().getParentFile(), b);
        } catch (IOException e) {
            com.kwad.sdk.core.d.a.b(e);
        }
    }

    protected abstract int c();
}
