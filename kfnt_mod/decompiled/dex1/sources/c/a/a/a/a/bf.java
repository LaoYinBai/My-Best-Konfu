package c.a.a.a.a;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class bf {

    /* renamed from: a, reason: collision with root package name */
    public static int f2084a;

    /* renamed from: b, reason: collision with root package name */
    public static int f2085b;

    /* renamed from: c, reason: collision with root package name */
    public static int f2086c;

    public static String a(Context context) {
        try {
            b(context);
            c(context);
            d(context);
        } catch (Throwable unused) {
        }
        return "" + f2086c + "[<!>]" + f2084a + "*" + f2085b + "[<!>]";
    }

    private static void b(Context context) {
        int height;
        int i;
        try {
            Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            Point point = new Point();
            if (Build.VERSION.SDK_INT >= 17) {
                defaultDisplay.getRealSize(point);
                i = point.x;
            } else {
                if (Build.VERSION.SDK_INT < 13) {
                    f2084a = defaultDisplay.getWidth();
                    height = defaultDisplay.getHeight();
                    f2085b = height;
                }
                defaultDisplay.getSize(point);
                i = point.x;
            }
            f2084a = i;
            height = point.y;
            f2085b = height;
        } catch (Throwable unused) {
        }
    }

    private static void c(Context context) {
        if (context == null) {
            return;
        }
        try {
            new DisplayMetrics();
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            float f = displayMetrics.density;
            f2086c = displayMetrics.densityDpi;
        } catch (Throwable unused) {
        }
    }

    private static void d(Context context) {
        try {
            new DisplayMetrics();
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            float f = displayMetrics.xdpi;
            float f2 = displayMetrics.ydpi;
        } catch (Throwable unused) {
        }
    }
}
