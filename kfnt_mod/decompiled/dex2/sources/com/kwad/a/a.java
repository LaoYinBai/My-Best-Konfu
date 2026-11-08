package com.kwad.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import com.kwad.a.b;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a implements b.a {

    /* renamed from: com.kwad.a.a$a, reason: collision with other inner class name */
    private static class C0020a {
        public ZipFile a;
        public ZipEntry b;

        public C0020a(ZipFile zipFile, ZipEntry zipEntry) {
            this.a = zipFile;
            this.b = zipEntry;
        }
    }

    private long a(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[4096];
        long j = 0;
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                outputStream.flush();
                return j;
            }
            outputStream.write(bArr, 0, i);
            j += i;
        }
    }

    private C0020a a(Context context, String[] strArr, String str, c cVar) {
        int i;
        String[] strArrA = a(context);
        int length = strArrA.length;
        char c = 0;
        ZipFile zipFile = null;
        int i2 = 0;
        while (i2 < length) {
            String str2 = strArrA[i2];
            int i3 = 0;
            while (true) {
                int i4 = i3 + 1;
                i = 5;
                if (i3 >= 5) {
                    break;
                }
                try {
                    zipFile = new ZipFile(new File(str2), 1);
                    break;
                } catch (IOException unused) {
                    i3 = i4;
                }
            }
            if (zipFile != null) {
                int i5 = 0;
                while (true) {
                    int i6 = i5 + 1;
                    if (i5 >= i) {
                        break;
                    }
                    int length2 = strArr.length;
                    int i7 = 0;
                    while (i7 < length2) {
                        String str3 = "lib" + File.separatorChar + strArr[i7] + File.separatorChar + str;
                        Object[] objArr = new Object[2];
                        objArr[c] = str3;
                        objArr[1] = str2;
                        cVar.a("Looking for %s in APK %s...", objArr);
                        ZipEntry entry = zipFile.getEntry(str3);
                        if (entry != null) {
                            return new C0020a(zipFile, entry);
                        }
                        i7++;
                        c = 0;
                        i = 5;
                    }
                    i5 = i6;
                    c = 0;
                }
            }
            i2++;
            c = 0;
        }
        return null;
    }

    private void a(Closeable closeable) throws IOException {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    private String[] a(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (Build.VERSION.SDK_INT < 21 || applicationInfo.splitSourceDirs == null || applicationInfo.splitSourceDirs.length == 0) {
            return new String[]{applicationInfo.sourceDir};
        }
        String[] strArr = new String[applicationInfo.splitSourceDirs.length + 1];
        strArr[0] = applicationInfo.sourceDir;
        System.arraycopy(applicationInfo.splitSourceDirs, 0, strArr, 1, applicationInfo.splitSourceDirs.length);
        return strArr;
    }

    @Override // com.kwad.a.b.a
    public void a(Context context, String[] strArr, String str, File file, c cVar) throws Throwable {
        C0020a c0020aA;
        InputStream inputStream;
        FileOutputStream fileOutputStream;
        long jA;
        Closeable closeable = null;
        try {
            c0020aA = a(context, strArr, str, cVar);
        } catch (Throwable th) {
            th = th;
            c0020aA = null;
        }
        try {
            if (c0020aA == null) {
                throw new RuntimeException(str);
            }
            int i = 0;
            while (true) {
                int i2 = i + 1;
                if (i >= 5) {
                    cVar.a("FATAL! Couldn't extract the library from the APK!");
                    if (c0020aA != null) {
                        try {
                            if (c0020aA.a != null) {
                                c0020aA.a.close();
                                return;
                            }
                            return;
                        } catch (IOException unused) {
                            return;
                        }
                    }
                    return;
                }
                cVar.a("Found %s! Extracting...", str);
                try {
                    if (file.exists() || file.createNewFile()) {
                        try {
                            inputStream = c0020aA.a.getInputStream(c0020aA.b);
                            try {
                                fileOutputStream = new FileOutputStream(file);
                            } catch (FileNotFoundException unused2) {
                                fileOutputStream = null;
                            } catch (IOException unused3) {
                                fileOutputStream = null;
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        } catch (FileNotFoundException unused4) {
                            inputStream = null;
                            fileOutputStream = null;
                        } catch (IOException unused5) {
                            inputStream = null;
                            fileOutputStream = null;
                        } catch (Throwable th3) {
                            th = th3;
                            inputStream = null;
                        }
                        try {
                            jA = a(inputStream, fileOutputStream);
                            fileOutputStream.getFD().sync();
                        } catch (FileNotFoundException unused6) {
                            a(inputStream);
                            a(fileOutputStream);
                            i = i2;
                        } catch (IOException unused7) {
                            a(inputStream);
                            a(fileOutputStream);
                            i = i2;
                        } catch (Throwable th4) {
                            th = th4;
                            closeable = fileOutputStream;
                            a(inputStream);
                            a(closeable);
                            throw th;
                        }
                        if (jA == file.length()) {
                            a(inputStream);
                            a(fileOutputStream);
                            file.setReadable(true, false);
                            file.setExecutable(true, false);
                            file.setWritable(true);
                            if (c0020aA != null) {
                                try {
                                    if (c0020aA.a != null) {
                                        c0020aA.a.close();
                                        return;
                                    }
                                    return;
                                } catch (IOException unused8) {
                                    return;
                                }
                            }
                            return;
                        }
                        a(inputStream);
                        a(fileOutputStream);
                    }
                } catch (IOException unused9) {
                }
                i = i2;
            }
        } catch (Throwable th5) {
            th = th5;
            if (c0020aA != null) {
                try {
                    if (c0020aA.a != null) {
                        c0020aA.a.close();
                    }
                } catch (IOException unused10) {
                }
            }
            throw th;
        }
    }
}
