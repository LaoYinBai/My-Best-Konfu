package com.bytedance.pangle.helper;

import android.os.Build;
import android.os.Process;
import com.bun.miitmdid.core.Utils;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.util.FieldUtils;
import com.bytedance.pangle.util.g;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    private static String f4639a;

    /* renamed from: b, reason: collision with root package name */
    private static String f4640b;

    /* renamed from: c, reason: collision with root package name */
    private static Map<String, Integer> f4641c = new HashMap();

    static {
        if (g.a()) {
            f4640b = Build.SUPPORTED_ABIS[0];
        } else {
            f4640b = Build.CPU_ABI;
        }
        f4641c.put("arm64-v8a", 64);
        f4641c.put("armeabi-v7a", 32);
        f4641c.put("armeabi", 32);
        f4641c.put("x86_64", 64);
        f4641c.put(Utils.CPU_ABI_X86, 32);
        f4641c.put("mips64", 64);
        f4641c.put("mips", 32);
        f4639a = c();
    }

    public static void a(File file, File file2, String str) throws Throwable {
        ZipFile zipFile;
        HashSet hashSet;
        LinkedList linkedList = null;
        try {
            zipFile = new ZipFile(file);
            try {
                Map<String, List<ZipEntry>> mapA = a(zipFile);
                boolean zContainsKey = mapA.containsKey(f4639a);
                ZeusLogger.i(ZeusLogger.TAG_SO, "NativeLibHelper copyNativeLib pre-verify-matchHostAbi[" + zContainsKey + "], pkg=" + str);
                if (zContainsKey) {
                    if (!mapA.isEmpty()) {
                        linkedList = new LinkedList();
                        hashSet = new HashSet();
                        switch (f4639a != null ? f4639a : f4640b) {
                            case "arm64-v8a":
                                a(mapA, "arm64-v8a", linkedList, hashSet);
                                break;
                            case "armeabi-v7a":
                                a(mapA, "armeabi-v7a", linkedList, hashSet);
                                a(mapA, "armeabi", linkedList, hashSet);
                                break;
                            case "armeabi":
                                a(mapA, "armeabi", linkedList, hashSet);
                                break;
                            case "x86_64":
                                a(mapA, "x86_64", linkedList, hashSet);
                                if (linkedList.size() == 0) {
                                    a(mapA, "arm64-v8a", linkedList, hashSet);
                                    break;
                                }
                                break;
                            case "x86":
                                a(mapA, Utils.CPU_ABI_X86, linkedList, hashSet);
                                if (linkedList.size() == 0) {
                                    a(mapA, "armeabi-v7a", linkedList, hashSet);
                                    a(mapA, "armeabi", linkedList, hashSet);
                                    break;
                                }
                                break;
                            case "mips64":
                                a(mapA, "mips64", linkedList, hashSet);
                                break;
                            case "mips":
                                a(mapA, "mips", linkedList, hashSet);
                                break;
                        }
                    }
                    if (linkedList != null && !linkedList.isEmpty()) {
                        if (!file2.exists()) {
                            file2.mkdirs();
                        }
                        Iterator it = linkedList.iterator();
                        while (it.hasNext()) {
                            a(zipFile, (ZipEntry) it.next(), file2);
                        }
                    }
                    ZeusLogger.i(ZeusLogger.TAG_INSTALL, "NativeLibHelper copyNativeLib, supportedSoEntries empty, pkg=".concat(String.valueOf(str)));
                    zipFile.close();
                    return;
                }
                zipFile.close();
            } catch (Throwable th) {
                th = th;
                if (zipFile != null) {
                    zipFile.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            zipFile = null;
        }
    }

    private static Map<String, List<ZipEntry>> a(ZipFile zipFile) {
        String[] strArrSplit;
        HashMap map = new HashMap();
        Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
        Pattern patternCompile = Pattern.compile("^lib/[^/]+/lib[^/]+.so$");
        while (enumerationEntries.hasMoreElements()) {
            ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
            if (!zipEntryNextElement.isDirectory() && patternCompile.matcher(zipEntryNextElement.getName()).matches() && (strArrSplit = zipEntryNextElement.getName().split(File.separator)) != null && strArrSplit.length >= 2) {
                String str = strArrSplit[strArrSplit.length - 2];
                if (f4641c.containsKey(str)) {
                    if (map.get(str) == null) {
                        map.put(str, new LinkedList());
                    }
                    ((List) map.get(str)).add(zipEntryNextElement);
                }
            }
        }
        ZeusLogger.i(ZeusLogger.TAG_SO, "NativeLibHelper getAllSoZipEntries, zipFile=" + zipFile.getName() + ", soEntries=" + map.toString());
        return map;
    }

    private static void a(Map<String, List<ZipEntry>> map, String str, List<ZipEntry> list, Set<String> set) {
        List<ZipEntry> list2 = map.get(str);
        if (list2 == null || list2.size() == 0) {
            return;
        }
        for (ZipEntry zipEntry : list2) {
            String strSubstring = zipEntry.getName().substring(zipEntry.getName().lastIndexOf(File.separator) + 1);
            if (!set.contains(strSubstring)) {
                list.add(zipEntry);
                set.add(strSubstring);
            }
        }
    }

    private static void a(ZipFile zipFile, ZipEntry zipEntry, File file) throws IOException {
        File file2 = new File(file, zipEntry.getName().substring(zipEntry.getName().lastIndexOf(File.separator) + 1));
        int i = 0;
        boolean z = false;
        do {
            if (file2.exists()) {
                file2.delete();
            }
            try {
                ZeusLogger.i(ZeusLogger.TAG_INSTALL, "NativeLibHelper copySoZipEntry, soZipEntry=" + zipEntry + ", targetSoFile=" + file2);
                a(zipFile.getInputStream(zipEntry), new FileOutputStream(file2));
                z = true;
            } catch (IOException e) {
                if (i >= 3) {
                    throw e;
                }
                i++;
            }
        } while (!z);
    }

    private static void a(InputStream inputStream, OutputStream outputStream) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        byte[] bArr = new byte[8192];
        while (true) {
            int i = bufferedInputStream.read(bArr, 0, 8192);
            if (-1 != i) {
                bufferedOutputStream.write(bArr, 0, i);
            } else {
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                bufferedInputStream.close();
                return;
            }
        }
    }

    public static String a() throws JSONException {
        if (f4639a != null) {
            return f4639a;
        }
        String strC = c();
        f4639a = strC;
        return strC;
    }

    public static int b() throws JSONException {
        return f4641c.get(a()).intValue();
    }

    private static String c() throws JSONException {
        JSONObject jSONObjectD = d();
        String strA = a(jSONObjectD);
        return strA == null ? b(jSONObjectD) : strA;
    }

    private static String a(JSONObject jSONObject) throws JSONException {
        if (!g.a()) {
            return null;
        }
        try {
            String str = (String) FieldUtils.readField(Zeus.getAppApplication().getApplicationInfo(), "primaryCpuAbi");
            ZeusLogger.w(ZeusLogger.TAG_SO, "NativeLibHelper inferHostAbiAuto, primaryCpuAbi=".concat(String.valueOf(str)));
            a(jSONObject, "primaryCpuAbi", str);
            if (str == null) {
                return null;
            }
            int i = 0;
            if (Build.VERSION.SDK_INT >= 23) {
                try {
                    i = Process.is64Bit() ? 64 : 32;
                    ZeusLogger.w(ZeusLogger.TAG_SO, "NativeLibHelper inferHostAbiAuto, processMode=".concat(String.valueOf(i)));
                } catch (Exception unused) {
                    ZeusLogger.w(ZeusLogger.TAG_SO, "NativeLibHelper inferHostAbiAuto, processMode exception default=".concat(String.valueOf(i)));
                }
            } else {
                ZeusLogger.w(ZeusLogger.TAG_SO, "NativeLibHelper inferHostAbiAuto, processMode default=0");
            }
            a(jSONObject, "processMode", String.valueOf(i));
            if (i != 0) {
                if (f4641c.get(str).intValue() != i) {
                    return null;
                }
                ZeusLogger.w(ZeusLogger.TAG_SO, "NativeLibHelper inferHostAbiAuto2, sHostAbi=".concat(String.valueOf(str)));
                return str;
            }
            ZeusLogger.w(ZeusLogger.TAG_SO, "NativeLibHelper inferHostAbiAuto1, sHostAbi=".concat(String.valueOf(str)));
            return str;
        } catch (Exception e) {
            ZeusLogger.e(ZeusLogger.TAG_SO, "NativeLibHelper inferHostAbiAuto failed!", e);
            a(jSONObject, "autoError", "1");
            return null;
        }
    }

    private static String b(JSONObject jSONObject) throws JSONException {
        HashSet hashSet;
        String[] strArr;
        try {
            ZipFile zipFile = new ZipFile(new File(Zeus.getAppApplication().getPackageManager().getPackageInfo(Zeus.getAppApplication().getPackageName(), 0).applicationInfo.sourceDir));
            hashSet = new HashSet(a(zipFile).keySet());
            try {
                zipFile.close();
            } catch (IOException unused) {
                ZeusLogger.w(ZeusLogger.TAG_SO, "NativeLibHelper inferHostAbiManual, close sourceApkZipFile error!");
            }
            if (g.a()) {
                strArr = Build.SUPPORTED_ABIS;
            } else {
                strArr = new String[]{Build.CPU_ABI, Build.CPU_ABI2};
            }
        } catch (Throwable th) {
            ZeusLogger.e(ZeusLogger.TAG_SO, "NativeLibHelper inferHostAbiManual failed!", th);
            a(jSONObject, "manualError", "1");
        }
        if (hashSet.isEmpty()) {
            ZeusLogger.w(ZeusLogger.TAG_SO, "NativeLibHelper inferHostAbiManual, host source apk .so is empty, use supportedABIs[0]=" + strArr[0]);
            a(jSONObject, "supportedABI0", strArr[0]);
            return strArr[0];
        }
        for (String str : strArr) {
            if (hashSet.contains(str)) {
                ZeusLogger.w(ZeusLogger.TAG_SO, "NativeLibHelper inferHostAbiManual, match cpuAbi=".concat(String.valueOf(str)));
                a(jSONObject, "matchCpuAbi", str);
                return str;
            }
        }
        if (g.a()) {
            a(jSONObject, "defaultABI0", Build.SUPPORTED_ABIS[0]);
            return Build.SUPPORTED_ABIS[0];
        }
        a(jSONObject, "defaultABI", Build.CPU_ABI);
        return Build.CPU_ABI;
    }

    public static boolean a(File file) throws Throwable {
        ZipFile zipFile;
        boolean zContainsKey;
        ZipFile zipFile2 = null;
        try {
            try {
                zipFile = new ZipFile(file);
            } catch (Throwable th) {
                th = th;
                zipFile = zipFile2;
            }
        } catch (IOException e) {
            e = e;
        }
        try {
            Map<String, List<ZipEntry>> mapA = a(zipFile);
            if (mapA.isEmpty()) {
                zContainsKey = true;
                ZeusLogger.i(ZeusLogger.TAG_SO, "NativeLibHelper isPluginApkMatchHostAbi [true] soEntries empty, ".concat(String.valueOf(file)));
            } else {
                zContainsKey = mapA.containsKey(f4639a);
                if (zContainsKey) {
                    ZeusLogger.i(ZeusLogger.TAG_SO, "NativeLibHelper isPluginApkMatchHostAbi [" + zContainsKey + "], " + file);
                } else {
                    ZeusLogger.e(ZeusLogger.TAG_SO, "NativeLibHelper isPluginApkMatchHostAbi [" + zContainsKey + "], " + file);
                }
            }
            try {
                zipFile.close();
            } catch (IOException unused) {
                ZeusLogger.e(ZeusLogger.TAG_SO, "NativeLibHelper isPluginApkMatchHostAbi, close sourceApkZipFile error!");
            }
            return zContainsKey;
        } catch (IOException e2) {
            e = e2;
            zipFile2 = zipFile;
            ZeusLogger.e(ZeusLogger.TAG_SO, "NativeLibHelper isPluginApkMatchHostAbi, get sourceApk ZipFile failed!", e);
            if (zipFile2 == null) {
                return false;
            }
            try {
                zipFile2.close();
                return false;
            } catch (IOException unused2) {
                ZeusLogger.e(ZeusLogger.TAG_SO, "NativeLibHelper isPluginApkMatchHostAbi, close sourceApkZipFile error!");
                return false;
            }
        } catch (Throwable th2) {
            th = th2;
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (IOException unused3) {
                    ZeusLogger.e(ZeusLogger.TAG_SO, "NativeLibHelper isPluginApkMatchHostAbi, close sourceApkZipFile error!");
                }
            }
            throw th;
        }
    }

    public static boolean b(File file) throws Throwable {
        ZipFile zipFile;
        ZipFile zipFile2 = null;
        try {
            try {
                zipFile = new ZipFile(file);
            } catch (IOException e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
            zipFile = zipFile2;
        }
        try {
            Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
            Pattern patternCompile = Pattern.compile("^lib/[^/]+/lib[^/]+.so$");
            while (enumerationEntries.hasMoreElements()) {
                ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                if (!zipEntryNextElement.isDirectory() && patternCompile.matcher(zipEntryNextElement.getName()).matches()) {
                    try {
                        zipFile.close();
                        return true;
                    } catch (IOException unused) {
                        ZeusLogger.e(ZeusLogger.TAG_SO, "NativeLibHelper hasNativeLib, close sourceApkZipFile error!");
                        return true;
                    }
                }
            }
            try {
                zipFile.close();
            } catch (IOException unused2) {
                ZeusLogger.e(ZeusLogger.TAG_SO, "NativeLibHelper hasNativeLib, close sourceApkZipFile error!");
            }
            return false;
        } catch (IOException e2) {
            e = e2;
            zipFile2 = zipFile;
            ZeusLogger.e(ZeusLogger.TAG_SO, "NativeLibHelper hasNativeLib, get sourceApk ZipFile failed!", e);
            if (zipFile2 != null) {
                try {
                    zipFile2.close();
                } catch (IOException unused3) {
                    ZeusLogger.e(ZeusLogger.TAG_SO, "NativeLibHelper hasNativeLib, close sourceApkZipFile error!");
                }
            }
            return false;
        } catch (Throwable th2) {
            th = th2;
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (IOException unused4) {
                    ZeusLogger.e(ZeusLogger.TAG_SO, "NativeLibHelper hasNativeLib, close sourceApkZipFile error!");
                }
            }
            throw th;
        }
    }

    private static void a(JSONObject jSONObject, String str, String str2) throws JSONException {
        try {
            jSONObject.put(str, str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static JSONObject d() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("primaryCpuAbi", OnekeyLoginConstants.CU_RESULT_SUCCESS);
            jSONObject.put("processMode", OnekeyLoginConstants.CU_RESULT_SUCCESS);
            jSONObject.put("supportedABI0", OnekeyLoginConstants.CU_RESULT_SUCCESS);
            jSONObject.put("matchCpuAbi", OnekeyLoginConstants.CU_RESULT_SUCCESS);
            jSONObject.put("defaultABI0", OnekeyLoginConstants.CU_RESULT_SUCCESS);
            jSONObject.put("defaultABI", OnekeyLoginConstants.CU_RESULT_SUCCESS);
            jSONObject.put("autoError", OnekeyLoginConstants.CU_RESULT_SUCCESS);
            jSONObject.put("manualError", OnekeyLoginConstants.CU_RESULT_SUCCESS);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
