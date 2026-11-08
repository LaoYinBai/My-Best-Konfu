package com.bytedance.a;

import com.bytedance.applog.AppLog;
import com.volcengine.onekit.service.Device;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ab implements Device {
    public String a() {
        return AppLog.getDid();
    }

    public String b() {
        return AppLog.getIid();
    }

    public String c() {
        return AppLog.getSsid();
    }
}
