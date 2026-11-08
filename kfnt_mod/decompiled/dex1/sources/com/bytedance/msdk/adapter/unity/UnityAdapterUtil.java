package com.bytedance.msdk.adapter.unity;

import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdErrorUtil;
import com.bytedance.sdk.account.platform.api.IWeiboService;
import com.unity3d.ads.UnityAds;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class UnityAdapterUtil {

    /* renamed from: com.bytedance.msdk.adapter.unity.UnityAdapterUtil$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f4217a = new int[UnityAds.UnityAdsError.values().length];

        static {
            try {
                f4217a[UnityAds.UnityAdsError.NOT_INITIALIZED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4217a[UnityAds.UnityAdsError.INITIALIZE_FAILED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f4217a[UnityAds.UnityAdsError.INIT_SANITY_CHECK_FAIL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f4217a[UnityAds.UnityAdsError.INVALID_ARGUMENT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f4217a[UnityAds.UnityAdsError.AD_BLOCKER_DETECTED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f4217a[UnityAds.UnityAdsError.FILE_IO_ERROR.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f4217a[UnityAds.UnityAdsError.DEVICE_ID_ERROR.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f4217a[UnityAds.UnityAdsError.SHOW_ERROR.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f4217a[UnityAds.UnityAdsError.INTERNAL_ERROR.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public static AdError errorMsg(UnityAds.UnityAdsError unityAdsError, String str) {
        switch (AnonymousClass1.f4217a[unityAdsError.ordinal()]) {
            case 1:
                return AdErrorUtil.obtainAdError(AdError.ERROR_CODE_THIRD_SDK_NOT_INIT, AdError.messageFormat(AdError.AD_THIRD_SDK_NOT_INIT_MSG, "unity", new String[0]) + IWeiboService.Scope.EMPTY_SCOPE + str);
            case 2:
            case 3:
                return AdErrorUtil.obtainAdError(AdError.ERROR_CODE_THIRD_SDK_INIT_FAIL, AdError.messageFormat(AdError.AD_THIRD_SDK_INIT_FAIL_MSG, "unity", new String[0]) + IWeiboService.Scope.EMPTY_SCOPE + str);
            case 4:
                return AdErrorUtil.obtainAdError(AdError.ERROR_CODE_THIRD_SDK_PARARM_ERROR, AdError.messageFormat(AdError.AD_THIRD_SDK_PARARM_ERROR_MSG, "unity", new String[0]) + IWeiboService.Scope.EMPTY_SCOPE + str);
            case 5:
                return AdErrorUtil.obtainAdError(AdError.ERROR_CODE_THIRD_SDK_AD_BLOCKER_DETECTED, AdError.messageFormat(AdError.AD_THIRD_SDK_AD_BLOCKER_DETECTED_MSG, "unity", new String[0]) + IWeiboService.Scope.EMPTY_SCOPE + str);
            case 6:
                return AdErrorUtil.obtainAdError(AdError.ERROR_CODE_THIRD_SDK_FILE_IO_ERROR, AdError.messageFormat(AdError.AD_THIRD_SDK_FILE_IO_ERROR_MSG, "unity", new String[0]) + IWeiboService.Scope.EMPTY_SCOPE + str);
            case 7:
                return AdErrorUtil.obtainAdError(AdError.ERROR_CODE_THIRD_SDK_DEVICE_ID_ERROR, AdError.messageFormat(AdError.AD_THIRD_SDK_DEVICE_ID_ERROR_MSG, "unity", new String[0]) + IWeiboService.Scope.EMPTY_SCOPE + str);
            case 8:
                return AdErrorUtil.obtainAdError(AdError.ERROR_CODE_THIRD_SDK_AD_SHOW_ERROR, AdError.messageFormat(AdError.AD_THIRD_SDK_AD_SHOW_ERROR_MSG, "unity", new String[0]) + IWeiboService.Scope.EMPTY_SCOPE + str);
            case 9:
                return AdErrorUtil.obtainAdError(AdError.ERROR_CODE_THIRD_SDK_INTERNAL_ERROR, AdError.messageFormat(AdError.AD_THIRD_SDK_INTERNAL_ERROR_MSG, "unity", new String[0]) + IWeiboService.Scope.EMPTY_SCOPE + str);
            default:
                return AdErrorUtil.obtainAdError(0, AdError.AD_UNKNOWN_ERROR_MSG);
        }
    }
}
