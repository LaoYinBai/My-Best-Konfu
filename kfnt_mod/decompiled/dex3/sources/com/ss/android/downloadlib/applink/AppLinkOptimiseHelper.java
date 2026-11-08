package com.ss.android.downloadlib.applink;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.DownloadComponentManager;
import com.ss.android.downloadlib.constants.DownloadSettingKeys;
import com.ss.android.downloadlib.constants.EventConstants;
import com.ss.android.downloadlib.event.AdEventHandler;
import com.ss.android.downloadlib.utils.DownloadSettingUtils;
import com.ss.android.downloadlib.utils.ToolUtils;
import com.ss.android.socialbase.downloader.common.AppStatusManager;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AppLinkOptimiseHelper {
    private static final int APP_LINK_CHECKOUT_TIMEOUT = 300000;
    private static final int TRY_CHECK_LINK_STATUS_COUNT = 10;
    private static Handler sUIHandler = new Handler(Looper.getMainLooper());

    public static void invoke(final NativeDownloadModel nativeDownloadModel, @NonNull final IAppLinkOptimiseCallback iAppLinkOptimiseCallback) {
        boolean zIsAppForeground = AppStatusManager.getInstance().isAppForeground();
        if (!zIsAppForeground && Build.VERSION.SDK_INT >= 29) {
            ToolUtils.tryMoveAppToFront();
        }
        boolean zIsAppForeground2 = AppStatusManager.getInstance().isAppForeground();
        boolean z = !zIsAppForeground && zIsAppForeground2;
        if (nativeDownloadModel != null) {
            nativeDownloadModel.setDeeplinkAfterBackApp(z);
        }
        iAppLinkOptimiseCallback.invoke(z);
        if (nativeDownloadModel == null) {
            return;
        }
        checkAppLinkSuccessDelay(nativeDownloadModel, checkAppLinkSuccessCount(nativeDownloadModel));
        if (zIsAppForeground2) {
            return;
        }
        final long jCurrentTimeMillis = System.currentTimeMillis();
        AppStatusManager.getInstance().registerAppSwitchListener(new AppStatusManager.AppStatusChangeListener() { // from class: com.ss.android.downloadlib.applink.AppLinkOptimiseHelper.1
            @Override // com.ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
            public void onAppBackground() {
            }

            @Override // com.ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
            public void onAppForeground() {
                AppStatusManager.getInstance().unregisterAppSwitchListener(this);
                DownloadComponentManager.getInstance().submitCPUTask(new Runnable() { // from class: com.ss.android.downloadlib.applink.AppLinkOptimiseHelper.1.1
                    @Override // java.lang.Runnable
                    public void run() throws PackageManager.NameNotFoundException {
                        boolean zIsAppActivatedByPKG = ToolUtils.isAppActivatedByPKG(nativeDownloadModel.getPackageName());
                        long invokeAppBackTimeLimit = AppLinkOptimiseHelper.getInvokeAppBackTimeLimit(nativeDownloadModel);
                        if (!zIsAppActivatedByPKG || invokeAppBackTimeLimit >= System.currentTimeMillis() - jCurrentTimeMillis) {
                            if (System.currentTimeMillis() - jCurrentTimeMillis > AppLinkOptimiseHelper.checkAppLinkTimeout(nativeDownloadModel)) {
                                AdEventHandler.getInstance().sendUnityEvent(EventConstants.UnityLabel.DEEPLINK_DELAY_TIMEOUT, nativeDownloadModel);
                                return;
                            }
                            nativeDownloadModel.setDeeplinkAfterBackApp(true);
                            AdEventHandler.getInstance().sendUnityEvent(EventConstants.UnityLabel.DEEPLINK_DELAY_INVOKE, nativeDownloadModel);
                            iAppLinkOptimiseCallback.invoke(true);
                            AppLinkOptimiseHelper.checkAppLinkSuccessDelay(nativeDownloadModel, AppLinkOptimiseHelper.checkAppLinkSuccessCount(nativeDownloadModel));
                        }
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void checkAppLinkSuccessDelay(@NonNull final NativeDownloadModel nativeDownloadModel, final int i) {
        if (i <= 0) {
            return;
        }
        DownloadComponentManager.getInstance().submitScheduledTask(new Runnable() { // from class: com.ss.android.downloadlib.applink.AppLinkOptimiseHelper.2
            @Override // java.lang.Runnable
            public void run() throws JSONException {
                int i2 = 1;
                if (ToolUtils.isAppActivatedByPKG(nativeDownloadModel.getPackageName())) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        if (!nativeDownloadModel.isDeeplinkAfterBackApp()) {
                            i2 = 2;
                        }
                        jSONObject.putOpt(EventConstants.ExtraJson.KEY_DEEPLINK_SOURCE, Integer.valueOf(i2));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    AdEventHandler.getInstance().sendUnityEvent(EventConstants.UnityLabel.DEEPLINK_SUCCESS_2, jSONObject, nativeDownloadModel);
                    return;
                }
                AppLinkOptimiseHelper.checkAppLinkSuccessDelay(nativeDownloadModel, i - 1);
            }
        }, checkAppLinkSuccessDelay(nativeDownloadModel) * 1000);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long checkAppLinkTimeout(NativeDownloadModel nativeDownloadModel) {
        return DownloadSettingUtils.getSetting(nativeDownloadModel).optLong(DownloadSettingKeys.KEY_APP_LINK_CHECK_TIMEOUT, 300000L);
    }

    private static int checkAppLinkSuccessDelay(NativeDownloadModel nativeDownloadModel) {
        return DownloadSettingUtils.getSetting(nativeDownloadModel).optInt(DownloadSettingKeys.KEY_APP_LINK_CHECK_DELAY, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int checkAppLinkSuccessCount(NativeDownloadModel nativeDownloadModel) {
        return DownloadSettingUtils.getSetting(nativeDownloadModel).optInt(DownloadSettingKeys.KEY_APP_LINK_CHECK_COUNT, 10);
    }

    public static boolean switchIsOpen(NativeDownloadModel nativeDownloadModel) {
        return DownloadSettingUtils.getSetting(nativeDownloadModel).optInt(DownloadSettingKeys.KEY_APP_LINK_OPT_SWITCH) == 1;
    }

    public static boolean installSwitchIsOpen(NativeDownloadModel nativeDownloadModel) {
        return DownloadSettingUtils.getSetting(nativeDownloadModel).optInt(DownloadSettingKeys.KEY_APP_LINK_OPT_INSTALL_SWITCH) == 1;
    }

    public static boolean invokeSwitchIsOpen(NativeDownloadModel nativeDownloadModel) {
        return DownloadSettingUtils.getSetting(nativeDownloadModel).optInt(DownloadSettingKeys.KEY_APP_LINK_OPT_INVOKE_SWITCH) == 1;
    }

    public static boolean dialogSwitchIsOpen(NativeDownloadModel nativeDownloadModel) {
        return DownloadSettingUtils.getSetting(nativeDownloadModel).optInt(DownloadSettingKeys.KEY_APP_LINK_OPT_DIALOG_SWITCH) == 1;
    }

    public static long getInvokeAppBackTimeLimit(NativeDownloadModel nativeDownloadModel) {
        if (nativeDownloadModel == null) {
            return 3000L;
        }
        return DownloadSettingUtils.getSetting(nativeDownloadModel).optInt(DownloadSettingKeys.KEY_APP_LINK_OPT_BACK_TIME_LIMIT, 3) * 1000;
    }
}
