package com.ss.union.game.sdk.core.vapp;

import com.ss.union.game.sdk.common.util.logger.LogUtils;
import com.xiaomi.mipush.sdk.Constants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class VLog {
    private static final String a = "DevKit_VCenter";

    public static void d(String str) {
        LogUtils.log(a, str);
    }

    public static void d(String str, String str2) {
        LogUtils.log(a, str + Constants.COLON_SEPARATOR + str2);
    }

    public static void e(String str) {
        LogUtils.log(a, str);
    }

    public static void e(String str, String str2) {
        LogUtils.log(a, str + Constants.COLON_SEPARATOR + str2);
    }

    public static class UniversalCmd {
        private static final String a = "V_UniversalCmd";

        public static void d(String str) {
            VLog.d(a, str);
        }

        public static void e(String str) {
            VLog.e(a, str);
        }
    }

    public static class Account {
        private static final String a = "V_Account";

        public static void d(String str) {
            VLog.d(a, str);
        }

        public static void e(String str) {
            VLog.e(a, str);
        }
    }

    public static class Pay {
        private static final String a = "V_PAY";

        public static void d(String str) {
            VLog.d(a, str);
        }

        public static void e(String str) {
            VLog.e(a, str);
        }
    }
}
