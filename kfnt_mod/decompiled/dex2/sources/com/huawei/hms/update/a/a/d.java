package com.huawei.hms.update.a.a;

import com.meizu.cloud.pushsdk.constants.PushConstants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class d {
    public static String a(int i) {
        if (i == 1000) {
            return "CHECK_OK";
        }
        if (i == 2000) {
            return "DOWNLOAD_SUCCESS";
        }
        switch (i) {
            case 1201:
                return "CHECK_FAILURE";
            case 1202:
                return "CHECK_NO_UPDATE";
            case 1203:
                return "CHECK_NO_SUPPORTED";
            default:
                switch (i) {
                    case PushConstants.BROADCAST_MESSAGE_ARRIVE /* 2100 */:
                        return "DOWNLOADING";
                    case 2101:
                        return "DOWNLOAD_CANCELED";
                    default:
                        switch (i) {
                            case PushConstants.ON_TIME_NOTIFICATION /* 2201 */:
                                return "DOWNLOAD_FAILURE";
                            case PushConstants.DELAY_NOTIFICATION /* 2202 */:
                                return "DOWNLOAD_HASH_ERROR";
                            case 2203:
                                return "DOWNLOAD_NO_SPACE";
                            case 2204:
                                return "DOWNLOAD_NO_STORAGE";
                            default:
                                return "UNKNOWN - " + i;
                        }
                }
        }
    }
}
