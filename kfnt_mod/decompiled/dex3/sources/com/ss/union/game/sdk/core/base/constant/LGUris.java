package com.ss.union.game.sdk.core.base.constant;

import com.ss.union.game.sdk.core.base.config.ConfigManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class LGUris {
    public static final String API_HOST_PREVIEW_OHAYOO = "preview-ohayoo.bytedance.net";
    private static final String a = "ohayoo-boe.bytedance.net";
    private static final String b = "sandbox-sdk.ohayoo.cn";
    private static final String c = "ohayoo.cn";
    private static final String d = "https://";
    private static final String e = "http://";
    private static a f;

    private enum a {
        ONLINE,
        PREVIEW_ONLINE,
        BOE,
        SEND_BOX
    }

    public static String onlineHost() {
        return "https://ohayoo.cn";
    }

    public static String path(String str) {
        return "https://" + getCurrentDomain() + str;
    }

    public static String getCurrentDomain() {
        if (f == null) {
            return ConfigManager.AppConfig.isDebug() ? b : c;
        }
        switch (f) {
            case SEND_BOX:
                return b;
            case BOE:
                return a;
            case PREVIEW_ONLINE:
                return API_HOST_PREVIEW_OHAYOO;
            default:
                return c;
        }
    }
}
