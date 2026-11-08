package com.mbridge.msdk.optimize;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.kwad.v8.Platform;
import com.mbridge.msdk.optimize.a.a.c;
import com.mbridge.msdk.optimize.a.a.d;
import com.mbridge.msdk.optimize.a.a.e;
import com.mbridge.msdk.optimize.a.a.f;
import com.mbridge.msdk.optimize.a.a.g;
import com.mbridge.msdk.optimize.a.a.h;
import com.mbridge.msdk.optimize.a.a.i;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class SensitiveDataUtil {
    private static String androidID;
    private static String deviceid;
    private static String imsi;
    private static String mSelfId;
    private static String macAddress;
    private static String oaid;

    /* JADX WARN: Removed duplicated region for block: B:21:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void writeFile(android.content.Context r1, java.io.File r2, java.lang.String r3) throws java.lang.Throwable {
        /*
            java.io.File r1 = r2.getParentFile()
            boolean r1 = r1.exists()
            if (r1 != 0) goto L11
            java.io.File r1 = r2.getParentFile()
            r1.mkdirs()
        L11:
            r2.createNewFile()
            r1 = 0
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L24 java.lang.Throwable -> L28
            r0.<init>(r2)     // Catch: java.lang.Throwable -> L24 java.lang.Throwable -> L28
            byte[] r1 = r3.getBytes()     // Catch: java.lang.Throwable -> L22 java.lang.Throwable -> L34
            r0.write(r1)     // Catch: java.lang.Throwable -> L22 java.lang.Throwable -> L34
            goto L30
        L22:
            r1 = move-exception
            goto L2b
        L24:
            r2 = move-exception
            r0 = r1
            r1 = r2
            goto L35
        L28:
            r2 = move-exception
            r0 = r1
            r1 = r2
        L2b:
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L34
            if (r0 == 0) goto L33
        L30:
            r0.close()
        L33:
            return
        L34:
            r1 = move-exception
        L35:
            if (r0 == 0) goto L3a
            r0.close()
        L3a:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.optimize.SensitiveDataUtil.writeFile(android.content.Context, java.io.File, java.lang.String):void");
    }

    private static String writeInstallationFile(Context context, File file) throws Throwable {
        UUID uuidRandomUUID = UUID.randomUUID();
        writeFile(context, file, uuidRandomUUID.toString());
        return uuidRandomUUID.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x002e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String readInstallationFile(java.io.File r4) throws java.lang.Throwable {
        /*
            r0 = 0
            java.io.RandomAccessFile r1 = new java.io.RandomAccessFile     // Catch: java.lang.Throwable -> L1d java.lang.Throwable -> L20
            java.lang.String r2 = "r"
            r1.<init>(r4, r2)     // Catch: java.lang.Throwable -> L1d java.lang.Throwable -> L20
            long r2 = r1.length()     // Catch: java.lang.Throwable -> L1b java.lang.Throwable -> L2b
            int r4 = (int) r2     // Catch: java.lang.Throwable -> L1b java.lang.Throwable -> L2b
            byte[] r4 = new byte[r4]     // Catch: java.lang.Throwable -> L1b java.lang.Throwable -> L2b
            r1.readFully(r4)     // Catch: java.lang.Throwable -> L1b java.lang.Throwable -> L2b
            java.lang.String r2 = new java.lang.String     // Catch: java.lang.Throwable -> L1b java.lang.Throwable -> L2b
            r2.<init>(r4)     // Catch: java.lang.Throwable -> L1b java.lang.Throwable -> L2b
            r1.close()
            return r2
        L1b:
            r4 = move-exception
            goto L22
        L1d:
            r4 = move-exception
            r1 = r0
            goto L2c
        L20:
            r4 = move-exception
            r1 = r0
        L22:
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L2b
            if (r1 == 0) goto L2a
            r1.close()
        L2a:
            return r0
        L2b:
            r4 = move-exception
        L2c:
            if (r1 == 0) goto L31
            r1.close()
        L31:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.optimize.SensitiveDataUtil.readInstallationFile(java.io.File):java.lang.String");
    }

    public static String getSelfId(Context context) {
        if (mSelfId == null) {
            File file = new File(Environment.getExternalStorageDirectory().toString(), "/.a/track_id.bin");
            try {
                if (!file.exists()) {
                    mSelfId = writeInstallationFile(context, file);
                } else {
                    mSelfId = readInstallationFile(file);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mSelfId == null ? "" : mSelfId;
    }

    public static String getIMEI(Context context) {
        try {
            if (TextUtils.isEmpty(deviceid)) {
                deviceid = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
                if (deviceid == null) {
                    deviceid = "";
                }
            }
        } catch (Throwable unused) {
            deviceid = "";
        }
        return deviceid;
    }

    public static String getMacAddress(Context context) {
        String macAddress2;
        try {
            if (TextUtils.isEmpty(macAddress)) {
                if (Build.VERSION.SDK_INT >= 23) {
                    macAddress2 = getMac();
                } else {
                    macAddress2 = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
                }
                if (macAddress2 == null) {
                    return "";
                }
                macAddress = macAddress2.replaceAll(":", "").toLowerCase();
                return macAddress;
            }
            return macAddress;
        } catch (Exception unused) {
            return "";
        }
    }

    private static String getMac() throws SocketException {
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.getName().equalsIgnoreCase("wlan0")) {
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return "";
                    }
                    StringBuilder sb = new StringBuilder();
                    for (byte b : hardwareAddress) {
                        sb.append(String.format("%02X:", Byte.valueOf(b)));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    return sb.toString();
                }
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getAndroidID(Context context) {
        try {
            if (TextUtils.isEmpty(androidID)) {
                androidID = Settings.Secure.getString(context.getContentResolver(), "android_id");
                if (androidID == null) {
                    androidID = "";
                }
                return androidID;
            }
        } catch (Exception unused) {
            androidID = "";
        }
        return androidID;
    }

    public static String getImsi(Context context) {
        try {
            if (TextUtils.isEmpty(imsi)) {
                imsi = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
                if (imsi == null) {
                    imsi = "";
                }
            }
        } catch (Exception unused) {
            imsi = "";
        }
        return imsi;
    }

    public static String getOaid(Context context) {
        if (!TextUtils.isEmpty(oaid)) {
            return oaid;
        }
        try {
            oaid = new a(context).b;
            if (!TextUtils.isEmpty(oaid)) {
                return oaid;
            }
            String str = Build.MANUFACTURER;
            if (isFreeMeOS()) {
                str = "FERRMEOS";
            } else if (isSSUIOS()) {
                str = "SSUI";
            }
            if (TextUtils.isEmpty(str)) {
                return "";
            }
            String upperCase = str.toUpperCase();
            if (Arrays.asList("ASUS", "HUAWEI", "OPPO", "ONEPLUS", "ZTE", "FERRMEOS", "SSUI", "SAMSUNG", "MEIZU", "MOTOLORA", "LENOVO").contains(upperCase)) {
                getFormNewThread(context, upperCase);
                return "";
            }
            if ("VIVO".equals(upperCase)) {
                oaid = new h(context).a();
                return "";
            }
            if (!"NUBIA".equals(upperCase)) {
                return "";
            }
            oaid = new d(context).a();
            return "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public static boolean isFreeMeOS() throws ClassNotFoundException {
        String property = getProperty("ro.build.freeme.label");
        return !TextUtils.isEmpty(property) && property.equalsIgnoreCase("FREEMEOS");
    }

    public static boolean isSSUIOS() throws ClassNotFoundException {
        String property = getProperty("ro.ssui.product");
        return (TextUtils.isEmpty(property) || property.equalsIgnoreCase(Platform.UNKNOWN)) ? false : true;
    }

    private static String getProperty(String str) throws ClassNotFoundException {
        if (str == null) {
            return null;
        }
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class, String.class).invoke(cls, str, Platform.UNKNOWN);
        } catch (Exception unused) {
            return null;
        }
    }

    static final class a {
        private static Object e;
        private static Class<?> f;
        private static Method g;
        private static Method h;
        private static Method i;
        private static Method j;
        final String a;
        final String b;
        final String c;
        final String d;

        static {
            try {
                f = Class.forName("com.android.id.impl.IdProviderImpl");
                e = f.newInstance();
                g = f.getMethod("getUDID", Context.class);
                h = f.getMethod("getOAID", Context.class);
                i = f.getMethod("getVAID", Context.class);
                j = f.getMethod("getAAID", Context.class);
            } catch (Throwable unused) {
            }
        }

        a(Context context) {
            this.a = a(context, g);
            this.b = a(context, h);
            this.c = a(context, i);
            this.d = a(context, j);
        }

        private static String a(Context context, Method method) {
            if (e == null || method == null) {
                return null;
            }
            try {
                Object objInvoke = method.invoke(e, context);
                if (objInvoke != null) {
                    return (String) objInvoke;
                }
                return null;
            } catch (Throwable unused) {
                return null;
            }
        }
    }

    private static void getFormNewThread(final Context context, final String str) {
        new Thread(new Runnable() { // from class: com.mbridge.msdk.optimize.SensitiveDataUtil.1
            @Override // java.lang.Runnable
            public final void run() throws PackageManager.NameNotFoundException, NoSuchAlgorithmException {
                com.mbridge.msdk.optimize.a.b bVar = new com.mbridge.msdk.optimize.a.b() { // from class: com.mbridge.msdk.optimize.SensitiveDataUtil.1.1
                    @Override // com.mbridge.msdk.optimize.a.b
                    public final void a(String str2) {
                    }

                    @Override // com.mbridge.msdk.optimize.a.b
                    public final void a(String str2, boolean z) {
                        String unused = SensitiveDataUtil.oaid = str2;
                    }
                };
                if ("ASUS".equals(str)) {
                    new com.mbridge.msdk.optimize.a.a.a(context).a(bVar);
                    return;
                }
                if ("OPPO".equals(str)) {
                    new f(context).a(bVar);
                    return;
                }
                if ("ONEPLUS".equals(str)) {
                    new e(context).a(bVar);
                    return;
                }
                if ("ZTE".equals(str) || "FERRMEOS".equals(str) || "SSUI".equals(str)) {
                    new i(context).a(bVar);
                    return;
                }
                if ("HUAWEI".equals(str)) {
                    new com.mbridge.msdk.optimize.a.a(context).a(bVar);
                    return;
                }
                if ("SAMSUNG".equals(str)) {
                    new g(context).a(bVar);
                    return;
                }
                if ("LENOVO".equals(str) || "MOTOLORA".equals(str)) {
                    new com.mbridge.msdk.optimize.a.a.b(context).a(bVar);
                } else if ("MEIZU".equals(str)) {
                    new c(context).a(bVar);
                }
            }
        }).start();
    }
}
