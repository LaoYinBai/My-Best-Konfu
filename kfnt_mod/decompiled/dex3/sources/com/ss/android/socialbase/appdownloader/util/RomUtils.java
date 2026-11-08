package com.ss.android.socialbase.appdownloader.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.ss.android.socialbase.appdownloader.AppUtils;
import com.ss.android.socialbase.downloader.BuildConfig;
import com.ss.android.socialbase.downloader.constants.DownloadConstants;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import com.ss.union.game.sdk.feedback.picture.select.d;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class RomUtils {
    private static final String KEY_VERSION_EMUI = "ro.build.version.emui";
    private static final String KEY_VERSION_GIONEE = "ro.gn.sv.version";
    private static final String KEY_VERSION_LENOVO = "ro.lenovo.lvp.version";
    private static final String KEY_VERSION_MIUI = "ro.miui.ui.version.name";
    private static final String KEY_VERSION_ONEPLUS = "ro.rom.version";
    private static String KEY_VERSION_OPPO = "";
    private static final String KEY_VERSION_SMARTISAN = "ro.smartisan.version";
    private static final String KEY_VERSION_VIVO = "ro.vivo.os.version";
    public static final String MARKET_PKG_NAME_EMUI = "com.huawei.appmarket";
    public static final String MARKET_PKG_NAME_GIONEE = "com.gionee.aora.market";
    public static final String MARKET_PKG_NAME_LENOVO = "com.lenovo.leos.appstore";
    public static final String MARKET_PKG_NAME_MEIZU = "com.meizu.mstore";
    public static final String MARKET_PKG_NAME_MIUI = "com.xiaomi.market";
    public static final String MARKET_PKG_NAME_NUBIA = "cn.nubia.neostore";
    public static String MARKET_PKG_NAME_OPPO = "";
    public static final String MARKET_PKG_NAME_OPPO1 = "com.heytap.market";
    public static final String MARKET_PKG_NAME_QIKU = "";
    public static final String MARKET_PKG_NAME_SAMSUNG = "com.sec.android.app.samsungapps";
    public static final String MARKET_PKG_NAME_SMARTISAN = "com.smartisanos.appstore";
    public static final String MARKET_PKG_NAME_VIVO = "com.bbk.appstore";
    public static final String MARKET_PKG_NAME_ZTE = "zte.com.market";
    public static final String ROM_EMUI = "EMUI";
    public static final String ROM_GIONEE = "QIONEE";
    public static final String ROM_LENOVO = "LENOVO";
    public static final String ROM_MEIZU = "FLYME";
    public static final String ROM_MIUI = "MIUI";
    public static final String ROM_NUBIA = "NUBIA";
    public static final String ROM_ONEPLUS = "ONEPLUS";
    public static String ROM_OPPO = null;
    public static final String ROM_QIKU = "QIKU";
    public static final String ROM_SAMSUNG = "SAMSUNG";
    public static final String ROM_SMARTISAN = "SMARTISAN";
    public static final String ROM_VIVO = "VIVO";
    public static final String ROM_ZTE = "ZTE";
    public static String sMarketPackageName;
    private static String sMiuiVersion;
    private static String sName;
    private static String sVersion;

    public static boolean isEmui() {
        return check(ROM_EMUI);
    }

    public static boolean isMiui() {
        return check(ROM_MIUI);
    }

    public static boolean isVivo() {
        return check(ROM_VIVO);
    }

    public static boolean isOppo() {
        ensureOPPO();
        return check(ROM_OPPO);
    }

    public static boolean isFlyme() {
        return check(ROM_MEIZU);
    }

    public static boolean isQiku() {
        return check(ROM_QIKU) || check("360");
    }

    public static boolean isSmartisan() {
        return check(ROM_SMARTISAN);
    }

    public static boolean isGionee() {
        return check(ROM_GIONEE);
    }

    public static boolean isSamsung() {
        return check(ROM_SAMSUNG);
    }

    public static boolean isLenovo() {
        return check(ROM_LENOVO);
    }

    public static boolean isZTE() {
        return check(ROM_ZTE);
    }

    public static boolean isNubia() {
        return check(ROM_NUBIA);
    }

    public static boolean isOnePlus() {
        return check(ROM_ONEPLUS);
    }

    public static String getName() {
        if (sName == null) {
            check("");
        }
        return sName;
    }

    public static String getVersion() {
        if (sVersion == null) {
            check("");
        }
        return sVersion;
    }

    public static String getMarketPackageName() {
        if (sMarketPackageName == null) {
            check("");
        }
        return sMarketPackageName;
    }

    private static void ensureOPPO() {
        if (TextUtils.isEmpty(ROM_OPPO)) {
            DownloadComponentManager.ensureOPPO();
            ROM_OPPO = DownloadConstants.UPPER_OPPO;
            KEY_VERSION_OPPO = "ro.build.version." + DownloadConstants.LOWER_OPPO + "rom";
            MARKET_PKG_NAME_OPPO = "com." + DownloadConstants.LOWER_OPPO + ".market";
        }
    }

    public static boolean check(String str) {
        ensureOPPO();
        if (sName != null) {
            return sName.equals(str);
        }
        String prop = getProp(KEY_VERSION_MIUI);
        sVersion = prop;
        if (!TextUtils.isEmpty(prop)) {
            sName = ROM_MIUI;
            sMarketPackageName = MARKET_PKG_NAME_MIUI;
            sMiuiVersion = sVersion;
        } else {
            String prop2 = getProp(KEY_VERSION_EMUI);
            sVersion = prop2;
            if (!TextUtils.isEmpty(prop2)) {
                sName = ROM_EMUI;
                sMarketPackageName = MARKET_PKG_NAME_EMUI;
            } else {
                String prop3 = getProp(KEY_VERSION_OPPO);
                sVersion = prop3;
                if (!TextUtils.isEmpty(prop3)) {
                    sName = ROM_OPPO;
                    if (AppUtils.getAppVersionCode(MARKET_PKG_NAME_OPPO) > -1) {
                        sMarketPackageName = MARKET_PKG_NAME_OPPO;
                    } else {
                        sMarketPackageName = MARKET_PKG_NAME_OPPO1;
                    }
                } else {
                    String prop4 = getProp(KEY_VERSION_VIVO);
                    sVersion = prop4;
                    if (!TextUtils.isEmpty(prop4)) {
                        sName = ROM_VIVO;
                        sMarketPackageName = MARKET_PKG_NAME_VIVO;
                    } else {
                        String prop5 = getProp(KEY_VERSION_SMARTISAN);
                        sVersion = prop5;
                        if (!TextUtils.isEmpty(prop5)) {
                            sName = ROM_SMARTISAN;
                            sMarketPackageName = MARKET_PKG_NAME_SMARTISAN;
                        } else {
                            String prop6 = getProp(KEY_VERSION_GIONEE);
                            sVersion = prop6;
                            if (!TextUtils.isEmpty(prop6)) {
                                sName = ROM_GIONEE;
                                sMarketPackageName = MARKET_PKG_NAME_GIONEE;
                            } else {
                                String prop7 = getProp(KEY_VERSION_LENOVO);
                                sVersion = prop7;
                                if (!TextUtils.isEmpty(prop7)) {
                                    sName = ROM_LENOVO;
                                    sMarketPackageName = MARKET_PKG_NAME_LENOVO;
                                } else if (getManufacturer().toUpperCase().contains(ROM_SAMSUNG)) {
                                    sName = ROM_SAMSUNG;
                                    sMarketPackageName = MARKET_PKG_NAME_SAMSUNG;
                                } else if (getManufacturer().toUpperCase().contains(ROM_ZTE)) {
                                    sName = ROM_ZTE;
                                    sMarketPackageName = MARKET_PKG_NAME_ZTE;
                                } else if (getManufacturer().toUpperCase().contains(ROM_NUBIA)) {
                                    sName = ROM_NUBIA;
                                    sMarketPackageName = MARKET_PKG_NAME_NUBIA;
                                } else if (getDisplay().toUpperCase().contains(ROM_MEIZU)) {
                                    sName = ROM_MEIZU;
                                    sMarketPackageName = MARKET_PKG_NAME_MEIZU;
                                    sVersion = getDisplay();
                                } else if (getManufacturer().toUpperCase().contains(ROM_ONEPLUS)) {
                                    sName = ROM_ONEPLUS;
                                    sVersion = getProp(KEY_VERSION_ONEPLUS);
                                    if (AppUtils.getAppVersionCode(MARKET_PKG_NAME_OPPO) > -1) {
                                        sMarketPackageName = MARKET_PKG_NAME_OPPO;
                                    } else {
                                        sMarketPackageName = MARKET_PKG_NAME_OPPO1;
                                    }
                                } else {
                                    sName = getManufacturer().toUpperCase();
                                    sMarketPackageName = "";
                                    sVersion = "";
                                }
                            }
                        }
                    }
                }
            }
        }
        return sName.equals(str);
    }

    public static String getPropByCmd(String str) throws Throwable {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop " + str).getInputStream()), d.d);
            try {
                String line = bufferedReader.readLine();
                bufferedReader.close();
                DownloadUtils.safeClose(bufferedReader);
                return line;
            } catch (Throwable unused) {
                DownloadUtils.safeClose(bufferedReader);
                return null;
            }
        } catch (Throwable th) {
            th = th;
        }
    }

    public static String getPropByReflect(String str) throws Throwable {
        return (String) Class.forName("android.os.SystemProperties").getMethod(MonitorConstants.CONNECT_TYPE_GET, String.class).invoke(null, str);
    }

    public static String getProp(String str) {
        if (DownloadSetting.getGlobalSettings().optBoolean(DownloadSettingKeys.ENABLE_REFLECT_SYSTEM_PROPERTIES, true)) {
            try {
                return getPropByReflect(str);
            } catch (Throwable th) {
                th.printStackTrace();
                return getPropByCmd(str);
            }
        }
        return getPropByCmd(str);
    }

    @NonNull
    public static String getManufacturer() {
        return Build.MANUFACTURER == null ? "" : Build.MANUFACTURER.trim();
    }

    @NonNull
    public static String getDisplay() {
        return Build.DISPLAY == null ? "" : Build.DISPLAY.trim();
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

    public static boolean isMiuiV10() {
        initMiuiVersion();
        return "V10".equals(sMiuiVersion);
    }

    public static boolean isMiuiV11() {
        initMiuiVersion();
        return "V11".equals(sMiuiVersion);
    }

    public static boolean isMiuiV12() {
        initMiuiVersion();
        return "V12".equals(sMiuiVersion);
    }

    private static void initMiuiVersion() {
        if (sMiuiVersion == null) {
            try {
                sMiuiVersion = getProp(KEY_VERSION_MIUI);
            } catch (Exception e) {
                e.printStackTrace();
            }
            sMiuiVersion = sMiuiVersion == null ? "" : sMiuiVersion;
        }
    }

    public static boolean tryStartSysPermissionActivity(Context context) {
        Intent intent;
        if (isOppo()) {
            Intent intent2 = new Intent();
            intent2.setFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
            intent2.putExtra("packageName", BuildConfig.APPLICATION_ID);
            intent2.setComponent(new ComponentName("com.color.safecenter", "com.color.safecenter.permission.PermissionManagerActivity"));
            context.startActivity(intent2);
            return true;
        }
        if (!isVivo()) {
            if (isMiui()) {
                if (isMiuiV7()) {
                    intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
                    intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
                    intent.putExtra("extra_pkgname", context.getPackageName());
                } else if (isMiuiV8() || isMiuiV9()) {
                    intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
                    intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
                    intent.putExtra("extra_pkgname", context.getPackageName());
                } else {
                    intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
                    intent.setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity"));
                    intent.putExtra("extra_pkgname", BuildConfig.APPLICATION_ID);
                }
                context.startActivity(intent);
                return true;
            }
            if (isEmui()) {
                Intent intent3 = new Intent();
                intent3.setFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
                intent3.putExtra("packageName", BuildConfig.APPLICATION_ID);
                intent3.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity"));
                context.startActivity(intent3);
                return true;
            }
            if (isFlyme()) {
                Intent intent4 = new Intent("com.meizu.safe.security.SHOW_APPSEC");
                intent4.addCategory("android.intent.category.DEFAULT");
                intent4.putExtra("packageName", BuildConfig.APPLICATION_ID);
                context.startActivity(intent4);
                return true;
            }
            if (isQiku()) {
                Intent intent5 = new Intent("com.meizu.safe.security.SHOW_APPSEC");
                intent5.addCategory("android.intent.category.DEFAULT");
                intent5.putExtra("packageName", BuildConfig.APPLICATION_ID);
                context.startActivity(intent5);
                return true;
            }
        }
        return false;
    }
}
