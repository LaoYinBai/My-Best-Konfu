package com.kwai.filedownloader.e;

import android.app.ActivityManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.text.TextUtils;
import com.kwai.filedownloader.exception.FileDownloadGiveUpRetryException;
import com.liulishuo.filedownloader.database.SqliteDatabaseImpl;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.mbridge.msdk.foundation.tools.SameMD5;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f {
    private static int a = 65536;
    private static long b = 2000;
    private static String c;
    private static Boolean d;
    private static Boolean e;
    private static final Pattern f = Pattern.compile("attachment;\\s*filename\\s*=\\s*\"([^\"]*)\"");

    public static int a() {
        return a;
    }

    public static int a(String str, String str2, boolean z) {
        return com.kwai.filedownloader.download.b.a().b().a(str, str2, z);
    }

    public static String a(int i, com.kwai.filedownloader.kwai.b bVar) {
        if (bVar == null) {
            throw new RuntimeException("connection is null when findEtag");
        }
        String strA = bVar.a("Etag");
        if (d.a) {
            d.c(f.class, "etag find %s for task(%d)", strA, Integer.valueOf(i));
        }
        return strA;
    }

    public static String a(com.kwai.filedownloader.kwai.b bVar, String str) {
        String strG = g(bVar.a("Content-Disposition"));
        if (TextUtils.isEmpty(strG)) {
            strG = c(str);
        }
        return strG.replaceAll("\\/", "_");
    }

    public static String a(String str, String str2) {
        if (str2 == null) {
            throw new IllegalStateException("can't generate real path, the file name is null");
        }
        if (str != null) {
            return a("%s%s%s", str, File.separator, str2);
        }
        throw new IllegalStateException("can't generate real path, the directory is null");
    }

    public static String a(String str, boolean z, String str2) {
        if (str == null) {
            return null;
        }
        if (!z) {
            return str;
        }
        if (str2 == null) {
            return null;
        }
        return a(str, str2);
    }

    public static String a(String str, Object... objArr) {
        return String.format(Locale.ENGLISH, str, objArr);
    }

    public static void a(int i) throws IllegalAccessException {
        if (!a(c.a())) {
            throw new IllegalAccessException("This value is used in the :filedownloader process, so set this value in your process is without effect. You can add 'process.non-separate=true' in 'filedownloader.properties' to share the main process to FileDownloadService. Or you can configure this value in 'filedownloader.properties' by 'download.min-progress-step'.");
        }
        a = i;
    }

    public static void a(long j) throws IllegalAccessException {
        if (!a(c.a())) {
            throw new IllegalAccessException("This value is used in the :filedownloader process, so set this value in your process is without effect. You can add 'process.non-separate=true' in 'filedownloader.properties' to share the main process to FileDownloadService. Or you can configure this value in 'filedownloader.properties' by 'download.min-progress-time'.");
        }
        b = j;
    }

    public static boolean a(int i, com.kwai.filedownloader.c.c cVar) {
        return a(i, cVar, (Boolean) null);
    }

    public static boolean a(int i, com.kwai.filedownloader.c.c cVar, Boolean bool) {
        if (cVar == null) {
            if (d.a) {
                d.c(f.class, "can't continue %d model == null", Integer.valueOf(i));
            }
            return false;
        }
        if (cVar.e() != null) {
            return a(i, cVar, cVar.e(), bool);
        }
        if (d.a) {
            d.c(f.class, "can't continue %d temp path == null", Integer.valueOf(i));
        }
        return false;
    }

    public static boolean a(int i, com.kwai.filedownloader.c.c cVar, String str, Boolean bool) {
        Class<f> cls;
        String str2;
        Object[] objArr;
        if (str != null) {
            File file = new File(str);
            boolean zExists = file.exists();
            boolean zIsDirectory = file.isDirectory();
            if (!zExists || zIsDirectory) {
                if (!d.a) {
                    return false;
                }
                d.c(f.class, "can't continue %d file not suit, exists[%B], directory[%B]", Integer.valueOf(i), Boolean.valueOf(zExists), Boolean.valueOf(zIsDirectory));
                return false;
            }
            long length = file.length();
            long jG = cVar.g();
            if (cVar.n() > 1 || jG != 0) {
                long jH = cVar.h();
                if (length < jG || (jH != -1 && (length > jH || jG >= jH))) {
                    if (!d.a) {
                        return false;
                    }
                    d.c(f.class, "can't continue %d dirty data fileLength[%d] sofar[%d] total[%d]", Integer.valueOf(i), Long.valueOf(length), Long.valueOf(jG), Long.valueOf(jH));
                    return false;
                }
                if (bool == null || bool.booleanValue() || jH != length) {
                    return true;
                }
                if (!d.a) {
                    return false;
                }
                cls = f.class;
                str2 = "can't continue %d, because of the output stream doesn't support seek, but the task has already pre-allocated, so we only can download it from the very beginning.";
                objArr = new Object[]{Integer.valueOf(i)};
            } else {
                if (!d.a) {
                    return false;
                }
                cls = f.class;
                str2 = "can't continue %d the downloaded-record is zero.";
                objArr = new Object[]{Integer.valueOf(i)};
            }
        } else {
            if (!d.a) {
                return false;
            }
            cls = f.class;
            str2 = "can't continue %d path = null";
            objArr = new Object[]{Integer.valueOf(i)};
        }
        d.c(cls, str2, objArr);
        return false;
    }

    public static boolean a(long j, long j2) {
        return j > ((long) a()) && j2 > b();
    }

    public static boolean a(Context context) {
        Class<f> cls;
        String str;
        if (d == null) {
            boolean zEndsWith = false;
            if (!e.a().d) {
                int iMyPid = Process.myPid();
                ActivityManager activityManager = (ActivityManager) context.getSystemService(PushConstants.INTENT_ACTIVITY_NAME);
                if (activityManager != null) {
                    List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
                    if (runningAppProcesses != null && !runningAppProcesses.isEmpty()) {
                        Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            ActivityManager.RunningAppProcessInfo next = it.next();
                            if (next.pid == iMyPid) {
                                zEndsWith = next.processName.endsWith(":filedownloader");
                                break;
                            }
                        }
                    } else {
                        cls = f.class;
                        str = "The running app process info list from ActivityManager is null or empty, maybe current App is not running.";
                    }
                } else {
                    cls = f.class;
                    str = "fail to get the activity manager!";
                }
                d.d(cls, str, new Object[0]);
                return false;
            }
            zEndsWith = true;
            d = Boolean.valueOf(zEndsWith);
        }
        return d.booleanValue();
    }

    public static boolean a(String str) {
        return true;
    }

    public static int b(String str, String str2) {
        return com.kwai.filedownloader.download.b.a().b().a(str, str2, false);
    }

    public static long b() {
        return b;
    }

    public static long b(int i, com.kwai.filedownloader.kwai.b bVar) {
        long jK = k(bVar.a("Content-Length"));
        String strA = bVar.a("Transfer-Encoding");
        if (jK >= 0) {
            return jK;
        }
        if (strA != null && strA.equals("chunked")) {
            return -1L;
        }
        if (!e.a().c) {
            throw new FileDownloadGiveUpRetryException("can't know the size of the download file, and its Transfer-Encoding is not Chunked either.\nyou can ignore such exception by add http.lenient=true to the filedownloader.properties");
        }
        if (!d.a) {
            return -1L;
        }
        d.c(f.class, "%d response header is not legal but HTTP lenient is true, so handle as the case of transfer encoding chunk", Integer.valueOf(i));
        return -1L;
    }

    public static String b(String str) {
        return a(c(), c(str));
    }

    public static void b(Context context) throws IOException {
        File fileC = c(context);
        try {
            fileC.getParentFile().mkdirs();
            fileC.createNewFile();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static File c(Context context) {
        return new File(context.getFilesDir().getAbsolutePath() + File.separator + SqliteDatabaseImpl.TABLE_NAME, ".old_file_converted");
    }

    public static String c() {
        if (TextUtils.isEmpty(c)) {
            return (c.a().getExternalCacheDir() == null ? Environment.getDownloadCacheDirectory() : c.a().getExternalCacheDir()).getAbsolutePath();
        }
        return c;
    }

    public static String c(String str) {
        return e(str);
    }

    public static void c(String str, String str2) {
        m(str2);
        n(str);
    }

    public static String d(String str) {
        return a("%s.temp", str);
    }

    public static boolean d() {
        ConnectivityManager connectivityManager = (ConnectivityManager) c.a().getSystemService("connectivity");
        if (connectivityManager == null) {
            d.d(f.class, "failed to get connectivity manager!", new Object[0]);
            return true;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo == null || activeNetworkInfo.getType() != 1;
    }

    public static String e() {
        return a("FileDownloader/%s", "3.3.17");
    }

    public static String e(String str) {
        try {
            byte[] bArrDigest = MessageDigest.getInstance(SameMD5.TAG).digest(str.getBytes(C.UTF8_NAME));
            StringBuilder sb = new StringBuilder(bArrDigest.length * 2);
            for (byte b2 : bArrDigest) {
                int i = b2 & FileDownloadStatus.error;
                if (i < 16) {
                    sb.append(PushConstants.PUSH_TYPE_NOTIFY);
                }
                sb.append(Integer.toHexString(i));
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e2);
        } catch (NoSuchAlgorithmException e3) {
            throw new RuntimeException("Huh, MD5 should be supported?", e3);
        }
    }

    public static long f(String str) {
        return Build.VERSION.SDK_INT >= 18 ? new StatFs(str).getAvailableBytes() : r0.getAvailableBlocks() * r0.getBlockSize();
    }

    public static String g(String str) {
        if (str == null) {
            return null;
        }
        try {
            Matcher matcher = f.matcher(str);
            if (matcher.find()) {
                return matcher.group(1);
            }
        } catch (IllegalStateException unused) {
        }
        return null;
    }

    public static String h(String str) {
        int length = str.length();
        int i = (File.separatorChar == '\\' && length > 2 && str.charAt(1) == ':') ? 2 : 0;
        int iLastIndexOf = str.lastIndexOf(File.separatorChar);
        int i2 = (iLastIndexOf != -1 || i <= 0) ? iLastIndexOf : 2;
        if (i2 == -1 || str.charAt(length - 1) == File.separatorChar) {
            return null;
        }
        return (str.indexOf(File.separatorChar) == i2 && str.charAt(i) == File.separatorChar) ? str.substring(0, i2 + 1) : str.substring(0, i2);
    }

    public static String i(String str) {
        return "FileDownloader-" + str;
    }

    public static boolean j(String str) {
        return c.a().checkCallingOrSelfPermission(str) == 0;
    }

    public static long k(String str) {
        if (str == null) {
            return -1L;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }

    public static com.kwai.filedownloader.d.a l(String str) throws IOException {
        if (TextUtils.isEmpty(str)) {
            throw new RuntimeException("found invalid internal destination path, empty");
        }
        if (!a(str)) {
            throw new RuntimeException(a("found invalid internal destination filename %s", str));
        }
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            file.delete();
            if (!file.createNewFile()) {
                throw new RuntimeException(a("found invalid internal destination path[%s], & path is directory[%B]", str, Boolean.valueOf(file.isDirectory())));
            }
        }
        if (file.exists() || file.createNewFile()) {
            return com.kwai.filedownloader.download.b.a().a(file);
        }
        throw new IOException(a("create new file error  %s", file.getAbsolutePath()));
    }

    public static void m(String str) {
        if (str != null) {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public static void n(String str) {
        if (str != null) {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
