package com.bytedance.a;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bytedance.common.utility.Logger;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"MissingPermission"})
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class m {

    /* renamed from: a, reason: collision with root package name */
    public static Account f3530a;

    /* renamed from: b, reason: collision with root package name */
    public static JSONObject f3531b;

    public static Account a(Context context) {
        String string;
        Account account = f3530a;
        if (account != null) {
            return account;
        }
        AccountManager accountManager = AccountManager.get(context);
        String packageName = context.getPackageName();
        try {
            string = context.getString(context.getApplicationInfo().labelRes);
        } catch (Throwable th) {
            Logger.d("NewUserModeUtil", "getAccount:" + th);
            string = null;
        }
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(packageName)) {
            return null;
        }
        Account[] accountsByType = accountManager.getAccountsByType(packageName);
        int length = accountsByType.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            Account account2 = accountsByType[i];
            if (account2 != null && string.equals(account2.name)) {
                f3530a = account2;
                break;
            }
            i++;
        }
        return f3530a;
    }

    /* JADX WARN: Not initialized variable reg: 3, insn: 0x0070: MOVE (r1 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:43:0x0070 */
    public static String a() throws Throwable {
        BufferedReader bufferedReader;
        FileReader fileReader;
        BufferedReader bufferedReader2;
        String[] strArrSplit;
        BufferedReader bufferedReader3 = null;
        try {
            try {
                try {
                    fileReader = new FileReader("/proc/cpuinfo");
                } catch (IOException e) {
                    de.a("", e);
                    return null;
                }
            } catch (IOException e2) {
                e = e2;
                fileReader = null;
                bufferedReader2 = null;
            } catch (Throwable th) {
                th = th;
                fileReader = null;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedReader3 = bufferedReader;
        }
        try {
            bufferedReader2 = new BufferedReader(fileReader);
            while (true) {
                try {
                    String line = bufferedReader2.readLine();
                    if (line == null) {
                        fileReader.close();
                        break;
                    }
                    if (line.contains(":")) {
                        strArrSplit = line.split(":");
                        if (strArrSplit[0].contains("Hardware") || strArrSplit[0].contains("model name")) {
                            break;
                        }
                    }
                } catch (IOException e3) {
                    e = e3;
                    de.a("", e);
                    if (fileReader != null) {
                        fileReader.close();
                    }
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    return null;
                }
            }
            String str = strArrSplit[1];
            try {
                fileReader.close();
                bufferedReader2.close();
            } catch (IOException e4) {
                de.a("", e4);
            }
            return str;
        } catch (IOException e5) {
            e = e5;
            bufferedReader2 = null;
        } catch (Throwable th3) {
            th = th3;
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e6) {
                    de.a("", e6);
                    throw th;
                }
            }
            if (bufferedReader3 != null) {
                bufferedReader3.close();
            }
            throw th;
        }
    }

    public static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; bArr != null && i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i] & 255);
            if (hexString.length() == 1) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static JSONObject a(String str, int i, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (!TextUtils.isEmpty(str)) {
            jSONObject.put("id", str);
            jSONObject.put("slot_index", i);
            jSONObject.put("type", str2);
        }
        return jSONObject;
    }

    public static void a(JSONObject jSONObject, String str, String str2) throws JSONException {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        jSONObject.put(str, str2);
    }

    public static boolean a(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 128) != null;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static boolean a(Object obj, Object obj2) {
        return (obj != null || obj2 == null) && (obj == null || obj2 != null);
    }

    public static boolean a(Object obj, Object obj2, String str) throws JSONException {
        if (!a(obj, obj2)) {
            return false;
        }
        if (obj instanceof JSONObject) {
            return a((JSONObject) obj, (JSONObject) obj2, str);
        }
        if (!(obj instanceof JSONArray)) {
            if (obj.getClass() != obj2.getClass()) {
                return false;
            }
            String string = obj.toString();
            String string2 = obj2.toString();
            return a(string, string2) && string.equals(string2);
        }
        JSONArray jSONArray = (JSONArray) obj;
        JSONArray jSONArray2 = (JSONArray) obj2;
        if (a(jSONArray, jSONArray2)) {
            HashMap map = new HashMap();
            for (int i = 0; i < jSONArray.length(); i++) {
                Object obj3 = jSONArray.get(i);
                map.put(obj3, (!map.containsKey(obj3) || map.get(obj3) == null) ? 1 : Integer.valueOf(((Integer) map.get(obj3)).intValue() + 1));
            }
            HashMap map2 = new HashMap();
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                Object obj4 = jSONArray2.get(i2);
                map2.put(obj4, (!map2.containsKey(obj4) || map2.get(obj4) == null) ? 1 : Integer.valueOf(((Integer) map2.get(obj4)).intValue() + 1));
            }
            if (map.size() == map2.size()) {
                for (Map.Entry entry : map.entrySet()) {
                    if (!((Integer) entry.getValue()).equals((Integer) map2.get(entry.getKey()))) {
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static boolean a(@Nullable JSONArray jSONArray) {
        int length;
        if (jSONArray == null || (length = jSONArray.length()) <= 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject != null && !TextUtils.isEmpty(jSONObjectOptJSONObject.optString("id"))) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(JSONObject jSONObject, JSONObject jSONObject2, String str) throws JSONException {
        if (!a(jSONObject, jSONObject2)) {
            return false;
        }
        if (jSONObject != null && jSONObject.length() != jSONObject2.length()) {
            return false;
        }
        Iterator<String> itKeys = jSONObject.keys();
        boolean zA = true;
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            zA = a(jSONObject.get(next), jSONObject2.get(next), next);
            if (!zA) {
                break;
            }
        }
        return zA;
    }

    public static boolean a(JSONObject jSONObject, @Nullable Class<?>[] clsArr, @Nullable Class<?>[] clsArr2) throws JSONException {
        if (jSONObject == null) {
            return false;
        }
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            Object obj = jSONObject.get(itKeys.next());
            if (obj == null) {
                return false;
            }
            if (obj instanceof JSONArray) {
                JSONArray jSONArray = (JSONArray) obj;
                for (int i = 0; i < jSONArray.length(); i++) {
                    Object obj2 = jSONArray.get(i);
                    if (clsArr2 != null && !a(clsArr2, obj2.getClass())) {
                        return false;
                    }
                }
            } else if (clsArr != null && !a(clsArr, obj.getClass())) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean a(T[] tArr, T t) {
        for (T t2 : tArr) {
            if (t2 == t) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x003c, code lost:
    
        r3 = java.lang.Long.parseLong(java.util.regex.Pattern.compile("[^0-9]").matcher(r0).replaceAll("").trim()) * android.support.v4.media.session.PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0040, code lost:
    
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0044, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0045, code lost:
    
        com.bytedance.a.de.a("", r0);
     */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00ab A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x009f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:94:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static long b() throws java.lang.Throwable {
        /*
            r0 = 0
            java.io.FileReader r1 = new java.io.FileReader     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L77
            java.lang.String r2 = "/proc/meminfo"
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L77
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L6c
            r3 = 8192(0x2000, float:1.148E-41)
            r2.<init>(r1, r3)     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L6c
        Lf:
            java.lang.String r0 = r2.readLine()     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L62
            if (r0 == 0) goto L55
            boolean r3 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L62
            if (r3 == 0) goto L1c
            goto Lf
        L1c:
            java.lang.String r3 = "MemTotal"
            boolean r3 = r0.contains(r3)     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L62
            if (r3 == 0) goto Lf
            java.lang.String r3 = "[^0-9]"
            java.util.regex.Pattern r3 = java.util.regex.Pattern.compile(r3)     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L62
            java.util.regex.Matcher r0 = r3.matcher(r0)     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L62
            java.lang.String r3 = ""
            java.lang.String r0 = r0.replaceAll(r3)     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L62
            java.lang.String r0 = r0.trim()     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L62
            long r3 = java.lang.Long.parseLong(r0)     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L62
            r5 = 1024(0x400, double:5.06E-321)
            long r3 = r3 * r5
            r1.close()     // Catch: java.io.IOException -> L44
            goto L4a
        L44:
            r0 = move-exception
            java.lang.String r1 = ""
            com.bytedance.a.de.a(r1, r0)
        L4a:
            r2.close()     // Catch: java.io.IOException -> L4e
            goto L54
        L4e:
            r0 = move-exception
            java.lang.String r1 = ""
            com.bytedance.a.de.a(r1, r0)
        L54:
            return r3
        L55:
            r1.close()     // Catch: java.io.IOException -> L59
            goto L8c
        L59:
            r0 = move-exception
            java.lang.String r1 = ""
            com.bytedance.a.de.a(r1, r0)
            goto L8c
        L60:
            r0 = move-exception
            goto L9d
        L62:
            r0 = move-exception
            r7 = r1
            r1 = r0
            r0 = r7
            goto L79
        L67:
            r2 = move-exception
            r7 = r2
            r2 = r0
        L6a:
            r0 = r7
            goto L9d
        L6c:
            r2 = move-exception
            r7 = r2
            r2 = r0
            r0 = r1
            r1 = r7
            goto L79
        L72:
            r1 = move-exception
            r2 = r0
            r0 = r1
            r1 = r2
            goto L9d
        L77:
            r1 = move-exception
            r2 = r0
        L79:
            java.lang.String r3 = ""
            com.bytedance.a.de.a(r3, r1)     // Catch: java.lang.Throwable -> L99
            if (r0 == 0) goto L8a
            r0.close()     // Catch: java.io.IOException -> L84
            goto L8a
        L84:
            r0 = move-exception
            java.lang.String r1 = ""
            com.bytedance.a.de.a(r1, r0)
        L8a:
            if (r2 == 0) goto L96
        L8c:
            r2.close()     // Catch: java.io.IOException -> L90
            goto L96
        L90:
            r0 = move-exception
            java.lang.String r1 = ""
            com.bytedance.a.de.a(r1, r0)
        L96:
            r0 = -1
            return r0
        L99:
            r1 = move-exception
            r7 = r1
            r1 = r0
            goto L6a
        L9d:
            if (r1 == 0) goto La9
            r1.close()     // Catch: java.io.IOException -> La3
            goto La9
        La3:
            r1 = move-exception
            java.lang.String r3 = ""
            com.bytedance.a.de.a(r3, r1)
        La9:
            if (r2 == 0) goto Lb5
            r2.close()     // Catch: java.io.IOException -> Laf
            goto Lb5
        Laf:
            r1 = move-exception
            java.lang.String r2 = ""
            com.bytedance.a.de.a(r2, r1)
        Lb5:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.a.m.b():long");
    }

    public static WifiInfo b(Context context) {
        if (context == null) {
            return null;
        }
        try {
            WifiInfo connectionInfo = ((WifiManager) context.getApplicationContext().getSystemService(OnekeyLoginConstants.NETWORK_TYPE_WIFI_STR)).getConnectionInfo();
            NetworkInfo networkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getNetworkInfo(1);
            if (networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED && connectionInfo != null) {
                if (connectionInfo.getSSID() != null) {
                    return connectionInfo;
                }
            }
        } catch (Exception e) {
            de.a("", e);
        }
        return null;
    }

    public static String b(String str) throws Throwable {
        FileInputStream fileInputStream;
        File file = new File(str);
        StringBuilder sb = new StringBuilder();
        if (!file.exists()) {
            return "";
        }
        BufferedReader bufferedReader = null;
        try {
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(fileInputStream));
                    while (true) {
                        try {
                            String line = bufferedReader2.readLine();
                            if (line == null) {
                                break;
                            }
                            sb.append(line);
                        } catch (IOException e) {
                            e = e;
                            bufferedReader = bufferedReader2;
                            de.a("", e);
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e2) {
                                    de.a("", e2);
                                }
                            }
                            if (fileInputStream == null) {
                                return "";
                            }
                            try {
                                fileInputStream.close();
                                return "";
                            } catch (IOException e3) {
                                de.a("", e3);
                                return "";
                            }
                        } catch (Throwable th) {
                            th = th;
                            bufferedReader = bufferedReader2;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e4) {
                                    de.a("", e4);
                                }
                            }
                            if (fileInputStream == null) {
                                throw th;
                            }
                            try {
                                fileInputStream.close();
                                throw th;
                            } catch (IOException e5) {
                                de.a("", e5);
                                throw th;
                            }
                        }
                    }
                    String string = sb.toString();
                    try {
                        bufferedReader2.close();
                    } catch (IOException e6) {
                        de.a("", e6);
                    }
                    try {
                        fileInputStream.close();
                    } catch (IOException e7) {
                        de.a("", e7);
                    }
                    return string;
                } catch (IOException e8) {
                    e = e8;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e9) {
            e = e9;
            fileInputStream = null;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(7:7|(5:56|8|54|9|10)|(4:58|11|(2:12|(1:14)(1:48))|16)|46|34|35|36) */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] b(byte[] r6) throws java.lang.Throwable {
        /*
            r0 = 0
            if (r6 == 0) goto L4f
            int r1 = r6.length
            if (r1 > 0) goto L7
            goto L4f
        L7:
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream
            r1.<init>()
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L3f
            r2.<init>(r6)     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L3f
            java.util.zip.GZIPInputStream r6 = new java.util.zip.GZIPInputStream     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L40
            r6.<init>(r2)     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L40
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r0]     // Catch: java.lang.Throwable -> L29 java.io.IOException -> L2e
        L1a:
            int r3 = r6.read(r0)     // Catch: java.lang.Throwable -> L29 java.io.IOException -> L2e
            if (r3 < 0) goto L25
            r4 = 0
            r1.write(r0, r4, r3)     // Catch: java.lang.Throwable -> L29 java.io.IOException -> L2e
            goto L1a
        L25:
            r6.close()     // Catch: java.io.IOException -> L47
            goto L47
        L29:
            r0 = move-exception
            r5 = r0
            r0 = r6
            r6 = r5
            goto L34
        L2e:
            r0 = r6
            goto L40
        L30:
            r6 = move-exception
            goto L34
        L32:
            r6 = move-exception
            r2 = r0
        L34:
            if (r0 == 0) goto L39
            r0.close()     // Catch: java.io.IOException -> L39
        L39:
            if (r2 == 0) goto L3e
            r2.close()     // Catch: java.io.IOException -> L3e
        L3e:
            throw r6
        L3f:
            r2 = r0
        L40:
            if (r0 == 0) goto L45
            r0.close()     // Catch: java.io.IOException -> L45
        L45:
            if (r2 == 0) goto L4a
        L47:
            r2.close()     // Catch: java.io.IOException -> L4a
        L4a:
            byte[] r6 = r1.toByteArray()
            return r6
        L4f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.a.m.b(byte[]):byte[]");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.io.IOException, java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v9 */
    public static String c() throws Throwable {
        InputStream inputStream;
        String str = "";
        ?? e = 0;
        e = 0;
        try {
            try {
                try {
                    inputStream = new ProcessBuilder("/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq").start().getInputStream();
                } catch (Throwable th) {
                    th = th;
                }
            } catch (IOException e2) {
                e = e2;
            }
        } catch (IOException e3) {
            e = e3;
            de.a("", e);
        }
        try {
            byte[] bArr = new byte[24];
            while (inputStream.read(bArr) != -1) {
                str = str + new String(bArr);
            }
            inputStream.close();
            e = bArr;
        } catch (IOException e4) {
            e = e4;
            e = inputStream;
            de.a("", e);
            str = "N/A";
            if (e != 0) {
                e.close();
                e = e;
            }
            return str.trim();
        } catch (Throwable th2) {
            th = th2;
            e = inputStream;
            if (e != 0) {
                try {
                    e.close();
                } catch (IOException e5) {
                    de.a("", e5);
                }
            }
            throw th;
        }
        return str.trim();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.String[]] */
    /* JADX WARN: Type inference failed for: r1v3, types: [int] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    @SuppressLint({"HardwareIds"})
    public static String[] c(Context context) {
        ?? r1;
        int i;
        List<SubscriptionInfo> activeSubscriptionInfoList;
        ?? r0 = 0;
        if (context == null) {
            return null;
        }
        try {
            r1 = Build.VERSION.SDK_INT;
            i = 0;
            try {
                try {
                } catch (SecurityException e) {
                    e = e;
                }
            } catch (Throwable th) {
                th = th;
                de.a("", th);
                return r1;
            }
        } catch (Throwable th2) {
            th = th2;
            r1 = r0;
        }
        if (r1 >= 22) {
            try {
                activeSubscriptionInfoList = SubscriptionManager.from(context).getActiveSubscriptionInfoList();
            } catch (SecurityException e2) {
                e = e2;
                r1 = 0;
                Object obj = r1;
                de.a("", e);
                r1 = obj;
                r0 = obj;
                return r1;
            }
            if (activeSubscriptionInfoList != null && !activeSubscriptionInfoList.isEmpty()) {
                String[] strArr = new String[activeSubscriptionInfoList.size()];
                while (true) {
                    int size = activeSubscriptionInfoList.size();
                    r0 = size;
                    r1 = strArr;
                    if (i < size) {
                        strArr[i] = activeSubscriptionInfoList.get(i).getIccId();
                        i++;
                    }
                }
                return r1;
            }
            return null;
        }
        String[] strArr2 = new String[1];
        String str = "phone";
        strArr2[0] = ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
        r0 = str;
        r1 = strArr2;
        return r1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.io.IOException, java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v9 */
    public static String d() throws Throwable {
        InputStream inputStream;
        String str = "";
        ?? e = 0;
        e = 0;
        try {
            try {
                try {
                    inputStream = new ProcessBuilder("/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq").start().getInputStream();
                } catch (Throwable th) {
                    th = th;
                }
            } catch (IOException e2) {
                e = e2;
            }
        } catch (IOException e3) {
            e = e3;
            de.a("", e);
        }
        try {
            byte[] bArr = new byte[24];
            while (inputStream.read(bArr) != -1) {
                str = str + new String(bArr);
            }
            inputStream.close();
            e = bArr;
        } catch (IOException e4) {
            e = e4;
            e = inputStream;
            de.a("", e);
            str = "N/A";
            if (e != 0) {
                e.close();
                e = e;
            }
            return str.trim();
        } catch (Throwable th2) {
            th = th2;
            e = inputStream;
            if (e != 0) {
                try {
                    e.close();
                } catch (IOException e5) {
                    de.a("", e5);
                }
            }
            throw th;
        }
        return str.trim();
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x004b, code lost:
    
        r1 = new java.lang.StringBuilder();
        r3 = r2.length;
        r5 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0054, code lost:
    
        if (r5 >= r3) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x005a, code lost:
    
        r1.append(java.lang.String.format("%02X:", java.lang.Byte.valueOf(r2[r5])));
        r5 = r5 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0070, code lost:
    
        if (r1.length() <= 0) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0072, code lost:
    
        r1.deleteCharAt(r1.length() - 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x007e, code lost:
    
        r0 = r1.toString();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String d(android.content.Context r9) throws java.net.SocketException {
        /*
            com.bytedance.applog.InitConfig r0 = com.bytedance.applog.AppLog.getInitConfig()
            if (r0 == 0) goto L1d
            com.bytedance.applog.InitConfig r0 = com.bytedance.applog.AppLog.getInitConfig()
            com.bytedance.applog.ISensitiveInfoProvider r0 = r0.getSensitiveInfoProvider()
            if (r0 == 0) goto L1d
            com.bytedance.applog.InitConfig r9 = com.bytedance.applog.AppLog.getInitConfig()
            com.bytedance.applog.ISensitiveInfoProvider r9 = r9.getSensitiveInfoProvider()
            java.lang.String r9 = r9.getMac()
            return r9
        L1d:
            r0 = 0
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 23
            if (r1 < r2) goto L86
            java.util.Enumeration r1 = java.net.NetworkInterface.getNetworkInterfaces()     // Catch: java.net.SocketException -> L80
        L28:
            if (r1 == 0) goto L86
            boolean r2 = r1.hasMoreElements()     // Catch: java.net.SocketException -> L80
            if (r2 == 0) goto L86
            java.lang.Object r2 = r1.nextElement()     // Catch: java.net.SocketException -> L80
            java.net.NetworkInterface r2 = (java.net.NetworkInterface) r2     // Catch: java.net.SocketException -> L80
            java.lang.String r3 = "wlan0"
            java.lang.String r4 = r2.getName()     // Catch: java.net.SocketException -> L80
            boolean r3 = r3.equals(r4)     // Catch: java.net.SocketException -> L80
            if (r3 == 0) goto L28
            byte[] r2 = r2.getHardwareAddress()     // Catch: java.net.SocketException -> L80
            if (r2 == 0) goto L28
            int r3 = r2.length     // Catch: java.net.SocketException -> L80
            if (r3 <= 0) goto L28
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.net.SocketException -> L80
            r1.<init>()     // Catch: java.net.SocketException -> L80
            int r3 = r2.length     // Catch: java.net.SocketException -> L80
            r4 = 0
            r5 = 0
        L53:
            r6 = 1
            if (r5 >= r3) goto L6c
            r7 = r2[r5]     // Catch: java.net.SocketException -> L80
            java.lang.String r8 = "%02X:"
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: java.net.SocketException -> L80
            java.lang.Byte r7 = java.lang.Byte.valueOf(r7)     // Catch: java.net.SocketException -> L80
            r6[r4] = r7     // Catch: java.net.SocketException -> L80
            java.lang.String r6 = java.lang.String.format(r8, r6)     // Catch: java.net.SocketException -> L80
            r1.append(r6)     // Catch: java.net.SocketException -> L80
            int r5 = r5 + 1
            goto L53
        L6c:
            int r2 = r1.length()     // Catch: java.net.SocketException -> L80
            if (r2 <= 0) goto L7a
            int r2 = r1.length()     // Catch: java.net.SocketException -> L80
            int r2 = r2 - r6
            r1.deleteCharAt(r2)     // Catch: java.net.SocketException -> L80
        L7a:
            java.lang.String r1 = r1.toString()     // Catch: java.net.SocketException -> L80
            r0 = r1
            goto L86
        L80:
            r1 = move-exception
            java.lang.String r2 = "U SHALL NOT PASS!"
            com.bytedance.a.de.a(r2, r1)
        L86:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto La5
            java.lang.String r1 = "wifi"
            java.lang.Object r9 = r9.getSystemService(r1)     // Catch: java.lang.SecurityException -> L9f
            android.net.wifi.WifiManager r9 = (android.net.wifi.WifiManager) r9     // Catch: java.lang.SecurityException -> L9f
            android.net.wifi.WifiInfo r9 = r9.getConnectionInfo()     // Catch: java.lang.SecurityException -> L9f
            if (r9 == 0) goto La5
            java.lang.String r9 = r9.getMacAddress()     // Catch: java.lang.SecurityException -> L9f
            goto La6
        L9f:
            r9 = move-exception
            java.lang.String r1 = "U SHALL NOT PASS!"
            com.bytedance.a.de.a(r1, r9)
        La5:
            r9 = r0
        La6:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.a.m.d(android.content.Context):java.lang.String");
    }

    @NonNull
    @SuppressLint({"MissingPermission"})
    public static JSONArray e(Context context) {
        JSONArray jSONArray = new JSONArray();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (Build.VERSION.SDK_INT >= 26) {
            try {
                jSONArray.put(a(telephonyManager.getMeid(0), 0, "meid"));
            } catch (Exception e) {
                de.a("", e);
            }
            try {
                jSONArray.put(a(telephonyManager.getMeid(1), 1, "meid"));
            } catch (Exception e2) {
                de.a("", e2);
            }
            try {
                jSONArray.put(a(telephonyManager.getImei(0), 0, "imei"));
            } catch (Exception e3) {
                de.a("", e3);
            }
            try {
                jSONArray.put(a(telephonyManager.getImei(1), 1, "imei"));
            } catch (Exception e4) {
                de.a("", e4);
            }
        } else {
            jSONArray.put(a(a(context, 0), 0, "unknown"));
            jSONArray.put(a(a(context, 1), 1, "unknown"));
        }
        return jSONArray;
    }

    @SuppressLint({"HardwareIds"})
    public static String f(Context context) {
        String serial;
        if (context == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT < 26 || context.getApplicationInfo().targetSdkVersion < 26) {
            serial = null;
        } else {
            try {
                serial = Build.getSerial();
            } catch (SecurityException e) {
                de.a("failed to get Build.SERIAL as no permission: READ_PRIVILEGED_PHONE_STATE or READ_PHONE_STATE", e);
            } catch (Throwable th) {
                de.a("U SHALL NOT PASS!", th);
            }
        }
        if (TextUtils.isEmpty(serial) || TextUtils.equals(serial, "unknown")) {
            serial = Build.SERIAL;
        }
        return (TextUtils.isEmpty(serial) || TextUtils.equals(serial, "unknown")) ? "" : serial;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] a(java.lang.String r3) {
        /*
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r1 = 8192(0x2000, float:1.148E-41)
            r0.<init>(r1)
            r1 = 0
            boolean r2 = com.bytedance.applog.AppLog.getEncryptAndCompress()     // Catch: java.lang.Throwable -> L30
            if (r2 == 0) goto L21
            java.util.zip.GZIPOutputStream r2 = new java.util.zip.GZIPOutputStream     // Catch: java.lang.Throwable -> L30
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L30
            java.lang.String r1 = "UTF-8"
            byte[] r3 = r3.getBytes(r1)     // Catch: java.lang.Throwable -> L1e
            r2.write(r3)     // Catch: java.lang.Throwable -> L1e
            r1 = r2
            goto L2a
        L1e:
            r3 = move-exception
            r1 = r2
            goto L31
        L21:
            java.lang.String r2 = "UTF-8"
            byte[] r3 = r3.getBytes(r2)     // Catch: java.lang.Throwable -> L30
            r0.write(r3)     // Catch: java.lang.Throwable -> L30
        L2a:
            if (r1 == 0) goto L42
            r1.close()     // Catch: java.io.IOException -> L3c
            goto L42
        L30:
            r3 = move-exception
        L31:
            java.lang.String r2 = "U SHALL NOT PASS!"
            com.bytedance.a.de.a(r2, r3)     // Catch: java.lang.Throwable -> L62
            if (r1 == 0) goto L42
            r1.close()     // Catch: java.io.IOException -> L3c
            goto L42
        L3c:
            r3 = move-exception
            java.lang.String r1 = "U SHALL NOT PASS!"
            com.bytedance.a.de.a(r1, r3)
        L42:
            byte[] r3 = r0.toByteArray()
            boolean r0 = com.bytedance.applog.AppLog.getEncryptAndCompress()
            if (r0 == 0) goto L61
            com.bytedance.applog.InitConfig r0 = com.bytedance.applog.AppLog.getInitConfig()
            com.bytedance.mpaas.IEncryptor r0 = r0.getEncryptor()
            if (r0 == 0) goto L5c
            int r1 = r3.length
            byte[] r3 = r0.encrypt(r3, r1)
            goto L61
        L5c:
            int r0 = r3.length
            byte[] r3 = com.bytedance.applog.encryptor.EncryptorUtil.encrypt(r3, r0)
        L61:
            return r3
        L62:
            r3 = move-exception
            if (r1 == 0) goto L6f
            r1.close()     // Catch: java.io.IOException -> L69
            goto L6f
        L69:
            r0 = move-exception
            java.lang.String r1 = "U SHALL NOT PASS!"
            com.bytedance.a.de.a(r1, r0)
        L6f:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.a.m.a(java.lang.String):byte[]");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0034  */
    @androidx.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(android.content.Context r4, int r5) {
        /*
            java.lang.String r0 = "getDeviceId"
            r1 = 0
            java.lang.String r2 = "phone"
            java.lang.Object r4 = r4.getSystemService(r2)     // Catch: java.lang.Exception -> L2e
            android.telephony.TelephonyManager r4 = (android.telephony.TelephonyManager) r4     // Catch: java.lang.Exception -> L2e
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Exception -> L2e
            r3 = 21
            if (r2 < r3) goto L34
            java.lang.Class r2 = r4.getClass()     // Catch: java.lang.Exception -> L2e
            java.lang.Class[] r3 = c(r0)     // Catch: java.lang.Exception -> L2e
            java.lang.reflect.Method r0 = r2.getMethod(r0, r3)     // Catch: java.lang.Exception -> L2e
            if (r5 < 0) goto L34
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Exception -> L2e
            r3 = 0
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch: java.lang.Exception -> L2e
            r2[r3] = r5     // Catch: java.lang.Exception -> L2e
            java.lang.Object r4 = r0.invoke(r4, r2)     // Catch: java.lang.Exception -> L2e
            goto L35
        L2e:
            r4 = move-exception
            java.lang.String r5 = ""
            com.bytedance.a.de.a(r5, r4)
        L34:
            r4 = r1
        L35:
            java.lang.String r4 = (java.lang.String) r4
            boolean r5 = com.bytedance.a.de.f3494c
            if (r5 == 0) goto L4f
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r0 = "getDeviceId  deviceId="
            r5.append(r0)
            r5.append(r4)
            java.lang.String r5 = r5.toString()
            com.bytedance.a.de.a(r5, r1)
        L4f:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.a.m.a(android.content.Context, int):java.lang.String");
    }

    public static Class[] c(String str) throws SecurityException {
        Class<?>[] clsArr;
        try {
            clsArr = null;
            for (Method method : TelephonyManager.class.getDeclaredMethods()) {
                try {
                    if (str.equals(method.getName())) {
                        Class<?>[] parameterTypes = method.getParameterTypes();
                        try {
                            if (parameterTypes.length >= 1) {
                                de.a("length:" + parameterTypes.length, null);
                                return parameterTypes;
                            }
                            clsArr = parameterTypes;
                        } catch (Exception e) {
                            e = e;
                            clsArr = parameterTypes;
                            de.a("", e);
                            return clsArr;
                        }
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            }
            return clsArr;
        } catch (Exception e3) {
            e = e3;
            clsArr = null;
        }
    }
}
