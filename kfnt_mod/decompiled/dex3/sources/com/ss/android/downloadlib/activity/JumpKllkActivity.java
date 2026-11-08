package com.ss.android.downloadlib.activity;

import android.text.TextUtils;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.exception.TTDownloaderMonitor;
import com.ss.android.downloadlib.utils.InnerOpenAppUtils;
import com.ss.android.socialbase.appdownloader.AppDownloadUtils;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class JumpKllkActivity extends TTDelegateActivity {
    @Override // com.ss.android.downloadlib.activity.TTDelegateActivity
    protected void handleIntent() throws JSONException {
        if (getIntent() == null) {
            TTDownloaderMonitor.inst().monitorDataError("handleIntent is null");
            AppDownloadUtils.safeFinish(this);
        }
        String stringExtra = getIntent().getStringExtra("p");
        long longExtra = getIntent().getLongExtra(AdDownloadModel.JsonKey.ID, 0L);
        if (TextUtils.isEmpty(stringExtra) || longExtra == 0) {
            TTDownloaderMonitor.inst().monitorDataError("getPackage or id is null");
            AppDownloadUtils.safeFinish(this);
        }
        int iOptInt = GlobalInfo.getDownloadSettings().optInt("ab", 0);
        InnerOpenAppUtils.realOpenKllk2Market(this, stringExtra, longExtra, iOptInt == 1);
        if (iOptInt != 1) {
            AppDownloadUtils.safeFinish(this);
        }
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        AppDownloadUtils.safeFinish(this);
    }
}
