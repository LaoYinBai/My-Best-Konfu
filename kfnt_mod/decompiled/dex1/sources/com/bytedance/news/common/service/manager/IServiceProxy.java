package com.bytedance.news.common.service.manager;

import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IServiceProxy<T> {
    void collectService(Map<String, String> map);

    T newInstance();
}
