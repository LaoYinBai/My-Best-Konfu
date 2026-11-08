package com.kwai.library.widget.refresh;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.LayoutRes;
import com.kwad.sdk.R;
import com.kwai.library.widget.refresh.RefreshLayout;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b extends RefreshLayout {

    @LayoutRes
    private int mRefreshLayout;

    public b(Context context) {
        super(context);
    }

    public b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public View getRefreshView() {
        return this.mRefreshView;
    }

    @Override // com.kwai.library.widget.refresh.RefreshLayout
    protected c onCreateDragDistanceConvert() {
        return new a(getContext());
    }

    @Override // com.kwai.library.widget.refresh.RefreshLayout
    protected RefreshLayout.a onCreateLayoutParams(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.CustomRefreshLayout);
        int dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.CustomRefreshLayout_refreshViewWidth, this.mRefreshViewSize);
        int dimensionPixelOffset2 = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.CustomRefreshLayout_refreshViewWidth, this.mRefreshViewSize);
        typedArrayObtainStyledAttributes.recycle();
        return new RefreshLayout.a(dimensionPixelOffset, dimensionPixelOffset2);
    }

    @Override // com.kwai.library.widget.refresh.RefreshLayout
    protected View onCreateRefreshView(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.CustomRefreshLayout);
        this.mRefreshLayout = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CustomRefreshLayout_refreshLayout, 0);
        typedArrayObtainStyledAttributes.recycle();
        int iA = g.a(getContext(), 22.5f);
        View kwaiRefreshView = this.mRefreshLayout == 0 ? new KwaiRefreshView(getContext()) : g.a(getContext(), this.mRefreshLayout);
        kwaiRefreshView.setPadding(iA, iA, iA, iA);
        return kwaiRefreshView;
    }

    public void setRefreshStatus(f fVar) {
        this.mRefreshStatus = fVar;
    }

    public void setRefreshView(View view) {
        this.mRefreshView = view;
    }
}
