package com.kwad.sdk.reward.d;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;
import com.kwad.sdk.core.imageloader.KSImageLoader;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.utils.af;
import com.kwad.sdk.utils.at;
import com.kwad.sdk.widget.KSCornerImageView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f extends d implements View.OnClickListener {
    private ViewGroup a;
    private ViewGroup b;
    private KSCornerImageView c;
    private TextView d;
    private KSCornerImageView e;
    private b f;

    static class a {
        private String a;
        private String b;

        a() {
        }

        @Nullable
        static a a(AdTemplate adTemplate) {
            if (adTemplate == null) {
                return null;
            }
            AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(adTemplate);
            a aVar = new a();
            aVar.b = com.kwad.sdk.core.config.b.aD();
            aVar.a = com.kwad.sdk.core.response.a.a.aE(adInfoJ);
            return aVar;
        }
    }

    public f(ViewGroup viewGroup, b bVar) {
        this.a = viewGroup;
        this.f = bVar;
        b();
    }

    private void b() {
        this.c = (KSCornerImageView) this.a.findViewById(R.id.ksad_reward_followed_icon);
        this.d = (TextView) this.a.findViewById(R.id.ksad_reward_followed_btn_follow);
        this.e = (KSCornerImageView) this.a.findViewById(R.id.ksad_reward_followed_kwai_logo);
        this.b = (ViewGroup) this.a.findViewById(R.id.ksad_reward_followed_root);
        this.d.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.b.setOnClickListener(this);
        if (af.e(this.a.getContext())) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = this.a.getLayoutParams();
        if (layoutParams instanceof FrameLayout.LayoutParams) {
            this.a.setLayoutParams((FrameLayout.LayoutParams) layoutParams);
        }
    }

    @Override // com.kwad.sdk.reward.d.d
    public ViewGroup a() {
        return this.b;
    }

    @Override // com.kwad.sdk.reward.d.d
    public void a(AdTemplate adTemplate) {
        super.a(adTemplate);
        a aVarA = a.a(adTemplate);
        if (aVarA == null) {
            return;
        }
        this.d.setText(aVarA.b);
        KSImageLoader.loadImage(this.c, aVarA.a, adTemplate);
        String strAF = com.kwad.sdk.core.config.b.aF();
        if (at.a(strAF)) {
            return;
        }
        KSImageLoader.loadImage(this.e, strAF, adTemplate);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.f == null) {
            return;
        }
        if (view.equals(this.d)) {
            this.f.c();
        } else if (view.equals(this.c)) {
            this.f.a();
        } else if (view.equals(this.b)) {
            this.f.b();
        }
    }
}
