package com.ksad.download.kwai;

import android.content.Context;
import android.content.Intent;
import com.ksad.download.DownloadTask;
import com.kwad.sdk.api.proxy.app.DownloadService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    public static void a(Context context, String str) {
        com.kwad.sdk.core.d.a.a("DownloadClient", "pauseDownload");
        try {
            Intent intent = new Intent(context, (Class<?>) DownloadService.class);
            intent.putExtra("download_service_type_tag", 2);
            intent.putExtra("download_service_id_tag", str);
            context.startService(intent);
        } catch (Exception unused) {
        }
    }

    public static void a(Context context, String str, DownloadTask.DownloadRequest downloadRequest) {
        try {
            Intent intent = new Intent(context, (Class<?>) DownloadService.class);
            intent.putExtra("download_service_type_tag", 1);
            intent.putExtra("download_service_id_tag", str);
            intent.putExtra("download_service_args_tag", downloadRequest);
            context.startService(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void b(Context context, String str) {
        com.kwad.sdk.core.d.a.a("DownloadClient", "cancelDownload");
        try {
            Intent intent = new Intent(context, (Class<?>) DownloadService.class);
            intent.putExtra("download_service_type_tag", 4);
            intent.putExtra("download_service_id_tag", str);
            context.startService(intent);
        } catch (Exception unused) {
        }
    }
}
