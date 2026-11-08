package com.ss.union.game.sdk.core.base.debug.automatic_detection.ui;

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
public abstract class BaseCommonLayout extends FrameLayout {
    public void a() {
    }

    public void a(int i, int i2, Intent intent) {
    }

    public void b() {
    }

    public void c() {
    }

    public void d() {
    }

    public void e() {
    }

    public void f() {
    }

    public void g() {
    }

    public abstract String getLayoutName();

    public void h() {
    }

    public BaseCommonLayout(Context context) {
        this(context, null);
    }

    public BaseCommonLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BaseCommonLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        LayoutInflater.from(context).inflate(ResourceUtils.getLayoutIdByName(getLayoutName()), (ViewGroup) this, true);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        b();
        a();
    }

    protected View.OnClickListener a(View.OnClickListener onClickListener) {
        return new AntiShakeClickListener(onClickListener);
    }

    protected <T extends View> T a(String str) {
        return (T) findViewById(ResourceUtils.getIdByName(str));
    }
}
