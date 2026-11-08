package b.b.a.a;

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
import androidx.annotation.Nullable;
import b.b.a.k.v;
import com.bytedance.common.utility.Logger;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"MissingPermission"})
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    public static Account f1032a;

    /* renamed from: b, reason: collision with root package name */
    public static JSONObject f1033b;

    public static boolean a(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 128) != null;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:42:? A[RETURN, SYNTHETIC] */
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
            boolean r2 = com.apm.applog.a.f()     // Catch: java.lang.Throwable -> L30
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
            if (r1 == 0) goto L3e
            r1.close()     // Catch: java.io.IOException -> L3a
            goto L3e
        L30:
            r3 = move-exception
        L31:
            b.b.a.k.v.a(r3)     // Catch: java.lang.Throwable -> L4e
            if (r1 == 0) goto L3e
            r1.close()     // Catch: java.io.IOException -> L3a
            goto L3e
        L3a:
            r3 = move-exception
            b.b.a.k.v.a(r3)
        L3e:
            byte[] r3 = r0.toByteArray()
            boolean r0 = com.apm.applog.a.f()
            if (r0 == 0) goto L4d
            int r0 = r3.length
            byte[] r3 = com.bytedance.applog.encryptor.EncryptorUtil.encrypt(r3, r0)
        L4d:
            return r3
        L4e:
            r3 = move-exception
            if (r1 == 0) goto L59
            r1.close()     // Catch: java.io.IOException -> L55
            goto L59
        L55:
            r0 = move-exception
            b.b.a.k.v.a(r0)
        L59:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.a.a.b.a(java.lang.String):byte[]");
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
    
        r0.printStackTrace();
     */
    /* JADX WARN: Removed duplicated region for block: B:77:0x008e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0098 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:92:? A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:49:0x0082 -> B:73:0x0085). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static long b() throws java.lang.Throwable {
        /*
            r0 = 0
            java.io.FileReader r1 = new java.io.FileReader     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L6a
            java.lang.String r2 = "/proc/meminfo"
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L6a
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L5f java.lang.Exception -> L61
            r3 = 8192(0x2000, float:1.148E-41)
            r2.<init>(r1, r3)     // Catch: java.lang.Throwable -> L5f java.lang.Exception -> L61
        Lf:
            java.lang.String r0 = r2.readLine()     // Catch: java.lang.Exception -> L5d java.lang.Throwable -> L88
            if (r0 == 0) goto L51
            boolean r3 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Exception -> L5d java.lang.Throwable -> L88
            if (r3 == 0) goto L1c
            goto Lf
        L1c:
            java.lang.String r3 = "MemTotal"
            boolean r3 = r0.contains(r3)     // Catch: java.lang.Exception -> L5d java.lang.Throwable -> L88
            if (r3 == 0) goto Lf
            java.lang.String r3 = "[^0-9]"
            java.util.regex.Pattern r3 = java.util.regex.Pattern.compile(r3)     // Catch: java.lang.Exception -> L5d java.lang.Throwable -> L88
            java.util.regex.Matcher r0 = r3.matcher(r0)     // Catch: java.lang.Exception -> L5d java.lang.Throwable -> L88
            java.lang.String r3 = ""
            java.lang.String r0 = r0.replaceAll(r3)     // Catch: java.lang.Exception -> L5d java.lang.Throwable -> L88
            java.lang.String r0 = r0.trim()     // Catch: java.lang.Exception -> L5d java.lang.Throwable -> L88
            long r3 = java.lang.Long.parseLong(r0)     // Catch: java.lang.Exception -> L5d java.lang.Throwable -> L88
            r5 = 1024(0x400, double:5.06E-321)
            long r3 = r3 * r5
            r1.close()     // Catch: java.io.IOException -> L44
            goto L48
        L44:
            r0 = move-exception
            r0.printStackTrace()
        L48:
            r2.close()     // Catch: java.io.IOException -> L4c
            goto L50
        L4c:
            r0 = move-exception
            r0.printStackTrace()
        L50:
            return r3
        L51:
            r1.close()     // Catch: java.io.IOException -> L55
            goto L59
        L55:
            r0 = move-exception
            r0.printStackTrace()
        L59:
            r2.close()     // Catch: java.io.IOException -> L81
            goto L85
        L5d:
            r0 = move-exception
            goto L6e
        L5f:
            r2 = move-exception
            goto L8c
        L61:
            r2 = move-exception
            r7 = r2
            r2 = r0
            r0 = r7
            goto L6e
        L66:
            r1 = move-exception
            r2 = r1
            r1 = r0
            goto L8c
        L6a:
            r1 = move-exception
            r2 = r0
            r0 = r1
            r1 = r2
        L6e:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L88
            if (r1 == 0) goto L7b
            r1.close()     // Catch: java.io.IOException -> L77
            goto L7b
        L77:
            r0 = move-exception
            r0.printStackTrace()
        L7b:
            if (r2 == 0) goto L85
            r2.close()     // Catch: java.io.IOException -> L81
            goto L85
        L81:
            r0 = move-exception
            r0.printStackTrace()
        L85:
            r0 = -1
            return r0
        L88:
            r0 = move-exception
            r7 = r2
            r2 = r0
            r0 = r7
        L8c:
            if (r1 == 0) goto L96
            r1.close()     // Catch: java.io.IOException -> L92
            goto L96
        L92:
            r1 = move-exception
            r1.printStackTrace()
        L96:
            if (r0 == 0) goto La0
            r0.close()     // Catch: java.io.IOException -> L9c
            goto La0
        L9c:
            r0 = move-exception
            r0.printStackTrace()
        La0:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.a.a.b.b():long");
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
            e.printStackTrace();
        }
        return null;
    }

    public static Account a(Context context) {
        String string;
        Account account = f1032a;
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
                f1032a = account2;
                break;
            }
            i++;
        }
        return f1032a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.io.IOException] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v8 */
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
                    inputStream = e;
                }
            } catch (IOException e2) {
                e = e2;
            }
        } catch (IOException e3) {
            e = e3;
            e.printStackTrace();
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
            e.printStackTrace();
            str = "N/A";
            if (e != 0) {
                e.close();
                e = e;
            }
            return str.trim();
        } catch (Throwable th2) {
            th = th2;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            throw th;
        }
        return str.trim();
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x007f A[Catch: IOException -> 0x007b, TRY_LEAVE, TryCatch #8 {IOException -> 0x007b, blocks: (B:48:0x0077, B:52:0x007f), top: B:64:0x0077 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0077 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a() throws java.lang.Throwable {
        /*
            java.lang.String r0 = ":"
            r1 = 0
            java.io.FileReader r2 = new java.io.FileReader     // Catch: java.lang.Throwable -> L58 java.io.IOException -> L5b
            java.lang.String r3 = "/proc/cpuinfo"
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L58 java.io.IOException -> L5b
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L53 java.io.IOException -> L55
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L53 java.io.IOException -> L55
        Lf:
            java.lang.String r4 = r3.readLine()     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            if (r4 == 0) goto L43
            boolean r5 = r4.contains(r0)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            if (r5 == 0) goto Lf
            java.lang.String[] r4 = r4.split(r0)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            r5 = 0
            r6 = r4[r5]     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            java.lang.String r7 = "Hardware"
            boolean r6 = r6.contains(r7)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            if (r6 != 0) goto L34
            r5 = r4[r5]     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            java.lang.String r6 = "model name"
            boolean r5 = r5.contains(r6)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            if (r5 == 0) goto Lf
        L34:
            r0 = 1
            r0 = r4[r0]     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            r2.close()     // Catch: java.io.IOException -> L3e
            r3.close()     // Catch: java.io.IOException -> L3e
            goto L42
        L3e:
            r1 = move-exception
            r1.printStackTrace()
        L42:
            return r0
        L43:
            r2.close()     // Catch: java.io.IOException -> L4a
            r3.close()     // Catch: java.io.IOException -> L4a
            goto L72
        L4a:
            r0 = move-exception
            r0.printStackTrace()
            goto L72
        L4f:
            r0 = move-exception
            goto L74
        L51:
            r0 = move-exception
            goto L5e
        L53:
            r0 = move-exception
            goto L75
        L55:
            r0 = move-exception
            r3 = r1
            goto L5e
        L58:
            r0 = move-exception
            r2 = r1
            goto L75
        L5b:
            r0 = move-exception
            r2 = r1
            r3 = r2
        L5e:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L73
            if (r2 == 0) goto L69
            r2.close()     // Catch: java.io.IOException -> L67
            goto L69
        L67:
            r0 = move-exception
            goto L6f
        L69:
            if (r3 == 0) goto L72
            r3.close()     // Catch: java.io.IOException -> L67
            goto L72
        L6f:
            r0.printStackTrace()
        L72:
            return r1
        L73:
            r0 = move-exception
        L74:
            r1 = r3
        L75:
            if (r2 == 0) goto L7d
            r2.close()     // Catch: java.io.IOException -> L7b
            goto L7d
        L7b:
            r1 = move-exception
            goto L83
        L7d:
            if (r1 == 0) goto L86
            r1.close()     // Catch: java.io.IOException -> L7b
            goto L86
        L83:
            r1.printStackTrace()
        L86:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.a.a.b.a():java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x003d, code lost:
    
        r1 = new java.lang.StringBuilder();
        r3 = r2.length;
        r5 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0046, code lost:
    
        if (r5 >= r3) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x004c, code lost:
    
        r1.append(java.lang.String.format("%02X:", java.lang.Byte.valueOf(r2[r5])));
        r5 = r5 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0062, code lost:
    
        if (r1.length() <= 0) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0064, code lost:
    
        r1.deleteCharAt(r1.length() - 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0070, code lost:
    
        r0 = r1.toString();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String d(android.content.Context r9) throws java.net.SocketException {
        /*
            b.b.a.g r0 = com.apm.applog.a.c()
            if (r0 == 0) goto Lf
            b.b.a.g r9 = com.apm.applog.a.c()
            java.lang.String r9 = r9.b()
            return r9
        Lf:
            r0 = 0
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 23
            if (r1 < r2) goto L76
            java.util.Enumeration r1 = java.net.NetworkInterface.getNetworkInterfaces()     // Catch: java.net.SocketException -> L72
        L1a:
            if (r1 == 0) goto L76
            boolean r2 = r1.hasMoreElements()     // Catch: java.net.SocketException -> L72
            if (r2 == 0) goto L76
            java.lang.Object r2 = r1.nextElement()     // Catch: java.net.SocketException -> L72
            java.net.NetworkInterface r2 = (java.net.NetworkInterface) r2     // Catch: java.net.SocketException -> L72
            java.lang.String r3 = "wlan0"
            java.lang.String r4 = r2.getName()     // Catch: java.net.SocketException -> L72
            boolean r3 = r3.equals(r4)     // Catch: java.net.SocketException -> L72
            if (r3 == 0) goto L1a
            byte[] r2 = r2.getHardwareAddress()     // Catch: java.net.SocketException -> L72
            if (r2 == 0) goto L1a
            int r3 = r2.length     // Catch: java.net.SocketException -> L72
            if (r3 <= 0) goto L1a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.net.SocketException -> L72
            r1.<init>()     // Catch: java.net.SocketException -> L72
            int r3 = r2.length     // Catch: java.net.SocketException -> L72
            r4 = 0
            r5 = 0
        L45:
            r6 = 1
            if (r5 >= r3) goto L5e
            r7 = r2[r5]     // Catch: java.net.SocketException -> L72
            java.lang.String r8 = "%02X:"
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: java.net.SocketException -> L72
            java.lang.Byte r7 = java.lang.Byte.valueOf(r7)     // Catch: java.net.SocketException -> L72
            r6[r4] = r7     // Catch: java.net.SocketException -> L72
            java.lang.String r6 = java.lang.String.format(r8, r6)     // Catch: java.net.SocketException -> L72
            r1.append(r6)     // Catch: java.net.SocketException -> L72
            int r5 = r5 + 1
            goto L45
        L5e:
            int r2 = r1.length()     // Catch: java.net.SocketException -> L72
            if (r2 <= 0) goto L6c
            int r2 = r1.length()     // Catch: java.net.SocketException -> L72
            int r2 = r2 - r6
            r1.deleteCharAt(r2)     // Catch: java.net.SocketException -> L72
        L6c:
            java.lang.String r1 = r1.toString()     // Catch: java.net.SocketException -> L72
            r0 = r1
            goto L76
        L72:
            r1 = move-exception
            b.b.a.k.v.a(r1)
        L76:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L93
            java.lang.String r1 = "wifi"
            java.lang.Object r9 = r9.getSystemService(r1)     // Catch: java.lang.SecurityException -> L8f
            android.net.wifi.WifiManager r9 = (android.net.wifi.WifiManager) r9     // Catch: java.lang.SecurityException -> L8f
            android.net.wifi.WifiInfo r9 = r9.getConnectionInfo()     // Catch: java.lang.SecurityException -> L8f
            if (r9 == 0) goto L93
            java.lang.String r9 = r9.getMacAddress()     // Catch: java.lang.SecurityException -> L8f
            goto L94
        L8f:
            r9 = move-exception
            b.b.a.k.v.a(r9)
        L93:
            r9 = r0
        L94:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.a.a.b.d(android.content.Context):java.lang.String");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.String[]] */
    /* JADX WARN: Type inference failed for: r1v3, types: [int] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.lang.String[]] */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8 */
    @SuppressLint({"HardwareIds"})
    public static String[] c(Context context) {
        ?? r1;
        int i;
        List<SubscriptionInfo> activeSubscriptionInfoList;
        ?? r0 = 0;
        String[] strArr = null;
        if (context == null) {
            return null;
        }
        try {
            r1 = Build.VERSION.SDK_INT;
            i = 0;
            try {
            } catch (Throwable th) {
                th = th;
                v.a("", th);
                return r1;
            }
        } catch (Throwable th2) {
            th = th2;
            r1 = r0;
        }
        if (r1 >= 22) {
            try {
                activeSubscriptionInfoList = SubscriptionManager.from(context).getActiveSubscriptionInfoList();
            } catch (SecurityException e) {
                e = e;
            }
            if (activeSubscriptionInfoList != null && !activeSubscriptionInfoList.isEmpty()) {
                String[] strArr2 = new String[activeSubscriptionInfoList.size()];
                while (true) {
                    try {
                        int size = activeSubscriptionInfoList.size();
                        r0 = size;
                        r1 = strArr2;
                        if (i < size) {
                            strArr2[i] = activeSubscriptionInfoList.get(i).getIccId();
                            i++;
                        }
                    } catch (SecurityException e2) {
                        e = e2;
                        strArr = strArr2;
                        e.printStackTrace();
                        r1 = strArr;
                        r0 = strArr;
                        return r1;
                    }
                }
                return r1;
            }
            return null;
        }
        r1 = new String[1];
        String str = "phone";
        try {
            r1[0] = ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
            r0 = str;
            r1 = r1;
        } catch (SecurityException e3) {
            e3.printStackTrace();
            r0 = str;
            r1 = r1;
        }
        return r1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.io.IOException] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v8 */
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
                    inputStream = e;
                }
            } catch (IOException e2) {
                e = e2;
            }
        } catch (IOException e3) {
            e = e3;
            e.printStackTrace();
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
            e.printStackTrace();
            str = "N/A";
            if (e != 0) {
                e.close();
                e = e;
            }
            return str.trim();
        } catch (Throwable th2) {
            th = th2;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            throw th;
        }
        return str.trim();
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

    public static void a(JSONObject jSONObject, String str, String str2) throws JSONException {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        jSONObject.put(str, str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x0073 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0069 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:78:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String b(java.lang.String r4) throws java.lang.Throwable {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r4)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            boolean r1 = r0.exists()
            if (r1 == 0) goto L7c
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L49 java.io.IOException -> L4c
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L49 java.io.IOException -> L4c
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L45 java.io.IOException -> L47
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L45 java.io.IOException -> L47
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L45 java.io.IOException -> L47
            r0.<init>(r3)     // Catch: java.lang.Throwable -> L45 java.io.IOException -> L47
        L20:
            java.lang.String r1 = r0.readLine()     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L42
            if (r1 == 0) goto L2a
            r4.append(r1)     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L42
            goto L20
        L2a:
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L42
            r0.close()     // Catch: java.io.IOException -> L32
            goto L36
        L32:
            r0 = move-exception
            r0.printStackTrace()
        L36:
            r2.close()     // Catch: java.io.IOException -> L3a
            goto L3e
        L3a:
            r0 = move-exception
            r0.printStackTrace()
        L3e:
            return r4
        L3f:
            r4 = move-exception
            r1 = r0
            goto L67
        L42:
            r4 = move-exception
            r1 = r0
            goto L4e
        L45:
            r4 = move-exception
            goto L67
        L47:
            r4 = move-exception
            goto L4e
        L49:
            r4 = move-exception
            r2 = r1
            goto L67
        L4c:
            r4 = move-exception
            r2 = r1
        L4e:
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L66
            if (r1 == 0) goto L5b
            r1.close()     // Catch: java.io.IOException -> L57
            goto L5b
        L57:
            r4 = move-exception
            r4.printStackTrace()
        L5b:
            if (r2 == 0) goto L7c
            r2.close()     // Catch: java.io.IOException -> L61
            goto L7c
        L61:
            r4 = move-exception
            r4.printStackTrace()
            goto L7c
        L66:
            r4 = move-exception
        L67:
            if (r1 == 0) goto L71
            r1.close()     // Catch: java.io.IOException -> L6d
            goto L71
        L6d:
            r0 = move-exception
            r0.printStackTrace()
        L71:
            if (r2 == 0) goto L7b
            r2.close()     // Catch: java.io.IOException -> L77
            goto L7b
        L77:
            r0 = move-exception
            r0.printStackTrace()
        L7b:
            throw r4
        L7c:
            java.lang.String r4 = ""
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.a.a.b.b(java.lang.String):java.lang.String");
    }
}
