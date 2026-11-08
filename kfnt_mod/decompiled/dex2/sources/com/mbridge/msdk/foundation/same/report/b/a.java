package com.mbridge.msdk.foundation.same.report.b;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.b.b;
import com.mbridge.msdk.foundation.same.b.e;
import com.mbridge.msdk.foundation.same.report.c;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CrashHandlerUtil.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a implements Thread.UncaughtExceptionHandler {
    private static volatile a c;
    Handler a = new Handler() { // from class: com.mbridge.msdk.foundation.same.report.b.a.1
        @Override // android.os.Handler
        public final void handleMessage(Message message) throws JSONException {
            Context context;
            HashMap map;
            super.handleMessage(message);
            if (message.what == 101 && (context = (Context) a.this.b.get()) != null && (message.obj instanceof HashMap) && (map = (HashMap) message.obj) != null) {
                new c(context).a((String) map.get("crashinfo"), (File) map.get("file"));
            }
        }
    };
    private WeakReference<Context> b;
    private Thread.UncaughtExceptionHandler d;
    private Throwable e;

    private a(Context context) {
        this.b = new WeakReference<>(context);
    }

    public static a a(Context context) {
        if (c == null) {
            synchronized (a.class) {
                if (c == null) {
                    c = new a(context);
                }
            }
        }
        return c;
    }

    public final void a() {
        this.d = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    private String a(Throwable th, String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            Context context = this.b.get();
            if (context == null) {
                return "";
            }
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            long usableSpace = externalStorageDirectory.getUsableSpace();
            long totalSpace = externalStorageDirectory.getTotalSpace();
            String fileSize = Formatter.formatFileSize(context, usableSpace);
            String fileSize2 = Formatter.formatFileSize(context, totalSpace);
            jSONObject.put("max_memory", String.valueOf((float) ((Runtime.getRuntime().maxMemory() * 1.0d) / 1048576.0d)));
            jSONObject.put("memoryby_app", String.valueOf((float) ((Runtime.getRuntime().totalMemory() * 1.0d) / 1048576.0d)));
            jSONObject.put("remaining_memory", (float) ((Runtime.getRuntime().freeMemory() * 1.0d) / 1048576.0d));
            jSONObject.put("sdcard_remainder", fileSize);
            jSONObject.put("totalspacestr", fileSize2);
            jSONObject.put("crashtime", str);
            String strB = com.mbridge.msdk.foundation.a.a.a.a().b(MBridgeConstans.SDK_APP_ID);
            if (!TextUtils.isEmpty(strB)) {
                jSONObject.put(HiAnalyticsConstant.HaKey.BI_KEY_APPID, strB);
            }
            try {
                jSONObject.put("osversion", Build.VERSION.SDK_INT);
                jSONObject.put("appversioncode", b(context));
                jSONObject.put("appversionname", c(context));
                jSONObject.put("appname", d(context));
            } catch (Exception unused) {
                jSONObject.put("osversion", 0);
                jSONObject.put("appversioncode", "-1");
                jSONObject.put("appversionname", "-1");
                jSONObject.put("appname", "");
            }
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            jSONObject.put("crashinfo", stringWriter.toString());
            String string = jSONObject.toString();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("key=2000052");
            stringBuffer.append("&exception=" + string);
            return stringBuffer.toString();
        } catch (Throwable th2) {
            th2.printStackTrace();
            return "";
        }
    }

    private int b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static String c(Context context) {
        try {
            return context.getApplicationContext().getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("", e.getMessage());
            return "";
        }
    }

    private String d(Context context) {
        try {
            return context.getResources().getString(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) throws InterruptedException {
        try {
            this.e = th;
            com.mbridge.msdk.b.a aVarB = b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
            if (aVarB == null) {
                aVarB = b.a().b();
            }
            String strO = aVarB.o() == null ? "mbridge" : aVarB.o();
            List<String> listA = a(strO, "<mvpackage>(.*?)</mvpackage>");
            if (listA.size() > 0) {
                for (int i = 0; i < listA.size(); i++) {
                    a(a(th), listA.get(i), th, thread);
                }
                return;
            }
            a(a(th), strO, th, thread);
        } catch (Exception e) {
            a(thread, th);
            e.printStackTrace();
        }
    }

    private String a(Throwable th) {
        StackTraceElement[] stackTrace = th.getStackTrace();
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement stackTraceElement : stackTrace) {
            sb.append(stackTraceElement.toString() + "\n");
        }
        return sb.toString();
    }

    private void a(String str, String str2, Throwable th, Thread thread) throws InterruptedException {
        if (str.contains(str2)) {
            HashMap<String, Object> mapB = b();
            if (mapB == null || mapB.get("file") == null) {
                return;
            }
            Message messageObtain = Message.obtain();
            HashMap map = new HashMap();
            map.put("crashinfo", a(th, (String) mapB.get("time")));
            map.put("file", mapB.get("file"));
            messageObtain.obj = map;
            messageObtain.what = 101;
            this.a.sendMessage(messageObtain);
            a(thread, th);
            return;
        }
        if (this.d != null) {
            this.d.uncaughtException(thread, th);
        }
    }

    private List<String> a(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        try {
            Matcher matcher = Pattern.compile(str2).matcher(str);
            while (matcher.find()) {
                arrayList.add(matcher.group(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    private void a(Thread thread, Throwable th) throws InterruptedException {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Process.killProcess(Process.myPid());
    }

    private HashMap<String, Object> b() {
        HashMap<String, Object> map = new HashMap<>();
        if (this.e == null) {
            return null;
        }
        String strB = e.b(com.mbridge.msdk.foundation.same.b.c.MBRIDGE_CRASH_INFO);
        File file = new File(strB + "/");
        if (!file.exists()) {
            file.mkdirs();
        }
        String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
        File file2 = new File(strB + "/sdkcrash" + str + ".txt");
        try {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file2)));
            printWriter.println(a(this.e, str));
            printWriter.println("====");
            this.e.printStackTrace(printWriter);
            printWriter.close();
            map.put("file", file2);
            map.put("time", str);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
