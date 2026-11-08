package com.ss.android.downloadlib.addownload.compliance;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.addownload.compliance.BitmapCache;
import com.ss.android.downloadlib.addownload.model.AppInfoX;
import com.ss.android.downloadlib.constants.EventConstants;
import com.ss.android.downloadlib.guide.install.ClipImageView;
import com.ss.android.downloadlib.utils.ToolUtils;
import com.ss.android.socialbase.appdownloader.AppDownloadUtils;
import com.ss.union.game.sdk.core.base.R;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AdLpAppInfoDialog extends Dialog {
    private Activity activity;
    private final AppInfoX appInfoX;
    private long cid;
    private final long infoId;
    private ClipImageView ivAppIcon;
    private LinearLayout llDownload;
    private TextView tvAppDetail;
    private TextView tvAppDeveloper;
    private TextView tvAppName;
    private TextView tvAppPrivacy;
    private TextView tvAppVersion;
    private TextView tvGiveUp;

    public AdLpAppInfoDialog(@NonNull Activity activity, long j) {
        super(activity);
        this.activity = activity;
        this.infoId = j;
        this.appInfoX = (AppInfoX) AppInfoCache.getInstance().get(Long.valueOf(j));
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.appInfoX == null) {
            dismiss();
            return;
        }
        requestWindowFeature(1);
        setContentView(R.layout.ttdownloader_dialog_appinfo);
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(R.drawable.ttdownloader_bg_transparent);
        }
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        this.cid = this.appInfoX.cid;
        initView();
        EventSender.sendEvent(EventConstants.Label.LP_APP_DIALOG_SHOW, this.cid);
        setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.ss.android.downloadlib.addownload.compliance.AdLpAppInfoDialog.1
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                EventSender.sendDialogClickEvent(EventConstants.Refer.LP_APP_DIALOG_CANCEL, AdLpAppInfoDialog.this.cid);
            }
        });
    }

    private void initView() {
        this.tvAppName = (TextView) findViewById(R.id.tv_app_name);
        this.tvAppVersion = (TextView) findViewById(R.id.tv_app_version);
        this.tvAppDeveloper = (TextView) findViewById(R.id.tv_app_developer);
        this.tvAppDetail = (TextView) findViewById(R.id.tv_app_detail);
        this.tvAppPrivacy = (TextView) findViewById(R.id.tv_app_privacy);
        this.tvGiveUp = (TextView) findViewById(R.id.tv_give_up);
        this.ivAppIcon = (ClipImageView) findViewById(R.id.iv_app_icon);
        this.llDownload = (LinearLayout) findViewById(R.id.ll_download);
        this.tvAppName.setText(ToolUtils.getNotEmptyStr(this.appInfoX.appName, "--"));
        this.tvAppVersion.setText("版本号：" + ToolUtils.getNotEmptyStr(this.appInfoX.versionName, "--"));
        this.tvAppDeveloper.setText("开发者：" + ToolUtils.getNotEmptyStr(this.appInfoX.developer, "应用信息正在完善中"));
        this.ivAppIcon.setRoundRadius(ToolUtils.dp2px(GlobalInfo.getContext(), 8.0f));
        this.ivAppIcon.setBackgroundColor(Color.parseColor("#EBEBEB"));
        BitmapCache.getInstance().setCallback(this.infoId, new BitmapCache.Callback() { // from class: com.ss.android.downloadlib.addownload.compliance.AdLpAppInfoDialog.2
            @Override // com.ss.android.downloadlib.addownload.compliance.BitmapCache.Callback
            public void loadFinish(Bitmap bitmap) throws JSONException {
                if (bitmap != null) {
                    AdLpAppInfoDialog.this.ivAppIcon.setImageBitmap(bitmap);
                } else {
                    EventSender.sendErrorEvent(8, AdLpAppInfoDialog.this.cid);
                }
            }
        });
        this.tvAppDetail.setOnClickListener(new View.OnClickListener() { // from class: com.ss.android.downloadlib.addownload.compliance.AdLpAppInfoDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AdLpComplianceManager.getInstance().pushDialogActivity(AdLpAppInfoDialog.this.activity);
                AppDetailInfoActivity.start(AdLpAppInfoDialog.this.activity, AdLpAppInfoDialog.this.infoId);
                EventSender.sendDialogClickEvent(EventConstants.Refer.LP_APP_DIALOG_CLICK_DETAIL, AdLpAppInfoDialog.this.cid);
            }
        });
        this.tvAppPrivacy.setOnClickListener(new View.OnClickListener() { // from class: com.ss.android.downloadlib.addownload.compliance.AdLpAppInfoDialog.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AdLpComplianceManager.getInstance().pushDialogActivity(AdLpAppInfoDialog.this.activity);
                AppPrivacyPolicyActivity.start(AdLpAppInfoDialog.this.activity, AdLpAppInfoDialog.this.infoId);
                EventSender.sendDialogClickEvent(EventConstants.Refer.LP_APP_DIALOG_CLICK_PRIVACY, AdLpAppInfoDialog.this.cid);
            }
        });
        this.tvGiveUp.setOnClickListener(new View.OnClickListener() { // from class: com.ss.android.downloadlib.addownload.compliance.AdLpAppInfoDialog.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AdLpAppInfoDialog.this.dismiss();
                EventSender.sendDialogClickEvent(EventConstants.Refer.LP_APP_DIALOG_CLICK_GIVE_UP, AdLpAppInfoDialog.this.cid);
            }
        });
        this.llDownload.setOnClickListener(new View.OnClickListener() { // from class: com.ss.android.downloadlib.addownload.compliance.AdLpAppInfoDialog.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EventSender.sendDialogClickEvent(EventConstants.Refer.LP_APP_DIALOG_CLICK_DOWNLOAD, AdLpAppInfoDialog.this.cid);
                AdLpComplianceManager.getInstance().startDownload(AdLpAppInfoDialog.this.cid);
                AdLpAppInfoDialog.this.dismiss();
            }
        });
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        AppDownloadUtils.safeFinish(this.activity);
    }
}
