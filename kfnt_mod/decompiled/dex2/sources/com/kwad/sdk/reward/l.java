package com.kwad.sdk.reward;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;
import com.kwad.sdk.core.imageloader.KSImageLoader;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdProductInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.utils.w;
import com.kwad.sdk.widget.KSCornerImageView;
import com.kwad.sdk.widget.KsPriceView;
import com.kwad.sdk.widget.KsStyledTextButton;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class l extends com.kwad.sdk.h.e {
    private static l a;
    private AdTemplate b;
    private a c;

    public interface a {
        void a();

        void b();

        void c();

        void d();
    }

    public static class b implements a {
        private l a;
        private a b;

        public b(@Nullable l lVar, @Nullable a aVar) {
            this.b = aVar;
            this.a = lVar;
        }

        @Override // com.kwad.sdk.reward.l.a
        public void a() {
            if (this.b != null) {
                this.b.a();
            }
        }

        @Override // com.kwad.sdk.reward.l.a
        public void b() {
            e();
            if (this.b != null) {
                this.b.b();
            }
        }

        @Override // com.kwad.sdk.reward.l.a
        public void c() {
            if (this.b != null) {
                this.b.c();
            }
        }

        @Override // com.kwad.sdk.reward.l.a
        public void d() {
            e();
            if (this.b != null) {
                this.b.d();
            }
        }

        protected void e() {
            if (this.a != null) {
                this.a.dismiss();
            }
        }
    }

    private static class c extends com.kwad.sdk.reward.d.d implements View.OnClickListener {
        private ViewGroup a;
        private a b;
        private ViewGroup c;
        private TextView d;
        private TextView e;
        private KSCornerImageView f;
        private TextView g;
        private KsStyledTextButton h;
        private View i;
        private AdTemplate j;

        public c(ViewGroup viewGroup, AdTemplate adTemplate) {
            this.a = viewGroup;
            this.j = adTemplate;
            b();
        }

        private void a(e eVar) {
            this.d.setText(eVar.b());
            SpannableString spannableStringG = eVar.g();
            if (spannableStringG != null) {
                this.e.setText(spannableStringG);
            }
            this.g.setText(eVar.c());
            this.h.setText(eVar.d());
            KSImageLoader.loadImage(this.f, eVar.a(), this.j);
        }

        private void b() {
            this.c = (ViewGroup) this.a.findViewById(R.id.ksad_reward_follow_end_root);
            this.d = (TextView) this.a.findViewById(R.id.ksad_reward_follow_end_title);
            this.e = (TextView) this.a.findViewById(R.id.ksad_reward_follow_end_fans);
            this.f = (KSCornerImageView) this.a.findViewById(R.id.ksad_reward_follow_end_icon);
            this.g = (TextView) this.a.findViewById(R.id.ksad_reward_follow_end_desc);
            this.h = (KsStyledTextButton) this.a.findViewById(R.id.ksad_reward_follow_end_btn_follow);
            this.i = this.a.findViewById(R.id.ksad_reward_follow_end_btn_close);
            this.h.setOnClickListener(this);
            this.i.setOnClickListener(this);
            this.f.setOnClickListener(this);
            this.d.setOnClickListener(this);
            this.e.setOnClickListener(this);
            this.g.setOnClickListener(this);
        }

        @Override // com.kwad.sdk.reward.d.d
        public ViewGroup a() {
            return this.c;
        }

        @Override // com.kwad.sdk.reward.d.d
        public void a(AdTemplate adTemplate) {
            super.a(adTemplate);
            a(e.a(adTemplate));
        }

        public void a(a aVar) {
            this.b = aVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.b == null) {
                return;
            }
            if (view.equals(this.i)) {
                this.b.b();
                return;
            }
            if (view.equals(this.h)) {
                this.b.c();
            } else if (view.equals(this.g) || view.equals(this.d) || view.equals(this.e)) {
                this.b.d();
            }
        }
    }

    private static class d extends com.kwad.sdk.reward.d.d implements View.OnClickListener {
        private ViewGroup a;
        private a b;
        private ViewGroup c;
        private TextView d;
        private KSCornerImageView e;
        private TextView f;
        private KsPriceView g;
        private KsStyledTextButton h;
        private View i;

        public d(ViewGroup viewGroup) {
            this.a = viewGroup;
            b();
        }

        private void a(e eVar, AdTemplate adTemplate) {
            this.h.setText(eVar.d());
            this.d.setText(eVar.b());
            this.f.setText(eVar.c());
            this.g.a(eVar.e(), eVar.f());
            KSImageLoader.loadImage(this.e, eVar.a(), adTemplate);
        }

        private void b() {
            this.c = (ViewGroup) this.a.findViewById(R.id.ksad_reward_order_end_card_root);
            this.d = (TextView) this.a.findViewById(R.id.ksad_reward_order_end_title);
            this.e = (KSCornerImageView) this.a.findViewById(R.id.ksad_reward_order_end_icon);
            this.f = (TextView) this.a.findViewById(R.id.ksad_reward_order_end_desc);
            this.g = (KsPriceView) this.a.findViewById(R.id.ksad_reward_order_end_price);
            this.h = (KsStyledTextButton) this.a.findViewById(R.id.ksad_reward_order_end_btn_buy);
            this.i = this.a.findViewById(R.id.ksad_reward_order_end_btn_close);
            this.h.setOnClickListener(this);
            this.i.setOnClickListener(this);
            this.f.setOnClickListener(this);
            this.g.setOnClickListener(this);
            this.d.setOnClickListener(this);
        }

        @Override // com.kwad.sdk.reward.d.d
        public ViewGroup a() {
            return this.c;
        }

        @Override // com.kwad.sdk.reward.d.d
        public void a(AdTemplate adTemplate) {
            super.a(adTemplate);
            a(e.b(adTemplate), adTemplate);
        }

        public void a(a aVar) {
            this.b = aVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.b == null) {
                return;
            }
            if (view.equals(this.i)) {
                this.b.b();
                return;
            }
            if (view.equals(this.h)) {
                this.b.c();
            } else if (view.equals(this.f) || view.equals(this.d) || view.equals(this.g)) {
                this.b.d();
            }
        }
    }

    private static class e {
        private String a;
        private String b;
        private String c;
        private String d;
        private String e;
        private String f;
        private SpannableString g;

        private e() {
        }

        @Nullable
        static e a(AdTemplate adTemplate) {
            if (adTemplate == null) {
                return null;
            }
            AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(adTemplate);
            e eVar = new e();
            eVar.b = com.kwad.sdk.core.response.a.a.aD(adInfoJ);
            eVar.a = com.kwad.sdk.core.response.a.a.aE(adInfoJ);
            eVar.g = com.kwad.sdk.core.response.a.a.a(adInfoJ, com.kwad.sdk.core.config.b.aJ());
            eVar.c = com.kwad.sdk.core.response.a.a.aC(adInfoJ);
            eVar.d = com.kwad.sdk.core.response.a.a.aA(adInfoJ) ? com.kwad.sdk.core.config.b.aD() : com.kwad.sdk.core.config.b.aG();
            return eVar;
        }

        @Nullable
        static e b(AdTemplate adTemplate) {
            if (adTemplate == null) {
                return null;
            }
            AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(adTemplate);
            AdProductInfo adProductInfoAK = com.kwad.sdk.core.response.a.a.aK(adInfoJ);
            e eVar = new e();
            eVar.b = adProductInfoAK.getName();
            eVar.a = adProductInfoAK.getIcon();
            eVar.c = com.kwad.sdk.core.response.a.a.s(adInfoJ);
            eVar.d = com.kwad.sdk.core.config.b.aE();
            eVar.e = adProductInfoAK.getPrice();
            eVar.f = adProductInfoAK.getOriginPrice();
            return eVar;
        }

        public String a() {
            return this.a;
        }

        public String b() {
            return this.b;
        }

        public String c() {
            return this.c;
        }

        public String d() {
            return this.d;
        }

        public String e() {
            return this.e;
        }

        public String f() {
            return this.f;
        }

        public SpannableString g() {
            return this.g;
        }
    }

    public static int a(AdTemplate adTemplate) {
        if (adTemplate == null) {
            return -1;
        }
        AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(adTemplate);
        if (com.kwad.sdk.core.response.a.a.aH(adInfoJ)) {
            return 0;
        }
        return com.kwad.sdk.core.response.a.a.aI(adInfoJ) ? 1 : -1;
    }

    public static void a(Activity activity, AdTemplate adTemplate, b bVar) {
        if (adTemplate == null || activity == null || activity.isFinishing()) {
            return;
        }
        a = new l();
        Bundle bundle = new Bundle();
        bundle.putString("key_template_json", adTemplate.toJson().toString());
        bVar.a = a;
        a.setArguments(bundle);
        a.a(bVar);
        try {
            a.show(activity.getFragmentManager(), "videoEndDialog");
        } catch (Exception unused) {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.kwad.sdk.h.e
    @Nullable
    public View a(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View viewInflate;
        com.kwad.sdk.widget.c cVar;
        c cVar2;
        getDialog().requestWindowFeature(1);
        try {
            String string = getArguments().getString("key_template_json");
            this.b = new AdTemplate();
            this.b.parseJson(new JSONObject(string));
        } catch (Throwable th) {
            com.kwad.sdk.core.d.a.b(th);
        }
        if (a(this.b) != 0) {
            viewInflate = layoutInflater.inflate(R.layout.ksad_reward_order_end_dialog, viewGroup, false);
            d dVar = new d((ViewGroup) viewInflate);
            dVar.a(new b(this, this.c));
            cVar = new com.kwad.sdk.widget.c(com.kwad.sdk.core.config.b.aL());
            cVar2 = dVar;
        } else {
            viewInflate = layoutInflater.inflate(R.layout.ksad_reward_follow_end_dialog, viewGroup, false);
            c cVar3 = new c((ViewGroup) viewInflate, this.b);
            cVar3.a(new b(this, this.c));
            cVar = new com.kwad.sdk.widget.c(com.kwad.sdk.core.config.b.aJ());
            cVar2 = cVar3;
        }
        cVar2.a(this.b);
        w.a(cVar, cVar2.a());
        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.kwad.sdk.reward.l.1
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return i == 4 && keyEvent.getAction() == 0;
            }
        });
        return viewInflate;
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        Window window = getDialog().getWindow();
        if (window == null) {
            return;
        }
        getDialog().setCanceledOnTouchOutside(false);
        window.setLayout(-1, -1);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        if (this.c != null) {
            this.c.a();
        }
    }
}
