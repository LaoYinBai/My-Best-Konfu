package com.ss.union.game.sdk.core.vapp.buoy;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.ss.union.game.sdk.common.ui.floatview.BaseFloatView;
import com.ss.union.game.sdk.common.ui.floatview.FloatIntent;
import com.ss.union.game.sdk.common.ui.floatview.IFloatViewState;
import com.ss.union.game.sdk.common.util.GlobalApplicationUtils;
import com.ss.union.game.sdk.common.util.ResourceUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class f extends BaseFloatView {
    private View a;
    private ImageView b;

    @Override // com.ss.union.game.sdk.common.ui.floatview.BaseFloatView
    protected IFloatViewState createFloatViewStateManager() {
        return null;
    }

    public static GradientDrawable a(int i, float f) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(i);
        gradientDrawable.setCornerRadius(f);
        return gradientDrawable;
    }

    @Override // com.ss.union.game.sdk.common.ui.floatview.BaseFloatView
    protected void attachIntent(FloatIntent floatIntent) {
        super.attachIntent(floatIntent);
        setSlideEnabled(false);
    }

    @Override // com.ss.union.game.sdk.common.ui.floatview.BaseFloatView
    protected void setContent() {
        View viewInflate = LayoutInflater.from(GlobalApplicationUtils.getContext()).inflate(ResourceUtils.getLayoutIdByName("lg_v_floatball_hide_tips"), (ViewGroup) this.mRoot, false);
        this.a = viewInflate;
        this.b = (ImageView) viewInflate.findViewById(ResourceUtils.getIdByName("lg_float_ball_delete_iv"));
        this.mRoot.addView(viewInflate);
        b();
    }

    @Override // com.ss.union.game.sdk.common.ui.floatview.BaseFloatView
    protected void onWindowLayoutParamsCreated() {
        super.onWindowLayoutParamsCreated();
        this.wlp.gravity = 81;
        this.wlp.x = 0;
        this.wlp.y = 0;
    }

    protected void a() {
        try {
            this.a.setBackground(a(Color.parseColor("#CCEF2F39"), dp2px(8)));
        } catch (Exception unused) {
        }
        int drawableIdByName = ResourceUtils.getDrawableIdByName("lg_v_float_ball_delete_open");
        if (drawableIdByName == 0) {
            return;
        }
        this.b.setImageResource(drawableIdByName);
    }

    protected void b() {
        try {
            this.a.setBackground(a(Color.parseColor("#99000000"), dp2px(8)));
        } catch (Exception unused) {
        }
        int drawableIdByName = ResourceUtils.getDrawableIdByName("lg_v_float_ball_delete_close");
        if (drawableIdByName == 0) {
            return;
        }
        this.b.setImageResource(drawableIdByName);
    }
}
