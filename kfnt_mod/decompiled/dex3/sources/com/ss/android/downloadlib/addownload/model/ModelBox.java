package com.ss.android.downloadlib.addownload.model;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.ss.android.download.api.download.DownloadController;
import com.ss.android.download.api.download.DownloadEventConfig;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.downloadad.api.download.AdDownloadController;
import com.ss.android.downloadad.api.download.AdDownloadEventConfig;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.downloadad.api.model.InnerUnifyData;
import com.ss.android.downloadad.api.model.NativeDownloadModel;
import java.util.List;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ModelBox implements InnerUnifyData {
    public DownloadController controller;
    public DownloadEventConfig event;
    public long id;
    public DownloadModel model;
    public NativeDownloadModel nativeModel;

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public int getDownloadId() {
        return 0;
    }

    public ModelBox() {
    }

    public ModelBox(long j, @NonNull DownloadModel downloadModel, @NonNull DownloadEventConfig downloadEventConfig, @NonNull DownloadController downloadController) {
        this.id = j;
        this.model = downloadModel;
        this.event = downloadEventConfig;
        this.controller = downloadController;
    }

    public boolean notValid() {
        if (this.id == 0 || this.model == null || this.event == null || this.controller == null) {
            return true;
        }
        return this.model.isAd() && this.id <= 0;
    }

    public boolean isStrictValid() {
        if (notValid()) {
            return false;
        }
        if (this.model.isAd()) {
            return (this.model instanceof AdDownloadModel) && !TextUtils.isEmpty(this.model.getLogExtra()) && (this.event instanceof AdDownloadEventConfig) && (this.controller instanceof AdDownloadController);
        }
        return this.model instanceof AdDownloadModel;
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public String getDownloadUrl() {
        return this.model.getDownloadUrl();
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public long getId() {
        return this.model.getId();
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public boolean isAd() {
        return this.model.isAd();
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public String getLogExtra() {
        return this.model.getLogExtra();
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public String getPackageName() {
        return this.model.getPackageName();
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public String getOpenUrl() {
        if (this.model.getDeepLink() != null) {
            return this.model.getDeepLink().getOpenUrl();
        }
        return null;
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public JSONObject getExtra() {
        return this.model.getExtra();
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public int getFunnelType() {
        if (this.controller.getDownloadMode() == 2) {
            return 2;
        }
        return this.model.getFunnelType();
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public long getExtValue() {
        return this.model.getExtraValue();
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public List<String> getClickTrackUrl() {
        return this.model.getClickTrackUrl();
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public String getEventRefer() {
        return this.event.getRefer();
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public String getEventTag() {
        return this.event.getClickButtonTag();
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public JSONObject getParamsJson() {
        return this.event.getParamsJson();
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public boolean isV3Event() {
        return this.event.isEnableV3Event();
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public Object getExtraEventObject() {
        return this.event.getExtraEventObject();
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public JSONObject getEventExtra() {
        return this.event.getExtraJson();
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public boolean enableNewActivity() {
        return this.controller.enableNewActivity();
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public JSONObject getDownloadSettings() {
        return this.model.getDownloadSettings();
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public DownloadModel getModel() {
        return this.model;
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public DownloadEventConfig getEvent() {
        return this.event;
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public DownloadController getController() {
        return this.controller;
    }

    @Override // com.ss.android.downloadad.api.model.InnerUnifyData
    public int getDownloadScene() {
        return this.event.getDownloadScene();
    }
}
