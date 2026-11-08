package com.ss.android.a.a;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.location.LocationManager;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.bytedance.common.utility.Logger;
import com.bytedance.common.utility.StringUtils;
import com.bytedance.common.utility.reflect.Reflect;
import com.sigmob.sdk.common.mta.PointCategory;
import com.ss.android.account.model2.BDAccountPlatformEntity;
import com.ss.android.download.api.constant.BaseConstants;
import com.ss.android.downloadlib.utils.ToolUtils;
import com.ss.android.socialbase.downloader.constants.DBDefinition;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.ss.union.game.sdk.core.glide.request.target.Target;
import com.ss.union.game.sdk.feedback.picture.select.d;
import com.tachikoma.core.utility.UriUtil;
import com.umeng.message.MsgConstant;
import com.xiaomi.mipush.sdk.Constants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class a {
    public static final int a = 1;
    public static final int b = 2;
    public static final int c = 4;
    public static String d = ":push";
    public static final String e = ":nodex";
    public static boolean f = false;
    public static boolean g = false;
    private static final int h = 16777216;
    private static String i = null;
    private static String j = null;
    private static boolean k = true;

    private a() {
    }

    public static void a(String str) {
        d = str;
    }

    public static int a(Context context) {
        if (context == null) {
            return -1;
        }
        try {
            if (StringUtils.isEmpty(Settings.Secure.getString(context.getContentResolver(), "location_providers_allowed"))) {
                return 0;
            }
            LocationManager locationManager = (LocationManager) context.getSystemService(MsgConstant.KEY_LOCATION_PARAMS);
            int i2 = locationManager.isProviderEnabled("gps") ? 1 : 0;
            if (locationManager.isProviderEnabled(PointCategory.NETWORK)) {
                i2 |= 2;
            }
            return locationManager.isProviderEnabled("passive") ? i2 | 4 : i2;
        } catch (Throwable unused) {
            return -1;
        }
    }

    public static Intent a(Context context, String str) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage == null) {
            return launchIntentForPackage;
        }
        if (!launchIntentForPackage.hasCategory("android.intent.category.LAUNCHER")) {
            launchIntentForPackage.addCategory("android.intent.category.LAUNCHER");
            Logger.d(ToolUtils.TAG, "add category LAUNCHER in launch intent");
        }
        launchIntentForPackage.setPackage(null);
        launchIntentForPackage.addFlags(2097152);
        launchIntentForPackage.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        return launchIntentForPackage;
    }

    public static boolean b(Context context, String str) {
        if (context == null || StringUtils.isEmpty(str)) {
            return false;
        }
        try {
            return context.getPackageManager().getPackageInfo(str, 16777216) != null;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean a(Context context, Intent intent) {
        List<ResolveInfo> listQueryIntentActivities;
        return (intent == null || (listQueryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 65536)) == null || listQueryIntentActivities.size() <= 0) ? false : true;
    }

    public static void a(Context context, String str, String str2) {
        Intent intentA;
        if ((!StringUtils.isEmpty(str) ? b(context, str) : false) && (intentA = a(context, str)) != null) {
            context.startActivity(intentA);
            return;
        }
        if (!StringUtils.isEmpty(str2)) {
            try {
                context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str2)));
                return;
            } catch (Exception unused) {
            }
        }
        if (StringUtils.isEmpty(str)) {
            return;
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(BaseConstants.MARKET_PREFIX + str));
        intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        try {
            context.startActivity(intent);
        } catch (Exception unused2) {
        }
    }

    public static boolean a() {
        try {
            if (StringUtils.isEmpty(Build.BRAND) || !Build.BRAND.toLowerCase(Locale.getDefault()).startsWith(BDAccountPlatformEntity.PLAT_NAME_HUAWEI)) {
                if (StringUtils.isEmpty(Build.MANUFACTURER)) {
                    return false;
                }
                if (!Build.MANUFACTURER.toLowerCase(Locale.getDefault()).startsWith(BDAccountPlatformEntity.PLAT_NAME_HUAWEI)) {
                    return false;
                }
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            str = b();
        }
        return (!TextUtils.isEmpty(str) && str.toLowerCase(Locale.getDefault()).startsWith("emotionui")) || a();
    }

    public static String b() {
        return f("ro.build.version.emui");
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x007f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String f(java.lang.String r7) throws java.lang.Throwable {
        /*
            java.lang.String r0 = com.ss.android.a.a.a.i
            boolean r0 = com.bytedance.common.utility.StringUtils.isEmpty(r0)
            if (r0 != 0) goto Lb
            java.lang.String r7 = com.ss.android.a.a.a.i
            return r7
        Lb:
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L52 java.lang.Throwable -> L54
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L52 java.lang.Throwable -> L54
            r2.<init>()     // Catch: java.lang.Throwable -> L52 java.lang.Throwable -> L54
            java.lang.String r3 = "getprop "
            r2.append(r3)     // Catch: java.lang.Throwable -> L52 java.lang.Throwable -> L54
            r2.append(r7)     // Catch: java.lang.Throwable -> L52 java.lang.Throwable -> L54
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L52 java.lang.Throwable -> L54
            java.lang.Process r1 = r1.exec(r2)     // Catch: java.lang.Throwable -> L52 java.lang.Throwable -> L54
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L52 java.lang.Throwable -> L54
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L52 java.lang.Throwable -> L54
            java.io.InputStream r1 = r1.getInputStream()     // Catch: java.lang.Throwable -> L52 java.lang.Throwable -> L54
            r3.<init>(r1)     // Catch: java.lang.Throwable -> L52 java.lang.Throwable -> L54
            r1 = 1024(0x400, float:1.435E-42)
            r2.<init>(r3, r1)     // Catch: java.lang.Throwable -> L52 java.lang.Throwable -> L54
            java.lang.String r1 = r2.readLine()     // Catch: java.lang.Throwable -> L50 java.lang.Throwable -> L7b
            r2.close()     // Catch: java.lang.Throwable -> L4b java.lang.Throwable -> L7b
            com.ss.android.a.a.a.i = r1     // Catch: java.lang.Throwable -> L4b java.lang.Throwable -> L7b
            r2.close()     // Catch: java.io.IOException -> L42
            goto L4a
        L42:
            r7 = move-exception
            java.lang.String r0 = "ToolUtils"
            java.lang.String r2 = "Exception while closing InputStream"
            com.bytedance.common.utility.Logger.e(r0, r2, r7)
        L4a:
            return r1
        L4b:
            r0 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
            goto L56
        L50:
            r1 = move-exception
            goto L56
        L52:
            r7 = move-exception
            goto L7d
        L54:
            r1 = move-exception
            r2 = r0
        L56:
            java.lang.String r3 = "ToolUtils"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L7b
            r4.<init>()     // Catch: java.lang.Throwable -> L7b
            java.lang.String r5 = "Unable to read sysprop "
            r4.append(r5)     // Catch: java.lang.Throwable -> L7b
            r4.append(r7)     // Catch: java.lang.Throwable -> L7b
            java.lang.String r7 = r4.toString()     // Catch: java.lang.Throwable -> L7b
            com.bytedance.common.utility.Logger.e(r3, r7, r1)     // Catch: java.lang.Throwable -> L7b
            if (r2 == 0) goto L7a
            r2.close()     // Catch: java.io.IOException -> L72
            goto L7a
        L72:
            r7 = move-exception
            java.lang.String r1 = "ToolUtils"
            java.lang.String r2 = "Exception while closing InputStream"
            com.bytedance.common.utility.Logger.e(r1, r2, r7)
        L7a:
            return r0
        L7b:
            r7 = move-exception
            r0 = r2
        L7d:
            if (r0 == 0) goto L8b
            r0.close()     // Catch: java.io.IOException -> L83
            goto L8b
        L83:
            r0 = move-exception
            java.lang.String r1 = "ToolUtils"
            java.lang.String r2 = "Exception while closing InputStream"
            com.bytedance.common.utility.Logger.e(r1, r2, r0)
        L8b:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.a.a.a.f(java.lang.String):java.lang.String");
    }

    public static boolean c() {
        if (!g) {
            try {
                if (Class.forName("miui.os.Build") != null) {
                    f = true;
                    g = true;
                    return f;
                }
            } catch (Exception unused) {
            }
            g = true;
        }
        return f;
    }

    public static boolean d() {
        if (StringUtils.isEmpty(Build.DISPLAY) || Build.DISPLAY.indexOf("Flyme") < 0) {
            return !StringUtils.isEmpty(Build.USER) && Build.USER.equals(BDAccountPlatformEntity.PLAT_NAME_FLYME);
        }
        return true;
    }

    private static String m(Context context, String str) {
        List<PackageInfo> installedPackages;
        int i2;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            installedPackages = context.getPackageManager().getInstalledPackages(8);
        } catch (Exception unused) {
        }
        if (installedPackages == null) {
            return null;
        }
        Iterator<PackageInfo> it = installedPackages.iterator();
        while (it.hasNext()) {
            ProviderInfo[] providerInfoArr = it.next().providers;
            if (providerInfoArr != null) {
                int length = providerInfoArr.length;
                while (i2 < length) {
                    ProviderInfo providerInfo = providerInfoArr[i2];
                    i2 = (str.equals(providerInfo.readPermission) || str.equals(providerInfo.writePermission)) ? 0 : i2 + 1;
                    return providerInfo.authority;
                }
            }
        }
        return null;
    }

    public static boolean c(Context context, String str) {
        boolean z = false;
        try {
            String strM = m(context, "com.android.launcher.permission.READ_SETTINGS");
            if (strM == null) {
                strM = "com.android.launcher.settings";
            }
            Cursor cursorQuery = context.getContentResolver().query(Uri.parse("content://" + strM + "/favorites?notify=true"), new String[]{DBDefinition.TITLE}, "title=?", new String[]{str}, null);
            if (cursorQuery != null && cursorQuery.getCount() > 0) {
                z = true;
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (Exception unused) {
        }
        return z;
    }

    public static void d(Context context, String str) throws Resources.NotFoundException, PackageManager.NameNotFoundException {
        if (context == null) {
            return;
        }
        try {
            Intent intentA = a(context, str);
            if (intentA == null) {
                return;
            }
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 0);
            Resources resourcesForApplication = packageManager.getResourcesForApplication(applicationInfo);
            String resourceName = resourcesForApplication.getResourceName(applicationInfo.icon);
            CharSequence applicationLabel = null;
            try {
                int i2 = packageManager.getActivityInfo(intentA.getComponent(), 0).labelRes;
                if (i2 > 0) {
                    applicationLabel = resourcesForApplication.getString(i2);
                }
            } catch (Resources.NotFoundException unused) {
                applicationLabel = packageManager.getApplicationLabel(applicationInfo);
            }
            if (applicationLabel == null) {
                return;
            }
            Intent intent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
            intent.putExtra("android.intent.extra.shortcut.NAME", applicationLabel);
            intent.putExtra("android.intent.extra.shortcut.INTENT", intentA);
            intent.putExtra("launch_from", 1);
            Intent.ShortcutIconResource shortcutIconResource = new Intent.ShortcutIconResource();
            shortcutIconResource.packageName = str;
            shortcutIconResource.resourceName = resourceName;
            intent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", shortcutIconResource);
            intent.putExtra("duplicate", false);
            context.sendBroadcast(intent);
        } catch (Exception e2) {
            Log.w(ToolUtils.TAG, "addAppShortcut failed: " + e2);
        }
    }

    public static void c(String str) throws Exception {
        File file = new File(str);
        if (file.exists()) {
            File[] fileArrListFiles = file.listFiles();
            int length = fileArrListFiles.length;
            for (int i2 = 0; i2 < length; i2++) {
                if (fileArrListFiles[i2].isDirectory()) {
                    d(fileArrListFiles[i2].getAbsolutePath());
                } else if (fileArrListFiles[i2].isFile()) {
                    fileArrListFiles[i2].delete();
                }
            }
        }
    }

    public static void d(String str) throws Exception {
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            File[] fileArrListFiles = file.listFiles();
            int length = fileArrListFiles.length;
            for (int i2 = 0; i2 < length; i2++) {
                if (fileArrListFiles[i2].isDirectory()) {
                    d(fileArrListFiles[i2].getAbsolutePath());
                } else {
                    fileArrListFiles[i2].delete();
                }
            }
            file.delete();
        }
    }

    public static void a(String str, Set<String> set) throws Exception {
        File file = new File(str);
        if (file.exists()) {
            File[] fileArrListFiles = file.listFiles();
            int length = fileArrListFiles.length;
            for (int i2 = 0; i2 < length; i2++) {
                if (fileArrListFiles[i2].isDirectory()) {
                    b(fileArrListFiles[i2].getAbsolutePath(), set);
                } else if (fileArrListFiles[i2].isFile()) {
                    String name = fileArrListFiles[i2].getName();
                    if (set == null || !set.contains(name)) {
                        fileArrListFiles[i2].delete();
                    }
                }
            }
        }
    }

    public static void b(String str, Set<String> set) throws Exception {
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            File[] fileArrListFiles = file.listFiles();
            int length = fileArrListFiles.length;
            for (int i2 = 0; i2 < length; i2++) {
                if (fileArrListFiles[i2].isDirectory()) {
                    b(fileArrListFiles[i2].getAbsolutePath(), set);
                } else {
                    String name = fileArrListFiles[i2].getName();
                    if (set == null || !set.contains(name)) {
                        fileArrListFiles[i2].delete();
                    }
                }
            }
        }
    }

    public static long a(File file, boolean z) {
        long length = 0;
        if (!file.exists() && file.isDirectory()) {
            return 0L;
        }
        for (File file2 : file.listFiles()) {
            if (file2.isDirectory()) {
                length += a(file2, z);
            } else if (file2.isFile()) {
                length += file2.length();
            }
        }
        return length;
    }

    public static void e(Context context, String str) {
        try {
            context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(new File(str))));
        } catch (Exception e2) {
            Logger.w(ToolUtils.TAG, "add image media exception: " + e2);
        }
    }

    public static void f(Context context, String str) {
        try {
            new C0026a(context, str).a();
        } catch (Exception unused) {
        }
    }

    /* renamed from: com.ss.android.a.a.a$a, reason: collision with other inner class name */
    public static class C0026a implements MediaScannerConnection.MediaScannerConnectionClient {
        private MediaScannerConnection a;
        private Context b;
        private String c;

        public C0026a(Context context, String str) {
            this.b = context;
            this.c = str;
        }

        public void a() {
            if (this.a != null && this.a.isConnected()) {
                this.a.disconnect();
            }
            this.a = new MediaScannerConnection(this.b, this);
            this.a.connect();
        }

        @Override // android.media.MediaScannerConnection.MediaScannerConnectionClient
        public void onMediaScannerConnected() {
            try {
                this.a.scanFile(this.c, d.e);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // android.media.MediaScannerConnection.OnScanCompletedListener
        public void onScanCompleted(String str, Uri uri) {
            if (this.a != null) {
                if (this.a.isConnected()) {
                    this.a.disconnect();
                }
                this.a = null;
            }
        }
    }

    public static boolean b(Context context, String str, String str2) throws PackageManager.NameNotFoundException {
        PackageInfo packageArchiveInfo;
        PackageInfo packageInfo;
        if (context == null || StringUtils.isEmpty(str) || StringUtils.isEmpty(str2)) {
            return false;
        }
        try {
            File file = new File(str);
            if (!file.exists() || (packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(file.getAbsolutePath(), 1)) == null || !packageArchiveInfo.packageName.equals(str2)) {
                return false;
            }
            int i2 = packageArchiveInfo.versionCode;
            try {
                packageInfo = context.getPackageManager().getPackageInfo(str2, 1);
            } catch (PackageManager.NameNotFoundException unused) {
                packageInfo = null;
            }
            if (packageInfo == null) {
                return false;
            }
            return i2 == packageInfo.versionCode;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean g(Context context, String str) throws PackageManager.NameNotFoundException {
        PackageInfo packageArchiveInfo;
        PackageInfo packageInfo;
        if (context == null || str == null || StringUtils.isEmpty(str)) {
            return false;
        }
        try {
            File file = new File(str);
            if (!file.exists() || (packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(file.getAbsolutePath(), 1)) == null) {
                return false;
            }
            String str2 = packageArchiveInfo.packageName;
            int i2 = packageArchiveInfo.versionCode;
            try {
                packageInfo = context.getPackageManager().getPackageInfo(str2, 1);
            } catch (PackageManager.NameNotFoundException unused) {
                packageInfo = null;
            }
            if (packageInfo == null) {
                return false;
            }
            return i2 <= packageInfo.versionCode;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean h(Context context, String str) {
        String strF = f(context);
        return strF != null && strF.endsWith(str);
    }

    public static boolean b(Context context) {
        String strF = f(context);
        return strF != null && strF.endsWith(d);
    }

    public static boolean c(Context context) {
        String strF = f(context);
        return strF != null && strF.endsWith(e);
    }

    public static boolean d(Context context) {
        String strF = f(context);
        return (strF == null || !strF.contains(Constants.COLON_SEPARATOR)) && strF != null && strF.equals(context.getPackageName());
    }

    public static long e(Context context) {
        if (d(context)) {
            return Thread.currentThread().getId();
        }
        return 0L;
    }

    public static String f(Context context) {
        String str = j;
        if (!StringUtils.isEmpty(str)) {
            return str;
        }
        try {
            int iMyPid = Process.myPid();
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == iMyPid) {
                    if (Logger.debug()) {
                        Logger.d("Process", "processName = " + runningAppProcessInfo.processName);
                    }
                    j = runningAppProcessInfo.processName;
                    return j;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        j = e();
        return j;
    }

    private static String e() throws Throwable {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/" + Process.myPid() + "/cmdline"), "iso-8859-1"));
            try {
                StringBuilder sb = new StringBuilder();
                while (true) {
                    int i2 = bufferedReader.read();
                    if (i2 <= 0) {
                        break;
                    }
                    sb.append((char) i2);
                }
                if (Logger.debug()) {
                    Logger.d("Process", "get processName = " + sb.toString());
                }
                String string = sb.toString();
                try {
                    bufferedReader.close();
                } catch (Exception unused) {
                }
                return string;
            } catch (Throwable unused2) {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Exception unused3) {
                    }
                }
                return null;
            }
        } catch (Throwable th) {
            th = th;
        }
    }

    public static boolean i(Context context, String str) throws SecurityException {
        ComponentName componentName;
        if (context == null || StringUtils.isEmpty(str)) {
            return false;
        }
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (Build.VERSION.SDK_INT < 21) {
            List<ActivityManager.RunningTaskInfo> runningTasks = activityManager.getRunningTasks(1);
            if (!runningTasks.isEmpty() && (componentName = runningTasks.get(0).topActivity) != null && str.equals(componentName.getPackageName())) {
                return true;
            }
        } else {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
                if (str.equals(runningAppProcessInfo.processName)) {
                    return runningAppProcessInfo.importance == 100;
                }
            }
        }
        return false;
    }

    public static void a(Context context, Intent intent, String str, Bitmap bitmap) {
        if (context == null || intent == null || StringUtils.isEmpty(str) || bitmap == null || c(context, str)) {
            return;
        }
        try {
            Intent intent2 = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
            intent2.putExtra("android.intent.extra.shortcut.NAME", str);
            intent2.putExtra("android.intent.extra.shortcut.ICON", bitmap);
            intent2.putExtra("duplicate", false);
            intent2.putExtra("android.intent.extra.shortcut.INTENT", intent);
            context.sendBroadcast(intent2);
            if (Logger.debug()) {
                Logger.d("launcher_ad", "createShortCut intent " + intent.toUri(0));
            }
        } catch (Exception unused) {
        }
    }

    public static void a(Context context, Intent intent, String str) {
        if (context == null || intent == null || StringUtils.isEmpty(str)) {
            return;
        }
        try {
            Intent intent2 = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
            intent2.putExtra("android.intent.extra.shortcut.INTENT", intent);
            intent2.putExtra("android.intent.extra.shortcut.NAME", str);
            intent2.putExtra("duplicate", false);
            context.sendBroadcast(intent2);
            if (Logger.debug()) {
                Logger.d("launcher_ad", "deleteShortCut intent " + intent.toUri(0));
            }
        } catch (Exception unused) {
        }
    }

    public static void j(Context context, String str) {
        if (context == null || StringUtils.isEmpty(str)) {
            return;
        }
        try {
            Intent intent = new Intent("android.intent.action.DIAL", Uri.parse("tel:" + Uri.encode(str)));
            if (!(context instanceof Activity)) {
                intent.setFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
            }
            context.startActivity(intent);
        } catch (Exception unused) {
        }
    }

    public static void k(Context context, String str) {
        if (context == null || StringUtils.isEmpty(str)) {
            return;
        }
        try {
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
            launchIntentForPackage.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
            context.startActivity(launchIntentForPackage);
        } catch (Exception unused) {
        }
    }

    public static String l(Context context, String str) {
        if (context == null || StringUtils.isEmpty(str)) {
            return null;
        }
        try {
            for (ProviderInfo providerInfo : context.getPackageManager().getPackageInfo(context.getPackageName(), 8).providers) {
                if (str.equals(providerInfo.name)) {
                    return providerInfo.authority;
                }
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public static String g(Context context) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.addCategory("android.intent.category.BROWSABLE");
            intent.setDataAndType(Uri.parse(UriUtil.HTTP_PREFIX), null);
            List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 65568);
            if (listQueryIntentActivities.size() <= 0) {
                return "com.android.browser/com.android.browser.BrowserActivity";
            }
            for (ResolveInfo resolveInfo : listQueryIntentActivities) {
                if (resolveInfo != null && resolveInfo.activityInfo != null && "com.android.browser".equals(resolveInfo.activityInfo.packageName)) {
                    return resolveInfo.activityInfo.packageName + "/" + resolveInfo.activityInfo.name;
                }
            }
            ResolveInfo resolveInfo2 = listQueryIntentActivities.get(0);
            if (resolveInfo2 == null || resolveInfo2.activityInfo == null) {
                return "com.android.browser/com.android.browser.BrowserActivity";
            }
            return resolveInfo2.activityInfo.packageName + "/" + resolveInfo2.activityInfo.name;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "com.android.browser/com.android.browser.BrowserActivity";
        }
    }

    public static String e(String str) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        try {
            if (Logger.debug()) {
                Logger.d("Process", "before handle = " + str);
            }
            String strReplaceAll = str.replaceAll(".." + File.separator, "");
            try {
                if (!Logger.debug()) {
                    return strReplaceAll;
                }
                Logger.d("Process", "after handle = " + strReplaceAll);
                return strReplaceAll;
            } catch (Exception unused) {
                return strReplaceAll;
            }
        } catch (Exception unused2) {
            return str;
        }
    }

    public static String h(Context context) {
        if (context == null) {
            return "";
        }
        try {
            List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(5);
            if (runningTasks == null) {
                return "";
            }
            String packageName = context.getPackageName();
            StringBuilder sb = new StringBuilder();
            for (ActivityManager.RunningTaskInfo runningTaskInfo : runningTasks) {
                if (runningTaskInfo != null && runningTaskInfo.baseActivity != null && packageName.equals(runningTaskInfo.baseActivity.getPackageName())) {
                    sb.append("id = ");
                    sb.append(runningTaskInfo.id);
                    sb.append(" ");
                    sb.append("description = ");
                    sb.append(runningTaskInfo.description);
                    sb.append(" ");
                    sb.append("number_of_activities = ");
                    sb.append(runningTaskInfo.numActivities);
                    sb.append(" ");
                    sb.append("number_of_running_activities = ");
                    sb.append(runningTaskInfo.numRunning);
                    sb.append(" ");
                    sb.append("topActivity = ");
                    sb.append(runningTaskInfo.topActivity.toString());
                    sb.append(" ");
                    sb.append("baseActivity = ");
                    sb.append(runningTaskInfo.baseActivity.toString());
                    return sb.toString();
                }
            }
            return "";
        } catch (Throwable unused) {
            return "";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x003f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0040  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String i(android.content.Context r3) throws java.lang.NullPointerException {
        /*
            if (r3 == 0) goto L48
            r0 = 0
            java.io.File r1 = r3.getCacheDir()     // Catch: java.lang.Throwable -> L39
            if (r1 == 0) goto L13
            java.io.File r3 = r3.getCacheDir()     // Catch: java.lang.Throwable -> L39
            java.lang.String r3 = r3.getPath()     // Catch: java.lang.Throwable -> L39
        L11:
            r0 = r3
            goto L39
        L13:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L39
            r1.<init>()     // Catch: java.lang.Throwable -> L39
            java.lang.String r2 = "/data/data/"
            r1.append(r2)     // Catch: java.lang.Throwable -> L39
            java.lang.String r2 = r3.getPackageName()     // Catch: java.lang.Throwable -> L39
            r1.append(r2)     // Catch: java.lang.Throwable -> L39
            java.lang.String r2 = "/cache/"
            r1.append(r2)     // Catch: java.lang.Throwable -> L39
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L39
            r2 = 0
            java.io.File r3 = r3.getDir(r1, r2)     // Catch: java.lang.Throwable -> L39
            if (r3 == 0) goto L39
            java.lang.String r3 = r3.getPath()     // Catch: java.lang.Throwable -> L39
            goto L11
        L39:
            boolean r3 = com.bytedance.common.utility.StringUtils.isEmpty(r0)
            if (r3 != 0) goto L40
            return r0
        L40:
            java.lang.NullPointerException r3 = new java.lang.NullPointerException
            java.lang.String r0 = "Cannot Create Cache Dir"
            r3.<init>(r0)
            throw r3
        L48:
            java.lang.NullPointerException r3 = new java.lang.NullPointerException
            java.lang.String r0 = "Context is NUll"
            r3.<init>(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.a.a.a.i(android.content.Context):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x003f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0040  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String j(android.content.Context r3) throws java.lang.NullPointerException {
        /*
            if (r3 == 0) goto L48
            r0 = 0
            java.io.File r1 = r3.getFilesDir()     // Catch: java.lang.Throwable -> L39
            if (r1 == 0) goto L13
            java.io.File r3 = r3.getFilesDir()     // Catch: java.lang.Throwable -> L39
            java.lang.String r3 = r3.getPath()     // Catch: java.lang.Throwable -> L39
        L11:
            r0 = r3
            goto L39
        L13:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L39
            r1.<init>()     // Catch: java.lang.Throwable -> L39
            java.lang.String r2 = "/data/data/"
            r1.append(r2)     // Catch: java.lang.Throwable -> L39
            java.lang.String r2 = r3.getPackageName()     // Catch: java.lang.Throwable -> L39
            r1.append(r2)     // Catch: java.lang.Throwable -> L39
            java.lang.String r2 = "/files/"
            r1.append(r2)     // Catch: java.lang.Throwable -> L39
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L39
            r2 = 0
            java.io.File r3 = r3.getDir(r1, r2)     // Catch: java.lang.Throwable -> L39
            if (r3 == 0) goto L39
            java.lang.String r3 = r3.getPath()     // Catch: java.lang.Throwable -> L39
            goto L11
        L39:
            boolean r3 = com.bytedance.common.utility.StringUtils.isEmpty(r0)
            if (r3 != 0) goto L40
            return r0
        L40:
            java.lang.NullPointerException r3 = new java.lang.NullPointerException
            java.lang.String r0 = "Cannot Create Files Dir"
            r3.<init>(r0)
            throw r3
        L48:
            java.lang.NullPointerException r3 = new java.lang.NullPointerException
            java.lang.String r0 = "Context is NUll"
            r3.<init>(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.a.a.a.j(android.content.Context):java.lang.String");
    }

    public static boolean c(Context context, String str, String str2) {
        if (context == null || StringUtils.isEmpty(str) || StringUtils.isEmpty(str2)) {
            return false;
        }
        try {
            List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) context.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE);
            if (runningServices == null || runningServices.size() <= 0) {
                return false;
            }
            for (ActivityManager.RunningServiceInfo runningServiceInfo : runningServices) {
                if (str.equals(runningServiceInfo.service.getPackageName()) && str2.equals(runningServiceInfo.service.getClassName())) {
                    return true;
                }
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static String k(Context context) {
        if (context == null) {
            return null;
        }
        Object systemService = context.getSystemService("user");
        if (systemService == null) {
            Logger.e(ToolUtils.TAG, "userManager not exsit !!!");
            return null;
        }
        try {
            Object objInvoke = Process.class.getMethod("myUserHandle", (Class[]) null).invoke(Process.class, (Object[]) null);
            return String.valueOf(((Long) systemService.getClass().getMethod("getSerialNumberForUser", objInvoke.getClass()).invoke(systemService, objInvoke)).longValue());
        } catch (IllegalAccessException e2) {
            Logger.e(ToolUtils.TAG, "", e2);
            return null;
        } catch (IllegalArgumentException e3) {
            Logger.e(ToolUtils.TAG, "", e3);
            return null;
        } catch (NoSuchMethodException e4) {
            Logger.e(ToolUtils.TAG, "", e4);
            return null;
        } catch (InvocationTargetException e5) {
            Logger.e(ToolUtils.TAG, "", e5);
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    @TargetApi(19)
    public static int l(Context context) {
        if (context == null) {
            return -1;
        }
        try {
            AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService("appops");
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            String packageName = context.getApplicationContext().getPackageName();
            int i2 = applicationInfo.uid;
            Reflect reflectOn = Reflect.on(appOpsManager);
            return ((Integer) reflectOn.call("checkOpNoThrow", new Class[]{Integer.TYPE, Integer.TYPE, String.class}, new Object[]{Integer.valueOf(((Integer) reflectOn.field("OP_POST_NOTIFICATION", new Class[]{Integer.TYPE}).get()).intValue()), Integer.valueOf(i2), packageName}).get()).intValue() == 0 ? 1 : 0;
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }
    }

    public static void a(Window window) {
        if (window == null) {
            return;
        }
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                window.clearFlags(201326592);
                window.getDecorView().setSystemUiVisibility(1792);
                window.addFlags(Target.SIZE_ORIGINAL);
                window.setStatusBarColor(0);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void a(boolean z) {
        k = z;
    }

    @TargetApi(21)
    public static void a(Context context, Window window, int i2, boolean z) {
        if (context == null || window == null) {
            return;
        }
        try {
            if (!k) {
                window.clearFlags(Target.SIZE_ORIGINAL);
                return;
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (c()) {
                    if (z) {
                        a(false, window);
                    } else {
                        a(true, window);
                    }
                } else if (!d()) {
                    View decorView = window.getDecorView();
                    int systemUiVisibility = decorView.getSystemUiVisibility();
                    if (Build.VERSION.SDK_INT >= 23) {
                        if (z) {
                            decorView.setSystemUiVisibility(systemUiVisibility & (-8193));
                        } else {
                            decorView.setSystemUiVisibility(systemUiVisibility | 8192);
                        }
                    }
                } else if (z) {
                    b(false, window);
                } else {
                    b(true, window);
                }
                window.clearFlags(DownloadExpSwitchCode.BACK_BUGFIX_SIGBUS);
                window.addFlags(Target.SIZE_ORIGINAL);
                window.setStatusBarColor(context.getResources().getColor(i2));
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static void a(boolean z, Window window) {
        try {
            Class<?> cls = window.getClass();
            Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            int i2 = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
            Method method = cls.getMethod("setExtraFlags", Integer.TYPE, Integer.TYPE);
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(z ? i2 : 0);
            objArr[1] = Integer.valueOf(i2);
            method.invoke(window, objArr);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static void b(boolean z, Window window) {
        try {
            WindowManager.LayoutParams attributes = window.getAttributes();
            Field declaredField = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
            if (z) {
                declaredField.set(attributes, Integer.valueOf(declaredField.getInt(attributes) | 512));
            } else {
                declaredField.set(attributes, Integer.valueOf(declaredField.getInt(attributes) & (-513)));
            }
            window.setAttributes(attributes);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
