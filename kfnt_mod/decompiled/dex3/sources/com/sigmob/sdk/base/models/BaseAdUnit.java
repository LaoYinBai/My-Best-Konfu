package com.sigmob.sdk.base.models;

import android.text.TextUtils;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.a.c;
import com.sigmob.sdk.base.common.e;
import com.sigmob.sdk.base.common.f;
import com.sigmob.sdk.base.common.j;
import com.sigmob.sdk.base.common.v;
import com.sigmob.sdk.base.views.b;
import com.sigmob.sdk.common.Constants;
import com.sigmob.sdk.common.a.b;
import com.sigmob.sdk.common.a.c;
import com.sigmob.sdk.common.b.c;
import com.sigmob.sdk.common.models.sigdsp.pb.Ad;
import com.sigmob.sdk.common.models.sigdsp.pb.AdPrivacy;
import com.sigmob.sdk.common.models.sigdsp.pb.AdSetting;
import com.sigmob.sdk.common.models.sigdsp.pb.ClickAreaSetting;
import com.sigmob.sdk.common.models.sigdsp.pb.MaterialMeta;
import com.sigmob.sdk.common.models.sigdsp.pb.NativeAdSetting;
import com.sigmob.sdk.common.models.sigdsp.pb.ResponseAsset;
import com.sigmob.sdk.common.models.sigdsp.pb.ResponseAssetImage;
import com.sigmob.sdk.common.models.sigdsp.pb.ResponseAssetVideo;
import com.sigmob.sdk.common.models.sigdsp.pb.ResponseNativeAd;
import com.sigmob.sdk.common.models.sigdsp.pb.RvAdSetting;
import com.sigmob.sdk.common.models.sigdsp.pb.SingleNativeAdSetting;
import com.sigmob.sdk.common.models.sigdsp.pb.SlotAdSetting;
import com.sigmob.sdk.common.models.sigdsp.pb.SplashAdSetting;
import com.sigmob.sdk.common.models.sigdsp.pb.Tracking;
import com.sigmob.sdk.common.utils.d;
import com.sigmob.sdk.common.utils.k;
import com.sigmob.sdk.common.utils.m;
import com.sigmob.sdk.common.utils.n;
import com.sigmob.sdk.videoAd.a;
import com.ss.android.pushmanager.setting.PushMultiProcessSharedProvider;
import com.unity.purchasing.googleplay.Consts;
import java.io.File;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class BaseAdUnit implements Serializable {
    private static final long serialVersionUID = 1;
    private String a;
    private String b;
    private String c;
    private Ad d;
    private int e;
    private long f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private List m;
    private SigVideo n;
    private HashMap<String, List<e>> o;
    private SigMacroCommon p;
    private VideoStatusCommon q;
    private ClickCommon r;
    private String s;
    private SlotAdSetting t;
    private double u = -1.0d;
    private double v = -1.0d;
    private transient v w;
    private int x;
    private transient f y;

    private String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(d.a() + String.format("/%s.mp4", getVideo_md5()));
        if (file.exists()) {
            return file.getAbsolutePath();
        }
        File fileC = com.sigmob.sdk.base.common.d.e().c(str);
        if (fileC == null || !fileC.exists()) {
            return null;
        }
        return fileC.getAbsolutePath();
    }

    private static boolean a(String str, String str2) throws Throwable {
        String strB = k.b(str);
        SigmobLog.d("path: [ " + str + " ] calc [ " + strB + " ] origin " + str2);
        return strB != null && strB.equalsIgnoreCase(str2);
    }

    public static BaseAdUnit adUnit(Ad ad, String str, String str2, SlotAdSetting slotAdSetting) {
        ResponseNativeAd nativeAd;
        BaseAdUnit baseAdUnit = null;
        try {
            MaterialMeta materialMeta = ad.materials.get(0);
            if (materialMeta == null) {
                return null;
            }
            BaseAdUnit baseAdUnit2 = new BaseAdUnit();
            try {
                baseAdUnit2.f = System.currentTimeMillis();
                baseAdUnit2.a = ad.adslot_id;
                baseAdUnit2.e = ad.ad_type.intValue();
                baseAdUnit2.d = ad;
                baseAdUnit2.c = ad.crid;
                baseAdUnit2.b = ad.camp_id;
                baseAdUnit2.j = str;
                baseAdUnit2.h = materialMeta.endcard_md5;
                baseAdUnit2.g = materialMeta.video_md5;
                baseAdUnit2.k = str2;
                baseAdUnit2.i = ad.ad_source_channel;
                if ((materialMeta.creative_type.intValue() == j.CreativeTypeVideo_Html_Snippet.a() || materialMeta.creative_type.intValue() == j.CreativeTypeVideo_transparent_html.a()) && materialMeta.html_snippet != null) {
                    materialMeta.html_snippet.size();
                }
                baseAdUnit2.t = slotAdSetting;
                j(baseAdUnit2);
                if (ad.ad_track_macro != null) {
                    baseAdUnit2.getMacroCommon().setServerMacroMap(ad.ad_track_macro);
                }
                if (baseAdUnit2.e == 5 && (nativeAd = baseAdUnit2.getNativeAd()) == null) {
                    if (nativeAd.type.intValue() == 1) {
                        baseAdUnit2.getNativeVideo();
                    } else {
                        baseAdUnit2.getImageUrlList();
                    }
                }
                return baseAdUnit2;
            } catch (Throwable th) {
                th = th;
                baseAdUnit = baseAdUnit2;
                SigmobLog.e("adUnit error", th);
                return baseAdUnit;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static b.a createFileRefTable() {
        b.a.C0017a c0017a = new b.a.C0017a();
        c0017a.a("file_reference");
        c0017a.a("crid", "text");
        c0017a.a("adslot_id", "text");
        HashMap map = new HashMap();
        map.put("crid", "text");
        map.put("adslot_id", "text");
        map.put("video_md5", PushMultiProcessSharedProvider.STRING_TYPE);
        map.put("endcard_md5", PushMultiProcessSharedProvider.STRING_TYPE);
        c0017a.a(map);
        return c0017a.a();
    }

    public static b.a createTable() {
        b.a.C0017a c0017a = new b.a.C0017a();
        c0017a.a("ads");
        c0017a.a("crid", "text");
        c0017a.a("adslot_id", "text");
        Map<String, String> mapB = n.b(BaseAdUnit.class);
        if (mapB != null) {
            mapB.remove("serialVersionUID");
            mapB.remove("$change");
            mapB.remove("TAG");
            mapB.remove("macroCommon");
            c0017a.a(mapB);
        }
        return c0017a.a();
    }

    public static List<e> createTrackersForUrls(List<String> list, String str, String str2, Integer num) {
        m.a.a(list);
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            e eVar = new e(it.next(), str, str2);
            eVar.a(num);
            arrayList.add(eVar);
        }
        return arrayList;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static String getTAG() {
        return "BaseAdUnit";
    }

    private static void j(BaseAdUnit baseAdUnit) {
        List<Tracking> ad_tracking = baseAdUnit.getAd_tracking();
        baseAdUnit.o = new HashMap<>();
        for (Tracking tracking : ad_tracking) {
            baseAdUnit.o.put(tracking.tracking_event_type, createTrackersForUrls(tracking.tracking_url, tracking.tracking_event_type, baseAdUnit.j, Integer.valueOf(baseAdUnit.getTrackingRetryNum())));
        }
    }

    public boolean checkEndCardZipValid() {
        if (TextUtils.isEmpty(getEndcard_url()) || TextUtils.isEmpty(this.h)) {
            return true;
        }
        return a(getEndCardZipPath(), getEndcard_md5());
    }

    public boolean checkVideoValid() {
        if (TextUtils.isEmpty(getVideo_url()) || TextUtils.isEmpty(this.g)) {
            return true;
        }
        return a(getVideoPath(), getVideo_OriginMD5());
    }

    public boolean enable_full_click() {
        SplashAdSetting splashAdSetting = getSplashAdSetting();
        if (splashAdSetting != null) {
            return splashAdSetting.enable_full_click.booleanValue();
        }
        return false;
    }

    public Ad getAd() {
        return this.d;
    }

    public f getAdConfig() {
        if (this.y == null) {
            switch (getAd_type()) {
                case 1:
                case 4:
                    return a.c(this);
                case 2:
                    return com.sigmob.sdk.splash.d.c(this);
                case 3:
                    return c.c(this);
                case 5:
                    return com.sigmob.sdk.nativead.b.c(this);
            }
        }
        return f.a(this);
    }

    public Integer getAdExpiredTime() {
        return Integer.valueOf((this.d == null || this.d.expired_time == null) ? 0 : this.d.expired_time.intValue() * 1000);
    }

    public String getAdLogo() {
        return getAd_source_logo();
    }

    public double getAdPercent() {
        if (this.u > 0.0d) {
            return this.u;
        }
        if (this.v > 0.0d) {
            return this.v;
        }
        return 1.7777777910232544d;
    }

    public File getAdPrivacyTemplateFile() {
        AdPrivacy adPrivacy = getadPrivacy();
        if (adPrivacy == null) {
            return null;
        }
        String str = adPrivacy.privacy_template_url;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String strA = k.a(str);
        return new File(d.g(d.b), strA + ".html");
    }

    public AdSetting getAdSetting() {
        if (this.d != null) {
            return this.d.ad_setting;
        }
        return null;
    }

    public List<e> getAdTracker(com.sigmob.sdk.base.common.a aVar) {
        if (this.o != null) {
            return this.o.get(aVar.a());
        }
        return null;
    }

    public List<e> getAdTracker(String str) {
        if (this.o != null) {
            return this.o.get(str);
        }
        return null;
    }

    public String getAd_source_channel() {
        return this.i;
    }

    public String getAd_source_logo() {
        if (Constants.IS_MOCK.booleanValue()) {
            return "https://n.sigmob.cn/icon/sig_logo.png";
        }
        if (this.d != null) {
            return this.d.ad_source_logo;
        }
        return null;
    }

    public List<Tracking> getAd_tracking() {
        if (this.d != null) {
            return this.d.ad_tracking;
        }
        return null;
    }

    public int getAd_type() {
        return this.e;
    }

    public String getAdslot_id() {
        return this.a;
    }

    public String getAppName() {
        AdPrivacy adPrivacy = getadPrivacy();
        if (adPrivacy == null || adPrivacy.privacy_template_info == null) {
            return null;
        }
        return adPrivacy.privacy_template_info.get("app_name");
    }

    public String getAppVersion() {
        AdPrivacy adPrivacy = getadPrivacy();
        if (adPrivacy == null || adPrivacy.privacy_template_info == null) {
            return null;
        }
        return adPrivacy.privacy_template_info.get("app_version");
    }

    public String getBid_token() {
        return this.l;
    }

    public String getCTAText() {
        MaterialMeta material = getMaterial();
        String str = material != null ? material.button_text : null;
        return !TextUtils.isEmpty(str) ? str : material.interaction_type.intValue() == 1 ? "查看详情" : "立即下载";
    }

    public String getCamp_id() {
        return this.b;
    }

    public ClickAreaSetting getClickAreaSetting() {
        RvAdSetting rvAdSetting = getRvAdSetting();
        if (!Constants.IS_MOCK.booleanValue() && rvAdSetting.click_setting != null) {
            if (rvAdSetting != null) {
                return rvAdSetting.click_setting;
            }
            return null;
        }
        ClickAreaSetting.Builder builder = new ClickAreaSetting.Builder();
        builder.bottom = Float.valueOf(0.1f);
        builder.right = Float.valueOf(0.1f);
        builder.top = Float.valueOf(0.1f);
        builder.left = Float.valueOf(0.1f);
        return builder.build();
    }

    public ClickCommon getClickCommon() {
        if (this.r == null) {
            this.r = new ClickCommon();
        }
        return this.r;
    }

    public String getCloseCardHtmlData() {
        if (getMaterial() == null) {
            return null;
        }
        if (getMaterial().closecard_html_snippet != null || getMaterial().closecard_html_snippet.size() >= 10) {
            return getMaterial().closecard_html_snippet.utf8();
        }
        return null;
    }

    public String getCompanyName() {
        AdPrivacy adPrivacy = getadPrivacy();
        if (adPrivacy == null || adPrivacy.privacy_template_info == null) {
            return null;
        }
        return adPrivacy.privacy_template_info.get("app_company");
    }

    public long getCreate_time() {
        return this.f;
    }

    public b.EnumC0014b getCreativeResourceType() {
        return (TextUtils.isEmpty(getEndcard_url()) || !(getCreativeType() == j.CreativeTypeVideo_Tar.a() || getCreativeType() == j.CreativeTypeVideo_Tar_Companion.a())) ? !TextUtils.isEmpty(getHtmlData()) ? b.EnumC0014b.HTML_RESOURCE : !TextUtils.isEmpty(getHtmlUrl()) ? b.EnumC0014b.URL_RESOURCE : b.EnumC0014b.NATIVE_RESOURCE : b.EnumC0014b.NATIVE_RESOURCE;
    }

    public int getCreativeType() {
        if (getMaterial() != null) {
            return getMaterial().creative_type.intValue();
        }
        return 0;
    }

    public String getCrid() {
        return this.c;
    }

    public String getDesc() {
        MaterialMeta material = getMaterial();
        if (material != null) {
            return material.desc;
        }
        return null;
    }

    public boolean getDisableAutoLoad() {
        RvAdSetting rvAdSetting = getRvAdSetting();
        if (rvAdSetting != null) {
            return rvAdSetting.disable_auto_load.booleanValue();
        }
        return false;
    }

    public String getEndCardDirPath() {
        return d.a() + String.format("/%s/", getEndcard_md5());
    }

    public String getEndCardIndexPath() {
        return getEndCardDirPath() + "endcard.html";
    }

    public String getEndCardZipPath() {
        return d.a() + String.format("/%s.tgz", this.h);
    }

    public String getEndCard_OriginMD5() {
        return this.h;
    }

    public int getEndTime() {
        RvAdSetting rvAdSetting = getRvAdSetting();
        if (rvAdSetting != null) {
            return rvAdSetting.end_time.intValue();
        }
        return 0;
    }

    public int getEndcardCloseImage() {
        RvAdSetting rvAdSetting = getRvAdSetting();
        if (rvAdSetting != null) {
            return rvAdSetting.endcard_close_image.intValue();
        }
        return 0;
    }

    public String getEndcard_md5() {
        return !TextUtils.isEmpty(this.h) ? this.h : k.a(getCrid());
    }

    public String getEndcard_url() {
        MaterialMeta material = getMaterial();
        if (material != null) {
            return material.endcard_url;
        }
        return null;
    }

    public float getFinishedTime() {
        RvAdSetting rvAdSetting = getRvAdSetting();
        if (rvAdSetting != null) {
            return rvAdSetting.finished.floatValue();
        }
        return 1.0f;
    }

    public boolean getFullClickOnVideo() {
        if (Constants.IS_MOCK.booleanValue()) {
            return true;
        }
        RvAdSetting rvAdSetting = getRvAdSetting();
        if (rvAdSetting != null) {
            return rvAdSetting.full_click_on_video.booleanValue();
        }
        return false;
    }

    public String getHtmlData() {
        if (getMaterial() == null) {
            return null;
        }
        if (getMaterial().html_snippet != null || getMaterial().html_snippet.size() >= 10) {
            return getMaterial().html_snippet.utf8();
        }
        return null;
    }

    public String getHtmlUrl() {
        if (getMaterial() == null) {
            return null;
        }
        return getMaterial().html_url;
    }

    public String getIconUrl() {
        MaterialMeta material = getMaterial();
        if (material != null) {
            return material.icon_url;
        }
        return null;
    }

    public List<SigImage> getImageUrlList() {
        ResponseNativeAd nativeAd = getNativeAd();
        if (this.m == null) {
            this.m = new ArrayList();
            if (nativeAd != null && nativeAd.type.intValue() != 1) {
                Iterator<ResponseAsset> it = nativeAd.assets.iterator();
                while (it.hasNext()) {
                    ResponseAssetImage responseAssetImage = it.next().image;
                    if (responseAssetImage != null) {
                        SigImage sigImage = new SigImage();
                        sigImage.imageUrl = responseAssetImage.url;
                        sigImage.height = responseAssetImage.h.intValue();
                        sigImage.width = responseAssetImage.w.intValue();
                        if (this.u < 0.0d && responseAssetImage.w.intValue() > 0 && responseAssetImage.h.intValue() > 0) {
                            this.u = (responseAssetImage.w.intValue() * 1.0f) / responseAssetImage.h.intValue();
                        }
                        this.m.add(sigImage);
                    }
                }
            }
        }
        return this.m;
    }

    public boolean getInvisibleAdLabel() {
        RvAdSetting rvAdSetting;
        Boolean bool;
        if (getAd_type() == 2) {
            SplashAdSetting splashAdSetting = getSplashAdSetting();
            if (splashAdSetting == null) {
                return false;
            }
            bool = splashAdSetting.invisible_ad_label;
        } else {
            if ((getAd_type() != 1 && getAd_type() != 4) || (rvAdSetting = getRvAdSetting()) == null) {
                return false;
            }
            bool = rvAdSetting.invisible_ad_label;
        }
        return bool.booleanValue();
    }

    public String getLanding_page() {
        if (getMaterial() != null) {
            return getMaterial().landing_page;
        }
        return null;
    }

    public String getLoad_id() {
        return this.k;
    }

    public SigMacroCommon getMacroCommon() throws UnsupportedEncodingException {
        if (this.p == null) {
            this.p = new SigMacroCommon();
            String video_url = getVideo_url();
            if (!TextUtils.isEmpty(video_url)) {
                try {
                    String strEncode = URLEncoder.encode(video_url, "UTF-8");
                    if (!TextUtils.isEmpty(strEncode)) {
                        this.p.addMarcoKey(SigMacroCommon._VURL_, strEncode);
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return this.p;
    }

    public MaterialMeta getMaterial() {
        if (this.d == null || this.d.materials == null || this.d.materials.size() <= 0) {
            return null;
        }
        return this.d.materials.get(0);
    }

    public ResponseNativeAd getNativeAd() {
        if (this.d == null || this.d.materials == null || this.d.materials.size() <= 0) {
            return null;
        }
        return this.d.materials.get(0).native_ad;
    }

    public NativeAdSetting getNativeAdSetting() {
        if (this.t != null) {
            return this.t.native_setting;
        }
        return null;
    }

    public SigVideo getNativeVideo() {
        ResponseNativeAd nativeAd = getNativeAd();
        if (this.n == null && nativeAd != null && nativeAd.type.intValue() == 1) {
            for (ResponseAsset responseAsset : nativeAd.assets) {
                ResponseAssetVideo responseAssetVideo = responseAsset.video;
                if (responseAssetVideo != null) {
                    if (this.n == null) {
                        this.n = new SigVideo();
                    }
                    this.n.c = responseAssetVideo.url;
                    this.n.b = responseAssetVideo.h.intValue();
                    this.n.a = responseAssetVideo.w.intValue();
                    if (this.u < 0.0d && responseAssetVideo.h.intValue() > 0 && responseAssetVideo.w.intValue() > 0) {
                        this.u = (responseAssetVideo.w.intValue() * 1.0f) / responseAssetVideo.h.intValue();
                    }
                }
                ResponseAssetImage responseAssetImage = responseAsset.image;
                if (responseAssetImage != null) {
                    if (this.n == null) {
                        this.n = new SigVideo();
                    }
                    this.n.d = responseAssetImage.url;
                }
            }
        }
        return this.n;
    }

    public int getPlayMode() {
        if (getMaterial() != null) {
            return getMaterial().play_mode.intValue();
        }
        return 0;
    }

    public String getProductId() {
        if (getAd() != null) {
            return getAd().product_id;
        }
        return null;
    }

    public String getProxyVideoUrl() {
        String video_url = getVideo_url();
        return !TextUtils.isEmpty(video_url) ? com.sigmob.sdk.base.common.d.e().a(video_url) : video_url;
    }

    public String getRequestId() {
        return this.j;
    }

    public RvAdSetting getRvAdSetting() {
        if (this.t != null) {
            return this.t.rv_setting;
        }
        return null;
    }

    public v getSessionManager() {
        return this.w;
    }

    public SingleNativeAdSetting getSingleNativeSetting() {
        AdSetting adSetting = getAdSetting();
        if (adSetting != null) {
            return adSetting.single_native_setting;
        }
        return null;
    }

    public int getSkipSeconds() {
        RvAdSetting rvAdSetting = getRvAdSetting();
        if (rvAdSetting != null) {
            return rvAdSetting.skip_seconds.intValue();
        }
        return -1;
    }

    public SlotAdSetting getSlotAdSetting() {
        return this.t;
    }

    public SplashAdSetting getSplashAdSetting() {
        SlotAdSetting slotAdSetting = getSlotAdSetting();
        if (slotAdSetting != null) {
            return slotAdSetting.splash_setting;
        }
        return null;
    }

    public String getSplashFilePath() {
        StringBuilder sb;
        String str;
        if (j.CreativeTypeSplashVideo.a() == getMaterial().creative_type.intValue()) {
            sb = new StringBuilder();
            sb.append(d.b());
            sb.append(File.separator);
            str = getMaterial().video_url;
        } else {
            sb = new StringBuilder();
            sb.append(d.b());
            sb.append(File.separator);
            str = getMaterial().image_src;
        }
        sb.append(k.a(str));
        return sb.toString();
    }

    public String getSplashURL() {
        return j.CreativeTypeSplashVideo.a() == getMaterial().creative_type.intValue() ? getMaterial().video_url : getMaterial().image_src;
    }

    public String getTitle() {
        MaterialMeta material = getMaterial();
        if (material != null) {
            return material.title;
        }
        return null;
    }

    public int getTrackingRetryNum() {
        if (this.t != null) {
            return this.t.retry_count.intValue();
        }
        return 0;
    }

    public String getUuid() {
        if (this.s == null) {
            this.s = UUID.randomUUID().toString();
        }
        return this.s;
    }

    public String getVid() {
        if (getAd() != null) {
            return getAd().vid;
        }
        return null;
    }

    public VideoStatusCommon getVideoCommon() {
        if (this.q == null) {
            this.q = new VideoStatusCommon();
        }
        return this.q;
    }

    public String getVideoPath() {
        String strA = a(getVideo_url());
        if (!TextUtils.isEmpty(strA)) {
            return strA;
        }
        return d.a() + String.format("/%s.mp4", getVideo_md5());
    }

    public File getVideoProxyFile() {
        String strA = a(getVideo_url());
        if (!TextUtils.isEmpty(strA)) {
            return new File(strA);
        }
        String video_url = getVideo_url();
        if (TextUtils.isEmpty(video_url)) {
            return null;
        }
        return com.sigmob.sdk.base.common.d.e().c(video_url);
    }

    public String getVideoThumbUrl() {
        if (this.n != null) {
            return this.n.d;
        }
        return null;
    }

    public String getVideoTmpPath() {
        return d.a() + String.format("/%s.mp4.tmp", getVideo_md5());
    }

    public String getVideo_OriginMD5() {
        return this.g;
    }

    public String getVideo_md5() {
        return !TextUtils.isEmpty(this.g) ? this.g : k.a(getVideo_url());
    }

    public String getVideo_url() {
        if (this.d == null || this.d.materials.size() <= 0) {
            return null;
        }
        if (this.d.ad_type.intValue() != 5) {
            return this.d.materials.get(0).video_url;
        }
        SigVideo nativeVideo = getNativeVideo();
        if (nativeVideo != null) {
            return nativeVideo.c;
        }
        return null;
    }

    public AdPrivacy getadPrivacy() {
        MaterialMeta material = getMaterial();
        if (material != null) {
            return material.ad_privacy;
        }
        return null;
    }

    public String getadslot_id() {
        return this.a;
    }

    public void insertToDB(final c.a aVar) {
        c.a.b().a().submit(new Runnable() { // from class: com.sigmob.sdk.base.models.BaseAdUnit.1
            @Override // java.lang.Runnable
            public void run() {
                b.C0018b.a aVar2 = new b.C0018b.a();
                aVar2.a("ads");
                HashMap map = new HashMap();
                map.put("adslot_id", BaseAdUnit.this.a);
                map.put("camp_id", BaseAdUnit.this.b);
                map.put("crid", BaseAdUnit.this.c);
                map.put("ad", BaseAdUnit.this.d.encode());
                map.put("ad_type", Integer.valueOf(BaseAdUnit.this.e));
                map.put("create_time", Long.valueOf(BaseAdUnit.this.f));
                map.put("video_md5", BaseAdUnit.this.getVideo_md5());
                map.put("endcard_md5", BaseAdUnit.this.getEndcard_md5());
                map.put(Consts.INAPP_REQUEST_ID, BaseAdUnit.this.j);
                map.put("ad_source_channel", BaseAdUnit.this.i);
                map.put("load_id", BaseAdUnit.this.k);
                aVar2.a(map);
                com.sigmob.sdk.common.a.c.a().a(com.sigmob.sdk.common.a.c.a().getWritableDatabase(), aVar2.a(), new c.a() { // from class: com.sigmob.sdk.base.models.BaseAdUnit.1.1
                    @Override // com.sigmob.sdk.common.a.c.a
                    public void onFailed(Throwable th) {
                        if (aVar != null) {
                            aVar.onFailed(th);
                        }
                        SigmobLog.e(th.getMessage());
                    }

                    @Override // com.sigmob.sdk.common.a.c.a
                    public void onSuccess() {
                        SigmobLog.d(BaseAdUnit.this.getCrid() + "insert success!");
                        if (aVar != null) {
                            aVar.onSuccess();
                        }
                    }
                });
            }
        });
    }

    public boolean isEndCardIndexExist() {
        if (TextUtils.isEmpty(getEndcard_url())) {
            return true;
        }
        if (getCreativeType() == j.CreativeTypeVideo_Tar.a() || getCreativeType() == j.CreativeTypeVideo_Tar_Companion.a()) {
            return new File(getEndCardIndexPath()).exists();
        }
        return true;
    }

    public boolean isNativeAdH5() {
        return true;
    }

    public boolean isSkipSigmobBrowser() {
        return (this.e == 2 || this.e == 5) ? false : true;
    }

    public boolean isUse_floating_btn() {
        SplashAdSetting splashAdSetting = getSplashAdSetting();
        if (splashAdSetting != null) {
            return splashAdSetting.use_floating_btn.booleanValue();
        }
        return false;
    }

    public boolean isVideoExist() {
        if (TextUtils.isEmpty(getVideo_url())) {
            return true;
        }
        String videoPath = getVideoPath();
        boolean zExists = new File(videoPath).exists();
        SigmobLog.d("isVideoExist path :" + videoPath + " isExist: " + zExists);
        return zExists;
    }

    public String resourcePath() {
        return (TextUtils.isEmpty(getEndcard_url()) || !(getCreativeType() == j.CreativeTypeVideo_Tar.a() || getCreativeType() == j.CreativeTypeVideo_Tar_Companion.a())) ? !TextUtils.isEmpty(getHtmlData()) ? getHtmlData() : getHtmlUrl() : getEndCardIndexPath();
    }

    public void setAd(Ad ad) {
        this.d = ad;
    }

    public void setAdSize(int i, int i2) {
        this.x = i;
        this.x = i2;
        getMacroCommon().addMarcoKey(SigMacroCommon._WIDTH_, String.valueOf(i));
        getMacroCommon().addMarcoKey(SigMacroCommon._HEIGHT_, String.valueOf(i2));
    }

    public void setAd_source_channel(String str) {
        this.i = str;
    }

    public void setAd_type(int i) {
        this.e = i;
    }

    public void setAdslot_id(String str) {
        this.a = str;
    }

    public void setBid_token(String str) {
        this.l = str;
    }

    public void setCamp_id(String str) {
        this.b = str;
    }

    public void setCreate_time(long j) {
        this.f = j;
    }

    public void setCrid(String str) {
        this.c = str;
    }

    public void setEndcard_md5(String str) {
        this.h = str;
    }

    public void setLoad_id(String str) {
        this.k = str;
    }

    public void setMacroCommon(SigMacroCommon sigMacroCommon) {
        this.p = sigMacroCommon;
    }

    public void setRequest_id(String str) {
        this.j = str;
    }

    public void setSessionManager(v vVar) {
        this.w = vVar;
    }

    public void setVideo_md5(String str) {
        this.g = str;
    }

    public void setadslot_id(String str) {
        this.a = str;
    }

    public void updateRealAdPercent(double d) {
        this.v = d;
    }
}
