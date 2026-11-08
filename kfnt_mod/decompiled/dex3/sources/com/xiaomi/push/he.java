package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.push.service.XMPushService;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class he implements XMPushService.l {
    private static boolean a;

    /* renamed from: a, reason: collision with other field name */
    private int f466a;

    /* renamed from: a, reason: collision with other field name */
    private Context f467a;
    private boolean b;

    public he(Context context) {
        this.f467a = context;
    }

    private String a(String str) {
        return "com.xiaomi.xmsf".equals(str) ? "1000271" : this.f467a.getSharedPreferences("pref_registered_pkg_names", 0).getString(str, null);
    }

    private void a(Context context) {
        this.b = com.xiaomi.push.service.aq.a(context).a(ho.TinyDataUploadSwitch.a(), true);
        this.f466a = com.xiaomi.push.service.aq.a(context).a(ho.TinyDataUploadFrequency.a(), 7200);
        this.f466a = Math.max(60, this.f466a);
    }

    public static void a(boolean z) {
        a = z;
    }

    private boolean a() {
        return Math.abs((System.currentTimeMillis() / 1000) - this.f467a.getSharedPreferences("mipush_extra", 4).getLong("last_tiny_data_upload_timestamp", -1L)) > ((long) this.f466a);
    }

    private boolean a(hi hiVar) {
        if (!bg.b(this.f467a) || hiVar == null || TextUtils.isEmpty(a(this.f467a.getPackageName())) || !new File(this.f467a.getFilesDir(), "tiny_data.data").exists() || a) {
            return false;
        }
        return !com.xiaomi.push.service.aq.a(this.f467a).a(ho.ScreenOnOrChargingTinyDataUploadSwitch.a(), false) || i.m435a(this.f467a) || i.m438b(this.f467a);
    }

    @Override // com.xiaomi.push.service.XMPushService.l
    /* renamed from: a, reason: collision with other method in class */
    public void mo381a() {
        a(this.f467a);
        if (this.b && a()) {
            com.xiaomi.channel.commonutils.logger.b.m22a("TinyData TinyDataCacheProcessor.pingFollowUpAction ts:" + System.currentTimeMillis());
            hi hiVarA = hh.a(this.f467a).a();
            if (a(hiVarA)) {
                a = true;
                hf.a(this.f467a, hiVarA);
            } else {
                com.xiaomi.channel.commonutils.logger.b.m22a("TinyData TinyDataCacheProcessor.pingFollowUpAction !canUpload(uploader) ts:" + System.currentTimeMillis());
            }
        }
    }
}
