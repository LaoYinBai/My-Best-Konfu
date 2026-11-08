package com.ss.android.socialbase.appdownloader.depend;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IDownloadAlertDialogBuilder {
    IDownloadAlertDialogBuilder setCanceledOnTouchOutside(boolean z);

    IDownloadAlertDialogBuilder setIcon(int i);

    IDownloadAlertDialogBuilder setIcon(Drawable drawable);

    IDownloadAlertDialogBuilder setMessage(String str);

    IDownloadAlertDialogBuilder setNegativeButton(int i, DialogInterface.OnClickListener onClickListener);

    IDownloadAlertDialogBuilder setNegativeButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener);

    IDownloadAlertDialogBuilder setOnCancelListener(DialogInterface.OnCancelListener onCancelListener);

    IDownloadAlertDialogBuilder setPositiveButton(int i, DialogInterface.OnClickListener onClickListener);

    IDownloadAlertDialogBuilder setPositiveButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener);

    IDownloadAlertDialogBuilder setTitle(int i);

    IDownloadAlertDialog show();
}
