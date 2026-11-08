package com.kwad.sdk.a.kwai;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.v8.Platform;
import java.lang.reflect.Field;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static int a;

    public static int a(@Nullable Context context) {
        if (a <= 0 && context != null) {
            int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", Platform.ANDROID);
            if (identifier > 0) {
                a = context.getResources().getDimensionPixelSize(identifier);
            } else {
                try {
                    Class<?> cls = Class.forName("com.android.internal.R$dimen");
                    Object objNewInstance = cls.newInstance();
                    Field field = cls.getField("status_bar_height");
                    field.setAccessible(true);
                    a = context.getResources().getDimensionPixelSize(Integer.parseInt(field.get(objNewInstance).toString()));
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
            if (a <= 0) {
                a = a(context, 25.0f);
            }
            return a;
        }
        return a;
    }

    public static int a(Context context, float f) {
        return (int) (context == null ? f * 2.0f : (f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static View a(@NonNull Activity activity) {
        return a(activity.getWindow());
    }

    public static View a(ViewGroup viewGroup, int i, boolean z) {
        return d(viewGroup.getContext()).inflate(i, viewGroup, z);
    }

    public static View a(@NonNull Window window) {
        return window.getDecorView().findViewById(R.id.content);
    }

    public static void a(View view, int i) {
        if (view == null || i == 0) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = i;
        view.setLayoutParams(layoutParams);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(android.view.View r5, int r6, int r7) {
        /*
            if (r5 != 0) goto L3
            return
        L3:
            if (r6 == 0) goto L4f
            if (r7 != 0) goto L8
            goto L4f
        L8:
            android.view.ViewParent r0 = r5.getParent()
            android.view.View r0 = (android.view.View) r0
            if (r0 != 0) goto L11
            return
        L11:
            int r1 = r0.getWidth()
            int r0 = r0.getHeight()
            if (r1 == 0) goto L4f
            if (r0 != 0) goto L1e
            goto L4f
        L1e:
            android.view.ViewGroup$LayoutParams r2 = r5.getLayoutParams()
            r3 = 1065353216(0x3f800000, float:1.0)
            r4 = -1
            if (r1 <= r0) goto L3c
            if (r6 <= r7) goto L2e
        L29:
            r2.width = r4
            r2.height = r4
            goto L4c
        L2e:
            float r6 = (float) r6
            float r7 = (float) r7
            float r7 = r7 * r3
            float r6 = r6 / r7
            float r7 = (float) r0
            float r6 = r6 * r7
            int r6 = (int) r6
            r2.width = r6
            r2.height = r0
            goto L4c
        L3c:
            if (r7 <= r6) goto L3f
            goto L29
        L3f:
            r2.width = r1
            float r7 = (float) r7
            float r6 = (float) r6
            float r6 = r6 * r3
            float r7 = r7 / r6
            float r6 = (float) r1
            float r7 = r7 * r6
            int r6 = (int) r7
            r2.height = r6
        L4c:
            r5.setLayoutParams(r2)
        L4f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.a.kwai.a.a(android.view.View, int, int):void");
    }

    public static int b(@NonNull Activity activity) {
        return a(activity).getHeight();
    }

    @Deprecated
    public static int b(@Nullable Context context) {
        if (context == null) {
            return 0;
        }
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static void b(View view, int i, int i2) {
        View view2;
        if (view == null || i == 0 || i2 == 0 || (view2 = (View) view.getParent()) == null) {
            return;
        }
        int width = view2.getWidth();
        int height = view2.getHeight();
        if (width == 0 || height == 0) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (i > i2) {
            layoutParams.width = width;
            layoutParams.height = (int) ((i2 / (i * 1.0f)) * width);
        } else {
            layoutParams.width = (int) ((i / (i2 * 1.0f)) * height);
            layoutParams.height = height;
        }
        view.setLayoutParams(layoutParams);
    }

    @Deprecated
    public static int c(@Nullable Context context) {
        if (context == null) {
            return 0;
        }
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static void c(View view, int i, int i2) {
        View view2;
        if (view == null || i == 0 || i2 == 0 || (view2 = (View) view.getParent()) == null) {
            return;
        }
        int width = view2.getWidth();
        int height = view2.getHeight();
        if (width == 0 || height == 0) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (width <= height || i > i2) {
            layoutParams.width = width;
            layoutParams.height = (int) ((i2 / (i * 1.0f)) * width);
        } else {
            layoutParams.width = (int) ((i / (i2 * 1.0f)) * height);
            layoutParams.height = height;
        }
        view.setLayoutParams(layoutParams);
    }

    private static LayoutInflater d(Context context) {
        return LayoutInflater.from(context);
    }
}
