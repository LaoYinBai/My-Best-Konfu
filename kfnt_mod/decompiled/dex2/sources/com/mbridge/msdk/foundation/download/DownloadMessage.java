package com.mbridge.msdk.foundation.download;

import com.mbridge.msdk.foundation.download.core.GlobalComponent;
import com.mbridge.msdk.foundation.download.utils.ILogger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class DownloadMessage<T> {
    private T campaign;
    private int downloadRate;
    private DownloadResourceType downloadResourceType;
    private String downloadUrl;
    private Map<String, Object> extraData;
    private String resourceUrl;
    private String saveFileName;
    private String saveFilePath;
    private String md5 = "";
    private boolean checkMD5 = false;

    public DownloadMessage(T t, String str, String str2, int i, DownloadResourceType downloadResourceType) {
        this.downloadRate = 100;
        this.campaign = t;
        this.downloadUrl = str;
        this.saveFileName = str2;
        this.downloadRate = i;
        this.downloadResourceType = downloadResourceType;
        try {
            URL url = new URL(str);
            this.resourceUrl = url.getProtocol() + "://" + url.getHost() + url.getPath();
            ILogger logger = GlobalComponent.getInstance().getLogger();
            StringBuilder sb = new StringBuilder();
            sb.append(" resourceUrl: ");
            sb.append(this.resourceUrl);
            logger.log("DownloadMessage", sb.toString());
        } catch (MalformedURLException unused) {
            this.resourceUrl = "";
        }
    }

    public T getCampaign() {
        return this.campaign;
    }

    public String getDownloadUrl() {
        return this.downloadUrl;
    }

    public String getSaveFileName() {
        return this.saveFileName;
    }

    public String getMd5() {
        return this.md5;
    }

    public void setMd5(String str) {
        this.md5 = str;
    }

    public boolean isCheckMD5() {
        return this.checkMD5;
    }

    public void setCheckMD5(boolean z) {
        this.checkMD5 = z;
    }

    public int getDownloadRate() {
        return this.downloadRate;
    }

    public void setDownloadRate(int i) {
        this.downloadRate = i;
    }

    public void addExtra(String str, Object obj) {
        if (this.extraData == null) {
            this.extraData = new HashMap(4);
        }
        this.extraData.put(str, obj);
    }

    public Object getExtra(String str) {
        if (this.extraData == null || !this.extraData.containsKey(str)) {
            return null;
        }
        return this.extraData.get(str);
    }

    public DownloadResourceType getDownloadResourceType() {
        return this.downloadResourceType;
    }

    public String getSaveFilePath() {
        return this.saveFilePath;
    }

    public void setSaveFilePath(String str) {
        this.saveFilePath = str;
    }

    public String getResourceUrl() {
        return this.resourceUrl;
    }
}
