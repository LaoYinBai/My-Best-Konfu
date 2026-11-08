package com.qq.e.comm.managers;

import com.qq.e.ads.dfa.GDTAppDialogClickListener;
import com.qq.e.comm.managers.devtool.DevTools;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IGDTAdManager {
    String getBuyerId();

    DevTools getDevTools();

    String getSDKInfo(String str);

    int showOpenOrInstallAppDialog(GDTAppDialogClickListener gDTAppDialogClickListener);
}
