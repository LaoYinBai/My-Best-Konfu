package com.kwad.sdk.reward.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class AppScoreView extends LinearLayout {
    private ImageView a;
    private ImageView b;

    public AppScoreView(Context context) {
        this(context, null);
    }

    public AppScoreView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AppScoreView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        inflate(getContext(), R.layout.ksad_app_score, this);
        this.a = (ImageView) findViewById(R.id.ksad_score_fourth);
        this.b = (ImageView) findViewById(R.id.ksad_score_fifth);
    }

    public void setScore(float f) {
        ImageView imageView;
        int i;
        ImageView imageView2;
        int i2;
        double d = f;
        if (d > 4.5d) {
            this.a.setImageResource(R.drawable.ksad_app_score_yellow);
            imageView2 = this.b;
            i2 = R.drawable.ksad_app_score_yellow;
        } else if (d > 4.0d) {
            this.a.setImageResource(R.drawable.ksad_app_score_yellow);
            imageView2 = this.b;
            i2 = R.drawable.ksad_app_score_half;
        } else {
            if (d > 3.5d) {
                imageView = this.a;
                i = R.drawable.ksad_app_score_yellow;
            } else if (d > 3.0d) {
                imageView = this.a;
                i = R.drawable.ksad_app_score_half;
            } else {
                if (d != 3.0d) {
                    return;
                }
                imageView = this.a;
                i = R.drawable.ksad_app_score_gray;
            }
            imageView.setImageResource(i);
            imageView2 = this.b;
            i2 = R.drawable.ksad_app_score_gray;
        }
        imageView2.setImageResource(i2);
    }
}
