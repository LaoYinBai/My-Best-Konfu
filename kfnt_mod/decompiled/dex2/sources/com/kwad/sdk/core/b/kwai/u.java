package com.kwad.sdk.core.b.kwai;

import com.heytap.mcssdk.mode.Message;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.core.response.model.AggregatePageEntranceInfo;
import com.kwad.sdk.core.response.model.NewsInfo;
import com.kwad.sdk.core.response.model.PageInfo;
import com.kwad.sdk.core.response.model.PhotoAd;
import com.kwad.sdk.core.response.model.PhotoInfo;
import com.kwad.sdk.core.response.model.PreloadData;
import com.kwad.sdk.core.response.model.VideoPlayerStatus;
import com.kwad.sdk.internal.api.SceneImpl;
import com.kwad.sdk.live.mode.LiveInfo;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class u implements com.kwad.sdk.core.d<AdTemplate> {
    @Override // com.kwad.sdk.core.d
    public void a(AdTemplate adTemplate, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        adTemplate.mOriginJString = jSONObject.optString("mOriginJString");
        if (jSONObject.opt("mOriginJString") == JSONObject.NULL) {
            adTemplate.mOriginJString = "";
        }
        adTemplate.posId = jSONObject.optLong("posId");
        adTemplate.type = jSONObject.optInt(Message.TYPE);
        adTemplate.contentType = jSONObject.optInt("contentType");
        adTemplate.adInfoList = new ArrayList();
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("adInfo");
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                AdInfo adInfo = new AdInfo();
                adInfo.parseJson(jSONArrayOptJSONArray.optJSONObject(i));
                adTemplate.adInfoList.add(adInfo);
            }
        }
        adTemplate.photoInfo = new PhotoInfo();
        adTemplate.photoInfo.parseJson(jSONObject.optJSONObject("photoInfo"));
        adTemplate.photoAd = new PhotoAd();
        adTemplate.photoAd.parseJson(jSONObject.optJSONObject("photoAd"));
        adTemplate.newsInfo = new NewsInfo();
        adTemplate.newsInfo.parseJson(jSONObject.optJSONObject("newsInfo"));
        adTemplate.aggregatePageEntranceInfo = new AggregatePageEntranceInfo();
        adTemplate.aggregatePageEntranceInfo.parseJson(jSONObject.optJSONObject("aggregatePageEntranceInfo"));
        adTemplate.needHide = jSONObject.optBoolean("needHide");
        adTemplate.impAdExtra = jSONObject.optString("impAdExtra");
        if (jSONObject.opt("impAdExtra") == JSONObject.NULL) {
            adTemplate.impAdExtra = "";
        }
        adTemplate.mLiveInfo = new LiveInfo();
        adTemplate.mLiveInfo.parseJson(jSONObject.optJSONObject("liveInfo"));
        adTemplate.llsid = jSONObject.optLong("llsid");
        adTemplate.mIsFromContent = jSONObject.optBoolean("mIsFromContent");
        adTemplate.isDrawAdHasLook = jSONObject.optBoolean("isDrawAdHasLook");
        adTemplate.extra = jSONObject.optString(PushConstants.EXTRA);
        if (jSONObject.opt(PushConstants.EXTRA) == JSONObject.NULL) {
            adTemplate.extra = "";
        }
        adTemplate.mUniqueId = jSONObject.optString("mUniqueId");
        if (jSONObject.opt("mUniqueId") == JSONObject.NULL) {
            adTemplate.mUniqueId = "";
        }
        adTemplate.mBidEcpm = jSONObject.optInt("mBidEcpm");
        adTemplate.mAdScene = new SceneImpl();
        adTemplate.mAdScene.parseJson(jSONObject.optJSONObject("mAdScene"));
        adTemplate.realShowType = jSONObject.optInt("realShowType");
        adTemplate.mInitVoiceStatus = jSONObject.optInt("mInitVoiceStatus");
        adTemplate.mPreloadData = new PreloadData();
        adTemplate.mPreloadData.parseJson(jSONObject.optJSONObject("mPreloadData"));
        adTemplate.mMediaPlayerType = jSONObject.optInt("mMediaPlayerType");
        adTemplate.mIsTubeEpisodeList = jSONObject.optBoolean("mIsTubeEpisodeList");
        adTemplate.mVideoPlayerStatus = new VideoPlayerStatus();
        adTemplate.mVideoPlayerStatus.parseJson(jSONObject.optJSONObject("mVideoPlayerStatus"));
        adTemplate.mOutClickTimeParam = jSONObject.optLong("mOutClickTimeParam", new Long("-1").longValue());
        adTemplate.mVisibleTimeParam = jSONObject.optLong("mVisibleTimeParam", new Long("-1").longValue());
        adTemplate.mIsLeftSlipStatus = jSONObject.optInt("mIsLeftSlipStatus");
        adTemplate.mPhotoResponseType = jSONObject.optInt("mPhotoResponseType");
        adTemplate.mPageInfo = new PageInfo();
        adTemplate.mPageInfo.parseJson(jSONObject.optJSONObject("mPageInfo"));
        adTemplate.mPcursor = jSONObject.optString("mPcursor");
        if (jSONObject.opt("mPcursor") == JSONObject.NULL) {
            adTemplate.mPcursor = "";
        }
        adTemplate.mHasEntryAdClick = jSONObject.optBoolean("mHasEntryAdClick");
        adTemplate.mIsNotNeedAvatarGuider = jSONObject.optBoolean("mIsNotNeedAvatarGuider");
        adTemplate.mRewardVerifyCalled = jSONObject.optBoolean("mRewardVerifyCalled");
        adTemplate.isWebViewDownload = jSONObject.optBoolean("isWebViewDownload");
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(AdTemplate adTemplate, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "mOriginJString", adTemplate.mOriginJString);
        com.kwad.sdk.utils.t.a(jSONObject, "posId", adTemplate.posId);
        com.kwad.sdk.utils.t.a(jSONObject, Message.TYPE, adTemplate.type);
        com.kwad.sdk.utils.t.a(jSONObject, "contentType", adTemplate.contentType);
        com.kwad.sdk.utils.t.a(jSONObject, "adInfo", adTemplate.adInfoList);
        com.kwad.sdk.utils.t.a(jSONObject, "photoInfo", adTemplate.photoInfo);
        com.kwad.sdk.utils.t.a(jSONObject, "photoAd", adTemplate.photoAd);
        com.kwad.sdk.utils.t.a(jSONObject, "newsInfo", adTemplate.newsInfo);
        com.kwad.sdk.utils.t.a(jSONObject, "aggregatePageEntranceInfo", adTemplate.aggregatePageEntranceInfo);
        com.kwad.sdk.utils.t.a(jSONObject, "needHide", adTemplate.needHide);
        com.kwad.sdk.utils.t.a(jSONObject, "impAdExtra", adTemplate.impAdExtra);
        com.kwad.sdk.utils.t.a(jSONObject, "liveInfo", adTemplate.mLiveInfo);
        com.kwad.sdk.utils.t.a(jSONObject, "llsid", adTemplate.llsid);
        com.kwad.sdk.utils.t.a(jSONObject, "mIsFromContent", adTemplate.mIsFromContent);
        com.kwad.sdk.utils.t.a(jSONObject, "isDrawAdHasLook", adTemplate.isDrawAdHasLook);
        com.kwad.sdk.utils.t.a(jSONObject, PushConstants.EXTRA, adTemplate.extra);
        com.kwad.sdk.utils.t.a(jSONObject, "mUniqueId", adTemplate.mUniqueId);
        com.kwad.sdk.utils.t.a(jSONObject, "mBidEcpm", adTemplate.mBidEcpm);
        com.kwad.sdk.utils.t.a(jSONObject, "mAdScene", adTemplate.mAdScene);
        com.kwad.sdk.utils.t.a(jSONObject, "realShowType", adTemplate.realShowType);
        com.kwad.sdk.utils.t.a(jSONObject, "mInitVoiceStatus", adTemplate.mInitVoiceStatus);
        com.kwad.sdk.utils.t.a(jSONObject, "mPreloadData", adTemplate.mPreloadData);
        com.kwad.sdk.utils.t.a(jSONObject, "mMediaPlayerType", adTemplate.mMediaPlayerType);
        com.kwad.sdk.utils.t.a(jSONObject, "mIsTubeEpisodeList", adTemplate.mIsTubeEpisodeList);
        com.kwad.sdk.utils.t.a(jSONObject, "mVideoPlayerStatus", adTemplate.mVideoPlayerStatus);
        com.kwad.sdk.utils.t.a(jSONObject, "mOutClickTimeParam", adTemplate.mOutClickTimeParam);
        com.kwad.sdk.utils.t.a(jSONObject, "mVisibleTimeParam", adTemplate.mVisibleTimeParam);
        com.kwad.sdk.utils.t.a(jSONObject, "mIsLeftSlipStatus", adTemplate.mIsLeftSlipStatus);
        com.kwad.sdk.utils.t.a(jSONObject, "mPhotoResponseType", adTemplate.mPhotoResponseType);
        com.kwad.sdk.utils.t.a(jSONObject, "mPageInfo", adTemplate.mPageInfo);
        com.kwad.sdk.utils.t.a(jSONObject, "mPcursor", adTemplate.mPcursor);
        com.kwad.sdk.utils.t.a(jSONObject, "mHasEntryAdClick", adTemplate.mHasEntryAdClick);
        com.kwad.sdk.utils.t.a(jSONObject, "mIsNotNeedAvatarGuider", adTemplate.mIsNotNeedAvatarGuider);
        com.kwad.sdk.utils.t.a(jSONObject, "mRewardVerifyCalled", adTemplate.mRewardVerifyCalled);
        com.kwad.sdk.utils.t.a(jSONObject, "isWebViewDownload", adTemplate.isWebViewDownload);
        return jSONObject;
    }
}
