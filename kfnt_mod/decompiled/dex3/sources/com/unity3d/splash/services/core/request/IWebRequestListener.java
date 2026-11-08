package com.unity3d.splash.services.core.request;

import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IWebRequestListener {
    void onComplete(String str, String str2, int i, Map map);

    void onFailed(String str, String str2);
}
