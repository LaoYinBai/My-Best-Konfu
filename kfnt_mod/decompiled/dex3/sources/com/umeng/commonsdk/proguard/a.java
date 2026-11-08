package com.umeng.commonsdk.proguard;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import com.sigmob.sdk.common.mta.PointCategory;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.commonsdk.utils.UMUtils;
import com.umeng.message.MsgConstant;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private static final String a = "UMSysLocation";
    private static final int c = 10000;
    private LocationManager b;
    private Context d;
    private c e;

    private a() {
    }

    public a(Context context) throws Throwable {
        if (context == null) {
            MLog.e("Context参数不能为null");
        } else {
            this.d = context.getApplicationContext();
            this.b = (LocationManager) context.getApplicationContext().getSystemService(MsgConstant.KEY_LOCATION_PARAMS);
        }
    }

    public synchronized void a(c cVar) {
        boolean zIsProviderEnabled;
        boolean zIsProviderEnabled2;
        Location lastKnownLocation;
        ULog.i(a, "getSystemLocation");
        if (cVar != null && this.d != null) {
            this.e = cVar;
            boolean zCheckPermission = UMUtils.checkPermission(this.d, "android.permission.ACCESS_COARSE_LOCATION");
            boolean zCheckPermission2 = UMUtils.checkPermission(this.d, "android.permission.ACCESS_FINE_LOCATION");
            if (!zCheckPermission && !zCheckPermission2) {
                if (this.e != null) {
                    this.e.a(null);
                }
                return;
            }
            try {
                if (this.b != null) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        zIsProviderEnabled = this.b.isProviderEnabled("gps");
                        zIsProviderEnabled2 = this.b.isProviderEnabled(PointCategory.NETWORK);
                    } else {
                        zIsProviderEnabled = zCheckPermission2 ? this.b.isProviderEnabled("gps") : false;
                        zIsProviderEnabled2 = zCheckPermission ? this.b.isProviderEnabled(PointCategory.NETWORK) : false;
                    }
                    if (zIsProviderEnabled || zIsProviderEnabled2) {
                        ULog.i(a, "getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)");
                        if (zCheckPermission2) {
                            lastKnownLocation = this.b.getLastKnownLocation("passive");
                        } else if (zCheckPermission) {
                            lastKnownLocation = this.b.getLastKnownLocation(PointCategory.NETWORK);
                        }
                        this.e.a(lastKnownLocation);
                    }
                    lastKnownLocation = null;
                    this.e.a(lastKnownLocation);
                }
            } catch (Throwable th) {
                ULog.i(a, "e is " + th);
                if (cVar != null) {
                    try {
                        cVar.a(null);
                    } catch (Throwable th2) {
                        UMCrashManager.reportCrash(this.d, th2);
                    }
                }
                UMCrashManager.reportCrash(this.d, th);
            }
        }
    }

    public synchronized void a() {
        ULog.i(a, "destroy");
        try {
            if (this.b != null) {
                this.b = null;
            }
        } catch (Throwable th) {
            UMCrashManager.reportCrash(this.d, th);
        }
    }
}
