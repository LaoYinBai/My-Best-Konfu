package com.qq.e.ads;

import android.app.Activity;
import com.qq.e.comm.compliance.DownloadConfirmCallBack;
import com.qq.e.comm.compliance.DownloadConfirmListener;
import com.qq.e.comm.pi.LADI;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class LiteAbstractAD<T extends LADI> extends AbstractAD<T> implements LADI, DownloadConfirmListener {
    private DownloadConfirmListener f;

    public String getApkInfoUrl() {
        T t = this.f5481a;
        if (t != null) {
            return t.getApkInfoUrl();
        }
        a("getApkInfoUrl");
        return null;
    }

    public int getECPM() {
        T t = this.f5481a;
        if (t != null) {
            return t.getECPM();
        }
        a("getECPM");
        return -1;
    }

    public String getECPMLevel() {
        T t = this.f5481a;
        if (t != null) {
            return t.getECPMLevel();
        }
        a("getECPMLevel");
        return null;
    }

    public Map<String, Object> getExtraInfo() {
        T t = this.f5481a;
        if (t != null) {
            return t.getExtraInfo();
        }
        a("getExtraInfo");
        return new HashMap();
    }

    public void onDownloadConfirm(Activity activity, int i, String str, DownloadConfirmCallBack downloadConfirmCallBack) {
        DownloadConfirmListener downloadConfirmListener = this.f;
        if (downloadConfirmListener != null) {
            downloadConfirmListener.onDownloadConfirm(activity, i, str, downloadConfirmCallBack);
        }
    }

    public void sendLossNotification(int i, int i2, String str) {
        T t = this.f5481a;
        if (t != null) {
            t.sendLossNotification(i, i2, str);
        } else {
            a("sendLossNotification");
        }
    }

    public void sendWinNotification(int i) {
        T t = this.f5481a;
        if (t != null) {
            t.sendWinNotification(i);
        } else {
            a("sendWinNotification");
        }
    }

    public void setBidECPM(int i) {
        T t = this.f5481a;
        if (t != null) {
            t.setBidECPM(i);
        } else {
            a("setBidECPM");
        }
    }

    public void setDownloadConfirmListener(DownloadConfirmListener downloadConfirmListener) {
        this.f = downloadConfirmListener;
        T t = this.f5481a;
        if (t != null) {
            t.setDownloadConfirmListener(this);
        }
    }
}
