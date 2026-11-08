package com.kwad.sdk.reward.presenter.a.a;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.kwad.sdk.R;
import com.kwad.sdk.core.download.a.a;
import com.kwad.sdk.core.imageloader.KSImageLoader;
import com.kwad.sdk.core.imageloader.core.decode.DecodedResult;
import com.kwad.sdk.core.imageloader.core.listener.SimpleImageLoadingListener;
import com.kwad.sdk.core.imageloader.utils.IoUtils;
import com.kwad.sdk.glide.framesequence.FrameSequence;
import com.kwad.sdk.reward.a.f;
import com.kwad.sdk.reward.a.h;
import com.kwad.sdk.reward.d;
import com.kwad.sdk.reward.g;
import com.kwad.sdk.utils.at;
import com.kwad.sdk.widget.WebpAnimationImageView;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c extends g implements View.OnClickListener {
    private ViewGroup b;
    private ImageView c;
    private WebpAnimationImageView d;
    private WebpAnimationImageView e;
    private final f f = new f() { // from class: com.kwad.sdk.reward.presenter.a.a.c.1
        @Override // com.kwad.sdk.reward.a.f
        public void a() {
            if (c.this.a.x || c.this.f() || c.this.g() || !c.this.a.v) {
                return;
            }
            c.this.e();
        }
    };
    private final h g = new h() { // from class: com.kwad.sdk.reward.presenter.a.a.c.2
        @Override // com.kwad.sdk.reward.a.h
        public void a() {
            c.this.e();
        }
    };

    private void a(final WebpAnimationImageView webpAnimationImageView, final String str) {
        if (at.a(str) || !FrameSequence.isEnable()) {
            webpAnimationImageView.setImageResource(R.drawable.ksad_reward_icon_end);
        } else {
            if (str.equals(webpAnimationImageView.getTag())) {
                return;
            }
            KSImageLoader.loadImage(str, this.a.g, KSImageLoader.IMGOPTION_ENTRY, new SimpleImageLoadingListener() { // from class: com.kwad.sdk.reward.presenter.a.a.c.3
                @Override // com.kwad.sdk.core.imageloader.core.listener.SimpleImageLoadingListener, com.kwad.sdk.core.imageloader.core.listener.ImageLoadingListener
                public boolean onDecode(String str2, InputStream inputStream, DecodedResult decodedResult) {
                    try {
                        FrameSequence frameSequenceDecodeStream = FrameSequence.decodeStream(inputStream);
                        IoUtils.closeSilently(inputStream);
                        decodedResult.mFrameSequence = frameSequenceDecodeStream;
                        return frameSequenceDecodeStream != null;
                    } catch (Throwable th) {
                        com.kwad.sdk.core.d.a.a(th);
                        return false;
                    }
                }

                @Override // com.kwad.sdk.core.imageloader.core.listener.SimpleImageLoadingListener, com.kwad.sdk.core.imageloader.core.listener.ImageLoadingListener
                public void onLoadingComplete(String str2, View view, DecodedResult decodedResult) {
                    if (str.equals(str2)) {
                        if (decodedResult.mFrameSequence != null) {
                            webpAnimationImageView.setWebpStream(decodedResult.mFrameSequence);
                            webpAnimationImageView.setTag(str2);
                            webpAnimationImageView.a();
                        } else {
                            if (decodedResult.mBitmap == null || decodedResult.mBitmap.isRecycled()) {
                                return;
                            }
                            webpAnimationImageView.setImageBitmap(decodedResult.mBitmap);
                        }
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.a.s) {
            if (this.c != null) {
                this.c.setVisibility(8);
            }
            String strC = com.kwad.sdk.core.response.a.c.c(this.a.g);
            if (this.a.f != 1) {
                if (this.e != null) {
                    a(this.e, strC);
                    this.e.setVisibility(0);
                    this.e.setOnClickListener(this);
                }
                if (this.b != null) {
                    this.b.setVisibility(8);
                    return;
                }
                return;
            }
            if (this.d != null) {
                a(this.d, strC);
                this.d.setVisibility(0);
                this.d.setOnClickListener(this);
            }
            if (this.b == null) {
                return;
            }
        } else {
            if (this.d != null) {
                this.d.setVisibility(8);
            }
            if (this.e != null) {
                this.e.setVisibility(8);
            }
            if (this.c != null) {
                this.c.setVisibility(0);
                this.c.setOnClickListener(this);
            }
            if (this.b == null) {
                return;
            }
        }
        this.b.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        com.kwad.sdk.core.report.a.a(this.a.g, 41, this.a.j.getTouchCoords(), this.a.e);
        this.a.b.a();
    }

    @Override // com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        d.a().a(this.g);
        this.a.a(this.f);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        d.a().b(this.g);
        this.a.b(this.f);
        this.d.b();
        this.e.b();
        this.b.setVisibility(8);
        this.c.setVisibility(8);
        this.d.setVisibility(8);
        this.e.setVisibility(8);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.b = (ViewGroup) a(R.id.ksad_end_reward_icon_layout);
        this.c = (ImageView) a(R.id.ksad_end_reward_icon);
        this.d = (WebpAnimationImageView) a(R.id.ksad_end_reward_icon_new_left);
        this.e = (WebpAnimationImageView) a(R.id.ksad_end_reward_icon_new_right);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.c || view == this.d || view == this.e) {
            com.kwad.sdk.core.download.a.a.a(new a.C0035a(view.getContext()).a(this.a.g).a(this.a.l).a(false).a(2).a(new a.b() { // from class: com.kwad.sdk.reward.presenter.a.a.c.4
                @Override // com.kwad.sdk.core.download.a.a.b
                public void a() {
                    c.this.i();
                }
            }));
        }
    }
}
