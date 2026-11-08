package com.bytedance.sdk.account.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.webkit.CookieSyncManager;
import com.bytedance.b.a;
import com.bytedance.sdk.account.IMonitor;
import com.bytedance.sdk.account.api.AccountDef;
import com.bytedance.sdk.account.api.BDAccountEvent;
import com.bytedance.sdk.account.api.BDAccountEventListener;
import com.bytedance.sdk.account.api.IBDAccount;
import com.bytedance.sdk.account.api.IBDAccountAPI;
import com.bytedance.sdk.account.api.call.AbsApiCall;
import com.bytedance.sdk.account.api.call.BaseApiResponse;
import com.bytedance.sdk.account.api.call.LogoutApiResponse;
import com.bytedance.sdk.account.api.call.MobileApiResponse;
import com.bytedance.sdk.account.api.call.UserApiResponse;
import com.bytedance.sdk.account.api.callback.GetAccountInfoCallback;
import com.bytedance.sdk.account.api.callback.UpdateTokenCallback;
import com.bytedance.sdk.account.api.pool.IApiController;
import com.bytedance.sdk.account.api.response.GetAccountInfoResponse;
import com.bytedance.sdk.account.api.response.LoginByTicketResponse;
import com.bytedance.sdk.account.api.response.UpdateTokenResponse;
import com.bytedance.sdk.account.constants.UserInfoThreadConstants;
import com.bytedance.sdk.account.job.UpdateTokenJob;
import com.bytedance.sdk.account.mobile.query.IUserQueryObj;
import com.bytedance.sdk.account.user.IBDAccountUserEntity;
import com.bytedance.sdk.account.utility.SharedPrefsEditorCompat;
import com.bytedance.sdk.account.utility.StringUtils;
import com.bytedance.sdk.account.utility.WeakContainer;
import com.bytedance.sdk.account.utility.WeakHandler;
import com.ss.android.account.BDAccountUserEntity;
import com.ss.android.account.TTAccountInit;
import com.ss.android.account.model2.BDAccountPlatformEntity;
import com.ss.android.token.TTTokenManager;
import com.ss.android.token.TTTokenMonitor;
import com.ss.union.game.sdk.account.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class BDAccountManager implements IBDAccount, IDispatchReceiver, WeakHandler.IHandler {
    public static final int DISPATCH_MESSAGE = 100;
    public static final int GENDER_FEMALE = 2;
    public static final int GENDER_MALE = 1;
    public static final int GENDER_NONE = 0;
    static final String KEY_AVATAR_URL = "avatar_url";
    private static final String KEY_BG_IMG_URL = "bg_img_url";
    private static final String KEY_CAN_BE_FOUND_BY_PHONE = "can_be_found_by_phone";
    private static final String KEY_CAN_SYNC_SHARE = "can_sync_share";
    static final String KEY_COUNTRY_CODE = "country_code";
    private static final String KEY_DISPLAY_OCR_ENTRANCE = "display_ocr_entrance";
    private static final String KEY_FOLLOWERS_COUNT = "followers_count";
    private static final String KEY_FOLLOWING_COUNT = "following_count";
    private static final String KEY_IS_BLOCKED = "is_blocked";
    private static final String KEY_IS_BLOCKING = "is_blocking";
    static final String KEY_IS_LOGIN = "is_login";
    static final String KEY_IS_NEW_USER = "is_new_user";
    private static final String KEY_IS_RECOMMEND_ALLOWED = "is_recommend_allowed";
    private static final String KEY_IS_SHARE_SHOW_ICON = "user_privacy_extend";
    private static final String KEY_IS_TOUTIAO = "is_toutiao";
    private static final String KEY_MEDIA_ID = "media_id";
    protected static final String KEY_PGC_AVATAR_URL = "pgc_avatar_url";
    protected static final String KEY_PGC_MEDIAID = "pgc_mediaid";
    protected static final String KEY_PGC_NAME = "pgc_name";
    private static final String KEY_PLATFROM_PREFIX = "_platform_";
    private static final String KEY_RECOMMEND_HINT_MESSAGE = "recommend_hint_message";
    static final String KEY_SCREEN_NAME = "screen_name";
    static final String KEY_SESSION = "session";
    static final String KEY_SESSION_KEY = "session_key";
    private static final String KEY_USER_AUTH_INFO = "user_auth_info";
    static final String KEY_USER_BIRTHDAY = "user_birthday";
    static final String KEY_USER_DECORATION = "user_decoration";
    static final String KEY_USER_DESCRIPTION = "user_description";
    static final String KEY_USER_EMAIL = "user_email";
    static final String KEY_USER_GENDER = "user_gender";
    static final String KEY_USER_HASPWD = "user_has_pwd";
    static final String KEY_USER_ID = "user_id";
    static final String KEY_USER_INDUSTRY = "user_industry";
    static final String KEY_USER_LOCATION = "user_location";
    static final String KEY_USER_MOBILE = "user_mobile";
    static final String KEY_USER_NAME = "user_name";
    private static final String KEY_USER_PRIVACY_EXTEND = "user_privacy_extend_value";
    static final String KEY_USER_VERIFIED = "user_verified";
    static final String KEY_VERIFIED_CONTENT = "verified_content";
    private static final String KEY_VISITORS_COUNT = "visitors_count";
    private static final String PKG_NAME = "com.bytedance.sdk.account";
    static final String SETTING_SP = "com.bytedance.sdk.account_setting";
    private static volatile IBDAccount sInstance;
    protected IBDAccountAPI mAccountAPI;
    private final BDAccountPlatformEntity[] mAllPlatforms;
    private int mCanFoundByPhone;
    private int mCanSyncShare;
    final Context mContext;
    private int mCountryCode;
    private boolean mDataLoaded;
    private int mGender;
    private boolean mHasPassword;
    private int mIsBlocked;
    private int mIsBlocking;
    private boolean mIsNewUser;
    private boolean mIsRecommendAllowed;
    private boolean mIsToutiao;
    private int mShareShowIcon;
    IBDAccountUserEntity mUserInfo;
    private int mUserPrivacyExtend;
    private boolean mUserVerified;
    static final BDAccountPlatformEntity WEIBO = new BDAccountPlatformEntity(a.ai, R.string.ss_account_pname_weibo);
    static final BDAccountPlatformEntity TENCENT = new BDAccountPlatformEntity(a.ak, R.string.ss_account_pname_tencent);
    static final BDAccountPlatformEntity RENREN = new BDAccountPlatformEntity(a.al, R.string.ss_account_pname_renren);
    static final BDAccountPlatformEntity KAIXIN = new BDAccountPlatformEntity(a.am, R.string.ss_account_pname_kaixin);
    static final BDAccountPlatformEntity QZONE = new BDAccountPlatformEntity(a.an, R.string.ss_account_pname_qzone);
    static final BDAccountPlatformEntity MOBILE = new BDAccountPlatformEntity("mobile", R.string.ss_account_pname_mobile);
    static final BDAccountPlatformEntity WEIXIN = new BDAccountPlatformEntity(a.ar, R.string.ss_account_pname_weixin);
    static final BDAccountPlatformEntity FLYME = new BDAccountPlatformEntity(a.ag, R.string.ss_account_pname_flyme);
    static final BDAccountPlatformEntity HUAWEI = new BDAccountPlatformEntity(a.ah, R.string.ss_account_pname_huawei);
    static final BDAccountPlatformEntity TELECOM = new BDAccountPlatformEntity("telecom", R.string.ss_account_pname_telecom);
    static final BDAccountPlatformEntity XIAOMI = new BDAccountPlatformEntity(a.as, R.string.ss_account_pname_xiaomi);
    static final BDAccountPlatformEntity EMAIL = new BDAccountPlatformEntity("email", R.string.ss_account_pname_email);
    static final BDAccountPlatformEntity HUOSHAN = new BDAccountPlatformEntity(a.ap, R.string.ss_account_pname_huoshan);
    static final BDAccountPlatformEntity DOUYIN = new BDAccountPlatformEntity(a.aq, R.string.ss_account_pname_douyin);
    static final BDAccountPlatformEntity DOUYINNEW = new BDAccountPlatformEntity("aweme_v2", R.string.ss_account_pname_douyin);
    static final BDAccountPlatformEntity GOOGLE = new BDAccountPlatformEntity("google", R.string.ss_account_pname_google);
    static final BDAccountPlatformEntity FB = new BDAccountPlatformEntity("facebook", R.string.ss_account_pname_fb);
    static final BDAccountPlatformEntity TWITTER = new BDAccountPlatformEntity("twitter", R.string.ss_account_pname_twitter);
    static final BDAccountPlatformEntity INSTAGRAM = new BDAccountPlatformEntity("instagram", R.string.ss_account_pname_instagram);
    static final BDAccountPlatformEntity LINE = new BDAccountPlatformEntity("line", R.string.ss_account_pname_line);
    static final BDAccountPlatformEntity KAKAO = new BDAccountPlatformEntity("kakaotalk", R.string.ss_account_pname_kakao);
    static final BDAccountPlatformEntity VK = new BDAccountPlatformEntity("vk", R.string.ss_account_pname_vk);
    static final BDAccountPlatformEntity TOUTIAO = new BDAccountPlatformEntity("toutiao", R.string.ss_account_pname_toutiao);
    static final BDAccountPlatformEntity TOUTIAONEW = new BDAccountPlatformEntity("toutiao_v2", R.string.ss_account_pname_toutiao);
    static final BDAccountPlatformEntity FLIPCHAT = new BDAccountPlatformEntity("flipchat", R.string.ss_account_pname_flipchat);
    private static final BDAccountPlatformEntity[] ALL = {WEIBO, TENCENT, RENREN, KAIXIN, QZONE, MOBILE, WEIXIN, FLYME, HUAWEI, TELECOM, XIAOMI, EMAIL, HUOSHAN, DOUYIN, DOUYINNEW, GOOGLE, FB, TWITTER, INSTAGRAM, LINE, KAKAO, VK, TOUTIAO, TOUTIAONEW, FLIPCHAT};
    private static List<ApiResponseHandler> apiResponseHandlers = new ArrayList();
    private final int MSG_USER_INFO_RETRY = 1000;
    private String mUserLocation = "";
    private String mAvatarUrl = "";
    private String mBgImgUrl = "";
    private String mUserBirthday = "";
    private String mUserDescription = "";
    private String mEmail = "";
    private String mUserIndustry = "";
    public long mMediaId = 0;
    private String mMobile = "";
    private String mUserName = "";
    private String mScreenName = "";
    private String mRecommendHintMessage = "";
    public String mUserAuthInfo = "";
    private String mUserDecoration = "";
    private long mUserId = 0;
    private String mVerifiedContent = "";
    private String mSessionKey = "";
    private boolean mIsLogin = false;
    private long mPgcMediaId = 0;
    private String mPgcAvatarUrl = "";
    private String mPgcName = "";
    public int mDisplayOcrEntrance = 0;
    public int mFollowingCount = 0;
    public int mFollowersCount = 0;
    public int mVisitorsCount = 0;
    protected final WeakHandler mHandler = new WeakHandler(Looper.getMainLooper(), this);
    private WeakContainer<BDAccountEventListener> mAccountListener = new WeakContainer<>();

    private interface ApiResponseHandler {
        void handle(BaseApiResponse baseApiResponse);
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void setUserMobile(String str) {
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void notifyBDAccountEvent(BDAccountEvent bDAccountEvent) {
        synchronized (this.mAccountListener) {
            Iterator<BDAccountEventListener> it = this.mAccountListener.iterator();
            while (it.hasNext()) {
                BDAccountEventListener next = it.next();
                if (next != null) {
                    next.onReceiveAccountEvent(bDAccountEvent);
                }
            }
        }
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public BDAccountPlatformEntity getPlatformByName(String str) {
        for (BDAccountPlatformEntity bDAccountPlatformEntity : this.mAllPlatforms) {
            if (bDAccountPlatformEntity != null && bDAccountPlatformEntity.mName.equals(str)) {
                return bDAccountPlatformEntity;
            }
        }
        return null;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void setPlatformMap(HashMap<String, BDAccountPlatformEntity> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        for (BDAccountPlatformEntity bDAccountPlatformEntity : this.mAllPlatforms) {
            bDAccountPlatformEntity.mLogin = false;
            BDAccountPlatformEntity bDAccountPlatformEntity2 = map.get(bDAccountPlatformEntity.mName);
            if (bDAccountPlatformEntity2 == null) {
                bDAccountPlatformEntity.invalidate();
            } else {
                if (!bDAccountPlatformEntity.mLogin) {
                    bDAccountPlatformEntity.mLogin = true;
                }
                bDAccountPlatformEntity.mExpire = bDAccountPlatformEntity2.mExpire;
                bDAccountPlatformEntity.mExpireIn = bDAccountPlatformEntity2.mExpireIn;
                bDAccountPlatformEntity.mNickname = bDAccountPlatformEntity2.mNickname;
                bDAccountPlatformEntity.mAvatar = bDAccountPlatformEntity2.mAvatar;
                bDAccountPlatformEntity.mPlatformUid = bDAccountPlatformEntity2.mPlatformUid;
                bDAccountPlatformEntity.mUserId = bDAccountPlatformEntity2.mUserId;
                bDAccountPlatformEntity.mModifyTime = bDAccountPlatformEntity2.mModifyTime;
            }
        }
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public String getSessionKey() {
        return this.mSessionKey;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void setSessionKey(String str) {
        this.mSessionKey = str;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void addListener(BDAccountEventListener bDAccountEventListener) {
        synchronized (this.mAccountListener) {
            this.mAccountListener.add(bDAccountEventListener);
        }
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void removeListener(BDAccountEventListener bDAccountEventListener) {
        synchronized (this.mAccountListener) {
            this.mAccountListener.remove(bDAccountEventListener);
        }
    }

    private BDAccountManager(Context context) {
        apiResponseHandlers.add(new UserAPiHandler());
        apiResponseHandlers.add(new LogoutHandle());
        this.mContext = context.getApplicationContext();
        this.mDataLoaded = false;
        this.mAllPlatforms = new BDAccountPlatformEntity[]{MOBILE, QZONE, WEIBO, TENCENT, RENREN, KAIXIN, WEIXIN, FLYME, HUAWEI, TELECOM, XIAOMI, EMAIL, HUOSHAN, DOUYIN, DOUYINNEW, LINE, KAKAO, VK, TOUTIAO, TOUTIAONEW, FLIPCHAT};
        loadData();
        this.mAccountAPI = BDAccountDelegate.createBDAccountApi(this.mContext);
    }

    private void handleList(BaseApiResponse baseApiResponse) {
        Iterator<ApiResponseHandler> it = apiResponseHandlers.iterator();
        while (it.hasNext()) {
            it.next().handle(baseApiResponse);
        }
    }

    private void invokeCall(ResponseCallable responseCallable) {
        if (responseCallable.apiCall != null) {
            AbsApiCall absApiCall = responseCallable.apiCall;
            absApiCall.dispatchOnResponse(responseCallable.response);
            IApiController iApiController = absApiCall.mJobController;
            if (iApiController != null) {
                iApiController.releaseRef();
            }
        }
    }

    @Override // com.bytedance.sdk.account.impl.IDispatchReceiver
    public void handleDispatch(ResponseCallable responseCallable) {
        if (responseCallable.response != 0) {
            handleList(responseCallable.response);
            invokeCall(responseCallable);
        }
    }

    @Override // com.bytedance.sdk.account.utility.WeakHandler.IHandler
    public void handleMsg(Message message) {
        if (message.what == 100 && (message.obj instanceof ResponseCallable)) {
            handleDispatch((ResponseCallable) message.obj);
        }
        if (message.what == 1000) {
            this.mHandler.removeMessages(1000);
            tryUpdateUserInfo("polling");
        }
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void tryUpdateUserInfo(String str) {
        if (!isLogin()) {
            sendQueryUserInfoMsg();
        } else if (this.mAccountAPI != null) {
            this.mAccountAPI.getNewAccountInfo(str, new GetAccountInfoCallback() { // from class: com.bytedance.sdk.account.impl.BDAccountManager.1
                @Override // com.bytedance.sdk.account.CommonCallBack
                public void onSuccess(GetAccountInfoResponse getAccountInfoResponse) {
                    BDAccountManager.this.sendQueryUserInfoMsg();
                }

                @Override // com.bytedance.sdk.account.CommonCallBack
                public void onError(GetAccountInfoResponse getAccountInfoResponse, int i) {
                    BDAccountManager.this.sendQueryUserInfoMsg();
                }
            });
        }
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void stopUpdateUserInfo() {
        if (this.mHandler != null) {
            this.mHandler.removeMessages(1000);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendQueryUserInfoMsg() {
        if (this.mHandler != null) {
            this.mHandler.sendEmptyMessageDelayed(1000, TTAccountInit.getExtraConfig() != null ? TTAccountInit.getExtraConfig().getUpdateInfoInterval() : 600000L);
        }
    }

    Handler getDispatchHandler() {
        return this.mHandler;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void invalidateSession(boolean z) throws JSONException {
        if (this.mIsLogin) {
            this.mIsNewUser = false;
            this.mIsLogin = false;
            this.mUserId = 0L;
            this.mSessionKey = "";
            this.mUserName = "";
            this.mGender = 0;
            this.mScreenName = "";
            this.mVerifiedContent = "";
            this.mUserDescription = "";
            this.mUserLocation = "";
            this.mUserIndustry = "";
            this.mIsBlocked = 0;
            this.mIsBlocking = 0;
            this.mUserDecoration = "";
            this.mUserBirthday = "";
            this.mUserVerified = false;
            this.mIsRecommendAllowed = false;
            this.mIsToutiao = false;
            this.mCountryCode = 0;
            this.mFollowingCount = 0;
            this.mFollowersCount = 0;
            this.mVisitorsCount = 0;
            this.mHasPassword = false;
            this.mMediaId = 0L;
            this.mBgImgUrl = "";
            this.mEmail = "";
            this.mMobile = "";
            this.mDisplayOcrEntrance = 0;
            this.mPgcAvatarUrl = "";
            this.mPgcMediaId = 0L;
            this.mPgcName = "";
            this.mUserAuthInfo = "";
            for (BDAccountPlatformEntity bDAccountPlatformEntity : this.mAllPlatforms) {
                bDAccountPlatformEntity.invalidate();
            }
            saveData();
            setAppLogInfo(this.mUserId, this.mSessionKey);
        }
        if (z) {
            notifyUserSessionExpired();
        }
    }

    static IBDAccount instance(Context context) {
        if (sInstance == null) {
            synchronized (BDAccountManager.class) {
                if (sInstance == null) {
                    sInstance = new BDAccountManager(context);
                }
            }
        }
        return sInstance;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void onUserInfoRefreshed(IBDAccountUserEntity iBDAccountUserEntity, boolean z) throws JSONException {
        boolean z2;
        if (iBDAccountUserEntity == null) {
            return;
        }
        long userId = iBDAccountUserEntity.getUserId();
        boolean z3 = false;
        boolean z4 = true;
        if (userId <= 0) {
            if (this.mIsLogin) {
                this.mIsNewUser = false;
                this.mIsLogin = false;
                this.mUserId = 0L;
                this.mUserName = "";
                this.mGender = 0;
                this.mScreenName = "";
                this.mVerifiedContent = "";
                this.mAvatarUrl = "";
                this.mUserBirthday = "";
                this.mUserLocation = "";
                this.mUserIndustry = "";
                this.mUserDecoration = "";
                this.mUserDescription = "";
                this.mUserVerified = false;
                this.mIsRecommendAllowed = false;
                this.mSessionKey = "";
                this.mFollowingCount = 0;
                this.mFollowersCount = 0;
                this.mVisitorsCount = 0;
                this.mIsBlocked = 0;
                this.mIsBlocking = 0;
                this.mIsToutiao = false;
                this.mHasPassword = false;
                this.mMediaId = 0L;
                this.mBgImgUrl = "";
                this.mDisplayOcrEntrance = 0;
                this.mPgcAvatarUrl = "";
                this.mPgcMediaId = 0L;
                this.mPgcName = "";
                this.mUserAuthInfo = "";
                this.mUserInfo = null;
                for (BDAccountPlatformEntity bDAccountPlatformEntity : this.mAllPlatforms) {
                    bDAccountPlatformEntity.invalidate();
                }
                z2 = true;
                z3 = true;
            } else {
                z2 = false;
            }
            z4 = false;
        } else {
            this.mUserInfo = iBDAccountUserEntity;
            if (this.mIsLogin) {
                z2 = false;
            } else {
                this.mIsLogin = true;
                try {
                    CookieSyncManager.getInstance().sync();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                z2 = true;
            }
            if (iBDAccountUserEntity.isNewUser) {
                this.mIsNewUser = true;
            }
            if (this.mUserId != userId) {
                this.mUserId = userId;
                z2 = true;
                z3 = true;
            }
            if (!StringUtils.equal(this.mSessionKey, iBDAccountUserEntity.getSessionKey())) {
                this.mSessionKey = iBDAccountUserEntity.getSessionKey();
                z2 = true;
                z3 = true;
            }
            if (!StringUtils.equal(this.mMobile, iBDAccountUserEntity.getMobile())) {
                this.mMobile = iBDAccountUserEntity.getMobile();
                z2 = true;
            }
            if (!StringUtils.equal(this.mEmail, iBDAccountUserEntity.getEmail())) {
                this.mEmail = iBDAccountUserEntity.getEmail();
                z2 = true;
            }
            if (this.mHasPassword != iBDAccountUserEntity.hasPassword) {
                this.mHasPassword = iBDAccountUserEntity.hasPassword;
                z2 = true;
            }
            if (this.mCountryCode != iBDAccountUserEntity.countryCode) {
                this.mCountryCode = iBDAccountUserEntity.countryCode;
                z2 = true;
            }
            if (onUserRefreshPlatformChanged(iBDAccountUserEntity)) {
                z2 = true;
            }
            if (iBDAccountUserEntity instanceof BDAccountUserEntity) {
                BDAccountUserEntity bDAccountUserEntity = (BDAccountUserEntity) iBDAccountUserEntity;
                if (!StringUtils.equal(this.mUserName, bDAccountUserEntity.userName)) {
                    this.mUserName = bDAccountUserEntity.userName;
                    z2 = true;
                }
                if (!StringUtils.equal(this.mScreenName, bDAccountUserEntity.screenName)) {
                    this.mScreenName = bDAccountUserEntity.screenName;
                    z2 = true;
                }
                if (!StringUtils.equal(this.mVerifiedContent, bDAccountUserEntity.verifiedContent)) {
                    this.mVerifiedContent = bDAccountUserEntity.verifiedContent;
                    z2 = true;
                }
                if (this.mGender != bDAccountUserEntity.gender) {
                    this.mGender = bDAccountUserEntity.gender;
                    z2 = true;
                }
                if (!StringUtils.equal(this.mUserDescription, bDAccountUserEntity.description)) {
                    this.mUserDescription = bDAccountUserEntity.description;
                    z2 = true;
                }
                if (!StringUtils.equal(this.mAvatarUrl, bDAccountUserEntity.avatarUrl)) {
                    this.mAvatarUrl = bDAccountUserEntity.avatarUrl;
                    z2 = true;
                }
                if (this.mUserVerified != bDAccountUserEntity.user_verified) {
                    this.mUserVerified = bDAccountUserEntity.user_verified;
                    z2 = true;
                }
                if (!StringUtils.equal(this.mUserLocation, bDAccountUserEntity.area)) {
                    this.mUserLocation = bDAccountUserEntity.area;
                    z2 = true;
                }
                if (!StringUtils.equal(this.mUserIndustry, bDAccountUserEntity.industry)) {
                    this.mUserIndustry = bDAccountUserEntity.industry;
                    z2 = true;
                }
                if (this.mIsToutiao != bDAccountUserEntity.isToutiao) {
                    this.mIsToutiao = bDAccountUserEntity.isToutiao;
                    z2 = true;
                }
                if (this.mIsBlocking != bDAccountUserEntity.isBlocking) {
                    this.mIsBlocking = bDAccountUserEntity.isBlocking;
                    z2 = true;
                }
                if (this.mIsBlocked != bDAccountUserEntity.isBlocked) {
                    this.mIsBlocked = bDAccountUserEntity.isBlocked;
                    z2 = true;
                }
                if (this.mIsRecommendAllowed != bDAccountUserEntity.isRecommendAllowed) {
                    this.mIsRecommendAllowed = bDAccountUserEntity.isRecommendAllowed;
                    z2 = true;
                }
                if (!StringUtils.equal(this.mRecommendHintMessage, bDAccountUserEntity.recommendHintMessage)) {
                    this.mRecommendHintMessage = bDAccountUserEntity.recommendHintMessage;
                    z2 = true;
                }
                if (this.mCanFoundByPhone != bDAccountUserEntity.canFoundByPhone) {
                    this.mCanFoundByPhone = bDAccountUserEntity.canFoundByPhone;
                    z2 = true;
                }
                if (this.mShareShowIcon != bDAccountUserEntity.shareShowIcon) {
                    this.mShareShowIcon = bDAccountUserEntity.shareShowIcon;
                    z2 = true;
                }
                if (this.mUserPrivacyExtend != bDAccountUserEntity.userPrivacyExtend) {
                    this.mUserPrivacyExtend = bDAccountUserEntity.userPrivacyExtend;
                    z2 = true;
                }
                if (this.mCanSyncShare != bDAccountUserEntity.canSyncShare) {
                    this.mCanSyncShare = bDAccountUserEntity.canSyncShare;
                    z2 = true;
                }
                if (!this.mUserDecoration.equals(bDAccountUserEntity.user_decoration)) {
                    this.mUserDecoration = bDAccountUserEntity.user_decoration;
                    z2 = true;
                }
                if (this.mMediaId != bDAccountUserEntity.mMediaId) {
                    this.mMediaId = bDAccountUserEntity.mMediaId;
                    z2 = true;
                }
                if (!StringUtils.equal(this.mPgcAvatarUrl, bDAccountUserEntity.pgcAvatarUrl)) {
                    this.mPgcAvatarUrl = bDAccountUserEntity.pgcAvatarUrl;
                    z2 = true;
                }
                if (!StringUtils.equal(this.mPgcName, bDAccountUserEntity.pgcName)) {
                    this.mPgcName = bDAccountUserEntity.pgcName;
                    z2 = true;
                }
                if (this.mPgcMediaId != bDAccountUserEntity.pgcMediaId) {
                    this.mPgcMediaId = bDAccountUserEntity.pgcMediaId;
                    z2 = true;
                }
                if ((!TextUtils.isEmpty(this.mBgImgUrl) && !TextUtils.isEmpty(bDAccountUserEntity.mBgImgUrl) && !this.mBgImgUrl.equals(bDAccountUserEntity.mBgImgUrl)) || ((TextUtils.isEmpty(this.mBgImgUrl) && !TextUtils.isEmpty(bDAccountUserEntity.mBgImgUrl)) || (!TextUtils.isEmpty(this.mBgImgUrl) && TextUtils.isEmpty(bDAccountUserEntity.mBgImgUrl)))) {
                    this.mBgImgUrl = bDAccountUserEntity.mBgImgUrl;
                    z2 = true;
                }
                if (this.mDisplayOcrEntrance != bDAccountUserEntity.mDisplayOcrEntrance) {
                    this.mDisplayOcrEntrance = bDAccountUserEntity.mDisplayOcrEntrance;
                    z2 = true;
                }
                if (!StringUtils.equal(this.mUserAuthInfo, bDAccountUserEntity.user_auth_info)) {
                    this.mUserAuthInfo = bDAccountUserEntity.user_auth_info;
                    z2 = true;
                }
            }
            this.mIsLogin = true;
        }
        if (z2) {
            saveData();
        }
        if (z3) {
            setAppLogInfo(this.mUserId, this.mSessionKey);
        }
        if (z2 && z) {
            notifyUserInfoChange(z4);
        }
    }

    private void notifyUserInfoChange(boolean z) {
        BDAccountEvent bDAccountEvent = new BDAccountEvent(0);
        bDAccountEvent.success = z;
        synchronized (this.mAccountListener) {
            Iterator<BDAccountEventListener> it = this.mAccountListener.iterator();
            while (it.hasNext()) {
                it.next().onReceiveAccountEvent(bDAccountEvent);
            }
        }
    }

    private void notifyUserSessionExpired() {
        BDAccountEvent bDAccountEvent = new BDAccountEvent(2);
        bDAccountEvent.success = false;
        synchronized (this.mAccountListener) {
            Iterator<BDAccountEventListener> it = this.mAccountListener.iterator();
            while (it.hasNext()) {
                it.next().onReceiveAccountEvent(bDAccountEvent);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void notifyLogout(IBDAccount iBDAccount, String str) {
        BDAccountEvent bDAccountEvent = new BDAccountEvent(1);
        bDAccountEvent.logoutScene = getLogoutScene(str);
        iBDAccount.notifyBDAccountEvent(bDAccountEvent);
    }

    private static int getLogoutScene(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        char c2 = 65535;
        int iHashCode = str.hashCode();
        if (iHashCode != -2085722047) {
            if (iHashCode != -355378050) {
                if (iHashCode == 90865289 && str.equals(AccountDef.LogoutScene.SESSION_LOGOUT)) {
                    c2 = 1;
                }
            } else if (str.equals(AccountDef.LogoutScene.USER_LOGOUT)) {
                c2 = 0;
            }
        } else if (str.equals(AccountDef.LogoutScene.CANCEL_ACCOUNT_LOGOUT)) {
            c2 = 2;
        }
        switch (c2) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
            default:
                return 3;
        }
    }

    private boolean onUserRefreshPlatformChanged(IBDAccountUserEntity iBDAccountUserEntity) {
        boolean z = false;
        for (BDAccountPlatformEntity bDAccountPlatformEntity : this.mAllPlatforms) {
            bDAccountPlatformEntity.mLogin = false;
            BDAccountPlatformEntity bDAccountPlatformEntity2 = iBDAccountUserEntity.getBindMap().get(bDAccountPlatformEntity.mName);
            if (bDAccountPlatformEntity2 == null) {
                bDAccountPlatformEntity.invalidate();
            } else {
                if (!bDAccountPlatformEntity.mLogin) {
                    bDAccountPlatformEntity.mLogin = true;
                    z = true;
                }
                bDAccountPlatformEntity.mExpire = bDAccountPlatformEntity2.mExpire;
                bDAccountPlatformEntity.mExpireIn = bDAccountPlatformEntity2.mExpireIn;
                bDAccountPlatformEntity.mNickname = bDAccountPlatformEntity2.mNickname;
                bDAccountPlatformEntity.mAvatar = bDAccountPlatformEntity2.mAvatar;
                bDAccountPlatformEntity.mPlatformUid = bDAccountPlatformEntity2.mPlatformUid;
                bDAccountPlatformEntity.mUserId = bDAccountPlatformEntity2.mUserId;
                bDAccountPlatformEntity.mModifyTime = bDAccountPlatformEntity2.mModifyTime;
            }
        }
        return z;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public boolean isLogin() {
        return this.mIsLogin;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void setLogin(boolean z) {
        this.mIsLogin = z;
        SharedPreferences.Editor editorEdit = this.mContext.getSharedPreferences(SETTING_SP, 0).edit();
        editorEdit.putBoolean(KEY_IS_LOGIN, this.mIsLogin);
        editorEdit.apply();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public long getUserId() {
        return this.mUserId;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void setUserId(long j) {
        this.mUserId = j;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public String getAvatarUrl() {
        return this.mAvatarUrl;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void setAvatarUrl(String str) {
        this.mAvatarUrl = str;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public String getUserName() {
        return this.mUserName;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void setUserName(String str) {
        this.mUserName = str;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public int getUserGender() {
        return this.mGender;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void setUserGender(int i) {
        if (i < 0 || i > 2) {
            return;
        }
        this.mGender = i;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public String getScreenName() {
        return this.mScreenName;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void setScreenName(String str) {
        this.mScreenName = str;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public String getUserDescription() {
        return this.mUserDescription;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void setUserDescription(String str) {
        this.mUserDescription = str;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public String getBgImgUrl() {
        return this.mBgImgUrl;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void setBgImgUrl(String str) {
        this.mBgImgUrl = str;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public String getUserBirthday() {
        return this.mUserBirthday;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void setUserBirthday(String str) {
        this.mUserBirthday = str;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public String getUserLocation() {
        return this.mUserLocation;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void setUserLocation(String str) {
        this.mUserLocation = str;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public String getUserIndustry() {
        return this.mUserIndustry;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void setUserIndustry(String str) {
        this.mUserIndustry = str;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public String getUserMobile() {
        return this.mMobile;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public int getCountryCode() {
        return this.mCountryCode;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void setCountryCode(int i) {
        this.mCountryCode = i;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public String getUserEmail() {
        return this.mEmail;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void setUserEmail(String str) {
        this.mEmail = str;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public long getPgcMediaId() {
        return this.mPgcMediaId;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void setPgcMediaId(long j) {
        this.mPgcMediaId = j;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public String getPgcAvatarUrl() {
        return this.mPgcAvatarUrl;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void setPgcAvatarUrl(String str) {
        this.mPgcAvatarUrl = str;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public String getPgcName() {
        return this.mPgcName;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void setPgcName(String str) {
        this.mPgcName = str;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public boolean getHasPassword() {
        return this.mHasPassword;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void setHasPassword(boolean z) {
        this.mHasPassword = z;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public int getCanSyncShare() {
        return this.mCanSyncShare;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void setCanSyncShare(int i) {
        this.mCanSyncShare = i;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public int getDisplayOcrEntrance() {
        return this.mDisplayOcrEntrance;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public String getUserAuth() {
        return this.mUserAuthInfo;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void setUserAuth(String str) {
        this.mUserAuthInfo = str;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public String getVerifiedContent() {
        return this.mVerifiedContent;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void setVerifiedContent(String str) {
        this.mVerifiedContent = str;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public boolean isUserVerified() {
        return this.mUserVerified;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void setUserVerified(boolean z) {
        this.mUserVerified = z;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public boolean isRecommendAllowed() {
        return this.mIsRecommendAllowed;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void setRecommendAllowed(boolean z) {
        this.mIsRecommendAllowed = z;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public String getRecommendHintMessage() {
        return this.mRecommendHintMessage;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void setRecommendHintMessage(String str) {
        this.mRecommendHintMessage = str;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public int getFollowingCount() {
        return this.mFollowingCount;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public int getFollowersCount() {
        return this.mFollowersCount;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public int getVisitorsCount() {
        return this.mVisitorsCount;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public long getMediaId() {
        return this.mMediaId;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void setMediaId(long j) {
        this.mMediaId = j;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void setFollowingCount(int i) {
        this.mFollowingCount = i;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void setFollowersCount(int i) {
        this.mFollowersCount = i;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void setVisitorsCount(int i) {
        this.mVisitorsCount = i;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public int getCanFoundByPhone() {
        return this.mCanFoundByPhone;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void setCanFoundByPhone(int i) {
        this.mCanFoundByPhone = i;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public int getShareShowIcon() {
        return this.mShareShowIcon;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void setShareShowIcon(int i) {
        this.mShareShowIcon = i;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public int getUserPrivacyExtend() {
        return this.mUserPrivacyExtend;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void setUserPrivacyExtend(int i) {
        this.mUserPrivacyExtend = i;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public int getUserIsBlock() {
        return this.mIsBlocked;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void setUserIsBlock(int i) {
        this.mIsBlocked = i;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public int getUserIsBlocking() {
        return this.mIsBlocking;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void setUserIsBlocking(int i) {
        this.mIsBlocking = i;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public boolean isUserToutiao() {
        return this.mIsToutiao;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void setUserToutiao(boolean z) {
        this.mIsToutiao = z;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public String getUserDecoration() {
        return this.mUserDecoration;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void setUserDecoration(String str) {
        this.mUserDecoration = str;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void updateUserInfo(IBDAccountUserEntity iBDAccountUserEntity) throws JSONException {
        this.mUserInfo = iBDAccountUserEntity;
        onUserInfoRefreshed(iBDAccountUserEntity, false);
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public IBDAccountUserEntity getUserInfo() {
        return this.mUserInfo;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void wapLoginSync() {
        setLogin(true);
        syncTokenInfo();
    }

    private void syncTokenInfo() {
        if (this.mAccountAPI != null) {
            final String tokenBeatUrl = TTTokenManager.getTokenBeatUrl(false, true);
            if (TextUtils.isEmpty(tokenBeatUrl)) {
                return;
            }
            UpdateTokenJob.updateToken(this.mContext, tokenBeatUrl, new UpdateTokenCallback() { // from class: com.bytedance.sdk.account.impl.BDAccountManager.2
                @Override // com.bytedance.sdk.account.CommonCallBack
                public void onSuccess(UpdateTokenResponse updateTokenResponse) {
                }

                @Override // com.bytedance.sdk.account.CommonCallBack
                public void onError(UpdateTokenResponse updateTokenResponse, int i) {
                    if (updateTokenResponse != null && UserInfoThreadConstants.SESSION_EXPIRED.equalsIgnoreCase(updateTokenResponse.errorName)) {
                        TTTokenManager.onSessionExpired(tokenBeatUrl, (List) null, (AbsApiCall) null);
                    } else {
                        TTTokenMonitor.monitorToken("tt_token_beat", (List) null, i, updateTokenResponse != null ? updateTokenResponse.errorMsg : "");
                    }
                }
            }).start();
        }
    }

    private void loadPlatform(SharedPreferences sharedPreferences) {
        for (int i = 0; i < this.mAllPlatforms.length; i++) {
            this.mAllPlatforms[i].mLogin = false;
            BDAccountPlatformEntity bDAccountPlatformEntity = this.mAllPlatforms[i];
            try {
                if (!TextUtils.isEmpty(bDAccountPlatformEntity.mName)) {
                    String string = sharedPreferences.getString(KEY_PLATFROM_PREFIX + bDAccountPlatformEntity.mName, null);
                    if (string != null) {
                        JSONObject jSONObject = new JSONObject(string);
                        if (jSONObject.optString("mName", "").equals(bDAccountPlatformEntity.mName)) {
                            if (jSONObject.has("mNickname")) {
                                bDAccountPlatformEntity.mNickname = jSONObject.optString("mNickname", null);
                            }
                            if (jSONObject.has("mAvatar")) {
                                bDAccountPlatformEntity.mAvatar = jSONObject.optString("mAvatar", null);
                            }
                            if (jSONObject.has("mPlatformUid")) {
                                bDAccountPlatformEntity.mPlatformUid = jSONObject.optString("mPlatformUid", null);
                            }
                            if (jSONObject.has("mExpire")) {
                                bDAccountPlatformEntity.mExpire = jSONObject.optLong("mExpire", bDAccountPlatformEntity.mExpire);
                            }
                            if (jSONObject.has("mExpireIn")) {
                                bDAccountPlatformEntity.mExpireIn = jSONObject.optLong("mExpireIn", bDAccountPlatformEntity.mExpireIn);
                            }
                            if (jSONObject.has("isLogin")) {
                                bDAccountPlatformEntity.mLogin = jSONObject.optBoolean("isLogin", false);
                            }
                            if (jSONObject.has("mUserId")) {
                                bDAccountPlatformEntity.mUserId = jSONObject.optLong("mUserId", 0L);
                            }
                            if (jSONObject.has("mModifyTime")) {
                                bDAccountPlatformEntity.mModifyTime = jSONObject.optLong("mModifyTime");
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void loadData() {
        if (this.mDataLoaded) {
            return;
        }
        this.mDataLoaded = true;
        SharedPreferences sharedPreferences = this.mContext.getSharedPreferences(SETTING_SP, 0);
        this.mIsLogin = sharedPreferences.getBoolean(KEY_IS_LOGIN, false);
        this.mUserId = sharedPreferences.getLong("user_id", 0L);
        this.mIsNewUser = sharedPreferences.getBoolean(KEY_IS_NEW_USER, false);
        this.mSessionKey = sharedPreferences.getString(KEY_SESSION_KEY, "");
        this.mUserName = sharedPreferences.getString("user_name", "");
        this.mGender = sharedPreferences.getInt(KEY_USER_GENDER, 0);
        this.mScreenName = sharedPreferences.getString(KEY_SCREEN_NAME, "");
        this.mVerifiedContent = sharedPreferences.getString(KEY_VERIFIED_CONTENT, "");
        this.mUserVerified = sharedPreferences.getBoolean(KEY_USER_VERIFIED, false);
        this.mAvatarUrl = sharedPreferences.getString(KEY_AVATAR_URL, "");
        this.mUserBirthday = sharedPreferences.getString(KEY_USER_BIRTHDAY, "");
        this.mUserLocation = sharedPreferences.getString(KEY_USER_LOCATION, "");
        this.mUserIndustry = sharedPreferences.getString(KEY_USER_INDUSTRY, "");
        this.mEmail = sharedPreferences.getString(KEY_USER_EMAIL, "");
        this.mMobile = sharedPreferences.getString(KEY_USER_MOBILE, "");
        this.mUserDecoration = sharedPreferences.getString(KEY_USER_DECORATION, "");
        this.mUserDescription = sharedPreferences.getString(KEY_USER_DESCRIPTION, "");
        this.mIsRecommendAllowed = sharedPreferences.getBoolean(KEY_IS_RECOMMEND_ALLOWED, false);
        this.mRecommendHintMessage = sharedPreferences.getString(KEY_RECOMMEND_HINT_MESSAGE, "");
        this.mIsBlocked = sharedPreferences.getInt(KEY_IS_BLOCKED, 0);
        this.mIsBlocking = sharedPreferences.getInt(KEY_IS_BLOCKING, 0);
        this.mIsToutiao = sharedPreferences.getBoolean(KEY_IS_TOUTIAO, false);
        this.mHasPassword = sharedPreferences.getBoolean(KEY_USER_HASPWD, false);
        this.mCountryCode = sharedPreferences.getInt(KEY_COUNTRY_CODE, 0);
        this.mPgcMediaId = sharedPreferences.getLong(KEY_PGC_MEDIAID, 0L);
        this.mPgcAvatarUrl = sharedPreferences.getString(KEY_PGC_AVATAR_URL, "");
        this.mPgcName = sharedPreferences.getString(KEY_PGC_NAME, "");
        this.mCanFoundByPhone = sharedPreferences.getInt(KEY_CAN_BE_FOUND_BY_PHONE, 1);
        this.mCanSyncShare = sharedPreferences.getInt(KEY_CAN_SYNC_SHARE, 0);
        this.mShareShowIcon = sharedPreferences.getInt(KEY_IS_SHARE_SHOW_ICON, 0);
        this.mUserPrivacyExtend = sharedPreferences.getInt(KEY_USER_PRIVACY_EXTEND, 2147483646);
        this.mBgImgUrl = sharedPreferences.getString(KEY_BG_IMG_URL, "");
        this.mFollowingCount = sharedPreferences.getInt(KEY_FOLLOWING_COUNT, 0);
        this.mFollowersCount = sharedPreferences.getInt(KEY_FOLLOWERS_COUNT, 0);
        this.mVisitorsCount = sharedPreferences.getInt(KEY_VISITORS_COUNT, 0);
        this.mMediaId = sharedPreferences.getLong(KEY_MEDIA_ID, 0L);
        this.mBgImgUrl = sharedPreferences.getString(KEY_BG_IMG_URL, "");
        this.mDisplayOcrEntrance = sharedPreferences.getInt(KEY_DISPLAY_OCR_ENTRANCE, 0);
        this.mUserAuthInfo = sharedPreferences.getString(KEY_USER_AUTH_INFO, "");
        if (this.mIsLogin && this.mUserId <= 0) {
            this.mIsLogin = false;
            this.mUserId = 0L;
        } else if (!this.mIsLogin && this.mUserId > 0) {
            this.mUserId = 0L;
        }
        loadPlatform(sharedPreferences);
        if (this.mUserId > 0) {
            setAppLogInfo(this.mUserId, this.mSessionKey);
        }
        this.mUserInfo = getAccountEntity();
    }

    private void savePlatform(SharedPreferences.Editor editor) throws JSONException {
        for (BDAccountPlatformEntity bDAccountPlatformEntity : this.mAllPlatforms) {
            if (bDAccountPlatformEntity.mLogin) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("mName", bDAccountPlatformEntity.mName);
                    jSONObject.put("mNickname", bDAccountPlatformEntity.mNickname);
                    jSONObject.put("mAvatar", bDAccountPlatformEntity.mAvatar);
                    jSONObject.put("mPlatformUid", bDAccountPlatformEntity.mPlatformUid);
                    jSONObject.put("mExpire", bDAccountPlatformEntity.mExpire);
                    jSONObject.put("mExpireIn", bDAccountPlatformEntity.mExpireIn);
                    jSONObject.put("isLogin", bDAccountPlatformEntity.mLogin);
                    jSONObject.put("mUserId", bDAccountPlatformEntity.mUserId);
                    jSONObject.put("mModifyTime", bDAccountPlatformEntity.mModifyTime);
                    editor.putString(KEY_PLATFROM_PREFIX + bDAccountPlatformEntity.mName, jSONObject.toString());
                } catch (Exception unused) {
                }
            }
        }
        editor.apply();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccount
    public void saveData() throws JSONException {
        SharedPreferences.Editor editorEdit = this.mContext.getSharedPreferences(SETTING_SP, 0).edit();
        savePlatform(editorEdit);
        editorEdit.remove(KEY_SESSION);
        editorEdit.putBoolean(KEY_IS_LOGIN, this.mIsLogin);
        editorEdit.putLong("user_id", this.mUserId);
        editorEdit.putString(KEY_SESSION_KEY, this.mSessionKey);
        editorEdit.putString("user_name", this.mUserName);
        editorEdit.putString(KEY_VERIFIED_CONTENT, this.mVerifiedContent);
        editorEdit.putInt(KEY_USER_GENDER, this.mGender);
        editorEdit.putString(KEY_SCREEN_NAME, this.mScreenName);
        editorEdit.putBoolean(KEY_USER_VERIFIED, this.mUserVerified);
        editorEdit.putString(KEY_AVATAR_URL, this.mAvatarUrl);
        editorEdit.putBoolean(KEY_IS_NEW_USER, this.mIsNewUser);
        editorEdit.putString(KEY_USER_EMAIL, this.mEmail);
        editorEdit.putString(KEY_USER_MOBILE, this.mMobile);
        editorEdit.putInt(KEY_IS_BLOCKED, this.mIsBlocked);
        editorEdit.putInt(KEY_IS_BLOCKING, this.mIsBlocking);
        editorEdit.putBoolean(KEY_IS_TOUTIAO, this.mIsToutiao);
        editorEdit.putBoolean(KEY_USER_HASPWD, this.mHasPassword);
        editorEdit.putInt(KEY_COUNTRY_CODE, this.mCountryCode);
        editorEdit.putString(KEY_USER_LOCATION, this.mUserLocation);
        editorEdit.putString(KEY_USER_INDUSTRY, this.mUserIndustry);
        editorEdit.putString(KEY_USER_DECORATION, this.mUserDecoration);
        editorEdit.putString(KEY_USER_BIRTHDAY, this.mUserBirthday);
        editorEdit.putLong(KEY_PGC_MEDIAID, this.mPgcMediaId);
        editorEdit.putString(KEY_PGC_AVATAR_URL, this.mPgcAvatarUrl);
        editorEdit.putString(KEY_PGC_NAME, this.mPgcName);
        editorEdit.putString(KEY_USER_DESCRIPTION, this.mUserDescription);
        editorEdit.putBoolean(KEY_IS_RECOMMEND_ALLOWED, this.mIsRecommendAllowed);
        editorEdit.putString(KEY_RECOMMEND_HINT_MESSAGE, this.mRecommendHintMessage);
        editorEdit.putInt(KEY_CAN_BE_FOUND_BY_PHONE, this.mCanFoundByPhone);
        editorEdit.putInt(KEY_CAN_SYNC_SHARE, this.mCanSyncShare);
        editorEdit.putInt(KEY_FOLLOWING_COUNT, this.mFollowingCount);
        editorEdit.putInt(KEY_FOLLOWERS_COUNT, this.mFollowersCount);
        editorEdit.putInt(KEY_VISITORS_COUNT, this.mVisitorsCount);
        editorEdit.putLong(KEY_MEDIA_ID, this.mMediaId);
        editorEdit.putString(KEY_BG_IMG_URL, this.mBgImgUrl);
        editorEdit.putInt(KEY_DISPLAY_OCR_ENTRANCE, this.mDisplayOcrEntrance);
        editorEdit.putString(KEY_USER_AUTH_INFO, this.mUserAuthInfo);
        editorEdit.putInt(KEY_IS_SHARE_SHOW_ICON, this.mShareShowIcon);
        editorEdit.putInt(KEY_USER_PRIVACY_EXTEND, this.mUserPrivacyExtend);
        SharedPrefsEditorCompat.apply(editorEdit);
    }

    private static class LogoutHandle implements ApiResponseHandler {
        private LogoutHandle() {
        }

        @Override // com.bytedance.sdk.account.impl.BDAccountManager.ApiResponseHandler
        public void handle(BaseApiResponse baseApiResponse) {
            if (baseApiResponse.api == 10001 && baseApiResponse.success) {
                IBDAccount iBDAccountInstance = BDAccountDelegate.instance(TTAccountInit.getConfig().getApplicationContext());
                iBDAccountInstance.invalidateSession(false);
                BDAccountManager.notifyLogout(iBDAccountInstance, baseApiResponse instanceof LogoutApiResponse ? ((LogoutApiResponse) baseApiResponse).mLogoutScene : "");
            }
        }
    }

    private static class UserAPiHandler implements ApiResponseHandler {
        private UserAPiHandler() {
        }

        @Override // com.bytedance.sdk.account.impl.BDAccountManager.ApiResponseHandler
        public void handle(BaseApiResponse baseApiResponse) {
            IBDAccountUserEntity iBDAccountUserEntity;
            Context applicationContext = TTAccountInit.getConfig().getApplicationContext();
            if (baseApiResponse instanceof UserApiResponse) {
                IBDAccountUserEntity iBDAccountUserEntity2 = ((UserApiResponse) baseApiResponse).userInfo;
                if (iBDAccountUserEntity2 == null || !(iBDAccountUserEntity2 instanceof IBDAccountUserEntity)) {
                    return;
                }
                BDAccountDelegate.instance(applicationContext).onUserInfoRefreshed(iBDAccountUserEntity2, true);
                return;
            }
            if (baseApiResponse instanceof MobileApiResponse) {
                T t = ((MobileApiResponse) baseApiResponse).mobileObj;
                if (t instanceof IUserQueryObj) {
                    BDAccountDelegate.instance(applicationContext).onUserInfoRefreshed(((IUserQueryObj) t).getUserInfo(), true);
                    return;
                }
                return;
            }
            if ((baseApiResponse instanceof LoginByTicketResponse) && (iBDAccountUserEntity = ((LoginByTicketResponse) baseApiResponse).mUserInfo) != null && (iBDAccountUserEntity instanceof IBDAccountUserEntity)) {
                BDAccountDelegate.instance(applicationContext).onUserInfoRefreshed(iBDAccountUserEntity, true);
            }
        }
    }

    public BDAccountUserEntity getAccountEntity() {
        BDAccountUserEntity bDAccountUserEntity = new BDAccountUserEntity();
        bDAccountUserEntity.userId = this.mUserId;
        bDAccountUserEntity.isNewUser = this.mIsNewUser;
        bDAccountUserEntity.sessionKey = this.mSessionKey;
        bDAccountUserEntity.userName = this.mUserName;
        bDAccountUserEntity.gender = this.mGender;
        bDAccountUserEntity.screenName = this.mScreenName;
        bDAccountUserEntity.verifiedContent = this.mVerifiedContent;
        bDAccountUserEntity.avatarUrl = this.mAvatarUrl;
        bDAccountUserEntity.birthday = this.mUserBirthday;
        bDAccountUserEntity.user_verified = this.mUserVerified;
        bDAccountUserEntity.area = this.mUserLocation;
        bDAccountUserEntity.industry = this.mUserIndustry;
        bDAccountUserEntity.user_decoration = this.mUserDecoration;
        bDAccountUserEntity.description = this.mUserDescription;
        bDAccountUserEntity.isRecommendAllowed = this.mIsRecommendAllowed;
        bDAccountUserEntity.recommendHintMessage = this.mRecommendHintMessage;
        bDAccountUserEntity.canFoundByPhone = this.mCanFoundByPhone;
        bDAccountUserEntity.canSyncShare = this.mCanSyncShare;
        bDAccountUserEntity.mBgImgUrl = this.mBgImgUrl;
        bDAccountUserEntity.mFollowingCount = this.mFollowingCount;
        bDAccountUserEntity.mFollowersCount = this.mFollowersCount;
        bDAccountUserEntity.mVisitorsCount = this.mVisitorsCount;
        bDAccountUserEntity.mMediaId = this.mMediaId;
        bDAccountUserEntity.email = this.mEmail;
        bDAccountUserEntity.user_auth_info = this.mUserAuthInfo;
        bDAccountUserEntity.mDisplayOcrEntrance = this.mDisplayOcrEntrance;
        bDAccountUserEntity.userPrivacyExtend = this.mUserPrivacyExtend;
        bDAccountUserEntity.shareShowIcon = this.mShareShowIcon;
        bDAccountUserEntity.isBlocked = this.mIsBlocked;
        bDAccountUserEntity.isBlocking = this.mIsBlocking;
        bDAccountUserEntity.isToutiao = this.mIsToutiao;
        bDAccountUserEntity.hasPassword = this.mHasPassword;
        bDAccountUserEntity.pgcAvatarUrl = this.mPgcAvatarUrl;
        bDAccountUserEntity.pgcMediaId = this.mMediaId;
        bDAccountUserEntity.pgcName = this.mPgcName;
        bDAccountUserEntity.countryCode = this.mCountryCode;
        for (BDAccountPlatformEntity bDAccountPlatformEntity : this.mAllPlatforms) {
            if (!TextUtils.isEmpty(bDAccountPlatformEntity.mName) && bDAccountPlatformEntity.mLogin) {
                bDAccountUserEntity.getBindMap().put(bDAccountPlatformEntity.mName, bDAccountPlatformEntity);
            }
        }
        return bDAccountUserEntity;
    }

    void setAppLogInfo(long j, String str) {
        try {
            IMonitor monitor = TTAccountInit.getConfig().getMonitor();
            if (monitor != null) {
                if (TextUtils.isEmpty(str)) {
                    str = "";
                }
                monitor.setAppLogInfo(j, str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
