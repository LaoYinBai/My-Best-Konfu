package com.tachikoma.core.component.listview;

import android.content.Context;
import com.kwad.v8.V8Object;
import com.tachikoma.annotation.TK_EXPORT_CLASS;
import com.tachikoma.core.bridge.JSContext;
import com.tachikoma.core.component.TKBase;
import com.tachikoma.core.component.listview.viewpager.RecyclerViewPager;
import com.tachikoma.core.component.listview.viewpager.TKOnPageChangeCallbackImpl;
import com.tachikoma.core.component.listview.viewpager.TKViewPagerAdapterExtra;
import com.tachikoma.core.utility.V8Proxy;
import java.util.ArrayList;
import java.util.List;

@TK_EXPORT_CLASS
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKViewPager extends TKBase<RecyclerViewPager> {
    private TKRecyclerAdapter mAdapter;
    private V8Object mDelegate;
    private int mDirection;
    private TKIndicator mIndicator;
    private JSContext.V8AssociateReference mIndicatorAssociateRef;
    private TKOnPageChangeCallbackImpl mPageChangeCallback;

    public TKViewPager(Context context, List<Object> list) {
        super(context, list);
        this.mDirection = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tachikoma.core.component.TKBase
    public RecyclerViewPager createViewInstance(Context context) {
        return new RecyclerViewPager(context);
    }

    @Override // com.tachikoma.core.component.TKBase
    public void onAttach() {
        super.onAttach();
        getView().setDirection(this.mDirection);
        if (this.mIndicator != null) {
            getView().addItemDecoration(new TKPagerIndicatorDecoration(this.mIndicator.create()));
        }
    }

    @Override // com.tachikoma.core.component.TKBase, com.tachikoma.core.common.ILifeCycle
    public void onDestroy() {
        super.onDestroy();
        if (this.mIndicatorAssociateRef != null) {
            this.mIndicatorAssociateRef.close();
            this.mIndicatorAssociateRef = null;
        }
        V8Proxy.release(this.mDelegate);
    }

    public void registerOnPageChangeCallback(V8Object v8Object) {
        if (V8Proxy.isV8Valid(v8Object)) {
            this.mPageChangeCallback = new TKOnPageChangeCallbackImpl(v8Object, this.mTKJSContext);
            getView().addOnPageListener(new RecyclerViewPager.OnPageChangeListener() { // from class: com.tachikoma.core.component.listview.TKViewPager.1
                @Override // com.tachikoma.core.component.listview.viewpager.RecyclerViewPager.OnPageChangeListener
                public void onPageScrollStateChanged(int i) {
                    if (TKViewPager.this.mPageChangeCallback != null) {
                        TKViewPager.this.mPageChangeCallback.onPageScrollStateChanged(i);
                    }
                }

                @Override // com.tachikoma.core.component.listview.viewpager.RecyclerViewPager.OnPageChangeListener
                public void onPageScrolled(int i, float f, int i2) {
                    if (TKViewPager.this.mPageChangeCallback != null) {
                        TKViewPager.this.mPageChangeCallback.onPageScrolled(i, f, i2);
                    }
                }

                @Override // com.tachikoma.core.component.listview.viewpager.RecyclerViewPager.OnPageChangeListener
                public void onPageSelected(int i, int i2) {
                    if (TKViewPager.this.mPageChangeCallback != null) {
                        TKViewPager.this.mPageChangeCallback.onPageSelected(i);
                    }
                }
            });
        }
    }

    public void reloadData() {
        if (this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void scrollTo(int i, int i2) {
        getView().setCurrentItem(i2, true);
    }

    public void setAdapter(V8Object v8Object) {
        if (V8Proxy.isV8Valid(v8Object)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.mTKJSContext);
            arrayList.add(v8Object);
            this.mAdapter = new TKViewPagerAdapterExtra(getContext(), arrayList);
            if (this.mDelegate != null) {
                this.mAdapter.setV8Delegate(this.mDelegate);
                V8Proxy.release(this.mDelegate);
            }
            getView().setAdapter(this.mAdapter);
        }
    }

    public void setDirection(int i) {
        this.mDirection = i;
    }

    @Deprecated
    public void setIndicator(V8Object v8Object) {
        JSContext.V8AssociateReference associateReference = this.mTKJSContext.context().getAssociateReference(v8Object);
        this.mIndicator = (TKIndicator) associateReference.nativeObject;
        if (this.mIndicatorAssociateRef != null) {
            this.mIndicatorAssociateRef.close();
        }
        this.mIndicatorAssociateRef = associateReference;
    }

    public void setViewPagerDelegate(V8Object v8Object) {
        if (V8Proxy.isV8Valid(v8Object)) {
            if (this.mAdapter != null) {
                this.mAdapter.setV8Delegate(v8Object);
            } else {
                this.mDelegate = v8Object.twin();
            }
        }
    }

    public void smoothScrollTo(int i, int i2) {
        getView().setCurrentItem(i2, true);
    }
}
