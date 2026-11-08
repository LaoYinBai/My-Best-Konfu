package com.ss.union.game.sdk.core.base.debug.automatic_detection.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import com.ss.union.game.sdk.common.util.AntiShakeClickListener;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LGDarkCommonRetryLayout extends BaseCommonLayout {
    private ImageView a;

    @Override // com.ss.union.game.sdk.core.base.debug.automatic_detection.ui.BaseCommonLayout
    public String getLayoutName() {
        return "lg_dark_common_retry_layout";
    }

    public LGDarkCommonRetryLayout(Context context) {
        this(context, null);
    }

    public LGDarkCommonRetryLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LGDarkCommonRetryLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.ss.union.game.sdk.core.base.debug.automatic_detection.ui.BaseCommonLayout
    public void a() {
        super.a();
        this.a = (ImageView) a("lg_common_retry_btn");
    }

    public void setRetryBtnListener(View.OnClickListener onClickListener) {
        this.a.setOnClickListener(new AntiShakeClickListener(onClickListener));
    }
}
