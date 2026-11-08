package com.huawei.hms.update.a;

import android.os.Handler;
import android.os.Looper;
import com.huawei.hms.utils.Checker;
import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c implements com.huawei.hms.update.a.a.a {
    private static final Executor a = Executors.newSingleThreadExecutor();
    private final com.huawei.hms.update.a.a.a b;

    public c(com.huawei.hms.update.a.a.a aVar) {
        Checker.checkNonNull(aVar, "update must not be null.");
        this.b = aVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static com.huawei.hms.update.a.a.b b(final com.huawei.hms.update.a.a.b bVar) {
        return new com.huawei.hms.update.a.a.b() { // from class: com.huawei.hms.update.a.c.1
            @Override // com.huawei.hms.update.a.a.b
            public void a(final int i, final com.huawei.hms.update.a.a.c cVar) {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.huawei.hms.update.a.c.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        bVar.a(i, cVar);
                    }
                });
            }

            @Override // com.huawei.hms.update.a.a.b
            public void a(final int i, final int i2, final int i3, final File file) {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.huawei.hms.update.a.c.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        bVar.a(i, i2, i3, file);
                    }
                });
            }
        };
    }

    @Override // com.huawei.hms.update.a.a.a
    public void a() {
        this.b.a();
    }

    @Override // com.huawei.hms.update.a.a.a
    public void a(final com.huawei.hms.update.a.a.b bVar, final com.huawei.hms.update.a.a.c cVar) {
        a.execute(new Runnable() { // from class: com.huawei.hms.update.a.c.2
            @Override // java.lang.Runnable
            public void run() {
                c.this.b.a(c.b(bVar), cVar);
            }
        });
    }
}
