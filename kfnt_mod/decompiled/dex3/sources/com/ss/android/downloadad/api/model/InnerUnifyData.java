package com.ss.android.downloadad.api.model;

import com.ss.android.download.api.download.DownloadController;
import com.ss.android.download.api.download.DownloadEventConfig;
import com.ss.android.download.api.download.DownloadModel;
import java.util.List;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface InnerUnifyData {
    boolean enableNewActivity();

    List<String> getClickTrackUrl();

    DownloadController getController();

    int getDownloadId();

    int getDownloadScene();

    JSONObject getDownloadSettings();

    String getDownloadUrl();

    DownloadEventConfig getEvent();

    JSONObject getEventExtra();

    String getEventRefer();

    String getEventTag();

    long getExtValue();

    JSONObject getExtra();

    Object getExtraEventObject();

    int getFunnelType();

    long getId();

    String getLogExtra();

    DownloadModel getModel();

    String getOpenUrl();

    String getPackageName();

    JSONObject getParamsJson();

    boolean isAd();

    boolean isV3Event();
}
