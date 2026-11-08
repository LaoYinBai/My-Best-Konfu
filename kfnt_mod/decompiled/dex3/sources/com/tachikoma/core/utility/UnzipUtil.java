package com.tachikoma.core.utility;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.ss.union.game.sdk.feedback.picture.select.d;
import com.tachikoma.core.log.Logger;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class UnzipUtil {
    private static final byte[] PNG_END_CODE = {73, 69, 78, 68};
    private static final byte[] ZIP_HEADER = {4, 3, 75, 80};

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v10, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v8, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v1, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v7 */
    public static void copyAssetsFile(Context context, String str, String str2, String str3) throws Throwable {
        FileOutputStream fileOutputStream;
        Exception e;
        File file = new File(str3 + File.separator + str);
        if (file.exists()) {
            return;
        }
        File file2 = new File(str3);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        try {
            try {
                try {
                    context = context.getAssets().open(str2);
                    try {
                        fileOutputStream = new FileOutputStream(file);
                        try {
                            byte[] bArr = new byte[d.d];
                            while (true) {
                                int i = context.read(bArr);
                                if (i == -1) {
                                    break;
                                } else {
                                    fileOutputStream.write(bArr, 0, i);
                                }
                            }
                            fileOutputStream.flush();
                            if (context != 0) {
                                context.close();
                            }
                            fileOutputStream.close();
                        } catch (Exception e2) {
                            e = e2;
                            Logger.logE("copyAssetsFile exception", e);
                            if (context != 0) {
                                context.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                        }
                    } catch (Exception e3) {
                        fileOutputStream = null;
                        e = e3;
                    } catch (Throwable th) {
                        str2 = 0;
                        th = th;
                        if (context != 0) {
                            try {
                                context.close();
                            } catch (IOException e4) {
                                Logger.logE("copyAssetsFile exception", e4);
                                throw th;
                            }
                        }
                        if (str2 != 0) {
                            str2.close();
                        }
                        throw th;
                    }
                } catch (IOException e5) {
                    Logger.logE("copyAssetsFile exception", e5);
                }
            } catch (Exception e6) {
                fileOutputStream = null;
                e = e6;
                context = 0;
            } catch (Throwable th2) {
                str2 = 0;
                th = th2;
                context = 0;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public static File ensureFile(File file, String str) {
        File file2 = new File(file, str);
        if (file2.exists()) {
            FileUtil.deleteContents(file2);
            file2.delete();
        }
        return file2;
    }

    private static File extractZipFromPng(File file) throws IOException {
        if (Build.VERSION.SDK_INT > 23) {
            return file;
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(4);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        Throwable th = null;
        try {
            try {
                bufferedInputStream.skip(8L);
                do {
                    bufferedInputStream.read(byteBufferAllocate.array());
                    bufferedInputStream.read(byteBufferAllocate2.array());
                    byteBufferAllocate.rewind();
                    bufferedInputStream.skip(byteBufferAllocate.getInt() + 4);
                } while (!Arrays.equals(PNG_END_CODE, byteBufferAllocate2.array()));
                File file2 = new File(file.getParent(), UUID.randomUUID().toString() + "_png.zip");
                FileUtil.copyInputStreamToFile(bufferedInputStream, file2);
                bufferedInputStream.close();
                return file2;
            } finally {
            }
        } catch (Throwable th2) {
            if (th != null) {
                try {
                    bufferedInputStream.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
            } else {
                bufferedInputStream.close();
            }
            throw th2;
        }
    }

    public static Charset getKwaiDefaultUnzipFileCharset() {
        try {
            return Charset.forName("8859_1");
        } catch (Exception unused) {
            return Charset.defaultCharset();
        }
    }

    public static File getRealFileName(String str, String str2, Charset charset) {
        String[] strArrSplit = str2.split("/");
        File file = new File(str);
        if (strArrSplit.length < 1) {
            return file;
        }
        int i = 0;
        while (i < strArrSplit.length - 1) {
            File file2 = new File(file, getTextWithCharset(strArrSplit[i], charset));
            i++;
            file = file2;
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        return new File(file, getTextWithCharset(strArrSplit[strArrSplit.length - 1], charset));
    }

    public static String getSafeEntryNameOrThrow(ZipEntry zipEntry) {
        String name = zipEntry.getName();
        if (name.contains("../")) {
            throw new IllegalStateException("file name can't contains ../");
        }
        return name;
    }

    public static String getTextWithCharset(String str, Charset charset) {
        if (charset != null) {
            try {
                if (charset.equals(getKwaiDefaultUnzipFileCharset())) {
                    return new String(str.getBytes("8859_1"), "GB2312");
                }
            } catch (Throwable unused) {
            }
        }
        return str;
    }

    public static void unzipFile(File file, String str) throws IOException {
        unzipFile(file, str, getKwaiDefaultUnzipFileCharset());
    }

    public static void unzipFile(File file, String str, @NonNull String str2) throws Throwable {
        unzipFile(file, str, str2, getKwaiDefaultUnzipFileCharset());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void unzipFile(@NonNull File file, @NonNull String str, @NonNull String str2, Charset charset) throws Throwable {
        File file2;
        ZipFile zipFile;
        int i;
        BufferedOutputStream bufferedOutputStream;
        BufferedInputStream bufferedInputStream = null;
        try {
            String str3 = new File(str).getPath() + "_folder_" + System.currentTimeMillis();
            file2 = new File(str3);
            try {
                try {
                    file2.mkdirs();
                    if (file.length() > 100000000) {
                        Logger.logE("upzip fileSize: " + file.length() + " targetFile: " + file.getPath(), null);
                    }
                    zipFile = new ZipFile(file);
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Throwable th2) {
                th = th2;
                zipFile = bufferedInputStream;
            }
            try {
                Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
                byte[] bArr = new byte[d.d];
                while (true) {
                    if (!enumerationEntries.hasMoreElements()) {
                        break;
                    }
                    ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                    if (TextUtils.isEmpty(zipEntryNextElement.getName())) {
                        Logger.logE("empty" + zipEntryNextElement.getName(), null);
                    }
                    if (zipEntryNextElement.isDirectory()) {
                        new File(getTextWithCharset(new File(file2, getSafeEntryNameOrThrow(zipEntryNextElement)).getPath(), charset)).mkdirs();
                    } else {
                        try {
                            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(getRealFileName(str3, getSafeEntryNameOrThrow(zipEntryNextElement), charset)));
                            try {
                                BufferedInputStream bufferedInputStream2 = new BufferedInputStream(zipFile.getInputStream(zipEntryNextElement));
                                while (true) {
                                    try {
                                        int i2 = bufferedInputStream2.read(bArr, 0, d.d);
                                        if (i2 == -1) {
                                            break;
                                        } else {
                                            bufferedOutputStream.write(bArr, 0, i2);
                                        }
                                    } catch (Throwable th3) {
                                        th = th3;
                                        bufferedInputStream = bufferedInputStream2;
                                        CloseableUtil.closeQuietly((InputStream) bufferedInputStream);
                                        CloseableUtil.closeQuietly((OutputStream) bufferedOutputStream);
                                        throw th;
                                    }
                                }
                                CloseableUtil.closeQuietly((InputStream) bufferedInputStream2);
                                CloseableUtil.closeQuietly((OutputStream) bufferedOutputStream);
                            } catch (Throwable th4) {
                                th = th4;
                            }
                        } catch (Throwable th5) {
                            th = th5;
                            bufferedOutputStream = null;
                        }
                    }
                }
                File file3 = new File(str);
                if (!file3.exists()) {
                    file3.mkdirs();
                }
                File[] fileArrListFiles = file2.listFiles();
                if (fileArrListFiles.length != 1 || TextUtils.isEmpty(str2)) {
                    for (File file4 : fileArrListFiles) {
                        if (!file4.renameTo(ensureFile(file3, file4.getName()))) {
                            throw new IOException("rename failed, " + file3);
                        }
                    }
                } else {
                    if (!fileArrListFiles[0].renameTo(ensureFile(file3, str2))) {
                        throw new IOException("rename failed, " + file3);
                    }
                }
                if (file3.length() > 100000000) {
                    Logger.logE("upzip fileSize: " + file3.length() + " targetFile: " + file3.getPath(), null);
                }
                CloseableUtil.closeQuietly(zipFile);
                FileUtil.deleteContents(file2);
                file2.delete();
            } catch (Throwable th6) {
                th = th6;
                CloseableUtil.closeQuietly(zipFile);
                if (file2 != null) {
                    FileUtil.deleteContents(file2);
                    file2.delete();
                }
                throw th;
            }
        } catch (Throwable th7) {
            th = th7;
            file2 = null;
        }
    }

    public static void unzipFile(File file, String str, Charset charset) throws IOException {
        File fileExtractZipFromPng = extractZipFromPng(file);
        try {
            unzipFile(fileExtractZipFromPng, str, null, charset);
        } finally {
            if (fileExtractZipFromPng != file) {
                FileUtil.deleteQuietly(fileExtractZipFromPng);
            }
        }
    }
}
