package com.ss.android.downloadlib.addownload.optimize;

import android.text.TextUtils;
import androidx.annotation.WorkerThread;
import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.constants.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DownloadOptimizationManager {
    private static volatile DownloadOptimizationManager mInstance;
    private ICleanSpaceResultListener mCleanResultListener;
    private long mLastClearSpaceTime = 0;
    private ConcurrentHashMap<String, ICleanSpaceResultListener> mCleanSpaceResultMap = new ConcurrentHashMap<>();
    private HashMap<String, Integer> mRestartDownloadTaskMap = new HashMap<>();
    private List<String> mNoNeedCleanSpaceUrl = new CopyOnWriteArrayList();

    public static DownloadOptimizationManager getInstance() {
        if (mInstance == null) {
            synchronized (DownloadOptimizationManager.class) {
                if (mInstance == null) {
                    mInstance = new DownloadOptimizationManager();
                }
            }
        }
        return mInstance;
    }

    public ICleanSpaceResultListener getCleanResultListener(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.mCleanSpaceResultMap.get(str);
    }

    public void setCleanResultListener(String str, ICleanSpaceResultListener iCleanSpaceResultListener) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mCleanSpaceResultMap.put(str, iCleanSpaceResultListener);
    }

    public void removeCleanResultListener(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mCleanSpaceResultMap.remove(str);
    }

    long getLastClearSpaceTime() {
        return this.mLastClearSpaceTime;
    }

    void updateLastClearSpaceTime() {
        this.mLastClearSpaceTime = System.currentTimeMillis();
    }

    public void addRestartTaskTimes(String str) {
        this.mRestartDownloadTaskMap.put(str, Integer.valueOf(getRestartTaskTimes(str) + 1));
    }

    public int getRestartTaskTimes(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        if (this.mRestartDownloadTaskMap == null) {
            this.mRestartDownloadTaskMap = new HashMap<>();
        }
        if (this.mRestartDownloadTaskMap.containsKey(str)) {
            return this.mRestartDownloadTaskMap.get(str).intValue();
        }
        return 0;
    }

    public boolean isDownloadTaskCanRestart(String str) {
        if (this.mRestartDownloadTaskMap == null || TextUtils.isEmpty(str)) {
            return false;
        }
        return (this.mRestartDownloadTaskMap.containsKey(str) ? this.mRestartDownloadTaskMap.get(str).intValue() : 0) > 1;
    }

    @WorkerThread
    public static void deleteDownloadFileWhenInstall(NativeDownloadModel nativeDownloadModel) {
        DownloadInfo downloadInfo;
        if (nativeDownloadModel == null || nativeDownloadModel.getId() <= 0 || (downloadInfo = Downloader.getInstance(GlobalInfo.getContext()).getDownloadInfo(nativeDownloadModel.getDownloadId())) == null) {
            return;
        }
        deleteFileWhenInstall(downloadInfo);
    }

    @WorkerThread
    public static void deleteFileWhenInstall(DownloadInfo downloadInfo) {
        if (downloadInfo == null || DownloadSetting.obtain(downloadInfo.getId()).optInt(DownloadSettingKeys.KEY_DELETE_FILE_AFTER_INSTALL, 0) == 0) {
            return;
        }
        try {
            String str = downloadInfo.getSavePath() + File.separator + downloadInfo.getName();
            if (TextUtils.isEmpty(str)) {
                return;
            }
            File file = new File(str);
            if (file.isFile() && file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
