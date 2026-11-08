package com.kwad.sdk.utils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class AbiUtil {
    private static Abi a;

    public enum Abi {
        UNKNOWN,
        ARMEABI_V7A,
        ARM64_V8A
    }

    public static String a() {
        return b() ? "arm64-v8a" : "armeabi-v7a";
    }

    public static boolean b() {
        return c() == Abi.ARM64_V8A;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x000d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static com.kwad.sdk.utils.AbiUtil.Abi c() {
        /*
            com.kwad.sdk.utils.AbiUtil$Abi r0 = com.kwad.sdk.utils.AbiUtil.a
            if (r0 == 0) goto L7
            com.kwad.sdk.utils.AbiUtil$Abi r0 = com.kwad.sdk.utils.AbiUtil.a
            return r0
        L7:
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 21
            if (r0 >= r1) goto L12
        Ld:
            com.kwad.sdk.utils.AbiUtil$Abi r0 = com.kwad.sdk.utils.AbiUtil.Abi.ARMEABI_V7A
        Lf:
            com.kwad.sdk.utils.AbiUtil.a = r0
            goto L74
        L12:
            int r0 = android.os.Build.VERSION.SDK_INT
            r2 = 23
            if (r0 < r2) goto L21
            boolean r0 = android.os.Process.is64Bit()
            if (r0 == 0) goto Ld
            com.kwad.sdk.utils.AbiUtil$Abi r0 = com.kwad.sdk.utils.AbiUtil.Abi.ARM64_V8A
            goto Lf
        L21:
            int r0 = android.os.Build.VERSION.SDK_INT
            if (r0 < r1) goto L74
            java.lang.String r0 = "dalvik.system.VMRuntime"
            com.kwad.sdk.api.loader.Reflect r0 = com.kwad.sdk.api.loader.Reflect.a(r0)     // Catch: java.lang.Throwable -> L4b
            java.lang.String r1 = "getRuntime"
            com.kwad.sdk.api.loader.Reflect r0 = r0.d(r1)     // Catch: java.lang.Throwable -> L4b
            java.lang.String r1 = "is64Bit"
            com.kwad.sdk.api.loader.Reflect r0 = r0.d(r1)     // Catch: java.lang.Throwable -> L4b
            java.lang.Object r0 = r0.a()     // Catch: java.lang.Throwable -> L4b
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch: java.lang.Throwable -> L4b
            boolean r0 = r0.booleanValue()     // Catch: java.lang.Throwable -> L4b
            if (r0 == 0) goto L46
            com.kwad.sdk.utils.AbiUtil$Abi r0 = com.kwad.sdk.utils.AbiUtil.Abi.ARM64_V8A     // Catch: java.lang.Throwable -> L4b
            goto L48
        L46:
            com.kwad.sdk.utils.AbiUtil$Abi r0 = com.kwad.sdk.utils.AbiUtil.Abi.ARMEABI_V7A     // Catch: java.lang.Throwable -> L4b
        L48:
            com.kwad.sdk.utils.AbiUtil.a = r0     // Catch: java.lang.Throwable -> L4b
            goto L74
        L4b:
            r0 = move-exception
            r0.printStackTrace()
            com.kwad.sdk.KsAdSDKImpl r0 = com.kwad.sdk.KsAdSDKImpl.get()     // Catch: java.lang.Throwable -> L6d
            android.content.Context r0 = r0.getContext()     // Catch: java.lang.Throwable -> L6d
            android.content.pm.ApplicationInfo r0 = r0.getApplicationInfo()     // Catch: java.lang.Throwable -> L6d
            java.lang.String r0 = r0.nativeLibraryDir     // Catch: java.lang.Throwable -> L6d
            java.lang.String r1 = "arm64"
            boolean r0 = r0.contains(r1)     // Catch: java.lang.Throwable -> L6d
            if (r0 == 0) goto L6a
            com.kwad.sdk.utils.AbiUtil$Abi r0 = com.kwad.sdk.utils.AbiUtil.Abi.ARM64_V8A     // Catch: java.lang.Throwable -> L6d
        L67:
            com.kwad.sdk.utils.AbiUtil.a = r0     // Catch: java.lang.Throwable -> L6d
            goto L74
        L6a:
            com.kwad.sdk.utils.AbiUtil$Abi r0 = com.kwad.sdk.utils.AbiUtil.Abi.UNKNOWN     // Catch: java.lang.Throwable -> L6d
            goto L67
        L6d:
            r0 = move-exception
            r0.printStackTrace()
            com.kwad.sdk.utils.AbiUtil$Abi r0 = com.kwad.sdk.utils.AbiUtil.Abi.UNKNOWN
            goto Lf
        L74:
            com.kwad.sdk.utils.AbiUtil$Abi r0 = com.kwad.sdk.utils.AbiUtil.a
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.utils.AbiUtil.c():com.kwad.sdk.utils.AbiUtil$Abi");
    }
}
