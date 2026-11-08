package com.kwad.sdk.core.download;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class DownloadStatusManager {
    private static com.ksad.download.f f;
    private static final BroadcastReceiver g = new BroadcastReceiver() { // from class: com.kwad.sdk.core.download.DownloadStatusManager.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            DownloadStatusManager.a().a(intent);
        }
    };
    private final WeakHashMap<c, AdTemplate> a;
    private final Map<c, AdTemplate> b;
    private volatile boolean c;
    private final HashMap<String, AdTemplate> d;
    private final Map<String, AdTemplate> e;

    private enum Holder {
        INSTANCE;

        private DownloadStatusManager mInstance = new DownloadStatusManager();
        private final Object lock = new Object();

        Holder() {
        }

        DownloadStatusManager getInstance() {
            if (!this.mInstance.c) {
                synchronized (this.lock) {
                    this.mInstance.b();
                }
            }
            return this.mInstance;
        }
    }

    private DownloadStatusManager() {
        this.a = new WeakHashMap<>();
        this.b = Collections.synchronizedMap(this.a);
        this.c = false;
        this.d = new HashMap<>();
        this.e = Collections.synchronizedMap(this.d);
        b();
    }

    public static DownloadStatusManager a() {
        return Holder.INSTANCE.getInstance();
    }

    public static void a(Context context) {
        if (Holder.INSTANCE.mInstance.c) {
            try {
                synchronized (Holder.INSTANCE.lock) {
                    if (Holder.INSTANCE.mInstance.c) {
                        context.unregisterReceiver(g);
                        Holder.INSTANCE.mInstance.b.clear();
                        Holder.INSTANCE.mInstance.e.clear();
                        Holder.INSTANCE.mInstance.c = false;
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Intent intent) {
        String schemeSpecificPart = (!TextUtils.equals("android.intent.action.PACKAGE_ADDED", intent.getAction()) || intent.getData() == null) ? null : intent.getData().getSchemeSpecificPart();
        if (KsAdSDKImpl.get().isDebugLogEnable()) {
            com.kwad.sdk.core.d.a.a("DownloadStatusManager", "handleInstallApp(), pkgNameAdded=" + schemeSpecificPart);
        }
        if (TextUtils.isEmpty(schemeSpecificPart)) {
            return;
        }
        f fVar = new f();
        synchronized (this.b) {
            for (c cVar : this.b.keySet()) {
                if (cVar != null && !TextUtils.isEmpty(schemeSpecificPart) && TextUtils.equals(schemeSpecificPart, cVar.b())) {
                    cVar.a((String) null, 0, fVar);
                }
            }
        }
        f.a(schemeSpecificPart);
        synchronized (this.e) {
            Iterator<Map.Entry<String, AdTemplate>> it = this.e.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, AdTemplate> next = it.next();
                if (next != null) {
                    String key = next.getKey();
                    if (!TextUtils.isEmpty(schemeSpecificPart) && TextUtils.equals(schemeSpecificPart, key)) {
                        it.remove();
                    }
                }
            }
        }
    }

    private void a(String str, com.kwad.sdk.b.a<c> aVar) {
        Set<c> setKeySet = this.b.keySet();
        synchronized (this.b) {
            for (c cVar : setKeySet) {
                if (cVar != null && TextUtils.equals(cVar.a(), str)) {
                    try {
                        aVar.a(cVar);
                    } catch (Exception e) {
                        com.kwad.sdk.core.d.a.a(e);
                    }
                }
            }
        }
    }

    private void a(String str, f fVar) {
        AdTemplate value;
        for (Map.Entry<String, AdTemplate> entry : this.e.entrySet()) {
            if (entry != null && (value = entry.getValue()) != null) {
                AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(value);
                com.kwad.sdk.core.a.a().a(str, value);
                if (!TextUtils.isEmpty(str) && adInfoJ.downloadId.equals(str) && !value.mDownloadFinishReported) {
                    if (fVar.b()) {
                        com.kwad.sdk.core.report.a.e(value, (JSONObject) null);
                        fVar.a();
                    }
                    value.mDownloadFinishReported = true;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        Context context = KsAdSDKImpl.get().getContext();
        if (this.c || context == null) {
            return;
        }
        f = new com.kwad.sdk.core.download.b.a(context);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        context.registerReceiver(g, intentFilter);
        this.c = true;
    }

    public void a(c cVar) {
        this.b.remove(cVar);
    }

    public void a(c cVar, AdTemplate adTemplate) {
        this.b.put(cVar, adTemplate);
    }

    public void a(AdTemplate adTemplate) {
        try {
            String strV = com.kwad.sdk.core.response.a.a.v(com.kwad.sdk.core.response.a.d.j(adTemplate));
            if (TextUtils.isEmpty(strV)) {
                return;
            }
            this.e.put(strV, adTemplate);
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.a(e);
        }
    }

    public void a(final String str) {
        final f fVar = new f();
        a(str, new com.kwad.sdk.b.a<c>() { // from class: com.kwad.sdk.core.download.DownloadStatusManager.1
            @Override // com.kwad.sdk.b.a
            public void a(c cVar) {
                cVar.a(str, fVar);
            }
        });
    }

    public void a(final String str, final int i, final int i2, final int i3) {
        a(str, new com.kwad.sdk.b.a<c>() { // from class: com.kwad.sdk.core.download.DownloadStatusManager.4
            @Override // com.kwad.sdk.b.a
            public void a(c cVar) {
                cVar.a(str, i, i2, i3);
            }
        });
    }

    public void a(final String str, final int i, final String str2) {
        final f fVar = new f();
        a(str, new com.kwad.sdk.b.a<c>() { // from class: com.kwad.sdk.core.download.DownloadStatusManager.6
            @Override // com.kwad.sdk.b.a
            public void a(c cVar) {
                cVar.a(str, i, str2, fVar);
            }
        });
    }

    public void a(final String str, final String str2) {
        final f fVar = new f();
        a(str, new com.kwad.sdk.b.a<c>() { // from class: com.kwad.sdk.core.download.DownloadStatusManager.5
            @Override // com.kwad.sdk.b.a
            public void a(c cVar) {
                cVar.a(str, str2, fVar);
            }
        });
        a(str, fVar);
    }

    public void b(final String str) {
        final f fVar = new f();
        a(str, new com.kwad.sdk.b.a<c>() { // from class: com.kwad.sdk.core.download.DownloadStatusManager.7
            @Override // com.kwad.sdk.b.a
            public void a(c cVar) {
                cVar.b(str, fVar);
            }
        });
    }

    public void c(final String str) {
        final f fVar = new f();
        a(str, new com.kwad.sdk.b.a<c>() { // from class: com.kwad.sdk.core.download.DownloadStatusManager.8
            @Override // com.kwad.sdk.b.a
            public void a(c cVar) {
                cVar.c(str, fVar);
            }
        });
    }

    public void d(final String str) {
        final f fVar = new f();
        a(str, new com.kwad.sdk.b.a<c>() { // from class: com.kwad.sdk.core.download.DownloadStatusManager.9
            @Override // com.kwad.sdk.b.a
            public void a(c cVar) {
                cVar.d(str, fVar);
            }
        });
    }

    public void e(final String str) {
        final f fVar = new f();
        a(str, new com.kwad.sdk.b.a<c>() { // from class: com.kwad.sdk.core.download.DownloadStatusManager.2
            @Override // com.kwad.sdk.b.a
            public void a(c cVar) {
                cVar.e(str, fVar);
            }
        });
    }
}
