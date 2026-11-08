package com.sigmob.sdk.base.services;

import android.annotation.SuppressLint;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.base.services.j;
import com.sigmob.sdk.common.mta.PointCategory;
import com.ss.android.downloadlib.constants.DownloadConstants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class e implements j.a {
    private static LocationManager b;
    private k d = k.STOP;
    private static final LocationListener a = new LocationListener() { // from class: com.sigmob.sdk.base.services.e.1
        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            if (com.sigmob.sdk.common.a.n() == null) {
                return;
            }
            com.sigmob.sdk.common.a.n().a(location);
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String str, int i, Bundle bundle) {
        }
    };
    private static String c = PointCategory.NETWORK;

    e() {
        LocationManager locationManagerA;
        if (com.sigmob.sdk.common.a.n() == null || (locationManagerA = a()) == null) {
            return;
        }
        Criteria criteria = new Criteria();
        criteria.setAccuracy(2);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(false);
        criteria.setPowerRequirement(1);
        c = locationManagerA.getBestProvider(criteria, false);
    }

    static LocationManager a() {
        if (b == null) {
            synchronized (e.class) {
                if (b == null) {
                    b = com.sigmob.sdk.common.a.n().am();
                }
            }
        }
        return b;
    }

    @SuppressLint({"MissingPermission"})
    private void d() {
        try {
            if (b != null) {
                b.removeUpdates(a);
            }
            b = null;
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
        }
    }

    @SuppressLint({"MissingPermission"})
    private void e() {
        try {
            LocationManager locationManagerA = a();
            if (locationManagerA == null || c == null || !locationManagerA.isProviderEnabled(c)) {
                return;
            }
            SigmobLog.d("private :use_location ");
            locationManagerA.requestLocationUpdates(c, DownloadConstants.DEFAULT_NEXT_INSTALL_MIN_INTERVAL, 10.0f, a);
            this.d = k.RUNNING;
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
        }
    }

    @Override // com.sigmob.sdk.base.services.j.a
    public boolean b() {
        if (this.d == k.RUNNING) {
            return false;
        }
        e();
        return true;
    }

    @Override // com.sigmob.sdk.base.services.j.a
    public void c() {
        if (this.d == k.RUNNING) {
            d();
            this.d = k.STOP;
        }
    }
}
