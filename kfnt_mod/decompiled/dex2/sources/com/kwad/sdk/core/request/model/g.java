package com.kwad.sdk.core.request.model;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.kuaishou.weapon.adsdk.DeviceInfo;
import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.crash.utils.SystemUtil;
import com.kwad.sdk.utils.AbiUtil;
import com.kwad.sdk.utils.av;
import com.kwad.sdk.utils.bg;
import com.kwad.sdk.utils.t;
import com.mbridge.msdk.playercommon.exoplayer2.util.MimeTypes;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class g implements com.kwad.sdk.core.b {
    private int a;
    private String b;
    private int c;
    private Long d;
    private Long e;
    private Long f;
    private Long g;
    private String h;
    private String i;
    private long k;
    private String l;
    private String m;
    private long n;
    private String p;
    private String q;
    private boolean s;
    private List<a> u;
    private com.kwad.sdk.collector.kwai.f v;
    private com.kwad.sdk.collector.kwai.d w;
    private c x;
    private List<com.kwad.sdk.collector.kwai.e> y;
    private List<com.kwad.sdk.core.request.model.a> j = new ArrayList();
    private int o = -1;
    private float r = -1.0f;
    private int t = -1;

    public static class a extends com.kwad.sdk.core.response.kwai.a implements com.kwad.sdk.core.b {
        public int a;
        public int b = -1;
        public int c = -1;
        public int d = -1;

        public a(int i) {
            this.a = -1;
            this.a = i;
        }

        private int a(int i) {
            switch (i) {
                case 0:
                default:
                    return 0;
                case 1:
                    return 1;
                case 2:
                    return 2;
                case 3:
                    return 3;
                case 4:
                    return 4;
                case 5:
                    return 5;
            }
        }

        public static List<a> a(Context context) {
            ArrayList arrayList = new ArrayList();
            if (com.kwad.sdk.core.config.b.a(256L)) {
                return arrayList;
            }
            for (int i = 0; i <= 5; i++) {
                a aVar = new a(i);
                if (com.kwad.sdk.b.a.booleanValue()) {
                    aVar.b(context);
                } else {
                    aVar.c(context);
                }
                arrayList.add(aVar);
            }
            return arrayList;
        }

        public a b(Context context) {
            if (context == null) {
                return null;
            }
            int[] audioVolumes = DeviceInfo.getAudioVolumes(context, a(this.a));
            if (audioVolumes != null && audioVolumes.length == 3) {
                this.d = audioVolumes[0];
                this.b = audioVolumes[1];
                if (Build.VERSION.SDK_INT >= 28) {
                    this.c = audioVolumes[2];
                }
            }
            return this;
        }

        public a c(Context context) {
            if (context == null) {
                return null;
            }
            int iA = a(this.a);
            AudioManager audioManager = (AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
            this.d = audioManager.getStreamVolume(iA);
            this.b = audioManager.getStreamMaxVolume(iA);
            if (Build.VERSION.SDK_INT >= 28) {
                this.c = audioManager.getStreamMinVolume(iA);
            }
            return this;
        }
    }

    @WorkerThread
    public static g a() {
        g gVar = new g();
        gVar.a = av.c();
        gVar.b = AbiUtil.a();
        gVar.c = av.a(KsAdSDKImpl.get().getContext());
        gVar.d = Long.valueOf(av.b(KsAdSDKImpl.get().getContext()));
        gVar.e = Long.valueOf(av.c(KsAdSDKImpl.get().getContext()));
        gVar.f = Long.valueOf(av.a());
        gVar.g = Long.valueOf(av.b());
        gVar.h = av.h(KsAdSDKImpl.get().getContext());
        gVar.i = av.j(KsAdSDKImpl.get().getContext());
        gVar.j = bg.a(KsAdSDKImpl.get().getContext(), 15);
        gVar.k = av.h();
        gVar.n = av.i();
        gVar.q = av.j();
        gVar.p = av.k();
        gVar.l = av.l();
        gVar.m = av.m();
        Context context = KsAdSDKImpl.get().getContext();
        if (context != null) {
            gVar.o = av.m(context);
            gVar.u = a.a(context);
            gVar.r = SystemUtil.d(context);
            gVar.a(context);
        }
        gVar.w = com.kwad.sdk.collector.kwai.c.a();
        gVar.x = av.l(context);
        gVar.y = com.kwad.sdk.collector.i.b();
        gVar.v = av.k(context);
        return gVar;
    }

    private void a(@NonNull Context context) {
        int chargeType;
        if (com.kwad.sdk.core.config.b.a(512L)) {
            return;
        }
        if (com.kwad.sdk.b.a.booleanValue()) {
            this.s = DeviceInfo.isCharging(context);
            chargeType = DeviceInfo.getChargeType(context);
        } else {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
            intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
            intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
            Intent intentRegisterReceiver = context.registerReceiver(null, intentFilter);
            if (intentRegisterReceiver == null) {
                return;
            }
            int intExtra = intentRegisterReceiver.getIntExtra("status", -1);
            this.s = intExtra == 2 || intExtra == 5;
            int intExtra2 = intentRegisterReceiver.getIntExtra("plugged", -1);
            if (intExtra2 == 2) {
                this.t = 1;
                return;
            }
            if (intExtra2 == 1) {
                this.t = 2;
                return;
            } else {
                if (intExtra2 != 4) {
                    if (intExtra2 == 0) {
                        this.t = 0;
                        return;
                    }
                    return;
                }
                chargeType = 3;
            }
        }
        this.t = chargeType;
    }

    @Override // com.kwad.sdk.core.b
    public void parseJson(@Nullable JSONObject jSONObject) {
    }

    @Override // com.kwad.sdk.core.b
    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        t.a(jSONObject, "cpuCount", this.a);
        t.a(jSONObject, "cpuAbi", this.b);
        t.a(jSONObject, "batteryPercent", this.c);
        t.a(jSONObject, "totalMemorySize", this.d.longValue());
        t.a(jSONObject, "availableMemorySize", this.e.longValue());
        t.a(jSONObject, "totalDiskSize", this.f.longValue());
        t.a(jSONObject, "availableDiskSize", this.g.longValue());
        t.a(jSONObject, "imsi", this.h);
        t.a(jSONObject, "iccid", this.i);
        t.a(jSONObject, "wifiList", this.j);
        t.a(jSONObject, "bootTime", this.k);
        t.a(jSONObject, "romName", this.l);
        t.a(jSONObject, "romVersion", this.m);
        t.a(jSONObject, "romBuildTimestamp", this.n);
        t.a(jSONObject, "ringerMode", this.o);
        t.a(jSONObject, "audioStreamInfo", this.u);
        t.a(jSONObject, "baseBandVersion", this.p);
        t.a(jSONObject, "fingerPrint", this.q);
        t.a(jSONObject, "screenBrightness", this.r);
        t.a(jSONObject, "isCharging", this.s);
        t.a(jSONObject, "chargeType", this.t);
        if (this.v != null) {
            t.a(jSONObject, "simCardInfo", this.v);
        }
        if (this.w != null) {
            t.a(jSONObject, "environmentInfo", this.w);
        }
        if (this.x != null) {
            t.a(jSONObject, "baseStationInfo", this.x);
        }
        if (this.y != null) {
            t.a(jSONObject, "sensorEventInfoList", this.y);
        }
        return jSONObject;
    }
}
