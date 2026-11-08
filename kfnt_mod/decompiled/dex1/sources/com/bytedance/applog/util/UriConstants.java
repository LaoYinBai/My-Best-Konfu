package com.bytedance.applog.util;

import com.bytedance.applog.UriConfig;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class UriConstants {
    public static final int DEFAULT = 0;
    public static final int REGION_DEFAULT = 0;

    /* renamed from: a, reason: collision with root package name */
    public static final UriConfig f3575a;

    static {
        new UriConfig.Builder().setRegisterUri("https://log.snssdk.com/service/2/device_register/").setActiveUri("https://ichannel.snssdk.com/service/2/app_alert_check/").setSendUris(new String[]{"https://log.snssdk.com/service/2/app_log/", "https://applog.snssdk.com/service/2/app_log/"}).setRealUris(new String[]{"https://rtlog.snssdk.com/service/2/app_log/", "https://rtapplog.snssdk.com/service/2/app_log/"}).setSettingUri("https://log.snssdk.com/service/2/log_settings/").setALinkAttributionUri("https://toblog-alink.ctobsnssdk.com/service/2/attribution_data").setALinkQueryUri("https://toblog-alink.ctobsnssdk.com/service/2/alink_data").build();
        f3575a = new UriConfig.Builder().setRegisterUri("https://toblog.ctobsnssdk.com/service/2/device_register/").setActiveUri("https://toblog.ctobsnssdk.com/service/2/app_alert_check/").setSendUris(new String[]{"https://toblog.ctobsnssdk.com/service/2/app_log/", "https://tobapplog.ctobsnssdk.com/service/2/app_log/"}).setProfileUri("https://toblog.ctobsnssdk.com/service/2/profile/").setSettingUri("https://toblog.ctobsnssdk.com/service/2/log_settings/").setAbUri("https://toblog.ctobsnssdk.com/service/2/abtest_config/").setMonitor("https://success.ctobsnssdk.com/service/2/app_log/").setALinkAttributionUri("https://toblog-alink.ctobsnssdk.com/service/2/attribution_data").setALinkQueryUri("https://toblog-alink.ctobsnssdk.com/service/2/alink_data").build();
    }

    public static final UriConfig createUriConfig(int i) {
        return f3575a;
    }
}
