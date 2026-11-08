package com.kwad.sdk.reward.presenter.platdetail.kwai;

import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import com.kwad.sdk.R;
import com.kwad.sdk.api.KsVideoPlayConfig;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.reward.g;
import com.kwad.sdk.utils.j;
import com.kwad.sdk.utils.x;
import java.util.Observable;
import java.util.Observer;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d extends g implements View.OnClickListener, Observer {
    private ImageView b;
    private AdTemplate c;

    @NonNull
    private com.kwad.sdk.reward.c.a d;
    private j.a e = new j.a() { // from class: com.kwad.sdk.reward.presenter.platdetail.kwai.d.1
        @Override // com.kwad.sdk.utils.j.a
        public void a() {
            if (d.this.b == null || com.kwad.sdk.core.config.b.F()) {
                return;
            }
            d.this.b.post(new Runnable() { // from class: com.kwad.sdk.reward.presenter.platdetail.kwai.d.1.1
                @Override // java.lang.Runnable
                public void run() {
                    d.this.b.setSelected(false);
                    d.this.d.a(false, false);
                }
            });
        }

        @Override // com.kwad.sdk.utils.j.a
        public void b() {
        }
    };
    private com.kwad.sdk.contentalliance.detail.video.d f = new com.kwad.sdk.contentalliance.detail.video.e() { // from class: com.kwad.sdk.reward.presenter.platdetail.kwai.d.2
        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void c() {
            d.this.e();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.b.setOnClickListener(this);
    }

    @Override // com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        ImageView imageView;
        boolean z;
        super.a();
        this.c = this.a.g;
        this.d = this.a.k;
        KsVideoPlayConfig ksVideoPlayConfig = this.a.d;
        if (!this.d.a() && com.kwad.sdk.utils.b.a(q()).a()) {
            imageView = this.b;
            z = false;
        } else {
            if (ksVideoPlayConfig != null) {
                this.b.setSelected(ksVideoPlayConfig.isVideoSoundEnable());
                this.d.a(this.f);
                this.d.a(this.e);
                x.a().addObserver(this);
            }
            imageView = this.b;
            z = true;
        }
        imageView.setSelected(z);
        this.d.a(this.f);
        this.d.a(this.e);
        x.a().addObserver(this);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        x.a().deleteObserver(this);
        this.d.b(this.f);
        this.d.b(this.e);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.b = (ImageView) a(R.id.ksad_video_sound_switch);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.b) {
            this.d.a(!this.b.isSelected(), true);
            this.b.setSelected(!this.b.isSelected());
        }
    }

    @Override // java.util.Observer
    public void update(Observable observable, Object obj) {
        if (observable.equals(x.a()) && (obj instanceof String)) {
            String str = (String) obj;
            char c = 65535;
            int iHashCode = str.hashCode();
            if (iHashCode != -1891028138) {
                if (iHashCode == 321925954 && str.equals("ACTION_APP_GO_TO_BACKGROUND")) {
                    c = 0;
                }
            } else if (str.equals("ACTION_APP_BACK_TO_FOREGROUND")) {
                c = 1;
            }
            switch (c) {
                case 0:
                    if (this.c.mXiaomiAppStoreDetailViewOpen && this.c.mAdScene != null && this.c.mAdScene.getAdStyle() == 2) {
                        this.d.d();
                        break;
                    }
                    break;
                case 1:
                    if (this.c.mXiaomiAppStoreDetailViewOpen && this.c.mAdScene != null && this.c.mAdScene.getAdStyle() == 2) {
                        this.d.b();
                        break;
                    }
                    break;
            }
        }
    }
}
