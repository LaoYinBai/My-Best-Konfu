package com.bytedance.pangle.e.a;

import android.content.pm.PackageInfo;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.log.ZeusLogger;
import java.io.File;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class d {
    private static String a(int i) {
        return (i >>> 24) == 1 ? "android:" : "";
    }

    public static e a(File file) throws Throwable {
        ZipFile zipFile;
        a aVar;
        a aVar2;
        Throwable th;
        int next;
        int i;
        try {
            try {
                if (!file.exists()) {
                    ZeusLogger.e(ZeusLogger.TAG_INSTALL, file.getAbsolutePath() + " not exists!");
                    com.bytedance.pangle.util.f.a(null);
                    return null;
                }
                zipFile = new ZipFile(file);
                try {
                    ZipEntry entry = zipFile.getEntry("AndroidManifest.xml");
                    if (entry == null) {
                        ZeusLogger.e(ZeusLogger.TAG_INSTALL, "没有找到AndroidManifest.xml entry");
                        com.bytedance.pangle.util.f.a(zipFile);
                        return null;
                    }
                    aVar2 = new a();
                    try {
                        InputStream inputStream = zipFile.getInputStream(entry);
                        aVar2.close();
                        if (inputStream != null) {
                            aVar2.f4586a = new c(inputStream);
                        }
                        do {
                            next = aVar2.next();
                            if (next == 1) {
                                ZeusLogger.e(ZeusLogger.TAG_INSTALL, "已达到END_DOCUMENT");
                                try {
                                    aVar2.close();
                                } catch (Throwable unused) {
                                }
                                com.bytedance.pangle.util.f.a(zipFile);
                                return null;
                            }
                        } while (next != 2);
                        int attributeCount = aVar2.getAttributeCount();
                        String strA = null;
                        String strA2 = null;
                        for (int i2 = 0; i2 != attributeCount; i2++) {
                            if ("versionCode".equals(aVar2.getAttributeName(i2))) {
                                strA = a(aVar2, i2);
                            } else if ("package".equals(aVar2.getAttributeName(i2))) {
                                strA2 = a(aVar2, i2);
                            }
                        }
                        try {
                            i = Integer.parseInt(strA);
                        } catch (Throwable unused2) {
                            i = -1;
                        }
                        if (i == -1) {
                            ZeusLogger.e(ZeusLogger.TAG_INSTALL, "versionCode获取失败:".concat(String.valueOf(strA)));
                            try {
                                aVar2.close();
                            } catch (Throwable unused3) {
                            }
                            com.bytedance.pangle.util.f.a(zipFile);
                            return null;
                        }
                        e eVar = new e(strA2, i);
                        try {
                            aVar2.close();
                        } catch (Throwable unused4) {
                        }
                        com.bytedance.pangle.util.f.a(zipFile);
                        return eVar;
                    } catch (Throwable th2) {
                        th = th2;
                        ZeusLogger.e(ZeusLogger.TAG_INSTALL, "getSimplePackageInfo failed", th);
                        PackageInfo packageArchiveInfo = Zeus.getAppApplication().getPackageManager().getPackageArchiveInfo(file.getPath(), 0);
                        if (packageArchiveInfo == null) {
                            if (aVar2 != null) {
                                try {
                                    aVar2.close();
                                } catch (Throwable unused5) {
                                }
                            }
                            com.bytedance.pangle.util.f.a(zipFile);
                            return null;
                        }
                        e eVar2 = new e(packageArchiveInfo.packageName, packageArchiveInfo.versionCode);
                        if (aVar2 != null) {
                            try {
                                aVar2.close();
                            } catch (Throwable unused6) {
                            }
                        }
                        com.bytedance.pangle.util.f.a(zipFile);
                        return eVar2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    aVar2 = null;
                }
            } catch (Throwable th4) {
                th = th4;
            }
        } catch (Throwable th5) {
            th = th5;
            zipFile = null;
            aVar = null;
        }
    }

    private static String a(a aVar, int i) {
        int iA = aVar.a(i);
        int iB = aVar.b(i);
        if (iA == 3) {
            return aVar.getAttributeValue(i);
        }
        return iA == 2 ? String.format("?%s%08X", a(iB), Integer.valueOf(iB)) : (iA < 16 || iA > 31) ? String.format("<0x%X, type 0x%02X>", Integer.valueOf(iB), Integer.valueOf(iA)) : String.valueOf(iB);
    }
}
