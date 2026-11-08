package com.ss.android.socialbase.appdownloader.ah;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.ss.android.socialbase.appdownloader.util.DecryptUtils;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;
import com.umeng.commonsdk.proguard.o;
import com.xiaomi.mipush.sdk.Constants;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class O3DevicePlan extends AbsDevicePlan {
    private String mApkName;
    private String mContentUri;

    public O3DevicePlan(Context context, DownloadSetting downloadSetting, String str, String str2, String str3) {
        super(context, downloadSetting, str);
        this.mContentUri = str2;
        this.mApkName = str3;
    }

    @Override // com.ss.android.socialbase.appdownloader.ah.IAhDevicePlan
    public Intent getJumpIntent() {
        String strOptString = this.mSetting.optString(o.at);
        String strDecrypt = DecryptUtils.decrypt(this.mSetting.optString("ak"), strOptString);
        String strDecrypt2 = DecryptUtils.decrypt(this.mSetting.optString("am"), strOptString);
        String strDecrypt3 = DecryptUtils.decrypt(this.mSetting.optString("an"), strOptString);
        String strSubstring = null;
        if (TextUtils.isEmpty(strDecrypt3) || strDecrypt3.split(Constants.ACCEPT_TIME_SEPARATOR_SP).length != 2) {
            return null;
        }
        String[] strArrSplit = strDecrypt3.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
        String strDecrypt4 = DecryptUtils.decrypt(this.mSetting.optString("al"), strOptString);
        String strDecrypt5 = DecryptUtils.decrypt(this.mSetting.optString("ao"), strOptString);
        if (TextUtils.isEmpty(strDecrypt5) || strDecrypt5.split(Constants.ACCEPT_TIME_SEPARATOR_SP).length != 2) {
            return null;
        }
        String[] strArrSplit2 = strDecrypt5.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
        JSONObject jSONObjectOptJSONObject = this.mSetting.optJSONObject(DownloadSettingKeys.KEY_ANTI_HIJACK_DIR);
        if (jSONObjectOptJSONObject != null) {
            String strOptString2 = jSONObjectOptJSONObject.optString(DownloadSettingKeys.AntiHijackDir.KEY_ANTI_HIJACK_DIR_NAME);
            if (!TextUtils.isEmpty(strOptString2) && strOptString2.contains("%s")) {
                try {
                    strSubstring = String.format(strOptString2, this.mApkName);
                } catch (Throwable unused) {
                    strSubstring = this.mApkName;
                }
            } else {
                strSubstring = this.mApkName;
            }
            if (strSubstring.length() > 255) {
                strSubstring = strDecrypt4.substring(strSubstring.length() - 255);
            }
        }
        Intent intent = new Intent(strDecrypt);
        intent.putExtra(strArrSplit2[0], strArrSplit2[1]);
        intent.putExtra(strDecrypt2, this.mContentUri);
        intent.putExtra(strDecrypt4, strSubstring);
        intent.putExtra(strArrSplit[0], Integer.parseInt(strArrSplit[1]));
        intent.addFlags(268468224);
        return intent;
    }
}
