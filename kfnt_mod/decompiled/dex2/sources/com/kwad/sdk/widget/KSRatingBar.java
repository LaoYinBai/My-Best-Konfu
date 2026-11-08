package com.kwad.sdk.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.kwad.sdk.R;
import java.math.BigDecimal;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class KSRatingBar extends LinearLayout {
    private boolean a;
    private boolean b;
    private int c;
    private int d;
    private a e;
    private float f;
    private float g;
    private float h;
    private Drawable i;
    private Drawable j;
    private Drawable k;
    private int l;
    private boolean m;

    public interface a {
        void a(float f);
    }

    public KSRatingBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.l = 1;
        this.m = false;
        setOrientation(0);
        setDividerDrawable(getResources().getDrawable(R.drawable.ksad_reward_apk_stars_divider));
        setShowDividers(2);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ksad_KSRatingBar);
        this.k = typedArrayObtainStyledAttributes.getDrawable(R.styleable.ksad_KSRatingBar_ksad_starHalf);
        this.i = typedArrayObtainStyledAttributes.getDrawable(R.styleable.ksad_KSRatingBar_ksad_starEmpty);
        this.j = typedArrayObtainStyledAttributes.getDrawable(R.styleable.ksad_KSRatingBar_ksad_starFill);
        this.f = typedArrayObtainStyledAttributes.getDimension(R.styleable.ksad_KSRatingBar_ksad_starImageWidth, 60.0f);
        this.g = typedArrayObtainStyledAttributes.getDimension(R.styleable.ksad_KSRatingBar_ksad_starImageHeight, 120.0f);
        this.h = typedArrayObtainStyledAttributes.getDimension(R.styleable.ksad_KSRatingBar_ksad_starImagePadding, 15.0f);
        this.c = typedArrayObtainStyledAttributes.getInteger(R.styleable.ksad_KSRatingBar_ksad_totalStarCount, 5);
        this.d = typedArrayObtainStyledAttributes.getInteger(R.styleable.ksad_KSRatingBar_ksad_starCount, 5);
        this.a = typedArrayObtainStyledAttributes.getBoolean(R.styleable.ksad_KSRatingBar_ksad_clickable, true);
        this.b = typedArrayObtainStyledAttributes.getBoolean(R.styleable.ksad_KSRatingBar_ksad_halfstart, false);
        for (int i = 0; i < this.c; i++) {
            ImageView imageViewA = a(context, this.m);
            imageViewA.setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.widget.KSRatingBar.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    KSRatingBar kSRatingBar;
                    float fIndexOfChild;
                    a aVar;
                    float fIndexOfChild2;
                    if (KSRatingBar.this.a) {
                        if (!KSRatingBar.this.b) {
                            KSRatingBar.this.setStar(KSRatingBar.this.indexOfChild(view) + 1.0f);
                            if (KSRatingBar.this.e != null) {
                                KSRatingBar.this.e.a(KSRatingBar.this.indexOfChild(view) + 1.0f);
                                return;
                            }
                            return;
                        }
                        if (KSRatingBar.this.l % 2 == 0) {
                            kSRatingBar = KSRatingBar.this;
                            fIndexOfChild = KSRatingBar.this.indexOfChild(view) + 1.0f;
                        } else {
                            kSRatingBar = KSRatingBar.this;
                            fIndexOfChild = KSRatingBar.this.indexOfChild(view) + 0.5f;
                        }
                        kSRatingBar.setStar(fIndexOfChild);
                        if (KSRatingBar.this.e != null) {
                            if (KSRatingBar.this.l % 2 == 0) {
                                aVar = KSRatingBar.this.e;
                                fIndexOfChild2 = KSRatingBar.this.indexOfChild(view) + 1.0f;
                            } else {
                                aVar = KSRatingBar.this.e;
                                fIndexOfChild2 = KSRatingBar.this.indexOfChild(view) + 0.5f;
                            }
                            aVar.a(fIndexOfChild2);
                            KSRatingBar.e(KSRatingBar.this);
                        }
                    }
                }
            });
            addView(imageViewA);
        }
        setStar(this.d);
    }

    private ImageView a(Context context, boolean z) {
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(Math.round(this.f), Math.round(this.g)));
        imageView.setPadding(0, 0, Math.round(this.h), 0);
        imageView.setImageDrawable(z ? this.i : this.j);
        return imageView;
    }

    static /* synthetic */ int e(KSRatingBar kSRatingBar) {
        int i = kSRatingBar.l;
        kSRatingBar.l = i + 1;
        return i;
    }

    public void setImagePadding(float f) {
        this.h = f;
    }

    public void setOnRatingChangeListener(a aVar) {
        this.e = aVar;
    }

    public void setStar(float f) {
        int i = (int) f;
        float fFloatValue = new BigDecimal(Float.toString(f)).subtract(new BigDecimal(Integer.toString(i))).floatValue();
        float f2 = i > this.c ? this.c : i;
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        for (int i2 = 0; i2 < f2; i2++) {
            ((ImageView) getChildAt(i2)).setImageDrawable(this.j);
        }
        if (fFloatValue > 0.0f) {
            ((ImageView) getChildAt(i)).setImageDrawable(this.k);
            int i3 = this.c;
            while (true) {
                i3--;
                if (i3 < 1.0f + f2) {
                    return;
                } else {
                    ((ImageView) getChildAt(i3)).setImageDrawable(this.i);
                }
            }
        } else {
            int i4 = this.c;
            while (true) {
                i4--;
                if (i4 < f2) {
                    return;
                } else {
                    ((ImageView) getChildAt(i4)).setImageDrawable(this.i);
                }
            }
        }
    }

    public void setStarEmptyDrawable(Drawable drawable) {
        this.i = drawable;
    }

    public void setStarFillDrawable(Drawable drawable) {
        this.j = drawable;
    }

    public void setStarHalfDrawable(Drawable drawable) {
        this.k = drawable;
    }

    public void setStarImageHeight(float f) {
        this.g = f;
    }

    public void setStarImageWidth(float f) {
        this.f = f;
    }

    public void setTotalStarCount(int i) {
        this.c = i;
    }

    public void setmClickable(boolean z) {
        this.a = z;
    }
}
