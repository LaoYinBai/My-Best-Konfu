package com.ycbjie.ycupdatelib;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.umeng.message.MsgConstant;
import com.ycbjie.ycupdatelib.PermissionUtils;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class UpdateFragment {
    private static final String[] mPermission = {MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE, "android.permission.READ_EXTERNAL_STORAGE"};
    private String apkName;
    private String apkUrl;
    private String desc;
    private int downloadStatus = 6;
    private BaseDownloadTask downloadTask;
    private FileDownloadListener listener;
    private Context mContext;
    private String packageName;
    private String saveApkPath;

    public static void showUpdate(Context context, String str, String str2, String str3, String str4) {
        UpdateFragment updateFragment = new UpdateFragment();
        updateFragment.init(context, str, str2, str3, str4);
        FileDownloader.setup(context);
        updateFragment.createFilePath();
        updateFragment.updateStatus();
    }

    public void init(Context context, String str, String str2, String str3, String str4) {
        this.mContext = context;
        this.apkUrl = str;
        this.desc = str3;
        this.apkName = str2;
        this.packageName = str4;
    }

    private void createFilePath() {
        this.saveApkPath = UpdateUtils.getLocalApkDownSavePath(this.apkName);
        if (new File(this.saveApkPath).exists()) {
            changeUploadStatus(8);
        } else {
            changeUploadStatus(6);
        }
    }

    public void updateStatus() {
        switch (this.downloadStatus) {
            case 6:
            case 7:
                if (this.downloadTask != null) {
                    this.downloadTask.pause();
                    break;
                } else {
                    checkPermissionAndDownApk();
                    break;
                }
            case 8:
                if (new File(this.saveApkPath).exists()) {
                    UpdateUtils.installNormal(this.mContext, this.saveApkPath, this.packageName);
                    break;
                } else {
                    checkPermissionAndDownApk();
                    break;
                }
            case 9:
            case 10:
                checkPermissionAndDownApk();
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeUploadStatus(int i) {
        this.downloadStatus = i;
        switch (i) {
            case 6:
                Toast.makeText(this.mContext, "开始下载", 0).show();
                break;
            case 7:
                Toast.makeText(this.mContext, "下载中……", 0).show();
                break;
            case 8:
                Toast.makeText(this.mContext, "开始安装", 0).show();
                break;
            case 9:
                Toast.makeText(this.mContext, "下载失败", 0).show();
                break;
            case 10:
                Toast.makeText(this.mContext, "暂停下载", 0).show();
                break;
        }
    }

    @SuppressLint({"WrongConstant"})
    @TargetApi(16)
    private void checkPermissionAndDownApk() {
        if (this.mContext == null) {
            return;
        }
        PermissionUtils.init(this.mContext);
        if (PermissionUtils.isGranted(mPermission)) {
            this.downloadTask = downApk(this.apkUrl, this.saveApkPath, getListener());
            return;
        }
        PermissionUtils permissionUtilsPermission = PermissionUtils.permission(mPermission);
        permissionUtilsPermission.callback(new PermissionUtils.SimpleCallback() { // from class: com.ycbjie.ycupdatelib.UpdateFragment.1
            @Override // com.ycbjie.ycupdatelib.PermissionUtils.SimpleCallback
            public void onDenied() {
            }

            @Override // com.ycbjie.ycupdatelib.PermissionUtils.SimpleCallback
            public void onGranted() {
                UpdateFragment.this.downloadTask = UpdateFragment.this.downApk(UpdateFragment.this.apkUrl, UpdateFragment.this.saveApkPath, UpdateFragment.this.getListener());
            }
        });
        permissionUtilsPermission.request();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BaseDownloadTask downApk(String str, String str2, FileDownloadListener fileDownloadListener) {
        BaseDownloadTask listener = FileDownloader.getImpl().create(str).setPath(str2).setListener(fileDownloadListener);
        listener.start();
        return listener;
    }

    public FileDownloadListener getListener() {
        if (this.listener == null) {
            this.listener = new FileDownloadListener() { // from class: com.ycbjie.ycupdatelib.UpdateFragment.2
                protected void pending(BaseDownloadTask baseDownloadTask, int i, int i2) {
                    UpdateFragment.this.changeUploadStatus(7);
                }

                protected void progress(BaseDownloadTask baseDownloadTask, int i, int i2) {
                    Log.e("UpdateFragment", String.format("下载进度: %d%%", Integer.valueOf((int) ((baseDownloadTask.getSmallFileSoFarBytes() / baseDownloadTask.getSmallFileTotalBytes()) * 100.0f))));
                }

                protected void completed(BaseDownloadTask baseDownloadTask) {
                    UpdateFragment.this.changeUploadStatus(8);
                    UpdateUtils.installNormal(UpdateFragment.this.mContext, UpdateFragment.this.saveApkPath, UpdateFragment.this.packageName);
                }

                protected void paused(BaseDownloadTask baseDownloadTask, int i, int i2) {
                    UpdateFragment.this.changeUploadStatus(10);
                }

                protected void error(BaseDownloadTask baseDownloadTask, Throwable th) {
                    UpdateFragment.this.changeUploadStatus(9);
                    Log.e("UpdateFragment", th.getLocalizedMessage());
                }

                protected void warn(BaseDownloadTask baseDownloadTask) {
                    UpdateFragment.this.changeUploadStatus(9);
                }
            };
        }
        return this.listener;
    }
}
