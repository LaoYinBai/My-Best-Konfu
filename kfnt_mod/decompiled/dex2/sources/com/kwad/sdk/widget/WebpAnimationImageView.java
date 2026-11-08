package com.kwad.sdk.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.kwad.sdk.core.page.widget.RoundAngleImageView;
import com.kwad.sdk.glide.framesequence.FrameSequence;
import com.kwad.sdk.glide.framesequence.FrameSequenceDrawable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class WebpAnimationImageView extends RoundAngleImageView {
    private int a;
    private FrameSequenceDrawable b;
    private a c;
    private FrameSequenceDrawable.b d;

    public interface a {
        void a();
    }

    public WebpAnimationImageView(Context context) {
        super(context);
        this.a = 1;
    }

    public WebpAnimationImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = 1;
        a(context, attributeSet);
    }

    public WebpAnimationImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = 1;
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        this.d = new FrameSequenceDrawable.b() { // from class: com.kwad.sdk.widget.WebpAnimationImageView.1
            @Override // com.kwad.sdk.glide.framesequence.FrameSequenceDrawable.b
            public void a(FrameSequenceDrawable frameSequenceDrawable) {
                if (WebpAnimationImageView.this.c != null) {
                    WebpAnimationImageView.this.c.a();
                }
            }
        };
        com.kwad.sdk.glide.kwai.a.a(context);
    }

    public void a() {
        if (this.b != null) {
            this.b.start();
        }
    }

    public void b() {
        if (this.b != null) {
            this.b.stop();
        }
    }

    public void setOnFinishedListener(a aVar) {
        this.c = aVar;
    }

    public void setWebpStream(FrameSequence frameSequence) {
        if (frameSequence == null) {
            return;
        }
        try {
            FrameSequenceDrawable frameSequenceDrawable = new FrameSequenceDrawable(frameSequence);
            frameSequenceDrawable.setLoopCount(this.a);
            frameSequenceDrawable.setOnFinishedListener(this.d);
            setImageDrawable(frameSequenceDrawable);
            if (this.b != null) {
                this.b.destroy();
            }
            this.b = frameSequenceDrawable;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
