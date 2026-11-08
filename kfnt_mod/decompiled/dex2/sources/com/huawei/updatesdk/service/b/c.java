package com.huawei.updatesdk.service.b;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class c {
    private static c a = new c();
    private static a b = new a() { // from class: com.huawei.updatesdk.service.b.c.1
        private final List<b> a = new ArrayList();

        @Override // com.huawei.updatesdk.service.b.a
        public void a(int i, com.huawei.updatesdk.a.b.c.b bVar) {
            synchronized (this.a) {
                Iterator<b> it = this.a.iterator();
                while (it.hasNext()) {
                    it.next().a(i, bVar);
                }
            }
        }

        @Override // com.huawei.updatesdk.service.b.a
        public void a(b bVar) {
            String str;
            String str2;
            synchronized (this.a) {
                try {
                    if (bVar == null) {
                        return;
                    }
                    if (!this.a.contains(bVar)) {
                        try {
                            this.a.add(bVar);
                        } catch (ClassCastException unused) {
                            str = "InstallObserverManager";
                            str2 = "registerObserver ClassCastException";
                            com.huawei.updatesdk.a.a.b.a.a.a.d(str, str2);
                        } catch (IllegalArgumentException unused2) {
                            str = "InstallObserverManager";
                            str2 = "registerObserver IllegalArgumentException";
                            com.huawei.updatesdk.a.a.b.a.a.a.d(str, str2);
                        } catch (UnsupportedOperationException unused3) {
                            str = "InstallObserverManager";
                            str2 = "registerObserver UnsupportedOperationException";
                            com.huawei.updatesdk.a.a.b.a.a.a.d(str, str2);
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // com.huawei.updatesdk.service.b.a
        public void b(b bVar) {
            synchronized (this.a) {
                try {
                    this.a.remove(bVar);
                } catch (UnsupportedOperationException unused) {
                    com.huawei.updatesdk.a.a.b.a.a.a.d("InstallObserverManager", "unRegisterObserver UnsupportedOperationException");
                }
            }
        }
    };

    private c() {
    }

    public static c a() {
        return a;
    }

    public static a b() {
        return b;
    }

    public void a(com.huawei.updatesdk.a.b.c.b bVar) {
        b.a(0, bVar);
    }

    public void b(com.huawei.updatesdk.a.b.c.b bVar) {
        b.a(1, bVar);
    }

    public void c(com.huawei.updatesdk.a.b.c.b bVar) {
        b.a(2, bVar);
    }
}
