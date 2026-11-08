package com.tachikoma.core.component.recyclerview;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.ss.union.game.sdk.feedback.picture.select.d;
import com.tachikoma.core.component.listview.TKRecyclerAdapter;
import com.tachikoma.core.yoga.layout.YogaLayout;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class RecyclerHeaderFooterAdapter extends RecyclerView.Adapter {
    private static final int BASE_FOOTER_VIEW_TYPE = -2048;
    private static final int BASE_HEADER_VIEW_TYPE = -1024;
    private RecyclerView.Adapter mAdapter;
    private final RecyclerView.AdapterDataObserver mAdapterDataObserverProxy;
    private RecyclerView.Adapter mFooterAdapter;
    private final HeaderFooterViewGroup mFooterGroup;
    private RecyclerView.AdapterDataObserver mFooterObserverProxy;
    private boolean mForceNotifyAll;
    private RecyclerView.Adapter mHeaderAdapter;
    private final HeaderFooterViewGroup mHeaderGroup;
    private RecyclerView.AdapterDataObserver mHeaderObserverProxy;
    private boolean mHeaderOrFooterDirty;
    private boolean mIsOverrideLayoutParams;
    private boolean mIsStaggeredGrid;
    private int mLastAdapterContentCount;
    private int mMaxFooterViewType;
    private int mMaxHeaderViewType;
    private boolean mSmoothNotifyChange;

    public static class HeaderFooterViewGroup {
        public int mIndex;
        public final SparseArray<View> mViews;

        HeaderFooterViewGroup() {
            this(null);
        }

        HeaderFooterViewGroup(List<View> list) {
            this.mViews = new SparseArray<>();
            this.mIndex = 0;
            if (list != null) {
                for (View view : list) {
                    SparseArray<View> sparseArray = this.mViews;
                    int i = this.mIndex;
                    this.mIndex = i + 1;
                    sparseArray.put(i, view);
                }
            }
        }

        boolean addView(View view) {
            if (containsView(view)) {
                return false;
            }
            SparseArray<View> sparseArray = this.mViews;
            int i = this.mIndex;
            this.mIndex = i + 1;
            sparseArray.put(i, view);
            return true;
        }

        boolean containsView(View view) {
            return this.mViews.indexOfValue(view) >= 0;
        }

        View getViewAtPos(int i) {
            if (i < 0 || i >= this.mViews.size()) {
                return null;
            }
            return this.mViews.valueAt(i);
        }

        int getViewTypeAtPos(int i) {
            if (i < 0 || i >= this.mViews.size()) {
                return -1;
            }
            return this.mViews.keyAt(i);
        }

        View getViewWithType(int i) {
            return this.mViews.get(i);
        }

        int maxIndex() {
            return this.mIndex;
        }

        boolean removeView(View view) {
            int iIndexOfValue = this.mViews.indexOfValue(view);
            if (iIndexOfValue < 0) {
                return false;
            }
            this.mViews.removeAt(iIndexOfValue);
            return true;
        }

        int viewCount() {
            return this.mViews.size();
        }
    }

    public RecyclerHeaderFooterAdapter(RecyclerView.Adapter adapter) {
        this(adapter, null, null);
    }

    public RecyclerHeaderFooterAdapter(RecyclerView.Adapter adapter, List<View> list, List<View> list2) {
        this.mMaxFooterViewType = BASE_FOOTER_VIEW_TYPE;
        this.mMaxHeaderViewType = BASE_HEADER_VIEW_TYPE;
        this.mLastAdapterContentCount = -1;
        this.mForceNotifyAll = false;
        this.mIsOverrideLayoutParams = true;
        this.mHeaderOrFooterDirty = false;
        this.mAdapter = adapter;
        this.mHeaderGroup = new HeaderFooterViewGroup(list);
        this.mFooterGroup = new HeaderFooterViewGroup(list2);
        this.mAdapterDataObserverProxy = new RecyclerView.AdapterDataObserver() { // from class: com.tachikoma.core.component.recyclerview.RecyclerHeaderFooterAdapter.1
            private void smoothChange(int i) {
                try {
                    int i2 = RecyclerHeaderFooterAdapter.this.mLastAdapterContentCount;
                    int headerCount = RecyclerHeaderFooterAdapter.this.getHeaderCount();
                    if (i2 == -1) {
                        RecyclerHeaderFooterAdapter.this.notifyDataSetChanged();
                    } else if (i == i2) {
                        RecyclerHeaderFooterAdapter.this.notifyItemRangeChanged(headerCount, i);
                    } else if (i > i2) {
                        RecyclerHeaderFooterAdapter.this.notifyItemRangeChanged(headerCount, i2);
                        RecyclerHeaderFooterAdapter.this.notifyItemRangeInserted(headerCount + i2, i - i2);
                    } else {
                        RecyclerHeaderFooterAdapter.this.notifyItemRangeChanged(headerCount, i);
                        RecyclerHeaderFooterAdapter.this.notifyItemRangeRemoved(headerCount + i, i2 - i);
                    }
                } catch (Exception unused) {
                }
                RecyclerHeaderFooterAdapter.this.mLastAdapterContentCount = i;
            }

            public void onChanged() {
                if (RecyclerHeaderFooterAdapter.this.mForceNotifyAll) {
                    RecyclerHeaderFooterAdapter.this.notifyDataSetChanged();
                    return;
                }
                if (RecyclerHeaderFooterAdapter.this.mSmoothNotifyChange) {
                    smoothChange(RecyclerHeaderFooterAdapter.this.mAdapter.getItemCount());
                    return;
                }
                int itemCount = RecyclerHeaderFooterAdapter.this.mAdapter.getItemCount();
                try {
                    if (RecyclerHeaderFooterAdapter.this.mLastAdapterContentCount == -1 || (itemCount != 0 && itemCount == RecyclerHeaderFooterAdapter.this.mLastAdapterContentCount)) {
                        RecyclerHeaderFooterAdapter.this.notifyItemRangeChanged(RecyclerHeaderFooterAdapter.this.getHeaderCount(), itemCount);
                    } else {
                        RecyclerHeaderFooterAdapter.this.notifyDataSetChanged();
                    }
                } catch (Exception unused) {
                }
                RecyclerHeaderFooterAdapter.this.mLastAdapterContentCount = itemCount;
            }

            public void onItemRangeChanged(int i, int i2) {
                RecyclerHeaderFooterAdapter.this.mLastAdapterContentCount = RecyclerHeaderFooterAdapter.this.mAdapter.getItemCount();
                try {
                    RecyclerHeaderFooterAdapter.this.notifyItemRangeChanged(i + RecyclerHeaderFooterAdapter.this.getHeaderCount(), i2);
                } catch (Exception unused) {
                }
            }

            public void onItemRangeChanged(int i, int i2, Object obj) {
                RecyclerHeaderFooterAdapter.this.mLastAdapterContentCount = RecyclerHeaderFooterAdapter.this.mAdapter.getItemCount();
                try {
                    RecyclerHeaderFooterAdapter.this.notifyItemRangeChanged(i + RecyclerHeaderFooterAdapter.this.getHeaderCount(), i2, obj);
                } catch (Exception unused) {
                }
            }

            public void onItemRangeInserted(int i, int i2) {
                RecyclerHeaderFooterAdapter.this.mLastAdapterContentCount = RecyclerHeaderFooterAdapter.this.mAdapter.getItemCount();
                try {
                    RecyclerHeaderFooterAdapter.this.notifyItemRangeInserted(i + RecyclerHeaderFooterAdapter.this.getHeaderCount(), i2);
                } catch (Exception unused) {
                }
            }

            public void onItemRangeMoved(int i, int i2, int i3) {
                try {
                    RecyclerHeaderFooterAdapter.this.notifyItemMoved(i + RecyclerHeaderFooterAdapter.this.getHeaderCount(), i2 + RecyclerHeaderFooterAdapter.this.getHeaderCount());
                } catch (Exception unused) {
                }
            }

            public void onItemRangeRemoved(int i, int i2) {
                try {
                    RecyclerHeaderFooterAdapter.this.notifyItemRangeRemoved(i + RecyclerHeaderFooterAdapter.this.getHeaderCount(), i2);
                } catch (Exception unused) {
                }
            }
        };
        this.mHeaderObserverProxy = this.mAdapterDataObserverProxy;
        this.mFooterObserverProxy = this.mAdapterDataObserverProxy;
        this.mAdapter.registerAdapterDataObserver(this.mAdapterDataObserverProxy);
    }

    private void adjustHeaderFooterView(RecyclerView.ViewHolder viewHolder, boolean z) {
        StaggeredGridLayoutManager.LayoutParams layoutParams = viewHolder.itemView.getLayoutParams();
        if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            layoutParams.setFullSpan(z);
        }
    }

    private void adjustHeaderFooterView(RecyclerView recyclerView) {
        GridLayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.tachikoma.core.component.recyclerview.RecyclerHeaderFooterAdapter.4
                public int getSpanSize(int i) {
                    if (RecyclerHeaderFooterAdapter.this.isHeaderPosition(i) || RecyclerHeaderFooterAdapter.this.isFooterPosition(i)) {
                        return gridLayoutManager.getSpanCount();
                    }
                    return 1;
                }
            });
        }
    }

    private RecyclerView.ViewHolder createHeaderFooterViewHolder(View view) {
        StaggeredGridLayoutManager.LayoutParams layoutParams;
        ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
        int i = layoutParams2 == null ? -1 : layoutParams2.width;
        int i2 = layoutParams2 == null ? -2 : layoutParams2.height;
        if (this.mIsOverrideLayoutParams) {
            if (this.mIsStaggeredGrid) {
                layoutParams = new StaggeredGridLayoutManager.LayoutParams(i, i2);
                layoutParams.setFullSpan(true);
            } else {
                layoutParams = new RecyclerView.LayoutParams(i, i2);
            }
            view.setLayoutParams(layoutParams);
        }
        if (!(view instanceof YogaLayout)) {
            return new RecyclerView.ViewHolder(view) { // from class: com.tachikoma.core.component.recyclerview.RecyclerHeaderFooterAdapter.2
            };
        }
        YogaLayout yogaLayout = (YogaLayout) view;
        return new HeaderFooterViewHolder(view, yogaLayout.getYogaNode().getWidth(), yogaLayout.getYogaNode().getHeight());
    }

    private void notifyDataSetChangedCatchException() {
        try {
            notifyDataSetChanged();
        } catch (Exception unused) {
        }
    }

    public void addFooterAdapter(RecyclerView.Adapter adapter) {
        addFooterAdapter(adapter, this.mAdapterDataObserverProxy);
    }

    public void addFooterAdapter(@NonNull RecyclerView.Adapter adapter, @Nullable RecyclerView.AdapterDataObserver adapterDataObserver) {
        this.mFooterAdapter = adapter;
        this.mFooterObserverProxy = adapterDataObserver == null ? new RecyclerFooterObserver(this) : this.mAdapterDataObserverProxy;
        try {
            this.mFooterAdapter.registerAdapterDataObserver(this.mFooterObserverProxy);
        } catch (Exception unused) {
        }
        notifyDataSetChanged();
    }

    public void addFooterView(View view) {
        addFooterView(view, null);
    }

    public void addFooterView(View view, @Nullable ViewGroup.LayoutParams layoutParams) {
        if (view == null) {
            throw new IllegalArgumentException("the view to add must not be null!");
        }
        if ((view.getParent() instanceof ViewGroup) && view.getParent() != this.mFooterGroup) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        if (layoutParams != null) {
            view.setLayoutParams(layoutParams);
        }
        if (this.mFooterGroup.addView(view)) {
            notifyDataSetChangedCatchException();
        }
    }

    public void addHeaderAdapter(RecyclerView.Adapter adapter) {
        addHeaderAdapter(adapter, this.mAdapterDataObserverProxy);
    }

    public void addHeaderAdapter(@NonNull RecyclerView.Adapter adapter, @Nullable RecyclerView.AdapterDataObserver adapterDataObserver) {
        this.mHeaderAdapter = adapter;
        this.mHeaderObserverProxy = adapterDataObserver == null ? new RecyclerHeaderObserver(this) : this.mAdapterDataObserverProxy;
        try {
            this.mHeaderAdapter.registerAdapterDataObserver(this.mHeaderObserverProxy);
        } catch (Exception unused) {
        }
        notifyDataSetChanged();
    }

    public void addHeaderView(View view) {
        addHeaderView(view, null);
    }

    public void addHeaderView(View view, @Nullable ViewGroup.LayoutParams layoutParams) {
        if (view == null) {
            throw new IllegalArgumentException("the view to add must not be null");
        }
        if (layoutParams != null) {
            view.setLayoutParams(layoutParams);
        }
        if (this.mHeaderGroup.addView(view)) {
            notifyDataSetChangedCatchException();
        }
    }

    public int addHeaderViewWithoutNotify(View view) {
        if (view == null) {
            throw new IllegalArgumentException("the view to add must not be null");
        }
        if (this.mHeaderGroup.addView(view)) {
            return getHeaderCount() - 1;
        }
        return -1;
    }

    public void adjustSpanSizeForHeaderFooter(RecyclerView recyclerView) {
        adjustSpanSizeForHeaderFooter(recyclerView, null);
    }

    public void adjustSpanSizeForHeaderFooter(RecyclerView recyclerView, final GridLayoutManager.SpanSizeLookup spanSizeLookup) {
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            final GridLayoutManager layoutManager = recyclerView.getLayoutManager();
            layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.tachikoma.core.component.recyclerview.RecyclerHeaderFooterAdapter.3
                public int getSpanSize(int i) {
                    if (RecyclerHeaderFooterAdapter.this.isHeaderPosition(i) || RecyclerHeaderFooterAdapter.this.isFooterPosition(i)) {
                        return layoutManager.getSpanCount();
                    }
                    if (spanSizeLookup != null) {
                        return spanSizeLookup.getSpanSize(i);
                    }
                    return 1;
                }
            });
        }
        if (recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager) {
            this.mIsStaggeredGrid = true;
        }
    }

    public boolean containsFooterView(View view) {
        return this.mFooterGroup.containsView(view);
    }

    public boolean containsHeaderView(View view) {
        return this.mHeaderGroup.containsView(view);
    }

    public RecyclerView.Adapter getAdapter() {
        return this.mAdapter;
    }

    public int getBodyCount() {
        return this.mAdapter.getItemCount();
    }

    public RecyclerView.Adapter getFooterAdapter() {
        return this.mFooterAdapter;
    }

    public int getFooterCount() {
        return this.mFooterAdapter != null ? this.mFooterAdapter.getItemCount() : this.mFooterGroup.viewCount();
    }

    public RecyclerView.Adapter getHeaderAdapter() {
        return this.mHeaderAdapter;
    }

    public int getHeaderCount() {
        return this.mHeaderAdapter != null ? this.mHeaderAdapter.getItemCount() : this.mHeaderGroup.viewCount();
    }

    public HeaderFooterViewGroup getHeaderGroup() {
        return this.mHeaderGroup;
    }

    public int getItemCount() {
        return getFooterCount() + getHeaderCount() + this.mAdapter.getItemCount();
    }

    public long getItemId(int i) {
        if (isHeaderPosition(i)) {
            return this.mHeaderAdapter != null ? this.mHeaderAdapter.getItemId(i) : getItemViewType(i);
        }
        if (!isFooterPosition(i)) {
            return this.mAdapter.getItemId(i - getHeaderCount());
        }
        if (this.mFooterAdapter != null) {
            return this.mFooterAdapter.getItemId((i - getHeaderCount()) - this.mAdapter.getItemCount());
        }
        return getItemViewType(i);
    }

    public int getItemViewType(int i) {
        if (isHeaderPosition(i)) {
            int itemViewType = (this.mHeaderAdapter != null ? this.mHeaderAdapter.getItemViewType(i) : this.mHeaderGroup.getViewTypeAtPos(i)) + BASE_HEADER_VIEW_TYPE;
            this.mMaxHeaderViewType = Math.max(itemViewType, this.mMaxHeaderViewType);
            return itemViewType;
        }
        if (!isFooterPosition(i)) {
            return this.mAdapter.getItemViewType(i - getHeaderCount());
        }
        int itemCount = (i - this.mAdapter.getItemCount()) - getHeaderCount();
        int itemViewType2 = (this.mFooterAdapter != null ? this.mFooterAdapter.getItemViewType(itemCount) : this.mFooterGroup.getViewTypeAtPos(itemCount)) + BASE_FOOTER_VIEW_TYPE;
        this.mMaxFooterViewType = Math.max(itemViewType2, this.mMaxFooterViewType);
        return itemViewType2;
    }

    public boolean isEmpty() {
        return this.mAdapter == null || this.mAdapter.getItemCount() == 0;
    }

    public boolean isFooterPosition(int i) {
        return i >= getHeaderCount() + this.mAdapter.getItemCount();
    }

    public boolean isFooterViewType(int i) {
        return i >= BASE_FOOTER_VIEW_TYPE && i <= this.mMaxFooterViewType;
    }

    public boolean isHeaderPosition(int i) {
        return i < getHeaderCount();
    }

    public boolean isHeaderViewType(int i) {
        return i >= BASE_HEADER_VIEW_TYPE && i <= this.mMaxHeaderViewType;
    }

    public int map2FooterViewType(int i) {
        return i + 2048;
    }

    public int map2HeaderViewType(int i) {
        return i + d.d;
    }

    public void markHeaderOrFooterDirty() {
        this.mHeaderOrFooterDirty = true;
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        if (this.mAdapter.hasObservers()) {
            this.mAdapter.unregisterAdapterDataObserver(this.mAdapterDataObserverProxy);
        }
        this.mAdapter.registerAdapterDataObserver(this.mAdapterDataObserverProxy);
        this.mAdapter.onAttachedToRecyclerView(recyclerView);
        if (this.mHeaderAdapter != null) {
            this.mHeaderAdapter.onAttachedToRecyclerView(recyclerView);
            this.mHeaderAdapter.unregisterAdapterDataObserver(this.mHeaderObserverProxy);
            this.mHeaderAdapter.registerAdapterDataObserver(this.mHeaderObserverProxy);
        }
        if (this.mFooterAdapter != null) {
            this.mFooterAdapter.onAttachedToRecyclerView(recyclerView);
            this.mFooterAdapter.unregisterAdapterDataObserver(this.mFooterObserverProxy);
            this.mFooterAdapter.registerAdapterDataObserver(this.mFooterObserverProxy);
        }
        adjustHeaderFooterView(recyclerView);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        RecyclerView.Adapter adapter;
        if (i >= getHeaderCount() && i < getHeaderCount() + this.mAdapter.getItemCount()) {
            this.mAdapter.onBindViewHolder(viewHolder, i - getHeaderCount());
            return;
        }
        if (i >= getHeaderCount() || this.mHeaderAdapter == null) {
            if (i >= getHeaderCount() + this.mAdapter.getItemCount() && this.mFooterAdapter != null) {
                adapter = this.mFooterAdapter;
                i = (i - getHeaderCount()) - this.mAdapter.getItemCount();
            }
            if ((viewHolder instanceof HeaderFooterViewHolder) || !this.mHeaderOrFooterDirty) {
            }
            ((HeaderFooterViewHolder) viewHolder).maybeReset();
            this.mHeaderOrFooterDirty = false;
            return;
        }
        adapter = this.mHeaderAdapter;
        adapter.onBindViewHolder(viewHolder, i);
        if (viewHolder instanceof HeaderFooterViewHolder) {
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List list) {
        RecyclerView.Adapter adapter;
        if (list.isEmpty()) {
            onBindViewHolder(viewHolder, i);
            return;
        }
        if (i >= getHeaderCount() && i < getHeaderCount() + this.mAdapter.getItemCount()) {
            this.mAdapter.onBindViewHolder(viewHolder, i - getHeaderCount(), list);
            return;
        }
        if (i < getHeaderCount() && this.mHeaderAdapter != null) {
            adapter = this.mHeaderAdapter;
        } else {
            if (i < getHeaderCount() + this.mAdapter.getItemCount() || this.mFooterAdapter == null) {
                return;
            }
            adapter = this.mFooterAdapter;
            i = (i - getHeaderCount()) - this.mAdapter.getItemCount();
        }
        adapter.onBindViewHolder(viewHolder, i, list);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        RecyclerView.Adapter adapter;
        if (isHeaderViewType(i)) {
            i = map2HeaderViewType(i);
            if (this.mHeaderAdapter == null) {
                View viewWithType = this.mHeaderGroup.getViewWithType(i);
                if (viewWithType != null && (viewWithType.getParent() instanceof ViewGroup)) {
                    ((ViewGroup) viewWithType.getParent()).removeView(viewWithType);
                }
                return createHeaderFooterViewHolder(viewWithType);
            }
            adapter = this.mHeaderAdapter;
        } else if (isFooterViewType(i)) {
            i = map2FooterViewType(i);
            if (this.mFooterAdapter == null) {
                View viewWithType2 = this.mFooterGroup.getViewWithType(i);
                if (viewWithType2 != null && (viewWithType2.getParent() instanceof ViewGroup)) {
                    ((ViewGroup) viewWithType2.getParent()).removeView(viewWithType2);
                }
                return createHeaderFooterViewHolder(viewWithType2);
            }
            adapter = this.mFooterAdapter;
        } else {
            adapter = this.mAdapter;
        }
        return adapter.onCreateViewHolder(viewGroup, i);
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        if (this.mAdapter.hasObservers()) {
            this.mAdapter.unregisterAdapterDataObserver(this.mAdapterDataObserverProxy);
        }
        this.mAdapter.onDetachedFromRecyclerView(recyclerView);
        if (this.mHeaderAdapter != null) {
            this.mHeaderAdapter.onDetachedFromRecyclerView(recyclerView);
            this.mHeaderAdapter.unregisterAdapterDataObserver(this.mHeaderObserverProxy);
        }
        if (this.mFooterAdapter != null) {
            this.mFooterAdapter.onDetachedFromRecyclerView(recyclerView);
            this.mFooterAdapter.unregisterAdapterDataObserver(this.mFooterObserverProxy);
        }
    }

    public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
        RecyclerView.Adapter adapter;
        super.onViewAttachedToWindow(viewHolder);
        int itemViewType = viewHolder.getItemViewType();
        if (isHeaderViewType(itemViewType)) {
            if (this.mHeaderAdapter != null) {
                adapter = this.mHeaderAdapter;
                adapter.onViewAttachedToWindow(viewHolder);
                return;
            }
            adjustHeaderFooterView(viewHolder, true);
        }
        if (!isFooterViewType(itemViewType)) {
            this.mAdapter.onViewAttachedToWindow(viewHolder);
            adjustHeaderFooterView(viewHolder, this.mAdapter instanceof TKRecyclerAdapter ? ((TKRecyclerAdapter) this.mAdapter).isFullWidgetForView(viewHolder.getLayoutPosition() - getHeaderCount()) : false);
            return;
        }
        if (this.mFooterAdapter != null) {
            adapter = this.mFooterAdapter;
            adapter.onViewAttachedToWindow(viewHolder);
            return;
        }
        adjustHeaderFooterView(viewHolder, true);
    }

    public void onViewDetachedFromWindow(RecyclerView.ViewHolder viewHolder) {
        RecyclerView.Adapter adapter;
        super.onViewDetachedFromWindow(viewHolder);
        int itemViewType = viewHolder.getItemViewType();
        if (isHeaderViewType(itemViewType)) {
            if (this.mHeaderAdapter == null) {
                return;
            } else {
                adapter = this.mHeaderAdapter;
            }
        } else if (!isFooterViewType(itemViewType)) {
            adapter = this.mAdapter;
        } else if (this.mFooterAdapter == null) {
            return;
        } else {
            adapter = this.mFooterAdapter;
        }
        adapter.onViewDetachedFromWindow(viewHolder);
    }

    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver adapterDataObserver) {
        super.registerAdapterDataObserver(adapterDataObserver);
    }

    public boolean removeFooterView(View view) {
        boolean zRemoveView = this.mFooterGroup.removeView(view);
        if (zRemoveView) {
            notifyDataSetChangedCatchException();
        }
        return zRemoveView;
    }

    public boolean removeFooterView(View view, boolean z) {
        boolean zRemoveView = this.mFooterGroup.removeView(view);
        if (zRemoveView && z) {
            notifyDataSetChangedCatchException();
        }
        return zRemoveView;
    }

    public boolean removeHeaderView(View view) {
        boolean zRemoveView = this.mHeaderGroup.removeView(view);
        if (zRemoveView) {
            notifyDataSetChangedCatchException();
        }
        return zRemoveView;
    }

    public void setFootersVisibility(boolean z) {
        for (int i = 0; i < this.mFooterGroup.viewCount(); i++) {
            this.mFooterGroup.getViewAtPos(i).setVisibility(z ? 0 : 8);
        }
        try {
            notifyItemRangeChanged(getHeaderCount() + this.mAdapter.getItemCount(), getFooterCount());
        } catch (Exception unused) {
        }
    }

    public void setForceNotifyAll(boolean z) {
        this.mForceNotifyAll = z;
    }

    public void setHeadersVisibility(boolean z) {
        for (int i = 0; i < this.mHeaderGroup.viewCount(); i++) {
            this.mHeaderGroup.getViewAtPos(i).setVisibility(z ? 0 : 8);
        }
        try {
            notifyItemRangeChanged(0, getHeaderCount());
        } catch (Exception unused) {
        }
    }

    public void setIsStaggeredGrid(boolean z) {
        this.mIsStaggeredGrid = z;
    }

    public void setOverrideLayoutParams(boolean z) {
        this.mIsOverrideLayoutParams = z;
    }

    public void setSmoothNotifyChange(boolean z) {
        this.mSmoothNotifyChange = z;
    }

    public String toString() {
        return "RecyclerHeaderFooterAdapter{mAdapter=" + this.mAdapter + ", mHeaderAdapter=" + this.mHeaderAdapter + ", mFooterAdapter=" + this.mFooterAdapter + '}';
    }

    public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver adapterDataObserver) {
        super.unregisterAdapterDataObserver(adapterDataObserver);
    }

    public void updateAdapter(RecyclerView.Adapter adapter) {
        this.mAdapter = adapter;
        try {
            this.mAdapter.registerAdapterDataObserver(this.mAdapterDataObserverProxy);
        } catch (Exception unused) {
        }
    }
}
