package com.huawei.hms.support.api.opendevice;

import android.app.PendingIntent;
import com.huawei.hms.support.api.client.Result;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class OaidResult extends Result {
    private String id;
    private boolean isTrackLimited;
    private PendingIntent settingIntent;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public boolean isTrackLimited() {
        return this.isTrackLimited;
    }

    public void setTrackLimited(boolean z) {
        this.isTrackLimited = z;
    }

    public PendingIntent getSettingIntent() {
        return this.settingIntent;
    }

    public void setSettingIntent(PendingIntent pendingIntent) {
        this.settingIntent = pendingIntent;
    }
}
