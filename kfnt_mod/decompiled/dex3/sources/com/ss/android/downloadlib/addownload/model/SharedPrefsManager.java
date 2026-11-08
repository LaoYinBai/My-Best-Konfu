package com.ss.android.downloadlib.addownload.model;

import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.DownloadComponentManager;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.constants.DownloadConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SharedPrefsManager {

    private static class SingleTonHolder {
        private static SharedPrefsManager INSTANCE = new SharedPrefsManager();

        private SingleTonHolder() {
        }
    }

    public static SharedPrefsManager getInstance() {
        return SingleTonHolder.INSTANCE;
    }

    private SharedPrefsManager() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SharedPreferences getDownloadModelSp() {
        return GlobalInfo.getContext().getSharedPreferences(DownloadConstants.SP_AD_DOWNLOAD_EVENT, 0);
    }

    NativeDownloadModel getNativeDownloadModel(long j) {
        return getNativeDownloadModel(String.valueOf(j));
    }

    NativeDownloadModel getNativeDownloadModel(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            String string = getDownloadModelSp().getString(str, "");
            if (TextUtils.isEmpty(string)) {
                return null;
            }
            return NativeDownloadModel.fromJson(new JSONObject(string));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @NonNull
    ConcurrentHashMap<Long, NativeDownloadModel> getAllNativeDownloadModels() {
        ConcurrentHashMap<Long, NativeDownloadModel> concurrentHashMap = new ConcurrentHashMap<>();
        Map<String, ?> all = getDownloadModelSp().getAll();
        if (all == null) {
            return concurrentHashMap;
        }
        for (Map.Entry<String, ?> entry : all.entrySet()) {
            if (entry.getValue() != null) {
                try {
                    long jLongValue = Long.valueOf(entry.getKey()).longValue();
                    NativeDownloadModel nativeDownloadModelFromJson = NativeDownloadModel.fromJson(new JSONObject(String.valueOf(entry.getValue())));
                    if (jLongValue > 0 && nativeDownloadModelFromJson != null) {
                        concurrentHashMap.put(Long.valueOf(jLongValue), nativeDownloadModelFromJson);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return concurrentHashMap;
    }

    public void saveNativeDownloadModel(NativeDownloadModel nativeDownloadModel) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(nativeDownloadModel);
        saveNativeDownloadModels(arrayList);
    }

    public synchronized void saveNativeDownloadModels(final Collection<NativeDownloadModel> collection) {
        if (collection != null) {
            if (!collection.isEmpty()) {
                DownloadComponentManager.getInstance().submitCPUTask(new Runnable() { // from class: com.ss.android.downloadlib.addownload.model.SharedPrefsManager.1
                    @Override // java.lang.Runnable
                    public void run() {
                        SharedPreferences.Editor editorEdit = SharedPrefsManager.this.getDownloadModelSp().edit();
                        for (NativeDownloadModel nativeDownloadModel : collection) {
                            if (nativeDownloadModel != null && nativeDownloadModel.getId() != 0) {
                                editorEdit.putString(String.valueOf(nativeDownloadModel.getId()), nativeDownloadModel.toJson().toString());
                            }
                        }
                        editorEdit.apply();
                    }
                }, true);
            }
        }
    }

    public static List<String> getNativeModelIds(List<NativeDownloadModel> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (NativeDownloadModel nativeDownloadModel : list) {
            if (nativeDownloadModel != null) {
                arrayList.add(String.valueOf(nativeDownloadModel.getId()));
            }
        }
        return arrayList;
    }

    public void removeNativeDownloadModels(final List<String> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        DownloadComponentManager.getInstance().submitCPUTask(new Runnable() { // from class: com.ss.android.downloadlib.addownload.model.SharedPrefsManager.2
            @Override // java.lang.Runnable
            public void run() {
                SharedPreferences.Editor editorEdit = SharedPrefsManager.this.getDownloadModelSp().edit();
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    editorEdit.remove((String) it.next());
                }
                editorEdit.apply();
            }
        }, true);
    }
}
