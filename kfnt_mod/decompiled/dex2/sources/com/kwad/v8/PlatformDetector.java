package com.kwad.v8;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class PlatformDetector {

    public static class Arch {
        public static String getName() {
            String property = System.getProperty("os.arch");
            String strNormalizeArch = PlatformDetector.normalizeArch(property);
            if (!strNormalizeArch.equals(Platform.UNKNOWN)) {
                return strNormalizeArch;
            }
            throw new UnsatisfiedLinkError("Unsupported arch: " + property);
        }
    }

    public static class OS {
        public static String getLibFileExtension() {
            if (isWindows()) {
                return "dll";
            }
            if (isMac()) {
                return "dylib";
            }
            if (isLinux() || isAndroid() || isNativeClient()) {
                return "so";
            }
            throw new UnsatisfiedLinkError("Unsupported platform library-extension for: " + getName());
        }

        public static String getName() {
            String property = System.getProperty("os.name");
            String strNormalizeOs = PlatformDetector.normalizeOs(property);
            String property2 = System.getProperty("java.specification.vendor");
            if (PlatformDetector.normalize(property2).contains(Platform.ANDROID) || strNormalizeOs.contains(Platform.ANDROID)) {
                return Platform.ANDROID;
            }
            if (!strNormalizeOs.equals(Platform.UNKNOWN)) {
                return strNormalizeOs;
            }
            throw new UnsatisfiedLinkError("Unsupported platform/vendor: " + property + " / " + property2);
        }

        public static boolean isAndroid() {
            return getName().equals(Platform.ANDROID);
        }

        public static boolean isLinux() {
            return getName().equals(Platform.LINUX);
        }

        public static boolean isMac() {
            return getName().equals(Platform.MACOSX);
        }

        public static boolean isNativeClient() {
            return getName().equals(Platform.NATIVE_CLIENT);
        }

        public static boolean isWindows() {
            return getName().equals(Platform.WINDOWS);
        }
    }

    public static class Vendor {
        private static final String LINUX_ID_PREFIX = "ID=";
        private static final String[] LINUX_OS_RELEASE_FILES = {"/etc/os-release", "/usr/lib/os-release"};
        private static final String REDHAT_RELEASE_FILE = "/etc/redhat-release";

        private static void closeQuietly(Closeable closeable) throws IOException {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException unused) {
                }
            }
        }

        private static String getLinuxOsReleaseId() {
            for (String str : LINUX_OS_RELEASE_FILES) {
                File file = new File(str);
                if (file.exists()) {
                    return parseLinuxOsReleaseFile(file);
                }
            }
            File file2 = new File(REDHAT_RELEASE_FILE);
            if (file2.exists()) {
                return parseLinuxRedhatReleaseFile(file2);
            }
            throw new UnsatisfiedLinkError("Unsupported linux vendor: " + getName());
        }

        public static String getName() {
            if (OS.isWindows()) {
                return "microsoft";
            }
            if (OS.isMac()) {
                return "apple";
            }
            if (OS.isLinux()) {
                return getLinuxOsReleaseId();
            }
            if (OS.isAndroid()) {
                return "google";
            }
            throw new UnsatisfiedLinkError("Unsupported vendor: " + getName());
        }

        /* JADX WARN: Code restructure failed: missing block: B:8:0x0020, code lost:
        
            r4 = com.kwad.v8.PlatformDetector.normalizeOsReleaseValue(r4.substring(com.kwad.v8.PlatformDetector.Vendor.LINUX_ID_PREFIX.length()));
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private static java.lang.String parseLinuxOsReleaseFile(java.io.File r4) throws java.lang.Throwable {
            /*
                r0 = 0
                java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3c
                java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3c
                java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3c
                r3.<init>(r4)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3c
                java.lang.String r4 = "utf-8"
                r2.<init>(r3, r4)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3c
                r1.<init>(r2)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3c
            L12:
                java.lang.String r4 = r1.readLine()     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L3d
                if (r4 == 0) goto L2f
                java.lang.String r2 = "ID="
                boolean r2 = r4.startsWith(r2)     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L3d
                if (r2 == 0) goto L12
                java.lang.String r2 = "ID="
                int r2 = r2.length()     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L3d
                java.lang.String r4 = r4.substring(r2)     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L3d
                java.lang.String r4 = com.kwad.v8.PlatformDetector.access$300(r4)     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L3d
                goto L30
            L2f:
                r4 = r0
            L30:
                closeQuietly(r1)
                return r4
            L34:
                r4 = move-exception
                r0 = r1
                goto L38
            L37:
                r4 = move-exception
            L38:
                closeQuietly(r0)
                throw r4
            L3c:
                r1 = r0
            L3d:
                closeQuietly(r1)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.kwad.v8.PlatformDetector.Vendor.parseLinuxOsReleaseFile(java.io.File):java.lang.String");
        }

        private static String parseLinuxRedhatReleaseFile(File file) throws Throwable {
            BufferedReader bufferedReader;
            String str;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
                try {
                    String line = bufferedReader.readLine();
                    if (line != null) {
                        String lowerCase = line.toLowerCase(Locale.US);
                        if (lowerCase.contains("centos")) {
                            str = "centos";
                        } else if (lowerCase.contains("fedora")) {
                            str = "fedora";
                        } else {
                            if (!lowerCase.contains("red hat enterprise linux")) {
                                closeQuietly(bufferedReader);
                                return null;
                            }
                            str = "rhel";
                        }
                        closeQuietly(bufferedReader);
                        return str;
                    }
                } catch (IOException unused) {
                } catch (Throwable th) {
                    th = th;
                    closeQuietly(bufferedReader);
                    throw th;
                }
            } catch (IOException unused2) {
                bufferedReader = null;
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
            }
            closeQuietly(bufferedReader);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String normalize(String str) {
        return str == null ? "" : str.toLowerCase(Locale.US).replaceAll("[^a-z0-9]+", "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String normalizeArch(String str) {
        String strNormalize = normalize(str);
        return strNormalize.matches("^(x8664|amd64|ia32e|em64t|x64)$") ? "x86_64" : strNormalize.matches("^(x8632|x86|i[3-6]86|ia32|x32)$") ? "x86_32" : strNormalize.matches("^(ia64|itanium64)$") ? "itanium_64" : strNormalize.matches("^(sparc|sparc32)$") ? "sparc_32" : strNormalize.matches("^(sparcv9|sparc64)$") ? "sparc_64" : (strNormalize.matches("^(arm|arm32)$") || strNormalize.startsWith("armv7")) ? "arm_32" : ("aarch64".equals(strNormalize) || strNormalize.startsWith("armv8")) ? "aarch_64" : strNormalize.matches("^(ppc|ppc32)$") ? "ppc_32" : "ppc64".equals(strNormalize) ? "ppc_64" : "ppc64le".equals(strNormalize) ? "ppcle_64" : "s390".equals(strNormalize) ? "s390_32" : "s390x".equals(strNormalize) ? "s390_64" : Platform.UNKNOWN;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String normalizeOs(String str) {
        String strNormalize = normalize(str);
        return strNormalize.startsWith("aix") ? "aix" : strNormalize.startsWith("hpux") ? "hpux" : (!strNormalize.startsWith("os400") || (strNormalize.length() > 5 && Character.isDigit(strNormalize.charAt(5)))) ? strNormalize.startsWith(Platform.ANDROID) ? Platform.ANDROID : strNormalize.startsWith(Platform.LINUX) ? Platform.LINUX : strNormalize.startsWith(Platform.NATIVE_CLIENT) ? Platform.NATIVE_CLIENT : (strNormalize.startsWith(Platform.MACOSX) || strNormalize.startsWith("osx")) ? Platform.MACOSX : strNormalize.startsWith("freebsd") ? "freebsd" : strNormalize.startsWith("openbsd") ? "openbsd" : strNormalize.startsWith("netbsd") ? "netbsd" : (strNormalize.startsWith("solaris") || strNormalize.startsWith("sunos")) ? "sunos" : strNormalize.startsWith(Platform.WINDOWS) ? Platform.WINDOWS : Platform.UNKNOWN : "os400";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String normalizeOsReleaseValue(String str) {
        return str.trim().replace("\"", "");
    }
}
