package com.tachikoma.core.component.recyclerview;

import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.kwai.library.widget.refresh.RefreshLayout;
import com.kwai.library.widget.refresh.e;
import com.tachikoma.core.component.recyclerview.export.ITKPageList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LoadMorePresenter {
    private e mLoadingView;
    private final ITKPageList mPageList;
    private final RecyclerView mRecyclerView;

    public LoadMorePresenter(RecyclerView recyclerView, ITKPageList iTKPageList) {
        this.mRecyclerView = recyclerView;
        this.mPageList = iTKPageList;
    }

    public void addFooterView(RefreshLayout refreshLayout, RecyclerHeaderFooterAdapter recyclerHeaderFooterAdapter) {
        e eVar = new e(refreshLayout.getContext(), (AttributeSet) null);
        eVar.setVisibility(4);
        this.mLoadingView = eVar;
        LinearLayout linearLayout = new LinearLayout(refreshLayout.getContext());
        linearLayout.addView((View) this.mLoadingView, -1, -2);
        recyclerHeaderFooterAdapter.addFooterView(linearLayout);
    }

    public void hide() {
        this.mLoadingView.a(false, (CharSequence) null);
    }

    public void registerOnScrollListener() {
        this.mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.tachikoma.core.component.recyclerview.LoadMorePresenter.1
            boolean isReadyLoadMore(ITKPageList iTKPageList) {
                return iTKPageList != null && iTKPageList.hasMore();
            }

            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                if (i == 1) {
                    tryToLoadMore(recyclerView);
                }
            }

            public void onScrolled(@NonNull RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                if (i2 != 0) {
                    tryToLoadMore(recyclerView);
                }
            }

            void tryToLoadMore(RecyclerView recyclerView) {
                if (recyclerView.getChildCount() <= 0 || !isReadyLoadMore(LoadMorePresenter.this.mPageList) || recyclerView.canScrollVertically(1)) {
                    return;
                }
                LoadMorePresenter.this.show();
                LoadMorePresenter.this.mPageList.load();
            }
        });
    }

    public void show() {
        this.mLoadingView.a(true, (CharSequence) null);
    }
}
