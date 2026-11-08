package com.ss.android.downloadlib.addownload.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.ss.union.game.sdk.core.base.R;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SelectOperationDialog extends Dialog implements IReserveWifiView {
    private Activity mActivity;
    private TextView mCancelView;
    private TextView mConfirmView;
    private boolean mIsFromConfirm;
    private String mMessage;
    private TextView mMessageView;
    private String mNegativeBtnText;
    private String mPositiveBtnText;
    private ISelectOperationListener mSelectOperationListener;

    public SelectOperationDialog(@NonNull Activity activity, String str, String str2, String str3, boolean z, @NonNull ISelectOperationListener iSelectOperationListener) {
        super(activity, R.style.ttdownloader_translucent_dialog);
        this.mActivity = activity;
        this.mSelectOperationListener = iSelectOperationListener;
        this.mMessage = str;
        this.mPositiveBtnText = str2;
        this.mNegativeBtnText = str3;
        setCanceledOnTouchOutside(z);
        init();
    }

    private void init() {
        setContentView(LayoutInflater.from(this.mActivity.getApplicationContext()).inflate(getLayoutId(), (ViewGroup) null));
        this.mConfirmView = (TextView) findViewById(getConfirmViewId());
        this.mCancelView = (TextView) findViewById(getCancelViewId());
        this.mMessageView = (TextView) findViewById(R.id.message_tv);
        if (!TextUtils.isEmpty(this.mPositiveBtnText)) {
            this.mConfirmView.setText(this.mPositiveBtnText);
        }
        if (!TextUtils.isEmpty(this.mNegativeBtnText)) {
            this.mCancelView.setText(this.mNegativeBtnText);
        }
        if (!TextUtils.isEmpty(this.mMessage)) {
            this.mMessageView.setText(this.mMessage);
        }
        this.mConfirmView.setOnClickListener(new View.OnClickListener() { // from class: com.ss.android.downloadlib.addownload.dialog.SelectOperationDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SelectOperationDialog.this.confirm();
            }
        });
        this.mCancelView.setOnClickListener(new View.OnClickListener() { // from class: com.ss.android.downloadlib.addownload.dialog.SelectOperationDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SelectOperationDialog.this.clickCancel();
            }
        });
    }

    @Override // com.ss.android.downloadlib.addownload.dialog.IReserveWifiView
    public int getLayoutId() {
        return R.layout.ttdownloader_dialog_select_operation;
    }

    @Override // com.ss.android.downloadlib.addownload.dialog.IReserveWifiView
    public int getConfirmViewId() {
        return R.id.confirm_tv;
    }

    @Override // com.ss.android.downloadlib.addownload.dialog.IReserveWifiView
    public int getCancelViewId() {
        return R.id.cancel_tv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void confirm() {
        this.mIsFromConfirm = true;
        dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickCancel() {
        dismiss();
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        if (!this.mActivity.isFinishing()) {
            this.mActivity.finish();
        }
        if (this.mIsFromConfirm) {
            this.mSelectOperationListener.confirm();
        } else {
            this.mSelectOperationListener.cancel();
        }
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchKeyEvent(@NonNull KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 4) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public static class Builder {
        private Activity mActivity;
        private boolean mCancelableOnTouchOutside;
        private String mMessage;
        private String mNegativeBtnText;
        private String mPositiveBtnText;
        private ISelectOperationListener mSelectOperationListener;

        public Builder(Activity activity) {
            this.mActivity = activity;
        }

        public Builder setMessage(String str) {
            this.mMessage = str;
            return this;
        }

        public Builder setPositiveBtnText(String str) {
            this.mPositiveBtnText = str;
            return this;
        }

        public Builder setNegativeBtnText(String str) {
            this.mNegativeBtnText = str;
            return this;
        }

        public Builder setCancelableOnTouchOutside(boolean z) {
            this.mCancelableOnTouchOutside = z;
            return this;
        }

        public Builder setSelectOperationListener(ISelectOperationListener iSelectOperationListener) {
            this.mSelectOperationListener = iSelectOperationListener;
            return this;
        }

        public SelectOperationDialog build() {
            return new SelectOperationDialog(this.mActivity, this.mMessage, this.mPositiveBtnText, this.mNegativeBtnText, this.mCancelableOnTouchOutside, this.mSelectOperationListener);
        }
    }
}
