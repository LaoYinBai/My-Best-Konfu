package com.ss.android.socialbase.appdownloader.ah;

import android.content.Context;
import android.content.Intent;
import com.ss.android.socialbase.downloader.constants.DownloadConstants;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class O1DevicePlan extends AbsDevicePlan {
    public O1DevicePlan(Context context, DownloadSetting downloadSetting, String str) {
        super(context, downloadSetting, str);
    }

    @Override // com.ss.android.socialbase.appdownloader.ah.IAhDevicePlan
    public Intent getJumpIntent() {
        Intent intent = new Intent(DownloadConstants.LOWER_OPPO + ".filemanager.intent.action.BROWSER_FILE");
        intent.putExtra("CurrentDir", this.mJumpPath);
        intent.putExtra("CurrentMode", 1);
        intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        intent.addFlags(32768);
        intent.addFlags(1073741824);
        return intent;
    }
}
