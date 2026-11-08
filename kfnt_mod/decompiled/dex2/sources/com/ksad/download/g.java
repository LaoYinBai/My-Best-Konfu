package com.ksad.download;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.api.core.fragment.FileProvider;
import com.kwad.sdk.core.download.DownloadParams;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.io.File;
import java.util.Iterator;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class g {
    public static PendingIntent a(DownloadTask downloadTask) {
        File file = new File(downloadTask.getTargetFilePath());
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(268435456);
        intent.addFlags(3);
        Context contextA = b.a();
        Uri uriA = a(contextA, file);
        intent.setDataAndType(uriA, "application/vnd.android.package-archive");
        Iterator<ResolveInfo> it = contextA.getPackageManager().queryIntentActivities(intent, C.DEFAULT_BUFFER_SEGMENT_SIZE).iterator();
        while (it.hasNext()) {
            contextA.grantUriPermission(it.next().activityInfo.packageName, uriA, 3);
        }
        if (KsAdSDKImpl.get().isEnableInstallPermission() && (downloadTask.getTag() instanceof DownloadParams) && ((DownloadParams) downloadTask.getTag()).requestInstallPermission) {
            intent = a(intent);
        }
        return PendingIntent.getActivity(contextA, downloadTask.getId(), intent, 134217728);
    }

    public static PendingIntent a(File file, int i, boolean z) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(268435456);
        intent.addFlags(3);
        Context contextA = b.a();
        Uri uriA = a(contextA, file);
        intent.setDataAndType(uriA, "application/vnd.android.package-archive");
        Iterator<ResolveInfo> it = contextA.getPackageManager().queryIntentActivities(intent, C.DEFAULT_BUFFER_SEGMENT_SIZE).iterator();
        while (it.hasNext()) {
            contextA.grantUriPermission(it.next().activityInfo.packageName, uriA, 3);
        }
        if (z && KsAdSDKImpl.get().isEnableInstallPermission()) {
            intent = a(intent);
        }
        return PendingIntent.getActivity(contextA, i, intent, 134217728);
    }

    public static PendingIntent a(String str, int i) {
        Context contextA = b.a();
        Intent launchIntentForPackage = contextA.getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage == null) {
            return null;
        }
        return PendingIntent.getActivity(contextA, i, launchIntentForPackage, 134217728);
    }

    private static Intent a(Intent intent) {
        Intent intent2 = new Intent("intent.action.requestInstallPermission");
        intent2.putExtra("fromNotification", true);
        intent2.putExtra(BaseGmsClient.KEY_PENDING_INTENT, intent);
        intent2.addFlags(268435456);
        return intent2;
    }

    private static Uri a(Context context, File file) {
        if (Build.VERSION.SDK_INT < 24) {
            return Uri.fromFile(file);
        }
        return FileProvider.getUriForFile(context, context.getPackageName() + ".adFileProvider", file);
    }
}
