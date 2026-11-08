package com.ss.union.game.sdk.common.util;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.qq.e.comm.compliance.ApkDownloadComplianceInterface;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.ss.union.game.sdk.core.glide.request.target.Target;
import com.ss.union.game.sdk.feedback.picture.select.d;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class StatusBarUtils {
    public static void openImmersion(Activity activity) {
        if (activity == null) {
            return;
        }
        openImmersion(activity.getWindow());
    }

    public static void openImmersion(Dialog dialog) {
        if (dialog == null) {
            return;
        }
        openImmersion(dialog.getWindow());
    }

    public static void openImmersion(DialogFragment dialogFragment) {
        if (dialogFragment == null) {
            return;
        }
        openImmersion(dialogFragment.getDialog());
    }

    public static void openImmersion(Window window) {
        if (window == null) {
            return;
        }
        try {
            window.addFlags(d.d);
            if (Build.VERSION.SDK_INT >= 21 && !OSUtils.isEMUI3_x()) {
                a(window);
                window.clearFlags(DownloadExpSwitchCode.BACK_BUGFIX_SIGBUS);
                window.clearFlags(DownloadExpSwitchCode.BUGFIX_SIGBUS_24_25);
                window.addFlags(Target.SIZE_ORIGINAL);
                window.setStatusBarColor(0);
            } else {
                window.addFlags(DownloadExpSwitchCode.BACK_BUGFIX_SIGBUS);
            }
            window.getDecorView().setSystemUiVisibility(5380);
        } catch (Throwable unused) {
        }
    }

    public static void closeImmersion(Activity activity) {
        if (activity == null) {
            return;
        }
        closeImmersion(activity.getWindow());
    }

    public static void closeImmersion(Dialog dialog) {
        if (dialog == null) {
            return;
        }
        closeImmersion(dialog.getWindow());
    }

    public static void closeImmersion(DialogFragment dialogFragment) {
        if (dialogFragment == null) {
            return;
        }
        closeImmersion(dialogFragment.getDialog());
    }

    public static void closeImmersion(Window window) {
        if (window == null) {
            return;
        }
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                window.getDecorView().setSystemUiVisibility(ApkDownloadComplianceInterface.INSTALL_BITS);
                if (Build.VERSION.SDK_INT >= 28) {
                    WindowManager.LayoutParams attributes = window.getAttributes();
                    attributes.layoutInDisplayCutoutMode = 0;
                    window.setAttributes(attributes);
                }
            } else if (Build.VERSION.SDK_INT >= 19) {
                WindowManager.LayoutParams attributes2 = window.getAttributes();
                attributes2.flags = (-67108865) & attributes2.flags;
                window.setAttributes(attributes2);
            }
        } catch (Throwable unused) {
        }
    }

    private static void a(Window window) {
        if (Build.VERSION.SDK_INT >= 28) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.layoutInDisplayCutoutMode = 1;
            window.setAttributes(attributes);
        }
    }

    public static void showStatusBar(Activity activity) {
        if (activity == null) {
            return;
        }
        showStatusBar(activity.getWindow());
    }

    public static void showStatusBar(Dialog dialog) {
        if (dialog == null) {
            return;
        }
        showStatusBar(dialog.getWindow());
    }

    public static void showStatusBar(DialogFragment dialogFragment) {
        if (dialogFragment == null) {
            return;
        }
        showStatusBar(dialogFragment.getDialog());
    }

    public static void showStatusBar(Window window) {
        if (window == null) {
            return;
        }
        try {
            window.clearFlags(d.d);
            window.getDecorView().setSystemUiVisibility(5376);
        } catch (Throwable unused) {
        }
    }

    public static void hideStatusBar(Activity activity) {
        if (activity == null) {
            return;
        }
        hideStatusBar(activity.getWindow());
    }

    public static void hideStatusBar(Dialog dialog) {
        if (dialog == null) {
            return;
        }
        hideStatusBar(dialog.getWindow());
    }

    public static void hideStatusBar(DialogFragment dialogFragment) {
        if (dialogFragment == null) {
            return;
        }
        hideStatusBar(dialogFragment.getDialog());
    }

    public static void hideStatusBar(Window window) {
        if (window == null) {
            return;
        }
        try {
            window.addFlags(d.d);
            window.getDecorView().setSystemUiVisibility(5380);
        } catch (Throwable unused) {
        }
    }

    public static void changeStatusBarColor(Activity activity, String str) {
        if (activity == null) {
            return;
        }
        changeStatusBarColor(activity.getWindow(), str);
    }

    public static void changeStatusBarColor(Dialog dialog, String str) {
        if (dialog == null) {
            return;
        }
        changeStatusBarColor(dialog.getWindow(), str);
    }

    public static void changeStatusBarColor(DialogFragment dialogFragment, String str) {
        if (dialogFragment == null) {
            return;
        }
        changeStatusBarColor(dialogFragment.getDialog(), str);
    }

    public static void changeStatusBarColor(Window window, String str) {
        try {
            if (Build.VERSION.SDK_INT < 21 || window == null) {
                return;
            }
            window.setStatusBarColor(Color.parseColor(str));
        } catch (Throwable unused) {
        }
    }

    public static void changeStatusBarColor(Window window, int i) {
        try {
            if (Build.VERSION.SDK_INT < 21 || window == null) {
                return;
            }
            window.setStatusBarColor(i);
        } catch (Throwable unused) {
        }
    }

    public static boolean isSupportStatusBarLightMode() {
        return OSUtils.isMIUI6Later() || OSUtils.isFlymeOS4Later() || Build.VERSION.SDK_INT >= 23;
    }

    public static boolean statusBarLightMode(Activity activity, boolean z) {
        if (activity == null) {
            return false;
        }
        return statusBarLightMode(activity.getWindow(), z);
    }

    public static boolean statusBarLightMode(Dialog dialog, boolean z) {
        if (dialog == null) {
            return false;
        }
        return statusBarLightMode(dialog.getWindow(), z);
    }

    public static boolean statusBarLightMode(DialogFragment dialogFragment, boolean z) {
        if (dialogFragment == null) {
            return false;
        }
        return statusBarLightMode(dialogFragment.getDialog(), z);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean statusBarLightMode(android.view.Window r4, boolean r5) {
        /*
            r0 = 0
            if (r4 != 0) goto L4
            return r0
        L4:
            r1 = 1
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L23
            r3 = 19
            if (r2 < r3) goto L23
            boolean r2 = b(r4, r5)     // Catch: java.lang.Throwable -> L23
            if (r2 == 0) goto L13
            r2 = 1
            goto L24
        L13:
            boolean r2 = a(r4, r5)     // Catch: java.lang.Throwable -> L23
            if (r2 == 0) goto L1b
            r2 = 2
            goto L24
        L1b:
            boolean r2 = c(r4, r5)     // Catch: java.lang.Throwable -> L23
            if (r2 == 0) goto L23
            r2 = 3
            goto L24
        L23:
            r2 = 0
        L24:
            if (r2 != 0) goto L2d
            if (r5 == 0) goto L2d
            java.lang.String r5 = "#000000"
            changeStatusBarColor(r4, r5)
        L2d:
            if (r2 == 0) goto L30
            r0 = 1
        L30:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.union.game.sdk.common.util.StatusBarUtils.statusBarLightMode(android.view.Window, boolean):boolean");
    }

    private static boolean a(Window window, boolean z) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        if (window != null) {
            try {
                WindowManager.LayoutParams attributes = window.getAttributes();
                Field declaredField = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field declaredField2 = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                declaredField.setAccessible(true);
                declaredField2.setAccessible(true);
                int i = declaredField.getInt(null);
                int i2 = declaredField2.getInt(attributes);
                declaredField2.setInt(attributes, z ? i2 | i : (~i) & i2);
                window.setAttributes(attributes);
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    private static boolean b(Window window, boolean z) throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (window != null) {
            Class<?> cls = window.getClass();
            try {
                Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
                Method method = cls.getMethod("setExtraFlags", Integer.TYPE, Integer.TYPE);
                if (z) {
                    method.invoke(window, Integer.valueOf(i), Integer.valueOf(i));
                } else {
                    method.invoke(window, 0, Integer.valueOf(i));
                }
                c(window, z);
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    private static boolean c(Window window, boolean z) {
        if (Build.VERSION.SDK_INT < 23) {
            return false;
        }
        View decorView = window.getDecorView();
        int systemUiVisibility = decorView.getSystemUiVisibility();
        int i = z ? systemUiVisibility | 8192 : systemUiVisibility & (-8193);
        if (i == systemUiVisibility) {
            return true;
        }
        decorView.setSystemUiVisibility(i);
        return true;
    }

    public static int getStatusBarHeight() {
        Context context = GlobalApplicationUtils.getContext();
        if (context == null) {
            return 0;
        }
        try {
            int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (identifier > 0) {
                return context.getResources().getDimensionPixelSize(identifier);
            }
            return 0;
        } catch (Throwable unused) {
            return 0;
        }
    }
}
