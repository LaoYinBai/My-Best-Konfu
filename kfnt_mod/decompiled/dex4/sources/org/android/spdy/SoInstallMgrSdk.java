package org.android.spdy;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
public class SoInstallMgrSdk {
    private static final String ARMEABI = "armeabi";
    private static final int EventID_SO_INIT = 21033;
    static final String LOGTAG = "INIT_SO";
    private static final String MIPS = "mips";
    private static final String X86 = "x86";
    static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    public static boolean initSo(String str, int i) {
        return initSo(str, i, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x0040 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean initSo(java.lang.String r7, int r8, java.lang.ClassLoader r9) throws java.lang.IllegalAccessException, java.lang.NoSuchMethodException, java.lang.SecurityException, java.lang.IllegalArgumentException, java.lang.reflect.InvocationTargetException {
        /*
            r0 = 1
            r1 = 0
            if (r9 != 0) goto Le
            java.lang.System.loadLibrary(r7)     // Catch: java.lang.Error -> L8 java.lang.UnsatisfiedLinkError -> La java.lang.Exception -> Lc
            goto L3e
        L8:
            r0 = move-exception
            goto L32
        La:
            r0 = move-exception
            goto L36
        Lc:
            r0 = move-exception
            goto L3a
        Le:
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Error -> L8 java.lang.UnsatisfiedLinkError -> La java.lang.Exception -> Lc
            r3 = 2
            java.lang.Class[] r4 = new java.lang.Class[r3]     // Catch: java.lang.Error -> L8 java.lang.UnsatisfiedLinkError -> La java.lang.Exception -> Lc
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            r4[r1] = r5     // Catch: java.lang.Error -> L8 java.lang.UnsatisfiedLinkError -> La java.lang.Exception -> Lc
            java.lang.Class<java.lang.ClassLoader> r5 = java.lang.ClassLoader.class
            r4[r0] = r5     // Catch: java.lang.Error -> L8 java.lang.UnsatisfiedLinkError -> La java.lang.Exception -> Lc
            java.lang.Class<java.lang.Runtime> r5 = java.lang.Runtime.class
            java.lang.String r6 = "loadLibrary"
            java.lang.reflect.Method r4 = r5.getDeclaredMethod(r6, r4)     // Catch: java.lang.Error -> L8 java.lang.UnsatisfiedLinkError -> La java.lang.Exception -> Lc
            r4.setAccessible(r0)     // Catch: java.lang.Error -> L8 java.lang.UnsatisfiedLinkError -> La java.lang.Exception -> Lc
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Error -> L8 java.lang.UnsatisfiedLinkError -> La java.lang.Exception -> Lc
            r3[r1] = r7     // Catch: java.lang.Error -> L8 java.lang.UnsatisfiedLinkError -> La java.lang.Exception -> Lc
            r3[r0] = r9     // Catch: java.lang.Error -> L8 java.lang.UnsatisfiedLinkError -> La java.lang.Exception -> Lc
            r4.invoke(r2, r3)     // Catch: java.lang.Error -> L8 java.lang.UnsatisfiedLinkError -> La java.lang.Exception -> Lc
            goto L3e
        L32:
            r0.printStackTrace()
            goto L3d
        L36:
            r0.printStackTrace()
            goto L3d
        L3a:
            r0.printStackTrace()
        L3d:
            r0 = 0
        L3e:
            if (r0 != 0) goto L84
            boolean r2 = isExist(r7, r8)     // Catch: java.lang.Error -> L75 java.lang.UnsatisfiedLinkError -> L7a java.lang.Exception -> L7f
            if (r2 == 0) goto L50
            boolean r2 = _loadUnzipSo(r7, r8, r9)     // Catch: java.lang.Error -> L75 java.lang.UnsatisfiedLinkError -> L7a java.lang.Exception -> L7f
            if (r2 == 0) goto L4d
            return r2
        L4d:
            removeSoIfExit(r7, r8)     // Catch: java.lang.Error -> L75 java.lang.UnsatisfiedLinkError -> L7a java.lang.Exception -> L7f
        L50:
            java.lang.String r2 = _cpuType()     // Catch: java.lang.Error -> L75 java.lang.UnsatisfiedLinkError -> L7a java.lang.Exception -> L7f
            java.lang.String r3 = "mips"
            boolean r3 = r2.equalsIgnoreCase(r3)     // Catch: java.lang.Error -> L75 java.lang.UnsatisfiedLinkError -> L7a java.lang.Exception -> L7f
            if (r3 != 0) goto L84
            java.lang.String r3 = "x86"
            boolean r2 = r2.equalsIgnoreCase(r3)     // Catch: java.lang.Error -> L75 java.lang.UnsatisfiedLinkError -> L7a java.lang.Exception -> L7f
            if (r2 == 0) goto L65
            goto L84
        L65:
            boolean r7 = unZipSelectedFiles(r7, r8, r9)     // Catch: java.io.IOException -> L6b java.util.zip.ZipException -> L70 java.lang.Error -> L75 java.lang.UnsatisfiedLinkError -> L7a java.lang.Exception -> L7f
            r1 = r7
            goto L85
        L6b:
            r7 = move-exception
            r7.printStackTrace()     // Catch: java.lang.Error -> L75 java.lang.UnsatisfiedLinkError -> L7a java.lang.Exception -> L7f
            goto L84
        L70:
            r7 = move-exception
            r7.printStackTrace()     // Catch: java.lang.Error -> L75 java.lang.UnsatisfiedLinkError -> L7a java.lang.Exception -> L7f
            goto L84
        L75:
            r7 = move-exception
            r7.printStackTrace()
            goto L85
        L7a:
            r7 = move-exception
            r7.printStackTrace()
            goto L85
        L7f:
            r7 = move-exception
            r7.printStackTrace()
            goto L85
        L84:
            r1 = r0
        L85:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.android.spdy.SoInstallMgrSdk.initSo(java.lang.String, int, java.lang.ClassLoader):boolean");
    }

    private static String _getFieldReflectively(Build build, String str) {
        try {
            return Build.class.getField(str).get(build).toString();
        } catch (Exception unused) {
            return "Unknown";
        }
    }

    private static String _cpuType() {
        String str_getFieldReflectively = _getFieldReflectively(new Build(), "CPU_ABI");
        if (str_getFieldReflectively == null || str_getFieldReflectively.length() == 0 || str_getFieldReflectively.equals("Unknown")) {
            str_getFieldReflectively = ARMEABI;
        }
        return str_getFieldReflectively.toLowerCase();
    }

    static String _targetSoFile(String str, int i) {
        Context context = mContext;
        if (context == null) {
            return "";
        }
        String path = "/data/data/" + context.getPackageName() + "/files";
        File filesDir = context.getFilesDir();
        if (filesDir != null) {
            path = filesDir.getPath();
        }
        return path + "/lib" + str + "bk" + i + ".so";
    }

    static void removeSoIfExit(String str, int i) {
        File file = new File(_targetSoFile(str, i));
        if (file.exists()) {
            file.delete();
        }
    }

    static boolean isExist(String str, int i) {
        return new File(_targetSoFile(str, i)).exists();
    }

    static boolean _loadUnzipSo(String str, int i, ClassLoader classLoader) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        try {
            if (isExist(str, i)) {
                if (classLoader == null) {
                    System.load(_targetSoFile(str, i));
                } else {
                    Runtime runtime = Runtime.getRuntime();
                    Method declaredMethod = Runtime.class.getDeclaredMethod("load", String.class, ClassLoader.class);
                    declaredMethod.setAccessible(true);
                    declaredMethod.invoke(runtime, _targetSoFile(str, i), classLoader);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
            return false;
        } catch (Error e3) {
            e3.printStackTrace();
            return false;
        }
    }

    static boolean unZipSelectedFiles(String str, int i, ClassLoader classLoader) throws Throwable {
        Context context;
        InputStream inputStream;
        FileOutputStream fileOutputStreamOpenFileOutput;
        FileChannel channel;
        String str2 = "lib/armeabi/lib" + str + ".so";
        try {
            context = mContext;
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (context == null) {
            return false;
        }
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        ZipFile zipFile = new ZipFile(applicationInfo != null ? applicationInfo.sourceDir : "");
        Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
        while (enumerationEntries.hasMoreElements()) {
            ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
            String name = zipEntryNextElement.getName();
            if (!name.contains("..") && !name.contains("\\") && !name.contains("%")) {
                if (zipEntryNextElement.getName().startsWith(str2)) {
                    FileChannel fileChannel = null;
                    try {
                        removeSoIfExit(str, i);
                        inputStream = zipFile.getInputStream(zipEntryNextElement);
                        try {
                            fileOutputStreamOpenFileOutput = context.openFileOutput("lib" + str + "bk" + i + ".so", 0);
                            try {
                                channel = fileOutputStreamOpenFileOutput.getChannel();
                            } catch (Throwable th) {
                                th = th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            fileOutputStreamOpenFileOutput = null;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        inputStream = null;
                        fileOutputStreamOpenFileOutput = null;
                    }
                    try {
                        byte[] bArr = new byte[1024];
                        int i2 = 0;
                        while (true) {
                            int i3 = inputStream.read(bArr);
                            if (i3 <= 0) {
                                break;
                            }
                            channel.write(ByteBuffer.wrap(bArr, 0, i3));
                            i2 += i3;
                        }
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                        if (channel != null) {
                            try {
                                channel.close();
                            } catch (Exception e3) {
                                e3.printStackTrace();
                            }
                        }
                        if (fileOutputStreamOpenFileOutput != null) {
                            try {
                                fileOutputStreamOpenFileOutput.close();
                            } catch (Exception e4) {
                                e4.printStackTrace();
                            }
                        }
                        zipFile.close();
                        if (i2 > 0) {
                            return _loadUnzipSo(str, i, classLoader);
                        }
                        return false;
                    } catch (Throwable th4) {
                        th = th4;
                        fileChannel = channel;
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Exception e5) {
                                e5.printStackTrace();
                            }
                        }
                        if (fileChannel != null) {
                            try {
                                fileChannel.close();
                            } catch (Exception e6) {
                                e6.printStackTrace();
                            }
                        }
                        if (fileOutputStreamOpenFileOutput != null) {
                            try {
                                fileOutputStreamOpenFileOutput.close();
                            } catch (Exception e7) {
                                e7.printStackTrace();
                            }
                        }
                        zipFile.close();
                        throw th;
                    }
                }
            }
            return false;
        }
        return false;
    }
}
