package com.kwad.sdk.reflux;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.kwad.sdk.R;
import com.kwad.sdk.feed.FeedType;
import com.kwad.sdk.reflux.b;
import com.kwad.sdk.reflux.kwai.c;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class KsRefluxNativeView extends FrameLayout {
    private RecyclerView a;
    private b b;

    static class a extends RecyclerView.ViewHolder {
        public a(View view) {
            super(view);
        }

        @Nullable
        public com.kwad.sdk.reflux.kwai.a a() {
            if (this.itemView instanceof com.kwad.sdk.reflux.kwai.a) {
                return (com.kwad.sdk.reflux.kwai.a) this.itemView;
            }
            return null;
        }
    }

    static class b extends RecyclerView.Adapter {
        private Context a;
        private List<com.kwad.sdk.reflux.b> b = new ArrayList();

        public b(Context context) {
            this.a = context;
        }

        private void a(List<com.kwad.sdk.reflux.b> list, List<com.kwad.sdk.reflux.b> list2) {
            com.kwad.sdk.reflux.b bVar;
            int i;
            int size = list2.size();
            for (int i2 = 0; i2 < size; i2++) {
                list.add(list2.get(i2));
                if (i2 == size - 1) {
                    bVar = new com.kwad.sdk.reflux.b();
                    i = -2;
                } else {
                    bVar = new com.kwad.sdk.reflux.b();
                    i = -1;
                }
                bVar.b(i);
                list.add(bVar);
            }
        }

        public void a(List<com.kwad.sdk.reflux.b> list) {
            if (list == null) {
                return;
            }
            this.b.clear();
            a(this.b, list);
            notifyDataSetChanged();
        }

        public int getItemCount() {
            return this.b.size();
        }

        public long getItemId(int i) {
            return i;
        }

        public int getItemViewType(int i) {
            return this.b.get(i).k();
        }

        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            com.kwad.sdk.reflux.b bVar = this.b.get(i);
            a aVar = (a) viewHolder;
            int itemViewType = aVar.getItemViewType();
            if (itemViewType == FeedType.FEED_TYPE_UNKNOWN.getType() || itemViewType == FeedType.FEED_TYPE_TEXT_ABOVE.getType() || itemViewType == FeedType.FEED_TYPE_TEXT_LEFT.getType()) {
                if (bVar.i()) {
                    bVar.b();
                    bVar.a(new b.a() { // from class: com.kwad.sdk.reflux.KsRefluxNativeView.b.1
                        @Override // com.kwad.sdk.reflux.b.a
                        public void a(com.kwad.sdk.reflux.b bVar2) {
                            try {
                                b.this.notifyDataSetChanged();
                            } catch (IllegalStateException unused) {
                            }
                        }
                    });
                }
                com.kwad.sdk.reflux.kwai.a aVarA = aVar.a();
                if (aVarA != null) {
                    aVarA.a(bVar);
                }
            }
        }

        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View cVar;
            int i2;
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -2);
            LayoutInflater layoutInflaterFrom = LayoutInflater.from(viewGroup.getContext());
            if (i == FeedType.FEED_TYPE_TEXT_ABOVE.getType()) {
                cVar = new c(this.a);
            } else if (i == FeedType.FEED_TYPE_TEXT_LEFT.getType()) {
                cVar = new com.kwad.sdk.reflux.kwai.b(this.a);
            } else {
                if (i == -1) {
                    i2 = R.layout.ksad_reflux_card_divider;
                } else if (i == -2) {
                    i2 = R.layout.ksad_reflux_native_list_footer;
                } else {
                    cVar = new c(this.a);
                }
                cVar = layoutInflaterFrom.inflate(i2, viewGroup, false);
            }
            cVar.setLayoutParams(layoutParams);
            return new a(cVar);
        }
    }

    public KsRefluxNativeView(@NonNull Context context) {
        super(context);
        a(context, null, 0);
    }

    public KsRefluxNativeView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet, 0);
    }

    public KsRefluxNativeView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet, i);
    }

    @RequiresApi(api = 21)
    public KsRefluxNativeView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a(context, attributeSet, i);
    }

    private void a(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        inflate(context, R.layout.ksad_reflux_native, this);
        this.a = findViewById(R.id.ksad_reflux_native_list);
        this.a.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        this.b = new b(getContext());
        this.b.setHasStableIds(true);
        this.a.setAdapter(this.b);
    }

    public void a(List<com.kwad.sdk.reflux.b> list) {
        this.b.a(list);
    }
}
