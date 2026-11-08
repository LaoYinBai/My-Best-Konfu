package com.kwad.sdk.core.diskcache;

import androidx.annotation.NonNull;
import com.kwad.sdk.KsAdSDKImpl;
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
public class ApkCacheManager {
    private Future a;
    private File b;
    private final ExecutorService c;
    private final Callable<Void> d;

    private enum Holder {
        INSTANCE;

        private ApkCacheManager mInstance = new ApkCacheManager();

        Holder() {
        }

        ApkCacheManager getInstance() {
            return this.mInstance;
        }
    }

    private ApkCacheManager() {
        this.c = b.j();
        this.d = new Callable<Void>() { // from class: com.kwad.sdk.core.diskcache.ApkCacheManager.1
            @Override // java.util.concurrent.Callable
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Void call() {
                synchronized (ApkCacheManager.class) {
                    try {
                        try {
                        } catch (Throwable th) {
                            com.kwad.sdk.core.d.a.a(th);
                        }
                        if (ApkCacheManager.this.b != null && ApkCacheManager.this.b.exists() && !ApkCacheManager.this.c()) {
                            for (File file : ApkCacheManager.this.d(ApkCacheManager.this.b)) {
                                if (file.getName().endsWith(".apk")) {
                                    ApkCacheManager.this.c(file);
                                    if (ApkCacheManager.this.c()) {
                                        return null;
                                    }
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
        if (KsAdSDKImpl.get().getContext() == null) {
            return;
        }
        try {
            this.b = aq.c(KsAdSDKImpl.get().getContext());
        } catch (Throwable th) {
            com.kwad.sdk.core.d.a.a(th);
        }
    }

    private int a(File file) {
        return (int) ((b(file) / 1000.0f) / 1000.0f);
    }

    public static ApkCacheManager a() {
        return Holder.INSTANCE.getInstance();
    }

    private void a(List<File> list) {
        Collections.sort(list, new Comparator<File>() { // from class: com.kwad.sdk.core.diskcache.ApkCacheManager.2
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

    private long b(File file) {
        File[] fileArrListFiles = file.listFiles();
        long jB = 0;
        if (fileArrListFiles != null) {
            int length = fileArrListFiles.length;
            for (int i = 0; i < length; i++) {
                jB += fileArrListFiles[i].isDirectory() ? b(fileArrListFiles[i]) : fileArrListFiles[i].length();
            }
        }
        return jB;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(File file) {
        if (file == null || !file.exists()) {
            return;
        }
        try {
            if (file.isDirectory()) {
                for (File file2 : file.listFiles()) {
                    c(file2);
                }
            } else if (!file.exists()) {
                return;
            }
            file.delete();
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.a(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c() {
        if (this.b == null || !this.b.exists()) {
            return false;
        }
        File[] fileArrListFiles = this.b.listFiles();
        return fileArrListFiles.length <= 5 || (fileArrListFiles.length <= 10 && a(this.b) <= 400);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<File> d(@NonNull File file) {
        ArrayList arrayList = new ArrayList();
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            return arrayList;
        }
        arrayList.addAll(Arrays.asList(fileArrListFiles));
        a(arrayList);
        return arrayList;
    }

    public void b() {
        if (this.b == null || !this.b.exists()) {
            return;
        }
        if (this.a == null || this.a.isDone()) {
            this.a = this.c.submit(this.d);
        }
    }
}
