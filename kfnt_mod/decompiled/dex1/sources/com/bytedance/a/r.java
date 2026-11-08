package com.bytedance.a;

import android.os.Bundle;
import com.bytedance.applog.AppLog;
import com.volcengine.onekit.service.Analytics;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class r implements Analytics {
    public void a(String str, Bundle bundle) {
        AppLog.onEventV3(str, bundle);
    }

    public void a(String str, JSONObject jSONObject) {
        AppLog.onEventV3(str, jSONObject);
    }
}
