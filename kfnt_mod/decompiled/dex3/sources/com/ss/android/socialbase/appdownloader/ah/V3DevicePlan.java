package com.ss.android.socialbase.appdownloader.ah;

import android.content.Context;
import android.content.Intent;
import com.ss.android.socialbase.appdownloader.util.DecryptUtils;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.umeng.commonsdk.proguard.o;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class V3DevicePlan extends AbsDevicePlan {
    public V3DevicePlan(Context context, DownloadSetting downloadSetting, String str) {
        super(context, downloadSetting, str);
    }

    @Override // com.ss.android.socialbase.appdownloader.ah.IAhDevicePlan
    public Intent getJumpIntent() {
        String strOptString = this.mSetting.optString(o.at);
        String strDecrypt = DecryptUtils.decrypt(this.mSetting.optString("ag"), strOptString);
        String strDecrypt2 = DecryptUtils.decrypt(this.mSetting.optString("ah"), strOptString);
        String strDecrypt3 = DecryptUtils.decrypt(this.mSetting.optString("ai"), strOptString);
        String strDecrypt4 = DecryptUtils.decrypt(this.mSetting.optString("aj"), strOptString);
        Intent intent = new Intent();
        intent.putExtra(strDecrypt, this.mJumpPath);
        intent.putExtra(strDecrypt2, "*/*");
        intent.putExtra(strDecrypt3, true);
        intent.setAction(strDecrypt4);
        intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        intent.addFlags(32768);
        return intent;
    }
}
