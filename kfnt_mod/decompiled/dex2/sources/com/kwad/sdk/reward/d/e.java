package com.kwad.sdk.reward.d;

import android.content.Context;
import android.text.SpannableString;
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
public class e extends d implements View.OnClickListener {
    private ViewGroup a;
    private ViewGroup b;
    private KSCornerImageView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private KSCornerImageView g;
    private View h;
    private b i;
    private boolean j;

    static class a {
        private String a;
        private String b;
        private String c;
        private String d;
        private SpannableString e;

        a() {
        }

        @Nullable
        static a a(AdTemplate adTemplate) {
            if (adTemplate == null) {
                return null;
            }
            AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(adTemplate);
            a aVar = new a();
            aVar.d = com.kwad.sdk.core.config.b.aG();
            aVar.b = com.kwad.sdk.core.response.a.a.aC(adInfoJ);
            aVar.a = com.kwad.sdk.core.response.a.a.aD(adInfoJ);
            aVar.c = com.kwad.sdk.core.response.a.a.aE(adInfoJ);
            aVar.e = com.kwad.sdk.core.response.a.a.a(adInfoJ, com.kwad.sdk.core.config.b.aJ());
            return aVar;
        }

        public String a(boolean z) {
            return this.a;
        }

        public CharSequence b(boolean z) {
            if (!z && this.e != null) {
                return this.e;
            }
            return this.b;
        }
    }

    public e(ViewGroup viewGroup, boolean z, b bVar) {
        this.a = viewGroup;
        this.j = z;
        this.i = bVar;
        b();
    }

    private void b() {
        this.b = (ViewGroup) this.a.findViewById(R.id.ksad_reward_follow_root);
        this.c = (KSCornerImageView) this.a.findViewById(R.id.ksad_reward_follow_icon);
        this.d = (TextView) this.a.findViewById(R.id.ksad_reward_follow_name);
        this.e = (TextView) this.a.findViewById(R.id.ksad_reward_follow_desc);
        this.f = (TextView) this.a.findViewById(R.id.ksad_reward_follow_btn_follow);
        this.g = (KSCornerImageView) this.a.findViewById(R.id.ksad_reward_follow_kwai_logo);
        this.h = this.a.findViewById(R.id.ksad_reward_text_aera);
        this.f.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.h.setOnClickListener(this);
        Context context = this.a.getContext();
        if (af.e(context)) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = this.a.getLayoutParams();
        if (layoutParams instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
            layoutParams2.width = context.getResources().getDimensionPixelSize(R.dimen.ksad_reward_follow_card_width_horizontal);
            this.a.setLayoutParams(layoutParams2);
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
        this.c.setVisibility(this.j ? 8 : 0);
        KSImageLoader.loadImage(this.c, aVarA.c, adTemplate);
        String strAF = com.kwad.sdk.core.config.b.aF();
        if (!at.a(strAF)) {
            KSImageLoader.loadImage(this.g, strAF, adTemplate);
        }
        this.d.setText(aVarA.a(this.j));
        this.e.setText(aVarA.b(this.j));
        this.f.setText(aVarA.d);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.i == null) {
            return;
        }
        if (view.equals(this.f)) {
            this.i.c();
        } else if (view.equals(this.c)) {
            this.i.a();
        } else if (view.equals(this.h)) {
            this.i.b();
        }
    }
}
