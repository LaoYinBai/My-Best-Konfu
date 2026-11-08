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
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DownloadPercentInterceptor implements IPauseInterceptor {
    private static ISelectOperationListener sSelectOperationListener;

    public static ISelectOperationListener getSelectOperationListener() {
        return sSelectOperationListener;
    }

    @Override // com.ss.android.downloadlib.addownload.pause.IPauseInterceptor
    public boolean interceptor(final NativeDownloadModel nativeDownloadModel, int i, final IPauseCallback iPauseCallback) {
        DownloadInfo downloadInfo;
        if (nativeDownloadModel == null || nativeDownloadModel.isIsShowDownloadPercentRetain() || !enable(nativeDownloadModel) || (downloadInfo = TTDownloader.inst(null).getDownloadInfo(nativeDownloadModel.getDownloadUrl())) == null) {
            return false;
        }
        long curBytes = downloadInfo.getCurBytes();
        long totalBytes = downloadInfo.getTotalBytes();
        if (curBytes > 0 && totalBytes > 0) {
            int iHandlePercent = DownloadPercentHelper.handlePercent(downloadInfo.getId(), (int) ((curBytes * 100) / totalBytes));
            if (iHandlePercent > getDownloadPercentDetain(nativeDownloadModel.getDownloadId())) {
                sSelectOperationListener = new ISelectOperationListener() { // from class: com.ss.android.downloadlib.addownload.pause.DownloadPercentInterceptor.1
                    @Override // com.ss.android.downloadlib.addownload.dialog.ISelectOperationListener
                    public void confirm() throws JSONException {
                        ISelectOperationListener unused = DownloadPercentInterceptor.sSelectOperationListener = null;
                        JSONObject jSONObject = new JSONObject();
                        try {
                            jSONObject.putOpt(EventConstants.ExtraJson.PAUSE_OPTIMISE_TYPE, "download_percent");
                            jSONObject.putOpt(EventConstants.ExtraJson.PAUSE_OPTIMISE_ACTION, "confirm");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        AdEventHandler.getInstance().sendUnityEvent(EventConstants.UnityLabel.PAUSE_OPTIMISE, jSONObject, nativeDownloadModel);
                    }

                    @Override // com.ss.android.downloadlib.addownload.dialog.ISelectOperationListener
                    public void cancel() throws JSONException {
                        ISelectOperationListener unused = DownloadPercentInterceptor.sSelectOperationListener = null;
                        JSONObject jSONObject = new JSONObject();
                        try {
                            jSONObject.putOpt(EventConstants.ExtraJson.PAUSE_OPTIMISE_TYPE, "download_percent");
                            jSONObject.putOpt(EventConstants.ExtraJson.PAUSE_OPTIMISE_ACTION, "cancel");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        AdEventHandler.getInstance().sendUnityEvent(EventConstants.UnityLabel.PAUSE_OPTIMISE, jSONObject, nativeDownloadModel);
                        iPauseCallback.pause(nativeDownloadModel);
                    }
                };
                TTDelegateActivity.showDownloadPercentRetainDialog(nativeDownloadModel, String.format("已下载%s%%，即将下载完成，是否继续下载？", Integer.valueOf(iHandlePercent)), "继续", "暂停");
                nativeDownloadModel.setIsShowDownloadPercentRetain(true);
                return true;
            }
        }
        return false;
    }

    private int getDownloadPercentDetain(int i) {
        return DownloadSetting.obtain(i).optInt(DownloadSettingKeys.PAUSE_OPTIMISE_DOWNLOAD_PERCENT, 50);
    }

    private boolean enable(InnerUnifyData innerUnifyData) {
        return DownloadSettingUtils.getSetting(innerUnifyData).optInt(DownloadSettingKeys.PAUSE_OPTIMISE_DOWNLOAD_PERCENT_SWITCH, 0) == 1 && innerUnifyData.enableNewActivity();
    }
}
