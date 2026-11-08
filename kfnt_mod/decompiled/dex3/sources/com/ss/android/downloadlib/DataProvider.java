package com.ss.android.downloadlib;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.ss.android.download.api.constant.BaseConstants;
import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.addownload.model.ModelManager;
import com.ss.android.downloadlib.utils.ToolUtils;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DataProvider {

    private static class SingleTonHolder {
        private static DataProvider INSTANCE = new DataProvider();

        private SingleTonHolder() {
        }
    }

    public static DataProvider getInstance() {
        return SingleTonHolder.INSTANCE;
    }

    private DataProvider() {
    }

    @NonNull
    @WorkerThread
    public List<NativeDownloadModel> getRecentUnInstalledModels(int i) {
        DownloadInfo downloadInfo;
        ArrayList arrayList = new ArrayList();
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (i <= 0) {
            return arrayList;
        }
        ModelManager.getInstance().init();
        for (NativeDownloadModel nativeDownloadModel : ModelManager.getInstance().getAllNativeModels().values()) {
            if (nativeDownloadModel.isAd() && !ToolUtils.isInstalledApp(nativeDownloadModel) && nativeDownloadModel.getInstallScene() != 4 && nativeDownloadModel.getDownloadStatus() == 2 && jCurrentTimeMillis - nativeDownloadModel.getTimeStamp() <= BaseConstants.Time.DAY * i && (downloadInfo = Downloader.getInstance(GlobalInfo.getContext()).getDownloadInfo(nativeDownloadModel.getDownloadId())) != null && DownloadUtils.isFileDownloaded(downloadInfo, false, downloadInfo.getMd5())) {
                arrayList.add(nativeDownloadModel);
            }
        }
        return arrayList;
    }
}
