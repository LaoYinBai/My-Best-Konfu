package com.bytedance.a;

import android.text.TextUtils;
import com.bytedance.applog.AppLog;
import com.bytedance.applog.IDataObserver;
import com.bytedance.applog.onekit.DeviceComponentRegistrar;
import com.volcengine.onekit.component.ComponentContainer;
import com.volcengine.onekit.service.Device;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class w implements IDataObserver {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ComponentContainer f3551a;

    public w(DeviceComponentRegistrar.a aVar, ComponentContainer componentContainer) {
        this.f3551a = componentContainer;
    }

    @Override // com.bytedance.applog.IDataObserver
    public void onAbVidsChange(String str, String str2) {
    }

    @Override // com.bytedance.applog.IDataObserver
    public void onIdLoaded(String str, String str2, String str3) {
        if (TextUtils.isEmpty(AppLog.getDid())) {
            return;
        }
        this.f3551a.set(new ab(), new Class[]{Device.class});
        AppLog.removeDataObserver(this);
    }

    @Override // com.bytedance.applog.IDataObserver
    public void onRemoteAbConfigGet(boolean z, JSONObject jSONObject) {
    }

    @Override // com.bytedance.applog.IDataObserver
    public void onRemoteConfigGet(boolean z, JSONObject jSONObject) {
    }

    @Override // com.bytedance.applog.IDataObserver
    public void onRemoteIdGet(boolean z, String str, String str2, String str3, String str4, String str5, String str6) {
        if (TextUtils.isEmpty(AppLog.getDid())) {
            return;
        }
        this.f3551a.set(new ab(), new Class[]{Device.class});
        AppLog.removeDataObserver(this);
    }
}
