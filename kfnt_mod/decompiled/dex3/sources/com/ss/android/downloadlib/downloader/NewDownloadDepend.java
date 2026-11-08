package com.ss.android.downloadlib.downloader;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.widget.Button;
import com.ss.android.download.api.model.DownloadAlertDialogInfo;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.utils.TLogger;
import com.ss.android.socialbase.appdownloader.depend.AbsAppDownloadDepend;
import com.ss.android.socialbase.appdownloader.depend.IDownloadAlertDialog;
import com.ss.android.socialbase.appdownloader.depend.IDownloadAlertDialogBuilder;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NewDownloadDepend extends AbsAppDownloadDepend {
    private static String TAG = "NewDownloadDepend";

    @Override // com.ss.android.socialbase.appdownloader.depend.AbsAppDownloadDepend, com.ss.android.socialbase.appdownloader.depend.IAppDownloadDepend
    public boolean getAllowBootReceiver() {
        return true;
    }

    @Override // com.ss.android.socialbase.appdownloader.depend.AbsAppDownloadDepend, com.ss.android.socialbase.appdownloader.depend.IAppDownloadDepend
    public boolean getAllowNetwork(Context context) {
        return true;
    }

    @Override // com.ss.android.socialbase.appdownloader.depend.AbsAppDownloadDepend
    public boolean needClearWhenTaskReset() {
        return false;
    }

    @Override // com.ss.android.socialbase.appdownloader.depend.AbsAppDownloadDepend, com.ss.android.socialbase.appdownloader.depend.IAppDownloadDepend
    public IDownloadAlertDialogBuilder getThemedAlertDlgBuilder(final Context context) {
        return new IDownloadAlertDialogBuilder() { // from class: com.ss.android.downloadlib.downloader.NewDownloadDepend.1
            private DownloadAlertDialogInfo.Builder mBuilder;
            private DialogInterface.OnCancelListener mCancelListener;
            private DialogInterface.OnClickListener mNegativeClickListener;
            private DialogInterface.OnClickListener mPositiveClickListener;

            @Override // com.ss.android.socialbase.appdownloader.depend.IDownloadAlertDialogBuilder
            public IDownloadAlertDialogBuilder setIcon(int i) {
                return this;
            }

            {
                this.mBuilder = new DownloadAlertDialogInfo.Builder(context);
            }

            @Override // com.ss.android.socialbase.appdownloader.depend.IDownloadAlertDialogBuilder
            public IDownloadAlertDialogBuilder setIcon(Drawable drawable) {
                this.mBuilder.setIcon(drawable);
                return this;
            }

            @Override // com.ss.android.socialbase.appdownloader.depend.IDownloadAlertDialogBuilder
            public IDownloadAlertDialogBuilder setTitle(int i) {
                this.mBuilder.setTitle(context.getResources().getString(i));
                return this;
            }

            @Override // com.ss.android.socialbase.appdownloader.depend.IDownloadAlertDialogBuilder
            public IDownloadAlertDialogBuilder setMessage(String str) {
                this.mBuilder.setMessage(str);
                return this;
            }

            @Override // com.ss.android.socialbase.appdownloader.depend.IDownloadAlertDialogBuilder
            public IDownloadAlertDialogBuilder setPositiveButton(int i, DialogInterface.OnClickListener onClickListener) {
                this.mBuilder.setPositiveBtnText(context.getResources().getString(i));
                this.mPositiveClickListener = onClickListener;
                return this;
            }

            @Override // com.ss.android.socialbase.appdownloader.depend.IDownloadAlertDialogBuilder
            public IDownloadAlertDialogBuilder setPositiveButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
                this.mBuilder.setPositiveBtnText((String) charSequence);
                this.mPositiveClickListener = onClickListener;
                return this;
            }

            @Override // com.ss.android.socialbase.appdownloader.depend.IDownloadAlertDialogBuilder
            public IDownloadAlertDialogBuilder setNegativeButton(int i, DialogInterface.OnClickListener onClickListener) {
                this.mBuilder.setNegativeBtnText(context.getResources().getString(i));
                this.mNegativeClickListener = onClickListener;
                return this;
            }

            @Override // com.ss.android.socialbase.appdownloader.depend.IDownloadAlertDialogBuilder
            public IDownloadAlertDialogBuilder setNegativeButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
                this.mBuilder.setNegativeBtnText((String) charSequence);
                this.mNegativeClickListener = onClickListener;
                return this;
            }

            @Override // com.ss.android.socialbase.appdownloader.depend.IDownloadAlertDialogBuilder
            public IDownloadAlertDialogBuilder setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
                this.mCancelListener = onCancelListener;
                return this;
            }

            @Override // com.ss.android.socialbase.appdownloader.depend.IDownloadAlertDialogBuilder
            public IDownloadAlertDialogBuilder setCanceledOnTouchOutside(boolean z) {
                this.mBuilder.setCancelableOnTouchOutside(z);
                return this;
            }

            @Override // com.ss.android.socialbase.appdownloader.depend.IDownloadAlertDialogBuilder
            public IDownloadAlertDialog show() {
                this.mBuilder.setDialogStatusChangedListener(new DownloadAlertDialogInfo.OnDialogStatusChangedListener() { // from class: com.ss.android.downloadlib.downloader.NewDownloadDepend.1.1
                    @Override // com.ss.android.download.api.model.DownloadAlertDialogInfo.OnDialogStatusChangedListener
                    public void onPositiveBtnClick(DialogInterface dialogInterface) {
                        if (AnonymousClass1.this.mPositiveClickListener != null) {
                            AnonymousClass1.this.mPositiveClickListener.onClick(dialogInterface, -1);
                        }
                    }

                    @Override // com.ss.android.download.api.model.DownloadAlertDialogInfo.OnDialogStatusChangedListener
                    public void onNegativeBtnClick(DialogInterface dialogInterface) {
                        if (AnonymousClass1.this.mNegativeClickListener != null) {
                            AnonymousClass1.this.mNegativeClickListener.onClick(dialogInterface, -2);
                        }
                    }

                    @Override // com.ss.android.download.api.model.DownloadAlertDialogInfo.OnDialogStatusChangedListener
                    public void onCancel(DialogInterface dialogInterface) {
                        if (AnonymousClass1.this.mCancelListener == null || dialogInterface == null) {
                            return;
                        }
                        AnonymousClass1.this.mCancelListener.onCancel(dialogInterface);
                    }
                });
                TLogger.v(NewDownloadDepend.TAG, "getThemedAlertDlgBuilder", null);
                this.mBuilder.setScene(3);
                return new DownloadAlertDialog(GlobalInfo.getDownloadUIFactory().showAlertDialog(this.mBuilder.build()));
            }
        };
    }

    private static class DownloadAlertDialog implements IDownloadAlertDialog {
        private Dialog dialog;

        @Override // com.ss.android.socialbase.appdownloader.depend.IDownloadAlertDialog
        public Button getButton(int i) {
            return null;
        }

        public DownloadAlertDialog(Dialog dialog) {
            if (dialog != null) {
                this.dialog = dialog;
                show();
            }
        }

        @Override // com.ss.android.socialbase.appdownloader.depend.IDownloadAlertDialog
        public void show() {
            if (this.dialog != null) {
                this.dialog.show();
            }
        }

        @Override // com.ss.android.socialbase.appdownloader.depend.IDownloadAlertDialog
        public void dismiss() {
            if (this.dialog != null) {
                this.dialog.dismiss();
            }
        }

        @Override // com.ss.android.socialbase.appdownloader.depend.IDownloadAlertDialog
        public boolean isShowing() {
            if (this.dialog != null) {
                return this.dialog.isShowing();
            }
            return false;
        }
    }
}
