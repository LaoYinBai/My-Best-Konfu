package com.kwad.sdk.core.page.recycle;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.kwad.sdk.mvp.Presenter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class d extends RecyclerView.Adapter<c> {
    private List<Presenter> a = new ArrayList();

    protected abstract c a(ViewGroup viewGroup, int i);

    public void a() {
        Iterator<Presenter> it = this.a.iterator();
        while (it.hasNext()) {
            it.next().k();
        }
    }

    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onViewRecycled(c cVar) {
        super.onViewRecycled(cVar);
        cVar.a.j();
    }

    @Override // 
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(c cVar, int i) {
        cVar.a.a(cVar.b);
    }

    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public final c onCreateViewHolder(ViewGroup viewGroup, int i) {
        c cVarA = a(viewGroup, i);
        this.a.add(cVarA.a);
        return cVarA;
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        a();
    }
}
