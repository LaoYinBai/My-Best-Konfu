package com.ss.union.game.sdk.core.util;

import com.ss.union.game.sdk.common.util.HMacMD5;
import com.ss.union.game.sdk.common.util.logger.LogUtils;
import com.ss.union.game.sdk.core.base.account.LGAccountDataUtil;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class CheckSignUtils {
    public static final String KEY_HTTP_HEADER_SIGN = "Data-Sign";
    private static final String a = "8szsh73d9dym";

    public static boolean checkSign(String str, String str2) {
        String strHmacMD5;
        try {
            strHmacMD5 = HMacMD5.hmacMD5((LGAccountDataUtil.getLoginToken() + a).getBytes(), str.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            strHmacMD5 = null;
        }
        if (strHmacMD5 == null) {
            LogUtils.log("generateSign is null...");
            return false;
        }
        return strHmacMD5.equals(str2);
    }
}
