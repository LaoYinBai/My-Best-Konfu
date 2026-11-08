package com.huawei.hms.utils.countrycode;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.huawei.hms.framework.common.SystemPropUtils;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.hms.support.log.HMSLog;
import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;
import java.util.Locale;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class CountryCodeBean {
    private String b;
    private String a = GrsBaseInfo.CountryCodeSource.UNKNOWN;
    private boolean c = false;

    public CountryCodeBean(Context context, boolean z) {
        this.b = GrsBaseInfo.CountryCodeSource.UNKNOWN;
        a(context, z);
        this.b = this.b.toUpperCase(Locale.ENGLISH);
    }

    public String a() {
        return this.b;
    }

    private void a(Context context, boolean z) {
        if (context == null) {
            throw new NullPointerException("context must be not null.Please provide app's Context");
        }
        try {
            d();
            if (b()) {
                HMSLog.i("CountryCodeBean", "getCountryCode get country code from VENDOR_COUNTRY");
                return;
            }
            b(context, z);
            if (b()) {
                HMSLog.i("CountryCodeBean", "getCountryCode get country code from SIM_COUNTRY");
                return;
            }
            e();
            if (b()) {
                HMSLog.i("CountryCodeBean", "getCountryCode get country code from LOCALE_INFO");
            }
        } catch (Exception unused) {
            HMSLog.w("CountryCodeBean", "get CountryCode error");
        }
    }

    private boolean b() {
        return !GrsBaseInfo.CountryCodeSource.UNKNOWN.equals(this.b);
    }

    private void c() {
        if (this.b == null || this.b.length() != 2) {
            this.b = GrsBaseInfo.CountryCodeSource.UNKNOWN;
            this.a = GrsBaseInfo.CountryCodeSource.UNKNOWN;
        }
    }

    private void d() {
        this.a = GrsBaseInfo.CountryCodeSource.VENDOR_COUNTRY;
        this.b = SystemPropUtils.getProperty("get", "ro.hw.country", "android.os.SystemProperties", GrsBaseInfo.CountryCodeSource.UNKNOWN);
        if ("normal".equalsIgnoreCase(this.b)) {
            this.c = true;
        }
        if ("eu".equalsIgnoreCase(this.b) || "la".equalsIgnoreCase(this.b)) {
            this.b = GrsBaseInfo.CountryCodeSource.UNKNOWN;
            this.a = GrsBaseInfo.CountryCodeSource.UNKNOWN;
        } else {
            c();
        }
    }

    private void b(Context context, boolean z) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getApplicationContext().getSystemService("phone");
        if (telephonyManager != null) {
            if (z && telephonyManager.getPhoneType() != 2) {
                HMSLog.i("CountryCodeBean", "getCountryCode get country code from NETWORK_COUNTRY");
                this.b = telephonyManager.getNetworkCountryIso();
                this.a = GrsBaseInfo.CountryCodeSource.NETWORK_COUNTRY;
            } else {
                HMSLog.i("CountryCodeBean", "getCountryCode get country code from SIM_COUNTRY");
                this.b = telephonyManager.getSimCountryIso();
                this.a = GrsBaseInfo.CountryCodeSource.SIM_COUNTRY;
            }
        }
        c();
    }

    private void e() {
        this.b = SystemPropUtils.getProperty("get", "ro.product.locale.region", "android.os.SystemProperties", GrsBaseInfo.CountryCodeSource.UNKNOWN);
        this.a = GrsBaseInfo.CountryCodeSource.LOCALE_INFO;
        if (AdvanceSetting.CLEAR_NOTIFICATION.equalsIgnoreCase(this.b) || this.c) {
            return;
        }
        HMSLog.w("CountryCodeBean", "countryCode from system language is not reliable.");
        this.b = GrsBaseInfo.CountryCodeSource.UNKNOWN;
        this.a = GrsBaseInfo.CountryCodeSource.UNKNOWN;
    }
}
