package com.ss.android.download.api.common;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.ss.android.download.api.config.DownloadUIFactory;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.download.api.model.DownloadAlertDialogInfo;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DefaultDownloadUIFactory implements DownloadUIFactory {
    @Override // com.ss.android.download.api.config.DownloadUIFactory
    public void showToastWithDuration(int i, @Nullable Context context, DownloadModel downloadModel, String str, Drawable drawable, int i2) {
        Toast.makeText(context, str, 0).show();
    }

    @Override // com.ss.android.download.api.config.DownloadUIFactory
    public Dialog showAlertDialog(@NonNull DownloadAlertDialogInfo downloadAlertDialogInfo) {
        return showDialog(downloadAlertDialogInfo);
    }

    @Override // com.ss.android.download.api.config.DownloadUIFactory
    public Notification buildNotification(@NonNull NotificationCompat.Builder builder) {
        return builder.build();
    }

    private static Dialog showDialog(final DownloadAlertDialogInfo downloadAlertDialogInfo) {
        if (downloadAlertDialogInfo == null) {
            return null;
        }
        AlertDialog alertDialogShow = new AlertDialog.Builder(downloadAlertDialogInfo.mContext).setTitle(downloadAlertDialogInfo.mTitle).setMessage(downloadAlertDialogInfo.mMessage).setPositiveButton(downloadAlertDialogInfo.mPositiveBtnText, new DialogInterface.OnClickListener() { // from class: com.ss.android.download.api.common.DefaultDownloadUIFactory.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (downloadAlertDialogInfo.mDialogStatusChangedListener != null) {
                    downloadAlertDialogInfo.mDialogStatusChangedListener.onPositiveBtnClick(dialogInterface);
                }
            }
        }).setNegativeButton(downloadAlertDialogInfo.mNegativeBtnText, new DialogInterface.OnClickListener() { // from class: com.ss.android.download.api.common.DefaultDownloadUIFactory.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (downloadAlertDialogInfo.mDialogStatusChangedListener != null) {
                    downloadAlertDialogInfo.mDialogStatusChangedListener.onNegativeBtnClick(dialogInterface);
                }
            }
        }).show();
        alertDialogShow.setCanceledOnTouchOutside(downloadAlertDialogInfo.mCancelableOnTouchOutside);
        alertDialogShow.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.ss.android.download.api.common.DefaultDownloadUIFactory.3
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                if (downloadAlertDialogInfo.mDialogStatusChangedListener != null) {
                    downloadAlertDialogInfo.mDialogStatusChangedListener.onCancel(dialogInterface);
                }
            }
        });
        if (downloadAlertDialogInfo.mIcon != null) {
            alertDialogShow.setIcon(downloadAlertDialogInfo.mIcon);
        }
        return alertDialogShow;
    }
}
