package com.ss.android.socialbase.appdownloader.notification;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.view.KeyEvent;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationManagerCompat;
import com.ss.android.socialbase.appdownloader.ResourceUtils;
import com.ss.android.socialbase.appdownloader.depend.INotificationPermissionRequestCallback;
import com.ss.android.socialbase.appdownloader.view.NotificationPermissionRequestFragment;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NotificationPermissionHelper {
    private static final String TAG = "NotificationPermissionHelper";
    private static NotificationPermissionRequestFragment fragment;
    private static List<INotificationPermissionRequestCallback> permissionRequestCallbacks = new ArrayList();
    private static AlertDialog permissionRequestDialog;

    public static boolean isNotificationEnabled() {
        try {
            return NotificationManagerCompat.from(DownloadComponentManager.getAppContext()).areNotificationsEnabled();
        } catch (Throwable th) {
            th.printStackTrace();
            return true;
        }
    }

    public static synchronized void handlePermissionRequestCallback(boolean z) {
        try {
            if (permissionRequestDialog != null) {
                permissionRequestDialog.cancel();
                permissionRequestDialog = null;
            }
            for (INotificationPermissionRequestCallback iNotificationPermissionRequestCallback : permissionRequestCallbacks) {
                if (iNotificationPermissionRequestCallback != null) {
                    if (z) {
                        iNotificationPermissionRequestCallback.onGranted();
                    } else {
                        iNotificationPermissionRequestCallback.onDenied();
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static synchronized void showNotificationRequestDialog(@NonNull final Activity activity, @NonNull final INotificationPermissionRequestCallback iNotificationPermissionRequestCallback) {
        if (iNotificationPermissionRequestCallback == null) {
            return;
        }
        try {
            if (activity != null) {
                try {
                } catch (Throwable th) {
                    th.printStackTrace();
                    handlePermissionRequestCallback(false);
                }
                if (!activity.isFinishing()) {
                    int stringId = ResourceUtils.getStringId(DownloadComponentManager.getAppContext(), "tt_appdownloader_notification_request_title");
                    int stringId2 = ResourceUtils.getStringId(DownloadComponentManager.getAppContext(), "tt_appdownloader_notification_request_message");
                    int stringId3 = ResourceUtils.getStringId(DownloadComponentManager.getAppContext(), "tt_appdownloader_notification_request_btn_yes");
                    int stringId4 = ResourceUtils.getStringId(DownloadComponentManager.getAppContext(), "tt_appdownloader_notification_request_btn_no");
                    permissionRequestCallbacks.add(iNotificationPermissionRequestCallback);
                    if (permissionRequestDialog == null || !permissionRequestDialog.isShowing()) {
                        permissionRequestDialog = new AlertDialog.Builder(activity).setTitle(stringId).setMessage(stringId2).setPositiveButton(stringId3, new DialogInterface.OnClickListener() { // from class: com.ss.android.socialbase.appdownloader.notification.NotificationPermissionHelper.3
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i) {
                                NotificationPermissionHelper.requestNotificationPermission(activity, iNotificationPermissionRequestCallback);
                                dialogInterface.cancel();
                                AlertDialog unused = NotificationPermissionHelper.permissionRequestDialog = null;
                            }
                        }).setNegativeButton(stringId4, new DialogInterface.OnClickListener() { // from class: com.ss.android.socialbase.appdownloader.notification.NotificationPermissionHelper.2
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i) {
                                NotificationPermissionHelper.handlePermissionRequestCallback(false);
                            }
                        }).setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.ss.android.socialbase.appdownloader.notification.NotificationPermissionHelper.1
                            @Override // android.content.DialogInterface.OnKeyListener
                            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                                if (i != 4) {
                                    return false;
                                }
                                if (keyEvent.getAction() == 1) {
                                    NotificationPermissionHelper.handlePermissionRequestCallback(false);
                                }
                                return true;
                            }
                        }).setCancelable(false).show();
                    }
                    return;
                }
            }
            iNotificationPermissionRequestCallback.onDenied();
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public static void requestNotificationPermission(@NonNull Activity activity, @NonNull INotificationPermissionRequestCallback iNotificationPermissionRequestCallback) {
        if (activity != null) {
            try {
                if (!activity.isFinishing()) {
                    FragmentManager fragmentManager = activity.getFragmentManager();
                    fragment = (NotificationPermissionRequestFragment) fragmentManager.findFragmentByTag(TAG);
                    if (fragment == null) {
                        fragment = new NotificationPermissionRequestFragment();
                        fragmentManager.beginTransaction().add(fragment, TAG).commitAllowingStateLoss();
                        try {
                            fragmentManager.executePendingTransactions();
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                    fragment.getToNotificationSetting();
                    return;
                }
            } catch (Throwable th2) {
                try {
                    th2.printStackTrace();
                    iNotificationPermissionRequestCallback.onGranted();
                    return;
                } catch (Throwable th3) {
                    th3.printStackTrace();
                    return;
                }
            }
        }
        iNotificationPermissionRequestCallback.onGranted();
    }
}
