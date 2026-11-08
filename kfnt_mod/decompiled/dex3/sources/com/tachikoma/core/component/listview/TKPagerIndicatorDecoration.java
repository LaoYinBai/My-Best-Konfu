package com.tachikoma.core.component.listview;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKPagerIndicatorDecoration extends RecyclerView.ItemDecoration {
    private final float mIndicatorHeight;
    private final float mIndicatorItemPadding;
    private final IIndicatorRender mIndicatorRender;
    private final float mIndicatorWidth;
    private final Interpolator mInterpolator = new AccelerateDecelerateInterpolator();
    private final Paint mPaint;

    public interface IIndicatorRender {
        void drawHighlights(float f, float f2, float f3, float f4, int i, int i2, Paint paint, Canvas canvas);

        void drawInactiveIndicators(float f, float f2, float f3, float f4, Paint paint, Canvas canvas);

        float getIndicatorHeight();

        float getIndicatorPadding();

        int getIndicatorTopMargin();

        float getIndicatorWidth();

        Paint getPaint();

        void setItemWidth(int i);

        void setTextSize(int i);
    }

    public TKPagerIndicatorDecoration(@NonNull IIndicatorRender iIndicatorRender) {
        this.mIndicatorRender = iIndicatorRender;
        this.mPaint = this.mIndicatorRender.getPaint();
        this.mIndicatorHeight = this.mIndicatorRender.getIndicatorHeight();
        this.mIndicatorWidth = this.mIndicatorRender.getIndicatorWidth();
        this.mIndicatorItemPadding = this.mIndicatorRender.getIndicatorPadding();
    }

    private void drawHighlights(Canvas canvas, float f, float f2, int i, float f3, int i2, int i3) {
        float f4 = this.mIndicatorWidth + this.mIndicatorItemPadding;
        float f5 = f + (i * f4);
        if (f3 == 0.0f) {
            this.mIndicatorRender.drawHighlights(f5, f2, f5 + this.mIndicatorWidth, f2, i2, i3, this.mPaint, canvas);
            return;
        }
        float f6 = this.mIndicatorWidth * f3;
        this.mIndicatorRender.drawHighlights(f5 + f6, f2, f5 + this.mIndicatorWidth, f2, i2, i3, this.mPaint, canvas);
        if (i < i2 - 1) {
            float f7 = f5 + f4;
            this.mIndicatorRender.drawHighlights(f7, f2, f7 + f6, f2, i2, i3, this.mPaint, canvas);
        }
    }

    private void drawInactiveIndicators(Canvas canvas, float f, float f2, int i) {
        float f3 = this.mIndicatorWidth + this.mIndicatorItemPadding;
        for (int i2 = 0; i2 < i; i2++) {
            this.mIndicatorRender.drawInactiveIndicators(f, f2, f + this.mIndicatorWidth, f2, this.mPaint, canvas);
            f += f3;
        }
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        rect.bottom = this.mIndicatorRender.getIndicatorTopMargin();
    }

    public void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        View viewFindViewByPosition;
        super.onDrawOver(canvas, recyclerView, state);
        if (recyclerView.getAdapter() == null || !(recyclerView.getLayoutManager() instanceof LinearLayoutManager)) {
            return;
        }
        int itemCount = recyclerView.getAdapter().getItemCount();
        float width = (recyclerView.getWidth() - ((this.mIndicatorWidth * itemCount) + (Math.max(0, itemCount - 1) * this.mIndicatorItemPadding))) / 2.0f;
        float height = recyclerView.getHeight() - (this.mIndicatorHeight / 2.0f);
        drawInactiveIndicators(canvas, width, height, itemCount);
        LinearLayoutManager layoutManager = recyclerView.getLayoutManager();
        int iFindFirstCompletelyVisibleItemPosition = layoutManager.findFirstCompletelyVisibleItemPosition();
        if (iFindFirstCompletelyVisibleItemPosition == -1) {
            iFindFirstCompletelyVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
        }
        int i = iFindFirstCompletelyVisibleItemPosition;
        if (i == -1 || (viewFindViewByPosition = layoutManager.findViewByPosition(i)) == null) {
            return;
        }
        drawHighlights(canvas, width, height, i, this.mInterpolator.getInterpolation((viewFindViewByPosition.getLeft() * (-1)) / viewFindViewByPosition.getWidth()), itemCount, i);
    }
}
