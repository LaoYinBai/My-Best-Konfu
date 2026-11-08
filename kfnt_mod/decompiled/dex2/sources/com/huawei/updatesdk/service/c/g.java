package com.huawei.updatesdk.service.c;

import android.content.ContextWrapper;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.updatesdk.service.c.c;
import com.huawei.updatesdk.service.c.d;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class g {
    protected static final d a = new d();
    private static final ExecutorService b = Executors.newFixedThreadPool(1);

    public static final class a extends AsyncTask<Void, Void, Boolean> {
        private final String a;
        private final int b;
        private boolean c;
        private b d;

        private a(String str, int i, boolean z) {
            this.c = false;
            this.a = str;
            this.b = i;
            this.c = z;
        }

        private void a() {
            if (TextUtils.isEmpty(this.d.d())) {
                return;
            }
            File file = new File(this.d.d());
            if (!file.exists() || file.delete()) {
                return;
            }
            com.huawei.updatesdk.a.a.b.a.a.a.d("PackageService", "file delete error.");
        }

        public static void a(String str, int i) {
            new a(str, i, false).execute(new Void[0]);
        }

        public static void a(String str, int i, boolean z) {
            new a(str, i, z).execute(new Void[0]);
        }

        private void a(String str, File file) {
            String str2 = str + File.separator + file.getName();
            File file2 = new File(str);
            if (!file2.exists() && !file2.mkdirs()) {
                com.huawei.updatesdk.a.a.b.a.a.a.d("PackageService", "getbackFile mkdirs failed!");
            }
            File file3 = new File(str2);
            if (!f.a(file, file3)) {
                if (b()) {
                    this.c = false;
                    return;
                } else {
                    g.a(5, 0);
                    return;
                }
            }
            File parentFile = file3.getParentFile();
            if (parentFile != null) {
                parentFile.setExecutable(true, false);
            }
            file3.setReadable(true, false);
            this.d.b(str2);
            String str3 = "install|pkg:" + this.d.c() + "|path:" + this.d.d();
            Thread thread = new Thread(new e(com.huawei.updatesdk.a.b.a.a.a().b(), this.d));
            thread.setName(str3);
            thread.start();
        }

        private boolean a(String str) {
            String str2;
            String str3;
            this.d = g.b(str);
            if (this.d == null) {
                return false;
            }
            if (1 == this.b) {
                if (!TextUtils.isEmpty(this.d.d())) {
                    g.a.remove(c.b.INSTALL);
                    return com.huawei.updatesdk.service.e.c.a(this.d.d());
                }
            } else {
                if (this.c) {
                    this.d.b(this.d.f() + 1);
                    if (this.d.f() >= h.a().length) {
                        str2 = "PackageService";
                        str3 = "getRetryBackupPaths large,can not change file path to retry install";
                    } else {
                        String str4 = h.a()[this.d.f()];
                        File file = new File(this.d.d());
                        if (file.exists()) {
                            a(str4, file);
                        } else {
                            str2 = "PackageService";
                            str3 = "file do not exist,can not change file path to retry install";
                        }
                    }
                    com.huawei.updatesdk.a.a.b.a.a.a.d(str2, str3);
                    this.c = false;
                    return false;
                }
                a();
            }
            return false;
        }

        private boolean b() {
            return Build.VERSION.SDK_INT < 23 || -1 != new ContextWrapper(com.huawei.updatesdk.a.b.a.a.a().b()).checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Boolean doInBackground(Void... voidArr) {
            boolean zA = a(this.a);
            if (this.d != null && c.b.INSTALL == this.d.e() && 1 != this.b && !this.c && this.d.f() > -1) {
                a();
            }
            return Boolean.valueOf(zA);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Boolean bool) {
            super.onPostExecute(bool);
        }
    }

    private static b a(String str, d.a aVar) {
        b bVarA = a.a(str, aVar);
        if (bVarA != null) {
            return bVarA;
        }
        return null;
    }

    public static void a(int i, int i2) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt("INSTALL_STATE", i);
        bundle.putInt("INSTALL_TYPE", i2);
        intent.putExtras(bundle);
        com.huawei.updatesdk.service.b.c.a().c(com.huawei.updatesdk.a.b.c.b.a(intent));
    }

    public static void a(String str, String str2) {
        b(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static b b(String str) {
        return a(str, d.a.INSTALL_TYPE);
    }

    private static synchronized void b(String str, String str2) {
        com.huawei.updatesdk.a.a.b.a.a.a.a("PackageService", "process:processType=install,path=" + str + ",packageName:" + str2 + ",isNow=false");
        if (TextUtils.isEmpty(str)) {
            com.huawei.updatesdk.a.a.b.a.a.a.d("PackageService", "install failed!!!path is empty!!!!");
            return;
        }
        b bVar = new b(str2, str);
        bVar.a(c.a.WAIT_INSTALL);
        bVar.a(false);
        a.put(str2, bVar);
        bVar.a(c.b.INSTALL);
        String str3 = "install|pkg:" + bVar.c() + "|path:" + bVar.d();
        Thread thread = new Thread(new e(com.huawei.updatesdk.a.b.a.a.a().b(), bVar));
        thread.setName(str3);
        thread.start();
    }
}
