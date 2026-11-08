package com.mbridge.msdk.foundation.download.download;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Patterns;
import android.webkit.URLUtil;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.b.a;
import com.mbridge.msdk.b.b;
import com.mbridge.msdk.foundation.download.DownloadError;
import com.mbridge.msdk.foundation.download.DownloadMessage;
import com.mbridge.msdk.foundation.download.DownloadPriority;
import com.mbridge.msdk.foundation.download.DownloadResourceType;
import com.mbridge.msdk.foundation.download.MBDownloadManager;
import com.mbridge.msdk.foundation.download.OnDownloadStateListener;
import com.mbridge.msdk.foundation.download.resource.MBResourceManager;
import com.mbridge.msdk.foundation.same.b.c;
import com.mbridge.msdk.foundation.same.b.e;
import com.mbridge.msdk.foundation.tools.SameMD5;
import com.mbridge.msdk.foundation.tools.ab;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.y;
import com.mbridge.msdk.foundation.tools.z;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class H5DownLoadManager {
    public static final String SP_ENDING_PAGE_SAVE_TIME = "ending_page_save_time";
    public static final String SP_ENDING_PAGE_SOURCE = "ending_page_source";
    private static final String TAG = "H5DownLoadManager";
    private static H5DownLoadManager sH5Manager;
    private HTMLResourceManager htmlResourceManager;
    private boolean isUseDownloadModule;
    private CopyOnWriteArrayList<String> mResDownloadingList;
    private ConcurrentMap<String, DownLoadH5SourceListener> mResDownloadingMap;
    private ResourceManager resourceManager;

    public interface H5ResDownloadListerInter {
        void onFailed(String str, String str2);

        void onSuccess(String str);
    }

    public interface IH5SourceDownloadListener extends H5ResDownloadListerInter {
    }

    public interface IOnDownLoadH5Source {
        void onFailed(String str);

        void onStart();

        void onSuccess(String str, byte[] bArr, String str2);
    }

    public interface ZipDownloadListener extends H5ResDownloadListerInter {
    }

    private H5DownLoadManager() {
        this.isUseDownloadModule = false;
        try {
            this.resourceManager = ResourceManager.getinstance();
            this.htmlResourceManager = HTMLResourceManager.getInstance();
            this.mResDownloadingList = new CopyOnWriteArrayList<>();
            this.mResDownloadingMap = new ConcurrentHashMap();
            a aVarB = b.a().b("app_id");
            if (aVarB != null) {
                this.isUseDownloadModule = aVarB.d(1);
            }
        } catch (Throwable th) {
            s.b(TAG, th.getMessage(), th);
        }
    }

    public static H5DownLoadManager getInstance() {
        if (sH5Manager == null) {
            synchronized (H5DownLoadManager.class) {
                if (sH5Manager == null) {
                    sH5Manager = new H5DownLoadManager();
                }
            }
        }
        return sH5Manager;
    }

    public String getResAddress(String str) {
        if (this.resourceManager != null) {
            return this.resourceManager.getResDirFromCampaign(str);
        }
        return null;
    }

    private String getHtmlAddress(String str) {
        return this.htmlResourceManager != null ? this.htmlResourceManager.getHtmlPathFromUrl(str) : str;
    }

    private void downloadZipByDownloadModule(String str, final ZipDownloadListener zipDownloadListener) throws NoSuchAlgorithmException {
        if (TextUtils.isEmpty(str)) {
            if (zipDownloadListener != null) {
                zipDownloadListener.onFailed(str, "zip url is null");
                return;
            }
            return;
        }
        try {
            new URL(str);
            String strB = e.b(c.MBRIDGE_700_RES);
            String md5 = SameMD5.getMD5(ab.a(str));
            String str2 = strB + "/";
            final String str3 = strB + "/" + md5;
            MBDownloadManager.getInstance().download(new DownloadMessage<>(new Object(), str, md5 + ".zip", 100, DownloadResourceType.DOWNLOAD_RESOURCE_TYPE_ZIP)).withReadTimeout(30000L).withConnectTimeout(20000L).withDownloadPriority(DownloadPriority.HIGH).withHttpRetryCounter(1).withDirectoryPathInternal(str2).withDownloadStateListener(new OnDownloadStateListener() { // from class: com.mbridge.msdk.foundation.download.download.H5DownLoadManager.1
                @Override // com.mbridge.msdk.foundation.download.OnDownloadStateListener
                public void onDownloadStart(DownloadMessage downloadMessage) {
                    s.a(H5DownLoadManager.TAG, "开始下载 zip： " + downloadMessage.getDownloadUrl() + " " + downloadMessage.getDownloadResourceType());
                }

                @Override // com.mbridge.msdk.foundation.download.OnDownloadStateListener
                public void onDownloadComplete(DownloadMessage downloadMessage) {
                    s.a(H5DownLoadManager.TAG, "下载结束，开始解压缩文件： " + downloadMessage.getDownloadUrl() + " " + downloadMessage.getDownloadResourceType() + " " + downloadMessage.getSaveFilePath());
                    try {
                        if (TextUtils.isEmpty(ResourceManager.getinstance().getResDirFromCampaign(downloadMessage.getDownloadUrl()))) {
                            MBResourceManager.getInstance().unZip(downloadMessage.getSaveFilePath(), str3);
                        }
                        s.a(H5DownLoadManager.TAG, "下载结束，开始解压缩文件，文件解压成功： " + str3);
                        if (zipDownloadListener != null) {
                            zipDownloadListener.onSuccess(downloadMessage.getDownloadUrl());
                        }
                    } catch (IOException e) {
                        s.a(H5DownLoadManager.TAG, "下载结束，开始解压缩文件，文件解压失败： " + e.getMessage());
                        if (zipDownloadListener != null) {
                            zipDownloadListener.onFailed(downloadMessage.getDownloadUrl(), e.getMessage());
                        }
                    }
                }

                @Override // com.mbridge.msdk.foundation.download.OnDownloadStateListener
                public void onCancelDownload(DownloadMessage downloadMessage) {
                    s.a(H5DownLoadManager.TAG, "下载取消： " + downloadMessage.getDownloadUrl() + " " + downloadMessage.getDownloadResourceType());
                    if (!TextUtils.isEmpty(ResourceManager.getinstance().getResDirFromCampaign(downloadMessage.getDownloadUrl()))) {
                        if (zipDownloadListener != null) {
                            zipDownloadListener.onSuccess(downloadMessage.getDownloadUrl());
                        }
                    } else if (zipDownloadListener != null) {
                        zipDownloadListener.onFailed(downloadMessage.getDownloadUrl(), "task cancel");
                    }
                }

                @Override // com.mbridge.msdk.foundation.download.OnDownloadStateListener
                public void onDownloadError(DownloadMessage downloadMessage, DownloadError downloadError) {
                    s.a(H5DownLoadManager.TAG, "下载错误： " + downloadMessage.getDownloadUrl() + " " + downloadMessage.getDownloadResourceType() + "  " + downloadError.getException().getMessage());
                    if (!TextUtils.isEmpty(ResourceManager.getinstance().getResDirFromCampaign(downloadMessage.getDownloadUrl()))) {
                        if (zipDownloadListener != null) {
                            zipDownloadListener.onSuccess(downloadMessage.getDownloadUrl());
                        }
                    } else if (zipDownloadListener != null) {
                        zipDownloadListener.onFailed(downloadMessage.getDownloadUrl(), downloadError.getException().getMessage());
                    }
                }
            }).build().start();
        } catch (Exception unused) {
            if (zipDownloadListener != null) {
                zipDownloadListener.onFailed(str, "zip url is unlawful");
            }
        }
    }

    public void downloadZip(String str, ZipDownloadListener zipDownloadListener) throws NoSuchAlgorithmException {
        if (this.isUseDownloadModule) {
            downloadZipByDownloadModule(str, zipDownloadListener);
        } else {
            downloadZipByOldDownloadModule(str, zipDownloadListener);
        }
    }

    private void downloadZipByOldDownloadModule(String str, ZipDownloadListener zipDownloadListener) {
        try {
            if (!TextUtils.isEmpty(this.resourceManager.getResDirFromCampaign(str))) {
                if (zipDownloadListener != null) {
                    zipDownloadListener.onSuccess(str);
                }
            } else {
                if (this.mResDownloadingMap.containsKey(str)) {
                    DownLoadH5SourceListener downLoadH5SourceListener = this.mResDownloadingMap.get(str);
                    if (downLoadH5SourceListener != null) {
                        downLoadH5SourceListener.setZipDownloadListener(zipDownloadListener);
                        return;
                    }
                    return;
                }
                DownLoadH5SourceListener downLoadH5SourceListener2 = new DownLoadH5SourceListener(this.mResDownloadingMap, this.resourceManager, zipDownloadListener, str);
                this.mResDownloadingMap.put(str, downLoadH5SourceListener2);
                DownLoadUtils.getSourceCodeFromNetUrl(str, downLoadH5SourceListener2, true);
            }
        } catch (Exception e) {
            if (zipDownloadListener != null) {
                zipDownloadListener.onFailed(str, "downloadzip failed");
            }
            if (MBridgeConstans.DEBUG) {
                e.printStackTrace();
            }
        }
    }

    public void download(String str) throws NoSuchAlgorithmException {
        download(str, (IH5SourceDownloadListener) null);
    }

    public void download(String str, H5ResDownloadListerInter h5ResDownloadListerInter) throws NoSuchAlgorithmException {
        if (this.isUseDownloadModule) {
            downloadHTMLByDownloadModule(str, h5ResDownloadListerInter);
        } else {
            downloadHTML(str, h5ResDownloadListerInter);
        }
    }

    private void downloadHTML(final String str, final H5ResDownloadListerInter h5ResDownloadListerInter) {
        try {
            s.d(TAG, "download url:" + str);
            if (this.mResDownloadingList.contains(str)) {
                return;
            }
            this.mResDownloadingList.add(str);
            DownloadTask.getInstance().runTask(new com.mbridge.msdk.foundation.same.e.a() { // from class: com.mbridge.msdk.foundation.download.download.H5DownLoadManager.2
                @Override // com.mbridge.msdk.foundation.same.e.a
                public void cancelTask() {
                }

                @Override // com.mbridge.msdk.foundation.same.e.a
                public void pauseTask(boolean z) {
                }

                @Override // com.mbridge.msdk.foundation.same.e.a
                public void runTask() {
                    if (!TextUtils.isEmpty(H5DownLoadManager.this.htmlResourceManager.getHtmlContentFromUrl(str))) {
                        H5DownLoadManager.this.mResDownloadingList.remove(str);
                        if (h5ResDownloadListerInter != null) {
                            h5ResDownloadListerInter.onSuccess(str);
                            return;
                        }
                        return;
                    }
                    DownLoadUtils.getSourceCodeFromNetUrl(str, new IOnDownLoadH5Source() { // from class: com.mbridge.msdk.foundation.download.download.H5DownLoadManager.2.1
                        @Override // com.mbridge.msdk.foundation.download.download.H5DownLoadManager.IOnDownLoadH5Source
                        public void onStart() {
                        }

                        @Override // com.mbridge.msdk.foundation.download.download.H5DownLoadManager.IOnDownLoadH5Source
                        public void onSuccess(String str2, byte[] bArr, String str3) {
                            try {
                                H5DownLoadManager.this.mResDownloadingList.remove(str3);
                                if (bArr == null || bArr.length <= 0) {
                                    return;
                                }
                                if (H5DownLoadManager.this.htmlResourceManager.saveResHtmlFile(str3, bArr)) {
                                    if (h5ResDownloadListerInter != null) {
                                        h5ResDownloadListerInter.onSuccess(str3);
                                    }
                                } else if (h5ResDownloadListerInter != null) {
                                    h5ResDownloadListerInter.onFailed(str3, "save file failed");
                                }
                            } catch (Exception e) {
                                if (MBridgeConstans.DEBUG) {
                                    e.printStackTrace();
                                }
                                if (h5ResDownloadListerInter != null) {
                                    h5ResDownloadListerInter.onFailed(str3, e.getMessage());
                                }
                            }
                        }

                        @Override // com.mbridge.msdk.foundation.download.download.H5DownLoadManager.IOnDownLoadH5Source
                        public void onFailed(String str2) {
                            try {
                                H5DownLoadManager.this.mResDownloadingList.remove(str);
                                if (h5ResDownloadListerInter != null) {
                                    h5ResDownloadListerInter.onFailed(str, str2);
                                }
                            } catch (Exception e) {
                                if (MBridgeConstans.DEBUG) {
                                    e.printStackTrace();
                                }
                                if (h5ResDownloadListerInter != null) {
                                    h5ResDownloadListerInter.onFailed(str, str2);
                                }
                            }
                        }
                    }, true);
                }
            });
        } catch (Throwable th) {
            if (MBridgeConstans.DEBUG) {
                th.printStackTrace();
            }
        }
    }

    private void downloadHTMLByDownloadModule(final String str, final H5ResDownloadListerInter h5ResDownloadListerInter) throws NoSuchAlgorithmException {
        if (TextUtils.isEmpty(str)) {
            if (h5ResDownloadListerInter != null) {
                h5ResDownloadListerInter.onFailed("zip url is null", str);
                return;
            }
            return;
        }
        try {
            new URL(str);
            String str2 = e.b(c.MBRIDGE_700_HTML) + "/";
            String md5 = SameMD5.getMD5(ab.a(str));
            MBDownloadManager.getInstance().download(new DownloadMessage<>(new Object(), str, md5 + ".html", 100, DownloadResourceType.DOWNLOAD_RESOURCE_TYPE_HTML)).withReadTimeout(30000L).withConnectTimeout(20000L).withDownloadPriority(DownloadPriority.HIGH).withHttpRetryCounter(1).withDirectoryPathInternal(str2).withDownloadStateListener(new OnDownloadStateListener() { // from class: com.mbridge.msdk.foundation.download.download.H5DownLoadManager.3
                @Override // com.mbridge.msdk.foundation.download.OnDownloadStateListener
                public void onDownloadStart(DownloadMessage downloadMessage) {
                    s.a(H5DownLoadManager.TAG, "开始下载 html： " + downloadMessage.getDownloadUrl() + " " + downloadMessage.getDownloadResourceType());
                }

                @Override // com.mbridge.msdk.foundation.download.OnDownloadStateListener
                public void onDownloadComplete(DownloadMessage downloadMessage) {
                    s.a(H5DownLoadManager.TAG, "下载结束： " + downloadMessage.getDownloadUrl() + " " + downloadMessage.getDownloadResourceType() + " " + downloadMessage.getSaveFilePath());
                    if (h5ResDownloadListerInter != null) {
                        h5ResDownloadListerInter.onSuccess(str);
                    }
                }

                @Override // com.mbridge.msdk.foundation.download.OnDownloadStateListener
                public void onCancelDownload(DownloadMessage downloadMessage) {
                    s.a(H5DownLoadManager.TAG, "下载取消： ");
                    if (h5ResDownloadListerInter != null) {
                        h5ResDownloadListerInter.onFailed(str, "task cancel");
                    }
                }

                @Override // com.mbridge.msdk.foundation.download.OnDownloadStateListener
                public void onDownloadError(DownloadMessage downloadMessage, DownloadError downloadError) {
                    s.a(H5DownLoadManager.TAG, "下载结束失败： " + downloadError.getException().getMessage());
                    if (h5ResDownloadListerInter != null) {
                        h5ResDownloadListerInter.onFailed(str, downloadError.getException().getMessage());
                    }
                }
            }).build().start();
        } catch (Exception unused) {
            if (h5ResDownloadListerInter != null) {
                h5ResDownloadListerInter.onFailed("zip url is unlawful", str);
            }
        }
    }

    public String getSourceContentFromSp(String str) {
        try {
            Object objB = y.b(com.mbridge.msdk.foundation.controller.a.f().j(), SP_ENDING_PAGE_SOURCE + str, "");
            if (objB == null || !(objB instanceof String)) {
                return null;
            }
            String str2 = (String) objB;
            if (z.b(str2)) {
                return str2;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void saveSourceContent(String str, String str2) {
        try {
            s.b(TAG, "sourceContent:" + str);
            y.a(com.mbridge.msdk.foundation.controller.a.f().j(), SP_ENDING_PAGE_SOURCE + str2, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private long getPreSaveTimeFromSp(String str) {
        try {
            Object objB = y.b(com.mbridge.msdk.foundation.controller.a.f().j(), SP_ENDING_PAGE_SAVE_TIME + str, 0L);
            if (objB != null && (objB instanceof Long)) {
                return ((Long) objB).longValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    private void updateEndingPageSaveTime(String str) {
        try {
            y.a(com.mbridge.msdk.foundation.controller.a.f().j(), SP_ENDING_PAGE_SAVE_TIME + str, Long.valueOf(System.currentTimeMillis()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void downloadH5Res(String str) {
        downloadH5Res(str, null);
    }

    public void downloadH5Res(String str, H5ResDownloadListerInter h5ResDownloadListerInter) {
        try {
            if (Patterns.WEB_URL.matcher(str).matches() || URLUtil.isValidUrl(str)) {
                String path = Uri.parse(str).getPath();
                if (!TextUtils.isEmpty(path)) {
                    if (path.toLowerCase().endsWith(".zip")) {
                        downloadZip(str, (ZipDownloadListener) h5ResDownloadListerInter);
                        return;
                    } else {
                        download(str, h5ResDownloadListerInter);
                        return;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (h5ResDownloadListerInter != null) {
            h5ResDownloadListerInter.onFailed(str, "The URL does not contain a path ");
        }
    }

    public String getH5ResAddress(String str) {
        try {
            if (Patterns.WEB_URL.matcher(str).matches() || URLUtil.isValidUrl(str)) {
                Uri uri = Uri.parse(str);
                String path = uri.getPath();
                if (TextUtils.isEmpty(path) || !TextUtils.isEmpty(uri.getQueryParameter("urlDebug"))) {
                    return str;
                }
                if (path.toLowerCase().endsWith(".zip")) {
                    return getResAddress(str);
                }
                return getHtmlAddress(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}
