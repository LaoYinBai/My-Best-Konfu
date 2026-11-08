package cp.bd.c;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import cp.bd.c.n;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class l implements n.a {

    static class a {
        public ZipFile a;
        public ZipEntry b;

        public a(ZipFile zipFile, ZipEntry zipEntry) {
            this.a = zipFile;
            this.b = zipEntry;
        }
    }

    private static long a(InputStream inputStream, OutputStream outputStream) throws IOException {
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

    private static a a(Context context, String[] strArr, String str) throws IOException {
        String[] strArrA = a(context);
        int length = strArrA.length;
        int i = 0;
        while (true) {
            ZipFile zipFile = null;
            if (i >= length) {
                return null;
            }
            String str2 = strArrA[i];
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                if (i2 >= 5) {
                    break;
                }
                try {
                    zipFile = new ZipFile(new File(str2), 1);
                    break;
                } catch (IOException unused) {
                    i2 = i3;
                }
            }
            if (zipFile != null) {
                int i4 = 0;
                while (true) {
                    int i5 = i4 + 1;
                    if (i4 < 5) {
                        for (String str3 : strArr) {
                            String str4 = "lib" + File.separatorChar + str3 + File.separatorChar + str;
                            o.a("Looking for %s in APK %s...", str4, str2);
                            ZipEntry entry = zipFile.getEntry(str4);
                            if (entry != null) {
                                return new a(zipFile, entry);
                            }
                        }
                        i4 = i5;
                    } else {
                        try {
                            zipFile.close();
                            break;
                        } catch (IOException unused2) {
                        }
                    }
                }
            }
            i++;
        }
    }

    private static void a(Closeable closeable) throws IOException {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    private static String[] a(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (Build.VERSION.SDK_INT < 21 || applicationInfo.splitSourceDirs == null || applicationInfo.splitSourceDirs.length == 0) {
            return new String[]{applicationInfo.sourceDir};
        }
        String[] strArr = new String[applicationInfo.splitSourceDirs.length + 1];
        strArr[0] = applicationInfo.sourceDir;
        System.arraycopy(applicationInfo.splitSourceDirs, 0, strArr, 1, applicationInfo.splitSourceDirs.length);
        return strArr;
    }

    private static String[] a(Context context, String str) {
        Pattern patternCompile = Pattern.compile("lib" + File.separatorChar + "([^\\" + File.separatorChar + "]*)" + File.separatorChar + str);
        HashSet hashSet = new HashSet();
        for (String str2 : a(context)) {
            try {
                Enumeration<? extends ZipEntry> enumerationEntries = new ZipFile(new File(str2), 1).entries();
                while (enumerationEntries.hasMoreElements()) {
                    Matcher matcher = patternCompile.matcher(enumerationEntries.nextElement().getName());
                    if (matcher.matches()) {
                        hashSet.add(matcher.group(1));
                    }
                }
            } catch (IOException unused) {
            }
        }
        return (String[]) hashSet.toArray(new String[hashSet.size()]);
    }

    @Override // cp.bd.c.n.a
    public final void a(Context context, String[] strArr, String str, File file) throws Throwable {
        a aVarA;
        String[] strArrA;
        InputStream inputStream;
        FileOutputStream fileOutputStream;
        long jA;
        FileOutputStream fileOutputStream2 = null;
        try {
            aVarA = a(context, strArr, str);
        } catch (Throwable th) {
            th = th;
            aVarA = null;
        }
        try {
            if (aVarA == null) {
                try {
                    strArrA = a(context, str);
                } catch (Exception e) {
                    strArrA = new String[]{e.toString()};
                }
                throw new m(str, strArr, strArrA);
            }
            int i = 0;
            while (true) {
                int i2 = i + 1;
                if (i >= 5) {
                    if (aVarA != null) {
                        try {
                            if (aVarA.a != null) {
                                aVarA.a.close();
                                return;
                            }
                            return;
                        } catch (IOException unused) {
                            return;
                        }
                    }
                    return;
                }
                o.a("Found %s! Extracting...", str);
                try {
                    if (file.exists() || file.createNewFile()) {
                        try {
                            inputStream = aVarA.a.getInputStream(aVarA.b);
                            try {
                                fileOutputStream = new FileOutputStream(file);
                            } catch (FileNotFoundException unused2) {
                                fileOutputStream = null;
                            } catch (IOException unused3) {
                                fileOutputStream = null;
                            } catch (Throwable th2) {
                                th = th2;
                            }
                            try {
                                jA = a(inputStream, fileOutputStream);
                                fileOutputStream.getFD().sync();
                            } catch (FileNotFoundException unused4) {
                                a(inputStream);
                                a(fileOutputStream);
                                i = i2;
                            } catch (IOException unused5) {
                                a(inputStream);
                                a(fileOutputStream);
                                i = i2;
                            } catch (Throwable th3) {
                                th = th3;
                                fileOutputStream2 = fileOutputStream;
                                a(inputStream);
                                a(fileOutputStream2);
                                throw th;
                            }
                        } catch (FileNotFoundException unused6) {
                            inputStream = null;
                            fileOutputStream = null;
                        } catch (IOException unused7) {
                            inputStream = null;
                            fileOutputStream = null;
                        } catch (Throwable th4) {
                            th = th4;
                            inputStream = null;
                        }
                        if (jA == file.length()) {
                            a(inputStream);
                            a(fileOutputStream);
                            file.setReadable(true, false);
                            file.setExecutable(true, false);
                            file.setWritable(true);
                            if (aVarA != null) {
                                try {
                                    if (aVarA.a != null) {
                                        aVarA.a.close();
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
            if (aVarA != null) {
                try {
                    if (aVarA.a != null) {
                        aVarA.a.close();
                    }
                } catch (IOException unused10) {
                }
            }
            throw th;
        }
    }
}
