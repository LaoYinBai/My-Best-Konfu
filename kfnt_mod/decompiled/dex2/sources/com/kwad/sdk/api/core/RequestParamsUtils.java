package com.kwad.sdk.api.core;

import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class RequestParamsUtils {
    private static final String USER_AGENT = getUserAgentParams() + "ksad-android-3.3.17";
    public static final String USER_AGENT_KEY = "User-Agent";

    private RequestParamsUtils() {
    }

    public static String getUserAgent() {
        return USER_AGENT;
    }

    private static String getUserAgentParams() {
        String property = System.getProperty("http.agent");
        if (TextUtils.isEmpty(property)) {
            return property;
        }
        StringBuffer stringBuffer = new StringBuffer();
        int length = property.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = property.charAt(i);
            if (cCharAt <= 31 || cCharAt >= 127) {
                stringBuffer.append(String.format("\\u%04x", Integer.valueOf(cCharAt)));
            } else {
                stringBuffer.append(cCharAt);
            }
        }
        stringBuffer.append("-");
        return stringBuffer.toString();
    }
}
