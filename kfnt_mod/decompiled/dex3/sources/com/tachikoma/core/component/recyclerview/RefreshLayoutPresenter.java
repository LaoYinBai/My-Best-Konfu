package com.tachikoma.core.component.recyclerview;

import com.kwai.library.widget.refresh.RefreshLayout;
import com.tachikoma.core.component.recyclerview.export.TKRefreshControl;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class RefreshLayoutPresenter {
    private TKRefreshControl mRefreshControl;
    private RefreshLayout mRefreshLayout;

    public RefreshLayoutPresenter(RefreshLayout refreshLayout) {
        this.mRefreshLayout = refreshLayout;
    }

    public void registerRefreshControl(TKRefreshControl tKRefreshControl) {
        this.mRefreshControl = tKRefreshControl;
    }

    public void registerRefreshListener() {
        this.mRefreshLayout.setOnRefreshListener(new RefreshLayout.b() { // from class: com.tachikoma.core.component.recyclerview.RefreshLayoutPresenter.1
            public void onRefresh() {
                RefreshLayoutPresenter.this.mRefreshControl.onRefresh();
            }
        });
    }
}
