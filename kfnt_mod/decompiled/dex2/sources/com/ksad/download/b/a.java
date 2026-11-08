package com.ksad.download.b;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import com.ksad.download.DownloadTask;
import com.ksad.download.d;
import com.kwad.sdk.h.c;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends c {
    private d a;
    private Service c;
    private final Map<String, Integer> b = new ConcurrentHashMap();
    private final HandlerC0017a d = new HandlerC0017a(this);

    /* renamed from: com.ksad.download.b.a$a, reason: collision with other inner class name */
    static class HandlerC0017a extends Handler {
        final WeakReference<a> a;

        public HandlerC0017a(a aVar) {
            this.a = new WeakReference<>(aVar);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            a aVar = this.a.get();
            if (aVar != null && message.what == 1) {
                if (aVar.a == null || !aVar.a.d()) {
                    sendEmptyMessageDelayed(1, 30000L);
                } else {
                    aVar.c.stopSelf();
                }
            }
        }
    }

    private void a(Intent intent) {
        if (intent == null) {
            return;
        }
        try {
            int intExtra = intent.getIntExtra("download_service_type_tag", 0);
            String stringExtra = intent.getStringExtra("download_service_id_tag");
            DownloadTask.DownloadRequest downloadRequest = (DownloadTask.DownloadRequest) intent.getSerializableExtra("download_service_args_tag");
            Integer num = this.b.get(stringExtra);
            switch (intExtra) {
                case 1:
                    this.b.put(stringExtra, Integer.valueOf(this.a.a(downloadRequest, (com.ksad.download.c) null)));
                    break;
                case 2:
                    this.a.d(num.intValue());
                    break;
                case 3:
                    this.a.e(num.intValue());
                    break;
                case 4:
                    this.a.c(num.intValue());
                    break;
            }
        } catch (Exception unused) {
        }
    }

    @Override // com.kwad.sdk.h.c, com.kwad.sdk.api.proxy.IServiceProxy
    public void onCreate(Service service) {
        if (service == null) {
            return;
        }
        this.c = service;
        this.a = d.a();
        this.d.sendEmptyMessageDelayed(1, 30000L);
    }

    @Override // com.kwad.sdk.h.c, com.kwad.sdk.api.proxy.IServiceProxy
    public int onStartCommand(Service service, Intent intent, int i, int i2) {
        a(intent);
        return super.onStartCommand(service, intent, i, i2);
    }
}
