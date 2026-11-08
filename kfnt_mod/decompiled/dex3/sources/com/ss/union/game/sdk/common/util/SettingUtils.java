package com.ss.union.game.sdk.common.util;

import android.app.Activity;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import com.ss.android.account.model2.BDAccountPlatformEntity;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.umeng.message.common.a;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SettingUtils {

    public static class Permission {
        public static void goToPermissionSettingPage() {
            goToPermissionSettingPage(GlobalApplicationUtils.getContext());
        }

        public static void goToPermissionSettingPage(Context context) {
            if (context == null) {
                return;
            }
            Intent intentA = a(context);
            intentA.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
            try {
                context.startActivity(intentA);
            } catch (Exception unused) {
                context.startActivity(b(context));
            }
        }

        public static void goToPermissionSettingPage(Object obj, int i) {
            if (obj == null) {
                goToPermissionSettingPage();
            }
            if (obj instanceof Activity) {
                Activity activity = (Activity) obj;
                try {
                    activity.startActivityForResult(a(activity), i);
                    return;
                } catch (Exception unused) {
                    activity.startActivityForResult(b(activity), i);
                    return;
                }
            }
            if (obj instanceof Fragment) {
                Fragment fragment = (Fragment) obj;
                Activity activity2 = fragment.getActivity();
                try {
                    fragment.startActivityForResult(a(activity2), i);
                    return;
                } catch (Exception unused2) {
                    fragment.startActivityForResult(b(activity2), i);
                    return;
                }
            }
            goToPermissionSettingPage();
        }

        private static Intent a(Context context) {
            String lowerCase = Build.MANUFACTURER.toLowerCase();
            if (lowerCase.contains(BDAccountPlatformEntity.PLAT_NAME_HUAWEI)) {
                return c(context);
            }
            if (lowerCase.contains("xiaomi")) {
                return d(context);
            }
            if (lowerCase.contains("oppo")) {
                return e(context);
            }
            if (lowerCase.contains("vivo")) {
                return f(context);
            }
            if (lowerCase.contains("meizu")) {
                return g(context);
            }
            return b(context);
        }

        private static Intent b(Context context) {
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts(a.u, context.getPackageName(), null));
            return intent;
        }

        private static Intent c(Context context) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity"));
            if (a(context, intent)) {
                return intent;
            }
            intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.addviewmonitor.AddViewMonitorActivity"));
            if (a(context, intent)) {
                return intent;
            }
            intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.notificationmanager.ui.NotificationManagmentActivity"));
            return intent;
        }

        private static Intent d(Context context) {
            Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
            intent.putExtra("extra_pkgname", context.getPackageName());
            if (a(context, intent)) {
                return intent;
            }
            intent.setPackage("com.miui.securitycenter");
            if (a(context, intent)) {
                return intent;
            }
            intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
            if (a(context, intent)) {
                return intent;
            }
            intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
            return intent;
        }

        private static Intent e(Context context) {
            Intent intent = new Intent();
            intent.putExtra("packageName", context.getPackageName());
            intent.setClassName("com.color.safecenter", "com.color.safecenter.permission.floatwindow.FloatWindowListActivity");
            if (a(context, intent)) {
                return intent;
            }
            intent.setClassName("com.coloros.safecenter", "com.coloros.safecenter.sysfloatwindow.FloatWindowListActivity");
            if (a(context, intent)) {
                return intent;
            }
            intent.setClassName("com.oppo.safe", "com.oppo.safe.permission.PermissionAppListActivity");
            return intent;
        }

        private static Intent f(Context context) {
            Intent intent = new Intent();
            intent.putExtra("packagename", context.getPackageName());
            intent.setClassName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.SoftPermissionDetailActivity");
            if (a(context, intent)) {
                return intent;
            }
            intent.setClassName("com.iqoo.secure", "com.iqoo.secure.safeguard.SoftPermissionDetailActivity");
            if (a(context, intent)) {
                return intent;
            }
            intent.setClassName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.FloatWindowManager");
            intent.putExtra("packagename", context.getPackageName());
            return intent;
        }

        private static Intent g(Context context) {
            Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
            intent.putExtra("packageName", context.getPackageName());
            intent.setComponent(new ComponentName("com.meizu.safe", "com.meizu.safe.security.AppSecActivity"));
            return intent;
        }

        private static boolean a(Context context, Intent intent) {
            return context.getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
        }
    }
}
