package com.bytedance.sdk.account.api;

import com.bytedance.sdk.account.user.IBDAccountUserEntity;
import com.ss.android.account.model2.BDAccountPlatformEntity;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IBDAccount {
    void addListener(BDAccountEventListener bDAccountEventListener);

    String getAvatarUrl();

    String getBgImgUrl();

    int getCanFoundByPhone();

    int getCanSyncShare();

    int getCountryCode();

    int getDisplayOcrEntrance();

    int getFollowersCount();

    int getFollowingCount();

    boolean getHasPassword();

    long getMediaId();

    String getPgcAvatarUrl();

    long getPgcMediaId();

    String getPgcName();

    BDAccountPlatformEntity getPlatformByName(String str);

    String getRecommendHintMessage();

    String getScreenName();

    String getSessionKey();

    int getShareShowIcon();

    String getUserAuth();

    String getUserBirthday();

    String getUserDecoration();

    String getUserDescription();

    String getUserEmail();

    int getUserGender();

    long getUserId();

    String getUserIndustry();

    IBDAccountUserEntity getUserInfo();

    int getUserIsBlock();

    int getUserIsBlocking();

    String getUserLocation();

    String getUserMobile();

    String getUserName();

    int getUserPrivacyExtend();

    String getVerifiedContent();

    int getVisitorsCount();

    void invalidateSession(boolean z);

    boolean isLogin();

    boolean isRecommendAllowed();

    boolean isUserToutiao();

    boolean isUserVerified();

    void notifyBDAccountEvent(BDAccountEvent bDAccountEvent);

    void onUserInfoRefreshed(IBDAccountUserEntity iBDAccountUserEntity, boolean z);

    void removeListener(BDAccountEventListener bDAccountEventListener);

    void saveData();

    void setAvatarUrl(String str);

    void setBgImgUrl(String str);

    void setCanFoundByPhone(int i);

    void setCanSyncShare(int i);

    void setCountryCode(int i);

    void setFollowersCount(int i);

    void setFollowingCount(int i);

    void setHasPassword(boolean z);

    void setLogin(boolean z);

    void setMediaId(long j);

    void setPgcAvatarUrl(String str);

    void setPgcMediaId(long j);

    void setPgcName(String str);

    void setPlatformMap(HashMap<String, BDAccountPlatformEntity> map);

    void setRecommendAllowed(boolean z);

    void setRecommendHintMessage(String str);

    void setScreenName(String str);

    void setSessionKey(String str);

    void setShareShowIcon(int i);

    void setUserAuth(String str);

    void setUserBirthday(String str);

    void setUserDecoration(String str);

    void setUserDescription(String str);

    void setUserEmail(String str);

    void setUserGender(int i);

    void setUserId(long j);

    void setUserIndustry(String str);

    void setUserIsBlock(int i);

    void setUserIsBlocking(int i);

    void setUserLocation(String str);

    void setUserMobile(String str);

    void setUserName(String str);

    void setUserPrivacyExtend(int i);

    void setUserToutiao(boolean z);

    void setUserVerified(boolean z);

    void setVerifiedContent(String str);

    void setVisitorsCount(int i);

    void stopUpdateUserInfo();

    void tryUpdateUserInfo(String str);

    void updateUserInfo(IBDAccountUserEntity iBDAccountUserEntity);

    void wapLoginSync();
}
