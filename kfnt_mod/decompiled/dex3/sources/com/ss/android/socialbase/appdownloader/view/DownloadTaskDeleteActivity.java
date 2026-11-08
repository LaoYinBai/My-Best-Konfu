package com.ss.android.socialbase.appdownloader.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.Nullable;
import com.ss.android.socialbase.appdownloader.AppDownloader;
import com.ss.android.socialbase.appdownloader.ResourceUtils;
import com.ss.android.socialbase.appdownloader.constants.Constants;
import com.ss.android.socialbase.appdownloader.depend.IAppDownloadDepend;
import com.ss.android.socialbase.appdownloader.depend.IAppDownloadEventHandler;
import com.ss.android.socialbase.appdownloader.depend.IDownloadAlertDialog;
import com.ss.android.socialbase.appdownloader.depend.IDownloadAlertDialogBuilder;
import com.ss.android.socialbase.appdownloader.impls.DefaultAlertDialogBuilder;
import com.ss.android.socialbase.downloader.depend.IDownloadNotificationEventListener;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DownloadTaskDeleteActivity extends Activity {
    private Intent mCurrentIntent;
    private IDownloadAlertDialog mDialog;

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override // android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        initWindowSetting();
    }

    private void initWindowSetting() {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.alpha = 0.0f;
        window.setAttributes(attributes);
    }

    @Override // android.app.Activity
    protected void onResume() throws Resources.NotFoundException {
        super.onResume();
        this.mCurrentIntent = getIntent();
        showDialog();
        if (this.mDialog != null && !this.mDialog.isShowing()) {
            this.mDialog.show();
        } else if (this.mDialog == null) {
            finish();
        }
    }

    private void showDialog() throws Resources.NotFoundException {
        if (this.mDialog != null || this.mCurrentIntent == null) {
            return;
        }
        try {
            final boolean z = false;
            final int intExtra = this.mCurrentIntent.getIntExtra(Constants.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS, 0);
            final DownloadInfo downloadInfo = Downloader.getInstance(getApplicationContext()).getDownloadInfo(intExtra);
            if (downloadInfo == null) {
                return;
            }
            String title = downloadInfo.getTitle();
            if (TextUtils.isEmpty(title)) {
                Log.w("DeleteActivity", "Missing appName; skipping handle");
                return;
            }
            String string = String.format(getString(ResourceUtils.getStringId(this, "tt_appdownloader_notification_download_delete")), title);
            IAppDownloadDepend appDownloadDepend = AppDownloader.getInstance().getAppDownloadDepend();
            IDownloadAlertDialogBuilder themedAlertDlgBuilder = appDownloadDepend != null ? appDownloadDepend.getThemedAlertDlgBuilder(this) : null;
            if (themedAlertDlgBuilder == null) {
                themedAlertDlgBuilder = new DefaultAlertDialogBuilder(this);
            }
            if (themedAlertDlgBuilder != null) {
                int stringId = ResourceUtils.getStringId(this, "tt_appdownloader_tip");
                int stringId2 = ResourceUtils.getStringId(this, "tt_appdownloader_label_ok");
                int stringId3 = ResourceUtils.getStringId(this, "tt_appdownloader_label_cancel");
                if (DownloadSetting.obtain(downloadInfo.getId()).optInt(DownloadSettingKeys.CANCEL_WITH_NET_OPT, 0) == 1 && DownloadUtils.isNoWifiAndInNet() && downloadInfo.getCurBytes() != downloadInfo.getTotalBytes()) {
                    z = true;
                }
                if (z) {
                    stringId2 = ResourceUtils.getStringId(this, "tt_appdownloader_label_reserve_wifi");
                    stringId3 = ResourceUtils.getStringId(this, "tt_appdownloader_label_cancel_directly");
                    string = getResources().getString(ResourceUtils.getStringId(this, "tt_appdownloader_resume_in_wifi"));
                }
                themedAlertDlgBuilder.setTitle(stringId).setMessage(string).setPositiveButton(stringId2, new DialogInterface.OnClickListener() { // from class: com.ss.android.socialbase.appdownloader.view.DownloadTaskDeleteActivity.3
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!z) {
                            DownloadTaskDeleteActivity.this.cancelDownloadTask(downloadInfo, intExtra);
                        } else {
                            downloadInfo.setOnlyWifi(true);
                            Downloader.getInstance(DownloadTaskDeleteActivity.this).pause(downloadInfo.getId());
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.ss.android.socialbase.appdownloader.view.DownloadTaskDeleteActivity.3.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    Downloader.getInstance(DownloadTaskDeleteActivity.this).resume(downloadInfo.getId());
                                }
                            }, 100L);
                        }
                        DownloadTaskDeleteActivity.this.finish();
                    }
                }).setNegativeButton(stringId3, new DialogInterface.OnClickListener() { // from class: com.ss.android.socialbase.appdownloader.view.DownloadTaskDeleteActivity.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (z) {
                            DownloadTaskDeleteActivity.this.cancelDownloadTask(downloadInfo, intExtra);
                        }
                        DownloadTaskDeleteActivity.this.finish();
                    }
                }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.ss.android.socialbase.appdownloader.view.DownloadTaskDeleteActivity.1
                    @Override // android.content.DialogInterface.OnCancelListener
                    public void onCancel(DialogInterface dialogInterface) {
                        DownloadTaskDeleteActivity.this.finish();
                    }
                });
                this.mDialog = themedAlertDlgBuilder.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelDownloadTask(DownloadInfo downloadInfo, int i) {
        IAppDownloadEventHandler appDownloadEventHandler = AppDownloader.getInstance().getAppDownloadEventHandler();
        if (appDownloadEventHandler != null) {
            appDownloadEventHandler.handleDownloadCancel(downloadInfo);
        }
        IDownloadNotificationEventListener downloadNotificationEventListener = Downloader.getInstance(DownloadComponentManager.getAppContext()).getDownloadNotificationEventListener(i);
        if (downloadNotificationEventListener != null) {
            downloadNotificationEventListener.onNotificationEvent(10, downloadInfo, "", "");
        }
        if (DownloadComponentManager.getAppContext() != null) {
            Downloader.getInstance(DownloadComponentManager.getAppContext()).cancel(i);
        }
    }
}
