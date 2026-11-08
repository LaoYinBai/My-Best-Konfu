package com.ss.union.game.sdk.core.base.debug.automatic_detection.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.ss.union.game.sdk.common.util.AntiShakeClickListener;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LGDarkCommonNetWorkLayout extends BaseCommonLayout {
    private TextView a;

    @Override // com.ss.union.game.sdk.core.base.debug.automatic_detection.ui.BaseCommonLayout
    public String getLayoutName() {
        return "lg_dark_common_no_network";
    }

    public LGDarkCommonNetWorkLayout(Context context) {
        this(context, null);
    }

    public LGDarkCommonNetWorkLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LGDarkCommonNetWorkLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.ss.union.game.sdk.core.base.debug.automatic_detection.ui.BaseCommonLayout
    public void a() {
        super.a();
        this.a = (TextView) a("lg_dark_common_no_network_retry");
    }

    public void b(View.OnClickListener onClickListener) {
        this.a.setOnClickListener(new AntiShakeClickListener(onClickListener));
    }
}
