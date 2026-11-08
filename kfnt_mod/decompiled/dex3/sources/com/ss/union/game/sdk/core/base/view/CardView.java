package com.ss.union.game.sdk.core.base.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.ss.union.game.sdk.common.util.AntiShakeClickListener;
import com.ss.union.game.sdk.common.util.ResourceUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class CardView extends FrameLayout {
    public abstract String getLayoutName();

    public void initData() {
    }

    public void initView() {
    }

    public void onActivityResult(int i, int i2, Intent intent) {
    }

    public void onDestroy() {
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void onStart() {
    }

    public void onStop() {
    }

    public void reloadData() {
    }

    public CardView(Context context) {
        this(context, null);
    }

    public CardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        LayoutInflater.from(context).inflate(ResourceUtils.getLayoutIdByName(getLayoutName()), (ViewGroup) this, true);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        initData();
        initView();
    }

    protected View.OnClickListener antiShake(View.OnClickListener onClickListener) {
        return new AntiShakeClickListener(onClickListener);
    }
}
