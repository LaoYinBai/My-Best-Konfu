package com.ss.android.socialbase.appdownloader.ah;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.ss.android.socialbase.appdownloader.util.DecryptUtils;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.umeng.commonsdk.proguard.o;
import com.xiaomi.mipush.sdk.Constants;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class VbiDevicePlan extends AbsDevicePlan {
    public VbiDevicePlan(Context context, DownloadSetting downloadSetting, String str) {
        super(context, downloadSetting, str);
    }

    @Override // com.ss.android.socialbase.appdownloader.ah.IAhDevicePlan
    public Intent getJumpIntent() {
        String strOptString = this.mSetting.optString(o.at);
        String strDecrypt = DecryptUtils.decrypt(this.mSetting.optString("bb"), strOptString);
        if (TextUtils.isEmpty(strDecrypt) || strDecrypt.split(Constants.ACCEPT_TIME_SEPARATOR_SP).length != 2) {
            return null;
        }
        String strDecrypt2 = DecryptUtils.decrypt(this.mSetting.optString("bc"), strOptString);
        if (TextUtils.isEmpty(strDecrypt2) || strDecrypt2.split(Constants.ACCEPT_TIME_SEPARATOR_SP).length != 2) {
            return null;
        }
        String[] strArrSplit = strDecrypt.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
        String[] strArrSplit2 = strDecrypt2.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
        String strDecrypt3 = DecryptUtils.decrypt(this.mSetting.optString("bd"), strOptString);
        String strDecrypt4 = DecryptUtils.decrypt(this.mSetting.optString("be"), strOptString);
        String strDecrypt5 = DecryptUtils.decrypt(this.mSetting.optString("bf"), strOptString);
        HashMap map = new HashMap();
        map.put(strArrSplit[0], strArrSplit[1]);
        map.put(strArrSplit2[0], strArrSplit2[1]);
        map.put(strDecrypt3, this.mJumpPath);
        Intent intent = new Intent();
        intent.setAction(strDecrypt5);
        intent.setData(Uri.parse(strDecrypt4 + getUrlParamsByMap(map)));
        intent.addFlags(268468224);
        return intent;
    }

    public static String getUrlParamsByMap(Map<String, String> map) {
        if (map == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            stringBuffer.append(entry.getKey());
            stringBuffer.append("=");
            stringBuffer.append(URLEncoder.encode(entry.getValue()));
            stringBuffer.append("&");
        }
        String string = stringBuffer.toString();
        return string.endsWith("&") ? string.substring(0, string.length() - 1) : string;
    }
}
