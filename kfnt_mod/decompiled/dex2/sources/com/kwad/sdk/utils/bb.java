package com.kwad.sdk.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.api.core.ResContext;
import com.kwad.sdk.core.response.model.PhotoInfo;
import java.util.HashSet;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class bb {
    private static long a;

    @Deprecated
    public static int a(@Nullable Context context) {
        if (context == null) {
            return 0;
        }
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int a(Context context, float f) {
        return (int) (context == null ? f * 2.0f : (f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    @NonNull
    public static Context a(@NonNull View view) {
        Activity activityB = b(view);
        if (activityB == null && (view.getParent() instanceof View)) {
            activityB = b((View) view.getParent());
        }
        return activityB == null ? view.getContext() : activityB;
    }

    public static void a(View view, int i, int i2, int i3, int i4) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).setMargins(i, i2, i3, i4);
            view.requestLayout();
        }
    }

    public static boolean a() {
        long jUptimeMillis = SystemClock.uptimeMillis();
        if (Math.abs(jUptimeMillis - a) < 500) {
            a = jUptimeMillis;
            return true;
        }
        a = jUptimeMillis;
        return false;
    }

    public static boolean a(@Nullable Matrix matrix, int i, int i2, PhotoInfo.VideoInfo videoInfo) {
        if (matrix != null) {
            matrix.reset();
        }
        if (videoInfo == null) {
            return false;
        }
        float f = videoInfo.width;
        float f2 = videoInfo.height;
        if (f >= f2) {
            return false;
        }
        float f3 = (float) videoInfo.leftRatio;
        float f4 = (float) videoInfo.topRatio;
        float f5 = (float) videoInfo.widthRatio;
        float f6 = (float) videoInfo.heightRatio;
        float f7 = i;
        float f8 = f7 / f;
        float f9 = i2;
        float f10 = f9 / f2;
        float f11 = f8 / f10;
        if (Math.abs(f11 - 1.0f) < 0.01d) {
            return false;
        }
        if (f8 > f10) {
            float f12 = f10 / f8;
            float f13 = (1.0f - f12) / 2.0f;
            float f14 = (f12 + 1.0f) / 2.0f;
            if (f4 < f13 || f4 + f6 > f14) {
                return false;
            }
        } else {
            float f15 = (1.0f - f11) / 2.0f;
            float f16 = (f11 + 1.0f) / 2.0f;
            if (f3 < f15 || f3 + f5 > f16) {
                return false;
            }
            f8 = f10;
        }
        if (matrix == null) {
            return true;
        }
        matrix.preTranslate((f7 - f) / 2.0f, (f9 - f2) / 2.0f);
        matrix.preScale(f / f7, f2 / f9);
        matrix.postScale(f8, f8, f7 / 2.0f, f9 / 2.0f);
        return true;
    }

    public static boolean a(View view, int i) {
        return view != null && b(view, i, true) && view.hasWindowFocus() && e(view.getContext());
    }

    public static boolean a(View view, int i, boolean z) {
        return view != null && b(view, i, z) && e(view.getContext());
    }

    @Deprecated
    public static int b(@Nullable Context context) {
        if (context == null) {
            return 0;
        }
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    @Nullable
    public static Activity b(View view) {
        Object context = view.getContext();
        HashSet hashSet = new HashSet();
        do {
            hashSet.add(context);
            if (!(context instanceof ContextWrapper)) {
                break;
            }
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = context instanceof ResContext ? ((ResContext) context).getDelegatedContext() : ((ContextWrapper) context).getBaseContext();
        } while (!hashSet.contains(context));
        return null;
    }

    private static boolean b(View view, int i, boolean z) {
        if (view == null || view.getParent() == null) {
            return false;
        }
        if (com.kwad.sdk.core.config.b.ay()) {
            if (!view.isShown() && view.getVisibility() != 0) {
                return false;
            }
        } else if (!view.isShown() || view.getVisibility() != 0) {
            return false;
        }
        if (z && !view.hasWindowFocus()) {
            return false;
        }
        if (!view.getGlobalVisibleRect(new Rect())) {
            return false;
        }
        long height = view.getHeight() * view.getWidth();
        return height > 0 && (r9.height() * r9.width()) * 100 >= ((long) i) * height;
    }

    public static float c(@NonNull Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    @Nullable
    public static int[] c(View view) {
        if (view == null) {
            return null;
        }
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        int width = view.getWidth();
        int height = view.getHeight();
        iArr[0] = iArr[0] + (width / 2);
        iArr[1] = iArr[1] + (height / 2);
        return iArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public static Context d(Context context) {
        Context delegatedContext;
        ResContext resContext = context instanceof ResContext ? (ResContext) context : null;
        HashSet hashSet = new HashSet();
        hashSet.add(context);
        ResContext resContext2 = resContext;
        for (Context context2 = context; context2 instanceof ContextWrapper; context2 = delegatedContext) {
            if (context2 instanceof Activity) {
                return context2;
            }
            if (context2 instanceof ResContext) {
                ResContext resContext3 = (ResContext) context2;
                resContext2 = resContext3;
                delegatedContext = resContext3.getDelegatedContext();
            } else {
                delegatedContext = ((ContextWrapper) context2).getBaseContext();
            }
            if (hashSet.contains(delegatedContext)) {
                break;
            }
            hashSet.add(delegatedContext);
        }
        return resContext2 != null ? resContext2.getDelegatedContext() : context;
    }

    private static boolean e(Context context) {
        return ak.a().a(context);
    }
}
