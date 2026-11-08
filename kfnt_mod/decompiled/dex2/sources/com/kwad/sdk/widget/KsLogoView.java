package com.kwad.sdk.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;
import com.kwad.sdk.core.imageloader.core.assist.FailReason;
import com.kwad.sdk.core.imageloader.core.decode.DecodedResult;
import com.kwad.sdk.core.imageloader.core.listener.SimpleImageLoadingListener;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class KsLogoView extends LinearLayout {
    TextView a;
    ImageView b;
    private boolean c;
    private SimpleImageLoadingListener d;
    private a e;

    public interface a {
        void a();
    }

    public KsLogoView(Context context) {
        super(context);
        this.d = new SimpleImageLoadingListener() { // from class: com.kwad.sdk.widget.KsLogoView.1
            @Override // com.kwad.sdk.core.imageloader.core.listener.SimpleImageLoadingListener, com.kwad.sdk.core.imageloader.core.listener.ImageLoadingListener
            public void onLoadingComplete(String str, View view, DecodedResult decodedResult) {
                if (KsLogoView.this.e != null) {
                    KsLogoView.this.e.a();
                }
            }

            @Override // com.kwad.sdk.core.imageloader.core.listener.SimpleImageLoadingListener, com.kwad.sdk.core.imageloader.core.listener.ImageLoadingListener
            public void onLoadingFailed(String str, View view, FailReason failReason) {
                KsLogoView.this.a();
                if (KsLogoView.this.e != null) {
                    KsLogoView.this.e.a();
                }
            }
        };
        a(context);
    }

    public KsLogoView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = new SimpleImageLoadingListener() { // from class: com.kwad.sdk.widget.KsLogoView.1
            @Override // com.kwad.sdk.core.imageloader.core.listener.SimpleImageLoadingListener, com.kwad.sdk.core.imageloader.core.listener.ImageLoadingListener
            public void onLoadingComplete(String str, View view, DecodedResult decodedResult) {
                if (KsLogoView.this.e != null) {
                    KsLogoView.this.e.a();
                }
            }

            @Override // com.kwad.sdk.core.imageloader.core.listener.SimpleImageLoadingListener, com.kwad.sdk.core.imageloader.core.listener.ImageLoadingListener
            public void onLoadingFailed(String str, View view, FailReason failReason) {
                KsLogoView.this.a();
                if (KsLogoView.this.e != null) {
                    KsLogoView.this.e.a();
                }
            }
        };
        a(context);
    }

    public KsLogoView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = new SimpleImageLoadingListener() { // from class: com.kwad.sdk.widget.KsLogoView.1
            @Override // com.kwad.sdk.core.imageloader.core.listener.SimpleImageLoadingListener, com.kwad.sdk.core.imageloader.core.listener.ImageLoadingListener
            public void onLoadingComplete(String str, View view, DecodedResult decodedResult) {
                if (KsLogoView.this.e != null) {
                    KsLogoView.this.e.a();
                }
            }

            @Override // com.kwad.sdk.core.imageloader.core.listener.SimpleImageLoadingListener, com.kwad.sdk.core.imageloader.core.listener.ImageLoadingListener
            public void onLoadingFailed(String str, View view, FailReason failReason) {
                KsLogoView.this.a();
                if (KsLogoView.this.e != null) {
                    KsLogoView.this.e.a();
                }
            }
        };
        a(context);
    }

    public KsLogoView(Context context, boolean z) {
        super(context);
        this.d = new SimpleImageLoadingListener() { // from class: com.kwad.sdk.widget.KsLogoView.1
            @Override // com.kwad.sdk.core.imageloader.core.listener.SimpleImageLoadingListener, com.kwad.sdk.core.imageloader.core.listener.ImageLoadingListener
            public void onLoadingComplete(String str, View view, DecodedResult decodedResult) {
                if (KsLogoView.this.e != null) {
                    KsLogoView.this.e.a();
                }
            }

            @Override // com.kwad.sdk.core.imageloader.core.listener.SimpleImageLoadingListener, com.kwad.sdk.core.imageloader.core.listener.ImageLoadingListener
            public void onLoadingFailed(String str, View view, FailReason failReason) {
                KsLogoView.this.a();
                if (KsLogoView.this.e != null) {
                    KsLogoView.this.e.a();
                }
            }
        };
        if (z) {
            setBackgroundResource(R.drawable.ksad_splash_logo_bg);
        }
        a(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        this.b.setImageDrawable(getContext().getResources().getDrawable(this.c ? R.drawable.ksad_logo_gray : R.drawable.ksad_logo_white));
    }

    private void a(Context context) {
        TextView textView;
        int i;
        inflate(context, R.layout.ksad_logo_layout, this);
        this.a = (TextView) findViewById(R.id.ksad_logo_text);
        this.b = (ImageView) findViewById(R.id.ksad_logo_icon);
        this.c = getBackground() == null;
        if (this.c) {
            this.b.setImageDrawable(context.getResources().getDrawable(R.drawable.ksad_logo_gray));
            textView = this.a;
            i = -6513508;
        } else {
            this.b.setImageDrawable(context.getResources().getDrawable(R.drawable.ksad_logo_white));
            textView = this.a;
            i = -1711276033;
        }
        textView.setTextColor(i);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.kwad.sdk.core.response.model.AdTemplate r7) {
        /*
            r6 = this;
            int r0 = com.kwad.sdk.R.id.ksad_logo_container
            android.view.View r0 = r6.findViewById(r0)
            com.kwad.sdk.core.response.model.AdInfo r1 = com.kwad.sdk.core.response.a.d.j(r7)
            boolean r2 = r6.c
            if (r2 == 0) goto L13
            com.kwad.sdk.core.response.model.AdInfo$AdBaseInfo r2 = r1.adBaseInfo
            java.lang.String r2 = r2.adGrayMarkIcon
            goto L17
        L13:
            com.kwad.sdk.core.response.model.AdInfo$AdBaseInfo r2 = r1.adBaseInfo
            java.lang.String r2 = r2.adMarkIcon
        L17:
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            r4 = 0
            if (r3 == 0) goto L48
            com.kwad.sdk.core.response.model.AdInfo$AdBaseInfo r3 = r1.adBaseInfo
            java.lang.String r3 = r3.adSourceDescription
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L48
            android.widget.TextView r7 = r6.a
            r7.setVisibility(r4)
            android.widget.TextView r7 = r6.a
            java.lang.String r1 = com.kwad.sdk.core.response.a.a.z(r1)
            r7.setText(r1)
            android.widget.ImageView r7 = r6.b
            r7.setVisibility(r4)
            r6.a()
            com.kwad.sdk.widget.KsLogoView$a r7 = r6.e
            if (r7 == 0) goto L92
        L42:
            com.kwad.sdk.widget.KsLogoView$a r7 = r6.e
            r7.a()
            goto L92
        L48:
            com.kwad.sdk.core.response.model.AdInfo$AdBaseInfo r3 = r1.adBaseInfo
            java.lang.String r3 = r3.adSourceDescription
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            r5 = 8
            if (r3 != 0) goto L63
            android.widget.TextView r3 = r6.a
            java.lang.String r1 = com.kwad.sdk.core.response.a.a.z(r1)
            r3.setText(r1)
            android.widget.TextView r1 = r6.a
            r1.setVisibility(r4)
            goto L6f
        L63:
            android.widget.TextView r1 = r6.a
            r1.setVisibility(r5)
            android.widget.TextView r1 = r6.a
            java.lang.String r3 = ""
            r1.setText(r3)
        L6f:
            boolean r1 = android.text.TextUtils.isEmpty(r2)
            if (r1 != 0) goto L82
            android.widget.ImageView r1 = r6.b
            com.kwad.sdk.core.imageloader.core.listener.SimpleImageLoadingListener r3 = r6.d
            com.kwad.sdk.core.imageloader.KSImageLoader.loadFeeImage(r1, r2, r7, r3)
            android.widget.ImageView r7 = r6.b
            r7.setVisibility(r4)
            goto L92
        L82:
            android.widget.ImageView r7 = r6.b
            r7.setVisibility(r5)
            android.widget.ImageView r7 = r6.b
            r1 = 0
            r7.setImageDrawable(r1)
            com.kwad.sdk.widget.KsLogoView$a r7 = r6.e
            if (r7 == 0) goto L92
            goto L42
        L92:
            r0.setVisibility(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.widget.KsLogoView.a(com.kwad.sdk.core.response.model.AdTemplate):void");
    }

    public ImageView getIcon() {
        return this.b;
    }

    public TextView getTextView() {
        return this.a;
    }

    public void setLogoLoadFinishListener(a aVar) {
        this.e = aVar;
    }
}
