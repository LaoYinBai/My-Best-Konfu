package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.bytedance.msdk.adapter.util.Logger;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e {

    /* renamed from: a, reason: collision with root package name */
    private static long f1991a = 1800000;

    /* renamed from: b, reason: collision with root package name */
    private static Handler f1992b = new Handler(Looper.getMainLooper());

    /* renamed from: c, reason: collision with root package name */
    private static ExecutorService f1993c = Executors.newSingleThreadExecutor();

    static class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f1994a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ LocationManager f1995b;

        a(Context context, LocationManager locationManager) {
            this.f1994a = context;
            this.f1995b = locationManager;
        }

        @Override // java.lang.Runnable
        public void run() {
            e.b(this.f1994a, this.f1995b);
        }
    }

    static class b implements LocationListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f1996a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ LocationManager f1997b;

        b(Context context, LocationManager locationManager) {
            this.f1996a = context;
            this.f1997b = locationManager;
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            if (location != null && e.b(location)) {
                e.b(this.f1996a, location);
            }
            e.b(this.f1997b, this);
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
    }

    static class c implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ LocationManager f1998a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ LocationListener f1999b;

        c(LocationManager locationManager, LocationListener locationListener) {
            this.f1998a = locationManager;
            this.f1999b = locationListener;
        }

        @Override // java.lang.Runnable
        public void run() {
            e.b(this.f1998a, this.f1999b);
        }
    }

    private static class d implements Callable<Location> {

        /* renamed from: a, reason: collision with root package name */
        private LocationManager f2000a;

        /* renamed from: b, reason: collision with root package name */
        private String f2001b;

        public d(LocationManager locationManager, String str) {
            this.f2000a = locationManager;
            this.f2001b = str;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public Location call() throws Exception {
            long jCurrentTimeMillis = System.currentTimeMillis();
            Location lastKnownLocation = this.f2000a.getLastKnownLocation(this.f2001b);
            Logger.d("AdLocationUtils", "location:" + lastKnownLocation + ",getLastKnownLocation use time :" + (System.currentTimeMillis() - jCurrentTimeMillis));
            return lastKnownLocation;
        }
    }

    @Nullable
    public static bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.d b(Context context) {
        if (!bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b.D().q().isCanUseLocation()) {
            return null;
        }
        Context contextD = context == null ? bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d() : context.getApplicationContext();
        if (contextD == null) {
            return null;
        }
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.d dVarA = a(contextD);
        if (!c(contextD)) {
            return dVarA;
        }
        int iA = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_case1.c.a(contextD, "android.permission.ACCESS_FINE_LOCATION");
        int iA2 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_case1.c.a(contextD, "android.permission.ACCESS_COARSE_LOCATION");
        if (iA == 0 || iA2 == 0) {
            return d(contextD);
        }
        return null;
    }

    private static boolean c(Context context) {
        long jA = u.a((String) null, context).a("lbstime", -1L);
        return jA == -1 || System.currentTimeMillis() - jA > f1991a;
    }

    private static bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.d d(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.d dVar = null;
        if (locationManager != null) {
            try {
                Location locationA = a(locationManager);
                if (locationA != null && b(locationA)) {
                    b(context, locationA);
                    dVar = new bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.d((float) locationA.getLatitude(), (float) locationA.getLongitude());
                }
                if (Looper.myLooper() != Looper.getMainLooper()) {
                    new Handler(Looper.getMainLooper()).post(new a(context, locationManager));
                } else {
                    b(context, locationManager);
                }
            } catch (Throwable th) {
                if (Logger.isDebug()) {
                    th.printStackTrace();
                }
            }
        }
        return dVar;
    }

    @Nullable
    private static bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.d a(Context context) {
        u uVarA = u.a((String) null, context);
        float fA = uVarA.a("latitude", -1.0f);
        float fA2 = uVarA.a("longitude", -1.0f);
        if (fA == -1.0f || fA2 == -1.0f) {
            return null;
        }
        return new bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.d(fA, fA2);
    }

    private static Location a(LocationManager locationManager) {
        Location locationA = a(locationManager, "gps");
        if (locationA == null) {
            locationA = a(locationManager, "network");
        }
        return locationA == null ? a(locationManager, "passive") : locationA;
    }

    private static Location a(LocationManager locationManager, String str) {
        try {
            FutureTask futureTask = new FutureTask(new d(locationManager, str));
            f1993c.execute(futureTask);
            Location location = (Location) futureTask.get(1L, TimeUnit.SECONDS);
            Logger.d("AdLocationUtils", "location:" + location);
            return location;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static String b(LocationManager locationManager) {
        if (locationManager.isProviderEnabled("gps")) {
            return "gps";
        }
        if (locationManager.isProviderEnabled("network")) {
            return "network";
        }
        if (locationManager.isProviderEnabled("passive")) {
            return "passive";
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context, LocationManager locationManager) {
        if (context == null || locationManager == null) {
            return;
        }
        b bVar = new b(context, locationManager);
        try {
            String strB = b(locationManager);
            if (TextUtils.isEmpty(strB)) {
                return;
            }
            locationManager.requestSingleUpdate(strB, bVar, Looper.getMainLooper());
            f1992b.postDelayed(new c(locationManager, bVar), com.bytedance.b.a.a.c.g.e);
        } catch (Throwable th) {
            if (Logger.isDebug()) {
                th.printStackTrace();
            }
            b(locationManager, bVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(LocationManager locationManager, LocationListener locationListener) {
        if (locationManager == null || locationListener == null) {
            return;
        }
        try {
            locationManager.removeUpdates(locationListener);
        } catch (Throwable th) {
            if (Logger.isDebug()) {
                th.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context, Location location) {
        if (b(location)) {
            u uVarA = u.a((String) null, context);
            uVarA.b("latitude", (float) location.getLatitude());
            uVarA.b("longitude", (float) location.getLongitude());
            uVarA.b("lbstime", System.currentTimeMillis());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(Location location) {
        return (location.getLatitude() == 0.0d || location.getLongitude() == 0.0d) ? false : true;
    }
}
