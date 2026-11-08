package com.mbridge.msdk.foundation.download.utils;

import com.mbridge.msdk.foundation.tools.s;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class UnzipUtility {
    private static final int BUFFER_SIZE = 4096;
    private static final String TAG = "UnzipUtility";

    public int unzip(String str, String str2) throws Throwable {
        FileOutputStream fileOutputStream;
        InputStream inputStream;
        if (str == null || str2 == null) {
            return -1;
        }
        if (!str2.endsWith("/")) {
            str2 = str2 + "/";
        }
        File file = new File(str);
        if (!file.exists()) {
            return 1;
        }
        InputStream inputStream2 = null;
        try {
            ZipFile zipFile = new ZipFile(file);
            Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
            fileOutputStream = null;
            while (enumerationEntries.hasMoreElements()) {
                try {
                    try {
                        ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                        if (zipEntryNextElement == null) {
                            if (inputStream2 != null) {
                                try {
                                    inputStream2.close();
                                } catch (IOException e) {
                                    s.d(TAG, e.getMessage());
                                }
                            }
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e2) {
                                    s.d(TAG, e2.getMessage());
                                }
                            }
                            return 2;
                        }
                        File file2 = new File(str2 + zipEntryNextElement.getName());
                        if (zipEntryNextElement.isDirectory()) {
                            file2.mkdirs();
                        } else {
                            if (!file2.getParentFile().exists()) {
                                file2.getParentFile().mkdirs();
                            }
                            FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
                            try {
                                inputStream = zipFile.getInputStream(zipEntryNextElement);
                            } catch (IOException e3) {
                                e = e3;
                            } catch (Throwable th) {
                                th = th;
                            }
                            try {
                                byte[] bArr = new byte[1024];
                                while (true) {
                                    int i = inputStream.read(bArr, 0, 1024);
                                    if (i == -1) {
                                        break;
                                    }
                                    fileOutputStream2.write(bArr, 0, i);
                                    fileOutputStream2.flush();
                                }
                                inputStream2 = inputStream;
                                fileOutputStream = fileOutputStream2;
                            } catch (IOException e4) {
                                e = e4;
                                inputStream2 = inputStream;
                                fileOutputStream = fileOutputStream2;
                                s.d(TAG, e.getMessage());
                                if (inputStream2 != null) {
                                    try {
                                        inputStream2.close();
                                    } catch (IOException e5) {
                                        s.d(TAG, e5.getMessage());
                                    }
                                }
                                if (fileOutputStream != null) {
                                    try {
                                        fileOutputStream.close();
                                    } catch (IOException e6) {
                                        s.d(TAG, e6.getMessage());
                                    }
                                }
                                return 3;
                            } catch (Throwable th2) {
                                th = th2;
                                inputStream2 = inputStream;
                                fileOutputStream = fileOutputStream2;
                                if (inputStream2 != null) {
                                    try {
                                        inputStream2.close();
                                    } catch (IOException e7) {
                                        s.d(TAG, e7.getMessage());
                                    }
                                }
                                if (fileOutputStream == null) {
                                    throw th;
                                }
                                try {
                                    fileOutputStream.close();
                                    throw th;
                                } catch (IOException e8) {
                                    s.d(TAG, e8.getMessage());
                                    throw th;
                                }
                            }
                        }
                    } catch (IOException e9) {
                        e = e9;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            }
            zipFile.close();
            if (inputStream2 != null) {
                try {
                    inputStream2.close();
                } catch (IOException e10) {
                    s.d(TAG, e10.getMessage());
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e11) {
                    s.d(TAG, e11.getMessage());
                }
            }
            return 0;
        } catch (IOException e12) {
            e = e12;
            fileOutputStream = null;
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream = null;
        }
    }

    private void extractFile(ZipInputStream zipInputStream, String str) throws Throwable {
        BufferedOutputStream bufferedOutputStream;
        File file = new File(str);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e) {
            e = e;
        }
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                int i = zipInputStream.read(bArr);
                if (i != -1) {
                    bufferedOutputStream.write(bArr, 0, i);
                } else {
                    bufferedOutputStream.close();
                    return;
                }
            }
        } catch (IOException e2) {
            e = e2;
            throw new IOException(e);
        } catch (Throwable th2) {
            th = th2;
            bufferedOutputStream2 = bufferedOutputStream;
            if (bufferedOutputStream2 != null) {
                bufferedOutputStream2.close();
            }
            throw th;
        }
    }
}
