package com.ss.union.game.sdk.core.applog.header;

import com.bytedance.applog.AppLog;
import com.bytedance.applog.game.OhayooGameHelper;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AppLogHeaderHelper {
    public static void setCommonHeader(String str, String str2) {
        OhayooGameHelper.setOhayooCustomHeader(str, str2);
    }

    public static void removeCommonHeader(String str) {
        AppLog.removeHeaderInfo(str);
    }

    public static void setChannel(String str) {
        OhayooGameHelper.setOhayooCustomHeader("ohayoo_packagechannel", str);
    }

    public static void setZoneID(String str) {
        OhayooGameHelper.setOhayooCustomHeader("ohayoo_zoneid", str);
    }

    public static void setServerID(String str) {
        OhayooGameHelper.setOhayooCustomHeader("ohayoo_serverid", str);
    }

    public static void setSdkOpenId(String str) {
        OhayooGameHelper.setOhayooCustomHeader("ohayoo_sdk_open_id", str);
    }

    public static void setUserType(String str) {
        OhayooGameHelper.setOhayooCustomHeader("ohayoo_usertype", str);
    }

    public static void setRoleID(String str) {
        OhayooGameHelper.setOhayooCustomHeader("ohayoo_roleid", str);
    }

    public static void setUserLevel(String str) {
        OhayooGameHelper.setOhayooCustomHeader("ohayoo_level", str);
    }
}
