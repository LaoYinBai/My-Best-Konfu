package com.tachikoma.core.component.recyclerview.export;

import android.content.Context;
import android.view.View;
import com.kwad.v8.V8Array;
import com.kwad.v8.V8Object;
import com.kwai.library.widget.refresh.RefreshLayout;
import com.tachikoma.annotation.TK_EXPORT_CLASS;
import com.tachikoma.core.bridge.JSContext;
import com.tachikoma.core.component.TKBase;
import com.tachikoma.core.component.view.TKView;
import com.tachikoma.core.utility.V8Proxy;
import java.util.List;

@TK_EXPORT_CLASS
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKRefreshControl extends TKBase<View> {
    private TKView animatedView;
    private JSContext.V8AssociateReference animatedViewAsoRef;
    private V8Object mAssociateObject;
    private RefreshLayout mRefreshLayout;

    public TKRefreshControl(Context context, List<Object> list) {
        super(context, list);
    }

    public void beginRefreshing() {
        if (this.mRefreshLayout != null) {
            this.mRefreshLayout.setRefreshing(true);
        }
    }

    @Override // com.tachikoma.core.component.TKBase
    protected View createViewInstance(Context context) {
        return null;
    }

    public void endRefreshing() {
        if (this.mRefreshLayout != null) {
            this.mRefreshLayout.setRefreshing(false);
        }
    }

    @Override // com.tachikoma.core.component.TKBase, com.tachikoma.core.common.ILifeCycle
    public void onDestroy() {
        super.onDestroy();
        if (this.animatedViewAsoRef != null) {
            this.animatedViewAsoRef.close();
            this.animatedViewAsoRef = null;
        }
        if (this.mAssociateObject != null) {
            this.mAssociateObject.close();
            this.mAssociateObject = null;
        }
    }

    public void onRefresh() {
        boolean zExecuteBooleanFunction;
        if (V8Proxy.isV8Valid(this.mAssociateObject)) {
            try {
                zExecuteBooleanFunction = this.mAssociateObject.executeBooleanFunction("shouldBeginRefreshing", (V8Array) null);
            } catch (Exception unused) {
                zExecuteBooleanFunction = false;
            }
            if (zExecuteBooleanFunction) {
                if (this.mAssociateObject != null) {
                    this.mAssociateObject.executeVoidFunction("onRefresh", (V8Array) null);
                }
            } else if (this.mRefreshLayout != null) {
                this.mRefreshLayout.setRefreshing(false);
            }
        }
    }

    public void setAnimatedView(V8Object v8Object) {
        JSContext.V8AssociateReference associateReference = this.mTKJSContext.context().getAssociateReference(v8Object);
        this.animatedView = (TKView) associateReference.nativeObject;
        if (this.animatedViewAsoRef != null) {
            this.animatedViewAsoRef.close();
        }
        this.animatedViewAsoRef = associateReference;
    }

    public void setAssociateObject(V8Object v8Object) {
        this.mAssociateObject = v8Object.twin();
    }

    public void setRefreshLayout(RefreshLayout refreshLayout) {
        this.mRefreshLayout = refreshLayout;
    }
}
