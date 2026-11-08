package com.tachikoma.core.component.listview;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.kwad.v8.V8Object;
import com.kwad.v8.V8Value;
import com.kwai.library.widget.refresh.RefreshLayout;
import com.kwai.library.widget.refresh.b;
import com.tachikoma.annotation.TK_EXPORT_CLASS;
import com.tachikoma.core.bridge.JSContext;
import com.tachikoma.core.component.TKBase;
import com.tachikoma.core.component.recyclerview.LoadMorePresenter;
import com.tachikoma.core.component.recyclerview.RecyclerHeaderFooterAdapter;
import com.tachikoma.core.component.recyclerview.RefreshLayoutPresenter;
import com.tachikoma.core.component.recyclerview.export.TKRefreshControl;
import com.tachikoma.core.component.recyclerview.export.TKWaterLayoutConfiguration;
import com.tachikoma.core.component.recyclerview.pagelist.TKPageList;
import com.tachikoma.core.component.recyclerview.view.NestedRecyclerView;
import com.tachikoma.core.event.TKEventProcess;
import com.tachikoma.core.event.base.IBaseEvent;
import com.tachikoma.core.event.view.TKScrollEvent;
import com.tachikoma.core.utility.ObjectUtils;
import com.tachikoma.core.utility.SizeUtil;
import com.tachikoma.core.utility.V8Proxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@TK_EXPORT_CLASS
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKRecyclerView extends TKBase<b> {
    protected static final String TYPE_GRID = "grid";
    protected static final String TYPE_STAGGER = "stagger";
    private TKRecyclerAdapter mAdapter;
    protected int mDirection;
    private boolean mEnableLoadMore;
    private List<JSContext.V8AssociateReference> mFooterAssociateRefList;
    private List<View> mFooterViewList;
    private List<JSContext.V8AssociateReference> mHeaderAssociateRefList;
    private RecyclerHeaderFooterAdapter mHeaderFooterAdapter;
    private List<View> mHeaderViewList;
    protected TKWaterLayoutConfiguration mItemDecoration;
    protected String mLayoutType;
    private LoadMorePresenter mLoadMorePresenter;
    public int mOnEndReachedThreshold;
    private NestedRecyclerView mRecyclerView;
    private JSContext.V8AssociateReference mRefreshAssociateRef;
    private RefreshLayoutPresenter mRefreshListener;
    protected int mSpanCount;
    protected V8Object mV8AssociatedObject;

    public TKRecyclerView(Context context, List<Object> list) {
        super(context, list);
        this.mDirection = 1;
        this.mLayoutType = TYPE_GRID;
        this.mHeaderAssociateRefList = new ArrayList();
        this.mFooterAssociateRefList = new ArrayList();
        this.mV8AssociatedObject = ((V8Object) ObjectUtils.requireNonNull(V8Proxy.getAssociateJsObject(list))).twin();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasMore() {
        if (V8Proxy.isV8Valid(this.mV8AssociatedObject)) {
            return ((Boolean) this.mV8AssociatedObject.executeJSFunction("hasMore")).booleanValue();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onEndReached() {
        if (V8Proxy.isV8Valid(this.mV8AssociatedObject)) {
            this.mV8AssociatedObject.executeJSFunction("onEndReached", new Object[]{Integer.valueOf(this.mOnEndReachedThreshold)});
        }
    }

    private void setAdapterIfNeed() {
        if (this.mAdapter == null) {
            Object obj = this.mV8AssociatedObject.get("dataSource");
            if ((obj instanceof V8Object) && V8Proxy.isV8Valid((V8Object) obj)) {
                setAdapter(obj);
            }
            if (obj instanceof V8Value) {
                V8Proxy.release((V8Value) obj);
            }
        }
        if (this.mAdapter != null) {
            V8Object v8Object = (V8Object) this.mV8AssociatedObject.get("delegate");
            this.mAdapter.setV8Delegate(v8Object);
            V8Proxy.release(v8Object);
        }
    }

    public void addFooterView(V8Object v8Object) {
        if (V8Proxy.isV8Valid(v8Object)) {
            if (this.mFooterViewList == null) {
                this.mFooterViewList = new ArrayList();
            }
            JSContext.V8AssociateReference associateReference = this.mTKJSContext.context().getAssociateReference(v8Object);
            Object obj = associateReference.nativeObject;
            if (!(obj instanceof TKBase)) {
                associateReference.close();
            } else {
                this.mFooterViewList.add(((TKBase) obj).getView());
                this.mFooterAssociateRefList.add(associateReference);
            }
        }
    }

    public void addHeaderView(V8Object v8Object) {
        if (V8Proxy.isV8Valid(v8Object)) {
            if (this.mHeaderViewList == null) {
                this.mHeaderViewList = new ArrayList();
            }
            JSContext.V8AssociateReference associateReference = this.mTKJSContext.context().getAssociateReference(v8Object);
            Object obj = associateReference.nativeObject;
            if (!(obj instanceof TKBase)) {
                associateReference.close();
            } else {
                this.mHeaderViewList.add(((TKBase) obj).getView());
                this.mHeaderAssociateRefList.add(associateReference);
            }
        }
    }

    protected RecyclerView.LayoutManager createLayoutManager() {
        if (!TYPE_STAGGER.equals(this.mLayoutType)) {
            return new GridLayoutManager(getContext(), this.mSpanCount > 0 ? this.mSpanCount : 1, this.mDirection, false);
        }
        this.mAdapter.setNeedFixHeight(true);
        this.mAdapter.setSupportForFullSpan(true);
        this.mHeaderFooterAdapter.setIsStaggeredGrid(true);
        return new StaggeredGridLayoutManager(this.mSpanCount > 0 ? this.mSpanCount : 1, this.mDirection);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tachikoma.core.component.TKBase
    public b createViewInstance(Context context) {
        b bVar = new b(context);
        this.mRecyclerView = new NestedRecyclerView(context);
        this.mRecyclerView.setOverScrollMode(2);
        bVar.addView(this.mRecyclerView);
        initRefreshController(bVar);
        return bVar;
    }

    protected RecyclerView getRecyclerView() {
        return this.mRecyclerView;
    }

    public void hideLoadMore() {
        if (this.mLoadMorePresenter != null) {
            this.mLoadMorePresenter.hide();
        }
    }

    protected void initHeaderAndFooterView() {
        if (this.mHeaderFooterAdapter != null && this.mHeaderViewList != null && !this.mHeaderViewList.isEmpty()) {
            Iterator<View> it = this.mHeaderViewList.iterator();
            while (it.hasNext()) {
                this.mHeaderFooterAdapter.addHeaderView(it.next());
            }
        }
        if (this.mHeaderFooterAdapter == null || this.mFooterViewList == null || this.mFooterViewList.isEmpty()) {
            return;
        }
        Iterator<View> it2 = this.mFooterViewList.iterator();
        while (it2.hasNext()) {
            this.mHeaderFooterAdapter.addFooterView(it2.next());
        }
    }

    protected void initLoadMore(RecyclerHeaderFooterAdapter recyclerHeaderFooterAdapter) {
        if (this.mEnableLoadMore) {
            this.mLoadMorePresenter = new LoadMorePresenter(getRecyclerView(), new TKPageList(null) { // from class: com.tachikoma.core.component.listview.TKRecyclerView.3
                @Override // com.tachikoma.core.component.recyclerview.pagelist.TKPageList, com.tachikoma.core.component.recyclerview.export.ITKPageList
                public boolean hasMore() {
                    return TKRecyclerView.this.hasMore();
                }
            });
            this.mLoadMorePresenter.addFooterView((RefreshLayout) getView(), recyclerHeaderFooterAdapter);
            this.mLoadMorePresenter.registerOnScrollListener();
        }
    }

    protected void initRefreshControl() {
        Object obj = this.mV8AssociatedObject.get("refreshControl");
        try {
            if ((obj instanceof V8Object) && V8Proxy.isV8Valid((V8Object) obj)) {
                JSContext.V8AssociateReference associateReference = this.mTKJSContext.context().getAssociateReference((V8Object) obj);
                TKRefreshControl tKRefreshControl = (TKRefreshControl) associateReference.nativeObject;
                tKRefreshControl.setRefreshLayout((RefreshLayout) getView());
                tKRefreshControl.setAssociateObject((V8Object) obj);
                if (this.mRefreshListener != null) {
                    this.mRefreshListener.registerRefreshControl(tKRefreshControl);
                    if (this.mRefreshAssociateRef != null) {
                        this.mRefreshAssociateRef.close();
                    }
                    this.mRefreshAssociateRef = associateReference;
                } else {
                    associateReference.close();
                }
            }
        } finally {
            if (obj instanceof V8Value) {
                V8Proxy.release((V8Value) obj);
            }
        }
    }

    protected void initRefreshController(RefreshLayout refreshLayout) {
        this.mRefreshListener = new RefreshLayoutPresenter(refreshLayout);
        this.mRefreshListener.registerRefreshListener();
    }

    public void notifyHeaderViewChanged() {
        this.mHeaderFooterAdapter.markHeaderOrFooterDirty();
        this.mHeaderFooterAdapter.notifyDataSetChanged();
    }

    public void notifyItemRangeChanged(int i, int i2) {
        if (this.mAdapter != null) {
            this.mAdapter.notifyItemRangeChanged(i, i2);
        }
    }

    public void notifyItemRangeInserted(int i, int i2) {
        if (this.mAdapter != null) {
            this.mAdapter.notifyItemRangeInserted(i, i2);
        }
    }

    public void notifyItemRangeRemoved(int i, int i2) {
        if (this.mAdapter != null) {
            this.mAdapter.notifyItemRangeRemoved(i, i2);
        }
    }

    @Override // com.tachikoma.core.component.TKBase
    public void onAttach() {
        setAdapterIfNeed();
        getRecyclerView().setLayoutManager(createLayoutManager());
        getRecyclerView().addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.tachikoma.core.component.listview.TKRecyclerView.1
            public void onScrolled(final RecyclerView recyclerView, final int i, final int i2) {
                super.onScrolled(recyclerView, i, i2);
                TKRecyclerView.this.dispatchEvent("scroll", new TKEventProcess.EventInterface() { // from class: com.tachikoma.core.component.listview.TKRecyclerView.1.1
                    @Override // com.tachikoma.core.event.TKEventProcess.EventInterface
                    public void callBackEvent(IBaseEvent iBaseEvent) {
                        if (iBaseEvent instanceof TKScrollEvent) {
                            TKScrollEvent tKScrollEvent = (TKScrollEvent) iBaseEvent;
                            tKScrollEvent.setState(recyclerView.getScrollState());
                            tKScrollEvent.setDx(SizeUtil.px2DpAsFloat(i));
                            tKScrollEvent.setDy(SizeUtil.px2DpAsFloat(i2));
                            tKScrollEvent.setScrollOffsetX(SizeUtil.px2DpAsFloat(recyclerView.computeHorizontalScrollOffset()));
                            tKScrollEvent.setScrollOffsetY(SizeUtil.px2DpAsFloat(recyclerView.computeVerticalScrollOffset()));
                        }
                    }
                });
            }
        });
        if (this.mItemDecoration != null && this.mItemDecoration.isValid()) {
            getRecyclerView().addItemDecoration(new TKCustomItemDecoration(this.mItemDecoration));
        }
        initRefreshControl();
        registerScrollListener();
        initHeaderAndFooterView();
    }

    @Override // com.tachikoma.core.component.TKBase, com.tachikoma.core.common.ILifeCycle
    public void onDestroy() {
        super.onDestroy();
        if (this.mAdapter != null) {
            this.mAdapter.onDestroy();
        }
        JSContext.V8AssociateReference.closeAllReference(this.mHeaderAssociateRefList.iterator());
        this.mHeaderAssociateRefList.clear();
        JSContext.V8AssociateReference.closeAllReference(this.mFooterAssociateRefList.iterator());
        this.mFooterAssociateRefList.clear();
        if (this.mRefreshAssociateRef != null) {
            this.mRefreshAssociateRef.close();
            this.mRefreshAssociateRef = null;
        }
        if (this.mV8AssociatedObject != null) {
            this.mV8AssociatedObject.close();
            this.mV8AssociatedObject = null;
        }
    }

    protected void registerScrollListener() {
        if (this.mRecyclerView.getLayoutManager() == null) {
            return;
        }
        this.mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.tachikoma.core.component.listview.TKRecyclerView.2
            boolean isScrollDown = false;

            private int getLastVisibleItem(int[] iArr) {
                int i = -1;
                for (int i2 = 0; i2 < iArr.length; i2++) {
                    if (i2 == 0) {
                        i = iArr[i2];
                    } else if (iArr[i2] > i) {
                        i = iArr[i2];
                    }
                }
                return i;
            }

            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i) {
                int lastVisibleItem;
                super.onScrollStateChanged(recyclerView, i);
                int itemCount = TKRecyclerView.this.mAdapter.getItemCount();
                if (i == 0 && this.isScrollDown && TKRecyclerView.this.mOnEndReachedThreshold > 0) {
                    if (TKRecyclerView.this.mRecyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                        lastVisibleItem = TKRecyclerView.this.mRecyclerView.getLayoutManager().findLastCompletelyVisibleItemPosition();
                        if (lastVisibleItem == -1) {
                            lastVisibleItem = TKRecyclerView.this.mRecyclerView.getLayoutManager().findLastVisibleItemPosition();
                        }
                    } else {
                        lastVisibleItem = -1;
                    }
                    if (TKRecyclerView.this.mRecyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager) {
                        int[] iArrFindLastCompletelyVisibleItemPositions = TKRecyclerView.this.mRecyclerView.getLayoutManager().findLastCompletelyVisibleItemPositions((int[]) null);
                        int lastVisibleItem2 = getLastVisibleItem(iArrFindLastCompletelyVisibleItemPositions);
                        if (lastVisibleItem2 == -1) {
                            TKRecyclerView.this.mRecyclerView.getLayoutManager().findLastVisibleItemPositions(iArrFindLastCompletelyVisibleItemPositions);
                            lastVisibleItem = getLastVisibleItem(iArrFindLastCompletelyVisibleItemPositions);
                        } else {
                            lastVisibleItem = lastVisibleItem2;
                        }
                    }
                    if (lastVisibleItem == -1 || (itemCount - lastVisibleItem) - TKRecyclerView.this.mHeaderFooterAdapter.getHeaderCount() > TKRecyclerView.this.mOnEndReachedThreshold) {
                        return;
                    }
                    TKRecyclerView.this.onEndReached();
                }
            }

            public void onScrolled(@NonNull RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                this.isScrollDown = i2 >= 0 && Math.abs(i2) >= Math.abs(i);
            }
        });
    }

    public void reloadData() {
        if (this.mHeaderFooterAdapter != null) {
            this.mHeaderFooterAdapter.notifyDataSetChanged();
        }
    }

    public void scrollToPosition(int i) {
        getRecyclerView().scrollToPosition(i);
    }

    public void scrollToPositionWithOffset(int i, int i2) {
        if (getRecyclerView().getLayoutManager() instanceof LinearLayoutManager) {
            getRecyclerView().getLayoutManager().scrollToPositionWithOffset(i, i2);
        }
    }

    public void setAdapter(Object obj) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.mTKJSContext);
        arrayList.add(obj);
        this.mAdapter = new TKRecyclerAdapter(getContext(), arrayList);
        this.mHeaderFooterAdapter = new RecyclerHeaderFooterAdapter(this.mAdapter);
        this.mRecyclerView.setAdapter(this.mHeaderFooterAdapter);
        initLoadMore(this.mHeaderFooterAdapter);
    }

    public void setCanPullToRefresh(boolean z) {
        getView().setEnabled(z);
        if (this.mRecyclerView != null) {
            this.mRecyclerView.setCanPullToRefresh(z);
        }
    }

    public void setDirection(int i) {
        this.mDirection = i == 0 ? 0 : 1;
    }

    public void setEnableLoadMore(boolean z) {
        this.mEnableLoadMore = z;
    }

    public void setFixScrollConflictDirection(int i) {
        this.mRecyclerView.setFixScrollConflictDirection(i);
    }

    public void setLayoutType(String str) {
        this.mLayoutType = str;
    }

    public void setSpanCount(int i) {
        this.mSpanCount = i;
    }

    public void setWaterLayout(V8Object v8Object) {
        if (V8Proxy.isV8Valid(v8Object)) {
            this.mItemDecoration = new TKWaterLayoutConfiguration();
            this.mItemDecoration.edgePadding = SizeUtil.dp2Px(V8Proxy.getV8IntValueSafely(v8Object, "edgePadding", 0));
            this.mItemDecoration.centerPadding = SizeUtil.dp2Px(V8Proxy.getV8IntValueSafely(v8Object, "centerPadding", 0));
            this.mItemDecoration.rowPadding = SizeUtil.dp2Px(V8Proxy.getV8IntValueSafely(v8Object, "rowPadding", 0));
        }
    }

    public void showLoadMore() {
        if (this.mLoadMorePresenter != null) {
            this.mLoadMorePresenter.show();
        }
    }

    public void smoothScrollToPosition(int i) {
        getRecyclerView().smoothScrollToPosition(i);
    }
}
