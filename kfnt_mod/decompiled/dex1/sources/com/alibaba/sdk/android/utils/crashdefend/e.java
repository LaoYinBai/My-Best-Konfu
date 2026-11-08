package com.alibaba.sdk.android.utils.crashdefend;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import android.util.Log;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class e {
    static void a(Context context, a aVar, List<c> list) {
        String str;
        String str2;
        if (context == null) {
            return;
        }
        synchronized (list) {
            FileOutputStream fileOutputStreamOpenFileOutput = null;
            try {
                try {
                    JSONObject jSONObject = new JSONObject();
                    if (aVar != null) {
                        jSONObject.put("startSerialNumber", aVar.f2413a);
                    }
                    if (list != null) {
                        try {
                            JSONArray jSONArray = new JSONArray();
                            for (c cVar : list) {
                                if (cVar != null) {
                                    JSONObject jSONObject2 = new JSONObject();
                                    jSONObject2.put("sdkId", cVar.f58a);
                                    jSONObject2.put(AccountMonitorConstants.NEW_SDK_VERSION_KEY, cVar.f60b);
                                    jSONObject2.put("crashLimit", cVar.f2418a);
                                    jSONObject2.put("crashCount", cVar.crashCount);
                                    jSONObject2.put("waitTime", cVar.f2419b);
                                    jSONObject2.put("registerSerialNumber", cVar.f59b);
                                    jSONObject2.put("startSerialNumber", cVar.f56a);
                                    jSONObject2.put("restoreCount", cVar.f2420c);
                                    jSONArray.put(jSONObject2);
                                }
                            }
                            jSONObject.put("sdkList", jSONArray);
                        } catch (JSONException e) {
                            Log.e("CrashUtils", "save sdk json fail:", e);
                        }
                    }
                    String string = jSONObject.toString();
                    fileOutputStreamOpenFileOutput = m34a(context) ? context.openFileOutput("com_alibaba_aliyun_crash_defend_sdk_info", 0) : context.openFileOutput("com_alibaba_aliyun_crash_defend_sdk_info_" + a(context), 0);
                    fileOutputStreamOpenFileOutput.write(string.getBytes());
                    if (fileOutputStreamOpenFileOutput != null) {
                        try {
                            fileOutputStreamOpenFileOutput.close();
                        } catch (IOException e2) {
                            e = e2;
                            str = "CrashUtils";
                            str2 = "save sdk io fail:";
                            Log.e(str, str2, e);
                        }
                    }
                } catch (IOException e3) {
                    Log.e("CrashUtils", "save sdk io fail:", e3);
                    if (fileOutputStreamOpenFileOutput != null) {
                        try {
                            fileOutputStreamOpenFileOutput.close();
                        } catch (IOException e4) {
                            e = e4;
                            str = "CrashUtils";
                            str2 = "save sdk io fail:";
                            Log.e(str, str2, e);
                        }
                    }
                } catch (Exception e5) {
                    Log.e("CrashUtils", "save sdk exception:", e5);
                    if (fileOutputStreamOpenFileOutput != null) {
                        try {
                            fileOutputStreamOpenFileOutput.close();
                        } catch (IOException e6) {
                            e = e6;
                            str = "CrashUtils";
                            str2 = "save sdk io fail:";
                            Log.e(str, str2, e);
                        }
                    }
                }
            } finally {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x009b A[Catch: all -> 0x0136, DONT_GENERATE, TRY_LEAVE, TryCatch #5 {, blocks: (B:19:0x0049, B:50:0x0095, B:52:0x009b, B:54:0x009d, B:55:0x00b7, B:57:0x00bd, B:59:0x00c3, B:61:0x0116, B:67:0x012d, B:64:0x011d, B:66:0x0126, B:23:0x0052, B:71:0x0132, B:77:0x0140, B:76:0x0139, B:30:0x0063, B:38:0x0077, B:46:0x008b, B:8:0x000c, B:10:0x0012, B:13:0x0034, B:14:0x0038, B:16:0x003e, B:12:0x001a, B:28:0x005a, B:36:0x006e, B:44:0x0082), top: B:84:0x000c, inners: #2, #6, #11 }] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x009d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a, reason: collision with other method in class */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static boolean m35a(android.content.Context r9, com.alibaba.sdk.android.utils.crashdefend.a r10, java.util.List<com.alibaba.sdk.android.utils.crashdefend.c> r11) {
        /*
            Method dump skipped, instructions count: 323
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.utils.crashdefend.e.m35a(android.content.Context, com.alibaba.sdk.android.utils.crashdefend.a, java.util.List):boolean");
    }

    /* renamed from: a, reason: collision with other method in class */
    private static boolean m34a(Context context) {
        return context.getPackageName().equalsIgnoreCase(a(context));
    }

    private static String a(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        ActivityManager activityManager = (ActivityManager) context.getSystemService(TTDownloadField.TT_ACTIVITY);
        if (activityManager == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null) {
            return "";
        }
        int iMyPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == iMyPid) {
                return runningAppProcessInfo.processName;
            }
        }
        return "";
    }
}
