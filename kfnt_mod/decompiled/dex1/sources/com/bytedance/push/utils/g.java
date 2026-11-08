package com.bytedance.push.utils;

import android.content.Context;
import android.util.Base64;
import java.io.File;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class g {
    public static JSONObject a(byte[] bArr, boolean z) throws DataFormatException, IOException {
        String str;
        String str2;
        byte[] bArr2 = new byte[65536];
        char c2 = (char) bArr[0];
        if (c2 == 'a') {
            if (com.bytedance.common.push.utility.a.b()) {
                com.bytedance.common.push.utility.a.b("PushService getMessage", "uncopress message");
            }
            str = new String(bArr, 1, bArr.length - 1);
        } else if (c2 == 'c') {
            Inflater inflater = new Inflater();
            if (z) {
                inflater.setInput(Base64.decode(bArr, 1, bArr.length - 1, 0));
            } else {
                inflater.setInput(bArr, 1, bArr.length - 1);
            }
            if (com.bytedance.common.push.utility.a.b()) {
                com.bytedance.common.push.utility.a.b("PushService getMessage", "copress message");
            }
            synchronized (bArr2) {
                int iInflate = inflater.inflate(bArr2);
                inflater.end();
                str2 = (iInflate <= 0 || iInflate >= bArr2.length) ? null : new String(bArr2, 0, iInflate, "UTF-8");
            }
            str = str2;
        } else {
            if (com.bytedance.common.push.utility.a.b()) {
                com.bytedance.common.push.utility.a.b("PushService", "onMessage : " + new String(bArr, 0, bArr.length));
            }
            str = null;
        }
        if (str != null) {
            try {
                return new JSONObject(str);
            } catch (JSONException e) {
                e.printStackTrace();
                if (com.bytedance.common.push.utility.a.b()) {
                    com.bytedance.common.push.utility.a.b("PushService", "onMessage : " + new String(bArr, 0, bArr.length));
                }
            }
        }
        return null;
    }

    public static void a(Context context) throws IOException {
        if (context == null) {
            return;
        }
        a(context, new String[]{"lockFile", "lockFile1", "lockFile2"});
        a(context, new String[]{"observerFile", "observerFile1", "observerFile2"});
    }

    public static void a(Context context, String[] strArr) {
        if (context == null || strArr == null) {
            return;
        }
        try {
            String strB = b(context);
            if (strB == null) {
                return;
            }
            for (String str : strArr) {
                File file = new File(strB + "/files/" + str);
                if (file.exists()) {
                    file.delete();
                }
            }
        } catch (Exception unused) {
        }
    }

    public static String b(Context context) {
        File filesDir;
        File parentFile;
        if (context == null || (filesDir = context.getFilesDir()) == null || !filesDir.exists() || (parentFile = filesDir.getParentFile()) == null || !parentFile.exists()) {
            return null;
        }
        return parentFile.getAbsolutePath();
    }
}
