package com.ss.android.socialbase.appdownloader.ah;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.RequiresApi;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;

@RequiresApi(api = 26)
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AndroidUnknownSourcePlan extends AbsDevicePlan {
    public AndroidUnknownSourcePlan(Context context) {
        super(context, null, null);
    }

    @Override // com.ss.android.socialbase.appdownloader.ah.IAhDevicePlan
    public Intent getJumpIntent() {
        Intent intent = new Intent("android.settings.MANAGE_UNKNOWN_APP_SOURCES", Uri.parse("package:" + this.mContext.getPackageName()));
        intent.addFlags(1073741824);
        intent.addFlags(DownloadExpSwitchCode.FIX_CANCEL_DELETE_FILE_ERROR);
        intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        return intent;
    }
}
