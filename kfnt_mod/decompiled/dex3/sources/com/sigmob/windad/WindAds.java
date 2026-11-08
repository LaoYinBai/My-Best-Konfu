package com.sigmob.windad;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.base.services.b;
import com.sigmob.sdk.base.services.j;
import com.sigmob.sdk.common.Constants;
import com.sigmob.sdk.common.SDKConfig;
import com.sigmob.sdk.common.a;
import com.sigmob.sdk.common.a.c;
import com.sigmob.sdk.common.e.d;
import com.sigmob.sdk.common.e.f;
import com.sigmob.sdk.common.mta.BuriedPointManager;
import com.sigmob.sdk.common.mta.PointCategory;
import com.sigmob.sdk.common.mta.PointEntityCommon;
import com.sigmob.sdk.common.mta.PointType;
import com.sigmob.sdk.common.utils.g;
import com.sigmob.sdk.common.utils.p;
import com.sigmob.volley.s;
import com.sigmob.windad.common.AdapterClsUtil;
import com.sigmob.windad.common.PointEntityWindUtils;
import com.sigmob.windad.consent.ConsentStatus;
import com.sigmob.windad.consent.WindAdConsentInformation;
import com.umeng.message.MsgConstant;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class WindAds {
    public static final String AD_SCENE = "ad_scene";
    private static volatile WindAds a;
    private Context e;
    private boolean f;
    private WeakReference<Activity> h;
    private int i;
    private boolean b = true;
    private boolean c = false;
    private WindConsentStatus g = WindConsentStatus.UNKNOWN;
    private WindAgeRestrictedUserStatus j = WindAgeRestrictedUserStatus.WindAgeRestrictedStatusUnknown;
    private boolean k = true;
    private boolean m = true;
    private WindAdOptions d = null;
    private Handler l = new Handler(Looper.getMainLooper());

    /* renamed from: com.sigmob.windad.WindAds$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] a = new int[ConsentStatus.values().length];

        static {
            try {
                a[ConsentStatus.ACCEPT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[ConsentStatus.DENIED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[ConsentStatus.UNKNOWN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private WindAds() {
    }

    private static void a(Context context) {
        f.a(context);
    }

    private void b(Context context) {
        f.a().a(new d(SDKConfig.getGDPRRegionURL(), new d.a() { // from class: com.sigmob.windad.WindAds.1
            @Override // com.sigmob.volley.n.a
            public void onErrorResponse(s sVar) {
                WindAds.this.i();
            }

            @Override // com.sigmob.sdk.common.e.d.a
            public void onSuccess(JSONObject jSONObject) {
                if (jSONObject != null) {
                    try {
                        Boolean boolValueOf = Boolean.valueOf(jSONObject.getBoolean(Constants.IS_REQUEST_IN_EEA_OR_UNKNOWN));
                        if (boolValueOf != null) {
                            a.n().c(boolValueOf.booleanValue());
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
                WindAds.this.i();
            }
        }, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d() {
        try {
            j.a("LocationService", a.b() && !SDKConfig.sharedInstance().isDisable_up_location());
        } catch (Throwable th) {
            SigmobLog.e("updateLocationMonitor fail", th);
        }
    }

    private static void e() {
        try {
            j.a("AppInstallService", true);
        } catch (Throwable th) {
            SigmobLog.e("initAppInstallService fail", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void f() {
        try {
            j.a("WifiScanService", a.b() && SDKConfig.sharedInstance().isEnableWifiScanList());
        } catch (Throwable th) {
            SigmobLog.e("updateWifiScanService fail", th);
        }
    }

    private static void g() {
        try {
            j.a("DownloadService", true);
        } catch (Throwable th) {
            SigmobLog.e("initDownloadService fail", th);
        }
    }

    public static String getUserId() {
        return a.a();
    }

    public static String getVersion() {
        return Constants.SDK_VERSION;
    }

    private void h() {
        PointEntityCommon pointEntityCommon = new PointEntityCommon();
        pointEntityCommon.setAc_type(PointType.WIND_COMMON);
        pointEntityCommon.setCategory(PointCategory.AGGRE_INIT);
        pointEntityCommon.setIs_mediation(sharedAds().getOptions().getUseMediation() ? "1" : Constants.FAIL);
        pointEntityCommon.setAppinfo_switch(!SDKConfig.sharedInstance().isDisableUpAppInfo() ? "1,1" : "0,0");
        int i = 1;
        int i2 = a.n().an() != null ? 1 : 0;
        if (SDKConfig.sharedInstance().isDisable_up_location()) {
            i2 = 0;
            i = 0;
        }
        pointEntityCommon.setLocation_switch(i + com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP + i2);
        HashMap map = new HashMap();
        map.put("is_minor", this.b ? Constants.FAIL : "1");
        map.put("is_unpersonalized", this.m ? Constants.FAIL : "1");
        pointEntityCommon.setOptions(map);
        pointEntityCommon.commit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        BuriedPointManager.clearLogDB();
        g();
        e();
        a();
        a.n().d(getUserGDPRConsentStatus().a());
        h();
        SDKConfig.sharedInstance().setOnSDKUpdateListener(new SDKConfig.a() { // from class: com.sigmob.windad.WindAds.2
            @Override // com.sigmob.sdk.common.SDKConfig.a
            public void onUpdate() {
                WindAds.d();
                WindAds.f();
                b.a();
            }
        }).startUpdate();
        if (SDKConfig.sharedInstance().isEnable_report_crash()) {
            com.sigmob.sdk.common.c.a.a().b();
        }
        if (SDKConfig.sharedInstance().isEnable_debug_level()) {
            AdapterClsUtil.checkAdapterCls();
        }
        j();
    }

    private void j() {
        g.a(this.e).a();
    }

    private void k() {
        if (this.e != null) {
            try {
                a.n().c(p.a(this.e).getBoolean(Constants.USER_GDPR_REGION, false));
            } catch (Throwable unused) {
            }
        }
    }

    public static void requestPermission(Activity activity) throws ClassNotFoundException {
        if (activity == null || Build.VERSION.SDK_INT < 23) {
            return;
        }
        boolean zB = a.b(activity, MsgConstant.PERMISSION_READ_PHONE_STATE);
        boolean zB2 = a.b(activity, MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE);
        boolean zB3 = a.b(activity, "android.permission.ACCESS_FINE_LOCATION");
        if (zB && zB2 && zB3) {
            return;
        }
        activity.requestPermissions(new String[]{MsgConstant.PERMISSION_READ_PHONE_STATE, MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE, "android.permission.ACCESS_FINE_LOCATION"}, 0);
    }

    public static void setUserId(String str) {
        a.a(str);
    }

    public static WindAds sharedAds() {
        if (a == null) {
            synchronized (WindAds.class) {
                if (a == null) {
                    a = new WindAds();
                    a.setDebugEnable(true);
                }
            }
        }
        return a;
    }

    void a() {
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() { // from class: com.sigmob.windad.WindAds.3
            @Override // java.lang.Runnable
            public void run() {
                BuriedPointManager.getInstance().sendPoint();
            }
        }, 0L, SDKConfig.sharedInstance().getSendLogInterval() * 1000, TimeUnit.MILLISECONDS);
    }

    public boolean canCollectPersonalInformation() {
        return a.b();
    }

    public void debugDeviceID() {
        boolean z;
        try {
            String strY = a.n().y();
            if (TextUtils.isEmpty(strY)) {
                z = false;
            } else {
                SigmobLog.i(String.format("debug device Type: IMEI,  ID => %s", strY));
                z = true;
            }
            try {
                String strW = a.n().w();
                if (!TextUtils.isEmpty(strW)) {
                    SigmobLog.i(String.format("debug device Type: gaid, ID => %s", strW));
                    z = true;
                }
                if (!TextUtils.isEmpty(a.n().Y())) {
                    SigmobLog.i(String.format("debug device Type oaid, ID => %s", strW));
                    z = true;
                }
            } catch (Throwable unused) {
            }
        } catch (Throwable unused2) {
            z = false;
        }
        if (z) {
            return;
        }
        SigmobLog.e(String.format("can't find any can be used debug valid Device Type", new Object[0]));
    }

    public Activity getActivity() {
        if (this.h != null) {
            return this.h.get();
        }
        return null;
    }

    public WindAgeRestrictedUserStatus getAgeRestrictedStatus() {
        try {
            if (this.j == WindAgeRestrictedUserStatus.WindAgeRestrictedStatusUnknown && this.e != null) {
                int i = p.a(this.e).getInt(Constants.WIND_AGERESTRICTED_STATUS, WindAgeRestrictedUserStatus.WindAgeRestrictedStatusUnknown.getValue());
                this.j = i == WindAgeRestrictedUserStatus.WindAgeRestrictedStatusNO.getValue() ? WindAgeRestrictedUserStatus.WindAgeRestrictedStatusNO : i == WindAgeRestrictedUserStatus.WindAgeRestrictedStatusYES.getValue() ? WindAgeRestrictedUserStatus.WindAgeRestrictedStatusYES : WindAgeRestrictedUserStatus.WindAgeRestrictedStatusUnknown;
            }
        } catch (Throwable unused) {
        }
        return this.j;
    }

    public Context getContext() {
        return this.e.getApplicationContext();
    }

    public Handler getHandler() {
        if (this.l == null) {
            this.l = new Handler(Looper.getMainLooper());
        }
        return this.l;
    }

    public WindAdOptions getOptions() {
        return this.d;
    }

    public int getUserAge() {
        try {
            if (this.i == 0 && this.e != null) {
                this.i = p.a(this.e).getInt(Constants.WIND_CONSENT_STATUS, 0);
            }
        } catch (Throwable unused) {
        }
        return this.i;
    }

    public WindConsentStatus getUserGDPRConsentStatus() {
        if (this.g == WindConsentStatus.UNKNOWN && this.e != null) {
            try {
                String string = p.a(this.e).getString(Constants.WIND_CONSENT_STATUS, WindConsentStatus.UNKNOWN.a());
                this.g = string.equals(WindConsentStatus.ACCEPT.a()) ? WindConsentStatus.ACCEPT : string.equals(WindConsentStatus.DENIED.a()) ? WindConsentStatus.DENIED : WindConsentStatus.UNKNOWN;
            } catch (Throwable unused) {
            }
        }
        return this.g;
    }

    public String getWindUid() {
        try {
            return a.n().ac();
        } catch (Exception e) {
            e.printStackTrace();
            return "Please initialize the SDK properly first";
        }
    }

    public boolean isAdult() {
        return this.b;
    }

    public boolean isDebugEnable() {
        return this.f;
    }

    public boolean isInit() {
        return this.c;
    }

    public boolean isPersonalizedAdvertisingOn() {
        return this.m;
    }

    public void loadConsentStatus() {
        WindConsentStatus windConsentStatus;
        try {
            getUserGDPRConsentStatus();
            k();
            if (this.g == WindConsentStatus.UNKNOWN) {
                switch (AnonymousClass4.a[WindAdConsentInformation.getInstance(this.e).getConsentStatus().ordinal()]) {
                    case 1:
                        windConsentStatus = WindConsentStatus.ACCEPT;
                        break;
                    case 2:
                        windConsentStatus = WindConsentStatus.DENIED;
                        break;
                    case 3:
                        windConsentStatus = WindConsentStatus.UNKNOWN;
                        break;
                    default:
                        return;
                }
                setUserGDPRConsentStatus(windConsentStatus);
            }
        } catch (Throwable unused) {
        }
    }

    public void setActivity(Activity activity) {
        this.h = new WeakReference<>(activity);
    }

    public void setAdult(boolean z) {
        this.b = z;
        if (this.c) {
            a.n().d(this.b);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x000f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setDebugEnable(boolean r2) {
        /*
            r1 = this;
            r1.f = r2
            java.lang.Boolean r0 = com.sigmob.sdk.common.Constants.IS_TEST
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L15
            if (r2 == 0) goto Lf
            java.util.logging.Level r2 = java.util.logging.Level.FINE
            goto L11
        Lf:
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
        L11:
            com.sigmob.logger.SigmobLog.setSdkHandlerLevel(r2)
            goto L1a
        L15:
            if (r2 == 0) goto Lf
            java.util.logging.Level r2 = java.util.logging.Level.INFO
            goto L11
        L1a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sigmob.windad.WindAds.setDebugEnable(boolean):void");
    }

    public void setIsAgeRestrictedUser(WindAgeRestrictedUserStatus windAgeRestrictedUserStatus) {
        this.j = windAgeRestrictedUserStatus;
        if (!this.c || a.n() == null) {
            return;
        }
        a.n().a(this.j.getValue());
    }

    public void setPersonalizedAdvertisingOn(boolean z) {
        this.m = z;
        if (this.c) {
            a.n().e(this.m);
        }
    }

    public void setUserAge(int i) {
        this.i = i;
        if (!this.c || a.n() == null) {
            return;
        }
        a.n().c(i);
    }

    public void setUserGDPRConsentStatus(WindConsentStatus windConsentStatus) {
        this.g = windConsentStatus;
        if (!this.c || a.n() == null) {
            return;
        }
        a.n().b(windConsentStatus.a());
        a.n().d(getUserGDPRConsentStatus().a());
    }

    public void setUserGDPRDialogRegion(boolean z) {
        this.k = z;
        if (!this.c || a.n() == null) {
            return;
        }
        a.n().a(z);
        a.n().d(getUserGDPRConsentStatus().a());
    }

    public boolean startWithOptions(Context context, WindAdOptions windAdOptions) {
        String str;
        if (context == null) {
            str = "ApplicationContext is null ";
        } else {
            if (windAdOptions != null && !TextUtils.isEmpty(windAdOptions.getAppId())) {
                if (this.c || Build.VERSION.SDK_INT < 18) {
                    SigmobLog.i("already startWithOptions");
                    return true;
                }
                try {
                    this.d = windAdOptions;
                    this.e = context.getApplicationContext();
                    com.sigmob.sdk.common.utils.d.a(context.getApplicationContext(), Constants.SDK_FOLDER);
                    a.n().a(context.getApplicationContext(), windAdOptions.getAppId(), windAdOptions.getAppKey());
                    c.a(context.getApplicationContext());
                    loadConsentStatus();
                    a.n().b(this.g.a());
                    a.n().c(getUserAge());
                    a.n().a(getAgeRestrictedStatus().getValue());
                    a.n().d(this.b);
                    a.n().e(this.m);
                    a(context.getApplicationContext());
                    b(context);
                    WindAdLifecycleManager.initialize((Application) context.getApplicationContext());
                    this.c = true;
                    return true;
                } catch (Throwable th) {
                    SigmobLog.e("startWithOptions", th);
                    try {
                        PointEntityWindUtils.WindError("error", null, null, WindAdError.ERROR_SIGMOB_INIT_FAIL.getErrorCode(), th.getMessage(), null);
                    } catch (Throwable th2) {
                        SigmobLog.e("startWithOptions", th2);
                    }
                    return false;
                }
            }
            str = "invalid Options " + windAdOptions;
        }
        SigmobLog.e(str);
        return false;
    }
}
