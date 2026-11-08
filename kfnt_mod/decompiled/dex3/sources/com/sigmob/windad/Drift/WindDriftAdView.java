package com.sigmob.windad.Drift;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Movie;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sigmob.sdk.a.a;
import com.sigmob.sdk.a.b;
import com.sigmob.sdk.base.views.GifImageView;
import com.sigmob.sdk.common.utils.c;
import com.sigmob.sdk.common.utils.d;
import com.sigmob.sdk.common.utils.r;
import com.sigmob.windad.WindAdAdapterError;
import com.sigmob.windad.WindAdError;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class WindDriftAdView extends RelativeLayout {
    WindDriftAdListener a;
    private final Handler b;
    private b c;
    private GifImageView d;
    private String e;
    private boolean f;

    private class DriftAdListener implements a {
        private DriftAdListener() {
        }

        @Override // com.sigmob.sdk.a.a
        public void onDriftAdClose(final String str) {
            WindDriftAdView.this.b.post(new Runnable() { // from class: com.sigmob.windad.Drift.WindDriftAdView.DriftAdListener.4
                @Override // java.lang.Runnable
                public void run() {
                    if (WindDriftAdView.this.a != null) {
                        WindDriftAdView.this.a.onDriftAdClosed(str);
                    }
                }
            });
        }

        @Override // com.sigmob.sdk.a.a
        public void onDriftAdError(int i, String str, String str2) {
            if (WindDriftAdView.this.f || WindDriftAdView.this.a == null) {
                return;
            }
            WindDriftAdView.this.a.onDriftAdError(new WindAdAdapterError(i, str), str2);
        }

        @Override // com.sigmob.sdk.a.a
        public void onDriftAdPresent(final String str) {
            WindDriftAdView.this.f = true;
            WindDriftAdView.this.c.c();
            WindDriftAdView.this.b.post(new Runnable() { // from class: com.sigmob.windad.Drift.WindDriftAdView.DriftAdListener.3
                @Override // java.lang.Runnable
                public void run() {
                    if (WindDriftAdView.this.a != null) {
                        WindDriftAdView.this.a.onDriftAdExposure(str);
                    }
                }
            });
        }

        @Override // com.sigmob.sdk.a.a
        public void onDriftAdReceive(final String str) {
            Handler handler;
            Runnable runnable;
            File fileB = WindDriftAdView.this.c.b();
            if (fileB != null) {
                if (fileB.getName().substring(fileB.getName().lastIndexOf(".") + 1).equalsIgnoreCase("gif")) {
                    WindDriftAdView.this.d.setMovie(Movie.decodeByteArray(d.e(fileB.getAbsolutePath()), 0, d.e(fileB.getAbsolutePath()).length));
                } else {
                    WindDriftAdView.this.d.setImageBitmap(BitmapFactory.decodeFile(fileB.getAbsolutePath()));
                }
                WindDriftAdView.this.setVisibility(0);
                if (WindDriftAdView.this.f) {
                    return;
                }
                handler = WindDriftAdView.this.b;
                runnable = new Runnable() { // from class: com.sigmob.windad.Drift.WindDriftAdView.DriftAdListener.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (WindDriftAdView.this.a != null) {
                            WindDriftAdView.this.a.onDriftAdLoadSuccess(str);
                        }
                    }
                };
            } else {
                if (WindDriftAdView.this.f) {
                    return;
                }
                handler = WindDriftAdView.this.b;
                runnable = new Runnable() { // from class: com.sigmob.windad.Drift.WindDriftAdView.DriftAdListener.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (WindDriftAdView.this.a != null) {
                            WindDriftAdView.this.a.onDriftAdError(new WindAdAdapterError(WindAdError.ERROR_SIGMOB_FILE_DOWNLOAD.getErrorCode(), WindAdError.ERROR_SIGMOB_FILE_DOWNLOAD.getMessage()), str);
                        }
                    }
                };
            }
            handler.post(runnable);
        }
    }

    public WindDriftAdView(Context context, String str, WindDriftAdListener windDriftAdListener) {
        super(context);
        this.f = false;
        this.c = new b(context, str, new DriftAdListener());
        this.b = new Handler(Looper.getMainLooper());
        this.d = new GifImageView(context);
        this.a = windDriftAdListener;
        this.e = str;
        setVisibility(4);
        setupLayout(context);
        if (this.c != null) {
            this.c.c();
        } else if (this.a != null) {
            this.a.onDriftAdError(new WindAdAdapterError(WindAdError.ERROR_SIGMOB_REQUEST.getErrorCode(), "ad is null"), str);
        }
    }

    private void setupLayout(Context context) {
        setBackgroundColor(0);
        RelativeLayout relativeLayout = new RelativeLayout(context);
        relativeLayout.setId(com.sigmob.sdk.common.a.r());
        ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams2.setMargins(0, c.b(10.0f, context), c.b(10.0f, context), 0);
        this.d.setImageBitmap(com.sigmob.sdk.base.views.d.GRAYCLOSE.a());
        this.d.setScaleType(ImageView.ScaleType.FIT_XY);
        relativeLayout.addView(this.d, layoutParams2);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(9);
        layoutParams3.addRule(12);
        TextView textView = new TextView(context);
        textView.setId(com.sigmob.sdk.common.a.r());
        textView.setText("广告");
        textView.setTextColor(-1);
        textView.setBackgroundColor(Color.parseColor("#dadada"));
        textView.setGravity(3);
        textView.setTextSize(1, 10.0f);
        relativeLayout.addView(textView, layoutParams3);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(c.b(20.0f, context), c.b(20.0f, context));
        layoutParams4.addRule(7, relativeLayout.getId());
        layoutParams4.addRule(6, relativeLayout.getId());
        ImageView imageView = new ImageView(context);
        imageView.setImageBitmap(com.sigmob.sdk.base.views.d.GRAYCLOSE.a());
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.sigmob.windad.Drift.WindDriftAdView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                this.setVisibility(8);
                WindDriftAdView.this.c.e();
                r.a(this);
                if (WindDriftAdView.this.a != null) {
                    WindDriftAdView.this.a.onDriftAdViewClosed(WindDriftAdView.this.e);
                }
            }
        });
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sigmob.windad.Drift.WindDriftAdView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WindDriftAdView.this.c.a();
            }
        });
        addView(relativeLayout, layoutParams);
        addView(imageView, layoutParams4);
    }
}
