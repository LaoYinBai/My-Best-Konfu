package com.bytedance.push;

import android.app.Service;
import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import com.bytedance.push.settings.AliveOnlineSettings;
import com.bytedance.push.settings.PushOnlineSettings;
import com.bytedance.push.settings.SettingsManager;
import com.ss.android.pushmanager.setting.PushSetting;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class k implements com.bytedance.push.c.k {

    /* renamed from: b, reason: collision with root package name */
    private final com.bytedance.push.c.i f4863b;

    /* renamed from: c, reason: collision with root package name */
    private final com.bytedance.push.c.g f4864c;
    private final d d;

    @Override // com.bytedance.push.c.k
    public void a(Context context, String str, JSONObject jSONObject) {
    }

    k(com.bytedance.push.c.i iVar, com.bytedance.push.c.g gVar, d dVar) {
        this.f4863b = iVar;
        this.f4864c = gVar;
        this.d = dVar;
    }

    @Override // com.bytedance.push.c.k
    public boolean a(Context context, String str) {
        List<String> listL;
        return (TextUtils.isEmpty(str) || (listL = ((AliveOnlineSettings) SettingsManager.obtain(context, AliveOnlineSettings.class)).l()) == null || !listL.contains(str)) ? false : true;
    }

    @Override // com.bytedance.push.c.k
    public JSONObject a(byte[] bArr, boolean z) throws DataFormatException, IOException {
        return com.bytedance.push.utils.g.a(bArr, z);
    }

    @Override // com.bytedance.push.c.k
    public Pair<String, String> a(int i) {
        return com.bytedance.push.third.e.a(com.ss.android.message.a.a()).a(i, this.d);
    }

    public void b(Context context, int i) {
        String deviceId = PushSetting.getInstance().getDeviceId();
        if (TextUtils.isEmpty(deviceId)) {
            return;
        }
        this.f4863b.a(context.getApplicationContext(), deviceId, i);
    }

    @Override // com.bytedance.push.c.k
    public void a(Context context, com.bytedance.push.third.c cVar) {
        com.bytedance.push.h.a.a(context, cVar);
    }

    @Override // com.bytedance.push.c.k
    public String a(Context context, int i) {
        return com.bytedance.push.h.a.a(context, i);
    }

    @Override // com.bytedance.push.c.k
    public void a(Service service) {
        com.bytedance.push.utils.b.a(service);
    }

    @Override // com.bytedance.push.c.k
    public void a(Context context, String str, int i, String str2) {
        this.f4864c.a(context, str, i);
    }

    @Override // com.bytedance.push.c.k
    public void a(Context context, final int i, final String str) {
        if (com.ss.android.message.a.b.c(context)) {
            i.m().a(context, new com.bytedance.push.third.c() { // from class: com.bytedance.push.k.1
                @Override // com.bytedance.push.third.c
                public String a(Context context2) {
                    return str;
                }

                @Override // com.bytedance.push.third.c
                public int a() {
                    return i;
                }
            });
        }
        if (((PushOnlineSettings) SettingsManager.obtain(com.ss.android.message.a.a(), PushOnlineSettings.class)).i() > 0) {
            com.bytedance.push.utils.e.a("forbid set alias. pushType = " + i + ", token = " + str);
            return;
        }
        b(com.ss.android.message.a.a(), i);
    }

    @Override // com.bytedance.push.c.k
    public void a(int i, String str, String str2) {
        i.l().b("registerSenderFailed: pushType=" + i + " ,errorId=" + str + " ,msg=" + str2);
    }

    @Override // com.bytedance.push.c.k
    public boolean a(Context context) {
        return com.ss.android.message.a.b.c(context);
    }

    @Override // com.bytedance.push.c.k
    public com.ss.android.push.b<String, String, String> a() {
        return this.d.q.d();
    }
}
