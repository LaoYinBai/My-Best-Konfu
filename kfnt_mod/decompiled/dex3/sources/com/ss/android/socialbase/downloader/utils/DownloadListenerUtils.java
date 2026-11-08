package com.ss.android.socialbase.downloader.utils;

import android.util.SparseArray;
import com.ss.android.socialbase.downloader.depend.AbsDownloadListener;
import com.ss.android.socialbase.downloader.depend.IDownloadExtListener;
import com.ss.android.socialbase.downloader.depend.IDownloadListener;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.model.DownloadInfo;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DownloadListenerUtils {
    public static void notifyListener(int i, SparseArray<IDownloadListener> sparseArray, boolean z, DownloadInfo downloadInfo, BaseException baseException) {
        SparseArray<IDownloadListener> sparseArrayClone;
        if (!z || sparseArray == null) {
            return;
        }
        try {
            if (sparseArray.size() <= 0) {
                return;
            }
            synchronized (sparseArray) {
                sparseArrayClone = sparseArray.clone();
            }
            for (int i2 = 0; i2 < sparseArrayClone.size(); i2++) {
                IDownloadListener iDownloadListener = sparseArrayClone.get(sparseArrayClone.keyAt(i2));
                if (iDownloadListener != null) {
                    switch (i) {
                        case -7:
                            if (iDownloadListener instanceof AbsDownloadListener) {
                                ((AbsDownloadListener) iDownloadListener).onIntercept(downloadInfo);
                                break;
                            } else {
                                break;
                            }
                        case -6:
                            iDownloadListener.onFirstSuccess(downloadInfo);
                            break;
                        case -5:
                        case -2:
                            iDownloadListener.onPause(downloadInfo);
                            break;
                        case -4:
                            iDownloadListener.onCanceled(downloadInfo);
                            break;
                        case -3:
                            iDownloadListener.onSuccessed(downloadInfo);
                            break;
                        case -1:
                            iDownloadListener.onFailed(downloadInfo, baseException);
                            break;
                        case 1:
                            iDownloadListener.onPrepare(downloadInfo);
                            break;
                        case 2:
                            iDownloadListener.onStart(downloadInfo);
                            break;
                        case 4:
                            iDownloadListener.onProgress(downloadInfo);
                            break;
                        case 5:
                            iDownloadListener.onRetry(downloadInfo, baseException);
                            break;
                        case 6:
                            iDownloadListener.onFirstStart(downloadInfo);
                            break;
                        case 7:
                            iDownloadListener.onRetryDelay(downloadInfo, baseException);
                            break;
                        case 11:
                            if (iDownloadListener instanceof IDownloadExtListener) {
                                ((IDownloadExtListener) iDownloadListener).onWaitingDownloadCompleteHandler(downloadInfo);
                                break;
                            } else {
                                break;
                            }
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
