package a.a.c.a;

import a.a.c.a.a.f;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import androidx.annotation.RequiresApi;
import anet.channel.strategy.dispatch.DispatchConstants;
import com.appsflyer.share.Constants;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    public static final b f350a;

    /* renamed from: b, reason: collision with root package name */
    public static final String[] f351b = {"log", "m", "stdc++", "dl", Constants.URL_CAMPAIGN, "z", DispatchConstants.ANDROID, "jnigraphics", "EGL", "GLESv1_CM", "GLESv2", "GLESv3", "OpenSLES", "OpenMAXAL"};

    /* renamed from: c, reason: collision with root package name */
    public final File f352c;
    public final c d;
    public final File f;
    public File g;
    public volatile boolean h;
    public ZipFile[] i;
    public String j;
    public ApplicationInfo k;
    public Map<String, String> l;
    public String[] m;
    public String n;
    public final String[] o = {"/system/lib64", "/vendor/lib64"};
    public final Map<String, a> e = new HashMap();

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public boolean f353a = false;

        public String toString() {
            return Boolean.toString(this.f353a);
        }
    }

    /* renamed from: a.a.c.a.b$b, reason: collision with other inner class name */
    public class C0002b {

        /* renamed from: a, reason: collision with root package name */
        public RandomAccessFile f354a;

        /* renamed from: b, reason: collision with root package name */
        public FileLock f355b;

        /* renamed from: c, reason: collision with root package name */
        public FileChannel f356c;
        public File d;

        public C0002b(File file) {
            this.d = file;
        }

        public void a() throws IOException {
            this.f354a = new RandomAccessFile(this.d, "rw");
            try {
                this.f356c = this.f354a.getChannel();
                try {
                    b.this.d.a("blocking on lock " + this.d.getPath());
                    this.f355b = this.f356c.lock();
                    b.this.d.a("acquired on lock " + this.d.getPath());
                } catch (IOException e) {
                    b.this.a(this.f356c);
                    throw e;
                }
            } catch (IOException e2) {
                b.this.a(this.f354a);
                throw e2;
            }
        }

        public void b() throws IOException {
            FileLock fileLock = this.f355b;
            if (fileLock != null) {
                try {
                    fileLock.release();
                } catch (IOException unused) {
                }
            }
            b.this.d.a("released lock " + this.d.getPath());
            b.this.a(this.f356c);
            b.this.a(this.f354a);
        }
    }

    static {
        Context context = a.a.c.a.a.f337a;
        b bVar = null;
        a.a.c.a.a.f337a = null;
        if (context == null) {
            throw new d("you should call init first or use loadLibraryForModule.");
        }
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        File dir = context.getDir("cgs_data", 0);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, (a.a.c.a.a.f338b != null ? a.a.c.a.a.f338b : "czl.version") + "." + (new File(applicationInfo.sourceDir).lastModified() >> 8));
        File[] fileArrListFiles = dir.listFiles();
        if (fileArrListFiles != null) {
            for (File file2 : fileArrListFiles) {
                if (!file.getName().equals(file2.getName())) {
                    File[] fileArrListFiles2 = file2.listFiles();
                    if (fileArrListFiles2 != null) {
                        for (File file3 : fileArrListFiles2) {
                            file3.delete();
                        }
                    }
                    file2.delete();
                }
            }
        }
        file.mkdirs();
        if (file.exists()) {
            c cVar = a.a.c.a.a.f339c;
            if (cVar == null) {
                cVar = new c();
            }
            bVar = new b(applicationInfo, file, cVar);
        }
        f350a = bVar;
    }

    public b(ApplicationInfo applicationInfo, File file, c cVar) {
        this.f352c = file;
        this.d = cVar;
        this.k = applicationInfo;
        this.f = new File(this.f352c, "process.lock");
    }

    public static String a(String str) {
        return "lib" + str + ".so";
    }

    public final File a(InputStream inputStream, File file) throws IOException {
        File fileCreateTempFile = File.createTempFile("tmp-", file.getName(), file.getParentFile());
        this.d.a("extracting " + fileCreateTempFile.getPath());
        FileOutputStream fileOutputStream = new FileOutputStream(fileCreateTempFile);
        try {
            byte[] bArr = new byte[8192];
            while (true) {
                int i = inputStream.read(bArr);
                if (i == -1) {
                    break;
                }
                fileOutputStream.write(bArr, 0, i);
            }
            if (!fileCreateTempFile.setReadOnly()) {
                throw new IOException("failed to mark readonly \"" + fileCreateTempFile.getAbsolutePath() + "\" (tmp of \"" + file.getAbsolutePath() + "\")");
            }
            this.d.a("renaming to " + file.getPath());
            if (fileCreateTempFile.renameTo(file)) {
                return file;
            }
            throw new IOException("failed to rename \"" + fileCreateTempFile.getAbsolutePath() + "\" to \"" + file.getAbsolutePath() + "\"");
        } finally {
            a(fileOutputStream);
            fileCreateTempFile.delete();
        }
    }

    @RequiresApi(api = 21)
    public final void a() {
        String str;
        String[] strArr;
        if (this.h) {
            return;
        }
        synchronized (this) {
            if (this.h) {
                return;
            }
            File file = new File(this.k.sourceDir);
            ArrayList arrayList = new ArrayList();
            arrayList.add(file);
            if (Build.VERSION.SDK_INT >= 21 && (strArr = this.k.splitSourceDirs) != null && strArr.length != 0) {
                for (String str2 : strArr) {
                    arrayList.add(new File(str2));
                }
            }
            ZipFile[] zipFileArr = new ZipFile[arrayList.size()];
            Iterator it = arrayList.iterator();
            int i = 0;
            while (it.hasNext()) {
                File file2 = (File) it.next();
                int i2 = i + 1;
                try {
                    zipFileArr[i] = new ZipFile(file2);
                } catch (IOException e) {
                    new d("fail to get zip file " + file2.getName() + ", size " + file2.length() + ", exists " + file2.exists(), e).printStackTrace();
                }
                i = i2;
            }
            this.i = zipFileArr;
            this.g = new File(this.k.nativeLibraryDir);
            this.l = new HashMap();
            this.l.put("arm64-v8a", "arm64");
            this.l.put("armeabi-v7a", "arm");
            this.l.put("armeabi", "arm");
            if (Build.VERSION.SDK_INT >= 21) {
                try {
                    Class<?> cls = Class.forName("dalvik.system.VMRuntime");
                    str = (String) cls.getDeclaredMethod("vmInstructionSet", new Class[0]).invoke(cls.getDeclaredMethod("getRuntime", new Class[0]).invoke(null, new Object[0]), new Object[0]);
                } catch (Exception e2) {
                    this.d.a("fail to process vm abi previously", new d("reflect err", e2));
                }
            } else {
                str = null;
            }
            this.j = str;
            this.d.a("vm instruction set: " + this.j);
            this.m = Build.VERSION.SDK_INT < 21 ? new String[]{Build.CPU_ABI, Build.CPU_ABI2} : Build.SUPPORTED_ABIS;
            this.h = true;
        }
    }

    public final void a(Closeable closeable) throws IOException {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException e) {
            this.d.a("failed to close resource", e);
        }
    }

    public final void a(File file, boolean z) throws IOException {
        String name = file.getName();
        C0002b c0002b = new C0002b(this.f);
        try {
            try {
                c0002b.a();
                if (!a(file)) {
                    if (!z) {
                        throw new d("fail to extract " + name);
                    }
                    this.d.a("may be system lib, no found " + name);
                }
            } catch (IOException e) {
                throw new d("fail to extract " + name, e);
            }
        } finally {
            c0002b.b();
        }
    }

    @RequiresApi(api = 21)
    public void a(String str, boolean z) {
        synchronized (this.e) {
            a aVar = this.e.get(str);
            if (aVar == null) {
                aVar = new a();
                this.e.put(str, aVar);
            } else if (aVar.f353a) {
                return;
            }
            synchronized (aVar) {
                if (!aVar.f353a) {
                    b(str, z);
                    aVar.f353a = true;
                    return;
                }
                this.d.a("lib was already loaded before: " + str);
            }
        }
    }

    public final void a(File[] fileArr, int i, int i2) {
        if (i == i2) {
            return;
        }
        File file = fileArr[i];
        fileArr[i] = fileArr[i2];
        fileArr[i2] = file;
    }

    public final boolean a(File file) throws IOException {
        c(file.getName());
        String str = "lib/" + this.n + Constants.URL_PATH_DELIMITER + file.getName();
        for (ZipFile zipFile : this.i) {
            ZipEntry entry = zipFile.getEntry(str);
            if (entry != null) {
                InputStream inputStream = zipFile.getInputStream(entry);
                try {
                    a(inputStream, file);
                    this.d.a("get lib file " + file.getPath() + " from " + str + " of " + zipFile.getName());
                    a(inputStream);
                    return true;
                } catch (Throwable th) {
                    a(inputStream);
                    throw th;
                }
            }
        }
        return false;
    }

    public final boolean a(File file, String str, boolean z) {
        try {
            this.d.d(file.getPath());
            this.d.a("loaded the extracted lib " + str);
            return true;
        } catch (UnsatisfiedLinkError e) {
            if (z) {
                throw new d("finally fail to load " + file.getPath(), e);
            }
            this.d.a("fail to load " + file.getName() + ", out lib exists", e);
            return false;
        }
    }

    public final boolean a(String str, String str2) {
        try {
            this.d.c(str);
            this.d.a("loaded the origin lib " + str + ", tag: " + str2);
            return true;
        } catch (UnsatisfiedLinkError e) {
            this.d.a("fail to load " + str + ", tag: " + str2, e);
            return false;
        }
    }

    @RequiresApi(api = 21)
    public final void b(File file, boolean z) throws Throwable {
        f fVar;
        try {
            try {
                fVar = new f(file);
                try {
                    List<String> listA = fVar.a();
                    Collections.sort(listA);
                    a(fVar);
                    for (String str : listA) {
                        String strSubstring = str.substring(3, str.length() - 3);
                        String[] strArr = f351b;
                        int length = strArr.length;
                        boolean z2 = false;
                        int i = 0;
                        while (true) {
                            if (i >= length) {
                                break;
                            }
                            if (strArr[i].equals(strSubstring)) {
                                z2 = true;
                                break;
                            }
                            i++;
                        }
                        if (!z2) {
                            this.d.a("to load depended lib " + strSubstring);
                            a(strSubstring, z);
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    a(fVar);
                    throw th;
                }
            } catch (IOException e) {
                throw new d("fail to load depended lib", e);
            }
        } catch (Throwable th2) {
            th = th2;
            fVar = null;
        }
    }

    public final boolean b(String str) {
        for (String str2 : this.o) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public final void c(String str) {
        boolean zEquals;
        if (this.n != null) {
            return;
        }
        for (String str2 : this.m) {
            if (this.j == null) {
                zEquals = true;
            } else {
                String str3 = this.l.get(str2);
                String str4 = this.j;
                if (str3 == null) {
                    str3 = str2;
                }
                zEquals = str4.equals(str3);
            }
            if (zEquals) {
                String str5 = "lib/" + str2 + Constants.URL_PATH_DELIMITER + str;
                for (ZipFile zipFile : this.i) {
                    if (zipFile != null && zipFile.getEntry(str5) != null) {
                        this.n = str2;
                        this.d.a("ensure that abi is " + str2);
                        return;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("can not ensure abi for ");
        sb.append(str);
        sb.append(", check ");
        sb.append(this.j);
        sb.append(", apks ");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("[");
        for (ZipFile zipFile2 : this.i) {
            if (zipFile2 != null) {
                String name = zipFile2.getName();
                File file = new File(name);
                sb2.append(name);
                sb2.append("#");
                sb2.append(zipFile2.size());
                sb2.append(":");
                sb2.append(file.length());
                sb2.append("|");
            }
        }
        sb2.append("]");
        sb.append(sb2.toString());
        throw new d(sb.toString());
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0240  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0264  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0058 A[PHI: r4
  0x0058: PHI (r4v1 boolean) = (r4v0 boolean), (r4v0 boolean), (r4v8 boolean) binds: [B:22:0x0044, B:30:0x0056, B:78:0x0147] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x018f  */
    @androidx.annotation.RequiresApi(api = 21)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void b(java.lang.String r17, boolean r18) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 665
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: a.a.c.a.b.b(java.lang.String, boolean):void");
    }
}
