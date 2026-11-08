package com.ss.android.downloadlib.addownload.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import com.ss.android.download.api.config.DownloadUIFactory;
import com.ss.android.download.api.model.DownloadAlertDialogInfo;
import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.TTDownloader;
import com.ss.android.downloadlib.activity.TTDelegateActivity;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.addownload.model.AppInfo;
import com.ss.android.downloadlib.addownload.model.ModelManager;
import com.ss.android.downloadlib.constants.DownloadConstants;
import com.ss.android.downloadlib.constants.DownloadSettingKeys;
import com.ss.android.downloadlib.constants.EventConstants;
import com.ss.android.downloadlib.event.AdEventHandler;
import com.ss.android.downloadlib.exception.TTDownloaderMonitor;
import com.ss.android.downloadlib.utils.Chain;
import com.ss.android.downloadlib.utils.TLogger;
import com.ss.android.downloadlib.utils.ToolUtils;
import com.ss.android.socialbase.appdownloader.AppDownloader;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import java.io.File;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AdDownloadDialogManager {
    private static final String TAG = "AdDownloadDialogManager";
    private static AdDownloadDialogManager sInstance;
    private String mReadyInstallPackage;
    private boolean mHasShownInstallDialog = false;
    private AdDownloadDialogSpHelper mSpHelper = new AdDownloadDialogSpHelper();

    @NonNull
    private CopyOnWriteArrayList<AppInfo> mUnInstalledInfoList = this.mSpHelper.loadInfoFromSp(DownloadConstants.SP_NAME_UNINSTALLED_APP, "key_uninstalled_list");

    public interface OnExitClickListener {
        void onExitClick();
    }

    private AdDownloadDialogManager() {
    }

    public static AdDownloadDialogManager Instance() {
        if (sInstance == null) {
            sInstance = new AdDownloadDialogManager();
        }
        return sInstance;
    }

    public void resetInstallDialogHasShown() {
        this.mHasShownInstallDialog = false;
    }

    public Chain<DownloadInfo, Boolean> tryShowInstallDialog(final Activity activity, final boolean z, final OnExitClickListener onExitClickListener) {
        TLogger.v(TAG, "tryShowInstallDialog canBackRefresh:" + z, null);
        if (GlobalInfo.getDownloadSettings().optInt(DownloadSettingKeys.KEY_DISABLE_INSTALL_APP_DIALOG) == 1 || this.mHasShownInstallDialog) {
            return Chain.createOnMain(new Chain.IRun<DownloadInfo, Boolean>() { // from class: com.ss.android.downloadlib.addownload.dialog.AdDownloadDialogManager.1
                @Override // com.ss.android.downloadlib.utils.Chain.IRun
                public Boolean run(DownloadInfo downloadInfo) {
                    return false;
                }
            }, null);
        }
        return Chain.createOnCpu(new Chain.IRun<Object, DownloadInfo>() { // from class: com.ss.android.downloadlib.addownload.dialog.AdDownloadDialogManager.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.ss.android.downloadlib.utils.Chain.IRun
            public DownloadInfo run(Object obj) {
                return AdDownloadDialogManager.this.findSuitableDownloadedMiniApk(GlobalInfo.getContext());
            }
        }, null).nextOnMain(new Chain.IRun<DownloadInfo, Boolean>() { // from class: com.ss.android.downloadlib.addownload.dialog.AdDownloadDialogManager.2
            @Override // com.ss.android.downloadlib.utils.Chain.IRun
            public Boolean run(DownloadInfo downloadInfo) {
                return Boolean.valueOf(AdDownloadDialogManager.this.showInstallDialog(activity, downloadInfo, z, onExitClickListener));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean showInstallDialog(Activity activity, DownloadInfo downloadInfo, boolean z, OnExitClickListener onExitClickListener) {
        if (downloadInfo == null) {
            try {
                if (this.mUnInstalledInfoList.isEmpty()) {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        if (activity != null && !activity.isFinishing()) {
            boolean z2 = true;
            if (downloadInfo != null && this.mUnInstalledInfoList.isEmpty()) {
                showPopDialog(activity, new AppInfo(downloadInfo.getId(), 0L, 0L, downloadInfo.getPackageName(), downloadInfo.getTitle(), null, downloadInfo.getTargetFilePath()), z, onExitClickListener);
                return true;
            }
            long jLastModified = downloadInfo != null ? new File(downloadInfo.getTargetFilePath()).lastModified() : 0L;
            ListIterator<AppInfo> listIterator = this.mUnInstalledInfoList.listIterator(this.mUnInstalledInfoList.size());
            while (true) {
                if (!listIterator.hasPrevious()) {
                    z2 = false;
                    break;
                }
                AppInfo appInfoPrevious = listIterator.previous();
                if (appInfoPrevious != null && !ToolUtils.isInstalledApp(GlobalInfo.getContext(), appInfoPrevious.mPackageName) && ToolUtils.exists(appInfoPrevious.mFileName)) {
                    if (new File(appInfoPrevious.mFileName).lastModified() >= jLastModified) {
                        showPopDialog(activity, appInfoPrevious, z, onExitClickListener);
                    } else {
                        showPopDialog(activity, new AppInfo(downloadInfo.getId(), 0L, 0L, downloadInfo.getPackageName(), downloadInfo.getTitle(), null, downloadInfo.getTargetFilePath()), z, onExitClickListener);
                    }
                }
            }
            TLogger.v(TAG, "tryShowInstallDialog isShow:" + z2, null);
            return z2;
        }
        return false;
    }

    @MainThread
    public boolean tryShowInstallDialogSync(Activity activity, boolean z, OnExitClickListener onExitClickListener) {
        if (GlobalInfo.getDownloadSettings().optInt(DownloadSettingKeys.KEY_DISABLE_INSTALL_APP_DIALOG) == 1 || this.mHasShownInstallDialog) {
            return false;
        }
        return showInstallDialog(activity, findSuitableDownloadedMiniApk(activity), z, onExitClickListener);
    }

    public void showPopDialog(Context context, AppInfo appInfo, boolean z, OnExitClickListener onExitClickListener) throws JSONException {
        this.mUnInstalledInfoList.clear();
        showBackInstallDialog(context, appInfo, onExitClickListener, z);
        this.mHasShownInstallDialog = true;
        TTDownloader.inst(context).updateLastActiveTimpstamp();
        this.mSpHelper.cleanSpKey(DownloadConstants.SP_NAME_UNINSTALLED_APP, "key_uninstalled_list");
        TLogger.v(TAG, "tryShowInstallDialog isShow:true", null);
    }

    public DownloadInfo findSuitableDownloadedMiniApk(Context context) {
        long lastActiveTimpstamp;
        List<DownloadInfo> successedDownloadInfosWithMimeType;
        DownloadInfo downloadInfo = null;
        try {
            lastActiveTimpstamp = TTDownloader.inst(context).getLastActiveTimpstamp();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (GlobalInfo.getDownloadSettings().optInt(DownloadConstants.KEY_ENABLE_SHOW_MINIAPP_DIALOG, 0) != 0 && (successedDownloadInfosWithMimeType = Downloader.getInstance(context).getSuccessedDownloadInfosWithMimeType("application/vnd.android.package-archive")) != null && !successedDownloadInfosWithMimeType.isEmpty()) {
            long j = 0;
            for (DownloadInfo downloadInfo2 : successedDownloadInfosWithMimeType) {
                if (downloadInfo2 != null && !ToolUtils.isInstalledApp(context, downloadInfo2.getPackageName()) && ToolUtils.exists(downloadInfo2.getTargetFilePath())) {
                    long jLastModified = new File(downloadInfo2.getTargetFilePath()).lastModified();
                    if (jLastModified >= lastActiveTimpstamp && downloadInfo2.getExtra() != null) {
                        try {
                            if (new JSONObject(downloadInfo2.getExtra()).has("isMiniApp") && (j == 0 || jLastModified > j)) {
                                downloadInfo = downloadInfo2;
                                j = jLastModified;
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
            return downloadInfo;
        }
        return null;
    }

    public void addUninstalledInfo(long j, long j2, long j3, String str, String str2, String str3, String str4) throws JSONException {
        for (int i = 0; i < this.mUnInstalledInfoList.size(); i++) {
            AppInfo appInfo = this.mUnInstalledInfoList.get(i);
            if (appInfo != null && appInfo.mAdId == j2) {
                this.mUnInstalledInfoList.set(i, new AppInfo(j, j2, j3, str, str2, str3, str4));
                this.mSpHelper.updateInfoToSp(DownloadConstants.SP_NAME_UNINSTALLED_APP, "key_uninstalled_list", this.mUnInstalledInfoList);
                return;
            }
        }
        this.mUnInstalledInfoList.add(new AppInfo(j, j2, j3, str, str2, str3, str4));
        this.mSpHelper.updateInfoToSp(DownloadConstants.SP_NAME_UNINSTALLED_APP, "key_uninstalled_list", this.mUnInstalledInfoList);
    }

    private void showBackInstallDialog(final Context context, final AppInfo appInfo, final OnExitClickListener onExitClickListener, boolean z) throws JSONException {
        final NativeDownloadModel nativeDownloadModel = ModelManager.getInstance().getNativeDownloadModel(appInfo.mAdId);
        if (nativeDownloadModel == null) {
            TTDownloaderMonitor.inst().monitorDataError("showBackInstallDialog nativeModel null");
            return;
        }
        DownloadUIFactory downloadUIFactory = GlobalInfo.getDownloadUIFactory();
        DownloadAlertDialogInfo.Builder title = new DownloadAlertDialogInfo.Builder(context).setTitle(z ? "应用安装确认" : "退出确认");
        Object[] objArr = new Object[1];
        objArr[0] = TextUtils.isEmpty(appInfo.mAppName) ? "刚刚下载的应用" : appInfo.mAppName;
        downloadUIFactory.showAlertDialog(title.setMessage(String.format("%1$s下载完成，是否立即安装？", objArr)).setPositiveBtnText("立即安装").setNegativeBtnText(z ? "暂不安装" : String.format("退出%1$s", context.getResources().getString(context.getApplicationContext().getApplicationInfo().labelRes))).setCancelableOnTouchOutside(false).setIcon(ToolUtils.getApkIconFromApk(context, appInfo.mFileName)).setDialogStatusChangedListener(new DownloadAlertDialogInfo.OnDialogStatusChangedListener() { // from class: com.ss.android.downloadlib.addownload.dialog.AdDownloadDialogManager.4
            @Override // com.ss.android.download.api.model.DownloadAlertDialogInfo.OnDialogStatusChangedListener
            public void onPositiveBtnClick(DialogInterface dialogInterface) {
                AdEventHandler.getInstance().sendEvent(EventConstants.Label.BACK_DIALOG_INSTALL, nativeDownloadModel);
                AppDownloader.startInstall(context, (int) appInfo.mDownloadId);
                dialogInterface.dismiss();
            }

            @Override // com.ss.android.download.api.model.DownloadAlertDialogInfo.OnDialogStatusChangedListener
            public void onNegativeBtnClick(DialogInterface dialogInterface) {
                AdEventHandler.getInstance().sendEvent(EventConstants.Label.BACK_DIALOG_EXIT, nativeDownloadModel);
                if (onExitClickListener != null) {
                    onExitClickListener.onExitClick();
                }
                AdDownloadDialogManager.this.resetReadyInstallPackage("");
                dialogInterface.dismiss();
            }

            @Override // com.ss.android.download.api.model.DownloadAlertDialogInfo.OnDialogStatusChangedListener
            public void onCancel(DialogInterface dialogInterface) {
                AdDownloadDialogManager.this.resetReadyInstallPackage("");
            }
        }).setScene(1).build());
        AdEventHandler.getInstance().sendEvent(EventConstants.Label.BACK_DIALOG_SHOW, nativeDownloadModel);
        this.mReadyInstallPackage = appInfo.mPackageName;
    }

    public boolean isPackageReadyToInstall(String str) {
        return TextUtils.equals(this.mReadyInstallPackage, str);
    }

    public void resetReadyInstallPackage(String str) {
        if (TextUtils.isEmpty(str)) {
            this.mReadyInstallPackage = "";
        } else if (TextUtils.equals(this.mReadyInstallPackage, str)) {
            this.mReadyInstallPackage = "";
        }
    }

    public void tryShowOpenAppDialog(NativeDownloadModel nativeDownloadModel) {
        if (GlobalInfo.getDownloadSettings().optInt(DownloadSettingKeys.KEY_ENABLE_OPEN_APP_DIALOG, 0) == 1 && !nativeDownloadModel.isShowAppDialog() && nativeDownloadModel.enableNewActivity()) {
            nativeDownloadModel.setIsShowAppDialog(true);
            TTDelegateActivity.showOpenAppDialog(nativeDownloadModel);
        }
    }
}
