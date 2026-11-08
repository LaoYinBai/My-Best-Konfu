package com.ss.union.game.sdk.customerSystem.a;

import com.ss.union.game.sdk.core.base.config.ConfigManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {

    /* renamed from: com.ss.union.game.sdk.customerSystem.a.a$a, reason: collision with other inner class name */
    public static class C0095a {
        private static final String a = "https://cs-sandbox.dailygn.com";
        private static final String b = "https://cs.snssdk.com";

        public static String a() {
            return ConfigManager.AppConfig.isDebug() ? "https://cs-sandbox.dailygn.com/customer_service/cp/reddot/get" : "https://cs.snssdk.comcustomer_service/cp/reddot/get";
        }

        public static String b() {
            return ConfigManager.AppConfig.isDebug() ? "https://cs-sandbox.dailygn.com?aid=%s&sdk_language=zh-CN&access_token=%s&platform_type=ohayoo" : "https://cs.snssdk.com?aid=%s&sdk_language=zh-CN&access_token=%s&platform_type=ohayoo";
        }
    }
}
