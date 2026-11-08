package com.appsflyer;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.appsflyer.j;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class c {

    static final class a {

        /* renamed from: ॱ, reason: contains not printable characters */
        static final c f168 = new c();
    }

    c() {
    }

    @Nullable
    /* renamed from: ॱ, reason: contains not printable characters */
    static Location m145(@NonNull Context context) {
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService("location");
            Location lastKnownLocation = m144(context, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}) ? locationManager.getLastKnownLocation("network") : null;
            Location lastKnownLocation2 = m144(context, new String[]{"android.permission.ACCESS_FINE_LOCATION"}) ? locationManager.getLastKnownLocation("gps") : null;
            if (lastKnownLocation2 == null && lastKnownLocation == null) {
                lastKnownLocation2 = null;
            } else {
                if (lastKnownLocation2 != null || lastKnownLocation == null) {
                    if (lastKnownLocation != null || lastKnownLocation2 == null) {
                        if (60000 < lastKnownLocation.getTime() - lastKnownLocation2.getTime()) {
                        }
                    }
                }
                lastKnownLocation2 = lastKnownLocation;
            }
            if (lastKnownLocation2 != null) {
                return lastKnownLocation2;
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: ˊ, reason: contains not printable characters */
    private static boolean m144(@NonNull Context context, @NonNull String[] strArr) {
        for (String str : strArr) {
            if (j.AnonymousClass1.m181(context, str)) {
                return true;
            }
        }
        return false;
    }
}
