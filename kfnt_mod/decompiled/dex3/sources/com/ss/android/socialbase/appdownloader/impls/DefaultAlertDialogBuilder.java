package com.ss.android.socialbase.appdownloader.impls;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Button;
import com.ss.android.socialbase.appdownloader.depend.AbsDownloadAlertDialogBuilder;
import com.ss.android.socialbase.appdownloader.depend.IDownloadAlertDialog;
import com.ss.android.socialbase.appdownloader.depend.IDownloadAlertDialogBuilder;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DefaultAlertDialogBuilder extends AbsDownloadAlertDialogBuilder {
    private AlertDialog.Builder builder;

    public DefaultAlertDialogBuilder(Context context) {
        this.builder = new AlertDialog.Builder(context);
    }

    @Override // com.ss.android.socialbase.appdownloader.depend.IDownloadAlertDialogBuilder
    public IDownloadAlertDialogBuilder setTitle(int i) {
        if (this.builder != null) {
            this.builder.setTitle(i);
        }
        return this;
    }

    @Override // com.ss.android.socialbase.appdownloader.depend.IDownloadAlertDialogBuilder
    public IDownloadAlertDialogBuilder setMessage(String str) {
        if (this.builder != null) {
            this.builder.setMessage(str);
        }
        return this;
    }

    @Override // com.ss.android.socialbase.appdownloader.depend.IDownloadAlertDialogBuilder
    public IDownloadAlertDialogBuilder setPositiveButton(int i, DialogInterface.OnClickListener onClickListener) {
        if (this.builder != null) {
            this.builder.setPositiveButton(i, onClickListener);
        }
        return this;
    }

    @Override // com.ss.android.socialbase.appdownloader.depend.IDownloadAlertDialogBuilder
    public IDownloadAlertDialogBuilder setPositiveButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        if (this.builder != null) {
            this.builder.setPositiveButton(charSequence, onClickListener);
        }
        return this;
    }

    @Override // com.ss.android.socialbase.appdownloader.depend.IDownloadAlertDialogBuilder
    public IDownloadAlertDialogBuilder setNegativeButton(int i, DialogInterface.OnClickListener onClickListener) {
        if (this.builder != null) {
            this.builder.setNegativeButton(i, onClickListener);
        }
        return this;
    }

    @Override // com.ss.android.socialbase.appdownloader.depend.IDownloadAlertDialogBuilder
    public IDownloadAlertDialogBuilder setNegativeButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        if (this.builder != null) {
            this.builder.setNegativeButton(charSequence, onClickListener);
        }
        return this;
    }

    @Override // com.ss.android.socialbase.appdownloader.depend.IDownloadAlertDialogBuilder
    public IDownloadAlertDialogBuilder setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        if (this.builder != null) {
            this.builder.setOnCancelListener(onCancelListener);
        }
        return this;
    }

    @Override // com.ss.android.socialbase.appdownloader.depend.IDownloadAlertDialogBuilder
    public IDownloadAlertDialog show() {
        return new DownloadAlertDialog(this.builder);
    }

    private static class DownloadAlertDialog implements IDownloadAlertDialog {
        private AlertDialog alertDialog;

        public DownloadAlertDialog(AlertDialog.Builder builder) {
            if (builder != null) {
                this.alertDialog = builder.show();
            }
        }

        @Override // com.ss.android.socialbase.appdownloader.depend.IDownloadAlertDialog
        public void show() {
            if (this.alertDialog != null) {
                this.alertDialog.show();
            }
        }

        @Override // com.ss.android.socialbase.appdownloader.depend.IDownloadAlertDialog
        public void dismiss() {
            if (this.alertDialog != null) {
                this.alertDialog.dismiss();
            }
        }

        @Override // com.ss.android.socialbase.appdownloader.depend.IDownloadAlertDialog
        public boolean isShowing() {
            if (this.alertDialog != null) {
                return this.alertDialog.isShowing();
            }
            return false;
        }

        @Override // com.ss.android.socialbase.appdownloader.depend.IDownloadAlertDialog
        public Button getButton(int i) {
            if (this.alertDialog != null) {
                return this.alertDialog.getButton(i);
            }
            return null;
        }
    }
}
