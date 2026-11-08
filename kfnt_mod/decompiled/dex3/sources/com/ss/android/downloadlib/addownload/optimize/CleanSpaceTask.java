package com.ss.android.downloadlib.addownload.optimize;

import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.addownload.model.ModelManager;
import com.ss.android.downloadlib.addownload.model.SharedPrefsManager;
import com.ss.android.downloadlib.constants.EventConstants;
import com.ss.android.downloadlib.event.AdEventHandler;
import com.ss.android.downloadlib.utils.DownloadSettingUtils;
import com.ss.android.downloadlib.utils.ToolUtils;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class CleanSpaceTask implements Runnable {
    private DownloadInfo info;

    public CleanSpaceTask(DownloadInfo downloadInfo) {
        this.info = downloadInfo;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        final NativeDownloadModel nativeModelByInfo;
        if (this.info == null || (nativeModelByInfo = ModelManager.getInstance().getNativeModelByInfo(this.info)) == null) {
            return;
        }
        AdEventHandler.getInstance().sendUnityEvent(EventConstants.UnityLabel.CLEAN_SPACE_TASK, nativeModelByInfo);
        long jLongValue = Double.valueOf((DownloadSettingUtils.minInstallSize(this.info.getId()) + 1.0d) * this.info.getTotalBytes()).longValue() - this.info.getCurBytes();
        long externalAvailableSpaceBytes = ToolUtils.getExternalAvailableSpaceBytes(0L);
        if (GlobalInfo.getCleanManager() != null) {
            GlobalInfo.getCleanManager().clearStorageSpace();
        }
        ClearSpaceUtil.clearUnCompleteApk();
        ClearSpaceUtil.clearCompleteApk();
        if (DownloadSettingUtils.cleanAppCacheDir(nativeModelByInfo.getDownloadId())) {
            ClearSpaceUtil.clearAppCacheDir(GlobalInfo.getContext());
        }
        long externalAvailableSpaceBytes2 = ToolUtils.getExternalAvailableSpaceBytes(0L);
        if (externalAvailableSpaceBytes2 >= jLongValue) {
            nativeModelByInfo.setDownloadFinishReason("1");
            SharedPrefsManager.getInstance().saveNativeDownloadModel(nativeModelByInfo);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.putOpt(EventConstants.ExtraJson.QUITE_CLEAN_SIZE, Long.valueOf(externalAvailableSpaceBytes2 - externalAvailableSpaceBytes));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            AdEventHandler.getInstance().sendUnityEvent(EventConstants.UnityLabel.DOWNLOAD_AFTER_QUITE_CLEAN, jSONObject, nativeModelByInfo);
            Downloader.getInstance(GlobalInfo.getContext()).restart(this.info.getId());
            return;
        }
        if (GlobalInfo.getCleanManager() != null) {
            nativeModelByInfo.setIsCleanSpaceBeforeDownload(false);
            DownloadOptimizationManager.getInstance().setCleanResultListener(nativeModelByInfo.getDownloadUrl(), new ICleanSpaceResultListener() { // from class: com.ss.android.downloadlib.addownload.optimize.CleanSpaceTask.1
                @Override // com.ss.android.downloadlib.addownload.optimize.ICleanSpaceResultListener
                public void onCleanSpaceEnd(boolean z, String str) {
                    nativeModelByInfo.setDownloadFinishReason(str);
                    SharedPrefsManager.getInstance().saveNativeDownloadModel(nativeModelByInfo);
                    Downloader.getInstance(GlobalInfo.getContext()).restart(CleanSpaceTask.this.info.getId());
                    DownloadOptimizationManager.getInstance().removeCleanResultListener(nativeModelByInfo.getDownloadUrl());
                }
            });
            if (GlobalInfo.getCleanManager().handleCleanSpace(this.info.getId(), this.info.getUrl(), true, jLongValue)) {
                nativeModelByInfo.setHandleNoEnoughSpaceDownload(true);
                return;
            }
            return;
        }
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.putOpt(EventConstants.ExtraJson.CLEAN_SHOW_DIALOG_RESULT, 3);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        AdEventHandler.getInstance().sendUnityEvent(EventConstants.UnityLabel.CLEAN_SHOW_DIALOG, jSONObject2, nativeModelByInfo);
    }
}
