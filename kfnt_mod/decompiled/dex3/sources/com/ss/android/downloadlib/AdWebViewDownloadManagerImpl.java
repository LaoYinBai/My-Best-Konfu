package com.ss.android.downloadlib;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.ss.android.download.api.download.DownloadController;
import com.ss.android.download.api.download.DownloadEventConfig;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.download.api.download.DownloadStatusChangeListener;
import com.ss.android.download.api.download.SimpleDownloadController;
import com.ss.android.download.api.download.SimpleDownloadEventConfig;
import com.ss.android.download.api.model.DownloadAlertDialogInfo;
import com.ss.android.download.api.utils.MarketUriUtils;
import com.ss.android.downloadad.api.AdWebViewDownloadManager;
import com.ss.android.downloadad.api.download.AdDownloadController;
import com.ss.android.downloadad.api.download.AdDownloadEventConfig;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.addownload.compliance.AdLpComplianceManager;
import com.ss.android.downloadlib.addownload.model.ModelBox;
import com.ss.android.downloadlib.addownload.model.ModelManager;
import com.ss.android.downloadlib.addownload.model.OpenAppResult;
import com.ss.android.downloadlib.applink.AdAppLinkUtils;
import com.ss.android.downloadlib.constants.DownloadSettingKeys;
import com.ss.android.downloadlib.constants.EventConstants;
import com.ss.android.downloadlib.event.AdEventHandler;
import com.ss.android.downloadlib.exception.Safe;
import com.ss.android.downloadlib.utils.OpenAppUtils;
import com.ss.android.downloadlib.utils.TLogger;
import com.ss.android.downloadlib.utils.ToolUtils;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AdWebViewDownloadManagerImpl implements AdWebViewDownloadManager {
    private static String TAG = "AdWebViewDownloadManagerImpl";
    private static volatile AdWebViewDownloadManagerImpl sInstance;
    private TTDownloader mTTDownloader = TTDownloader.inst(GlobalInfo.getContext());

    private AdWebViewDownloadManagerImpl() {
    }

    public static AdWebViewDownloadManagerImpl inst() {
        if (sInstance == null) {
            synchronized (AdWebViewDownloadManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new AdWebViewDownloadManagerImpl();
                }
            }
        }
        return sInstance;
    }

    @Override // com.ss.android.downloadad.api.AdWebViewDownloadManager
    public Dialog tryStartDownload(Context context, String str, boolean z, @NonNull DownloadModel downloadModel, DownloadEventConfig downloadEventConfig, DownloadController downloadController, DownloadStatusChangeListener downloadStatusChangeListener, int i) {
        return tryStartDownload(context, str, z, downloadModel, downloadEventConfig, downloadController, downloadStatusChangeListener, i, false);
    }

    @Override // com.ss.android.downloadad.api.AdWebViewDownloadManager
    public Dialog tryStartDownload(Context context, String str, boolean z, @NonNull DownloadModel downloadModel, DownloadStatusChangeListener downloadStatusChangeListener, int i) {
        return tryStartDownload(context, str, z, downloadModel, null, null, downloadStatusChangeListener, i);
    }

    @Override // com.ss.android.downloadad.api.AdWebViewDownloadManager
    public Dialog tryStartDownload(final Context context, final String str, final boolean z, @NonNull final DownloadModel downloadModel, final DownloadEventConfig downloadEventConfig, final DownloadController downloadController, final DownloadStatusChangeListener downloadStatusChangeListener, final int i, final boolean z2) {
        return (Dialog) Safe.noCatch(new Safe.Run<Dialog>() { // from class: com.ss.android.downloadlib.AdWebViewDownloadManagerImpl.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.ss.android.downloadlib.exception.Safe.Run
            public Dialog run() {
                return AdWebViewDownloadManagerImpl.this.innerTryStartDownload(context, str, z, downloadModel, downloadEventConfig, downloadController, downloadStatusChangeListener, i, z2);
            }
        });
    }

    public Dialog innerTryStartDownload(Context context, String str, boolean z, final DownloadModel downloadModel, DownloadEventConfig downloadEventConfig, DownloadController downloadController, DownloadStatusChangeListener downloadStatusChangeListener, int i, boolean z2) {
        if (isDownloadInfoExisted(downloadModel.getId())) {
            if (z2) {
                action(downloadModel.getId(), downloadEventConfig, downloadController);
            } else {
                action(downloadModel.getId());
            }
            return null;
        }
        if (context == null || TextUtils.isEmpty(downloadModel.getDownloadUrl())) {
            return null;
        }
        this.mTTDownloader.bind(context, i, downloadStatusChangeListener, downloadModel);
        final DownloadEventConfig downloadEventConfig2 = (DownloadEventConfig) ToolUtils.getNonNull(downloadEventConfig, createDownloadEventConfigure());
        final DownloadController downloadController2 = (DownloadController) ToolUtils.getNonNull(downloadController, createDownloadController());
        downloadEventConfig2.setDownloadScene(1);
        if ((downloadController2.enableShowComplianceDialog() && AdLpComplianceManager.getInstance().shouldShowDialog(downloadModel)) ? true : z | (GlobalInfo.getDownloadSettings().optInt(DownloadSettingKeys.DISABLE_LP_DIALOG, 0) == 1)) {
            this.mTTDownloader.action(downloadModel.getDownloadUrl(), downloadModel.getId(), 2, downloadEventConfig2, downloadController2);
            return null;
        }
        TLogger.v(TAG, "tryStartDownload show dialog appName:" + downloadModel.getDownloadUrl(), null);
        Dialog dialogShowAlertDialog = GlobalInfo.getDownloadUIFactory().showAlertDialog(new DownloadAlertDialogInfo.Builder(context).setTitle(downloadModel.getName()).setMessage("确认要下载此应用吗？").setPositiveBtnText("确认").setNegativeBtnText("取消").setDialogStatusChangedListener(new DownloadAlertDialogInfo.OnDialogStatusChangedListener() { // from class: com.ss.android.downloadlib.AdWebViewDownloadManagerImpl.2
            @Override // com.ss.android.download.api.model.DownloadAlertDialogInfo.OnDialogStatusChangedListener
            public void onPositiveBtnClick(DialogInterface dialogInterface) {
                AdWebViewDownloadManagerImpl.this.mTTDownloader.action(downloadModel.getDownloadUrl(), downloadModel.getId(), 2, downloadEventConfig2, downloadController2);
                AdEventHandler.getInstance().sendEvent(EventConstants.Label.LP_DOWNLOAD_DIALOG_CONFIRM, downloadModel, downloadEventConfig2, downloadController2);
                dialogInterface.dismiss();
            }

            @Override // com.ss.android.download.api.model.DownloadAlertDialogInfo.OnDialogStatusChangedListener
            public void onNegativeBtnClick(DialogInterface dialogInterface) {
                AdEventHandler.getInstance().sendEvent(EventConstants.Label.LP_DOWNLOAD_DIALOG_CANCEL, downloadModel, downloadEventConfig2, downloadController2);
                dialogInterface.dismiss();
            }

            @Override // com.ss.android.download.api.model.DownloadAlertDialogInfo.OnDialogStatusChangedListener
            public void onCancel(DialogInterface dialogInterface) {
                AdEventHandler.getInstance().sendEvent(EventConstants.Label.LP_DOWNLOAD_DIALOG_CANCEL, downloadModel, downloadEventConfig2, downloadController2);
            }
        }).setScene(0).build());
        AdEventHandler.getInstance().sendEvent(EventConstants.Label.LP_DOWNLOAD_DIALOG_SHOW, downloadModel, downloadEventConfig2, downloadController2);
        return dialogShowAlertDialog;
    }

    @Override // com.ss.android.downloadad.api.AdWebViewDownloadManager
    public boolean bind(Context context, long j, String str, DownloadStatusChangeListener downloadStatusChangeListener, int i) {
        NativeDownloadModel nativeDownloadModel = ModelManager.getInstance().getNativeDownloadModel(j);
        if (nativeDownloadModel != null) {
            this.mTTDownloader.bind(context, i, downloadStatusChangeListener, nativeDownloadModel.generateDownloadModel());
            return true;
        }
        DownloadModel downloadModel = ModelManager.getInstance().getDownloadModel(j);
        if (downloadModel == null) {
            return false;
        }
        this.mTTDownloader.bind(context, i, downloadStatusChangeListener, downloadModel);
        return true;
    }

    @Override // com.ss.android.downloadad.api.AdWebViewDownloadManager
    public boolean unbind(long j, int i) {
        DownloadModel downloadModel = ModelManager.getInstance().getDownloadModel(j);
        if (downloadModel == null) {
            return false;
        }
        this.mTTDownloader.unbind(downloadModel.getDownloadUrl(), i);
        return true;
    }

    @Override // com.ss.android.downloadad.api.AdWebViewDownloadManager
    public void action(long j, DownloadEventConfig downloadEventConfig, DownloadController downloadController) {
        DownloadModel downloadModel = ModelManager.getInstance().getDownloadModel(j);
        NativeDownloadModel nativeDownloadModel = ModelManager.getInstance().getNativeDownloadModel(j);
        if (downloadModel == null && nativeDownloadModel != null) {
            downloadModel = nativeDownloadModel.generateDownloadModel();
        }
        if (downloadModel == null) {
            return;
        }
        if (downloadEventConfig == null || downloadController == null || (downloadEventConfig instanceof SimpleDownloadEventConfig) || (downloadController instanceof SimpleDownloadController)) {
            action(j);
        } else {
            downloadEventConfig.setDownloadScene(1);
            this.mTTDownloader.action(downloadModel.getDownloadUrl(), j, 2, downloadEventConfig, downloadController);
        }
    }

    @Override // com.ss.android.downloadad.api.AdWebViewDownloadManager
    public void action(long j) {
        DownloadModel downloadModel = ModelManager.getInstance().getDownloadModel(j);
        NativeDownloadModel nativeDownloadModel = ModelManager.getInstance().getNativeDownloadModel(j);
        if (downloadModel == null && nativeDownloadModel != null) {
            downloadModel = nativeDownloadModel.generateDownloadModel();
        }
        if (downloadModel == null) {
            return;
        }
        DownloadEventConfig downloadEventConfig = ModelManager.getInstance().getDownloadEventConfig(j);
        DownloadController downloadController = ModelManager.getInstance().getDownloadController(j);
        if (downloadEventConfig instanceof SimpleDownloadEventConfig) {
            downloadEventConfig = null;
        }
        if (downloadController instanceof SimpleDownloadController) {
            downloadController = null;
        }
        if (nativeDownloadModel == null) {
            if (downloadEventConfig == null) {
                downloadEventConfig = createDownloadEventConfigure();
            }
            if (downloadController == null) {
                downloadController = createDownloadController();
            }
        } else {
            if (downloadEventConfig == null) {
                downloadEventConfig = new AdDownloadEventConfig.Builder().setClickButtonTag(nativeDownloadModel.getEventTag()).setRefer(nativeDownloadModel.getEventRefer()).setIsEnableV3Event(nativeDownloadModel.isV3Event()).setIsEnableClickEvent(false).setClickStartLabel("click_start_detail").setClickPauseLabel("click_pause_detail").setClickContinueLabel("click_continue_detail").setClickInstallLabel("click_install_detail").setStorageDenyLabel("storage_deny_detail").build();
            }
            if (downloadController == null) {
                downloadController = nativeDownloadModel.generateDownloadController();
            }
        }
        DownloadEventConfig downloadEventConfig2 = downloadEventConfig;
        DownloadController downloadController2 = downloadController;
        downloadEventConfig2.setDownloadScene(1);
        this.mTTDownloader.action(downloadModel.getDownloadUrl(), j, 2, downloadEventConfig2, downloadController2);
    }

    @Override // com.ss.android.downloadad.api.AdWebViewDownloadManager
    public boolean isDownloadInfoExisted(long j) {
        return (ModelManager.getInstance().getDownloadModel(j) == null && ModelManager.getInstance().getNativeDownloadModel(j) == null) ? false : true;
    }

    @Override // com.ss.android.downloadad.api.AdWebViewDownloadManager
    public boolean tryOpenMarket(final Context context, final Uri uri, final DownloadModel downloadModel, final DownloadEventConfig downloadEventConfig, final DownloadController downloadController) {
        return ((Boolean) Safe.noCatch(new Safe.Run<Boolean>() { // from class: com.ss.android.downloadlib.AdWebViewDownloadManagerImpl.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.ss.android.downloadlib.exception.Safe.Run
            public Boolean run() {
                return Boolean.valueOf(AdWebViewDownloadManagerImpl.this.innerTryOpenMarket(context, uri, downloadModel, downloadEventConfig, downloadController));
            }
        })).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean innerTryOpenMarket(Context context, Uri uri, DownloadModel downloadModel, DownloadEventConfig downloadEventConfig, DownloadController downloadController) throws JSONException {
        DownloadController downloadControllerCreateDownloadController = downloadController;
        if (!MarketUriUtils.isMarketUri(uri) || GlobalInfo.getDownloadSettings().optInt(DownloadSettingKeys.KEY_DISABLE_MARKET) == 1) {
            return false;
        }
        Context context2 = context == null ? GlobalInfo.getContext() : context;
        String packageNameFromUri = MarketUriUtils.getPackageNameFromUri(uri);
        if (downloadModel == null) {
            return OpenAppUtils.tryOpenMarket(context2, packageNameFromUri).getType() == 5;
        }
        if (!TextUtils.isEmpty(packageNameFromUri) && (downloadModel instanceof AdDownloadModel)) {
            ((AdDownloadModel) downloadModel).setPackageName(packageNameFromUri);
        }
        if (downloadControllerCreateDownloadController != null) {
            downloadControllerCreateDownloadController.setDownloadMode(2);
        } else if ((downloadModel instanceof AdDownloadModel) && TextUtils.isEmpty(downloadModel.getDownloadUrl())) {
            ((AdDownloadModel) downloadModel).setDownloadUrl(uri.toString());
            downloadControllerCreateDownloadController = createDownloadController(true);
        } else if (downloadModel.getDownloadUrl().startsWith("market")) {
            downloadControllerCreateDownloadController = createDownloadController(true);
        } else {
            downloadControllerCreateDownloadController = createDownloadController();
        }
        ModelBox modelBox = new ModelBox(downloadModel.getId(), downloadModel, (DownloadEventConfig) ToolUtils.getNonNull(downloadEventConfig, createDownloadEventConfigure()), downloadControllerCreateDownloadController);
        ModelManager.getInstance().addDownloadModel(modelBox.model);
        ModelManager.getInstance().addDownloadEventConfig(modelBox.id, modelBox.event);
        ModelManager.getInstance().addDownloadController(modelBox.id, modelBox.controller);
        if (ToolUtils.isInstalledApp(downloadModel) && DownloadSetting.obtainGlobal().optInt(DownloadSettingKeys.APP_LINK_OPT) == 1 && AdAppLinkUtils.tryAppLinkWhenClick(modelBox)) {
            return true;
        }
        JSONObject jSONObject = new JSONObject();
        ToolUtils.safePut(jSONObject, EventConstants.ExtraJson.MARKET_URL, uri.toString());
        ToolUtils.safePut(jSONObject, "download_scene", 1);
        AdEventHandler.getInstance().sendEvent(EventConstants.Label.MARKET_CLICK_OPEN, jSONObject, modelBox);
        OpenAppResult openAppResultTryOpenMarket = OpenAppUtils.tryOpenMarket(context2, modelBox, packageNameFromUri);
        String notEmptyStr = ToolUtils.getNotEmptyStr(openAppResultTryOpenMarket.getSource(), EventConstants.AppLinkSource.OPEN_MARKET);
        if (openAppResultTryOpenMarket.getType() == 5) {
            AdAppLinkUtils.onMarketSuccess(notEmptyStr, jSONObject, modelBox, true);
            return true;
        }
        if (openAppResultTryOpenMarket.getType() != 6) {
            return true;
        }
        ToolUtils.safePut(jSONObject, "error_code", Integer.valueOf(openAppResultTryOpenMarket.getMessage()));
        AdEventHandler.getInstance().sendEvent(EventConstants.Label.MARKET_OPEN_FAILED, jSONObject, modelBox);
        return false;
    }

    @Override // com.ss.android.downloadad.api.AdWebViewDownloadManager
    public boolean tryOpenMarket(Context context, Uri uri, DownloadModel downloadModel) {
        return tryOpenMarket(context, uri, downloadModel, null, null);
    }

    public static DownloadController createDownloadController() {
        return createDownloadController(false);
    }

    public static DownloadController createDownloadController(boolean z) {
        AdDownloadController.Builder shouldUseNewWebView = new AdDownloadController.Builder().setLinkMode(0).setIsEnableBackDialog(true).setIsEnableMultipleDownload(false).setShouldUseNewWebView(false);
        if (z) {
            shouldUseNewWebView.setDownloadMode(2);
        } else {
            shouldUseNewWebView.setDownloadMode(0);
        }
        return shouldUseNewWebView.build();
    }

    public static DownloadEventConfig createDownloadEventConfigure() {
        return new AdDownloadEventConfig.Builder().setClickButtonTag(EventConstants.Tag.LANDING_H5_DOWNLOAD_AD_BUTTON).setClickItemTag(EventConstants.Tag.LANDING_H5_DOWNLOAD_AD_BUTTON).setClickStartLabel("click_start_detail").setClickPauseLabel("click_pause_detail").setClickContinueLabel("click_continue_detail").setClickInstallLabel("click_install_detail").setClickOpenLabel("click_open_detail").setStorageDenyLabel("storage_deny_detail").setDownloadScene(1).setIsEnableClickEvent(false).setIsEnableNoChargeClickEvent(true).setIsEnableV3Event(false).build();
    }
}
