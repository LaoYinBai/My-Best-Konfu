package com.bytedance.pangle.plugin;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.alipay.sdk.util.f;
import com.appsflyer.share.Constants;
import com.bytedance.pangle.ApkVerifier;
import com.bytedance.pangle.PluginClassLoader;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.ZeusParam;
import com.bytedance.pangle.b.b;
import com.bytedance.pangle.d.b;
import com.bytedance.pangle.g;
import com.bytedance.pangle.helper.PluginDirHelper;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.util.e;
import dalvik.system.DexFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class b {
    b() {
    }

    static boolean a(File file, String str, int i) throws Throwable {
        StringBuilder sb = new StringBuilder();
        byte b2 = 0;
        try {
            try {
                com.bytedance.pangle.log.c cVarA = com.bytedance.pangle.log.c.a(ZeusLogger.TAG_INSTALL, "PluginInstaller", "install:".concat(String.valueOf(str)));
                a(com.bytedance.pangle.b.b.e, b.a.l, str, i, -1L, null, null);
                e.a(PluginDirHelper.getPackageVersionDir(str, i));
                long jA = cVarA.a("cleanDir");
                if (jA > 30 || jA < 0) {
                    sb.append("cleanDir cost:");
                    sb.append(jA);
                    sb.append(f.f2657b);
                }
                try {
                    ApkVerifier verifier = g.a().f4632b.getVerifier();
                    if (verifier != null) {
                        if (!verifier.verify(file)) {
                            throw new RuntimeException("安装包签名校验失败[0]");
                        }
                    } else if (!com.bytedance.pangle.f.e.a(file.getAbsolutePath())) {
                        throw new RuntimeException("安装包签名校验失败[1]");
                    }
                    long jA2 = cVarA.a("checkSignature");
                    if (jA2 > 30 || jA2 < 0) {
                        sb.append("checkSignature cost:");
                        sb.append(jA2);
                        sb.append(f.f2657b);
                    }
                    ZeusParam zeusParam = g.a().f4632b;
                    if (zeusParam != null && zeusParam.checkMatchHostAbi()) {
                        try {
                            if (!com.bytedance.pangle.helper.b.a(file)) {
                                throw new a("插件包包含so不符合宿主ABI类型", b2);
                            }
                        } catch (Exception e) {
                            a(com.bytedance.pangle.b.b.f, b.a.u, str, i, -1L, null, e);
                            throw new a("插件包包含so不符合宿主ABI类型", e, b2);
                        }
                    }
                    long jA3 = cVarA.a("checkMathHostAbi");
                    if (jA3 > 30 || jA3 < 0) {
                        sb.append("checkMathHostAbi cost:");
                        sb.append(jA3);
                        sb.append(f.f2657b);
                    }
                    b(file, str, i);
                    long jA4 = cVarA.a("checkPermissions");
                    if (jA4 > 30 || jA4 < 0) {
                        sb.append("checkPermissions cost:");
                        sb.append(jA4);
                        sb.append(f.f2657b);
                    }
                    String sourceFile = PluginDirHelper.getSourceFile(str, i);
                    try {
                        String absolutePath = file.getAbsolutePath();
                        if (!TextUtils.isEmpty(absolutePath) && !TextUtils.isEmpty(sourceFile)) {
                            File file2 = new File(absolutePath);
                            File file3 = new File(sourceFile);
                            if (file2.exists() && file2.isFile() && file2.canRead()) {
                                if (file3.getParentFile() != null && !file3.getParentFile().exists()) {
                                    file3.getParentFile().mkdirs();
                                }
                                try {
                                    com.bytedance.pangle.util.f.a(new FileInputStream(absolutePath), new FileOutputStream(sourceFile), file2.length());
                                } catch (IOException e2) {
                                    if (file3.exists()) {
                                        file3.delete();
                                        throw e2;
                                    }
                                    throw e2;
                                }
                            }
                        }
                        long jA5 = cVarA.a("copyApk");
                        if (jA5 > 30 || jA5 < 0) {
                            sb.append("copyApk cost:");
                            sb.append(jA5);
                            sb.append(f.f2657b);
                        }
                        try {
                            com.bytedance.pangle.helper.b.a(new File(PluginDirHelper.getSourceFile(str, i)), new File(PluginDirHelper.getNativeLibraryDir(str, i)), str);
                            long jA6 = cVarA.a("copySo");
                            if (jA6 > 30 || jA6 < 0) {
                                sb.append("copySo cost:");
                                sb.append(jA6);
                                sb.append(f.f2657b);
                            }
                            try {
                                String dalvikCacheDir = PluginDirHelper.getDalvikCacheDir(str, i);
                                String nativeLibraryDir = PluginDirHelper.getNativeLibraryDir(str, i);
                                String absolutePath2 = file.getAbsolutePath();
                                if (Build.VERSION.SDK_INT < 26) {
                                    try {
                                        if (com.bytedance.pangle.helper.c.b(Zeus.getAppApplication())) {
                                            DexFile.loadDex(absolutePath2, null, 0).close();
                                        }
                                    } catch (Exception e3) {
                                        e3.printStackTrace();
                                    }
                                }
                                String sourceFile2 = PluginDirHelper.getSourceFile(str, i);
                                try {
                                    if (g.a().f4632b.fastDex2Oat()) {
                                        int i2 = Build.VERSION.SDK_INT;
                                        if (i2 >= 21 && i2 < 26) {
                                            StringBuilder sb2 = new StringBuilder();
                                            sb2.append(dalvikCacheDir);
                                            sb2.append(File.separator);
                                            String strSubstring = sourceFile2.substring(sourceFile2.lastIndexOf(Constants.URL_PATH_DELIMITER) + 1);
                                            String strSubstring2 = strSubstring.substring(strSubstring.lastIndexOf("."));
                                            String str2 = Build.VERSION.SDK_INT >= 26 ? ".odex" : ".dex";
                                            if (!".dex".equals(strSubstring2)) {
                                                if (".zip".equals(strSubstring2) || ".apk".equals(strSubstring2)) {
                                                    strSubstring = strSubstring.replace(strSubstring2, str2);
                                                } else {
                                                    strSubstring = strSubstring + str2;
                                                }
                                            }
                                            sb2.append(strSubstring);
                                            String string = sb2.toString();
                                            File file4 = new File(string);
                                            if (!file4.exists() || !com.bytedance.pangle.d.c.a(file4)) {
                                                int i3 = b.a.f4547b;
                                                ArrayList arrayList = new ArrayList();
                                                arrayList.add("dex2oat");
                                                if (Build.VERSION.SDK_INT >= 24) {
                                                    arrayList.add("--runtime-arg");
                                                    arrayList.add("-classpath");
                                                    arrayList.add("--runtime-arg");
                                                    arrayList.add("&");
                                                }
                                                arrayList.add("--instruction-set=" + com.bytedance.pangle.d.b.a());
                                                if (i3 == b.a.f4546a) {
                                                    arrayList.add("--compiler-filter=verify-none");
                                                } else if (i3 == b.a.f4547b) {
                                                    arrayList.add("--compiler-filter=interpret-only");
                                                } else {
                                                    int i4 = b.a.f4548c;
                                                }
                                                arrayList.add("--dex-file=".concat(String.valueOf(sourceFile2)));
                                                arrayList.add("--oat-file=".concat(String.valueOf(string)));
                                                com.bytedance.pangle.d.a.a((String[]) arrayList.toArray(new String[arrayList.size()]));
                                            }
                                        }
                                    }
                                } catch (Throwable th) {
                                    ZeusLogger.e(ZeusLogger.TAG_INSTALL, "fastDex2oat failed.", th);
                                }
                                PluginClassLoader pluginClassLoader = new PluginClassLoader(sourceFile2, dalvikCacheDir, nativeLibraryDir, null);
                                if (!TextUtils.isEmpty("")) {
                                    pluginClassLoader.loadClass("");
                                }
                                long jA7 = cVarA.a("dexOpt");
                                if (jA7 > 30 || jA7 < 0) {
                                    sb.append("dexOpt cost:");
                                    sb.append(jA7);
                                    sb.append(f.f2657b);
                                }
                                e.a(file);
                                long jA8 = cVarA.a("cleanPluginApk");
                                if (jA8 > 30 || jA8 < 0) {
                                    sb.append("cleanPluginApk cost:");
                                    sb.append(jA8);
                                    sb.append(f.f2657b);
                                }
                                a(com.bytedance.pangle.b.b.f, b.a.m, str, i, cVarA.a(), sb.toString(), null);
                                cVarA.b("success");
                                return true;
                            } catch (Exception e4) {
                                a(com.bytedance.pangle.b.b.f, b.a.t, str, i, -1L, null, e4);
                                throw new a("dexOpt失败", e4, (byte) 0);
                            }
                        } catch (Exception e5) {
                            a(com.bytedance.pangle.b.b.f, b.a.r, str, i, -1L, null, e5);
                            throw new a("安装包动态库拷贝失败", e5, (byte) 0);
                        }
                    } catch (Exception e6) {
                        a(com.bytedance.pangle.b.b.f, b.a.q, str, i, -1L, null, e6);
                        throw new a("安装包拷贝失败", e6, (byte) 0);
                    }
                } catch (Exception e7) {
                    a(com.bytedance.pangle.b.b.f, b.a.o, str, i, -1L, null, e7);
                    throw new a(e7.getMessage(), e7, (byte) 0);
                }
            } catch (a e8) {
                ZeusLogger.e(ZeusLogger.TAG_INSTALL, "PluginInstaller " + str + " install failed.", e8);
                return false;
            }
        } catch (Exception e9) {
            ZeusLogger.e(ZeusLogger.TAG_INSTALL, "PluginInstaller " + str + " install failed unknown error.", e9);
            a(com.bytedance.pangle.b.b.f, b.a.n, str, i, -1L, sb.toString(), e9);
            return false;
        }
    }

    private static void b(File file, String str, int i) throws a, JSONException, PackageManager.NameNotFoundException {
        ZeusParam zeusParam = g.a().f4632b;
        byte b2 = 0;
        try {
            PackageInfo packageInfo = Zeus.getAppApplication().getPackageManager().getPackageInfo(Zeus.getAppApplication().getPackageName(), 4096);
            PackageInfo packageArchiveInfo = Zeus.getAppApplication().getPackageManager().getPackageArchiveInfo(file.getAbsolutePath(), 4096);
            List listAsList = Arrays.asList(packageInfo.requestedPermissions);
            if (packageArchiveInfo.requestedPermissions == null || packageArchiveInfo.requestedPermissions.length <= 0) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (String str2 : packageArchiveInfo.requestedPermissions) {
                if (!listAsList.contains(str2)) {
                    arrayList.add(str2);
                }
            }
            if (arrayList.isEmpty()) {
                return;
            }
            ZeusLogger.e("PluginInstaller", "The following permissions are declared in the plugin but not in the host: ".concat(String.valueOf(arrayList)));
            if (zeusParam != null && zeusParam.checkPermission()) {
                throw new a("The following permissions are declared in the plugin but not in the host: ".concat(String.valueOf(arrayList)), b2);
            }
        } catch (Exception e) {
            a(com.bytedance.pangle.b.b.f, b.a.p, str, i, -1L, null, e);
            throw new a("安装包权限校验失败", e, b2);
        }
    }

    private static void a(String str, int i, @NonNull String str2, int i2, long j, String str3, @Nullable Throwable th) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject.putOpt("status_code", com.bytedance.pangle.log.d.a(Integer.valueOf(i)));
            jSONObject.putOpt("plugin_package_name", com.bytedance.pangle.log.d.a(str2));
            jSONObject.putOpt("version_code", com.bytedance.pangle.log.d.a(Integer.valueOf(i2)));
            jSONObject3.putOpt("duration", com.bytedance.pangle.log.d.a(Long.valueOf(j)));
            jSONObject2.putOpt("throwable", com.bytedance.pangle.log.d.a(th));
            jSONObject2.putOpt("timestamp", com.bytedance.pangle.log.d.a(Long.valueOf(System.currentTimeMillis())));
            jSONObject2.putOpt("message", com.bytedance.pangle.log.d.a(str3));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        com.bytedance.pangle.b.b.a().a(str, jSONObject, jSONObject3, jSONObject2);
    }

    static class a extends IOException {
        /* synthetic */ a(String str, byte b2) {
            this(str);
        }

        /* synthetic */ a(String str, Throwable th, byte b2) {
            this(str, th);
        }

        private a(String str) {
            super(str);
        }

        private a(String str, Throwable th) {
            super(str, th);
        }
    }
}
