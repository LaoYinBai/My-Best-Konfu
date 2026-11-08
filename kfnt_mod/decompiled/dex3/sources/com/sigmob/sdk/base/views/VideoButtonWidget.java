package com.sigmob.sdk.base.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.volley.s;
import com.sigmob.volley.toolbox.k;
import com.tachikoma.core.utility.UriUtil;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class VideoButtonWidget extends RelativeLayout {
    private ImageView a;
    private int b;

    public VideoButtonWidget(Context context, int i) {
        super(context);
        this.b = com.sigmob.sdk.common.utils.c.b(30.0f, context);
        a(this.b / 2);
        ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.b, this.b);
        int i2 = this.b / 2;
        int color = Color.parseColor("#ffffff");
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(color);
        gradientDrawable.setCornerRadius(i2);
        gradientDrawable.setAlpha(102);
        setBackground(gradientDrawable);
        setLayoutParams(layoutParams);
    }

    private void a(int i) {
        this.a = new ImageView(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i, i);
        layoutParams.addRule(13);
        this.a.setImageBitmap(d.CLOSE.a());
        this.a.setScaleType(ImageView.ScaleType.CENTER_CROP);
        this.a.setImageAlpha(204);
        addView(this.a, layoutParams);
    }

    private void b(final String str) {
        k kVarB = com.sigmob.sdk.common.e.f.b();
        if (kVarB != null) {
            kVarB.a(str, new k.d() { // from class: com.sigmob.sdk.base.views.VideoButtonWidget.1
                @Override // com.sigmob.volley.toolbox.k.d
                public void a(k.c cVar, boolean z) {
                    Bitmap bitmapB = cVar.b();
                    if (bitmapB != null) {
                        VideoButtonWidget.this.a.setImageBitmap(bitmapB);
                    } else {
                        SigmobLog.d(String.format("%s returned null bitmap", str));
                    }
                }

                @Override // com.sigmob.volley.n.a
                public void onErrorResponse(s sVar) {
                    SigmobLog.d("Failed to load image.", sVar);
                }
            });
        }
    }

    public void a(Bitmap bitmap) {
        this.a.setImageBitmap(bitmap);
    }

    public void a(BaseAdUnit baseAdUnit) {
        ImageView imageView;
        d dVar;
        if (baseAdUnit == null || baseAdUnit.getEndcardCloseImage() != 1) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(0);
            setBackground(gradientDrawable);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.b, this.b);
            layoutParams.addRule(13);
            this.a.setLayoutParams(layoutParams);
            imageView = this.a;
            dVar = d.CLOSE_OLD;
        } else {
            imageView = this.a;
            dVar = d.CLOSE;
        }
        imageView.setImageBitmap(dVar.a());
    }

    public void a(String str) {
        String lowerCase = str.toLowerCase();
        if (lowerCase.startsWith(UriUtil.HTTP_PREFIX) || lowerCase.startsWith(UriUtil.HTTPS_PREFIX)) {
            b(str);
        } else if (lowerCase.startsWith(UriUtil.FILE_PREFIX)) {
            try {
                this.a.setImageURI(Uri.fromFile(new File(str)));
            } catch (Throwable th) {
                SigmobLog.e(th.getMessage());
            }
        }
    }
}
