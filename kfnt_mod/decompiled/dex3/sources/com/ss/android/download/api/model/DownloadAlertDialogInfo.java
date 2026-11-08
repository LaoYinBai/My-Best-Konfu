package com.ss.android.download.api.model;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.View;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DownloadAlertDialogInfo {
    public boolean mCancelableOnTouchOutside;
    public Context mContext;
    public OnDialogStatusChangedListener mDialogStatusChangedListener;
    public Drawable mIcon;
    public String mMessage;
    public String mNegativeBtnText;
    public String mPositiveBtnText;
    public int mScene;
    public String mTitle;
    public View mView;

    public interface OnDialogStatusChangedListener {
        void onCancel(DialogInterface dialogInterface);

        void onNegativeBtnClick(DialogInterface dialogInterface);

        void onPositiveBtnClick(DialogInterface dialogInterface);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Scene {
        public static final int BACK_INSTALL = 1;
        public static final int CANCEL = 3;
        public static final int OPEN_APP = 2;
        public static final int WEBVIEW_START = 0;
    }

    private DownloadAlertDialogInfo(Builder builder) {
        this.mCancelableOnTouchOutside = true;
        this.mContext = builder.mContext;
        this.mTitle = builder.mTitle;
        this.mMessage = builder.mMessage;
        this.mPositiveBtnText = builder.mPositiveBtnText;
        this.mNegativeBtnText = builder.mNegativeBtnText;
        this.mCancelableOnTouchOutside = builder.mCancelableOnTouchOutside;
        this.mIcon = builder.mIcon;
        this.mDialogStatusChangedListener = builder.mDialogStatusChangedListener;
        this.mView = builder.mView;
        this.mScene = builder.mScene;
    }

    public static final class Builder {
        private boolean mCancelableOnTouchOutside;
        private Context mContext;
        private OnDialogStatusChangedListener mDialogStatusChangedListener;
        private Drawable mIcon;
        private String mMessage;
        private String mNegativeBtnText;
        private String mPositiveBtnText;
        public int mScene;
        private String mTitle;
        public View mView;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder setTitle(String str) {
            this.mTitle = str;
            return this;
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

        public Builder setIcon(Drawable drawable) {
            this.mIcon = drawable;
            return this;
        }

        public Builder setDialogStatusChangedListener(OnDialogStatusChangedListener onDialogStatusChangedListener) {
            this.mDialogStatusChangedListener = onDialogStatusChangedListener;
            return this;
        }

        public Builder setView(View view) {
            this.mView = view;
            return this;
        }

        public Builder setScene(int i) {
            this.mScene = i;
            return this;
        }

        public DownloadAlertDialogInfo build() {
            return new DownloadAlertDialogInfo(this);
        }
    }
}
