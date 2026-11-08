package com.bytedance.services.slardar.config;

import androidx.annotation.Nullable;
import com.bytedance.news.common.service.manager.IService;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IConfigManager extends IService {
    void fetchConfig();

    int getConfigInt(String str, int i);

    @Nullable
    JSONObject getConfigJSON(String str);

    boolean getLogTypeSwitch(String str);

    boolean getMetricTypeSwitch(String str);

    boolean getServiceSwitch(String str);

    boolean getSwitch(String str);

    boolean isConfigReady();

    String queryConfig();

    void registerConfigListener(a aVar);

    void registerResponseConfigListener(b bVar);

    void unregisterConfigListener(a aVar);

    void unregisterResponseConfigListener(b bVar);
}
