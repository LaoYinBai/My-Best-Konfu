package com.ss.android.downloadlib.addownload;

import android.os.Handler;
import androidx.annotation.NonNull;
import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.addownload.CommonDownloadHandler;
import com.ss.android.downloadlib.addownload.model.ModelBox;
import com.ss.android.downloadlib.addownload.model.ModelManager;
import com.ss.android.downloadlib.addownload.model.SharedPrefsManager;
import com.ss.android.downloadlib.addownload.optimize.ClearSpaceUtil;
import com.ss.android.downloadlib.addownload.optimize.DownloadOptimizationManager;
import com.ss.android.downloadlib.addownload.optimize.ICleanSpaceResultListener;
import com.ss.android.downloadlib.constants.EventConstants;
import com.ss.android.downloadlib.event.AdEventHandler;
import com.ss.android.downloadlib.utils.DownloadSettingUtils;
import com.ss.android.downloadlib.utils.ToolUtils;
import com.ss.android.socialbase.appdownloader.AppDownloadUtils;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.network.IFetchHttpHeadInfoListener;
import com.ss.android.socialbase.downloader.network.connectionpool.DownloadPreconnecter;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class CleanSpaceHelper {
    private ModelBox box;
    private Handler mDownloadInfoChangeHandler;
    private AtomicBoolean mIsHandled = new AtomicBoolean(false);
    private AtomicBoolean mDownloadActionCanceled = new AtomicBoolean(false);

    CleanSpaceHelper(Handler handler) {
        this.mDownloadInfoChangeHandler = handler;
    }

    public void setModelBox(ModelBox modelBox) {
        this.box = modelBox;
    }

    public boolean isDownloadActionCanceled() {
        return this.mDownloadActionCanceled.get();
    }

    public void setDownloadActionCanceled(boolean z) {
        this.mDownloadActionCanceled.set(z);
    }

    void handleSpaceBeforeDownload(final int i, final long j, long j2, final CommonDownloadHandler.DownloadInvoker downloadInvoker) {
        this.mDownloadActionCanceled.set(false);
        if (downloadInvoker == null) {
            return;
        }
        if (!DownloadSettingUtils.cleanSpaceEnable(i) || !DownloadSettingUtils.cleanSpaceBeforeDownloadSwitch(i)) {
            downloadInvoker.invoke();
            return;
        }
        long fetchApkHeadTimeout = DownloadSettingUtils.getFetchApkHeadTimeout(i);
        this.mIsHandled.set(false);
        final String downloadUrl = this.box.model.getDownloadUrl();
        NativeDownloadModel nativeModelByUrl = ModelManager.getInstance().getNativeModelByUrl(downloadUrl);
        if (nativeModelByUrl == null) {
            nativeModelByUrl = new NativeDownloadModel(this.box.model, this.box.event, this.box.controller, 0);
            ModelManager.getInstance().putNativeModel(nativeModelByUrl);
        }
        final NativeDownloadModel nativeDownloadModel = nativeModelByUrl;
        nativeDownloadModel.setHandleNoEnoughSpaceDownload(false);
        if (GlobalInfo.getCleanManager() != null) {
            GlobalInfo.getCleanManager().setModelId(nativeDownloadModel.getId());
        }
        DownloadOptimizationManager.getInstance().removeCleanResultListener(nativeDownloadModel.getDownloadUrl());
        boolean zFetchApkSizeSwitch = DownloadSettingUtils.fetchApkSizeSwitch(i);
        if (j2 > 0) {
            handleStorageSpace(i, downloadUrl, j2, nativeDownloadModel, j, downloadInvoker);
        } else if (zFetchApkSizeSwitch) {
            fetchApkSize(downloadUrl, nativeDownloadModel, new CommonDownloadHandler.IFetchApkSizeListener() { // from class: com.ss.android.downloadlib.addownload.CleanSpaceHelper.1
                @Override // com.ss.android.downloadlib.addownload.CommonDownloadHandler.IFetchApkSizeListener
                public void onFetchEnd(long j3) throws Throwable {
                    CleanSpaceHelper.this.handleStorageSpace(i, downloadUrl, j3, nativeDownloadModel, j, downloadInvoker);
                }
            });
        } else {
            fetchApkHeadTimeout = 0;
        }
        this.mDownloadInfoChangeHandler.postDelayed(new Runnable() { // from class: com.ss.android.downloadlib.addownload.CleanSpaceHelper.2
            @Override // java.lang.Runnable
            public void run() {
                if (CleanSpaceHelper.this.mIsHandled.get()) {
                    return;
                }
                CleanSpaceHelper.this.mIsHandled.set(true);
                downloadInvoker.invoke();
            }
        }, fetchApkHeadTimeout);
    }

    private void fetchApkSize(String str, NativeDownloadModel nativeDownloadModel, final CommonDownloadHandler.IFetchApkSizeListener iFetchApkSizeListener) {
        if (iFetchApkSizeListener == null) {
            return;
        }
        DownloadPreconnecter.asyncFetchHttpHeadInfo(str, new IFetchHttpHeadInfoListener() { // from class: com.ss.android.downloadlib.addownload.CleanSpaceHelper.3
            @Override // com.ss.android.socialbase.downloader.network.IFetchHttpHeadInfoListener
            public void onFetchFinished(Map<String, String> map) throws JSONException {
                if (CleanSpaceHelper.this.mIsHandled.get()) {
                    return;
                }
                CleanSpaceHelper.this.mIsHandled.set(true);
                long contentLength = CleanSpaceHelper.this.getContentLength(map);
                if (contentLength > 0) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.putOpt(EventConstants.ExtraJson.APK_SIZE, Long.valueOf(contentLength));
                        jSONObject.putOpt("available_space", Long.valueOf(CleanSpaceHelper.getAvailableSize()));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                iFetchApkSizeListener.onFetchEnd(contentLength);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getContentLength(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return 0L;
        }
        try {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if ("content-length".equalsIgnoreCase(key)) {
                    return Long.parseLong(value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleStorageSpace(int i, String str, long j, final NativeDownloadModel nativeDownloadModel, long j2, final CommonDownloadHandler.DownloadInvoker downloadInvoker) throws Throwable {
        this.mIsHandled.set(true);
        boolean zTryCleanStorageSpace = false;
        if (j > 0) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.putOpt(EventConstants.ExtraJson.APK_SIZE, Long.valueOf(j));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            long jLongValue = (Double.valueOf((DownloadSettingUtils.minInstallSize(i) + 1.0d) * j).longValue() + DownloadSettingUtils.getStorageMinSize(i)) - j2;
            long availableSize = getAvailableSize();
            if (availableSize < jLongValue) {
                sendSpaceNoEnoughEvent(nativeDownloadModel, jSONObject, jLongValue, availableSize);
                cleanAppCache(nativeDownloadModel);
                long availableSize2 = getAvailableSize();
                if (availableSize2 < jLongValue) {
                    nativeDownloadModel.setIsCleanSpaceBeforeDownload(true);
                    final String downloadUrl = nativeDownloadModel.getDownloadUrl();
                    DownloadOptimizationManager.getInstance().setCleanResultListener(downloadUrl, new ICleanSpaceResultListener() { // from class: com.ss.android.downloadlib.addownload.CleanSpaceHelper.4
                        @Override // com.ss.android.downloadlib.addownload.optimize.ICleanSpaceResultListener
                        public void onCleanSpaceEnd(boolean z, final String str2) {
                            CleanSpaceHelper.this.mDownloadInfoChangeHandler.post(new Runnable() { // from class: com.ss.android.downloadlib.addownload.CleanSpaceHelper.4.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    nativeDownloadModel.setDownloadFinishReason(str2);
                                    SharedPrefsManager.getInstance().saveNativeDownloadModel(nativeDownloadModel);
                                    downloadInvoker.invoke();
                                }
                            });
                            DownloadOptimizationManager.getInstance().removeCleanResultListener(downloadUrl);
                        }
                    });
                    zTryCleanStorageSpace = tryCleanStorageSpace(i, nativeDownloadModel, str, jLongValue);
                    if (zTryCleanStorageSpace) {
                        nativeDownloadModel.setHandleNoEnoughSpaceDownload(true);
                    }
                } else {
                    sendQuiteCleanEvent(nativeDownloadModel, jSONObject, availableSize, availableSize2);
                }
            }
        }
        if (zTryCleanStorageSpace) {
            return;
        }
        this.mDownloadInfoChangeHandler.post(new Runnable() { // from class: com.ss.android.downloadlib.addownload.CleanSpaceHelper.5
            @Override // java.lang.Runnable
            public void run() {
                downloadInvoker.invoke();
            }
        });
    }

    private boolean tryCleanStorageSpace(int i, @NonNull NativeDownloadModel nativeDownloadModel, String str, long j) throws JSONException {
        if (!DownloadSettingUtils.cleanSpaceEnable(i)) {
            return false;
        }
        if (GlobalInfo.getCleanManager() != null) {
            return GlobalInfo.getCleanManager().handleCleanSpace(i, str, true, j);
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt(EventConstants.ExtraJson.CLEAN_SHOW_DIALOG_RESULT, 3);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AdEventHandler.getInstance().sendUnityEvent(EventConstants.UnityLabel.CLEAN_SHOW_DIALOG, jSONObject, nativeDownloadModel);
        return false;
    }

    public static boolean tryShowInstallFailCleanDialog(final DownloadInfo downloadInfo, long j) {
        int id = downloadInfo.getId();
        boolean zHandleCleanSpace = false;
        if (!DownloadSettingUtils.cleanSpaceEnable(id)) {
            return false;
        }
        if (GlobalInfo.getCleanManager() != null && (zHandleCleanSpace = GlobalInfo.getCleanManager().handleCleanSpace(id, downloadInfo.getUrl(), false, j))) {
            DownloadOptimizationManager.getInstance().setCleanResultListener(downloadInfo.getUrl(), new ICleanSpaceResultListener() { // from class: com.ss.android.downloadlib.addownload.CleanSpaceHelper.6
                @Override // com.ss.android.downloadlib.addownload.optimize.ICleanSpaceResultListener
                public void onCleanSpaceEnd(boolean z, String str) throws Throwable {
                    NativeDownloadModel nativeModelByInfo = ModelManager.getInstance().getNativeModelByInfo(downloadInfo);
                    CleanSpaceHelper.cleanAppCache(nativeModelByInfo);
                    AdEventHandler.getInstance().sendUnityEvent(EventConstants.UnityLabel.CLEAN_SPACE_INSTALL, CleanSpaceHelper.getInstallExtJson("clean_space_success"), nativeModelByInfo);
                    int iStartViewIntent = AppDownloadUtils.startViewIntent(GlobalInfo.getContext(), downloadInfo.getId(), false);
                    nativeModelByInfo.setInstallAfterCleanSpace(true);
                    ModelManager.getInstance().putNativeModel(nativeModelByInfo);
                    if (iStartViewIntent == 1) {
                        AdEventHandler.getInstance().sendUnityEvent(EventConstants.UnityLabel.CLEAN_SPACE_INSTALL, CleanSpaceHelper.getInstallExtJson("install_success"), nativeModelByInfo);
                    }
                    DownloadOptimizationManager.getInstance().removeCleanResultListener(downloadInfo.getUrl());
                }
            });
        }
        return zHandleCleanSpace;
    }

    public static JSONObject getInstallExtJson(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt(EventConstants.ExtraJson.CLEAN_SPACE_INSTALL_PARAMS, str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public static void startScan(int i) {
        if (DownloadSettingUtils.cleanSpaceEnable(i) && GlobalInfo.getCleanManager() != null && GlobalInfo.getCleanManager().enableScan()) {
            GlobalInfo.getCleanManager().startScan();
        }
    }

    public static long getLastScanSpaceSize() {
        if (GlobalInfo.getCleanManager() != null) {
            return GlobalInfo.getCleanManager().getLastScanSpaceSize();
        }
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void cleanAppCache(NativeDownloadModel nativeDownloadModel) throws Throwable {
        long availableSize = getAvailableSize();
        if (GlobalInfo.getCleanManager() != null) {
            GlobalInfo.getCleanManager().clearStorageSpace();
        }
        ClearSpaceUtil.clearUnCompleteApk();
        ClearSpaceUtil.clearCompleteApk();
        if (DownloadSettingUtils.cleanAppCacheDir(nativeDownloadModel.getDownloadId())) {
            ClearSpaceUtil.clearAppCacheDir(GlobalInfo.getContext());
        }
        long availableSize2 = getAvailableSize();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt(EventConstants.ExtraJson.QUITE_CLEAN_SIZE, Long.valueOf(availableSize2 - availableSize));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AdEventHandler.getInstance().sendUnityEvent(EventConstants.UnityLabel.CLEAN_QUITE_FINISH, jSONObject, nativeDownloadModel);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long getAvailableSize() {
        return ToolUtils.getExternalAvailableSpaceBytes(0L);
    }

    private void sendSpaceNoEnoughEvent(NativeDownloadModel nativeDownloadModel, JSONObject jSONObject, long j, long j2) throws JSONException {
        try {
            jSONObject.putOpt("available_space", Long.valueOf(j2));
            jSONObject.putOpt(EventConstants.ExtraJson.APK_DOWNLOAD_NEED_SIZE, Long.valueOf(j));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AdEventHandler.getInstance().sendUnityEvent(EventConstants.UnityLabel.CLEAN_SPACE_NO_ENOUGH, jSONObject, nativeDownloadModel);
    }

    private void sendQuiteCleanEvent(NativeDownloadModel nativeDownloadModel, JSONObject jSONObject, long j, long j2) throws JSONException {
        nativeDownloadModel.setDownloadFinishReason("1");
        SharedPrefsManager.getInstance().saveNativeDownloadModel(nativeDownloadModel);
        try {
            jSONObject.putOpt(EventConstants.ExtraJson.QUITE_CLEAN_SIZE, Long.valueOf(j2 - j));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AdEventHandler.getInstance().sendUnityEvent(EventConstants.UnityLabel.DOWNLOAD_AFTER_QUITE_CLEAN, jSONObject, nativeDownloadModel);
    }
}
