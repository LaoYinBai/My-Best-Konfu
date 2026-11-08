package com.ss.android.socialbase.appdownloader.ah;

import android.content.Context;
import android.util.Log;
import com.ss.android.socialbase.downloader.logger.Logger;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class AbsDevicePlan implements IAhDevicePlan {
    private static final String TAG = "AbsDevicePlan";
    protected final Context mContext;
    protected final String mJumpPath;
    protected final DownloadSetting mSetting;

    public AbsDevicePlan(Context context, DownloadSetting downloadSetting, String str) {
        this.mContext = context;
        this.mSetting = downloadSetting;
        this.mJumpPath = str;
    }

    @Override // com.ss.android.socialbase.appdownloader.ah.IAhDevicePlan
    public boolean isValid() {
        if (this.mContext == null) {
            return false;
        }
        try {
        } catch (Throwable th) {
            if (Logger.debug()) {
                Log.e(TAG, "check is valid failed!", th);
            }
        }
        return getJumpIntent().resolveActivity(this.mContext.getPackageManager()) != null;
    }
}
