package com.bytedance.common.utility;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Point;
import android.net.Proxy;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;
import anet.channel.strategy.dispatch.DispatchConstants;
import com.bytedance.common.util.HoneyCombMR2V13Compat;
import com.bytedance.common.utility.device.SystemPropertiesUtils;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import com.ss.union.game.sdk.core.base.R;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class DeviceUtils {
    private static final int DEFAULT_CACHE_SIZE = 1024;
    private static final String IMMERSION_EMUI_NAVIGATION_BAR_HIDE_SHOW = "navigationbar_is_min";
    private static final String IMMERSION_MIUI_NAVIGATION_BAR_HIDE_SHOW = "force_fsg_nav_bar";
    static final String IMMERSION_NAVIGATION_BAR_HEIGHT = "navigation_bar_height";
    private static final String KEY_SYSTEM_PROPERTIES_CACHE = "system_properties_cache";
    private static final String KEY_VERSION_EMUI = "ro.build.version.emui";
    private static final String KEY_VERSION_GIONEE = "ro.gn.sv.version";
    private static final String KEY_VERSION_LENOVO = "ro.lenovo.lvp.version";
    private static final String KEY_VERSION_MIUI = "ro.miui.ui.version.name";
    private static final String KEY_VERSION_OPPO = "ro.build.version.opporom";
    private static final String KEY_VERSION_SMARTISAN = "ro.smartisan.version";
    private static final String KEY_VERSION_VIVO = "ro.vivo.os.version";
    public static final String ROM_EMUI = "EMUI";
    public static final String ROM_FLYME = "FLYME";
    public static final String ROM_GIONEE = "QIONEE";
    public static final String ROM_LENOVO = "LENOVO";
    public static final String ROM_MIUI = "MIUI";
    public static final String ROM_OPPO = "OPPO";
    public static final String ROM_QIKU = "QIKU";
    public static final String ROM_SAMSUNG = "samsung";
    public static final String ROM_SMARTISAN = "SMARTISAN";
    public static final String ROM_VIVO = "VIVO";
    public static final String ROM_ZTE = "zte";
    private static final String TAG = "DeviceUtils";
    public static boolean deviceDataHasInit = false;
    public static int displayMetricsHeightPixels = 0;
    public static int displayMetricsWidthPixels = 0;
    private static Boolean isCoolpad = null;
    private static Boolean isEmui = null;
    private static int mIsFoldableScreen = -1;
    private static int sEmuiLevel = -1;
    private static boolean sIsMiui = false;
    private static boolean sIsMiuiInited = false;
    private static String sMiuiVersion;
    private static String sName;
    private static int sStatusBarHeight;
    private static String sVersion;
    private static final Map<String, String> SYSTEM_PROPERTIES_CACHE = new HashMap();
    private static volatile Properties properties = null;

    private DeviceUtils() {
    }

    public static boolean isEmui() {
        if (isEmui != null) {
            return isEmui.booleanValue();
        }
        boolean z = false;
        try {
            Method declaredMethod = Build.class.getDeclaredMethod("getString", String.class);
            declaredMethod.setAccessible(true);
            Object objInvoke = declaredMethod.invoke(null, KEY_VERSION_EMUI);
            if ((objInvoke instanceof String) && !StringUtils.isEmpty((String) objInvoke)) {
                if (!"unknown".equals((String) objInvoke)) {
                    z = true;
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        isEmui = Boolean.valueOf(z);
        return z;
    }

    public static boolean isCoolpad() {
        if (isCoolpad != null) {
            return isCoolpad.booleanValue();
        }
        String str = Build.BRAND;
        if (!TextUtils.isEmpty(str)) {
            isCoolpad = Boolean.valueOf(str.toLowerCase(Locale.getDefault()).contains("coolpad"));
        } else {
            isCoolpad = false;
        }
        return isCoolpad.booleanValue();
    }

    public static boolean isWifiProxy(Context context) throws NumberFormatException {
        int port;
        String property;
        if (Build.VERSION.SDK_INT >= 14) {
            property = System.getProperty("http.proxyHost");
            String property2 = System.getProperty("http.proxyPort");
            if (property2 == null) {
                property2 = "-1";
            }
            port = Integer.parseInt(property2);
        } else {
            String host = Proxy.getHost(context);
            port = Proxy.getPort(context);
            property = host;
        }
        return (TextUtils.isEmpty(property) || port == -1) ? false : true;
    }

    public static boolean isInstallXposed() throws Exception {
        try {
            throw new Exception("hook");
        } catch (Exception e) {
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                if (stackTraceElement.getClassName().contains("de.robv.android.xposed")) {
                    return true;
                }
            }
            return false;
        }
    }

    public static void setNoScreenCap(Activity activity) {
        activity.getWindow().setFlags(8192, 8192);
    }

    private static boolean checkHuaweiFoldableList(String str) {
        return TextUtils.equals(str, "HWTAH") || TextUtils.equals(str, "unknownRLI") || TextUtils.equals(str, "unknownRHA") || TextUtils.equals(str, "HWTAH-C");
    }

    private static boolean isHwFoldableDevice(Context context) {
        if (context == null) {
            return false;
        }
        try {
            if (context.getPackageManager() != null) {
                return context.getPackageManager().hasSystemFeature("com.huawei.hardware.sensor.posture");
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    private static boolean checkSamsungFoldableList(String str) {
        return TextUtils.equals(str, "winner") || TextUtils.equals(str, "zodiac");
    }

    public static boolean isFoldableScreen() {
        if (mIsFoldableScreen > -1) {
            return mIsFoldableScreen > 0;
        }
        mIsFoldableScreen = 0;
        if (isSamsung() && checkSamsungFoldableList(Build.DEVICE)) {
            mIsFoldableScreen = 1;
        } else if (isHuawei() && checkHuaweiFoldableList(Build.DEVICE)) {
            mIsFoldableScreen = 1;
        }
        return mIsFoldableScreen > 0;
    }

    public static boolean isFoldableScreenV2(Context context) {
        if (mIsFoldableScreen > -1) {
            return mIsFoldableScreen > 0;
        }
        mIsFoldableScreen = 0;
        if (isSamsung() && checkSamsungFoldableList(Build.DEVICE)) {
            mIsFoldableScreen = 1;
        } else if (isHuawei() && (checkHuaweiFoldableList(Build.DEVICE) || isHwFoldableDevice(context))) {
            mIsFoldableScreen = 1;
        }
        return mIsFoldableScreen > 0;
    }

    public static boolean isMiui() {
        if (!sIsMiuiInited) {
            try {
                if (Class.forName("miui.os.Build") != null) {
                    sIsMiui = true;
                }
            } catch (Exception unused) {
            }
            sIsMiuiInited = true;
        }
        return sIsMiui;
    }

    public static void setMiuiStatusBarDarkMode(boolean z, Window window) {
        try {
            Class<?> cls = window.getClass();
            Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
            Method method = cls.getMethod("setExtraFlags", Integer.TYPE, Integer.TYPE);
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(z ? i : 0);
            objArr[1] = Integer.valueOf(i);
            method.invoke(window, objArr);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static boolean isMiuiV6() {
        initMiuiVersion();
        return "V6".equals(sMiuiVersion);
    }

    public static boolean isMiuiV7() {
        initMiuiVersion();
        return "V7".equals(sMiuiVersion);
    }

    public static boolean isMiuiV8() {
        initMiuiVersion();
        return "V8".equals(sMiuiVersion);
    }

    public static boolean isMiuiV9() {
        initMiuiVersion();
        return "V9".equals(sMiuiVersion);
    }

    private static void initMiuiVersion() {
        if (sMiuiVersion == null) {
            try {
                sMiuiVersion = getProperty(KEY_VERSION_MIUI);
            } catch (IOException e) {
                e.printStackTrace();
            }
            sMiuiVersion = sMiuiVersion == null ? "" : sMiuiVersion;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0058  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int getEmuiLevel() throws java.lang.Throwable {
        /*
            int r0 = com.bytedance.common.utility.DeviceUtils.sEmuiLevel
            r1 = -1
            if (r0 <= r1) goto L8
            int r0 = com.bytedance.common.utility.DeviceUtils.sEmuiLevel
            return r0
        L8:
            r0 = 0
            com.bytedance.common.utility.DeviceUtils.sEmuiLevel = r0
            java.util.Properties r0 = new java.util.Properties
            r0.<init>()
            java.io.File r1 = new java.io.File
            java.io.File r2 = android.os.Environment.getRootDirectory()
            java.lang.String r3 = "build.prop"
            r1.<init>(r2, r3)
            r2 = 0
            boolean r3 = r1.exists()
            if (r3 == 0) goto L50
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
            r3.<init>(r1)     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L36
            r0.load(r3)     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L31
            r3.close()     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L31
            goto L50
        L2e:
            r0 = move-exception
            r2 = r3
            goto L45
        L31:
            r1 = move-exception
            r2 = r3
            goto L37
        L34:
            r0 = move-exception
            goto L45
        L36:
            r1 = move-exception
        L37:
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L34
            if (r2 == 0) goto L50
            r2.close()     // Catch: java.lang.Exception -> L40
            goto L50
        L40:
            r1 = move-exception
            r1.printStackTrace()
            goto L50
        L45:
            if (r2 == 0) goto L4f
            r2.close()     // Catch: java.lang.Exception -> L4b
            goto L4f
        L4b:
            r1 = move-exception
            r1.printStackTrace()
        L4f:
            throw r0
        L50:
            java.lang.String r1 = "ro.build.hw_emui_api_level"
            boolean r1 = r0.containsKey(r1)
            if (r1 == 0) goto L69
            java.lang.String r1 = "ro.build.hw_emui_api_level"
            java.lang.String r0 = r0.getProperty(r1)
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.Exception -> L65
            com.bytedance.common.utility.DeviceUtils.sEmuiLevel = r0     // Catch: java.lang.Exception -> L65
            goto L69
        L65:
            r0 = move-exception
            r0.printStackTrace()
        L69:
            int r0 = com.bytedance.common.utility.DeviceUtils.sEmuiLevel
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.common.utility.DeviceUtils.getEmuiLevel():int");
    }

    public static boolean isHuawei() {
        return Build.MANUFACTURER != null && Build.MANUFACTURER.toUpperCase(Locale.getDefault()).contains("HUAWEI");
    }

    public static boolean isSumsungV4_4_4() {
        if (!isSamsung()) {
            return false;
        }
        if (Build.VERSION.RELEASE.startsWith("4.4.4")) {
            return true;
        }
        return Build.VERSION.RELEASE.startsWith("4.4.2") && Build.DEVICE.startsWith("klte");
    }

    public static boolean isSumsungV5() {
        return isSamsung() && Build.VERSION.SDK_INT >= 21;
    }

    public static boolean isSumsungCorePrime() {
        return isSamsung() && Build.DISPLAY.contains("G3608ZMU1AOA4");
    }

    public static boolean isFlyme() {
        return Build.DISPLAY.startsWith("Flyme");
    }

    public static boolean isFlyme2() {
        return Build.DISPLAY.startsWith("Flyme 2");
    }

    public static boolean isFlyme4() {
        return Build.DISPLAY.startsWith("Flyme OS 4");
    }

    public static boolean isFlyme5() {
        return Build.DISPLAY.startsWith("Flyme 5");
    }

    public static boolean isFlyme6() {
        return Build.DISPLAY.startsWith("Flyme 6");
    }

    public static boolean isFlyme7() {
        return Build.DISPLAY.startsWith("Flyme 7");
    }

    public static boolean isOnePlusLOLLIPOP() {
        return Build.BRAND.equals("ONEPLUS") && Build.VERSION.SDK_INT >= 21;
    }

    public static boolean isSamsung() {
        return ROM_SAMSUNG.equalsIgnoreCase(Build.BRAND) || ROM_SAMSUNG.equalsIgnoreCase(Build.MANUFACTURER);
    }

    public static boolean isLG() {
        return ("lge".equalsIgnoreCase(Build.BRAND) || "lge".equalsIgnoreCase(Build.MANUFACTURER)) && Build.MODEL != null && Build.MODEL.toLowerCase(Locale.getDefault()).contains("lg");
    }

    public static boolean isMeizuMx3() {
        if (isMeizu()) {
            return "mx3".equalsIgnoreCase(Build.DEVICE);
        }
        return false;
    }

    public static boolean isHtcOs() {
        return Build.BRAND != null && Build.BRAND.toLowerCase(Locale.getDefault()).contains("htc") && Build.MANUFACTURER != null && Build.MANUFACTURER.toLowerCase(Locale.getDefault()).contains("htc") && Build.MODEL != null && Build.MODEL.toLowerCase(Locale.getDefault()).contains("htc");
    }

    public static boolean isMeizu() {
        String str = Build.BRAND;
        return str != null && str.toLowerCase(Locale.ENGLISH).indexOf("meizu") > -1;
    }

    public static boolean isVivo() {
        String str = Build.BRAND;
        if (!TextUtils.isEmpty(str) && str.toLowerCase(Locale.getDefault()).contains("vivo")) {
            return true;
        }
        String str2 = Build.MODEL;
        if (!TextUtils.isEmpty(str2) && str2.toLowerCase(Locale.getDefault()).contains("vivo")) {
            return true;
        }
        String str3 = Build.MANUFACTURER;
        return !TextUtils.isEmpty(str3) && str3.toLowerCase(Locale.getDefault()).contains("vivo");
    }

    public static boolean isOppo() {
        return check(ROM_OPPO);
    }

    public static boolean isQiku() {
        return check(ROM_QIKU) || check("360");
    }

    public static boolean isSmartisan() {
        return check(ROM_SMARTISAN);
    }

    public static String getName() throws Throwable {
        if (sName == null) {
            check("");
        }
        return sName;
    }

    public static String getVersion() throws Throwable {
        if (sVersion == null) {
            check("");
        }
        return sVersion;
    }

    public static boolean check(String str) throws Throwable {
        if (sName != null) {
            return sName.equals(str);
        }
        String prop = getProp(KEY_VERSION_MIUI);
        sVersion = prop;
        if (!TextUtils.isEmpty(prop)) {
            sName = ROM_MIUI;
        } else {
            String prop2 = getProp(KEY_VERSION_EMUI);
            sVersion = prop2;
            if (!TextUtils.isEmpty(prop2)) {
                sName = ROM_EMUI;
            } else {
                String prop3 = getProp(KEY_VERSION_OPPO);
                sVersion = prop3;
                if (!TextUtils.isEmpty(prop3)) {
                    sName = ROM_OPPO;
                } else {
                    String prop4 = getProp(KEY_VERSION_VIVO);
                    sVersion = prop4;
                    if (!TextUtils.isEmpty(prop4)) {
                        sName = ROM_VIVO;
                    } else {
                        String prop5 = getProp(KEY_VERSION_SMARTISAN);
                        sVersion = prop5;
                        if (!TextUtils.isEmpty(prop5)) {
                            sName = ROM_SMARTISAN;
                        } else {
                            String prop6 = getProp(KEY_VERSION_GIONEE);
                            sVersion = prop6;
                            if (!TextUtils.isEmpty(prop6)) {
                                sName = ROM_GIONEE;
                            } else {
                                String prop7 = getProp(KEY_VERSION_LENOVO);
                                sVersion = prop7;
                                if (!TextUtils.isEmpty(prop7)) {
                                    sName = ROM_LENOVO;
                                } else if (getManufacturer().toLowerCase(Locale.getDefault()).contains(ROM_SAMSUNG)) {
                                    sName = ROM_SAMSUNG;
                                } else if (getManufacturer().toLowerCase(Locale.getDefault()).contains(ROM_ZTE)) {
                                    sName = ROM_ZTE;
                                } else {
                                    sVersion = Build.DISPLAY;
                                    if (sVersion.toUpperCase(Locale.getDefault()).contains(ROM_FLYME)) {
                                        sName = ROM_FLYME;
                                    } else {
                                        sVersion = "unknown";
                                        sName = Build.MANUFACTURER.toUpperCase(Locale.getDefault());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return sName.equals(str);
    }

    public static String getProp(String str) throws Throwable {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop " + str).getInputStream(), "UTF-8"), 1024);
            try {
                String line = bufferedReader.readLine();
                bufferedReader.close();
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return line;
            } catch (IOException unused) {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                return null;
            } catch (Throwable th) {
                th = th;
                bufferedReader2 = bufferedReader;
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (IOException unused2) {
            bufferedReader = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static String getManufacturer() {
        return Build.MANUFACTURER == null ? "" : Build.MANUFACTURER.trim();
    }

    public static String getSystemPropertyInSp(Context context, String str) {
        String systemProperty;
        SharedPreferences sharedPreferences;
        String string;
        synchronized (SYSTEM_PROPERTIES_CACHE) {
            systemProperty = SYSTEM_PROPERTIES_CACHE.get(str);
            if (StringUtils.isEmpty(systemProperty)) {
                try {
                    sharedPreferences = context.getSharedPreferences(KEY_SYSTEM_PROPERTIES_CACHE, 0);
                    string = sharedPreferences.getString(str, null);
                } catch (Exception e) {
                    e = e;
                }
                try {
                    if (StringUtils.isEmpty(string)) {
                        systemProperty = getSystemProperty(str);
                        if (!StringUtils.isEmpty(systemProperty)) {
                            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                            editorEdit.putString(str, systemProperty);
                            editorEdit.apply();
                        }
                    } else {
                        systemProperty = string;
                    }
                } catch (Exception e2) {
                    e = e2;
                    systemProperty = string;
                    Logger.e(TAG, "getSystemProperties in sp error", e);
                    return systemProperty;
                }
            }
        }
        return systemProperty;
    }

    public static String getSystemProperty(String str) {
        return SystemPropertiesUtils.getSystemProperty(str);
    }

    public static double getEmuiVersion() {
        try {
            String systemProperty = getSystemProperty(KEY_VERSION_EMUI);
            return Double.parseDouble(systemProperty.substring(systemProperty.indexOf("_") + 1));
        } catch (Exception e) {
            e.printStackTrace();
            return 4.0d;
        }
    }

    public static boolean hasSmartBar() {
        if (!isMeizu()) {
            return false;
        }
        try {
            return ((Boolean) Class.forName("android.os.Build").getMethod("hasSmartBar", new Class[0]).invoke(null, new Object[0])).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return (Build.DEVICE.equals("mx") || Build.DEVICE.equals("m9")) ? false : true;
        }
    }

    @SuppressLint({"NewApi"})
    public static boolean hasVirtualButtons(Context context) {
        if (Build.VERSION.SDK_INT >= 14) {
            return !ViewConfiguration.get(context).hasPermanentMenuKey();
        }
        return false;
    }

    public static String getDeviceId(Context context) {
        String deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        return (TextUtils.isEmpty(deviceId) || "000000000000000".equals(deviceId)) ? ((WifiManager) context.getApplicationContext().getSystemService(OnekeyLoginConstants.NETWORK_TYPE_WIFI_STR)).getConnectionInfo().getMacAddress() : deviceId;
    }

    public static boolean isLenovo() {
        return "lenovo".equalsIgnoreCase(Build.BRAND) || "lenovo".equalsIgnoreCase(Build.MANUFACTURER) || "motorola".equalsIgnoreCase(Build.BRAND) || "motorola".equalsIgnoreCase(Build.MANUFACTURER);
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x004a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static long getTotalMemory() throws java.lang.Throwable {
        /*
            java.lang.String r0 = "/proc/meminfo"
            r1 = 0
            java.io.FileReader r2 = new java.io.FileReader     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L33
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L33
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L33
            r3 = 8192(0x2000, float:1.148E-41)
            r0.<init>(r2, r3)     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L33
            java.lang.String r1 = r0.readLine()     // Catch: java.lang.Exception -> L2c java.lang.Throwable -> L47
            java.lang.String r2 = "\\s+"
            java.lang.String[] r1 = r1.split(r2)     // Catch: java.lang.Exception -> L2c java.lang.Throwable -> L47
            r2 = 1
            r1 = r1[r2]     // Catch: java.lang.Exception -> L2c java.lang.Throwable -> L47
            int r1 = java.lang.Integer.parseInt(r1)     // Catch: java.lang.Exception -> L2c java.lang.Throwable -> L47
            int r1 = r1 * 1024
            long r1 = (long) r1
            r0.close()     // Catch: java.io.IOException -> L27
            goto L46
        L27:
            r0 = move-exception
            r0.printStackTrace()
            goto L46
        L2c:
            r1 = move-exception
            goto L37
        L2e:
            r0 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L48
        L33:
            r0 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
        L37:
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L47
            if (r0 == 0) goto L44
            r0.close()     // Catch: java.io.IOException -> L40
            goto L44
        L40:
            r0 = move-exception
            r0.printStackTrace()
        L44:
            r1 = 0
        L46:
            return r1
        L47:
            r1 = move-exception
        L48:
            if (r0 == 0) goto L52
            r0.close()     // Catch: java.io.IOException -> L4e
            goto L52
        L4e:
            r0 = move-exception
            r0.printStackTrace()
        L52:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.common.utility.DeviceUtils.getTotalMemory():long");
    }

    public static int getStatusBarHeight(Context context) {
        if (sStatusBarHeight > 0) {
            return sStatusBarHeight;
        }
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", DispatchConstants.ANDROID);
        int dimensionPixelSize = identifier > 0 ? context.getResources().getDimensionPixelSize(identifier) : 0;
        if (dimensionPixelSize == 0) {
            dimensionPixelSize = (int) UIUtils.dip2Px(context, 25.0f);
        }
        sStatusBarHeight = dimensionPixelSize;
        return dimensionPixelSize;
    }

    public static void initDeviceData(Context context) {
        if (context == null) {
            return;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager == null) {
            return;
        }
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int orientation = windowManager.getDefaultDisplay().getOrientation();
        if (orientation == 1 || orientation == 3) {
            displayMetricsWidthPixels = displayMetrics.heightPixels;
            displayMetricsHeightPixels = displayMetrics.widthPixels;
        } else {
            displayMetricsWidthPixels = displayMetrics.widthPixels;
            displayMetricsHeightPixels = displayMetrics.heightPixels;
        }
        deviceDataHasInit = true;
    }

    public static int getEquipmentWidth(Context context) {
        if (!deviceDataHasInit || isFoldableScreenV2(context)) {
            initDeviceData(context);
        }
        return displayMetricsWidthPixels;
    }

    public static int getEquipmentHeight(Context context) {
        if (!deviceDataHasInit || isFoldableScreenV2(context)) {
            initDeviceData(context);
        }
        return displayMetricsHeightPixels;
    }

    public static boolean isLargeScreenPad(Context context, int i, int i2) {
        if (isFoldableScreenV2(context)) {
            return false;
        }
        Resources resources = context.getResources();
        return i >= resources.getDimensionPixelSize(R.dimen.mohist_utility_large_pad_min_width) && i2 >= resources.getDimensionPixelSize(R.dimen.mohist_utility_large_pad_min_height);
    }

    public static boolean isLargeScreenPad(Display display, Context context) {
        Point point = new Point();
        HoneyCombMR2V13Compat.getDisplaySize(display, point);
        return isLargeScreenPad(context, point.x, point.y);
    }

    public static boolean isPad(Context context) {
        if (context == null || context.getResources() == null || context.getResources().getConfiguration() == null) {
            return false;
        }
        return "tablet".equals(getDeviceType(context));
    }

    public static boolean isHuawei_Os_3x() {
        if (!isHuawei()) {
            return false;
        }
        String systemProperty = getSystemProperty(KEY_VERSION_EMUI);
        return "EmotionUI 3".equals(systemProperty) || "EmotionUI_3.1".contains(systemProperty) || "EmotionUI_3.0".contains(systemProperty);
    }

    public static int getNavigationBarHeight(Context context) {
        if (Build.VERSION.SDK_INT < 14 || !hasNavBar((Activity) context)) {
            return 0;
        }
        return getInternalDimensionSize(context, IMMERSION_NAVIGATION_BAR_HEIGHT);
    }

    public static boolean hasNavBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= 17) {
            if (Settings.Global.getInt(activity.getContentResolver(), IMMERSION_MIUI_NAVIGATION_BAR_HIDE_SHOW, 0) != 0) {
                return false;
            }
            if (isEmui()) {
                if (isHuawei_Os_3x() || Build.VERSION.SDK_INT < 21) {
                    if (Settings.System.getInt(activity.getContentResolver(), IMMERSION_EMUI_NAVIGATION_BAR_HIDE_SHOW, 0) != 0) {
                        return false;
                    }
                } else if (Settings.Global.getInt(activity.getContentResolver(), IMMERSION_EMUI_NAVIGATION_BAR_HIDE_SHOW, 0) != 0) {
                    return false;
                }
            }
        }
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealMetrics(displayMetrics);
        }
        int i = displayMetrics.heightPixels;
        int i2 = displayMetrics.widthPixels;
        DisplayMetrics displayMetrics2 = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics2);
        return i2 - displayMetrics2.widthPixels > 0 || i - displayMetrics2.heightPixels > 0;
    }

    public static int getInternalDimensionSize(Context context, String str) throws Resources.NotFoundException {
        try {
            int identifier = Resources.getSystem().getIdentifier(str, "dimen", DispatchConstants.ANDROID);
            if (identifier <= 0) {
                return 0;
            }
            int dimensionPixelSize = context.getResources().getDimensionPixelSize(identifier);
            int dimensionPixelSize2 = Resources.getSystem().getDimensionPixelSize(identifier);
            if (dimensionPixelSize2 >= dimensionPixelSize) {
                return dimensionPixelSize2;
            }
            return Math.round((dimensionPixelSize * Resources.getSystem().getDisplayMetrics().density) / context.getResources().getDisplayMetrics().density);
        } catch (Resources.NotFoundException unused) {
            return 0;
        }
    }

    private static String getDeviceType(Context context) {
        String systemPropertyInSp = getSystemPropertyInSp(context, "ro.build.characteristics");
        return (TextUtils.isEmpty(systemPropertyInSp) || !systemPropertyInSp.equals("tablet")) ? "phone" : systemPropertyInSp;
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int orientation = windowManager.getDefaultDisplay().getOrientation();
        if (orientation == 1 || orientation == 3) {
            return displayMetrics.heightPixels;
        }
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int orientation = windowManager.getDefaultDisplay().getOrientation();
        if (orientation == 1 || orientation == 3) {
            return displayMetrics.widthPixels;
        }
        return displayMetrics.heightPixels;
    }

    private static String getProperty(String str) throws IOException {
        if (properties == null) {
            synchronized (DeviceUtils.class) {
                if (properties == null) {
                    properties = new Properties();
                    properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
                }
            }
        }
        return properties.getProperty(str);
    }
}
