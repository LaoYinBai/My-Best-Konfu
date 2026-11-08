package com.umeng.commonsdk.internal.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.text.TextUtils;
import com.umeng.commonsdk.proguard.o;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.message.MsgConstant;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class i {
    private static final String a = "/.um/.umm.dat";
    private static final String b = "/.uxx/.cca.dat";
    private static final String c = "/.cc/.adfwe.dat";
    private static final String d = "/.a.dat";
    private static final String e = "umdat";

    public static void a(Context context, String str) throws Throwable {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String strG = g(context);
        if (TextUtils.isEmpty(strG) || !str.equals(strG)) {
            b(context, str);
        }
    }

    public static String a(Context context) {
        String strB = b(context);
        if (strB == null || strB.equals("")) {
            strB = g(context);
        }
        if (strB == null || strB.equals("")) {
            strB = c(context);
        }
        if (strB == null || strB.equals("")) {
            strB = d(context);
        }
        if (strB == null || strB.equals("")) {
            strB = e(context);
        }
        return (strB == null || strB.equals("")) ? f(context) : strB;
    }

    public static String b(Context context) {
        return h(context);
    }

    public static String c(Context context) {
        return c(context, a);
    }

    public static String d(Context context) {
        return c(context, b);
    }

    public static String e(Context context) {
        return c(context, c);
    }

    public static String f(Context context) {
        return c(context, d);
    }

    public static String g(Context context) {
        return i(context);
    }

    private static String h(Context context) {
        return com.umeng.commonsdk.framework.a.a(context, o.e, (String) null);
    }

    public static void b(Context context, String str) throws Throwable {
        a(context, str, a);
        a(context, str, b);
        a(context, str, c);
        a(context, str, d);
        d(context, str);
    }

    private static void a(Context context, String str, String str2) throws Throwable {
        if (DeviceConfig.checkPermission(context, MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE)) {
            try {
                String externalStorageState = Environment.getExternalStorageState();
                if (externalStorageState == null || !externalStorageState.equalsIgnoreCase("mounted")) {
                    return;
                }
                String strC = c(context, str2);
                if (strC == null || !strC.equals(str)) {
                    File file = new File(Environment.getExternalStorageDirectory() + str2);
                    if (file.getParentFile() != null && !file.getParentFile().exists()) {
                        file.getParentFile().mkdir();
                    }
                    RandomAccessFile randomAccessFile = new RandomAccessFile(Environment.getExternalStorageDirectory() + str2, "rw");
                    randomAccessFile.setLength((long) str.getBytes().length);
                    FileChannel channel = randomAccessFile.getChannel();
                    FileLock fileLockTryLock = channel.tryLock();
                    ByteBuffer byteBufferAllocate = ByteBuffer.allocate(com.ss.union.game.sdk.feedback.picture.select.d.d);
                    byteBufferAllocate.clear();
                    byteBufferAllocate.put(str.getBytes());
                    byteBufferAllocate.flip();
                    while (byteBufferAllocate.hasRemaining()) {
                        channel.write(byteBufferAllocate);
                    }
                    channel.force(true);
                    if (fileLockTryLock != null) {
                        fileLockTryLock.release();
                    }
                    channel.close();
                }
            } catch (Exception e2) {
                ULog.e("saveFileUmtt:" + e2.getMessage());
            }
        }
    }

    private static String c(Context context, String str) throws Throwable {
        String externalStorageState;
        try {
            if (!DeviceConfig.checkPermission(context, "android.permission.READ_EXTERNAL_STORAGE") || (externalStorageState = Environment.getExternalStorageState()) == null || !externalStorageState.equalsIgnoreCase("mounted")) {
                return null;
            }
            if (!new File(Environment.getExternalStorageDirectory() + str).exists()) {
                return null;
            }
            FileChannel channel = new RandomAccessFile(Environment.getExternalStorageDirectory() + str, "rw").getChannel();
            FileLock fileLockTryLock = channel.tryLock();
            StringBuilder sb = new StringBuilder();
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(com.ss.union.game.sdk.feedback.picture.select.d.d);
            byteBufferAllocate.clear();
            while (channel.read(byteBufferAllocate) != -1) {
                byte[] bArr = new byte[byteBufferAllocate.position()];
                for (int i = 0; i < byteBufferAllocate.position(); i++) {
                    bArr[i] = byteBufferAllocate.get(i);
                }
                sb.append(new String(bArr));
                byteBufferAllocate.clear();
            }
            if (channel != null) {
                fileLockTryLock.release();
            }
            channel.close();
            return sb.toString();
        } catch (Exception e2) {
            ULog.e("getFileUmtt:" + e2.getMessage());
            return null;
        }
    }

    private static void d(Context context, String str) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(e, 0);
        if (sharedPreferences != null) {
            String string = sharedPreferences.getString("u", null);
            if (string == null || !string.equals(str)) {
                sharedPreferences.edit().putString("u", str).commit();
            }
        }
    }

    private static String i(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(e, 0);
        if (sharedPreferences != null) {
            return sharedPreferences.getString("u", null);
        }
        return null;
    }
}
