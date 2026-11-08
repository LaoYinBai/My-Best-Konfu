package com.kwai.filedownloader.services;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.Process;
import android.webkit.WebView;
import androidx.annotation.Keep;
import com.kwad.sdk.api.core.KsAdSdkDynamicImpl;
import com.kwad.sdk.api.proxy.app.FileDownloadService;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.lang.ref.WeakReference;

@KsAdSdkDynamicImpl(FileDownloadService.class)
@Keep
@SuppressLint({"Registered"})
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class FileDownloadServiceProxy extends com.kwad.sdk.h.c {
    private static final String TAG = "filedownloader";
    public Service context;
    private i handler;

    @KsAdSdkDynamicImpl(FileDownloadService.SeparateProcessService.class)
    @Keep
    public static class SeparateProcessServiceProxy extends FileDownloadServiceProxy {
        private String getProcessName(Context context) {
            if (context == null) {
                return "ksad";
            }
            try {
                for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService(PushConstants.INTENT_ACTIVITY_NAME)).getRunningAppProcesses()) {
                    if (runningAppProcessInfo.pid == Process.myPid()) {
                        return runningAppProcessInfo.processName;
                    }
                }
                return "ksad";
            } catch (Exception e) {
                e.printStackTrace();
                return "ksad";
            }
        }

        @Override // com.kwai.filedownloader.services.FileDownloadServiceProxy, com.kwad.sdk.h.c, com.kwad.sdk.api.proxy.IServiceProxy
        public void onCreate(Service service) {
            if (Build.VERSION.SDK_INT >= 28) {
                try {
                    WebView.setDataDirectorySuffix(getProcessName(service.getApplicationContext()));
                } catch (Exception unused) {
                }
            }
            super.onCreate(service);
        }
    }

    @KsAdSdkDynamicImpl(FileDownloadService.SharedMainProcessService.class)
    @Keep
    public static class SharedMainProcessServiceProxy extends FileDownloadServiceProxy {
        @Override // com.kwai.filedownloader.services.FileDownloadServiceProxy, com.kwad.sdk.h.c, com.kwad.sdk.api.proxy.IServiceProxy
        public void onCreate(Service service) {
            super.onCreate(service);
        }
    }

    @Override // com.kwad.sdk.h.c, com.kwad.sdk.api.proxy.IServiceProxy
    public IBinder onBind(Service service, Intent intent) {
        return this.handler.a(intent);
    }

    @Override // com.kwad.sdk.h.c, com.kwad.sdk.api.proxy.IServiceProxy
    public void onCreate(Service service) {
        if (service == null) {
            return;
        }
        this.context = service;
        com.kwai.filedownloader.e.c.a(this.context);
        try {
            com.kwai.filedownloader.e.f.a(com.kwai.filedownloader.e.e.a().a);
            com.kwai.filedownloader.e.f.a(com.kwai.filedownloader.e.e.a().b);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        g gVar = new g();
        this.handler = com.kwai.filedownloader.e.e.a().d ? new e(new WeakReference(this), gVar) : new d(new WeakReference(this), gVar);
    }

    @Override // com.kwad.sdk.h.c, com.kwad.sdk.api.proxy.IServiceProxy
    public void onDestroy(Service service) {
        this.handler.e();
    }

    @Override // com.kwad.sdk.h.c, com.kwad.sdk.api.proxy.IServiceProxy
    public int onStartCommand(Service service, Intent intent, int i, int i2) {
        this.handler.a(intent, i, i2);
        return 1;
    }
}
