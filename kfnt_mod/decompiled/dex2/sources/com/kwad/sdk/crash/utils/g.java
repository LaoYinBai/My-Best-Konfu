package com.kwad.sdk.crash.utils;

import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class g {
    public static final char i;
    public static final Charset a = Charset.forName(C.ASCII_NAME);
    public static final Charset b = Charset.forName(C.UTF8_NAME);
    public static final BigInteger c = BigInteger.valueOf(1024);
    public static final BigInteger d = c.multiply(c);
    public static final BigInteger e = c.multiply(d);
    public static final BigInteger f = c.multiply(e);
    public static final BigInteger g = c.multiply(f);
    public static final BigInteger h = c.multiply(g);
    public static final BigInteger j = BigInteger.valueOf(1024).multiply(BigInteger.valueOf(1152921504606846976L));
    public static final BigInteger k = c.multiply(j);
    public static final File[] l = new File[0];
    public static final String m = Character.toString('.');
    private static final Charset n = Charset.forName(C.UTF8_NAME);
    private static final char o = File.separatorChar;

    static {
        i = a() ? '/' : '\\';
    }

    public static FileInputStream a(File file) throws IOException {
        if (!file.exists()) {
            throw new FileNotFoundException("File '" + file + "' does not exist");
        }
        if (file.isDirectory()) {
            throw new IOException("File '" + file + "' exists but is a directory");
        }
        if (file.canRead()) {
            return new FileInputStream(file);
        }
        throw new IOException("File '" + file + "' cannot be read");
    }

    public static FileOutputStream a(File file, boolean z) throws IOException {
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            if (parentFile != null && !parentFile.mkdirs() && !parentFile.isDirectory()) {
                throw new IOException("Directory '" + parentFile + "' could not be created");
            }
        } else {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }
            if (!file.canWrite()) {
                throw new IOException("File '" + file + "' cannot be written to");
            }
        }
        return new FileOutputStream(file, z);
    }

    public static String a(File file, Charset charset) throws Throwable {
        FileInputStream fileInputStreamA;
        try {
            fileInputStreamA = a(file);
            try {
                String strA = h.a(fileInputStreamA, a.a(charset));
                b.a((InputStream) fileInputStreamA);
                return strA;
            } catch (Throwable th) {
                th = th;
                b.a((InputStream) fileInputStreamA);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStreamA = null;
        }
    }

    public static void a(File file, File file2) throws Throwable {
        a(file, file2, true);
    }

    public static void a(File file, File file2, FileFilter fileFilter, boolean z) throws Throwable {
        if (file == null) {
            throw new NullPointerException("Source must not be null");
        }
        if (file2 == null) {
            throw new NullPointerException("Destination must not be null");
        }
        if (!file.exists()) {
            throw new FileNotFoundException("Source '" + file + "' does not exist");
        }
        if (!file.isDirectory()) {
            throw new IOException("Source '" + file + "' exists but is not a directory");
        }
        if (file.getCanonicalPath().equals(file2.getCanonicalPath())) {
            throw new IOException("Source '" + file + "' and destination '" + file2 + "' are the same");
        }
        ArrayList arrayList = null;
        if (file2.getCanonicalPath().startsWith(file.getCanonicalPath())) {
            File[] fileArrListFiles = fileFilter == null ? file.listFiles() : file.listFiles(fileFilter);
            if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                arrayList = new ArrayList(fileArrListFiles.length);
                for (File file3 : fileArrListFiles) {
                    arrayList.add(new File(file2, file3.getName()).getCanonicalPath());
                }
            }
        }
        a(file, file2, fileFilter, z, arrayList);
    }

    private static void a(File file, File file2, FileFilter fileFilter, boolean z, List<String> list) throws Throwable {
        File[] fileArrListFiles = fileFilter == null ? file.listFiles() : file.listFiles(fileFilter);
        if (fileArrListFiles == null) {
            throw new IOException("Failed to list contents of " + file);
        }
        if (file2.exists()) {
            if (!file2.isDirectory()) {
                throw new IOException("Destination '" + file2 + "' exists but is not a directory");
            }
        } else if (!file2.mkdirs() && !file2.isDirectory()) {
            throw new IOException("Destination '" + file2 + "' directory cannot be created");
        }
        if (!file2.canWrite()) {
            throw new IOException("Destination '" + file2 + "' cannot be written to");
        }
        for (File file3 : fileArrListFiles) {
            File file4 = new File(file2, file3.getName());
            if (list == null || !list.contains(file3.getCanonicalPath())) {
                if (file3.isDirectory()) {
                    a(file3, file4, fileFilter, z, list);
                } else {
                    b(file3, file4, z);
                }
            }
        }
        if (z) {
            file2.setLastModified(file.lastModified());
        }
    }

    public static void a(File file, File file2, boolean z) throws Throwable {
        a(file, file2, null, z);
    }

    public static boolean a() {
        return o == '\\';
    }

    public static void b(File file) throws IOException {
        if (file.exists()) {
            if (!f(file)) {
                c(file);
            }
            if (file.delete()) {
                return;
            }
            throw new IOException("Unable to delete directory " + file + ".");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4, types: [java.io.FileOutputStream, java.io.OutputStream] */
    private static void b(File file, File file2, boolean z) throws Throwable {
        FileInputStream fileInputStream;
        ?? fileOutputStream;
        FileChannel channel;
        if (file2.exists() && file2.isDirectory()) {
            throw new IOException("Destination '" + file2 + "' exists but is a directory");
        }
        FileChannel fileChannel = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (Throwable th) {
            th = th;
            fileInputStream = null;
            fileOutputStream = 0;
        }
        try {
            fileOutputStream = new FileOutputStream(file2);
            try {
                channel = fileInputStream.getChannel();
                try {
                    FileChannel channel2 = fileOutputStream.getChannel();
                    try {
                        long size = channel.size();
                        long jTransferFrom = 0;
                        while (jTransferFrom < size) {
                            long j2 = size - jTransferFrom;
                            jTransferFrom += channel2.transferFrom(channel, jTransferFrom, j2 > 31457280 ? 31457280L : j2);
                        }
                        b.a(channel2);
                        b.a((OutputStream) fileOutputStream);
                        b.a(channel);
                        b.a((InputStream) fileInputStream);
                        if (file.length() == file2.length()) {
                            if (z) {
                                file2.setLastModified(file.lastModified());
                            }
                        } else {
                            throw new IOException("Failed to copy full contents from '" + file + "' to '" + file2 + "'");
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        fileChannel = channel2;
                        b.a(fileChannel);
                        b.a((OutputStream) fileOutputStream);
                        b.a(channel);
                        b.a((InputStream) fileInputStream);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (Throwable th4) {
                th = th4;
                channel = null;
            }
        } catch (Throwable th5) {
            th = th5;
            fileOutputStream = 0;
            channel = fileOutputStream;
            b.a(fileChannel);
            b.a((OutputStream) fileOutputStream);
            b.a(channel);
            b.a((InputStream) fileInputStream);
            throw th;
        }
    }

    public static void c(File file) throws IOException {
        if (!file.exists()) {
            throw new IllegalArgumentException(file + " does not exist");
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(file + " is not a directory");
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            throw new IOException("Failed to list contents of " + file);
        }
        IOException e2 = null;
        for (File file2 : fileArrListFiles) {
            try {
                e(file2);
            } catch (IOException e3) {
                e2 = e3;
            }
        }
        if (e2 != null) {
            throw e2;
        }
    }

    public static String d(File file) {
        return a(file, Charset.defaultCharset());
    }

    public static void e(File file) throws IOException {
        if (file.isDirectory()) {
            b(file);
            return;
        }
        boolean zExists = file.exists();
        if (file.delete()) {
            return;
        }
        if (zExists) {
            throw new IOException("Unable to delete file: " + file);
        }
        throw new FileNotFoundException("File does not exist: " + file);
    }

    public static boolean f(File file) {
        if (file == null) {
            throw new NullPointerException("File must not be null");
        }
        if (a()) {
            return false;
        }
        if (file.getParent() != null) {
            file = new File(file.getParentFile().getCanonicalFile(), file.getName());
        }
        return !file.getCanonicalFile().equals(file.getAbsoluteFile());
    }
}
