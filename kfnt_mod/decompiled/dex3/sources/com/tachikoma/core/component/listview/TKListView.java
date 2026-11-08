package com.tachikoma.core.component.listview;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.kwad.v8.V8Object;
import com.tachikoma.annotation.TK_EXPORT_CLASS;
import com.tachikoma.core.bridge.JSContext;
import com.tachikoma.core.common.ILifeCycle;
import com.tachikoma.core.component.TKBase;
import com.tachikoma.core.component.view.TKView;
import com.tachikoma.core.utility.V8Proxy;
import java.util.Iterator;
import java.util.List;

@TK_EXPORT_CLASS
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKListView extends TKBase<ListView> implements ILifeCycle {
    private JSContext.V8AssociateReference listViewAdapterAssociateRef;
    ListView mList;

    public TKListView(Context context, List<Object> list) {
        super(context, list);
    }

    public void addFooterView(TKView tKView) {
        this.mList.addFooterView(tKView.getView());
    }

    public void addHeaderView(View view) {
        this.mList.addHeaderView(view);
    }

    public void addHeaderView(View view, Object obj, boolean z) {
        this.mList.addHeaderView(view, obj, z);
    }

    @RequiresApi(api = 19)
    public boolean areFooterDividersEnabled() {
        return this.mList.areFooterDividersEnabled();
    }

    @RequiresApi(api = 19)
    public boolean areHeaderDividersEnabled() {
        return this.mList.areHeaderDividersEnabled();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tachikoma.core.component.TKBase
    public ListView createViewInstance(Context context) {
        return new ListView(context);
    }

    public ListAdapter getAdapter() {
        return this.mList.getAdapter();
    }

    public int getDividerHeight() {
        return this.mList.getDividerHeight();
    }

    public int getFooterViewsCount() {
        return this.mList.getFooterViewsCount();
    }

    public int getHeaderViewsCount() {
        return this.mList.getHeaderViewsCount();
    }

    public boolean isOpaque() {
        return this.mList.isOpaque();
    }

    public boolean isSmoothScrollbarEnabled() {
        return this.mList.isSmoothScrollbarEnabled();
    }

    @Override // com.tachikoma.core.component.TKBase, com.tachikoma.core.common.ILifeCycle
    public void onCreate() {
        super.onCreate();
        this.mList = getView();
    }

    @Override // com.tachikoma.core.component.TKBase, com.tachikoma.core.common.ILifeCycle
    public void onDestroy() {
        if (getAdapter() instanceof TKBaseAdapter) {
            Iterator<V8Object> it = ((TKBaseAdapter) getAdapter()).getAssociatV8Objects().iterator();
            while (it.hasNext()) {
                V8Proxy.release(it.next());
            }
        }
        if (this.listViewAdapterAssociateRef != null) {
            this.listViewAdapterAssociateRef.close();
            this.listViewAdapterAssociateRef = null;
        }
        super.onDestroy();
    }

    public boolean removeFooterView(View view) {
        return this.mList.removeFooterView(view);
    }

    public boolean removeHeaderView(View view) {
        return this.mList.removeHeaderView(view);
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        return this.mList.requestChildRectangleOnScreen(view, rect, z);
    }

    public void setAdapter(V8Object v8Object) {
        JSContext.V8AssociateReference associateReference = this.mTKJSContext.context().getAssociateReference(v8Object);
        this.mList.setAdapter((ListAdapter) associateReference.nativeObject);
        if (this.listViewAdapterAssociateRef != null) {
            this.listViewAdapterAssociateRef.close();
        }
        this.listViewAdapterAssociateRef = associateReference;
    }

    public void setDivider(@Nullable Drawable drawable) {
        this.mList.setDivider(drawable);
    }

    public void setFooterDividersEnabled(boolean z) {
        this.mList.setFooterDividersEnabled(z);
    }

    public void setHeaderDividersEnabled(boolean z) {
        this.mList.setHeaderDividersEnabled(z);
    }

    public void setSmoothScrollbarEnabled(boolean z) {
        this.mList.setSmoothScrollbarEnabled(z);
    }

    public void smoothScrollBy(int i, int i2) {
        this.mList.smoothScrollBy(i, i2);
    }

    public void smoothScrollByOffset(int i) {
        this.mList.smoothScrollByOffset(i);
    }

    public void smoothScrollToPosition(int i) {
        this.mList.smoothScrollToPosition(i);
    }

    public void smoothScrollToPosition(int i, int i2) {
        this.mList.smoothScrollToPosition(i, i2);
    }

    public void smoothScrollToPositionFromTop(int i, int i2) {
        this.mList.smoothScrollToPositionFromTop(i, i2);
    }

    public void smoothScrollToPositionFromTop(int i, int i2, int i3) {
        this.mList.smoothScrollToPositionFromTop(i, i2, i3);
    }
}
