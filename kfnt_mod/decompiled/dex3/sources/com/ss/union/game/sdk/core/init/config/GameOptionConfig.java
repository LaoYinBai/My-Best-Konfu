package com.ss.union.game.sdk.core.init.config;

import android.text.TextUtils;
import com.ss.union.game.sdk.common.util.DateUtils;
import com.ss.union.game.sdk.common.util.GlobalApplicationUtils;
import com.ss.union.game.sdk.common.util.SPUtils;
import com.ss.union.game.sdk.core.base.a.a;
import com.ss.union.game.sdk.core.base.event.PageStater;
import com.ss.union.game.sdk.core.init.bean.GameSDKOption;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class GameOptionConfig {

    public static final class GameOption {

        public static class AppUpgrade {
            public static GameSDKOption.d mAppUpgradeConfig;
        }

        public static void updateGameOption(GameSDKOption gameSDKOption) throws JSONException {
            if (gameSDKOption != null) {
                ScreenRecord.b(gameSDKOption.a);
                MV.b(gameSDKOption.b);
                HighQualityVideo.b(gameSDKOption.d);
                VE.b(gameSDKOption.c);
                SplashAdTest.b(gameSDKOption.e);
                AntiAddiction.Account.b(gameSDKOption.f.a);
                AntiAddiction.Device.b(gameSDKOption.f.b);
                AntiAddiction.Pay.b(gameSDKOption.f.c);
                VApp.b(gameSDKOption.g);
                AgeTip.b(gameSDKOption.i);
                AppUpgrade.mAppUpgradeConfig = gameSDKOption.h;
                IdentifyAward.b(gameSDKOption.j);
            }
        }

        public static final class ScreenRecord {
            private static final String a = "sp_key_record_screen_switch_status";
            private static final String b = "sp_key_record_screen_switch_code";
            private static final String c = "sp_key_record_screen_switch_message";

            /* JADX INFO: Access modifiers changed from: private */
            public static void b(GameSDKOption.i iVar) {
                if (iVar != null) {
                    GameOption.b().put(a, iVar.a());
                    GameOption.b().put(b, iVar.b());
                    GameOption.b().put(c, iVar.c());
                }
            }

            public static boolean recordScreenEnable() {
                return GameOption.b().getBoolean(a, false);
            }

            public static int getCode() {
                return GameOption.b().getInt(b, 0);
            }

            public static String getMessage() {
                return GameOption.b().getString(c, "");
            }
        }

        public static final class MV {
            private static final String a = "sp_key_mv_switch_status";
            private static final String b = "sp_key_mv_switch_code";
            private static final String c = "sp_key_mv_switch_message";

            /* JADX INFO: Access modifiers changed from: private */
            public static void b(GameSDKOption.g gVar) {
                if (gVar != null) {
                    GameOption.b().put(a, gVar.a());
                    GameOption.b().put(b, gVar.b());
                    GameOption.b().put(c, gVar.c());
                }
            }

            public static boolean mvEnable() {
                return GameOption.b().getBoolean(a, false);
            }

            public static int getCode() {
                return GameOption.b().getInt(b, 0);
            }

            public static String getMessage() {
                return GameOption.b().getString(c, "");
            }
        }

        public static final class HighQualityVideo {
            static final int a = -3000;
            static final int b = 30000;
            static final int c = -3001;
            static final int d = 10002;
            static final String e = "您当前没有优质视频需要分享";
            static final String f = "本日分享次数已经用完,请明日再分享";
            static final String g = "请连接网络再分享";
            private static final String h = "sp_key_high_quality_video_last_update_time";
            private static final String i = "sp_key_high_quality_video_share_count";

            /* JADX INFO: Access modifiers changed from: private */
            public static void b(GameSDKOption.e eVar) {
                if (eVar != null) {
                    long j = GameOption.b().getLong(h, 0L);
                    if (j <= 0) {
                        GameOption.b().put(h, System.currentTimeMillis());
                        GameOption.b().put(i, eVar.a());
                    } else if (!DateUtils.isSameDay(j, System.currentTimeMillis())) {
                        GameOption.b().put(i, eVar.a());
                    }
                    List<GameSDKOption.VideoShareConfig> listB = eVar.b();
                    if (listB == null || listB.size() <= 0) {
                        return;
                    }
                    a.a(GlobalApplicationUtils.getContext()).a().a(listB);
                }
            }

            public static int getShareCount() {
                return GameOption.b().getInt(i, 0);
            }

            public static GameSDKOption.VideoShareConfig queryShouldShowVideo() {
                return a.a(GlobalApplicationUtils.getContext()).a().a();
            }
        }

        public static final class VE {
            private static final String a = "sp_key_ve_use_huawei_encoding";
            private static final String b = "sp_key_ve_dy_share_topic";
            private static final String c = "sp_key_effect_download_url";
            private static final String d = "sp_key_effect_checksum";
            private static final String e = "sp_key_bgm_download_url";
            private static final String f = "sp_key_bgm_checksum";

            /* JADX INFO: Access modifiers changed from: private */
            public static void b(GameSDKOption.l lVar) {
                if (lVar != null) {
                    GameOption.b().put(a, lVar.a);
                    GameOption.b().put(b, lVar.b);
                    GameOption.b().put(c, lVar.c);
                    GameOption.b().put(d, lVar.d);
                    GameOption.b().put(e, lVar.e);
                    GameOption.b().put(f, lVar.f);
                }
            }

            public static boolean useHuaWeiEncoding() {
                return GameOption.b().getBoolean(a, false);
            }

            public static String getDyShareTopic() {
                return GameOption.b().getString(b, "");
            }

            public static String getEffectDownloadUrl() {
                return GameOption.b().getString(c, "");
            }

            public static String getEffectDownloadMD5() {
                return GameOption.b().getString(d, "");
            }

            public static String getBgmDownloadUrl() {
                return GameOption.b().getString(e, "");
            }

            public static String getBgmDownloadMD5() {
                return GameOption.b().getString(f, "");
            }
        }

        public static final class SplashAdTest {
            private static final String a = "sp_key_splash_ad_config_group";
            private static final String b = "sp_key_splash_ad_config_enable";
            private static final String c = "sp_key_splash_ad_config_frequency";
            private static final String d = "sp_key_splash_ad_config_load_last_time";
            private static final String e = "sp_key_splash_ad_config_load_times_on_day";

            /* JADX INFO: Access modifiers changed from: private */
            public static void b(GameSDKOption.j jVar) throws JSONException {
                if (jVar != null) {
                    GameOption.b().put(a, jVar.a);
                    GameOption.b().put(b, jVar.b);
                    GameOption.b().put(c, jVar.c);
                    if (isEnterGroup()) {
                        PageStater.V1.onEvent("Splash_ads", "group", getGroupType().a());
                    }
                }
            }

            public static boolean isOpen() {
                return GameOption.b().getInt(a, 0) == 1;
            }

            public static boolean isEnterGroup() {
                return a() != null;
            }

            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            /* JADX WARN: Removed duplicated region for block: B:12:0x003d  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public static boolean isCanLoadAd() {
                /*
                    boolean r0 = isOpen()
                    r1 = 0
                    if (r0 == 0) goto L5b
                    boolean r0 = isEnterGroup()
                    if (r0 == 0) goto L5b
                    int r0 = b()
                    long r2 = java.lang.System.currentTimeMillis()
                    com.ss.union.game.sdk.common.util.SPUtils r4 = com.ss.union.game.sdk.core.init.config.GameOptionConfig.GameOption.a()
                    java.lang.String r5 = "sp_key_splash_ad_config_load_last_time"
                    r6 = 0
                    long r4 = r4.getLong(r5, r6)
                    boolean r2 = com.ss.union.game.sdk.common.util.DateUtils.isSameDay(r2, r4)
                    if (r2 == 0) goto L28
                    r0 = 0
                L28:
                    int r2 = getAdLoadFrequency()
                    int[] r3 = com.ss.union.game.sdk.core.init.config.GameOptionConfig.AnonymousClass1.a
                    com.ss.union.game.sdk.core.init.bean.GameSDKOption$j$a r4 = getGroupType()
                    int r4 = r4.ordinal()
                    r3 = r3[r4]
                    r4 = 1
                    switch(r3) {
                        case 1: goto L47;
                        case 2: goto L3f;
                        case 3: goto L3d;
                        default: goto L3c;
                    }
                L3c:
                    goto L4a
                L3d:
                    r1 = 1
                    goto L4a
                L3f:
                    if (r2 > 0) goto L42
                    goto L3d
                L42:
                    int r2 = r0 % r2
                    if (r2 != 0) goto L4a
                    goto L3d
                L47:
                    if (r0 != 0) goto L4a
                    goto L3d
                L4a:
                    com.ss.union.game.sdk.common.util.SPUtils r2 = com.ss.union.game.sdk.core.init.config.GameOptionConfig.GameOption.a()
                    java.lang.String r3 = "sp_key_splash_ad_config_load_last_time"
                    long r5 = java.lang.System.currentTimeMillis()
                    r2.put(r3, r5)
                    int r0 = r0 + r4
                    a(r0)
                L5b:
                    return r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.ss.union.game.sdk.core.init.config.GameOptionConfig.GameOption.SplashAdTest.isCanLoadAd():boolean");
            }

            public static GameSDKOption.j.a getGroupType() {
                GameSDKOption.j.a aVarA = a();
                return aVarA == null ? GameSDKOption.j.a.A : aVarA;
            }

            public static int getAdLoadFrequency() {
                return GameOption.b().getInt(c, 0);
            }

            private static GameSDKOption.j.a a() {
                String string = GameOption.b().getString(a);
                if (TextUtils.isEmpty(string)) {
                    return null;
                }
                if (string.equals("A") || string.equals("B1") || string.equals("B2") || string.equals("B3")) {
                    return GameSDKOption.j.a.valueOf(string);
                }
                return null;
            }

            private static int b() {
                return GameOption.b().getInt(e, 0);
            }

            private static void a(int i) {
                GameOption.b().put(e, i);
            }
        }

        public static final class AntiAddiction {
            public static final String ANTI_ADDICTION_UNVERIFIED_GUEST = "GUEST";
            public static final String ANTI_ADDICTION_UNVERIFIED_TEENAGE = "MINOR";

            public static final class Account {
                private static final String a = "sp_key_anti_addiction_account_show_real_name_window_auto";
                private static final String b = "sp_key_anti_addiction_account_real_name_window_can_close";

                /* JADX INFO: Access modifiers changed from: private */
                public static void b(GameSDKOption.b bVar) {
                    if (bVar != null) {
                        GameOption.b().put(a, bVar.a);
                        GameOption.b().put(b, bVar.b);
                    }
                }

                public static boolean isShowRealNameWindowAuto() {
                    return GameOption.b().getBoolean(a, true);
                }

                public static boolean isRealNameWindowCanClose() {
                    return GameOption.b().getBoolean(b, false);
                }
            }

            public static final class Device {
                private static final String a = "sp_key_anti_addiction_device_show_real_name_window_auto";
                private static final String b = "sp_key_anti_addiction_device_real_name_window_can_close";

                /* JADX INFO: Access modifiers changed from: private */
                public static void b(GameSDKOption.b bVar) {
                    if (bVar != null) {
                        GameOption.b().put(a, bVar.a);
                        GameOption.b().put(b, bVar.b);
                    }
                }

                public static boolean isShowRealNameWindowAuto() {
                    return GameOption.b().getBoolean(a, true);
                }

                public static boolean isRealNameWindowCanClose() {
                    return GameOption.b().getBoolean(b, false);
                }
            }

            public static final class Pay {
                private static final String a = "sp_key_pay_anti_addiction";

                /* JADX INFO: Access modifiers changed from: private */
                public static void b(GameSDKOption.h hVar) {
                    if (hVar != null) {
                        GameOption.b().put(a, hVar.a);
                    }
                }

                public static boolean isEnableAntiAddiction() {
                    return GameOption.b().getBoolean(a, true);
                }
            }
        }

        public static final class VApp {
            private static final String a = "sp_key_vapp_float_ball_can_show";
            private static final String b = "sp_key_vapp_float_ball_icon";
            private static final String c = "sp_key_vapp_float_ball_click_url";

            /* JADX INFO: Access modifiers changed from: private */
            public static void b(GameSDKOption.k kVar) {
                if (kVar != null) {
                    GameOption.b().put(a, kVar.a);
                    GameOption.b().put(b, kVar.b);
                    GameOption.b().put(c, kVar.c);
                }
            }

            public static boolean floatBallCanShow() {
                return GameOption.b().getBoolean(a, false);
            }

            public static String floatBallIcon() {
                return GameOption.b().getString(b, "");
            }

            public static String floatBallClickUrl() {
                return GameOption.b().getString(c, "https://u.ohayoo.cn/v/front/community");
            }
        }

        public static class AgeTip {
            private static final String a = "SP_KEY_AGE_TIPS_CONTENT";

            /* JADX INFO: Access modifiers changed from: private */
            public static void b(GameSDKOption.a aVar) {
                if (aVar != null) {
                    GameOption.b().put(a, aVar.a);
                }
            }

            public static String ageTipsContent() {
                return GameOption.b().getString(a, "");
            }
        }

        public static class IdentifyAward {
            private static final String a = "SP_KEY_IDENTIFY_AWARD_ENABLE";
            private static final String b = "SP_KEY_IDENTIFY_AWARD_NAME";
            private static final String c = "SP_KEY_IDENTIFY_AWARD_ICON";
            private static final String d = "SP_KEY_IDENTIFY_AWARD_CUSTOM_INFO";

            /* JADX INFO: Access modifiers changed from: private */
            public static void b(GameSDKOption.f fVar) {
                GameOption.b().put(a, fVar.e);
                GameOption.b().put(b, fVar.f);
                GameOption.b().put(c, fVar.g);
                GameOption.b().put(d, fVar.h);
            }

            public static boolean awardEnable() {
                return GameOption.b().getBoolean(a, false);
            }

            public static String awardName() {
                return GameOption.b().getString(b, "");
            }

            public static String awardIcon() {
                return GameOption.b().getString(c, "");
            }

            public static String awardCustomInfo() {
                return GameOption.b().getString(d, "");
            }

            public static String toAwardJson() throws JSONException {
                JSONObject jSONObject = new JSONObject();
                try {
                    if (awardEnable()) {
                        jSONObject.putOpt(GameSDKOption.f.a, Boolean.valueOf(awardEnable()));
                        jSONObject.putOpt(GameSDKOption.f.b, awardName());
                        jSONObject.putOpt(GameSDKOption.f.c, awardIcon());
                        jSONObject.putOpt(GameSDKOption.f.d, awardCustomInfo());
                    } else {
                        jSONObject.putOpt(GameSDKOption.f.a, false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return jSONObject.toString();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static SPUtils b() {
            return SPUtils.getInstance("lg_game_option");
        }
    }
}
