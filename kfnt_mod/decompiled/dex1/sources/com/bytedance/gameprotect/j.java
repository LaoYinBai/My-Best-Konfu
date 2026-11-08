package com.bytedance.gameprotect;

import android.content.Context;
import c.a.a.a.a.aq;
import com.appsflyer.share.Constants;
import com.bun.miitmdid.core.Utils;
import com.bytedance.sdk.account.platform.api.IWeiboService;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class j {

    /* renamed from: a, reason: collision with root package name */
    private Context f3876a;

    /* renamed from: b, reason: collision with root package name */
    private String f3877b;

    /* renamed from: c, reason: collision with root package name */
    private String f3878c;
    private String d;
    private String e;
    private String f;
    private String g;
    private Map<String, String> h;

    private static class b {

        /* renamed from: a, reason: collision with root package name */
        private static final j f3879a = new j();
    }

    private j() {
        this.f3876a = null;
        this.f3877b = "";
        this.f3878c = "ExtractLib";
        this.d = "";
        this.e = "";
        this.f = "HfCfg.dat";
        this.g = "HfClientGuard.dat";
        this.h = new HashMap();
        this.d = System.mapLibraryName("gp");
        this.h.put("arm64", "arm64-v8a");
        this.h.put("arm", "armeabi-v7a");
        this.h.put(Utils.CPU_ABI_X86, Utils.CPU_ABI_X86);
    }

    private ZipEntry a(ZipFile zipFile, String str) {
        try {
            return zipFile.getEntry(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    private void a() throws IOException {
        String strB = b();
        if (strB.isEmpty()) {
            return;
        }
        String str = String.format("%s/%s", strB, this.f3878c);
        if (a(str)) {
            String strD = d(str);
            if (strD.isEmpty()) {
                return;
            }
            c(strD);
        }
    }

    private void a(Closeable closeable) throws IOException {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException unused) {
        }
    }

    private static void a(ZipFile zipFile) throws IOException {
        if (zipFile == null) {
            return;
        }
        try {
            zipFile.close();
        } catch (IOException unused) {
        }
    }

    private boolean a(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                return true;
            }
            file.mkdirs();
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    private boolean a(String str, String str2) {
        try {
            File file = new File(str);
            File file2 = new File(str2);
            return !file.exists() ? file2.exists() : file.renameTo(file2);
        } catch (Throwable unused) {
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String b() {
        /*
            r4 = this;
            android.content.Context r0 = r4.f3876a
            if (r0 != 0) goto L7
            java.lang.String r0 = ""
            return r0
        L7:
            java.lang.String r0 = r4.f3877b
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L12
            java.lang.String r0 = r4.f3877b
            return r0
        L12:
            android.content.Context r0 = r4.f3876a
            java.io.File r0 = r0.getFilesDir()
            android.content.Context r1 = r4.f3876a
            android.content.pm.ApplicationInfo r1 = r1.getApplicationInfo()
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.String r1 = r1.packageName
            r3 = 0
            r2[r3] = r1
            java.lang.String r1 = "/data/data/%s/files"
            java.lang.String r1 = java.lang.String.format(r1, r2)
            if (r0 != 0) goto L2f
            goto L3d
        L2f:
            android.content.Context r0 = r4.f3876a
            java.io.File r0 = r0.getFilesDir()
            java.lang.String r0 = r0.getAbsolutePath()
            r4.f3877b = r0
            if (r0 != 0) goto L3f
        L3d:
            r4.f3877b = r1
        L3f:
            java.lang.String r0 = r4.f3877b
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.gameprotect.j.b():java.lang.String");
    }

    private String b(String str) throws IOException {
        Closeable closeable = null;
        try {
            File file = new File(str);
            if (!file.exists()) {
                a((Closeable) null);
                return "";
            }
            if (file.length() != 40) {
                a((Closeable) null);
                return "";
            }
            FileInputStream fileInputStream = new FileInputStream(str);
            try {
                byte[] bArr = new byte[40];
                if (fileInputStream.read(bArr, 0, 40) != 40) {
                    a(fileInputStream);
                    return "";
                }
                String str2 = new String(bArr);
                a(fileInputStream);
                return str2;
            } catch (Throwable unused) {
                closeable = fileInputStream;
                a(closeable);
                return "";
            }
        } catch (Throwable unused2) {
        }
    }

    private ZipEntry b(ZipFile zipFile) {
        String strD = d();
        if (this.h.containsKey(strD)) {
            return a(zipFile, String.format("lib/%s/%s", this.h.get(strD), this.d));
        }
        for (String str : aq.a().split(IWeiboService.Scope.EMPTY_SCOPE)) {
            ZipEntry zipEntryA = a(zipFile, String.format("lib/%s/%s", str, this.d));
            if (zipEntryA != null) {
                return zipEntryA;
            }
        }
        return null;
    }

    private String c() throws IOException {
        String strB = b();
        if (strB.isEmpty()) {
            return "";
        }
        String strD = d();
        if (strD.isEmpty()) {
            return "";
        }
        String str = String.format("%s/HfFiles/%s", strB, strD);
        String strB2 = b(String.format("%s/%s", str, this.f));
        if (strB2.isEmpty()) {
            return "";
        }
        String str2 = String.format("%s/%s", str, this.d);
        return !a(String.format("%s/HfCache_%s", str, strB2), str2) ? "" : str2;
    }

    private boolean c(String str) {
        try {
            System.load(str);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    private String d() {
        if (!this.e.isEmpty()) {
            return this.e;
        }
        String[] strArrSplit = this.f3876a.getApplicationInfo().nativeLibraryDir.split(Constants.URL_PATH_DELIMITER);
        if (strArrSplit.length == 0) {
            return this.e;
        }
        String str = strArrSplit[strArrSplit.length - 1];
        this.e = str;
        return str;
    }

    private String d(String str) throws IOException {
        InputStream inputStream;
        ZipFile zipFile;
        ZipEntry zipEntryB;
        String str2 = "";
        Closeable closeable = null;
        try {
            zipFile = new ZipFile(new File(this.f3876a.getApplicationInfo().sourceDir), 1);
            try {
                zipEntryB = b(zipFile);
            } catch (Throwable unused) {
                inputStream = null;
            }
        } catch (Throwable unused2) {
            inputStream = null;
            zipFile = null;
        }
        if (zipEntryB == null) {
            a((Closeable) null);
            a(closeable);
            a(zipFile);
            return str2;
        }
        File file = new File(str, this.d);
        file.createNewFile();
        inputStream = zipFile.getInputStream(zipEntryB);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                byte[] bArr = new byte[16384];
                while (true) {
                    int i = inputStream.read(bArr);
                    if (i <= 0) {
                        String absolutePath = file.getAbsolutePath();
                        try {
                            file.setExecutable(true);
                            file.setReadable(true);
                            a(inputStream);
                            a(fileOutputStream);
                            a(zipFile);
                            return absolutePath;
                        } catch (Throwable unused3) {
                            str2 = absolutePath;
                            closeable = fileOutputStream;
                            a(inputStream);
                            a(closeable);
                            a(zipFile);
                            return str2;
                        }
                    }
                    fileOutputStream.write(bArr, 0, i);
                }
            } catch (Throwable unused4) {
            }
        } catch (Throwable unused5) {
        }
    }

    public static j e() {
        return b.f3879a;
    }

    private void f() throws IOException {
        try {
            System.loadLibrary("gp");
        } catch (UnsatisfiedLinkError unused) {
            a();
        }
    }

    private boolean h() {
        try {
            File file = new File(String.format("%s/HfFiles/%s/%s", b(), d(), this.g));
            if (file.exists()) {
                return false;
            }
            return file.createNewFile();
        } catch (Throwable unused) {
            return false;
        }
    }

    public void a(Context context) {
        this.f3876a = context;
    }

    public void g() throws IOException {
        String strC = c();
        if (!strC.isEmpty() && h() && c(strC)) {
            return;
        }
        f();
    }
}
