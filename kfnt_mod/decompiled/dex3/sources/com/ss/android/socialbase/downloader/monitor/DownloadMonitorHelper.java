package com.ss.android.socialbase.downloader.monitor;

import android.net.Uri;
import android.text.TextUtils;
import com.sigmob.sdk.common.mta.PointCategory;
import com.ss.android.downloadlib.constants.EventConstants;
import com.ss.android.socialbase.downloader.constants.DBDefinition;
import com.ss.android.socialbase.downloader.constants.DownloadErrorCode;
import com.ss.android.socialbase.downloader.constants.DownloadStatus;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import com.ss.android.socialbase.downloader.depend.AbsDownloadMonitorDepend;
import com.ss.android.socialbase.downloader.depend.IDownloadDepend;
import com.ss.android.socialbase.downloader.depend.IDownloadMonitorDepend;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.downloader.IMonitorConfig;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.exception.DownloadTTNetException;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.model.DownloadTask;
import com.ss.android.socialbase.downloader.network.AbsDownloadHttpConnection;
import com.ss.android.socialbase.downloader.network.IDefaultDownloadHttpConnection;
import com.ss.android.socialbase.downloader.network.IDownloadHttpConnection;
import com.ss.android.socialbase.downloader.segment.Segment;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DownloadMonitorHelper {
    private static final String DEFAULT_MONITOR_SCENE = "default";

    public static void monitorSend(DownloadTask downloadTask, BaseException baseException, int i) {
        if (downloadTask == null) {
            return;
        }
        try {
            DownloadInfo downloadInfo = downloadTask.getDownloadInfo();
            if (downloadInfo == null) {
                return;
            }
            IDownloadMonitorDepend monitorDepend = downloadTask.getMonitorDepend();
            boolean zIsMonitorStatus = DownloadStatus.isMonitorStatus(i);
            if (!zIsMonitorStatus && !(zIsMonitorStatus = isMonitorStatus(downloadInfo.getExtraMonitorStatus(), i)) && monitorDepend != null && (monitorDepend instanceof AbsDownloadMonitorDepend)) {
                zIsMonitorStatus = isMonitorStatus(((AbsDownloadMonitorDepend) monitorDepend).getAdditionalMonitorStatus(), i);
            }
            if (zIsMonitorStatus) {
                try {
                    IDownloadDepend depend = downloadTask.getDepend();
                    if (depend != null) {
                        depend.monitorLogSend(downloadInfo, baseException, i);
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                monitorSendWithTaskMonitor(monitorDepend, downloadInfo, baseException, i);
                monitorSendWithGlobalSdkMonitor(DownloadComponentManager.getDownloadMonitorListener(), downloadInfo, baseException, i);
            }
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    private static boolean isMonitorStatus(int[] iArr, int i) {
        if (iArr != null && iArr.length > 0) {
            for (int i2 : iArr) {
                if (i == i2) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void monitorSendWithTaskMonitor(IDownloadMonitorDepend iDownloadMonitorDepend, DownloadInfo downloadInfo, BaseException baseException, int i) {
        if (iDownloadMonitorDepend == null) {
            return;
        }
        try {
            String eventPage = iDownloadMonitorDepend.getEventPage();
            if (TextUtils.isEmpty(eventPage)) {
                eventPage = "default";
            }
            JSONObject monitorJson = getMonitorJson(eventPage, downloadInfo, baseException, i);
            if (monitorJson == null) {
                monitorJson = new JSONObject();
            }
            iDownloadMonitorDepend.monitorLogSend(monitorJson);
        } catch (Throwable unused) {
        }
    }

    public static void monitorSendWithGlobalSdkMonitor(IDownloadMonitorListener iDownloadMonitorListener, DownloadInfo downloadInfo, BaseException baseException, int i) {
        if (iDownloadMonitorListener == null || !downloadInfo.isNeedSDKMonitor() || TextUtils.isEmpty(downloadInfo.getMonitorScene())) {
            return;
        }
        try {
            JSONObject monitorJson = getMonitorJson(downloadInfo.getMonitorScene(), downloadInfo, baseException, i);
            if (monitorJson == null) {
                monitorJson = new JSONObject();
            }
            if (i == -1) {
                monitorJson.put("status", baseException.getErrorCode());
                iDownloadMonitorListener.monitorEvent("download_failed", monitorJson, null, null);
            } else {
                putMonitorJsonStatus(i, monitorJson, downloadInfo);
                iDownloadMonitorListener.monitorEvent("download_common", monitorJson, null, null);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static void putMonitorJsonStatus(int i, JSONObject jSONObject, DownloadInfo downloadInfo) throws JSONException {
        String str;
        if (i == 0) {
            str = "download_create";
        } else if (i == 2) {
            str = PointCategory.DOWNLOAD_START;
        } else if (i != 6) {
            switch (i) {
                case -5:
                    str = "download_uncomplete";
                    break;
                case -4:
                    str = EventConstants.Label.DOWNLOAD_CANCEL;
                    break;
                case -3:
                    str = "download_success";
                    double downloadSpeed = downloadInfo.getDownloadSpeed();
                    if (downloadSpeed >= 0.0d) {
                        jSONObject.put("download_speed", downloadSpeed);
                        break;
                    }
                    break;
                case -2:
                    str = "download_pause";
                    break;
                default:
                    str = "";
                    break;
            }
        } else {
            str = "download_first_start";
        }
        jSONObject.put("status", str);
    }

    public static String parseDevicePostfix(String str) {
        try {
            return TextUtils.isDigitsOnly(str) ? String.valueOf(Long.valueOf(str).longValue() % 100) : "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static JSONObject getMonitorJson(String str, DownloadInfo downloadInfo, BaseException baseException, int i) throws JSONException {
        JSONObject jSONObject;
        JSONObject jSONObject2;
        int updateVersion;
        String host;
        try {
            jSONObject = new JSONObject();
            try {
                IMonitorConfig monitorConfig = DownloadComponentManager.getMonitorConfig();
                String deviceId = "";
                String devicePostfix = "";
                String appId = "";
                String requestLog = "";
                if (monitorConfig != null) {
                    deviceId = monitorConfig.getDeviceId();
                    devicePostfix = parseDevicePostfix(deviceId);
                    appId = monitorConfig.getAppId();
                    updateVersion = monitorConfig.getUpdateVersion();
                } else {
                    updateVersion = 0;
                }
                if (baseException != null && (baseException instanceof DownloadTTNetException)) {
                    requestLog = ((DownloadTTNetException) baseException).getRequestLog();
                }
                jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_PAGE, str);
                jSONObject.put("app_id", appId);
                jSONObject.put("device_id", deviceId);
                jSONObject.put(MonitorConstants.EXTRA_DEVICE_ID_POSTFIX, devicePostfix);
                jSONObject.put(MonitorConstants.EXTRA_UPDATE_VERSION, updateVersion);
                jSONObject.put("download_status", i);
                if (downloadInfo != null) {
                    jSONObject.put(DownloadSettingKeys.SETTING_TAG, DownloadSetting.obtain(downloadInfo.getId()).optString(DownloadSettingKeys.SETTING_TAG));
                    jSONObject.put("download_id", downloadInfo.getId());
                    jSONObject.put("name", downloadInfo.getName());
                    jSONObject.put("url", downloadInfo.getUrl());
                    jSONObject.put("save_path", downloadInfo.getSavePath());
                    jSONObject.put("download_time", downloadInfo.getDownloadTime());
                    jSONObject.put("cur_bytes", downloadInfo.getCurBytes());
                    jSONObject.put("total_bytes", downloadInfo.getTotalBytes());
                    jSONObject.put("network_quality", downloadInfo.getNetworkQuality());
                    jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_ONLY_WIFI, downloadInfo.isOnlyWifi() ? 1 : 0);
                    jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_NEED_HTTPS_DEGRADE, downloadInfo.isNeedHttpsToHttpRetry() ? 1 : 0);
                    jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_HTTPS_DEGRADE_RETRY_USED, downloadInfo.isHttpsToHttpRetryUsed() ? 1 : 0);
                    jSONObject.put("md5", downloadInfo.getMd5() == null ? "" : downloadInfo.getMd5());
                    jSONObject.put("chunk_count", downloadInfo.getChunkCount());
                    jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_IS_FORCE, downloadInfo.isForce() ? 1 : 0);
                    jSONObject.put("retry_count", downloadInfo.getRetryCount());
                    jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_CUR_RETRY_TIME, downloadInfo.getCurRetryTime());
                    jSONObject.put("need_retry_delay", downloadInfo.isNeedRetryDelay() ? 1 : 0);
                    jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_NEED_REUSE_FIRST_CONNECTION, downloadInfo.isNeedReuseFirstConnection() ? 1 : 0);
                    jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_DEFAULT_HTTP_SERVICE_BACKUP, downloadInfo.isNeedDefaultHttpServiceBackUp() ? 1 : 0);
                    jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_RETRY_DELAY_STATUS, downloadInfo.getRetryDelayStatus().ordinal());
                    jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_BACKUP_URL_USED, downloadInfo.isBackUpUrlUsed() ? 1 : 0);
                    jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_BYTE_ERROR_RETRY_STATUS, downloadInfo.getByteInvalidRetryStatus().ordinal());
                    jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_FORBIDDEN_HANDLER_STATUS, downloadInfo.getAsyncHandleStatus().ordinal());
                    jSONObject.put("need_independent_process", downloadInfo.isNeedIndependentProcess() ? 1 : 0);
                    jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_HEAD_CONNECTION_ERROR_MSG, downloadInfo.getHeadConnectionException() != null ? downloadInfo.getHeadConnectionException() : "");
                    jSONObject.put("extra", downloadInfo.getExtra() != null ? downloadInfo.getExtra() : "");
                    jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_ADD_LISTENER_TO_SAME_TASK, downloadInfo.isAddListenerToSameTask() ? 1 : 0);
                    jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_BACKUP_URL_COUNT, downloadInfo.getBackUpUrls() != null ? downloadInfo.getBackUpUrls().size() : 0);
                    jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_CUR_BACKUP_URL_INDEX, downloadInfo.getBackUpUrls() != null ? downloadInfo.getCurBackUpUrlIndex() : -1);
                    jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_FORBIDDEN_URLS, downloadInfo.getForbiddenBackupUrls() != null ? downloadInfo.getForbiddenBackupUrls().toString() : "");
                    jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_TASK_ID, TextUtils.isEmpty(downloadInfo.getTaskId()) ? "" : downloadInfo.getTaskId());
                    try {
                        String path = "";
                        String lastPathSegment = "";
                        String url = downloadInfo.getUrl();
                        if (TextUtils.isEmpty(url)) {
                            host = "";
                        } else {
                            Uri uri = Uri.parse(url);
                            host = uri.getHost();
                            path = uri.getPath();
                            lastPathSegment = uri.getLastPathSegment();
                            if (!TextUtils.isEmpty(path) && !TextUtils.isEmpty(lastPathSegment)) {
                                try {
                                    path = path.substring(0, path.length() - lastPathSegment.length());
                                } catch (Throwable th) {
                                    th.printStackTrace();
                                }
                            }
                        }
                        jSONObject.put(MonitorConstants.URL_HOST, host);
                        jSONObject.put(MonitorConstants.URL_PATH, path);
                        jSONObject.put(MonitorConstants.URL_LAST_PATH_SEGMENT, lastPathSegment);
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                }
                jSONObject.put("error_code", baseException != null ? baseException.getErrorCode() : 0);
                jSONObject.put("error_msg", baseException != null ? baseException.getErrorMessage() : "");
                jSONObject.put(MonitorConstants.REQUEST_LOG, requestLog);
                jSONObject2 = jSONObject;
            } catch (JSONException e) {
                e = e;
                e.printStackTrace();
                jSONObject2 = jSONObject;
                return jSONObject2;
            }
        } catch (JSONException e2) {
            e = e2;
            jSONObject = null;
        }
        return jSONObject2;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x003d A[PHI: r6
  0x003d: PHI (r6v8 int) = (r6v0 int), (r6v1 int) binds: [B:14:0x0023, B:21:0x0032] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x007e A[PHI: r6
  0x007e: PHI (r6v7 int) = (r6v3 int), (r6v3 int), (r6v5 int), (r6v3 int) binds: [B:33:0x0055, B:37:0x0064, B:36:0x0061, B:26:0x0044] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void monitorDownloadConnect(@androidx.annotation.Nullable com.ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection r13, java.lang.String r14, java.lang.String r15, long r16, java.lang.String r18, int r19, java.io.IOException r20, com.ss.android.socialbase.downloader.model.DownloadInfo r21) {
        /*
            Method dump skipped, instructions count: 325
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.socialbase.downloader.monitor.DownloadMonitorHelper.monitorDownloadConnect(com.ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection, java.lang.String, java.lang.String, long, java.lang.String, int, java.io.IOException, com.ss.android.socialbase.downloader.model.DownloadInfo):void");
    }

    public static void monitorDownloadIO(DownloadSetting downloadSetting, DownloadInfo downloadInfo, String str, IDownloadHttpConnection iDownloadHttpConnection, boolean z, boolean z2, BaseException baseException, long j, long j2, boolean z3, long j3, long j4, long j5, JSONObject jSONObject) {
        monitorIO(MonitorConstants.DOWNLOAD_IO, downloadSetting.optInt(DownloadSettingKeys.MONITOR_DOWNLOAD_IO), downloadSetting, downloadInfo, str, null, null, iDownloadHttpConnection, z, z2, baseException, j, j2, z3, j3, j4, j5, null);
    }

    public static void monitorSegmentIO(DownloadSetting downloadSetting, DownloadInfo downloadInfo, String str, String str2, String str3, boolean z, IDownloadHttpConnection iDownloadHttpConnection, BaseException baseException, long j, long j2) {
        monitorIO(MonitorConstants.SEGMENT_IO, downloadSetting.optInt(DownloadSettingKeys.MONITOR_SEGMENT_IO), downloadSetting, downloadInfo, str, str2, str3, iDownloadHttpConnection, z, false, baseException, j, j2, false, -1L, -1L, -1L, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r18v0 */
    /* JADX WARN: Type inference failed for: r18v1 */
    /* JADX WARN: Type inference failed for: r18v2 */
    private static void monitorIO(String str, int i, DownloadSetting downloadSetting, DownloadInfo downloadInfo, String str2, String str3, String str4, IDownloadHttpConnection iDownloadHttpConnection, boolean z, boolean z2, BaseException baseException, long j, long j2, boolean z3, long j3, long j4, long j5, JSONObject jSONObject) {
        String errorMessage;
        int i2;
        long j6;
        String requestLog;
        int i3;
        int i4;
        String str5;
        int i5;
        if (i <= 0 || j2 <= 0) {
            return;
        }
        try {
            Uri uri = Uri.parse(str2);
            String host = uri.getHost();
            String path = uri.getPath();
            String lastPathSegment = uri.getLastPathSegment();
            if (!TextUtils.isEmpty(path) && !TextUtils.isEmpty(lastPathSegment)) {
                try {
                    path = path.substring(0, path.length() - lastPathSegment.length());
                } catch (Throwable unused) {
                }
            }
            if (z) {
                errorMessage = null;
                i2 = 1;
            } else if (z2) {
                errorMessage = null;
                i2 = 2;
            } else if (baseException != null) {
                int errorCode = !DownloadUtils.isNetworkConnected(DownloadComponentManager.getAppContext()) ? DownloadErrorCode.ERROR_NETWORK_NOT_AVAILABLE : baseException.getErrorCode();
                errorMessage = baseException.getErrorMessage();
                i2 = errorCode;
            } else {
                errorMessage = null;
                i2 = 0;
            }
            JSONObject jSONObject2 = new JSONObject();
            if (iDownloadHttpConnection != null) {
                int i6 = iDownloadHttpConnection instanceof IDefaultDownloadHttpConnection ? 0 : 1;
                String responseHeaderField = iDownloadHttpConnection.getResponseHeaderField(DownloadUtils.X_CACHE);
                ?? Contains = TextUtils.isEmpty(responseHeaderField) ? -1 : responseHeaderField.toLowerCase().contains("hit");
                if (downloadSetting.optInt(DownloadSettingKeys.MONITOR_SLA, 1) == 1 && !z && !z2 && (iDownloadHttpConnection instanceof AbsDownloadHttpConnection)) {
                    ((AbsDownloadHttpConnection) iDownloadHttpConnection).monitorNetworkInfo(jSONObject2, true);
                }
                j6 = j;
                requestLog = iDownloadHttpConnection instanceof AbsDownloadHttpConnection ? ((AbsDownloadHttpConnection) iDownloadHttpConnection).getRequestLog() : "";
                i3 = i6;
                i4 = Contains;
            } else {
                j6 = j;
                requestLog = "";
                i3 = -1;
                i4 = -1;
            }
            double d = j6 / 1048576.0d;
            double d2 = j2;
            double nanos = d2 / TimeUnit.SECONDS.toNanos(1L);
            jSONObject2.put(DownloadSettingKeys.SETTING_TAG, downloadSetting.optString(DownloadSettingKeys.SETTING_TAG));
            jSONObject2.put(MonitorConstants.URL_HOST, host);
            jSONObject2.putOpt(MonitorConstants.HOST_IP, str3);
            jSONObject2.putOpt(MonitorConstants.HOST_REAL_IP, str4);
            jSONObject2.put(MonitorConstants.URL_PATH, path);
            jSONObject2.put(MonitorConstants.URL_LAST_PATH_SEGMENT, lastPathSegment);
            jSONObject2.put(MonitorConstants.NET_LIB, i3);
            jSONObject2.put(MonitorConstants.HIT_CDN_CACHE, i4);
            jSONObject2.put("status_code", i2);
            jSONObject2.put(MonitorConstants.REQUEST_LOG, requestLog);
            if (errorMessage != null) {
                jSONObject2.put("error_msg", DownloadUtils.getFixLengthString(errorMessage, downloadSetting.optInt("exception_msg_length", 500)));
            }
            jSONObject2.put(MonitorConstants.DOWNLOAD_SEC, nanos);
            jSONObject2.put(MonitorConstants.DOWNLOAD_MB, d);
            if (nanos > 0.0d) {
                jSONObject2.put("download_speed", d / nanos);
            }
            if (z3) {
                jSONObject2.put(MonitorConstants.RW_READ_TIME, j3 / d2);
                jSONObject2.put(MonitorConstants.RW_WRITE_TIME, j4 / d2);
                jSONObject2.put(MonitorConstants.RW_SYNC_TIME, j5 / d2);
            }
            jSONObject2.put(MonitorConstants.PKG_NAME, downloadInfo.getPackageName());
            jSONObject2.put("name", downloadInfo.getTitle());
            if (i == 1 || i == 3) {
                IDownloadMonitorListener downloadMonitorListener = DownloadComponentManager.getDownloadMonitorListener();
                if (downloadMonitorListener != null) {
                    str5 = str;
                    downloadMonitorListener.monitorEvent(str5, jSONObject2, null, null);
                } else {
                    str5 = str;
                }
                i5 = 2;
            } else {
                i5 = 2;
                str5 = str;
            }
            if (i == i5 || i == 3) {
                DownloadComponentManager.getEventListener().onEvent(downloadInfo.getId(), str5, jSONObject2);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void monitorSegmentsError(DownloadInfo downloadInfo, List<Segment> list) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(DBDefinition.SEGMENT_TABLE_NAME, Segment.toString(list));
            jSONObject.put("cur_bytes", downloadInfo.getCurBytes());
            jSONObject.put("total_bytes", downloadInfo.getTotalBytes());
            InnerEventListener eventListener = DownloadComponentManager.getEventListener();
            if (eventListener != null) {
                eventListener.onEvent(downloadInfo.getId(), "segments_error", jSONObject);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
