package com.ss.android.downloadlib.addownload;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.addownload.model.ModelBox;
import com.ss.android.downloadlib.addownload.model.ModelManager;
import com.ss.android.downloadlib.constants.DownloadSettingKeys;
import com.ss.android.downloadlib.downloader.ApkModifyNameManager;
import com.ss.android.downloadlib.downloader.ApkParseManager;
import com.ss.android.socialbase.appdownloader.AhUtils;
import com.ss.android.socialbase.appdownloader.AppDownloadUtils;
import com.ss.android.socialbase.appdownloader.AppDownloader;
import com.ss.android.socialbase.appdownloader.AppTaskBuilder;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;
import java.io.File;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DownloadInsideHelper {
    public static boolean isAllowDeepLink(int i) {
        return i == 2 || i == 1;
    }

    public static boolean isAllowDeepLinkOnly(int i) {
        return i == 2;
    }

    public static boolean isAllowNormalLink(int i) {
        return i == 0 || i == 1;
    }

    public static boolean isRecommendAd(DownloadModel downloadModel) {
        return downloadModel.isAd() && (downloadModel instanceof AdDownloadModel) && downloadModel.getModelType() == 1;
    }

    public static boolean isGame(DownloadModel downloadModel) {
        return downloadModel != null && downloadModel.getModelType() == 2;
    }

    public static int addDownloadTaskWithNewDownloader(@NonNull ModelBox modelBox, boolean z, AppTaskBuilder appTaskBuilder) {
        int iRedirectSavePathIfPossible;
        if (appTaskBuilder == null || TextUtils.isEmpty(appTaskBuilder.getUrl()) || appTaskBuilder.getContext() == null) {
            return 0;
        }
        try {
            iRedirectSavePathIfPossible = redirectSavePathIfPossible(appTaskBuilder, appTaskBuilder.getUrl());
        } catch (Throwable th) {
            GlobalInfo.getTTMonitor().monitorException(th, "redirectSavePathIfPossible");
            iRedirectSavePathIfPossible = 4;
        }
        appTaskBuilder.setAntiHijackErrorCode(iRedirectSavePathIfPossible);
        if (iRedirectSavePathIfPossible == 0) {
            appTaskBuilder.addDownloadCompleteHandler(new ApkModifyNameManager());
        }
        if (!appTaskBuilder.isAutoInstall()) {
            appTaskBuilder.addDownloadCompleteHandler(new ApkParseManager());
        }
        int iAddDownloadTask = AppDownloader.getInstance().addDownloadTask(appTaskBuilder);
        NativeDownloadModel nativeDownloadModelCreateNativeDownloadModel = createNativeDownloadModel(modelBox, iAddDownloadTask);
        ModelManager.getInstance().putNativeModel(nativeDownloadModelCreateNativeDownloadModel);
        nativeDownloadModelCreateNativeDownloadModel.setDownloadId(iAddDownloadTask);
        nativeDownloadModelCreateNativeDownloadModel.setClickDownloadTime(System.currentTimeMillis());
        nativeDownloadModelCreateNativeDownloadModel.setClickDownloadSize(0L);
        DownloadSetting downloadSettingObtain = DownloadSetting.obtain(appTaskBuilder.getDownloadSetting());
        if (!showUnknownSource(appTaskBuilder, downloadSettingObtain, iAddDownloadTask) && modelBox.model.isShowToast()) {
            String startToast = modelBox.model.getStartToast();
            if (TextUtils.isEmpty(startToast)) {
                startToast = downloadSettingObtain.optString(DownloadSettingKeys.KEY_DOWNLOAD_START_TOAST_TEXT);
            }
            if (TextUtils.isEmpty(startToast)) {
                startToast = z ? "已开始下载，可在\"我的\"里查看管理" : "已开始下载";
            }
            GlobalInfo.getDownloadUIFactory().showToastWithDuration(2, appTaskBuilder.getContext(), modelBox.model, startToast, null, 0);
        }
        return iAddDownloadTask;
    }

    private static NativeDownloadModel createNativeDownloadModel(ModelBox modelBox, int i) {
        NativeDownloadModel nativeDownloadModel = new NativeDownloadModel(modelBox.model, modelBox.event, modelBox.controller, i);
        if (DownloadSetting.obtain(i).optInt(DownloadSettingKeys.KEY_DOWNLOAD_EVENT_OPT, 1) > 1) {
            try {
                String packageName = modelBox.model.getPackageName();
                if (!TextUtils.isEmpty(packageName)) {
                    nativeDownloadModel.setIsUpdateDownload(GlobalInfo.getContext().getPackageManager().getPackageInfo(packageName, 0) != null);
                }
            } catch (Throwable unused) {
            }
        }
        return nativeDownloadModel;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:44:0x008c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean showUnknownSource(com.ss.android.socialbase.appdownloader.AppTaskBuilder r10, @androidx.annotation.NonNull com.ss.android.socialbase.downloader.setting.DownloadSetting r11, int r12) {
        /*
            java.lang.String r10 = "ah_plans"
            org.json.JSONArray r10 = r11.optJSONArray(r10)
            r0 = 0
            if (r10 == 0) goto Lc6
            int r1 = r10.length()
            if (r1 != 0) goto L11
            goto Lc6
        L11:
            int r1 = r10.length()
            r2 = 0
            r4 = r2
            r3 = 0
        L18:
            r5 = 1
            if (r3 >= r1) goto La9
            org.json.JSONObject r6 = r10.optJSONObject(r3)
            if (r6 == 0) goto La5
            java.lang.String r7 = "type"
            java.lang.String r7 = r6.optString(r7)
            java.lang.String r8 = "plan_c"
            if (r7 == r8) goto L33
            boolean r8 = com.ss.android.socialbase.appdownloader.util.AnUtils.checkAnConfig(r6)
            if (r8 != 0) goto L33
            goto La5
        L33:
            r8 = -1
            int r9 = r7.hashCode()
            switch(r9) {
                case -985763637: goto L82;
                case -985763636: goto L78;
                case -985763635: goto L6e;
                case -985763634: goto L64;
                case -985763633: goto L5a;
                case -985763632: goto L50;
                case -985763631: goto L46;
                case -985763630: goto L3c;
                default: goto L3b;
            }
        L3b:
            goto L8c
        L3c:
            java.lang.String r9 = "plan_h"
            boolean r7 = r7.equals(r9)
            if (r7 == 0) goto L8c
            r7 = 5
            goto L8d
        L46:
            java.lang.String r9 = "plan_g"
            boolean r7 = r7.equals(r9)
            if (r7 == 0) goto L8c
            r7 = 6
            goto L8d
        L50:
            java.lang.String r9 = "plan_f"
            boolean r7 = r7.equals(r9)
            if (r7 == 0) goto L8c
            r7 = 3
            goto L8d
        L5a:
            java.lang.String r9 = "plan_e"
            boolean r7 = r7.equals(r9)
            if (r7 == 0) goto L8c
            r7 = 2
            goto L8d
        L64:
            java.lang.String r9 = "plan_d"
            boolean r7 = r7.equals(r9)
            if (r7 == 0) goto L8c
            r7 = 4
            goto L8d
        L6e:
            java.lang.String r9 = "plan_c"
            boolean r7 = r7.equals(r9)
            if (r7 == 0) goto L8c
            r7 = 7
            goto L8d
        L78:
            java.lang.String r9 = "plan_b"
            boolean r7 = r7.equals(r9)
            if (r7 == 0) goto L8c
            r7 = 1
            goto L8d
        L82:
            java.lang.String r9 = "plan_a"
            boolean r7 = r7.equals(r9)
            if (r7 == 0) goto L8c
            r7 = 0
            goto L8d
        L8c:
            r7 = -1
        L8d:
            switch(r7) {
                case 0: goto L9c;
                case 1: goto L9c;
                case 2: goto L9c;
                case 3: goto L9c;
                case 4: goto La9;
                case 5: goto La9;
                case 6: goto L93;
                case 7: goto L91;
                default: goto L90;
            }
        L90:
            goto La5
        L91:
            r4 = r6
            goto La5
        L93:
            com.ss.android.socialbase.appdownloader.AhAttempt r6 = com.ss.android.socialbase.appdownloader.AhUtils.checkBrowserInstallConfig(r6, r11)
            int r6 = r6.error_code
            if (r6 != 0) goto La5
            goto La9
        L9c:
            com.ss.android.socialbase.appdownloader.AhAttempt r6 = com.ss.android.socialbase.appdownloader.AhUtils.checkJumpFileManagerConfig(r6, r11)
            int r6 = r6.error_code
            if (r6 != 0) goto La5
            goto La9
        La5:
            int r3 = r3 + 1
            goto L18
        La9:
            if (r4 == 0) goto Lc5
            java.lang.String r10 = "show_unknown_source_on_startup"
            int r10 = r4.optInt(r10)
            if (r10 != r5) goto Lb4
            goto Lb5
        Lb4:
            r5 = 0
        Lb5:
            if (r5 == 0) goto Lc5
            android.content.Context r10 = com.ss.android.socialbase.downloader.downloader.DownloadComponentManager.getAppContext()
            com.ss.android.socialbase.appdownloader.AhAttempt r11 = new com.ss.android.socialbase.appdownloader.AhAttempt
            r11.<init>()
            boolean r10 = com.ss.android.socialbase.appdownloader.AhUtils.tryShowUnknownSource(r10, r2, r4, r12, r11)
            return r10
        Lc5:
            return r0
        Lc6:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.downloadlib.addownload.DownloadInsideHelper.showUnknownSource(com.ss.android.socialbase.appdownloader.AppTaskBuilder, com.ss.android.socialbase.downloader.setting.DownloadSetting, int):boolean");
    }

    public static String getNotificationJumpUrl(DownloadInfo downloadInfo) {
        if (downloadInfo == null) {
            return null;
        }
        try {
            String extra = downloadInfo.getExtra();
            if (!TextUtils.isEmpty(extra)) {
                return new JSONObject(extra).optString("notification_jump_url", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static int redirectSavePathIfPossible(AppTaskBuilder appTaskBuilder, String str) {
        DownloadSetting downloadSettingObtain = DownloadSetting.obtain(appTaskBuilder.getDownloadSetting());
        JSONObject jSONObjectOptJSONObject = downloadSettingObtain.optJSONObject(com.ss.android.socialbase.downloader.setting.DownloadSettingKeys.KEY_ANTI_HIJACK_DIR);
        if (jSONObjectOptJSONObject == null || TextUtils.isEmpty(jSONObjectOptJSONObject.optString(DownloadSettingKeys.AntiHijackDir.KEY_ANTI_HIJACK_DIR_NAME))) {
            return -1;
        }
        String name = appTaskBuilder.getName();
        String saveName = appTaskBuilder.getSaveName();
        if (TextUtils.isEmpty(saveName)) {
            saveName = AppDownloadUtils.getValidName(str, name, appTaskBuilder.getMimeType(), true);
        }
        if (saveName.length() > 255) {
            saveName = saveName.substring(saveName.length() - 255);
        }
        if (TextUtils.isEmpty(name)) {
            name = saveName;
        }
        String savePath = appTaskBuilder.getSavePath();
        if (TextUtils.isEmpty(savePath)) {
            savePath = AppDownloadUtils.getAppDownloadPath();
        }
        String str2 = savePath + File.separator + AppDownloadUtils.getRedirectSavePath(name, downloadSettingObtain);
        DownloadInfo appDownloadInfo = AppDownloader.getInstance().getAppDownloadInfo(appTaskBuilder.getContext(), str);
        if (appDownloadInfo != null && appDownloadInfo.isSavePathRedirected()) {
            appTaskBuilder.savePath(appDownloadInfo.getSavePath());
            try {
                appTaskBuilder.downloadSetting(new JSONObject(appDownloadInfo.getDownloadSettingString()));
                return 0;
            } catch (Throwable unused) {
                return 0;
            }
        }
        if (appDownloadInfo != null || !"application/vnd.android.package-archive".equalsIgnoreCase(AppDownloader.getInstance().getMimeType(saveName, appTaskBuilder.getMimeType()))) {
            return appDownloadInfo != null ? 8 : 9;
        }
        int savePathRedirectedCode = AhUtils.getSavePathRedirectedCode(downloadSettingObtain);
        if (savePathRedirectedCode != 0) {
            return savePathRedirectedCode;
        }
        appTaskBuilder.savePath(str2);
        return savePathRedirectedCode;
    }
}
