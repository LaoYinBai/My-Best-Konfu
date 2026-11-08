package com.taobao.tlog.adapter;

import android.content.Context;
import android.util.Log;
import com.taobao.tao.log.upload.FileUploadListener;
import com.taobao.tao.log.upload.LogFileUploadManager;
import com.umeng.commonsdk.framework.c;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TLogFileUploader {
    private static boolean isValid;

    @Deprecated
    public static void uploadLogFile(Context context, Map<String, Object> map) {
    }

    static {
        try {
            Class.forName("com.taobao.tao.log.TLog");
            isValid = true;
        } catch (ClassNotFoundException unused) {
            isValid = false;
        }
    }

    public static void uploadLogFile(Context context, String str) {
        HashMap map = new HashMap();
        map.put("type", str);
        uploadLogFile(context, map);
    }

    public static void uploadLogFile(Context context, String str, String str2, Map<String, String> map) {
        new LogFileUploadManager(context).uploadWithFilePrefix(str, str2, map, new FileUploadListener() { // from class: com.taobao.tlog.adapter.TLogFileUploader.1
            public void onError(String str3, String str4, String str5) {
                Log.w("TlogAdapter", "uploadWithFilePrefix failure!" + str4 + " msg:" + str5);
            }

            public void onSucessed(String str3, String str4) {
                Log.w("TlogAdapter", "uploadWithFilePrefix success!");
            }
        });
    }

    public void uploadWithFilePath(Context context, String str, String str2, String str3, String str4, Map<String, String> map) {
        new LogFileUploadManager(context).uploadWithFilePath(str, str2, str3, str4, map, new FileUploadListener() { // from class: com.taobao.tlog.adapter.TLogFileUploader.2
            public void onError(String str5, String str6, String str7) {
                Log.w("TlogAdapter", "uploadWithFilePath failure! " + str6 + " msg:" + str7);
            }

            public void onSucessed(String str5, String str6) {
                Log.w("TlogAdapter", "uploadWithFilePath success!");
            }
        });
    }

    @Deprecated
    public static void uploadLogFile(Context context, Map<String, Object> map, String str) {
        if (isValid) {
            HashMap map2 = new HashMap();
            if (map != null) {
                Object obj = map.get("type");
                if ((obj instanceof String) && c.c.equalsIgnoreCase((String) obj)) {
                    return;
                }
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    String key = entry.getKey();
                    if (entry.getValue() instanceof String) {
                        map2.put(key, (String) entry.getValue());
                    }
                }
            }
            new LogFileUploadManager(context).uploadWithFilePrefix("FEEDBACK", "taobao4android_feedback_21646297", map2, new FileUploadListener() { // from class: com.taobao.tlog.adapter.TLogFileUploader.3
                public void onError(String str2, String str3, String str4) {
                    Log.w("TlogAdapter", "uploadWithFilePrefix failure! " + str3 + " msg:" + str4);
                }

                public void onSucessed(String str2, String str3) {
                    Log.w("TlogAdapter", "uploadWithFilePrefix success!");
                }
            });
        }
    }
}
