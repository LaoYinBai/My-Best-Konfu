package com.unity3d.services.core.misc;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import com.sigmob.sdk.common.Constants;
import com.ss.union.game.sdk.common.util.StringEncryptUtils;
import com.unity3d.services.core.log.DeviceLog;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import kotlin.UByte;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class Utilities {
    public static void runOnUiThread(Runnable runnable) {
        runOnUiThread(runnable, 0L);
    }

    public static void runOnUiThread(Runnable runnable, long j) {
        Handler handler = new Handler(Looper.getMainLooper());
        if (j > 0) {
            handler.postDelayed(runnable, j);
        } else {
            handler.post(runnable);
        }
    }

    public static String Sha256(String str) {
        return Sha256(str.getBytes());
    }

    public static String Sha256(byte[] bArr) throws IllegalAccessException, NoSuchMethodException, NoSuchAlgorithmException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (bArr == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(StringEncryptUtils.SHA_256);
            messageDigest.update(bArr, 0, bArr.length);
            return toHexString(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            DeviceLog.exception("SHA-256 algorithm not found", e);
            return null;
        }
    }

    public static String Sha256(InputStream inputStream) throws IllegalAccessException, NoSuchMethodException, NoSuchAlgorithmException, IOException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (inputStream == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(StringEncryptUtils.SHA_256);
            byte[] bArr = new byte[4096];
            while (true) {
                int i = inputStream.read(bArr);
                if (i != -1) {
                    messageDigest.update(bArr, 0, i);
                } else {
                    return toHexString(messageDigest.digest());
                }
            }
        } catch (NoSuchAlgorithmException e) {
            DeviceLog.exception("SHA-256 algorithm not found", e);
            return null;
        }
    }

    public static String toHexString(byte[] bArr) {
        String str = "";
        for (byte b : bArr) {
            int i = b & UByte.MAX_VALUE;
            if (i <= 15) {
                str = str + Constants.FAIL;
            }
            str = str + Integer.toHexString(i);
        }
        return str;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x003d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean writeFile(java.io.File r4, java.lang.String r5) throws java.lang.Throwable {
        /*
            r0 = 0
            if (r4 != 0) goto L4
            return r0
        L4:
            r1 = 0
            r2 = 1
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L27 java.lang.Exception -> L29
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L27 java.lang.Exception -> L29
            byte[] r5 = r5.getBytes()     // Catch: java.lang.Throwable -> L21 java.lang.Exception -> L24
            r3.write(r5)     // Catch: java.lang.Throwable -> L21 java.lang.Exception -> L24
            r3.flush()     // Catch: java.lang.Throwable -> L21 java.lang.Exception -> L24
            r3.close()     // Catch: java.lang.Exception -> L19
            goto L1f
        L19:
            r5 = move-exception
            java.lang.String r0 = "Error closing FileOutputStream"
            com.unity3d.services.core.log.DeviceLog.exception(r0, r5)
        L1f:
            r0 = 1
            goto L3b
        L21:
            r4 = move-exception
            r1 = r3
            goto L56
        L24:
            r5 = move-exception
            r1 = r3
            goto L2a
        L27:
            r4 = move-exception
            goto L56
        L29:
            r5 = move-exception
        L2a:
            java.lang.String r2 = "Could not write file"
            com.unity3d.services.core.log.DeviceLog.exception(r2, r5)     // Catch: java.lang.Throwable -> L27
            if (r1 == 0) goto L3b
            r1.close()     // Catch: java.lang.Exception -> L35
            goto L3b
        L35:
            r5 = move-exception
            java.lang.String r1 = "Error closing FileOutputStream"
            com.unity3d.services.core.log.DeviceLog.exception(r1, r5)
        L3b:
            if (r0 == 0) goto L55
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r1 = "Wrote file: "
            r5.append(r1)
            java.lang.String r4 = r4.getAbsolutePath()
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            com.unity3d.services.core.log.DeviceLog.debug(r4)
        L55:
            return r0
        L56:
            if (r1 == 0) goto L62
            r1.close()     // Catch: java.lang.Exception -> L5c
            goto L62
        L5c:
            r5 = move-exception
            java.lang.String r0 = "Error closing FileOutputStream"
            com.unity3d.services.core.log.DeviceLog.exception(r0, r5)
        L62:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.services.core.misc.Utilities.writeFile(java.io.File, java.lang.String):boolean");
    }

    public static byte[] readFileBytes(File file) throws Throwable {
        FileInputStream fileInputStream;
        if (file == null || !file.exists()) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            return Files.readAllBytes(file.toPath());
        }
        try {
            fileInputStream = new FileInputStream(file);
            try {
                byte[] bArr = new byte[(int) file.length()];
                if (fileInputStream.read(bArr) != bArr.length) {
                    throw new IOException("Failed to read all bytes from input file path: " + file.getPath());
                }
                fileInputStream.close();
                return bArr;
            } catch (Throwable th) {
                th = th;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
        }
    }

    public static JSONObject mergeJsonObjects(JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        if (jSONObject == null) {
            return jSONObject2;
        }
        if (jSONObject2 == null) {
            return jSONObject;
        }
        JSONObject jSONObject3 = new JSONObject();
        Iterator<String> itKeys = jSONObject2.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            jSONObject3.put(next, jSONObject2.get(next));
        }
        Iterator<String> itKeys2 = jSONObject.keys();
        while (itKeys2.hasNext()) {
            String next2 = itKeys2.next();
            if (jSONObject3.has(next2) && (jSONObject3.get(next2) instanceof JSONObject) && (jSONObject.get(next2) instanceof JSONObject)) {
                jSONObject3.put(next2, mergeJsonObjects(jSONObject.getJSONObject(next2), jSONObject3.getJSONObject(next2)));
            } else {
                jSONObject3.put(next2, jSONObject.get(next2));
            }
        }
        return jSONObject3;
    }
}
