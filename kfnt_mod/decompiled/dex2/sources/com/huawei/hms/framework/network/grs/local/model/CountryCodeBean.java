package com.huawei.hms.framework.network.grs.local.model;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.SystemPropUtils;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import java.util.Locale;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class CountryCodeBean {
    private static final String ANDRIOD_SYSTEMPROP = "android.os.SystemProperties";
    private static final int COUNTRYCODE_SIZE = 2;
    private static final String LOCALE_COUNTRYSYSTEMPROP = "ro.product.locale.region";
    private static final String SPECIAL_COUNTRYCODE_CN = "cn";
    private static final String SPECIAL_COUNTRYCODE_EU = "eu";
    private static final String SPECIAL_COUNTRYCODE_LA = "la";
    private static final String TAG = "CountryCodeBean";
    private static final String VENDORCOUNTRY_SYSTEMPROP = "ro.hw.country";
    private String countryCode;
    private String countrySource = GrsBaseInfo.CountryCodeSource.UNKNOWN;

    public CountryCodeBean(Context context, boolean z) {
        this.countryCode = GrsBaseInfo.CountryCodeSource.UNKNOWN;
        init(context, z);
        this.countryCode = this.countryCode.toUpperCase(Locale.ENGLISH);
    }

    private void checkCodeLenth() {
        if (this.countryCode == null || this.countryCode.length() != 2) {
            this.countryCode = GrsBaseInfo.CountryCodeSource.UNKNOWN;
            this.countrySource = GrsBaseInfo.CountryCodeSource.UNKNOWN;
        }
    }

    private void getLocaleCountryCode() {
        this.countryCode = SystemPropUtils.getProperty("get", LOCALE_COUNTRYSYSTEMPROP, ANDRIOD_SYSTEMPROP, GrsBaseInfo.CountryCodeSource.UNKNOWN);
        this.countrySource = GrsBaseInfo.CountryCodeSource.LOCALE_INFO;
        if ("cn".equalsIgnoreCase(this.countryCode)) {
            return;
        }
        Logger.w(TAG, "countryCode from system language is not reliable.");
        this.countryCode = GrsBaseInfo.CountryCodeSource.UNKNOWN;
        this.countrySource = GrsBaseInfo.CountryCodeSource.UNKNOWN;
    }

    private void getSimCountryCode(Context context, boolean z) {
        String str;
        TelephonyManager telephonyManager = (TelephonyManager) context.getApplicationContext().getSystemService("phone");
        if (telephonyManager != null) {
            if (!z || telephonyManager.getPhoneType() == 2) {
                Logger.v(TAG, "getCountryCode get country code from {%s}", GrsBaseInfo.CountryCodeSource.SIM_COUNTRY);
                this.countryCode = telephonyManager.getSimCountryIso();
                str = GrsBaseInfo.CountryCodeSource.SIM_COUNTRY;
            } else {
                Logger.v(TAG, "getCountryCode get country code from {%s}", GrsBaseInfo.CountryCodeSource.NETWORK_COUNTRY);
                this.countryCode = telephonyManager.getNetworkCountryIso();
                str = GrsBaseInfo.CountryCodeSource.NETWORK_COUNTRY;
            }
            this.countrySource = str;
        }
        checkCodeLenth();
    }

    private void getVendorCountryCode() {
        this.countrySource = GrsBaseInfo.CountryCodeSource.VENDOR_COUNTRY;
        this.countryCode = SystemPropUtils.getProperty("get", VENDORCOUNTRY_SYSTEMPROP, ANDRIOD_SYSTEMPROP, GrsBaseInfo.CountryCodeSource.UNKNOWN);
        if (!SPECIAL_COUNTRYCODE_EU.equalsIgnoreCase(this.countryCode) && !SPECIAL_COUNTRYCODE_LA.equalsIgnoreCase(this.countryCode)) {
            checkCodeLenth();
        } else {
            this.countryCode = GrsBaseInfo.CountryCodeSource.UNKNOWN;
            this.countrySource = GrsBaseInfo.CountryCodeSource.UNKNOWN;
        }
    }

    private void init(Context context, boolean z) {
        if (context == null) {
            throw new NullPointerException("context must be not null.Please provide app's Context");
        }
        try {
            getVendorCountryCode();
            if (isCodeValidate()) {
                Logger.v(TAG, "getCountryCode get country code from {%s}", GrsBaseInfo.CountryCodeSource.VENDOR_COUNTRY);
                return;
            }
            getSimCountryCode(context, z);
            if (isCodeValidate()) {
                Logger.v(TAG, "getCountryCode get country code from {%s}", GrsBaseInfo.CountryCodeSource.SIM_COUNTRY);
                return;
            }
            getLocaleCountryCode();
            if (isCodeValidate()) {
                Logger.v(TAG, "getCountryCode get country code from {%s}", GrsBaseInfo.CountryCodeSource.LOCALE_INFO);
            }
        } catch (Exception unused) {
            Logger.w(TAG, "get CountryCode error");
        }
    }

    private boolean isCodeValidate() {
        return !GrsBaseInfo.CountryCodeSource.UNKNOWN.equals(this.countryCode);
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getCountrySource() {
        return this.countrySource;
    }
}
