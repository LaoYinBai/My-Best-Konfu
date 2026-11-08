package com.ss.android.socialbase.appdownloader;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import com.ss.android.socialbase.appdownloader.constants.Constants;
import com.ss.android.socialbase.appdownloader.depend.IAppDownloadEventListener;
import com.ss.android.socialbase.downloader.constants.EnqueueType;
import com.ss.android.socialbase.downloader.constants.ExecutorGroup;
import com.ss.android.socialbase.downloader.depend.IDownloadCompleteHandler;
import com.ss.android.socialbase.downloader.depend.IDownloadDepend;
import com.ss.android.socialbase.downloader.depend.IDownloadDiskSpaceHandler;
import com.ss.android.socialbase.downloader.depend.IDownloadFileUriProvider;
import com.ss.android.socialbase.downloader.depend.IDownloadListener;
import com.ss.android.socialbase.downloader.depend.IDownloadMonitorDepend;
import com.ss.android.socialbase.downloader.depend.INotificationClickCallback;
import com.ss.android.socialbase.downloader.downloader.IChunkAdjustCalculator;
import com.ss.android.socialbase.downloader.downloader.IChunkCntCalculator;
import com.ss.android.socialbase.downloader.downloader.IRetryDelayTimeCalculator;
import com.ss.android.socialbase.downloader.model.HttpHeader;
import com.ss.android.socialbase.downloader.notification.AbsNotificationItem;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AppTaskBuilder {
    private Activity activity;
    private IChunkAdjustCalculator adjustCalculator;
    private int antiHijackErrorCode;
    private IAppDownloadEventListener appDownloadEventListener;
    private boolean autoResumed;
    private int backUpUrlRetryCount;
    private List<String> backUpUrls;
    private IChunkCntCalculator chunkStrategy;
    private Context context;
    private IDownloadDepend depend;
    private IDownloadDiskSpaceHandler diskSpaceHandler;
    private boolean distinctDirectory;
    private JSONObject downloadSetting;
    private int executorGroup;
    private long expectFileLength;
    private String extra;
    private int[] extraMonitorStatus;
    private IDownloadFileUriProvider fileUriProvider;
    private boolean force;
    private List<HttpHeader> headers;
    private String iconUrl;
    private IDownloadListener mainThreadListener;
    private int maxProgressCount;
    private String md5;
    private IDownloadMonitorDepend monitorDepend;
    private String monitorScene;
    private String name;
    private boolean needChunkDowngradeRetry;
    private boolean needHttpsToHttpRetry;
    private boolean needIndependentProcess;
    private boolean needNotificationPermission;
    private boolean needRetryDelay;
    private boolean needReuseChunkRunnable;
    private boolean needReuseFirstConnection;
    private INotificationClickCallback notificationClickCallback;
    private AbsNotificationItem notificationItem;
    private IDownloadListener notificationListener;
    private String packageName;
    private String retryDelayTimeArray;
    private IRetryDelayTimeCalculator retryDelayTimeCalculator;
    private String saveName;
    private String savePath;
    private boolean showNotificationForAutoResumed;
    private long throttleNetSpeed;
    private String url;
    private boolean showNotification = true;
    private boolean autoInstallWithoutNotification = false;
    private boolean autoInstallWithNotification = true;
    private boolean needWifi = false;
    private String mimeType = "application/vnd.android.package-archive";
    private int retryCount = 5;
    private boolean needDefaultHttpServiceBackUp = true;
    private EnqueueType enqueueType = EnqueueType.ENQUEUE_NONE;
    private int minProgressTimeMsInterval = Constants.MIN_PROGRESS_TIME;
    private boolean headConnectionAvailable = true;
    private List<IDownloadCompleteHandler> downloadCompleteHandlers = new ArrayList();
    private boolean autoInstall = true;
    private boolean needSDKMonitor = true;

    public AppTaskBuilder(@NonNull Context context, @NonNull String str) {
        this.context = context.getApplicationContext();
        this.url = str;
    }

    public Activity getActivity() {
        return this.activity;
    }

    public Context getContext() {
        return this.context;
    }

    public String getUrl() {
        return this.url;
    }

    public String getName() {
        return this.name;
    }

    public String getSavePath() {
        return this.savePath;
    }

    public List<HttpHeader> getHeaders() {
        return this.headers;
    }

    public boolean isShowNotification() {
        return this.showNotification;
    }

    public boolean isAutoInstallWithoutNotification() {
        return this.autoInstallWithoutNotification;
    }

    public boolean isAutoInstallWithNotification() {
        return this.autoInstallWithNotification;
    }

    public boolean isNeedWifi() {
        return this.needWifi;
    }

    public IDownloadListener getMainThreadListener() {
        return this.mainThreadListener;
    }

    public IDownloadListener getNotificationListener() {
        return this.notificationListener;
    }

    public String getExtra() {
        return this.extra;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public boolean isNeedHttpsToHttpRetry() {
        return this.needHttpsToHttpRetry;
    }

    public AbsNotificationItem getNotificationItem() {
        return this.notificationItem;
    }

    public IChunkCntCalculator getChunkStrategy() {
        return this.chunkStrategy;
    }

    public IChunkAdjustCalculator getChunkAdjustCalculator() {
        return this.adjustCalculator;
    }

    public boolean isAutoResumed() {
        return this.autoResumed;
    }

    public boolean isShowNotificationForAutoResumed() {
        return this.showNotificationForAutoResumed;
    }

    public int getAntiHijackErrorCode() {
        return this.antiHijackErrorCode;
    }

    public void setAntiHijackErrorCode(int i) {
        this.antiHijackErrorCode = i;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getMd5() {
        return this.md5;
    }

    public long getExpectFileLength() {
        return this.expectFileLength;
    }

    public int getRetryCount() {
        return this.retryCount;
    }

    public int getBackUpUrlRetryCount() {
        return this.backUpUrlRetryCount;
    }

    public boolean isNeedRetryDelay() {
        return this.needRetryDelay;
    }

    public String getRetryDelayTimeArray() {
        return this.retryDelayTimeArray;
    }

    public boolean isNeedDefaultHttpServiceBackUp() {
        return this.needDefaultHttpServiceBackUp;
    }

    public boolean isNeedReuseFirstConnection() {
        return this.needReuseFirstConnection;
    }

    public IRetryDelayTimeCalculator getRetryDelayTimeCalculator() {
        return this.retryDelayTimeCalculator;
    }

    public int getMinProgressTimeMsInterval() {
        return this.minProgressTimeMsInterval;
    }

    public int getMaxProgressCount() {
        return this.maxProgressCount;
    }

    public boolean isNeedReuseChunkRunnable() {
        return this.needReuseChunkRunnable;
    }

    public boolean isNeedIndependentProcess() {
        return this.needIndependentProcess;
    }

    public boolean isHeadConnectionAvailable() {
        return this.headConnectionAvailable;
    }

    public boolean isNeedChunkDowngradeRetry() {
        return this.needChunkDowngradeRetry;
    }

    public EnqueueType getEnqueueType() {
        return this.enqueueType;
    }

    public boolean isForce() {
        return this.force;
    }

    public String getSaveName() {
        return this.saveName;
    }

    public IDownloadMonitorDepend getMonitorDepend() {
        return this.monitorDepend;
    }

    public IDownloadDepend getDepend() {
        return this.depend;
    }

    public IAppDownloadEventListener getAppDownloadEventListener() {
        return this.appDownloadEventListener;
    }

    public IDownloadFileUriProvider getFileUriProvider() {
        return this.fileUriProvider;
    }

    public INotificationClickCallback getNotificationClickCallback() {
        return this.notificationClickCallback;
    }

    public List<IDownloadCompleteHandler> getDownloadCompleteHandlers() {
        return this.downloadCompleteHandlers;
    }

    public boolean isNeedNotificationPermission() {
        return this.needNotificationPermission;
    }

    public int getExecutorGroup() {
        return this.executorGroup;
    }

    public long getThrottleNetSpeed() {
        return this.throttleNetSpeed;
    }

    public boolean isNeedSDKMonitor() {
        return this.needSDKMonitor;
    }

    public String getMonitorScene() {
        return this.monitorScene;
    }

    public int[] getExtraMonitorStatus() {
        return this.extraMonitorStatus;
    }

    public boolean isDistinctDirectory() {
        return this.distinctDirectory;
    }

    public boolean isAutoInstall() {
        return this.autoInstall;
    }

    public AppTaskBuilder name(String str) {
        this.name = str;
        return this;
    }

    public AppTaskBuilder saveName(String str) {
        this.saveName = str;
        return this;
    }

    public AppTaskBuilder savePath(@NonNull String str) {
        this.savePath = str;
        return this;
    }

    public AppTaskBuilder headers(List<HttpHeader> list) {
        this.headers = list;
        return this;
    }

    public AppTaskBuilder showNotification(boolean z) {
        this.showNotification = z;
        return this;
    }

    @Deprecated
    public AppTaskBuilder autoInstallWithNotification(boolean z) {
        this.autoInstallWithNotification = z;
        return this;
    }

    public AppTaskBuilder autoInstallWithoutNotification(boolean z) {
        this.autoInstallWithoutNotification = z;
        return this;
    }

    public AppTaskBuilder needWifi(boolean z) {
        this.needWifi = z;
        return this;
    }

    public AppTaskBuilder mainThreadListener(IDownloadListener iDownloadListener) {
        this.mainThreadListener = iDownloadListener;
        return this;
    }

    public AppTaskBuilder notificationListener(IDownloadListener iDownloadListener) {
        this.notificationListener = iDownloadListener;
        return this;
    }

    public AppTaskBuilder extra(String str) {
        this.extra = str;
        return this;
    }

    public AppTaskBuilder mimeType(String str) {
        this.mimeType = str;
        return this;
    }

    public AppTaskBuilder needHttpsToHttpRetry(boolean z) {
        this.needHttpsToHttpRetry = z;
        return this;
    }

    public AppTaskBuilder chunkStrategy(IChunkCntCalculator iChunkCntCalculator) {
        this.chunkStrategy = iChunkCntCalculator;
        return this;
    }

    public AppTaskBuilder adjustChunkCalculator(IChunkAdjustCalculator iChunkAdjustCalculator) {
        this.adjustCalculator = iChunkAdjustCalculator;
        return this;
    }

    public AppTaskBuilder retryDelayTimeCalculator(IRetryDelayTimeCalculator iRetryDelayTimeCalculator) {
        this.retryDelayTimeCalculator = iRetryDelayTimeCalculator;
        return this;
    }

    public AppTaskBuilder notificationItem(AbsNotificationItem absNotificationItem) {
        this.notificationItem = absNotificationItem;
        return this;
    }

    public AppTaskBuilder autoResumed(boolean z) {
        this.autoResumed = z;
        return this;
    }

    public AppTaskBuilder showNotificationForAutoResumed(boolean z) {
        this.showNotificationForAutoResumed = z;
        return this;
    }

    public AppTaskBuilder packageName(String str) {
        this.packageName = str;
        return this;
    }

    public AppTaskBuilder md5(String str) {
        this.md5 = str;
        return this;
    }

    public AppTaskBuilder expectFileLength(long j) {
        this.expectFileLength = j;
        return this;
    }

    public AppTaskBuilder retryCount(int i) {
        this.retryCount = i;
        return this;
    }

    public AppTaskBuilder backUpUrlRetryCount(int i) {
        this.backUpUrlRetryCount = i;
        return this;
    }

    public AppTaskBuilder needRetryDelay(boolean z) {
        this.needRetryDelay = z;
        return this;
    }

    public AppTaskBuilder retryDelayTimeArray(String str) {
        this.retryDelayTimeArray = str;
        return this;
    }

    public AppTaskBuilder needChunkDowngradeRetry(boolean z) {
        this.needChunkDowngradeRetry = z;
        return this;
    }

    public AppTaskBuilder needDefaultHttpServiceBackUp(boolean z) {
        this.needDefaultHttpServiceBackUp = z;
        return this;
    }

    public AppTaskBuilder needReuseFirstConnection(boolean z) {
        this.needReuseFirstConnection = z;
        return this;
    }

    public AppTaskBuilder needReuseChunkRunnable(boolean z) {
        this.needReuseChunkRunnable = z;
        return this;
    }

    public AppTaskBuilder needIndependentProcess(boolean z) {
        this.needIndependentProcess = z;
        return this;
    }

    public AppTaskBuilder minProgressTimeMsInterval(int i) {
        this.minProgressTimeMsInterval = i;
        return this;
    }

    public AppTaskBuilder maxProgressCount(int i) {
        this.maxProgressCount = i;
        return this;
    }

    public AppTaskBuilder headConnectionAvailable(boolean z) {
        this.headConnectionAvailable = z;
        return this;
    }

    public AppTaskBuilder enqueueType(EnqueueType enqueueType) {
        this.enqueueType = enqueueType;
        return this;
    }

    public AppTaskBuilder force(boolean z) {
        this.force = z;
        return this;
    }

    public AppTaskBuilder depend(IDownloadDepend iDownloadDepend) {
        this.depend = iDownloadDepend;
        return this;
    }

    public AppTaskBuilder monitorDepend(IDownloadMonitorDepend iDownloadMonitorDepend) {
        this.monitorDepend = iDownloadMonitorDepend;
        return this;
    }

    public AppTaskBuilder appDownloadEventListener(IAppDownloadEventListener iAppDownloadEventListener) {
        this.appDownloadEventListener = iAppDownloadEventListener;
        return this;
    }

    public AppTaskBuilder fileUriProvider(IDownloadFileUriProvider iDownloadFileUriProvider) {
        this.fileUriProvider = iDownloadFileUriProvider;
        return this;
    }

    public AppTaskBuilder notificationClickCallback(INotificationClickCallback iNotificationClickCallback) {
        this.notificationClickCallback = iNotificationClickCallback;
        return this;
    }

    public AppTaskBuilder activity(Activity activity) {
        this.activity = activity;
        return this;
    }

    public AppTaskBuilder needNotificationPermission(boolean z) {
        this.needNotificationPermission = z;
        return this;
    }

    public AppTaskBuilder needSDKMonitor(boolean z) {
        this.needSDKMonitor = z;
        return this;
    }

    public AppTaskBuilder monitorScene(String str) {
        this.monitorScene = str;
        return this;
    }

    public AppTaskBuilder extraMonitorStatus(int[] iArr) {
        this.extraMonitorStatus = iArr;
        return this;
    }

    public AppTaskBuilder iconUrl(String str) {
        this.iconUrl = str;
        return this;
    }

    public String getIconUrl() {
        return this.iconUrl;
    }

    public AppTaskBuilder executorGroup(@ExecutorGroup int i) {
        this.executorGroup = i;
        return this;
    }

    public List<String> getBackUpUrls() {
        return this.backUpUrls;
    }

    public AppTaskBuilder backUpUrls(List<String> list) {
        this.backUpUrls = list;
        return this;
    }

    public IDownloadDiskSpaceHandler getDiskSpaceHandler() {
        return this.diskSpaceHandler;
    }

    public AppTaskBuilder diskSpaceHandler(IDownloadDiskSpaceHandler iDownloadDiskSpaceHandler) {
        this.diskSpaceHandler = iDownloadDiskSpaceHandler;
        return this;
    }

    public JSONObject getDownloadSetting() {
        return this.downloadSetting;
    }

    public AppTaskBuilder downloadSetting(JSONObject jSONObject) {
        this.downloadSetting = jSONObject;
        return this;
    }

    public AppTaskBuilder addDownloadCompleteHandler(IDownloadCompleteHandler iDownloadCompleteHandler) {
        synchronized (this.downloadCompleteHandlers) {
            if (iDownloadCompleteHandler != null) {
                try {
                    if (!this.downloadCompleteHandlers.contains(iDownloadCompleteHandler)) {
                        this.downloadCompleteHandlers.add(iDownloadCompleteHandler);
                        return this;
                    }
                } finally {
                }
            }
            return this;
        }
    }

    public AppTaskBuilder throttleNetSpeed(long j) {
        this.throttleNetSpeed = j;
        return this;
    }

    public AppTaskBuilder distinctDirectory(boolean z) {
        this.distinctDirectory = z;
        return this;
    }

    public AppTaskBuilder autoInstall(boolean z) {
        this.autoInstall = z;
        return this;
    }
}
