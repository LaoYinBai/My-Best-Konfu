package com.kwad.sdk.utils;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import java.lang.reflect.Field;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class n {
    private static void a(Context context, View view) {
        InputMethodManager inputMethodManager;
        if (context == null || view == null || Build.VERSION.SDK_INT >= 29 || (inputMethodManager = (InputMethodManager) context.getSystemService("input_method")) == null) {
            return;
        }
        for (String str : new String[]{"mCurRootView", "mServedView", "mNextServedView"}) {
            try {
                Field declaredField = inputMethodManager.getClass().getDeclaredField(str);
                if (!declaredField.isAccessible()) {
                    declaredField.setAccessible(true);
                }
                Object obj = declaredField.get(inputMethodManager);
                if (!(obj instanceof View)) {
                    continue;
                } else if (!context.equals(((View) obj).getContext())) {
                    return;
                } else {
                    declaredField.set(inputMethodManager, null);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private static void a(Context context, Window window) {
        if (window == null) {
            return;
        }
        View decorView = window.getDecorView();
        a(decorView);
        a(context, decorView);
    }

    private static synchronized void a(View view) {
        if (view == null) {
            return;
        }
        if (view instanceof WebView) {
            try {
                ((WebView) view).destroy();
            } catch (Throwable unused) {
            }
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                a(viewGroup.getChildAt(i));
            }
        }
    }

    public static void a(com.kwad.sdk.h.a aVar) {
        if (aVar == null) {
            return;
        }
        a(aVar.getActivity(), aVar.getWindow());
    }

    public static void a(com.kwad.sdk.h.b bVar) {
        if (bVar == null) {
            return;
        }
        a(bVar.getActivity(), bVar.getWindow());
    }

    public static void a(com.kwad.sdk.h.d dVar) {
        if (dVar == null) {
            return;
        }
        View view = dVar.getView();
        a(dVar.getView());
        a(dVar.getContext(), view);
    }
}
