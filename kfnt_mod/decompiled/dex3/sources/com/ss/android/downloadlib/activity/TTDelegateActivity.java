package com.ss.android.downloadlib.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import com.ss.android.download.api.config.DownloadUIFactory;
import com.ss.android.download.api.config.IPermissionCallback;
import com.ss.android.download.api.constant.BaseConstants;
import com.ss.android.download.api.model.DownloadAlertDialogInfo;
import com.ss.android.downloadad.api.model.InnerUnifyData;
import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.TTDownloader;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.addownload.ReverseWifiHelper;
import com.ss.android.downloadlib.addownload.compliance.AdLpAppInfoDialog;
import com.ss.android.downloadlib.addownload.dialog.SelectOperationDialog;
import com.ss.android.downloadlib.addownload.model.ModelManager;
import com.ss.android.downloadlib.addownload.pause.ApkSizeInterceptor;
import com.ss.android.downloadlib.addownload.pause.DownloadPercentInterceptor;
import com.ss.android.downloadlib.applink.AdAppLinkUtils;
import com.ss.android.downloadlib.constants.DownloadSettingKeys;
import com.ss.android.downloadlib.constants.EventConstants;
import com.ss.android.downloadlib.event.AdEventHandler;
import com.ss.android.downloadlib.exception.TTDownloaderMonitor;
import com.ss.android.downloadlib.guide.install.IAppInstallCallback;
import com.ss.android.downloadlib.utils.InnerOpenAppUtils;
import com.ss.android.downloadlib.utils.PermissionUtils;
import com.ss.android.downloadlib.utils.ToolUtils;
import com.ss.android.socialbase.appdownloader.AppDownloadUtils;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TTDelegateActivity extends Activity implements ActivityCompat.OnRequestPermissionsResultCallback {
    public static final String APP_INFO_ID = "app_info_id";
    public static final String DOWNLOAD_ID = "download_id";
    public static final String DOWNLOAD_SIZE = "download_size";
    public static final String DOWNLOAD_URL = "download_url";
    public static final String EXT_JSON = "ext_json";
    public static final String INTENT_TYPE = "type";
    public static final String IS_DOWNLOAD = "is_download";
    public static final String MESSAGE_TEXT = "message_text";
    public static final String MODEL_ID = "model_id";
    public static final String NEGATIVE_BUTTON_TEXT = "negative_button_text";
    public static final String OPEN_URL = "open_url";
    public static final String PACKAGE_NAME = "package_name";
    public static final String PARAM = "param";
    public static final String PERMISSION_CONTENT_KEY = "permission_content_key";
    public static final String PERMISSION_ID_KEY = "permission_id_key";
    public static final String POSITIVE_BUTTON_TEXT = "positive_button_text";
    private static IAppInstallCallback sAppInstallCallback;
    protected Intent mCurrentIntent = null;
    private NativeDownloadModel mNativeDownloadModel;
    private boolean mShowPauseOptimiseDialog;

    @Retention(RetentionPolicy.SOURCE)
    public @interface IntentType {
        public static final int AD_LP_APPINFO_DIALOG = 10;
        public static final int APK_INSTALL = 9;
        public static final int APK_SIZE_RETAIN = 8;
        public static final int DOWNLOAD_PERCENT_RETAIN = 7;
        public static final int INSTALL_GUIDE = 6;
        public static final int INTENT_CLEAN_DISK_SPACE = 3;
        public static final int OPEN_APP_DIALOG = 4;
        public static final int OPEN_URL = 2;
        public static final int OPEN_V1_MARKET = 12;
        public static final int PACKAGE_NAME = 11;
        public static final int REQUEST_PERMISSION = 1;
        public static final int REVERSE_WIFI_DIALOG = 5;
    }

    public static void requestPermission(String str, String[] strArr) {
        Intent intent = new Intent(GlobalInfo.getContext(), (Class<?>) TTDelegateActivity.class);
        intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        intent.putExtra("type", 1);
        intent.putExtra(PERMISSION_ID_KEY, str);
        intent.putExtra(PERMISSION_CONTENT_KEY, strArr);
        if (GlobalInfo.getContext() != null) {
            GlobalInfo.getContext().startActivity(intent);
        }
    }

    public static void openApp(String str, InnerUnifyData innerUnifyData) {
        Intent activityIntent = getActivityIntent(innerUnifyData);
        activityIntent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        activityIntent.putExtra("type", 2);
        activityIntent.putExtra("open_url", str);
        if (GlobalInfo.getContext() != null) {
            GlobalInfo.getContext().startActivity(activityIntent);
        }
    }

    public static void openAppByPKG(String str, InnerUnifyData innerUnifyData) {
        Intent activityIntent = getActivityIntent(innerUnifyData);
        activityIntent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        activityIntent.putExtra("type", 11);
        activityIntent.putExtra("package_name", str);
        if (GlobalInfo.getContext() != null) {
            GlobalInfo.getContext().startActivity(activityIntent);
        }
    }

    public static void showOpenAppDialog(InnerUnifyData innerUnifyData) {
        Intent activityIntent = getActivityIntent(innerUnifyData);
        activityIntent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        activityIntent.putExtra("type", 4);
        activityIntent.putExtra("model_id", innerUnifyData.getId());
        if (GlobalInfo.getContext() != null) {
            GlobalInfo.getContext().startActivity(activityIntent);
        }
    }

    public static void installApk(InnerUnifyData innerUnifyData, IAppInstallCallback iAppInstallCallback) {
        Intent activityIntent = getActivityIntent(innerUnifyData);
        activityIntent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        activityIntent.putExtra("type", 9);
        sAppInstallCallback = iAppInstallCallback;
        if (GlobalInfo.getContext() != null) {
            GlobalInfo.getContext().startActivity(activityIntent);
        }
    }

    public static void showAppInfoDialog(long j) {
        Intent intent = new Intent(GlobalInfo.getContext(), (Class<?>) TTDelegateActivity.class);
        intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        intent.putExtra("type", 10);
        intent.putExtra(APP_INFO_ID, j);
        if (GlobalInfo.getContext() != null) {
            GlobalInfo.getContext().startActivity(intent);
        }
    }

    public static void openV1Market(String str, long j, String str2, @NonNull JSONObject jSONObject) {
        Intent intent = new Intent(GlobalInfo.getContext(), (Class<?>) TTDelegateActivity.class);
        intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        intent.putExtra("type", 12);
        intent.putExtra("package_name", str);
        intent.putExtra("model_id", j);
        intent.putExtra(PARAM, str2);
        intent.putExtra(EXT_JSON, jSONObject.toString());
        if (GlobalInfo.getContext() != null) {
            GlobalInfo.getContext().startActivity(intent);
        }
    }

    private static Intent getActivityIntent(@NonNull InnerUnifyData innerUnifyData) {
        return new Intent(GlobalInfo.getContext(), (Class<?>) TTDelegateActivity.class);
    }

    public static void showReverseWifiDialog(@NonNull InnerUnifyData innerUnifyData) {
        showSelectOperationDialog(innerUnifyData, 5, "", "", "");
    }

    public static void showApkSizeRetainDialog(@NonNull InnerUnifyData innerUnifyData, String str, String str2, String str3) {
        showSelectOperationDialog(innerUnifyData, 8, str, str2, str3);
    }

    public static void showDownloadPercentRetainDialog(@NonNull InnerUnifyData innerUnifyData, String str, String str2, String str3) {
        showSelectOperationDialog(innerUnifyData, 7, str, str2, str3);
    }

    private static void showSelectOperationDialog(@NonNull InnerUnifyData innerUnifyData, int i, String str, String str2, String str3) {
        Intent activityIntent = getActivityIntent(innerUnifyData);
        activityIntent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        activityIntent.putExtra("type", i);
        if (!TextUtils.isEmpty(str2)) {
            activityIntent.putExtra(POSITIVE_BUTTON_TEXT, str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            activityIntent.putExtra(NEGATIVE_BUTTON_TEXT, str3);
        }
        if (!TextUtils.isEmpty(str)) {
            activityIntent.putExtra(MESSAGE_TEXT, str);
        }
        activityIntent.putExtra("model_id", innerUnifyData.getId());
        if (GlobalInfo.getContext() != null) {
            GlobalInfo.getContext().startActivity(activityIntent);
        }
    }

    @Override // android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) throws JSONException {
        super.onCreate(bundle);
        initWindowSetting();
        this.mCurrentIntent = getIntent();
        GlobalInfo.makeSureContext(this);
        handleIntent();
    }

    private void initWindowSetting() {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.alpha = 0.0f;
        window.setAttributes(attributes);
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) throws JSONException {
        super.onNewIntent(intent);
        setIntent(intent);
        this.mCurrentIntent = intent;
        GlobalInfo.makeSureContext(this);
        handleIntent();
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        GlobalInfo.getDownloadPermissionChecker().onRequestPermissionsResult(this, i, strArr, iArr);
    }

    @Override // android.app.Activity
    protected void onStop() {
        DownloadInfo downloadInfo;
        super.onStop();
        if (!this.mShowPauseOptimiseDialog || this.mNativeDownloadModel == null || (downloadInfo = TTDownloader.inst(null).getDownloadInfo(this.mNativeDownloadModel.getDownloadUrl())) == null || downloadInfo.getCurBytes() < downloadInfo.getTotalBytes() || isFinishing()) {
            return;
        }
        finish();
    }

    protected void handleIntent() throws JSONException {
        if (this.mCurrentIntent == null) {
            return;
        }
        switch (this.mCurrentIntent.getIntExtra("type", 0)) {
            case 1:
                requestPermissionInner(this.mCurrentIntent.getStringExtra(PERMISSION_ID_KEY), this.mCurrentIntent.getStringArrayExtra(PERMISSION_CONTENT_KEY));
                break;
            case 2:
                openAppInner(this.mCurrentIntent.getStringExtra("open_url"));
                break;
            case 3:
            case 6:
            default:
                AppDownloadUtils.safeFinish(this);
                break;
            case 4:
                showOpenAppDialogInner(this.mCurrentIntent.getLongExtra("model_id", 0L));
                break;
            case 5:
                showReverseWifiDialogInner(this.mCurrentIntent.getLongExtra("model_id", 0L));
                break;
            case 7:
            case 8:
                showSelectOperationDialog();
                break;
            case 9:
                if (sAppInstallCallback != null) {
                    sAppInstallCallback.onInstallApp();
                }
                AppDownloadUtils.safeFinish(this);
                break;
            case 10:
                innerShowAppInfoDialog(this.mCurrentIntent.getLongExtra(APP_INFO_ID, 0L));
                break;
            case 11:
                openAppInnerByPKG(this.mCurrentIntent.getStringExtra("package_name"));
                break;
            case 12:
                InnerOpenAppUtils.realInnerOpenV1Market(this, this.mCurrentIntent.getStringExtra("package_name"), this.mCurrentIntent.getLongExtra("model_id", 0L), this.mCurrentIntent.getStringExtra(PARAM), this.mCurrentIntent.getStringExtra(EXT_JSON));
                AppDownloadUtils.safeFinish(this);
                break;
        }
        this.mCurrentIntent = null;
    }

    private void showSelectOperationDialog() throws JSONException {
        long longExtra = this.mCurrentIntent.getLongExtra("model_id", 0L);
        String stringExtra = this.mCurrentIntent.getStringExtra(MESSAGE_TEXT);
        String stringExtra2 = this.mCurrentIntent.getStringExtra(POSITIVE_BUTTON_TEXT);
        String stringExtra3 = this.mCurrentIntent.getStringExtra(NEGATIVE_BUTTON_TEXT);
        int intExtra = this.mCurrentIntent.getIntExtra("type", 0);
        NativeDownloadModel nativeDownloadModel = ModelManager.getInstance().getNativeDownloadModel(longExtra);
        String str = "";
        SelectOperationDialog.Builder negativeBtnText = new SelectOperationDialog.Builder(this).setCancelableOnTouchOutside(false).setMessage(stringExtra).setPositiveBtnText(stringExtra2).setNegativeBtnText(stringExtra3);
        if (intExtra == 7) {
            if (DownloadPercentInterceptor.getSelectOperationListener() == null) {
                return;
            }
            negativeBtnText.setSelectOperationListener(DownloadPercentInterceptor.getSelectOperationListener());
            negativeBtnText.build().show();
            str = "download_percent";
        } else if (intExtra == 8) {
            if (ApkSizeInterceptor.getSelectOperationListener() == null) {
                return;
            }
            negativeBtnText.setSelectOperationListener(ApkSizeInterceptor.getSelectOperationListener());
            negativeBtnText.build().show();
            str = EventConstants.ExtraJson.APK_SIZE;
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mShowPauseOptimiseDialog = true;
        this.mNativeDownloadModel = nativeDownloadModel;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt(EventConstants.ExtraJson.PAUSE_OPTIMISE_TYPE, str);
            jSONObject.putOpt(EventConstants.ExtraJson.PAUSE_OPTIMISE_ACTION, "show_dialog");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AdEventHandler.getInstance().sendUnityEvent(EventConstants.UnityLabel.PAUSE_OPTIMISE, jSONObject, nativeDownloadModel);
    }

    private void showReverseWifiDialogInner(long j) throws JSONException {
        if (ReverseWifiHelper.getReverseWifiListener() == null) {
            return;
        }
        NativeDownloadModel nativeDownloadModel = ModelManager.getInstance().getNativeDownloadModel(j);
        if (nativeDownloadModel != null) {
            DownloadInfo downloadInfo = Downloader.getInstance(GlobalInfo.getContext()).getDownloadInfo(nativeDownloadModel.getDownloadId());
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.putOpt(EventConstants.ExtraJson.TIME_AFTER_CLICK, Long.valueOf(System.currentTimeMillis() - nativeDownloadModel.getClickDownloadTime()));
                jSONObject.putOpt(EventConstants.ExtraJson.CLICK_DOWNLOAD_SIZE, Long.valueOf(nativeDownloadModel.getClickDownloadSize()));
                if (downloadInfo != null) {
                    jSONObject.putOpt(EventConstants.ExtraJson.DOWNLOAD_LENGTH, Long.valueOf(downloadInfo.getCurBytes()));
                    jSONObject.putOpt("download_percent", Long.valueOf(downloadInfo.getCurBytes() / downloadInfo.getTotalBytes()));
                    jSONObject.putOpt(EventConstants.ExtraJson.DOWNLOAD_APK_SIZE, Long.valueOf(downloadInfo.getTotalBytes()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            AdEventHandler.getInstance().sendEvent(EventConstants.Label.PAUSE_RESERVE_WIFI_DIALOG_SHOW, jSONObject, nativeDownloadModel);
        }
        new SelectOperationDialog.Builder(this).setCancelableOnTouchOutside(false).setSelectOperationListener(ReverseWifiHelper.getReverseWifiListener()).build().show();
        this.mShowPauseOptimiseDialog = true;
        this.mNativeDownloadModel = nativeDownloadModel;
    }

    private void requestPermissionInner(final String str, String[] strArr) {
        if (TextUtils.isEmpty(str) || strArr == null || strArr.length <= 0) {
            AppDownloadUtils.safeFinish(this);
            return;
        }
        IPermissionCallback iPermissionCallback = new IPermissionCallback() { // from class: com.ss.android.downloadlib.activity.TTDelegateActivity.1
            private WeakReference<Activity> activityReference;

            {
                this.activityReference = new WeakReference<>(TTDelegateActivity.this);
            }

            @Override // com.ss.android.download.api.config.IPermissionCallback
            public void onGranted() {
                PermissionUtils.handleYes(str);
                AppDownloadUtils.safeFinish(this.activityReference.get());
            }

            @Override // com.ss.android.download.api.config.IPermissionCallback
            public void onDenied(String str2) {
                PermissionUtils.handleNo(str, str2);
                AppDownloadUtils.safeFinish(this.activityReference.get());
            }
        };
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                GlobalInfo.getDownloadPermissionChecker().requestPermission(this, strArr, iPermissionCallback);
                return;
            } catch (Exception e) {
                GlobalInfo.getTTMonitor().monitorException(e, "requestPermission");
                iPermissionCallback.onGranted();
                return;
            }
        }
        iPermissionCallback.onGranted();
    }

    private void openAppInner(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            try {
                Uri uri = Uri.parse(str);
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(uri);
                intent.putExtra("open_url", str);
                intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
                if (DownloadSetting.obtainGlobal().optBugFix(DownloadSettingKeys.BugFix.FIX_APP_LINK_FLAG)) {
                    intent.addFlags(DownloadExpSwitchCode.BACK_BUGFIX_SIGBUS);
                }
                intent.putExtra(BaseConstants.START_ONLY_FOR_ANDROID, true);
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            AppDownloadUtils.safeFinish(this);
        }
    }

    private void openAppInnerByPKG(String str) {
        Intent launchIntentForPackage = ToolUtils.getLaunchIntentForPackage(this, str);
        if (launchIntentForPackage == null) {
            return;
        }
        try {
            try {
                launchIntentForPackage.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
                launchIntentForPackage.putExtra(BaseConstants.START_ONLY_FOR_ANDROID, true);
                startActivity(launchIntentForPackage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            AppDownloadUtils.safeFinish(this);
        }
    }

    private void showOpenAppDialogInner(long j) throws JSONException {
        final NativeDownloadModel nativeDownloadModel = ModelManager.getInstance().getNativeDownloadModel(j);
        if (nativeDownloadModel == null) {
            TTDownloaderMonitor.inst().monitorDataError("showOpenAppDialogInner nativeModel null");
            AppDownloadUtils.safeFinish(this);
            return;
        }
        DownloadUIFactory downloadUIFactory = GlobalInfo.getDownloadUIFactory();
        DownloadAlertDialogInfo.Builder title = new DownloadAlertDialogInfo.Builder(this).setTitle("已安装完成");
        Object[] objArr = new Object[1];
        objArr[0] = TextUtils.isEmpty(nativeDownloadModel.getAppName()) ? "刚刚下载的应用" : nativeDownloadModel.getAppName();
        downloadUIFactory.showAlertDialog(title.setMessage(String.format("%1$s已安装完成，是否立即打开？", objArr)).setPositiveBtnText("打开").setNegativeBtnText("取消").setCancelableOnTouchOutside(false).setIcon(ToolUtils.getAppIcon(this, nativeDownloadModel.getPackageName())).setDialogStatusChangedListener(new DownloadAlertDialogInfo.OnDialogStatusChangedListener() { // from class: com.ss.android.downloadlib.activity.TTDelegateActivity.2
            @Override // com.ss.android.download.api.model.DownloadAlertDialogInfo.OnDialogStatusChangedListener
            public void onPositiveBtnClick(DialogInterface dialogInterface) {
                AdAppLinkUtils.tryAppLinkWhenClickDialog(nativeDownloadModel);
                dialogInterface.dismiss();
                AppDownloadUtils.safeFinish(TTDelegateActivity.this);
            }

            @Override // com.ss.android.download.api.model.DownloadAlertDialogInfo.OnDialogStatusChangedListener
            public void onNegativeBtnClick(DialogInterface dialogInterface) {
                AdEventHandler.getInstance().sendEvent(EventConstants.Label.OPEN_APP_DIALOG_CANCEL, nativeDownloadModel);
                dialogInterface.dismiss();
                AppDownloadUtils.safeFinish(TTDelegateActivity.this);
            }

            @Override // com.ss.android.download.api.model.DownloadAlertDialogInfo.OnDialogStatusChangedListener
            public void onCancel(DialogInterface dialogInterface) {
                AppDownloadUtils.safeFinish(TTDelegateActivity.this);
            }
        }).setScene(2).build());
        AdEventHandler.getInstance().sendEvent(EventConstants.Label.OPEN_APP_DIALOG_SHOW, nativeDownloadModel);
    }

    private void innerShowAppInfoDialog(long j) {
        new AdLpAppInfoDialog(this, j).show();
    }
}
