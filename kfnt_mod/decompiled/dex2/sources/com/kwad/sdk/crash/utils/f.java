package com.kwad.sdk.crash.utils;

import android.content.Context;
import android.os.Build;
import android.os.Debug;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.os.StatFs;
import android.system.Os;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.measurement.AppMeasurement;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.kuaishou.weapon.adsdk.DeviceInfo;
import com.kwad.sdk.crash.model.message.DiskInfo;
import com.kwad.sdk.crash.model.message.ExceptionMessage;
import com.kwad.sdk.crash.model.message.JavaExceptionMessage;
import com.kwad.sdk.crash.model.message.MemoryInfo;
import com.kwad.sdk.crash.model.message.ThreadInfo;
import com.kwad.sdk.crash.utils.SystemUtil;
import com.kwad.sdk.utils.AbiUtil;
import com.kwad.sdk.utils.s;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.SyncFailedException;
import java.io.Writer;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class f {
    private static final File a = new File("/proc/self/fd");
    private static final File b = new File("/proc/self/task");

    private static int a() {
        File[] fileArrListFiles;
        Objects.requireNonNull(a);
        if (a.exists() && a.isDirectory() && (fileArrListFiles = a.listFiles()) != null) {
            return fileArrListFiles.length;
        }
        return 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v9, types: [java.io.InputStream] */
    private static String a(Context context) throws Throwable {
        Throwable th;
        Throwable th2;
        InputStream inputStreamOpen;
        JSONException e;
        IOException e2;
        try {
            try {
                inputStreamOpen = context.getResources().getAssets().open("apk.json");
                try {
                    String string = new JSONObject(h.a(inputStreamOpen)).getString(PushConstants.TASK_ID);
                    b.a(inputStreamOpen);
                    return string;
                } catch (IOException e3) {
                    e2 = e3;
                    com.kwad.sdk.core.d.a.b(e2);
                    b.a(inputStreamOpen);
                    return "";
                } catch (JSONException e4) {
                    e = e4;
                    com.kwad.sdk.core.d.a.b(e);
                    b.a(inputStreamOpen);
                    return "";
                } catch (Throwable th3) {
                    th2 = th3;
                    com.kwad.sdk.core.d.a.b(th2);
                    b.a(inputStreamOpen);
                    return "";
                }
            } catch (Throwable th4) {
                th = th4;
                b.a((InputStream) context);
                throw th;
            }
        } catch (IOException e5) {
            e2 = e5;
            inputStreamOpen = null;
        } catch (JSONException e6) {
            e = e6;
            inputStreamOpen = null;
        } catch (Throwable th5) {
            th = th5;
            context = 0;
            b.a((InputStream) context);
            throw th;
        }
    }

    public static String a(String str) {
        return str.contains(".") ? str.substring(0, str.lastIndexOf(46)) : str;
    }

    public static String a(@NonNull String str, String str2) {
        Objects.requireNonNull(str);
        return !str.endsWith(str2) ? str : str.substring(0, str.lastIndexOf(str2));
    }

    public static String a(Throwable th) throws Throwable {
        StringWriter stringWriter;
        String string = th.toString();
        StringWriter stringWriter2 = null;
        try {
            try {
                stringWriter = new StringWriter();
            } catch (Exception e) {
                e = e;
            }
        } catch (Throwable th2) {
            th = th2;
            stringWriter = stringWriter2;
        }
        try {
            e.a(th, new PrintWriter(stringWriter));
            String string2 = stringWriter.toString();
            b.a((Writer) stringWriter);
            return string2;
        } catch (Exception e2) {
            e = e2;
            stringWriter2 = stringWriter;
            com.kwad.sdk.core.d.a.b(e);
            b.a((Writer) stringWriter2);
            return string;
        } catch (Throwable th3) {
            th = th3;
            b.a((Writer) stringWriter);
            throw th;
        }
    }

    public static String a(StackTraceElement[] stackTraceElementArr) {
        return a(stackTraceElementArr, 0);
    }

    public static String a(StackTraceElement[] stackTraceElementArr, int i) {
        if (stackTraceElementArr == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
            for (int i2 = 0; i2 < i; i2++) {
                sb.append("\t");
            }
            sb.append("at ");
            sb.append(stackTraceElement);
            sb.append('\n');
        }
        return sb.substring(0);
    }

    private static void a(@NonNull ExceptionMessage exceptionMessage) {
        exceptionMessage.mVirtualApp = com.kwad.sdk.crash.d.a().d();
        exceptionMessage.mVersionCode = com.kwad.sdk.crash.d.a().e();
    }

    public static void a(ExceptionMessage exceptionMessage, int i) {
        com.kwad.sdk.crash.f fVarG = com.kwad.sdk.crash.d.a().g();
        if (fVarG == null) {
            com.kwad.sdk.core.d.a.a(RemoteMessageConst.Notification.TAG, "getter is null!");
            return;
        }
        com.kwad.sdk.crash.model.message.a aVarA = fVarG.a(i);
        if (aVarA != null) {
            exceptionMessage.mCustomMsg = aVarA.toString();
        }
    }

    private static void a(@NonNull ExceptionMessage exceptionMessage, @Nullable Context context) throws Throwable {
        if (exceptionMessage instanceof JavaExceptionMessage) {
            if ("Unknown".equals(exceptionMessage.mThreadName)) {
                exceptionMessage.mThreadName = Thread.currentThread().getName();
            }
            exceptionMessage.mTid = Process.myTid();
        }
        if (context != null) {
            String strA = SystemUtil.a(context);
            if (!TextUtils.isEmpty(strA)) {
                exceptionMessage.mProcessName = strA;
            }
        }
        exceptionMessage.mPid = Process.myPid();
        exceptionMessage.mCurrentTimeStamp = System.currentTimeMillis();
        exceptionMessage.mUsageTimeMills = com.kwad.sdk.crash.d.a().h();
        exceptionMessage.mAbi = AbiUtil.b() ? "arm64" : "arm";
        exceptionMessage.mVersionConflict = TextUtils.equals(exceptionMessage.mVersionCode, "3.3.17");
        exceptionMessage.mBuildConfigInfo = b(context);
        a(exceptionMessage);
        b(exceptionMessage, context);
        exceptionMessage.mTaskId = a(context);
    }

    private static void a(ExceptionMessage exceptionMessage, DiskInfo diskInfo, @Nullable Context context) {
        File externalStorageDirectory;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            if (com.kwad.sdk.b.a.booleanValue()) {
                diskInfo.mDataTotalGB = DeviceInfo.getDataTotalGB();
            } else {
                diskInfo.mDataTotalGB = BigDecimal.valueOf((statFs.getTotalBytes() >> 20) / 1024.0f).setScale(2, 4).floatValue();
            }
            if (!com.kwad.sdk.core.config.b.a(1024L)) {
                if (com.kwad.sdk.b.a.booleanValue()) {
                    diskInfo.mDataAvailableGB = DeviceInfo.getDataAvailableGB();
                } else {
                    diskInfo.mDataAvailableGB = BigDecimal.valueOf((statFs.getAvailableBytes() >> 20) / 1024.0f).setScale(2, 4).floatValue();
                }
            }
            if ("mounted".equals(Environment.getExternalStorageState()) && (externalStorageDirectory = Environment.getExternalStorageDirectory()) != null) {
                StatFs statFs2 = new StatFs(externalStorageDirectory.getPath());
                if (com.kwad.sdk.b.a.booleanValue()) {
                    diskInfo.mExternalStorageTotalGB = DeviceInfo.getExternalStorageTotalGB();
                } else {
                    diskInfo.mExternalStorageTotalGB = BigDecimal.valueOf((statFs2.getTotalBytes() >> 20) / 1024.0f).setScale(2, 4).floatValue();
                }
                if (!com.kwad.sdk.core.config.b.a(1024L)) {
                    if (com.kwad.sdk.b.a.booleanValue()) {
                        diskInfo.mExternalStorageAvailableGB = DeviceInfo.getExternalStorageAvailableGB();
                    } else {
                        diskInfo.mExternalStorageAvailableGB = BigDecimal.valueOf((statFs2.getAvailableBytes() >> 20) / 1024.0f).setScale(2, 4).floatValue();
                    }
                }
            }
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.b(e);
        }
        exceptionMessage.mDiskInfo = diskInfo.toJson().toString();
    }

    private static void a(ExceptionMessage exceptionMessage, MemoryInfo memoryInfo, @Nullable Context context) throws Throwable {
        List<String> list;
        String canonicalPath;
        SystemUtil.a aVarC = SystemUtil.c();
        aVarC.e = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        aVarC.a = SystemUtil.a();
        aVarC.d = Debug.getPss();
        memoryInfo.mTotalMB = (int) (aVarC.a / 1048576);
        memoryInfo.mJavaHeapLimitMB = (int) (com.kwad.sdk.crash.c.a / 1048576.0d);
        memoryInfo.mJavaHeapMB = (int) (aVarC.e / 1048576);
        memoryInfo.mVssMB = (int) (aVarC.b / 1024);
        memoryInfo.mRssMB = (int) (aVarC.c / 1024);
        memoryInfo.mPssMB = (int) (aVarC.d / 1024);
        memoryInfo.mThreadsCount = aVarC.f;
        memoryInfo.mFdCount = a();
        if (context != null) {
            memoryInfo.mAvailableMB = (int) (SystemUtil.c(context) / 1048576);
        }
        exceptionMessage.mFdOverflow = "False";
        if (memoryInfo.mFdCount > 800) {
            exceptionMessage.mCrashType = exceptionMessage.getTypeFdOOM();
            exceptionMessage.mFdOverflow = "True";
            File[] fileArrListFiles = a.listFiles();
            if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                for (File file : fileArrListFiles) {
                    try {
                        if (Build.VERSION.SDK_INT >= 21) {
                            list = memoryInfo.mFds;
                            canonicalPath = Os.readlink(file.getPath());
                        } else {
                            list = memoryInfo.mFds;
                            canonicalPath = file.getCanonicalPath();
                        }
                        list.add(canonicalPath);
                    } catch (Exception e) {
                        com.kwad.sdk.core.d.a.b(e);
                    }
                }
                Collections.sort(memoryInfo.mFds);
            }
        }
        exceptionMessage.mThreadOverflow = "False";
        if (aVarC.f > 400) {
            exceptionMessage.mCrashType = exceptionMessage.getTypeThreadOOM();
            exceptionMessage.mThreadOverflow = "True";
            a(memoryInfo);
            Collections.sort(memoryInfo.mAllThreads, new Comparator<ThreadInfo>() { // from class: com.kwad.sdk.crash.utils.f.1
                @Override // java.util.Comparator
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public int compare(ThreadInfo threadInfo, ThreadInfo threadInfo2) {
                    return threadInfo.mName.compareTo(threadInfo2.mName);
                }
            });
        }
        exceptionMessage.mMemoryInfo = memoryInfo.toJson().toString();
    }

    private static void a(MemoryInfo memoryInfo) {
        File[] fileArrListFiles = b.listFiles();
        if (fileArrListFiles == null) {
            return;
        }
        for (File file : fileArrListFiles) {
            ThreadInfo threadInfo = new ThreadInfo();
            try {
                threadInfo.mName = h.a(new File(file, "comm"));
            } catch (IOException e) {
                com.kwad.sdk.core.d.a.b(e);
            }
            if (!TextUtils.isEmpty(threadInfo.mName)) {
                threadInfo.mName = a(threadInfo.mName, "\n");
                memoryInfo.mAllThreads.add(threadInfo);
            }
        }
    }

    public static void a(File file) throws Throwable {
        try {
            c.a(SystemUtil.a(21) ? new String[]{"logcat", "-v", "threadtime", "-b", "main", "-b", "system", "-b", "events", "-b", AppMeasurement.CRASH_ORIGIN, "-d", "-f", file.getPath()} : new String[]{"logcat", "-v", "threadtime", "-b", "main", "-b", "system", "-b", "events", "-d", "-f", file.getPath()}, 0);
        } catch (IOException e) {
            com.kwad.sdk.core.d.a.b(e);
        }
    }

    public static void a(File file, File file2) throws Throwable {
        BufferedReader bufferedReader;
        try {
            e(file);
            e(file2);
            BufferedWriter bufferedWriter = null;
            try {
                try {
                    bufferedReader = new BufferedReader(new FileReader(file));
                    try {
                        BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(file2, true));
                        while (true) {
                            try {
                                String line = bufferedReader.readLine();
                                if (line == null) {
                                    break;
                                }
                                bufferedWriter2.write(line);
                                bufferedWriter2.newLine();
                            } catch (FileNotFoundException e) {
                                e = e;
                                bufferedWriter = bufferedWriter2;
                                com.kwad.sdk.core.d.a.b(e);
                                b.a((Writer) bufferedWriter);
                                b.a((Reader) bufferedReader);
                            } catch (IOException e2) {
                                e = e2;
                                bufferedWriter = bufferedWriter2;
                                com.kwad.sdk.core.d.a.b(e);
                                b.a((Writer) bufferedWriter);
                                b.a((Reader) bufferedReader);
                            } catch (Throwable th) {
                                th = th;
                                bufferedWriter = bufferedWriter2;
                                b.a((Writer) bufferedWriter);
                                b.a((Reader) bufferedReader);
                                throw th;
                            }
                        }
                        b.a((Writer) bufferedWriter2);
                    } catch (FileNotFoundException e3) {
                        e = e3;
                    } catch (IOException e4) {
                        e = e4;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (FileNotFoundException e5) {
                e = e5;
                bufferedReader = null;
            } catch (IOException e6) {
                e = e6;
                bufferedReader = null;
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
            }
            b.a((Reader) bufferedReader);
        } catch (IOException e7) {
            com.kwad.sdk.core.d.a.b(e7);
        }
    }

    public static void a(File file, CharSequence charSequence) throws Throwable {
        a(file, charSequence, Charset.defaultCharset(), false);
    }

    public static void a(File file, CharSequence charSequence, Charset charset, boolean z) throws Throwable {
        a(file, charSequence == null ? null : charSequence.toString(), charset, z);
    }

    public static void a(File file, String str, Charset charset, boolean z) throws Throwable {
        FileOutputStream fileOutputStreamA;
        FileOutputStream fileOutputStream = null;
        try {
            try {
                fileOutputStreamA = g.a(file, z);
            } catch (Throwable th) {
                th = th;
                fileOutputStreamA = fileOutputStream;
            }
        } catch (Exception e) {
            e = e;
        }
        try {
            h.a(str, fileOutputStreamA, charset);
            a(fileOutputStreamA);
            b.a((OutputStream) fileOutputStreamA);
        } catch (Exception e2) {
            e = e2;
            fileOutputStream = fileOutputStreamA;
            com.kwad.sdk.core.d.a.b(e);
            b.a((OutputStream) fileOutputStream);
        } catch (Throwable th2) {
            th = th2;
            b.a((OutputStream) fileOutputStreamA);
            throw th;
        }
    }

    public static void a(@NonNull Throwable th, @NonNull ExceptionMessage exceptionMessage) throws Throwable {
        String strA = a(th);
        if (th instanceof StackOverflowError) {
            strA = c(strA);
        }
        exceptionMessage.mCrashDetail = strA.replaceAll("[\n\t]", "#");
    }

    public static void a(@Nullable Throwable th, @NonNull ExceptionMessage exceptionMessage, @Nullable Context context) throws Throwable {
        if (th != null) {
            a(th, exceptionMessage);
        }
        a(exceptionMessage, context);
        a(exceptionMessage, new MemoryInfo(), context);
        a(exceptionMessage, new DiskInfo(), context);
        if (exceptionMessage instanceof JavaExceptionMessage) {
            b(th, exceptionMessage);
        }
    }

    public static boolean a(FileOutputStream fileOutputStream) throws SyncFailedException {
        if (fileOutputStream == null) {
            return true;
        }
        try {
            fileOutputStream.getFD().sync();
            return true;
        } catch (IOException e) {
            com.kwad.sdk.core.d.a.b(e);
            return false;
        }
    }

    public static long b(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                return file.length();
            }
        } catch (Exception unused) {
        }
        return -1L;
    }

    private static String b(Context context) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("BuildConfig Version Name: 3.3.17\n");
            sb.append("PackageInfo CodePath: " + context.getPackageCodePath() + "\n");
            sb.append("PackageInfo ResPath: " + context.getPackageResourcePath() + "\n");
            sb.append("DexPath: " + c(context) + "\n");
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.b(e);
        }
        return sb.toString();
    }

    private static void b(@NonNull ExceptionMessage exceptionMessage, @Nullable Context context) throws IOException {
        String absolutePath;
        String strGroup;
        if (context == null) {
            return;
        }
        File parentFile = context.getCacheDir().getParentFile().getParentFile().getParentFile();
        try {
            absolutePath = parentFile.getCanonicalPath();
        } catch (IOException e) {
            com.kwad.sdk.core.d.a.b(e);
            absolutePath = parentFile.getAbsolutePath();
        }
        if (com.kwad.sdk.crash.c.b.matcher(absolutePath).matches() || com.kwad.sdk.crash.c.c.matcher(absolutePath).matches()) {
            exceptionMessage.mVirtualApp = context.getPackageName();
            return;
        }
        Matcher matcher = com.kwad.sdk.crash.c.d.matcher(absolutePath);
        Matcher matcher2 = com.kwad.sdk.crash.c.e.matcher(absolutePath);
        if (matcher.matches()) {
            strGroup = matcher.group(1);
        } else {
            if (!matcher2.matches()) {
                exceptionMessage.mVirtualApp = absolutePath;
                return;
            }
            strGroup = matcher2.group(1);
        }
        exceptionMessage.mVirtualApp = strGroup;
    }

    public static void b(File file) {
        if (file == null) {
            return;
        }
        try {
            g.c(file);
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.b(e);
        }
    }

    private static void b(Throwable th, ExceptionMessage exceptionMessage) {
        if (b(th) && exceptionMessage.mCrashType.equals(exceptionMessage.getTypeCommon())) {
            exceptionMessage.mCrashType = exceptionMessage.getTypeHeapOOM();
        }
    }

    private static boolean b(@Nullable Throwable th) {
        if (th == null) {
            return false;
        }
        while (th.getCause() != null) {
            th = th.getCause();
        }
        return th instanceof OutOfMemoryError;
    }

    private static String c(Context context) {
        StringBuilder sb = new StringBuilder();
        ClassLoader classLoader = context.getClassLoader();
        if (classLoader != null) {
            String string = classLoader.toString();
            StringBuilder sb2 = new StringBuilder();
            sb2.append("ClassLoader ");
            int i = 0;
            sb2.append(0);
            sb2.append(" : ");
            sb2.append(string);
            while (true) {
                sb.append(sb2.toString());
                if (classLoader.getParent() == null) {
                    break;
                }
                classLoader = classLoader.getParent();
                i++;
                sb2 = new StringBuilder();
                sb2.append("\nClassLoader ");
                sb2.append(i);
                sb2.append(" : ");
                sb2.append(classLoader.toString());
            }
            if (string != null) {
                String[] strArrSplit = string.split("\"");
                if (strArrSplit.length >= 2) {
                    sb.append("\n====path: " + strArrSplit[1] + ", length: " + b(strArrSplit[1]));
                }
            }
        }
        return sb.toString();
    }

    public static String c(String str) {
        HashSet hashSet = new HashSet();
        for (String str2 : str.split("\n")) {
            hashSet.add(str2);
        }
        ArrayList arrayList = new ArrayList(hashSet);
        StringBuilder sb = new StringBuilder();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            sb.append((String) it.next());
            sb.append("\n");
        }
        return sb.substring(0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void c(File file) throws Throwable {
        BufferedWriter bufferedWriter;
        boolean zHasNext;
        try {
            e(file);
            BufferedWriter bufferedWriter2 = null;
            bufferedWriter = null;
            BufferedWriter bufferedWriter3 = null;
            try {
                try {
                    bufferedWriter = new BufferedWriter(new FileWriter(file, true));
                } catch (Throwable th) {
                    th = th;
                    bufferedWriter = bufferedWriter2;
                }
            } catch (FileNotFoundException e) {
                e = e;
            } catch (IOException e2) {
                e = e2;
            }
            try {
                Iterator<Map.Entry<Thread, StackTraceElement[]>> it = Thread.getAllStackTraces().entrySet().iterator();
                while (true) {
                    zHasNext = it.hasNext();
                    if (zHasNext == 0) {
                        break;
                    }
                    Map.Entry<Thread, StackTraceElement[]> next = it.next();
                    String strA = a(next.getValue());
                    if (strA.isEmpty()) {
                        strA = "(no managed stack frames)\n";
                    }
                    bufferedWriter.write(next.getKey().getName());
                    bufferedWriter.newLine();
                    bufferedWriter.write(strA);
                    bufferedWriter.newLine();
                }
                b.a((Writer) bufferedWriter);
                bufferedWriter2 = zHasNext;
            } catch (FileNotFoundException e3) {
                e = e3;
                bufferedWriter3 = bufferedWriter;
                com.kwad.sdk.core.d.a.b(e);
                b.a((Writer) bufferedWriter3);
                bufferedWriter2 = bufferedWriter3;
            } catch (IOException e4) {
                e = e4;
                bufferedWriter3 = bufferedWriter;
                com.kwad.sdk.core.d.a.b(e);
                b.a((Writer) bufferedWriter3);
                bufferedWriter2 = bufferedWriter3;
            } catch (Throwable th2) {
                th = th2;
                b.a((Writer) bufferedWriter);
                throw th;
            }
        } catch (IOException e5) {
            com.kwad.sdk.core.d.a.b(e5);
        }
    }

    public static void d(File file) throws FileNotFoundException {
        String str;
        Object[] objArr;
        try {
            Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
            Debug.getMemoryInfo(memoryInfo);
            Object objA = s.a(s.a("android.app.ActivityThread", "currentActivityThread", new Object[0]), "mAppThread");
            Object objOpen = ParcelFileDescriptor.open(file, 1006632960);
            if (!SystemUtil.a(26)) {
                objOpen = ((ParcelFileDescriptor) objOpen).getFileDescriptor();
            }
            if (SystemUtil.a(24)) {
                s.a(objA, "dumpMemInfo", objOpen, memoryInfo, false, true, true, false, false, new String[0]);
            } else {
                if (SystemUtil.a(23)) {
                    str = "dumpMemInfo";
                    objArr = new Object[]{objOpen, memoryInfo, false, true, true, false, new String[0]};
                } else if (SystemUtil.a(19)) {
                    str = "dumpMemInfo";
                    objArr = new Object[]{objOpen, memoryInfo, false, true, true, new String[0]};
                }
                s.a(objA, str, objArr);
            }
            Object objOpen2 = ParcelFileDescriptor.open(file, 973078528);
            if (!SystemUtil.a(26)) {
                objOpen2 = ((ParcelFileDescriptor) objOpen2).getFileDescriptor();
            }
            s.a(objA, "dumpGfxInfo", objOpen2, new String[]{SystemUtil.a(com.kwad.sdk.crash.d.a().f())});
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.b(e);
        }
    }

    private static void e(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }
            return;
        }
        File parentFile = file.getParentFile();
        if (parentFile == null) {
            throw new IOException("Could not find parent directory");
        }
        if (parentFile.mkdirs() || parentFile.isDirectory()) {
            file.createNewFile();
            return;
        }
        throw new IOException("Directory '" + parentFile + "' could not be created");
    }
}
