package com.tachikoma.core.component.listview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.kwad.v8.V8Object;
import com.tachikoma.core.bridge.TKJSContext;
import com.tachikoma.core.component.TKBase;
import com.tachikoma.core.component.recyclerview.export.ITKListViewDelegate;
import com.tachikoma.core.utility.ObjectUtils;
import com.tachikoma.core.utility.V8Proxy;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKRecyclerAdapter extends RecyclerView.Adapter<TKViewHolder> implements ITKListViewDelegate {
    private ITKRecyclerAdapter mAdapter;
    private V8Object mDelegateV8;
    private TKJSContext mTKJSContext;
    private V8Object mV8AssociatedObject;

    public static class TKViewHolder extends RecyclerView.ViewHolder {
        public TKBase<?> mBase;
        public V8Object mJSObject;

        public TKViewHolder(View view) {
            super(view);
        }
    }

    public TKRecyclerAdapter(Context context, List<Object> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        this.mTKJSContext = V8Proxy.getTKContext(list);
        this.mV8AssociatedObject = ((V8Object) ObjectUtils.requireNonNull(V8Proxy.getAssociateJsObject(list))).twin();
        this.mAdapter = new TKJSAdapterImpl(this.mV8AssociatedObject, V8Proxy.getTKContext(list));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void didSelectItemView(int i) {
        didSelectItemView(this.mV8AssociatedObject, i);
    }

    private void willDisplayItemView(int i) {
        willDisplayItemView(this.mV8AssociatedObject, i);
    }

    @Override // com.tachikoma.core.component.recyclerview.export.ITKListViewDelegate
    public void didSelectItemView(V8Object v8Object, int i) {
        if (V8Proxy.isV8Valid(this.mDelegateV8)) {
            this.mDelegateV8.executeJSFunction("didSelectItemView", new Object[]{v8Object, Integer.valueOf(i)});
        }
    }

    public int getItemCount() {
        return this.mAdapter.getItemCount();
    }

    public int getItemViewType(int i) {
        return this.mAdapter.getItemViewType(i);
    }

    public boolean isFullWidgetForView(int i) {
        return this.mAdapter.isFullWidgetForView(i);
    }

    public void onBindViewHolder(@NonNull TKViewHolder tKViewHolder, final int i) {
        this.mAdapter.onBindViewHolder(tKViewHolder, i);
        tKViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.tachikoma.core.component.listview.TKRecyclerAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                TKRecyclerAdapter.this.didSelectItemView(i);
            }
        });
        willDisplayItemView(i);
    }

    @Override // 
    public TKViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return this.mAdapter.onCreateViewHolder(viewGroup, i);
    }

    public void onDestroy() {
        if (this.mAdapter != null) {
            this.mAdapter.onDestroy();
        }
        if (this.mDelegateV8 != null) {
            this.mDelegateV8.close();
            this.mDelegateV8 = null;
        }
        if (this.mV8AssociatedObject != null) {
            this.mV8AssociatedObject.close();
            this.mV8AssociatedObject = null;
        }
    }

    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.mAdapter.onDestroy();
    }

    public void setNeedFixHeight(boolean z) {
        this.mAdapter.setNeedFixHeight(z);
    }

    public void setSupportForFullSpan(boolean z) {
        this.mAdapter.setSupportForFullSpan(z);
    }

    public void setV8Delegate(V8Object v8Object) {
        this.mDelegateV8 = v8Object.twin();
    }

    @Override // com.tachikoma.core.component.recyclerview.export.ITKListViewDelegate
    public void willDisplayItemView(V8Object v8Object, int i) {
        if (V8Proxy.isV8Valid(this.mDelegateV8)) {
            this.mDelegateV8.executeJSFunction("willDisplayItemView", new Object[]{v8Object, Integer.valueOf(i)});
        }
    }
}
