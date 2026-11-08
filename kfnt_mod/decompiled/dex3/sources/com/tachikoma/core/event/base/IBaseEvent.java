package com.tachikoma.core.event.base;

import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IBaseEvent {
    void configWithData(HashMap<String, Object> map);

    void setState(int i);

    void setType(String str);
}
