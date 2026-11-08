package com.ss.union.game.sdk.common.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import androidx.annotation.Keep;
import com.ss.union.game.sdk.common.ui.roundedimageview.RoundedImageView;
import com.ss.union.game.sdk.common.util.UIUtils;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class BorderImageView extends RoundedImageView {
    public BorderImageView(Context context) {
        this(context, null);
    }

    public BorderImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BorderImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.ss.union.game.sdk.common.ui.roundedimageview.RoundedImageView, android.view.View
    public void setBackground(Drawable drawable) {
        hideBorder();
        super.setBackground(drawable);
    }

    @Override // com.ss.union.game.sdk.common.ui.roundedimageview.RoundedImageView, android.view.View
    public void setBackgroundResource(int i) {
        hideBorder();
        super.setBackgroundResource(i);
    }

    @Override // com.ss.union.game.sdk.common.ui.roundedimageview.RoundedImageView, android.view.View
    public void setBackgroundColor(int i) {
        hideBorder();
        super.setBackgroundColor(i);
    }

    @Override // com.ss.union.game.sdk.common.ui.roundedimageview.RoundedImageView, android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        hideBorder();
        super.setBackgroundDrawable(drawable);
    }

    @Override // com.ss.union.game.sdk.common.ui.roundedimageview.RoundedImageView, android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        showBorder();
        super.setImageBitmap(bitmap);
    }

    @Override // com.ss.union.game.sdk.common.ui.roundedimageview.RoundedImageView, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        showBorder();
        super.setImageDrawable(drawable);
    }

    @Override // com.ss.union.game.sdk.common.ui.roundedimageview.RoundedImageView, android.widget.ImageView
    public void setImageResource(int i) {
        showBorder();
        super.setImageResource(i);
    }

    @Override // com.ss.union.game.sdk.common.ui.roundedimageview.RoundedImageView, android.widget.ImageView
    public void setImageURI(Uri uri) {
        showBorder();
        super.setImageURI(uri);
    }

    public void showBorder() {
        super.setBackgroundDrawable(null);
        setBorderColor(Color.parseColor("#D4D4D4"));
        setBorderWidth(UIUtils.dip2Px(0.5f));
        setCornerRadius(UIUtils.dip2Px(6.0f));
        mutateBackground(true);
    }

    public void hideBorder() {
        setBorderWidth(0.0f);
        setCornerRadius(0.0f);
    }
}
