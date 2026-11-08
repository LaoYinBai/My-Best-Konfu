package com.tachikoma.core.component.progressbar;

import android.R;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.text.TextUtils;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.tachikoma.annotation.TK_EXPORT_CLASS;
import com.tachikoma.core.Tachikoma;
import com.tachikoma.core.component.TKBase;
import com.tachikoma.core.utility.TKColorUtil;
import com.tachikoma.core.utility.TKConverter;
import java.util.List;

@TK_EXPORT_CLASS
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class KTProgressBarView extends TKBase<ProgressBar> {
    private static final int MAX_PROGRESS = 1000;
    public boolean animating;
    String backgroundColor;
    public String color;
    float cornerRadius;
    private String mColor;
    public double progress;
    private int style;

    public KTProgressBarView(@NonNull Context context, @Nullable List<Object> list) {
        super(context, list);
        this.style = R.attr.progressBarStyle;
        this.cornerRadius = 0.0f;
        if (list != null) {
            try {
                if (list.size() > 0 && (list.get(0) instanceof String)) {
                    this.style = getStyleFromString((String) list.get(0));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        getView().setMax(1000);
    }

    private Drawable generateBackDrawable(String str) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(this.cornerRadius);
        ClipDrawable clipDrawable = new ClipDrawable(gradientDrawable, 3, 1);
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setCornerRadius(this.cornerRadius);
        if (!TextUtils.isEmpty(str)) {
            gradientDrawable2.setColor(Color.parseColor(str));
        }
        return new LayerDrawable(new Drawable[]{gradientDrawable2, clipDrawable});
    }

    static int getStyleFromString(@Nullable String str) throws Exception {
        if (str == null) {
            throw new Exception("ProgressBar needs to have a style, null received");
        }
        if (str.equals("Horizontal")) {
            return R.attr.progressBarStyleHorizontal;
        }
        if (str.equals("Small")) {
            return R.attr.progressBarStyleSmall;
        }
        if (str.equals("Large")) {
            return R.attr.progressBarStyleLarge;
        }
        if (str.equals("Inverse")) {
            return R.attr.progressBarStyleInverse;
        }
        if (str.equals("SmallInverse")) {
            return R.attr.progressBarStyleSmallInverse;
        }
        if (str.equals("LargeInverse")) {
            return R.attr.progressBarStyleLargeInverse;
        }
        if (str.equals("Normal")) {
            return R.attr.progressBarStyle;
        }
        throw new Exception("Unknown ProgressBar style: " + str);
    }

    private void setProgressColor(ProgressBar progressBar, String str) {
        Drawable indeterminateDrawable = progressBar.isIndeterminate() ? progressBar.getIndeterminateDrawable() : progressBar.getProgressDrawable();
        if (indeterminateDrawable == null) {
            indeterminateDrawable = generateBackDrawable(this.backgroundColor);
            setProgressDrawable(getView().isIndeterminate(), indeterminateDrawable);
        }
        if (indeterminateDrawable instanceof LayerDrawable) {
            Drawable drawable = ((LayerDrawable) indeterminateDrawable).getDrawable(r2.getNumberOfLayers() - 1);
            if (str == null || !str.startsWith("#")) {
                drawable.clearColorFilter();
            } else {
                drawable.setColorFilter(Color.parseColor(str), PorterDuff.Mode.SRC_IN);
            }
        }
    }

    private void setProgressDrawable(boolean z, Drawable drawable) {
        if (z) {
            getView().setIndeterminateDrawable(drawable);
        } else {
            getView().setProgressDrawable(drawable);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tachikoma.core.component.TKBase
    public ProgressBar createViewInstance(Context context) {
        return new ProgressBar(context, null, this.style);
    }

    public void setAnimating(boolean z) {
        ProgressBar view;
        int i;
        if (z) {
            view = getView();
            i = 0;
        } else {
            view = getView();
            i = 8;
        }
        view.setVisibility(i);
    }

    @Override // com.tachikoma.core.component.TKBase
    public void setBackgroundColor(Object obj) {
        if (obj instanceof String) {
            this.backgroundColor = (String) obj;
            setProgressDrawable(getView().isIndeterminate(), generateBackDrawable(TKColorUtil.rgba2argb(this.backgroundColor)));
        }
    }

    public void setColor(@Nullable String str) {
        setProgressColor(getView(), TKColorUtil.rgba2argb(str));
    }

    public void setCornerRadius(float f) {
        this.cornerRadius = TKConverter.dp2px(Tachikoma.sApplication, f);
        setProgressDrawable(getView().isIndeterminate(), generateBackDrawable(this.backgroundColor));
    }

    public void setIndeterminate(boolean z) {
        getView().setIndeterminate(z);
        setProgressDrawable(z, generateBackDrawable(TKColorUtil.rgba2argb(this.backgroundColor)));
    }

    public void setProgress(double d) {
        getView().getMax();
        getView().setProgress((int) (d * 1000.0d));
    }
}
