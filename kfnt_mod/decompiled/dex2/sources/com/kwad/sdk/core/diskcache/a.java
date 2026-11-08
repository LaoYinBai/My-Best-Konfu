package com.kwad.sdk.core.diskcache;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import androidx.annotation.NonNull;
import com.ksad.download.f;
import com.kwad.sdk.core.i.b;
import com.kwad.sdk.utils.aq;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static volatile a g;
    private Future a;
    private File b;
    private PackageManager d;
    private final f e;
    private volatile boolean f;
    private final ExecutorService c = b.k();
    private final Callable<PackageInfo> h = new Callable<PackageInfo>() { // from class: com.kwad.sdk.core.diskcache.a.1
        @Override // java.util.concurrent.Callable
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PackageInfo call() {
            PackageInfo packageInfoA;
            synchronized (a.class) {
                try {
                    try {
                    } catch (Throwable th) {
                        com.kwad.sdk.core.d.a.a(th);
                    }
                    if (a.this.b != null && a.this.b.exists()) {
                        for (File file : a.this.b(a.this.b)) {
                            if (file.getName().endsWith(".apk") && com.kwad.sdk.core.a.a().a(file.getAbsolutePath()) != null && (packageInfoA = a.this.a(file)) != null) {
                                a.this.e.a(file);
                                return packageInfoA;
                            }
                        }
                        return null;
                    }
                    return null;
                } finally {
                }
            }
        }
    };

    private a(@NonNull Context context) {
        this.f = false;
        this.e = new com.kwad.sdk.core.download.b.a(context);
        try {
            this.b = aq.c(context);
            this.d = context.getPackageManager();
        } catch (Throwable th) {
            com.kwad.sdk.core.d.a.a(th);
        }
        this.f = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PackageInfo a(File file) {
        if (file != null && file.exists()) {
            try {
                if (file.exists() & (!file.isDirectory())) {
                    PackageInfo packageArchiveInfo = this.d.getPackageArchiveInfo(file.getPath(), 65);
                    if (this.d.getPackageInfo(packageArchiveInfo.packageName, 1) != null) {
                        return null;
                    }
                    return packageArchiveInfo;
                }
            } catch (Exception e) {
                com.kwad.sdk.core.d.a.a(e);
            }
        }
        return null;
    }

    public static a a(@NonNull Context context) {
        if (g == null) {
            synchronized (a.class) {
                if (g == null) {
                    g = new a(context);
                }
            }
        }
        return g;
    }

    private void a(List<File> list) {
        Collections.sort(list, new Comparator<File>() { // from class: com.kwad.sdk.core.diskcache.a.2
            @Override // java.util.Comparator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(File file, File file2) {
                if (file.lastModified() >= file2.lastModified()) {
                    return file.lastModified() == file2.lastModified() ? 0 : 1;
                }
                return -1;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<File> b(@NonNull File file) {
        ArrayList arrayList = new ArrayList();
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            return arrayList;
        }
        arrayList.addAll(Arrays.asList(fileArrListFiles));
        a(arrayList);
        return arrayList;
    }

    public void a() {
        if (this.f && this.b != null && this.b.exists()) {
            if (this.a == null || this.a.isDone()) {
                this.a = this.c.submit(this.h);
            }
        }
    }
}
