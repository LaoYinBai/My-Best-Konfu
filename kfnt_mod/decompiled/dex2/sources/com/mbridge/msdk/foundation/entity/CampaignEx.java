package com.mbridge.msdk.foundation.entity;

import android.net.Uri;
import android.text.TextUtils;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.click.CommonJumpLoader;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.foundation.tools.z;
import com.mbridge.msdk.system.NoProGuard;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class CampaignEx extends DomainCampaignEx implements NoProGuard, Serializable {
    public static final int CAMPAIN_NV_T2_VALUE_3 = 3;
    public static final int CAMPAIN_NV_T2_VALUE_4 = 4;
    public static final String CLICKMODE_ON = "5";
    public static final int CLICK_TIMEOUT_INTERVAL_DEFAULT_VALUE = 2;
    public static final int C_UA_DEFAULT_VALUE = 1;
    private static final int DEFAULT_READY_RATE = 100;
    public static final String ENDCARD_URL = "endcard_url";
    public static final int FLAG_DEFAULT_SPARE_OFFER = -1;
    public static final int FLAG_IS_SPARE_OFFER = 1;
    public static final int FLAG_NOT_SPARE_OFFER = 0;
    public static final int IMP_UA_DEFAULT_VALUE = 1;
    public static final String JSON_AD_IMP_KEY = "sec";
    public static final String JSON_AD_IMP_VALUE = "url";
    public static final String JSON_KEY_ADVIMP = "adv_imp";
    public static final String JSON_KEY_AD_AKS = "aks";
    public static final String JSON_KEY_AD_AL = "al";
    public static final String JSON_KEY_AD_HTML = "ad_html";
    public static final String JSON_KEY_AD_K = "k";
    public static final String JSON_KEY_AD_MP = "mp";
    public static final String JSON_KEY_AD_Q = "q";
    public static final String JSON_KEY_AD_R = "r";
    public static final String JSON_KEY_AD_SOURCE_ID = "ad_source_id";
    public static final String JSON_KEY_AD_TMP_IDS = "tmp_ids";
    public static final String JSON_KEY_AD_TRACKING_DROPOUT_TRACK = "dropout_track";
    public static final String JSON_KEY_AD_TRACKING_IMPRESSION_T2 = "impression_t2";
    public static final String JSON_KEY_AD_TRACKING_PLYCMPT_TRACK = "plycmpt_track";
    public static final String JSON_KEY_AD_URL_LIST = "ad_url_list";
    public static final String JSON_KEY_AD_ZIP = "ad_tpl_url";
    public static final String JSON_KEY_APP_SIZE = "app_size";
    public static final String JSON_KEY_BANNER_HTML = "banner_html";
    public static final String JSON_KEY_BANNER_URL = "banner_url";
    public static final String JSON_KEY_BTY = "ctype";
    public static final String JSON_KEY_CAMPAIGN_UNITID = "unitId";
    public static final String JSON_KEY_CLICK_INTERVAL = "c_ct";
    public static final String JSON_KEY_CLICK_MODE = "click_mode";
    public static final String JSON_KEY_CLICK_TIMEOUT_INTERVAL = "c_toi";
    public static final String JSON_KEY_CLICK_URL = "click_url";
    public static final String JSON_KEY_CREATIVE_ID = "creative_id";
    public static final String JSON_KEY_CTA_TEXT = "ctatext";
    public static final String JSON_KEY_C_UA = "c_ua";
    public static final String JSON_KEY_DEEP_LINK_URL = "deep_link";
    public static final String JSON_KEY_DESC = "desc";
    public static final String JSON_KEY_ENCRYPT_PRICE = "encrypt_p";
    public static final String JSON_KEY_ENDCARD_CLICK = "endcard_click_result";
    public static final String JSON_KEY_EXT_DATA = "ext_data";
    public static final String JSON_KEY_FCA = "fca";
    public static final String JSON_KEY_FCB = "fcb";
    public static final String JSON_KEY_FLB = "flb";
    public static final String JSON_KEY_FLB_SKIP_TIME = "flb_skiptime";
    public static final String JSON_KEY_GIF_URL = "gif_url";
    public static final String JSON_KEY_GUIDELINES = "guidelines";
    public static final String JSON_KEY_HASMBTPLMARK = "hasMBTplMark";
    public static final String JSON_KEY_HB = "hb";
    public static final String JSON_KEY_ICON_URL = "icon_url";
    public static final String JSON_KEY_ID = "id";
    public static final String JSON_KEY_IMAGE_SIZE = "image_size";
    public static final String JSON_KEY_IMAGE_URL = "image_url";
    public static final String JSON_KEY_IMPRESSION_URL = "impression_url";
    public static final String JSON_KEY_IMP_UA = "imp_ua";
    public static final String JSON_KEY_JM_PD = "jm_pd";
    public static final String JSON_KEY_LANDING_TYPE = "landing_type";
    public static final String JSON_KEY_LINK_TYPE = "link_type";
    public static final String JSON_KEY_MAITVE = "maitve";
    public static final String JSON_KEY_MAITVESRC = "maitve_src";
    public static final String JSON_KEY_MRAID = "mraid";
    public static final String JSON_KEY_MRAIDFORH5 = "mraid_src";
    public static final String JSON_KEY_NOTICE_URL = "notice_url";
    public static final String JSON_KEY_NUMBER_RATING = "number_rating";
    public static final String JSON_KEY_NV_T2 = "nv_t2";
    public static final String JSON_KEY_OFFER_TYPE = "offer_type";
    public static final String JSON_KEY_PACKAGE_NAME = "package_name";
    public static final String JSON_KEY_PLCT = "plct";
    public static final String JSON_KEY_PLCTB = "plctb";
    public static final String JSON_KEY_PV_URLS = "pv_urls";
    public static final String JSON_KEY_READY_RATE = "ready_rate";
    public static final String JSON_KEY_RETARGET_OFFER = "retarget_offer";
    public static final String JSON_KEY_RETARGET_TYPE = "rtins_type";
    public static final String JSON_KEY_REWARD_AMOUNT = "reward_amount";
    public static final String JSON_KEY_REWARD_NAME = "reward_name";
    public static final String JSON_KEY_REWARD_PLUS = "rw_pl";
    public static final String JSON_KEY_REWARD_TEMPLATE = "rv";
    public static final String JSON_KEY_REWARD_VIDEO_MD5 = "md5_file";
    public static final String JSON_KEY_RS_IGNORE_CHECK_RULE = "rs_ignc_r";
    public static final String JSON_KEY_STAR = "rating";
    public static final String JSON_KEY_ST_IEX = "iex";
    public static final String JSON_KEY_ST_TS = "ts";
    public static final String JSON_KEY_TEMPLATE = "template";
    public static final String JSON_KEY_TIMESTAMP = "timestamp";
    public static final String JSON_KEY_TITLE = "title";
    public static final String JSON_KEY_TOKEN_RULE = "token_r";
    public static final String JSON_KEY_T_IMP = "t_imp";
    public static final String JSON_KEY_USER_ACTIVATION = "user_activation";
    public static final String JSON_KEY_VCN = "vcn";
    public static final String JSON_KEY_VIDEO_CHECK_TYPE = "vck_t";
    public static final String JSON_KEY_VIDEO_CTN_TYPE = "vctn_t";
    public static final String JSON_KEY_VIDEO_LENGTHL = "video_length";
    public static final String JSON_KEY_VIDEO_RESOLUTION = "video_resolution";
    public static final String JSON_KEY_VIDEO_SIZE = "video_size";
    public static final String JSON_KEY_VIDEO_URL = "video_url";
    public static final String JSON_KEY_WATCH_MILE = "watch_mile";
    public static final String JSON_KEY_WITHOUT_INSTALL_CHECK = "wtick";
    public static final String JSON_NATIVE_VIDEO_AD_TRACKING = "ad_tracking";
    public static final String JSON_NATIVE_VIDEO_CLICK = "click";
    public static final String JSON_NATIVE_VIDEO_CLOSE = "close";
    public static final String JSON_NATIVE_VIDEO_COMPLETE = "complete";
    public static final String JSON_NATIVE_VIDEO_ENDCARD = "endcard";
    public static final String JSON_NATIVE_VIDEO_ENDCARD_SHOW = "endcard_show";
    public static final String JSON_NATIVE_VIDEO_ERROR = "error";
    public static final String JSON_NATIVE_VIDEO_FIRST_QUARTILE = "first_quartile";
    public static final String JSON_NATIVE_VIDEO_MIDPOINT = "midpoint";
    public static final String JSON_NATIVE_VIDEO_MUTE = "mute";
    public static final String JSON_NATIVE_VIDEO_PAUSE = "pause";
    public static final String JSON_NATIVE_VIDEO_PLAY_PERCENTAGE = "play_percentage";
    public static final String JSON_NATIVE_VIDEO_RESUME = "resume";
    public static final String JSON_NATIVE_VIDEO_START = "start";
    public static final String JSON_NATIVE_VIDEO_THIRD_QUARTILE = "third_quartile";
    public static final String JSON_NATIVE_VIDEO_UNMUTE = "unmute";
    public static final String JSON_NATIVE_VIDEO_VIDEO_CLICK = "video_click";
    public static final String JSON_NATIVE_VIDOE_IMPRESSION = "impression";
    public static final String KEY_ADCHOICE = "adchoice";
    public static final String KEY_AD_TYPE = "ad_type";
    public static final String KEY_IA_CACHE = "ia_cache";
    public static final String KEY_IA_EXT1 = "ia_ext1";
    public static final String KEY_IA_EXT2 = "ia_ext2";
    public static final String KEY_IA_ICON = "ia_icon";
    public static final String KEY_IA_ORI = "ia_ori";
    public static final String KEY_IA_RST = "ia_rst";
    public static final String KEY_IA_URL = "ia_url";
    public static final String KEY_IS_DOWNLOAD = "is_download_zip";
    public static final String KEY_OC_TIME = "oc_time";
    public static final String KEY_OC_TYPE = "oc_type";
    public static final String KEY_OMID = "omid";
    public static final String KEY_T_LIST = "t_list";
    public static final int LANDING_TYPE_VALUE_OPEN_BROWSER = 1;
    public static final int LANDING_TYPE_VALUE_OPEN_GP_BY_PACKAGE = 3;
    public static final int LANDING_TYPE_VALUE_OPEN_WEBVIEW = 2;
    public static final int LINK_TYPE_1 = 1;
    public static final int LINK_TYPE_2 = 2;
    public static final int LINK_TYPE_3 = 3;
    public static final int LINK_TYPE_4 = 4;
    public static final int LINK_TYPE_8 = 8;
    public static final int LINK_TYPE_9 = 9;
    public static final String PLAYABLE_ADS_WITHOUT_VIDEO = "playable_ads_without_video";
    public static final int PLAYABLE_ADS_WITHOUT_VIDEO_DEFAULT = 1;
    public static final int PLAYABLE_ADS_WITHOUT_VIDEO_ENDCARD = 2;
    public static final int RETAR_GETING_IS = 1;
    public static final int RETAR_GETING_NOT = 2;
    public static final int RTINS_TYPE_DONE = 1;
    public static final int RTINS_TYPE_NOT_DONE = 2;
    public static final String TAG = "CampaignEx";
    public static final String VIDEO_END_TYPE = "video_end_type";
    public static final int VIDEO_END_TYPE_BROWSER = 5;
    public static final int VIDEO_END_TYPE_DEFAULT = 2;
    public static final int VIDEO_END_TYPE_FINISH = 1;
    public static final int VIDEO_END_TYPE_NATIVE = 100;
    public static final int VIDEO_END_TYPE_REULSE = 2;
    public static final int VIDEO_END_TYPE_VAST = 3;
    public static final int VIDEO_END_TYPE_WEBVIEW = 4;
    private static final long serialVersionUID = 1;
    private int adType;
    private String ad_url_list;
    private a adchoice;
    private String advImp;
    private HashMap<String, String> aks;
    private String al;
    private int bty;
    private long cVersionCode;
    private int cacheLevel;
    private String campaignUnitId;
    private int cbt;
    private int clickInterval;
    private String click_mode;
    private String endScreenUrl;
    private int endcard_click_result;
    private String endcard_url;
    private String ext_data;
    private int fca;
    private int fcb;
    private int flb;
    private int flbSkipTime;
    private String gifUrl;
    private String guidelines;
    private boolean hasMBTplMark;
    private String htmlUrl;
    private String ia_ext1;
    private String ia_ext2;
    private int iex;
    private String interactiveCache;
    private int isAddSuccesful;
    private boolean isBidCampaign;
    private boolean isCallbacked;
    private int isClick;
    private int isDeleted;
    private int isDownLoadZip;
    private boolean isMraid;
    private boolean isReport;
    private boolean isReportClick;
    private int jmPd;
    private CommonJumpLoader.JumpLoaderResult jumpResult;
    private String k;
    private String keyIaIcon;
    private int keyIaOri;
    private int keyIaRst;
    private String keyIaUrl;
    private String label;
    private String landingType;
    private int linkType;
    private int maitve;
    private String maitve_src;
    private b mediaViewHolder;
    private String mof_template_url;
    private int mof_tplid;
    private String mp;
    private String mraid;
    private m nativeVideoTracking;
    private String nativeVideoTrackingString;
    private int oc_time;
    private int offerType;
    private String pkgSource;
    private String placementId;
    private List<String> pv_urls;
    private String q;
    private String r;
    private String req_ext_data;
    private String requestId;
    private String requestIdNotice;
    private int retarget_offer;
    private int rewardAmount;
    private int rewardPlayStatus;
    public RewardPlus rewardPlus;
    private c rewardTemplateMode;
    private String reward_name;
    private ArrayList<Integer> rsIgnoreCheckRule;
    private int rtinsType;
    private int t_imp;
    private String t_list;
    private int template;
    private long ts;
    private String videoResolution;
    private int videoSize;
    private int watchMile;
    private int readyState = 1;
    private String adZip = "";
    private String adHtml = "";
    private boolean isReady = false;
    private long plct = 0;
    private long plctb = 0;
    private String bannerUrl = "";
    private String bannerHtml = "";
    private long creativeId = 0;
    private int vcn = 0;
    private int tokenRule = 0;
    private String encryptPrice = "";
    private int spareOfferFlag = -1;
    private int oc_type = 0;
    private int impUA = 1;
    private int cUA = 1;
    private int nvT2 = 6;
    private int tab = -1;
    private String impressionURL = "";
    private String noticeUrl = "";
    private String clickURL = "";
    private int wtick = 0;
    private String deepLinkUrl = "";
    private boolean userActivation = false;
    private String onlyImpressionURL = "";
    private String imageSize = "";
    private boolean isCallBackImpression = false;
    private String videoUrlEncode = "";
    private String bidToken = "";
    private int video_end_type = 2;
    public String videoMD5Value = "";
    private int ready_rate = -1;
    private int nscpt = 1;
    private int loadTimeoutState = 0;
    private int clickTimeOutInterval = 2;
    private int playable_ads_without_video = 1;
    private boolean hasReportAdTrackPause = false;
    private String omid = null;
    private int videoCheckType = 2;
    private int videoCtnType = 1;
    private int isTimeoutCheckVideoStatus = -1;

    public static final class b implements Serializable {
        public boolean a = false;
        public boolean b = false;
        public boolean c = false;
        public boolean d = false;
        public boolean e = false;
        public boolean f = false;
        public boolean g = false;
        public boolean h = false;
        public boolean i = false;
        public boolean j = false;
        public boolean k = false;
        public Map<Integer, String> l;
    }

    public String getAdZip() {
        return this.adZip;
    }

    public void setAdZip(String str) {
        this.adZip = str;
    }

    public String getAdHtml() {
        return this.adHtml;
    }

    public void setAdHtml(String str) {
        this.adHtml = str;
    }

    public void setPv_urls(List<String> list) {
        this.pv_urls = list;
    }

    public List<String> getPv_urls() {
        return this.pv_urls;
    }

    public int getReadyState() {
        return this.readyState;
    }

    public String getEncryptPrice() {
        return this.encryptPrice;
    }

    public void setEncryptPrice(String str) {
        this.encryptPrice = str;
    }

    public int getTokenRule() {
        if (this.tokenRule == 1) {
            return this.tokenRule;
        }
        return 0;
    }

    public void setTokenRule(int i) {
        this.tokenRule = i;
    }

    public int getVcn() {
        if (this.vcn > 0) {
            return this.vcn;
        }
        return 1;
    }

    public void setVcn(int i) {
        this.vcn = i;
    }

    public int getSpareOfferFlag() {
        return this.spareOfferFlag;
    }

    public void setSpareOfferFlag(int i) {
        this.spareOfferFlag = i;
    }

    public long getPlct() {
        return this.plct;
    }

    public void setPlct(long j) {
        this.plct = j;
    }

    public long getPlctb() {
        return this.plctb;
    }

    public void setPlctb(long j) {
        this.plctb = j;
    }

    public String getBannerUrl() {
        return this.bannerUrl;
    }

    public void setBannerUrl(String str) {
        this.bannerUrl = str;
    }

    public String getBannerHtml() {
        return this.bannerHtml;
    }

    public void setBannerHtml(String str) {
        this.bannerHtml = str;
    }

    public long getCreativeId() {
        return this.creativeId;
    }

    public void setCreativeId(long j) {
        this.creativeId = j;
    }

    public boolean isHasMBTplMark() {
        return this.hasMBTplMark;
    }

    public void setHasMBTplMark(boolean z) {
        this.hasMBTplMark = z;
    }

    public int getFlb() {
        return this.flb;
    }

    public void setFlb(int i) {
        this.flb = i;
    }

    public int getFlbSkipTime() {
        return this.flbSkipTime;
    }

    public void setFlbSkipTime(int i) {
        this.flbSkipTime = i;
    }

    public a getAdchoice() {
        return this.adchoice;
    }

    public void setAdchoice(a aVar) {
        this.adchoice = aVar;
    }

    public RewardPlus getRewardPlus() {
        return this.rewardPlus;
    }

    public void setRewardPlus(RewardPlus rewardPlus) {
        this.rewardPlus = rewardPlus;
    }

    public int getOc_type() {
        return this.oc_type;
    }

    public void setOc_type(int i) {
        this.oc_type = i;
    }

    public int getOc_time() {
        return this.oc_time;
    }

    public void setOc_time(int i) {
        this.oc_time = i;
    }

    public String getT_list() {
        return this.t_list;
    }

    public void setT_list(String str) {
        this.t_list = str;
    }

    public String getInteractiveCache() {
        return this.interactiveCache;
    }

    public void setInteractiveCache(String str) {
        this.interactiveCache = str;
    }

    public int getIsDownLoadZip() {
        return this.isDownLoadZip;
    }

    public void setIsDownLoadZip(int i) {
        this.isDownLoadZip = i;
    }

    public int getAdType() {
        return this.adType;
    }

    public void setAdType(int i) {
        this.adType = i;
    }

    public String getIa_ext1() {
        return this.ia_ext1;
    }

    public void setIa_ext1(String str) {
        this.ia_ext1 = str;
    }

    public String getIa_ext2() {
        return this.ia_ext2;
    }

    public void setIa_ext2(String str) {
        this.ia_ext2 = str;
    }

    public String getKeyIaIcon() {
        return this.keyIaIcon;
    }

    public void setKeyIaIcon(String str) {
        this.keyIaIcon = str;
    }

    public int getKeyIaRst() {
        return this.keyIaRst;
    }

    public void setKeyIaRst(int i) {
        this.keyIaRst = i;
    }

    public String getKeyIaUrl() {
        return this.keyIaUrl;
    }

    public void setKeyIaUrl(String str) {
        this.keyIaUrl = str;
    }

    public int getKeyIaOri() {
        return this.keyIaOri;
    }

    public void setKeyIaOri(int i) {
        this.keyIaOri = i;
    }

    public int getIsAddSuccesful() {
        return this.isAddSuccesful;
    }

    public void setIsAddSuccesful(int i) {
        this.isAddSuccesful = i;
    }

    public int getJmPd() {
        return this.jmPd;
    }

    public int getIsDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(int i) {
        this.isDeleted = i;
    }

    public int getIsClick() {
        return this.isClick;
    }

    public void setIsClick(int i) {
        this.isClick = i;
    }

    public void setJmPd(int i) {
        this.jmPd = i;
    }

    public int getNvT2() {
        return this.nvT2;
    }

    public void setNvT2(int i) {
        this.nvT2 = i;
    }

    public String getGifUrl() {
        return this.gifUrl;
    }

    public void setGifUrl(String str) {
        this.gifUrl = str;
    }

    public int getRtinsType() {
        return this.rtinsType;
    }

    public void setRtinsType(int i) {
        this.rtinsType = i;
    }

    public HashMap<String, String> getAks() {
        return this.aks;
    }

    public void setAks(HashMap<String, String> map) {
        this.aks = map;
    }

    public String getK() {
        return this.k;
    }

    public void setK(String str) {
        this.k = str;
    }

    public String getQ() {
        return this.q;
    }

    public void setQ(String str) {
        this.q = str;
    }

    public String getR() {
        return this.r;
    }

    public void setR(String str) {
        this.r = str;
    }

    public String getAl() {
        return this.al;
    }

    public void setAl(String str) {
        this.al = str;
    }

    public String getMp() {
        return this.mp;
    }

    public void setMp(String str) {
        this.mp = str;
    }

    public boolean isBidCampaign() {
        return this.isBidCampaign;
    }

    public void setIsBidCampaign(boolean z) {
        this.isBidCampaign = z;
    }

    public String getBidToken() {
        return this.bidToken;
    }

    public void setBidToken(String str) {
        this.bidToken = str;
    }

    public int getEndcard_click_result() {
        return this.endcard_click_result;
    }

    public void setEndcard_click_result(int i) {
        this.endcard_click_result = i;
    }

    public int getImpUA() {
        return this.impUA;
    }

    public void setImpUA(int i) {
        this.impUA = i;
    }

    public int getcUA() {
        return this.cUA;
    }

    public void setcUA(int i) {
        this.cUA = i;
    }

    public String getVideoMD5Value() {
        return this.videoMD5Value;
    }

    public void setVideoMD5Value(String str) {
        this.videoMD5Value = str;
    }

    public int getVideo_end_type() {
        return this.video_end_type;
    }

    public void setVideo_end_type(int i) {
        this.video_end_type = i;
    }

    public String getMraid() {
        return this.mraid;
    }

    public void setMraid(String str) {
        this.mraid = str;
    }

    public boolean isMraid() {
        return this.isMraid;
    }

    public void setIsMraid(boolean z) {
        this.isMraid = z;
    }

    public String getendcard_url() {
        return this.endcard_url;
    }

    public void setendcard_url(String str) {
        this.endcard_url = str;
    }

    public int getPlayable_ads_without_video() {
        return this.playable_ads_without_video;
    }

    public void setPlayable_ads_without_video(int i) {
        this.playable_ads_without_video = i;
    }

    public String getCampaignUnitId() {
        return this.campaignUnitId;
    }

    public void setCampaignUnitId(String str) {
        this.campaignUnitId = str;
    }

    public String getNativeVideoTrackingString() {
        return this.nativeVideoTrackingString;
    }

    public void setNativeVideoTrackingString(String str) {
        this.nativeVideoTrackingString = str;
    }

    public m getNativeVideoTracking() {
        return this.nativeVideoTracking;
    }

    public void setNativeVideoTracking(m mVar) {
        this.nativeVideoTracking = mVar;
    }

    public String getAd_url_list() {
        return this.ad_url_list;
    }

    public void setAd_url_list(String str) {
        this.ad_url_list = str;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public String getPkgSource() {
        return this.pkgSource;
    }

    public void setPkgSource(String str) {
        this.pkgSource = str;
    }

    public int getIex() {
        return this.iex;
    }

    public void setIex(int i) {
        this.iex = i;
    }

    public long getTs() {
        return this.ts;
    }

    public void setTs(long j) {
        this.ts = j;
    }

    public b getMediaViewHolder() {
        return this.mediaViewHolder;
    }

    public void setMediaViewHolder(b bVar) {
        this.mediaViewHolder = bVar;
    }

    public c getRewardTemplateMode() {
        return this.rewardTemplateMode;
    }

    public void setRewardTemplateMode(c cVar) {
        this.rewardTemplateMode = cVar;
    }

    public int getRetarget_offer() {
        return this.retarget_offer;
    }

    public void setRetarget_offer(int i) {
        this.retarget_offer = i;
    }

    public int getRewardPlayStatus() {
        return this.rewardPlayStatus;
    }

    public void setRewardPlayStatus(int i) {
        this.rewardPlayStatus = i;
    }

    public String getGuidelines() {
        return this.guidelines;
    }

    public void setGuidelines(String str) {
        this.guidelines = str;
    }

    public int getOfferType() {
        return this.offerType;
    }

    public void setOfferType(int i) {
        this.offerType = i;
    }

    public String getHtmlUrl() {
        return this.htmlUrl;
    }

    public void setHtmlUrl(String str) {
        this.htmlUrl = str;
    }

    public String getEndScreenUrl() {
        return this.endScreenUrl;
    }

    public void setEndScreenUrl(String str) {
        this.endScreenUrl = str;
    }

    public int getRewardAmount() {
        return this.rewardAmount;
    }

    public void setRewardAmount(int i) {
        this.rewardAmount = i;
    }

    public String getRewardName() {
        return this.reward_name;
    }

    public void setRewardName(String str) {
        this.reward_name = str;
    }

    public int getLinkType() {
        return this.linkType;
    }

    public void setLinkType(int i) {
        this.linkType = i;
    }

    @Override // com.mbridge.msdk.out.Campaign
    public void setAdCall(String str) {
        if (TextUtils.isEmpty(str)) {
            str = this.linkType != 2 ? "learn more" : "install";
        }
        super.setAdCall(str);
    }

    public int getBty() {
        return this.bty;
    }

    public void setBty(int i) {
        this.bty = i;
    }

    public String getAdvImp() {
        return this.advImp;
    }

    public void setAdvImp(String str) {
        this.advImp = str;
    }

    public Map<Integer, String> getAdvImpList() {
        return generateAdImpression(this.advImp);
    }

    public int getTImp() {
        return this.t_imp;
    }

    public void setTImp(int i) {
        this.t_imp = i;
    }

    public String getVideoUrlEncode() {
        return this.videoUrlEncode;
    }

    public void setVideoUrlEncode(String str) {
        this.videoUrlEncode = str;
    }

    public int getVideoSize() {
        return this.videoSize;
    }

    public void setVideoSize(int i) {
        this.videoSize = i;
    }

    public String getVideoResolution() {
        return this.videoResolution;
    }

    public void setVideoResolution(String str) {
        this.videoResolution = str;
    }

    public int getWatchMile() {
        return this.watchMile;
    }

    public void setWatchMile(int i) {
        this.watchMile = i;
    }

    public int getCacheLevel() {
        return this.cacheLevel;
    }

    public void setCacheLevel(int i) {
        this.cacheLevel = i;
    }

    public boolean isCallBackImpression() {
        return this.isCallBackImpression;
    }

    public void setCallBackImpression(boolean z) {
        this.isCallBackImpression = z;
    }

    public void setReport(boolean z) {
        this.isReport = z;
    }

    public boolean isReport() {
        return this.isReport;
    }

    public boolean isReportClick() {
        return this.isReportClick;
    }

    public void setReportClick(boolean z) {
        this.isReportClick = z;
    }

    public int getClickInterval() {
        return this.clickInterval;
    }

    public void setClickInterval(int i) {
        this.clickInterval = i;
    }

    public int getClickTimeOutInterval() {
        return this.clickTimeOutInterval;
    }

    public void setClickTimeOutInterval(int i) {
        this.clickTimeOutInterval = i;
    }

    public String getRequestId() {
        try {
            if (!TextUtils.isEmpty(this.requestId)) {
                return this.requestId;
            }
            if (TextUtils.isEmpty(this.onlyImpressionURL)) {
                return null;
            }
            Uri uri = Uri.parse(this.onlyImpressionURL);
            if (uri != null) {
                this.requestId = uri.getQueryParameter(JSON_KEY_AD_K);
                setRequestId(this.requestId);
            }
            return this.requestId;
        } catch (Exception unused) {
            return null;
        }
    }

    public void setRequestId(String str) {
        this.requestId = str;
    }

    public String getRequestIdNotice() {
        try {
            if (!TextUtils.isEmpty(this.requestIdNotice)) {
                return this.requestIdNotice;
            }
            if (TextUtils.isEmpty(this.noticeUrl)) {
                return "";
            }
            Uri uri = Uri.parse(this.noticeUrl);
            if (uri != null) {
                this.requestIdNotice = uri.getQueryParameter(JSON_KEY_AD_K);
                setRequestIdNotice(this.requestIdNotice);
            }
            return this.requestIdNotice;
        } catch (Exception unused) {
            return "";
        }
    }

    public void setRequestIdNotice(String str) {
        this.requestIdNotice = str;
    }

    public String getClick_mode() {
        return this.click_mode;
    }

    public void setClick_mode(String str) {
        this.click_mode = str;
    }

    public String getLandingType() {
        return this.landingType;
    }

    public void setLandingType(String str) {
        this.landingType = str;
    }

    public int getFca() {
        return this.fca;
    }

    public void setFca(int i) {
        this.fca = i;
    }

    public int getFcb() {
        return this.fcb;
    }

    public void setFcb(int i) {
        this.fcb = i;
    }

    public int getTab() {
        return this.tab;
    }

    public void setTab(int i) {
        this.tab = i;
    }

    public String getClickURL() {
        return this.clickURL;
    }

    public void setClickURL(String str) {
        this.clickURL = str;
    }

    public int getWtick() {
        return this.wtick;
    }

    public void setWtick(int i) {
        this.wtick = i;
    }

    public String getDeepLinkURL() {
        return this.deepLinkUrl;
    }

    public void setDeepLinkUrl(String str) {
        this.deepLinkUrl = str;
    }

    public void setUserActivation(boolean z) {
        this.userActivation = z;
    }

    public boolean getUserActivation() {
        return this.userActivation;
    }

    public String getImpressionURL() {
        return this.impressionURL;
    }

    public void setImpressionURL(String str) {
        this.impressionURL = str;
    }

    public String getNoticeUrl() {
        return this.noticeUrl;
    }

    public void setNoticeUrl(String str) {
        this.noticeUrl = str;
    }

    public String getOnlyImpressionURL() {
        return this.onlyImpressionURL;
    }

    public void setOnlyImpressionURL(String str) {
        this.onlyImpressionURL = str;
    }

    @Override // com.mbridge.msdk.foundation.entity.DomainCampaignEx
    public CommonJumpLoader.JumpLoaderResult getJumpResult() {
        return this.jumpResult;
    }

    @Override // com.mbridge.msdk.foundation.entity.DomainCampaignEx
    public void setJumpResult(CommonJumpLoader.JumpLoaderResult jumpLoaderResult) {
        this.jumpResult = jumpLoaderResult;
    }

    public int getTemplate() {
        return this.template;
    }

    public void setTemplate(int i) {
        this.template = i;
    }

    public String getImageSize() {
        return this.imageSize;
    }

    public void setImageSize(String str) {
        this.imageSize = str;
    }

    public String getHost() {
        if (TextUtils.isEmpty(getNoticeUrl())) {
            return "";
        }
        try {
            URL url = new URL(getNoticeUrl());
            return url.getProtocol() + "://" + url.getHost();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static CampaignEx parseSettingCampaign(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        CampaignEx campaignEx = new CampaignEx();
        campaignEx.setId(jSONObject.optString("campaignid"));
        campaignEx.setPackageName(jSONObject.optString("packageName"));
        campaignEx.setAppName(jSONObject.optString("title"));
        campaignEx.setAdCall(jSONObject.optString("cta"));
        campaignEx.setAppDesc(jSONObject.optString(JSON_KEY_DESC));
        campaignEx.setImpressionURL(jSONObject.optString(JSON_KEY_IMPRESSION_URL));
        campaignEx.setImageUrl(jSONObject.optString(JSON_KEY_IMAGE_URL));
        campaignEx.setPlct(jSONObject.optLong(JSON_KEY_PLCT));
        campaignEx.setPlctb(jSONObject.optLong(JSON_KEY_PLCTB));
        campaignEx.setAdHtml(jSONObject.optString(JSON_KEY_AD_HTML));
        campaignEx.setAdZip(jSONObject.optString(JSON_KEY_AD_ZIP));
        campaignEx.setBannerUrl(jSONObject.optString(JSON_KEY_BANNER_URL));
        campaignEx.setBannerHtml(jSONObject.optString(JSON_KEY_BANNER_HTML));
        campaignEx.setCreativeId(jSONObject.optInt(JSON_KEY_CREATIVE_ID));
        campaignEx.setPlacementId(jSONObject.optString(MBridgeConstans.PLACEMENT_ID));
        return campaignEx;
    }

    private static boolean isBreakCampainOrSetItByEndCard(CampaignEx campaignEx, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (com.mbridge.msdk.foundation.controller.a.f().m() == 2 && "1".equals(Uri.parse(str).getQueryParameter("dpwgl"))) {
            return true;
        }
        campaignEx.setendcard_url(str);
        return false;
    }

    public static CampaignEx parseCampaignWithBackData(JSONObject jSONObject) {
        CampaignEx campaignEx;
        ArrayList arrayList;
        CampaignEx campaignWithBackData = null;
        if (jSONObject == null) {
            return null;
        }
        try {
            campaignEx = new CampaignEx();
        } catch (Exception e) {
            e = e;
            campaignEx = campaignWithBackData;
        }
        try {
            campaignEx.setId(jSONObject.optString("id"));
            campaignEx.setAppName(jSONObject.optString("title"));
            campaignEx.setAppDesc(jSONObject.optString(JSON_KEY_DESC));
            campaignEx.setPackageName(jSONObject.optString(JSON_KEY_PACKAGE_NAME));
            campaignEx.setRtinsType(jSONObject.optInt(JSON_KEY_RETARGET_TYPE));
            campaignEx.setIconUrl(jSONObject.optString(JSON_KEY_ICON_URL));
            campaignEx.setImageUrl(jSONObject.optString(JSON_KEY_IMAGE_URL));
            campaignEx.setSize(jSONObject.optString(JSON_KEY_APP_SIZE));
            campaignEx.setImageSize(jSONObject.optString(JSON_KEY_IMAGE_SIZE));
            campaignEx.setImpressionURL(jSONObject.optString(JSON_KEY_IMPRESSION_URL));
            campaignEx.setClickURL(jSONObject.optString(JSON_KEY_CLICK_URL));
            campaignEx.setRewardPlus(RewardPlus.parseByString(jSONObject.optString(JSON_KEY_REWARD_PLUS)));
            campaignEx.setWtick(jSONObject.optInt(JSON_KEY_WITHOUT_INSTALL_CHECK));
            campaignEx.setDeepLinkUrl(jSONObject.optString(JSON_KEY_DEEP_LINK_URL));
            campaignEx.setUserActivation(jSONObject.optBoolean(JSON_KEY_USER_ACTIVATION, false));
            campaignEx.setNoticeUrl(jSONObject.optString(JSON_KEY_NOTICE_URL));
            campaignEx.setTemplate(jSONObject.optInt("template"));
            campaignEx.setType(jSONObject.optInt(JSON_KEY_AD_SOURCE_ID, 1));
            campaignEx.setFca(jSONObject.optInt(JSON_KEY_FCA));
            campaignEx.setFcb(jSONObject.optInt(JSON_KEY_FCB));
            campaignEx.setEndcard_click_result(jSONObject.optInt(JSON_KEY_ENDCARD_CLICK));
            if (!TextUtils.isEmpty(jSONObject.optString(JSON_KEY_STAR))) {
                campaignEx.setRating(Double.parseDouble(jSONObject.optString(JSON_KEY_STAR, PushConstants.PUSH_TYPE_NOTIFY)));
            }
            if (!TextUtils.isEmpty(jSONObject.optString(JSON_KEY_NUMBER_RATING))) {
                campaignEx.setNumberRating(jSONObject.optInt(JSON_KEY_NUMBER_RATING, 333333));
            }
            campaignEx.setClick_mode(jSONObject.optString(JSON_KEY_CLICK_MODE));
            campaignEx.setLandingType(jSONObject.optString(JSON_KEY_LANDING_TYPE));
            campaignEx.setLinkType(jSONObject.optInt(JSON_KEY_LINK_TYPE, 4));
            campaignEx.setClickInterval(jSONObject.optInt(JSON_KEY_CLICK_INTERVAL));
            campaignEx.setAdCall(jSONObject.optString(JSON_KEY_CTA_TEXT));
            campaignEx.setAd_url_list(jSONObject.optString(JSON_KEY_AD_URL_LIST));
            campaignEx.setRetarget_offer(jSONObject.optInt(JSON_KEY_RETARGET_OFFER, 2));
            campaignEx.setVideoUrlEncode(jSONObject.optString(JSON_KEY_VIDEO_URL));
            campaignEx.setVideoLength(jSONObject.optInt(JSON_KEY_VIDEO_LENGTHL));
            campaignEx.setVideoSize(jSONObject.optInt(JSON_KEY_VIDEO_SIZE));
            campaignEx.setVideoResolution(jSONObject.optString(JSON_KEY_VIDEO_RESOLUTION));
            campaignEx.setWatchMile(jSONObject.optInt(JSON_KEY_WATCH_MILE));
            campaignEx.setTimestamp(System.currentTimeMillis());
            campaignEx.setBty(jSONObject.optInt(JSON_KEY_BTY));
            campaignEx.setAdvImp(jSONObject.optString(JSON_KEY_ADVIMP));
            campaignEx.setTImp(jSONObject.optInt(JSON_KEY_T_IMP));
            campaignEx.setHtmlUrl(jSONObject.optString("html_url"));
            campaignEx.setEndScreenUrl(jSONObject.optString("end_screen_url"));
            campaignEx.setGuidelines(jSONObject.optString(JSON_KEY_GUIDELINES));
            campaignEx.setOfferType(jSONObject.optInt(JSON_KEY_OFFER_TYPE));
            campaignEx.setRewardName(jSONObject.optString(JSON_KEY_REWARD_NAME));
            campaignEx.setRewardAmount(jSONObject.optInt(JSON_KEY_REWARD_AMOUNT));
            try {
                if (jSONObject.has(JSON_NATIVE_VIDEO_AD_TRACKING)) {
                    String strOptString = jSONObject.optString(JSON_NATIVE_VIDEO_AD_TRACKING);
                    if (!TextUtils.isEmpty(strOptString)) {
                        campaignEx.setNativeVideoTrackingString(strOptString);
                        campaignEx.setNativeVideoTracking(TrackingStr2Object(strOptString));
                    }
                }
            } catch (Exception unused) {
            }
            try {
                campaignEx.setReady_rate(jSONObject.optInt(JSON_KEY_READY_RATE, -1));
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(JSON_KEY_EXT_DATA);
                if (jSONObjectOptJSONObject != null) {
                    campaignEx.setExt_data(jSONObjectOptJSONObject.toString());
                }
                campaignEx.setMof_tplid(jSONObject.optInt(CampaignUnit.JSON_KEY_MOF_TPLID));
                campaignEx.setMof_template_url(jSONObject.optString(CampaignUnit.JSON_KEY_MOF_TEMPLATE_URL));
                campaignEx.setNscpt(jSONObject.optInt(CampaignUnit.JSON_KEY_NSCPT));
                new JSONArray();
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("pv_urls");
                if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0) {
                    arrayList = null;
                } else {
                    arrayList = new ArrayList(jSONArrayOptJSONArray.length());
                    for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                        arrayList.add(jSONArrayOptJSONArray.optString(i));
                    }
                }
                campaignEx.setPv_urls(arrayList);
                JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject(CampaignUnit.JSON_KEY_REQ_EXT_DATA);
                if (jSONObjectOptJSONObject2 != null) {
                    campaignEx.setReq_ext_data(jSONObjectOptJSONObject2.toString());
                }
            } catch (Exception unused2) {
            }
            campaignEx.setVideo_end_type(jSONObject.optInt(VIDEO_END_TYPE, 2));
            if (isBreakCampainOrSetItByEndCard(campaignEx, jSONObject.optString(ENDCARD_URL))) {
                return null;
            }
            campaignEx.setPlayable_ads_without_video(jSONObject.optInt(PLAYABLE_ADS_WITHOUT_VIDEO, 1));
            if (jSONObject.has(JSON_KEY_REWARD_VIDEO_MD5)) {
                campaignEx.setVideoMD5Value(jSONObject.optString(JSON_KEY_REWARD_VIDEO_MD5));
            }
            if (jSONObject.has(JSON_KEY_NV_T2)) {
                campaignEx.setNvT2(jSONObject.optInt(JSON_KEY_NV_T2));
            }
            if (jSONObject.has(JSON_KEY_GIF_URL)) {
                campaignEx.setGifUrl(jSONObject.optString(JSON_KEY_GIF_URL));
            }
            campaignEx.setRewardTemplateMode(c.a(jSONObject.optJSONObject(JSON_KEY_REWARD_TEMPLATE)));
            campaignEx.setClickTimeOutInterval(jSONObject.optInt(JSON_KEY_CLICK_TIMEOUT_INTERVAL, 2));
            campaignEx.setImpUA(jSONObject.optInt(JSON_KEY_IMP_UA, 1));
            campaignEx.setcUA(jSONObject.optInt(JSON_KEY_C_UA, 1));
            campaignEx.setJmPd(jSONObject.optInt(JSON_KEY_JM_PD));
            campaignEx.setKeyIaIcon(jSONObject.optString("ia_icon"));
            campaignEx.setKeyIaRst(jSONObject.optInt("ia_rst"));
            campaignEx.setKeyIaUrl(jSONObject.optString("ia_url"));
            campaignEx.setKeyIaOri(jSONObject.optInt("ia_ori"));
            campaignEx.setAdType(jSONObject.optInt("ad_type"));
            campaignEx.setIa_ext1(jSONObject.optString(KEY_IA_EXT1));
            campaignEx.setIa_ext2(jSONObject.optString(KEY_IA_EXT2));
            campaignEx.setIsDownLoadZip(jSONObject.optInt(KEY_IS_DOWNLOAD));
            campaignEx.setInteractiveCache(jSONObject.optString(KEY_IA_CACHE));
            campaignEx.setOc_time(jSONObject.optInt(KEY_OC_TIME));
            campaignEx.setOc_type(jSONObject.optInt(KEY_OC_TYPE));
            campaignEx.setT_list(jSONObject.optString(KEY_T_LIST));
            campaignEx.setAdchoice(a.a(jSONObject.optString(KEY_ADCHOICE, "")));
            campaignEx.setPlct(jSONObject.optLong(JSON_KEY_PLCT));
            campaignEx.setPlctb(jSONObject.optLong(JSON_KEY_PLCTB));
            JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray(KEY_OMID);
            if (jSONArrayOptJSONArray2 == null) {
                if (!TextUtils.isEmpty(jSONObject.optString(KEY_OMID))) {
                    campaignEx.setOmid(jSONObject.optString(KEY_OMID));
                } else {
                    campaignEx.setOmid(null);
                }
            } else {
                campaignEx.setOmid(jSONArrayOptJSONArray2.toString());
            }
            campaignEx.setCreativeId(jSONObject.optInt(JSON_KEY_CREATIVE_ID));
            String strOptString2 = jSONObject.optString("cam_tpl_url");
            Uri uri = Uri.parse(strOptString2);
            if (!TextUtils.isEmpty(uri.getPath()) && (uri.getPath().endsWith(".zip") || uri.getPath().endsWith(".ZIP"))) {
                campaignEx.setAdZip(strOptString2);
            } else {
                campaignEx.setBannerUrl(strOptString2);
            }
            campaignEx.setBannerHtml(jSONObject.optString("cam_html"));
            campaignEx.setAdHtml(jSONObject.optString("cam_html"));
            campaignEx.setCampaignUnitId(jSONObject.optString("unitId"));
            String strOptString3 = jSONObject.optString(JSON_KEY_MRAID);
            if (TextUtils.isEmpty(strOptString3)) {
                strOptString3 = jSONObject.optString(JSON_KEY_MRAIDFORH5);
            }
            if (!TextUtils.isEmpty(strOptString3)) {
                campaignEx.setIsMraid(true);
                campaignEx.setMraid(strOptString3);
            } else if (!TextUtils.isEmpty(campaignEx.getAdHtml()) && !campaignEx.getAdHtml().contains("<MBTPLMARK>")) {
                campaignEx.setIsMraid(true);
            } else {
                campaignEx.setIsMraid(false);
            }
            try {
                String strOptString4 = jSONObject.optString("only_impression_url");
                if (!TextUtils.isEmpty(strOptString4)) {
                    campaignEx.setOnlyImpressionURL(strOptString4);
                    Uri uri2 = Uri.parse(strOptString4);
                    if (uri2 != null) {
                        campaignEx.setRequestId(uri2.getQueryParameter(JSON_KEY_AD_K));
                    }
                }
                String strOptString5 = jSONObject.optString(JSON_KEY_NOTICE_URL);
                if (!TextUtils.isEmpty(strOptString5)) {
                    campaignEx.setNoticeUrl(strOptString5);
                    Uri uri3 = Uri.parse(strOptString5);
                    if (uri3 != null) {
                        campaignEx.setRequestIdNotice(uri3.getQueryParameter(JSON_KEY_AD_K));
                    }
                }
            } catch (Exception unused3) {
            }
            campaignEx.setIsBidCampaign(jSONObject.optBoolean(JSON_KEY_HB, false));
            campaignEx.setPlacementId(jSONObject.optString(MBridgeConstans.PLACEMENT_ID));
            campaignEx.setMaitve(jSONObject.optInt(JSON_KEY_MAITVE));
            campaignEx.setMaitve_src(jSONObject.optString(JSON_KEY_MAITVESRC));
            campaignEx.setFlb(jSONObject.optInt(JSON_KEY_FLB));
            campaignEx.setFlbSkipTime(jSONObject.optInt(JSON_KEY_FLB_SKIP_TIME));
            campaignWithBackData = parseCampaignWithBackData(jSONObject, campaignEx);
            campaignWithBackData.setVcn(jSONObject.optInt("vcn"));
            campaignWithBackData.setTokenRule(jSONObject.optInt("token_r"));
            campaignWithBackData.setEncryptPrice(jSONObject.optString("encrypt_p"));
            campaignWithBackData.setVideoCheckType(jSONObject.optInt(JSON_KEY_VIDEO_CHECK_TYPE, 2));
            campaignWithBackData.setVideoCtnType(jSONObject.optInt(JSON_KEY_VIDEO_CTN_TYPE, 1));
            JSONArray jSONArrayOptJSONArray3 = jSONObject.optJSONArray(JSON_KEY_RS_IGNORE_CHECK_RULE);
            if (jSONArrayOptJSONArray3 != null && jSONArrayOptJSONArray3.length() > 0) {
                ArrayList<Integer> arrayList2 = new ArrayList<>();
                for (int i2 = 0; i2 < jSONArrayOptJSONArray3.length(); i2++) {
                    arrayList2.add(Integer.valueOf(jSONArrayOptJSONArray3.optInt(i2)));
                }
                if (arrayList2.size() > 0) {
                    campaignWithBackData.setRsIgnoreCheckRule(arrayList2);
                }
            }
            return campaignWithBackData;
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            return campaignEx;
        }
    }

    private static JSONObject dealV5Temp(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArrayOptJSONArray;
        try {
            if (!jSONObject.has(JSON_KEY_AD_TMP_IDS) || (jSONArrayOptJSONArray = jSONObject.optJSONArray(JSON_KEY_AD_TMP_IDS)) == null || jSONArrayOptJSONArray.length() <= 0) {
                return jSONObject;
            }
            jSONObject.remove(JSON_KEY_AD_TMP_IDS);
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                JSONObject jSONObjectA = com.mbridge.msdk.foundation.db.a.a.a().a(jSONArrayOptJSONArray.getString(i));
                if (jSONObjectA != null) {
                    Iterator<String> itKeys = jSONObjectA.keys();
                    while (itKeys.hasNext()) {
                        String next = itKeys.next();
                        jSONObject.put(next, jSONObjectA.opt(next));
                    }
                }
            }
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    public static CampaignEx parseCampaign(JSONObject jSONObject, String str, String str2, String str3, boolean z, CampaignUnit campaignUnit) {
        return parseCampaign(jSONObject, str, str2, str3, z, campaignUnit, "");
    }

    public static CampaignEx parseCampaign(JSONObject jSONObject, String str, String str2, String str3, boolean z, CampaignUnit campaignUnit, String str4) throws JSONException {
        CampaignEx campaignEx;
        ArrayList arrayList;
        JSONObject jSONObjectDealV5Temp = dealV5Temp(jSONObject);
        CampaignEx campaign = null;
        if (jSONObjectDealV5Temp == null) {
            return null;
        }
        try {
            campaignEx = new CampaignEx();
        } catch (Exception e) {
            e = e;
            campaignEx = campaign;
        }
        try {
            String strOptString = jSONObjectDealV5Temp.optString(JSON_KEY_AD_AKS);
            if (!TextUtils.isEmpty(strOptString)) {
                JSONObject jSONObject2 = new JSONObject(strOptString);
                Iterator<String> itKeys = jSONObject2.keys();
                HashMap<String, String> map = new HashMap<>();
                while (itKeys != null && itKeys.hasNext()) {
                    String next = itKeys.next();
                    map.put(next, jSONObject2.optString(next));
                }
                campaignEx.setAks(map);
            }
            if (!TextUtils.isEmpty(str4)) {
                campaignEx.setBidToken(str4);
                campaignEx.setIsBidCampaign(true);
            }
            try {
                campaignEx.setReady_rate(jSONObjectDealV5Temp.optInt(JSON_KEY_READY_RATE, -1));
                JSONObject jSONObjectOptJSONObject = jSONObjectDealV5Temp.optJSONObject(JSON_KEY_EXT_DATA);
                if (jSONObjectOptJSONObject != null) {
                    campaignEx.setExt_data(jSONObjectOptJSONObject.toString());
                }
                campaignEx.setMof_tplid(jSONObjectDealV5Temp.optInt(CampaignUnit.JSON_KEY_MOF_TPLID));
                campaignEx.setMof_template_url(jSONObjectDealV5Temp.optString(CampaignUnit.JSON_KEY_MOF_TEMPLATE_URL));
                campaignEx.setNscpt(jSONObjectDealV5Temp.optInt(CampaignUnit.JSON_KEY_NSCPT));
                new JSONArray();
                JSONArray jSONArrayOptJSONArray = jSONObjectDealV5Temp.optJSONArray("pv_urls");
                if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0) {
                    arrayList = null;
                } else {
                    arrayList = new ArrayList(jSONArrayOptJSONArray.length());
                    for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                        arrayList.add(jSONArrayOptJSONArray.optString(i));
                    }
                }
                campaignEx.setPv_urls(arrayList);
                JSONObject jSONObjectOptJSONObject2 = jSONObjectDealV5Temp.optJSONObject(CampaignUnit.JSON_KEY_REQ_EXT_DATA);
                if (jSONObjectOptJSONObject2 != null) {
                    campaignEx.setReq_ext_data(jSONObjectOptJSONObject2.toString());
                }
            } catch (Exception unused) {
            }
            campaignEx.setId(jSONObjectDealV5Temp.optString("id"));
            campaignEx.setAppName(jSONObjectDealV5Temp.optString("title"));
            campaignEx.setCampaignUnitId(jSONObjectDealV5Temp.optString("unitId", ""));
            campaignEx.setAppDesc(jSONObjectDealV5Temp.optString(JSON_KEY_DESC));
            campaignEx.setPackageName(jSONObjectDealV5Temp.optString(JSON_KEY_PACKAGE_NAME));
            campaignEx.setRtinsType(jSONObjectDealV5Temp.optInt(JSON_KEY_RETARGET_TYPE));
            campaignEx.setIconUrl(jSONObjectDealV5Temp.optString(JSON_KEY_ICON_URL));
            campaignEx.setImageUrl(jSONObjectDealV5Temp.optString(JSON_KEY_IMAGE_URL));
            campaignEx.setSize(jSONObjectDealV5Temp.optString(JSON_KEY_APP_SIZE));
            campaignEx.setImageSize(jSONObjectDealV5Temp.optString(JSON_KEY_IMAGE_SIZE));
            campaignEx.setImpressionURL(replaceValueByKey(campaignUnit, campaignEx, jSONObjectDealV5Temp.optString(JSON_KEY_IMPRESSION_URL)));
            campaignEx.setClickURL(replaceValueByKey(campaignUnit, campaignEx, jSONObjectDealV5Temp.optString(JSON_KEY_CLICK_URL)));
            campaignEx.setRewardPlus(RewardPlus.parseByString(jSONObjectDealV5Temp.optString(JSON_KEY_REWARD_PLUS)));
            campaignEx.setWtick(jSONObjectDealV5Temp.optInt(JSON_KEY_WITHOUT_INSTALL_CHECK));
            campaignEx.setDeepLinkUrl(replaceValueByKey(campaignUnit, campaignEx, jSONObjectDealV5Temp.optString(JSON_KEY_DEEP_LINK_URL)));
            campaignEx.setUserActivation(jSONObjectDealV5Temp.optBoolean(JSON_KEY_USER_ACTIVATION, false));
            campaignEx.setNoticeUrl(replaceValueByKey(campaignUnit, campaignEx, jSONObjectDealV5Temp.optString(JSON_KEY_NOTICE_URL)));
            campaignEx.setTemplate(jSONObjectDealV5Temp.optInt("template"));
            campaignEx.setType(jSONObjectDealV5Temp.optInt(JSON_KEY_AD_SOURCE_ID, 1));
            campaignEx.setFca(jSONObjectDealV5Temp.optInt(JSON_KEY_FCA));
            campaignEx.setFcb(jSONObjectDealV5Temp.optInt(JSON_KEY_FCB));
            campaignEx.setEndcard_click_result(jSONObjectDealV5Temp.optInt(JSON_KEY_ENDCARD_CLICK));
            if (!TextUtils.isEmpty(jSONObjectDealV5Temp.optString(JSON_KEY_STAR))) {
                campaignEx.setRating(Double.parseDouble(jSONObjectDealV5Temp.optString(JSON_KEY_STAR, PushConstants.PUSH_TYPE_NOTIFY)));
            }
            if (!TextUtils.isEmpty(jSONObjectDealV5Temp.optString(JSON_KEY_NUMBER_RATING))) {
                campaignEx.setNumberRating(jSONObjectDealV5Temp.optInt(JSON_KEY_NUMBER_RATING, 333333));
            }
            campaignEx.setClick_mode(jSONObjectDealV5Temp.optString(JSON_KEY_CLICK_MODE));
            campaignEx.setLandingType(jSONObjectDealV5Temp.optString(JSON_KEY_LANDING_TYPE));
            campaignEx.setLinkType(jSONObjectDealV5Temp.optInt(JSON_KEY_LINK_TYPE, 4));
            campaignEx.setClickInterval(jSONObjectDealV5Temp.optInt(JSON_KEY_CLICK_INTERVAL));
            campaignEx.setAdCall(jSONObjectDealV5Temp.optString(JSON_KEY_CTA_TEXT));
            campaignEx.setAd_url_list(jSONObjectDealV5Temp.optString(JSON_KEY_AD_URL_LIST));
            campaignEx.setRetarget_offer(jSONObjectDealV5Temp.optInt(JSON_KEY_RETARGET_OFFER, 2));
            String strOptString2 = jSONObjectDealV5Temp.optString(JSON_KEY_VIDEO_URL);
            if (!TextUtils.isEmpty(strOptString2)) {
                if (z) {
                    campaignEx.setVideoUrlEncode(strOptString2);
                } else {
                    campaignEx.setVideoUrlEncode(com.mbridge.msdk.foundation.tools.n.b(strOptString2));
                }
            }
            campaignEx.setVideoLength(jSONObjectDealV5Temp.optInt(JSON_KEY_VIDEO_LENGTHL));
            campaignEx.setVideoSize(jSONObjectDealV5Temp.optInt(JSON_KEY_VIDEO_SIZE));
            campaignEx.setVideoResolution(jSONObjectDealV5Temp.optString(JSON_KEY_VIDEO_RESOLUTION));
            campaignEx.setWatchMile(jSONObjectDealV5Temp.optInt(JSON_KEY_WATCH_MILE));
            campaignEx.setTimestamp(System.currentTimeMillis());
            campaignEx.setOnlyImpressionURL(replaceValueByKey(campaignUnit, campaignEx, str));
            campaignEx.setBty(jSONObjectDealV5Temp.optInt(JSON_KEY_BTY));
            campaignEx.setAdvImp(jSONObjectDealV5Temp.optString(JSON_KEY_ADVIMP));
            campaignEx.setTImp(jSONObjectDealV5Temp.optInt(JSON_KEY_T_IMP));
            campaignEx.setHtmlUrl(str2);
            campaignEx.setEndScreenUrl(str3);
            campaignEx.setGuidelines(jSONObjectDealV5Temp.optString(JSON_KEY_GUIDELINES));
            campaignEx.setOfferType(jSONObjectDealV5Temp.optInt(JSON_KEY_OFFER_TYPE));
            campaignEx.setRewardName(jSONObjectDealV5Temp.optString(JSON_KEY_REWARD_NAME));
            campaignEx.setRewardAmount(jSONObjectDealV5Temp.optInt(JSON_KEY_REWARD_AMOUNT));
            try {
                if (jSONObjectDealV5Temp.has(JSON_NATIVE_VIDEO_AD_TRACKING)) {
                    String strReplaceValueByKey = replaceValueByKey(campaignUnit, campaignEx, jSONObjectDealV5Temp.optString(JSON_NATIVE_VIDEO_AD_TRACKING));
                    if (!TextUtils.isEmpty(strReplaceValueByKey)) {
                        campaignEx.setNativeVideoTrackingString(strReplaceValueByKey);
                        campaignEx.setNativeVideoTracking(TrackingStr2Object(strReplaceValueByKey));
                    }
                }
            } catch (Exception unused2) {
            }
            campaignEx.setVideo_end_type(jSONObjectDealV5Temp.optInt(VIDEO_END_TYPE, 2));
            if (isBreakCampainOrSetItByEndCard(campaignEx, jSONObjectDealV5Temp.optString(ENDCARD_URL))) {
                return null;
            }
            campaignEx.setPlayable_ads_without_video(jSONObjectDealV5Temp.optInt(PLAYABLE_ADS_WITHOUT_VIDEO, 1));
            if (jSONObjectDealV5Temp.has(JSON_KEY_REWARD_VIDEO_MD5)) {
                campaignEx.setVideoMD5Value(jSONObjectDealV5Temp.optString(JSON_KEY_REWARD_VIDEO_MD5));
            }
            if (jSONObjectDealV5Temp.has(JSON_KEY_NV_T2)) {
                campaignEx.setNvT2(jSONObjectDealV5Temp.optInt(JSON_KEY_NV_T2));
            }
            if (jSONObjectDealV5Temp.has(JSON_KEY_GIF_URL)) {
                campaignEx.setGifUrl(jSONObjectDealV5Temp.optString(JSON_KEY_GIF_URL));
            }
            campaignEx.setRewardTemplateMode(c.a(jSONObjectDealV5Temp.optJSONObject(JSON_KEY_REWARD_TEMPLATE)));
            campaignEx.setClickTimeOutInterval(jSONObjectDealV5Temp.optInt(JSON_KEY_CLICK_TIMEOUT_INTERVAL, 2));
            campaignEx.setImpUA(jSONObjectDealV5Temp.optInt(JSON_KEY_IMP_UA, 1));
            campaignEx.setcUA(jSONObjectDealV5Temp.optInt(JSON_KEY_C_UA, 1));
            campaignEx.setJmPd(jSONObjectDealV5Temp.optInt(JSON_KEY_JM_PD));
            campaignEx.setKeyIaIcon(jSONObjectDealV5Temp.optString("ia_icon"));
            campaignEx.setKeyIaRst(jSONObjectDealV5Temp.optInt("ia_rst"));
            campaignEx.setKeyIaUrl(jSONObjectDealV5Temp.optString("ia_url"));
            campaignEx.setKeyIaOri(jSONObjectDealV5Temp.optInt("ia_ori"));
            campaignEx.setAdType(campaignUnit.getAdType());
            campaignEx.setIa_ext1(jSONObjectDealV5Temp.optString(KEY_IA_EXT1));
            campaignEx.setIa_ext2(jSONObjectDealV5Temp.optString(KEY_IA_EXT2));
            campaignEx.setIsDownLoadZip(jSONObjectDealV5Temp.optInt(KEY_IS_DOWNLOAD));
            campaignEx.setInteractiveCache(jSONObjectDealV5Temp.optString(KEY_IA_CACHE));
            campaignEx.setOc_time(jSONObjectDealV5Temp.optInt(KEY_OC_TIME));
            campaignEx.setOc_type(jSONObjectDealV5Temp.optInt(KEY_OC_TYPE));
            campaignEx.setT_list(jSONObjectDealV5Temp.optString(KEY_T_LIST));
            campaignEx.setAdchoice(a.a(jSONObjectDealV5Temp.optString(KEY_ADCHOICE, "")));
            campaignEx.setPlct(jSONObjectDealV5Temp.optLong(JSON_KEY_PLCT));
            campaignEx.setPlctb(jSONObjectDealV5Temp.optLong(JSON_KEY_PLCTB));
            campaignEx.setCreativeId(jSONObjectDealV5Temp.optLong(JSON_KEY_CREATIVE_ID));
            String strOptString3 = jSONObjectDealV5Temp.optString("cam_tpl_url");
            Uri uri = Uri.parse(strOptString3);
            if (!TextUtils.isEmpty(uri.getPath()) && (uri.getPath().endsWith(".zip") || uri.getPath().endsWith(".ZIP"))) {
                campaignEx.setAdZip(strOptString3);
            } else {
                campaignEx.setBannerUrl(strOptString3);
            }
            campaignEx.setBannerHtml(jSONObjectDealV5Temp.optString("cam_html"));
            campaignEx.setAdHtml(jSONObjectDealV5Temp.optString("cam_html"));
            String strOptString4 = jSONObjectDealV5Temp.optString(JSON_KEY_MRAID);
            if (!TextUtils.isEmpty(strOptString4)) {
                campaignEx.setIsMraid(true);
                campaignEx.setMraid(strOptString4);
            } else if (!TextUtils.isEmpty(campaignEx.getAdHtml()) && !campaignEx.getAdHtml().contains("<MBTPLMARK>")) {
                campaignEx.setIsMraid(true);
            } else {
                campaignEx.setIsMraid(false);
            }
            JSONArray jSONArrayOptJSONArray2 = jSONObjectDealV5Temp.optJSONArray(KEY_OMID);
            if (jSONArrayOptJSONArray2 == null) {
                if (!TextUtils.isEmpty(jSONObjectDealV5Temp.optString(KEY_OMID))) {
                    campaignEx.setOmid(jSONObjectDealV5Temp.optString(KEY_OMID));
                } else {
                    campaignEx.setOmid(null);
                }
            } else {
                campaignEx.setOmid(jSONArrayOptJSONArray2.toString());
            }
            campaignEx.setPlacementId(jSONObjectDealV5Temp.optString(MBridgeConstans.PLACEMENT_ID));
            campaignEx.setMaitve(jSONObjectDealV5Temp.optInt(JSON_KEY_MAITVE));
            campaignEx.setMaitve_src(jSONObjectDealV5Temp.optString(JSON_KEY_MAITVESRC));
            campaignEx.setFlb(jSONObjectDealV5Temp.optInt(JSON_KEY_FLB));
            campaignEx.setFlbSkipTime(jSONObjectDealV5Temp.optInt(JSON_KEY_FLB_SKIP_TIME));
            campaign = parseCampaign(jSONObjectDealV5Temp, campaignEx);
            try {
                campaign.setImpressionURL(w.a(com.mbridge.msdk.foundation.controller.a.f().j(), campaign.getPackageName(), campaign.getImpressionURL()));
            } catch (Exception e2) {
                s.d("campaign", e2.getMessage());
            }
            campaign.setVideoCheckType(jSONObjectDealV5Temp.optInt(JSON_KEY_VIDEO_CHECK_TYPE, 2));
            campaign.setVideoCtnType(jSONObjectDealV5Temp.optInt(JSON_KEY_VIDEO_CTN_TYPE, 1));
            JSONArray jSONArrayOptJSONArray3 = jSONObjectDealV5Temp.optJSONArray(JSON_KEY_RS_IGNORE_CHECK_RULE);
            if (jSONArrayOptJSONArray3 != null && jSONArrayOptJSONArray3.length() > 0) {
                ArrayList<Integer> arrayList2 = new ArrayList<>();
                for (int i2 = 0; i2 < jSONArrayOptJSONArray3.length(); i2++) {
                    arrayList2.add(Integer.valueOf(jSONArrayOptJSONArray3.optInt(i2)));
                }
                if (arrayList2.size() > 0) {
                    campaign.setRsIgnoreCheckRule(arrayList2);
                }
            }
            return campaign;
        } catch (Exception e3) {
            e = e3;
            e.printStackTrace();
            return campaignEx;
        }
    }

    public static m TrackingStr2Object(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(str);
            m mVar = new m();
            mVar.r(processNativeVideoTrackingArray(jSONObject.optJSONArray(JSON_NATIVE_VIDOE_IMPRESSION)));
            mVar.g(processNativeVideoTrackingArray(jSONObject.optJSONArray("start")));
            mVar.h(processNativeVideoTrackingArray(jSONObject.optJSONArray(JSON_NATIVE_VIDEO_FIRST_QUARTILE)));
            mVar.i(processNativeVideoTrackingArray(jSONObject.optJSONArray(JSON_NATIVE_VIDEO_MIDPOINT)));
            mVar.j(processNativeVideoTrackingArray(jSONObject.optJSONArray(JSON_NATIVE_VIDEO_THIRD_QUARTILE)));
            mVar.k(processNativeVideoTrackingArray(jSONObject.optJSONArray(JSON_NATIVE_VIDEO_COMPLETE)));
            mVar.a(parsePlayCentage(jSONObject.optJSONArray(JSON_NATIVE_VIDEO_PLAY_PERCENTAGE)));
            mVar.l(processNativeVideoTrackingArray(jSONObject.optJSONArray(JSON_NATIVE_VIDEO_MUTE)));
            mVar.m(processNativeVideoTrackingArray(jSONObject.optJSONArray(JSON_NATIVE_VIDEO_UNMUTE)));
            mVar.n(processNativeVideoTrackingArray(jSONObject.optJSONArray(JSON_NATIVE_VIDEO_CLICK)));
            mVar.o(processNativeVideoTrackingArray(jSONObject.optJSONArray(JSON_NATIVE_VIDEO_PAUSE)));
            mVar.p(processNativeVideoTrackingArray(jSONObject.optJSONArray(JSON_NATIVE_VIDEO_RESUME)));
            mVar.q(processNativeVideoTrackingArray(jSONObject.optJSONArray(JSON_NATIVE_VIDEO_ERROR)));
            mVar.s(processNativeVideoTrackingArray(jSONObject.optJSONArray(JSON_NATIVE_VIDEO_ENDCARD)));
            mVar.u(processNativeVideoTrackingArray(jSONObject.optJSONArray(JSON_NATIVE_VIDEO_CLOSE)));
            mVar.t(processNativeVideoTrackingArray(jSONObject.optJSONArray(JSON_NATIVE_VIDEO_ENDCARD_SHOW)));
            mVar.v(processNativeVideoTrackingArray(jSONObject.optJSONArray(JSON_NATIVE_VIDEO_VIDEO_CLICK)));
            mVar.f(processNativeVideoTrackingArray(jSONObject.optJSONArray(JSON_KEY_AD_TRACKING_IMPRESSION_T2)));
            mVar.a(processNativeVideoTrackingArray(jSONObject.optJSONArray(JSON_KEY_AD_TRACKING_DROPOUT_TRACK)));
            mVar.b(processNativeVideoTrackingArray(jSONObject.optJSONArray(JSON_KEY_AD_TRACKING_PLYCMPT_TRACK)));
            return trackingStr2Object(jSONObject, mVar);
        } catch (JSONException unused) {
            return null;
        }
    }

    protected static String[] processNativeVideoTrackingArray(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() <= 0) {
            return null;
        }
        String[] strArr = new String[jSONArray.length()];
        for (int i = 0; i < jSONArray.length(); i++) {
            strArr[i] = jSONArray.optString(i);
        }
        return strArr;
    }

    private Map<Integer, String> generateAdImpression(String str) {
        HashMap map = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() <= 0) {
                return null;
            }
            HashMap map2 = new HashMap();
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
                    int iOptInt = jSONObjectOptJSONObject.optInt(JSON_AD_IMP_KEY);
                    map2.put(Integer.valueOf(iOptInt), jSONObjectOptJSONObject.optString("url"));
                } catch (Exception e) {
                    e = e;
                    map = map2;
                    e.printStackTrace();
                    return map;
                }
            }
            return map2;
        } catch (Exception e2) {
            e = e2;
        }
    }

    public List<String> getAdUrlList() {
        String ad_url_list = getAd_url_list();
        ArrayList arrayList = null;
        try {
            if (!TextUtils.isEmpty(ad_url_list)) {
                JSONArray jSONArray = new JSONArray(ad_url_list);
                ArrayList arrayList2 = new ArrayList();
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        arrayList2.add(jSONArray.optString(i));
                    } catch (Exception e) {
                        arrayList = arrayList2;
                        e = e;
                        e.printStackTrace();
                        return arrayList;
                    }
                }
                return arrayList2;
            }
        } catch (Exception e2) {
            e = e2;
        }
        return arrayList;
    }

    public static JSONArray parseCamplistToJson(List<CampaignEx> list) {
        JSONArray jSONArray = null;
        if (list != null) {
            try {
                if (list.size() > 0) {
                    JSONArray jSONArray2 = new JSONArray();
                    try {
                        Iterator<CampaignEx> it = list.iterator();
                        while (it.hasNext()) {
                            try {
                                jSONArray2.put(campaignToJsonObject(it.next()));
                            } catch (Throwable th) {
                                th.printStackTrace();
                            }
                        }
                        return jSONArray2;
                    } catch (Exception e) {
                        e = e;
                        jSONArray = jSONArray2;
                        e.printStackTrace();
                        return jSONArray;
                    }
                }
            } catch (Exception e2) {
                e = e2;
            }
        }
        return jSONArray;
    }

    public static JSONObject campaignToJsonObject(CampaignEx campaignEx, boolean z, boolean z2) throws JSONException {
        JSONObject jSONObjectCampaignToJsonObject = campaignToJsonObject(campaignEx);
        jSONObjectCampaignToJsonObject.put("isReady", z);
        jSONObjectCampaignToJsonObject.put("expired", z2);
        return jSONObjectCampaignToJsonObject;
    }

    public static JSONObject campaignToJsonObject(CampaignEx campaignEx) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", campaignEx.getId());
        jSONObject.put(JSON_KEY_FLB, campaignEx.getFlb());
        jSONObject.put(JSON_KEY_FLB_SKIP_TIME, campaignEx.getFlbSkipTime());
        if (!TextUtils.isEmpty(campaignEx.getCampaignUnitId())) {
            jSONObject.put("unitId", campaignEx.getCampaignUnitId());
        }
        if (!TextUtils.isEmpty(campaignEx.getExt_data())) {
            try {
                jSONObject.put(JSON_KEY_EXT_DATA, new JSONObject(campaignEx.getExt_data()));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (!TextUtils.isEmpty(campaignEx.getReq_ext_data())) {
            try {
                jSONObject.put(CampaignUnit.JSON_KEY_REQ_EXT_DATA, new JSONObject(campaignEx.getReq_ext_data()));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        if (campaignEx.getPv_urls() != null && campaignEx.getPv_urls().size() > 0) {
            try {
                JSONArray jSONArray = new JSONArray();
                Iterator<String> it = campaignEx.getPv_urls().iterator();
                while (it.hasNext()) {
                    jSONArray.put(it.next());
                }
                jSONObject.put("pv_urls", jSONArray);
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
        }
        if (!TextUtils.isEmpty(campaignEx.getMof_template_url())) {
            jSONObject.put(CampaignUnit.JSON_KEY_MOF_TPLID, campaignEx.getMof_tplid());
            jSONObject.put(CampaignUnit.JSON_KEY_MOF_TEMPLATE_URL, campaignEx.getMof_template_url());
            jSONObject.put(CampaignUnit.JSON_KEY_NSCPT, campaignEx.getNscpt());
        }
        jSONObject.put(JSON_KEY_READY_RATE, campaignEx.getReady_rate());
        jSONObject.put("title", campaignEx.getAppName());
        jSONObject.put(JSON_KEY_DESC, campaignEx.getAppDesc());
        jSONObject.put(JSON_KEY_PACKAGE_NAME, campaignEx.getPackageName());
        jSONObject.put(JSON_KEY_RETARGET_TYPE, campaignEx.getRtinsType());
        jSONObject.put(JSON_KEY_ICON_URL, campaignEx.getIconUrl());
        jSONObject.put(JSON_KEY_IMAGE_URL, campaignEx.getImageUrl());
        jSONObject.put(JSON_KEY_APP_SIZE, campaignEx.getSize());
        jSONObject.put(JSON_KEY_IMAGE_SIZE, campaignEx.getImageSize());
        jSONObject.put(JSON_KEY_IMPRESSION_URL, campaignEx.getImpressionURL());
        jSONObject.put(JSON_KEY_CLICK_URL, campaignEx.getClickURL());
        if (campaignEx.getRewardPlus() != null) {
            jSONObject.put(JSON_KEY_REWARD_PLUS, campaignEx.getRewardPlus().toJsonObject());
        }
        jSONObject.put(JSON_KEY_WITHOUT_INSTALL_CHECK, campaignEx.getWtick());
        jSONObject.put(JSON_KEY_DEEP_LINK_URL, campaignEx.getDeepLinkURL());
        jSONObject.put(JSON_KEY_USER_ACTIVATION, campaignEx.getUserActivation());
        jSONObject.put(JSON_KEY_NOTICE_URL, campaignEx.getNoticeUrl());
        jSONObject.put("template", campaignEx.getTemplate());
        jSONObject.put(JSON_KEY_AD_SOURCE_ID, campaignEx.getType());
        jSONObject.put(JSON_KEY_FCA, campaignEx.getFca());
        jSONObject.put(JSON_KEY_FCB, campaignEx.getFcb());
        jSONObject.put(JSON_KEY_STAR, campaignEx.getRating() + "");
        jSONObject.put(JSON_KEY_NUMBER_RATING, campaignEx.getNumberRating());
        jSONObject.put(JSON_KEY_CLICK_MODE, campaignEx.getClick_mode());
        jSONObject.put(JSON_KEY_LANDING_TYPE, campaignEx.getLandingType());
        jSONObject.put(JSON_KEY_LINK_TYPE, campaignEx.getLinkType());
        jSONObject.put(JSON_KEY_CLICK_INTERVAL, campaignEx.getClickInterval());
        jSONObject.put(JSON_KEY_CTA_TEXT, campaignEx.getAdCall());
        jSONObject.put(JSON_KEY_ENDCARD_CLICK, campaignEx.getEndcard_click_result());
        jSONObject.put(JSON_KEY_RETARGET_OFFER, campaignEx.getRetarget_offer());
        jSONObject.put(JSON_KEY_VIDEO_URL, campaignEx.getVideoUrlEncode());
        jSONObject.put(JSON_KEY_VIDEO_LENGTHL, campaignEx.getVideoLength());
        jSONObject.put(JSON_KEY_VIDEO_SIZE, campaignEx.getVideoSize());
        jSONObject.put(JSON_KEY_VIDEO_RESOLUTION, campaignEx.getVideoResolution());
        jSONObject.put(JSON_KEY_WATCH_MILE, campaignEx.getWatchMile());
        jSONObject.put(JSON_KEY_AD_URL_LIST, campaignEx.getAd_url_list());
        jSONObject.put("only_impression_url", campaignEx.getOnlyImpressionURL());
        jSONObject.put(JSON_KEY_BTY, campaignEx.getBty());
        jSONObject.put(JSON_KEY_T_IMP, campaignEx.getTImp());
        jSONObject.put(JSON_KEY_ADVIMP, campaignEx.getAdvImp());
        jSONObject.put("html_url", campaignEx.getHtmlUrl());
        jSONObject.put("end_screen_url", campaignEx.getEndScreenUrl());
        jSONObject.put(JSON_KEY_GUIDELINES, campaignEx.getGuidelines());
        jSONObject.put(JSON_KEY_OFFER_TYPE, campaignEx.getOfferType());
        jSONObject.put(JSON_KEY_REWARD_AMOUNT, campaignEx.getRewardAmount());
        jSONObject.put(JSON_KEY_REWARD_NAME, campaignEx.getRewardName());
        jSONObject.put(JSON_KEY_GIF_URL, campaignEx.getGifUrl());
        if (z.b(campaignEx.getNativeVideoTrackingString())) {
            jSONObject.put(JSON_NATIVE_VIDEO_AD_TRACKING, new JSONObject(campaignEx.getNativeVideoTrackingString()));
        }
        jSONObject.put(VIDEO_END_TYPE, campaignEx.getVideo_end_type());
        jSONObject.put(ENDCARD_URL, campaignEx.getendcard_url());
        jSONObject.put(PLAYABLE_ADS_WITHOUT_VIDEO, campaignEx.getPlayable_ads_without_video());
        if (campaignEx != null && campaignEx.getRewardTemplateMode() != null && z.b(campaignEx.getRewardTemplateMode().a())) {
            jSONObject.put(JSON_KEY_REWARD_TEMPLATE, new JSONObject(campaignEx.getRewardTemplateMode().a()));
        }
        jSONObject.put(JSON_KEY_REWARD_VIDEO_MD5, campaignEx.getVideoMD5Value());
        jSONObject.put(JSON_KEY_CLICK_TIMEOUT_INTERVAL, campaignEx.getClickTimeOutInterval());
        jSONObject.put(JSON_KEY_C_UA, campaignEx.getcUA());
        jSONObject.put(JSON_KEY_IMP_UA, campaignEx.getImpUA());
        jSONObject.put(JSON_KEY_JM_PD, campaignEx.getJmPd());
        jSONObject.put("ia_icon", campaignEx.getKeyIaIcon());
        jSONObject.put("ia_rst", campaignEx.getKeyIaRst());
        jSONObject.put("ia_url", campaignEx.getKeyIaUrl());
        jSONObject.put("ia_ori", campaignEx.getKeyIaOri());
        jSONObject.put("ad_type", campaignEx.getAdType());
        jSONObject.put(KEY_IA_EXT1, campaignEx.getIa_ext1());
        jSONObject.put(KEY_IA_EXT2, campaignEx.getIa_ext2());
        jSONObject.put(KEY_IS_DOWNLOAD, campaignEx.getIsDownLoadZip());
        jSONObject.put(KEY_IA_CACHE, campaignEx.getInteractiveCache());
        jSONObject.put(KEY_OC_TYPE, campaignEx.getOc_type());
        jSONObject.put(KEY_OC_TIME, campaignEx.getOc_time());
        jSONObject.put(KEY_T_LIST, campaignEx.getT_list());
        a adchoice = campaignEx.getAdchoice();
        if (adchoice != null) {
            jSONObject.put(KEY_ADCHOICE, new JSONObject(adchoice.c()));
        }
        jSONObject.put(JSON_KEY_PLCT, campaignEx.getPlct());
        jSONObject.put(JSON_KEY_PLCTB, campaignEx.getPlctb());
        jSONObject.put(KEY_OMID, campaignEx.getOmid());
        jSONObject.put(JSON_KEY_CREATIVE_ID, campaignEx.getCreativeId());
        jSONObject.put("cam_html", campaignEx.getBannerHtml());
        jSONObject.put("cam_tpl_url", campaignEx.getBannerUrl() != null ? campaignEx.getBannerUrl() : campaignEx.getAdZip());
        jSONObject.put(JSON_KEY_MRAID, campaignEx.getMraid());
        jSONObject.put(JSON_KEY_MRAIDFORH5, campaignEx.getMraid());
        jSONObject.put(JSON_KEY_TIMESTAMP, campaignEx.getTimestamp());
        jSONObject.put(JSON_KEY_HB, campaignEx.isBidCampaign);
        jSONObject.put(MBridgeConstans.PLACEMENT_ID, campaignEx.getPlacementId());
        jSONObject.put(JSON_KEY_MAITVE, campaignEx.getMaitve());
        jSONObject.put(JSON_KEY_MAITVESRC, campaignEx.getMaitve_src());
        jSONObject.put("vcn", campaignEx.getVcn());
        jSONObject.put("token_r", campaignEx.getTokenRule());
        jSONObject.put("encrypt_p", campaignEx.getEncryptPrice());
        jSONObject.put(JSON_KEY_RS_IGNORE_CHECK_RULE, campaignEx.getRsIgnoreCheckRule());
        jSONObject.put(JSON_KEY_VIDEO_CHECK_TYPE, campaignEx.getVideoCheckType());
        jSONObject.put(JSON_KEY_VIDEO_CTN_TYPE, campaignEx.getVideoCtnType());
        try {
            jSONObject.put("misk_spt", w.c());
            jSONObject.put("misk_spt_det", w.d());
        } catch (JSONException unused) {
        }
        return campaignToJsonObject(jSONObject, campaignEx);
    }

    private static List<Map<Integer, String>> parsePlayCentage(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    String string = jSONArray.getString(i);
                    if (!TextUtils.isEmpty(string)) {
                        JSONObject jSONObject = new JSONObject(string);
                        HashMap map = new HashMap();
                        int i2 = jSONObject.getInt("rate");
                        map.put(Integer.valueOf(i2), jSONObject.getString("url"));
                        arrayList.add(map);
                    }
                } catch (Throwable unused) {
                }
            }
        }
        return arrayList;
    }

    public boolean isCallbacked() {
        return this.isCallbacked;
    }

    public void setCallbacked(boolean z) {
        this.isCallbacked = z;
    }

    public int getReady_rate() {
        return this.ready_rate;
    }

    public void setReady_rate(int i) {
        this.ready_rate = i;
    }

    public String getExt_data() {
        return this.ext_data;
    }

    public void setExt_data(String str) {
        this.ext_data = str;
    }

    public int getNscpt() {
        return this.nscpt;
    }

    public void setNscpt(int i) {
        this.nscpt = i;
    }

    public String getMof_template_url() {
        return this.mof_template_url;
    }

    public void setMof_template_url(String str) {
        this.mof_template_url = str;
    }

    public int getMof_tplid() {
        return this.mof_tplid;
    }

    public void setMof_tplid(int i) {
        this.mof_tplid = i;
    }

    public String getReq_ext_data() {
        return this.req_ext_data;
    }

    public void setReq_ext_data(String str) {
        this.req_ext_data = str;
    }

    public void setReadyState(int i) {
        this.readyState = i;
    }

    public boolean isReady() {
        return this.isReady;
    }

    public void setReady(boolean z) {
        this.isReady = z;
    }

    public int getLoadTimeoutState() {
        return this.loadTimeoutState;
    }

    public void setLoadTimeoutState(int i) {
        this.loadTimeoutState = i;
    }

    public int getMaitve() {
        return this.maitve;
    }

    public void setMaitve(int i) {
        this.maitve = i;
    }

    public String getMaitve_src() {
        return this.maitve_src;
    }

    public void setMaitve_src(String str) {
        this.maitve_src = str;
    }

    public static final class c implements Serializable {
        private String a;
        private int b;
        private int c;
        private String d;
        private String e;
        private List<a> f;

        public static final class a implements Serializable {
            public String a;
            public List<String> b = new ArrayList();
        }

        private c(String str) {
            this.a = str;
        }

        public final String a() {
            return this.a;
        }

        public final int b() {
            return this.c;
        }

        public final String c() {
            return this.d;
        }

        public final String d() {
            return this.e;
        }

        public final List<a> e() {
            return this.f;
        }

        public static c a(String str) {
            try {
                if (z.b(str)) {
                    return a(new JSONObject(str));
                }
                return null;
            } catch (Throwable unused) {
                return null;
            }
        }

        public static c a(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            try {
                if (!z.b(jSONObject.toString())) {
                    return null;
                }
                c cVar = new c(jSONObject.toString());
                cVar.b = jSONObject.optInt("video_template", 1);
                cVar.e = jSONObject.optString("template_url");
                cVar.c = jSONObject.optInt("orientation");
                cVar.d = jSONObject.optString("paused_url");
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("image");
                if (jSONObjectOptJSONObject != null) {
                    ArrayList arrayList = new ArrayList();
                    Iterator<String> itKeys = jSONObjectOptJSONObject.keys();
                    while (itKeys != null && itKeys.hasNext()) {
                        String next = itKeys.next();
                        List<String> listB = w.b(jSONObjectOptJSONObject.optJSONArray(next));
                        if (listB != null && listB.size() > 0) {
                            a aVar = new a();
                            aVar.a = next;
                            aVar.b.addAll(listB);
                            arrayList.add(aVar);
                        }
                    }
                    cVar.f = arrayList;
                }
                return cVar;
            } catch (Throwable unused) {
                return null;
            }
        }
    }

    public boolean isHasReportAdTrackPause() {
        return this.hasReportAdTrackPause;
    }

    public void setHasReportAdTrackPause(boolean z) {
        this.hasReportAdTrackPause = z;
    }

    public static final class a implements Serializable {
        private String a = "";
        private String b = "";
        private String c = "";
        private String d = "";
        private String e = "";
        private String f = "";
        private String g = "";
        private String h = "";
        private int i = 0;
        private int j = 0;
        private String k = "";

        public final int a() {
            return this.i;
        }

        public final int b() {
            return this.j;
        }

        public final String c() {
            return this.k;
        }

        public final String d() {
            return this.b;
        }

        public final String e() {
            return this.c;
        }

        public final String f() {
            return this.d;
        }

        public static a a(String str) {
            try {
                if (TextUtils.isEmpty(str)) {
                    return null;
                }
                return a(new JSONObject(str));
            } catch (Exception e) {
                if (MBridgeConstans.DEBUG) {
                    e.printStackTrace();
                }
                return null;
            } catch (Throwable th) {
                if (MBridgeConstans.DEBUG) {
                    th.printStackTrace();
                }
                return null;
            }
        }

        private static a a(JSONObject jSONObject) {
            a aVar;
            if (jSONObject == null) {
                return null;
            }
            try {
                aVar = new a();
                try {
                    aVar.c = jSONObject.optString("adchoice_icon");
                    aVar.b = jSONObject.optString("adchoice_link");
                    String strOptString = jSONObject.optString("adchoice_size");
                    aVar.d = strOptString;
                    aVar.a = jSONObject.optString("ad_logo_link");
                    aVar.h = jSONObject.optString("adv_logo");
                    aVar.g = jSONObject.optString("adv_name");
                    aVar.f = jSONObject.optString("platform_logo");
                    aVar.e = jSONObject.optString("platform_name");
                    aVar.j = b(strOptString);
                    aVar.i = c(strOptString);
                    aVar.k = jSONObject.toString();
                    return aVar;
                } catch (Exception e) {
                    e = e;
                    if (!MBridgeConstans.DEBUG) {
                        return aVar;
                    }
                    e.printStackTrace();
                    return aVar;
                } catch (Throwable th) {
                    th = th;
                    if (!MBridgeConstans.DEBUG) {
                        return aVar;
                    }
                    th.printStackTrace();
                    return aVar;
                }
            } catch (Exception e2) {
                e = e2;
                aVar = null;
            } catch (Throwable th2) {
                th = th2;
                aVar = null;
            }
        }

        private static int b(String str) {
            String[] strArrSplit;
            if (TextUtils.isEmpty(str)) {
                return 0;
            }
            try {
                if (!str.contains("x") || (strArrSplit = str.split("x")) == null || strArrSplit.length <= 1) {
                    return 0;
                }
                return Integer.parseInt(strArrSplit[1]);
            } catch (NumberFormatException unused) {
                return 0;
            } catch (Exception unused2) {
                return 0;
            }
        }

        private static int c(String str) {
            String[] strArrSplit;
            if (TextUtils.isEmpty(str)) {
                return 0;
            }
            try {
                if (!str.contains("x") || (strArrSplit = str.split("x")) == null || strArrSplit.length <= 0) {
                    return 0;
                }
                return Integer.parseInt(strArrSplit[0]);
            } catch (NumberFormatException unused) {
                return 0;
            } catch (Exception unused2) {
                return 0;
            }
        }
    }

    public boolean isSpareOffer(long j, long j2) {
        if (isEffectiveOffer(j)) {
            return false;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        return getPlctb() > 0 ? getTimestamp() + (getPlctb() * 1000) >= jCurrentTimeMillis : getTimestamp() + j2 >= jCurrentTimeMillis;
    }

    public boolean isEffectiveOffer(long j) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        return getPlct() > 0 ? getTimestamp() + (getPlct() * 1000) >= jCurrentTimeMillis : getTimestamp() + j >= jCurrentTimeMillis;
    }

    public String getOmid() {
        return this.omid;
    }

    public void setOmid(String str) {
        this.omid = str;
    }

    public boolean isActiveOm() {
        return (TextUtils.isEmpty(this.omid) || TextUtils.isEmpty(MBridgeConstans.OMID_JS_SERVICE_URL)) ? false : true;
    }

    public int getCbt() {
        return this.cbt;
    }

    public void setCbt(int i) {
        this.cbt = i;
    }

    public String getPlacementId() {
        return this.placementId;
    }

    public void setPlacementId(String str) {
        this.placementId = str;
    }

    public boolean needShowIDialog() {
        return super.needShowIDialog(this);
    }

    public long getcVersionCode() {
        return this.cVersionCode;
    }

    public void setcVersionCode(long j) {
        this.cVersionCode = j;
    }

    public ArrayList<Integer> getRsIgnoreCheckRule() {
        return this.rsIgnoreCheckRule;
    }

    public String getRsIgnoreCheckRuleString() {
        if (getRsIgnoreCheckRule() == null || getRsIgnoreCheckRule().size() <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        try {
            Iterator<Integer> it = getRsIgnoreCheckRule().iterator();
            while (it.hasNext()) {
                sb.append(it.next());
                sb.append(",");
            }
            if (sb.length() > 0) {
                sb.delete(sb.length() - 1, sb.length());
            }
            return !TextUtils.isEmpty(sb) ? sb.toString() : "";
        } catch (Throwable th) {
            s.d(TAG, th.getMessage());
            return "";
        }
    }

    public void setRsIgnoreCheckRule(ArrayList<Integer> arrayList) {
        this.rsIgnoreCheckRule = arrayList;
    }

    public void setRsIgnoreCheckRuleByString(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String[] strArrSplit = str.split(",");
        if (strArrSplit.length > 0) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            for (String str2 : strArrSplit) {
                arrayList.add(Integer.valueOf(Integer.parseInt(str2)));
            }
            setRsIgnoreCheckRule(arrayList);
        }
    }

    public int getVideoCheckType() {
        return this.videoCheckType;
    }

    public void setVideoCheckType(int i) {
        if (i > 2 || i < 1) {
            this.videoCheckType = 2;
        } else {
            this.videoCheckType = i;
        }
    }

    public int getVideoCtnType() {
        return this.videoCtnType;
    }

    public void setVideoCtnType(int i) {
        if (i > 2 || i < 1) {
            this.videoCtnType = 1;
        } else {
            this.videoCtnType = i;
        }
    }

    public int getIsTimeoutCheckVideoStatus() {
        return this.isTimeoutCheckVideoStatus;
    }

    public void setIsTimeoutCheckVideoStatus(int i) {
        this.isTimeoutCheckVideoStatus = i;
    }
}
