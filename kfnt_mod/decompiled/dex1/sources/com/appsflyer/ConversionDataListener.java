package com.appsflyer;

import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface ConversionDataListener {
    void onConversionDataLoaded(Map<String, String> map);

    void onConversionFailure(String str);
}
