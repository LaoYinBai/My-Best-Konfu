package com.bytedance.pangle.download;

import androidx.annotation.Keep;
import java.util.List;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class PluginDownloadBean {
    public boolean isOffline;
    public boolean isRevert;
    public boolean isWifiOnly;
    public int mApiVersionMax;
    public int mApiVersionMin;
    public List<String> mBackupUrlList;
    public int mClientVersionMax;
    public int mClientVersionMin;
    public int mDownloadType;
    public String mMd5;
    public int mOrder;
    public String mPackageName;
    public String mUrl;
    public int mVersionCode;

    public String toString() {
        return "PluginDownloadBean{mPackageName='" + this.mPackageName + "', mVersionCode=" + this.mVersionCode + ", mUrl='" + this.mUrl + "', mMd5='" + this.mMd5 + "', mOrder=" + this.mOrder + ", isOffline=" + this.isOffline + ", isRevert=" + this.isRevert + ", isWifiOnly=" + this.isWifiOnly + ", mClientVersionMin=" + this.mClientVersionMin + ", mClientVersionMax=" + this.mClientVersionMax + ", mApiVersionMin=" + this.mApiVersionMin + ", mApiVersionMax=" + this.mApiVersionMax + ", mDownloadType=" + this.mDownloadType + ", mBackupUrlList=" + this.mBackupUrlList + '}';
    }
}
