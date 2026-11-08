package com.bytedance.applog;

import com.bytedance.applog.util.UriConstants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class UriConfig {
    public static final String DOMAIN_BUSINESS = "https://log-api.oceanengine.com";
    public static final String PATH_AB = "/service/2/abtest_config/";
    public static final String PATH_ACTIVE = "/service/2/app_alert_check/";
    public static final String PATH_ALINK_ATTRIBUTION = "/service/2/attribution_data";
    public static final String PATH_ALINK_QUERY = "/service/2/alink_data";
    public static final String PATH_CONFIG = "/service/2/log_settings/";
    public static final String PATH_PROFILE = "/service/2/profile/";
    public static final String PATH_REGISTER = "/service/2/device_register/";
    public static final String PATH_SEND = "/service/2/app_log/";

    /* renamed from: a, reason: collision with root package name */
    public final String f3561a;

    /* renamed from: b, reason: collision with root package name */
    public final String f3562b;

    /* renamed from: c, reason: collision with root package name */
    public final String[] f3563c;
    public final String[] d;
    public final String e;
    public final String f;
    public final String g;
    public final String h;
    public final String i;
    public final String j;
    public final String k;

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        public String f3564a;

        /* renamed from: b, reason: collision with root package name */
        public String f3565b;

        /* renamed from: c, reason: collision with root package name */
        public String[] f3566c;
        public String[] d;
        public String e;
        public String f;
        public String g;
        public String h;
        public String i;
        public String j;
        public String k;

        public UriConfig build() {
            return new UriConfig(this, null);
        }

        public Builder setALinkAttributionUri(String str) {
            this.k = str;
            return this;
        }

        public Builder setALinkQueryUri(String str) {
            this.j = str;
            return this;
        }

        public Builder setAbUri(String str) {
            this.f = str;
            return this;
        }

        public Builder setActiveUri(String str) {
            this.f3565b = str;
            return this;
        }

        public Builder setBusinessUri(String str) {
            this.h = str;
            return this;
        }

        public Builder setMonitor(String str) {
            this.i = str;
            return this;
        }

        public Builder setProfileUri(String str) {
            this.g = str;
            return this;
        }

        public Builder setRealUris(String[] strArr) {
            this.d = strArr;
            return this;
        }

        public Builder setRegisterUri(String str) {
            this.f3564a = str;
            return this;
        }

        public Builder setSendUris(String[] strArr) {
            this.f3566c = strArr;
            return this;
        }

        public Builder setSettingUri(String str) {
            this.e = str;
            return this;
        }
    }

    public /* synthetic */ UriConfig(Builder builder, a aVar) {
        this.f3561a = builder.f3564a;
        this.f3562b = builder.f3565b;
        this.f3563c = builder.f3566c;
        this.d = builder.d;
        this.e = builder.e;
        this.f = builder.f;
        this.g = builder.g;
        this.h = builder.h;
        this.i = builder.i;
        this.j = builder.j;
        this.k = builder.k;
    }

    public static UriConfig createByDomain(String str, String[] strArr) {
        Builder builder = new Builder();
        builder.setRegisterUri(str + PATH_REGISTER).setActiveUri(str + "/service/2/app_alert_check/").setALinkAttributionUri(str + "/service/2/attribution_data").setALinkQueryUri(str + "/service/2/alink_data");
        if (strArr == null || strArr.length == 0) {
            builder.setSendUris(new String[]{str + PATH_SEND});
        } else {
            String[] strArr2 = new String[strArr.length + 1];
            strArr2[0] = str + PATH_SEND;
            for (int i = 1; i < strArr2.length; i++) {
                strArr2[i] = com.bytedance.a.a.a(new StringBuilder(), strArr[i - 1], PATH_SEND);
            }
            builder.setSendUris(strArr2);
        }
        builder.setSettingUri(str + "/service/2/log_settings/").setAbUri(str + "/service/2/abtest_config/").setProfileUri(str + "/service/2/profile/");
        return builder.build();
    }

    public static UriConfig createUriConfig(int i) {
        return UriConstants.createUriConfig(i);
    }

    public String getAbUri() {
        return this.f;
    }

    public String getActiveUri() {
        return this.f3562b;
    }

    public String getAlinkAttributionUri() {
        return this.k;
    }

    public String getAlinkQueryUri() {
        return this.j;
    }

    public String getBusinessUri() {
        return this.h;
    }

    public String getMonitorUri() {
        return this.i;
    }

    public String getProfileUri() {
        return this.g;
    }

    public String[] getRealUris() {
        return this.d;
    }

    public String getRegisterUri() {
        return this.f3561a;
    }

    public String[] getSendUris() {
        return this.f3563c;
    }

    public String getSettingUri() {
        return this.e;
    }
}
