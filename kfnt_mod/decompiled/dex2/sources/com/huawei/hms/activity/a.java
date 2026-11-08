package com.huawei.hms.activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import com.huawei.hms.activity.internal.BusResponseCallback;
import com.huawei.hms.activity.internal.BusResponseResult;
import com.huawei.hms.activity.internal.ForegroundBusResponseMgr;
import com.huawei.hms.activity.internal.ForegroundInnerHeader;
import com.huawei.hms.adapter.AvailableAdapter;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.common.internal.RequestHeader;
import com.huawei.hms.common.internal.ResponseHeader;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hms.support.hianalytics.HiAnalyticsUtil;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.HMSPackageManager;
import com.huawei.hms.utils.JsonUtil;
import com.huawei.hms.utils.Util;
import java.lang.ref.WeakReference;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a implements IBridgeActivityDelegate {
    private RequestHeader a;
    private String b;
    private ForegroundInnerHeader c = new ForegroundInnerHeader();
    private ResponseHeader d;
    private WeakReference<Activity> e;

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public int getRequestCode() {
        return 431057;
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeConfigurationChanged() {
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onKeyUp(int i, KeyEvent keyEvent) {
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityCreate(Activity activity) {
        this.e = new WeakReference<>(activity);
        Intent intent = activity.getIntent();
        String stringExtra = intent.getStringExtra("HMS_FOREGROUND_REQ_HEADER");
        this.a = new RequestHeader();
        if (!this.a.fromJson(stringExtra)) {
            a(0, "header is invalid");
            return;
        }
        this.b = intent.getStringExtra("HMS_FOREGROUND_REQ_BODY");
        this.c.fromJson(intent.getStringExtra("HMS_FOREGROUND_REQ_INNER"));
        if (this.c == null) {
            a(0, "inner header is invalid");
        } else if (TextUtils.isEmpty(this.a.getApiName())) {
            a(0, "action is invalid");
        } else {
            e();
            c();
        }
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityDestroy() {
        g();
        this.e = null;
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public boolean onBridgeActivityResult(int i, int i2, Intent intent) throws IllegalArgumentException {
        if (i != 431057) {
            return false;
        }
        if (intent != null && intent.hasExtra("HMS_FOREGROUND_RESP_HEADER")) {
            String stringExtra = intent.getStringExtra("HMS_FOREGROUND_RESP_HEADER");
            this.d = new ResponseHeader();
            JsonUtil.jsonToEntity(stringExtra, this.d);
        }
        h();
        BusResponseCallback busResponseCallbackA = a(this.c.getResponseCallbackKey());
        if (busResponseCallbackA == null) {
            a(i2, intent);
            return true;
        }
        BusResponseResult busResponseResultSucceedReturn = busResponseCallbackA.succeedReturn(this.e.get(), i2, intent);
        if (busResponseResultSucceedReturn == null) {
            a(i2, intent);
            return true;
        }
        a(busResponseResultSucceedReturn.getCode(), busResponseResultSucceedReturn.getIntent());
        return true;
    }

    private BusResponseCallback a(String str) {
        return ForegroundBusResponseMgr.getInstance().get(str);
    }

    private void a(int i, Intent intent) {
        HMSLog.i("ForegroundBusDelegate", "succeedReturn");
        Activity activityB = b();
        if (activityB == null) {
            return;
        }
        activityB.setResult(i, intent);
        a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, String str) {
        BusResponseResult busResponseResultInnerError;
        HMSLog.e("ForegroundBusDelegate", str);
        Activity activityB = b();
        if (activityB == null) {
            return;
        }
        BusResponseCallback busResponseCallbackA = a(this.c.getResponseCallbackKey());
        if (busResponseCallbackA == null || (busResponseResultInnerError = busResponseCallbackA.innerError(this.e.get(), i, str)) == null) {
            activityB.setResult(0);
        } else {
            activityB.setResult(busResponseResultInnerError.getCode(), busResponseResultInnerError.getIntent());
        }
        a();
    }

    private void a() {
        Activity activityB = b();
        if (activityB == null || activityB.isFinishing()) {
            return;
        }
        activityB.finish();
    }

    private Activity b() {
        if (this.e == null) {
            return null;
        }
        return this.e.get();
    }

    private void c() {
        if (b() == null) {
            HMSLog.e("ForegroundBusDelegate", "checkMinVersion failed, activity must not be null.");
            return;
        }
        if (!Util.isAvailableLibExist(b().getApplicationContext())) {
            if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(b().getApplicationContext(), this.c.getApkVersion()) != 0) {
                HMSLog.e("ForegroundBusDelegate", "checkMinVersion failed, and no available lib exists.");
                return;
            } else {
                d();
                return;
            }
        }
        C0005a c0005a = new C0005a();
        AvailableAdapter availableAdapter = new AvailableAdapter(this.c.getApkVersion());
        int iIsHuaweiMobileServicesAvailable = availableAdapter.isHuaweiMobileServicesAvailable(b());
        if (iIsHuaweiMobileServicesAvailable == 0) {
            c0005a.onComplete(iIsHuaweiMobileServicesAvailable);
        } else if (availableAdapter.isUserResolvableError(iIsHuaweiMobileServicesAvailable)) {
            a(b(), availableAdapter, c0005a);
        } else {
            c0005a.onComplete(iIsHuaweiMobileServicesAvailable);
        }
    }

    /* renamed from: com.huawei.hms.activity.a$a, reason: collision with other inner class name */
    private class C0005a implements AvailableAdapter.AvailableCallBack {
        private C0005a() {
        }

        @Override // com.huawei.hms.adapter.AvailableAdapter.AvailableCallBack
        public void onComplete(int i) {
            if (i == 0) {
                a.this.d();
            } else {
                HMSLog.i("ForegroundBusDelegate", "version check failed");
                a.this.a(0, "apk version is invalid");
            }
        }
    }

    private static void a(Activity activity, AvailableAdapter availableAdapter, AvailableAdapter.AvailableCallBack availableCallBack) {
        if (activity == null) {
            HMSLog.i("ForegroundBusDelegate", "null activity, could not start resolution intent");
        }
        availableAdapter.startResolution(activity, availableCallBack);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        HMSLog.i("ForegroundBusDelegate", "startApkHubActivity");
        Activity activityB = b();
        if (activityB == null) {
            HMSLog.e("ForegroundBusDelegate", "startApkHubActivity but activity is null");
            return;
        }
        String hMSPackageName = HMSPackageManager.getInstance(activityB.getApplicationContext()).getHMSPackageName();
        Intent intent = new Intent(this.c.getAction());
        intent.putExtra("HMS_FOREGROUND_REQ_BODY", this.b);
        intent.setPackage(hMSPackageName);
        intent.setClassName(hMSPackageName, "com.huawei.hms.core.activity.UiJumpActivity");
        intent.putExtra("HMS_FOREGROUND_REQ_HEADER", this.a.toJson());
        intent.putExtra("intent.extra.hms.core.DELEGATE_NAME", "com.huawei.hms.core.activity.ForegroundBus");
        try {
            f();
            activityB.startActivityForResult(intent, 431057);
        } catch (ActivityNotFoundException e) {
            HMSLog.e("ForegroundBusDelegate", "Launch sign in Intent failed. hms is probably being updated：", e);
            a(0, "launch bus intent failed");
        }
    }

    private void e() {
        Map<String, String> mapFromForegroundRequestHeader = HiAnalyticsUtil.getInstance().getMapFromForegroundRequestHeader(this.a);
        mapFromForegroundRequestHeader.put(HiAnalyticsConstant.HaKey.BI_KEY_DIRECTION, HiAnalyticsConstant.Direction.REQUEST);
        mapFromForegroundRequestHeader.put(HiAnalyticsConstant.HaKey.BI_KEY_VERSION, HiAnalyticsUtil.versionCodeToName(String.valueOf(this.a.getKitSdkVersion())));
        if (b() != null) {
            HiAnalyticsUtil.getInstance().onNewEvent(b().getApplicationContext(), HiAnalyticsConstant.HMS_SDK_BASE_ACTIVITY_STARTED, mapFromForegroundRequestHeader);
        }
    }

    private void f() {
        Map<String, String> mapFromForegroundRequestHeader = HiAnalyticsUtil.getInstance().getMapFromForegroundRequestHeader(this.a);
        mapFromForegroundRequestHeader.put(HiAnalyticsConstant.HaKey.BI_KEY_DIRECTION, HiAnalyticsConstant.Direction.REQUEST);
        mapFromForegroundRequestHeader.put(HiAnalyticsConstant.HaKey.BI_KEY_VERSION, HiAnalyticsUtil.versionCodeToName(String.valueOf(this.a.getKitSdkVersion())));
        if (b() != null) {
            HiAnalyticsUtil.getInstance().onNewEvent(b().getApplicationContext(), HiAnalyticsConstant.HMS_SDK_BASE_START_CORE_ACTIVITY, mapFromForegroundRequestHeader);
        }
    }

    private void g() {
        if (this.a != null) {
            Map<String, String> mapFromForegroundRequestHeader = HiAnalyticsUtil.getInstance().getMapFromForegroundRequestHeader(this.a);
            mapFromForegroundRequestHeader.put(HiAnalyticsConstant.HaKey.BI_KEY_DIRECTION, HiAnalyticsConstant.Direction.RESPONSE);
            mapFromForegroundRequestHeader.put(HiAnalyticsConstant.HaKey.BI_KEY_VERSION, HiAnalyticsUtil.versionCodeToName(String.valueOf(this.a.getKitSdkVersion())));
            if (this.d != null) {
                mapFromForegroundRequestHeader.put(HiAnalyticsConstant.HaKey.BI_KEY_RESULT, String.valueOf(this.d.getStatusCode()));
                mapFromForegroundRequestHeader.put("result", String.valueOf(this.d.getErrorCode()));
            }
            if (b() != null) {
                HiAnalyticsUtil.getInstance().onNewEvent(b().getApplicationContext(), HiAnalyticsConstant.HMS_SDK_BASE_ACTIVITY_STARTED, mapFromForegroundRequestHeader);
            }
        }
    }

    private void h() {
        Map<String, String> mapFromForegroundRequestHeader = HiAnalyticsUtil.getInstance().getMapFromForegroundRequestHeader(this.a);
        mapFromForegroundRequestHeader.put(HiAnalyticsConstant.HaKey.BI_KEY_DIRECTION, HiAnalyticsConstant.Direction.RESPONSE);
        mapFromForegroundRequestHeader.put(HiAnalyticsConstant.HaKey.BI_KEY_VERSION, HiAnalyticsUtil.versionCodeToName(String.valueOf(this.a.getKitSdkVersion())));
        if (this.d != null) {
            mapFromForegroundRequestHeader.put(HiAnalyticsConstant.HaKey.BI_KEY_RESULT, String.valueOf(this.d.getStatusCode()));
            mapFromForegroundRequestHeader.put("result", String.valueOf(this.d.getErrorCode()));
        }
        if (b() != null) {
            HiAnalyticsUtil.getInstance().onNewEvent(b().getApplicationContext(), HiAnalyticsConstant.HMS_SDK_BASE_START_CORE_ACTIVITY, mapFromForegroundRequestHeader);
        }
    }
}
