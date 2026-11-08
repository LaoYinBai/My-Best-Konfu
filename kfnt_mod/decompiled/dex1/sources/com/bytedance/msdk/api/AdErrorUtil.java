package com.bytedance.msdk.api;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AdErrorUtil {
    public static AdError getAdmobError(int i) {
        switch (i) {
            case 0:
                return new AdError(AdError.ERROR_CODE_INTERNAL_ERROR, AdError.getMessage(AdError.ERROR_CODE_INTERNAL_ERROR), i, AdError.AD_ERROR_INTERNAL_ERROR_MSG);
            case 1:
                return new AdError(AdError.ERROR_CODE_SLOT_ID_ERROR, AdError.getMessage(AdError.ERROR_CODE_SLOT_ID_ERROR), i, AdError.getMessage(AdError.ERROR_CODE_SLOT_ID_ERROR));
            case 2:
                return new AdError(AdError.ERROR_CODE_NETWORK_ERROR, AdError.getMessage(AdError.ERROR_CODE_NETWORK_ERROR), i, AdError.getMessage(AdError.ERROR_CODE_NETWORK_ERROR));
            case 3:
                return new AdError(AdError.ERROR_CODE_NO_AD, AdError.getMessage(AdError.ERROR_CODE_NO_AD), i, AdError.getMessage(AdError.ERROR_CODE_NO_AD));
            default:
                return new AdError(0, AdError.getMessage(0), 0, AdError.getMessage(0));
        }
    }

    public static AdError getGdtError(int i, String str) {
        if (i == 3001) {
            return new AdError(-2, AdError.getMessage(-2), i, str);
        }
        if (i == 3003) {
            return new AdError(-14, AdError.getMessage(-14), i, str);
        }
        if (i == 4001) {
            return new AdError(AdError.ERROR_CODE_SLOT_ID_APP_ID_DIFFER, AdError.getMessage(AdError.ERROR_CODE_SLOT_ID_APP_ID_DIFFER), i, str);
        }
        if (i == 4003) {
            return new AdError(AdError.ERROR_CODE_ADSLOT_ERROR, AdError.getMessage(AdError.ERROR_CODE_ADSLOT_ERROR), i, str);
        }
        if (i != 5005) {
            switch (i) {
                case 5001:
                    return new AdError(AdError.ERROR_CODE_SYS_ERROR, AdError.getMessage(AdError.ERROR_CODE_SYS_ERROR), i, str);
                default:
                    switch (i) {
                        case 5007:
                        case 5008:
                            break;
                        case 5009:
                            break;
                        default:
                            switch (i) {
                                case 5011:
                                    return new AdError(105, AdError.getMessage(105), i, str);
                                case 5012:
                                    return new AdError(-15, AdError.getMessage(-15), i, str);
                                case 5013:
                                    break;
                                default:
                                    return new AdError(0, AdError.getMessage(0), i, str);
                            }
                    }
                case 5002:
                case 5003:
                    return new AdError(-1, AdError.getMessage(-1), i, str);
            }
        }
        return new AdError(-8, AdError.getMessage(-8), i, str);
    }

    public static AdError getSigmobError(int i, String str) {
        switch (i) {
            case 200000:
                return new AdError(AdError.ERROR_CODE_NO_AD, AdError.getMessage(AdError.ERROR_CODE_NO_AD), i, str);
            case 500402:
                return new AdError(AdError.ERROR_CODE_NO_AD, AdError.getMessage(AdError.ERROR_CODE_NO_AD), i, str);
            case 500420:
                return new AdError(AdError.ERROR_CODE_INTERNAL_ERROR, AdError.getMessage(AdError.ERROR_CODE_INTERNAL_ERROR), i, str);
            case 500422:
                return new AdError(AdError.ERROR_CODE_NO_DEVICE_INFO, AdError.getMessage(AdError.ERROR_CODE_NO_DEVICE_INFO), i, str);
            case 500424:
                return new AdError(AdError.ERROR_CODE_NO_DEVICE_INFO, AdError.getMessage(AdError.ERROR_CODE_NO_DEVICE_INFO), i, str);
            case 500428:
                return new AdError(AdError.ERROR_CODE_ADSLOT_EMPTY, AdError.getMessage(AdError.ERROR_CODE_ADSLOT_EMPTY), i, str);
            case 500430:
                return new AdError(AdError.ERROR_CODE_ADSLOT_ERROR, AdError.getMessage(AdError.ERROR_CODE_ADSLOT_ERROR), i, str);
            case 500432:
                return new AdError(AdError.ERROR_CODE_SLOT_ID_APP_ID_DIFFER, AdError.getMessage(AdError.ERROR_CODE_SLOT_ID_APP_ID_DIFFER), i, str);
            case 500433:
                return new AdError(AdError.ERROR_CODE_ADSLOT_EMPTY, AdError.getMessage(AdError.ERROR_CODE_ADSLOT_EMPTY), i, str);
            case 500435:
                return new AdError(AdError.ERROR_CODE_INTERNAL_ERROR, AdError.getMessage(AdError.ERROR_CODE_INTERNAL_ERROR), i, str);
            case 500436:
                return new AdError(-13, AdError.getMessage(-13), i, str);
            case 500473:
                return new AdError(AdError.ERROR_CODE_APP_EMPTY, AdError.getMessage(AdError.ERROR_CODE_APP_EMPTY), i, str);
            case 500479:
                return new AdError(AdError.ERROR_CODE_ADSLOT_EMPTY, AdError.getMessage(AdError.ERROR_CODE_ADSLOT_EMPTY), i, str);
            case 500482:
                return new AdError(AdError.ERROR_CODE_NO_DEVICE_INFO, AdError.getMessage(AdError.ERROR_CODE_NO_DEVICE_INFO), i, str);
            case 500701:
                return new AdError(AdError.ERROR_CODE_ADSLOT_ID_ERROR, AdError.getMessage(AdError.ERROR_CODE_ADSLOT_ID_ERROR), i, str);
            case 600100:
                return new AdError(-2, AdError.getMessage(-2), i, str);
            case 600101:
                return new AdError(AdError.ERROR_CODE_SYS_ERROR, AdError.getMessage(AdError.ERROR_CODE_SYS_ERROR), i, str);
            case 600102:
                return new AdError(AdError.ERROR_CODE_SYS_ERROR, AdError.getMessage(AdError.ERROR_CODE_SYS_ERROR), i, str);
            case 600104:
                return new AdError(AdError.ERROR_CODE_THIRD_SDK_FILE_IO_ERROR, AdError.getMessage(AdError.ERROR_CODE_THIRD_SDK_FILE_IO_ERROR), i, str);
            case 600105:
                return new AdError(AdError.ERROR_CODE_CACHE_AD_MATERIAL_FAIL, AdError.getMessage(AdError.ERROR_CODE_CACHE_AD_MATERIAL_FAIL), i, str);
            case 600900:
                return new AdError(AdError.ERROR_CODE_THIRD_SDK_NOT_INIT, AdError.getMessage(AdError.ERROR_CODE_THIRD_SDK_NOT_INIT), i, str);
            case 600901:
                return new AdError(AdError.ERROR_CODE_ADSLOT_EMPTY, AdError.getMessage(AdError.ERROR_CODE_ADSLOT_EMPTY), i, str);
            case 600906:
                return new AdError(AdError.ERROR_CODE_THIRD_SDK_INIT_FAIL, AdError.getMessage(AdError.ERROR_CODE_THIRD_SDK_INIT_FAIL), i, str);
            default:
                return new AdError(0, AdError.getMessage(0), i, str);
        }
    }

    public static AdError obtainAdError(int i, String str) {
        return new AdError(0, AdError.getMessage(0), i, str);
    }
}
