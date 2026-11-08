package com.ss.android.downloadlib.addownload.manager;

import android.os.AsyncTask;
import com.ss.android.downloadlib.addownload.model.DownloadInstallInfo;
import com.ss.android.downloadlib.utils.ToolUtils;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DownloadInstallHelper extends AsyncTask<Void, Void, Void> {
    private DownloadInstallCallback mCallback;
    private List<DownloadInstallInfo> mQueryList;

    public interface DownloadInstallCallback {
        void onInstallQueryComplete(List<DownloadInstallInfo> list);
    }

    public DownloadInstallHelper(List<DownloadInstallInfo> list, DownloadInstallCallback downloadInstallCallback) {
        this.mQueryList = list;
        this.mCallback = downloadInstallCallback;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Void doInBackground(Void... voidArr) {
        if (this.mQueryList == null) {
            return null;
        }
        for (DownloadInstallInfo downloadInstallInfo : this.mQueryList) {
            downloadInstallInfo.setInstallStatus(ToolUtils.getInstalledAppStatus(downloadInstallInfo.getAppPackageName(), downloadInstallInfo.getVersionCode(), downloadInstallInfo.getVersionName()));
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(Void r2) {
        if (this.mCallback != null) {
            this.mCallback.onInstallQueryComplete(this.mQueryList);
        }
    }
}
