package com.kwad.sdk.reward.presenter.platdetail.kwai;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.kwad.sdk.R;
import com.kwad.sdk.core.download.a.a;
import com.kwad.sdk.core.imageloader.KSImageLoader;
import com.kwad.sdk.core.imageloader.core.decode.DecodedResult;
import com.kwad.sdk.core.imageloader.core.listener.SimpleImageLoadingListener;
import com.kwad.sdk.core.imageloader.utils.IoUtils;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.glide.framesequence.FrameSequence;
import com.kwad.sdk.reward.g;
import com.kwad.sdk.utils.at;
import com.kwad.sdk.widget.WebpAnimationImageView;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e extends g implements View.OnClickListener {
    private ImageView b;
    private TextView c;
    private ImageView d;
    private ViewGroup e;
    private WebpAnimationImageView f;
    private TextView g;
    private TextView h;
    private AdTemplate i;
    private com.kwad.sdk.core.download.a.b j;
    private String k;
    private com.kwad.sdk.contentalliance.detail.video.d l = new com.kwad.sdk.contentalliance.detail.video.e() { // from class: com.kwad.sdk.reward.presenter.platdetail.kwai.e.1
        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void a(long j, long j2) {
            long jP = com.kwad.sdk.core.response.a.a.p(com.kwad.sdk.core.response.a.d.j(e.this.i));
            if (jP > 0) {
                j = Math.min(j, jP);
            }
            e.this.a(j, j2);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void a(long j, long j2) {
        int i = (int) (((j - j2) / 1000.0f) + 0.5f);
        if (i < 0) {
            this.g.setVisibility(8);
            this.h.setText(q().getString(R.string.ksad_reward_success_tip));
            r();
        } else {
            if (i == 0) {
                return;
            }
            this.g.setText(i + NotifyType.SOUND);
            this.g.setVisibility(0);
            this.h.setText(this.k);
        }
    }

    private void a(final String str) {
        if (at.a(str) || !FrameSequence.isEnable()) {
            this.f.setImageResource(R.drawable.ksad_reward_icon_detail);
        } else {
            KSImageLoader.loadImage(str, this.i, KSImageLoader.IMGOPTION_ENTRY_FS(), new SimpleImageLoadingListener() { // from class: com.kwad.sdk.reward.presenter.platdetail.kwai.e.2
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
                            e.this.f.setWebpStream(decodedResult.mFrameSequence);
                            e.this.f.a();
                        } else {
                            if (decodedResult.mBitmap == null || decodedResult.mBitmap.isRecycled()) {
                                return;
                            }
                            e.this.f.setImageBitmap(decodedResult.mBitmap);
                        }
                    }
                }
            });
        }
    }

    private void e() {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.b.getLayoutParams();
        marginLayoutParams.leftMargin = 0;
        this.b.setLayoutParams(marginLayoutParams);
        this.c.setVisibility(8);
        AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(this.i);
        long jB = com.kwad.sdk.core.response.a.a.b(adInfoJ) * 1000;
        long jP = com.kwad.sdk.core.response.a.a.p(adInfoJ);
        if (jP > 0 && jB != 0) {
            jB = Math.min(jP, jB);
        }
        this.k = com.kwad.sdk.core.response.a.c.a(this.i);
        if (at.a(this.k)) {
            this.k = q().getString(R.string.ksad_reward_default_tip);
        }
        a(com.kwad.sdk.core.response.a.c.b(this.i));
        a(jB, 0L);
        this.e.setOnClickListener(this);
        this.e.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        com.kwad.sdk.core.report.a.a(this.i, 41, this.a.j.getTouchCoords(), this.a.e);
        this.a.b.a();
    }

    private void r() {
        this.a.b.e();
    }

    @Override // com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.i = this.a.g;
        this.j = this.a.l;
        e();
        this.a.k.a(this.l);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        this.a.k.b(this.l);
        this.f.b();
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.c = (TextView) a(R.id.ksad_video_count_down);
        this.b = (ImageView) a(R.id.ksad_video_sound_switch);
        this.d = (ImageView) a(R.id.ksad_detail_close_btn);
        this.e = (ViewGroup) a(R.id.ksad_reward_container_new);
        this.f = (WebpAnimationImageView) this.e.findViewById(R.id.ksad_detail_reward_icon_new);
        this.g = (TextView) this.e.findViewById(R.id.ksad_video_count_down_new);
        this.h = (TextView) this.e.findViewById(R.id.ksad_detail_reward_tip_new);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.e) {
            com.kwad.sdk.core.download.a.a.a(new a.C0035a(view.getContext()).a(this.i).a(this.j).a(2).a(new a.b() { // from class: com.kwad.sdk.reward.presenter.platdetail.kwai.e.3
                @Override // com.kwad.sdk.core.download.a.a.b
                public void a() {
                    e.this.i();
                }
            }));
        }
    }
}
