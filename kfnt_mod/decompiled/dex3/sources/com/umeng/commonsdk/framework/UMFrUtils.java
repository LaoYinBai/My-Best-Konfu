package com.umeng.commonsdk.framework;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.proguard.o;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.message.MsgConstant;
import com.xiaomi.mipush.sdk.Constants;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class UMFrUtils {
    private static final String KEY_LAST_INSTANT_SUCC_BUILD_TIME = "last_instant_build_time";
    private static final String KEY_LAST_SUCC_BUILD_TIME = "last_successful_build_time";
    private static Object mEnvelopeBuildTimeLock = new Object();
    private static String mDefaultEnvelopeDir = "envelope";
    private static String mDefaultEnvelopeDirPath = null;
    private static Object mEnvelopeFileLock = new Object();

    public static String getCurrentProcessName(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        try {
            int iMyPid = Process.myPid();
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null || runningAppProcesses.size() <= 0) {
                return "";
            }
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == iMyPid) {
                    return runningAppProcessInfo.processName;
                }
            }
            return "";
        } catch (Throwable th) {
            UMCrashManager.reportCrash(context.getApplicationContext(), th);
            return "";
        }
    }

    public static String getSubProcessName(Context context) {
        String strSubstring;
        strSubstring = "";
        try {
            String currentProcessName = getCurrentProcessName(context);
            int iIndexOf = currentProcessName.indexOf(Constants.COLON_SEPARATOR);
            strSubstring = iIndexOf >= 0 ? currentProcessName.substring(iIndexOf + 1) : "";
            return iIndexOf < 0 ? currentProcessName.substring(context.getPackageName().length() + 1, currentProcessName.length()) : strSubstring;
        } catch (Throwable th) {
            UMCrashManager.reportCrash(context.getApplicationContext(), th);
            return strSubstring;
        }
    }

    private static boolean checkPermission(Context context, String str) {
        if (context == null) {
            return false;
        }
        Context applicationContext = context.getApplicationContext();
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                if (((Integer) Class.forName("android.content.Context").getMethod("checkSelfPermission", String.class).invoke(context, str)).intValue() != 0) {
                    return false;
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(applicationContext, th);
                return false;
            }
        } else {
            try {
                if (applicationContext.getPackageManager().checkPermission(str, applicationContext.getPackageName()) != 0) {
                    return false;
                }
            } catch (Throwable th2) {
                UMCrashManager.reportCrash(applicationContext, th2);
                return false;
            }
        }
        return true;
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        try {
            if (!checkPermission(context, MsgConstant.PERMISSION_ACCESS_NETWORK_STATE) || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
                return false;
            }
            return activeNetworkInfo.isConnectedOrConnecting();
        } catch (Throwable th) {
            UMCrashManager.reportCrash(context.getApplicationContext(), th);
            return false;
        }
    }

    public static int envelopeFileNumber(Context context) {
        File file;
        String[] list;
        if (context == null) {
            return 0;
        }
        try {
            file = new File(getEnvelopeDirPath(context));
        } catch (Throwable th) {
            UMCrashManager.reportCrash(context, th);
        }
        synchronized (mEnvelopeFileLock) {
            if (!file.isDirectory() || (list = file.list()) == null) {
                return 0;
            }
            return list.length;
        }
    }

    private static long getDistanceDays(long j, long j2) {
        return (j < j2 ? j2 - j : j - j2) / 86400000;
    }

    public static void removeRedundantEnvelopeFiles(final Context context, int i) {
        File file = new File(getEnvelopeDirPath(context));
        synchronized (mEnvelopeFileLock) {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles != null && fileArrListFiles.length > i) {
                Arrays.sort(fileArrListFiles, new Comparator<File>() { // from class: com.umeng.commonsdk.framework.UMFrUtils.1
                    @Override // java.util.Comparator
                    /* renamed from: a, reason: merged with bridge method [inline-methods] */
                    public int compare(File file2, File file3) {
                        String name = file2.getName();
                        String name2 = file3.getName();
                        String createTimeFromFileName = UMFrUtils.getCreateTimeFromFileName(name);
                        String createTimeFromFileName2 = UMFrUtils.getCreateTimeFromFileName(name2);
                        if (TextUtils.isEmpty(createTimeFromFileName) || TextUtils.isEmpty(createTimeFromFileName2)) {
                            return 1;
                        }
                        try {
                            long jLongValue = Long.valueOf(createTimeFromFileName).longValue() - Long.valueOf(createTimeFromFileName2).longValue();
                            if (jLongValue > 0) {
                                return 1;
                            }
                            return jLongValue == 0 ? 0 : -1;
                        } catch (NumberFormatException e) {
                            UMCrashManager.reportCrash(context, e);
                            return 1;
                        }
                    }
                });
                if (fileArrListFiles.length > i) {
                    for (int i2 = 0; i2 < fileArrListFiles.length - i; i2++) {
                        try {
                            if (!fileArrListFiles[i2].delete()) {
                                ULog.d("--->>> remove [" + i2 + "] file fail.");
                            }
                        } catch (Throwable th) {
                            UMCrashManager.reportCrash(context, th);
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getCreateTimeFromFileName(String str) {
        Context appContext = UMModuleRegister.getAppContext();
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int iIndexOf = str.indexOf(95) + 1;
        try {
            return str.substring(iIndexOf, str.indexOf(95, iIndexOf));
        } catch (IndexOutOfBoundsException e) {
            UMCrashManager.reportCrash(appContext, e);
            return "";
        }
    }

    public static File getEnvelopeFile(final Context context) {
        if (context == null) {
            return null;
        }
        File file = new File(getEnvelopeDirPath(context));
        synchronized (mEnvelopeFileLock) {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles != null && fileArrListFiles.length != 0) {
                Arrays.sort(fileArrListFiles, new Comparator<File>() { // from class: com.umeng.commonsdk.framework.UMFrUtils.2
                    @Override // java.util.Comparator
                    /* renamed from: a, reason: merged with bridge method [inline-methods] */
                    public int compare(File file2, File file3) {
                        String name = file2.getName();
                        String name2 = file3.getName();
                        String createTimeFromFileName = UMFrUtils.getCreateTimeFromFileName(name);
                        String createTimeFromFileName2 = UMFrUtils.getCreateTimeFromFileName(name2);
                        if (TextUtils.isEmpty(createTimeFromFileName) || TextUtils.isEmpty(createTimeFromFileName2)) {
                            return 1;
                        }
                        try {
                            long jLongValue = Long.valueOf(createTimeFromFileName).longValue() - Long.valueOf(createTimeFromFileName2).longValue();
                            if (jLongValue > 0) {
                                return 1;
                            }
                            return jLongValue == 0 ? 0 : -1;
                        } catch (NumberFormatException e) {
                            UMCrashManager.reportCrash(context, e);
                            return 1;
                        }
                    }
                });
                return fileArrListFiles[0];
            }
            return null;
        }
    }

    public static void syncLegacyEnvelopeIfNeeded(Context context) {
        String legacyEnvelopeDir;
        if (context == null) {
            return;
        }
        try {
            legacyEnvelopeDir = getLegacyEnvelopeDir(context);
        } catch (Throwable th) {
            UMCrashManager.reportCrash(context, th);
        }
        if (TextUtils.isEmpty(legacyEnvelopeDir) || legacyEnvelopeDir.equals(mDefaultEnvelopeDir)) {
            return;
        }
        File file = new File(context.getFilesDir().getAbsolutePath() + "/." + legacyEnvelopeDir);
        if (file.exists()) {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null || fileArrListFiles.length == 0) {
                try {
                    if (file.isDirectory()) {
                        file.delete();
                        return;
                    }
                    return;
                } catch (Throwable th2) {
                    UMCrashManager.reportCrash(context, th2);
                    return;
                }
            }
            try {
                String envelopeDirPath = getEnvelopeDirPath(context);
                for (int i = 0; i < fileArrListFiles.length; i++) {
                    fileArrListFiles[i].renameTo(new File(envelopeDirPath + "/" + fileArrListFiles[i].getName()));
                }
                if (file.isDirectory()) {
                    file.delete();
                    return;
                }
                return;
            } catch (Throwable th3) {
                UMCrashManager.reportCrash(context, th3);
                return;
            }
            UMCrashManager.reportCrash(context, th);
        }
    }

    public static String getLegacyEnvelopeDir(Context context) throws Throwable {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager != null) {
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses == null) {
                ULog.d("--->>> getEnvelopeDir: can't get process name, use default envelope directory.");
                return mDefaultEnvelopeDir;
            }
            if (runningAppProcesses.size() == 0) {
                return mDefaultEnvelopeDir;
            }
            try {
                for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                    if (runningAppProcessInfo.pid == Process.myPid()) {
                        String strReplace = runningAppProcessInfo.processName.replace(':', '_');
                        ULog.d("--->>> getEnvelopeDir: use current process name as envelope directory.");
                        return strReplace;
                    }
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(context, th);
            }
        }
        return mDefaultEnvelopeDir;
    }

    public static String getEnvelopeDirPath(Context context) {
        String str;
        File file;
        synchronized (mEnvelopeFileLock) {
            try {
                if (mDefaultEnvelopeDirPath == null) {
                    mDefaultEnvelopeDirPath = context.getFilesDir().getAbsolutePath() + "/." + mDefaultEnvelopeDir;
                }
                file = new File(mDefaultEnvelopeDirPath);
            } catch (Throwable th) {
                UMCrashManager.reportCrash(context, th);
            }
            if (!file.exists() && !file.mkdir()) {
                ULog.d("--->>> Create Envelope Directory failed!!!");
                str = mDefaultEnvelopeDirPath;
            } else {
                str = mDefaultEnvelopeDirPath;
            }
        }
        return str;
    }

    public static long getLastSuccessfulBuildTime(Context context) {
        long j;
        synchronized (mEnvelopeBuildTimeLock) {
            j = PreferenceWrapper.getDefault(context).getLong(KEY_LAST_SUCC_BUILD_TIME, 0L);
        }
        return j;
    }

    public static long getLastInstantBuildTime(Context context) {
        long j;
        synchronized (mEnvelopeBuildTimeLock) {
            j = PreferenceWrapper.getDefault(context).getLong(KEY_LAST_INSTANT_SUCC_BUILD_TIME, 0L);
        }
        return j;
    }

    private static void updateLastSuccessfulBuildTime(Context context) {
        synchronized (mEnvelopeBuildTimeLock) {
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
            sharedPreferences.edit().putLong(KEY_LAST_SUCC_BUILD_TIME, System.currentTimeMillis()).commit();
        }
    }

    private static void updateLastInstantBuildTime(Context context) {
        synchronized (mEnvelopeBuildTimeLock) {
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
            sharedPreferences.edit().putLong(KEY_LAST_INSTANT_SUCC_BUILD_TIME, System.currentTimeMillis()).commit();
        }
    }

    public static int saveEnvelopeFile(Context context, String str, byte[] bArr) {
        FileOutputStream fileOutputStream;
        if (bArr == null) {
            return 101;
        }
        File file = new File(getEnvelopeDirPath(context) + "/" + str);
        synchronized (mEnvelopeFileLock) {
            FileOutputStream fileOutputStream2 = null;
            try {
                try {
                    fileOutputStream = new FileOutputStream(file);
                } catch (Throwable th) {
                    th = th;
                }
            } catch (IOException e) {
                e = e;
            }
            try {
                fileOutputStream.write(bArr);
                fileOutputStream.close();
                boolean zA = com.umeng.commonsdk.statistics.internal.a.a(context).a(str);
                boolean zB = com.umeng.commonsdk.statistics.internal.a.a(context).b(str);
                if (zA) {
                    updateLastSuccessfulBuildTime(context);
                }
                if (zB) {
                    updateLastInstantBuildTime(context);
                }
                return 0;
            } catch (IOException e2) {
                e = e2;
                fileOutputStream2 = fileOutputStream;
                UMCrashManager.reportCrash(context, e);
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (Throwable th2) {
                        UMCrashManager.reportCrash(context, th2);
                    }
                }
                return 101;
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream2 = fileOutputStream;
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (Throwable th4) {
                        UMCrashManager.reportCrash(context, th4);
                    }
                }
                throw th;
            }
        }
    }

    public static boolean removeEnvelopeFile(File file) {
        Context appContext = UMModuleRegister.getAppContext();
        synchronized (mEnvelopeFileLock) {
            try {
                if (file != null) {
                    try {
                        if (file.exists()) {
                            return file.delete();
                        }
                    } catch (Throwable th) {
                        UMCrashManager.reportCrash(appContext, th);
                    }
                }
                return true;
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v3 */
    public static byte[] toByteArray(String str) throws IOException {
        Throwable th;
        IOException e;
        byte[] bArr;
        Context appContext = UMModuleRegister.getAppContext();
        synchronized (mEnvelopeFileLock) {
            try {
                try {
                    FileChannel channel = new RandomAccessFile(str, "r").getChannel();
                    try {
                        MappedByteBuffer mappedByteBufferLoad = channel.map(FileChannel.MapMode.READ_ONLY, 0L, channel.size()).load();
                        bArr = new byte[(int) channel.size()];
                        if (mappedByteBufferLoad.remaining() > 0) {
                            mappedByteBufferLoad.get(bArr, 0, mappedByteBufferLoad.remaining());
                        }
                        try {
                            channel.close();
                        } catch (Throwable th2) {
                            UMCrashManager.reportCrash(appContext, th2);
                        }
                    } catch (IOException e2) {
                        e = e2;
                        UMCrashManager.reportCrash(appContext, e);
                        throw e;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    try {
                        str.close();
                    } catch (Throwable th4) {
                        UMCrashManager.reportCrash(appContext, th4);
                    }
                    throw th;
                }
            } catch (IOException e3) {
                e = e3;
            } catch (Throwable th5) {
                th = th5;
                str = 0;
                str.close();
                throw th;
            }
        }
        return bArr;
    }

    public static boolean hasEnvelopeFile(Context context, UMLogDataProtocol.UMBusinessType uMBusinessType) {
        File[] fileArrListFiles;
        String str = o.ap;
        if (uMBusinessType == UMLogDataProtocol.UMBusinessType.U_INTERNAL) {
            str = o.au;
        }
        String envelopeDirPath = getEnvelopeDirPath(context);
        if (envelopeDirPath == null) {
            return false;
        }
        File file = new File(envelopeDirPath);
        synchronized (mEnvelopeFileLock) {
            try {
                try {
                    fileArrListFiles = file.listFiles();
                } catch (Throwable th) {
                    UMCrashManager.reportCrash(context, th);
                }
                if (fileArrListFiles != null && fileArrListFiles.length != 0) {
                    for (File file2 : fileArrListFiles) {
                        if (file2.getName().startsWith(str)) {
                            return true;
                        }
                    }
                    return false;
                }
                return false;
            } finally {
            }
        }
    }
}
