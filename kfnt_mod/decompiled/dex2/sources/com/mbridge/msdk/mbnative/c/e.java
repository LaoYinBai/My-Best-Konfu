package com.mbridge.msdk.mbnative.c;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import androidx.core.view.ViewCompat;

/* compiled from: Views.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class e {
    private static View a(View view) {
        if (view == null) {
            return null;
        }
        try {
            ViewCompat.isAttachedToWindow(view);
        } catch (Throwable unused) {
        }
        try {
            View rootView = view.getRootView();
            if (rootView == null) {
                return null;
            }
            View viewFindViewById = rootView.findViewById(R.id.content);
            return viewFindViewById != null ? viewFindViewById : rootView;
        } catch (Throwable unused2) {
            return null;
        }
    }

    public static View a(Context context, View view) {
        View viewFindViewById = null;
        if (context != null && (context instanceof Activity)) {
            viewFindViewById = ((Activity) context).getWindow().getDecorView().findViewById(R.id.content);
        }
        return viewFindViewById != null ? viewFindViewById : a(view);
    }
}
