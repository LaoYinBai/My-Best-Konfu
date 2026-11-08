package com.kwad.sdk.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.view.Window;
import androidx.annotation.Nullable;
import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.api.core.ResContext;
import com.kwad.sdk.api.loader.Wrapper;
import java.util.HashSet;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class af {
    public static void a(Context context, boolean z) {
        try {
            Activity activityF = f(context);
            if (activityF == null) {
                return;
            }
            if (z) {
                if (Build.VERSION.SDK_INT < 19) {
                    activityF.getWindow().getDecorView().setSystemUiVisibility(8);
                } else {
                    activityF.getWindow().getDecorView().setSystemUiVisibility(1792);
                }
            } else if (Build.VERSION.SDK_INT < 19) {
                activityF.getWindow().getDecorView().setSystemUiVisibility(0);
            } else {
                activityF.getWindow().getDecorView().setSystemUiVisibility(3846);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean a() {
        Context context = KsAdSDKImpl.get().getContext();
        return context == null || Wrapper.unwrapContextIfNeed(context).getApplicationContext().getResources().getConfiguration().orientation == 1;
    }

    public static boolean a(Context context) {
        Activity activityF = f(context);
        if (activityF != null) {
            Window window = activityF.getWindow();
            z = (window.getAttributes().flags & 1024) == 1024;
            window.setFlags(1024, 1024);
        }
        return z;
    }

    public static void b(Context context) {
        Activity activityF = f(context);
        if (activityF != null) {
            activityF.getWindow().clearFlags(1024);
        }
    }

    @SuppressLint({"SourceLockedOrientationActivity"})
    public static void c(Context context) {
        Activity activityF = f(context);
        if (activityF != null) {
            activityF.setRequestedOrientation(0);
        }
    }

    @SuppressLint({"SourceLockedOrientationActivity"})
    public static void d(Context context) {
        Activity activityF = f(context);
        if (activityF != null) {
            activityF.setRequestedOrientation(1);
        }
    }

    public static boolean e(Context context) {
        return Wrapper.unwrapContextIfNeed(KsAdSDKImpl.get().getContext()).getResources().getConfiguration().orientation != 2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.HashSet, java.util.Set] */
    /* JADX WARN: Type inference failed for: r3v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    @Nullable
    private static Activity f(Context context) {
        ?? hashSet = new HashSet();
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
}
