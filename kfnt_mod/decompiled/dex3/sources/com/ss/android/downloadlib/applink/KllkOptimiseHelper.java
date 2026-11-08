package com.ss.android.downloadlib.applink;

import android.os.Build;
import androidx.annotation.NonNull;
import com.ss.android.downloadad.api.model.InnerUnifyData;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.constants.DownloadSettingKeys;
import com.ss.android.downloadlib.utils.DownloadSettingUtils;
import com.ss.android.socialbase.appdownloader.util.RomUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class KllkOptimiseHelper {
    public static boolean canInvokeAppFormOppoBackground(@NonNull InnerUnifyData innerUnifyData) {
        return RomUtils.isOppo() && Build.VERSION.SDK_INT < 29 && GlobalInfo.getAppStatusChangeListener() != null && GlobalInfo.getAppStatusChangeListener().isAppInBackground() && DownloadSettingUtils.getSetting(innerUnifyData).optInt(DownloadSettingKeys.KEY_INVOKE_APP_FORM_BACKGROUND_SWITCH) == 1 && innerUnifyData.enableNewActivity();
    }
}
