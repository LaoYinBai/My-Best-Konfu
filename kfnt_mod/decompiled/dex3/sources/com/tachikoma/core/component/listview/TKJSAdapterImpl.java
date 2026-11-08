package com.tachikoma.core.component.listview;

import android.view.ViewGroup;
import com.kwad.v8.V8Object;
import com.tachikoma.core.bridge.TKJSContext;
import com.tachikoma.core.component.anim.AnimationProperty;
import com.tachikoma.core.component.listview.TKRecyclerAdapter;
import com.tachikoma.core.component.recyclerview.export.ITKListDataSource;
import com.tachikoma.core.component.view.TKView;
import com.tachikoma.core.utility.SizeUtil;
import com.tachikoma.core.utility.V8Proxy;
import com.tachikoma.core.yoga.layout.YogaLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class TKJSAdapterImpl implements ITKRecyclerAdapter, ITKListDataSource {
    private static final int HEIGHT_INVALID = -1;
    private boolean mIsSupportForSetFullSpan;
    private final V8Object mJSAdapter;
    boolean mNeedFixHeight;
    private final TKJSContext mTKJSContext;
    private final List<V8Object> mV8Cache = new ArrayList();

    TKJSAdapterImpl(V8Object v8Object, TKJSContext tKJSContext) {
        this.mJSAdapter = v8Object.twin();
        this.mTKJSContext = tKJSContext;
    }

    private int getItemHeight(V8Object v8Object, int i) throws Throwable {
        V8Object v8ObjectSizeForItemView;
        try {
            v8ObjectSizeForItemView = sizeForItemView(v8Object, i);
            try {
                int iDp2Px = SizeUtil.dp2Px(((Number) v8ObjectSizeForItemView.get(AnimationProperty.HEIGHT)).intValue());
                V8Proxy.release(v8ObjectSizeForItemView);
                return iDp2Px;
            } catch (Exception unused) {
                V8Proxy.release(v8ObjectSizeForItemView);
                return -1;
            } catch (Throwable th) {
                th = th;
                V8Proxy.release(v8ObjectSizeForItemView);
                throw th;
            }
        } catch (Exception unused2) {
            v8ObjectSizeForItemView = null;
        } catch (Throwable th2) {
            th = th2;
            v8ObjectSizeForItemView = null;
        }
    }

    @Override // com.tachikoma.core.component.recyclerview.export.ITKListDataSource
    public int countOfItems(V8Object v8Object, int i) {
        return ((Integer) this.mJSAdapter.executeJSFunction("countOfItems", new Object[]{v8Object})).intValue();
    }

    @Override // com.tachikoma.core.component.recyclerview.export.ITKListDataSource
    public int countOfSections(V8Object v8Object) {
        return 0;
    }

    @Override // com.tachikoma.core.component.recyclerview.export.ITKListDataSource
    public V8Object createItemView(V8Object v8Object, V8Object v8Object2, String str) {
        return (V8Object) this.mJSAdapter.executeJSFunction("createItemView", new Object[]{v8Object, v8Object2, str});
    }

    @Override // com.tachikoma.core.component.recyclerview.export.ITKListDataSource
    public V8Object createSectionFooter(V8Object v8Object, int i, String str) {
        return null;
    }

    @Override // com.tachikoma.core.component.recyclerview.export.ITKListDataSource
    public V8Object createSectionHeader(V8Object v8Object, int i, String str) {
        return null;
    }

    @Override // com.tachikoma.core.component.listview.ITKRecyclerAdapter
    public int getItemCount() {
        return countOfItems(this.mJSAdapter, 0);
    }

    @Override // com.tachikoma.core.component.listview.ITKRecyclerAdapter
    public int getItemViewType(int i) {
        return itemType(0, i);
    }

    @Override // com.tachikoma.core.component.listview.ITKRecyclerAdapter
    public boolean isFullWidgetForView(int i) {
        if (this.mIsSupportForSetFullSpan) {
            try {
                return ((Boolean) this.mJSAdapter.executeJSFunction("isFullWidgetForView", new Object[]{Integer.valueOf(i)})).booleanValue();
            } catch (Throwable unused) {
            }
        }
        return false;
    }

    public int itemType(int i, int i2) {
        return reusableKeyForItemView(this.mJSAdapter, i2);
    }

    @Override // com.tachikoma.core.component.listview.ITKRecyclerAdapter
    public void onBindViewHolder(TKRecyclerAdapter.TKViewHolder tKViewHolder, int i) {
        int itemHeight;
        if (this.mNeedFixHeight && (itemHeight = getItemHeight(this.mJSAdapter, i)) != -1) {
            if (tKViewHolder.itemView.getLayoutParams() != null) {
                tKViewHolder.itemView.getLayoutParams().height = itemHeight;
            } else {
                tKViewHolder.itemView.setLayoutParams(new YogaLayout.LayoutParams(-1, itemHeight));
            }
        }
        reuseItemView(this.mJSAdapter, tKViewHolder.mJSObject, i);
    }

    @Override // com.tachikoma.core.component.listview.ITKRecyclerAdapter
    public TKRecyclerAdapter.TKViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        V8Object v8ObjectCreateItemView = createItemView(this.mJSAdapter, null, String.valueOf(i));
        TKView tKView = new TKView(viewGroup.getContext(), new ArrayList());
        tKView.setTKJSContext(this.mTKJSContext);
        tKView.add(v8ObjectCreateItemView);
        TKRecyclerAdapter.TKViewHolder tKViewHolder = new TKRecyclerAdapter.TKViewHolder(tKView.getView());
        tKViewHolder.mJSObject = v8ObjectCreateItemView.twin();
        tKViewHolder.mBase = tKView;
        this.mV8Cache.add(tKViewHolder.mJSObject);
        V8Proxy.release(v8ObjectCreateItemView);
        return tKViewHolder;
    }

    @Override // com.tachikoma.core.component.listview.ITKRecyclerAdapter
    public void onDestroy() {
        Iterator<V8Object> it = this.mV8Cache.iterator();
        while (it.hasNext()) {
            V8Proxy.release(it.next());
        }
        this.mV8Cache.clear();
        V8Proxy.release(this.mJSAdapter);
    }

    @Override // com.tachikoma.core.component.recyclerview.export.ITKListDataSource
    public int reusableKeyForItemView(V8Object v8Object, int i) {
        return ((Number) this.mJSAdapter.executeJSFunction("reusableKeyForItemView", new Object[]{v8Object, Integer.valueOf(i)})).intValue();
    }

    @Override // com.tachikoma.core.component.recyclerview.export.ITKListDataSource
    public String reusableKeyForSectionHeader(V8Object v8Object, int i) {
        return null;
    }

    @Override // com.tachikoma.core.component.recyclerview.export.ITKListDataSource
    public String reusableKeyForSetionFooter(V8Object v8Object, int i) {
        return null;
    }

    @Override // com.tachikoma.core.component.recyclerview.export.ITKListDataSource
    public void reuseItemView(V8Object v8Object, V8Object v8Object2, int i) {
        this.mJSAdapter.executeJSFunction("reuseItemView", new Object[]{v8Object, v8Object2, Integer.valueOf(i)});
    }

    @Override // com.tachikoma.core.component.recyclerview.export.ITKListDataSource
    public void reuseSectionFooter(V8Object v8Object, V8Object v8Object2, int i) {
    }

    @Override // com.tachikoma.core.component.recyclerview.export.ITKListDataSource
    public void reuseSectionHeader(V8Object v8Object, V8Object v8Object2, int i) {
    }

    @Override // com.tachikoma.core.component.listview.ITKRecyclerAdapter
    public void setNeedFixHeight(boolean z) {
        this.mNeedFixHeight = z;
    }

    @Override // com.tachikoma.core.component.listview.ITKRecyclerAdapter
    public void setSupportForFullSpan(boolean z) {
        this.mIsSupportForSetFullSpan = z;
    }

    @Override // com.tachikoma.core.component.recyclerview.export.ITKListDataSource
    public V8Object sizeForItemView(V8Object v8Object, int i) {
        return (V8Object) this.mJSAdapter.executeJSFunction("sizeForItemView", new Object[]{v8Object, Integer.valueOf(i)});
    }
}
