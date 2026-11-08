package com.umeng.message.proguard;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Process;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import com.ss.android.socialbase.downloader.constants.DownloadConstants;
import com.ss.union.game.sdk.common.util.DateUtils;
import com.tachikoma.core.utility.UriUtil;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.message.MessageSharedPrefs;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.umeng.message.entity.Ucode;
import com.xiaomi.mipush.sdk.Constants;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.StreamCorruptedException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.UByte;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class h {
    public static final String a = System.getProperty("line.separator");
    private static final String b = h.class.getName();
    private static final AtomicInteger c = new AtomicInteger(1);

    public static String a(String str) throws NoSuchAlgorithmException {
        if (str == null) {
            return null;
        }
        try {
            byte[] bytes = str.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(bytes);
            byte[] bArrDigest = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b2 : bArrDigest) {
                stringBuffer.append(String.format("%02X", Byte.valueOf(b2)));
            }
            return stringBuffer.toString();
        } catch (Exception unused) {
            return str.replaceAll("[^[a-z][A-Z][0-9][.][_]]", "");
        }
    }

    public static String b(String str) throws NoSuchAlgorithmException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            byte[] bArrDigest = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b2 : bArrDigest) {
                stringBuffer.append(Integer.toHexString(b2 & UByte.MAX_VALUE));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException unused) {
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(b, 2, "getMD5 error");
            return "";
        }
    }

    public static String a(File file) throws NoSuchAlgorithmException, IOException {
        byte[] bArr = new byte[com.ss.union.game.sdk.feedback.picture.select.d.d];
        try {
            if (!file.isFile()) {
                return "";
            }
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            FileInputStream fileInputStream = new FileInputStream(file);
            while (true) {
                int i = fileInputStream.read(bArr, 0, com.ss.union.game.sdk.feedback.picture.select.d.d);
                if (i != -1) {
                    messageDigest.update(bArr, 0, i);
                } else {
                    fileInputStream.close();
                    return String.format("%1$032x", new BigInteger(1, messageDigest.digest()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String a(Context context, long j) {
        if (j < 1000) {
            return ((int) j) + "B";
        }
        if (j < 1000000) {
            return Math.round(j / 1000.0d) + "K";
        }
        if (j < 1000000000) {
            return new DecimalFormat("#0.0").format(j / 1000000.0d) + "M";
        }
        return new DecimalFormat("#0.00").format(j / 1.0E9d) + "G";
    }

    public static String c(String str) {
        try {
            long jLongValue = Long.valueOf(str).longValue();
            if (jLongValue < DownloadConstants.KB) {
                return ((int) jLongValue) + "B";
            }
            if (jLongValue < 1048576) {
                return new DecimalFormat("#0.00").format(jLongValue / 1024.0d) + "K";
            }
            if (jLongValue < DownloadConstants.GB) {
                return new DecimalFormat("#0.00").format(jLongValue / 1048576.0d) + "M";
            }
            return new DecimalFormat("#0.00").format(jLongValue / 1.073741824E9d) + "G";
        } catch (NumberFormatException unused) {
            return str;
        }
    }

    public static void a(Context context, String str) {
        context.startActivity(context.getPackageManager().getLaunchIntentForPackage(str));
    }

    public static boolean b(Context context, String str) {
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean d(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean e(String str) {
        if (d(str)) {
            return false;
        }
        String lowerCase = str.trim().toLowerCase(Locale.US);
        return lowerCase.startsWith(UriUtil.HTTP_PREFIX) || lowerCase.startsWith(UriUtil.HTTPS_PREFIX);
    }

    public static String a() {
        return a(new Date());
    }

    public static String a(Date date) {
        return date == null ? "" : new SimpleDateFormat(DateUtils.FORMAT_ONE).format(date);
    }

    public static boolean a(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        try {
            runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (runningAppProcesses == null) {
            return false;
        }
        String packageName = context.getPackageName();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.importance == 100 && runningAppProcessInfo.processName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }

    public static byte[] a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[com.ss.union.game.sdk.feedback.picture.select.d.d];
        while (true) {
            int i = inputStream.read(bArr);
            if (-1 != i) {
                byteArrayOutputStream.write(bArr, 0, i);
            } else {
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    public static void b(InputStream inputStream) throws IOException {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception unused) {
            }
        }
    }

    public static void a(OutputStream outputStream) throws IOException {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (Exception unused) {
            }
        }
    }

    public static boolean b(Context context) {
        String packageName = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1).get(0).topActivity.getPackageName();
        return packageName != null && packageName.equals(context.getPackageName());
    }

    public static boolean c(Context context) {
        return context.getPackageManager().checkPermission(MsgConstant.PERMISSION_GET_TASKS, context.getPackageName()) == 0;
    }

    public static boolean c(Context context, String str) throws SecurityException {
        List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) context.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE);
        if (runningServices == null || runningServices.isEmpty()) {
            return false;
        }
        for (int i = 0; i < runningServices.size(); i++) {
            if (runningServices.get(i).service.getClassName().equals(str) && TextUtils.equals(runningServices.get(i).service.getPackageName(), context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    public static String a(Context context, int i) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return "";
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == i) {
                return runningAppProcessInfo.processName;
            }
        }
        return "";
    }

    public static void a(Context context, Class<?> cls) {
        ComponentName componentName;
        int componentEnabledSetting;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null && packageManager.getApplicationEnabledSetting(context.getPackageName()) > -1 && (componentEnabledSetting = packageManager.getComponentEnabledSetting((componentName = new ComponentName(context, cls)))) != 1 && componentEnabledSetting != 0) {
                packageManager.setComponentEnabledSetting(componentName, 1, 1);
            }
        } catch (Throwable unused) {
        }
    }

    public static final void b(Context context, Class<?> cls) {
        if (context != null) {
            try {
                PackageManager packageManager = context.getPackageManager();
                if (cls == null) {
                    return;
                }
                ComponentName componentName = new ComponentName(context, cls);
                if (packageManager.getComponentEnabledSetting(componentName) == 2) {
                    UMLog uMLog = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(b, 2, "rebootReceiver[" + componentName.toString() + "]--->[ENABLED]");
                    packageManager.setComponentEnabledSetting(componentName, 1, 1);
                }
            } catch (Throwable unused) {
            }
        }
    }

    public static Object f(String str) {
        try {
            try {
                return new ObjectInputStream(new ByteArrayInputStream(d.h(str.getBytes()))).readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        } catch (StreamCorruptedException e2) {
            e2.printStackTrace();
            return null;
        } catch (IOException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static String a(Object obj) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new ObjectOutputStream(byteArrayOutputStream).writeObject(obj);
            return new String(d.c(byteArrayOutputStream.toByteArray()));
        } catch (IOException unused) {
            return null;
        }
    }

    public static String b() {
        return UUID.randomUUID().toString().replace(Constants.ACCEPT_TIME_SEPARATOR_SERVER, "");
    }

    public static boolean a(final Context context, Handler handler) throws PackageManager.NameNotFoundException {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        boolean z8;
        boolean z9;
        boolean z10;
        boolean z11;
        boolean z12;
        boolean z13;
        boolean z14;
        boolean z15;
        boolean z16;
        boolean z17;
        boolean z18;
        boolean z19;
        boolean z20;
        boolean z21;
        boolean z22;
        boolean z23;
        boolean z24;
        boolean z25;
        boolean z26;
        boolean z27;
        boolean z28;
        boolean z29;
        boolean z30;
        boolean z31;
        boolean z32;
        if (!PushAgent.getInstance(context).isPushCheck()) {
            return true;
        }
        try {
            if (TextUtils.isEmpty(PushAgent.getInstance(context).getMessageAppkey())) {
                handler.post(new Runnable() { // from class: com.umeng.message.proguard.h.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Toast.makeText(context, "Please set umeng appkey!", 1).show();
                    }
                });
                return false;
            }
            if (TextUtils.isEmpty(PushAgent.getInstance(context).getMessageSecret())) {
                handler.post(new Runnable() { // from class: com.umeng.message.proguard.h.12
                    @Override // java.lang.Runnable
                    public void run() {
                        Toast.makeText(context, "Please set umeng appsecret!", 1).show();
                    }
                });
                return false;
            }
            Intent intent = new Intent();
            intent.setPackage(context.getPackageName());
            intent.setAction("com.taobao.accs.intent.action.SERVICE");
            Iterator<ResolveInfo> it = context.getPackageManager().queryIntentServices(intent, 64).iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                ResolveInfo next = it.next();
                if (next.serviceInfo.name.equals(com.taobao.accs.utl.j.channelService)) {
                    String str = next.serviceInfo.processName;
                    if (!TextUtils.isEmpty(str) && !TextUtils.equals(str, context.getPackageName()) && next.serviceInfo.exported) {
                        z = true;
                        break;
                    }
                }
            }
            if (!z) {
                handler.post(new Runnable() { // from class: com.umeng.message.proguard.h.18
                    @Override // java.lang.Runnable
                    public void run() {
                        Toast.makeText(context, "Please add or correct ChannelService in AndroidManifest!", 1).show();
                    }
                });
                return false;
            }
            Intent intent2 = new Intent();
            intent2.setPackage(context.getPackageName());
            intent2.setAction("com.taobao.accs.intent.action.ELECTION");
            Iterator<ResolveInfo> it2 = context.getPackageManager().queryIntentServices(intent2, 64).iterator();
            while (true) {
                if (!it2.hasNext()) {
                    z2 = false;
                    break;
                }
                ResolveInfo next2 = it2.next();
                if (next2.serviceInfo.name.equals(com.taobao.accs.utl.j.channelService)) {
                    String str2 = next2.serviceInfo.processName;
                    if (!TextUtils.isEmpty(str2) && !TextUtils.equals(str2, context.getPackageName()) && next2.serviceInfo.exported) {
                        z2 = true;
                        break;
                    }
                }
            }
            if (!z2) {
                handler.post(new Runnable() { // from class: com.umeng.message.proguard.h.19
                    @Override // java.lang.Runnable
                    public void run() {
                        Toast.makeText(context, "Please add or correct ChannelService in AndroidManifest!", 1).show();
                    }
                });
                return false;
            }
            Intent intent3 = new Intent();
            intent3.setPackage(context.getPackageName());
            intent3.setAction(com.taobao.accs.common.Constants.ACTION_RECEIVE);
            Iterator<ResolveInfo> it3 = context.getPackageManager().queryIntentServices(intent3, 64).iterator();
            while (true) {
                if (!it3.hasNext()) {
                    z3 = false;
                    break;
                }
                ResolveInfo next3 = it3.next();
                if (next3.serviceInfo.name.equals(com.taobao.accs.utl.j.msgService) && next3.serviceInfo.exported) {
                    z3 = true;
                    break;
                }
            }
            if (!z3) {
                handler.post(new Runnable() { // from class: com.umeng.message.proguard.h.20
                    @Override // java.lang.Runnable
                    public void run() {
                        Toast.makeText(context, "Please add or correct MsgDistributeService in AndroidManifest!", 1).show();
                    }
                });
                return false;
            }
            Intent intent4 = new Intent();
            intent4.setPackage(context.getPackageName());
            intent4.setAction("android.intent.action.BOOT_COMPLETED");
            Iterator<ResolveInfo> it4 = context.getPackageManager().queryBroadcastReceivers(intent4, 64).iterator();
            while (true) {
                if (!it4.hasNext()) {
                    z4 = false;
                    break;
                }
                ResolveInfo next4 = it4.next();
                if (next4.activityInfo.name.equals("com.taobao.accs.EventReceiver")) {
                    String str3 = next4.activityInfo.processName;
                    if (!TextUtils.isEmpty(str3) && !TextUtils.equals(str3, context.getPackageName())) {
                        z4 = true;
                        break;
                    }
                }
            }
            if (!z4) {
                handler.post(new Runnable() { // from class: com.umeng.message.proguard.h.21
                    @Override // java.lang.Runnable
                    public void run() {
                        Toast.makeText(context, "Please add or correct EventReceiver in AndroidManifest!", 1).show();
                    }
                });
                return false;
            }
            Intent intent5 = new Intent();
            intent5.setPackage(context.getPackageName());
            intent5.setAction("android.net.conn.CONNECTIVITY_CHANGE");
            Iterator<ResolveInfo> it5 = context.getPackageManager().queryBroadcastReceivers(intent5, 64).iterator();
            while (true) {
                if (!it5.hasNext()) {
                    z5 = false;
                    break;
                }
                ResolveInfo next5 = it5.next();
                if (next5.activityInfo.name.equals("com.taobao.accs.EventReceiver")) {
                    String str4 = next5.activityInfo.processName;
                    if (!TextUtils.isEmpty(str4) && !TextUtils.equals(str4, context.getPackageName())) {
                        z5 = true;
                        break;
                    }
                }
            }
            if (!z5) {
                handler.post(new Runnable() { // from class: com.umeng.message.proguard.h.22
                    @Override // java.lang.Runnable
                    public void run() {
                        Toast.makeText(context, "Please add or correct EventReceiver in AndroidManifest!", 1).show();
                    }
                });
                return false;
            }
            Intent intent6 = new Intent();
            intent6.setPackage(context.getPackageName());
            intent6.setAction("android.intent.action.PACKAGE_REMOVED");
            intent6.setData(Uri.parse("package:"));
            Iterator<ResolveInfo> it6 = context.getPackageManager().queryBroadcastReceivers(intent6, 64).iterator();
            while (true) {
                if (!it6.hasNext()) {
                    z6 = false;
                    break;
                }
                ResolveInfo next6 = it6.next();
                if (next6.activityInfo.name.equals("com.taobao.accs.EventReceiver")) {
                    String str5 = next6.activityInfo.processName;
                    if (!TextUtils.isEmpty(str5) && !TextUtils.equals(str5, context.getPackageName())) {
                        z6 = true;
                        break;
                    }
                }
            }
            if (!z6) {
                handler.post(new Runnable() { // from class: com.umeng.message.proguard.h.23
                    @Override // java.lang.Runnable
                    public void run() {
                        Toast.makeText(context, "Please add or correct EventReceiver in AndroidManifest!", 1).show();
                    }
                });
                return false;
            }
            Intent intent7 = new Intent();
            intent7.setPackage(context.getPackageName());
            intent7.setAction("android.intent.action.USER_PRESENT");
            Iterator<ResolveInfo> it7 = context.getPackageManager().queryBroadcastReceivers(intent7, 64).iterator();
            while (true) {
                if (!it7.hasNext()) {
                    z7 = false;
                    break;
                }
                ResolveInfo next7 = it7.next();
                if (next7.activityInfo.name.equals("com.taobao.accs.EventReceiver")) {
                    String str6 = next7.activityInfo.processName;
                    if (!TextUtils.isEmpty(str6) && !TextUtils.equals(str6, context.getPackageName())) {
                        z7 = true;
                        break;
                    }
                }
            }
            if (!z7) {
                handler.post(new Runnable() { // from class: com.umeng.message.proguard.h.24
                    @Override // java.lang.Runnable
                    public void run() {
                        Toast.makeText(context, "Please add or correct EventReceiver in AndroidManifest!", 1).show();
                    }
                });
                return false;
            }
            Intent intent8 = new Intent();
            intent8.setPackage(context.getPackageName());
            intent8.setAction(com.taobao.accs.common.Constants.ACTION_COMMAND);
            Iterator<ResolveInfo> it8 = context.getPackageManager().queryBroadcastReceivers(intent8, 64).iterator();
            while (true) {
                if (!it8.hasNext()) {
                    z8 = false;
                    break;
                }
                ResolveInfo next8 = it8.next();
                if (next8.activityInfo.name.equals("com.taobao.accs.ServiceReceiver")) {
                    String str7 = next8.activityInfo.processName;
                    if (!TextUtils.isEmpty(str7) && !TextUtils.equals(str7, context.getPackageName())) {
                        z8 = true;
                        break;
                    }
                }
            }
            if (!z8) {
                handler.post(new Runnable() { // from class: com.umeng.message.proguard.h.2
                    @Override // java.lang.Runnable
                    public void run() {
                        Toast.makeText(context, "Please add or correct ServiceReceiver in AndroidManifest!", 1).show();
                    }
                });
                return false;
            }
            Intent intent9 = new Intent();
            intent9.setPackage(context.getPackageName());
            intent9.setAction(com.taobao.accs.common.Constants.ACTION_START_FROM_AGOO);
            Iterator<ResolveInfo> it9 = context.getPackageManager().queryBroadcastReceivers(intent9, 64).iterator();
            while (true) {
                if (!it9.hasNext()) {
                    z9 = false;
                    break;
                }
                ResolveInfo next9 = it9.next();
                if (next9.activityInfo.name.equals("com.taobao.accs.ServiceReceiver")) {
                    String str8 = next9.activityInfo.processName;
                    if (!TextUtils.isEmpty(str8) && !TextUtils.equals(str8, context.getPackageName())) {
                        z9 = true;
                        break;
                    }
                }
            }
            if (!z9) {
                handler.post(new Runnable() { // from class: com.umeng.message.proguard.h.3
                    @Override // java.lang.Runnable
                    public void run() {
                        Toast.makeText(context, "Please add or correct ServiceReceiver in AndroidManifest!", 1).show();
                    }
                });
                return false;
            }
            Intent intent10 = new Intent();
            intent10.setPackage(context.getPackageName());
            intent10.setAction(com.taobao.accs.common.Constants.ACTION_RECEIVE);
            Iterator<ResolveInfo> it10 = context.getPackageManager().queryIntentServices(intent10, 64).iterator();
            while (true) {
                if (!it10.hasNext()) {
                    z10 = false;
                    break;
                }
                ResolveInfo next10 = it10.next();
                if (next10.serviceInfo.name.equals("org.android.agoo.accs.AgooService") && next10.serviceInfo.exported) {
                    z10 = true;
                    break;
                }
            }
            if (!z10) {
                handler.post(new Runnable() { // from class: com.umeng.message.proguard.h.4
                    @Override // java.lang.Runnable
                    public void run() {
                        Toast.makeText(context, "Please add or correct AgooService in AndroidManifest!", 1).show();
                    }
                });
                return false;
            }
            Intent intent11 = new Intent();
            intent11.setPackage(context.getPackageName());
            intent11.setAction("org.agoo.android.intent.action.RECEIVE");
            Iterator<ResolveInfo> it11 = context.getPackageManager().queryIntentServices(intent11, 64).iterator();
            while (true) {
                if (!it11.hasNext()) {
                    z11 = false;
                    break;
                }
                ResolveInfo next11 = it11.next();
                if (next11.serviceInfo.name.equals("com.umeng.message.UmengIntentService") && next11.serviceInfo.exported) {
                    z11 = true;
                    break;
                }
            }
            if (!z11) {
                handler.post(new Runnable() { // from class: com.umeng.message.proguard.h.5
                    @Override // java.lang.Runnable
                    public void run() {
                        Toast.makeText(context, "Please add or correct UmengIntentService in AndroidManifest!", 1).show();
                    }
                });
                return false;
            }
            Intent intent12 = new Intent();
            intent12.setPackage(context.getPackageName());
            intent12.setAction(context.getPackageName() + ".intent.action.COMMAND");
            Iterator<ResolveInfo> it12 = context.getPackageManager().queryBroadcastReceivers(intent12, 64).iterator();
            while (true) {
                if (!it12.hasNext()) {
                    z12 = false;
                    break;
                }
                ResolveInfo next12 = it12.next();
                if (next12.activityInfo.name.equals("com.taobao.agoo.AgooCommondReceiver") && next12.activityInfo.exported) {
                    z12 = true;
                    break;
                }
            }
            if (!z12) {
                handler.post(new Runnable() { // from class: com.umeng.message.proguard.h.6
                    @Override // java.lang.Runnable
                    public void run() {
                        Toast.makeText(context, "Please replace '${applicationId}.intent.action.COMMAND' with application's applicationId for AgooCommondReceiver in AndroidManifest!", 1).show();
                    }
                });
                return false;
            }
            Intent intent13 = new Intent();
            intent13.setPackage(context.getPackageName());
            intent13.setAction("android.intent.action.PACKAGE_REMOVED");
            intent13.setData(Uri.parse("package:"));
            Iterator<ResolveInfo> it13 = context.getPackageManager().queryBroadcastReceivers(intent13, 64).iterator();
            while (true) {
                if (!it13.hasNext()) {
                    z13 = false;
                    break;
                }
                ResolveInfo next13 = it13.next();
                if (next13.activityInfo.name.equals("com.taobao.agoo.AgooCommondReceiver") && next13.activityInfo.exported) {
                    z13 = true;
                    break;
                }
            }
            if (!z13) {
                handler.post(new Runnable() { // from class: com.umeng.message.proguard.h.7
                    @Override // java.lang.Runnable
                    public void run() {
                        Toast.makeText(context, "Please add or correct AgooCommondReceiver in AndroidManifest!", 1).show();
                    }
                });
                return false;
            }
            Intent intent14 = new Intent();
            intent14.setClassName(context.getPackageName(), "com.umeng.message.NotificationProxyBroadcastReceiver");
            Iterator<ResolveInfo> it14 = context.getPackageManager().queryBroadcastReceivers(intent14, 65536).iterator();
            while (true) {
                if (!it14.hasNext()) {
                    z14 = false;
                    break;
                }
                ResolveInfo next14 = it14.next();
                if (next14.activityInfo.name.equals("com.umeng.message.NotificationProxyBroadcastReceiver") && TextUtils.equals(next14.activityInfo.processName, context.getPackageName()) && !next14.activityInfo.exported) {
                    z14 = true;
                    break;
                }
            }
            if (!z14) {
                handler.post(new Runnable() { // from class: com.umeng.message.proguard.h.8
                    @Override // java.lang.Runnable
                    public void run() {
                        Toast.makeText(context, "Please add or correct NotificationProxyBroadcastReceiver in AndroidManifest!", 1).show();
                    }
                });
                return false;
            }
            Intent intent15 = new Intent();
            intent15.setPackage(context.getPackageName());
            intent15.setAction(MsgConstant.MESSAGE_REGISTER_CALLBACK_ACTION);
            Iterator<ResolveInfo> it15 = context.getPackageManager().queryIntentServices(intent15, 64).iterator();
            while (true) {
                if (!it15.hasNext()) {
                    z15 = false;
                    break;
                }
                ResolveInfo next15 = it15.next();
                if (next15.serviceInfo.name.equals("com.umeng.message.UmengMessageCallbackHandlerService") && TextUtils.equals(context.getPackageName(), next15.serviceInfo.processName) && !next15.serviceInfo.exported) {
                    z15 = true;
                    break;
                }
            }
            if (!z15) {
                handler.post(new Runnable() { // from class: com.umeng.message.proguard.h.9
                    @Override // java.lang.Runnable
                    public void run() {
                        Toast.makeText(context, "Please add or correct UmengMessageCallbackHandlerService in AndroidManifest!", 1).show();
                    }
                });
                return false;
            }
            Intent intent16 = new Intent();
            intent16.setPackage(context.getPackageName());
            intent16.setAction(MsgConstant.MESSAGE_ENABLE_CALLBACK_ACTION);
            Iterator<ResolveInfo> it16 = context.getPackageManager().queryIntentServices(intent16, 64).iterator();
            while (true) {
                if (!it16.hasNext()) {
                    z16 = false;
                    break;
                }
                ResolveInfo next16 = it16.next();
                if (next16.serviceInfo.name.equals("com.umeng.message.UmengMessageCallbackHandlerService") && !next16.serviceInfo.exported) {
                    z16 = true;
                    break;
                }
            }
            if (!z16) {
                handler.post(new Runnable() { // from class: com.umeng.message.proguard.h.10
                    @Override // java.lang.Runnable
                    public void run() {
                        Toast.makeText(context, "Please add or correct UmengMessageCallbackHandlerService in AndroidManifest!", 1).show();
                    }
                });
                return false;
            }
            Intent intent17 = new Intent();
            intent17.setPackage(context.getPackageName());
            intent17.setAction(MsgConstant.MESSAGE_DISABLE_CALLBACK_ACTION);
            Iterator<ResolveInfo> it17 = context.getPackageManager().queryIntentServices(intent17, 64).iterator();
            while (true) {
                if (!it17.hasNext()) {
                    z17 = false;
                    break;
                }
                ResolveInfo next17 = it17.next();
                if (next17.serviceInfo.name.equals("com.umeng.message.UmengMessageCallbackHandlerService") && !next17.serviceInfo.exported) {
                    z17 = true;
                    break;
                }
            }
            if (!z17) {
                handler.post(new Runnable() { // from class: com.umeng.message.proguard.h.11
                    @Override // java.lang.Runnable
                    public void run() {
                        Toast.makeText(context, "Please add or correct UmengMessageCallbackHandlerService in AndroidManifest!", 1).show();
                    }
                });
                return false;
            }
            Intent intent18 = new Intent();
            intent18.setPackage(context.getPackageName());
            intent18.setAction(MsgConstant.MESSAGE_MESSAGE_HANDLER_ACTION);
            Iterator<ResolveInfo> it18 = context.getPackageManager().queryIntentServices(intent18, 64).iterator();
            while (true) {
                if (!it18.hasNext()) {
                    z18 = false;
                    break;
                }
                ResolveInfo next18 = it18.next();
                if (next18.serviceInfo.name.equals("com.umeng.message.UmengMessageCallbackHandlerService") && !next18.serviceInfo.exported) {
                    z18 = true;
                    break;
                }
            }
            if (!z18) {
                handler.post(new Runnable() { // from class: com.umeng.message.proguard.h.13
                    @Override // java.lang.Runnable
                    public void run() {
                        Toast.makeText(context, "Please add or correct UmengMessageCallbackHandlerService in AndroidManifest!", 1).show();
                    }
                });
                return false;
            }
            Intent intent19 = new Intent();
            intent19.setClassName(context.getPackageName(), "com.umeng.message.UmengDownloadResourceService");
            Iterator<ResolveInfo> it19 = context.getPackageManager().queryIntentServices(intent19, 65536).iterator();
            while (true) {
                if (!it19.hasNext()) {
                    z19 = false;
                    break;
                }
                if (it19.next().serviceInfo.name.equals("com.umeng.message.UmengDownloadResourceService")) {
                    z19 = true;
                    break;
                }
            }
            if (!z19) {
                handler.post(new Runnable() { // from class: com.umeng.message.proguard.h.14
                    @Override // java.lang.Runnable
                    public void run() {
                        Toast.makeText(context, "Please add or correct UmengDownloadResourceService in AndroidManifest!", 1).show();
                    }
                });
                return false;
            }
            Intent intent20 = new Intent();
            intent20.setPackage(context.getPackageName());
            intent20.setAction("org.android.agoo.client.MessageReceiverService");
            Iterator<ResolveInfo> it20 = context.getPackageManager().queryIntentServices(intent20, 64).iterator();
            while (true) {
                if (!it20.hasNext()) {
                    z20 = false;
                    break;
                }
                ResolveInfo next19 = it20.next();
                if (next19.serviceInfo.name.equals("com.umeng.message.UmengMessageIntentReceiverService") && next19.serviceInfo.exported) {
                    String str9 = next19.serviceInfo.processName;
                    if (!TextUtils.isEmpty(str9) && !TextUtils.equals(str9, context.getPackageName())) {
                        z20 = true;
                        break;
                    }
                }
            }
            if (!z20) {
                handler.post(new Runnable() { // from class: com.umeng.message.proguard.h.15
                    @Override // java.lang.Runnable
                    public void run() {
                        Toast.makeText(context, "Please add or correct UmengMessageIntentReceiverService in AndroidManifest!", 1).show();
                    }
                });
                return false;
            }
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096);
            if (packageInfo.requestedPermissions != null) {
                z21 = false;
                z22 = false;
                z23 = false;
                z24 = false;
                z25 = false;
                z26 = false;
                z27 = false;
                z28 = false;
                z29 = false;
                z30 = false;
                z31 = false;
                z32 = false;
                for (int i = 0; i < packageInfo.requestedPermissions.length; i++) {
                    if (MsgConstant.PERMISSION_INTERNET.equals(packageInfo.requestedPermissions[i])) {
                        z21 = true;
                    } else if (MsgConstant.PERMISSION_ACCESS_WIFI_STATE.equals(packageInfo.requestedPermissions[i])) {
                        z23 = true;
                    } else if (MsgConstant.PERMISSION_ACCESS_NETWORK_STATE.equals(packageInfo.requestedPermissions[i])) {
                        z22 = true;
                    } else if (MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE.equals(packageInfo.requestedPermissions[i])) {
                        z24 = true;
                    } else if (MsgConstant.PERMISSION_WAKE_LOCK.equals(packageInfo.requestedPermissions[i])) {
                        z25 = true;
                    } else if (MsgConstant.PERMISSION_READ_PHONE_STATE.equals(packageInfo.requestedPermissions[i])) {
                        z26 = true;
                    } else if (MsgConstant.PERMISSION_BROADCAST_PACKAGE_ADDED.equals(packageInfo.requestedPermissions[i])) {
                        z27 = true;
                    } else if (MsgConstant.PERMISSION_BROADCAST_PACKAGE_CHANGED.equals(packageInfo.requestedPermissions[i])) {
                        z28 = true;
                    } else if (MsgConstant.PERMISSION_BROADCAST_PACKAGE_INSTALL.equals(packageInfo.requestedPermissions[i])) {
                        z29 = true;
                    } else if (MsgConstant.PERMISSION_BROADCAST_PACKAGE_REPLACED.equals(packageInfo.requestedPermissions[i])) {
                        z30 = true;
                    } else if (MsgConstant.PERMISSION_GET_TASKS.equals(packageInfo.requestedPermissions[i])) {
                        z31 = true;
                    } else if (MsgConstant.PERMISSION_RECEIVE_BOOT_COMPLETED.equals(packageInfo.requestedPermissions[i])) {
                        z32 = true;
                    }
                }
            } else {
                z21 = false;
                z22 = false;
                z23 = false;
                z24 = false;
                z25 = false;
                z26 = false;
                z27 = false;
                z28 = false;
                z29 = false;
                z30 = false;
                z31 = false;
                z32 = false;
            }
            if (z21 && z23 && z22 && z24 && z25 && z26 && z27 && z28 && z29 && z30 && z31 && z32) {
                return true;
            }
            handler.post(new Runnable() { // from class: com.umeng.message.proguard.h.16
                @Override // java.lang.Runnable
                public void run() {
                    Toast.makeText(context, "Please add required permission in AndroidManifest!", 1).show();
                }
            });
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public static String b(File file) throws Throwable {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            try {
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    sb.append(line);
                }
                String string = sb.toString();
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return string;
            } catch (Throwable th) {
                th = th;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedReader = null;
        }
    }

    public static boolean d(Context context) {
        String strA = a(context, Process.myPid());
        if (strA != null) {
            return strA.equals(context.getPackageName());
        }
        return false;
    }

    public static void b(final Context context, final Handler handler) {
        MessageSharedPrefs.getInstance(context).setRegisterTimes(MessageSharedPrefs.getInstance(context).getRegisterTimes() + 1);
        if (d(context)) {
            handler.postDelayed(new Runnable() { // from class: com.umeng.message.proguard.h.17
                @Override // java.lang.Runnable
                public void run() {
                    if (MessageSharedPrefs.getInstance(context).getRegisterTimes() <= 1) {
                        UMLog uMLog = UMConfigure.umDebugLog;
                        UMLog.mutlInfo(h.b, 0, "mPushAgent.register方法应该在主进程和channel进程中都被调用!");
                        UMLog uMLog2 = UMConfigure.umDebugLog;
                        UMLog.aq(k.b, 0, "\\|");
                        for (int i = 0; i < 3; i++) {
                            handler.postDelayed(new Runnable() { // from class: com.umeng.message.proguard.h.17.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    Toast.makeText(context, "mPushAgent.register方法应该在主进程和channel进程中都被调用!", 1).show();
                                }
                            }, i * 3500);
                        }
                    }
                }
            }, 20000L);
        }
    }

    public static String a(List<Ucode> list) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(list);
        String strEncode = URLEncoder.encode(byteArrayOutputStream.toString(f.a), "UTF-8");
        objectOutputStream.close();
        byteArrayOutputStream.close();
        return strEncode;
    }

    public static List<Ucode> g(String str) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(URLDecoder.decode(str, "UTF-8").getBytes(f.a));
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        List<Ucode> list = (List) objectInputStream.readObject();
        objectInputStream.close();
        byteArrayInputStream.close();
        return list;
    }

    public static String d(Context context, String str) {
        String str2 = context.getCacheDir() + "/umeng_push_inapp/";
        if (str == null) {
            return str2;
        }
        return str2 + str + "/";
    }

    public static int c() {
        int i;
        int i2;
        if (Build.VERSION.SDK_INT >= 17) {
            return View.generateViewId();
        }
        do {
            i = c.get();
            i2 = i + 1;
            if (i2 > 16777215) {
                i2 = 1;
            }
        } while (!c.compareAndSet(i, i2));
        return i;
    }
}
