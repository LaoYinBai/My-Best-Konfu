package com.bytedance.pangle.download;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationManagerCompat;
import anet.channel.entity.ConnType;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.b.b;
import com.bytedance.pangle.helper.PluginDirHelper;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.plugin.Plugin;
import com.bytedance.pangle.plugin.PluginManager;
import com.ss.android.socialbase.downloader.depend.AbsDownloadListener;
import com.ss.android.socialbase.downloader.depend.IDownloadInterceptor;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.model.DownloadTask;
import java.io.File;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e {

    /* renamed from: a, reason: collision with root package name */
    private static volatile e f4571a;

    public static e a() {
        if (f4571a == null) {
            synchronized (e.class) {
                if (f4571a == null) {
                    f4571a = new e();
                }
            }
        }
        return f4571a;
    }

    private e() {
    }

    public final void a(@NonNull String str, @NonNull final String str2, final int i, @NonNull final String str3, boolean z, @NonNull List<String> list, final int i2, @Nullable final ZeusPluginListener zeusPluginListener) {
        AbsDownloadListener absDownloadListener = new AbsDownloadListener() { // from class: com.bytedance.pangle.download.e.1
            final /* synthetic */ boolean f = true;

            public final void onProgress(DownloadInfo downloadInfo) {
                if (zeusPluginListener != null) {
                    zeusPluginListener.onEvent(11, ((downloadInfo.getCurBytes() * 1.0f) / downloadInfo.getTotalBytes()) + "%");
                }
            }

            public final void onStart(DownloadInfo downloadInfo) throws JSONException {
                ZeusLogger.d(ZeusLogger.TAG_DOWNLOAD, "download start : " + str2 + " : " + i);
                e.a(com.bytedance.pangle.b.b.f4524c, b.a.d, str2, i, -1L, null);
                if (zeusPluginListener != null) {
                    zeusPluginListener.onEvent(10, "start download " + str2);
                }
            }

            public final void onSuccessed(DownloadInfo downloadInfo) throws Throwable {
                if (downloadInfo != null) {
                    ZeusLogger.d(ZeusLogger.TAG_DOWNLOAD, "download onSuccessed : " + str2 + " : " + i);
                    e.a(i2, downloadInfo, str3, str2, i, this.f, zeusPluginListener);
                }
            }

            public final void onFailed(DownloadInfo downloadInfo, BaseException baseException) throws JSONException {
                ZeusLogger.e(ZeusLogger.TAG_DOWNLOAD, "download onFailed : " + str2 + " : " + i, (Throwable) baseException);
                e.a(com.bytedance.pangle.b.b.d, e.a(baseException), str2, i, -1L, baseException);
                if (zeusPluginListener != null) {
                    zeusPluginListener.onEvent(13, "download failed," + baseException.getErrorMessage());
                }
            }
        };
        IDownloadInterceptor iDownloadInterceptor = new IDownloadInterceptor() { // from class: com.bytedance.pangle.download.e.2
            public final boolean intercepte() {
                int iA = e.a(str2);
                if (i > iA && !e.b(str2, i)) {
                    return false;
                }
                ZeusLogger.i(ZeusLogger.TAG_DOWNLOAD, "download intercept : " + str2 + " : " + i);
                if (zeusPluginListener == null) {
                    return true;
                }
                zeusPluginListener.onEvent(13, i <= iA ? "EVENT_INTERCEPT_ALREADY_INSTALL" : "EVENT_INTERCEPT_DOWNLOAD_FILE_EXISTS");
                return true;
            }
        };
        DownloadTask downloadTaskUrl = Downloader.with(Zeus.getAppApplication()).url(str);
        String str4 = "";
        if (i2 == 1) {
            str4 = "sc";
        } else if (i2 == 0) {
            str4 = ConnType.PK_AUTO;
        }
        downloadTaskUrl.name(String.format("%s_%s_%s.jar", str4, str2, str3)).onlyWifi(z).savePath(PluginDirHelper.getDownloadDir()).retryCount(5).mimeType("mime_type_plugin").backUpUrls(list).subThreadListener(absDownloadListener).interceptor(iDownloadInterceptor).download();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(@NonNull String str, int i) throws PackageManager.NameNotFoundException {
        try {
            for (File file : new File(PluginDirHelper.getDownloadDir()).listFiles()) {
                if (file.getName().contains(str)) {
                    PackageInfo packageInfo = Zeus.getAppApplication().getPackageManager().getPackageInfo(Zeus.getAppApplication().getPackageName(), 0);
                    if (TextUtils.equals(packageInfo.packageName, str) && packageInfo.versionCode == i) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            ZeusLogger.e(ZeusLogger.TAG_DOWNLOAD, "isPluginDownloaded failed.", e);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(String str, int i, String str2, int i2, long j, Throwable th) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject.putOpt("status_code", com.bytedance.pangle.log.d.a(Integer.valueOf(i)));
            jSONObject.putOpt("plugin_package_name", com.bytedance.pangle.log.d.a(str2));
            jSONObject.putOpt("version_code", com.bytedance.pangle.log.d.a(Integer.valueOf(i2)));
            jSONObject2.putOpt("duration", com.bytedance.pangle.log.d.a(Long.valueOf(j)));
            jSONObject3.putOpt("throwable", com.bytedance.pangle.log.d.a(th));
            jSONObject3.putOpt("timestamp", com.bytedance.pangle.log.d.a(Long.valueOf(System.currentTimeMillis())));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        com.bytedance.pangle.b.b.a().a(str, jSONObject, jSONObject2, jSONObject3);
    }

    static /* synthetic */ void a(int i, DownloadInfo downloadInfo, String str, String str2, int i2, boolean z, ZeusPluginListener zeusPluginListener) throws Throwable {
        String str3;
        int i3;
        File file = new File(downloadInfo.getSavePath(), downloadInfo.getName());
        String strA = com.bytedance.pangle.util.c.a(file);
        boolean z2 = !TextUtils.isEmpty(strA) && strA.equals(str);
        boolean zA = com.bytedance.pangle.helper.b.a(file);
        if (z2 && zA) {
            a(com.bytedance.pangle.b.b.d, b.a.e, str2, i2, downloadInfo.getDownloadTime(), null);
            if (i == 0 || i == 1) {
                if (zeusPluginListener != null) {
                    zeusPluginListener.onEvent(12, "download success, ".concat(String.valueOf(str2)));
                }
                if (z) {
                    if (zeusPluginListener != null) {
                        zeusPluginListener.onEvent(20, "start install ".concat(String.valueOf(str2)));
                    }
                    PluginManager.getInstance().asyncInstall(file, zeusPluginListener);
                    return;
                }
                return;
            }
            return;
        }
        if (zA) {
            str3 = "Check md5 failed. " + strA + " != " + str;
            i3 = b.a.i;
        } else {
            str3 = "PluginAbi not match hostAbi[" + com.bytedance.pangle.helper.b.a() + "] md5[" + str + "]";
            i3 = b.a.j;
        }
        a(com.bytedance.pangle.b.b.d, i3, str2, i2, downloadInfo.getDownloadTime(), new RuntimeException(str3));
        file.delete();
        if (zeusPluginListener != null) {
            zeusPluginListener.onEvent(13, str3);
        }
    }

    static /* synthetic */ int a(BaseException baseException) {
        int i = b.a.h;
        return baseException != null ? i + NotificationManagerCompat.IMPORTANCE_UNSPECIFIED + 100 + baseException.getErrorCode() : i;
    }

    static /* synthetic */ int a(String str) {
        Plugin plugin = Zeus.getPlugin(str);
        if (plugin != null) {
            return plugin.getVersion();
        }
        return 0;
    }
}
