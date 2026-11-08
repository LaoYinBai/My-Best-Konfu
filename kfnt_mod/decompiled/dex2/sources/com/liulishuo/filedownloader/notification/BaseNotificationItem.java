package com.liulishuo.filedownloader.notification;

import android.app.NotificationManager;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.liulishuo.filedownloader.util.FileDownloadHelper;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class BaseNotificationItem {
    private String desc;
    private int id;
    private NotificationManager manager;
    private int sofar;
    private String title;
    private int total;
    private int status = 0;
    private int lastStatus = 0;

    public abstract void show(boolean z, int i, boolean z2);

    public BaseNotificationItem(int i, String str, String str2) {
        this.id = i;
        this.title = str;
        this.desc = str2;
    }

    public void show(boolean z) {
        show(isChanged(), getStatus(), z);
    }

    public void update(int i, int i2) {
        this.sofar = i;
        this.total = i2;
        show(true);
    }

    public void updateStatus(int i) {
        this.status = i;
    }

    public void cancel() {
        getManager().cancel(this.id);
    }

    protected NotificationManager getManager() {
        if (this.manager == null) {
            this.manager = (NotificationManager) FileDownloadHelper.getAppContext().getSystemService(RemoteMessageConst.NOTIFICATION);
        }
        return this.manager;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public int getSofar() {
        return this.sofar;
    }

    public void setSofar(int i) {
        this.sofar = i;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int i) {
        this.total = i;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public int getStatus() {
        this.lastStatus = this.status;
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public int getLastStatus() {
        return this.lastStatus;
    }

    public boolean isChanged() {
        return this.lastStatus != this.status;
    }
}
