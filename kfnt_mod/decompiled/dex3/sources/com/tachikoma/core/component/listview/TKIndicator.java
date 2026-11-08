package com.tachikoma.core.component.listview;

import android.content.Context;
import android.view.View;
import com.tachikoma.annotation.TK_EXPORT_CLASS;
import com.tachikoma.core.component.TKBase;
import java.util.List;

@TK_EXPORT_CLASS
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKIndicator extends TKBase<View> {
    private String mActiveColor;
    private String mInActiveColor;
    private int mMarginTop;
    private int mPadding;
    private int mSize;
    private int mTextSize;
    private String mType;

    public TKIndicator(Context context, List<Object> list) {
        super(context, list);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.tachikoma.core.component.listview.TKPagerIndicatorDecoration.IIndicatorRender create() {
        /*
            r7 = this;
            java.lang.String r0 = r7.mType
            int r1 = r0.hashCode()
            r2 = -1360216880(0xffffffffaeecbcd0, float:-1.0765577E-10)
            if (r1 == r2) goto L2a
            r2 = -1102672091(0xffffffffbe468f25, float:-0.19390543)
            if (r1 == r2) goto L20
            r2 = -1034364087(0xffffffffc258db49, float:-54.214146)
            if (r1 == r2) goto L16
            goto L34
        L16:
            java.lang.String r1 = "number"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L34
            r0 = 1
            goto L35
        L20:
            java.lang.String r1 = "linear"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L34
            r0 = 0
            goto L35
        L2a:
            java.lang.String r1 = "circle"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L34
            r0 = 2
            goto L35
        L34:
            r0 = -1
        L35:
            switch(r0) {
                case 0: goto L64;
                case 1: goto L53;
                default: goto L38;
            }
        L38:
            com.tachikoma.core.component.listview.TKCircleIndicator r0 = new com.tachikoma.core.component.listview.TKCircleIndicator
            int r2 = r7.mSize
            int r1 = r7.mMarginTop
            float r3 = (float) r1
            int r1 = r7.mPadding
            float r4 = (float) r1
            java.lang.String r1 = r7.mInActiveColor
            int r5 = android.graphics.Color.parseColor(r1)
            java.lang.String r1 = r7.mActiveColor
            int r6 = android.graphics.Color.parseColor(r1)
            r1 = r0
            r1.<init>(r2, r3, r4, r5, r6)
            goto L69
        L53:
            com.tachikoma.core.component.listview.TKNumberIndicator r0 = new com.tachikoma.core.component.listview.TKNumberIndicator
            java.lang.String r1 = r7.mActiveColor
            int r1 = android.graphics.Color.parseColor(r1)
            r0.<init>(r1)
            int r1 = r7.mTextSize
            r0.setTextSize(r1)
            goto L69
        L64:
            com.tachikoma.core.component.listview.TKLineIndicator r0 = new com.tachikoma.core.component.listview.TKLineIndicator
            r0.<init>()
        L69:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tachikoma.core.component.listview.TKIndicator.create():com.tachikoma.core.component.listview.TKPagerIndicatorDecoration$IIndicatorRender");
    }

    @Override // com.tachikoma.core.component.TKBase
    protected View createViewInstance(Context context) {
        return null;
    }

    public void setActiveColor(String str) {
        this.mActiveColor = str;
    }

    public void setInActiveColor(String str) {
        this.mInActiveColor = str;
    }

    public void setMarginTop(int i) {
        this.mMarginTop = i;
    }

    public void setPadding(int i) {
        this.mPadding = i;
    }

    public void setSize(int i) {
        this.mSize = i;
    }

    public void setTextSize(int i) {
        this.mTextSize = i;
    }

    public void setType(String str) {
        this.mType = str;
    }
}
