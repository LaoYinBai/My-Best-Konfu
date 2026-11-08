package com.kwad.v8;

import com.kwad.v8.PlatformDetector;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class LibraryLoader {
    private static final String KWAD_PREFIX = "kwad-";
    static final String SWT_LIB_DIR = ".j2v8";
    static final String DELIMITER = System.getProperty("line.separator");
    static final String SEPARATOR = System.getProperty("file.separator");

    LibraryLoader() {
    }

    static void chmod(String str, String str2) {
        if (PlatformDetector.OS.isWindows()) {
            return;
        }
        try {
            Runtime.getRuntime().exec(new String[]{"chmod", str, str2}).waitFor();
        } catch (Throwable unused) {
        }
    }

    public static String computeLibraryFullName(boolean z) {
        return "lib" + computeLibraryShortName(z) + "." + PlatformDetector.OS.getLibFileExtension();
    }

    public static String computeLibraryShortName(boolean z) {
        String str;
        String name = (z && PlatformDetector.OS.isLinux()) ? PlatformDetector.Vendor.getName() : null;
        String name2 = PlatformDetector.OS.getName();
        String name3 = PlatformDetector.Arch.getName();
        StringBuilder sb = new StringBuilder();
        sb.append("kwad-j2v8");
        if (name != null) {
            str = "-" + name;
        } else {
            str = "";
        }
        sb.append(str);
        sb.append("-");
        sb.append(name2);
        sb.append("-");
        sb.append(name3);
        return sb.toString();
    }

    static boolean extract(String str, String str2, StringBuffer stringBuffer) throws IOException {
        InputStream resourceAsStream;
        File file = new File(str);
        boolean z = true;
        FileOutputStream fileOutputStream = null;
        try {
            if (file.exists()) {
                file.delete();
            }
            resourceAsStream = LibraryLoader.class.getResourceAsStream("/" + str2);
            if (resourceAsStream != null) {
                try {
                    byte[] bArr = new byte[4096];
                    FileOutputStream fileOutputStream2 = new FileOutputStream(str);
                    while (true) {
                        try {
                            int i = resourceAsStream.read(bArr);
                            if (i == -1) {
                                break;
                            }
                            fileOutputStream2.write(bArr, 0, i);
                        } catch (Throwable unused) {
                            fileOutputStream = fileOutputStream2;
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException unused2) {
                                }
                            }
                            if (resourceAsStream != null) {
                                try {
                                    resourceAsStream.close();
                                } catch (IOException unused3) {
                                }
                            }
                            if (z && file.exists()) {
                                file.delete();
                            }
                            return false;
                        }
                    }
                    fileOutputStream2.close();
                    resourceAsStream.close();
                    chmod("755", str);
                    if (load(str, stringBuffer)) {
                        return true;
                    }
                } catch (Throwable unused4) {
                }
            }
        } catch (Throwable unused5) {
            resourceAsStream = null;
            z = false;
        }
        return false;
    }

    static boolean extract(String str, boolean z, StringBuffer stringBuffer) {
        String strComputeLibraryFullName = computeLibraryFullName(z);
        return extract(str + SEPARATOR + strComputeLibraryFullName, strComputeLibraryFullName, stringBuffer);
    }

    static boolean load(String str, StringBuffer stringBuffer) {
        try {
            if (str.indexOf(SEPARATOR) != -1) {
                System.load(str);
                return true;
            }
            System.loadLibrary(str);
            return true;
        } catch (UnsatisfiedLinkError e) {
            if (stringBuffer.length() == 0) {
                stringBuffer.append(DELIMITER);
            }
            stringBuffer.append('\t');
            stringBuffer.append(e.getMessage());
            stringBuffer.append(DELIMITER);
            return false;
        }
    }

    static void loadLibrary(String str) {
        if (PlatformDetector.OS.isAndroid()) {
            System.loadLibrary("kwad-j2v8");
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        if (tryLoad(true, stringBuffer) || tryLoad(false, stringBuffer)) {
            return;
        }
        if (str == null) {
            str = System.getProperty("java.io.tmpdir");
        }
        if (extract(str, true, stringBuffer) || extract(str, false, stringBuffer)) {
            return;
        }
        throw new UnsatisfiedLinkError("Could not load J2V8 library. Reasons: " + stringBuffer.toString());
    }

    static boolean tryLoad(boolean z, StringBuffer stringBuffer) {
        String strComputeLibraryShortName = computeLibraryShortName(z);
        String strComputeLibraryFullName = computeLibraryFullName(z);
        String str = System.getProperty("user.dir") + SEPARATOR + "jni" + SEPARATOR + strComputeLibraryFullName;
        if (load(strComputeLibraryFullName, stringBuffer) || load(strComputeLibraryShortName, stringBuffer)) {
            return true;
        }
        return new File(str).exists() && load(str, stringBuffer);
    }
}
