package com.ss.android.socialbase.appdownloader.view;

import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.ss.android.socialbase.appdownloader.notification.NotificationPermissionHelper;
import com.ss.android.socialbase.downloader.constants.DownloadConstants;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.umeng.message.common.a;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NotificationPermissionRequestFragment extends Fragment {
    public void getToNotificationSetting() {
        try {
            try {
                try {
                    startActivityForResult(getNotificationSettingsIntent(), 1000);
                } catch (Throwable unused) {
                    startActivityForResult(getOriginSettingIntent(), 1000);
                }
            } catch (Throwable unused2) {
                startActivityForResult(getAppSettingIntent(), 1000);
            }
        } catch (Throwable unused3) {
            startActivityForResult(getAppInfoIntent(), 1000);
        }
    }

    public Intent getAppSettingIntent() {
        Context appContext = getAppContext();
        if (appContext == null) {
            return null;
        }
        String packageName = appContext.getPackageName();
        if (!TextUtils.isEmpty(Build.MANUFACTURER)) {
            String lowerCase = Build.MANUFACTURER.toLowerCase();
            if (lowerCase.contains(DownloadConstants.LOWER_OPPO)) {
                Intent intent = new Intent();
                intent.putExtra("packageName", packageName);
                intent.setComponent(new ComponentName("com.color.safecenter", "com.color.safecenter.permission.PermissionManagerActivity"));
                return intent;
            }
            if (lowerCase.contains("vivo")) {
                Intent intent2 = new Intent();
                intent2.putExtra("packagename", packageName);
                if (Build.VERSION.SDK_INT >= 25) {
                    intent2.setComponent(new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.SoftPermissionDetailActivity"));
                } else {
                    intent2.setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.safeguard.SoftPermissionDetailActivity"));
                }
                return intent2;
            }
            if (lowerCase.contains("meizu") && Build.VERSION.SDK_INT < 25) {
                Intent intent3 = new Intent("com.meizu.safe.security.SHOW_APPSEC");
                intent3.putExtra("packageName", packageName);
                intent3.setComponent(new ComponentName("com.meizu.safe", "com.meizu.safe.security.AppSecActivity"));
                return intent3;
            }
        }
        return new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + appContext.getPackageName()));
    }

    private Context getAppContext() {
        Context appContext = DownloadComponentManager.getAppContext();
        return (appContext != null || getActivity() == null || getActivity().isFinishing()) ? appContext : getActivity().getApplicationContext();
    }

    public Intent getAppInfoIntent() {
        Context appContext = getAppContext();
        if (appContext == null) {
            return null;
        }
        return new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + appContext.getPackageName()));
    }

    private Intent getNotificationSettingsIntent() {
        Context appContext = getAppContext();
        if (appContext == null) {
            return null;
        }
        Intent intent = new Intent("android.settings.APP_NOTIFICATION_SETTINGS");
        String packageName = appContext.getPackageName();
        intent.putExtra(a.u, packageName);
        intent.putExtra("android.provider.extra.APP_PACKAGE", packageName);
        intent.putExtra("app_package", packageName);
        int i = appContext.getApplicationInfo().uid;
        intent.putExtra("uid", i);
        intent.putExtra("app_uid", i);
        return intent;
    }

    public static Intent getOriginSettingIntent() {
        return new Intent("android.settings.APPLICATION_SETTINGS");
    }

    @Override // android.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (NotificationPermissionHelper.isNotificationEnabled()) {
            NotificationPermissionHelper.handlePermissionRequestCallback(true);
        } else {
            NotificationPermissionHelper.handlePermissionRequestCallback(false);
        }
    }
}
