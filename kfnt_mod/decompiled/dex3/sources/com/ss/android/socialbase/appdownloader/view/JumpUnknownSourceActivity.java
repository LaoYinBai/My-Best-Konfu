package com.ss.android.socialbase.appdownloader.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.Nullable;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.socialbase.appdownloader.AhUtils;
import com.ss.android.socialbase.appdownloader.AppDownloadUtils;
import com.ss.android.socialbase.appdownloader.AppDownloader;
import com.ss.android.socialbase.appdownloader.InstallQueue;
import com.ss.android.socialbase.appdownloader.ResourceUtils;
import com.ss.android.socialbase.appdownloader.constants.Constants;
import com.ss.android.socialbase.appdownloader.depend.IAppDownloadDepend;
import com.ss.android.socialbase.appdownloader.depend.IDownloadAlertDialog;
import com.ss.android.socialbase.appdownloader.depend.IDownloadAlertDialogBuilder;
import com.ss.android.socialbase.appdownloader.impls.DefaultAlertDialogBuilder;
import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class JumpUnknownSourceActivity extends Activity {
    private JSONObject mConfig;
    private Intent mCurrentIntent;
    private IDownloadAlertDialog mDialog;
    private int mId;

    @Nullable
    private Intent mOriginIntent;

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        InstallQueue.getInstance().pushActivity(this);
    }

    @Override // android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        initWindowSetting();
        InstallQueue.getInstance().pushActivity(this);
    }

    private void initWindowSetting() {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.alpha = 0.0f;
        window.setAttributes(attributes);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        this.mCurrentIntent = intent;
        if (intent != null) {
            this.mOriginIntent = (Intent) intent.getParcelableExtra(Constants.INTENT);
            this.mId = intent.getIntExtra(AdDownloadModel.JsonKey.ID, -1);
            try {
                this.mConfig = new JSONObject(intent.getStringExtra("config"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (this.mConfig == null) {
            AppDownloadUtils.safeFinish(this);
            return;
        }
        showDialog();
        if (this.mDialog != null && !this.mDialog.isShowing()) {
            this.mDialog.show();
        } else if (this.mDialog == null) {
            finish();
        }
    }

    private void showDialog() {
        if (this.mDialog != null || this.mCurrentIntent == null) {
            return;
        }
        try {
            IAppDownloadDepend appDownloadDepend = AppDownloader.getInstance().getAppDownloadDepend();
            IDownloadAlertDialogBuilder themedAlertDlgBuilder = appDownloadDepend != null ? appDownloadDepend.getThemedAlertDlgBuilder(this) : null;
            if (themedAlertDlgBuilder == null) {
                themedAlertDlgBuilder = new DefaultAlertDialogBuilder(this);
            }
            int stringId = ResourceUtils.getStringId(this, "tt_appdownloader_tip");
            int stringId2 = ResourceUtils.getStringId(this, "tt_appdownloader_label_ok");
            int stringId3 = ResourceUtils.getStringId(this, "tt_appdownloader_label_cancel");
            String strOptString = this.mConfig.optString(DownloadSettingKeys.AhPlans.KEY_JUMP_UNKNOWN_SOURCE_TIPS);
            if (TextUtils.isEmpty(strOptString)) {
                strOptString = getString(ResourceUtils.getStringId(this, "tt_appdownloader_jump_unknown_source_tips"));
            }
            themedAlertDlgBuilder.setTitle(stringId).setMessage(strOptString).setPositiveButton(stringId2, new DialogInterface.OnClickListener() { // from class: com.ss.android.socialbase.appdownloader.view.JumpUnknownSourceActivity.3
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) throws JSONException {
                    if (!AhUtils.realJumpUnknownSource(JumpUnknownSourceActivity.this, JumpUnknownSourceActivity.this.mOriginIntent, JumpUnknownSourceActivity.this.mId, JumpUnknownSourceActivity.this.mConfig)) {
                        AhUtils.tryStartActivity(JumpUnknownSourceActivity.this, JumpUnknownSourceActivity.this.mOriginIntent, true);
                    } else {
                        AhUtils.sendGuideAuthOpenSettingEvent(JumpUnknownSourceActivity.this.mId, JumpUnknownSourceActivity.this.mConfig);
                    }
                    AhUtils.sendGuideAuthDialogConfirmEvent(JumpUnknownSourceActivity.this.mId, JumpUnknownSourceActivity.this.mConfig);
                    JumpUnknownSourceActivity.this.finish();
                }
            }).setNegativeButton(stringId3, new DialogInterface.OnClickListener() { // from class: com.ss.android.socialbase.appdownloader.view.JumpUnknownSourceActivity.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) throws JSONException {
                    if (JumpUnknownSourceActivity.this.mOriginIntent != null) {
                        AhUtils.tryStartActivity(JumpUnknownSourceActivity.this, JumpUnknownSourceActivity.this.mOriginIntent, true);
                    }
                    AhUtils.sendGuideAuthDialogCancelEvent(JumpUnknownSourceActivity.this.mId, JumpUnknownSourceActivity.this.mConfig);
                    JumpUnknownSourceActivity.this.finish();
                }
            }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.ss.android.socialbase.appdownloader.view.JumpUnknownSourceActivity.1
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) throws JSONException {
                    if (JumpUnknownSourceActivity.this.mOriginIntent != null) {
                        AhUtils.tryStartActivity(JumpUnknownSourceActivity.this, JumpUnknownSourceActivity.this.mOriginIntent, true);
                    }
                    AhUtils.sendGuideAuthDialogCancelEvent(JumpUnknownSourceActivity.this.mId, JumpUnknownSourceActivity.this.mConfig);
                    JumpUnknownSourceActivity.this.finish();
                }
            }).setCanceledOnTouchOutside(false);
            this.mDialog = themedAlertDlgBuilder.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
