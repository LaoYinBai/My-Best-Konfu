package com.kwad.sdk.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import androidx.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ar {
    private static String a = "plugin.signature";

    @Nullable
    @SuppressLint({"PackageManagerGetSignatures"})
    public static Signature[] a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures;
        } catch (PackageManager.NameNotFoundException e) {
            com.kwad.sdk.core.d.a.d(a, "Can not get signature, error = " + e.getLocalizedMessage());
            com.kwad.sdk.core.d.a.a(a, e);
            return null;
        }
    }

    public static String b(Context context) {
        try {
            Signature[] signatureArrA = a(context);
            if (signatureArrA != null && signatureArrA.length >= 1) {
                return aa.a(signatureArrA[0].toByteArray());
            }
            return "";
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.a(a, e);
            return "";
        }
    }
}
