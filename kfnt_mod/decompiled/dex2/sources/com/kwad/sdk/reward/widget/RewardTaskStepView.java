package com.kwad.sdk.reward.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.kwad.sdk.R;
import com.kwad.sdk.reward.b.b;
import com.kwad.sdk.widget.DividerView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class RewardTaskStepView extends LinearLayout {
    private List<b> a;
    private int b;
    private int c;

    public RewardTaskStepView(Context context) {
        super(context);
        this.a = new ArrayList();
        a(context, (AttributeSet) null, 0);
    }

    public RewardTaskStepView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new ArrayList();
        a(context, attributeSet, 0);
    }

    public RewardTaskStepView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new ArrayList();
        a(context, attributeSet, i);
    }

    @RequiresApi(api = 21)
    public RewardTaskStepView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.a = new ArrayList();
        a(context, attributeSet, i);
    }

    private void a() throws Resources.NotFoundException {
        int size = this.a.size();
        int i = 0;
        while (i < size) {
            b bVar = this.a.get(i);
            int i2 = i + 1;
            a(i2, bVar.c(), bVar.d());
            if (i < size - 1) {
                a(this.a.get(i2).d());
            }
            i = i2;
        }
    }

    private void a(int i, String str, boolean z) {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(getContext()).inflate(z ? R.layout.ksad_reward_task_step_item_checked : R.layout.ksad_reward_task_step_item_unchecked, (ViewGroup) this, false);
        if (z) {
            a(viewGroup, str);
        } else {
            a(viewGroup, i, str);
        }
        addView(viewGroup);
    }

    private void a(Context context, @Nullable AttributeSet attributeSet, int i) {
        setOrientation(1);
        this.b = context.getResources().getDimensionPixelSize(R.dimen.ksad_reward_apk_info_card_step_icon_size);
        this.c = context.getResources().getDimensionPixelSize(R.dimen.ksad_reward_apk_info_card_step_icon_text_size);
    }

    private void a(ViewGroup viewGroup, int i, String str) {
        ((TextView) viewGroup.findViewById(R.id.ksad_reward_task_step_item_text)).setText(str);
        ((TextView) viewGroup.findViewById(R.id.ksad_reward_task_step_item_icon_text)).setText(String.valueOf(i));
    }

    private void a(ViewGroup viewGroup, String str) {
        ((TextView) viewGroup.findViewById(R.id.ksad_reward_task_step_item_text)).setText(str);
    }

    private void a(boolean z) throws Resources.NotFoundException {
        DividerView dividerView = (DividerView) LayoutInflater.from(getContext()).inflate(R.layout.ksad_reward_task_dialog_dash, (ViewGroup) this, false);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.ksad_reward_apk_info_card_step_icon_size);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.ksad_reward_apk_info_card_step_divider_height);
        dividerView.setDividerColor(getResources().getColor(z ? R.color.ksad_reward_main_color : R.color.ksad_reward_undone_color));
        addView(dividerView, dimensionPixelSize, dimensionPixelSize2);
    }

    private int getStepIconSize() {
        return getContext().getResources().getDimensionPixelSize(R.dimen.ksad_reward_apk_info_card_step_icon_size);
    }

    public void a(List<b> list) throws Resources.NotFoundException {
        if (list == null || list.isEmpty()) {
            return;
        }
        this.a.clear();
        this.a.addAll(list);
        a();
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
