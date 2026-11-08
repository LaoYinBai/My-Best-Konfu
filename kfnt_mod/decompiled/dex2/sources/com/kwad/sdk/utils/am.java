package com.kwad.sdk.utils;

import android.content.Context;
import android.content.res.Resources;
import com.kwad.sdk.api.core.ResContext;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class am {
    public static int a(Context context, String str) {
        Resources resourcesA = a(context);
        if (resourcesA == null) {
            resourcesA = context.getResources();
        }
        return resourcesA.getIdentifier(str, "drawable", context.getPackageName());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Resources a(Context context) {
        if (context == null) {
            return null;
        }
        Context applicationContext = context.getApplicationContext();
        boolean z = applicationContext instanceof ResContext;
        Context delegatedContext = applicationContext;
        if (z) {
            delegatedContext = ((ResContext) applicationContext).getDelegatedContext();
        }
        return delegatedContext.getResources();
    }
}
