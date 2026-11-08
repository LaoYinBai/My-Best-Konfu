package com.ss.union.game.sdk.core.antiAddiction.config;

import com.ss.union.game.sdk.common.util.DateUtils;
import com.ss.union.game.sdk.common.util.SPUtils;
import com.ss.union.game.sdk.core.antiAddiction.bean.AntiAddictionInfo;
import com.ss.union.game.sdk.core.util.LogDevKitUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AntiAddictionConfig {

    public static class Device {
        private static final String a = "sp_key_anti_addiction_device_id";
        private static final String b = "sp_key_anti_addiction_device_remain_time";
        private static final String c = "sp_key_anti_addiction_device_is_holiday";
        private static final String d = "sp_key_anti_addiction_device_is_in_minor_limit";
        private static final String e = "sp_key_anti_addiction_device_server_time";

        public static void update(AntiAddictionInfo antiAddictionInfo) {
            if (antiAddictionInfo != null) {
                SPUtils sPUtilsB = AntiAddictionConfig.b();
                sPUtilsB.put(a, antiAddictionInfo.id);
                sPUtilsB.put(b, antiAddictionInfo.remainingTime);
                sPUtilsB.put(c, antiAddictionInfo.isHoliday);
                sPUtilsB.put(d, antiAddictionInfo.isInMinorLimit);
                sPUtilsB.put(e, antiAddictionInfo.serverTime);
            }
        }

        public static long serverTime() {
            long j = AntiAddictionConfig.b().getLong(e, -1L);
            return j <= 0 ? System.currentTimeMillis() / 1000 : j;
        }

        public static int remainTime() {
            int i = AntiAddictionConfig.b().getInt(b, -1);
            if (a()) {
                LogDevKitUtils.logAntiAddiction("hasTriggerAnti remainTime set 0");
                return 0;
            }
            if (i < 0) {
                return 0;
            }
            return i;
        }

        public static void triggerAnti() {
            AntiAddictionInfo antiAddictionInfoInfo = info();
            AntiAddictionConfig.b().clear(true);
            update(antiAddictionInfoInfo);
            AntiAddictionConfig.b().put(AntiAddictionConfig.b().getString(a, "") + "_" + DateUtils.format2(serverTime() * 1000), true);
        }

        private static boolean a() {
            return AntiAddictionConfig.b().getBoolean(AntiAddictionConfig.b().getString(a, "") + "_" + DateUtils.format2(serverTime() * 1000), false);
        }

        public static boolean isHoliday() {
            return AntiAddictionConfig.b().getBoolean(c, false);
        }

        public static AntiAddictionInfo info() {
            AntiAddictionInfo antiAddictionInfo = new AntiAddictionInfo();
            SPUtils sPUtilsB = AntiAddictionConfig.b();
            antiAddictionInfo.id = sPUtilsB.getString(a, antiAddictionInfo.id);
            antiAddictionInfo.remainingTime = sPUtilsB.getInt(b, antiAddictionInfo.remainingTime);
            antiAddictionInfo.isHoliday = sPUtilsB.getBoolean(c, antiAddictionInfo.isHoliday);
            antiAddictionInfo.isInMinorLimit = sPUtilsB.getBoolean(d, antiAddictionInfo.isInMinorLimit);
            antiAddictionInfo.serverTime = sPUtilsB.getLong(e, antiAddictionInfo.serverTime);
            return antiAddictionInfo;
        }
    }

    public static class Account {
        private static final String a = "sp_key_anti_addiction_account_id";
        private static final String b = "sp_key_anti_addiction_account_remain_time";
        private static final String c = "sp_key_anti_addiction_account_is_holiday";
        private static final String d = "sp_key_anti_addiction_account_is_in_minor_limit";
        private static final String e = "sp_key_anti_addiction_account_server_time";

        public static void update(AntiAddictionInfo antiAddictionInfo) {
            if (antiAddictionInfo != null) {
                SPUtils sPUtilsB = AntiAddictionConfig.b();
                sPUtilsB.put(a, antiAddictionInfo.id);
                sPUtilsB.put(b, antiAddictionInfo.remainingTime);
                sPUtilsB.put(c, antiAddictionInfo.isHoliday);
                sPUtilsB.put(d, antiAddictionInfo.isInMinorLimit);
                sPUtilsB.put(e, antiAddictionInfo.serverTime);
            }
        }

        public static long serverTime() {
            long j = AntiAddictionConfig.b().getLong(e, -1L);
            return j <= 0 ? System.currentTimeMillis() / 1000 : j;
        }

        public static int remainTime() {
            int i = AntiAddictionConfig.b().getInt(b, -1);
            if (a()) {
                LogDevKitUtils.logAntiAddiction("hasTriggerAnti remainTime set 0");
                return 0;
            }
            if (i < 0) {
                return 0;
            }
            return i;
        }

        public static void triggerAnti() {
            AntiAddictionInfo antiAddictionInfoInfo = info();
            AntiAddictionConfig.b().clear(true);
            update(antiAddictionInfoInfo);
            AntiAddictionConfig.b().put(AntiAddictionConfig.b().getString(a, "") + "_" + DateUtils.format2(serverTime() * 1000), true);
        }

        private static boolean a() {
            return AntiAddictionConfig.b().getBoolean(AntiAddictionConfig.b().getString(a, "") + "_" + DateUtils.format2(serverTime() * 1000), false);
        }

        public static boolean isHoliday() {
            return AntiAddictionConfig.b().getBoolean(c, false);
        }

        public static AntiAddictionInfo info() {
            AntiAddictionInfo antiAddictionInfo = new AntiAddictionInfo();
            SPUtils sPUtilsB = AntiAddictionConfig.b();
            antiAddictionInfo.id = sPUtilsB.getString(a, antiAddictionInfo.id);
            antiAddictionInfo.remainingTime = sPUtilsB.getInt(b, antiAddictionInfo.remainingTime);
            antiAddictionInfo.isHoliday = sPUtilsB.getBoolean(c, antiAddictionInfo.isHoliday);
            antiAddictionInfo.isInMinorLimit = sPUtilsB.getBoolean(d, antiAddictionInfo.isInMinorLimit);
            antiAddictionInfo.serverTime = sPUtilsB.getLong(e, antiAddictionInfo.serverTime);
            return antiAddictionInfo;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static SPUtils b() {
        return SPUtils.getInstance("lg_game_anti_addiction");
    }
}
