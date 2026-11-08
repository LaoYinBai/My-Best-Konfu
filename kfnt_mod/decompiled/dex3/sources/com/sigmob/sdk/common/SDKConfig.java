package com.sigmob.sdk.common;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.common.e.f;
import com.sigmob.sdk.common.e.j;
import com.sigmob.sdk.common.e.l;
import com.sigmob.sdk.common.e.m;
import com.sigmob.sdk.common.models.ssp.pb.Android;
import com.sigmob.sdk.common.models.ssp.pb.Common;
import com.sigmob.sdk.common.models.ssp.pb.CommonEndpointsConfig;
import com.sigmob.sdk.common.models.ssp.pb.DialogSetting;
import com.sigmob.sdk.common.models.ssp.pb.NativeConfig;
import com.sigmob.sdk.common.models.ssp.pb.RvConfig;
import com.sigmob.sdk.common.models.ssp.pb.RvEndpointsConfig;
import com.sigmob.sdk.common.models.ssp.pb.SdkConfigResponse;
import com.sigmob.sdk.common.models.ssp.pb.SdkConfigV2;
import com.sigmob.sdk.common.utils.d;
import com.sigmob.sdk.common.utils.p;
import com.sigmob.volley.s;
import com.umeng.commonsdk.proguard.b;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SDKConfig {
    private static SDKConfig a;
    private static boolean b;
    private final Handler c;
    private final Runnable d;
    private final String e;
    private boolean f;
    private long g;
    private SdkConfigV2 h;
    private SdkConfigV2 i;
    private a j;

    public interface a {
        void onUpdate();
    }

    private SDKConfig() throws Throwable {
        StringBuilder sb = new StringBuilder();
        sb.append("3.5.1|");
        sb.append(Constants.IS_TEST.booleanValue() ? "1" : Constants.FAIL);
        this.e = sb.toString();
        this.g = 0L;
        this.h = null;
        this.i = null;
        this.c = new Handler(Looper.getMainLooper());
        this.d = new Runnable() { // from class: com.sigmob.sdk.common.SDKConfig.1
            @Override // java.lang.Runnable
            public void run() {
                SDKConfig.this.c();
            }
        };
        this.f = true;
        a();
        b();
    }

    private void a() {
        String str;
        if (this.i == null) {
            SdkConfigV2.Builder builder = new SdkConfigV2.Builder();
            Common.Builder builder2 = new Common.Builder();
            Android.Builder builder3 = new Android.Builder();
            CommonEndpointsConfig.Builder builder4 = new CommonEndpointsConfig.Builder();
            if (Constants.IS_TEST.booleanValue()) {
                builder4.log("https://dctest.sigmob.cn/log");
                builder4.ads("https://adstage.sigmob.cn/ad/v3");
                builder4.hb_ads("https://adstage.sigmob.cn/hb/ad");
                str = "https://adstage.sigmob.cn/strategy/v3";
            } else {
                builder4.log("https://dc.sigmob.cn/log");
                builder4.ads("https://adservice.sigmob.cn/ad/v3");
                builder4.hb_ads("https://adservice.sigmob.cn/hb/ad");
                str = "https://adservice.sigmob.cn/strategy/v3";
            }
            builder4.strategy(str);
            builder2.endpoints(builder4.build());
            RvConfig.Builder builder5 = new RvConfig.Builder();
            RvEndpointsConfig.Builder builder6 = new RvEndpointsConfig.Builder();
            builder5.cacheTop(4);
            builder5.finished(Float.valueOf(1.0f));
            builder5.showClose(9999);
            builder5.ifMute(0);
            builder5.loadTimeout(45);
            builder5.loadExpired(7200);
            builder5.endpoints(builder6.build());
            builder2.rv_config(builder5.build());
            builder2.configRefresh(1000);
            builder3.disable_boot_mark(true);
            builder2.disable_up_location(true);
            builder3.disableUpAppInfo(true);
            builder3.oaid_api_is_disable(true);
            builder2.is_gdpr_region(false);
            builder2.enable_debug_level(false);
            builder3.enable_permission(false);
            builder3.enable_report_crash(false);
            builder.common_config(builder2.build());
            builder.android_config(builder3.build());
            this.i = builder.build();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(SdkConfigV2 sdkConfigV2) {
        if (sdkConfigV2 == null || sdkConfigV2.common_config == null) {
            return;
        }
        this.h = sdkConfigV2;
        b = this.h.common_config.is_gdpr_region.booleanValue();
        this.g = this.h.common_config.configRefresh.intValue() * 1000;
        if (this.j != null) {
            this.j.onUpdate();
        }
    }

    private void b() throws Throwable {
        ObjectInputStream objectInputStream;
        if (com.sigmob.sdk.common.a.n() == null) {
            a(this.i);
            return;
        }
        ObjectInputStream objectInputStream2 = null;
        String string = p.a(com.sigmob.sdk.common.a.n().af()).getString("sdkConfigVer", null);
        File file = new File(d.a() + "/config");
        if (this.e.equals(string)) {
            try {
                try {
                } catch (Throwable th) {
                    SigmobLog.e(th.getMessage());
                }
                if (file.exists()) {
                    try {
                        objectInputStream = new ObjectInputStream(new FileInputStream(file));
                    } catch (Throwable th2) {
                        th = th2;
                    }
                    try {
                        a(SdkConfigV2.ADAPTER.decode(objectInputStream));
                        objectInputStream.close();
                    } catch (Throwable th3) {
                        th = th3;
                        objectInputStream2 = objectInputStream;
                        a(this.i);
                        SigmobLog.e(th.getMessage());
                        if (objectInputStream2 != null) {
                            objectInputStream2.close();
                        }
                        return;
                    }
                    return;
                }
            } catch (Throwable th4) {
                th = th4;
            }
        }
        a(this.i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(SdkConfigV2 sdkConfigV2) throws Throwable {
        ObjectOutputStream objectOutputStream;
        if (sdkConfigV2 != null) {
            ObjectOutputStream objectOutputStream2 = null;
            File file = new File(d.a() + "/config");
            if (file.exists()) {
                file.delete();
            }
            file.getParentFile().mkdirs();
            try {
                try {
                    try {
                        objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
                    } catch (IOException e) {
                        e = e;
                    }
                } catch (Throwable th) {
                    th = th;
                    objectOutputStream = objectOutputStream2;
                }
                try {
                    sdkConfigV2.encode(objectOutputStream);
                    p.a(com.sigmob.sdk.common.a.n().af()).edit().putString("sdkConfigVer", this.e).apply();
                    objectOutputStream.close();
                } catch (IOException e2) {
                    e = e2;
                    objectOutputStream2 = objectOutputStream;
                    SigmobLog.e(e.getMessage());
                    if (objectOutputStream2 != null) {
                        objectOutputStream2.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (IOException e3) {
                            SigmobLog.e(e3.getMessage());
                        }
                    }
                    throw th;
                }
            } catch (IOException e4) {
                SigmobLog.e(e4.getMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (com.sigmob.sdk.common.a.n() == null) {
            return;
        }
        if (com.sigmob.sdk.common.a.n().aj() && com.sigmob.sdk.common.a.b()) {
            d();
        } else {
            SigmobLog.d("Can't load an ad because there is no network connectivity.");
            e();
        }
    }

    private void d() {
        l lVarA = f.a();
        j jVar = new j(getConfigUrl(), new j.a() { // from class: com.sigmob.sdk.common.SDKConfig.2
            @Override // com.sigmob.sdk.common.e.j.a
            public void a(SdkConfigResponse sdkConfigResponse) throws Throwable {
                SigmobLog.d(sdkConfigResponse.toString());
                SdkConfigV2 sdkConfigV2 = sdkConfigResponse.config_v2;
                if (sdkConfigV2 != null) {
                    SDKConfig.this.a(sdkConfigResponse.config_v2);
                    SDKConfig.this.b(sdkConfigV2);
                } else {
                    SigmobLog.e(sdkConfigResponse.error_message);
                }
                SDKConfig.this.e();
            }

            @Override // com.sigmob.volley.n.a
            public void onErrorResponse(s sVar) {
                SigmobLog.e(sVar.toString());
                SDKConfig.this.e();
            }
        });
        if (lVarA == null) {
            SigmobLog.e("queue is null");
            e();
        } else {
            lVarA.a(jVar);
            SigmobLog.i("update sdk config");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        f();
        if (this.f) {
            this.c.postDelayed(this.d, Math.max(b.d, this.g));
        }
    }

    private void f() {
        this.c.removeCallbacks(this.d);
    }

    public static String getConfigUrl() {
        StringBuilder sb;
        String str;
        if (Constants.IS_TEST.booleanValue()) {
            sb = new StringBuilder();
            str = "https://adstage.sigmob.cn/config?";
        } else {
            sb = new StringBuilder();
            str = "https://adservice.sigmob.cn/config?";
        }
        sb.append(str);
        sb.append(m.a());
        return sb.toString();
    }

    public static String getGDPRRegionURL() {
        StringBuilder sb;
        String str;
        if (Constants.IS_TEST.booleanValue()) {
            sb = new StringBuilder();
            str = "https://adstage.sigmob.cn/extconfig?";
        } else {
            sb = new StringBuilder();
            str = "https://adservice.sigmob.cn/extconfig?";
        }
        sb.append(str);
        sb.append(m.a());
        return sb.toString();
    }

    public static int getRequireAdapterVersion(int i) {
        switch (i) {
            case 1:
            case 4:
            case 5:
            case 6:
            case 7:
            case 9:
            case 11:
            case 12:
            case 15:
            case 18:
                return 3100;
            case 2:
            case 3:
            case 8:
            case 10:
            case 14:
            case 17:
            default:
                return -1;
            case 13:
            case 16:
                return 3500;
            case 19:
                return 3510;
        }
    }

    public static boolean isGDPRRegion() {
        return b;
    }

    public static SDKConfig sharedInstance() {
        if (a == null) {
            synchronized (SDKConfig.class) {
                if (a == null) {
                    a = new SDKConfig();
                }
            }
        }
        return a;
    }

    public boolean enableAntiFraud() {
        return (getCommonConfig() == null || getCommonConfig().anti_fraud_log == null || getCommonConfig().anti_fraud_log.events == null) ? false : true;
    }

    public boolean enableExitOnVideoClose() {
        return getCommonConfig().rv_config.enableExitOnVideoClose.booleanValue();
    }

    public boolean enableReport_log() {
        return false;
    }

    public boolean filterAntiEvent(String str) {
        if (!enableAntiFraud() || getCommonConfig().anti_fraud_log.events == null) {
            return false;
        }
        return getCommonConfig().anti_fraud_log.events.contains(str);
    }

    public long getADTrackerExpiredTime() {
        long jIntValue = getCommonConfig().tracking_expiration_time.intValue();
        if (jIntValue < 180) {
            jIntValue = 180;
        }
        return jIntValue * 1000;
    }

    public int getAdTrackerMaxRetryNum() {
        return 20;
    }

    public int getAdTrackerRetryInterval() {
        int iIntValue = getCommonConfig().tracking_retry_interval.intValue();
        if (iIntValue < 10) {
            iIntValue = 10;
        }
        return iIntValue * 1000;
    }

    public String getAdsUrl() {
        String str = getCommonConfig().endpoints.ads;
        if (TextUtils.isEmpty(str)) {
            if (Constants.IS_TEST.booleanValue()) {
                return "https://adstage.sigmob.cn/ad/v3?" + m.a();
            }
            return "https://adservice.sigmob.cn/ad/v3?" + m.a();
        }
        if (str.indexOf(63) == -1) {
            return str + "?" + m.a();
        }
        return str + "&" + m.a();
    }

    public Android getAndroidConfig() {
        return getSdkConfig().android_config;
    }

    public int getApk_expired_time() {
        if (getSdkConfig() != null) {
            return getAndroidConfig().apk_expired_time.intValue();
        }
        return 0;
    }

    public int getAutoLoadInterval() {
        int iIntValue = getCommonConfig().auto_load_interval.intValue();
        if (iIntValue <= 0 || iIntValue >= 30) {
            return iIntValue;
        }
        return 30;
    }

    public int getCacheTop() {
        return getCommonConfig().rv_config.cacheTop.intValue();
    }

    public DialogSetting getCloseDialogSetting() {
        if (getRvConfig() != null) {
            return getRvConfig().close_dialog_setting;
        }
        return null;
    }

    public int getClosePosition() {
        return getCommonConfig().rv_config.endcardClosePosition.intValue();
    }

    public Common getCommonConfig() {
        return getSdkConfig().common_config;
    }

    public int getDisable_up_OAid() {
        if (getSdkConfig() != null) {
            return getAndroidConfig().disable_up_oaid.intValue();
        }
        return 0;
    }

    public float getFinished() {
        return getCommonConfig().rv_config.finished.floatValue();
    }

    public String getHbAdsUrl() {
        String str = getCommonConfig().endpoints.hb_ads;
        if (TextUtils.isEmpty(str)) {
            if (Constants.IS_TEST.booleanValue()) {
                return "https://adstage.sigmob.cn/hb/ad?" + m.a();
            }
            return "https://adservice.sigmob.cn/hb/ad?" + m.a();
        }
        if (str.indexOf(63) == -1) {
            return str + "?" + m.a();
        }
        return str + "&" + m.a();
    }

    public long getLoadExpired() {
        long jIntValue = getCommonConfig().rv_config.loadExpired.intValue();
        if (jIntValue < 1) {
            jIntValue = 7200;
        }
        return jIntValue * 1000;
    }

    public long getLoadPeriodTime() {
        Integer num = getCommonConfig().rv_config.loadPeriodTime;
        if (num == null || num.intValue() < 1) {
            return 0L;
        }
        return num.intValue() * 1000;
    }

    public List<Integer> getLogBlackList() {
        return getCommonConfig().dclog_blacklist;
    }

    public String getLogUrl() {
        String str = getCommonConfig().endpoints.log;
        if (TextUtils.isEmpty(str)) {
            if (Constants.IS_TEST.booleanValue()) {
                return "https://dctest.sigmob.cn/log?" + m.a();
            }
            return "https://dc.sigmob.cn/log?" + m.a();
        }
        if (str.indexOf(63) == -1) {
            return str + "?" + m.a();
        }
        return str + "&" + m.a();
    }

    public int getMaxSendLogRecords() {
        int iIntValue = getCommonConfig().max_send_log_records.intValue();
        if (iIntValue < 10) {
            return 10;
        }
        return iIntValue;
    }

    public int getMotionCount() {
        if (!enableAntiFraud() || getCommonConfig().anti_fraud_log.motion_config == null) {
            return 0;
        }
        return getCommonConfig().anti_fraud_log.motion_config.count.intValue();
    }

    public int getMotionInterval() {
        if (!enableAntiFraud() || getCommonConfig().anti_fraud_log.motion_config == null) {
            return 0;
        }
        return getCommonConfig().anti_fraud_log.motion_config.interval.intValue();
    }

    public int getMotionQueueMax() {
        if (!enableAntiFraud() || getCommonConfig().anti_fraud_log.motion_config == null) {
            return 0;
        }
        return getCommonConfig().anti_fraud_log.motion_config.queue_max.intValue() + (-50) < getCommonConfig().anti_fraud_log.motion_config.count.intValue() * 2 ? (getCommonConfig().anti_fraud_log.motion_config.count.intValue() * 2) + 50 : getCommonConfig().anti_fraud_log.motion_config.queue_max.intValue();
    }

    public int getNativeAdCacheTop() {
        NativeConfig nativeConfig = getCommonConfig().native_config;
        if (nativeConfig != null) {
            return nativeConfig.cacheTop.intValue();
        }
        return 50;
    }

    public boolean getOaidApiDisable() {
        if (getSdkConfig() != null) {
            return getAndroidConfig().oaid_api_is_disable.booleanValue();
        }
        return true;
    }

    public RvConfig getRvConfig() {
        return getCommonConfig().rv_config;
    }

    public SdkConfigV2 getSdkConfig() {
        if (!Constants.IS_MOCK.booleanValue() && this.h != null) {
            return this.h;
        }
        return this.i;
    }

    public int getSendLogInterval() {
        int iIntValue = getCommonConfig().send_log_interval.intValue();
        if (iIntValue < 3) {
            return 3;
        }
        return iIntValue;
    }

    public int getShowClose() {
        return getCommonConfig().rv_config.skipPercent.intValue();
    }

    public int getSkipPosition() {
        return getCommonConfig().rv_config.videoClosePosition.intValue();
    }

    public int getSkipSeconds() {
        return getCommonConfig().rv_config.skipSeconds.intValue();
    }

    public int getSoundPostion() {
        return getCommonConfig().rv_config.mutePostion.intValue();
    }

    public int getSplashCacheTop() {
        Integer num;
        if (getCommonConfig().splash_config == null || (num = getCommonConfig().splash_config.cacheTop) == null || num.intValue() == 0) {
            return 50;
        }
        return num.intValue();
    }

    public long getSplashExpiredTime() {
        Integer num = getCommonConfig().splash_config != null ? getCommonConfig().splash_config.material_expired_time : null;
        if (num == null || num.intValue() == 0) {
            return 172800000L;
        }
        return num.intValue() < 0 ? num.intValue() : num.intValue() * 24 * 60 * 60 * 1000;
    }

    public int getSplashShowDuration() {
        if (getCommonConfig().splash_config == null || getCommonConfig().splash_config.showDuration.intValue() < 3) {
            return 3;
        }
        return getCommonConfig().splash_config.showDuration.intValue();
    }

    public String getStrategyUrl() {
        String str = getCommonConfig().endpoints.strategy;
        if (TextUtils.isEmpty(str)) {
            if (Constants.IS_TEST.booleanValue()) {
                return "https://adstage.sigmob.cn/strategy/v3?" + m.a();
            }
            return "https://adservice.sigmob.cn/strategy/v3?" + m.a();
        }
        if (str.indexOf(63) == -1) {
            return str + "?" + m.a();
        }
        return str + "&" + m.a();
    }

    public int ifMute() {
        Integer num = getCommonConfig().rv_config.ifMute;
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public boolean isDisableBootMark() {
        if (getSdkConfig() == null || getAndroidConfig() == null) {
            return true;
        }
        return getAndroidConfig().disable_boot_mark.booleanValue();
    }

    public boolean isDisableUpAppInfo() {
        if (getSdkConfig() == null || getAndroidConfig().disableUpAppInfo == null) {
            return true;
        }
        return getAndroidConfig().disableUpAppInfo.booleanValue();
    }

    public boolean isDisable_up_location() {
        if (getSdkConfig() == null || getCommonConfig().disable_up_location == null) {
            return true;
        }
        return getCommonConfig().disable_up_location.booleanValue();
    }

    public boolean isEnableWifiScanList() {
        Android androidConfig = getAndroidConfig();
        return androidConfig != null && androidConfig.up_wifi_list_interval.intValue() >= 60;
    }

    public boolean isEnable_debug_level() {
        if (getSdkConfig() == null || getCommonConfig().enable_debug_level == null) {
            return false;
        }
        return getCommonConfig().enable_debug_level.booleanValue();
    }

    public boolean isEnable_permission() {
        if (getSdkConfig() == null || getAndroidConfig().enable_permission == null) {
            return false;
        }
        return getAndroidConfig().enable_permission.booleanValue();
    }

    public boolean isEnable_report_crash() {
        if (getSdkConfig() == null || getAndroidConfig().enable_report_crash == null) {
            return false;
        }
        return getAndroidConfig().enable_report_crash.booleanValue();
    }

    public long loadAdTimeout() {
        int iIntValue = getCommonConfig().load_timeout.intValue();
        if (iIntValue < 45) {
            iIntValue = 45;
        }
        return iIntValue * 1000;
    }

    public SDKConfig setOnSDKUpdateListener(a aVar) {
        this.j = aVar;
        return a;
    }

    public void startUpdate() {
        f();
        this.c.post(this.d);
    }
}
