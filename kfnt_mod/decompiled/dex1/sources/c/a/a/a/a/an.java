package c.a.a.a.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class an {
    public static String a(Context context) {
        try {
            return d(context).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return "";
        }
    }

    public static int b(Context context) {
        try {
            return d(context).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            return 0;
        }
    }

    public static String c(Context context) {
        try {
            return String.valueOf(context.getPackageManager().getApplicationLabel(context.getApplicationInfo()));
        } catch (Exception unused) {
            return "";
        }
    }

    private static PackageInfo d(Context context) {
        return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
    }
}
