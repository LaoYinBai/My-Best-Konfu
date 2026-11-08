package com.huawei.hms.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.view.KeyEvent;
import com.huawei.hms.support.log.HMSLog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class AbstractDialog {
    private Activity a;
    private AlertDialog b;
    private Callback c;

    public interface Callback {
        void onCancel(AbstractDialog abstractDialog);

        void onDoWork(AbstractDialog abstractDialog);
    }

    protected abstract String onGetMessageString(Context context);

    protected abstract String onGetNegativeButtonString(Context context);

    protected abstract String onGetPositiveButtonString(Context context);

    protected abstract String onGetTitleString(Context context);

    private static int a(Context context) {
        if (context == null) {
            return 0;
        }
        return context.getResources().getIdentifier("androidhwext:style/Theme.Emui", null, null);
    }

    protected AlertDialog onCreateDialog(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), getDialogThemeId());
        String strOnGetTitleString = onGetTitleString(activity);
        if (strOnGetTitleString != null) {
            builder.setTitle(strOnGetTitleString);
        }
        String strOnGetMessageString = onGetMessageString(activity);
        if (strOnGetMessageString != null) {
            builder.setMessage(strOnGetMessageString);
        }
        String strOnGetPositiveButtonString = onGetPositiveButtonString(activity);
        if (strOnGetPositiveButtonString != null) {
            builder.setPositiveButton(strOnGetPositiveButtonString, new DialogInterface.OnClickListener() { // from class: com.huawei.hms.ui.AbstractDialog.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    AbstractDialog.this.fireDoWork();
                }
            });
        }
        String strOnGetNegativeButtonString = onGetNegativeButtonString(activity);
        if (strOnGetNegativeButtonString != null) {
            builder.setNegativeButton(strOnGetNegativeButtonString, new DialogInterface.OnClickListener() { // from class: com.huawei.hms.ui.AbstractDialog.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    AbstractDialog.this.cancel();
                }
            });
        }
        return builder.create();
    }

    public void show(Activity activity, Callback callback) {
        this.a = activity;
        this.c = callback;
        if (this.a == null || this.a.isFinishing()) {
            HMSLog.e("AbstractDialog", "In show, The activity is null or finishing.");
            return;
        }
        this.b = onCreateDialog(this.a);
        this.b.setCanceledOnTouchOutside(false);
        this.b.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.huawei.hms.ui.AbstractDialog.3
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                AbstractDialog.this.fireCancel();
            }
        });
        this.b.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.huawei.hms.ui.AbstractDialog.4
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (4 != i || keyEvent.getAction() != 1) {
                    return false;
                }
                AbstractDialog.this.cancel();
                return true;
            }
        });
        this.b.show();
    }

    public void cancel() {
        if (this.b != null) {
            this.b.cancel();
        }
    }

    public void dismiss() {
        if (this.b != null) {
            this.b.dismiss();
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (this.b != null) {
            this.b.setTitle(charSequence);
        }
    }

    public void setMessage(CharSequence charSequence) {
        if (this.b != null) {
            this.b.setMessage(charSequence);
        }
    }

    protected void fireCancel() {
        if (this.c != null) {
            this.c.onCancel(this);
        }
    }

    protected void fireDoWork() {
        if (this.c != null) {
            this.c.onDoWork(this);
        }
    }

    protected Activity getActivity() {
        return this.a;
    }

    protected int getDialogThemeId() {
        return (a(this.a) == 0 || Build.VERSION.SDK_INT < 16) ? 3 : 0;
    }
}
