package com.facebook.stetho.common.android;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import javax.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class ViewUtil {
    private ViewUtil() {
    }

    @Nullable
    static Activity tryGetActivity(View view) {
        if (view == null) {
            return null;
        }
        Activity activityTryGetActivity = tryGetActivity(view.getContext());
        if (activityTryGetActivity != null) {
            return activityTryGetActivity;
        }
        Object parent = view.getParent();
        if (parent instanceof View) {
            return tryGetActivity((View) parent);
        }
        return null;
    }

    @Nullable
    private static Activity tryGetActivity(Context context) {
        while (context != null) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            if (!(context instanceof ContextWrapper)) {
                return null;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }
}
