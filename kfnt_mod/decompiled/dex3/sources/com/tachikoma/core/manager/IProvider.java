package com.tachikoma.core.manager;

import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IProvider<T> {
    void apply(String str, Object obj, HashMap<String, Object> map);

    void clear();

    HashMap<String, String> get();

    void init();

    T of(String str);

    HashMap<String, Object> retrieveEvent(String str, Object obj);
}
