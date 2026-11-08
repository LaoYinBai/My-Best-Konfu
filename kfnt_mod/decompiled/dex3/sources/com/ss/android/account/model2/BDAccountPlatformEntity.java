package com.ss.android.account.model2;

import androidx.annotation.Nullable;
import com.ss.union.game.sdk.account.R;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class BDAccountPlatformEntity {
    public static final String PLAT_NAME_EMAIL = "email";
    public static final String PLAT_NAME_MOBILE = "mobile";
    public static final String PLAT_NAME_XIAOMI = "xiaomi";
    public String mCreateTIme;
    public long mExpire;
    public long mExpireIn;
    public long mModifyTime;
    public final String mName;
    public long mUserId;
    public final int mVerbose;
    public static final String PLAT_NAME_FLYME = "flyme";
    public static final String PLAT_NAME_HUAWEI = "huawei";
    public static final String PLAT_NAME_WEIBO = "sina_weibo";
    public static final String PLAT_NAME_TELECOM = "telecom";
    public static final String PLAT_NAME_TENCENT = "qq_weibo";
    public static final String PLAT_NAME_RENREN = "renren_sns";
    public static final String PLAT_NAME_KAIXIN = "kaixin_sns";
    public static final String PLAT_NAME_QZONE = "qzone_sns";
    public static final String PLAT_NAME_HUOSHAN = "live_stream";
    public static final String PLAT_NAME_DOUYIN = "aweme";
    public static final String PLAT_NAME_DOUYIN_NEW = "aweme_v2";
    public static final String PLAT_NAME_WX = "weixin";
    public static final String PLAT_NAME_GOOGLE = "google";
    public static final String PLAT_NAME_FB = "facebook";
    public static final String PLAT_NAME_TWITTER = "twitter";
    public static final String PLAT_NAME_INSTAGRAM = "instagram";
    public static final String PLAT_NAME_LINE = "line";
    public static final String PLAT_NAME_KAKAO = "kakaotalk";
    public static final String PLAT_NAME_VK = "vk";
    public static final String PLAT_NAME_TOUTIAO = "toutiao";
    public static final String PLAT_NAME_TOUTIAO_NEW = "toutiao_v2";
    public static final String PLAT_NAME_FLIPCHAT = "flipchat";
    public static final String PLAT_NAME_GOGOKID = "gogokid";
    public static final String[] ALL_PLATFORM = {PLAT_NAME_FLYME, PLAT_NAME_HUAWEI, PLAT_NAME_WEIBO, PLAT_NAME_TELECOM, PLAT_NAME_TENCENT, PLAT_NAME_RENREN, PLAT_NAME_KAIXIN, PLAT_NAME_QZONE, "mobile", PLAT_NAME_HUOSHAN, PLAT_NAME_DOUYIN, PLAT_NAME_DOUYIN_NEW, PLAT_NAME_WX, "xiaomi", "email", PLAT_NAME_GOOGLE, PLAT_NAME_FB, PLAT_NAME_TWITTER, PLAT_NAME_INSTAGRAM, PLAT_NAME_LINE, PLAT_NAME_KAKAO, PLAT_NAME_VK, PLAT_NAME_TOUTIAO, PLAT_NAME_TOUTIAO_NEW, PLAT_NAME_FLIPCHAT, PLAT_NAME_GOGOKID};
    public boolean mLogin = false;

    @Nullable
    public String mNickname = "";

    @Nullable
    public String mAvatar = null;

    @Nullable
    public String mPlatformUid = "";

    public static BDAccountPlatformEntity create(String str) {
        return create(str, 0);
    }

    public static BDAccountPlatformEntity create(String str, int i) {
        if (i != 0) {
            return new BDAccountPlatformEntity(str, i);
        }
        switch (str) {
            case "sina_weibo":
                i = R.string.ss_account_pname_weibo;
                break;
            case "qq_weibo":
                i = R.string.ss_account_pname_tencent;
                break;
            case "renren_sns":
                i = R.string.ss_account_pname_renren;
                break;
            case "kaixin_sns":
                i = R.string.ss_account_pname_kaixin;
                break;
            case "qzone_sns":
                i = R.string.ss_account_pname_qzone;
                break;
            case "mobile":
                i = R.string.ss_account_pname_mobile;
                break;
            case "weixin":
                i = R.string.ss_account_pname_weixin;
                break;
            case "flyme":
                i = R.string.ss_account_pname_flyme;
                break;
            case "huawei":
                i = R.string.ss_account_pname_huawei;
                break;
            case "telecom":
                i = R.string.ss_account_pname_telecom;
                break;
            case "xiaomi":
                i = R.string.ss_account_pname_xiaomi;
                break;
            case "email":
                i = R.string.ss_account_pname_email;
                break;
            case "live_stream":
                i = R.string.ss_account_pname_huoshan;
                break;
            case "aweme":
            case "aweme_v2":
                i = R.string.ss_account_pname_douyin;
                break;
            case "google":
                i = R.string.ss_account_pname_google;
                break;
            case "facebook":
                i = R.string.ss_account_pname_fb;
                break;
            case "twitter":
                i = R.string.ss_account_pname_twitter;
                break;
            case "line":
                i = R.string.ss_account_pname_line;
                break;
            case "kakaotalk":
                i = R.string.ss_account_pname_kakao;
                break;
            case "vk":
                i = R.string.ss_account_pname_vk;
                break;
            case "toutiao":
            case "toutiao_v2":
                i = R.string.ss_account_pname_toutiao;
                break;
            case "flipchat":
                i = R.string.ss_account_pname_flipchat;
                break;
            case "gogokid":
                i = R.string.ss_account_pname_gogokid;
                break;
        }
        return new BDAccountPlatformEntity(str, i);
    }

    public BDAccountPlatformEntity(String str, int i) {
        this.mName = str;
        this.mVerbose = i;
    }

    public void invalidate() {
        this.mLogin = false;
        this.mNickname = "";
        this.mAvatar = null;
        this.mPlatformUid = "";
        this.mExpire = 0L;
        this.mExpireIn = 0L;
        this.mUserId = 0L;
        this.mModifyTime = 0L;
    }
}
