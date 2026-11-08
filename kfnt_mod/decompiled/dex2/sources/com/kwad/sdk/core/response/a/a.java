package com.kwad.sdk.core.response.a;

import android.content.Context;
import android.net.Uri;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdProductInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.utils.af;
import com.kwad.sdk.utils.at;
import com.kwad.sdk.utils.z;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    public static String A(@Nullable AdInfo adInfo) {
        if (adInfo == null) {
            return "立即下载";
        }
        String str = adInfo.adBaseInfo.adActionDescription;
        if (TextUtils.isEmpty(str)) {
            return B(adInfo) ? "立即下载" : "查看详情";
        }
        return str;
    }

    public static boolean B(@NonNull AdInfo adInfo) {
        return E(adInfo) == 1;
    }

    public static boolean C(@NonNull AdInfo adInfo) {
        return adInfo.adBaseInfo.adAttributeType == 1;
    }

    public static boolean D(@NonNull AdInfo adInfo) {
        return adInfo.downloadSafeInfo.complianceInfo != null && B(adInfo) && adInfo.downloadSafeInfo.complianceInfo.titleBarTextSwitch == 1;
    }

    public static int E(@NonNull AdInfo adInfo) {
        switch (adInfo.adBaseInfo.adOperationType) {
            case 1:
                return 1;
            case 2:
                return 2;
            default:
                return 0;
        }
    }

    public static int F(@NonNull AdInfo adInfo) {
        return adInfo.adBaseInfo.ecpm;
    }

    public static String G(@NonNull AdInfo adInfo) {
        return z.a(adInfo.adConversionInfo.h5Url);
    }

    public static int H(@NonNull AdInfo adInfo) {
        return adInfo.adConversionInfo.h5Type;
    }

    @NonNull
    public static AdInfo.AdMaterialInfo.MaterialFeature I(@NonNull AdInfo adInfo) {
        for (AdInfo.AdMaterialInfo.MaterialFeature materialFeature : adInfo.adMaterialInfo.materialFeatureList) {
            if (materialFeature != null && materialFeature.featureType == 2 && !TextUtils.isEmpty(materialFeature.materialUrl)) {
                return materialFeature;
            }
        }
        com.kwad.sdk.core.d.a.e("AdInfoHelper", "getImageMaterialFeature in null");
        return new AdInfo.AdMaterialInfo.MaterialFeature();
    }

    @NonNull
    public static AdInfo.AdMaterialInfo.MaterialFeature J(@NonNull AdInfo adInfo) {
        for (AdInfo.AdMaterialInfo.MaterialFeature materialFeature : adInfo.adMaterialInfo.materialFeatureList) {
            if (materialFeature != null && materialFeature.featureType == 1 && !TextUtils.isEmpty(materialFeature.materialUrl)) {
                return materialFeature;
            }
        }
        com.kwad.sdk.core.d.a.e("AdInfoHelper", "getVideoMaterialFeature in null");
        return new AdInfo.AdMaterialInfo.MaterialFeature();
    }

    public static boolean K(AdInfo adInfo) {
        AdInfo.AdMaterialInfo.MaterialFeature materialFeatureJ = P(adInfo) ? J(adInfo) : I(adInfo);
        return materialFeatureJ.height > materialFeatureJ.width;
    }

    public static float L(AdInfo adInfo) {
        AdInfo.AdMaterialInfo.MaterialFeature materialFeatureJ = P(adInfo) ? J(adInfo) : I(adInfo);
        if (materialFeatureJ == null || materialFeatureJ.width == 0) {
            return -1.0f;
        }
        com.kwad.sdk.core.d.a.a("AdInfoHelper", "getMaterialRatio: height: " + materialFeatureJ.height + ", width: " + materialFeatureJ.width);
        return materialFeatureJ.height / materialFeatureJ.width;
    }

    public static boolean M(AdInfo adInfo) {
        AdInfo.AdMaterialInfo.MaterialFeature materialFeatureJ = J(adInfo);
        return materialFeatureJ.height > materialFeatureJ.width;
    }

    public static String N(AdInfo adInfo) {
        return adInfo.adPreloadInfo.preloadId;
    }

    @NonNull
    public static List<String> O(@NonNull AdInfo adInfo) {
        ArrayList arrayList = new ArrayList();
        switch (R(adInfo)) {
            case 2:
            case 3:
                for (AdInfo.AdMaterialInfo.MaterialFeature materialFeature : adInfo.adMaterialInfo.materialFeatureList) {
                    if (materialFeature.featureType == 2 && !TextUtils.isEmpty(materialFeature.materialUrl)) {
                        arrayList.add(materialFeature.materialUrl);
                    }
                }
                break;
        }
        return arrayList;
    }

    public static boolean P(@NonNull AdInfo adInfo) {
        return R(adInfo) == 1;
    }

    public static boolean Q(@NonNull AdInfo adInfo) {
        return I(adInfo).featureType == 2;
    }

    public static int R(AdInfo adInfo) {
        switch (adInfo.adMaterialInfo.materialType) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            default:
                return 0;
        }
    }

    @Nullable
    public static List<Integer> S(@NonNull AdInfo adInfo) throws NumberFormatException {
        String str = adInfo.adBaseInfo.videoPlayedNS;
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str)) {
            try {
                for (String str2 : str.split(",")) {
                    int i = Integer.parseInt(str2);
                    if (i > 0) {
                        arrayList.add(Integer.valueOf(i));
                    }
                }
            } catch (Exception e) {
                com.kwad.sdk.core.d.a.a(e);
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.add(3);
        }
        return arrayList;
    }

    public static boolean T(@NonNull AdInfo adInfo) {
        if (adInfo.adBaseInfo.mABParams.videoBlackAreaNewStyle == 0) {
            return false;
        }
        return af.a() ? !g(adInfo) : g(adInfo);
    }

    public static boolean U(@NonNull AdInfo adInfo) {
        return adInfo.adStyleInfo2.playDetailInfo.detailWebCardInfo.style == 1;
    }

    public static boolean V(@NonNull AdInfo adInfo) {
        return ((com.kwad.sdk.core.config.b.aa() && B(adInfo)) || adInfo.adBaseInfo.mABParams.showVideoAtH5 == 0 || at.a(G(adInfo))) ? false : true;
    }

    @NonNull
    public static int[] W(@NonNull AdInfo adInfo) {
        String[] strArrSplit;
        int[] iArr = {3, 3, 3};
        String str = adInfo.adBaseInfo.mABParams.drawActionBarTimes;
        if (TextUtils.isEmpty(str)) {
            return iArr;
        }
        try {
            strArrSplit = str.split(",");
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.a(e);
        }
        if (strArrSplit.length < 3) {
            return iArr;
        }
        iArr[0] = Integer.parseInt(strArrSplit[0]);
        iArr[1] = Integer.parseInt(strArrSplit[1]);
        iArr[2] = Integer.parseInt(strArrSplit[2]);
        return iArr;
    }

    public static int X(@NonNull AdInfo adInfo) {
        int[] iArrW = W(adInfo);
        if (iArrW.length >= 3 && iArrW[0] > 0) {
            return iArrW[0];
        }
        return 3;
    }

    public static int Y(@NonNull AdInfo adInfo) {
        int[] iArrW = W(adInfo);
        if (iArrW.length < 3) {
            return 6;
        }
        return (iArrW[1] > 0 ? iArrW[1] : 3) + X(adInfo);
    }

    public static int Z(@NonNull AdInfo adInfo) {
        int[] iArrW = W(adInfo);
        if (iArrW.length < 3) {
            return 9;
        }
        return (iArrW[2] > 0 ? iArrW[2] : 3) + Y(adInfo);
    }

    @Nullable
    public static SpannableString a(AdInfo adInfo, @ColorInt int i) {
        String str;
        int iIndexOf;
        String strAB = aB(adInfo);
        if (strAB == null || (iIndexOf = (str = String.format("已有%s粉丝关注了TA", strAB)).indexOf(strAB)) < 0) {
            return null;
        }
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new ForegroundColorSpan(i), iIndexOf, strAB.length() + iIndexOf, 18);
        return spannableString;
    }

    public static String a() {
        return "继续下载";
    }

    @Deprecated
    public static String a(int i) {
        return a(i, "下载中  %s%%");
    }

    public static String a(int i, String str) {
        if (str == null) {
            str = "下载中  %s%%";
        }
        return String.format(str, Integer.valueOf(i));
    }

    public static String a(Context context, @NonNull AdInfo adInfo) {
        File fileF = com.kwad.sdk.core.config.b.f(context);
        if (fileF.exists()) {
            return Uri.fromFile(fileF).toString();
        }
        String strD = com.kwad.sdk.core.config.b.d();
        return !at.a(strD) ? strD : adInfo.downloadSafeInfo.windowPopUrl;
    }

    public static String a(@NonNull AdInfo adInfo) {
        return J(adInfo).materialUrl;
    }

    public static String a(AdTemplate adTemplate) {
        if (adTemplate != null && adTemplate.mAdScene != null && adTemplate.mAdScene.getAdStyle() == 2) {
            return "安装获取奖励";
        }
        if (adTemplate == null) {
            return "立即安装";
        }
        AdInfo adInfoJ = d.j(adTemplate);
        return TextUtils.isEmpty(adInfoJ.adBaseInfo.installAppLabel) ? "立即安装" : adInfoJ.adBaseInfo.installAppLabel;
    }

    public static boolean aA(@NonNull AdInfo adInfo) {
        return adInfo.advertiserInfo.followed;
    }

    @Nullable
    public static String aB(@NonNull AdInfo adInfo) {
        int i = adInfo.advertiserInfo.fansCount;
        if (i >= 200 && i < 10000) {
            return String.valueOf(i);
        }
        if (i < 10000) {
            return null;
        }
        double d = i / 10000.0d;
        return new DecimalFormat("0.0").format(d) + "w";
    }

    public static String aC(@NonNull AdInfo adInfo) {
        return adInfo.advertiserInfo.brief;
    }

    public static String aD(@NonNull AdInfo adInfo) {
        return adInfo.advertiserInfo.userName;
    }

    public static String aE(@NonNull AdInfo adInfo) {
        return adInfo.advertiserInfo.portraitUrl;
    }

    public static boolean aF(@NonNull AdInfo adInfo) {
        return adInfo.ocpcActionType == 395 && adInfo.adBaseInfo.campaignType == 13;
    }

    public static boolean aG(AdInfo adInfo) {
        return aH(adInfo) || aI(adInfo);
    }

    public static boolean aH(AdInfo adInfo) {
        return az(adInfo) && com.kwad.sdk.core.config.b.aH() != 0;
    }

    public static boolean aI(AdInfo adInfo) {
        return aF(adInfo) && com.kwad.sdk.core.config.b.aI();
    }

    public static String aJ(@NonNull AdInfo adInfo) {
        return z.a(adInfo.adConversionInfo.deeplinkUrl);
    }

    public static AdProductInfo aK(AdInfo adInfo) {
        return adInfo.adProductInfo;
    }

    public static String aL(@NonNull AdInfo adInfo) {
        return adInfo.adConversionInfo.marketUrl;
    }

    @NonNull
    private static AdInfo.AdMaterialInfo.MaterialFeature aM(@NonNull AdInfo adInfo) {
        List<AdInfo.AdMaterialInfo.MaterialFeature> list = adInfo.adMaterialInfo.materialFeatureList;
        AdInfo.AdMaterialInfo.MaterialFeature materialFeature = list.size() > 0 ? list.get(0) : null;
        return materialFeature == null ? new AdInfo.AdMaterialInfo.MaterialFeature() : materialFeature;
    }

    private static boolean aN(AdInfo adInfo) {
        if (adInfo == null || adInfo.adConversionInfo == null || adInfo.adConversionInfo.playableStyleInfo == null) {
            return false;
        }
        int i = adInfo.adConversionInfo.playableStyleInfo.playableOrientation;
        return i == 0 || i == 2;
    }

    private static boolean aO(AdInfo adInfo) {
        if (adInfo == null || adInfo.adConversionInfo == null || adInfo.adConversionInfo.playableStyleInfo == null) {
            return false;
        }
        int i = adInfo.adConversionInfo.playableStyleInfo.playableOrientation;
        return i == 0 || i == 1;
    }

    @NonNull
    public static com.kwad.sdk.core.response.model.c aa(@NonNull AdInfo adInfo) {
        String strH;
        int iJ;
        int iK;
        boolean z;
        String strC = c(adInfo);
        int iE = e(adInfo);
        int iF = f(adInfo);
        if (at.a(strC) || at.b(strC) || iE == 0 || iF == 0) {
            strH = h(adInfo);
            iJ = j(adInfo);
            iK = k(adInfo);
            z = true;
        } else {
            strH = strC;
            iJ = iE;
            iK = iF;
            z = false;
        }
        com.kwad.sdk.core.d.a.a("AdInfoHelper", "frameUrl=" + strH + " useCover=" + z + " isAd=true");
        return new com.kwad.sdk.core.response.model.c(strH, iJ, iK, true, z);
    }

    public static String ab(AdInfo adInfo) {
        String str = adInfo.downloadSafeInfo.webPageTipbarText;
        return !TextUtils.isEmpty(str) ? str : "您访问的网站由第三方提供";
    }

    public static AdInfo.DownloadSafeInfo ac(AdInfo adInfo) {
        return adInfo.downloadSafeInfo;
    }

    public static boolean ad(AdInfo adInfo) {
        return adInfo.downloadSafeInfo.secWindowPopSwitch;
    }

    public static boolean ae(AdInfo adInfo) {
        return adInfo.downloadSafeInfo.secWindowPopNoWifiSwitch;
    }

    public static boolean af(AdInfo adInfo) {
        return adInfo.downloadSafeInfo.webPageTipbarSwitch;
    }

    public static int ag(AdInfo adInfo) {
        if (adInfo == null) {
            return 0;
        }
        int i = adInfo.status;
        if (i == 0) {
            return 1;
        }
        if (1 == i || 2 == i || 3 == i || 4 == i) {
            return 2;
        }
        return 8 == i ? 3 : 0;
    }

    @Nullable
    public static String ah(AdInfo adInfo) {
        if (adInfo != null) {
            return adInfo.adBaseInfo.corporationName;
        }
        return null;
    }

    @Nullable
    public static String ai(AdInfo adInfo) {
        if (adInfo != null) {
            return adInfo.downloadSafeInfo.permissionInfo;
        }
        return null;
    }

    @Nullable
    public static String aj(AdInfo adInfo) {
        if (adInfo != null) {
            return adInfo.downloadSafeInfo.appPermissionInfoUrl;
        }
        return null;
    }

    @Nullable
    public static String ak(AdInfo adInfo) {
        if (adInfo != null) {
            return adInfo.downloadSafeInfo.appPrivacyUrl;
        }
        return null;
    }

    @Nullable
    public static String al(AdInfo adInfo) {
        if (adInfo != null) {
            return adInfo.adBaseInfo.appVersion;
        }
        return null;
    }

    public static long am(AdInfo adInfo) {
        if (adInfo != null) {
            return adInfo.adBaseInfo.packageSize;
        }
        return 0L;
    }

    public static boolean an(AdInfo adInfo) {
        if (adInfo == null || adInfo.adConversionInfo == null) {
            return false;
        }
        return ao(adInfo) && (adInfo.adConversionInfo.playableUrl != null && adInfo.adConversionInfo.playableStyleInfo != null && !TextUtils.isEmpty(adInfo.adConversionInfo.playableUrl.trim()));
    }

    public static boolean ao(AdInfo adInfo) {
        return af.a() ? aO(adInfo) : aN(adInfo);
    }

    @Nullable
    public static String ap(AdInfo adInfo) {
        if (adInfo == null || adInfo.adConversionInfo == null) {
            return null;
        }
        return adInfo.adConversionInfo.playableUrl;
    }

    @Nullable
    public static String aq(AdInfo adInfo) {
        if (adInfo == null || adInfo.adConversionInfo == null) {
            return null;
        }
        return adInfo.adConversionInfo.callbackUrl;
    }

    @Nullable
    public static String ar(AdInfo adInfo) {
        if (adInfo == null || adInfo.adConversionInfo == null) {
            return null;
        }
        return adInfo.adConversionInfo.callbackUrlInfo;
    }

    public static boolean as(AdInfo adInfo) {
        return (TextUtils.isEmpty(G(adInfo)) || B(adInfo) || adInfo.adStyleInfo.adBrowseInfo.enableAdBrowse != 1) ? false : true;
    }

    @NonNull
    public static String at(@NonNull AdInfo adInfo) {
        String strT = B(adInfo) ? t(adInfo) : u(adInfo);
        if (TextUtils.isEmpty(strT)) {
            strT = adInfo.advertiserInfo.rawUserName;
        }
        return TextUtils.isEmpty(strT) ? "可爱的广告君" : strT;
    }

    public static String au(@NonNull AdInfo adInfo) {
        String str = adInfo.adBaseInfo.appIconUrl;
        return TextUtils.isEmpty(str) ? adInfo.advertiserInfo.portraitUrl : str;
    }

    @NonNull
    public static String av(AdInfo adInfo) {
        return (adInfo == null || adInfo.adSplashInfo == null || adInfo.adSplashInfo.skipTips == null || TextUtils.isEmpty(adInfo.adSplashInfo.skipTips)) ? "跳过" : adInfo.adSplashInfo.skipTips;
    }

    public static boolean aw(AdInfo adInfo) {
        return (adInfo == null || adInfo.adSplashInfo == null || adInfo.adSplashInfo.countdownShow != 1) ? false : true;
    }

    public static boolean ax(AdInfo adInfo) {
        if (adInfo == null || adInfo.downloadSafeInfo == null) {
            return false;
        }
        return adInfo.downloadSafeInfo.downloadPauseEnable;
    }

    public static String ay(@NonNull AdInfo adInfo) {
        return adInfo.adBaseInfo.sdkExtraData;
    }

    public static boolean az(@NonNull AdInfo adInfo) {
        return adInfo.ocpcActionType == 72 && adInfo.adBaseInfo.campaignType == 13;
    }

    public static int b(@NonNull AdInfo adInfo) {
        return J(adInfo).videoDuration;
    }

    public static String b(int i) {
        return "继续下载 " + i + "%";
    }

    public static String c(@NonNull AdInfo adInfo) {
        switch (R(adInfo)) {
            case 1:
                break;
            case 2:
            case 3:
                for (AdInfo.AdMaterialInfo.MaterialFeature materialFeature : adInfo.adMaterialInfo.materialFeatureList) {
                    if (materialFeature.featureType == 2 && !TextUtils.isEmpty(materialFeature.firstFrame)) {
                        return materialFeature.firstFrame;
                    }
                }
                break;
            default:
                return "";
        }
        for (AdInfo.AdMaterialInfo.MaterialFeature materialFeature2 : adInfo.adMaterialInfo.materialFeatureList) {
            if (materialFeature2.featureType == 1 && !TextUtils.isEmpty(materialFeature2.firstFrame)) {
                return materialFeature2.firstFrame;
            }
        }
        return "";
    }

    public static boolean d(@NonNull AdInfo adInfo) {
        return adInfo.adConversionInfo.supportThirdDownload == 1;
    }

    public static int e(@NonNull AdInfo adInfo) {
        return J(adInfo).videoWidth;
    }

    public static int f(@NonNull AdInfo adInfo) {
        return J(adInfo).videoHeight;
    }

    public static boolean g(@NonNull AdInfo adInfo) {
        AdInfo.AdMaterialInfo.MaterialFeature materialFeatureJ = J(adInfo);
        return materialFeatureJ.videoWidth <= materialFeatureJ.videoHeight;
    }

    public static String h(@NonNull AdInfo adInfo) {
        switch (R(adInfo)) {
            case 1:
                break;
            case 2:
            case 3:
                for (AdInfo.AdMaterialInfo.MaterialFeature materialFeature : adInfo.adMaterialInfo.materialFeatureList) {
                    if (materialFeature.featureType == 2) {
                        if (!TextUtils.isEmpty(materialFeature.materialUrl)) {
                            return materialFeature.materialUrl;
                        }
                        if (!TextUtils.isEmpty(materialFeature.coverUrl)) {
                            return materialFeature.coverUrl;
                        }
                    }
                }
                break;
            default:
                return "";
        }
        for (AdInfo.AdMaterialInfo.MaterialFeature materialFeature2 : adInfo.adMaterialInfo.materialFeatureList) {
            if (materialFeature2.featureType == 1 && !TextUtils.isEmpty(materialFeature2.coverUrl)) {
                return materialFeature2.coverUrl;
            }
        }
        return "";
    }

    public static String i(@NonNull AdInfo adInfo) {
        switch (R(adInfo)) {
            case 1:
                break;
            case 2:
            case 3:
                for (AdInfo.AdMaterialInfo.MaterialFeature materialFeature : adInfo.adMaterialInfo.materialFeatureList) {
                    if (materialFeature.featureType == 2 && !TextUtils.isEmpty(materialFeature.blurBackgroundUrl)) {
                        return materialFeature.blurBackgroundUrl;
                    }
                }
                break;
            default:
                return "";
        }
        return aM(adInfo).blurBackgroundUrl;
    }

    public static int j(@NonNull AdInfo adInfo) {
        return J(adInfo).width;
    }

    public static int k(@NonNull AdInfo adInfo) {
        return J(adInfo).height;
    }

    public static long l(@NonNull AdInfo adInfo) {
        return J(adInfo).photoId;
    }

    public static String m(@Nullable AdInfo adInfo) {
        return (adInfo == null || TextUtils.isEmpty(adInfo.adBaseInfo.openAppLabel)) ? "立即打开" : adInfo.adBaseInfo.openAppLabel;
    }

    public static long n(@NonNull AdInfo adInfo) {
        return adInfo.adRewardInfo.rewardTime;
    }

    public static long o(@NonNull AdInfo adInfo) {
        return adInfo.adRewardInfo.skipShowTime * 1000;
    }

    public static long p(@NonNull AdInfo adInfo) {
        return adInfo.adRewardInfo.rewardTime * 1000;
    }

    public static boolean q(@NonNull AdInfo adInfo) {
        return !B(adInfo) && r(adInfo);
    }

    public static boolean r(@NonNull AdInfo adInfo) {
        return adInfo.adRewardInfo.showLandingPage == 1;
    }

    public static String s(@NonNull AdInfo adInfo) {
        return adInfo.adBaseInfo.adDescription;
    }

    public static String t(@NonNull AdInfo adInfo) {
        return adInfo.adBaseInfo.appName;
    }

    public static String u(@NonNull AdInfo adInfo) {
        return adInfo.adBaseInfo.productName;
    }

    public static String v(AdInfo adInfo) {
        if (adInfo == null || adInfo.adBaseInfo == null) {
            return null;
        }
        return adInfo.adBaseInfo.appPackageName;
    }

    public static String w(@NonNull AdInfo adInfo) {
        return adInfo.adBaseInfo.appDownloadCountDesc;
    }

    public static float x(@NonNull AdInfo adInfo) {
        if (adInfo.adBaseInfo.appScore <= 0) {
            return 0.0f;
        }
        return adInfo.adBaseInfo.appScore / 10.0f;
    }

    public static float y(@NonNull AdInfo adInfo) {
        float f = adInfo.adBaseInfo.appScore;
        if (f < 30.0f) {
            return 3.0f;
        }
        if (f < 35.0f) {
            return 3.5f;
        }
        if (f < 40.0f) {
            return 4.0f;
        }
        return f < 45.0f ? 4.5f : 5.0f;
    }

    public static String z(AdInfo adInfo) {
        return at.a(adInfo.adBaseInfo.adSourceDescription) ? "广告" : adInfo.adBaseInfo.adSourceDescription;
    }
}
