package com.ss.android.download.api.config;

import android.app.Dialog;
import android.app.Notification;
import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.download.api.model.DownloadAlertDialogInfo;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface DownloadUIFactory {
    Notification buildNotification(@NonNull NotificationCompat.Builder builder);

    Dialog showAlertDialog(@NonNull DownloadAlertDialogInfo downloadAlertDialogInfo);

    void showToastWithDuration(int i, @Nullable Context context, @Nullable DownloadModel downloadModel, String str, Drawable drawable, int i2);
}
