package com.ss.union.game.sdk.core.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.common.util.ToastUtils;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NoNetWorkCard extends CardView {
    private boolean isFirstToast;
    private View mBtnRetry;
    private boolean showToast;

    @Override // com.ss.union.game.sdk.core.base.view.CardView
    public String getLayoutName() {
        return "lg_common_no_network";
    }

    public NoNetWorkCard(@NonNull Context context) {
        this(context, null);
    }

    public NoNetWorkCard(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NoNetWorkCard(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.showToast = true;
        this.isFirstToast = true;
    }

    @Override // com.ss.union.game.sdk.core.base.view.CardView
    public void initView() {
        super.initView();
        this.mBtnRetry = findViewById(ResourceUtils.getIdByName("lg_tv_retry"));
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        if (i == 0 && this.showToast) {
            if (!this.isFirstToast) {
                toastNetworkError();
            }
            this.isFirstToast = false;
        }
    }

    private void toastNetworkError() {
        ToastUtils.getInstance().toast(ResourceUtils.getString("lg_common_toast_network_error"));
    }

    public void listenRetryClick(View.OnClickListener onClickListener) {
        this.mBtnRetry.setOnClickListener(antiShake(onClickListener));
    }
}
