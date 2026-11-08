package com.ss.android.downloadlib.addownload.model;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.ss.android.download.api.download.DownloadController;
import com.ss.android.download.api.download.DownloadEventConfig;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.download.api.download.SimpleDownloadController;
import com.ss.android.download.api.download.SimpleDownloadEventConfig;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.DownloadComponentManager;
import com.ss.android.downloadlib.exception.TTDownloaderMonitor;
import com.ss.android.downloadlib.utils.ToolUtils;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ModelManager {
    private final ConcurrentHashMap<Long, DownloadController> controllers;
    private final ConcurrentHashMap<Long, DownloadEventConfig> eventConfigs;
    private volatile boolean init;
    private final ConcurrentHashMap<Long, DownloadModel> models;
    private final ConcurrentHashMap<Long, NativeDownloadModel> nativeModels;

    private static class SingleTonHolder {
        private static ModelManager INSTANCE = new ModelManager();

        private SingleTonHolder() {
        }
    }

    public static ModelManager getInstance() {
        return SingleTonHolder.INSTANCE;
    }

    private ModelManager() {
        this.init = false;
        this.models = new ConcurrentHashMap<>();
        this.eventConfigs = new ConcurrentHashMap<>();
        this.controllers = new ConcurrentHashMap<>();
        this.nativeModels = new ConcurrentHashMap<>();
    }

    public void init() {
        DownloadComponentManager.getInstance().submitCPUTask(new Runnable() { // from class: com.ss.android.downloadlib.addownload.model.ModelManager.1
            @Override // java.lang.Runnable
            public void run() {
                if (ModelManager.this.init) {
                    return;
                }
                synchronized (ModelManager.class) {
                    if (!ModelManager.this.init) {
                        ModelManager.this.nativeModels.putAll(SharedPrefsManager.getInstance().getAllNativeDownloadModels());
                        ModelManager.this.init = true;
                    }
                }
            }
        }, true);
    }

    public void addDownloadModel(DownloadModel downloadModel) {
        if (downloadModel != null) {
            this.models.put(Long.valueOf(downloadModel.getId()), downloadModel);
            if (downloadModel.getDeepLink() != null) {
                downloadModel.getDeepLink().setId(downloadModel.getId());
                downloadModel.getDeepLink().setPackageName(downloadModel.getPackageName());
            }
        }
    }

    public void addDownloadEventConfig(long j, DownloadEventConfig downloadEventConfig) {
        if (downloadEventConfig != null) {
            this.eventConfigs.put(Long.valueOf(j), downloadEventConfig);
        }
    }

    public void addDownloadController(long j, DownloadController downloadController) {
        if (downloadController != null) {
            this.controllers.put(Long.valueOf(j), downloadController);
        }
    }

    public synchronized void putNativeModel(NativeDownloadModel nativeDownloadModel) {
        if (nativeDownloadModel == null) {
            return;
        }
        this.nativeModels.put(Long.valueOf(nativeDownloadModel.getId()), nativeDownloadModel);
        SharedPrefsManager.getInstance().saveNativeDownloadModel(nativeDownloadModel);
    }

    public DownloadModel getDownloadModel(long j) {
        return this.models.get(Long.valueOf(j));
    }

    public DownloadEventConfig getDownloadEventConfig(long j) {
        return this.eventConfigs.get(Long.valueOf(j));
    }

    public DownloadController getDownloadController(long j) {
        return this.controllers.get(Long.valueOf(j));
    }

    public NativeDownloadModel getNativeDownloadModel(long j) {
        return this.nativeModels.get(Long.valueOf(j));
    }

    public ConcurrentHashMap<Long, NativeDownloadModel> getAllNativeModels() {
        return this.nativeModels;
    }

    public NativeDownloadModel getNativeModelByPkg(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        for (NativeDownloadModel nativeDownloadModel : this.nativeModels.values()) {
            if (nativeDownloadModel != null && str.equals(nativeDownloadModel.getPackageName())) {
                return nativeDownloadModel;
            }
        }
        return null;
    }

    public NativeDownloadModel getNativeModelByUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        for (NativeDownloadModel nativeDownloadModel : this.nativeModels.values()) {
            if (nativeDownloadModel != null && str.equals(nativeDownloadModel.getDownloadUrl())) {
                return nativeDownloadModel;
            }
        }
        return null;
    }

    public NativeDownloadModel getNativeModelByInfo(DownloadInfo downloadInfo) {
        if (downloadInfo == null) {
            return null;
        }
        for (NativeDownloadModel nativeDownloadModel : this.nativeModels.values()) {
            if (nativeDownloadModel != null && nativeDownloadModel.getDownloadId() == downloadInfo.getId()) {
                return nativeDownloadModel;
            }
        }
        if (!TextUtils.isEmpty(downloadInfo.getExtra())) {
            try {
                long jOptLong = ToolUtils.optLong(new JSONObject(downloadInfo.getExtra()), "extra");
                if (jOptLong != 0) {
                    for (NativeDownloadModel nativeDownloadModel2 : this.nativeModels.values()) {
                        if (nativeDownloadModel2 != null && nativeDownloadModel2.getId() == jOptLong) {
                            return nativeDownloadModel2;
                        }
                    }
                    TTDownloaderMonitor.inst().monitorDataError("getNativeModelByInfo");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (NativeDownloadModel nativeDownloadModel3 : this.nativeModels.values()) {
            if (nativeDownloadModel3 != null && TextUtils.equals(nativeDownloadModel3.getDownloadUrl(), downloadInfo.getUrl())) {
                return nativeDownloadModel3;
            }
        }
        return null;
    }

    public NativeDownloadModel getNativeModelByInfoId(int i) {
        for (NativeDownloadModel nativeDownloadModel : this.nativeModels.values()) {
            if (nativeDownloadModel != null && nativeDownloadModel.getDownloadId() == i) {
                return nativeDownloadModel;
            }
        }
        return null;
    }

    @NonNull
    public ModelBox getModelBox(long j) {
        ModelBox modelBox = new ModelBox();
        modelBox.id = j;
        modelBox.model = getDownloadModel(j);
        modelBox.event = getDownloadEventConfig(j);
        if (modelBox.event == null) {
            modelBox.event = new SimpleDownloadEventConfig();
        }
        modelBox.controller = getDownloadController(j);
        if (modelBox.controller == null) {
            modelBox.controller = new SimpleDownloadController();
        }
        return modelBox;
    }

    public void removeMemoryCaches(long j) {
        this.models.remove(Long.valueOf(j));
        this.eventConfigs.remove(Long.valueOf(j));
        this.controllers.remove(Long.valueOf(j));
    }

    @NonNull
    public Map<Long, NativeDownloadModel> correctNativeModelsPkgName(String str, String str2) {
        HashMap map = new HashMap();
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return map;
        }
        for (NativeDownloadModel nativeDownloadModel : this.nativeModels.values()) {
            if (nativeDownloadModel != null && TextUtils.equals(nativeDownloadModel.getDownloadUrl(), str)) {
                nativeDownloadModel.setPackageName(str2);
                map.put(Long.valueOf(nativeDownloadModel.getId()), nativeDownloadModel);
            }
        }
        return map;
    }

    public void correctModelsPkgName(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        for (DownloadModel downloadModel : this.models.values()) {
            if ((downloadModel instanceof AdDownloadModel) && TextUtils.equals(downloadModel.getDownloadUrl(), str)) {
                ((AdDownloadModel) downloadModel).setPackageName(str2);
            }
        }
    }

    public synchronized void removeNativeModels(List<NativeDownloadModel> list) {
        SharedPrefsManager.getInstance().removeNativeDownloadModels(SharedPrefsManager.getNativeModelIds(list));
        Iterator<NativeDownloadModel> it = list.iterator();
        while (it.hasNext()) {
            this.nativeModels.remove(Long.valueOf(it.next().getId()));
        }
    }

    public synchronized void removeNativeModelsById(List<Long> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<Long> it = list.iterator();
        while (it.hasNext()) {
            long jLongValue = it.next().longValue();
            arrayList.add(String.valueOf(jLongValue));
            this.nativeModels.remove(Long.valueOf(jLongValue));
        }
        SharedPrefsManager.getInstance().removeNativeDownloadModels(arrayList);
    }

    public synchronized void removeNativeModel(NativeDownloadModel nativeDownloadModel) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(nativeDownloadModel);
        removeNativeModels(arrayList);
    }
}
