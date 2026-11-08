package com.bytedance.sdk.openadsdk;

import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface ComplianceInfo {
    String getAppName();

    String getAppVersion();

    String getDeveloperName();

    Map<String, String> getPermissionsMap();

    String getPrivacyUrl();
}
