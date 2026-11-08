package com.mbridge.msdk.nativex.view;

import android.content.Context;
import android.view.View;
import androidx.viewpager.widget.ViewPager;
import com.mbridge.msdk.nativex.adapter.RollingAdapter;
import com.mbridge.msdk.nativex.listener.RollingPagerListenrt;
import com.mbridge.msdk.nativex.view.MBNativeRollView;
import com.mbridge.msdk.out.Frame;
import com.mbridge.msdk.out.NativeListener;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class RollingBCView extends ViewPager {
    private boolean a;
    private RollingPagerListenrt b;
    private NativeListener.FilpListener c;

    public void setFilpListening(NativeListener.FilpListener filpListener) {
        this.c = filpListener;
    }

    public RollingBCView(Context context) {
        super(context);
        this.a = true;
        this.b = new RollingPagerListenrt();
    }

    protected void onMeasure(int i, int i2) {
        if (View.MeasureSpec.getMode(i2) == Integer.MIN_VALUE) {
            int i3 = 0;
            for (int i4 = 0; i4 < getChildCount(); i4++) {
                View childAt = getChildAt(i4);
                childAt.measure(i, View.MeasureSpec.makeMeasureSpec(0, 0));
                int measuredHeight = childAt.getMeasuredHeight();
                if (measuredHeight > i3) {
                    i3 = measuredHeight;
                }
                if (measuredHeight > 10 && this.a) {
                    this.b.a(0);
                    this.a = false;
                }
            }
            i2 = View.MeasureSpec.makeMeasureSpec(i3, 1073741824);
        }
        super.onMeasure(i, i2);
    }

    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
    }

    public void setData(List<Frame> list, Context context, String str, MBNativeRollView.a aVar) {
        if (list == null || list.size() == 0) {
            throw new NegativeArraySizeException("ad date is null or size is 0");
        }
        RollingAdapter rollingAdapter = new RollingAdapter(list);
        if (aVar != null) {
            rollingAdapter.a(aVar);
        }
        setAdapter(rollingAdapter);
        this.b.a(list, context, str);
        if (this.c != null) {
            this.b.a(this.c);
        }
        setOnPageChangeListener(this.b);
        if (this.a) {
            this.b.a(0);
            this.a = false;
        }
    }
}
