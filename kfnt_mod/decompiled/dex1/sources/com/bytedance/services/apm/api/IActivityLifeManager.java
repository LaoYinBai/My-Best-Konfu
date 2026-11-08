package com.bytedance.services.apm.api;

import com.bytedance.news.common.service.manager.IService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IActivityLifeManager extends IService {
    boolean isForeground();

    void register(e eVar);

    void unregister(e eVar);
}
