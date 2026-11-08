package com.kwad.sdk.core.page;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.kwad.sdk.R;
import com.kwad.sdk.core.page.recycle.DetailWebRecycleView;
import com.kwad.sdk.core.page.recycle.e;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.feed.widget.k;
import com.kwad.sdk.mvp.Presenter;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b extends com.kwad.sdk.core.page.recycle.a {
    private AdTemplate a;
    private WebView b;
    private k c;
    private com.kwad.sdk.core.download.a.b d;
    private LinearLayout e;
    private TextView f;
    private ImageView g;

    private static class a extends com.kwad.sdk.core.page.recycle.d {
        AdTemplate a;
        e b;
        private Context c;

        a(AdTemplate adTemplate, Context context, e eVar) {
            this.a = adTemplate;
            this.c = context;
            this.b = eVar;
        }

        @Override // com.kwad.sdk.core.page.recycle.d
        protected com.kwad.sdk.core.page.recycle.c a(ViewGroup viewGroup, int i) {
            if (i == 1) {
                View viewInflate = LayoutInflater.from(this.c).inflate(R.layout.ksad_datail_webview_container, viewGroup, false);
                Presenter presenter = new Presenter();
                presenter.a((Presenter) new com.kwad.sdk.core.page.kwai.b());
                return new com.kwad.sdk.core.page.recycle.c(viewInflate, presenter, this.b);
            }
            if (i != 0) {
                View view = new View(viewGroup.getContext());
                view.setLayoutParams(new ViewGroup.LayoutParams(-1, com.kwad.sdk.a.kwai.a.a(viewGroup.getContext(), 64.0f)));
                return new com.kwad.sdk.core.page.recycle.c(view, new Presenter(), this.b);
            }
            k kVar = new k(viewGroup.getContext());
            Presenter presenter2 = new Presenter();
            presenter2.a((Presenter) new com.kwad.sdk.core.page.kwai.a());
            return new com.kwad.sdk.core.page.recycle.c(kVar, presenter2, this.b);
        }

        @Override // com.kwad.sdk.core.page.recycle.d
        /* renamed from: a */
        public void onBindViewHolder(com.kwad.sdk.core.page.recycle.c cVar, int i) {
            super.onBindViewHolder(cVar, i);
        }

        public int getItemCount() {
            return com.kwad.sdk.core.response.a.a.B(com.kwad.sdk.core.response.a.d.j(this.a)) ? 3 : 2;
        }

        public int getItemViewType(int i) {
            return i;
        }
    }

    public static b a(AdTemplate adTemplate) {
        b bVar = new b();
        Bundle bundle = new Bundle();
        bundle.putString("key_photo", adTemplate.toJson().toString());
        bundle.putBoolean("key_report", adTemplate.mPvReported);
        bVar.setArguments(bundle);
        return bVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(View view) {
        this.e = (LinearLayout) view.findViewById(R.id.ksad_web_tip_bar);
        this.f = (TextView) view.findViewById(R.id.ksad_web_tip_bar_textview);
        this.g = (ImageView) view.findViewById(R.id.ksad_web_tip_close_btn);
        this.g.setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.core.page.b.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                b.this.e.setVisibility(8);
            }
        });
        AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(this.a);
        boolean zAf = com.kwad.sdk.core.response.a.a.af(adInfoJ);
        String strAb = com.kwad.sdk.core.response.a.a.ab(adInfoJ);
        if (!zAf) {
            this.e.setVisibility(8);
            return;
        }
        this.e.setVisibility(0);
        this.f.setText(strAb);
        this.f.setSelected(true);
    }

    @Override // com.kwad.sdk.core.page.recycle.a
    protected com.kwad.sdk.core.page.recycle.d a(RecyclerView recyclerView) {
        return new a(this.a, recyclerView.getContext(), new e(this.a, this.d, recyclerView));
    }

    public void a(com.kwad.sdk.core.download.a.b bVar) {
        this.d = bVar;
    }

    @Override // com.kwad.sdk.h.d, com.kwad.sdk.h.kwai.b
    public boolean a_() {
        if (this.b != null && this.b.canGoBack()) {
            this.b.goBack();
            return true;
        }
        if (this.c != null) {
            return this.c.d();
        }
        return false;
    }

    @Override // com.kwad.sdk.core.page.recycle.a
    public int b() {
        return R.layout.ksad_detail_webview;
    }

    @Override // com.kwad.sdk.core.page.recycle.a, com.kwad.sdk.api.core.fragment.KsFragment, com.kwad.sdk.api.core.fragment.AbstractIFragmentLifecycle, com.kwad.sdk.api.core.fragment.IFragmentLifecycle
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        String string = getArguments().getString("key_photo");
        AdTemplate adTemplate = new AdTemplate();
        if (string != null) {
            try {
                adTemplate.parseJson(new JSONObject(string));
            } catch (JSONException e) {
                com.kwad.sdk.core.d.a.a(e);
            }
        }
        this.a = adTemplate;
        this.a.mPvReported = getArguments().getBoolean("key_report", false);
        this.a.mAdWebVideoPageShowing = true;
    }

    @Override // com.kwad.sdk.core.page.recycle.a, com.kwad.sdk.h.d, com.kwad.sdk.api.core.fragment.KsFragment, com.kwad.sdk.api.core.fragment.AbstractIFragmentLifecycle, com.kwad.sdk.api.core.fragment.IFragmentLifecycle
    public void onDestroy() {
        super.onDestroy();
        if (this.a != null) {
            this.a.mAdWebVideoPageShowing = false;
        }
    }

    @Override // com.kwad.sdk.core.page.recycle.a, com.kwad.sdk.api.core.fragment.KsFragment, com.kwad.sdk.api.core.fragment.AbstractIFragmentLifecycle, com.kwad.sdk.api.core.fragment.IFragmentLifecycle
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        final DetailWebRecycleView detailWebRecycleView = (DetailWebRecycleView) d();
        detailWebRecycleView.setInterceptRequestFocusForWeb(true);
        detailWebRecycleView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.kwad.sdk.core.page.b.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (detailWebRecycleView.getChildCount() > 0) {
                    RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = detailWebRecycleView.findViewHolderForAdapterPosition(0);
                    com.kwad.sdk.core.d.a.a("AdRecycleWebFragment", "recycleView.setTopViewHeight(" + viewHolderFindViewHolderForAdapterPosition.itemView.getHeight() + ")");
                    detailWebRecycleView.setTopViewHeight(viewHolderFindViewHolderForAdapterPosition.itemView.getHeight());
                    RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition2 = detailWebRecycleView.findViewHolderForAdapterPosition(0);
                    if (viewHolderFindViewHolderForAdapterPosition2.itemView instanceof k) {
                        b.this.c = (k) viewHolderFindViewHolderForAdapterPosition2.itemView;
                    }
                }
                if (detailWebRecycleView.getChildCount() > 1) {
                    View view2 = detailWebRecycleView.findViewHolderForAdapterPosition(1).itemView;
                    b.this.b = (WebView) view2.findViewById(R.id.ksad_video_webView);
                    b.this.a(view2);
                }
                com.kwad.sdk.core.d.a.a("AdRecycleWebFragment", "onGlobalLayout");
                detailWebRecycleView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }
}
