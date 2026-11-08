package com.bytedance.sdk.openadsdk.downloadnew;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.bytedance.sdk.openadsdk.TTAppContextHolder;
import com.bytedance.sdk.openadsdk.TTDownloadEventLogger;
import com.bytedance.sdk.openadsdk.downloadnew.c;
import com.bytedance.sdk.openadsdk.downloadnew.core.DialogBuilder;
import com.bytedance.sdk.openadsdk.downloadnew.core.ExitInstallListener;
import com.bytedance.sdk.openadsdk.downloadnew.core.IDialogStatusChangedListener;
import com.bytedance.sdk.openadsdk.downloadnew.core.ITTDownloadAdapter;
import com.bytedance.sdk.openadsdk.downloadnew.core.ITTDownloadVisitor;
import com.bytedance.sdk.openadsdk.downloadnew.core.ITTHttpCallback;
import com.bytedance.sdk.openadsdk.downloadnew.core.ITTPermissionCallback;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadEventModel;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import com.ss.android.download.api.DownloadConfigure;
import com.ss.android.download.api.config.AppStatusChangeListener;
import com.ss.android.download.api.config.DownloadEventLogger;
import com.ss.android.download.api.config.DownloadNetworkFactory;
import com.ss.android.download.api.config.DownloadPermissionChecker;
import com.ss.android.download.api.config.DownloadSettings;
import com.ss.android.download.api.config.DownloadUIFactory;
import com.ss.android.download.api.config.IEncryptor;
import com.ss.android.download.api.config.IHttpCallback;
import com.ss.android.download.api.config.IPermissionCallback;
import com.ss.android.download.api.download.DownloadController;
import com.ss.android.download.api.download.DownloadEventConfig;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.download.api.download.extend.DownloadCompletedListener;
import com.ss.android.download.api.model.AppInfo;
import com.ss.android.download.api.model.DownloadAlertDialogInfo;
import com.ss.android.download.api.model.DownloadEventModel;
import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.TTDownloader;
import com.ss.android.downloadlib.addownload.dialog.AdDownloadDialogManager;
import com.ss.android.downloadlib.addownload.model.ModelManager;
import com.ss.android.downloadlib.applink.MarketUriUtils;
import com.ss.android.downloadlib.utils.AntiHijackUtils;
import com.ss.android.socialbase.appdownloader.AppDownloader;
import com.ss.android.socialbase.downloader.depend.IDownloadSettings;
import com.ss.android.socialbase.downloader.depend.IInstallAppHandler;
import com.ss.android.socialbase.downloader.downloader.DownloaderBuilder;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.model.HttpHeader;
import com.ss.android.socialbase.downloader.network.IDownloadHttpConnection;
import com.ss.android.socialbase.downloader.network.IDownloadHttpService;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    public static volatile String f5296a;

    /* renamed from: c, reason: collision with root package name */
    public static ITTDownloadVisitor f5298c;
    private static Context e;
    private static Map<Integer, ITTDownloadAdapter.OnEventLogHandler> f;
    private static final DownloadCompletedListener g;
    private static final AtomicBoolean d = new AtomicBoolean(false);

    /* renamed from: b, reason: collision with root package name */
    public static boolean f5297b = true;

    static {
        try {
            f5296a = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
        } catch (Throwable unused) {
        }
        g = new DownloadCompletedListener() { // from class: com.bytedance.sdk.openadsdk.downloadnew.d.6
            public void onDownloadStart(DownloadModel downloadModel, DownloadController downloadController, DownloadEventConfig downloadEventConfig) {
                com.bytedance.sdk.openadsdk.api.b.d.a("TTDownloadVisitor", "completeListener: onDownloadStart");
            }

            public void onDownloadFinished(DownloadInfo downloadInfo, String str) {
                com.bytedance.sdk.openadsdk.api.b.d.a("TTDownloadVisitor", "completeListener: onDownloadFinished");
            }

            public void onInstalled(DownloadInfo downloadInfo, String str) {
                com.bytedance.sdk.openadsdk.api.b.d.a("TTDownloadVisitor", "completeListener: onInstalled");
                d.c(str);
            }

            public void onDownloadFailed(DownloadInfo downloadInfo, BaseException baseException, String str) {
                com.bytedance.sdk.openadsdk.api.b.d.a("TTDownloadVisitor", "completeListener: onDownloadFailed");
            }

            public void onCanceled(DownloadInfo downloadInfo) {
                com.bytedance.sdk.openadsdk.api.b.d.a("TTDownloadVisitor", "completeListener: onCanceled");
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ITTDownloadVisitor e() {
        if (f5298c == null) {
            return (ITTDownloadVisitor) TTAdSdk.getAdManager().getExtra(ITTDownloadVisitor.class, com.bytedance.sdk.openadsdk.downloadnew.b.a(1));
        }
        return f5298c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(String str) {
        NativeDownloadModel nativeModelByPkg;
        JSONObject extra;
        if (TextUtils.isEmpty(str) || (nativeModelByPkg = ModelManager.getInstance().getNativeModelByPkg(str)) == null || (extra = nativeModelByPkg.getExtra()) == null || e() == null) {
            return;
        }
        e().checkAutoControl(extra, str);
    }

    public static void a(Context context) {
        if (context == null) {
            context = TTAppContextHolder.getContext();
        }
        if (context == null || d.get()) {
            return;
        }
        synchronized (d.class) {
            if (!d.get()) {
                e = context.getApplicationContext();
                if (e() != null) {
                    String strInitPath = e().initPath(f5297b);
                    if (!TextUtils.isEmpty(strInitPath)) {
                        f5296a = strInitPath;
                    }
                }
                d.set(b(e));
            }
        }
    }

    public static void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        f5296a = str;
    }

    public static TTDownloader a() {
        a(getContext());
        return TTDownloader.inst(getContext());
    }

    public static boolean a(Context context, Uri uri, DownloadModel downloadModel, DownloadEventConfig downloadEventConfig, DownloadController downloadController) {
        return a().getAdWebViewDownloadManager().tryOpenMarket(context, uri, downloadModel, downloadEventConfig, downloadController);
    }

    public static boolean a(Uri uri) {
        return MarketUriUtils.isMarketUri(uri);
    }

    public static void b() {
        a().clearAllData();
        if (e() != null) {
            e().clearAllData(f5296a);
        }
    }

    public static void a(int i) {
        if (f != null) {
            f.remove(Integer.valueOf(i));
        }
    }

    public static void a(int i, ITTDownloadAdapter.OnEventLogHandler onEventLogHandler) {
        if (onEventLogHandler != null) {
            if (f == null) {
                f = Collections.synchronizedMap(new WeakHashMap());
            }
            f.put(Integer.valueOf(i), onEventLogHandler);
        }
    }

    public static Map<Integer, ITTDownloadAdapter.OnEventLogHandler> c() {
        return f;
    }

    public static boolean a(String str, String str2, JSONObject jSONObject, Object obj) {
        boolean z = false;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || jSONObject == null) {
            return false;
        }
        Map<Integer, ITTDownloadAdapter.OnEventLogHandler> mapC = c();
        if (mapC != null) {
            for (Map.Entry<Integer, ITTDownloadAdapter.OnEventLogHandler> entry : mapC.entrySet()) {
                int iIntValue = entry.getKey().intValue();
                ITTDownloadAdapter.OnEventLogHandler value = entry.getValue();
                if (value != null) {
                    boolean zOnEventLog = value.onEventLog(iIntValue, jSONObject.toString(), str, str2, obj);
                    if (!z && !zOnEventLog) {
                        z = true;
                    }
                }
            }
        }
        return z;
    }

    private static boolean b(Context context) {
        if (context == null) {
            return false;
        }
        Context applicationContext = context.getApplicationContext();
        String packageName = applicationContext.getPackageName();
        if (TextUtils.isEmpty(packageName)) {
            packageName = "";
        }
        DownloadConfigure downloadConfigure = TTDownloader.inst(applicationContext).getDownloadConfigure();
        if (downloadConfigure == null) {
            return false;
        }
        downloadConfigure.setDownloadPermissionChecker(new c()).setEventLogger(new a()).setDownloadUIFactory(new C0159d(applicationContext)).setDownloadNetworkFactory(new b()).setDownloadSettings(new DownloadSettings() { // from class: com.bytedance.sdk.openadsdk.downloadnew.d.3
            public JSONObject get() {
                if (d.e() != null) {
                    return d.e().getDownloadSettings();
                }
                return new JSONObject();
            }
        }).setAppStatusChangeListener(new AppStatusChangeListener() { // from class: com.bytedance.sdk.openadsdk.downloadnew.d.2
            public boolean isAppInBackground() {
                if (d.e() != null) {
                    return d.e().getAppIsBackground();
                }
                return false;
            }
        }).setAppInfo(new AppInfo.Builder().appId("143").appName(TTAdConstant.APP_NAME).appVersion("4.0.2.1").versionCode(String.valueOf(4021)).build()).setEncryptor(new IEncryptor() { // from class: com.bytedance.sdk.openadsdk.downloadnew.d.1
            public byte[] encrypt(byte[] bArr, int i) {
                return new byte[0];
            }
        }).setFileProviderAuthority(packageName + ".TTFileProvider").initDownloader(a(applicationContext, e() != null ? e().getDownloadSettings() : new JSONObject())).configEnd();
        AntiHijackUtils.tryHookAms();
        TTDownloader.inst(applicationContext).getAdDownloadCompletedEventHandler().checkEventStatus(1);
        TTDownloader.inst(applicationContext).addDownloadCompletedListener(g);
        AppDownloader.getInstance().setInstallAppHandler(new IInstallAppHandler() { // from class: com.bytedance.sdk.openadsdk.downloadnew.d.4
            public boolean installApp(Intent intent) {
                return false;
            }
        });
        return true;
    }

    private static DownloaderBuilder a(Context context, JSONObject jSONObject) {
        return new DownloaderBuilder(context).downloadSetting(new IDownloadSettings() { // from class: com.bytedance.sdk.openadsdk.downloadnew.d.5
            public JSONObject get() {
                if (d.e() != null) {
                    return d.e().getDownloadSettings();
                }
                return new JSONObject();
            }
        }).downloadExpSwitch(jSONObject.optInt("download_exp_switch_temp", 1040187391)).httpService(new e());
    }

    public static boolean a(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        List<DownloadInfo> downloadingDownloadInfosWithMimeType = AppDownloader.getInstance().getDownloadingDownloadInfosWithMimeType(context);
        if (!downloadingDownloadInfosWithMimeType.isEmpty()) {
            for (DownloadInfo downloadInfo : downloadingDownloadInfosWithMimeType) {
                if (downloadInfo != null && str.equals(downloadInfo.getUrl())) {
                    return true;
                }
            }
        }
        return false;
    }

    private static Context getContext() {
        return e == null ? TTAppContextHolder.getContext() : e;
    }

    private static class c implements DownloadPermissionChecker {
        public void onRequestPermissionsResult(Activity activity, int i, String[] strArr, int[] iArr) {
        }

        public void requestPermission(Activity activity, String[] strArr, final IPermissionCallback iPermissionCallback) {
            if (d.e() != null) {
                d.e().requestPermission(activity, strArr, new ITTPermissionCallback() { // from class: com.bytedance.sdk.openadsdk.downloadnew.d.c.1
                    @Override // com.bytedance.sdk.openadsdk.downloadnew.core.ITTPermissionCallback
                    public void onGranted() {
                        if (iPermissionCallback != null) {
                            iPermissionCallback.onGranted();
                        }
                    }

                    @Override // com.bytedance.sdk.openadsdk.downloadnew.core.ITTPermissionCallback
                    public void onDenied(String str) {
                        if (iPermissionCallback != null) {
                            iPermissionCallback.onDenied(str);
                        }
                    }
                });
            }
        }

        public boolean hasPermission(Context context, String str) {
            if (d.e() != null) {
                return d.e().hasPermission(context, str);
            }
            return false;
        }
    }

    private static class b implements DownloadNetworkFactory {
        private b() {
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x0025  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void execute(java.lang.String r5, java.lang.String r6, java.util.Map<java.lang.String, java.lang.Object> r7, final com.ss.android.download.api.config.IHttpCallback r8) {
            /*
                r4 = this;
                int r0 = r5.hashCode()
                r1 = 70454(0x11336, float:9.8727E-41)
                r2 = 1
                r3 = 0
                if (r0 == r1) goto L1b
                r1 = 2461856(0x2590a0, float:3.449795E-39)
                if (r0 == r1) goto L11
                goto L25
            L11:
                java.lang.String r0 = "POST"
                boolean r5 = r5.equals(r0)
                if (r5 == 0) goto L25
                r5 = 1
                goto L26
            L1b:
                java.lang.String r0 = "GET"
                boolean r5 = r5.equals(r0)
                if (r5 == 0) goto L25
                r5 = 0
                goto L26
            L25:
                r5 = -1
            L26:
                switch(r5) {
                    case 0: goto L2b;
                    case 1: goto L2a;
                    default: goto L29;
                }
            L29:
                goto L2b
            L2a:
                r3 = 1
            L2b:
                com.bytedance.sdk.openadsdk.downloadnew.core.ITTDownloadVisitor r5 = com.bytedance.sdk.openadsdk.downloadnew.d.d()
                if (r5 == 0) goto L3d
                com.bytedance.sdk.openadsdk.downloadnew.core.ITTDownloadVisitor r5 = com.bytedance.sdk.openadsdk.downloadnew.d.d()
                com.bytedance.sdk.openadsdk.downloadnew.d$b$1 r0 = new com.bytedance.sdk.openadsdk.downloadnew.d$b$1
                r0.<init>()
                r5.execute(r3, r6, r7, r0)
            L3d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bytedance.sdk.openadsdk.downloadnew.d.b.execute(java.lang.String, java.lang.String, java.util.Map, com.ss.android.download.api.config.IHttpCallback):void");
        }

        public void postBody(String str, byte[] bArr, String str2, int i, final IHttpCallback iHttpCallback) {
            if (d.e() != null) {
                d.e().postBody(str, bArr, str2, new ITTHttpCallback() { // from class: com.bytedance.sdk.openadsdk.downloadnew.d.b.2
                    @Override // com.bytedance.sdk.openadsdk.downloadnew.core.ITTHttpCallback
                    public void onResponse(String str3) {
                        if (iHttpCallback != null) {
                            iHttpCallback.onResponse(str3);
                        }
                    }

                    @Override // com.bytedance.sdk.openadsdk.downloadnew.core.ITTHttpCallback
                    public void onError(Throwable th) {
                        if (iHttpCallback != null) {
                            iHttpCallback.onError(th);
                        }
                    }
                });
            }
        }
    }

    public static class e implements IDownloadHttpService {
        public IDownloadHttpConnection downloadWithConnection(int i, String str, List<HttpHeader> list) throws IOException {
            final c.a aVarA = com.bytedance.sdk.openadsdk.downloadnew.c.a(str, list);
            if (aVarA != null) {
                return new IDownloadHttpConnection() { // from class: com.bytedance.sdk.openadsdk.downloadnew.d.e.1
                    public void cancel() {
                    }

                    public InputStream getInputStream() {
                        return aVarA.f5290a;
                    }

                    public String getResponseHeaderField(String str2) {
                        if (aVarA.f5291b != null) {
                            return aVarA.f5291b.get(str2);
                        }
                        return null;
                    }

                    public int getResponseCode() {
                        return aVarA.f5292c;
                    }

                    public void end() {
                        try {
                            aVarA.d.disconnect();
                        } catch (Exception unused) {
                        }
                    }
                };
            }
            return null;
        }
    }

    /* renamed from: com.bytedance.sdk.openadsdk.downloadnew.d$d, reason: collision with other inner class name */
    public static class C0159d implements DownloadUIFactory {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<Context> f5306a;

        public C0159d(Context context) {
            this.f5306a = new WeakReference<>(context);
        }

        public void showToastWithDuration(int i, Context context, DownloadModel downloadModel, String str, Drawable drawable, int i2) {
            try {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                Toast.makeText(context, str, 0).show();
            } catch (Exception e) {
                Log.e("LibUIFactory", "showToastWithDuration e " + e.getMessage());
            }
        }

        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public AlertDialog showAlertDialog(DownloadAlertDialogInfo downloadAlertDialogInfo) {
            if (downloadAlertDialogInfo != null && d.e() != null) {
                if (downloadAlertDialogInfo.mContext != null && (downloadAlertDialogInfo.mContext instanceof Activity)) {
                    return d.e().showDialogBySelf((Activity) downloadAlertDialogInfo.mContext, downloadAlertDialogInfo.mScene == 1, b(downloadAlertDialogInfo));
                }
                d.e().showDialogByDelegate(this.f5306a, downloadAlertDialogInfo.mScene == 1, b(downloadAlertDialogInfo));
            }
            return null;
        }

        public Notification buildNotification(NotificationCompat.Builder builder) {
            return builder.build();
        }

        private DialogBuilder b(final DownloadAlertDialogInfo downloadAlertDialogInfo) {
            return DialogBuilder.builder().setTitle(downloadAlertDialogInfo.mTitle).setMessage(downloadAlertDialogInfo.mMessage).setNegativeBtnText(downloadAlertDialogInfo.mNegativeBtnText).setPositiveBtnText(downloadAlertDialogInfo.mPositiveBtnText).setIcon(downloadAlertDialogInfo.mIcon).setDialogStatusChangedListener(new IDialogStatusChangedListener() { // from class: com.bytedance.sdk.openadsdk.downloadnew.d.d.1
                @Override // com.bytedance.sdk.openadsdk.downloadnew.core.IDialogStatusChangedListener
                public void onPositiveBtnClick(DialogInterface dialogInterface) {
                    if (downloadAlertDialogInfo.mDialogStatusChangedListener != null) {
                        downloadAlertDialogInfo.mDialogStatusChangedListener.onPositiveBtnClick(dialogInterface);
                    }
                }

                @Override // com.bytedance.sdk.openadsdk.downloadnew.core.IDialogStatusChangedListener
                public void onNegativeBtnClick(DialogInterface dialogInterface) {
                    if (downloadAlertDialogInfo.mDialogStatusChangedListener != null) {
                        try {
                            downloadAlertDialogInfo.mDialogStatusChangedListener.onNegativeBtnClick(dialogInterface);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override // com.bytedance.sdk.openadsdk.downloadnew.core.IDialogStatusChangedListener
                public void onCancel(DialogInterface dialogInterface) {
                    if (downloadAlertDialogInfo.mDialogStatusChangedListener != null) {
                        downloadAlertDialogInfo.mDialogStatusChangedListener.onCancel(dialogInterface);
                    }
                }
            });
        }
    }

    public static class a implements DownloadEventLogger {
        public void onV3Event(DownloadEventModel downloadEventModel) {
            com.bytedance.sdk.openadsdk.api.b.d.a("LibEventLogger", "onV3Event");
            a(downloadEventModel, true);
        }

        public void onEvent(DownloadEventModel downloadEventModel) {
            com.bytedance.sdk.openadsdk.api.b.d.a("LibEventLogger", "onEvent called");
            a(downloadEventModel, false);
            a(downloadEventModel);
        }

        private void a(DownloadEventModel downloadEventModel) {
            if (downloadEventModel == null) {
                return;
            }
            TTDownloadEventModel label = TTDownloadEventModel.builder().setTag(downloadEventModel.getTag()).setExtJson(downloadEventModel.getExtJson()).setLabel(downloadEventModel.getLabel());
            boolean z = "download_notification".equals(downloadEventModel.getTag()) || "landing_h5_download_ad_button".equals(downloadEventModel.getTag());
            if (d.e() != null) {
                d.e().executeLogUpload(label, z);
            }
        }

        private void a(DownloadEventModel downloadEventModel, boolean z) {
            TTDownloadEventLogger tTDownloadEventLogger;
            if (d.e() == null || (tTDownloadEventLogger = d.e().getTTDownloadEventLogger()) == null || downloadEventModel == null) {
                return;
            }
            if (tTDownloadEventLogger.shouldFilterOpenSdkLog() && d.e().isOpenSdkEvent(downloadEventModel.toString())) {
                return;
            }
            if (z) {
                tTDownloadEventLogger.onV3Event(d.b(downloadEventModel));
            } else {
                tTDownloadEventLogger.onEvent(d.b(downloadEventModel));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static JSONObject b(DownloadEventModel downloadEventModel) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("category", downloadEventModel.getCategory());
            jSONObject.put("tag", downloadEventModel.getTag());
            jSONObject.put(TTDownloadField.TT_LABEL, downloadEventModel.getLabel());
            jSONObject.put("isAd", downloadEventModel.isAd());
            jSONObject.put("adId", downloadEventModel.getAdId());
            jSONObject.put(TTDownloadField.TT_LOG_EXTRA, downloadEventModel.getLogExtra());
            jSONObject.put("extValue", downloadEventModel.getExtValue());
            jSONObject.put("extJson", downloadEventModel.getExtJson());
            jSONObject.put("paramsJson", downloadEventModel.getParamsJson());
            jSONObject.put("eventSource", downloadEventModel.getEventSource());
            jSONObject.put("extraObject", downloadEventModel.getExtraObject());
            jSONObject.put("clickTrackUrl", downloadEventModel.getClickTrackUrl());
            jSONObject.put("isV3", downloadEventModel.isV3());
            jSONObject.put("V3EventName", downloadEventModel.getV3EventName());
            jSONObject.put("V3EventParams", downloadEventModel.getV3EventParams());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public static boolean a(Activity activity, final ExitInstallListener exitInstallListener) {
        return AdDownloadDialogManager.Instance().tryShowInstallDialogSync(activity, false, new AdDownloadDialogManager.OnExitClickListener() { // from class: com.bytedance.sdk.openadsdk.downloadnew.d.7
            public void onExitClick() {
                if (exitInstallListener != null) {
                    exitInstallListener.onExitInstall();
                }
            }
        });
    }
}
