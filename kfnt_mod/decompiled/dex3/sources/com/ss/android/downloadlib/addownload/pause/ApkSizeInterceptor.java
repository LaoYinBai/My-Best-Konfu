package com.ss.android.downloadlib.addownload.pause;

import com.ss.android.downloadad.api.model.InnerUnifyData;
import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.TTDownloader;
import com.ss.android.downloadlib.activity.TTDelegateActivity;
import com.ss.android.downloadlib.addownload.DownloadPercentHelper;
import com.ss.android.downloadlib.addownload.dialog.ISelectOperationListener;
import com.ss.android.downloadlib.constants.DownloadSettingKeys;
import com.ss.android.downloadlib.constants.EventConstants;
import com.ss.android.downloadlib.event.AdEventHandler;
import com.ss.android.downloadlib.utils.DownloadSettingUtils;
import com.ss.android.socialbase.downloader.constants.DownloadConstants;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.union.game.sdk.feedback.picture.select.d;
import java.text.DecimalFormat;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ApkSizeInterceptor implements IPauseInterceptor {
    private static ISelectOperationListener sSelectOperationListener;

    public static ISelectOperationListener getSelectOperationListener() {
        return sSelectOperationListener;
    }

    @Override // com.ss.android.downloadlib.addownload.pause.IPauseInterceptor
    public boolean interceptor(final NativeDownloadModel nativeDownloadModel, int i, final IPauseCallback iPauseCallback) {
        DownloadInfo downloadInfo;
        if (nativeDownloadModel == null || nativeDownloadModel.isIsShowApkSizeRetain() || !enable(nativeDownloadModel) || (downloadInfo = TTDownloader.inst(null).getDownloadInfo(nativeDownloadModel.getDownloadUrl())) == null) {
            return false;
        }
        long currBytes = DownloadPercentHelper.getCurrBytes(downloadInfo.getId(), downloadInfo.getCurBytes(), downloadInfo.getTotalBytes());
        long totalBytes = downloadInfo.getTotalBytes();
        if (currBytes <= 0 || totalBytes <= 0 || totalBytes > getDetainApkSize(nativeDownloadModel.getDownloadId())) {
            return false;
        }
        sSelectOperationListener = new ISelectOperationListener() { // from class: com.ss.android.downloadlib.addownload.pause.ApkSizeInterceptor.1
            @Override // com.ss.android.downloadlib.addownload.dialog.ISelectOperationListener
            public void confirm() throws JSONException {
                ISelectOperationListener unused = ApkSizeInterceptor.sSelectOperationListener = null;
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.putOpt(EventConstants.ExtraJson.PAUSE_OPTIMISE_TYPE, EventConstants.ExtraJson.APK_SIZE);
                    jSONObject.putOpt(EventConstants.ExtraJson.PAUSE_OPTIMISE_ACTION, "confirm");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                AdEventHandler.getInstance().sendUnityEvent(EventConstants.UnityLabel.PAUSE_OPTIMISE, jSONObject, nativeDownloadModel);
            }

            @Override // com.ss.android.downloadlib.addownload.dialog.ISelectOperationListener
            public void cancel() throws JSONException {
                ISelectOperationListener unused = ApkSizeInterceptor.sSelectOperationListener = null;
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.putOpt(EventConstants.ExtraJson.PAUSE_OPTIMISE_TYPE, EventConstants.ExtraJson.APK_SIZE);
                    jSONObject.putOpt(EventConstants.ExtraJson.PAUSE_OPTIMISE_ACTION, "cancel");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                AdEventHandler.getInstance().sendUnityEvent(EventConstants.UnityLabel.PAUSE_OPTIMISE, jSONObject, nativeDownloadModel);
                iPauseCallback.pause(nativeDownloadModel);
            }
        };
        TTDelegateActivity.showApkSizeRetainDialog(nativeDownloadModel, String.format("该下载任务仅需%s，即将下载完成，是否继续下载？", getSizeStr(totalBytes - currBytes)), "继续", "暂停");
        nativeDownloadModel.setIsShowApkSizeRetain(true);
        return true;
    }

    private int getDetainApkSize(int i) {
        return DownloadSetting.obtain(i).optInt(DownloadSettingKeys.PAUSE_OPTIMISE_APK_SIZE, 100) * d.d * d.d;
    }

    private boolean enable(InnerUnifyData innerUnifyData) {
        return DownloadSettingUtils.getSetting(innerUnifyData).optInt(DownloadSettingKeys.PAUSE_OPTIMISE_APK_SIZE_SWITCH, 0) == 1 && innerUnifyData.enableNewActivity();
    }

    private static String getSizeStr(long j) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        if (j >= DownloadConstants.GB) {
            return (j / DownloadConstants.GB) + "G";
        }
        if (j >= 1048576) {
            return (j / 1048576) + "M";
        }
        return decimalFormat.format(j / 1048576.0f) + "M";
    }
}
