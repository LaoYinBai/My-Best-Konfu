package com.bytedance.pangle.c;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import com.alipay.sdk.util.e;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.util.k;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class a implements Closeable {

    /* renamed from: a, reason: collision with root package name */
    private final File f4529a;

    /* renamed from: b, reason: collision with root package name */
    private final long f4530b;

    /* renamed from: c, reason: collision with root package name */
    private final File f4531c;
    private final RandomAccessFile d;
    private final FileChannel e;
    private final FileLock f;

    a(File file, File file2) throws IOException {
        ZeusLogger.i(ZeusLogger.TAG_LOAD, "PluginMultiDex MultiDexExtractor(" + file.getPath() + ", " + file2.getPath() + ")");
        this.f4529a = file;
        this.f4531c = file2;
        this.f4530b = b(file);
        File file3 = new File(file2, "MultiDex.lock");
        this.d = new RandomAccessFile(file3, "rw");
        try {
            this.e = this.d.getChannel();
            try {
                ZeusLogger.i(ZeusLogger.TAG_LOAD, "PluginMultiDex Blocking on lock " + file3.getPath());
                this.f = this.e.lock();
                ZeusLogger.i(ZeusLogger.TAG_LOAD, "PluginMultiDex " + file3.getPath() + " locked");
            } catch (IOException | Error | RuntimeException e) {
                a(this.e);
                throw e;
            }
        } catch (IOException | Error | RuntimeException e2) {
            a(this.d);
            throw e2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x008c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final java.util.List<? extends java.io.File> a(android.content.Context r12, java.lang.String r13, boolean r14) throws java.io.IOException {
        /*
            r11 = this;
            java.lang.String r0 = "Zeus/load"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "PluginMultiDex MultiDexExtractor.load("
            r1.<init>(r2)
            java.io.File r2 = r11.f4529a
            java.lang.String r2 = r2.getPath()
            r1.append(r2)
            java.lang.String r2 = ", "
            r1.append(r2)
            r1.append(r14)
            java.lang.String r2 = ", "
            r1.append(r2)
            r1.append(r13)
            java.lang.String r2 = ")"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.bytedance.pangle.log.ZeusLogger.i(r0, r1)
            java.nio.channels.FileLock r0 = r11.f
            boolean r0 = r0.isValid()
            if (r0 == 0) goto Lcc
            if (r14 != 0) goto L8c
            java.io.File r0 = r11.f4529a
            long r1 = r11.f4530b
            android.content.SharedPreferences r3 = a(r12)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r13)
            java.lang.String r5 = "timestamp"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r5 = -1
            long r7 = r3.getLong(r4, r5)
            long r9 = a(r0)
            int r0 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r0 != 0) goto L7b
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r13)
            java.lang.String r4 = "crc"
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            long r3 = r3.getLong(r0, r5)
            int r0 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r0 == 0) goto L79
            goto L7b
        L79:
            r0 = 0
            goto L7c
        L7b:
            r0 = 1
        L7c:
            if (r0 != 0) goto L8c
            java.util.List r14 = r11.a(r12, r13)     // Catch: java.io.IOException -> L83
            goto Laf
        L83:
            r14 = move-exception
            java.lang.String r0 = "Zeus/load"
            java.lang.String r1 = "PluginMultiDex Failed to reload existing extracted secondary dex files, falling back to fresh extraction"
            com.bytedance.pangle.log.ZeusLogger.e(r0, r1, r14)
            goto L9d
        L8c:
            if (r14 == 0) goto L96
            java.lang.String r14 = "Zeus/load"
            java.lang.String r0 = "PluginMultiDex Forced extraction must be performed."
            com.bytedance.pangle.log.ZeusLogger.i(r14, r0)
            goto L9d
        L96:
            java.lang.String r14 = "Zeus/load"
            java.lang.String r0 = "PluginMultiDex Detected that extraction must be performed."
            com.bytedance.pangle.log.ZeusLogger.i(r14, r0)
        L9d:
            java.util.List r14 = r11.a()
            java.io.File r0 = r11.f4529a
            long r3 = a(r0)
            long r5 = r11.f4530b
            r1 = r12
            r2 = r13
            r7 = r14
            a(r1, r2, r3, r5, r7)
        Laf:
            java.lang.String r12 = "Zeus/load"
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            java.lang.String r0 = "PluginMultiDex load found "
            r13.<init>(r0)
            int r0 = r14.size()
            r13.append(r0)
            java.lang.String r0 = " secondary dex files"
            r13.append(r0)
            java.lang.String r13 = r13.toString()
            com.bytedance.pangle.log.ZeusLogger.i(r12, r13)
            return r14
        Lcc:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "MultiDexExtractor was closed"
            r12.<init>(r13)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.pangle.c.a.a(android.content.Context, java.lang.String, boolean):java.util.List");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.f.release();
        this.e.close();
        this.d.close();
    }

    private List<C0134a> a(Context context, String str) throws IOException {
        ZeusLogger.i(ZeusLogger.TAG_LOAD, "PluginMultiDex loading existing secondary dex files");
        String str2 = this.f4529a.getName() + ".classes";
        SharedPreferences sharedPreferencesA = a(context);
        int i = sharedPreferencesA.getInt(str + "dex.number", 1);
        ArrayList arrayList = new ArrayList(i + (-1));
        int i2 = 2;
        while (i2 <= i) {
            C0134a c0134a = new C0134a(this.f4531c, str2 + i2 + ".zip");
            if (!c0134a.isFile()) {
                throw new IOException("Missing extracted secondary dex file '" + c0134a.getPath() + "'");
            }
            c0134a.f4535a = b(c0134a);
            long j = sharedPreferencesA.getLong(str + "dex.crc." + i2, -1L);
            long j2 = sharedPreferencesA.getLong(str + "dex.time." + i2, -1L);
            long jLastModified = c0134a.lastModified();
            if (j2 == jLastModified) {
                String str3 = str2;
                SharedPreferences sharedPreferences = sharedPreferencesA;
                if (j == c0134a.f4535a) {
                    arrayList.add(c0134a);
                    i2++;
                    str2 = str3;
                    sharedPreferencesA = sharedPreferences;
                }
            }
            throw new IOException("Invalid extracted dex: " + c0134a + " (key \"" + str + "\"), expected modification time: " + j2 + ", modification time: " + jLastModified + ", expected crc: " + j + ", file crc: " + c0134a.f4535a);
        }
        return arrayList;
    }

    private static long a(File file) {
        long jLastModified = file.lastModified();
        return jLastModified == -1 ? jLastModified - 1 : jLastModified;
    }

    private static long b(File file) throws IOException {
        long jA = k.a(file);
        return jA == -1 ? jA - 1 : jA;
    }

    private List<C0134a> a() throws IOException {
        String str = this.f4529a.getName() + ".classes";
        b();
        ArrayList arrayList = new ArrayList();
        ZipFile zipFile = new ZipFile(this.f4529a);
        int i = 2;
        String str2 = "classes2.dex";
        while (true) {
            try {
                ZipEntry entry = zipFile.getEntry(str2);
                if (entry == null) {
                    return arrayList;
                }
                C0134a c0134a = new C0134a(this.f4531c, str + i + ".zip");
                arrayList.add(c0134a);
                ZeusLogger.i(ZeusLogger.TAG_LOAD, "PluginMultiDex Extraction is needed for file ".concat(String.valueOf(c0134a)));
                int i2 = 0;
                boolean z = false;
                while (i2 < 3 && !z) {
                    i2++;
                    a(zipFile, entry, c0134a, str);
                    try {
                        c0134a.f4535a = b(c0134a);
                        z = true;
                    } catch (IOException e) {
                        ZeusLogger.e(ZeusLogger.TAG_LOAD, "PluginMultiDex Failed to read crc from " + c0134a.getAbsolutePath(), e);
                        z = false;
                    }
                    StringBuilder sb = new StringBuilder("PluginMultiDex Extraction ");
                    sb.append(z ? "succeeded" : e.f2649a);
                    sb.append(" '");
                    sb.append(c0134a.getAbsolutePath());
                    sb.append("': length ");
                    sb.append(c0134a.length());
                    sb.append(" - crc: ");
                    sb.append(c0134a.f4535a);
                    ZeusLogger.i(ZeusLogger.TAG_LOAD, sb.toString());
                    if (!z) {
                        c0134a.delete();
                        if (c0134a.exists()) {
                            ZeusLogger.w(ZeusLogger.TAG_LOAD, "PluginMultiDex Failed to delete corrupted secondary dex '" + c0134a.getPath() + "'");
                        }
                    }
                }
                if (z) {
                    i++;
                    str2 = "classes" + i + ".dex";
                } else {
                    throw new IOException("Could not create zip file " + c0134a.getAbsolutePath() + " for secondary dex (" + i + ")");
                }
            } finally {
                try {
                    zipFile.close();
                } catch (IOException e2) {
                    ZeusLogger.e(ZeusLogger.TAG_LOAD, "PluginMultiDex Failed to close resource", e2);
                }
            }
        }
    }

    private static void a(Context context, String str, long j, long j2, List<C0134a> list) {
        SharedPreferences.Editor editorEdit = a(context).edit();
        editorEdit.putLong(str + "timestamp", j);
        editorEdit.putLong(str + "crc", j2);
        editorEdit.putInt(str + "dex.number", list.size() + 1);
        int i = 2;
        for (C0134a c0134a : list) {
            editorEdit.putLong(str + "dex.crc." + i, c0134a.f4535a);
            editorEdit.putLong(str + "dex.time." + i, c0134a.lastModified());
            i++;
        }
        editorEdit.commit();
    }

    private static SharedPreferences a(Context context) {
        return context.getSharedPreferences("multidex.version", Build.VERSION.SDK_INT < 11 ? 0 : 4);
    }

    private void b() {
        File[] fileArrListFiles = this.f4531c.listFiles(new FileFilter() { // from class: com.bytedance.pangle.c.a.1
            @Override // java.io.FileFilter
            public final boolean accept(File file) {
                return !file.getName().equals("MultiDex.lock");
            }
        });
        if (fileArrListFiles == null) {
            ZeusLogger.w(ZeusLogger.TAG_LOAD, "PluginMultiDex Failed to list secondary dex dir content (" + this.f4531c.getPath() + ").");
            return;
        }
        for (File file : fileArrListFiles) {
            ZeusLogger.i(ZeusLogger.TAG_LOAD, "PluginMultiDex Trying to delete old file " + file.getPath() + " of size " + file.length());
            if (file.delete()) {
                ZeusLogger.i(ZeusLogger.TAG_LOAD, "PluginMultiDex Deleted old file " + file.getPath());
            } else {
                ZeusLogger.w(ZeusLogger.TAG_LOAD, "PluginMultiDex Failed to delete old file " + file.getPath());
            }
        }
    }

    private static void a(ZipFile zipFile, ZipEntry zipEntry, File file, String str) throws IOException {
        InputStream inputStream = zipFile.getInputStream(zipEntry);
        File fileCreateTempFile = File.createTempFile("tmp-".concat(String.valueOf(str)), ".zip", file.getParentFile());
        ZeusLogger.i(ZeusLogger.TAG_LOAD, "PluginMultiDex Extracting " + fileCreateTempFile.getPath());
        try {
            ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(fileCreateTempFile)));
            try {
                ZipEntry zipEntry2 = new ZipEntry("classes.dex");
                zipEntry2.setTime(zipEntry.getTime());
                zipOutputStream.putNextEntry(zipEntry2);
                byte[] bArr = new byte[16384];
                while (true) {
                    int i = inputStream.read(bArr);
                    if (i == -1) {
                        break;
                    } else {
                        zipOutputStream.write(bArr, 0, i);
                    }
                }
                zipOutputStream.closeEntry();
                zipOutputStream.close();
                if (!fileCreateTempFile.setReadOnly()) {
                    throw new IOException("Failed to mark readonly \"" + fileCreateTempFile.getAbsolutePath() + "\" (tmp of \"" + file.getAbsolutePath() + "\")");
                }
                ZeusLogger.i(ZeusLogger.TAG_LOAD, "PluginMultiDex Renaming to " + file.getPath());
                if (fileCreateTempFile.renameTo(file)) {
                    return;
                }
                throw new IOException("Failed to rename \"" + fileCreateTempFile.getAbsolutePath() + "\" to \"" + file.getAbsolutePath() + "\"");
            } catch (Throwable th) {
                zipOutputStream.close();
                throw th;
            }
        } finally {
            a(inputStream);
            fileCreateTempFile.delete();
        }
    }

    private static void a(Closeable closeable) throws IOException {
        try {
            closeable.close();
        } catch (IOException e) {
            ZeusLogger.e(ZeusLogger.TAG_LOAD, "PluginMultiDex Failed to close resource", e);
        }
    }

    /* renamed from: com.bytedance.pangle.c.a$a, reason: collision with other inner class name */
    static class C0134a extends File {

        /* renamed from: a, reason: collision with root package name */
        public long f4535a;

        public C0134a(File file, String str) {
            super(file, str);
            this.f4535a = -1L;
        }
    }
}
