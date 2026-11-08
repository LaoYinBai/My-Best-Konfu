package com.ss.union.game.sdk.core.base.debug.automatic_detection.constants;

import com.sigmob.sdk.common.mta.PointCategory;
import com.ss.union.game.sdk.core.base.constant.LGUris;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LGDetectionConstant {

    public static class AccountItem {
        public static final String DETECTION_ID_INVOKE_LOGIN = "登录接口调用，检测未通过，请尝试触发该功能或检查调用";
        public static final String DETECTION_ID_INVOKE_SWITCH = "切换账号接口调用，检测未通过，请尝试触发该功能或检查调用";
        public static final String DETECTION_ID_LOGIN_SUCCESS = "登录成功回调，检测未通过，请尝试触发该功能或检查调用";
        public static final String DETECTION_ID_SWITCH_SUCCESS = "切换账号成功回调，检测未通过，请尝试触发该功能或检查调用";
        public static final String DETECTION_ID_VISITOR_BIND = "游客绑定接口调用，检测未通过，请尝试触发该功能或检查调用";
        public static final String DETECTION_ID_VISITOR_BIND_SUCCESS = "游客绑定成功回调，检测未通过，请尝试触发该功能或检查调用";
    }

    public static class AdItem {
        public static final String DETECTION_ID_FULL_SCREEN_CLOSE = "全屏视频广告关闭回调，检测未通过";
        public static final String DETECTION_ID_FULL_SCREEN_LOAD = "全屏视频广告加载，检测未通过";
        public static final String DETECTION_ID_FULL_SCREEN_LOAD_SUCCESS = "全屏视频广告加载成功回调，检测未通过";
        public static final String DETECTION_ID_FULL_SCREEN_SHOW = "全屏视频广告展示调用，检测未通过";
        public static final String DETECTION_ID_FULL_SCREEN_SHOW_SUCCESS = "全屏视频广告展示成功回调，检测未通过";
        public static final String DETECTION_ID_REWARD_CLOSE = "激励视频广告关闭回调，检测未通过";
        public static final String DETECTION_ID_REWARD_LOAD = "激励视频广告加载，检测未通过";
        public static final String DETECTION_ID_REWARD_LOAD_SUCCESS = "激励视频广告加载成功回调，检测未通过";
        public static final String DETECTION_ID_REWARD_SHOW = "激励视频广告展示调用，检测未通过";
        public static final String DETECTION_ID_REWARD_SHOW_SUCCESS = "激励视频广告展示成功回调，检测未通过";
        public static final String DETECTION_ID_SPLASH_LOAD = "开屏广告加载，检测未通过";
        public static final String DETECTION_ID_SPLASH_LOAD_SUCCESS = "开屏广告加载成功回调，检测未通过";
    }

    public static class AppLogItem {
        public static final String DETECTION_ID_DID_RETURN = "深度转化返回did，检测未通过";
        public static final String DETECTION_ID_SCHEME = "url scheme与后台比对，检测未通过";
    }

    public static final class AutomaticDetection {
        public static final String URL_CREATE_REPORT = LGUris.path("/game_sdk/light_game/detect");
    }

    public static class InitItem {
        public static final String DETECTION_ID_APP_LOG = "初始化深度转化SDK";
        public static final String DETECTION_ID_DOU_YIN = "初始化抖音SDK";
        public static final String DETECTION_ID_PANGOLIN = "初始化穿山甲SDK";
    }

    public static class Params {
        public static final String MEDIATION_FULL_SCREEN_ID = "聚合全屏视频广告位id";
        public static final String MEDIATION_REWARD_ID = "聚合激励视频广告位id";
        public static final String PANGOLIN_BANNER = "穿山甲banner广告位id";
        public static final String PANGOLIN_SPLASH = "穿山甲开屏广告位id";
    }

    public static class PrivacyItem {
        public static final String DETECTION_ID_BASIC_INFO = "游戏名称/主体名称/注册地址/更新时间/生效日期，不为空，检测未通过";
        public static final String DETECTION_ID_WINDOW_CALLBACK = "隐私弹窗回调成功，检测未通过";
        public static final String DETECTION_ID_WINDOW_INVOKE = "隐私弹窗调用，检测未通过";
    }

    public enum DetectionType {
        INIT(PointCategory.INIT, "初始化"),
        AD("ad", "广告"),
        APP_LOG("applog", "深度转化"),
        PRIVACY(PointCategory.PRIVACY, "隐私弹窗"),
        ACCOUNT("account", "登录");

        final String a;
        final String b;

        DetectionType(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public String getKey() {
            return this.a;
        }

        public String getValue() {
            return this.b;
        }
    }

    public enum DetectionState {
        UN_DETECTION(0),
        PASS(1),
        FAIL(2);

        final int a;

        DetectionState(int i) {
            this.a = i;
        }

        public int getValue() {
            return this.a;
        }

        public static DetectionState intToEnum(int i) {
            switch (i) {
                case 1:
                    return PASS;
                case 2:
                    return FAIL;
                default:
                    return UN_DETECTION;
            }
        }
    }
}
