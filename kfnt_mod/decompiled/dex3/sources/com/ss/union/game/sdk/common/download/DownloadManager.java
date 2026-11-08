package com.ss.union.game.sdk.common.download;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.ss.android.download.api.config.AppStatusChangeListener;
import com.ss.android.download.api.config.DownloadActionListener;
import com.ss.android.download.api.config.DownloadAutoInstallInterceptListener;
import com.ss.android.download.api.config.DownloadClearSpaceListener;
import com.ss.android.download.api.config.DownloadEventLogger;
import com.ss.android.download.api.config.DownloadNetworkFactory;
import com.ss.android.download.api.config.DownloadSettings;
import com.ss.android.download.api.config.DownloadUIFactory;
import com.ss.android.download.api.config.IHttpCallback;
import com.ss.android.download.api.download.DownloadController;
import com.ss.android.download.api.download.DownloadEventConfig;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.download.api.download.DownloadStatusChangeListener;
import com.ss.android.download.api.download.SimpleDownloadEventConfig;
import com.ss.android.download.api.model.DownloadAlertDialogInfo;
import com.ss.android.download.api.model.DownloadEventModel;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.downloadlib.BaseDownloadMonitorListener;
import com.ss.android.downloadlib.TTDownloader;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.constants.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.downloader.DownloaderBuilder;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.model.HttpHeader;
import com.ss.android.socialbase.downloader.network.IDownloadHttpConnection;
import com.ss.android.socialbase.downloader.network.IDownloadHttpService;
import com.ss.union.game.sdk.common.net.CoreNetClient;
import com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestGet;
import com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestPost;
import com.ss.union.game.sdk.common.net.http.base.response.CoreHttpResponse;
import com.ss.union.game.sdk.common.net.http.base.response.CoreNetFileWrapper;
import com.ss.union.game.sdk.common.net.http.base.response.CoreNetResponse;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.common.util.ToastUtils;
import com.ss.union.game.sdk.common.util.logger.LogUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class DownloadManager {
    public static String TAG = "GameDownloadManager";
    private static final long a = 100001;
    private static volatile DownloadManager d = null;
    private static final String e = "{\"min_resume_failed_interval_time\":10000,\"min_resume_uninstall_interval_time\":10000,\"max_resume_failed_notification_show_count\":3,\"max_resume_uninstall_notification_show_count\":3}";
    private Map<Long, DownloadModel> b = new HashMap();
    private Context c;

    public static DownloadManager getInstance() {
        if (d == null) {
            synchronized (DownloadManager.class) {
                if (d == null) {
                    d = new DownloadManager();
                }
            }
        }
        return d;
    }

    private DownloadManager() {
    }

    public void doInit(Context context) {
        this.c = context.getApplicationContext();
        if (GlobalInfo.getDownloadEventLogger() != null) {
            return;
        }
        TTDownloader.inst(context).getDownloadConfigure().setEventLogger(new DownloadEventLogger() { // from class: com.ss.union.game.sdk.common.download.DownloadManager.9
            @Override // com.ss.android.download.api.config.DownloadEventLogger
            public void onV3Event(@NonNull DownloadEventModel downloadEventModel) {
                LogUtils.log(DownloadManager.TAG, "onV3Event :" + downloadEventModel.getLabel());
            }

            @Override // com.ss.android.download.api.config.DownloadEventLogger
            public void onEvent(@NonNull DownloadEventModel downloadEventModel) {
                LogUtils.log(DownloadManager.TAG, "onEvent:" + downloadEventModel.getLabel());
            }
        }).setDownloadUIFactory(new DownloadUIFactory() { // from class: com.ss.union.game.sdk.common.download.DownloadManager.8
            @Override // com.ss.android.download.api.config.DownloadUIFactory
            public void showToastWithDuration(int i, @Nullable Context context2, @Nullable DownloadModel downloadModel, String str, Drawable drawable, int i2) {
                ToastUtils.getInstance().toast(str);
            }

            @Override // com.ss.android.download.api.config.DownloadUIFactory
            public Dialog showAlertDialog(@NonNull DownloadAlertDialogInfo downloadAlertDialogInfo) {
                return DownloadManager.b(downloadAlertDialogInfo);
            }

            @Override // com.ss.android.download.api.config.DownloadUIFactory
            public Notification buildNotification(@NonNull NotificationCompat.Builder builder) {
                Notification notificationBuild = builder.build();
                notificationBuild.flags |= 2;
                return notificationBuild;
            }
        }).setDownloadNetworkFactory(new DownloadNetworkFactory() { // from class: com.ss.union.game.sdk.common.download.DownloadManager.7
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.ss.android.download.api.config.DownloadNetworkFactory
            public void execute(String str, String str2, Map<String, Object> map, IHttpCallback iHttpCallback) {
                char c = 65535;
                try {
                    int iHashCode = str.hashCode();
                    if (iHashCode != 70454) {
                        if (iHashCode == 2461856 && str.equals("POST")) {
                            c = 1;
                        }
                    } else if (str.equals("GET")) {
                        c = 0;
                    }
                    switch (c) {
                        case 0:
                            if (iHttpCallback != null) {
                                CoreNetResponse<String, T> coreNetResponseString = ((ACoreRequestGet) ((ACoreRequestGet) CoreNetClient.get(str2).enableCommonHeader(false)).enableCommonParam(false)).string();
                                if (coreNetResponseString.isSuccess()) {
                                    iHttpCallback.onResponse(coreNetResponseString.httpBody);
                                    break;
                                } else {
                                    iHttpCallback.onError(coreNetResponseString.error);
                                    break;
                                }
                            }
                            break;
                        case 1:
                            if (iHttpCallback != null) {
                                ACoreRequestPost aCoreRequestPost = (ACoreRequestPost) ((ACoreRequestPost) CoreNetClient.post(str2).enableCommonHeader(false)).enableCommonParam(false);
                                if (map != null && map.size() > 0) {
                                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                                        if (entry != null) {
                                            aCoreRequestPost.param(entry.getKey(), entry.getValue() == null ? "" : entry.getValue().toString());
                                        }
                                    }
                                }
                                CoreNetResponse<String, T> coreNetResponseString2 = aCoreRequestPost.string();
                                if (coreNetResponseString2.isSuccess()) {
                                    iHttpCallback.onResponse(coreNetResponseString2.httpBody);
                                    break;
                                } else {
                                    iHttpCallback.onError(coreNetResponseString2.error);
                                    break;
                                }
                            }
                            break;
                    }
                } catch (Exception unused) {
                }
            }

            @Override // com.ss.android.download.api.config.DownloadNetworkFactory
            public void postBody(String str, byte[] bArr, String str2, int i, IHttpCallback iHttpCallback) {
                try {
                    CoreNetResponse<String, T> coreNetResponseString = CoreNetClient.post(str).upBytes(bArr, CoreNetFileWrapper.MediaType.parse(str2)).string();
                    if (coreNetResponseString.isSuccess()) {
                        iHttpCallback.onResponse(coreNetResponseString.httpBody);
                    } else {
                        iHttpCallback.onError(coreNetResponseString.error);
                    }
                } catch (Throwable unused) {
                }
            }
        }).setActionListener(new DownloadActionListener() { // from class: com.ss.union.game.sdk.common.download.DownloadManager.6
            @Override // com.ss.android.download.api.config.DownloadActionListener
            public void onItemClick(@Nullable Context context2, @NonNull DownloadModel downloadModel, @Nullable DownloadController downloadController, @Nullable DownloadEventConfig downloadEventConfig) {
                LogUtils.log(DownloadManager.TAG, "onItemClick");
            }

            @Override // com.ss.android.download.api.config.DownloadActionListener
            public void onItemStart(@Nullable Context context2, @NonNull DownloadModel downloadModel, @Nullable DownloadController downloadController) {
                LogUtils.log(DownloadManager.TAG, "onItemStart");
            }

            @Override // com.ss.android.download.api.config.DownloadActionListener
            public void onOpenApp(@Nullable Context context2, @NonNull DownloadModel downloadModel, @Nullable DownloadController downloadController, @Nullable DownloadEventConfig downloadEventConfig, String str, @NonNull String str2) {
                LogUtils.log(DownloadManager.TAG, "onOpenApp");
            }
        }).setDownloadMonitorListener(new BaseDownloadMonitorListener() { // from class: com.ss.union.game.sdk.common.download.DownloadManager.5
            @Override // com.ss.android.downloadlib.BaseDownloadMonitorListener, com.ss.android.socialbase.appdownloader.depend.IAppDownloadMonitorListener
            public void onAppDownloadMonitorSend(DownloadInfo downloadInfo, BaseException baseException, int i) {
                super.onAppDownloadMonitorSend(downloadInfo, baseException, i);
            }
        }).setDownloadSettings(new DownloadSettings() { // from class: com.ss.union.game.sdk.common.download.DownloadManager.4
            @Override // com.ss.android.download.api.config.DownloadSettings
            public JSONObject get() {
                return DownloadManager.this.c();
            }
        }).setAppStatusChangeListener(new AppStatusChangeListener() { // from class: com.ss.union.game.sdk.common.download.DownloadManager.3
            @Override // com.ss.android.download.api.config.AppStatusChangeListener
            public boolean isAppInBackground() {
                return false;
            }
        }).setDownloadAutoInstallInterceptListener(new DownloadAutoInstallInterceptListener() { // from class: com.ss.union.game.sdk.common.download.DownloadManager.2
            @Override // com.ss.android.download.api.config.DownloadAutoInstallInterceptListener
            public boolean installIntercept(boolean z) {
                return false;
            }
        }).setFileProviderAuthority(context.getPackageName() + ".TTSSFileProvider").setDownloadClearSpaceListener(new DownloadClearSpaceListener() { // from class: com.ss.union.game.sdk.common.download.DownloadManager.1
            @Override // com.ss.android.download.api.config.DownloadClearSpaceListener
            public void clearStorageSpace() {
                LogUtils.log(DownloadManager.TAG, "clearStorageSpace 开始清理空间");
            }
        }).initDownloader(new DownloaderBuilder(context).httpService(new DownloadHttpService()));
    }

    private DownloadModel a(long j, String str, String str2, String str3, String str4) {
        return new AdDownloadModel.Builder().setId(j).setVersionName(str4).setDownloadUrl(str).setPackageName(str2).setAppName(str3).setExtraValue(a).setIsAd(false).build();
    }

    public void setAutoInstall(Context context, final boolean z) {
        TTDownloader.inst(context.getApplicationContext()).getDownloadConfigure().setDownloadAutoInstallInterceptListener(new DownloadAutoInstallInterceptListener() { // from class: com.ss.union.game.sdk.common.download.DownloadManager.10
            @Override // com.ss.android.download.api.config.DownloadAutoInstallInterceptListener
            public boolean installIntercept(boolean z2) {
                return !z;
            }
        });
    }

    public void bind(Context context, long j, String str, String str2, String str3, String str4, DownloadStatusChangeListener downloadStatusChangeListener) {
        LogUtils.log(TAG, "bind downloadID:" + j + "，downloadUrl：" + str + ",packageName :" + str2 + ",appnamne:" + str3);
        DownloadModel downloadModelA = a(j, str, str2, str3, str4);
        this.b.put(Long.valueOf(j), downloadModelA);
        a().bind(context, (int) j, downloadStatusChangeListener, downloadModelA);
    }

    public void unBind(long j) {
        DownloadModel downloadModel = this.b.get(Long.valueOf(j));
        if (downloadModel == null) {
            return;
        }
        a().unbind(downloadModel.getDownloadUrl(), 0);
    }

    public void download(Activity activity, long j, long j2, long j3, long j4, String str, String str2) {
        LogUtils.log(TAG, "download()viewToken" + j + ",gameID:" + j2 + ",recID:" + j3 + ",noticeID:" + j4 + ",position:" + str + ",packageName:" + str2);
        SimpleDownloadEventConfig simpleDownloadEventConfigB = b();
        DownloadModel downloadModel = this.b.get(Long.valueOf(j));
        if (downloadModel == null) {
            return;
        }
        a().action(downloadModel.getDownloadUrl(), j, 2, simpleDownloadEventConfigB, null);
    }

    private TTDownloader a() {
        return TTDownloader.inst(this.c.getApplicationContext());
    }

    private SimpleDownloadEventConfig b() {
        return new SimpleDownloadEventConfig.Builder().setClickButtonTag("click_button").setClickStartLabel("click_start").setClickPauseLabel("click_pause").setClickContinueLabel("click_continue").setClickInstallLabel("click_install").setOpenLabel(DownloadEvent.LOG_LABEL_DOWNLOAD_OPEN).setDownloadFailedLabel("download_failed").setIsEnableClickEvent(true).setIsEnableCompletedEvent(true).setIsEnableNoChargeClickEvent(true).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Dialog b(final DownloadAlertDialogInfo downloadAlertDialogInfo) {
        if (downloadAlertDialogInfo == null) {
            return null;
        }
        if (downloadAlertDialogInfo.mView != null) {
            AlertDialog alertDialogCreate = new AlertDialog.Builder(downloadAlertDialogInfo.mContext).create();
            alertDialogCreate.setView(downloadAlertDialogInfo.mView);
            alertDialogCreate.setCancelable(false);
            alertDialogCreate.show();
            return alertDialogCreate;
        }
        AlertDialog.Builder onCancelListener = new AlertDialog.Builder(downloadAlertDialogInfo.mContext, ResourceUtils.getStyleIdByName(Build.VERSION.SDK_INT >= 21 ? "Theme.Dialog.TTDownload" : "Theme.Dialog.TTDownloadOld")).setTitle(downloadAlertDialogInfo.mTitle).setMessage(downloadAlertDialogInfo.mMessage).setPositiveButton(downloadAlertDialogInfo.mPositiveBtnText, new DialogInterface.OnClickListener() { // from class: com.ss.union.game.sdk.common.download.DownloadManager.13
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (downloadAlertDialogInfo.mDialogStatusChangedListener != null) {
                    downloadAlertDialogInfo.mDialogStatusChangedListener.onPositiveBtnClick(dialogInterface);
                }
            }
        }).setNegativeButton(downloadAlertDialogInfo.mNegativeBtnText, new DialogInterface.OnClickListener() { // from class: com.ss.union.game.sdk.common.download.DownloadManager.12
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (downloadAlertDialogInfo.mDialogStatusChangedListener != null) {
                    downloadAlertDialogInfo.mDialogStatusChangedListener.onNegativeBtnClick(dialogInterface);
                }
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.ss.union.game.sdk.common.download.DownloadManager.11
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                if (downloadAlertDialogInfo.mDialogStatusChangedListener != null) {
                    downloadAlertDialogInfo.mDialogStatusChangedListener.onCancel(dialogInterface);
                }
            }
        });
        if (downloadAlertDialogInfo.mIcon != null) {
            onCancelListener.setIcon(downloadAlertDialogInfo.mIcon);
        }
        AlertDialog alertDialogShow = onCancelListener.show();
        alertDialogShow.setCanceledOnTouchOutside(downloadAlertDialogInfo.mCancelableOnTouchOutside);
        return alertDialogShow;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject c() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("download_notification_config", e);
            jSONObject.put(DownloadSettingKeys.KEY_HOOK, 1);
            jSONObject.put(DownloadSettingKeys.KEY_CHECK_HIJACK, 1);
            jSONObject.put(DownloadSettingKeys.KEY_NEED_BACKUP, 1);
            jSONObject.put(DownloadSettingKeys.KEY_IS_ENABLE_START_INSTALL_AGAIN, 0);
            jSONObject.put(DownloadSettingKeys.KEY_NEXT_INSTALL_MIN_INTERVAL, 10000);
        } catch (JSONException e2) {
            LogUtils.log(TAG, "JSONException:" + e2.getMessage());
        }
        return jSONObject;
    }

    public static class DownloadEvent {
        public static final Set<String> DOWNLOAD_LABEL_SET = new HashSet();
        public static final String LOG_FIELD_PACKAGE_NAME = "rec_package_name";
        public static final String LOG_FIELD_POSITION = "position";
        public static final String LOG_FIELD_POSITION_NOTICE_ID = "notice_id";
        public static final String LOG_FIELD_POSITION_NOTIFICATION = "download_notification";
        public static final String LOG_FIELD_POSITION_RESOURCE_ID = "rec_id";
        public static final String LOG_FIELD_POSITION_VIDEO_WINDOW = "video_window";
        public static final String LOG_LABEL_DOWNLOAD_CONTINUE = "click_continue";
        public static final String LOG_LABEL_DOWNLOAD_DELETE = "click_delete";
        public static final String LOG_LABEL_DOWNLOAD_FAILED = "download_failed";
        public static final String LOG_LABEL_DOWNLOAD_FINISH = "download_finish";
        public static final String LOG_LABEL_DOWNLOAD_INSTALL = "click_install";
        public static final String LOG_LABEL_DOWNLOAD_OPEN = "click_open";
        public static final String LOG_LABEL_DOWNLOAD_PAUSE = "click_pause";
        public static final String LOG_LABEL_DOWNLOAD_START = "click_start";
        public static final String LOG_LABEL_INSTALL_FINISH = "install_finish";
        public static final String LOG_LABEL_INSTALL_WINDOW_SHOW = "install_window_show";
        public static final String LOG_LABEL_NOTIFY_CLICK = "click_item";

        static {
            DOWNLOAD_LABEL_SET.add("click_start");
            DOWNLOAD_LABEL_SET.add("click_pause");
            DOWNLOAD_LABEL_SET.add("click_continue");
            DOWNLOAD_LABEL_SET.add("click_install");
            DOWNLOAD_LABEL_SET.add(LOG_LABEL_DOWNLOAD_OPEN);
            DOWNLOAD_LABEL_SET.add("download_finish");
            DOWNLOAD_LABEL_SET.add("download_failed");
            DOWNLOAD_LABEL_SET.add("install_finish");
            DOWNLOAD_LABEL_SET.add("install_window_show");
            DOWNLOAD_LABEL_SET.add(LOG_LABEL_NOTIFY_CLICK);
            DOWNLOAD_LABEL_SET.add(LOG_LABEL_DOWNLOAD_DELETE);
        }
    }

    private static class DownloadHttpService implements IDownloadHttpService {
        private DownloadHttpService() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.ss.android.socialbase.downloader.network.IDownloadHttpService
        public IDownloadHttpConnection downloadWithConnection(int i, String str, List<HttpHeader> list) throws IOException {
            final String str2 = "TAG_DOWNLOAD" + System.currentTimeMillis();
            ACoreRequestGet aCoreRequestGet = (ACoreRequestGet) ((ACoreRequestGet) ((ACoreRequestGet) CoreNetClient.get(str).tag(str2)).enableCommonParam(false)).enableCommonHeader(false);
            if (list != null && list.size() > 0) {
                for (HttpHeader httpHeader : list) {
                    aCoreRequestGet.header(httpHeader.getName(), httpHeader.getValue());
                }
            }
            final CoreNetResponse<CoreHttpResponse, T> coreNetResponseResponse = aCoreRequestGet.response();
            if (coreNetResponseResponse == 0) {
                throw new IOException("can't get response");
            }
            final CoreHttpResponse coreHttpResponse = (CoreHttpResponse) coreNetResponseResponse.data;
            if (coreHttpResponse == null) {
                return null;
            }
            final InputStream inputStreamByteStream = coreHttpResponse.byteStream();
            return new IDownloadHttpConnection() { // from class: com.ss.union.game.sdk.common.download.DownloadManager.DownloadHttpService.1
                @Override // com.ss.android.socialbase.downloader.network.IDownloadHttpConnection
                public InputStream getInputStream() throws IOException {
                    return inputStreamByteStream;
                }

                @Override // com.ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection
                public String getResponseHeaderField(String str3) {
                    return coreNetResponseResponse.header(str3);
                }

                @Override // com.ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection
                public int getResponseCode() throws IOException {
                    return coreNetResponseResponse.httpCode;
                }

                @Override // com.ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection
                public void cancel() {
                    try {
                        CoreNetClient.cancel(str2);
                    } catch (Throwable unused) {
                    }
                }

                @Override // com.ss.android.socialbase.downloader.network.IDownloadHttpConnection
                public void end() {
                    try {
                        if (coreHttpResponse != null) {
                            coreHttpResponse.close();
                        }
                    } catch (Throwable unused) {
                    }
                }
            };
        }
    }
}
