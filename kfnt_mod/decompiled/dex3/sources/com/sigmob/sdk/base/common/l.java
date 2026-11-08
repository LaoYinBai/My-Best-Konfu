package com.sigmob.sdk.base.common;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import android.widget.Toast;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.base.common.t;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.base.models.IntentActions;
import com.sigmob.sdk.common.Constants;
import com.sigmob.sdk.common.SDKConfig;
import com.sigmob.sdk.common.mta.PointCategory;
import com.sigmob.sdk.common.mta.PointEntitySigmob;
import com.sigmob.sdk.common.utils.n;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.tachikoma.core.utility.UriUtil;
import com.umeng.message.MsgConstant;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class l {
    private static Map<String, String> a = new Hashtable();
    private static Set<a> b = new CopyOnWriteArraySet();

    private static class a {
        String a;
        String b;
        long c;

        public a(String str, String str2, long j) {
            this.a = str;
            this.b = str2;
            this.c = j;
        }
    }

    private static long a(Context context, String str, String str2) {
        if (str != null) {
            try {
                if (!str.isEmpty()) {
                    String strTrim = str.trim();
                    if (!strTrim.startsWith(Constants.HTTP)) {
                        strTrim = UriUtil.HTTP_PREFIX + strTrim;
                    }
                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(strTrim));
                    File file = new File(str2);
                    request.setTitle(file.getName());
                    request.setAllowedNetworkTypes(3);
                    request.setAllowedOverRoaming(true);
                    request.setVisibleInDownloadsUi(true);
                    request.setNotificationVisibility(1);
                    File parentFile = file.getParentFile();
                    if (parentFile.exists()) {
                        if (!parentFile.isDirectory()) {
                            parentFile.delete();
                            if (!parentFile.mkdirs()) {
                                return -1L;
                            }
                        }
                    } else if (!parentFile.mkdirs()) {
                        return -1L;
                    }
                    request.addRequestHeader("User-Agent", com.sigmob.sdk.common.e.f.c());
                    request.setDestinationUri(Uri.fromFile(file));
                    return ((DownloadManager) context.getApplicationContext().getSystemService("download")).enqueue(request);
                }
            } catch (Throwable th) {
                SigmobLog.e(th.getMessage());
            }
        }
        return -1L;
    }

    public static String a(File file) {
        String name = file.getName();
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(name.substring(name.lastIndexOf(".") + 1, name.length()).toLowerCase());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0055  */
    /* JADX WARN: Type inference failed for: r5v0, types: [android.app.DownloadManager] */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v4, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.List<java.lang.String> a(android.app.DownloadManager r5) throws java.lang.Throwable {
        /*
            r0 = 0
            android.app.DownloadManager$Query r1 = new android.app.DownloadManager$Query     // Catch: java.lang.Throwable -> L3e java.lang.Throwable -> L43
            r1.<init>()     // Catch: java.lang.Throwable -> L3e java.lang.Throwable -> L43
            r2 = 3
            r1.setFilterByStatus(r2)     // Catch: java.lang.Throwable -> L3e java.lang.Throwable -> L43
            android.database.Cursor r5 = r5.query(r1)     // Catch: java.lang.Throwable -> L3e java.lang.Throwable -> L43
            boolean r1 = r5.moveToFirst()     // Catch: java.lang.Throwable -> L3c java.lang.Throwable -> L52
            if (r1 != 0) goto L1d
            r5.close()     // Catch: java.lang.Throwable -> L3c java.lang.Throwable -> L52
            if (r5 == 0) goto L1c
            r5.close()
        L1c:
            return r0
        L1d:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L3c java.lang.Throwable -> L52
            r1.<init>()     // Catch: java.lang.Throwable -> L3c java.lang.Throwable -> L52
            java.lang.String r2 = "title"
            int r2 = r5.getColumnIndex(r2)     // Catch: java.lang.Throwable -> L3c java.lang.Throwable -> L52
            java.lang.String r2 = r5.getString(r2)     // Catch: java.lang.Throwable -> L3c java.lang.Throwable -> L52
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Throwable -> L3c java.lang.Throwable -> L52
            if (r3 != 0) goto L35
            r1.add(r2)     // Catch: java.lang.Throwable -> L3c java.lang.Throwable -> L52
        L35:
            r5.moveToNext()     // Catch: java.lang.Throwable -> L3c java.lang.Throwable -> L52
            r5.close()     // Catch: java.lang.Throwable -> L3c java.lang.Throwable -> L52
            return r1
        L3c:
            r1 = move-exception
            goto L45
        L3e:
            r5 = move-exception
            r4 = r0
            r0 = r5
            r5 = r4
            goto L53
        L43:
            r1 = move-exception
            r5 = r0
        L45:
            java.lang.String r1 = r1.getMessage()     // Catch: java.lang.Throwable -> L52
            com.sigmob.logger.SigmobLog.e(r1)     // Catch: java.lang.Throwable -> L52
            if (r5 == 0) goto L51
            r5.close()
        L51:
            return r0
        L52:
            r0 = move-exception
        L53:
            if (r5 == 0) goto L58
            r5.close()
        L58:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sigmob.sdk.base.common.l.a(android.app.DownloadManager):java.util.List");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0080  */
    /* JADX WARN: Type inference failed for: r5v0, types: [android.app.DownloadManager] */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v3, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.Map<java.lang.String, java.lang.Object> a(android.app.DownloadManager r5, long r6) throws java.lang.Throwable {
        /*
            r0 = 0
            android.app.DownloadManager$Query r1 = new android.app.DownloadManager$Query     // Catch: java.lang.Throwable -> L6b java.lang.Throwable -> L6e
            r1.<init>()     // Catch: java.lang.Throwable -> L6b java.lang.Throwable -> L6e
            r2 = 1
            long[] r2 = new long[r2]     // Catch: java.lang.Throwable -> L6b java.lang.Throwable -> L6e
            r3 = 0
            r2[r3] = r6     // Catch: java.lang.Throwable -> L6b java.lang.Throwable -> L6e
            r1.setFilterById(r2)     // Catch: java.lang.Throwable -> L6b java.lang.Throwable -> L6e
            android.net.Uri r6 = r5.getUriForDownloadedFile(r6)     // Catch: java.lang.Throwable -> L6b java.lang.Throwable -> L6e
            android.database.Cursor r5 = r5.query(r1)     // Catch: java.lang.Throwable -> L6b java.lang.Throwable -> L6e
            boolean r7 = r5.moveToFirst()     // Catch: java.lang.Throwable -> L69 java.lang.Throwable -> L7d
            if (r7 != 0) goto L26
            r5.close()     // Catch: java.lang.Throwable -> L69 java.lang.Throwable -> L7d
            if (r5 == 0) goto L25
            r5.close()
        L25:
            return r0
        L26:
            java.lang.String r7 = "status"
            int r7 = r5.getColumnIndex(r7)     // Catch: java.lang.Throwable -> L69 java.lang.Throwable -> L7d
            int r7 = r5.getInt(r7)     // Catch: java.lang.Throwable -> L69 java.lang.Throwable -> L7d
            java.lang.String r1 = "reason"
            int r1 = r5.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L69 java.lang.Throwable -> L7d
            int r1 = r5.getInt(r1)     // Catch: java.lang.Throwable -> L69 java.lang.Throwable -> L7d
            java.lang.String r2 = "local_uri"
            int r2 = r5.getColumnIndex(r2)     // Catch: java.lang.Throwable -> L69 java.lang.Throwable -> L7d
            java.lang.String r2 = r5.getString(r2)     // Catch: java.lang.Throwable -> L69 java.lang.Throwable -> L7d
            java.util.HashMap r3 = new java.util.HashMap     // Catch: java.lang.Throwable -> L69 java.lang.Throwable -> L7d
            r3.<init>()     // Catch: java.lang.Throwable -> L69 java.lang.Throwable -> L7d
            java.lang.String r4 = "fileName"
            r3.put(r4, r2)     // Catch: java.lang.Throwable -> L69 java.lang.Throwable -> L7d
            java.lang.String r2 = "status"
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch: java.lang.Throwable -> L69 java.lang.Throwable -> L7d
            r3.put(r2, r7)     // Catch: java.lang.Throwable -> L69 java.lang.Throwable -> L7d
            java.lang.String r7 = "uri"
            r3.put(r7, r6)     // Catch: java.lang.Throwable -> L69 java.lang.Throwable -> L7d
            java.lang.String r6 = "reason"
            java.lang.Integer r7 = java.lang.Integer.valueOf(r1)     // Catch: java.lang.Throwable -> L69 java.lang.Throwable -> L7d
            r3.put(r6, r7)     // Catch: java.lang.Throwable -> L69 java.lang.Throwable -> L7d
            r5.close()     // Catch: java.lang.Throwable -> L69 java.lang.Throwable -> L7d
            return r3
        L69:
            r6 = move-exception
            goto L70
        L6b:
            r6 = move-exception
            r5 = r0
            goto L7e
        L6e:
            r6 = move-exception
            r5 = r0
        L70:
            java.lang.String r6 = r6.getMessage()     // Catch: java.lang.Throwable -> L7d
            com.sigmob.logger.SigmobLog.e(r6)     // Catch: java.lang.Throwable -> L7d
            if (r5 == 0) goto L7c
            r5.close()
        L7c:
            return r0
        L7d:
            r6 = move-exception
        L7e:
            if (r5 == 0) goto L83
            r5.close()
        L83:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sigmob.sdk.base.common.l.a(android.app.DownloadManager, long):java.util.Map");
    }

    public static void a(Context context, String str, BaseAdUnit baseAdUnit) {
        try {
            SigmobLog.i("download apk:" + str);
            d.b(baseAdUnit);
            String strA = m.a(str);
            if (TextUtils.isEmpty(strA) && !TextUtils.isEmpty(baseAdUnit.getProductId())) {
                strA = baseAdUnit.getProductId() + com.ss.android.socialbase.appdownloader.constants.Constants.APK_SUFFIX;
            }
            a(strA, context, str, baseAdUnit);
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
            t.b(PointCategory.DOWNLOAD_START, Constants.FAIL, baseAdUnit);
            t.a(PointCategory.DOWNLOAD_START, 0, th.getMessage(), baseAdUnit);
            try {
                Toast.makeText(context, "下载失败", 1).show();
            } catch (Throwable th2) {
                SigmobLog.e(th2.getMessage());
            }
        }
    }

    private static void a(String str, Context context, final String str2, BaseAdUnit baseAdUnit) {
        HashMap map;
        String uuid;
        String str3;
        String str4;
        try {
            if (TextUtils.isEmpty(str)) {
                str = com.sigmob.sdk.common.utils.k.a(baseAdUnit.getLanding_page()) + com.ss.android.socialbase.appdownloader.constants.Constants.APK_SUFFIX;
            }
            final File file = new File(com.sigmob.sdk.common.utils.d.a(context), str);
            if (c(-1L, file.getAbsolutePath())) {
                try {
                    Toast.makeText(context, str + "当前正在努力下载，请稍等", 1).show();
                    return;
                } catch (Throwable unused) {
                    return;
                }
            }
            List<String> listA = a((DownloadManager) context.getApplicationContext().getSystemService("download"));
            if (listA != null) {
                for (String str5 : listA) {
                    if (!TextUtils.isEmpty(str5) && str5.equals(str)) {
                        try {
                            Toast.makeText(context, str + "当前正在努力下载，请稍等", 1).show();
                            return;
                        } catch (Throwable unused2) {
                            return;
                        }
                    }
                }
            }
            boolean zContainsKey = a.containsKey(baseAdUnit.getUuid());
            if (file.exists() && !zContainsKey && !a(context, file.getAbsolutePath())) {
                SigmobLog.i("cacheFile deleteFile:" + com.sigmob.sdk.common.utils.d.b(file.getAbsolutePath()));
            }
            SigmobLog.i(str + " exists:" + file.exists() + " containsKey:" + zContainsKey);
            if (file.exists()) {
                if (zContainsKey) {
                    b(context, file.getAbsolutePath(), baseAdUnit);
                    return;
                }
                long jLastModified = file.lastModified();
                if (SDKConfig.sharedInstance().getApk_expired_time() == 0) {
                    str4 = "default deleteFile:" + com.sigmob.sdk.common.utils.d.b(file.getAbsolutePath());
                } else {
                    if (System.currentTimeMillis() - jLastModified <= r2 * 1000) {
                        b(context, file.getAbsolutePath(), baseAdUnit);
                        return;
                    }
                    str4 = "timeOut deleteFile:" + com.sigmob.sdk.common.utils.d.b(file.getAbsolutePath());
                }
                SigmobLog.i(str4);
            }
            try {
                Toast.makeText(context, "下载开始", 1).show();
            } catch (Throwable th) {
                SigmobLog.e(th.getMessage());
            }
            a.put(baseAdUnit.getUuid(), str);
            long jA = a(context, str2, file.getAbsolutePath());
            if (jA != -1) {
                a(jA, file.getAbsolutePath());
                com.sigmob.sdk.common.utils.d.a(baseAdUnit, new File(com.sigmob.sdk.common.utils.d.d(), jA + MsgConstant.CACHE_LOG_FILE_EXT).getAbsolutePath());
                t.a(PointCategory.DOWNLOAD_START, "1", baseAdUnit, new t.a() { // from class: com.sigmob.sdk.base.common.l.1
                    @Override // com.sigmob.sdk.base.common.t.a
                    public void a(Object obj) {
                        if (obj instanceof PointEntitySigmob) {
                            HashMap map2 = new HashMap();
                            map2.put("apkfile", file.getAbsolutePath());
                            map2.put("apkurl", str2);
                            ((PointEntitySigmob) obj).setOptions(map2);
                        }
                    }
                });
                map = new HashMap();
                map.put("result", "1");
                uuid = baseAdUnit.getUuid();
                str3 = IntentActions.ACTION_INTERSTITIAL_DOWNLOAD_START;
            } else {
                t.a(PointCategory.DOWNLOAD_START, Constants.FAIL, baseAdUnit, new t.a() { // from class: com.sigmob.sdk.base.common.l.2
                    @Override // com.sigmob.sdk.base.common.t.a
                    public void a(Object obj) {
                        if (obj instanceof PointEntitySigmob) {
                            HashMap map2 = new HashMap();
                            map2.put("apkfile", file.getAbsolutePath());
                            map2.put("apkurl", str2);
                            ((PointEntitySigmob) obj).setOptions(map2);
                        }
                    }
                });
                if (com.sigmob.sdk.common.a.b(context, MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE)) {
                    t.a("download_failed", 2, "download Directory not exist", baseAdUnit);
                } else {
                    Toast.makeText(context, "请先给予应用 WRITE_EXTERNAL_STORAGE 权限", 1).show();
                    t.a("download_failed", 1, " No permission Manifest.permission.WRITE_EXTERNAL_STORAGE ", baseAdUnit);
                }
                map = new HashMap();
                map.put("result", Constants.FAIL);
                uuid = baseAdUnit.getUuid();
                str3 = IntentActions.ACTION_INTERSTITIAL_DOWNLOAD_START;
            }
            BaseBroadcastReceiver.a(context, uuid, map, str3);
            com.sigmob.sdk.base.a.c.a(baseAdUnit, com.sigmob.sdk.base.common.a.AD_DOWNLOAD_START);
        } catch (Throwable th2) {
            SigmobLog.e(th2.getMessage());
            t.b(PointCategory.DOWNLOAD_START, Constants.FAIL, baseAdUnit);
            t.a(PointCategory.DOWNLOAD_START, 0, th2.getMessage(), baseAdUnit);
            HashMap map2 = new HashMap();
            map2.put("result", Constants.FAIL);
            BaseBroadcastReceiver.a(context, baseAdUnit.getUuid(), map2, IntentActions.ACTION_INTERSTITIAL_DOWNLOAD_START);
            try {
                Toast.makeText(context, "请先给予应用权限", 1).show();
            } catch (Throwable unused3) {
            }
        }
    }

    public static boolean a(long j, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        b.add(new a(str, null, j));
        return true;
    }

    public static boolean a(Context context, String str) {
        try {
            return context.getPackageManager().getPackageArchiveInfo(str, 1) != null;
        } catch (Exception unused) {
            return false;
        }
    }

    public static void b(long j, String str) {
        for (a aVar : b) {
            if (aVar.c == j || aVar.a.equalsIgnoreCase(str)) {
                b.remove(aVar);
                return;
            }
        }
    }

    public static void b(Context context, String str, BaseAdUnit baseAdUnit) throws Throwable {
        Class<?> cls;
        Uri uri;
        File file = new File(str);
        SigmobLog.i("installAPK:" + file.getAbsolutePath() + com.xiaomi.mipush.sdk.Constants.COLON_SEPARATOR + file.exists());
        Intent intent = new Intent();
        intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        intent.setAction("android.intent.action.VIEW");
        try {
            Toast.makeText(context, "开始安装", 1).show();
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
        }
        if (Build.VERSION.SDK_INT < 24 || context.getApplicationInfo().targetSdkVersion < 24) {
            intent.setDataAndType(Uri.fromFile(file), a(file));
        } else {
            try {
                cls = Class.forName("androidx.core.content.FileProvider");
            } catch (Throwable unused) {
                cls = null;
            }
            try {
                cls = Class.forName("androidx.core.content.FileProvider");
            } catch (Throwable unused2) {
            }
            if (cls != null) {
                try {
                    n.a aVar = new n.a((Class) cls, "getUriForFile");
                    aVar.a(Context.class, context);
                    aVar.a(String.class, context.getApplicationContext().getPackageName() + ".sigprovider");
                    aVar.a(File.class, file);
                    uri = (Uri) aVar.a();
                } catch (Exception e) {
                    SigmobLog.e(e.getMessage());
                    uri = null;
                }
                if (uri != null) {
                    intent.addFlags(1);
                    intent.setDataAndType(uri, "application/vnd.android.package-archive");
                }
            }
        }
        context.startActivity(intent);
        com.sigmob.sdk.base.a.c.a(baseAdUnit, com.sigmob.sdk.base.common.a.AD_INSTALL_START);
        BaseBroadcastReceiver.a(context, baseAdUnit.getUuid(), (Map<String, Object>) null, IntentActions.ACTION_INTERSTITIAL_INSTALL_START);
        PackageInfo packageInfoA = com.sigmob.sdk.common.a.a(context, str);
        if (packageInfoA != null) {
            com.sigmob.sdk.common.utils.d.a(baseAdUnit, new File(com.sigmob.sdk.common.utils.d.d(), packageInfoA.packageName + MsgConstant.CACHE_LOG_FILE_EXT).getAbsolutePath());
        }
        t.a(baseAdUnit, PointCategory.APP_INSTALL_START, packageInfoA, "1");
    }

    public static boolean b(Context context, String str) throws Throwable {
        Cursor cursorQuery;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        DownloadManager downloadManager = (DownloadManager) context.getSystemService("download");
        Cursor cursor = null;
        try {
            try {
                DownloadManager.Query query = new DownloadManager.Query();
                query.setFilterByStatus(3);
                cursorQuery = downloadManager.query(query);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
            cursorQuery = cursor;
        }
        try {
            if (!cursorQuery.moveToFirst()) {
                cursorQuery.close();
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return false;
            }
            new ArrayList();
            do {
                String string = cursorQuery.getString(cursorQuery.getColumnIndex("uri"));
                if (!TextUtils.isEmpty(string) && str.equalsIgnoreCase(string)) {
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return true;
                }
            } while (cursorQuery.moveToNext());
            cursorQuery.close();
            return false;
        } catch (Throwable th3) {
            th = th3;
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }

    public static boolean c(long j, String str) {
        for (a aVar : b) {
            if (aVar.c == j || aVar.a.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }
}
