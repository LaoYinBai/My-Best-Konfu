package com.ss.android.socialbase.appdownloader.ah;

import android.content.Context;
import android.content.Intent;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class M1UnknownSourcePlan extends AbsDevicePlan {
    public M1UnknownSourcePlan(Context context) {
        super(context, null, null);
    }

    @Override // com.ss.android.socialbase.appdownloader.ah.IAhDevicePlan
    public Intent getJumpIntent() {
        Intent intent = new Intent("android.settings.SECURITY_SETTINGS");
        intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        intent.addFlags(1073741824);
        intent.addFlags(DownloadExpSwitchCode.FIX_CANCEL_DELETE_FILE_ERROR);
        return intent;
    }
}
